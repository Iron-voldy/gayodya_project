package com.fooddelivery.model;

public class Admin extends User {
    private String adminLevel;
    private String department;

    public Admin(String id, String username, String password, String email,
                 String adminLevel, String department) {
        super(id, username, password, email, "ADMIN");
        this.adminLevel = adminLevel;
        this.department = department;
    }

    // Getters and Setters
    public String getAdminLevel() { return adminLevel; }
    public void setAdminLevel(String adminLevel) { this.adminLevel = adminLevel; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    // File handling methods
    @Override
    public String toFileString() {
        return String.join("|",
                getId(), getUsername(), getPassword(), getEmail(),
                adminLevel, department
        );
    }

    private CharSequence getEmail() {
        return null;
    }

    private CharSequence getPassword() {
        return null;
    }

    private CharSequence getUsername() {
        return null;
    }

    public CharSequence getId() {
        return null;
    }

    public static Admin fromFileString(String fileString) {
        String[] parts = fileString.split("\\|");
        if (parts.length == 6) {
            return new Admin(
                    parts[0], parts[1], parts[2], parts[3],
                    parts[4], parts[5]
            );
        }
        return null;
    }
}