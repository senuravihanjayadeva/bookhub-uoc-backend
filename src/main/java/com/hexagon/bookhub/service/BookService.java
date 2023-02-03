package com.hexagon.bookhub.service;

import com.hexagon.bookhub.entity.DigitalBook;
import com.hexagon.bookhub.entity.Paper;
import com.hexagon.bookhub.entity.PhysicalBook;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface BookService {
    public ResponseEntity<?> donateBook(HttpServletRequest request, PhysicalBook physicalBook);
    public ResponseEntity<?> borrowBook(HttpServletRequest request, String id);
    public ResponseEntity<?> approvalForBorrowRequest(String id);
    public ResponseEntity<?> getAllPhysicalBooks();
    public ResponseEntity<?> editPhysicalBook(String id, PhysicalBook physicalBook);
    public ResponseEntity<?> saveDigitalBook(HttpServletRequest request, DigitalBook digitalBook);
    public ResponseEntity<?> getAllDigitalBooks();
    public ResponseEntity<?> editDigitalBook(String id, DigitalBook digitalBook);

}
