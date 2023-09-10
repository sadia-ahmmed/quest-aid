package com.project.questaidbackend.services.interfaces;

import com.project.questaidbackend.models.ClubMember;
import com.project.questaidbackend.models.base.ResponseClubMember;

public interface IClubMemberService {
    ResponseClubMember getClubMemberDetails(Long id);
    ClubMember addMemberInClub(Long studentId, Long clubId);
    ClubMember changeDepartment(Long oldDepartmentId, Long newDepartmentId, Long clubMemberId);
}
