package com.fooddelivery.model;

import java.io.Serializable;

public class OrderItem implements Serializable {
    private String id;
    private String menuItemId;
    private String name;
    private double price;
    private int quantity;
    private String specialInstructions;

    public OrderItem(String menuItemId, String name, double price, int quantity) {
        this.menuItemId = menuItemId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getMenuItemId() { return menuItemId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public String getSpecialInstructions() { return specialInstructions; }
    public void setSpecialInstructions(String instructions) { this.specialInstructions = instructions; }

    // File operations
    public String toFileString() {
        return String.join(",",
                menuItemId, name, String.valueOf(price),
                String.valueOf(quantity),
                specialInstructions != null ? specialInstructions : ""
        );
    }

    public static OrderItem fromFileString(String line) {
        String[] parts = line.split(",");
        if (parts.length >= 4) {
            OrderItem item = new OrderItem(
                    parts[0], parts[1],
                    Double.parseDouble(parts[2]),
                    Integer.parseInt(parts[3])
            );
            if (parts.length > 4) {
                item.setSpecialInstructions(parts[4]);
            }
            return item;
        }
        return null;
    }
}