package com.demo.librarymanagement.service;

import com.demo.librarymanagement.dto.BorrowerInputDto;
import com.demo.librarymanagement.model.Borrower;
import com.demo.librarymanagement.repository.BorrowerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BorrowerService {

    private final BorrowerRepository borrowerRepository;

    public BorrowerService(BorrowerRepository borrowerRepository) {
        this.borrowerRepository = borrowerRepository;
    }

    public String addBorrower(BorrowerInputDto borrowerInputDto) {

        Borrower borrower = Borrower.builder().name(borrowerInputDto.getName())
                .email(borrowerInputDto.getEmail()).build();

        Borrower savedBorrower = borrowerRepository.save(borrower);
        log.info("Borrower {} saved successfully", savedBorrower.getId());
        return "Borrower saved successfully";
    }
}
