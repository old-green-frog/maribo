package com.networks.maribo.service.mappers;

import com.networks.maribo.service.Status;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StatusMapper implements RowMapper<Status> {
    public Status mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Status.fromString(rs.getString("name"));
        }
}
