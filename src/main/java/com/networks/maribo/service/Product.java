package com.networks.maribo.service;

public class Product extends Model {

    private int id;
    private Integer order_id;
    private String name;
    private double price;

    public static String allQuery = "SELECT * FROM Product";

    public Product() {}
    public Product(int id) {
        this.id = id;

        this.oneQuery = String.format("SELECT * FROM Product WHERE id='%s'", this.id);
    }

    public void setId(int id) {
        this.id = id;
        this.updateField("id", id);
    }

    public void setName(String name) {
        this.name = name;
        this.updateField("name", name);
    }

    public void setOrder_id(Integer order_id) {

        this.order_id = order_id;
        this.updateField("order_id", this.order_id);
    }

    public void setPrice(double price) {
        this.price = price;
        this.updateField("price", price);
    }

    public int getId() {
        return id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
