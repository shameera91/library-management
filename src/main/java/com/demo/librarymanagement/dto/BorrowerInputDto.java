package com.demo.librarymanagement.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public class BorrowerInputDto {

    private String name;
    private String email;

    @JsonCreator
    public BorrowerInputDto(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
