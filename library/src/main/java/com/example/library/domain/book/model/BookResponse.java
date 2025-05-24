package com.example.library.domain.book.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponse {

    private String title;

    private String author;

    private String description;

    private String imageUrl;

    private int price;

    private LocalDateTime publicationAt;

    private Category category;

}
