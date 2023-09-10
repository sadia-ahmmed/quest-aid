package com.project.questaidbackend.services.interfaces;

import com.project.questaidbackend.models.ClubDepartment;
import com.project.questaidbackend.models.ClubMember;
import com.project.questaidbackend.models.base.ResponseClubMembers;

import java.util.List;

public interface IClubDepartmentService {
    ClubDepartment getClubDepartment(Long id);
    ClubDepartment createDepartment(ClubDepartment department, Long clubId);
    List<ResponseClubMembers> getDepartmentMembers(Long departmentId);
}
