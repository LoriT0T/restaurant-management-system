package com.restaurant;

import java.util.HashMap;
import java.util.Map;


public class SystemController {
    // Attributes
    private int systemID;
    private String systemStatus;
    private boolean missingDataStatus;
    
    // User authentication storage
    private Map<String, String> userCredentials;
    private Map<String, String> userRoles;
    private String currentUser;
    private boolean isLoggedIn;
    
    // Constructor
    public SystemController() {
        this.systemID = generateSystemID();
        this.systemStatus = "Active";
        this.missingDataStatus = false;
        this.userCredentials = new HashMap<>();
        this.userRoles = new HashMap<>();
        this.isLoggedIn = false;
        
        // Add some default users for testing
        userCredentials.put("admin", "admin123");
        userCredentials.put("waiter", "waiter123");
        userCredentials.put("chef", "chef123");

        // Default User Roles
        userRoles.put("admin", "manager");
        userRoles.put("waiter", "staff");
        userRoles.put("chef", "staff");
    }
    
    private int generateSystemID() {
        return (int)(System.currentTimeMillis() % 10000);
    }
    
    // Method 1: login
    public boolean login() {
        // This version checks if the system is ready for login
        if (systemStatus.equals("Active")) {
            System.out.println("Login system is ready");
            return true;
        } else {
            System.out.println("System is not active. Current status: " + systemStatus);
            return false;
        }
    }
    
    // Method 2: authenticate
    public boolean authenticate(String username, String password) {
        if (userCredentials.containsKey(username)) {
            String storedPassword = userCredentials.get(username);
            if (storedPassword.equals(password)) {
                isLoggedIn = true;
                currentUser = username;
                System.out.println("User " + username + " authenticated successfully");
                return true;
            }
        }
        System.out.println("Authentication failed for user " + username);
        return false;
    }
    
    // Method 3: logout
    public void logout() {
        if (isLoggedIn) {
            System.out.println("User " + currentUser + " logged out successfully");
            isLoggedIn = false;
            currentUser = null;
        } else {
            System.out.println("No user is currently logged in");
        }
    }
    
    // Method 4: accessData
    public Object accessData() {
        if (!isLoggedIn) {
            System.out.println("User must be logged in to access data");
            return null;
        }
        
        System.out.println("Accessing data for user " + currentUser);
        // In a real implementation, this would retrieve data from the database
        // based on the user's permissions
        return "Sample data for " + currentUser;
    }
    
    // Method 5: returnData
    public Object returnData() {
        if (!isLoggedIn) {
            System.out.println("User must be logged in to return data");
            return null;
        }
        
        // This could fetch specific data from the database
        System.out.println("Returning requested data");
        return "Requested data for " + currentUser;
    }
    
    // Method 6: handleNoAvailability
    public void handleNoAvailability() {
        System.out.println("Handling no availability situation");
        // This would implement logic for when resources (tables, items, etc.)
        // are not available
        this.systemStatus = "Resource Constraint";
        notifyResourceConstraint();
    }
    
    // Method 7: handleCapacityExceeded
    public boolean handleCapacityExceeded() {
        System.out.println("System capacity has been exceeded");
        
        // Check if we can expand capacity
        boolean canExpand = checkForCapacityExpansion();
        
        if (canExpand) {
            System.out.println("Expanding system capacity");
            // Logic to expand capacity would go here
            return true;
        } else {
            System.out.println("Cannot expand capacity, implementing restrictions");
            // Logic to implement restrictions would go here
            this.systemStatus = "At Capacity";
            return false;
        }
    }
    
    // Method 8: handleInvalidInput
    public boolean handleInvalidInput() {
        System.out.println("Invalid input detected");
        
        // Log the invalid input
        logInvalidInput();
        
        // Request correction
        System.out.println("Requesting input correction");
        
        // In a real application, this might trigger a UI prompt
        this.missingDataStatus = true;
        return false;
    }
    
