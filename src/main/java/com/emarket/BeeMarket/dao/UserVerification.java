package com.emarket.BeeMarket.dao;

import com.emarket.BeeMarket.model.RoleEnum;
import com.emarket.BeeMarket.model.AppUser;
import com.emarket.BeeMarket.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserVerification {
    private static final String GET_QUERY = "SELECT * FROM USERS WHERE EMAILID=?";

    public static AppUser verifyUser(String userId) throws SQLException {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement prepareGet = connection.prepareStatement(GET_QUERY)) {
            prepareGet.setString(1, userId);
            try (ResultSet userResult = prepareGet.executeQuery()) {
                if (userResult.next()) {
                    AppUser appUser = new AppUser();
                    appUser.setUserName(userResult.getString("USER_NAME"));
                    appUser.setEmail(userResult.getString("EMAIL"));
                    appUser.setGoogleId(userResult.getString("EMAILID"));
                    int role = userResult.getInt("USER_ROLE");
                    appUser.setRoleEnumName(RoleEnum.getByRole(role));
                    return appUser;
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw exception;
        }
        return null;
    }
}
