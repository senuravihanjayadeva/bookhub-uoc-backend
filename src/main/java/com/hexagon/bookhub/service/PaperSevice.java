package com.hexagon.bookhub.service;

import com.hexagon.bookhub.entity.Paper;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface PaperSevice {
    public ResponseEntity<Paper> savePaper(HttpServletRequest request, Paper paper);
}
