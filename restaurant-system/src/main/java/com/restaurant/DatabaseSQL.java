package com.restaurant;

import java.sql.*;
import java.util.*;

public class DatabaseSQL {
    private static DatabaseSQL instance;
    private Connection conn;

    private static final String DB_URL = "jdbc:sqlite:restaurant.db";

    private DatabaseSQL() {}

    public static DatabaseSQL getInstance() {
        if (instance == null) {
            instance = new DatabaseSQL();
            instance.connectInfo();
        }
        return instance;
    }

    public boolean connectInfo() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Connected to SQLite database.");
            return true;
        } catch (Exception e) {
            System.out.println("Database connection failed: " + e.getMessage());
            return false;
        }
    }

    public boolean storeReservation(Reservation reservation) {
        String sql = "INSERT INTO Reservation (CustomerID, DateTime, PartySize, Status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, reservation.getCustomerID());
            pstmt.setString(2, reservation.getDateTime());
            pstmt.setInt(3, reservation.getPartySize());
            pstmt.setString(4, reservation.getStatus());
            pstmt.executeUpdate();
            System.out.println("Stored reservation: " + reservation.getReservationID());
            return true;
        } catch (SQLException e) {
            System.out.println("Error storing reservation: " + e.getMessage());
            return false;
        }
    }

    public boolean storePaymentData(Payment payment) {
        String sql = "INSERT INTO Payment (OrderID, PaymentDate, Amount, PaymentMethod) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, payment.getOrderID());
            pstmt.setString(2, payment.getTimestamp());
            pstmt.setDouble(3, payment.getAmount());
            pstmt.setString(4, payment.getPaymentMethod());
            pstmt.executeUpdate();
            System.out.println("Stored payment: " + payment.getPaymentID());
            return true;
        } catch (SQLException e) {
            System.out.println("Error storing payment: " + e.getMessage());
            return false;
        }
    }

    public Payment retrievePaymentData(int paymentId) {
        String sql = "SELECT * FROM Payment WHERE PaymentID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, paymentId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Payment payment = new Payment(
                    rs.getInt("PaymentID"),
                    rs.getInt("OrderID"),
                    rs.getDouble("Amount")
                );
                payment.setPaymentMethod(rs.getString("PaymentMethod"));
                payment.setTimestamp(rs.getString("PaymentDate"));
                payment.setStatus("Completed");
                return payment;
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving payment: " + e.getMessage());
        }
        return null;
    }

    public boolean manageOrderData(Order order, String action) {
        try {
            if (action.equalsIgnoreCase("create")) {
                String sql = "INSERT INTO `Order` (ReservationID, OrderDate, Status, TotalPrice) VALUES (?, ?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setInt(1, order.getCustomerID());
                    pstmt.setString(2, java.time.LocalDate.now().toString());
                    pstmt.setString(3, order.getOrderStatus());
                    pstmt.setDouble(4, order.getTotalAmount());
                    pstmt.executeUpdate();
                }
                System.out.println("Order created: " + order.getOrderID());
                return true;
            } else if (action.equalsIgnoreCase("update")) {
                String sql = "UPDATE `Order` SET Status = ?, TotalPrice = ? WHERE OrderID = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, order.getOrderStatus());
                    pstmt.setDouble(2, order.getTotalAmount());
                    pstmt.setInt(3, order.getOrderID());
                    pstmt.executeUpdate();
                }
                System.out.println("Order updated: " + order.getOrderID());
                return true;
            } else if (action.equalsIgnoreCase("delete")) {
                String sql = "DELETE FROM `Order` WHERE OrderID = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setInt(1, order.getOrderID());
                    pstmt.executeUpdate();
                }
                System.out.println("Order deleted: " + order.getOrderID());
                return true;
            } else {
                System.out.println("Unknown action for order management.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error managing order: " + e.getMessage());
            return false;
        }
    }

    public Order retrieveOrderData(int orderId) {
        String sql = "SELECT * FROM `Order` WHERE OrderID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Order order = new Order(
                    rs.getInt("OrderID"),
                    rs.getInt("ReservationID")
                );
                order.setOrderStatus(rs.getString("Status"));
                order.setTotalAmount(rs.getDouble("TotalPrice"));
                return order;
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving order: " + e.getMessage());
        }
        return null;
    }

    public boolean storeTransaction(Map<String, Object> data) {
        System.out.println("Transaction logged (not persisted): " + data.toString());
        return true;
    }

    public List<Map<String, Object>> retrieveSalesData(String criteria) {
        List<Map<String, Object>> salesData = new ArrayList<>();
        String sql = "SELECT o.OrderID, o.TotalPrice, p.Amount, p.PaymentMethod FROM `Order` o JOIN Payment p ON o.OrderID = p.OrderID";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Map<String, Object> record = new HashMap<>();
                record.put("OrderID", rs.getInt("OrderID"));
                record.put("TotalPrice", rs.getDouble("TotalPrice"));
                record.put("AmountPaid", rs.getDouble("Amount"));
                record.put("PaymentMethod", rs.getString("PaymentMethod"));
                salesData.add(record);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving sales data: " + e.getMessage());
        }
        return salesData;
    }

    public boolean saveReport(String reportName, String reportData) {
        System.out.println("Report saved (not persisted): " + reportName);
        return true;
    }

    public String retrieveReport(String reportName) {
        System.out.println("Retrieving report (mock): " + reportName);
        return "Mock Report Content for " + reportName;
    }

    public void logAccess(int userId, String action) {
        System.out.println("Logging access: User " + userId + " Action: " + action);
    }

    public List<String> retrieveUserRoles(int userId) {
        List<String> roles = new ArrayList<>();
        roles.add("Customer"); // Mock role
        return roles;
    }

    public void close() {
        try {
            if (conn != null) conn.close();
            System.out.println("Database connection closed.");
        } catch (SQLException e) {
            System.out.println("Error closing database: " + e.getMessage());
        }
    }
}
