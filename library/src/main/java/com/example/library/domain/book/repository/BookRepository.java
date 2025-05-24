package com.example.library.domain.book.repository;

import com.example.library.domain.book.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {
}
