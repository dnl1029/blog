package com.example.blog.dto;

import lombok.Data;

@Data
public class Product {
    public int amount;
    public String name;

    public Product(int amount, String name) {
        this.amount = amount;
        this.name = name;
    }
}
