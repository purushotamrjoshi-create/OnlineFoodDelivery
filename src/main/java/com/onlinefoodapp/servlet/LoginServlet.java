package com.onlinefoodapp.servlet;

import java.io.IOException;

import com.onlinefoodapp.dao.UserDao;
import com.onlinefoodapp.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
    	

        
    	String email = request.getParameter("email");
        String password = request.getParameter("password");
        

        UserDao userDao = new UserDao();

        User user = userDao.loginUser(email, password);
        System.out.println("User Object: " + user);

        if (user != null) {

            HttpSession session = request.getSession();
            session.setAttribute("loggedInUser", user);

            response.sendRedirect("restaurants");

        } else {

            response.sendRedirect("login.jsp");
        }
    }
}