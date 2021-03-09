package com.company.backinstock.service;

import com.company.backinstock.model.Notification;
import com.company.backinstock.model.Product;
import com.company.backinstock.model.ProductCategory;
import com.company.backinstock.model.User;
import com.company.backinstock.model.UserPriority;
import java.util.List;
import java.util.stream.Collectors;

public class NotifyJobBuilder {

    public static List<Notification> build(User user, List<Product> products) {
        return products.stream()
                .map(product -> buildNotification(user, product))
                .collect(Collectors.toList());
    }

    private static Notification buildNotification(User user, Product product) {
        if (user.isPremium()) {
            return new Notification(user, product, UserPriority.HIGH.get());
        }
        else if (user.getAge() >= 70 && product.getCategory().equals(ProductCategory.MEDICAL.get())) {
            return new Notification(user, product, UserPriority.HIGH.get());
        }
        else if (user.getAge() >= 70) {
            return new Notification(user, product, UserPriority.MEDIUM.get());
        }
        else {
            return new Notification(user, product, UserPriority.LOW.get());
        }
    }
}
