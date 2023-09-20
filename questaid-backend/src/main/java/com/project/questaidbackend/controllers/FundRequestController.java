package com.project.questaidbackend.controllers;

import com.project.questaidbackend.models.FundRequest;
import com.project.questaidbackend.services.interfaces.IFundRequestService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/funds")
public class FundRequestController {

    private IFundRequestService fundRequestService;

    @PostMapping("/add/{clubId}/{adminId}")
    public ResponseEntity<FundRequest> addFundRequest(@Valid @RequestBody FundRequest fundRequest, @PathVariable Long clubId, @PathVariable Long adminId) {
        return new ResponseEntity<>(fundRequestService.addFundRequest(fundRequest, clubId, adminId), HttpStatus.CREATED);
    }

    @GetMapping("/get/all/{clubId}/club")
    public ResponseEntity<List<FundRequest>> getAllFundRequestsByClubId(@PathVariable Long clubId) {
        return new ResponseEntity<>(fundRequestService.getAllFundRequestsByClubId(clubId), HttpStatus.OK);
    }

    @GetMapping("/get/all/{adminId}/admin")
    public ResponseEntity<List<FundRequest>> getAllFundRequestsByAdminId(@PathVariable Long adminId) {
        return new ResponseEntity<>(fundRequestService.getAllFundRequestsByAdminId(adminId), HttpStatus.OK);
    }

    @GetMapping("/get/all/{status}/approved")
    public ResponseEntity<List<FundRequest>> getFundByStatus(boolean status) {
        return new ResponseEntity<>(fundRequestService.getFundByStatus(status), HttpStatus.OK);
    }

}
