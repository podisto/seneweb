package com.demo.seneweb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class ArticleController {

    @Autowired
    private ArticleRepository repository;

    /**
     * Ajouter un article quand on appelle http://localhost:8080/api/v1/articles
     * https://apiseneweb/api/v1/articles = endpoint (point d'entrée)
     * @param request
     * @return
     */
    @RequestMapping(value = "/articles", method = RequestMethod.POST)
    public ResponseEntity<String> create(@RequestBody ArticleRequest request) {
        log.info("donnees envoyees = {} ", request);

        Article article = new Article();
        article.setAuthor(request.getAuthor());
        article.setContent(request.getContent());
        article.setStatus(request.isStatus());
        final Article createdArticle = repository.save(article);
        log.info("Article Créé = {} ", createdArticle);
        return ResponseEntity.ok(createdArticle.getId().toString());
    }
}
