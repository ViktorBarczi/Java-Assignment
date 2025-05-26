package com.projekt.app.dtos;

import lombok.Data;

/**
 * DTO for changing the availability status of a book copy.
 * This class is used to encapsulate the availability status of a book copy.
 */
@Data
public class BookCopyAvaliability {
  private boolean isAvailable;
}
