package com.company.backinstock.service;

import com.company.backinstock.model.Product;
import com.company.backinstock.model.User;

import java.util.HashMap;
import java.util.List;

public class SubscribedProductsService {
    private HashMap<User, List<Product>> subscribedProduct;

    // request db for watched products
    // mocking in tests

    public HashMap<User, List<Product>> getSubscribedProduct() {
        return subscribedProduct;
    }
}
