package com.hexagon.bookhub.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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

    public Paper() {
    }

    public Paper(String id, String subject, int grade, String school, int term, String paperUrl, EPaperType paperType) {
        this.id = id;
        this.subject = subject;
        this.grade = grade;
        this.school = school;
        this.term = term;
        this.paperUrl = paperUrl;
        this.paperType = paperType;
    }

    public Paper(String subject, int grade, String school, int term, String paperUrl) {
        this.subject = subject;
        this.grade = grade;
        this.school = school;
        this.term = term;
        this.paperUrl = paperUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public String getPaperUrl() {
        return paperUrl;
    }

    public void setPaperUrl(String paperUrl) {
        this.paperUrl = paperUrl;
    }

    public EPaperType getPaperType() {
        return paperType;
    }

    public void setPaperType(EPaperType paperType) {
        this.paperType = paperType;
    }
}
