package com.resturant;

public class Waiter {

    // Attributes
    private int waiterID;
    private String name;
    private int staffID;

    // Constructor
    public Waiter(int waiterID, String name, int staffID) {
        this.waiterID = waiterID;
        this.name = name;
        this.staffID = staffID;
    }

    // Basic getters and setters
    public int getWaiterID() {
        return waiterID;
    }

    public void setWaiterID(int waiterID) {
        this.waiterID = waiterID;
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

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    // Method stubs
    public void notifyCustomer() {
        // Implementation to be added
    }

    public void suggestAlternatives() {
        // Implementation to be added
    }

    // Additional methods will be implemented later
}
