package com.hexagon.bookhub.service.impl;

import com.hexagon.bookhub.entity.Admin;
import com.hexagon.bookhub.entity.GuestUser;
import com.hexagon.bookhub.entity.Paper;
import com.hexagon.bookhub.entity.PhysicalBook;
import com.hexagon.bookhub.repository.AdminRepository;
import com.hexagon.bookhub.repository.GuestUserRepository;
import com.hexagon.bookhub.repository.PaperRepository;
import com.hexagon.bookhub.service.PaperSevice;
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
public class PaperServiceImpl implements PaperSevice {

    @Autowired
    private PaperRepository paperRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AutheticationUtil autheticationUtil;
    @Override
    public ResponseEntity<?> savePaper(HttpServletRequest request,Paper paper){
        try {
            log.info("Inside the savePaper in Paper Service");
            String userEmail = autheticationUtil.getAuthenticatedEmail(request);
            if(!userEmail.isEmpty()){
                log.info("User Email Exist " + userEmail);
                Optional<Admin> user = adminRepository.findByEmail(userEmail);
                log.info("User : " + user.get().getId());
                if (user.isPresent()) {
                    Paper _paper = paperRepository.save(paper);
                    log.info("Paper saved " + _paper.getId());
                    Admin _updatedUser = user.get();
                    log.info("Get User for updating the paper list");
                    List<Paper> _paperList = new ArrayList<Paper>();
                    log.info("Get User for  paper list size :" + _updatedUser.getPaperList().size());
                    if(_updatedUser.getPaperList().size() > 0){
                        log.info("Having a Papers List");
                        _paperList = _updatedUser.getPaperList();
                        _paperList.add(_paper);
                    }else{
                        log.info("Empty Papers List");
                        _paperList.add(_paper);
                    }

                    _updatedUser.setPaperList(_paperList);
                    log.info("Added the new Paper to the User Paper List");
                    adminRepository.save(_updatedUser);
                    log.info("Updated the user with new Paper List");
                    return new ResponseEntity<Paper>(_paper, HttpStatus.OK);
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

    @Override
    public ResponseEntity<?> getPapers(){
        try{
            log.info("Inside the getPapers in Paper Service");
           List<Paper> paperList =  paperRepository.findAll();
            if(paperList.size() > 0){
                List<Paper> filterePaperList = paperList.stream()
                        .filter(paper -> paper.isDeleted() == false)
                        .collect(Collectors.toList());
                log.info("Filtered the non deleted papers");
                return new ResponseEntity<List<Paper>>(filterePaperList, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> editPaper(String id, Paper paper){
        try {
            log.info("Inside the editPaper in Paper Service");
            Optional<Paper> _paper = paperRepository.findById(id);
            if (_paper.isPresent()) {
                Paper updatePaperRepo = _paper.get();
                updatePaperRepo.setGrade(paper.getGrade());
                updatePaperRepo.setPaperUrl(paper.getPaperUrl());
                updatePaperRepo.setSchool(paper.getSchool());
                updatePaperRepo.setSubject(paper.getSubject());
                updatePaperRepo.setTerm(paper.getTerm());
                updatePaperRepo.setPaperType(paper.getPaperType());
                return new ResponseEntity<>(paperRepository.save(updatePaperRepo), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Paper Update Error", HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
