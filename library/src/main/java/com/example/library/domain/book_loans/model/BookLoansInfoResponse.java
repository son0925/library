package com.example.library.domain.book_loans.model;

import com.example.library.domain.book.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookLoansInfoResponse {

    private String title;

    private Category category;

    private Integer userId;

    private String userName;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    private String bookImageUrl;

}
