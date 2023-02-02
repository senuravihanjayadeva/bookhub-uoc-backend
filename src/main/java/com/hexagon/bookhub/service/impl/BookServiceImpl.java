package com.hexagon.bookhub.service.impl;

import com.hexagon.bookhub.entity.GuestUser;
import com.hexagon.bookhub.entity.Paper;
import com.hexagon.bookhub.entity.PhysicalBook;
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
    private PhysicalBookRepository physicalBookRepository;
    @Autowired
    private AutheticationUtil autheticationUtil;

    public ResponseEntity<?> donateBook(HttpServletRequest request, PhysicalBook physicalBook){
        try {
            log.info("Inside the donateBook in Physical Book Service");
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
}
