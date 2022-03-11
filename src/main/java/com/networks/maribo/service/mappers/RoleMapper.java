package com.networks.maribo.service.mappers;

import com.networks.maribo.service.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper implements RowMapper<Role> {
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Role.fromString(rs.getString("name"));
    }
}