    // Method 9: updateReservation
    public boolean updateReservation(int reservationId, String newDetails) {
        if (!isLoggedIn) {
            System.out.println("User must be logged in to update reservations");
            return false;
        }
        
        System.out.println("Updating reservation " + reservationId);
        // In a real application, this would update the reservation in the database
        
        // Check if update was successful
        boolean updateSuccessful = true; // This would be the result of the database operation
        
        if (updateSuccessful) {
            System.out.println("Reservation updated successfully");
            return true;
        } else {
            System.out.println("Failed to update reservation");
            return false;
        }
    }

    // Method 10: manageOrder
    public boolean manageOrder(int orderId, String action) {
        if (!isLoggedIn) {
            System.out.println("User must be logged in to manage orders");
            return false;
        }
        
        System.out.println("Managing order " + orderId + " with action: " + action);
        
        switch (action.toLowerCase()) {
            case "create":
                return createOrder(orderId);
            case "update":
                return updateOrder(orderId);
            case "cancel":
                return cancelOrder(orderId);
            case "complete":
                return completeOrder(orderId);
            default:
                System.out.println("Unknown action: " + action);
                return false;
        }
    }
    
    // Method 11: handleNotification
    public void handleNotification(String recipient, String message, String priority) {
        System.out.println("Sending notification to " + recipient);
        
        // Validate priority
        if (!isPriorityValid(priority)) {
            priority = "normal";
        }
        
        // Log notification
        logNotification(recipient, message, priority);
        
        // Send notification based on priority
        switch (priority.toLowerCase()) {
            case "high":
                System.out.println("Sending high priority notification immediately");
                // Code to send urgent notification
                break;
            case "normal":
                System.out.println("Sending normal notification");
                // Code to send regular notification
                break;
            case "low":
                System.out.println("Queueing low priority notification");
                // Code to queue notification for batch processing
                break;
        }
    }
    
    // Method 12: updateOrderStatus
    public boolean updateOrderStatus(int orderId, String newStatus) {
        if (!isLoggedIn) {
            System.out.println("User must be logged in to update order status");
            return false;
        }
        
        System.out.println("Updating status for order " + orderId + " to: " + newStatus);
        
        // Validate the new status
        if (!isValidOrderStatus(newStatus)) {
            System.out.println("Invalid order status: " + newStatus);
            return false;
        }
        
        // In a real system, this would update the database
        boolean updateSuccessful = true; // Result of database update
        
        if (updateSuccessful) {
            System.out.println("Order status updated successfully");
            
            // Notify relevant parties
            notifyStatusUpdate(orderId, newStatus);
            
            return true;
        } else {
            System.out.println("Failed to update order status");
            return false;
        }
    }

    // Method 13: handleCorrections
    public boolean handleCorrections(int itemId, String correctionType, String newValue) {
        if (!isLoggedIn) {
            System.out.println("User must be logged in to make corrections");
            return false;
        }
        
        System.out.println("Processing correction for item " + itemId + ": " + correctionType);
        
        // Log the correction attempt
        logCorrection(itemId, correctionType, newValue);
        
        // Validate the correction type
        if (!isValidCorrectionType(correctionType)) {
            System.out.println("Invalid correction type: " + correctionType);
            return false;
        }
        
        // Apply the correction
        boolean correctionApplied = applyCorrectionToItem(itemId, correctionType, newValue);
        
        if (correctionApplied) {
            System.out.println("Correction successfully applied");
            notifyCorrectionApplied(itemId, correctionType);
            return true;
        } else {
            System.out.println("Failed to apply correction");
            return false;
        }
    }
    
    // Method 14: confirmActions
    public boolean confirmActions(String actionType, int actionId) {
        if (!isLoggedIn) {
            System.out.println("User must be logged in to confirm actions");
            return false;
        }
        
        System.out.println("Confirming " + actionType + " action #" + actionId);
        
        // Record confirmation
        recordActionConfirmation(actionType, actionId);
        
        // Validate that action exists and can be confirmed
        if (!isActionValid(actionType, actionId)) {
            System.out.println("Invalid action or action cannot be confirmed");
            return false;
        }
        
        // Update action status to confirmed
        boolean confirmationSuccessful = updateActionStatus(actionType, actionId, "Confirmed");
        
        if (confirmationSuccessful) {
            System.out.println("Action confirmed successfully");
            notifyActionConfirmed(actionType, actionId);
            return true;
        } else {
            System.out.println("Failed to confirm action");
            return false;
        }
    }
    
