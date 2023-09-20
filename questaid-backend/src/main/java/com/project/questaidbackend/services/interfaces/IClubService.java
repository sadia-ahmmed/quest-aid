package com.project.questaidbackend.services.interfaces;

import com.project.questaidbackend.models.Admin;
import com.project.questaidbackend.models.Club;
import com.project.questaidbackend.models.ClubDepartment;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IClubService {
    Club getClubByName(String name);
    Club createClub(Club club, MultipartFile file, Admin admin);
    void removeClub(Club club);
    void modifyClub(Club club);
    Club getClubById(Long id);
    Club getClubByEmail(String email);
    ClubDepartment addDepartment(ClubDepartment department, Long clubId);
    List<Club> getAllClubsUnderAdminId(Long id);
}
