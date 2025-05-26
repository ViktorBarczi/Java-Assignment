package com.projekt.app.dtos;

import lombok.Data;

/**
 * DTO for updating book details.
 * This class is used to encapsulate the data required to update an existing book.
 */
@Data
public class BookUpdate {
  private String title;
  private Integer publishedYear;
}