    // Method 15: authorizePayment
    public boolean authorizePayment(int orderId, double amount, String paymentMethod) {
        if (!isLoggedIn) {
            System.out.println("User must be logged in to authorize payments");
            return false;
        }
        
        System.out.println("Authorizing payment for order " + orderId + ": $" + amount + " via " + paymentMethod);
        
        // Validate order exists
        if (!doesOrderExist(orderId)) {
            System.out.println("Order does not exist: " + orderId);
            return false;
        }
        
        // Validate payment amount
        if (amount <= 0) {
            System.out.println("Invalid payment amount: " + amount);
            return false;
        }
        
        // Validate payment method
        if (!isValidPaymentMethod(paymentMethod)) {
            System.out.println("Invalid payment method: " + paymentMethod);
            return false;
        }
        
        // Process payment through payment service
        boolean paymentAuthorized = processPaymentAuthorization(orderId, amount, paymentMethod);
        
        if (paymentAuthorized) {
            System.out.println("Payment authorized successfully");
            updateOrderPaymentStatus(orderId, "Paid");
            generatePaymentReceipt(orderId, amount, paymentMethod);
            return true;
        } else {
            System.out.println("Payment authorization failed");
            updateOrderPaymentStatus(orderId, "Payment Failed");
            return false;
        }
    }


    // Method 16: Notify Waiter that order is ready
    public boolean notifyWaiter(int orderId, int tableNumber) {
        if (!isLoggedIn) {
            System.out.println("User must be logged in to get order status");
            return false;
        }

        String message = "Order Number " + orderId + " is ready for Table " + tableNumber + ".";
        System.out.println("Notifying waiter: " + message);

        boolean sent = sendNotificationToWaiter(tableNumber, message);
        return sent;
    }

    // Method 17: Check Stocks Levels in Inventory
    public boolean checkStockLevels(String itemName) {
        if (!isLoggedIn) {
            System.out.println("User must be logged in to get check stock levels");
            return false;
        }
        
        System.out.println("Checking Stock for item:" + itemName);

        int stockCount = getStockLevelForItem(itemName);
        
        if (stockCount < getReorderAmount(itemName)) {
            System.out.println("Stock for " + itemName +" is Low." + stockCount + " units remaining.");
            return false;
        } else {
            System.out.println("Stock for " + itemName +" is OK." + stockCount + " units remaining.");
            return true;
        } 

    } 

    //Method 18: Generate Purchase Orders
    public boolean generatePurchaseOrder(String itemName, int quantity) {
        if (!isLoggedIn) {
            System.out.println("User must be logged in to generate purchase orders.");
            return false;
        }

        System.out.println("Generating purchase order for item: " + itemName + " (Quantity: " + quantity + ")");

     
        boolean orderValid = true; // In a real system, this would check inventory thresholds, supplier availability, etc.

        if (!orderValid) {
            System.out.println("Failed to generate purchase order: invalid order details.");
            return false;
        }

        // In a real system, this would send the order to the SupplierSystem or database
        System.out.println("Purchase order succesfully raised.");
        return true;
    }




    // Method 19: Generate Sales Report
    public boolean generateSalesReport(String range) {
        if (!isLoggedIn) {
            System.out.println("Manager must be logged in to generate the sales reports");
            return false;
        }

        if (!handleMissingData("sales")) { // Checks data against Method 26 Missing Data
            return false;
        }
        

        System.out.println("Generating " + range.toUpperCase() + " Sales Report...");

        double totalRevenue = calculateTotalRevenue(range);
        int customerCount = calculateCustomerCount(range);
        String bestSeller = getBestSellingItem(range);

        System.out.println("\n--- " + range.toUpperCase() + " SALES REPORT ---");
        System.out.println("Total Revenue: £" + totalRevenue);
        System.out.println("Total Customers: " + customerCount);
        System.out.println("Best Selling Item: " + bestSeller);
        System.out.println("------------------------------\n");

        detectSalesOutliers(range); //To Connect to Method 21 for outliers

        return true;
    }

