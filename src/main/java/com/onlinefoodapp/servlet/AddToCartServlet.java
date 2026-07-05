package com.onlinefoodapp.servlet;

import java.io.IOException;

import com.onlinefoodapp.dao.CartDao;
import com.onlinefoodapp.model.Cart;
import com.onlinefoodapp.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/addToCart")
public class AddToCartServlet extends HttpServlet {

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

        int menuId = Integer.parseInt(request.getParameter("menuId"));

        Cart cart = new Cart();
        cart.setUserId(user.getUserId());
        cart.setMenuId(menuId);
        cart.setQuantity(1);

        CartDao cartDao = new CartDao();

        boolean status = cartDao.addToCart(cart);

        if (status) {
            response.sendRedirect("cart");
        } else {
            response.getWriter().println("Failed to add item to cart.");
        }
    }
}