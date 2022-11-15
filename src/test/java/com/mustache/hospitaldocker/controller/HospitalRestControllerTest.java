package com.mustache.hospitaldocker.controller;

import com.mustache.hospitaldocker.domain.dto.HospitalResponse;
import com.mustache.hospitaldocker.service.HospitalService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HospitalRestController.class)
class HospitalRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean // @Autowired 아님 -> HospitalService 는 테스트를 위해 가짜 객체를 쓰겠다는 뜻
    HospitalService hospitalService; // 가짜 객체를 사용하면 좋은점: DB와 상관없이 테스트가 가능하다.

    @Test
    @DisplayName("JSON 형태로 1개의 Response 가 잘 오는지") // 비즈니스 로직이 아닌 Controller 만 검증 -> Service 는 검증하지 않음
    void jsonResponse() throws Exception {
        HospitalResponse hospitalResponse = HospitalResponse.builder()
                .id(2321)
                .roadNameAddress("서울특별시 서초구 서초중앙로 230, 202호 (반포동, 동화반포프라자빌딩)")
                .hospitalName("노소아청소년과의원")
                .patientRoomCount(0)
                .totalNumberOfBeds(0)
                .businessTypeName("의원")
                .totalAreaSize(0.0f)
                .businessStatusName("영업중")
                .build();
        given(hospitalService.getHospital(2321)).willReturn(hospitalResponse);

        // 앞에서 Autowired 한 mockMvc
        int hospitalId = 2321;
        String url = String.format("/api/v1/hospitals/%d", hospitalId);
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.hospitalName").exists()) // $는 루트, $ 아래에 hospitalName 이 있어야 함
                .andExpect(jsonPath("$.hospitalName").value("노소아청소년과의원"))
                .andDo(print()); // http request, response 내역을 출력해라

        verify(hospitalService).getHospital(hospitalId); // getHospital() 메서드의 호출이 있었는지 확인
    }
}