package com.project.questaidbackend.repository;

import com.project.questaidbackend.models.FundRequest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FundRequestRepository extends CrudRepository<FundRequest, Long> {
    List<FundRequest> findByRequesterId(Long id);
    List<FundRequest> findByRequestedToId(Long id);
    List<FundRequest> findByRequestedToIdAndApproved(Long id, boolean status);
    List<FundRequest> findByApproved(boolean status);
}
