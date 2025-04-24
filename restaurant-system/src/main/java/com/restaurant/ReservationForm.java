package com.resturant;

public class ReservationForm {

    // Attributes
    private int reservationID;
    private String reservationDetails;
    private int customerID;

    // Constructor
    public ReservationForm(int reservationID, int customerID) {
        this.reservationID = reservationID;
        this.customerID = customerID;
    }

    // Basic getters and setters
    public int getReservationID() {
        return reservationID;
    }

    public String getReservationDetails() {
        return reservationDetails;
    }

    public void setReservationDetails(String reservationDetails) {
        this.reservationDetails = reservationDetails;
    }

    // Method stubs
    public void accessReservationSystem() {
        // Implementation to be added
    }

    public void requestReservationDetails() {
        // Implementation to be added
    }

    // Additional methods will be implemented later
}
