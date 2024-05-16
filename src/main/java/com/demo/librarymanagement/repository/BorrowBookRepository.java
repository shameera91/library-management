package com.demo.librarymanagement.repository;

import com.demo.librarymanagement.model.Book;
import com.demo.librarymanagement.model.BookBorrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface BorrowBookRepository extends JpaRepository<BookBorrow,Long> {

    Optional<BookBorrow> findByBookIdAndBorrowerIdAndReturned(long bookId,long borrowerId,boolean returned);

    @Modifying
    @Query("UPDATE BookBorrow b SET b.returned = ?2,b.returnedDate = ?3 WHERE b.id =?1")
    int updateReturnedBook(long id, boolean returned, LocalDate returnedDate);
}
