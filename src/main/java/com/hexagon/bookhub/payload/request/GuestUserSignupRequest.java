package com.hexagon.bookhub.payload.request;

import java.util.Set;

public class GuestUserSignupRequest {
    private String email;
    private Set<String> roles;
    private String password;
    private String fullName;
    private String contactNumber;
    private String address;
    private boolean isStudent;
    private String  companyOrUniversity;
    private boolean isPrivacyEnable;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
