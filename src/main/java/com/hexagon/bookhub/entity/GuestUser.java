package com.hexagon.bookhub.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "guest_users")
public class GuestUser extends User{
    public GuestUser() {
    }

    public GuestUser(String username, String email, String password) {
        super(username, email, password);
    }
}
