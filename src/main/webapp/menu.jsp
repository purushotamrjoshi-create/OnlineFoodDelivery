<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="com.onlinefoodapp.model.Menu"%>

<%
ArrayList<Menu> menuList =
        (ArrayList<Menu>) request.getAttribute("menuList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Restaurant Menu</title>
</head>

<body>

<h2>Restaurant Menu</h2>

<%
if(menuList != null && !menuList.isEmpty()){

    for(Menu menu : menuList){
%>

<div style="border:1px solid gray;
            width:300px;
            padding:15px;
            margin:15px;">

    <img src="<%=request.getContextPath()%>/images/<%=menu.getImage()%>"
         width="250"
         height="180">

    <h3><%=menu.getItemName()%></h3>

    <p><%=menu.getDescription()%></p>

    <h4>₹ <%=menu.getPrice()%></h4>

    <form action="addToCart" method="post">

    <input type="hidden"
           name="menuId"
           value="<%= menu.getMenuId() %>">

    <input type="submit"
           value="Add to Cart">

</form>

</div>

<%
    }
}
else{
%>

<h3>No Menu Items Available</h3>

<%
}
%>

</body>
</html>