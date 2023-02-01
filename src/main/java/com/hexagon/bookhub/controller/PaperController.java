package com.hexagon.bookhub.controller;

import com.hexagon.bookhub.entity.EPaperType;
import com.hexagon.bookhub.entity.Paper;
import com.hexagon.bookhub.payload.request.PaperRequest;
import com.hexagon.bookhub.repository.PaperRepository;
import com.hexagon.bookhub.service.PaperSevice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/paper")
@Slf4j
public class PaperController {

    @Autowired
    private PaperSevice paperSevice;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Paper> savePaper(HttpServletRequest request,@RequestBody PaperRequest paperRequest){
        log.info("Inside the savePaper in Paper Controller");
        Paper paper = new Paper(paperRequest.getSubject(), paperRequest.getGrade(), paperRequest.getSchool(), paperRequest.getTerm(), paperRequest.getPaperUrl());
        switch (paperRequest.getPaperType()){
            case "ADVANCED_LEVEL":
                paper.setPaperType(EPaperType.ADVANCED_LEVEL);
                break;
            case "ORDINARY_LEVEL":
                paper.setPaperType(EPaperType.ORDINARY_LEVEL);
                break;
            default:
                paper.setPaperType(EPaperType.OTHER);
        }
        return paperSevice.savePaper(request,paper);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Paper>> getPapers(){
        log.info("Inside the savePaper in Paper Controller");
        return paperSevice.getPapers();
    }
}
