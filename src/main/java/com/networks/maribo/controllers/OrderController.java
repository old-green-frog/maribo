package com.networks.maribo.controllers;


import com.networks.maribo.service.MariboOrder;
import com.networks.maribo.service.Product;
import com.networks.maribo.service.mappers.OrderMapper;
import com.networks.maribo.service.mappers.ProductMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "${urlprefix}/orders")
public class OrderController {

    @GetMapping(path = "")
    public List<MariboOrder> list(
            @RequestParam(value = "all", defaultValue = "false") boolean all,
            @RequestParam(value = "id", defaultValue = "0") int id
    ) {

        List<MariboOrder> orders = null;

        if (all) {
            orders = MariboOrder.getAll(MariboOrder.class, new OrderMapper());
        } else if (id != 0) {
            orders = MariboOrder.getOne(MariboOrder.class, new OrderMapper(), id);
        }

        if (orders == null || orders.size() == 0) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "orders not found!"
            );
        }

        return orders;
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(@RequestBody MariboOrder order)
    {
        order.save();

        for (Integer product_id : order.products) {
            Product prd = Product.getOne(Product.class, new ProductMapper(), product_id).get(0);
            prd.updateField("order_id", order.getId());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Ok");
    }

    @DeleteMapping(path = "")
    public ResponseEntity<String> delete(@RequestParam(value = "id", defaultValue = "0") int id) {
        MariboOrder ord = MariboOrder.getOne(MariboOrder.class, new OrderMapper(), id).get(0);
        ord.delete_from_db();

        return ResponseEntity.status(HttpStatus.OK).body("Ok");
    }
}
