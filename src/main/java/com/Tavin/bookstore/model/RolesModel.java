package com.Tavin.bookstore.model;

import lombok.Getter;

@Getter
public enum RolesModel {

    ADMIN("admin"),

    USER("user");

    private String role;

     RolesModel(String role) {
        this.role = role;
    }
}
