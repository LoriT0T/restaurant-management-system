package com.resturant;

public class OrderItem {

    // Attributes
    private String itemName;
    private String status;

    // Constructor
    public OrderItem(String itemName) {
        this.itemName = itemName;
        this.status = "Pending";
    }

    // Basic getters and setters
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Method stubs
    public void updateStatus() {
        // Implementation to be added
    }

    public boolean checkQuality() {
        // Implementation to be added
        return true;
    }

    // Additional methods will be implemented later
}
