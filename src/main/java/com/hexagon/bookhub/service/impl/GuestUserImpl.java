package com.hexagon.bookhub.service.impl;

import com.hexagon.bookhub.entity.GuestUser;
import com.hexagon.bookhub.entity.PhysicalBook;
import com.hexagon.bookhub.payload.response.GuestUserResponse;
import com.hexagon.bookhub.payload.response.PhysicalBookResponse;
import com.hexagon.bookhub.repository.GuestUserRepository;
import com.hexagon.bookhub.service.GuestUserService;
import com.hexagon.bookhub.util.AutheticationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class GuestUserImpl implements GuestUserService {
    @Autowired
    private AutheticationUtil autheticationUtil;

    @Autowired
    private GuestUserRepository guestUserRepository;
    public ResponseEntity<GuestUserResponse> getUserDetails(HttpServletRequest request){
        log.info("Inside the savePaper in Paper Service");
        try {
            String userEmail = autheticationUtil.getAuthenticatedEmail(request);
            Optional<GuestUser> guestUser = guestUserRepository.findByEmail(userEmail);
            if (guestUser.isPresent()) {
                List<PhysicalBookResponse> physicalBookResponseList = new ArrayList<>();
                for(PhysicalBook physicalBook: guestUser.get().getDonatedBookList()){
                    PhysicalBookResponse physicalBookResponse = new PhysicalBookResponse(
                            physicalBook.getId(),
                            physicalBook.getTitle(),
                            physicalBook.getAuthor(),
                            physicalBook.getGenre(),
                            physicalBook.getDescription(),
                            physicalBook.getPublisher(),
                            physicalBook.getEdition(),
                            physicalBook.getDonatedBy(),
                            physicalBook.getStatus()
                    );
                    physicalBookResponseList.add(physicalBookResponse);
                }

                GuestUserResponse guestUserResponse = new GuestUserResponse(
                        guestUser.get().getId(),
                        guestUser.get().getEmail(),
                        guestUser.get().getFullName(),
                        guestUser.get().getContactNumber(),
                        guestUser.get().getAddress(),
                        guestUser.get().isStudent(),
                        guestUser.get().getCompanyOrUniversity(),
                        guestUser.get().isPrivacyEnable(),
                        physicalBookResponseList
                );
                return new ResponseEntity<>(guestUserResponse, HttpStatus.OK);
            } else {
                log.info("User not found");
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }
}
