package com.techelevator;

import java.math.BigDecimal;

public class Product {
    private String type;
    private BigDecimal price;
    private String name;
    private String keyCode;
    private int stock;

    public Product(String type, BigDecimal price, String name, String keyCode) {
        this.type = type;
        this.price = price;
        this.name = name;
        this.keyCode = keyCode;
        this.stock = 5;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getKeyCode() {
        return keyCode;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
