package com.networks.maribo.controllers;

import com.networks.maribo.LocalContext;
import com.networks.maribo.service.Product;
import com.networks.maribo.service.Category;
import com.networks.maribo.service.mappers.ProductMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "${test-urlprefix}/products")
public class ProductController {

    private final String queryForAll = "SELECT * FROM product INNER JOIN characteristic ON product.product_id = characteristic.characteristic_id";
    private final String queryForOne = "SELECT * FROM product INNER JOIN characteristic ON product.product_id = characteristic.characteristic_id WHERE product_id=%s";
    private final String createCategoryQuery = "INSERT INTO category (name) VALUES ('%s')";
    private final String createProductQuery = "INSERT INTO characteristic (characteristic_id, width, height, length, weight) " +
            "VALUES('%s', '%s', '%s', '%s', '%s');" +
            "INSERT INTO product (category_id, characteristic_id, name, price) " +
            "VALUES('%s', '%s', '%s', '%s');";

    @GetMapping(path = "")
    public List<Product> list(
            @RequestParam(value = "all", defaultValue = "false") boolean all,
            @RequestParam(value = "id", defaultValue = "0") int id
    )
    {
        List<Product> products = null;

        if (all) {
            products = LocalContext.runner.query(queryForAll, new ProductMapper());
        } else if (id != 0) {
            products = LocalContext.runner.query(String.format(queryForOne, id), new ProductMapper());
        }

        if (products == null || products.size() == 0) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "products not found!"
            );
        }

        return products;
    }
    @PostMapping(path = "addCategory", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addCategory(@RequestBody Category category)
    {
        LocalContext.runner.update(
                String.format(createCategoryQuery, category.getName())
        );
        return ResponseEntity.status(HttpStatus.CREATED).body("Category " + category.getName() + " is created.");
    }

    @PostMapping(path = "addProduct", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addProduct(@RequestBody Product product)
    {
        LocalContext.runner.update(
                String.format(createProductQuery, product.getCharacteristic_id(), product.getWidth(), product.getHeight(), product.getLength(), product.getWeight(),
                        product.getCategory_id(), product.getCharacteristic_id(), product.getName(), product.getPrice())
        );
        return ResponseEntity.status(HttpStatus.CREATED).body("Product with id " + product.getCharacteristic_id() + " is added.");
    }
}
