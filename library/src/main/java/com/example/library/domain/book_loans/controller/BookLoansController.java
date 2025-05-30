package com.example.library.domain.book_loans.controller;

import com.example.library.domain.book_loans.model.BookLoansInfoResponse;
import com.example.library.domain.book_loans.model.BookLoansRequest;
import com.example.library.domain.book_loans.model.BookLoansResponse;
import com.example.library.domain.book_loans.serivce.BookLoansService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // 책을 빌린 유저 리스트
    @GetMapping("/book/{bookId}")
    public List<BookLoansInfoResponse> getBookHistoryList(
            @PathVariable Integer bookId
    ) {
        return bookLoansService.getBookHistoryList(bookId);
    }

    // 유저가 빌린 책 리스트
    @GetMapping("/user/{userId}")
    public List<BookLoansInfoResponse> getUserHistoryList(
            @PathVariable Integer userId
    ) {
        return bookLoansService.getUserHistoryList(userId);
    }
}
