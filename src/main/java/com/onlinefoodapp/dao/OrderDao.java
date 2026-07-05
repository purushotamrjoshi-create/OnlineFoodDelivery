package com.onlinefoodapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.onlinefoodapp.model.Cart;
import com.onlinefoodapp.model.Order;
import com.onlinefoodapp.util.DBConnection;

public class OrderDao {

    private Connection connection;

    public OrderDao() {
        connection = DBConnection.getConnection();
    }

    // Place Order
    public int placeOrder(Order order) {

        int orderId = 0;

        String sql = "INSERT INTO orders(user_id,total_amount,delivery_address,payment_method,order_status,order_date) VALUES(?,?,?,?,?,NOW())";

        try {

            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, order.getUserId());
            ps.setDouble(2, order.getTotalAmount());
            ps.setString(3, order.getDeliveryAddress());
            ps.setString(4, order.getPaymentMethod());
            ps.setString(5, order.getOrderStatus());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                orderId = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderId;
    }

    // Save Order Items
    public void saveOrderItems(int orderId, ArrayList<Cart> cartList) {

        String sql = "INSERT INTO order_items(order_id,menu_id,quantity,price) VALUES(?,?,?,?)";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            for (Cart cart : cartList) {

                ps.setInt(1, orderId);
                ps.setInt(2, cart.getMenuId());
                ps.setInt(3, cart.getQuantity());
                ps.setDouble(4, cart.getPrice());

                ps.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}