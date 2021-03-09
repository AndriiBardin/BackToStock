package com.company.backinstock.dao;

import com.company.backinstock.model.Product;
import java.util.Collections;
import java.util.List;

public class ProductDao {

    public List<Product> products;

    public List<Product> getProducts() {
        // replace with real db interface
        // mocking list in tests
        return Collections.emptyList();
    }
}
