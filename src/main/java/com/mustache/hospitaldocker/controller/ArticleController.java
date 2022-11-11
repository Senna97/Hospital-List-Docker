package com.mustache.hospitaldocker.controller;

import com.mustache.hospitaldocker.domain.dto.ArticleDto;
import com.mustache.hospitaldocker.domain.entity.Article;
import com.mustache.hospitaldocker.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/articles")
@Slf4j
public class ArticleController {

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/new")
    public String createPage() {
        return "new";
    }

    @PostMapping("")
    public String createArticle(ArticleDto articleDto) {
        Article savedArticle = articleRepository.save(articleDto.toEntity());
        return String.format("redirect:/articles/%d", savedArticle.getId());
    }

    @GetMapping("/{id}")
    public String showOne(@PathVariable Long id, Model model) {
        Optional<Article> optionalArticle = articleRepository.findById(id);

        if (optionalArticle.isPresent()) {
            model.addAttribute("article", optionalArticle.get());
            return "show";
        } else {
            return "error";
        }
    }

    @GetMapping("/list")
    public String listPage(Model model) {
        List<Article> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);
        return "list";
    }

    @GetMapping("{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Optional<Article> optionalArticle = articleRepository.findById(id);

        if (optionalArticle.isPresent()) {
            model.addAttribute("article", optionalArticle.get());
            return "edit";
        } else {
            model.addAttribute("message", String.format("There is no article %d,", id));
            return "error";
        }
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, ArticleDto articleDto, Model model) {
        log.info("title: {} content: {}", articleDto.getTitle(), articleDto.getContent());
        Article article = articleRepository.save(articleDto.toEntity());
        model.addAttribute("article", article);
        return String.format("redirect:/articles/%d", article.getId());
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, Model model) {
        articleRepository.deleteById(id);
        model.addAttribute("message", String.format("No. %d has been deleted.", id));
        return "redirect:/articles/list";
    }
}

