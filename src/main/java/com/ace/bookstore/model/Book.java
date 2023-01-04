package com.ace.bookstore.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String author;
    private double price;
    private String description;
    private String imageUrl;
    @ElementCollection
    private List<String> comments = new ArrayList<>();
    private LocalDate publishedDate;
    public Book(){}
    public Book(String title, String author, double price, String description, String imageUrl, List<String> comments, LocalDate publishedDate) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.comments = comments;
        this.publishedDate = publishedDate;
    }


}
