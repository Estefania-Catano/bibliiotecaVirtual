package Inventory.models;

public class Inventory {
    private String title;
    private String category;
    private boolean status;

    public Inventory(String title, String category, boolean status) {
        this.title = title;
        this.category = category;
        this.status = status;
    }

    public Inventory() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return title + " (" + category + ") - " + (status ? "borrowed" : "available");
    }
}
