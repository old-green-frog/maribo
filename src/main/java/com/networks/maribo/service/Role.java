package com.networks.maribo.service;

import com.networks.maribo.LocalContext;
import com.networks.maribo.service.mappers.RoleMapper;

public enum Role {
    USER("User"), ADMIN("Admin");

    private String role;
    Role(String role) {}
    public static Role fromString(String role) {
        Role rl = null;
        if (role.equals("User")) rl = Role.USER;
        if (role.equals("Admin")) rl = Role.ADMIN;
        if (rl != null) {
            rl.role = role;
            return rl;
        }
        return null;
    }

    public String getRole() {
        return this.role;
    }

    public static Role fromId(int id) {
        return LocalContext.runner.query(String.format("SELECT * FROM Role WHERE id='%s'", id), new RoleMapper()).get(0);
    }
}