    // Method 20 Detect Outliers in sales
    public void detectSalesOutliers(String range) {
        double revenue = calculateTotalRevenue(range);
        double expected = getExpectedRevenue(range);

        if (revenue > expected * 1.5) {
            System.out.println("ALERT: Unusual spike in sales detected!");
            System.out.println("Possible reason: Promotion, seasonal event, or bulk order.");
        } else if (revenue < expected * 0.5) {
            System.out.println("ALERT: Unusual drop in sales detected!");
            System.out.println("Possible reason: System error, bad weather, or closure.");
        } else {
            System.out.println("Sales levels for " + range + " are within expected range.");
        }
    }

    // Method 21 Send Data to UI
        public boolean sendDataToUI(String uiType, Object data) {
        if (!isLoggedIn) {
            System.out.println("User must be logged in to send data to the UI");
            return false;
        }

        System.out.println("Sending data to " + uiType + "...");

        boolean sent = dispatchToUI(uiType, data);
        return sent;
    }

    // Method 22 - fetchdate - Need to know what this does

    // Method 23: Update Schedule Record
    public boolean updateScheduleRecord(Object record) {
        if (!isLoggedIn) {
            System.out.println("User must be logged in to update schedule records");
            return false;
        }

        if (!"manager".equals(userRoles.get(currentUser))) {
            System.out.println("Only managers can update schedules");
            return false;
        }
        

        System.out.println("Attempting to update schedule record...");

        boolean updated = processScheduleUpdate(record);

        if (updated) {
            System.out.println("Schedule updated successfully.");
            return true;
        } else {
            System.out.println("Failed to update schedule.");
            return false;
        }
    }

    // Method 24 detectErrors - What does this do?

    // Method 25 Handle Missing Data on Reports
    public boolean handleMissingData(String reportType) {
        if (!isLoggedIn) {
            System.out.println("User must be logged in to view report data");
            return false;
        }

        System.out.println("Checking for missing or incomplete data for " + reportType + " report...");

        boolean dataIsComplete = validateReportData(reportType);

        if (dataIsComplete) {
            System.out.println("All data is available and complete. Proceeding with " + reportType + " report.");
            return true;
        } else {
            System.out.println("Missing or incomplete data detected for " + reportType + " report.");

            // get user to correct data
            boolean corrected = promptUserToCorrectData(reportType);

            if (corrected) {
                System.out.println("Missing data corrected. Proceeding with " + reportType + " report.");
                return true;
            } else {
                System.out.println("Report generation failed: missing data could not be resolved.");
                return false;
            }
        }
    }

    // Method 26: Apply Report Customisations
    public boolean updateCustomisations(Map<String, String> customisations) {
        if (!isLoggedIn) {
            System.out.println("User must be logged in to apply report customisations");
            return false;
        }

        System.out.println("Applying report customisations...");

        boolean applied = applyCustomisationSettings(customisations);

        if (applied) {
            System.out.println("Customisations applied successfully:");
            for (String key : customisations.keySet()) {
                System.out.println(" - " + key + ": " + customisations.get(key));
            }
            return true;
        } else {
            System.out.println("Failed to apply customisations");
            return false;
        }
    }

    // Method 27: Update Roles and Permissions
    public boolean updateRolesAndPermissions(String username, String newRole) {
        if (!isLoggedIn) {
            System.out.println("User must be logged in to update roles and permissions");
            return false;
        }

        if (!currentUser.equals("admin")) {
            System.out.println("Only administrators can update user roles and permissions");
            return false;
        }

        if (!userCredentials.containsKey(username)) {
            System.out.println("User not found: " + username);
            return false;
        }

        boolean updated = applyRoleUpdate(username, newRole);

        if (updated) {
            System.out.println("Updated role for user '" + username + "' to '" + newRole + "'");
            return true;
        } else {
            System.out.println("Failed to update role for user '" + username + "'");
            return false;
        }
    }

