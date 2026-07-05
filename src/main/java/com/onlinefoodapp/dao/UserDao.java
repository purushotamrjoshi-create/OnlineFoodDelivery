package com.onlinefoodapp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.onlinefoodapp.model.User;
import com.onlinefoodapp.util.DBConnection;

public class UserDao {

    private Connection connection;

    public UserDao() {
        connection = DBConnection.getConnection();
    }

 public boolean registerUser(User user) {

        String sql = "INSERT INTO users(full_name, email, password, phone, address) VALUES (?, ?, ?, ?, ?)";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getPhone());
            ps.setString(5, user.getAddress());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
 public User loginUser(String email, String password) {

	    String sql = "SELECT * FROM users WHERE email = ? AND password = ?";

	    try {

	        PreparedStatement ps = connection.prepareStatement(sql);

	        ps.setString(1, email);
	        ps.setString(2, password);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {

	            User user = new User();

	            user.setUserId(rs.getInt("user_id"));
	            user.setName(rs.getString("full_name"));
	            user.setEmail(rs.getString("email"));
	            user.setPassword(rs.getString("password"));
	            user.setPhone(rs.getString("phone"));
	            user.setAddress(rs.getString("address"));

	            return user;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return null;
	}

}