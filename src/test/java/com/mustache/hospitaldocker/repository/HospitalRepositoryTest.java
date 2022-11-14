package com.mustache.hospitaldocker.repository;

import com.mustache.hospitaldocker.domain.entity.Hospital;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HospitalRepositoryTest {

    @Autowired
    HospitalRepository hospitalRepository;

    void printHospitalNameAndAddress(List<Hospital> hospitals) {
        for (Hospital hospital : hospitals) {
            System.out.printf("%s | %s\n", hospital.getHospitalName(), hospital.getRoadNameAddress());
        }
    }

    void printHospitalNameAndAddressAndBeds(List<Hospital> hospitals) {
        for (Hospital hospital : hospitals) {
            System.out.printf("%s | %s | %d\n", hospital.getHospitalName(), hospital.getRoadNameAddress(), hospital.getTotalNumberOfBeds());
        }
    }

    @Test
    void findById() {
        Optional<Hospital> hospital = hospitalRepository.findById(1);
        Hospital hp = hospital.get();
        System.out.println(hp.getId());
        assertEquals(1, hp.getId());
    }

    @Test
    @DisplayName("BusinessTypeNamedl 보건소, 보건지소, 보건진료소인 데이터가 잘 파싱되는지")
    void findByBusinessTypeNameIn() {
        List<String> inClues = new ArrayList<>();
        inClues.add("보건소");
        inClues.add("보건지소");
        inClues.add("보건진료소");
        List<Hospital> hospitals = hospitalRepository.findByBusinessTypeNameIn(inClues);

        for (Hospital hospital : hospitals) {
            System.out.println(hospital.getHospitalName());
        }
    }

    @Test
    void containing() {
        List<Hospital> hospitals = hospitalRepository.findByRoadNameAddressContaining("서대문구");
        printHospitalNameAndAddress(hospitals);
    }

    @Test
    void countBeds() {
        List<Hospital> hospitals = hospitalRepository.findByTotalNumberOfBedsBetweenOrderByTotalNumberOfBedsDesc(10, 19);
        printHospitalNameAndAddressAndBeds(hospitals);
    }

    @Test
    void findAddressAndName() {
        List<Hospital> hospitals = hospitalRepository.findByRoadNameAddressContainingAndBusinessTypeNameContaining("서대문구", "치과");
        printHospitalNameAndAddress(hospitals);
    }
}