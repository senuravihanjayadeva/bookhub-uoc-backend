package com.hexagon.bookhub.payload.response;

import com.hexagon.bookhub.entity.PhysicalBook;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String id;
    private String email;
    private String fullName;
}
