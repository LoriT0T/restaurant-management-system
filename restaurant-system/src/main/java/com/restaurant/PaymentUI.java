package com.restaurant;

/**
 * PaymentUI class for managing payment interface
 */
public class PaymentUI {
    // Attributes
    private int uiID;
    private String uiStatus;
    private String displayType;
    
    /**
     * Constructor for PaymentUI
     */
    public PaymentUI() {
        this.uiID = generateUIID();
        this.uiStatus = "Active";
        this.displayType = "Standard";
    }
    
    /**
     * Constructor with specified ID
     * @param uiID The UI identifier
     */
    public PaymentUI(int uiID) {
        this.uiID = uiID;
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
     * Displays the payment form for a given order
     * @param orderId The order requiring payment
     */
    public void displayPaymentForm(int orderId) {
        System.out.println("Displaying payment form for order: " + orderId);
        // In a real application, this would render the payment UI
    }
    
    /**
     * Captures payment details input
     * @param paymentMethod The selected payment method
     * @return Payment details as a formatted string
     */
    public String inputPaymentDetails(String paymentMethod) {
        System.out.println("Capturing payment details for " + paymentMethod);
        // In a real application, this would capture payment information
        return "Sample payment details for " + paymentMethod;
    }
    
    /**
     * Confirms that payment has been processed
     * @param orderId The ID of the paid order
     * @param amount The payment amount
     * @return true if confirmation is successful
     */
    public boolean confirmPayment(int orderId, double amount) {
        System.out.println("Confirming payment of $" + amount + " for order: " + orderId);
        // In a real application, this would update UI state and possibly print receipt
        return true;
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