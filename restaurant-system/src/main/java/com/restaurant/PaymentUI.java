package com.restaurant;

public class PaymentUI {

    // Attributes
    private int uiID;
    private String uiStatus;
    private String displayType;

    // Constructor
    public PaymentUI() {
        this.uiID = generateUIID();  // Initialize with a method
        this.uiStatus = "Active";
        this.displayType = "Standard";
    }

    // Constructor with explicit ID
    public PaymentUI(int uiID) {
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
    public void displayPaymentForm() {
        // Implementation to be added
    }

    public void inputPaymentDetails() {
        // Implementation to be added
    }
}
