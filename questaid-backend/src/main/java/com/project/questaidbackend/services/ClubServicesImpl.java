package com.project.questaidbackend.services;

import com.project.questaidbackend.exceptions.EntityNotFoundException;
import com.project.questaidbackend.models.Club;
import com.project.questaidbackend.repository.ClubRepository;
import com.project.questaidbackend.services.interfaces.IClubService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ClubServicesImpl implements IClubService {

    private ClubRepository clubRepository;

    @Override
    public Club getClub(Long id) {
        Optional<Club> club = clubRepository.findById(id);
        return unwrapClub(club, id);
    }

    @Override
    public Club getClubByEmail(String email) {
        Optional<Club> club = clubRepository.findByEmail(email);
        return unwrapClub(club, 404L);
    }

    static Club unwrapClub(Optional<Club> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, Club.class);
    }
}
