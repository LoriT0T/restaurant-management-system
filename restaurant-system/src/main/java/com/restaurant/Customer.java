package com.restaurant;

public class Customer {

    // Attributes
    private int customerID;
    private String name;
    private String contactInfo;

    // Constructor
    public Customer(int customerID, String name, String contactInfo) {
        this.customerID = customerID;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    // Basic getters and setters
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
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

    // Method stubs
    public void requestReservationDetails() {
        // Implementation to be added
    }

    public void provideReservationDetails() {
        // Implementation to be added
    }

    // Additional methods will be implemented later
}
