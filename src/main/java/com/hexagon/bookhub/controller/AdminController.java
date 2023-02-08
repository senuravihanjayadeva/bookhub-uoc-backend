package com.hexagon.bookhub.controller;

import com.hexagon.bookhub.entity.Admin;
import com.hexagon.bookhub.payload.response.AdminResponse;
import com.hexagon.bookhub.payload.response.GuestUserResponse;
import com.hexagon.bookhub.service.AdminService;
import com.hexagon.bookhub.service.GuestUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @GetMapping
    public ResponseEntity<AdminResponse> getUserDetails(HttpServletRequest request){
        return adminService.getUserDetails(request);
    }
}
