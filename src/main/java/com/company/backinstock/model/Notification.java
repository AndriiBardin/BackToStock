package com.company.backinstock.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Notification {
    private User user;
    private Product product;
    private String priority;
}
