package com.hexagon.bookhub.service.impl;

import com.hexagon.bookhub.entity.Admin;
import com.hexagon.bookhub.payload.response.AdminResponse;
import com.hexagon.bookhub.repository.AdminRepository;
import com.hexagon.bookhub.service.AdminService;
import com.hexagon.bookhub.util.AutheticationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AutheticationUtil autheticationUtil;

    @Autowired
    private AdminRepository adminRepository;

    public ResponseEntity<AdminResponse> getUserDetails(HttpServletRequest request){
        log.info("Inside the getUserDetails in Admin Service");
        try {
            String userEmail = autheticationUtil.getAuthenticatedEmail(request);
            Optional<Admin> admin = adminRepository.findByEmail(userEmail);
            if (admin.isPresent()) {
                AdminResponse adminResponse = new AdminResponse(
                        admin.get().getId(),
                        admin.get().getEmail(),
                        "admin"
                );
                return new ResponseEntity<>(adminResponse, HttpStatus.OK);
            } else {
                log.info("User not found");
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
