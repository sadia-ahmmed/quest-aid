package com.project.questaidbackend.controllers;

import com.project.questaidbackend.models.ClubDepartment;
import com.project.questaidbackend.models.ClubMember;
import com.project.questaidbackend.models.base.ResponseClubMember;
import com.project.questaidbackend.services.interfaces.IClubDepartmentService;
import com.project.questaidbackend.services.interfaces.IClubMemberService;
import com.project.questaidbackend.services.interfaces.IClubService;
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

    private IClubService clubService;
    private IClubDepartmentService clubDepartmentService;
    private IClubMemberService clubMemberService;


    @GetMapping("/public/{id}")
    public ResponseEntity<ClubDepartment> getDepartment(@PathVariable Long id) {
        return new ResponseEntity<>(clubDepartmentService.getClubDepartment(id), HttpStatus.OK);
    }

    @PostMapping("/create/{id}/club")
    public ResponseEntity<ClubDepartment> createDepartment(@Valid @RequestBody ClubDepartment clubDepartment, @PathVariable Long id) {
        return new ResponseEntity<>(clubService.addDepartment(clubDepartment, id), HttpStatus.CREATED);
    }

    @GetMapping("/get-members/{id}")
    public ResponseEntity<List<ResponseClubMember>> getAllDepartmentMembers(@PathVariable Long id) {
        return new ResponseEntity<>(clubDepartmentService.getDepartmentMembers(id), HttpStatus.OK);
    }

    @PutMapping("/change/{oldDeptId}/to/{newDeptId}/member/{memberId}")
    public ResponseEntity<ClubMember> changeDepartment(@PathVariable Long oldDeptId, @PathVariable Long newDeptId, @PathVariable Long memberId) {
        return new ResponseEntity<>(clubMemberService.changeDepartment(oldDeptId, newDeptId, memberId), HttpStatus.CREATED);
    }

}
