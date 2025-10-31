package org.bibliotecavirtual.inventories.datasources;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.List;
import java.util.Optional;

import org.bibliotecavirtual.PropertiesLoader;
import org.bibliotecavirtual.inventories.models.Inventory;

public class InventoryDatasource {
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

                // Agregar la clase Inventory
                configuration.addAnnotatedClass(Inventory.class);

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

                    // Test 3: Verificar tabla inventories
                    Long count = ((Number) testSession.createNativeQuery("SELECT COUNT(*) FROM inventories").getSingleResult()).longValue();
                    System.out.println("✅ Test 3: Tabla inventories encontrada con " + count + " registros");

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

    public Inventory save(Inventory inventory) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(inventory);
            transaction.commit();
            return inventory;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Error saving inventory: " + e.getMessage(), e);
        } finally {
            session.close();
        }
    }

    public Optional<Inventory> findById(Long id) {
        Session session = sessionFactory.openSession();
        try {
            Inventory inventory = session.get(Inventory.class, id);
            return Optional.ofNullable(inventory);
        } catch (Exception e) {
            throw new RuntimeException("Error finding inventory by id: " + e.getMessage(), e);
        } finally {
            session.close();
        }
    }

    public List<Inventory> findAll() {
        Session session = sessionFactory.openSession();
        try {
            Query<Inventory> query = session.createQuery("FROM Inventory", Inventory.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error finding all inventories: " + e.getMessage(), e);
        } finally {
            session.close();
        }
    }

    public Inventory update(Inventory inventory) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Inventory updatedInventory = session.merge(inventory);
            transaction.commit();
            return updatedInventory;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Error updating inventory: " + e.getMessage(), e);
        } finally {
            session.close();
        }
    }

    public boolean deleteById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Inventory inventory = session.get(Inventory.class, id);
            if (inventory != null) {
                session.remove(inventory);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Error deleting inventory: " + e.getMessage(), e);
        } finally {
            session.close();
        }
    }

    public List<Inventory> findByCategory(String category) {
        Session session = sessionFactory.openSession();
        try {
            Query<Inventory> query = session.createQuery("FROM Inventory WHERE category = :category", Inventory.class);
            query.setParameter("category", category);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error finding inventories by category: " + e.getMessage(), e);
        } finally {
            session.close();
        }
    }

    public List<Inventory> findByStatus(boolean status) {
        Session session = sessionFactory.openSession();
        try {
            Query<Inventory> query = session.createQuery("FROM Inventory WHERE status = :status", Inventory.class);
            query.setParameter("status", status);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error finding inventories by status: " + e.getMessage(), e);
        } finally {
            session.close();
        }
    }

    public List<Inventory> findByTitle(String title) {
        Session session = sessionFactory.openSession();
        try {
            Query<Inventory> query = session.createQuery("FROM Inventory WHERE title LIKE :title", Inventory.class);
            query.setParameter("title", "%" + title + "%");
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error finding inventories by title: " + e.getMessage(), e);
        } finally {
            session.close();
        }
    }
}
