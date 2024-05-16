package com.demo.librarymanagement.controller;

import com.demo.librarymanagement.dto.BookBorrowDto;
import com.demo.librarymanagement.service.BookBorrowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/borrow")
public class BookBorrowController {

    private final BookBorrowService bookBorrowService;

    public BookBorrowController(BookBorrowService bookBorrowService) {
        this.bookBorrowService = bookBorrowService;
    }

    @PostMapping
    public ResponseEntity<String> borrowBook(@RequestBody BookBorrowDto bookBorrowDto) {
        return ResponseEntity.ok(bookBorrowService.borrowBook(bookBorrowDto));
    }

    @PostMapping("/return")
    public ResponseEntity<String> returnBook(@RequestBody BookBorrowDto bookBorrowDto) {
        return ResponseEntity.ok(bookBorrowService.returnBook(bookBorrowDto));
    }
}
