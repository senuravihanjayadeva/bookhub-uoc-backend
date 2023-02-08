package com.hexagon.bookhub.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "guest_users")
public class GuestUser extends User{

    private String fullName;
    private String contactNumber;
    private String address;
    private boolean isStudent;
    private String  companyOrUniversity;
    private boolean isPrivacyEnable;
    private String profileImageLink;
    @DBRef
    @Field
    private List<PhysicalBook> donatedBookList = new ArrayList<>();

    public GuestUser(String fullName, String contactNumber, String address, boolean isStudent, String companyOrUniversity, boolean isPrivacyEnable) {
        this.fullName = fullName;
        this.contactNumber = contactNumber;
        this.address = address;
        this.isStudent = isStudent;
        this.companyOrUniversity = companyOrUniversity;
        this.isPrivacyEnable = isPrivacyEnable;
    }

    public GuestUser(String email, String password, String fullName, String contactNumber, String address, boolean isStudent, String companyOrUniversity, boolean isPrivacyEnable, String profileImageLink) {
        super(email, password);
        this.fullName = fullName;
        this.contactNumber = contactNumber;
        this.address = address;
        this.isStudent = isStudent;
        this.companyOrUniversity = companyOrUniversity;
        this.isPrivacyEnable = isPrivacyEnable;
        this.profileImageLink = profileImageLink;
    }

    public GuestUser(String fullName, String contactNumber, String address, boolean isStudent, String companyOrUniversity, boolean isPrivacyEnable, List<PhysicalBook> donatedBookList) {
        this.fullName = fullName;
        this.contactNumber = contactNumber;
        this.address = address;
        this.isStudent = isStudent;
        this.companyOrUniversity = companyOrUniversity;
        this.isPrivacyEnable = isPrivacyEnable;
        this.donatedBookList = donatedBookList;
    }

    public GuestUser(String email, String password, String fullName, String contactNumber, String address, boolean isStudent, String companyOrUniversity, boolean isPrivacyEnable, List<PhysicalBook> donatedBookList) {
        super(email, password);
        this.fullName = fullName;
        this.contactNumber = contactNumber;
        this.address = address;
        this.isStudent = isStudent;
        this.companyOrUniversity = companyOrUniversity;
        this.isPrivacyEnable = isPrivacyEnable;
        this.donatedBookList = donatedBookList;
    }
}
