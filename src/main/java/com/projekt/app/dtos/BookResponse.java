package com.projekt.app.dtos;

import lombok.Data;

/**
 * DTO for returning book details.
 * This class is used to encapsulate the data returned when fetching book details.
 */
@Data
public class BookResponse {
  private Long id;
  private String title;
  private String author;
  private String isbn;
  private Integer publishedYear;
}
