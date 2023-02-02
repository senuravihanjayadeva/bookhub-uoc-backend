package com.hexagon.bookhub.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    private String id;
    private String title;
    private String author;
    private String Genre;
    private String description;
    private String publisher;
    private String edition;
    @Field
    private boolean isDeleted = false;
    public Book(String title, String author, String genre, String description, String publisher, String edition) {
        this.title = title;
        this.author = author;
        this.Genre = genre;
        this.description = description;
        this.publisher = publisher;
        this.edition = edition;
    }
}
