package com.fooddelivery.util;

import com.fooddelivery.model.Admin;
import java.util.ArrayList;
import java.util.List;

public class AdminFileHandler {
    private static final String FILE_PATH = "admins.txt";

    public static void saveAdmin(Admin admin) {
        String data = admin.toFileString() + "\n";
        FileHandler.appendToFile(FILE_PATH, data);
    }

    public static List<Admin> getAllAdmins() {
        List<String> lines = FileHandler.readFromFile(FILE_PATH);
        List<Admin> admins = new ArrayList<>();

        for (String line : lines) {
            Admin admin = Admin.fromFileString(line);
            if (admin != null) {
                admins.add(admin);
            }
        }
        return admins;
    }

    public static Admin getAdminById(String adminId) {
        List<Admin> admins = getAllAdmins();
        for (Admin admin : admins) {
            if (admin.getId().equals(adminId)) {
                return admin;
            }
        }
        return null;
    }

    public static void updateAdmin(Admin updatedAdmin) {
        List<Admin> admins = getAllAdmins();
        List<String> updatedLines = new ArrayList<>();

        for (Admin admin : admins) {
            if (admin.getId().equals(updatedAdmin.getId())) {
                updatedLines.add(updatedAdmin.toFileString());
            } else {
                updatedLines.add(admin.toFileString());
            }
        }

        FileHandler.writeToFile(FILE_PATH, String.join("\n", updatedLines));
    }

    public static void deleteAdmin(String adminId) {
        List<Admin> admins = getAllAdmins();
        List<String> updatedLines = new ArrayList<>();

        for (Admin admin : admins) {
            if (!admin.getId().equals(adminId)) {
                updatedLines.add(admin.toFileString());
            }
        }

        FileHandler.writeToFile(FILE_PATH, String.join("\n", updatedLines));
    }
}