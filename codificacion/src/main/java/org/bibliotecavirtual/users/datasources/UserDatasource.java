package org.bibliotecavirtual.users.datasources;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.List;
import java.util.Optional;

import org.bibliotecavirtual.PropertiesLoader;
import org.bibliotecavirtual.users.models.User;

public class UserDatasource {
    private static SessionFactory sessionFactory;

    static {
        initializeSessionFactory();
    }

    private static void initializeSessionFactory() {
        int maxRetries = 15;
        int retryDelay = 2000; // 2 segundos

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                System.out.println("🔄 Intento " + attempt + " de " + maxRetries + " para inicializar SessionFactory...");

                Configuration configuration = new Configuration();

                // Detectar si estamos en Docker o local
                String dbHost = isDockerEnvironment() ? "postgres" : "localhost";
                String dbUrl = PropertiesLoader.getProperty("db.url").replace("localhost", dbHost);

                System.out.println("🌐 Conectando a: " + dbUrl);
                System.out.println("👤 Usuario: " + PropertiesLoader.getProperty("db.username"));
                System.out.println("🐳 Entorno Docker: " + isDockerEnvironment());

                // Configuración básica y robusta
                configuration.setProperty("hibernate.connection.driver_class", PropertiesLoader.getProperty("db.driver"));
                configuration.setProperty("hibernate.connection.url", dbUrl);
                configuration.setProperty("hibernate.connection.username", PropertiesLoader.getProperty("db.username"));
                configuration.setProperty("hibernate.connection.password", PropertiesLoader.getProperty("db.password"));

                // Configuración básica de Hibernate
                configuration.setProperty("hibernate.dialect", PropertiesLoader.getProperty("hibernate.dialect"));
                configuration.setProperty("hibernate.show_sql", PropertiesLoader.getProperty("hibernate.show_sql"));
                configuration.setProperty("hibernate.format_sql", PropertiesLoader.getProperty("hibernate.format_sql"));
                configuration.setProperty("hibernate.hbm2ddl.auto", PropertiesLoader.getProperty("hibernate.hbm2ddl.auto"));

                // Configuración de charset
                configuration.setProperty("hibernate.connection.charset", PropertiesLoader.getProperty("hibernate.connection.charset"));
                configuration.setProperty("hibernate.connection.characterEncoding", PropertiesLoader.getProperty("hibernate.connection.characterEncoding"));
                configuration.setProperty("hibernate.connection.useUnicode", PropertiesLoader.getProperty("hibernate.connection.useUnicode"));

                // Configuración de pool de conexiones HikariCP
                configuration.setProperty("hibernate.connection.provider_class", "org.hibernate.hikaricp.internal.HikariCPConnectionProvider");
                configuration.setProperty("hibernate.hikari.minimumIdle", PropertiesLoader.getProperty("hibernate.hikari.minimumIdle"));
                configuration.setProperty("hibernate.hikari.maximumPoolSize", PropertiesLoader.getProperty("hibernate.hikari.maximumPoolSize"));
                configuration.setProperty("hibernate.hikari.idleTimeout", PropertiesLoader.getProperty("hibernate.hikari.idleTimeout"));
                configuration.setProperty("hibernate.hikari.connectionTimeout", PropertiesLoader.getProperty("hibernate.hikari.connectionTimeout"));
                configuration.setProperty("hibernate.hikari.maxLifetime", PropertiesLoader.getProperty("hibernate.hikari.maxLifetime"));
                configuration.setProperty("hibernate.hikari.leakDetectionThreshold", PropertiesLoader.getProperty("hibernate.hikari.leakDetectionThreshold"));

                // Configuración adicional para estabilidad
                configuration.setProperty("hibernate.connection.autocommit", "true");
                configuration.setProperty("hibernate.jdbc.batch_size", "20");
                configuration.setProperty("hibernate.order_inserts", "true");
                configuration.setProperty("hibernate.order_updates", "true");

                // Agregar la clase User
                configuration.addAnnotatedClass(User.class);

                // Intentar crear SessionFactory
                sessionFactory = configuration.buildSessionFactory();

                // Probar la conexión con múltiples queries
                try (Session testSession = sessionFactory.openSession()) {
                    // Test 1: Query simple
                    testSession.createNativeQuery("SELECT 1").getSingleResult();
                    System.out.println("✅ Test 1: Query simple exitosa");

                    // Test 2: Verificar base de datos
                    String dbName = (String) testSession.createNativeQuery("SELECT current_database()").getSingleResult();
                    System.out.println("✅ Test 2: Base de datos conectada: " + dbName);

                    // Test 3: Verificar tabla users
                    Long count = ((Number) testSession.createNativeQuery("SELECT COUNT(*) FROM users").getSingleResult()).longValue();
                    System.out.println("✅ Test 3: Tabla users encontrada con " + count + " registros");

                    System.out.println("🎉 SessionFactory inicializado y todas las pruebas exitosas!");
                    return; // Éxito, salir del método
                }

            } catch (Exception e) {
                System.err.println("❌ Error en intento " + attempt + ": " + e.getClass().getSimpleName() + " - " + e.getMessage());

                // Log detallado del error
                if (e.getCause() != null) {
                    System.err.println("🔍 Causa raíz: " + e.getCause().getClass().getSimpleName() + " - " + e.getCause().getMessage());
                }

                if (attempt == maxRetries) {
                    System.err.println("💥 Falló después de " + maxRetries + " intentos");
                    System.err.println("🔍 Último error completo:");
                    e.printStackTrace();
                    throw new ExceptionInInitializerError(e);
                } else {
                    System.out.println("⏳ Esperando " + (retryDelay / 1000) + " segundos antes del siguiente intento...");
                    try {
                        Thread.sleep(retryDelay);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        throw new ExceptionInInitializerError(ie);
                    }
                }
            }
        }
    }

    private static boolean isDockerEnvironment() {
        // Verificar múltiples indicadores de Docker
        boolean dockerEnv = System.getenv("DB_HOST") != null ||
                System.getenv("CONTAINER") != null ||
                System.getenv("HOSTNAME") != null && System.getenv("HOSTNAME").contains("docker") ||
                System.getenv("HOSTNAME") != null && System.getenv("HOSTNAME").contains("java-app") ||
                System.getProperty("java.class.path").contains("docker") ||
                System.getProperty("user.dir").contains("docker") ||
                System.getProperty("java.class.path").contains("app");

        System.out.println("🔍 Detección de entorno Docker:");
        System.out.println("  - DB_HOST: " + System.getenv("DB_HOST"));
        System.out.println("  - CONTAINER: " + System.getenv("CONTAINER"));
        System.out.println("  - HOSTNAME: " + System.getenv("HOSTNAME"));
        System.out.println("  - user.dir: " + System.getProperty("user.dir"));
        System.out.println("  - class.path contiene 'docker': " + System.getProperty("java.class.path").contains("docker"));
        System.out.println("  - class.path contiene 'app': " + System.getProperty("java.class.path").contains("app"));
        System.out.println("  - Resultado final: " + dockerEnv);

        return dockerEnv;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void closeSessionFactory() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }

    public User save(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Error saving user: " + e.getMessage(), e);
        } finally {
            session.close();
        }
    }

    public Optional<User> findById(Long id) {
        Session session = sessionFactory.openSession();
        try {
            User user = session.get(User.class, id);
            return Optional.ofNullable(user);
        } catch (Exception e) {
            throw new RuntimeException("Error finding user by id: " + e.getMessage(), e);
        } finally {
            session.close();
        }
    }

    public List<User> findAll() {
        Session session = sessionFactory.openSession();
        try {
            Query<User> query = session.createQuery("FROM User", User.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error finding all users: " + e.getMessage(), e);
        } finally {
            session.close();
        }
    }

    public User update(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            User updatedUser = session.merge(user);
            transaction.commit();
            return updatedUser;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Error updating user: " + e.getMessage(), e);
        } finally {
            session.close();
        }
    }

    public boolean deleteById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.remove(user);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Error deleting user: " + e.getMessage(), e);
        } finally {
            session.close();
        }
    }

    public Optional<User> findByEmail(String email) {
        Session session = sessionFactory.openSession();
        try {
            Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
            query.setParameter("email", email);
            User user = query.uniqueResult();
            return Optional.ofNullable(user);
        } catch (Exception e) {
            throw new RuntimeException("Error finding user by email: " + e.getMessage(), e);
        } finally {
            session.close();
        }
    }
}
