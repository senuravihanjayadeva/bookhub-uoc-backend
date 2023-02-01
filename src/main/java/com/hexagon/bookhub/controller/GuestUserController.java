package com.hexagon.bookhub.controller;

import com.hexagon.bookhub.security.jwt.AuthTokenFilter;
import com.hexagon.bookhub.security.jwt.JwtUtils;
import com.hexagon.bookhub.util.AutheticationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/guestauth")
public class GuestUserController {
    @Autowired
    private AutheticationUtil autheticationUtil;
    @GetMapping
    public String getEmail(HttpServletRequest request){
        return autheticationUtil.getAuthenticatedEmail(request);
    }
}
