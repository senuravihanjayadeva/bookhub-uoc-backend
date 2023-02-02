package com.hexagon.bookhub.service.impl;

import com.hexagon.bookhub.entity.*;
import com.hexagon.bookhub.repository.AdminRepository;
import com.hexagon.bookhub.repository.DigitalBookRepository;
import com.hexagon.bookhub.repository.GuestUserRepository;
import com.hexagon.bookhub.repository.PhysicalBookRepository;
import com.hexagon.bookhub.service.BookService;
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
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    @Autowired
    private GuestUserRepository guestUserRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private PhysicalBookRepository physicalBookRepository;
    @Autowired
    private DigitalBookRepository digitalBookRepository;
    @Autowired
    private AutheticationUtil autheticationUtil;

    public ResponseEntity<?> donateBook(HttpServletRequest request, PhysicalBook physicalBook){
        try {
            log.info("Inside the donateBook in Book Service");
            String userEmail = autheticationUtil.getAuthenticatedEmail(request);
            if(!userEmail.isEmpty()){
                log.info("User Email Exist " + userEmail);
                Optional<GuestUser> user = guestUserRepository.findByEmail(userEmail);
                log.info("User : " + user.get().getId());
                if (user.isPresent()) {
                    PhysicalBook _book = physicalBookRepository.save(physicalBook);
                    log.info("Book saved " + _book.getId());
                    GuestUser _updatedUser = user.get();
                    log.info("Get User for updating the phys list");
                    List<PhysicalBook> _bookList = new ArrayList<PhysicalBook>();
                    log.info("Get User for donated book list size :" + _updatedUser.getDonatedBookList().size());
                    if(_updatedUser.getDonatedBookList().size() > 0){
                        log.info("Having a donated book list");
                        _bookList = _updatedUser.getDonatedBookList();
                        _bookList.add(_book);
                    }else{
                        log.info("Empty donated book list");
                        _bookList.add(_book);
                    }

                    _updatedUser.setDonatedBookList(_bookList);
                    log.info("Added the new book to the User Donated Book List");
                    guestUserRepository.save(_updatedUser);
                    log.info("Updated the user with new Donated Book List");
                    return new ResponseEntity<PhysicalBook>(_book, HttpStatus.OK);
                } else {
                    log.info("No User for the given mail");
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }else{
                log.info("User not found");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getAllPhysicalBooks(){
        log.info("Inside the getAllPhysicalBooks in Book Service");
        try{
            List<PhysicalBook> bookList =  physicalBookRepository.findAll();
            if(bookList.size() > 0){
                List<PhysicalBook> filteredBookList = bookList.stream()
                        .filter(book -> book.isDeleted() == false)
                        .collect(Collectors.toList());
                log.info("Filtered the non deleted books");
                return new ResponseEntity<List<PhysicalBook>>(filteredBookList, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> editPhysicalBook(String id, PhysicalBook physicalBook){
        log.info("Inside the editPhysicalBook in Book Service");
        try {
            Optional<PhysicalBook> _book = physicalBookRepository.findById(id);
            if (_book.isPresent()) {
                PhysicalBook updateBookRepo = _book.get();
                updateBookRepo.setTitle(physicalBook.getTitle());
                updateBookRepo.setAuthor(physicalBook.getAuthor());
                updateBookRepo.setGenre(physicalBook.getGenre());
                updateBookRepo.setDescription(physicalBook.getDescription());
                updateBookRepo.setPublisher(physicalBook.getPublisher());
                updateBookRepo.setEdition(physicalBook.getEdition());
                updateBookRepo.setDonatedBy(physicalBook.getDonatedBy());
                return new ResponseEntity<>(physicalBookRepository.save(updateBookRepo), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Book Update Error", HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<?> saveDigitalBook(HttpServletRequest request, DigitalBook digitalBook){
        try {
            log.info("Inside the saveDigitalBook in Book Service");
            String userEmail = autheticationUtil.getAuthenticatedEmail(request);
            if(!userEmail.isEmpty()){
                log.info("User Email Exist " + userEmail);
                Optional<Admin> user = adminRepository.findByEmail(userEmail);
                log.info("User : " + user.get().getId());
                if (user.isPresent()) {
                    DigitalBook _book = digitalBookRepository.save(digitalBook);
                    log.info("Book saved " + _book.getId());
                    Admin _updatedUser = user.get();
                    log.info("Get User for updating the digital book list");
                    List<DigitalBook> _bookList = new ArrayList<DigitalBook>();
                    log.info("Get User for digital book list size :" + _updatedUser.getDigitalBookList().size());
                    if(_updatedUser.getDigitalBookList().size() > 0){
                        log.info("Having a digital book list");
                        _bookList = _updatedUser.getDigitalBookList();
                        _bookList.add(_book);
                    }else{
                        log.info("Empty digita; book list");
                        _bookList.add(_book);
                    }

                    _updatedUser.setDigitalBookList(_bookList);
                    log.info("Added the new book to the User Donated Book List");
                    adminRepository.save(_updatedUser);
                    log.info("Updated the user with new Donated Book List");
                    return new ResponseEntity<DigitalBook>(_book, HttpStatus.OK);
                } else {
                    log.info("No User for the given mail");
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }else{
                log.info("User not found");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getAllDigitalBooks(){
        log.info("Inside the getAllDigitalBooks in Book Service");
        try{
            List<DigitalBook> bookList =  digitalBookRepository.findAll();
            if(bookList.size() > 0){
                List<DigitalBook> filteredBookList = bookList.stream()
                        .filter(book -> book.isDeleted() == false)
                        .collect(Collectors.toList());
                log.info("Filtered the non deleted books");
                return new ResponseEntity<List<DigitalBook>>(filteredBookList, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<?> editDigitalBook(String id, DigitalBook digitalBook){
        log.info("Inside the editDigitalBook in Book Service");
        try {
            Optional<DigitalBook> _book = digitalBookRepository.findById(id);
            if (_book.isPresent()) {
                DigitalBook updateBookRepo = _book.get();
                updateBookRepo.setTitle(digitalBook.getTitle());
                updateBookRepo.setAuthor(digitalBook.getAuthor());
                updateBookRepo.setGenre(digitalBook.getGenre());
                updateBookRepo.setDescription(digitalBook.getDescription());
                updateBookRepo.setPublisher(digitalBook.getPublisher());
                updateBookRepo.setEdition(digitalBook.getEdition());
                updateBookRepo.setPdfLink(digitalBook.getPdfLink());
                return new ResponseEntity<>(digitalBookRepository.save(updateBookRepo), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Book Update Error", HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
