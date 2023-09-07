package com.project.questaidbackend.services.interfaces;

import com.project.questaidbackend.models.Club;

public interface IClubService {
    Club getClub(Long id);
    Club getClubByEmail(String email);
}
