package com.demo.librarymanagement.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public class BookInputDto {

    private String isbnNumber;
    private String title;
    private String author;

    @JsonCreator
    public BookInputDto(String isbnNumber, String title, String author) {
        this.isbnNumber = isbnNumber;
        this.title = title;
        this.author = author;
    }
}
