package com.demo.librarymanagement.dto;

import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BookOutputDto {

    private long id;
    private String isbn;
    private String title;
    private String author;
}
