package com.project.questaidbackend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.questaidbackend.models.Admin;
import com.project.questaidbackend.models.Club;
import com.project.questaidbackend.services.interfaces.IAdminService;
import com.project.questaidbackend.services.interfaces.IClubService;
import com.project.questaidbackend.services.interfaces.IFileStorageService;
import com.project.questaidbackend.services.interfaces.IStudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private IAdminService adminService;
    private IClubService clubService;
    private IStudentService studentService;

    // TODO: shift to university side
    @PostMapping("/register")
    public ResponseEntity<Admin> createAdmin(@Valid @RequestBody Admin admin) {
        return new ResponseEntity<>(adminService.createAdmin(admin), HttpStatus.CREATED);
    }

    @GetMapping("/by/{email}/email")
    public ResponseEntity<Admin> getAdminByEmail(@PathVariable String email) {
        return new ResponseEntity<>(adminService.getAdminByEmail(email), HttpStatus.OK);
    }

    @PostMapping("/add/club/{adminId}")
    public ResponseEntity<Club> createClub(
            @RequestParam("club") String stringClub,
            @RequestParam("file") MultipartFile file,
            @PathVariable Long adminId
    ) {
        Club club;
        try {
            club = new ObjectMapper().readValue(stringClub, Club.class);
        } catch (IOException e) {
            throw new RuntimeException("Error reading object");
        }
        Admin admin = adminService.getAdminById(adminId);
        return new ResponseEntity<>(clubService.createClub(club, file, admin), HttpStatus.CREATED);
    }

    @PutMapping("/verify/{id}/student/{status}")
    public ResponseEntity<HttpStatus> verifyStudent(@PathVariable Long id, @PathVariable Boolean status){
        studentService.verifyStudent(id, status);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
