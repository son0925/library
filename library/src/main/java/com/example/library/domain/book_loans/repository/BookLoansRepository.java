package com.example.library.domain.book_loans.repository;

import com.example.library.domain.book_loans.model.BookLoansEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookLoansRepository extends JpaRepository<BookLoansEntity, Integer> {
}
