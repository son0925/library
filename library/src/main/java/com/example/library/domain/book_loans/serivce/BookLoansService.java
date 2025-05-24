package com.example.library.domain.book_loans.serivce;

import com.example.library.domain.book_loans.repository.BookLoansRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookLoansService {

    private final BookLoansRepository bookLoansRepository;

}
