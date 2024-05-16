package com.demo.librarymanagement.controller;

import com.demo.librarymanagement.dto.BookInputDto;
import com.demo.librarymanagement.dto.BookOutputDto;
import com.demo.librarymanagement.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<String> addBook(@RequestBody BookInputDto bookInputDto) {
        return ResponseEntity.ok(bookService.addNewBook(bookInputDto));

    }

    @GetMapping
    public ResponseEntity<List<BookOutputDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }
}
