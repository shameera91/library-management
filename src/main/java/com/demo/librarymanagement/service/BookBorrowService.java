package com.demo.librarymanagement.service;

import com.demo.librarymanagement.dto.BookBorrowDto;
import com.demo.librarymanagement.exception.BookException;
import com.demo.librarymanagement.exception.ResourceNotFoundException;
import com.demo.librarymanagement.model.Book;
import com.demo.librarymanagement.model.BookBorrow;
import com.demo.librarymanagement.model.Borrower;
import com.demo.librarymanagement.repository.BookRepository;
import com.demo.librarymanagement.repository.BorrowBookRepository;
import com.demo.librarymanagement.repository.BorrowerRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@Service
public class BookBorrowService {

    private final BookRepository bookRepository;
    private final BorrowerRepository borrowerRepository;

    private final BorrowBookRepository borrowBookRepository;

    public BookBorrowService(BookRepository bookRepository,
                             BorrowerRepository borrowerRepository,
                             BorrowBookRepository borrowBookRepository) {
        this.bookRepository = bookRepository;
        this.borrowerRepository = borrowerRepository;
        this.borrowBookRepository = borrowBookRepository;
    }

    public String borrowBook(BookBorrowDto bookBorrowDto) {

        Book book = bookRepository.findById(bookBorrowDto.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found for id " + bookBorrowDto.getBookId()));

        Borrower borrower = borrowerRepository.findById(bookBorrowDto.getBorrowerId())
                .orElseThrow(() -> new ResourceNotFoundException("Borrower not found for id " + bookBorrowDto.getBorrowerId()));

        Optional<BookBorrow> bookBorrowedResult = borrowBookRepository
                .findByBookIdAndBorrowerIdAndReturned(bookBorrowDto.getBookId(), bookBorrowDto.getBorrowerId(), false);

        if (bookBorrowedResult.isPresent()) {
            //book already borrowed
            throw new BookException("Book Already Borrowed");

        }

        BookBorrow bookBorrow = BookBorrow.builder().bookId(book.getId())
                .borrowerId(borrower.getId()).borrowedDate(LocalDate.now()).build();

        BookBorrow borrowedResult = borrowBookRepository.save(bookBorrow);

        log.info("Book {} borrowed successfully", borrowedResult.getBookId());

        //Need to pass the proper dto as a response
        return "Book Borrowed Successfully";
    }

    @Transactional
    public String returnBook(BookBorrowDto bookBorrowDto) {

        Book book = bookRepository.findById(bookBorrowDto.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found for id " + bookBorrowDto.getBookId()));

        Borrower borrower = borrowerRepository.findById(bookBorrowDto.getBorrowerId())
                .orElseThrow(() -> new ResourceNotFoundException("Borrower not found for id " + bookBorrowDto.getBorrowerId()));

        Optional<BookBorrow> bookBorrowedResult = borrowBookRepository
                .findByBookIdAndBorrowerIdAndReturned(bookBorrowDto.getBookId(), bookBorrowDto.getBorrowerId(), false);

        if (bookBorrowedResult.isEmpty()) {
            throw new ResourceNotFoundException("No Book to Return");
        }

        //update table after returning book
        BookBorrow bookBorrow = bookBorrowedResult.get();
        borrowBookRepository.updateReturnedBook(bookBorrow.getId(), true, LocalDate.now());

        log.info("Book {} returned successfully", bookBorrow.getBookId());
        return "Book Returned Successfully";
    }
}
