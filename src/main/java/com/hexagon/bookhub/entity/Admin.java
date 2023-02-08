package com.hexagon.bookhub.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "admins")
public class Admin extends User{
    @DBRef
    @Field
    private List<Paper> paperList = new ArrayList<>();
    @DBRef
    @Field
    private List<DigitalBook> digitalBookList = new ArrayList<>();
    @DBRef
    @Field
    private List<AudioBook> audioBookList = new ArrayList<>();
    public Admin(String email, String password) {
        super(email, password);
    }
}
