package com.techelevator;

import java.math.BigDecimal;

public class Product {
    private String type;
    private BigDecimal price;
    private String name;
    private String keyCode;
    private int stock;
    private String sound;

    public Product(String type, BigDecimal price, String name, String keyCode) {
        this.type = type;
        this.price = price;
        this.name = name;
        this.keyCode = keyCode;
        this.stock = 5;
        switch(this.type){
            case("Chip"):
                this.sound = "Crunch Crunch, YUM!";
                break;
            case("Candy"):
                this.sound = "Munch Munch, YUM!";
                break;
            case("Drink"):
                this.sound = "Glug Glug, YUM!";
                break;
            case("Gum"):
                this.sound = "Chew Chew, YUM!";
                break;

        }
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

    public String getSound() {
        return sound;
    }
}
