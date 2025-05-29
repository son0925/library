package com.example.library.domain.book_loans.controller;

import com.example.library.domain.auth.model.CustomUserDetails;
import com.example.library.domain.book_loans.model.BookLoansRequest;
import com.example.library.domain.book_loans.model.BookLoansResponse;
import com.example.library.domain.book_loans.serivce.BookLoansService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book-loans")
public class BookLoansController {

    private final BookLoansService bookLoansService;

    // 책 대출
    @PostMapping
    public BookLoansResponse bookLoans(
            @RequestBody BookLoansRequest request
    ) {
        return bookLoansService.bookLoans(request.getUserId(), request.getBookId());
    }

    // 책 반납
    @PutMapping("/{bookId}")
    public ResponseEntity<BookLoansResponse> returnBook(
            @PathVariable Integer bookId
    ) {
        return bookLoansService.returnBook(bookId);
    }
}
