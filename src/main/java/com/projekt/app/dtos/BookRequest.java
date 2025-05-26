package com.projekt.app.dtos;

import lombok.Data;

@Data
public class BookRequest {
  private String title;
  private String author;
  private String isbn;
  private Integer publishedYear;
}
