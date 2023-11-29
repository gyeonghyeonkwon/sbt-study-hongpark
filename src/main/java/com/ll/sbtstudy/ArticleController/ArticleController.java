package com.ll.sbtstudy.ArticleController;

import com.ll.sbtstudy.ArticleRepository;
import com.ll.sbtstudy.Entity.Article;
import com.ll.sbtstudydto.ArticleForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/")
    public String ArticlesRedirect() {

        return "redirect:/articles";
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
        return "redirect:/articles/" + saved.getId();

    }
    @GetMapping("/articles/{id}")
    public String show (@PathVariable Long id, Model model) {

        log.info ("id =" +id);
        //1. id 를 조회해 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //2. 모델에 데이터 등록하기
        model.addAttribute("article",articleEntity);
        //3. 뷰 페이지 반환하기
       return "articles/show";
    }
    @GetMapping("/articles")
    public String index (Model model) {
        //1. 모든 데이터 가져오기
       List<Article> articleEntityList =  articleRepository.findAll();
        //2. 모델에 데이터 등록하기
        model.addAttribute("articleList",articleEntityList);
        //3. 뷰 페이지 설정하기
        return "articles/index";
    }
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable("id") Long id , Model model) {
       //1. 수정 할 데이터 데이터베이스에서 가져오기.
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //2.모델에 데이터 등록하기
        model.addAttribute("article" , articleEntity);
        //3. 뷰페이지 반환
        return "articles/edit";
    }
    @PostMapping("/article/update")
    public String update(ArticleForm form) {
        log.info(form.toString());
        //1. DTO를 엔티티로 변환하기
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());
        //2.엔티티를 DB에 저장하기
        //2-1 DB 에서 기존 데이터 가져오기
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);

        if(null !=target) {
            articleRepository.save(articleEntity);
        }
        //3.수정 결과 페이지로 리다이렉트 하기
        return "redirect:/articles/%s".formatted(articleEntity.getId());
    }

}
