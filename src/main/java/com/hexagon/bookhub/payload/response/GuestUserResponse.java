package com.hexagon.bookhub.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestUserResponse {

    private String id;
    private String email;
    private String fullName;
    private String contactNumber;
    private String address;
    private boolean isStudent;
    private String  companyOrUniversity;
    private boolean isPrivacyEnable;
    private String profileImageLink;
    private String role;
    private List<PhysicalBookResponse> donatedBookList;
}
