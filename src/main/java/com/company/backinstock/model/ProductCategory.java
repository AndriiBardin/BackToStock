package com.company.backinstock.model;

public enum ProductCategory {
    MEDICAL("MEDICAL"),
    DIGITAL("DIGITAL"),
    BOOK("BOOK");

    private final String category;

    ProductCategory(String category) {
        this.category = category;
    }

    public static ProductCategory categoryGet(String category) {
        for (ProductCategory product : ProductCategory.values()) {
            if (product.category.equals(category)) {
                return product;
            }
        }
        throw new IllegalArgumentException("No such category");
    }

    public String get() {
        return category;
    }
}
