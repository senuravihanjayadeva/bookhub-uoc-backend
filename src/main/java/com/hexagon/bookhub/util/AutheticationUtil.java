package com.hexagon.bookhub.util;

import com.hexagon.bookhub.security.jwt.AuthTokenFilter;
import com.hexagon.bookhub.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Service
public class AutheticationUtil {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AuthTokenFilter authTokenFilter;

    public String getAuthenticatedEmail(HttpServletRequest request){

        return jwtUtils.getUserNameFromJwtToken(parseJwt(request));
    }
    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }
        return null;
    }
}
