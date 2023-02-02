package com.hexagon.bookhub.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "papers")
public class Paper {

    @Id
    private String id;

    private String subject;

    private int grade;

    private String school;

    private int term;

    private String paperUrl;

    private EPaperType paperType;

    @Field
    private boolean isDeleted = false;

    public Paper(String subject, int grade, String school, int term, String paperUrl) {
        this.subject = subject;
        this.grade = grade;
        this.school = school;
        this.term = term;
        this.paperUrl = paperUrl;
    }
}