    // Method 28: Retrieve Security Logs
    public boolean retrieveLogs() {
        if (!isLoggedIn) {
            System.out.println("User must be logged in to retrieve logs");
            return false;
        }
    
        if (!"manager".equals(userRoles.get(currentUser))) {
            System.out.println("Only administrators can retrieve security logs");
            return false;
        }
    
        System.out.println("Retrieving security logs...");
    
        String logs = fetchSecurityLogs();
    
        if (logs != null && !logs.isEmpty()) {
            System.out.println("Security Logs:\n" + logs);
            return true;
        } else {
            System.out.println("No logs found or error retrieving logs.");
            return false;
        }
    }

    // Method 29 Handle Security Alerts
    public boolean handleSecurityAlerts() {
        if (!isLoggedIn) {
            System.out.println("User must be logged in to handle security alerts");
            return false;
        }
    
        if (!"manager".equals(userRoles.get(currentUser))) {
            System.out.println("Only administrators can manage security alerts");
            return false;
        }
    
        System.out.println("Retrieving security alerts...");
    
        String[] alerts = getSecurityAlerts();
    
        if (alerts.length == 0) {
            System.out.println("No current security alerts.");
            return true;
        }
    
        System.out.println("SECURITY ALERTS:");
        for (String alert : alerts) {
            System.out.println(" - " + alert);
        }
    
        respondToAlerts(alerts);  
        return true;
    }
    
    // Method 30 Flag Suspicous Activity
    public boolean flagSuspiciousActivity(String username, String deviceInfo) {
        if (!isLoggedIn) {
            System.out.println("User must be logged in to perform security checks");
            return false;
        }
    
        if (!userCredentials.containsKey(username)) {
            System.out.println("Unknown user attempted login: " + username);
            return false;
        }
    
        if (!isRecognisedDevice(username, deviceInfo)) {
            System.out.println("Suspicious activity detected: Unrecognised device for user '" + username + "'");
            logSuspiciousLogin(username, deviceInfo);
            alertAdministrator(username, deviceInfo);
            return true;
        }
    
        System.out.println("Device recognised for user '" + username + "'. Login allowed.");
        return false;
    }

    // Method 31 Deactivate Accounts
    public boolean deactivateAccount(String username) {
        if (!isLoggedIn) {
            System.out.println("User must be logged in to manage accounts.");
            return false;
        }
    
        if (!currentUser.equals("admin")) {
            System.out.println("Only administrators can deactivate accounts.");
            return false;
        }
    
        if (!userCredentials.containsKey(username)) {
            System.out.println("Cannot deactivate. User '" + username + "' does not exist.");
            return false;
        }
    
        userCredentials.remove(username);
        userRoles.remove(username);
        System.out.println("Account for user '" + username + "' has been deactivated.");
        
        logAccountDeactivation(username);
        return true;
    }
    
    







    
    // Helper methods

    //Helper method for Method 7 handleCapacityExceeded()
    private boolean checkForCapacityExpansion() {
        // Logic to check if system capacity can be expanded
        // This might check server resources, database connections, etc.
        return Math.random() > 0.5; // Simplified logic for example purposes
    }
    
    //Helper method for Method 8 handleInvalidInput()
    private void logInvalidInput() {
        // In a real system, this would log details about the invalid input
        System.out.println("Logging invalid input event at " + System.currentTimeMillis());
    }
    
    //Helper method for method 6 handleNoAvailability()
    private void notifyResourceConstraint() {
        System.out.println("Notifying managers about resource constraints");
        // In a real system, this might send emails, SMS, or update a dashboard
    }

    //Helper methods for method 10 manageOrder()
    private boolean createOrder(int orderId) {
        System.out.println("Creating new order with ID: " + orderId);
        return true; // Simplified for demo
    }
    
    private boolean updateOrder(int orderId) {
        System.out.println("Updating existing order: " + orderId);
        return true; // Simplified for demo
    }
    
    private boolean cancelOrder(int orderId) {
        System.out.println("Cancelling order: " + orderId);
        return true; // Simplified for demo
    }

