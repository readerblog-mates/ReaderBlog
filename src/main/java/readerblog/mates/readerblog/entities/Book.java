package com.geekbrains.Readerbook.entities;


import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "pages")
    private Integer pages;

    @Column(name = "format")
    private String format;

    @Column(name = "year_of_writing")
    private Integer yearOfWriting;

    @Column(name = "origin_language")
    private String originLanguage;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "rating")
    private Double rating;

    @ManyToOne()
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne()
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "authors_books",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;


}