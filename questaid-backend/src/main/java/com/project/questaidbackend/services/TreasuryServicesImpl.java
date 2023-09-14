package com.project.questaidbackend.services;

import com.project.questaidbackend.exceptions.EntityNotFoundException;
import com.project.questaidbackend.models.Club;
import com.project.questaidbackend.models.Treasury;
import com.project.questaidbackend.repository.TreasuryRepository;
import com.project.questaidbackend.services.interfaces.ITreasuryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TreasuryServicesImpl implements ITreasuryService {

    private TreasuryRepository treasuryRepository;

    @Override
    public Treasury createTreasuryForClub(Club club) {
        Treasury treasury = new Treasury();
        treasury.setClub(club);
        return treasuryRepository.save(treasury);
    }

    @Override
    public Treasury getTreasuryById(Long id) {
        return unwrapTreasury(treasuryRepository.findById(id), id);
    }

    @Override
    public Treasury getTreasuryByClubId(Long clubId) {
        return unwrapTreasury(treasuryRepository.findByClubId(clubId), clubId);
    }

    private static Treasury unwrapTreasury(Optional<Treasury> treasury, Long id) {
        if(treasury.isPresent()) return treasury.get();
        else throw new EntityNotFoundException(id, Treasury.class);
    }

}
