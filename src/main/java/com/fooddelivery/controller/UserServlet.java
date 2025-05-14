package com.fooddelivery.controller;

import com.fooddelivery.model.User;
import com.fooddelivery.util.FileHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet("/user/*")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1MB
        maxFileSize = 1024 * 1024 * 5,   // 5MB
        maxRequestSize = 1024 * 1024 * 10 // 10MB
)
public class UserServlet extends HttpServlet {
    private static final String USERS_FILE = "users.txt";

    @Override
    public void init() throws ServletException {
        super.init();
        // Initialize default avatar on startup
        FileHandler.initializeDefaultAvatar();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getPathInfo();
        if (action == null) {
            action = "/";
        }

        switch (action) {
            case "/register":
                showRegisterForm();
                break;
            case "/login":
                showLoginForm();
                break;
            case "/profile":
                showProfile();
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/logout":
                logout();
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/");
                break;
        }
    }

    private void logout() {

    }

    private void showProfile() {

    }

    private void showLoginForm() {

    }

    private void showRegisterForm() {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getPathInfo();
        if (action == null) {
            action = "/";
        }

        switch (action) {
            case "/register":
                registerUser();
                break;
            case "/login":
                loginUser();
                break;
            case "/update":
                updateUser();
                break;
            case "/upload-avatar":
                uploadAvatar(request, response);
                break;
            case "/delete":
                deleteUser();
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/");
                break;
        }
    }

    private void deleteUser() {

    }

    private void updateUser() {

    }

    private void loginUser() {

    }

    private void registerUser() {

    }

    // ... (keep existing helper methods)

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/user/login");
            return;
        }

        request.getRequestDispatcher("/views/editProfile.jsp").forward(request, response);
    }

    private void uploadAvatar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/user/login");
            return;
        }

        Part filePart = request.getPart("avatar");
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = UUID.randomUUID() +
                    filePart.getSubmittedFileName().substring(
                            filePart.getSubmittedFileName().lastIndexOf(".")
                    );

            String uploadPath = FileHandler.getResourcePath("images/avatars/" + fileName);

            // Save the file
            filePart.write(uploadPath);

            // Update user's avatar URL
            user.setAvatarUrl("/images/avatars/" + fileName);

            // Update in file
            List<String> lines = FileHandler.readAllLines(FileHandler.getFilePath(USERS_FILE));
            List<String> updatedLines = new ArrayList<>();

            for (String line : lines) {
                User u = User.fromFileString(line);
                if (u != null && u.getUserId().equals(user.getUserId())) {
                    updatedLines.add(user.toFileString());
                } else {
                    updatedLines.add(line);
                }
            }

            FileHandler.writeAllLines(FileHandler.getFilePath(USERS_FILE), updatedLines);

            // Update session
            session.setAttribute("user", user);
            request.setAttribute("successMessage", "Avatar updated successfully!");
        } else {
            request.setAttribute("errorMessage", "Please select an image to upload!");
        }

        request.getRequestDispatcher("/views/editProfile.jsp").forward(request, response);
    }

    // ... (update other methods to handle new User fields)
}