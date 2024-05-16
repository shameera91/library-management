package com.demo.librarymanagement.service;

import com.demo.librarymanagement.dto.BookInputDto;
import com.demo.librarymanagement.dto.BookOutputDto;
import com.demo.librarymanagement.exception.BookException;
import com.demo.librarymanagement.model.Book;
import com.demo.librarymanagement.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public String addNewBook(BookInputDto bookInputDto) {

        Optional<Book> byIsbn = bookRepository.findFirstByIsbn(bookInputDto.getIsbnNumber());
        if (byIsbn.isPresent()) {
            Book book = byIsbn.get();
            //same isbn found
            if (!(book.getAuthor().equals(bookInputDto.getAuthor()) && book.getTitle().equals(bookInputDto.getTitle()))) {

                log.error("Author and title should be same");
                throw new BookException("Author and title should be same");
            }
        }

        Book book = Book.builder().title(bookInputDto.getTitle()).isbn(bookInputDto.getIsbnNumber())
                .title(bookInputDto.getTitle()).author(bookInputDto.getAuthor()).build();

        Book savedBook = bookRepository.save(book);
        log.info("Book {} saved successfully", savedBook.getId());
        return "Book saved Successfully";
    }

    public List<BookOutputDto> getAllBooks() {
        return bookRepository.findAll().stream().map(book -> BookOutputDto.builder()
                .id(book.getId()).author(book.getAuthor()).isbn(book.getIsbn())
                .title(book.getTitle()).build()).toList();
    }
}
