package com.hexagon.bookhub.controller;

import com.hexagon.bookhub.entity.*;
import com.hexagon.bookhub.payload.request.DonateBookRequest;
import com.hexagon.bookhub.payload.request.PaperRequest;
import com.hexagon.bookhub.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/book")
@Slf4j
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/donatebook")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> donateBook(HttpServletRequest request, @RequestBody DonateBookRequest donateBookRequest){
        log.info("Inside the donateBook in Book Controller");
        PhysicalBook physicalBook = new PhysicalBook(
                donateBookRequest.getTitle(),
                donateBookRequest.getAuthor(),
                donateBookRequest.getGenre(),
                donateBookRequest.getDescription(),
                donateBookRequest.getPublisher(),
                donateBookRequest.getEdition(),
                donateBookRequest.getDonatedBy());

        physicalBook.setStatus(EStatus.AVAILABLE);

        return bookService.donateBook(request,physicalBook);
    }

    @GetMapping("/physicalbook/all")
    public ResponseEntity<?> getAllPhysicalBooks(){
        log.info("Inside the getAllPhysicalBooks in Book Controller");
        return bookService.getAllPhysicalBooks();
    }

    @PutMapping("/physicalbook/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> editPhysicalBook(@PathVariable String id, @RequestBody DonateBookRequest donateBookRequest){
        log.info("Inside the editPhysicalBook in Book Controller");
        PhysicalBook physicalBook = new PhysicalBook(
                donateBookRequest.getTitle(),
                donateBookRequest.getAuthor(),
                donateBookRequest.getGenre(),
                donateBookRequest.getDescription(),
                donateBookRequest.getPublisher(),
                donateBookRequest.getEdition(),
                donateBookRequest.getDonatedBy());

        return bookService.editPhysicalBook(id,physicalBook);
    }

    @PostMapping("/digitalbook")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> saveDigitalBook(HttpServletRequest request, @RequestBody DigitalBook digitalBook){
        log.info("Inside the saveDigitalBook in Book Controller");
        return bookService.saveDigitalBook(request,digitalBook);
    }

    @GetMapping("/digitalbook/all")
    public ResponseEntity<?> getAllDigitalBooks(){
        log.info("Inside the getAllDigitalBooks in Book Controller");
        return bookService.getAllDigitalBooks();
    }

}
