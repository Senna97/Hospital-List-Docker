package com.mustache.hospitaldocker.controller;

import com.mustache.hospitaldocker.domain.dto.ArticleDto;
import com.mustache.hospitaldocker.service.ArticleService;
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

@WebMvcTest(ArticleRestController.class)
class ArticleRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ArticleService articleService;

    @Test
    @DisplayName("한 개의 게시글을 조회하는 기능")
    void view() throws Exception {
        Long id = 1L;

        given(articleService.getArticle(id)).willReturn(new ArticleDto(1L, "first title", "first content ~ Blah Blah Blah"));

        mockMvc.perform(get("/api/v1/articles/1"))
                .andExpect(jsonPath("$.title").value("first title"))
                .andExpect(jsonPath("$.content").value("first content ~ Blah Blah Blah"))
                .andDo(print());

        verify(articleService).getArticle(id);
    }
}