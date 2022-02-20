package com.networks.maribo.service.mappers;

import com.networks.maribo.service.TestUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestUserMapper implements RowMapper<TestUser> {
    public TestUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TestUser(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("email")
        );
    }
}
