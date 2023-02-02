package com.hexagon.bookhub.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "physical_books")
public class PhysicalBook extends Book{
    private String donatedBy;
    @Field
    private List<GuestUser> borrowerList = new ArrayList<>();;
    private EStatus status;
    @Field
    private List<GuestUser> requestersList = new ArrayList<>();
    public PhysicalBook(String title, String author, String Genre, String description, String publisher, String edition, String donatedBy) {
        super(title, author, Genre, description, publisher, edition);
        this.donatedBy = donatedBy;
    }
}
