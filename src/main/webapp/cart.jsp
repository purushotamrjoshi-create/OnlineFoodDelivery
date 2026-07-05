<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="com.onlinefoodapp.model.Cart"%>

<%
ArrayList<Cart> cartList =
(ArrayList<Cart>) request.getAttribute("cartList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Cart</title>
</head>

<body>

<h2>My Cart</h2>

<%
double grandTotal = 0;

if(cartList != null && !cartList.isEmpty()){

for(Cart cart : cartList){

grandTotal += cart.getTotalPrice();
%>

<div style="border:1px solid gray;
padding:15px;
margin:15px;
width:350px;">

<img src="<%=request.getContextPath()%>/images/<%=cart.getImage()%>"
width="180"
height="130">

<h3><%=cart.getItemName()%></h3>

<p>Price : ₹ <%=cart.getPrice()%></p>

<p>

<a href="updateCart?action=decrease&cartId=<%=cart.getCartId()%>">➖</a>

<b> <%=cart.getQuantity()%> </b>

<a href="updateCart?action=increase&cartId=<%=cart.getCartId()%>">➕</a>

</p>

<a href="updateCart?action=remove&cartId=<%=cart.getCartId()%>">
Remove Item
</a>

<p>Total : ₹ <%=cart.getTotalPrice()%></p>
<form action="checkout" method="get">
    <input type="submit" value="Proceed to Checkout">
</form>

</div>

<%
}
%>

<h2>Grand Total : ₹ <%=grandTotal%></h2>

<%

}else{

%>

<h3>Your Cart is Empty</h3>

<%

}
%>

</body>
</html>