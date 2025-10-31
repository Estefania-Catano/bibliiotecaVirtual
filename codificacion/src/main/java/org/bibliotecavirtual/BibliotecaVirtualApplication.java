package org.bibliotecavirtual;

import org.bibliotecavirtual.users.controllers.UserController;

public class BibliotecaVirtualApplication {
    public static void main(String[] args) {
        UserController controller = new UserController();
        controller.starSystem();
    }
}