    private boolean completeOrder(int orderId) {
        System.out.println("Completing order: " + orderId);
        return true; // Simplified for demo
    }
    
    //Helper methods for method 11 handleNotification()
    private boolean isPriorityValid(String priority) {
        String lowercasePriority = priority.toLowerCase();
        return lowercasePriority.equals("high") || 
               lowercasePriority.equals("normal") || 
               lowercasePriority.equals("low");
    }
    
    private void logNotification(String recipient, String message, String priority) {
        System.out.println("Logging notification: To=" + recipient + 
                           ", Priority=" + priority + 
                           ", Message=" + message);
    }
    
    //Helper methods for method 12 updateOrderStatus()
    private boolean isValidOrderStatus(String status) {
        String[] validStatuses = {"new", "preparing", "ready", "delivered", "cancelled"};
        String lowercaseStatus = status.toLowerCase();
        
        for (String validStatus : validStatuses) {
            if (validStatus.equals(lowercaseStatus)) {
                return true;
            }
        }
        
        return false;
    }
    
    private void notifyStatusUpdate(int orderId, String newStatus) {
        System.out.println("Notifying relevant parties about status update for order " + orderId);
        // In a real system, this might notify kitchen staff, waiters, etc.
    }

    //Helper methods for method 13 handleCorrections()
    private void logCorrection(int itemId, String correctionType, String newValue) {
        System.out.println("Logging correction: Item=" + itemId + 
                           ", Type=" + correctionType + 
                           ", NewValue=" + newValue);
    }
    
