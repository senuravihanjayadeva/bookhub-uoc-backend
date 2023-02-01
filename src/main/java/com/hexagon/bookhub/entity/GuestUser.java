package com.hexagon.bookhub.entity;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "guest_users")
public class GuestUser extends User{

    private String fullName;
    private String contactNumber;
    private String address;
    private boolean isStudent;
    private String  companyOrUniversity;
    private boolean isPrivacyEnable;

    @DBRef
    @Field
    private List<Paper> paperList = new ArrayList<>();

    public GuestUser() {
    }

    public GuestUser(String fullName, String contactNumber, String address, boolean isStudent, String companyOrUniversity, boolean isPrivacyEnable) {
        this.fullName = fullName;
        this.contactNumber = contactNumber;
        this.address = address;
        this.isStudent = isStudent;
        this.companyOrUniversity = companyOrUniversity;
        this.isPrivacyEnable = isPrivacyEnable;
    }

    public GuestUser(String email, String password, String fullName, String contactNumber, String address, boolean isStudent, String companyOrUniversity, boolean isPrivacyEnable) {
        super(email, password);
        this.fullName = fullName;
        this.contactNumber = contactNumber;
        this.address = address;
        this.isStudent = isStudent;
        this.companyOrUniversity = companyOrUniversity;
        this.isPrivacyEnable = isPrivacyEnable;
    }

    public GuestUser(String fullName, String contactNumber, String address, boolean isStudent, String companyOrUniversity, boolean isPrivacyEnable, List<Paper> paperList) {
        this.fullName = fullName;
        this.contactNumber = contactNumber;
        this.address = address;
        this.isStudent = isStudent;
        this.companyOrUniversity = companyOrUniversity;
        this.isPrivacyEnable = isPrivacyEnable;
        this.paperList = paperList;
    }

    public GuestUser(String email, String password, String fullName, String contactNumber, String address, boolean isStudent, String companyOrUniversity, boolean isPrivacyEnable, List<Paper> paperList) {
        super(email, password);
        this.fullName = fullName;
        this.contactNumber = contactNumber;
        this.address = address;
        this.isStudent = isStudent;
        this.companyOrUniversity = companyOrUniversity;
        this.isPrivacyEnable = isPrivacyEnable;
        this.paperList = paperList;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }

    public String getCompanyOrUniversity() {
        return companyOrUniversity;
    }

    public void setCompanyOrUniversity(String companyOrUniversity) {
        this.companyOrUniversity = companyOrUniversity;
    }

    public boolean isPrivacyEnable() {
        return isPrivacyEnable;
    }

    public void setPrivacyEnable(boolean privacyEnable) {
        isPrivacyEnable = privacyEnable;
    }

    public List<Paper> getPaperList() {
        return paperList;
    }

    public void setPaperList(List<Paper> paperList) {
        this.paperList = paperList;
    }
}
