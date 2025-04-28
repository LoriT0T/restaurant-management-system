package com.restaurant;


/**
 * Customer class representing restaurant customers
 */
public class Customer {
    // Attributes
    private int customerID;
    private String name;
    private String contactInfo;
    
    /**
     * Constructor for Customer
     * @param customerID The unique ID for this customer
     * @param name The customer's name
     * @param contactInfo Contact information (phone, email, etc.)
     */
    public Customer(int customerID, String name, String contactInfo) {
        this.customerID = customerID;
        this.name = name;
        this.contactInfo = contactInfo;
    }
    
    /**
     * Requests details about available reservation slots
     * @param date The desired reservation date
     * @param partySize The number of guests
     * @return Available reservation times
     */
    public String[] requestReservationDetails(String date, int partySize) {
        System.out.println("Requesting reservation details for " + date + " with party size " + partySize);
        // In a real application, this would query the reservation system
        return new String[]{"17:00", "18:30", "20:00"};
    }
    
    /**
     * Provides details for making a reservation
     * @param date Selected date
     * @param time Selected time
     * @param partySize Number of guests
     * @param specialRequests Any special requests
     * @return Reservation ID if successful
     */
    public int provideReservationDetails(String date, String time, int partySize, String specialRequests) {
        System.out.println("Providing reservation details for " + date + " at " + time);
        // In a real application, this would create a reservation request
        return (int)(Math.random() * 10000);
    }
    
    /**
     * Modifies an existing reservation
     * @param reservationId The reservation to modify
     * @param newDate New date (or null if unchanged)
     * @param newTime New time (or null if unchanged)
     * @param newPartySize New party size (or -1 if unchanged)
     * @return true if modification is successful
     */
    public boolean modifyReservation(int reservationId, String newDate, String newTime, int newPartySize) {
        System.out.println("Modifying reservation " + reservationId);
        // In a real application, this would update the reservation
        return true;
    }
    
    /**
     * Cancels an existing reservation
     * @param reservationId The reservation to cancel
     * @return true if cancellation is successful
     */
    public boolean cancelReservation(int reservationId) {
        System.out.println("Cancelling reservation " + reservationId);
        // In a real application, this would update the reservation status
        return true;
    }
    
    /**
     * Selects items from the restaurant menu
     * @param menuItems Array of selected item IDs
     * @return Order ID for the new order
     */
    public int selectItemsFromMenu(int[] menuItems) {
        System.out.println("Selecting " + menuItems.length + " items from menu");
        // In a real application, this would start creating an order
        return (int)(Math.random() * 10000);
    }
    
    /**
     * Provides details about a server/waiter
     * @param waiterId The waiter to get details for
     * @return Formatted waiter information
     */
    public String inputServerDetails(int waiterId) {
        System.out.println("Providing input about server " + waiterId);
        // In a real application, this might be feedback or special requests
        return "Sample input about server " + waiterId;
    }
    
    /**
     * Confirms that an order has been received
     * @param orderId The order being confirmed
     * @return true if confirmation is successful
     */
    public boolean confirmOrderReceived(int orderId) {
        System.out.println("Confirming receipt of order " + orderId);
        // In a real application, this might update order status
        return true;
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
     * Confirms a corrected order
     * @param orderId The corrected order
     * @return true if confirmation is successful
     */
    public boolean confirmCorrectedOrder(int orderId) {
        System.out.println("Confirming corrected order " + orderId);
        // In a real application, this might update order status
        return true;
    }
    
    /**
     * Requests to make a payment for an order
     * @param orderId The order to pay for
     * @return true if payment request is successful
     */
    public boolean requestPayment(int orderId) {
        System.out.println("Requesting payment for order " + orderId);
        // In a real application, this would initiate payment process
        return true;
    }
    
    /**
     * Provides payment details for an order
     * @param orderId The order being paid for
     * @param paymentMethod The selected payment method
     * @param paymentDetails The payment information
     * @return true if payment details are accepted
     */
    public boolean providePaymentDetails(int orderId, String paymentMethod, String paymentDetails) {
        System.out.println("Providing " + paymentMethod + " payment details for order " + orderId);
        // In a real application, this would submit payment information
        return true;
    }
    
    /**
     * Confirms that payment has been processed
     * @param orderId The ID of the paid order
     * @param amount The payment amount
     * @return true if confirmation is successful
     */
    public boolean confirmPayment(int orderId, double amount) {
        System.out.println("Confirming payment of $" + amount + " for order: " + orderId);
        // In a real application, this might store receipt information
        return true;
    }
    
    // Getters and setters
    public int getCustomerID() { 
        return customerID; 
    }
    public String getName() { 
        return name; 
    }
    public void setName(String name) { 
        this.name = name; 
    }
    public String getContactInfo() { 
        return contactInfo; 
    }
    public void setContactInfo(String contactInfo) { 
        this.contactInfo = contactInfo; 
    }
}