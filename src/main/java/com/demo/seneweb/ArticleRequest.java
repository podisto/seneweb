package com.demo.seneweb;

import lombok.Data;

/**
 * Le modele que les clients (web, desktop et mobile) devront fournir pour créer un article
 */
@Data
public class ArticleRequest {
    public String author;
    public String content;
    public boolean status;
}
