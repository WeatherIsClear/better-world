package com.weather.betterworld.domains.member.repository;

import com.weather.betterworld.domains.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
