package com.ll.sbtstudy;

import com.ll.sbtstudy.Entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article,Long> {


}
