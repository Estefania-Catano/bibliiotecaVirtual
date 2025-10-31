package org.bibliotecavirtual.users.controllers;
import org.bibliotecavirtual.users.models.User;
import org.bibliotecavirtual.users.useCases.UserUseCase;
import org.bibliotecavirtual.inventories.controllers.InventoryController;
import java.util.Scanner;

public class UserController {
    private Scanner sc;
    public UserUseCase userUseCase;
    private InventoryController inventoryController;
    private static final int max_tries = 3;

    public UserController() {
        this.userUseCase = new UserUseCase();
        this.inventoryController = new InventoryController();
        sc = new Scanner(System.in);
    }

    // Read Users
    public String all() {
        return this.userUseCase.all();
    }

    // Create New User
    public String Create(String name, String password) {
        return this.userUseCase.create(new User(name, password));
    }

    // Update User
    public String Update(int index, String name, String password) {
        return this.userUseCase.update(index, new User(name, password));
    }

    // Delete User
    public String Delete(int index) {
        return this.userUseCase.delete(index);
    }

    public void starSystem(){
        System.out.println("Welcome to the virtual library system");
        
        boolean running = true;
        while (running) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Create new user");
            System.out.println("2. Login as admin user");
            System.out.println("3. View registered users");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            
            String option = sc.nextLine();
            
            switch (option) {
                case "1":
                    createUser();
                    break;
                case "2":
                    if (authenticate()) {
                        System.out.println("Welcome to the admin system!");
                        inventoryController.adminSystem();
                    }
                    break;
                case "3":
                    System.out.println(all());
                    break;
                case "4":
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please select a valid option.");
            }
        }
    }
    
    private void createUser() {
        System.out.println("\n=== CREATE NEW USER ===");
        System.out.print("Enter username: ");
        String name = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        
        String result = Create(name, password);
        System.out.println(result);
    }


    private boolean authenticate() {
        int tries = 0;

        while (tries < max_tries) {
            System.out.print("Enter your username: ");
            String user = sc.nextLine();
            System.out.print("Enter your password: ");
            String password = sc.nextLine();

            if (userUseCase.authenticate(user, password)) {
                System.out.println("Login successful!");
                return true;
            } else {
                tries++;
                System.out.println("Invalid credentials. Please try again. " + (max_tries - tries) + " attempts left.");
            }
        }
        System.out.println("Maximum login attempts exceeded. Exiting...");
        return false;
    }
}
