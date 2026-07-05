<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="com.onlinefoodapp.model.Cart"%>

<%
ArrayList<Cart> cartList =
(ArrayList<Cart>) request.getAttribute("cartList");

Double grandTotal =
(Double) request.getAttribute("grandTotal");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Checkout</title>
</head>

<body>

<h2>Checkout</h2>

<table border="1" cellpadding="10">

<tr>
<th>Item</th>
<th>Qty</th>
<th>Price</th>
<th>Total</th>
</tr>

<%
for(Cart cart : cartList){
%>

<tr>

<td><%=cart.getItemName()%></td>

<td><%=cart.getQuantity()%></td>

<td>₹ <%=cart.getPrice()%></td>

<td>₹ <%=cart.getTotalPrice()%></td>

</tr>

<%
}
%>

</table>

<h2>Grand Total : ₹ <%=grandTotal%></h2>

<form action="placeOrder" method="post">

<label>Delivery Address</label><br>

<textarea
name="deliveryAddress"
rows="4"
cols="40"
required></textarea>

<br><br>

<label>Payment Method</label>

<select name="paymentMethod">

<option>Cash on Delivery</option>

<option>UPI</option>

<option>Credit Card</option>

</select>

<br><br>

<input type="submit"
value="Place Order">

</form>

</body>
</html>