package com.ace.bookstore.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.ElementCollection;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@ToString
public class BookDto implements Serializable {
    private Integer id;
    private String title;
    private String author;
    private double price;
    private String description;
    private String imageUrl;
    private List<String> comments = new ArrayList<>();
    private LocalDate publishedDate;

    public BookDto(){}

    public BookDto(Integer id, String title, String author, double price, String description, String imageUrl, List<String> comments, LocalDate publishedDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.comments = comments;
        this.publishedDate = publishedDate;
    }
}
