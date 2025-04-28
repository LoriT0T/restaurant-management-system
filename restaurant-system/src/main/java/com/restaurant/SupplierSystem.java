package com.restaurant;


/**
 * SupplierSystem class for managing suppliers
 */
public class SupplierSystem {
    // Attributes
    private int supplierID;
    private String supplierName;
    
    /**
     * Constructor for SupplierSystem
     * @param supplierID The unique ID for this supplier
     * @param supplierName The supplier's name
     */
    public SupplierSystem(int supplierID, String supplierName) {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
    }
    
    /**
     * Confirms receipt of an order
     * @param orderId The order being confirmed
     * @return true if confirmation is successful
     */
    public boolean confirmOrderReceipt(int orderId) {
        System.out.println("Supplier " + supplierName + " confirming receipt of order " + orderId);
        // In a real application, this would update order status
        return true;
    }
    
    /**
     * Receives an order from the restaurant
     * @param orderDetails The order details
     * @return Order ID if receipt is successful
     */
    public int receiveOrder(String orderDetails) {
        System.out.println("Supplier " + supplierName + " receiving order: " + orderDetails);
        // In a real application, this would process the order
        return (int)(Math.random() * 10000);
    }
    
    /**
     * Authenticates with the supplier system
     * @param credentials Authentication credentials
     * @return true if authentication is successful
     */
    public boolean authenticate(String credentials) {
        System.out.println("Authenticating with supplier " + supplierName);
        // In a real application, this would validate credentials
        return true;
    }
    
    // Getters and setters
    public int getSupplierID() { 
        return supplierID; 
    }
    public String getSupplierName() { 
        return supplierName; 
    }
    public void setSupplierName(String supplierName) { 
        this.supplierName = supplierName; 
    }
}
