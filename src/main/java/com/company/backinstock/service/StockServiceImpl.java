package com.company.backinstock.service;

import com.company.backinstock.dao.ProductDao;
import com.company.backinstock.model.Notification;
import com.company.backinstock.model.Product;
import com.company.backinstock.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class StockServiceImpl implements StockService {

    private final ProductDao dao;
    private final NotifyService notifyService;
    private HashMap<User, List<Product>> subscribedProducts;


    public StockServiceImpl(ProductDao dao, SubscribedProductsService subscribedProductsService) {
        this.dao = dao;
        this.notifyService = new NotifyService();

    }

    @Override
    public void start() {
        while (true) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            process();
        }
    }

    @Override
    public void addObservers(HashMap<User, List<Product>> subscribedProducts) {
        this.subscribedProducts = subscribedProducts;
    }

    public List<Notification> process(){
        List<Product> newProducts = dao.getProducts();
        List<Notification> notifications = new ArrayList<>();
        subscribedProducts.entrySet().stream()
                .forEach(entry -> {
                    List<Product> newProductsFromUser = newProducts.stream()
                            .filter(product -> entry.getValue().contains(product))
                            .collect(Collectors.toList());
                    notifications.addAll(NotifyJobBuilder.build(entry.getKey(), newProductsFromUser));
                });
        notifyService.send(notifications);
        return notifications;
    }
}

