package com.project.questaidbackend.controllers;

import com.project.questaidbackend.models.ClubDepartment;
import com.project.questaidbackend.models.ClubMember;
import com.project.questaidbackend.models.base.ResponseClubMembers;
import com.project.questaidbackend.services.interfaces.IClubDepartmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/department")
public class ClubDepartmentController {

    private IClubDepartmentService clubDepartmentService;


    @GetMapping("/public/{id}")
    public ResponseEntity<ClubDepartment> getDepartment(@PathVariable Long id) {
        return new ResponseEntity<>(clubDepartmentService.getClubDepartment(id), HttpStatus.OK);
    }

    @PostMapping("/create/{id}/club")
    public ResponseEntity<ClubDepartment> createDepartment(@Valid @RequestBody ClubDepartment clubDepartment, @PathVariable Long id) {
        return new ResponseEntity<>(clubDepartmentService.createDepartment(clubDepartment, id), HttpStatus.CREATED);
    }

    @GetMapping("/get-members/{id}")
    public ResponseEntity<List<ResponseClubMembers>> getAllDepartmentMembers(@PathVariable Long id) {
        return new ResponseEntity<>(clubDepartmentService.getDepartmentMembers(id), HttpStatus.OK);
    }

}
