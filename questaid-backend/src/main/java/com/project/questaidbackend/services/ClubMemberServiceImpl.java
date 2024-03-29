package com.project.questaidbackend.services;

import com.project.questaidbackend.exceptions.EntityNotFoundException;
import com.project.questaidbackend.models.Club;
import com.project.questaidbackend.models.ClubDepartment;
import com.project.questaidbackend.models.ClubMember;
import com.project.questaidbackend.models.Student;
import com.project.questaidbackend.models.base.ResponseClubMember;
import com.project.questaidbackend.models.enums.ClubMemberRoles;
import com.project.questaidbackend.repository.ClubMemberRepository;
import com.project.questaidbackend.services.interfaces.IClubDepartmentService;
import com.project.questaidbackend.services.interfaces.IClubMemberService;
import com.project.questaidbackend.services.interfaces.IClubService;
import com.project.questaidbackend.services.interfaces.IStudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClubMemberServiceImpl implements IClubMemberService {

    private ClubMemberRepository clubMemberRepository;

    private IStudentService studentService;
    private IClubService clubService;
    private IClubDepartmentService clubDepartmentService;

    @Override
    public ClubMember getClubMemberAllDetailsById(Long id) {
        return unwrapClubMember(clubMemberRepository.findById(id), id);
    }

    @Override
    public ResponseClubMember getClubMemberById(Long id) {
        return new ResponseClubMember(unwrapClubMember(clubMemberRepository.findById(id), id));
    }

    @Override
    public ResponseClubMember getResponseClubMember(Long id) {
        ClubMember clubMember = unwrapClubMember(clubMemberRepository.findByStudentId(id), id);
        return new ResponseClubMember(clubMember);
    }

    @Override
    public ClubMember addMemberInClub(Long studentId, Long clubId) {
        Student student = studentService.getStudentById(studentId);
        Club club = clubService.getClubById(clubId);

        ClubMember clubMember = new ClubMember(
                student,
                club,
                club.getClubDepartments().stream()
                        .filter(clubDepartment -> clubDepartment.getDepartmentName().equals("General")).findFirst()
                        .get(),
                ClubMemberRoles.GENERAL_MEMBER);

        return clubMemberRepository.save(clubMember);
    }

    @Override
    public ClubMember changeDepartment(Long oldDepartmentId, Long newDepartmentId, Long clubMemberId) {
        ClubDepartment clubDepartment = clubDepartmentService.getClubDepartment(oldDepartmentId);
        ClubMember existingClubMember = unwrapClubMember(
                clubDepartment.getClubMemberList().stream()
                        .filter(clubMember -> clubMember.getId().equals(clubMemberId)).findFirst(),
                clubMemberId);

        existingClubMember.setClubDepartment(clubDepartmentService.getClubDepartment(newDepartmentId));

        System.out.println(existingClubMember.getStudent().getName());

        return clubMemberRepository.save(existingClubMember);
    }

    @Override
    public ClubMember changeMemberRole(Long clubMemberId, ClubMemberRoles newRole) {
        ClubMember clubMember = unwrapClubMember(clubMemberRepository.findById(clubMemberId), clubMemberId);
        clubMember.setClubMemberRoles(newRole);
        return clubMemberRepository.save(clubMember);
    }


    @Override
    public List<Student> getClubMembersByClubId(Long id) {
        List<ClubMember> clubMembers = clubMemberRepository.findByClubId(id);
        return clubMembers.stream().map(ClubMember::getStudent).toList();
    }

    @Override
    public List<ResponseClubMember> getClubMembersAsResponseMemberByClubId(Long id) {
        List<ClubMember> clubMembers = clubMemberRepository.findByClubId(id);
        return clubMembers.stream().map(ResponseClubMember::new).toList();
    }

    static ClubMember unwrapClubMember(Optional<ClubMember> entity, Long id) {
        if (entity.isPresent())
            return entity.get();
        else
            throw new EntityNotFoundException(id, ClubMember.class);
    }

}
