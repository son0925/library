package com.example.library.domain.book.model;

import java.time.LocalDate;

public record BookSummaryResponse(
        String title,
        String author,
        String publisher,
        Integer price,
        LocalDate publicationAt
) {
}
