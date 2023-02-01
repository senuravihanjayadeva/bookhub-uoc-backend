package com.hexagon.bookhub.service;

import com.hexagon.bookhub.entity.Paper;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface PaperSevice {
    public ResponseEntity<?> savePaper(HttpServletRequest request, Paper paper);

    public ResponseEntity<?> getPapers();
    public ResponseEntity<?> editPaper(String id,Paper paper);
}
