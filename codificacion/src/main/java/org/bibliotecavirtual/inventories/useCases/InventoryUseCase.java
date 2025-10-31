package org.bibliotecavirtual.inventories.useCases;

import org.bibliotecavirtual.inventories.models.Inventory;
import org.bibliotecavirtual.inventories.datasources.InventoryDatasource;

public class InventoryUseCase {
    private final InventoryDatasource inventoryDatasource;

    public InventoryUseCase() {
        this.inventoryDatasource = new InventoryDatasource();
    }

    // Create New Book

    public String create(Inventory book) {
        try {
            this.inventoryDatasource.save(book);
            return "Book created successfully";
        } catch (Exception e) {
            return "Error creating book: " + e.getMessage();
        }

    }

    // read Books
    public String all() {
        try {
            if (inventoryDatasource.findAll().isEmpty()) {
                return "No registered books.";
            }

            StringBuilder result = new StringBuilder();
            result.append("=== REGISTERED BOOKS ===\n");
            for (int i = 0; i < inventoryDatasource.findAll().size(); i++) {
                Inventory book = inventoryDatasource.findAll().get(i);
                result.append(i + 1)
                        .append(". Book: ")
                        .append(book.getTitle())
                        .append(" (")
                        .append(book.getCategory())
                        .append(") - ")
                        .append(book.isStatus() ? "Borrowed" : "Available")
                        .append("\n");
            }
            return result.toString();

        } catch (Exception e) {
            return "Error reading books: " + e.getMessage();
        }
    }

    // Update Book
    public String update(int index, Inventory bookIn) {
        try {
            if (index >= 0 && index < inventoryDatasource.findAll().size()) {
                Inventory book = inventoryDatasource.findAll().get(index);
                book.setTitle(bookIn.getTitle());
                book.setCategory(bookIn.getCategory());
                book.setStatus(bookIn.isStatus());
                // Persistir los cambios en la datasource
                this.inventoryDatasource.update(book);
                return "Book updated successfully: " + book.getTitle() + " (" + book.getCategory() + ")";
            } else {
                return "Invalid book index.";
            }
        } catch (Exception e) {
            return "Error updating book: " + e.getMessage();
        }
    }

    // Delete Book
    public String delete(int index) {
        try {
            if (index >= 0 && index < inventoryDatasource.findAll().size()) {
                Inventory book = inventoryDatasource.findAll().get(index);
                String bookTitle = book.getTitle();
                // Usar el id real de la entidad para eliminar
                inventoryDatasource.deleteById(book.getId());
                return "Book '" + bookTitle + "' has been deleted successfully";
            } else {
                return "Invalid book index.";
            }
        } catch (Exception e) {
            return "Error deleting book: " + e.getMessage();
        }
    }

    // Get Book by index
    public Inventory getBook(int index) {
        try {
            if (index >= 0 && index < inventoryDatasource.findAll().size()) {
                return inventoryDatasource.findAll().get(index);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
