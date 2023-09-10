package com.project.questaidbackend.services.interfaces;

import com.project.questaidbackend.models.ClubMember;
import com.project.questaidbackend.models.base.ResponseClubMember;
import com.project.questaidbackend.models.enums.ClubMemberRoles;

public interface IClubMemberService {
    ResponseClubMember getResponseClubMember(Long id);
    ResponseClubMember getClubMemberById(Long id);
    ClubMember getClubMemberAllDetailsById(Long id);
    ClubMember addMemberInClub(Long studentId, Long clubId);
    ClubMember changeDepartment(Long oldDepartmentId, Long newDepartmentId, Long clubMemberId);
    ClubMember changeMemberRole(Long clubMemberId, ClubMemberRoles newRole);
}
