package com.codecool.shop.model;

import com.codecool.shop.model.order.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

public class User {

    private int id;
    private String name;
    private HashMap<Product, Integer> shoppingCart;
    private static User instance;
    private Order newOrder;

    private User() {}



    public static User getInstance() {
        if(instance == null) {
            instance = new User();
            instance.setId(1);
            instance.setName("Bela");
            instance.setShoppingCart(new HashMap<>());
        }
        return instance;
    }

    public Order getNewOrder() {
        return newOrder;
    }

    public void setNewOrder(Order newOrder) {
        this.newOrder = newOrder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Product, Integer> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(HashMap<Product, Integer> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void add(Product product) {
        if(shoppingCart.containsKey(product))
            shoppingCart.put(product, shoppingCart.get(product)+1);
        else
            shoppingCart.put(product, 1);
    }

    public int cartSum() {
        return shoppingCart.values().stream().mapToInt(Integer::intValue).sum();
    }

    public float cartSumPrice() {
        float sum = 0;
        for(Product p: shoppingCart.keySet())
            sum += p.getFloatPrice() * shoppingCart.get(p);
        return sum;
    }

    public void updateCart(int productId, int quantity) {
        for(Product product : shoppingCart.keySet()) {
            if(product.getId() == productId) {
                if(quantity == 0)
                    shoppingCart.remove(product);
                else
                    shoppingCart.put(product, quantity);
            }
        }
    }
}
