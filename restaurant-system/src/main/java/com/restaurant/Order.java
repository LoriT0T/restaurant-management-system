package com.restaurant;


import java.util.ArrayList;
import java.util.List;

/**
 * Order class representing a customer's order
 */
public class Order {
    // Attributes
    private int orderID;
    private String orderStatus;
    private String orderDetails;
    private int customerID;
    private int reservationID;
    private List<OrderItem> items;
    private double totalAmount;
    
    /**
     * Constructor for Order
     * @param orderID The unique ID for this order
     * @param customerID The customer who placed the order
     */
    public Order(int orderID, int customerID) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.orderStatus = "New";
        this.items = new ArrayList<>();
        this.totalAmount = 0.0;
    }
    
    /**
     * Displays the order form
     * @return Order form representation
     */
    public Object displayOrderForm() {
        System.out.println("Displaying order form for order " + orderID);
        // In a real application, this would return an order form object
        return "Order form for order " + orderID;
    }
    
    /**
     * Processes input of order details
     * @param details The order details
     * @return true if input is successful
     */
    public boolean inputOrderDetails(String details) {
        System.out.println("Inputting details for order " + orderID);
        this.orderDetails = details;
        return true;
    }
    
    /**
     * Checks if items are available
     * @param itemIDs Array of item IDs to check
     * @return Array of availability results
     */
    public boolean[] checkItemAvailability(int[] itemIDs) {
        System.out.println("Checking availability of " + itemIDs.length + " items");
        // In a real application, this would check inventory
        boolean[] results = new boolean[itemIDs.length];
        for (int i = 0; i < itemIDs.length; i++) {
            results[i] = true; // Assuming all items are available
        }
        return results;
    }
    
    /**
     * Updates order with confirmation
     * @param confirmed Whether the order is confirmed
     * @return true if update is successful
     */
    public boolean updateOrderConfirmation(boolean confirmed) {
        System.out.println(confirmed ? "Confirming order " + orderID : "Rejecting order " + orderID);
        this.orderStatus = confirmed ? "Confirmed" : "Rejected";
        return true;
    }
    
    /**
     * Notifies about unavailable items
     * @param unavailableItems List of unavailable items
     * @return true if notification is successful
     */
    public boolean notifyUnavailableItems(List<String> unavailableItems) {
        System.out.println("Notifying about " + unavailableItems.size() + " unavailable items in order " + orderID);
        // In a real application, this would trigger notifications
        return true;
    }
    
    /**
     * Updates the order with new details
     * @param newDetails New order details
     * @return true if update is successful
     */
    public boolean updateOrder(String newDetails) {
        System.out.println("Updating order " + orderID);
        this.orderDetails = newDetails;
        return true;
    }
    
    /**
     * Displays a corrected order
     * @return Corrected order representation
     */
    public Object displayCorrectedOrder() {
        System.out.println("Displaying corrected order " + orderID);
        // In a real application, this would return updated order details
        return "Corrected order " + orderID + " details";
    }
    
    /**
     * Confirms corrections made to the order
     * @return true if confirmation is successful
     */
    public boolean confirmOrderCorrection() {
        System.out.println("Confirming corrections to order " + orderID);
        this.orderStatus = "Corrected";
        return true;
    }
    
    /**
     * Adds an item to the order
     * @param item The item to add
     */
    public void addItem(OrderItem item) {
        items.add(item);
        // In a real application, this would also update the total amount
    }
    
    // Getters and setters
    public int getOrderID() { 
        return orderID; 
    }
    public String getOrderStatus() { 
        return orderStatus; 
    }
    public void setOrderStatus(String orderStatus) { 
        this.orderStatus = orderStatus; 
    }
    public String getOrderDetails() { 
        return orderDetails; 
    }
    public void setOrderDetails(String orderDetails) { 
        this.orderDetails = orderDetails; 
    }
    public int getCustomerID() { 
        return customerID; 
    }
    public int getReservationID() { 
        return reservationID; 
    }
    public void setReservationID(int reservationID) { 
        this.reservationID = reservationID; 
    }
    public List<OrderItem> getItems() { 
        return new ArrayList<>(items); 
    } // Return a copy to maintain encapsulation
    public double getTotalAmount() { 
        return totalAmount; 
    }
    public void setTotalAmount(double totalAmount) { 
        this.totalAmount = totalAmount; 
    }
}