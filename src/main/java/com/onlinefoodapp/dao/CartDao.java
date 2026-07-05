package com.onlinefoodapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.onlinefoodapp.model.Cart;
import com.onlinefoodapp.util.DBConnection;

public class CartDao {

    private Connection connection;

    public CartDao() {
        connection = DBConnection.getConnection();
    }

    // Add item to cart
    public boolean addToCart(Cart cart) {

        String sql = "INSERT INTO cart(user_id, menu_id, quantity) VALUES (?, ?, ?)";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, cart.getUserId());
            ps.setInt(2, cart.getMenuId());
            ps.setInt(3, cart.getQuantity());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Get cart items of a user
    public ArrayList<Cart> getCartItems(int userId) {

        ArrayList<Cart> cartList = new ArrayList<>();

        String sql =
            "SELECT c.cart_id, c.user_id, c.menu_id, c.quantity, " +
            "m.item_name, m.price, m.image_url " +
            "FROM cart c JOIN menu m ON c.menu_id = m.menu_id " +
            "WHERE c.user_id = ?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Cart cart = new Cart();

                cart.setCartId(rs.getInt("cart_id"));
                cart.setUserId(rs.getInt("user_id"));
                cart.setMenuId(rs.getInt("menu_id"));
                cart.setItemName(rs.getString("item_name"));
                cart.setPrice(rs.getDouble("price"));
                cart.setQuantity(rs.getInt("quantity"));
                cart.setImage(rs.getString("image_url"));

                cart.setTotalPrice(
                        cart.getPrice() * cart.getQuantity());

                cartList.add(cart);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cartList;
    }
    public void increaseQuantity(int cartId) {

        String sql = "UPDATE cart SET quantity = quantity + 1 WHERE cart_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, cartId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void decreaseQuantity(int cartId) {

        String sql = "UPDATE cart SET quantity = quantity - 1 WHERE cart_id = ? AND quantity > 1";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, cartId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void removeItem(int cartId) {

        String sql = "DELETE FROM cart WHERE cart_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, cartId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void clearCart(int userId) {

        String sql = "DELETE FROM cart WHERE user_id=?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, userId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}