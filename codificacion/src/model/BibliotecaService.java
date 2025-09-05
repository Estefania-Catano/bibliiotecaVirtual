package model;
import java.util.*;

public class BibliotecaService {
    private List<Book> libros;
    private List<Book> librosPrestados;
    private User usuarioValido;

    public BibliotecaService() {
        inicializarLibros();
        inicializarUsuario();
        librosPrestados = new ArrayList<>();
    }

    private void inicializarLibros() {
        libros = new ArrayList<>();
        // Ciencia ficción
        libros.add(new Book("Dune", "Ciencia ficción"));
        libros.add(new Book("Neuromante", "Ciencia ficción"));
        libros.add(new Book("Fundación", "Ciencia ficción"));

        // Juveniles
        libros.add(new Book("Bajo la misma estrella", "Juveniles"));
        libros.add(new Book("El corredor del laberinto", "Juveniles"));
        libros.add(new Book("Divergente", "Juveniles"));

        // Infantiles
        libros.add(new Book("El principito", "Infantiles"));
        libros.add(new Book("Donde viven los monstruos", "Infantiles"));
        libros.add(new Book("Harry Potter y la piedra filosofal", "Infantiles"));
    }

    private void inicializarUsuario() {
        usuarioValido = new User("Andrea.Benitez", "1234");
    }

    public boolean autenticarUsuario(String nombre, String password) {
        return usuarioValido.validarCredenciales(nombre, password);
    }

    public String getNombreUsuario() {
        return usuarioValido.getNombre();
    }

    public List<String> getCategorias() {
        return Arrays.asList("Ciencia ficción", "Juveniles", "Infantiles");
    }

    public List<Book> getLibrosPorCategoria(String categoria) {
        return libros.stream()
                .filter(libro -> libro.getCategoria().equals(categoria))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    public boolean prestarLibro(String titulo) {
        Optional<Book> libro = libros.stream()
                .filter(l -> l.getTitulo().equals(titulo) && !l.isPrestado())
                .findFirst();

        if (libro.isPresent()) {
            libro.get().setPrestado(true);
            librosPrestados.add(libro.get());
            return true;
        }
        return false;
    }

    public boolean devolverLibro(String titulo) {
        Optional<Book> libro = librosPrestados.stream()
                .filter(l -> l.getTitulo().equals(titulo))
                .findFirst();

        if (libro.isPresent()) {
            libro.get().setPrestado(false);
            librosPrestados.remove(libro.get());
            return true;
        }
        return false;
    }

    public List<Book> getLibrosPrestados() {
        return new ArrayList<>(librosPrestados);
    }

    public List<Book> getLibrosDisponiblesPorCategoria(String categoria) {
        return libros.stream()
                .filter(libro -> libro.getCategoria().equals(categoria) && !libro.isPrestado())
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    public int getCantidadPrestamos() {
        return librosPrestados.size();
    }
}