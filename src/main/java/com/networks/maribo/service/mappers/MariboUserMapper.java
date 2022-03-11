package com.networks.maribo.service.mappers;

import com.networks.maribo.service.MariboUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MariboUserMapper implements RowMapper<MariboUser> {
    public MariboUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        MariboUser usr = new MariboUser(
                rs.getInt("id")
        );
        usr.setName(rs.getString("name"));
        usr.setEmail(rs.getString("email"));
        usr.setRole_id(rs.getInt("role_id"));
        usr.setBirthday(rs.getDate("birthday"));
        usr.setPassword(rs.getString("password"));

        return usr;
    }
}