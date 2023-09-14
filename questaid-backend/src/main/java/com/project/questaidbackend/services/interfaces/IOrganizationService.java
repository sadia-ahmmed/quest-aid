package com.project.questaidbackend.services.interfaces;

import com.project.questaidbackend.models.Organization;

public interface IOrganizationService {
    Organization getOrganizationByEmail(String email);
    Organization getOrganizationById(Long id);
}
