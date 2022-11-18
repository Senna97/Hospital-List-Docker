package com.mustache.hospitaldocker.repository;

import com.mustache.hospitaldocker.domain.entity.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
    List<Hospital> findByBusinessTypeNameIn(List<String> businessType);

    List<Hospital> findByRoadNameAddressContaining(String roadNameAddress);

    List<Hospital> findByTotalNumberOfBedsBetweenOrderByTotalNumberOfBedsDesc(int a, int b);

    List<Hospital> findByRoadNameAddressContainingAndBusinessTypeNameContaining(String roadNameAddress, String businessType);

    Page<Hospital> findByRoadNameAddressContaining(String keyword, Pageable pageable);
}