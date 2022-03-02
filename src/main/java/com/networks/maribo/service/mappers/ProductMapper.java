package com.networks.maribo.service.mappers;

import com.networks.maribo.service.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product>  {
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Product(
                rs.getInt("product_id"),
                rs.getInt("category_id"),
                rs.getInt("characteristic_id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getInt("width"),
                rs.getInt("height"),
                rs.getInt("length"),
                rs.getInt("weight")
        );
    }
}
