package com.company.backinstock;

import com.company.backinstock.dao.ProductDao;
import com.company.backinstock.service.StockService;
import com.company.backinstock.service.StockServiceImpl;
import com.company.backinstock.service.SubscribedProductsService;

public class App {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting app");
        ProductDao dao = new ProductDao();
        SubscribedProductsService subscribedProductsService = new SubscribedProductsService();
        StockService stockService = new StockServiceImpl(dao, subscribedProductsService);
        stockService.start();

    }
}
