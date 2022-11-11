package com.mustache.hospitaldocker.repository;

import com.mustache.hospitaldocker.domain.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
