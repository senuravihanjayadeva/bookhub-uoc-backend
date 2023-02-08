package com.hexagon.bookhub.service;

import com.hexagon.bookhub.payload.request.EmailRequest;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;
import java.io.IOException;

public interface ContactService {
    public ResponseEntity<?> sendEmail(EmailRequest emailRequest) throws MessagingException, IOException;
}
