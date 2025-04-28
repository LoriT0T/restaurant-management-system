package com.restaurant;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.restaurant.Reservation;
import com.restaurant.Payment;
import com.restaurant.Order;
import com.restaurant.MenuItem;

/**
 * Database class implemented as a Singleton to ensure only one database instance exists.
 * This implementation uses Maps for storage
 */
public class Database {
    // Attributes
    private int databaseID;
    private Map<Integer, Reservation> reservationRecords;
    private Map<Integer, Payment> paymentRecords;
    private boolean connectionStatus;
    private String connectionPath;
    private Map<Integer, Order> orderRecords;
    private Map<String, Object> cacheData;
    private Map<String, List<Map<String, Object>>> dataStore;
    
    // Singleton pattern
    private static Database instance = null;
    
    // Constructor is private to enforce singleton pattern
    private Database() {
        this.databaseID = generateDatabaseID();
        this.connectionStatus = false;
        this.connectionPath = "memory-database";
        this.reservationRecords = new HashMap<>();
        this.paymentRecords = new HashMap<>();
        this.orderRecords = new HashMap<>();
        this.cacheData = new HashMap<>();
        this.dataStore = new HashMap<>();
        
        // Initialize empty collections in dataStore
        dataStore.put("reservations", new ArrayList<>());
        dataStore.put("orders", new ArrayList<>());
        dataStore.put("payments", new ArrayList<>());
        dataStore.put("transactions", new ArrayList<>());
        dataStore.put("reports", new ArrayList<>());
        dataStore.put("users", new ArrayList<>());
        dataStore.put("errors", new ArrayList<>());
        
        // Add some dummy data
        initializeDummyData();
    }
    
    /**
     * Gets the singleton instance of the database
     * @return The single Database instance
     */
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
    
    private int generateDatabaseID() {
        return (int)(System.currentTimeMillis() % 10000);
    }
    
    /**
     * Initialize some dummy data for testing
     */
    private void initializeDummyData() {
        // Dummy reservations
        Map<String, Object> reservation1 = new HashMap<>();
        reservation1.put("reservationId", 1001);
        reservation1.put("customerId", 101);
        reservation1.put("dateTime", "2023-12-15 19:00");
        reservation1.put("partySize", 4);
        reservation1.put("status", "Confirmed");
        
        Map<String, Object> reservation2 = new HashMap<>();
        reservation2.put("reservationId", 1002);
        reservation2.put("customerId", 102);
        reservation2.put("dateTime", "2023-12-16 20:00");
        reservation2.put("partySize", 2);
        reservation2.put("status", "Pending");
        
        dataStore.get("reservations").add(reservation1);
        dataStore.get("reservations").add(reservation2);
        
        // Dummy orders
        Map<String, Object> order1 = new HashMap<>();
        order1.put("orderId", 2001);
        order1.put("customerId", 101);
        order1.put("items", new ArrayList<>());
        order1.put("status", "Completed");
        order1.put("totalAmount", 45.75);
        
        Map<String, Object> order2 = new HashMap<>();
        order2.put("orderId", 2002);
        order2.put("customerId", 103);
        order2.put("items", new ArrayList<>());
        order2.put("status", "In Progress");
        order2.put("totalAmount", 32.50);
        
        dataStore.get("orders").add(order1);
        dataStore.get("orders").add(order2);
        
        // Dummy payments
        Map<String, Object> payment1 = new HashMap<>();
        payment1.put("paymentId", 3001);
        payment1.put("orderId", 2001);
        payment1.put("amount", 45.75);
        payment1.put("method", "Credit Card");
        payment1.put("status", "Completed");
        
        Map<String, Object> payment2 = new HashMap<>();
        payment2.put("paymentId", 3002);
        payment2.put("orderId", 2002);
        payment2.put("amount", 32.50);
        payment2.put("method", "Cash");
        payment2.put("status", "Pending");
        
        dataStore.get("payments").add(payment1);
        dataStore.get("payments").add(payment2);
        
        // Dummy users
        Map<String, Object> user1 = new HashMap<>();
        user1.put("userId", 101);
        user1.put("name", "John Smith");
        user1.put("role", "Customer");
        
        Map<String, Object> user2 = new HashMap<>();
        user2.put("userId", 201);
        user2.put("name", "Jane Doe");
        user2.put("role", "Waiter");
        
        dataStore.get("users").add(user1);
        dataStore.get("users").add(user2);
    }
    
