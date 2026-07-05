package com.onlinefoodapp.servlet;

import java.io.IOException;

import com.onlinefoodapp.dao.CartDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/updateCart")
public class UpdateCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int cartId = Integer.parseInt(request.getParameter("cartId"));
        String action = request.getParameter("action");

        CartDao dao = new CartDao();

        if ("increase".equals(action)) {
            dao.increaseQuantity(cartId);
        } else if ("decrease".equals(action)) {
            dao.decreaseQuantity(cartId);
        } else if ("remove".equals(action)) {
            dao.removeItem(cartId);
        }

        response.sendRedirect("cart");
    }
}
