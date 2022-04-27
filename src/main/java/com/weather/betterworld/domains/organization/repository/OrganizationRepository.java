package com.weather.betterworld.domains.organization.repository;

import com.weather.betterworld.domains.organization.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
