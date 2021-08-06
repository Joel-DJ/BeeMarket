package com.emarket.BeeMarket.dao;

import com.emarket.BeeMarket.model.AppUser;
import com.emarket.BeeMarket.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoleUpdate {
    private static final String UPDATE_QUERY = "UPDATE USERS SET USER_ROLE=? WHERE EMAILID=?";

    public static int updateRole(AppUser appUser) throws SQLException {
        int rows;
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement prepareUpdate = connection.prepareStatement(UPDATE_QUERY)) {
            prepareUpdate.setInt(1, appUser.getRoleEnumName().getRole());
            prepareUpdate.setString(2, appUser.getGoogleId());
            rows = prepareUpdate.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw exception;
        }
        return rows;
    }
}
