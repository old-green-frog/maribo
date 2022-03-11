package com.networks.maribo.controllers;


import com.networks.maribo.service.Product;
import com.networks.maribo.service.mappers.ProductMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "${urlprefix}/products")
public class ProductController {

    @GetMapping(path = "")
    public List<Product> list(
            @RequestParam(value = "all", defaultValue = "false") boolean all,
            @RequestParam(value = "id", defaultValue = "0") int id
    ) {

        List<Product> products = null;

        if (all) {
            products = Product.getAll(Product.class, new ProductMapper());
        } else if (id != 0) {
            products = Product.getOne(Product.class, new ProductMapper(), id);
        }

        if (products == null || products.size() == 0) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "products not found!"
            );
        }

        return products;
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(@RequestBody Product product)
    {
        product.save();
        return ResponseEntity.status(HttpStatus.CREATED).body("Ok");
    }
}
