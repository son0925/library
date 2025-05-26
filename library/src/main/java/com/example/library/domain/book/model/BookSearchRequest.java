package com.example.library.domain.book.model;

public record BookSearchRequest(
        String title,
        String author,
        String publisher,
        Integer year,
        Category category,
        Integer maximum,
        Integer minimum,
        String sortBy,
        Boolean asc
) {}
