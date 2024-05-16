package com.demo.librarymanagement.controller;

import com.demo.librarymanagement.dto.BorrowerInputDto;
import com.demo.librarymanagement.service.BorrowerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/borrower")
public class BorrowerController {

    private final BorrowerService borrowerService;

    public BorrowerController(BorrowerService borrowerService) {
        this.borrowerService = borrowerService;
    }

    @PostMapping
    public ResponseEntity<String> addBorrower(@RequestBody BorrowerInputDto borrowerInputDto){
        return ResponseEntity.ok(borrowerService.addBorrower(borrowerInputDto));
    }
}
