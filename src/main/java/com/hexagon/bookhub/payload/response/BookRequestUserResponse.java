package com.hexagon.bookhub.payload.response;

import com.hexagon.bookhub.entity.GuestUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestUserResponse {
    private String id;
    private UserResponse guestUser;
    private Date requestedDate;
}
