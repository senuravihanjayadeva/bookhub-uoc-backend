package com.hexagon.bookhub.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private String profileImageLink;
}
