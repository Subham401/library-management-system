package com.lms.librarymanagementsystem.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class BookRequest {

    @NotBlank
    public String title;

    @NotBlank
    public String author;

    @NotBlank
    public String isbn;

    @Min(1)
    public int totalCopies;
}
