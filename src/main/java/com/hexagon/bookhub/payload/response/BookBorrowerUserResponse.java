package com.hexagon.bookhub.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrowerUserResponse {
    private String id;
    private UserResponse guestUser;
    private Date approvalDate;
}
