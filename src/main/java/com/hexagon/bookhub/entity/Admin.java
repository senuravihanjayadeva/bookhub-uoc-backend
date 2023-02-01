package com.hexagon.bookhub.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "admins")
public class Admin extends User{

    public Admin() {
    }

    public Admin(String username, String email, String password) {
        super(username, email, password);
    }
}
