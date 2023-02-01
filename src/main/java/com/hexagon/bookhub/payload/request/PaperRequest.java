package com.hexagon.bookhub.payload.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperRequest {

    private String subject;

    private int grade;

    private String school;

    private int term;

    private String paperUrl;

    private String paperType;
}
