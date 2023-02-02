package com.hexagon.bookhub.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonateBookRequest {
    private String title;
    private String author;
    private String Genre;
    private String description;
    private String publisher;
    private String edition;
    private String donatedBy;
}
