package com.hexagon.bookhub.service.impl;

import com.hexagon.bookhub.payload.request.EmailRequest;
import com.hexagon.bookhub.service.ContactService;
import com.hexagon.bookhub.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private EmailUtil emailUtil;
    public ResponseEntity<?> sendEmail(EmailRequest emailRequest) throws MessagingException, IOException {
        String[] emailList = {"2019s17363@stu.cmb.ac.lk", "2019s17452@stu.cmb.ac.lk","2019s17330@stu.cmb.ac.lk",
            emailRequest.getEmail()};
        String subject = "Message From " + emailRequest.getName();
        String content = emailRequest.getMessage() + " - Contact Number:" + emailRequest.getContactNumber();
        for(String email: emailList){
            emailUtil.sendmail(email, subject, content);
        }
        return new ResponseEntity<>("Email Sent", HttpStatus.OK);
    }
}
