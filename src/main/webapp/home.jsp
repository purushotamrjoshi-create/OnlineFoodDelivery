<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.onlinefoodapp.model.Restaurant" %>
<%@ page import="com.onlinefoodapp.model.User" %>

<%
User user = (User) session.getAttribute("loggedInUser");

if (user == null) {
    response.sendRedirect("login.jsp");
    return;
}

ArrayList<Restaurant> restaurantList =
(ArrayList<Restaurant>) request.getAttribute("restaurantList");
%>
<%
System.out.println("restaurantList in JSP = " + restaurantList);
if (restaurantList != null) {
    System.out.println("Size = " + restaurantList.size());
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>

<body>

<h2>Welcome, <%= user.getName() %> 🎉</h2>

<h3>Available Restaurants</h3>

<%
if (restaurantList != null) {

    for (Restaurant restaurant : restaurantList) {
%>

<div style="border:1px solid gray; padding:15px; margin:15px; width:300px;">
<img src="<%= request.getContextPath() %>/images/<%= restaurant.getImage() %>"
     alt="<%= restaurant.getRestaurantName() %>"
     width="250"
     height="180"
     style="border:2px solid red;">
    <h3><%= restaurant.getRestaurantName() %></h3>

    <p><b>Cuisine:</b> <%= restaurant.getCuisineType() %></p>

    <p><b>Rating:</b> ⭐ <%= restaurant.getRating() %></p>

    <p><b>Delivery Time:</b> <%= restaurant.getDeliveryTime() %> mins</p>

    <p><b>Address:</b> <%= restaurant.getAddress() %></p>
    <form action="menu" method="get">

    <input type="hidden"
           name="restaurantId"
           value="<%= restaurant.getRestaurantId() %>">

    <input type="submit" value="View Menu">

</form>

</div>

<%
    }
} else {
%>

<p>No restaurants found.</p>

<%
}
%>

</body>
</html>