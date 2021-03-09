package com.company.backinstock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class User {

    private String name;
    private int age;
    private Long id;
    private boolean premium;
}
