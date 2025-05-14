package com.fooddelivery.controller;

import com.fooddelivery.model.Admin;
import com.fooddelivery.util.AdminFileHandler;
import com.fooddelivery.util.FileHandler;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminServlet", value = {
        "/admin/dashboard",
        "/admin/manage",
        "/admin/add",
        "/admin/edit",
        "/admin/delete",
        "/admin/reports"
})
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/admin/dashboard":
                showDashboard(request, response);
                break;
            case "/admin/manage":
                showAdminManagement(request, response);
                break;
            case "/admin/add":
                showAddForm(request, response);
                break;
            case "/admin/edit":
                showEditForm(request, response);
                break;
            case "/admin/reports":
                showReports(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if (!action.equals("/admin/add")) {
            if (action.equals("/admin/edit")) {
                updateAdmin(request, response);
            } else if (action.equals("/admin/delete")) {
                deleteAdmin(request, response);
            }
        } else {
            addAdmin(request, response);
        }
    }

    private void showDashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // In a real app, you would fetch stats from other components
        request.setAttribute("totalUsers", 125);
        request.setAttribute("totalOrders", 89);
        request.setAttribute("totalRestaurants", 23);
        request.setAttribute("revenue", 4520.50);

        request.getRequestDispatcher("/views/admin/dashboard.jsp").forward(request, response);
    }

    private void showAdminManagement(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Admin> admins = AdminFileHandler.getAllAdmins();
        request.setAttribute("admins", admins);
        request.getRequestDispatcher("/views/admin/manage.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/admin/add.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adminId = request.getParameter("id");
        Admin admin = AdminFileHandler.getAdminById(adminId);
        request.setAttribute("admin", admin);
        request.getRequestDispatcher("/views/admin/edit.jsp").forward(request, response);
    }

    private void showReports(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/admin/reports.jsp").forward(request, response);
    }

    private void addAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = FileHandler.getNextId("admins.txt", "ADM");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String adminLevel = request.getParameter("adminLevel");
        String department = request.getParameter("department");

        Admin admin = new Admin(id, username, password, email, adminLevel, department);
        AdminFileHandler.saveAdmin(admin);
        response.sendRedirect(request.getContextPath() + "/admin/manage?success=added");
    }

    private void updateAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String adminLevel = request.getParameter("adminLevel");
        String department = request.getParameter("department");

        Admin admin = new Admin(id, username, password, email, adminLevel, department);
        AdminFileHandler.updateAdmin(admin);
        response.sendRedirect(request.getContextPath() + "/admin/manage?success=updated");
    }

    private void deleteAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String adminId = request.getParameter("id");
        AdminFileHandler.deleteAdmin(adminId);
        response.sendRedirect(request.getContextPath() + "/admin/manage?success=deleted");
    }
}