package com.letsdoit.TeamFinder.repositories;

import com.letsdoit.TeamFinder.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Integer> {
    Optional<Organization> findByOrganizationName(String organizationName);
    Optional<Organization> findByHqAddress(String HQAddress);
}
