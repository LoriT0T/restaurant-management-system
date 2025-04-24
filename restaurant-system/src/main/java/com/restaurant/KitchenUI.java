package com.resturant;

public class KitchenUI {

    // Attributes
    private int uiID;
    private String uiStatus;
    private String displayType;

    // Constructor
    public KitchenUI() {
        this.uiID = generateUIID();  // Initialize with a method
        this.uiStatus = "Active";
        this.displayType = "Standard";
    }

    // Constructor with explicit ID
    public KitchenUI(int uiID) {
        this.uiID = uiID;
        this.uiStatus = "Active";
        this.displayType = "Standard";
    }

    // Generate a UI ID
    private int generateUIID() {
        // Simple ID generation
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
    public void displayOrderQueue() {
        // Implementation to be added
    }

    public void updateOrderStatus() {
        // Implementation to be added
    }
}
