package com.resturant;

public class Order {

    // Attributes
    private int orderID;
    private String orderStatus;
    private String orderDetails;
    private int customerID;
    private int reservationID;

    // Constructor
    public Order(int orderID, int customerID) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.orderStatus = "New";
    }

    // Basic getters and setters
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

    // Method stubs
    public void displayOrderForm() {
        // Implementation to be added
    }

    public void inputOrderDetails() {
        // Implementation to be added
    }

    // Additional methods will be implemented later
}
