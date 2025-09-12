package models.user;

public class User {
    private String nombre;
    private String password;

    public User(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
    }

    public String getNombre() { return nombre; }
    public String getPassword() { return password; }

    public boolean validarCredenciales(String nombre, String password) {
        return this.nombre.equals(nombre) && this.password.equals(password);
    }
}
