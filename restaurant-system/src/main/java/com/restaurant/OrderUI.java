package com.restaurant;

public class OrderUI {

    // Attributes
    private int uiID;
    private String uiStatus;
    private String displayType;

    // Constructor
    public OrderUI() {
        this.uiID = generateUIID();  // Initialize with a method
        this.uiStatus = "Active";
        this.displayType = "Standard";
    }

    // Constructor with explicit ID
    public OrderUI(int uiID) {
        this.uiID = uiID;
        this.uiStatus = "Active";
        this.displayType = "Standard";
    }

    // Generate a UI ID
    private int generateUIID() {
        // Simple ID generation - in a real system, this might come from a database sequence
        return 0;
    }

    // Getters and setters
    public int getUiID() {
        return uiID;
    }

    public String getUiStatus() {
        return uiStatus;
    }

    public void setUiStatus(String uiStatus) {
        this.uiStatus = uiStatus;
    }

    public String getDisplayType() {
        return displayType;
    }

    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }

    // Method stubs
    public void displayOrderForm() {
        // Implementation to be added
    }

    public void inputOrderDetails() {
        // Implementation to be added
    }
}
