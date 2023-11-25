package com.ll.sbtstudy.ArticleController;

import com.ll.sbtstudy.ArticleRepository;
import com.ll.sbtstudy.Entity.Article;
import com.ll.sbtstudydto.ArticleForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/")
    public String ArticlesRedirect() {

        return "redirect:/articles/new";
    }

    @GetMapping("/articles/new")
    public String newArticleForm() {

        return "articles/new";
    }


    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {

        log.info(form.toString());


        //1. DTO를 엔티티로 변환
        Article article = form.toEntity();

        log.info(article.toString());

        //2.리파지터리로 엔티티를 DB에저장
        Article saved = articleRepository.save(article);

        log.info(saved.toString());
        return "";
    }


}
