package com.restaurant;

public class Manager {

    // Attributes
    private int managerID;
    private String name;
    private String email;
    private String role;

    // Constructor
    public Manager(int managerID, String name, String email, String role) {
        this.managerID = managerID;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    // Basic getters and setters
    public int getManagerID() {
        return managerID;
    }

    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Method stubs
    public void generateSalesReport(String format) {
        // Implementation to be added
    }

    // Additional methods will be implemented later
}
