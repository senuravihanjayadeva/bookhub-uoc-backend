package com.hexagon.bookhub.service;
import com.hexagon.bookhub.payload.response.AdminResponse;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface AdminService {
    public ResponseEntity<AdminResponse> getUserDetails(HttpServletRequest request);

}
