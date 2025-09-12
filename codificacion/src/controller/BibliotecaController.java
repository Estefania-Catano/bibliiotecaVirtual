package controller;


import models.book.Book;
import useCases.BookUseCases.BookUseCase;
import useCases.UserUseCases.UserUseCase;
import view.BibliotecaView;

import java.util.List;

public class BibliotecaController {
    private BookUseCase bookService;
    private UserUseCase userService;
    private BibliotecaView view;
    private static final int MAX_INTENTOS = 3;

    public BibliotecaController() {
        bookService = new BookUseCase();
        userService = new UserUseCase();
        view = new BibliotecaView();
    }

    public void iniciarSistema() {
        view.mostrarBienvenida();

        if (autenticarUsuario()) {
            ejecutarMenuPrincipal();
        }

        view.cerrarScanner();
    }

    private boolean autenticarUsuario() {
        int intentos = 0;

        while (intentos < MAX_INTENTOS) {
            String usuario = view.solicitarUsuario();
            String password = view.solicitarPassword();

            if (userService.autenticarUsuario(usuario, password)) {
                view.mostrarLoginExitoso(userService.getNombreUsuario());
                return true;
            } else {
                intentos++;
                view.mostrarLoginFallido(MAX_INTENTOS - intentos);
            }
        }

        view.mostrarBloqueoUsuario();
        return false;
    }

    private void ejecutarMenuPrincipal() {
        int opcion;

        do {
            opcion = view.mostrarMenuPrincipal();

            switch (opcion) {
                case 1:
                    realizarPrestamo();
                    break;
                case 2:
                    realizarDevolucion();
                    break;
                case 3:
                    consultarPrestamosActivos();
                    break;
                case 4:
                    consultarLibrosDisponibles();
                    break;
                case 5:
                    view.mostrarSalida(bookService.getCantidadPrestamos());
                    break;
                default:
                    view.mostrarOpcionInvalida();
                    break;
            }
        } while (opcion != 5);
    }

    private void realizarPrestamo() {
        List<String> categorias = bookService.getCategorias();
        int opcionCategoria = view.mostrarCategorias(categorias);

        if (opcionCategoria >= 1 && opcionCategoria <= categorias.size()) {
            String categoria = categorias.get(opcionCategoria - 1);
            List<Book> librosCategoria = bookService.getLibrosPorCategoria(categoria);

            int opcionLibro = view.mostrarLibros(librosCategoria);

            if (opcionLibro == 0) {
                view.mostrarRegreso();
            } else if (opcionLibro >= 1 && opcionLibro <= librosCategoria.size()) {
                String titulo = librosCategoria.get(opcionLibro - 1).getTitulo();

                if (bookService.prestarLibro(titulo)) {
                    view.mostrarPrestamoExitoso(titulo);
                } else {
                    view.mostrarLibroYaPrestado(titulo);
                }
            } else {
                view.mostrarOpcionInvalida();
            }
        }
    }

    private void realizarDevolucion() {
        List<String> categorias = bookService.getCategorias();
        int opcionCategoria = view.mostrarCategorias(categorias);

        if (opcionCategoria >= 1 && opcionCategoria <= categorias.size()) {
            String categoria = categorias.get(opcionCategoria - 1);
            List<Book> librosCategoria = bookService.getLibrosPorCategoria(categoria);

            int opcionLibro = view.mostrarLibros(librosCategoria);

            if (opcionLibro == 0) {
                view.mostrarRegreso();
            } else if (opcionLibro >= 1 && opcionLibro <= librosCategoria.size()) {
                String titulo = librosCategoria.get(opcionLibro - 1).getTitulo();

                if (bookService.devolverLibro(titulo)) {
                    view.mostrarDevolucionExitosa(titulo);
                } else {
                    view.mostrarLibroNoTomado(titulo);
                }
            } else {
                view.mostrarOpcionInvalida();
            }
        }
    }

    private void consultarPrestamosActivos() {
        List<Book> librosPrestados = bookService.getLibrosPrestados();
        view.mostrarLibrosPrestados(librosPrestados);
    }

    private void consultarLibrosDisponibles() {
        List<String> categorias = bookService.getCategorias();
        int opcionCategoria = view.mostrarCategorias(categorias);

        if (opcionCategoria >= 1 && opcionCategoria <= categorias.size()) {
            String categoria = categorias.get(opcionCategoria - 1);
                List<Book> librosDisponibles = bookService.getLibrosDisponiblesPorCategoria(categoria);
            view.mostrarLibrosDisponibles(librosDisponibles);
        }
    }
}