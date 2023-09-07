package com.project.questaidbackend.repository;

import com.project.questaidbackend.models.Club;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ClubRepository extends CrudRepository<Club, Long> {
//    @Transactional
//    @Modifying
//    @Query("UPDATE club c SET c.club_name = :name AND c.club_logo_path = :logo_path WHERE c.id = :id")
//    void updateClub(@Param(value = "name") String name, @Param(value = "logo_path") String logoPath, @Param(value = "id") Long id);
}
