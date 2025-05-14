package com.fooddelivery.controller;

import com.fooddelivery.model.Restaurant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "HomeServlet", value = "/home")
//@WebServlet("/home")
//@WebServlet(name = "HomeServlet", value = {"/home", "/"})
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get featured restaurants (mock data - replace with actual data from your files)
        List<Restaurant> featuredRestaurants = getFeaturedRestaurants();
        request.setAttribute("featuredRestaurants", featuredRestaurants);

        // Get popular categories (mock data)
        List<String> popularCategories = List.of("Pizza", "Burger", "Sushi", "Pasta", "Salad");
        request.setAttribute("popularCategories", popularCategories);

        request.getRequestDispatcher("/views/home.jsp").forward(request, response);
    }

    private List<Restaurant> getFeaturedRestaurants() {
        // In a real app, you would read this from restaurants.txt
        List<Restaurant> restaurants = new ArrayList<>();

        restaurants.add(new Restaurant("R001", "Pizza Heaven", "Italian", "4.5", "30-45 min", "/images/restaurants/pizza-heaven.jpg"));
        restaurants.add(new Restaurant("R002", "Burger King", "American", "4.2", "20-30 min", "/images/restaurants/burger-king.jpg"));
        restaurants.add(new Restaurant("R003", "Sushi World", "Japanese", "4.7", "40-55 min", "/images/restaurants/sushi-world.jpg"));
        restaurants.add(new Restaurant("R004", "Pasta Palace", "Italian", "4.3", "25-40 min", "/images/restaurants/pasta-palace.jpg"));

        return restaurants;
    }
}