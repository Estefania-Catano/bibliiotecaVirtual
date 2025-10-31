package org.bibliotecavirtual.inventories.models;

public class InventoryResponse {
    private Long id;
    private String title;
    private String category;
    private boolean status;

    public InventoryResponse(Inventory inventory) {
        this.id = inventory.getId() ;
        this.title = inventory.getTitle();
        this.category = inventory.getCategory();
        this.status = inventory.isStatus();
    }

    public InventoryResponse() {

    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
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
