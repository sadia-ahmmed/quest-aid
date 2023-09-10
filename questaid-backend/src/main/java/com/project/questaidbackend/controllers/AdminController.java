package com.project.questaidbackend.controllers;

import com.project.questaidbackend.models.Admin;
import com.project.questaidbackend.models.Club;
import com.project.questaidbackend.services.interfaces.IAdminService;
import com.project.questaidbackend.services.interfaces.IClubService;
import com.project.questaidbackend.services.interfaces.IStudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/add/club")
    public ResponseEntity<Club> createClub(@Valid @RequestBody Club club) {
        return new ResponseEntity<>(clubService.createClub(club), HttpStatus.CREATED);
    }


    @PutMapping("/verify/{id}/student/{status}")
    public ResponseEntity<HttpStatus> verifyStudent(@PathVariable Long id, @PathVariable Boolean status){
        studentService.verifyStudent(id, status);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
