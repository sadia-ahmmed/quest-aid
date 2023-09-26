package com.project.questaidbackend.services.interfaces;

import com.project.questaidbackend.models.Club;
import com.project.questaidbackend.models.ClubDepartment;
import com.project.questaidbackend.models.base.ResponseClubMember;

import java.util.List;

public interface IClubDepartmentService {
    ClubDepartment getClubDepartment(Long id);
    List<ClubDepartment> getDepartmentsByClubId(Long clubId);
    ClubDepartment addDepartment(ClubDepartment department, Club club);
    List<ResponseClubMember> getDepartmentMembers(Long departmentId);

}
