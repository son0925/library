package com.example.library.domain.book.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {

    private String title;

    private String author;

    private String publisher;

    private LocalDate publicationAt;

    private int price;

    private String imageUrl;

    private Category category;

    private String description;

}
