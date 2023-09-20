package com.project.questaidbackend.services;

import com.project.questaidbackend.exceptions.EntityNotFoundException;
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
import java.util.Optional;

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
        return fundRequestRepository.findByRequestedToIdAndApproved(id, false);
    }

    @Override
    public List<FundRequest> getFundByStatus(boolean status) {
        return fundRequestRepository.findByApproved(status);
    }

    @Override
    public void changeFundStatus(Long fundId, boolean status) {
        FundRequest fundRequest = unwrapFundRequest(fundRequestRepository.findById(fundId), fundId);
        fundRequest.setApproved(status);
        fundRequestRepository.save(fundRequest);
    }

    private static FundRequest unwrapFundRequest(Optional<FundRequest> fundRequest, Long id) {
        if(fundRequest.isPresent()) return fundRequest.get();
        else throw new EntityNotFoundException(id, FundRequest.class);
    }
}
