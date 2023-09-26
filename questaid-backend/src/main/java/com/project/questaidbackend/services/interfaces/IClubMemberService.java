package com.project.questaidbackend.services.interfaces;

import com.project.questaidbackend.models.ClubMember;
import com.project.questaidbackend.models.Student;
import com.project.questaidbackend.models.base.ResponseClubMember;
import com.project.questaidbackend.models.enums.ClubMemberRoles;

import java.util.List;

public interface IClubMemberService {
    ResponseClubMember getResponseClubMember(Long id);

    /**
     * Get the club member details to be viewed publicly
     * @param id: the unique id of the club member
     * @return ResponseClubMember: publicly available details of the club member
     */
    ResponseClubMember getClubMemberById(Long id);

    /**
     * Get all the club member details by the unique id
     * @param id: the unique id of the club member
     * @return ClubMember returned by the method
     */
    ClubMember getClubMemberAllDetailsById(Long id);
    ClubMember addMemberInClub(Long studentId, Long clubId);
    ClubMember changeDepartment(Long oldDepartmentId, Long newDepartmentId, Long clubMemberId);
    ClubMember changeMemberRole(Long clubMemberId, ClubMemberRoles newRole);
    List<Student> getClubMembersByClubId(Long id);
}
