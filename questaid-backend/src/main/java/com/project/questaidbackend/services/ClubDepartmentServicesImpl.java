package com.project.questaidbackend.services;

import com.project.questaidbackend.exceptions.EntityNotFoundException;
import com.project.questaidbackend.models.Club;
import com.project.questaidbackend.models.ClubDepartment;
import com.project.questaidbackend.models.ClubMember;
import com.project.questaidbackend.models.base.ResponseClubMember;
import com.project.questaidbackend.repository.ClubDepartmentRepository;
import com.project.questaidbackend.services.interfaces.IClubDepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ClubDepartmentServicesImpl implements IClubDepartmentService {

    private ClubDepartmentRepository clubDepartmentRepository;

    @Override
    public ClubDepartment getClubDepartment(Long id) {
        Optional<ClubDepartment> clubDepartment = clubDepartmentRepository.findById(id);
        return unwrapClubDepartment(clubDepartment, id);
    }

    @Override
    public List<ClubDepartment> getDepartmentsByClubId(Long clubId) {
        return clubDepartmentRepository.findByClubId(clubId);
    }

    @Override
    public ClubDepartment addDepartment(ClubDepartment department, Club club) {
        department.setClub(club);
        return clubDepartmentRepository.save(department);
    }

    @Override
    public List<ResponseClubMember> getDepartmentMembers(Long departmentId) {
        List<ClubMember> clubMemberList = getClubDepartment(departmentId).getClubMemberList();
        return clubMemberList.stream().map(ResponseClubMember::new)
                .collect(Collectors.toList());
    }

    static ClubDepartment unwrapClubDepartment(Optional<ClubDepartment> entity, Long id) {
        if (entity.isPresent())
            return entity.get();
        else
            throw new EntityNotFoundException(id, ClubDepartment.class);
    }
}
