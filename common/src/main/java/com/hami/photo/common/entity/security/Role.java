package com.hami.photo.common.entity.security;

public enum Role {
    NONE(0, "Empty role"),
    ROLE_ADMIN(1, "Role admin full access"),
    ROLE_USER(2, "Role user edit"),
    ROLE_CUSTOMER(3, "Role customer buy and show");

    private int roleId;
    private String description;

    Role(int roleId, String description) {
        this.roleId = roleId;
        this.description = description;
    }
    public int getRoleId() {
        return roleId;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", description='" + description + '\'' +
                '}';
    }
}
