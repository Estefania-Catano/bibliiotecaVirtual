package controller;

import model.BibliotecaService;
import model.Book;
import view.BibliotecaView;

import java.util.List;

public class BibliotecaController {
    private BibliotecaService service;
    private BibliotecaView view;
    private static final int MAX_INTENTOS = 3;

    public BibliotecaController() {
        service = new BibliotecaService();
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

            if (service.autenticarUsuario(usuario, password)) {
                view.mostrarLoginExitoso(service.getNombreUsuario());
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
                    view.mostrarSalida(service.getCantidadPrestamos());
                    break;
                default:
                    view.mostrarOpcionInvalida();
                    break;
            }
        } while (opcion != 5);
    }

    private void realizarPrestamo() {
        List<String> categorias = service.getCategorias();
        int opcionCategoria = view.mostrarCategorias(categorias);

        if (opcionCategoria >= 1 && opcionCategoria <= categorias.size()) {
            String categoria = categorias.get(opcionCategoria - 1);
            List<Book> librosCategoria = service.getLibrosPorCategoria(categoria);

            int opcionLibro = view.mostrarLibros(librosCategoria);

            if (opcionLibro == 0) {
                view.mostrarRegreso();
            } else if (opcionLibro >= 1 && opcionLibro <= librosCategoria.size()) {
                String titulo = librosCategoria.get(opcionLibro - 1).getTitulo();

                if (service.prestarLibro(titulo)) {
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
        List<String> categorias = service.getCategorias();
        int opcionCategoria = view.mostrarCategorias(categorias);

        if (opcionCategoria >= 1 && opcionCategoria <= categorias.size()) {
            String categoria = categorias.get(opcionCategoria - 1);
            List<Book> librosCategoria = service.getLibrosPorCategoria(categoria);

            int opcionLibro = view.mostrarLibros(librosCategoria);

            if (opcionLibro == 0) {
                view.mostrarRegreso();
            } else if (opcionLibro >= 1 && opcionLibro <= librosCategoria.size()) {
                String titulo = librosCategoria.get(opcionLibro - 1).getTitulo();

                if (service.devolverLibro(titulo)) {
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
        List<Book> librosPrestados = service.getLibrosPrestados();
        view.mostrarLibrosPrestados(librosPrestados);
    }

    private void consultarLibrosDisponibles() {
        List<String> categorias = service.getCategorias();
        int opcionCategoria = view.mostrarCategorias(categorias);

        if (opcionCategoria >= 1 && opcionCategoria <= categorias.size()) {
            String categoria = categorias.get(opcionCategoria - 1);
            List<Book> librosDisponibles = service.getLibrosDisponiblesPorCategoria(categoria);
            view.mostrarLibrosDisponibles(librosDisponibles);
        }
    }
}