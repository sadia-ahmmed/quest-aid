package com.project.questaidbackend.services;

import com.project.questaidbackend.exceptions.EntityNotFoundException;
import com.project.questaidbackend.models.Club;
import com.project.questaidbackend.models.Organization;
import com.project.questaidbackend.repository.OrganizationRepository;
import com.project.questaidbackend.services.interfaces.IOrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements IOrganizationService {

    private OrganizationRepository organizationRepository;

    @Override
    public Organization getOrganizationByEmail(String email) {
        return unwrapOrganization(organizationRepository.findByEmail(email), 404L);
    }

    @Override
    public Organization getOrganizationById(Long id) {
        return unwrapOrganization(organizationRepository.findById(id), id);
    }

    private static Organization unwrapOrganization(Optional<Organization> organization, Long id) {
        if(organization.isPresent()) return organization.get();
        else throw new EntityNotFoundException(id, Organization.class);
    }
}
