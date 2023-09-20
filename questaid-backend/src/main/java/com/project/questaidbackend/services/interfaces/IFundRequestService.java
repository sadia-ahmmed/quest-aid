package com.project.questaidbackend.services.interfaces;

import com.project.questaidbackend.models.FundRequest;

import java.util.List;

public interface IFundRequestService {
    /**
     * Add fund request
     * @param fundRequest
     * @param clubId
     * @param adminId
     * @return
     */
    FundRequest addFundRequest(FundRequest fundRequest, Long clubId, Long adminId);

    /**
     * Get all fund requests by club id
     * @param id
     * @return
     */
    List<FundRequest> getAllFundRequestsByClubId(Long id);

    /**
     * Get all fund requests by admin id
     * @param id
     * @return
     */
    List<FundRequest> getAllFundRequestsByAdminId(Long id);

    /**
     * Get all fund by status (true/false)
     * @param status
     * @return
     */
    List<FundRequest> getFundByStatus(boolean status);

    /**
     * Change the status of the fund
     * @param status
     * @return
     */
    void changeFundStatus(Long fundId, boolean status);
}
