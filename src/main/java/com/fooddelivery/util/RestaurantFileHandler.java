package com.fooddelivery.util;

import com.fooddelivery.model.Restaurant;
import java.util.ArrayList;
import java.util.List;

public class RestaurantFileHandler extends FileHandler {
    private static final String RESTAURANTS_FILE = "restaurants.txt";

    public static List<Restaurant> getAllRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        List<String> lines = readAllLines(getFilePath(RESTAURANTS_FILE));

        for (String line : lines) {
            Restaurant restaurant = Restaurant.fromFileString(line);
            if (restaurant != null) {
                restaurants.add(restaurant);
            }
        }
        return restaurants;
    }

    public static void saveRestaurant(Restaurant restaurant) {
        appendLine(getFilePath(RESTAURANTS_FILE), restaurant.toFileString());
    }

    public static void updateRestaurant(Restaurant updatedRestaurant) {
        List<Restaurant> restaurants = getAllRestaurants();
        List<String> updatedLines = new ArrayList<>();

        for (Restaurant restaurant : restaurants) {
            if (restaurant.getId().equals(updatedRestaurant.getId())) {
                updatedLines.add(updatedRestaurant.toFileString());
            } else {
                updatedLines.add(restaurant.toFileString());
            }
        }

        writeAllLines(getFilePath(RESTAURANTS_FILE), updatedLines);
    }

    public static void deleteRestaurant(String restaurantId) {
        List<Restaurant> restaurants = getAllRestaurants();
        List<String> updatedLines = new ArrayList<>();

        for (Restaurant restaurant : restaurants) {
            if (!restaurant.getId().equals(restaurantId)) {
                updatedLines.add(restaurant.toFileString());
            }
        }

        writeAllLines(getFilePath(RESTAURANTS_FILE), updatedLines);
    }

    public static Object findRestaurantById(String restaurantId) {
        return null;
    }
}