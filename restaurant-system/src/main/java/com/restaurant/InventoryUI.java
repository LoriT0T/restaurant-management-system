package com.restaurant;

/**
 * InventoryUI class for managing inventory
 */
public class InventoryUI {
    // Attributes
    private int uiID;
    private String uiStatus;
    private String displayType;
    
    /**
     * Constructor for InventoryUI
     */
    public InventoryUI() {
        this.uiID = generateUIID();
        this.uiStatus = "Active";
        this.displayType = "Standard";
    }
    
    /**
     * Generates a unique ID for the UI
     * @return The generated ID
     */
    private int generateUIID() {
        return (int)(System.currentTimeMillis() % 10000);
    }
    
    /**
     * Displays current inventory status
     */
    public void displayInventoryStatus() {
        System.out.println("Displaying current inventory status");
        // In a real application, this would retrieve and display inventory data
    }
    
    /**
     * Displays low stock alerts
     */
    public void displayLowStockAlerts() {
        System.out.println("Displaying low stock alerts");
        // In a real application, this would show items below threshold
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
}