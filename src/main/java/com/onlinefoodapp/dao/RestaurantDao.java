package com.onlinefoodapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.onlinefoodapp.model.Restaurant;
import com.onlinefoodapp.util.DBConnection;
public class RestaurantDao {

    private Connection connection;

    public RestaurantDao() {
        connection = DBConnection.getConnection();
    }
    public ArrayList<Restaurant> getAllRestaurants() {

        ArrayList<Restaurant> restaurantList = new ArrayList<>();

        String sql = "SELECT * FROM restaurants";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            System.out.println("Query executed successfully");

            while (rs.next()) {

                System.out.println("Restaurant Found: " + rs.getString("restaurant_name"));

                Restaurant restaurant = new Restaurant();

                restaurant.setRestaurantId(rs.getInt("restaurant_id"));
                restaurant.setRestaurantName(rs.getString("restaurant_name"));
                restaurant.setCuisineType(rs.getString("cuisine_type"));
                restaurant.setRating(rs.getDouble("rating"));

                // Temporary value because delivery_time doesn't exist
                restaurant.setDeliveryTime(30);

                restaurant.setAddress(rs.getString("address"));

                // Your database column is image_url
                restaurant.setImage(rs.getString("image_url"));

                restaurantList.add(restaurant);
            }

            System.out.println("Total Restaurants = " + restaurantList.size());

        } catch (SQLException e) {
            System.out.println("SQL Error:");
            e.printStackTrace();
        }

        return restaurantList;
    }
}
