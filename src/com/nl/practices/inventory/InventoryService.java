package com.nl.practices.inventory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class InventoryService {

    List<Product> products = new ArrayList<>();

    public void addProduct(Product product){
        products.add(product);
    }

    public void removeProduct(String productId){
        products.removeIf(product -> product.getProductId().equals(productId));
    }

    public void updateStock(String productId, int quantityInStock){
        products.stream()
                .filter(e -> e.getProductId().equals(productId))
                .findFirst()
                .ifPresent(product -> product.setQuantityInStock(quantityInStock));
    }

    public Optional<Product> findProductByName(String name){
        return products.stream()
                .filter(e -> e.getName().equals(name))
                .findFirst();
    }

    public List<Product> listProductsByPrice(){
        return products
                .stream()
                .sorted(Comparator.comparing(Product::getPrice))
                .toList();
    }
}
