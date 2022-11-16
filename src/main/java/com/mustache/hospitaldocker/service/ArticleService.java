package com.mustache.hospitaldocker.service;

import com.mustache.hospitaldocker.domain.dto.ArticleDto;
import com.mustache.hospitaldocker.domain.entity.Article;
import com.mustache.hospitaldocker.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ArticleDto getArticle(Long id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        Article article = optionalArticle.get();
        ArticleDto articleDto = Article.of(article);

        return articleDto;
    }
}
