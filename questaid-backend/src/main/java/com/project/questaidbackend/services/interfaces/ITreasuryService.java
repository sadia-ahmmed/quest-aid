package com.project.questaidbackend.services.interfaces;

import com.project.questaidbackend.models.Club;
import com.project.questaidbackend.models.Treasury;

public interface ITreasuryService {
    /**
     * Create a treasury for a club
     * @param club the club object
     * @return Treasury object
     */
    Treasury createTreasuryForClub(Club club);

    /**
     * Get the treasury object by unique id
     * @param id the unique id of the treasury
     * @return Treasury object
     */
    Treasury getTreasuryById(Long id);

    /**
     * Get the treasury object by club id
     * @param clubId the unique id of the club
     * @return Treasury object
     */
    Treasury getTreasuryByClubId(Long clubId);
}
