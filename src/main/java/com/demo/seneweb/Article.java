package com.demo.seneweb;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Modele qui sera enregistré en base de données
 */
@Entity
@Table(name = "articles")
@Data
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "auteur")
    private String author;

    @Column(name = "contenu")
    private String content;

    @Column(name = "etat")
    private boolean status;

    @Column(name = "date_creation")
    private LocalDate created;

    public Article() {
        this.created = LocalDate.now();
    }
}
