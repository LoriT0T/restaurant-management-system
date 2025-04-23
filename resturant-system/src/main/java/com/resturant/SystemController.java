package com.resturant;

import java.util.HashMap;
import java.util.Map;

public class SystemController {
    // Attributes
    private int systemID;
    private String systemStatus;
    private boolean missingDataStatus;
    
    // User authentication storage
    private Map<String, String> userCredentials;
    private String currentUser;
    private boolean isLoggedIn;
    
    // Constructor
    public SystemController() {
        this.systemID = generateSystemID();
        this.systemStatus = "Active";
        this.missingDataStatus = false;
        this.userCredentials = new HashMap<>();
        this.isLoggedIn = false;
        
        // Add some default users for testing
        userCredentials.put("admin", "admin123");
        userCredentials.put("waiter", "waiter123");
        userCredentials.put("chef", "chef123");
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
}