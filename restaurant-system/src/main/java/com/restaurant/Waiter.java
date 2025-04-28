package com.restaurant;

/**
 * Waiter class representing restaurant wait staff
 */
public class Waiter {
    // Attributes
    private int waiterID;
    private String name;
    private int staffID;
    
    /**
     * Constructor for Waiter
     * @param waiterID The unique ID for this waiter
     * @param name The waiter's name
     * @param staffID The general staff ID
     */
    public Waiter(int waiterID, String name, int staffID) {
        this.waiterID = waiterID;
        this.name = name;
        this.staffID = staffID;
    }
    
    /**
     * Notifies a customer about an update
     * @param customerId The customer to notify
     * @param message The notification message
     */
    public void notifyCustomer(int customerId, String message) {
        System.out.println("Waiter " + name + " notifying customer " + customerId + ": " + message);
        // In a real application, this might update a UI or send a notification
    }
    
    /**
     * Suggests alternatives when requested items are unavailable
     * @param unavailableItem The item that's not available
     * @return Array of suggested alternatives
     */
    public String[] suggestAlternatives(String unavailableItem) {
        System.out.println("Suggesting alternatives for " + unavailableItem);
        // In a real application, this would query similar items
        return new String[]{"Alternative 1", "Alternative 2"};
    }
    
    /**
     * Contacts a customer manually (phone, email, etc.)
     * @param customerId The customer to contact
     * @param contactMethod The method of contact (phone, email)
     * @return true if contact was successful
     */
    public boolean contactCustomerManually(int customerId, String contactMethod) {
        System.out.println("Contacting customer " + customerId + " via " + contactMethod);
        // In a real application, this might access customer contact info
        return true;
    }
    
    /**
     * Notifies customer about unavailable items
     * @param customerId The customer to notify
     * @param items Array of unavailable items
     */
    public void notifyUnavailableItems(int customerId, String[] items) {
        System.out.println("Notifying customer " + customerId + " about unavailable items");
        // In a real application, this would provide a formatted message
    }
    
    /**
     * Confirms receipt of an order to the customer
     * @param orderId The order being confirmed
     * @param customerId The customer who placed the order
     */
    public void confirmOrderReceived(int orderId, int customerId) {
        System.out.println("Confirming order " + orderId + " received for customer " + customerId);
        // In a real application, this might update order status
    }
    
    /**
     * Requests correction for an incorrect order
     * @param orderId The order needing correction
     * @param issue Description of the issue
     * @return Correction request ID
     */
    public int requestCorrection(int orderId, String issue) {
        System.out.println("Requesting correction for order " + orderId + ": " + issue);
        // In a real application, this would create a correction request
        return 10000 + orderId;
    }
    
    /**
     * Confirms a corrected order with the customer
     * @param orderId The corrected order
     * @param customerId The customer to confirm with
     * @return true if confirmation is successful
     */
    public boolean confirmCorrectedOrder(int orderId, int customerId) {
        System.out.println("Confirming corrected order " + orderId + " with customer " + customerId);
        // In a real application, this might update order status
        return true;
    }
    
    /**
     * Processes a payment for an order
     * @param orderId The order being paid for
     * @param amount The payment amount
     * @param paymentMethod The method of payment
     * @return true if payment is successful
     */
    public boolean processPayment(int orderId, double amount, String paymentMethod) {
        System.out.println("Processing " + paymentMethod + " payment of $" + amount + 
                           " for order " + orderId);
        // In a real application, this would call the payment service
        return true;
    }
    
    /**
     * Confirms pickup of an order from the kitchen
     * @param orderId The order being picked up
     * @return true if pickup is confirmed
     */
    public boolean confirmPickup(int orderId) {
        System.out.println("Confirming pickup of order " + orderId);
        // In a real application, this would update order status
        return true;
    }
    
    /**
     * Delivers an order to the customer's table
     * @param orderId The order being delivered
     * @param tableId The table to deliver to
     */
    public void deliverOrder(int orderId, int tableId) {
        System.out.println("Delivering order " + orderId + " to table " + tableId);
        // In a real application, this would update order status
    }
    
    // Getters and setters
    public int getWaiterID() { 
        return waiterID; 
    }
    public String getName() { 
        return name; 
    }
    public void setName(String name) { 
        this.name = name; 
    }
    public int getStaffID() { 
        return staffID; 
    }
}
