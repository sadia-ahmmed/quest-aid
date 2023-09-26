package com.project.questaidbackend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.questaidbackend.helpers.dto.EnumDto;
import com.project.questaidbackend.models.ClubDepartment;
import com.project.questaidbackend.models.ClubMember;
import com.project.questaidbackend.models.base.ResponseClubMember;
import com.project.questaidbackend.models.enums.ClubMemberRoles;
import com.project.questaidbackend.services.interfaces.IClubDepartmentService;
import com.project.questaidbackend.services.interfaces.IClubMemberService;
import com.project.questaidbackend.services.interfaces.IClubService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @GetMapping("/get-members/{id}")
    public ResponseEntity<List<ResponseClubMember>> getAllDepartmentMembers(@PathVariable Long id) {
        return new ResponseEntity<>(clubDepartmentService.getDepartmentMembers(id), HttpStatus.OK);
    }

    @GetMapping("/get/departments/{clubId}/club")
    public ResponseEntity<List<ClubDepartment>> getDepartmentsByClubId(@PathVariable Long clubId) {
        return new ResponseEntity<>(clubDepartmentService.getDepartmentsByClubId(clubId), HttpStatus.OK);
    }


    @PostMapping("/create/{id}/club")
    public ResponseEntity<ClubDepartment> createDepartment(@Valid @RequestBody ClubDepartment clubDepartment, @PathVariable Long id) {
        return new ResponseEntity<>(clubService.addDepartment(clubDepartment, id), HttpStatus.CREATED);
    }



    /**
     *
     * @param oldDeptId: the unique id of the old department
     * @param newDeptId: the unique id of the new department
     * @param memberId: the unique id of the member
     * @return JSON of club member
     */
    @PutMapping("/change/{oldDeptId}/to/{newDeptId}/member/{memberId}")
    public ResponseEntity<ClubMember> changeDepartment(@PathVariable Long oldDeptId, @PathVariable Long newDeptId, @PathVariable Long memberId) {
        return new ResponseEntity<>(clubMemberService.changeDepartment(oldDeptId, newDeptId, memberId), HttpStatus.CREATED);
    }


    /**
     *
     * @param id: the unique id of the club member
     * @param newRole: the new role of the club member annotated in {"content": "NEW_ROLE_ALL_CAPS"}
     * @return JSON of club member
     */
    @PutMapping("/promote/{id}/member")
    public ResponseEntity<ClubMember> promoteMember(@PathVariable Long id, @RequestBody String newRole) {
        try {
            EnumDto enumDto = new ObjectMapper().readValue(newRole, EnumDto.class);
            System.out.println("Check " + ClubMemberRoles.valueOf(enumDto.getContent()));
            return new ResponseEntity<>(clubMemberService.changeMemberRole(id, ClubMemberRoles.valueOf(enumDto.getContent())), HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("JSON couldn't be processed");
        }

    }

}
