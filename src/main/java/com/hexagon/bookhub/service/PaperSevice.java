package com.hexagon.bookhub.service;

import com.hexagon.bookhub.entity.Paper;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PaperSevice {
    public ResponseEntity<Paper> savePaper(HttpServletRequest request, Paper paper);

    public ResponseEntity<List<Paper>> getPapers();

}
