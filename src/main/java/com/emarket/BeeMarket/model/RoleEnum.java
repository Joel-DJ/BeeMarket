package com.emarket.BeeMarket.model;

public enum RoleEnum {
    SELLER(1, "Seller"),
    BUYER(2, "Buyer");

    private final int role;
    private final String roleName;

    RoleEnum(int role, String roleName) {
        this.role = role;
        this.roleName = roleName;
    }

    public final int getRole() {
        return role;
    }

    public final String getRoleName() {
        return roleName;
    }

    public static RoleEnum getByRole(int role) {
        for (RoleEnum roleEnum : values()) {
            if (role == roleEnum.getRole()) {
                return roleEnum;
            }
        }
        return BUYER;
    }


}
