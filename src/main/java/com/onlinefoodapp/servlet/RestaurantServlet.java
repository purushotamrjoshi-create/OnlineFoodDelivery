package com.onlinefoodapp.servlet;

import java.io.IOException;
import java.util.ArrayList;

import com.onlinefoodapp.dao.RestaurantDao;
import com.onlinefoodapp.model.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/restaurants")
public class RestaurantServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        RestaurantDao restaurantDao = new RestaurantDao();

        ArrayList<Restaurant> restaurantList = restaurantDao.getAllRestaurants();

        System.out.println("Restaurants fetched: " + restaurantList.size());

        request.setAttribute("restaurantList", restaurantList);

        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}