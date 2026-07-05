package com.onlinefoodapp.servlet;

import java.io.IOException;
import java.util.ArrayList;

import com.onlinefoodapp.dao.CartDao;
import com.onlinefoodapp.dao.OrderDao;
import com.onlinefoodapp.model.Cart;
import com.onlinefoodapp.model.Order;
import com.onlinefoodapp.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/placeOrder")
public class PlaceOrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        CartDao cartDao = new CartDao();
        ArrayList<Cart> cartList = cartDao.getCartItems(user.getUserId());

        double totalAmount = 0;

        for (Cart cart : cartList) {
            totalAmount += cart.getTotalPrice();
        }

        Order order = new Order();

        order.setUserId(user.getUserId());
        order.setTotalAmount(totalAmount);
        order.setDeliveryAddress(request.getParameter("deliveryAddress"));
        order.setPaymentMethod(request.getParameter("paymentMethod"));
        order.setOrderStatus("Placed");

        OrderDao orderDao = new OrderDao();

        int orderId = orderDao.placeOrder(order);

        if (orderId > 0) {

            orderDao.saveOrderItems(orderId, cartList);

            cartDao.clearCart(user.getUserId());

            response.sendRedirect("order_success.jsp");

        } else {

            response.getWriter().println("Order Failed!");

        }
    }
}