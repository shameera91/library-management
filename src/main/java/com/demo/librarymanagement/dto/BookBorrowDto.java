package com.demo.librarymanagement.dto;

import lombok.Getter;

@Getter
public class BookBorrowDto {

    private long bookId;
    private long borrowerId;

    public BookBorrowDto(long bookId, long borrowerId) {
        this.bookId = bookId;
        this.borrowerId = borrowerId;
    }
}
