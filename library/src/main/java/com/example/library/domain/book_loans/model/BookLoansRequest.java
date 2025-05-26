package com.example.library.domain.book_loans.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookLoansRequest {

    private Integer userId;

    private Integer bookId;

}
