package com.projekt.app.dtos;

import lombok.Data;

/**
 * DTO for creating a new book.
 * This class is used to encapsulate the data required to create a new book.
 */
@Data
public class BookRequest {
  private String title;
  private String author;
  private String isbn;
  private Integer publishedYear;
}