    /**
     * Establishes connection to the database
     * @return true if connection is successful, false otherwise
     */
    public boolean connectInfo() {
        if (connectionStatus) {
            System.out.println("Already connected to database");
            return true;
        }
        
        try {
            // Simulate connection
            System.out.println("Connecting to in-memory database");
            connectionStatus = true;
            return true;
        } catch (Exception e) {
            System.out.println("Connection failed: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Stores a reservation in the database
     * @param reservation The reservation to store
     * @return true if storage is successful
     */
    public boolean storeReservation(Reservation reservation) {
        if (!connectionStatus) {
            System.out.println("Cannot store reservation: Not connected to database");
            return false;
        }
        
        try {
            // Convert to Map for storage
            Map<String, Object> reservationMap = new HashMap<>();
            reservationMap.put("reservationId", reservation.getReservationID());
            reservationMap.put("customerId", reservation.getCustomerID());
            reservationMap.put("dateTime", reservation.getDateTime());
            reservationMap.put("partySize", reservation.getPartySize());
            reservationMap.put("status", reservation.getStatus());
            
            // Store in dataStore
            dataStore.get("reservations").add(reservationMap);
            
            // Also add to Map for quick lookup
            reservationRecords.put(reservation.getReservationID(), reservation);
            
            System.out.println("Stored reservation: " + reservation.getReservationID());
            return true;
        } catch (Exception e) {
            handleErrors(1, "Error storing reservation: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Retrieves data from the database
     * @param query The query parameters
     * @return The retrieved data
     */
    public Object retrieveData(String query) {
        if (!connectionStatus) {
            System.out.println("Cannot retrieve data: Not connected to database");
            return null;
        }
        
        // Parse the query (simplified)
        String[] parts = query.split(":");
        if (parts.length < 2) {
            handleErrors(2, "Invalid query format: " + query);
            return null;
        }
        
        String collection = parts[0];
        String criteria = parts[1];
        
        try {
            if (!dataStore.containsKey(collection)) {
                handleErrors(3, "Collection not found: " + collection);
                return null;
            }
            
            List<Map<String, Object>> results = new ArrayList<>();
            
            // Simple filtering - in a real database this would be more sophisticated
            for (Map<String, Object> item : dataStore.get(collection)) {
                if (matchesCriteria(item, criteria)) {
                    results.add(item);
                }
            }
            
            return results;
        } catch (Exception e) {
            handleErrors(4, "Error retrieving data: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Simple criteria matching for query filtering
     */
    private boolean matchesCriteria(Map<String, Object> item, String criteria) {
        String[] parts = criteria.split("=");
        if (parts.length < 2) return false;
        
        String key = parts[0].trim();
        String value = parts[1].trim();
        
        if (!item.containsKey(key)) return false;
        
        return item.get(key).toString().equals(value);
    }
    
    /**
     * Updates data in the database
     * @param collection The collection to update
     * @param id The ID of the record to update
     * @param updates The updates to apply
     * @return true if update is successful
     */
    public boolean updateData(String collection, int id, Map<String, Object> updates) {
        if (!connectionStatus) {
            System.out.println("Cannot update data: Not connected to database");
            return false;
        }
        
        try {
            if (!dataStore.containsKey(collection)) {
                handleErrors(5, "Collection not found: " + collection);
                return false;
            }
            
            String idField = collection.substring(0, collection.length() - 1) + "Id";
            
            for (Map<String, Object> item : dataStore.get(collection)) {
                if (item.containsKey(idField) && ((Integer)item.get(idField) == id)) {
                    // Apply updates
                    item.putAll(updates);
                    System.out.println("Updated " + collection + " with ID: " + id);
                    return true;
                }
            }
            
            handleErrors(6, "Record not found in " + collection + " with ID: " + id);
            return false;
        } catch (Exception e) {
            handleErrors(7, "Error updating data: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Manages order data in the database
     * @param order The order to manage
     * @param action The action to perform
     * @return true if action is successful
     */
    public boolean manageOrderData(Order order, String action) {
        if (!connectionStatus) {
            System.out.println("Cannot manage order: Not connected to database");
            return false;
        }
        
        try {
            switch (action.toLowerCase()) {
                case "create":
                    // Convert to Map for storage
                    Map<String, Object> orderMap = new HashMap<>();
                    orderMap.put("orderId", order.getOrderID());
                    orderMap.put("customerId", order.getCustomerID());
                    orderMap.put("status", order.getOrderStatus());
                    orderMap.put("totalAmount", order.getTotalAmount());
                    orderMap.put("items", order.getItems());
                    
                    // Store in dataStore
                    dataStore.get("orders").add(orderMap);
                    
                    // Also add to Map for quick lookup
                    orderRecords.put(order.getOrderID(), order);
                    
                    System.out.println("Created order: " + order.getOrderID());
                    return true;
                    
                case "update":
                    Map<String, Object> updates = new HashMap<>();
                    updates.put("status", order.getOrderStatus());
                    updates.put("totalAmount", order.getTotalAmount());
                    updates.put("items", order.getItems());
                    
                    return updateData("orders", order.getOrderID(), updates);
                    
                case "delete":
                    List<Map<String, Object>> orders = dataStore.get("orders");
                    for (int i = 0; i < orders.size(); i++) {
                        if (orders.get(i).get("orderId").equals(order.getOrderID())) {
                            orders.remove(i);
                            orderRecords.remove(order.getOrderID());
                            System.out.println("Deleted order: " + order.getOrderID());
                            return true;
                        }
                    }
                    return false;
                    
                default:
                    handleErrors(8, "Unknown action: " + action);
                    return false;
            }
        } catch (Exception e) {
            handleErrors(9, "Error managing order: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Handles errors in the database operations
     * @param errorCode The error code
     * @param message The error message
     */
    public void handleErrors(int errorCode, String message) {
        System.out.println("Database Error " + errorCode + ": " + message);
        
        Map<String, Object> error = new HashMap<>();
        error.put("errorCode", errorCode);
        error.put("message", message);
        error.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        
        dataStore.get("errors").add(error);
        
        // For certain error types, we might want to try recovery
        if (errorCode == 1 || errorCode == 2) {
            connectionStatus = false;
            connectInfo(); // Attempt to reconnect
        }
    }
    
    /**
     * Stores payment data in the database
     * @param payment The payment to store
     * @return true if storage is successful
     */
    public boolean storePaymentData(Payment payment) {
        if (!connectionStatus) {
            System.out.println("Cannot store payment: Not connected to database");
            return false;
        }
        
        try {
            // Convert to Map for storage
            Map<String, Object> paymentMap = new HashMap<>();
            paymentMap.put("paymentId", payment.getPaymentID());
            paymentMap.put("orderId", payment.getOrderID());
            paymentMap.put("amount", payment.getAmount());
            paymentMap.put("method", payment.getPaymentMethod());
            paymentMap.put("status", payment.getStatus());
            paymentMap.put("timestamp", payment.getTimestamp() != null ? 
                           payment.getTimestamp() : 
                           LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            
            // Store in dataStore
            dataStore.get("payments").add(paymentMap);
            
            // Also add to Map for quick lookup
            paymentRecords.put(payment.getPaymentID(), payment);
            
            System.out.println("Stored payment: " + payment.getPaymentID());
            return true;
        } catch (Exception e) {
            handleErrors(10, "Error storing payment: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Retrieves payment data from the database
     * @param paymentId The ID of the payment to retrieve
     * @return The payment or null if not found
     */
    public Payment retrievePaymentData(int paymentId) {
        if (!connectionStatus) {
            System.out.println("Cannot retrieve payment: Not connected to database");
            return null;
        }
        
        // First check the Map for quick lookup
        if (paymentRecords.containsKey(paymentId)) {
            return paymentRecords.get(paymentId);
        }
        
        try {
            for (Map<String, Object> payment : dataStore.get("payments")) {
                if (payment.containsKey("paymentId") && ((Integer)payment.get("paymentId") == paymentId)) {
                    Payment result = new Payment(
                        (Integer)payment.get("paymentId"),
                        (Integer)payment.get("orderId"),
                        (Double)payment.get("amount")
                    );
                    
                    result.setPaymentMethod((String)payment.get("method"));
                    result.setStatus((String)payment.get("status"));
                    result.setTimestamp((String)payment.get("timestamp"));
                    
                    // Cache for future lookups
                    paymentRecords.put(paymentId, result);
                    
                    return result;
                }
            }
            
            return null;
        } catch (Exception e) {
            handleErrors(11, "Error retrieving payment: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Retrieves order data from the database
     * @param orderId The ID of the order to retrieve
     * @return The order or null if not found
     */
    public Order retrieveOrderData(int orderId) {
        if (!connectionStatus) {
            System.out.println("Cannot retrieve order: Not connected to database");
            return null;
        }
        
        // First check the Map for quick lookup
        if (orderRecords.containsKey(orderId)) {
            return orderRecords.get(orderId);
        }
        
        try {
            for (Map<String, Object> order : dataStore.get("orders")) {
                if (order.containsKey("orderId") && ((Integer)order.get("orderId") == orderId)) {
                    Order result = new Order(
                        (Integer)order.get("orderId"),
                        (Integer)order.get("customerId")
                    );
                    
                    result.setOrderStatus((String)order.get("status"));
                    result.setTotalAmount((Double)order.get("totalAmount"));
                    
                    // Handle items if they exist
                    if (order.containsKey("items") && order.get("items") instanceof List) {
                        for (Object item : (List<?>)order.get("items")) {
                            if (item instanceof MenuItem) {
                                result.addItem((MenuItem)item);
                            }
                        }
                    }
                    
                    // Cache for future lookups
                    orderRecords.put(orderId, result);
                    
                    return result;
                }
            }
            
            return null;
        } catch (Exception e) {
            handleErrors(12, "Error retrieving order: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Updates payment data in the database
     * @param payment The payment with updated information
     * @return true if update is successful
     */
    public boolean updatePaymentData(Payment payment) {
        if (!connectionStatus) {
            System.out.println("Cannot update payment: Not connected to database");
            return false;
        }
        
        try {
            Map<String, Object> updates = new HashMap<>();
            updates.put("amount", payment.getAmount());
            updates.put("method", payment.getPaymentMethod());
            updates.put("status", payment.getStatus());
            
            boolean updated = updateData("payments", payment.getPaymentID(), updates);
            
            if (updated) {
                // Update the cached version too
                paymentRecords.put(payment.getPaymentID(), payment);
            }
            
            return updated;
        } catch (Exception e) {
            handleErrors(13, "Error updating payment: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Records a transaction in the database
     * @param data Transaction data to record
     * @return true if recording is successful
     */
    public boolean storeTransaction(Map<String, Object> data) {
        if (!connectionStatus) {
            System.out.println("Cannot store transaction: Not connected to database");
            return false;
        }
        
        try {
            // Add timestamp if not provided
            if (!data.containsKey("timestamp")) {
                data.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            }
            
            // Store in dataStore
            dataStore.get("transactions").add(data);
            
            System.out.println("Stored transaction: " + data.get("transactionId"));
            return true;
        } catch (Exception e) {
            handleErrors(14, "Error storing transaction: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Retrieves sales data based on specified criteria
     * @param criteria The search criteria
     * @return List of sales records matching the criteria
     */
    public List<Map<String, Object>> retrieveSalesData(String criteria) {
        if (!connectionStatus) {
            System.out.println("Cannot retrieve sales data: Not connected to database");
            return new ArrayList<>();
        }
        
        List<Map<String, Object>> salesData = new ArrayList<>();
        
        try {
            // Combine orders and payments to create sales records
            for (Map<String, Object> order : dataStore.get("orders")) {
                // Skip orders that don't match criteria
                if (!matchesCriteria(order, criteria)) {
                    continue;
                }
                
                Map<String, Object> salesRecord = new HashMap<>(order);
                
                // Find matching payment
                for (Map<String, Object> payment : dataStore.get("payments")) {
                    if (payment.get("orderId").equals(order.get("orderId"))) {
                        salesRecord.put("paymentId", payment.get("paymentId"));
                        salesRecord.put("paymentMethod", payment.get("method"));
                        salesRecord.put("paymentStatus", payment.get("status"));
                        break;
                    }
                }
                
                salesData.add(salesRecord);
            }
            
            return salesData;
        } catch (Exception e) {
            handleErrors(15, "Error retrieving sales data: " + e.getMessage());
            return salesData;
        }
    }
    
    /**
     * Saves a report to the database
     * @param reportName The name of the report
     * @param reportData The report content
     * @return true if saving is successful
     */
    public boolean saveReport(String reportName, String reportData) {
        if (!connectionStatus) {
            System.out.println("Cannot save report: Not connected to database");
            return false;
        }
        
        try {
            Map<String, Object> report = new HashMap<>();
            report.put("name", reportName);
            report.put("content", reportData);
            report.put("createdAt", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            
            dataStore.get("reports").add(report);
            
            System.out.println("Saved report: " + reportName);
            return true;
        } catch (Exception e) {
            handleErrors(16, "Error saving report: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Retrieves a report from the database
     * @param reportName The name of the report to retrieve
     * @return The report content
     */
    public String retrieveReport(String reportName) {
        if (!connectionStatus) {
            System.out.println("Cannot retrieve report: Not connected to database");
            return null;
        }
        
        try {
            String latestReport = null;
            LocalDateTime latestTime = LocalDateTime.MIN;
            
            for (Map<String, Object> report : dataStore.get("reports")) {
                if (report.get("name").equals(reportName)) {
                    String timeStr = (String)report.get("createdAt");
                    LocalDateTime reportTime = LocalDateTime.parse(timeStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    
                    if (reportTime.isAfter(latestTime)) {
                        latestTime = reportTime;
                        latestReport = (String)report.get("content");
                    }
                }
            }
            
            return latestReport;
        } catch (Exception e) {
            handleErrors(17, "Error retrieving report: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Logs access to the database
     * @param userId The ID of the user accessing the database
     * @param action The action performed
     */
    public void logAccess(int userId, String action) {
        if (!connectionStatus) {
            System.out.println("Cannot log access: Not connected to database");
            return;
        }
        
        try {
            Map<String, Object> logEntry = new HashMap<>();
            logEntry.put("userId", userId);
            logEntry.put("action", action);
            logEntry.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            
            if (!dataStore.containsKey("accessLogs")) {
                dataStore.put("accessLogs", new ArrayList<>());
            }
            
            dataStore.get("accessLogs").add(logEntry);
        } catch (Exception e) {
            // Just print the error, don't call handleErrors to avoid infinite recursion
            System.out.println("Error logging access: " + e.getMessage());
        }
    }
    
    /**
     * Retrieves user roles from the database
     * @param userId The ID of the user
     * @return List of roles assigned to the user
     */
    public List<String> retrieveUserRoles(int userId) {
        if (!connectionStatus) {
            System.out.println("Cannot retrieve user roles: Not connected to database");
            return new ArrayList<>();
        }
        
        List<String> roles = new ArrayList<>();
        
        try {
            for (Map<String, Object> user : dataStore.get("users")) {
                if (user.containsKey("userId") && ((Integer)user.get("userId") == userId)) {
                    if (user.containsKey("role")) {
                        roles.add((String)user.get("role"));
                    }
                    break;
                }
            }
            
            return roles;
        } catch (Exception e) {
            handleErrors(18, "Error retrieving user roles: " + e.getMessage());
            return roles;
        }
    }
    
    // Getters and setters
    public int getDatabaseID() { 
        return databaseID; 
    }
    public boolean isConnected() { 
        return connectionStatus; 
    }
    public String getConnectionPath() { 
        return connectionPath; 
    }
    public void setConnectionPath(String connectionPath) { 
        this.connectionPath = connectionPath; 
    }
}