    private boolean isValidCorrectionType(String correctionType) {
        String[] validTypes = {"price", "quantity", "description", "status"};
        String lowerType = correctionType.toLowerCase();
        
        for (String type : validTypes) {
            if (type.equals(lowerType)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean applyCorrectionToItem(int itemId, String correctionType, String newValue) {
        System.out.println("Applying " + correctionType + " correction to item " + itemId);
        // In a real system, this would update the database
        return true; // Simplified for demo
    }
    
    private void notifyCorrectionApplied(int itemId, String correctionType) {
        System.out.println("Notifying relevant staff about correction to item " + itemId);
    }
    
    //Helper methods for method 14 confirmActions()
    private void recordActionConfirmation(String actionType, int actionId) {
        System.out.println("Recording confirmation of " + actionType + " #" + actionId);
    }
    
    private boolean isActionValid(String actionType, int actionId) {
        // In a real system, this would check the database
        System.out.println("Validating action " + actionType + " #" + actionId);
        return true; // Simplified for demo
    }
    
    private boolean updateActionStatus(String actionType, int actionId, String status) {
        System.out.println("Updating status of " + actionType + " #" + actionId + " to " + status);
        return true; // Simplified for demo
    }
    
    private void notifyActionConfirmed(String actionType, int actionId) {
        System.out.println("Notifying relevant parties about confirmation of " + actionType + " #" + actionId);
    }
    
    //Helper method for method 15 authorizePayment()
    private boolean doesOrderExist(int orderId) {
        // In a real system, this would check the database
        return true; // Simplified for demo
    }
    
    private boolean isValidPaymentMethod(String paymentMethod) {
        String[] validMethods = {"cash", "credit", "debit", "gift card", "mobile payment"};
        String lowerMethod = paymentMethod.toLowerCase();
        
        for (String method : validMethods) {
            if (method.equals(lowerMethod)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean processPaymentAuthorization(int orderId, double amount, String paymentMethod) {
        System.out.println("Processing " + paymentMethod + " payment of $" + amount + " for order " + orderId);
        // In a real system, this would call the payment processor
        return Math.random() > 0.1; // 90% success rate for demo purposes
    }
    
    private void updateOrderPaymentStatus(int orderId, String status) {
        System.out.println("Updating payment status for order " + orderId + " to: " + status);
    }
    
    private void generatePaymentReceipt(int orderId, double amount, String paymentMethod) {
        System.out.println("Generating receipt for order " + orderId + " - $" + amount + " via " + paymentMethod);
    }
    
    // Getters and setters
    public int getSystemID() { 
        return systemID; 
    }
    public String getSystemStatus() { 
        return systemStatus; 
    }
    public void setSystemStatus(String systemStatus) { 
        this.systemStatus = systemStatus; 
    }
    public boolean isMissingDataStatus() { 
        return missingDataStatus; 
    }
    public void setMissingDataStatus(boolean missingDataStatus) { 
        this.missingDataStatus = missingDataStatus; 
    }
    public String getCurrentUser() { 
        return currentUser; 
    }
    public boolean isLoggedIn() { 
        return isLoggedIn; 
    }

    //Helper for Method 16 Waiter Notification or Order being ready
    private boolean sendNotificationToWaiter(int tableNumber, String message) {
        System.out.println("Sending to Waiter for table" + tableNumber + ": " + message);

        return true; 
    }

    //Helper for Method 17 Check Stocks Levels in Inventory

    // Mock Stock Levels
    private int getStockLevelForItem(String itemName) {
        Map<String, Integer> mockStock = new HashMap<>();
        mockStock.put("Steak", 12);
        mockStock.put("Chips", 3);
        mockStock.put("Pizza Bases", 25);
        mockStock.put("Salad Leaves", 0);
    
        return mockStock.getOrDefault(itemName, 0);
    }

    //Reorder Values for Par Stock
    private int getReorderAmount(String itemName) {  
        Map<String, Integer> reorderThresholds = new HashMap<>();
        reorderThresholds.put("Steak", 10);
        reorderThresholds.put("Chips", 5);
        reorderThresholds.put("Pizza Bases", 15);
        reorderThresholds.put("Salad Leaves", 5);
    
        return reorderThresholds.getOrDefault(itemName, 5);
    }

    // Helper Method for 19 Get Sales Report

    // Total Revenue Simulation
    private double calculateTotalRevenue(String range) {
        switch (range.toLowerCase()) {
            case "daily": return 425.75;
            case "weekly": return 2875.40;
            case "monthly": return 11345.90;
            default: return 0.0;
        }
    }

    // Customer Numbers Simulation
    private int calculateCustomerCount(String range) {
        switch (range.toLowerCase()) {
            case "daily": return 48;
            case "weekly": return 312;
            case "monthly": return 1280;
            default: return 0;
        }
    }

    // Top Seller Simulation    
    private String getBestSellingItem(String range) {
        switch (range.toLowerCase()) {
            case "daily": return "Cheeseburger";
            case "weekly": return "Margherita Pizza";
            case "monthly": return "Fish and Chips";
            default: return "N/A";
        }
    }

    // Helper for Method 20 - Get Outliers for Sales Report
    // Expected Revenue Numbers
    private double getExpectedRevenue(String range) {
        switch (range.toLowerCase()) {
            case "daily": return 500;
            case "weekly": return 3500;
            case "monthly": return 14000;
            default: return 0;
        }
    }

    // Helper for Method 21: Send  Data Based on UI Type
    private boolean dispatchToUI(String uiType, Object data) {
        switch (uiType.toLowerCase()) {
            case "securityui":
                System.out.println("[Security UI] Security Alert: " + data);
                break;
            case "inventoryui":
                System.out.println("[Inventory UI] Stock Update: " + data);
                break;
            case "scheduleui":
                System.out.println("[Schedule UI] Staff Rota: " + data);
                break;
            case "salesui":
                System.out.println("[Sales UI] Report: " + data);
                break;
            case "kitchenui":
                System.out.println("[Kitchen UI] New Order Placed: " + data);
                break;
            case "orderui":
                System.out.println("[Order UI] Order Summary: " + data);
                break;
            case "paymentui":
                System.out.println("[Payment UI] Payment Confirmation: " + data);
                break;
            default:
                System.out.println("Unknown UI type: " + uiType);
                return false;
        }
        return true;
    }

    // Helper for Method 23 Update Schedule Record
    private boolean processScheduleUpdate(Object record) {
        // Simulated update logic — this would normally interact with a database or file
        System.out.println("Updating schedule with data: " + record);
        return true; 
    }

    // Method 25 Handle Missing Data on Reports

    // Randomly simulate missing or complete data
    private boolean validateReportData(String reportType) {
        return Math.random() > 0.3; // 70% chance data is okay for testing purposes 
    }

    // Simulate user prompt to correct data
    private boolean promptUserToCorrectData(String reportType) {
        System.out.println("Prompting user to correct missing data for: " + reportType);

        // Simulate user correcting the data ( Random 50% chance for testing)
        boolean corrected = Math.random() > 0.5;

        if (corrected) {
            System.out.println("User corrected the data.");
        } else {
            System.out.println("User failed to correct the data.");
        }

        return corrected;
    }

    //Helper for Method 26 Customising report

    // Simulate applying custom filters, views, or formats
    private boolean applyCustomisationSettings(Map<String, String> customisations) {
        // In a real system, this would update the report config context
        return true;
    }

    // Testing Data that can be used during testing process

    // Map<String, String> filters = new HashMap<>();
    // filters.put("Date Range", "2025-04-01 to 2025-04-07");
    // filters.put("Format", "PDF");
    // filters.put("Department", "Kitchen");

    // system.updateCustomisations(filters); // example usage

    // Helper for Method 27: Actually update the role
    private boolean applyRoleUpdate(String username, String newRole) {
        if (userRoles == null) {
            userRoles = new HashMap<>();
        }
        
        userRoles.put(username, newRole.toLowerCase());
        return true;
    }

    // Helper for Method 28 Retrieve Security Logs
    private String fetchSecurityLogs() {
        // Simulate fetching from a database or log file
        StringBuilder logs = new StringBuilder();
        logs.append("Log [2025-04-24 10:45] - User admin logged in\n");
        logs.append("Log [2025-04-24 11:12] - User waiter failed login attempt\n");
        logs.append("Log [2025-04-24 13:30] - Role update performed by admin\n");
        logs.append("Log [2025-04-24 14:00] - Unauthorized access attempt blocked\n");
        return logs.toString();
    }

    // Helper for Method 29 Handle Security Alerts
    private String[] getSecurityAlerts() {
        // Simulated active alerts
        return new String[]{
            "Multiple failed login attempts detected for user 'waiter'",
            "Suspicious access attempt to manager functions",
            "Unauthorized data modification blocked"
        };
    }
    
    private void respondToAlerts(String[] alerts) {
        System.out.println("\nResponding to alerts...");
    
        for (String alert : alerts) {
            System.out.println(" - Logged alert: \"" + alert + "\"");
            // Simulated response actions (e.g., notify IT, lock account, create incident report)
        }
    
        System.out.println("All alerts logged and flagged for follow-up.\n");
    }

    // Helper for Method 30 Flag Suspicious Activity
    private boolean isRecognisedDevice(String username, String deviceInfo) {
        // Mocked list of known devices for demo
        Map<String, String[]> trustedDevices = new HashMap<>();
        trustedDevices.put("admin", new String[]{"AdminPC01", "SecureTablet"});
        trustedDevices.put("waiter", new String[]{"WaiterTablet01"});
        trustedDevices.put("chef", new String[]{"KitchenStation01"});
    
        String[] knownDevices = trustedDevices.getOrDefault(username, new String[0]);
        for (String device : knownDevices) {
            if (device.equalsIgnoreCase(deviceInfo)) {
                return true;
            }
        }
        return false;
    }
    
    private void logSuspiciousLogin(String username, String deviceInfo) {
        System.out.println("Logging suspicious login attempt by '" + username + "' from device: " + deviceInfo);
        // In a real system, this would insert into a security log table or file
    }
    
    private void alertAdministrator(String username, String deviceInfo) {
        System.out.println("ALERT: Administrator notified of suspicious login attempt.");
        System.out.println("User: " + username);
        System.out.println("Device: " + deviceInfo);
        // In a real system, this could send an email or push notification
    }

    // Helper for Method 31 Deactivate Account
    private void logAccountDeactivation(String username) {
        System.out.println("Security Log: '" + username + "' account deactivated by administrator.");
        // Logs in Security Log for testing
    }
    
    
    
    

 
    
    
}  
