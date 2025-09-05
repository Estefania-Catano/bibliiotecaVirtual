package model;

public class Book {
    private String titulo;
    private String categoria;
    private boolean prestado;

    public Book(String titulo, String categoria) {
        this.titulo = titulo;
        this.categoria = categoria;
        this.prestado = false;
    }

    // Getters y Setters
    public String getTitulo() {
        return titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public boolean isPrestado() {
        return prestado;
    }

    public void setPrestado(boolean prestado) {
        this.prestado = prestado;
    }

    @Override
    public String toString() {
        return titulo + " (" + categoria + ") - " + (prestado ? "Prestado" : "Disponible");
    }
}
