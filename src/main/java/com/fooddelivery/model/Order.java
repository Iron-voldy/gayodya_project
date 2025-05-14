package com.fooddelivery.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order implements Serializable {
    private String id;
    private String userId;
    private String restaurantId;
    private List<OrderItem> items;
    private double totalAmount;
    private String deliveryAddress;
    private String paymentMethod;
    private String status; // PENDING, PREPARING, ON_THE_WAY, DELIVERED, CANCELLED
    private String createdAt;
    private String deliveryInstructions;

    // Constructors
    public Order() {
        this.id = "ORD-" + UUID.randomUUID().toString().substring(0, 8);
        this.items = new ArrayList<>();
        this.status = "PENDING";
        this.createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public Order(String userId, String restaurantId, String deliveryAddress) {
        this();
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.deliveryAddress = deliveryAddress;
    }

    public Order(Object userId, String restaurantId, String deliveryAddress) {

    }

    // Getters and Setters
    public String getId() { return id; }
    public String getUserId() { return userId; }
    public String getRestaurantId() { return restaurantId; }
    public List<OrderItem> getItems() { return items; }
    public double getTotalAmount() { return totalAmount; }
    public String getDeliveryAddress() { return deliveryAddress; }
    public String getPaymentMethod() { return paymentMethod; }
    public String getStatus() { return status; }
    public String getCreatedAt() { return createdAt; }
    public String getDeliveryInstructions() { return deliveryInstructions; }

    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public void setStatus(String status) { this.status = status; }
    public void setDeliveryInstructions(String instructions) { this.deliveryInstructions = instructions; }

    // Business methods
    public void addItem(OrderItem item) {
        this.items.add(item);
        calculateTotal();
    }

    public void removeItem(String itemId) {
        items.removeIf(item -> item.getId().equals(itemId));
        calculateTotal();
    }

    private void calculateTotal() {
        this.totalAmount = items.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }

    // File operations
    public String toFileString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.join(",",
                id, userId, restaurantId, String.valueOf(totalAmount),
                deliveryAddress, paymentMethod, status, createdAt,
                deliveryInstructions
        ));

        // Add items
        for (OrderItem item : items) {
            sb.append("|").append(item.toFileString());
        }

        return sb.toString();
    }

    public static Order fromFileString(String line) {
        String[] mainParts = line.split("\\|", 2);
        String[] orderParts = mainParts[0].split(",");

        if (orderParts.length >= 9) {
            Order order = new Order(
                    orderParts[1], orderParts[2], orderParts[4]
            );
            order.setId(orderParts[0]);
            order.setTotalAmount(Double.parseDouble(orderParts[3]));
            order.setPaymentMethod(orderParts[5]);
            order.setStatus(orderParts[6]);
            order.setCreatedAt(orderParts[7]);
            order.setDeliveryInstructions(orderParts[8]);

            // Add items if present
            if (mainParts.length > 1) {
                String[] itemLines = mainParts[1].split("\\|");
                for (String itemLine : itemLines) {
                    OrderItem item = OrderItem.fromFileString(itemLine);
                    if (item != null) {
                        order.addItem(item);
                    }
                }
            }

            return order;
        }
        return null;
    }

    private void setCreatedAt(String orderPart) {

    }

    private void setTotalAmount(double v) {

    }

    private void setId(String orderPart) {

    }
}