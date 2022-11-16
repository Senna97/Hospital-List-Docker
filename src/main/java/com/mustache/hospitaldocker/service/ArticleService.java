package com.mustache.hospitaldocker.service;

import com.mustache.hospitaldocker.repository.ArticleRepository;

public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }
}
