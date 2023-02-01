package com.hexagon.bookhub.service;

import com.hexagon.bookhub.entity.GuestUser;
import com.hexagon.bookhub.payload.response.GuestUserResponse;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface GuestUserService {
    public ResponseEntity<GuestUserResponse> getUserDetails(HttpServletRequest request);

}
