package com.project.questaidbackend.controllers;

import com.project.questaidbackend.models.Club;
import com.project.questaidbackend.services.interfaces.IClubService;
import com.project.questaidbackend.services.interfaces.ITaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/club")
public class ClubController {

    private IClubService clubService;
    private ITaskService taskService;

    @GetMapping("/public/{id}")
    public ResponseEntity<Club> getClubDetails(@PathVariable Long id) {
        return new ResponseEntity<>(clubService.getClubById(id), HttpStatus.OK);
    }

    @GetMapping("/public/search/{clubName}")
    public ResponseEntity<Club> getClubByName(@PathVariable String name){
        return new ResponseEntity<>(clubService.getClubByName(name), HttpStatus.OK);
    }

    @GetMapping("/productivity/{clubId}")
    public ResponseEntity<Map> getProductivityOfClub(@PathVariable Long clubId){
        Map<String, Double> jsonMap = new HashMap<>();
        jsonMap.put("clubProductivity", taskService.getProductivityByClub(clubId));
        return new ResponseEntity<>(jsonMap, HttpStatus.OK);
    }

}
