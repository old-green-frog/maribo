package com.networks.maribo.service.mappers;

import com.networks.maribo.service.MariboOrder;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements RowMapper<MariboOrder> {
    public MariboOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
        MariboOrder usr = new MariboOrder(
                rs.getInt("id")
        );
        usr.setUser_id(rs.getInt("user_id"));
        usr.setStatus_id(rs.getInt("status_id"));

        return usr;
    }
}
