package com.hexagon.bookhub.controller;
import com.hexagon.bookhub.payload.request.EmailRequest;
import com.hexagon.bookhub.service.ContactService;
import com.hexagon.bookhub.util.EmailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/contact")
@Slf4j
public class ContactUsController {
    @Autowired
    private ContactService contactService;
    @PostMapping
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest) throws MessagingException, IOException {
       return contactService.sendEmail(emailRequest);
    }

}
