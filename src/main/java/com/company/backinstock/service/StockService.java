package com.company.backinstock.service;

import com.company.backinstock.model.Product;
import com.company.backinstock.model.User;

import java.util.HashMap;
import java.util.List;

public interface StockService {

    void start();

    void addObservers(HashMap<User, List<Product>> subscribedProducts);

}
