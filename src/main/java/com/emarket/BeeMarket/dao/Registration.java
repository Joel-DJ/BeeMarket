package com.emarket.BeeMarket.dao;

import com.emarket.BeeMarket.model.AppUser;
import com.emarket.BeeMarket.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Registration {
    private static final String INSERT_USER = "INSERT INTO USERS(EMAILID,EMAIL,USER_NAME,USER_ROLE) VALUES(?,?,?,?)";

    public static int registerUser(AppUser appUser) throws SQLException {
        int rows;
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement prepareInsert = connection.prepareStatement(INSERT_USER)) {
            prepareInsert.setString(1, appUser.getGoogleId());
            prepareInsert.setString(2, appUser.getEmail());
            prepareInsert.setString(3, appUser.getUserName());
            prepareInsert.setInt(4, appUser.getRoleEnumName().getRole());
            rows = prepareInsert.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            throw throwable;
        }
        return rows;
    }
}
