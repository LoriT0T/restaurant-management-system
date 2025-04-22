package com.resturant;

public class Staff {

    // Attributes
    private int staffID;
    private String name;
    private String schedule;
    private String payInformation; //This one needs checking if its needed and its usage

    // Constructor
    public Staff(int staffID, String name) {
        this.staffID = staffID;
        this.name = name;
    }

    // Basic getters and setters
    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    // Method stubs
    public void viewSchedule() {
        // Implementation to be added
    }

    // Additional methods will be implemented later
}
