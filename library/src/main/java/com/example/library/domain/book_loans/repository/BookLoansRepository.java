package com.example.library.domain.book_loans.repository;

import com.example.library.domain.book.model.BookEntity;
import com.example.library.domain.book_loans.model.BookLoansEntity;
import com.example.library.domain.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookLoansRepository extends JpaRepository<BookLoansEntity, Integer> {

    long countByUserAndEndAtIsNull(UserEntity user);

    boolean existsByBookAndEndAtIsNull(BookEntity book);

    List<BookLoansEntity> findAllByBook_TitleAndBook_AuthorAndEndAtIsNull(String title, String author);

    Optional<BookLoansEntity> findByBookAndEndAtIsNull(BookEntity book);

    List<BookLoansEntity> findAllByUser(UserEntity user);

    List<BookLoansEntity> findAllByBook(BookEntity book);
}
