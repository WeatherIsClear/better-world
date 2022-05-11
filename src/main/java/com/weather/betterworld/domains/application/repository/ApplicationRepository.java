package com.weather.betterworld.domains.application.repository;

import com.weather.betterworld.domains.application.domain.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
