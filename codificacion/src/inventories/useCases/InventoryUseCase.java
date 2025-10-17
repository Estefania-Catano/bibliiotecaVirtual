package inventories.useCases;
import inventories.models.Inventory;


import java.util.ArrayList;
import java.util.List;

public class InventoryUseCase {
    private final List<Inventory> books;



    public InventoryUseCase() {
        this.books = new ArrayList<>();
    }

    // Create New Book

    public String create (Inventory book) {
        try {
            this.books.add(book);
            return "Book created successfully";
        } catch (Exception e) {
            return "Error creating book: " + e.getMessage();
        }

    }

    // read Books
    public String all(){
        try {
            if (books.isEmpty()) {
                return "No registered books.";
            }

            StringBuilder result = new StringBuilder();
            result.append("=== REGISTERED BOOKS ===\n");
            for (int i = 0; i < books.size(); i++) {
                Inventory book = books.get(i);
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
    public String update (int index, Inventory bookIn) {
        try {
            if (index >= 0 && index < books.size()) {
                Inventory book = books.get(index);
                book.setTitle(bookIn.getTitle());
                book.setCategory(bookIn.getCategory());
                book.setStatus(bookIn.isStatus());
                return "Book updated successfully: " + book.getTitle() + " (" + book.getCategory() + ")";
            } else {
                return "Invalid book index.";
            }
        } catch (Exception e) {
            return "Error updating book: " + e.getMessage();
        }
    }

    // Delete Book
    public String delete (int index) {
        try {
            if (index >= 0 && index < books.size()) {
                String bookTitle = books.get(index).getTitle();
                books.remove(index);
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
            if (index >= 0 && index < books.size()) {
                return books.get(index);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
