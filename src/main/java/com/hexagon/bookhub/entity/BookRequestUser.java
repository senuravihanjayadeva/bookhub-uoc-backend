package com.hexagon.bookhub.entity;

import com.hexagon.bookhub.payload.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestUser {
    @Id
    private String id;
    private UserResponse guestUser;
    private Date requestedDate;
    private Date approvalDate;

}
