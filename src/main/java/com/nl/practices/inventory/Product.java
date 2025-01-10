package com.nl.practices.inventory;

import java.math.BigDecimal;

public class Product {
    private String productId;
    private String name;
    private BigDecimal price;
    private int quantityInStock;

    public Product(String productId, String name, BigDecimal price, int quantityInStock) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Product){
            return ((Product) obj).productId.equals(this.productId);
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return productId.hashCode();
    }
}
