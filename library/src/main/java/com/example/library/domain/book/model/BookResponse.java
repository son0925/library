package com.example.library.domain.book.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

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

    private String publisher;

    private LocalDate publicationAt;

    private Category category;

    private int totalCount;

    private int availableCount;

    private List<LocalDate> returnDateList;

}
