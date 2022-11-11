package com.mustache.hospitaldocker.repository;

import com.mustache.hospitaldocker.domain.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
}