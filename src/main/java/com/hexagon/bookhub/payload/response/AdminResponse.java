package com.hexagon.bookhub.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminResponse {
    private String id;
    private String email;
    private String role;
}
