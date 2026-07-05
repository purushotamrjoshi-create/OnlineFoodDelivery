package com.onlinefoodapp.servlet;

import java.io.IOException;
import java.util.ArrayList;

import com.onlinefoodapp.dao.MenuDao;
import com.onlinefoodapp.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));

        MenuDao menuDao = new MenuDao();

        ArrayList<Menu> menuList =
                menuDao.getMenuByRestaurantId(restaurantId);

        request.setAttribute("menuList", menuList);

        request.getRequestDispatcher("menu.jsp").forward(request, response);
    }
}