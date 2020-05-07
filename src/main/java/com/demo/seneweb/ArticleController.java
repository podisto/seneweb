package com.demo.seneweb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<String> create(@RequestBody ArticleDTO request) {
        log.info("donnees envoyees = {} ", request);

        Article article = new Article();
        article.setTitle(request.getTitle());
        article.setAuthor(request.getAuthor());
        article.setContent(request.getContent());
        article.setStatus(request.isStatus());
        final Article createdArticle = repository.save(article);
        log.info("Article Créé = {} ", createdArticle);
        return ResponseEntity.ok(createdArticle.getId().toString());
    }

    /**
     * Exposer un endpoint qui permet de récuperer tous les articles au niveau de la bdd
     */
    @RequestMapping(value = "/liste-articles", method = RequestMethod.GET)
    public ResponseEntity<List<ArticleDTO>> getArticles() {
        final List<Article> listArticles = repository.findAll();
        final List<ArticleDTO> articles = listArticles.stream()
                .map(item -> new ArticleDTO(item.getTitle(), item.getAuthor(), item.getContent(), item.isStatus(), item.getCreated()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/liste-articles/{title}")
    public ResponseEntity<ArticleDTO> getArticle(@PathVariable String title) {
        log.info("recuperer article dont le titre est {} ", title);
        final Article article = repository.findByTitle(title);
        ArticleDTO dto = new ArticleDTO(article.getTitle(), article.getAuthor(), article.getContent(), article.isStatus(), article.getCreated());
        return ResponseEntity.ok(dto);
    }
}
