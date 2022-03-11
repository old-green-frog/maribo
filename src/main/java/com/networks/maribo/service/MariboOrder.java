package com.networks.maribo.service;

import com.networks.maribo.LocalContext;
import com.networks.maribo.service.mappers.MariboUserMapper;
import com.networks.maribo.service.mappers.ProductMapper;

import java.util.List;

public class MariboOrder extends Model {

    private int id, user_id, status_id;

    public static String allQuery = "SELECT * FROM MariboOrder";
    public List<Integer> products;

    public MariboOrder() {}
    public MariboOrder(int id) {
        this.id = id;

        this.oneQuery = String.format("SELECT * FROM MariboOrder WHERE id='%s'", this.id);
    }

    public void setId(int id) {
        this.id = id;
        this.updateField("id", id);
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
        this.updateField("status_id", status_id);
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
        this.updateField("user_id", user_id);
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return Status.fromId(this.status_id).getStat();
    }

    public String getUsername() {
        return MariboUser.getOne(MariboUser.class, new MariboUserMapper(), this.user_id).get(0).getName();
    }

    public int getUser_id() {
        return user_id;
    }

    public void setProducts(List<Integer> product_ids) {
//        for (Integer product_id : product_ids) {
//            Product prd = Product.getOne(Product.class, new ProductMapper(), product_id).get(0);
//            prd.updateField("order_id", this.id);
//        }

        this.products = product_ids;
    }

    public List<Product> getProducts() {
        return LocalContext.runner.query(
                String.format("SELECT * FROM Product WHERE order_id=%s", this.id), new ProductMapper()
        );
    }
}
