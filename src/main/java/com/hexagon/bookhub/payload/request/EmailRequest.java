package com.hexagon.bookhub.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequest {
    private String email;
    private String name;
    private String contactNumber;
    private String message;
}
