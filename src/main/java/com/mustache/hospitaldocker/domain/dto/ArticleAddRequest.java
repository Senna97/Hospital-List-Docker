package com.mustache.hospitaldocker.domain.dto;

import com.mustache.hospitaldocker.domain.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ArticleAddRequest {
    private String title;
    private String content;

    public Article toEntity() {
        Article article = Article.builder().
                title(this.title)
                .title(this.content)
                .build();
        return article;
    }
}
