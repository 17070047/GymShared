package com.carryondown.app.base;

public class Product {

    private String url;
    private String price;
    private String new_price;

    public Product(String url, String price, String new_price) {
        this.url = url;
        this.price = price;
        this.new_price = new_price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNew_price() {
        return new_price;
    }

    public void setNew_price(String new_price) {
        this.new_price = new_price;
    }
}
