package org.bibliotecavirtual.inventories.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

public class InventoryRequest {
    
    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;

    @Size(max = 500, message = "Category cannot exceed 500 characters")
    private String category;
    
    @NotNull(message = "Status is required")
    private boolean status;

    public InventoryRequest(String title, String category, boolean status) {
        this.title = title;
        this.category = category;
        this.status = status;
    }

    public InventoryRequest() {

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
