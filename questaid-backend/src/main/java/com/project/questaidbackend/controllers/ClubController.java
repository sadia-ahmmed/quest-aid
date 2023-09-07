package com.project.questaidbackend.controllers;

import com.project.questaidbackend.models.Club;
import com.project.questaidbackend.services.interfaces.IClubService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/club")
public class ClubController {

    private IClubService clubService;

    @GetMapping("/public/{id}")
    public ResponseEntity<Club> getClubDetails(@PathVariable Long id) {
        return new ResponseEntity<>(clubService.getClub(id), HttpStatus.OK);
    }

}
