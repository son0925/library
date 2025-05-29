package com.example.library.domain.book.model;

import com.example.library.common.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "book")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BookEntity extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description; // 책 줄거리 요약

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String publisher; // 출판사

    @Column(nullable = false)
    private LocalDate publicationAt; // 출간년도

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private int price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;


}
