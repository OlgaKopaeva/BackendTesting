package com.gb.jdbc;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private int id;
    private String title;
    private int price;
    private String categoryTitle;

    public Product(int id, String title, int price, String categoryTitle) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.categoryTitle = categoryTitle;
    }

    public Integer getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getCategoryTitle() {
        return this.categoryTitle;
    }

    public int getPrice() {
        return this.price;
    }

    public String toString(){
        return "product: " + getId() + ", " + getTitle() + ", " + getPrice() + ", " + getCategoryTitle();
    }
}
