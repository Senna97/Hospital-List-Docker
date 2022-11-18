package com.mustache.hospitaldocker.service;

import com.mustache.hospitaldocker.domain.dto.HospitalResponse;
import com.mustache.hospitaldocker.domain.entity.Hospital;
import com.mustache.hospitaldocker.repository.HospitalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class HospitalServiceTest {
    private final HospitalRepository hospitalRepository = Mockito.mock(HospitalRepository.class);

    private HospitalService hospitalService;

    @BeforeEach
    void setUp() {
        hospitalService = new HospitalService(hospitalRepository);
    }

    @Test
    @DisplayName("상태코드가 3이면 폐업")
    void businessStatusCode3() {
        Hospital hospital = Hospital.builder()
                .id(113169)
                .businessStatusCode(3)
                .build();

        Mockito.when(hospitalRepository.findById(any()))
                .thenReturn(Optional.of(hospital));

        HospitalResponse hospitalResponse = hospitalService.getHospital(113169);

        assertEquals("폐업", hospitalResponse.getBusinessStatusName());

    }

    @Test
    @DisplayName("상태코드가 13이면 영업중")
    void businessStatusCode13() {
        Hospital hospital = Hospital.builder()
                .id(1)
                .businessStatusCode(13)
                .build();

        Mockito.when(hospitalRepository.findById(any()))
                .thenReturn(Optional.of(hospital));

        HospitalResponse hospitalResponse = hospitalService.getHospital(1);

        assertEquals("영업중", hospitalResponse.getBusinessStatusName());
    }
}