package com.emarket.BeeMarket.model;


public class AppUser {
    private String email = "";
    private String userName = "";
    private String googleId = "";
    private RoleEnum roleEnumName;

    public RoleEnum getRoleEnumName() {
        return roleEnumName;
    }

    public void setRoleEnumName(RoleEnum roleEnumName) {
        this.roleEnumName = roleEnumName;
    }


    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}

