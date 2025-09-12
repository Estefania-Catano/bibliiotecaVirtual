package view;

import models.book.Book;

import java.util.List;
import java.util.Scanner;

// BibliotecaView.java
public class BibliotecaView {
    private Scanner sc;

    public BibliotecaView() {
        sc = new Scanner(System.in);
    }

    public void mostrarBienvenida() {
        System.out.println("Bienvenido a la biblioteca virtual");
    }

    public String solicitarUsuario() {
        System.out.print("Ingrese su nombre de usuario: ");
        return sc.nextLine();
    }

    public String solicitarPassword() {
        System.out.print("Ingrese su contraseña: ");
        return sc.nextLine();
    }

    public void mostrarLoginExitoso(String nombreUsuario) {
        System.out.println("Bienvenido " + nombreUsuario);
        System.out.println("Ingreso exitoso");
    }

    public void mostrarLoginFallido(int intentosRestantes) {
        System.out.println("Usuario o contraseña incorrectos. Intentos restantes: " + intentosRestantes);
    }

    public void mostrarBloqueoUsuario() {
        System.out.println("Ha superado el número máximo de intentos. Comuníquese con el administrador.");
    }

    public int mostrarMenuPrincipal() {
        System.out.println("\nSeleccione una opción:");
        System.out.println("1. Realizar un préstamo de libro");
        System.out.println("2. Realizar una devolución de libro");
        System.out.println("3. Consultar préstamos activos");
        System.out.println("4. Consultar libros disponibles");
        System.out.println("5. Salir");
        return sc.nextInt();
    }

    public int mostrarCategorias(List<String> categorias) {
        System.out.println("\nCategorías disponibles:");
        for (int i = 0; i < categorias.size(); i++) {
            System.out.println((i + 1) + ". " + categorias.get(i));
        }
        return sc.nextInt();
    }

    public int mostrarLibros(List<Book> libros) {
        System.out.println("\n0. Regresar");
        for (int i = 0; i < libros.size(); i++) {
            System.out.println((i + 1) + ". " + libros.get(i).getTitulo());
        }
        return sc.nextInt();
    }

    public void mostrarPrestamoExitoso(String titulo) {
        System.out.println("Ha seleccionado el libro '" + titulo + "'.");
    }

    public void mostrarLibroYaPrestado(String titulo) {
        System.out.println("El libro '" + titulo + "' ya está prestado.");
    }

    public void mostrarDevolucionExitosa(String titulo) {
        System.out.println("Ha devuelto el libro '" + titulo + "'.");
    }

    public void mostrarLibroNoTomado(String titulo) {
        System.out.println("No ha tomado el libro '" + titulo + "'. Por favor, verifique el libro que desea devolver.");
    }

    public void mostrarLibrosPrestados(List<Book> librosPrestados) {
        System.out.println("\nLibros prestados:");
        if (librosPrestados.isEmpty()) {
            System.out.println("No hay libros prestados actualmente.");
        } else {
            for (Book libro : librosPrestados) {
                System.out.println("- " + libro.getTitulo());
            }
        }
    }

    public void mostrarLibrosDisponibles(List<Book> librosDisponibles) {
        System.out.println("\nLibros disponibles:");
        if (librosDisponibles.isEmpty()) {
            System.out.println("No hay libros disponibles en esta categoría.");
        } else {
            for (Book libro : librosDisponibles) {
                System.out.println("- " + libro.getTitulo() + " está disponible.");
            }
        }
    }

    public void mostrarRegreso() {
        System.out.println("Regresando al menú principal.");
    }

    public void mostrarOpcionInvalida() {
        System.out.println("Opción no válida.");
    }

    public void mostrarSalida(int cantidadPrestamos) {
        System.out.println("Saliendo del sistema. Gracias por usar la biblioteca virtual.");
        System.out.println("Usted ha realizado " + cantidadPrestamos + " préstamos de libros.");
    }

    public void cerrarScanner() {
        sc.close();
    }


}

