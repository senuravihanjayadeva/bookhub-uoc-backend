package com.hexagon.bookhub.service.impl;

import com.hexagon.bookhub.entity.*;
import com.hexagon.bookhub.payload.response.BookBorrowerUserResponse;
import com.hexagon.bookhub.payload.response.BookRequestUserResponse;
import com.hexagon.bookhub.payload.response.PhysicalBookResponse;
import com.hexagon.bookhub.payload.response.UserResponse;
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
import java.util.*;
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
                List<PhysicalBookResponse> physicalBookResponseList = covertToPhysicalBookListResponse(filteredBookList);
                log.info("Coverted the physical book list to PhysicalBookResponse list");
                return new ResponseEntity<>(physicalBookResponseList, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
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

                PhysicalBookResponse covertedResponse = covertToPhysicalBookResponse(physicalBookRepository.save(updateBookRepo));
                return new ResponseEntity<>(covertedResponse, HttpStatus.OK);
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
    public ResponseEntity<?> borrowBook(HttpServletRequest request, String id){
        log.info("Inside the editPhysicalBook in Book Service");
        try {
            log.info("Inside the saveDigitalBook in Book Service");
            String userEmail = autheticationUtil.getAuthenticatedEmail(request);
            if(!userEmail.isEmpty()) {
                log.info("User Email Exist " + userEmail);
                Optional<GuestUser> user = guestUserRepository.findByEmail(userEmail);
                log.info("User : " + user.get().getId());
                if (user.isPresent()) {
                    Optional<PhysicalBook> _book = physicalBookRepository.findById(id);
                    if (_book.isPresent()) {
                        PhysicalBook updateBookRepo = _book.get();
                        if(updateBookRepo.getStatus() == EStatus.AVAILABLE){
                            updateBookRepo.setStatus(EStatus.PENDING);

                            BookRequestUser bookRequestUser = new BookRequestUser();

                            UUID uuid = UUID.randomUUID();
                            bookRequestUser.setId(uuid.toString());
                            bookRequestUser.setGuestUser(user.get());
                            bookRequestUser.setRequestedDate(new Date());
                            log.info("Created a book request user");

                            List<BookRequestUser> _requestersList = new ArrayList<>();
                            log.info("Get requesters list size :" + updateBookRepo.getRequestersList().size());

                            if(updateBookRepo.getRequestersList().size() > 0){
                                log.info("Having a requesters list");
                                _requestersList = updateBookRepo.getRequestersList();
                                _requestersList.add(bookRequestUser);
                            }else{
                                log.info("Empty requesters list");
                                _requestersList.add(bookRequestUser);
                            }
                            updateBookRepo.setRequestersList(_requestersList);
                            log.info("set requesters list" + updateBookRepo.getRequestersList());

                            PhysicalBookResponse covertedResponse = covertToPhysicalBookResponse(physicalBookRepository.save(updateBookRepo));
                            return new ResponseEntity<>(covertedResponse, HttpStatus.OK);
                        }else{
                            return new ResponseEntity<>("You Cant Borrow this Book Now", HttpStatus.NOT_FOUND);
                        }
                    } else {
                        return new ResponseEntity<>("Book Update Error", HttpStatus.NOT_FOUND);
                    }
                }else{
                    log.info("User not found");
                    return new ResponseEntity<>("Book Update Error", HttpStatus.NOT_FOUND);
                }
            }else{
                log.info("User not found");
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<?> approvalForBorrowRequest(String id){
        try {
            log.info("Inside the approvalForBorrowRequest in Book Service");
            Optional<PhysicalBook> _book = physicalBookRepository.findById(id);
            if (_book.isPresent()) {
                PhysicalBook updateBookRepo = _book.get();
                if(updateBookRepo.getStatus() == EStatus.PENDING){

                    List<BookRequestUser> _borrowersList = new ArrayList<>();
                    log.info("Get requesters list size :" + updateBookRepo.getRequestersList().size());

                    if(updateBookRepo.getRequestersList().size() > 0){
                        log.info("Having a requesters list");
                        BookRequestUser bookRequestUser = updateBookRepo.getRequestersList().get(0);
                        log.info("Last requester : " + bookRequestUser.getGuestUser().getId());
                        bookRequestUser.setApprovalDate(new Date());
                        if(updateBookRepo.getBorrowerList().size() > 0){
                            log.info("Having a previous borrowers list");
                            _borrowersList = updateBookRepo.getBorrowerList();
                            log.info("Size of the current borrowed list" + _borrowersList.size());
                            _borrowersList.add(bookRequestUser);
                            updateBookRepo.setStatus(EStatus.BORROWED);
                            log.info("Gave approval for book request user");
                        }else {
                            log.info("No previous borrowers list");
                            _borrowersList.add(bookRequestUser);
                            updateBookRepo.setStatus(EStatus.BORROWED);
                            log.info("Gave approval for book request user");
                        }
                        log.info("Added book request user to borrowers list");
                    }else{
                        log.info("No requesters list");
                        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                    }
                    List<BookRequestUser> _emptyRequestersList = new ArrayList<>();
                    updateBookRepo.setRequestersList(_emptyRequestersList);
                    log.info("Empty requesters list");
                    updateBookRepo.setBorrowerList(_borrowersList);
                    log.info("set borrowers list");

                    PhysicalBookResponse covertedResponse = covertToPhysicalBookResponse(physicalBookRepository.save(updateBookRepo));
                    return new ResponseEntity<>(covertedResponse, HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("You Cant Borrow this Book Now", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>("Book Update Error", HttpStatus.NOT_FOUND);
            }
    }catch (Exception e){
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
    public List<PhysicalBookResponse> covertToPhysicalBookListResponse(List<PhysicalBook> filteredBookList){
        List<PhysicalBookResponse> physicalBookResponseList = new ArrayList<>();

        for(PhysicalBook physicalBook: filteredBookList){
            PhysicalBookResponse physicalBookResponse = new PhysicalBookResponse(
                    physicalBook.getId(),
                    physicalBook.getTitle(),
                    physicalBook.getAuthor(),
                    physicalBook.getGenre(),
                    physicalBook.getDescription(),
                    physicalBook.getPublisher(),
                    physicalBook.getEdition(),
                    physicalBook.getDonatedBy(),
                    physicalBook.getStatus());

            List<BookRequestUserResponse> requestersList = new ArrayList<>();
            for(BookRequestUser bookRequestUser: physicalBook.getRequestersList()){

                UserResponse userResponse = new UserResponse(
                        bookRequestUser.getGuestUser().getId(),
                        bookRequestUser.getGuestUser().getEmail(),
                        bookRequestUser.getGuestUser().getFullName());
                BookRequestUserResponse bookRequestUserResponse = new BookRequestUserResponse(
                        bookRequestUser.getId(),
                        userResponse,
                        bookRequestUser.getRequestedDate());
                requestersList.add(bookRequestUserResponse);
            }

            physicalBookResponse.setRequestersList(requestersList);

            List<BookBorrowerUserResponse> borrowesList = new ArrayList<>();
            for(BookRequestUser bookRequestUser: physicalBook.getBorrowerList()){
                UserResponse userResponse = new UserResponse(
                        bookRequestUser.getGuestUser().getId(),
                        bookRequestUser.getGuestUser().getEmail(),
                        bookRequestUser.getGuestUser().getFullName());
                BookBorrowerUserResponse bookBorrowerUserResponse = new BookBorrowerUserResponse(
                        bookRequestUser.getId(),
                        userResponse,
                        bookRequestUser.getApprovalDate());
                borrowesList.add(bookBorrowerUserResponse);
            }

            physicalBookResponse.setBorrowerList(borrowesList);

            physicalBookResponseList.add(physicalBookResponse);

        }
        return physicalBookResponseList;
    }
    public PhysicalBookResponse covertToPhysicalBookResponse(PhysicalBook physicalBook){

            PhysicalBookResponse physicalBookResponse = new PhysicalBookResponse(
                    physicalBook.getId(),
                    physicalBook.getTitle(),
                    physicalBook.getAuthor(),
                    physicalBook.getGenre(),
                    physicalBook.getDescription(),
                    physicalBook.getPublisher(),
                    physicalBook.getEdition(),
                    physicalBook.getDonatedBy(),
                    physicalBook.getStatus());

            List<BookRequestUserResponse> requestersList = new ArrayList<>();
            for(BookRequestUser bookRequestUser: physicalBook.getRequestersList()){

                UserResponse userResponse = new UserResponse(
                        bookRequestUser.getGuestUser().getId(),
                        bookRequestUser.getGuestUser().getEmail(),
                        bookRequestUser.getGuestUser().getFullName());
                BookRequestUserResponse bookRequestUserResponse = new BookRequestUserResponse(
                        bookRequestUser.getId(),
                        userResponse,
                        bookRequestUser.getRequestedDate());
                requestersList.add(bookRequestUserResponse);
            }

            physicalBookResponse.setRequestersList(requestersList);

            List<BookBorrowerUserResponse> borrowesList = new ArrayList<>();
            for(BookRequestUser bookRequestUser: physicalBook.getBorrowerList()){
                UserResponse userResponse = new UserResponse(
                        bookRequestUser.getGuestUser().getId(),
                        bookRequestUser.getGuestUser().getEmail(),
                        bookRequestUser.getGuestUser().getFullName());
                BookBorrowerUserResponse bookBorrowerUserResponse = new BookBorrowerUserResponse(
                        bookRequestUser.getId(),
                        userResponse,
                        bookRequestUser.getApprovalDate());
                borrowesList.add(bookBorrowerUserResponse);
            }

            physicalBookResponse.setBorrowerList(borrowesList);


        return physicalBookResponse;
    }
}
