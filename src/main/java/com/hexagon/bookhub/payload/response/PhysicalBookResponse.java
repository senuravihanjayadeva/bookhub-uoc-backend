package com.hexagon.bookhub.payload.response;

import com.hexagon.bookhub.entity.BookRequestUser;
import com.hexagon.bookhub.entity.EStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhysicalBookResponse {
    private String id;
    private String title;
    private String author;
    private String genre;
    private String description;
    private String publisher;
    private String edition;
    private String donatedBy;
    private List<BookBorrowerUserResponse> borrowerList;
    private EStatus status;
    private List<BookRequestUserResponse> requestersList;

    public PhysicalBookResponse(String id, String title, String author, String genre, String description, String publisher, String edition, String donatedBy, EStatus status) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.description = description;
        this.publisher = publisher;
        this.edition = edition;
        this.donatedBy = donatedBy;
        this.status = status;
    }
}
