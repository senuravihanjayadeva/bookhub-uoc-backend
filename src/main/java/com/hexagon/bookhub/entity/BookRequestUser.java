package com.hexagon.bookhub.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestUser {
    private GuestUser guestUser;
    private Date requestedDate;
    private Date approvalDate;

}
