package com.example.library.domain.book_loans.serivce;

import com.example.library.common.base.BaseAbstractService;
import com.example.library.common.exception.BadRequestException;
import com.example.library.config.BookLoansConfig;
import com.example.library.domain.book.model.BookEntity;
import com.example.library.domain.book.service.BookService;
import com.example.library.domain.book_loans.converter.BookLoansConverter;
import com.example.library.domain.book_loans.model.BookLoansEntity;
import com.example.library.domain.book_loans.model.BookLoansRequest;
import com.example.library.domain.book_loans.model.BookLoansResponse;
import com.example.library.domain.book_loans.repository.BookLoansRepository;
import com.example.library.domain.user.model.UserEntity;
import com.example.library.domain.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class BookLoansService extends BaseAbstractService<
        BookLoansEntity,
        BookLoansRequest,
        BookLoansResponse,
        BookLoansRepository,
        BookLoansConverter> {

    private final BookLoansRepository bookLoansRepository;
    private final UserService userService;
    private final BookService bookService;

    private final BookLoansConfig bookLoansConfig;

    protected BookLoansService(BookLoansRepository repository,
                               BookLoansConverter converter,
                               UserService userService,
                               BookService bookService,
                               BookLoansConfig bookLoansConfig) {
        super(repository, converter);
        this.userService = userService;
        this.bookService = bookService;
        this.bookLoansRepository = repository;
        this.bookLoansConfig = bookLoansConfig;
    }

    // 책 대출
    @Transactional
    public BookLoansResponse bookLoans(String phone, Integer bookId) {
        UserEntity user = userService.findByPhoneWithThrow(phone);
        BookEntity book = bookService.findByIdWithThrow(bookId);

        // 대출 할 수 없을 때 Exception
        throwIfCannotLoans(user, book);

        // 책 대출하기
        BookLoansEntity bookLoans = converter.toEntity(user, book);
        BookLoansEntity newEntity = repository.save(bookLoans);

        return converter.toResponse(newEntity);
    }

    // 책 반납하기
    @Transactional
    public ResponseEntity<BookLoansResponse> returnBook(Integer bookLoansId, String phone) {
        BookLoansEntity bookLoans = findByIdWithThrow(bookLoansId);

        // 현재 빌리고 있는 소유자가 아닌 경우
        throwIfNotOwner(bookLoans, phone);

        // 책 반납하기
        bookLoans.returnBook(LocalDateTime.now());

        // 반납 기간이 지났을 때 정지 시키기
        if (isOverdue(bookLoans)) {
            bookLoans.getUser().setSuspended();
            return ResponseEntity.status(207)
                    .body(converter.toResponse(bookLoans))
                    ;
        }

        return ResponseEntity.ok(converter.toResponse(bookLoans));
    }

    // 대출 기간을 지났는지
    private boolean isOverdue(BookLoansEntity bookLoans) {
        return bookLoans.getStartAt().plusDays(bookLoansConfig.getLoanPeriodDays()).isAfter(bookLoans.getEndAt());
    }

    // 현재 빌리고 있는 소유자가 아닌 경우
    private void throwIfNotOwner(BookLoansEntity bookLoans, String phone) {
        if (!bookLoans.getUser().getPhone().equals(phone)) {
            throw new BadRequestException();
        }
    }

    // 대출 불가능 할 때 Exception
    private void throwIfCannotLoans(UserEntity user, BookEntity book) {
        userService.throwIfSuspended(user); // 대출이 불가능한 상태일 때
        throwIfOverLoanLimit(user); // 대출할 수 있는 책 개수 초과일 때
        throwIfHasAlreadyBorrowed(book); // 이미 해당 책을 빌렸는지(본인, 타인)
    }

    // 이미 해당 책을 빌렸을 때
    private void throwIfHasAlreadyBorrowed(BookEntity book) {
        if (repository.existsByBookAndEndAtIsNull(book)) {
            throw new BadRequestException();
        }
    }

    // 대출 할 수 있는 책을 초과했을 때
    private void throwIfOverLoanLimit(UserEntity user) {
        if (countBookLoans(user) > bookLoansConfig.getMaxBooksPerUser()) {
            throw new BadRequestException();
        }
    }

    // 빌리고 있는 책 개수
    private long countBookLoans(UserEntity user) {
        return bookLoansRepository.countByUserAndEndAtIsNull(user);
    }

}
