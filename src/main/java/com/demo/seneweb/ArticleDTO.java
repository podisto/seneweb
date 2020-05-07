package com.demo.seneweb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Le modele que les clients (web, desktop et mobile) devront fournir pour créer un article
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {
    public String title;
    public String author;
    public String content;
    public boolean status;
    public LocalDate created;
}
