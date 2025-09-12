package models.user;


public class UserUseCase {
    
    private User usuarioValido;

    public UserUseCase() {
        
        inicializarUsuario();
        
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
}
