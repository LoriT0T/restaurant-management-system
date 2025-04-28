package com.restaurant;

/**
 * Reservation class to store reservation information from Database
 */

public class Reservation {
    private int reservationID;
    private int customerID;
    private String dateTime;
    private int partySize;
    private String status;
    
    public Reservation(int reservationID, int customerID) {
        this.reservationID = reservationID;
        this.customerID = customerID;
        this.status = "Pending";
    }
    
    // Getters and setters
    public int getReservationID() { 
        return reservationID; 
    }
    public int getCustomerID() { 
        return customerID; 
    }
    public String getDateTime() { 
        return dateTime; 
    }
    public void setDateTime(String dateTime) { 
        this.dateTime = dateTime; 
    }
    public int getPartySize() { 
        return partySize; 
    }
    public void setPartySize(int partySize) { 
        this.partySize = partySize; 
    }
    public String getStatus() { 
        return status; 
    }
    public void setStatus(String status) { 
        this.status = status; 
    }
}
