package com.project.questaidbackend.services;

import com.project.questaidbackend.models.Admin;
import com.project.questaidbackend.models.Club;
import com.project.questaidbackend.models.FundRequest;
import com.project.questaidbackend.repository.FundRequestRepository;
import com.project.questaidbackend.services.interfaces.IAdminService;
import com.project.questaidbackend.services.interfaces.IClubService;
import com.project.questaidbackend.services.interfaces.IFundRequestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FundRequestServiceImpl implements IFundRequestService {

    private FundRequestRepository fundRequestRepository;

    private IClubService clubService;
    private IAdminService adminService;

    @Override
    public FundRequest addFundRequest(FundRequest fundRequest, Long clubId, Long adminId) {
        Club club = clubService.getClubById(clubId);
        Admin admin = adminService.getAdminById(adminId);

        fundRequest.setRequester(club);
        fundRequest.setRequestedTo(admin);

        return fundRequestRepository.save(fundRequest);
    }

    @Override
    public List<FundRequest> getAllFundRequestsByClubId(Long id) {
        return fundRequestRepository.findByRequesterId(id);
    }

    @Override
    public List<FundRequest> getAllFundRequestsByAdminId(Long id) {
        return fundRequestRepository.findByRequestedToId(id);
    }

    @Override
    public List<FundRequest> getFundByStatus(boolean status) {
        return fundRequestRepository.findByApproved(status);
    }
}
