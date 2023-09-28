package com.project.questaidbackend.controllers;

import com.project.questaidbackend.models.ClubMember;
import com.project.questaidbackend.models.Student;
import com.project.questaidbackend.models.base.ResponseClubMember;
import com.project.questaidbackend.services.interfaces.IClubMemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/member")
public class ClubMemberController {

    private IClubMemberService clubMemberService;

    /**
     * Gets you the details and relational links to other tables
     * @param id: the unique id of a member
     * @return JSON ClubMember
     */
    @GetMapping("/private/{id}")
    public ResponseEntity<ClubMember> getClubMemberPublic(@PathVariable Long id) {
        return new ResponseEntity<>(clubMemberService.getClubMemberAllDetailsById(id), HttpStatus.OK);
    }


    @GetMapping("/get/members/{clubId}/club")
    public ResponseEntity<List<Student>> getClubMembersByClubId(@PathVariable Long clubId) {
        return new ResponseEntity<>(clubMemberService.getClubMembersByClubId(clubId), HttpStatus.OK);
    }

    @GetMapping("/get/members/details/{clubId}/club")
    public ResponseEntity<List<ResponseClubMember>> getClubMembersAsResponseMemberByClubId(@PathVariable Long clubId) {
        return new ResponseEntity<>(clubMemberService.getClubMembersAsResponseMemberByClubId(clubId), HttpStatus.OK);
    }


    /**
     * Gets you the name, email etc. public details
     * @param id: the unique id of a member
     * @return JSON ResponseClubMember
     */
    @GetMapping("/public/{id}")
    public ResponseEntity<ResponseClubMember> getClubMember(@PathVariable Long id) {
        return new ResponseEntity<>(clubMemberService.getClubMemberById(id), HttpStatus.OK);
    }


    /**
     * Add/Recruit a member to a club
     * @param studentId: the unique id of the student
     * @param clubId: the unique id of the club
     * @return JSON ClubMember
     */
    @PostMapping("/recruit/{studentId}/student/{clubId}/club")
    public ResponseEntity<ClubMember> addMemberInClub(@PathVariable Long studentId, @PathVariable Long clubId) {
        return new ResponseEntity<>(clubMemberService.addMemberInClub(studentId, clubId), HttpStatus.CREATED);
    }

}
