package com.project.questaidbackend.controllers;

import com.project.questaidbackend.models.Admin;
import com.project.questaidbackend.models.Club;
import com.project.questaidbackend.services.interfaces.IAdminService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private IAdminService adminService;

    // TODO: shift to university side
    @PostMapping("/register")
    public ResponseEntity<Admin> createAdmin(@Valid @RequestBody Admin admin) {
        return new ResponseEntity<>(adminService.createAdmin(admin), HttpStatus.CREATED);
    }


    @PostMapping("/add/club")
    public ResponseEntity<Club> createClub(@Valid @RequestBody Club club) {
        return new ResponseEntity<>(adminService.createClub(club), HttpStatus.CREATED);
    }

}
