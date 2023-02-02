package com.hexagon.bookhub.payload.response;

import com.hexagon.bookhub.entity.Paper;
import com.hexagon.bookhub.entity.PhysicalBook;
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
    private List<PhysicalBook> donatedBookList;
}
