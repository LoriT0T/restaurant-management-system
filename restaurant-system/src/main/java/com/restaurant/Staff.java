package com.restaurant;


/**
 * Staff class representing restaurant employees
 */
public class Staff {
    // Attributes
    private int staffID;
    private String name;
    private String schedule;
    private PayInformation payInfo;
    
    /**
     * Inner class to encapsulate payment information
     */
    public class PayInformation {
        private double hourlyRate;
        private String paymentMethod;
        private String bankInfo;
        
        public PayInformation(double hourlyRate, String paymentMethod) {
            this.hourlyRate = hourlyRate;
            this.paymentMethod = paymentMethod;
        }
        
        public double getHourlyRate() { return hourlyRate; }
        public void setHourlyRate(double hourlyRate) { this.hourlyRate = hourlyRate; }
        public String getPaymentMethod() { return paymentMethod; }
        public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
        public String getBankInfo() { return bankInfo; }
        public void setBankInfo(String bankInfo) { this.bankInfo = bankInfo; }
    }
    
    /**
     * Constructor for Staff
     * @param staffID The unique ID for this staff member
     * @param name The staff member's name
     */
    public Staff(int staffID, String name) {
        this.staffID = staffID;
        this.name = name;
    }
    
    /**
     * Views the current schedule
     * @return The staff member's schedule
     */
    public Object viewSchedule() {
        System.out.println("Staff " + name + " viewing schedule");
        // In a real application, this would return schedule data
        return schedule;
    }
    
    /**
     * Requests time off for a date range
     * @param startDate Start of time off
     * @param endDate End of time off
     * @param reason Reason for request
     * @return Request ID
     */
    public int requestTimeOff(String startDate, String endDate, String reason) {
        System.out.println("Staff " + name + " requesting time off from " + startDate + " to " + endDate);
        // In a real application, this would create a time off request
        return (int)(Math.random() * 10000);
    }
    
    /**
     * Views order details
     * @param orderId The order to view
     * @return Order details
     */
    public Object viewOrder(int orderId) {
        System.out.println("Staff " + name + " viewing order " + orderId);
        // In a real application, this would retrieve order data
        return "Order details for " + orderId;
    }
    
    /**
     * Views payroll information
     * @return Payroll details
     */
    public Object viewPayroll() {
        System.out.println("Staff " + name + " viewing payroll information");
        // In a real application, this would retrieve payroll data
        return payInfo;
    }
    
    /**
     * Prepares to service an order
     * @param orderId The order to prepare for
     * @return true if preparation is successful
     */
    public boolean prepareOrder(int orderId) {
        System.out.println("Staff " + name + " preparing to service order " + orderId);
        // In a real application, this might update order status
        return true;
    }
    
    /**
     * Updates the status of an order
     * @param orderId The order to update
     * @param newStatus The new status
     * @return true if update is successful
     */
    public boolean updateOrderStatus(int orderId, String newStatus) {
        System.out.println("Staff " + name + " updating order " + orderId + " to status: " + newStatus);
        // In a real application, this would update the order in the database
        return true;
    }
    
    /**
     * Removes an allocated table from a reservation
     * @param reservationId The reservation to update
     * @param tableId The table to remove
     * @return true if removal is successful
     */
    public boolean removeAllocatedTable(int reservationId, int tableId) {
        System.out.println("Staff " + name + " removing table " + tableId + " from reservation " + reservationId);
        // In a real application, this would update the reservation
        return true;
    }
    
    /**
     * Updates a reservation with new details
     * @param reservationId The reservation to update
     * @param newDetails The updated details
     * @return true if update is successful
     */
    public boolean updateReservation(int reservationId, String newDetails) {
        System.out.println("Staff " + name + " updating reservation " + reservationId);
        // In a real application, this would update the reservation
        return true;
    }
    
    // Getters and setters
    public int getStaffID() { 
        return staffID; 
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
    public PayInformation getPayInfo() { 
        return payInfo; 
    }
    public void setPayInfo(PayInformation payInfo) { 
        this.payInfo = payInfo; 
    }
}