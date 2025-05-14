package com.fooddelivery.util;

import java.util.regex.Pattern;

public class FileValidator {
    // Validate email format
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

    // Validate phone number format
    public static boolean isValidPhone(String phone) {
        String phoneRegex = "^[+]?[(]?[0-9]{3}[)]?[-\\s.]?[0-9]{3}[-\\s.]?[0-9]{4,6}$";
        return Pattern.compile(phoneRegex).matcher(phone).matches();
    }

    // Validate ID format (prefix followed by numbers)
    public static boolean isValidId(String id, String prefix) {
        if (!id.startsWith(prefix)) return false;
        try {
            Integer.parseInt(id.substring(prefix.length()));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Validate rating (1-5)
    public static boolean isValidRating(int rating) {
        return rating >= 1 && rating <= 5;
    }

    // Validate price (positive number)
    public static boolean isValidPrice(double price) {
        return price > 0;
    }

    // Validate required field
    public static boolean isNotEmpty(String field) {
        return field != null && !field.trim().isEmpty();
    }
}