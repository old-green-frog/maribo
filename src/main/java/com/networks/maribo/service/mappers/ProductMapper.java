package com.networks.maribo.service.mappers;

import com.networks.maribo.service.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product usr = new Product(
                rs.getInt("id")
        );
        usr.setName(rs.getString("name"));
        usr.setPrice(rs.getDouble("price"));
        usr.setOrder_id(rs.getInt("order_id"));

        return usr;
    }
}
