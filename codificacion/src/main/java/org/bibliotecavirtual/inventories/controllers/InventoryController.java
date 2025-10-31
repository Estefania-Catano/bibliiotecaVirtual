package org.bibliotecavirtual.inventories.controllers;

import org.bibliotecavirtual.inventories.models.Inventory;
import org.bibliotecavirtual.inventories.useCases.InventoryUseCase;



import java.util.Scanner;

public class InventoryController {
    private Scanner sc;
    public InventoryUseCase InventoryUseCase;

    public InventoryController() {
        this.InventoryUseCase = new InventoryUseCase();
        sc = new Scanner(System.in);
    }

    // Read Inventory
    public String all() {
        return this.InventoryUseCase.all();
    }

    // Create New Inventory
    public String Create(String title, String category, boolean status) {
        return this.InventoryUseCase.create(new Inventory(title, category, status));
    }

    // Update Inventory
    public String Update(int index, String title, String category, boolean status) {
        return this.InventoryUseCase.update(index, new Inventory(title, category, status));
    }

    // Delete Inventory
    public String Delete(int index) {
        return this.InventoryUseCase.delete(index);
    }

    // Admin Menu

    public void adminSystem(){
        System.out.println("Welcome to the virtual library admin system");

        boolean running = true;
        while (running) {
            System.out.println("\n=== ADMIN SYSTEM MENU ===");
            System.out.println("1. View registered books");
            System.out.println("2. Add new book");
            System.out.println("3. Update book");
            System.out.println("4. Lend book");
            System.out.println("5. Delete book");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");

            String option = sc.nextLine();

            switch (option) {
                case "1":
                    System.out.println(all());
                    break;
                case "2":
                    createBook();
                    break;
                case "3":
                    updateBook();
                    break;
                case "4":
                    lendBook();
                    break;
                case "5":

                    deleteBook();
                    break;
                case "6":
                    System.out.println("Returning to main menu...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please select a valid option.");
            }
        }
    }

    // USER Menu

    public void userSystem(){
        System.out.println("Welcome to the virtual library user system");

        boolean running = true;
        while (running) {
            System.out.println("\n=== USER SYSTEM MENU ===");
            System.out.println("1. View registered books");
            System.out.println("2. Lend book");
            System.out.println("3. Return book");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");

            String option = sc.nextLine();

            switch (option) {
                case "1":
                    System.out.println(all());
                    break;


                case "2":
                    lendBook();
                    break;

                case "3":
                    returnBook();
                    break;


                case "4":
                    System.out.println("Returning to main menu...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please select a valid option.");
            }
        }
    }
    private void createBook() {
        System.out.println("\n=== CREATE NEW BOOK ===");
        System.out.print("Enter Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Category: ");
        String category = sc.nextLine();

        String result = Create(title, category, false);
        System.out.println(result);
    }
    
    private void updateBook() {
        System.out.println("\n=== UPDATE BOOK ===");
        System.out.println(all());
        System.out.print("Enter the number of the book to update: ");
        try {
            int index = Integer.parseInt(sc.nextLine()) - 1;
            System.out.print("Enter new title: ");
            String title = sc.nextLine();
            System.out.print("Enter new category: ");
            String category = sc.nextLine();
            System.out.print("Is the book borrowed? (true/false): ");
            boolean status = Boolean.parseBoolean(sc.nextLine());
            
            String result = Update(index, title, category, status);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }

    private  void  lendBook(){
        System.out.println("\n=== LEND BOOK ===");
        System.out.println(all());
        System.out.print("Enter the number of the book to lend: ");
        try {
            int index = Integer.parseInt(sc.nextLine()) - 1;
            
            
            Inventory currentBook = InventoryUseCase.getBook(index);
            if (currentBook != null) {
                String title = currentBook.getTitle();
                String category = currentBook.getCategory();
                boolean status = true; 
                
                String result = Update(index, title, category, status);
                System.out.println(result);
            } else {
                System.out.println("Invalid book index.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }

    private  void  returnBook(){
        System.out.println("\n=== RETURN BOOK ===");
        System.out.println(all());
        System.out.print("Enter the number of the book to return: ");
        try {
            int index = Integer.parseInt(sc.nextLine()) - 1;


            Inventory currentBook = InventoryUseCase.getBook(index);
            if (currentBook != null) {
                String title = currentBook.getTitle();
                String category = currentBook.getCategory();
                boolean status = false;

                String result = Update(index, title, category, status);
                System.out.println(result);
            } else {
                System.out.println("Invalid book index.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }
    
    private void deleteBook() {
        System.out.println("\n=== DELETE BOOK ===");
        System.out.println(all());
        System.out.print("Enter the number of the book to delete: ");
        try {
            int index = Integer.parseInt(sc.nextLine()) - 1;
            String result = Delete(index);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }
}
