package com.mustache.hospitaldocker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mustache.hospitaldocker.domain.dto.ArticleAddRequest;
import com.mustache.hospitaldocker.domain.dto.ArticleAddResponse;
import com.mustache.hospitaldocker.domain.dto.ArticleDto;
import com.mustache.hospitaldocker.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticleRestController.class)
class ArticleRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ArticleService articleService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("한 개의 게시글 조회 기능")
    void view() throws Exception {
        Long id = 1L;

        given(articleService.getArticle(id)).willReturn(new ArticleDto(1L, "first title", "first content ~ Blah Blah Blah"));

        mockMvc.perform(get("/api/v1/articles/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").value("first title"))
                .andExpect(jsonPath("$.content").value("first content ~ Blah Blah Blah"))
                .andDo(print());

        verify(articleService).getArticle(id);
    }

    @Test
    @DisplayName("글 등록 기능")
    void add() throws Exception {
        ArticleAddRequest dto = new ArticleAddRequest("first title", "first content ~ Blah Blah Blah");

        given(articleService.add(any())).willReturn(new ArticleAddResponse(1L, dto.getTitle(), dto.getContent()));

        mockMvc.perform(post("/api/v1/articles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").value("first title"))
                .andExpect(jsonPath("$.content").value("first content ~ Blah Blah Blah"))
                .andDo(print());
    }
}