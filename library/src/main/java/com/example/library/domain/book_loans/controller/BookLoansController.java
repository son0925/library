package com.example.library.domain.book_loans.controller;

import com.example.library.domain.auth.model.CustomUserDetails;
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
    @PostMapping("/{id}")
    public BookLoansResponse bookLoans(
            @PathVariable Integer id,
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        return bookLoansService.bookLoans(user.getUsername(), id);
    }

    // 책 반납
    @PutMapping("/{bookLoansId}")
    public ResponseEntity<BookLoansResponse> returnBook(
            @PathVariable Integer bookLoansId,
            @AuthenticationPrincipal CustomUserDetails customUserDetails
    ) {
        return bookLoansService.returnBook(bookLoansId, customUserDetails.getUsername());
    }

}
