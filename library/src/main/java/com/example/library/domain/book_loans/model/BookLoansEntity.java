package com.example.library.domain.book_loans.model;

import com.example.library.common.base.BaseEntity;
import com.example.library.domain.book.model.BookEntity;
import com.example.library.domain.user.model.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "book_loans")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookLoansEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookEntity book;

    @Column(nullable = false)
    private LocalDateTime startAt; // 대출일

    private LocalDateTime endAt; // 반납일(대출 중에는 null)

    public void returnBook(LocalDateTime now) {
        this.endAt = now;
    }
}
