package com.fooddelivery.controller;

import com.fooddelivery.model.Payment;
import com.fooddelivery.util.PaymentFileHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(name = "PaymentServlet", value = {
        "/payments",
        "/payments/process",
        "/payments/history",
        "/payments/details"
})
public class PaymentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/payments":
                showPaymentMethods(request, response);
                break;
            case "/payments/history":
                showPaymentHistory(request, response);
                break;
            case "/payments/details":
                showPaymentDetails(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if (action.equals("/payments/process")) {
            processPayment(request, response);
        }
    }

    private void showPaymentMethods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        double amount = Double.parseDouble(request.getParameter("amount"));

        request.setAttribute("orderId", orderId);
        request.setAttribute("amount", amount);
        request.getRequestDispatcher("/views/payment/methods.jsp").forward(request, response);
    }

    private void processPayment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String paymentId = "PAY" + System.currentTimeMillis();
        String orderId = request.getParameter("orderId");
        String userId = (String) request.getSession().getAttribute("userId");
        String paymentMethod = request.getParameter("paymentMethod");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String transactionId = "TXN" + System.currentTimeMillis();

        Payment payment = new Payment(
                paymentId, orderId, userId, paymentMethod,
                amount, "Completed", LocalDateTime.now(), transactionId
        );

        PaymentFileHandler.savePayment(payment);
        response.sendRedirect(request.getContextPath() + "/payments/details?paymentId=" + paymentId);
    }

    private void showPaymentHistory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = (String) request.getSession().getAttribute("userId");
        List<Payment> payments = PaymentFileHandler.getPaymentsByUser(userId);

        request.setAttribute("payments", payments);
        request.getRequestDispatcher("/views/payment/history.jsp").forward(request, response);
    }

    private void showPaymentDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paymentId = request.getParameter("paymentId");
        Payment payment = PaymentFileHandler.getPaymentById(paymentId);

        request.setAttribute("payment", payment);
        request.getRequestDispatcher("/views/payment/details.jsp").forward(request, response);
    }
}