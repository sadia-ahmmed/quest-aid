package com.project.questaidbackend.services.interfaces;

import com.project.questaidbackend.models.Club;
import com.project.questaidbackend.models.ClubDepartment;

public interface IClubService {
    Club createClub(Club club);
    void removeClub(Club club);
    void modifyClub(Club club);
    Club getClub(Long id);
    Club getClubByEmail(String email);
    ClubDepartment addDepartment(ClubDepartment department, Long clubId);
}
