package com.restaurant;


/**
 * Stakeholder class representing individuals with interest in the restaurant
 */
public class StakeHolder {
    // Attributes
    private int stakeHolderID;
    private String department;
    
    /**
     * Constructor for Stakeholder
     * @param stakeholderID The unique ID for this stakeholder
     * @param department The stakeholder's department or area of interest
     */
    public StakeHolder(int stakeHolderID, String department) {
        this.stakeHolderID = stakeHolderID;
        this.department = department;
    }
    
    /**
     * Views a specific report
     * @param reportId The report to view
     * @return The report content
     */
    public Object viewReport(int reportId) {
        System.out.println("Stakeholder from " + department + " viewing report " + reportId);
        // In a real application, this would retrieve the report
        return "Report " + reportId + " content";
    }
    
    /**
     * Submits feedback on various aspects of the business
     * @param subject The subject of feedback
     * @param feedback The feedback content
     * @return Feedback ID if submission is successful
     */
    public int submitFeedback(String subject, String feedback) {
        System.out.println("Stakeholder from " + department + " submitting feedback on " + subject);
        // In a real application, this would store the feedback
        return (int)(Math.random() * 10000);
    }
    
    // Getters and setters
    public int getStakeholderID() { 
        return stakeHolderID; 
    }
    public String getDepartment() { 
        return department; 
    }
    public void setDepartment(String department) { 
        this.department = department; 
    }
}
