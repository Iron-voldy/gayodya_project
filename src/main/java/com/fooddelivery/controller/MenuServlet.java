package com.fooddelivery.controller;

import com.fooddelivery.model.MenuItem;
import com.fooddelivery.util.FileHandler;
import com.fooddelivery.util.MenuItemFileHandler;
import com.fooddelivery.util.RestaurantFileHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MenuServlet", value = "/menu/*")
@MultipartConfig
public class MenuServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();

        if (action == null) action = "/";

        switch (action) {
            case "/":
                listMenuItems(request, response);
                break;
            case "/add":
                showAddForm(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/view":
                viewMenuItem(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();

        if (action == null) action = "/";

        switch (action) {
            case "/add":
                addMenuItem(request, response);
                break;
            case "/update":
                updateMenuItem(request, response);
                break;
            case "/delete":
                deleteMenuItem(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void listMenuItems(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String restaurantId = request.getParameter("restaurantId");
        List<MenuItem> menuItems = MenuItemFileHandler.getMenuItemsByRestaurant(restaurantId);
        request.setAttribute("menuItems", menuItems);
        request.setAttribute("restaurant", RestaurantFileHandler.findRestaurantById(restaurantId));
        request.getRequestDispatcher("/views/menu/list.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String restaurantId = request.getParameter("restaurantId");
        request.setAttribute("restaurantId", restaurantId);
        request.getRequestDispatcher("/views/menu/add.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String itemId = request.getParameter("id");
        MenuItem menuItem = MenuItemFileHandler.getMenuItemById(itemId);

        if (menuItem != null) {
            request.setAttribute("menuItem", menuItem);
            request.getRequestDispatcher("/views/menu/edit.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void viewMenuItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String itemId = request.getParameter("id");
        MenuItem menuItem = MenuItemFileHandler.getMenuItemById(itemId);

        if (menuItem != null) {
            request.setAttribute("menuItem", menuItem);
            request.getRequestDispatcher("/views/menu/view.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void addMenuItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MenuItem menuItem = new MenuItem(
                request.getParameter("restaurantId"),
                request.getParameter("name"),
                request.getParameter("category"),
                Double.parseDouble(request.getParameter("price"))
        );

        menuItem.setDescription(request.getParameter("description"));
        menuItem.setVegetarian(request.getParameter("vegetarian") != null);
        menuItem.setSpicy(request.getParameter("spicy") != null);
        menuItem.setPreparationTime(Integer.parseInt(request.getParameter("preparationTime")));

        Part filePart = request.getPart("image");
        if (filePart != null && filePart.getSize() > 0) {
            String imageUrl = handleImageUpload(filePart);
            menuItem.setImageUrl(imageUrl);
        }

        MenuItemFileHandler.saveMenuItem(menuItem);
        response.sendRedirect(request.getContextPath() + "/menu/?restaurantId=" + menuItem.getRestaurantId());
    }

    private void updateMenuItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MenuItem menuItem = MenuItemFileHandler.getMenuItemById(request.getParameter("id"));

        if (menuItem != null) {
            menuItem.setName(request.getParameter("name"));
            menuItem.setCategory(request.getParameter("category"));
            menuItem.setDescription(request.getParameter("description"));
            menuItem.setPrice(Double.parseDouble(request.getParameter("price")));
            menuItem.setVegetarian(request.getParameter("vegetarian") != null);
            menuItem.setSpicy(request.getParameter("spicy") != null);
            menuItem.setPreparationTime(Integer.parseInt(request.getParameter("preparationTime")));

            Part filePart = request.getPart("image");
            if (filePart != null && filePart.getSize() > 0) {
                String imageUrl = handleImageUpload(filePart);
                menuItem.setImageUrl(imageUrl);
            }

            MenuItemFileHandler.updateMenuItem(menuItem);
            response.sendRedirect(request.getContextPath() + "/menu/?restaurantId=" + menuItem.getRestaurantId());
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void deleteMenuItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String itemId = request.getParameter("id");
        MenuItem menuItem = MenuItemFileHandler.getMenuItemById(itemId);

        if (menuItem != null) {
            MenuItemFileHandler.deleteMenuItem(itemId);
            response.sendRedirect(request.getContextPath() + "/menu/?restaurantId=" + menuItem.getRestaurantId());
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private String handleImageUpload(Part filePart) throws IOException {
        String fileName = "menu-item-" + System.currentTimeMillis() +
                filePart.getSubmittedFileName().substring(
                        filePart.getSubmittedFileName().lastIndexOf(".")
                );

        String uploadPath = FileHandler.getResourcePath("images/menu-items/" + fileName);
        filePart.write(uploadPath);

        return "/images/menu-items/" + fileName;
    }
}