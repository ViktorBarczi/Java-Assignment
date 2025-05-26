package com.projekt.app.dtos;

import lombok.Data;

@Data
public class BookResponse {
  private Long id;
  private String title;
  private String author;
  private String isbn;
  private Integer publishedYear;
}
