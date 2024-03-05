package com.letsdoit.TeamFinder.services;
import com.letsdoit.TeamFinder.domain.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.letsdoit.TeamFinder.repositories.OrganizationRepository;

@Service
public class OrganizationService {

    // This class is a service that will be used to interact with the database

    private final OrganizationRepository organizationRepository;

    @Autowired
    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }
    // This method will be used to create a new organization

    public Organization createOrganization(Organization organization) {
        return organizationRepository.save(organization);
    }
    // This method will be used to return a organization

    public Organization getOrganization(Integer id) {
        return organizationRepository.findById(id).orElse(null);
    }
    // This method will be used to delete a organization
    public void deleteOrganization(Integer id) {
        organizationRepository.deleteById(id);
    }
}
