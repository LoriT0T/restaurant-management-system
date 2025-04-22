package com.resturant.system;

import java.util.List;

public class Database {

    // Attributes
    private int databaseID;
    private Object reservationRecords;
    private Object paymentRecords;
    private boolean connectionStatus;
    private String connectionPath;
    private List<Object> storedData;

    // Singleton pattern
    private static Database instance = null;

    private Database() {
        // Private constructor for singleton
        connectionStatus = false;
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    // Method stubs
    public boolean connectInfo() {
        // Implementation to be added
        connectionStatus = true;
        return connectionStatus;
    }

    public void storeReservation() {
        // Implementation to be added
    }

    // Additional methods will be implemented later
}
