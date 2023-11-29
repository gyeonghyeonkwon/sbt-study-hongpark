package com.ll.sbtstudydto;

import com.ll.sbtstudy.Entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleForm {

    private Long id; //번호
    private String title; //제목을 받을 필드
    private  String content; //내용을 받을 필드

    public Article toEntity() {
        return new Article(id,title,content);
    }
}

