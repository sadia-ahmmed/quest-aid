package com.project.questaidbackend.repository;

import com.project.questaidbackend.models.ClubDepartment;
import org.springframework.data.repository.CrudRepository;

public interface ClubDepartmentRepository extends CrudRepository<ClubDepartment, Long> {
//    @Query(value = "" +
//            "SELECT s.first_name, s.last_name, s.email, s.phone, cm.club_member_roles, cd.department_name " +
//            "FROM club_member cm " +
//            "INNER JOIN " +
//            "club_department cd ON cm.department_id = cd.id " +
//            "INNER JOIN " +
//            "student s ON s.id = cm.member_id " +
//            "WHERE cd.id = :dept_id"
//            , nativeQuery = true
//    )
//    List<ResponseClubMember> getDepartmentMembers(@Param(value = "dept_id") Long deptId);
}
