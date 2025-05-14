package com.fooddelivery.util;

import com.fooddelivery.model.MenuItem;
import java.util.ArrayList;
import java.util.List;

public class MenuItemFileHandler extends FileHandler {
    private static final String MENU_ITEMS_FILE = "menuitems.txt";

    public static List<MenuItem> getMenuItemsByRestaurant(String restaurantId) {
        List<MenuItem> items = new ArrayList<>();
        List<String> lines = readAllLines(getFilePath(MENU_ITEMS_FILE));

        for (String line : lines) {
            MenuItem item = MenuItem.fromFileString(line);
            if (item != null && item.getRestaurantId().equals(restaurantId)) {
                items.add(item);
            }
        }
        return items;
    }

    public static void saveMenuItem(MenuItem item) {
        appendLine(getFilePath(MENU_ITEMS_FILE), item.toFileString());
    }

    public static void updateMenuItem(MenuItem updatedItem) {
        List<String> lines = readAllLines(getFilePath(MENU_ITEMS_FILE));
        List<String> updatedLines = new ArrayList<>();

        for (String line : lines) {
            MenuItem item = MenuItem.fromFileString(line);
            if (item != null && item.getId().equals(updatedItem.getId())) {
                updatedLines.add(updatedItem.toFileString());
            } else {
                updatedLines.add(line);
            }
        }

        writeAllLines(getFilePath(MENU_ITEMS_FILE), updatedLines);
    }

    public static void deleteMenuItem(String itemId) {
        List<String> lines = readAllLines(getFilePath(MENU_ITEMS_FILE));
        List<String> updatedLines = new ArrayList<>();

        for (String line : lines) {
            MenuItem item = MenuItem.fromFileString(line);
            if (item != null && !item.getId().equals(itemId)) {
                updatedLines.add(line);
            }
        }

        writeAllLines(getFilePath(MENU_ITEMS_FILE), updatedLines);
    }

    public static MenuItem getMenuItemById(String itemId) {
        List<String> lines = readAllLines(getFilePath(MENU_ITEMS_FILE));
        for (String line : lines) {
            MenuItem item = MenuItem.fromFileString(line);
            if (item != null && item.getId().equals(itemId)) {
                return item;
            }
        }
        return null;
    }
}