package com.networks.maribo.service;

public class Product {
    private final int id;
    private int category_id;
    private int characteristic_id;
    private String name;
    private int price;
    private int width;
    private int height;
    private int length;
    private int weight;

    public Product(int id, int category_id, int characteristic_id, String name, int price, int width, int height, int length, int weight) {
        this.id = id;
        this.category_id = category_id;
        this.characteristic_id = characteristic_id;
        this.name = name;
        this.price = price;
        this.width = width;
        this.height = height;
        this.length = length;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getCharacteristic_id() {
        return characteristic_id;
    }

    public void setCharacteristic_id(int characteristic_id) {
        this.characteristic_id = characteristic_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
