package com.demo.librarymanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "borrow_books")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookBorrow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long bookId;

    private long borrowerId;

    private LocalDate borrowedDate;

    private LocalDate returnedDate;

    @Builder.Default
    private boolean returned = false;
}
