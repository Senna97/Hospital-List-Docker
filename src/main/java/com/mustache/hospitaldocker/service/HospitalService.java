package com.mustache.hospitaldocker.service;

import com.mustache.hospitaldocker.domain.dto.HospitalResponse;
import com.mustache.hospitaldocker.domain.entity.Hospital;
import com.mustache.hospitaldocker.repository.HospitalRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public HospitalResponse getHospital(Integer id) {
        Optional<Hospital> optHospital = hospitalRepository.findById(id);
        Hospital hospital = optHospital.get();
        HospitalResponse hospitalResponse = Hospital.of(hospital);

        switch (hospital.getBusinessStatusCode()) {
            case 13:
                hospitalResponse.setBusinessStatusName("영업중");
                break;
            case 24:
                hospitalResponse.setBusinessStatusName("직권폐업");
                break;
            case 3:
                hospitalResponse.setBusinessStatusName("폐업");
                break;
            case 2:
                hospitalResponse.setBusinessStatusName("휴업");
                break;
        }

        return hospitalResponse;
    }
}
