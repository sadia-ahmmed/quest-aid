package com.project.questaidbackend.controllers;

import com.project.questaidbackend.models.ClubMember;
import com.project.questaidbackend.models.base.ResponseClubMember;
import com.project.questaidbackend.services.interfaces.IClubMemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/member")
public class ClubMemberController {

    private IClubMemberService clubMemberService;

    @GetMapping("/{id}")
    private ResponseEntity<ResponseClubMember> getClubMember(@PathVariable Long id) {
        return new ResponseEntity<>(clubMemberService.getClubMemberDetails(id), HttpStatus.OK);
    }

    @PostMapping("/add/{studentId}/student/{clubId}/club")
    public ResponseEntity<ClubMember> addMemberInClub(@PathVariable Long studentId, @PathVariable Long clubId) {
        return new ResponseEntity<>(clubMemberService.addMemberInClub(studentId, clubId), HttpStatus.CREATED);
    }

}
