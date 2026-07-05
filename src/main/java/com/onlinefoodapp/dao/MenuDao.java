package com.onlinefoodapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.onlinefoodapp.model.Menu;
import com.onlinefoodapp.util.DBConnection;

public class MenuDao {

    private Connection connection;

    public MenuDao() {
        connection = DBConnection.getConnection();
    }

    public ArrayList<Menu> getMenuByRestaurantId(int restaurantId) {

        ArrayList<Menu> menuList = new ArrayList<>();

        String sql = "SELECT * FROM menu WHERE restaurant_id = ?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, restaurantId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Menu menu = new Menu();

                menu.setMenuId(rs.getInt("menu_id"));
                menu.setRestaurantId(rs.getInt("restaurant_id"));
                menu.setItemName(rs.getString("item_name"));
                menu.setDescription(rs.getString("description"));
                menu.setPrice(rs.getDouble("price"));
                menu.setImage(rs.getString("image_url"));

                menuList.add(menu);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return menuList;
    }
}