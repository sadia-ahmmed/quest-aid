package com.project.questaidbackend.services;

import com.project.questaidbackend.exceptions.EntityNotFoundException;
import com.project.questaidbackend.models.Club;
import com.project.questaidbackend.models.ClubDepartment;
import com.project.questaidbackend.models.ClubMember;
import com.project.questaidbackend.models.base.ResponseClubMembers;
import com.project.questaidbackend.repository.ClubDepartmentRepository;
import com.project.questaidbackend.services.interfaces.IClubDepartmentService;
import com.project.questaidbackend.services.interfaces.IClubService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ClubDepartmentServicesImpl implements IClubDepartmentService {

    private ClubDepartmentRepository clubDepartmentRepository;
    private IClubService clubService;

    @Override
    public ClubDepartment getClubDepartment(Long id) {
        Optional<ClubDepartment> clubDepartment = clubDepartmentRepository.findById(id);
        return unwrapClubDepartment(clubDepartment, id);
    }

    @Override
    public ClubDepartment createDepartment(ClubDepartment department, Long clubId) {
        Club club = clubService.getClub(clubId);
        department.setClub(club);
        return clubDepartmentRepository.save(department);
    }

    @Override
    public List<ResponseClubMembers> getDepartmentMembers(Long departmentId) {
        List<ClubMember> clubMemberList = getClubDepartment(departmentId).getClubMemberList();
        List<ResponseClubMembers> responseClubMembers = new ArrayList<>();
        for(ClubMember clubMember : clubMemberList) {
            responseClubMembers.add(
                    new ResponseClubMembers(
                            clubMember.getStudent().getFirstName(),
                            clubMember.getStudent().getLastName(),
                            clubMember.getStudent().getEmail(),
                            clubMember.getStudent().getPhone(),
                            clubMember.getClubMemberRoles().toString(),
                            clubMember.getClubDepartment().getDepartmentName()
                    )
            );
        }
        return responseClubMembers;
    }



    static ClubDepartment unwrapClubDepartment(Optional<ClubDepartment> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, ClubDepartment.class);
    }
}
