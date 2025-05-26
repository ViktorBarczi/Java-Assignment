package com.projekt.app.utils;

public class Utilities {
  public static boolean isValidIsbn13(String isbn) {
    // Remove hyphens if any
    isbn = isbn.replace("-", "");

    // Must be 13 digits and only digits
    if (isbn.length() != 13 || !isbn.matches("\\d{13}")) {
      return false;
    }

    // Validate check digit
    int sum = 0;
    for (int i = 0; i < 12; i++) {
      int digit = Character.getNumericValue(isbn.charAt(i));
      sum += (i % 2 == 0) ? digit : digit * 3;
    }

    int expectedCheckDigit = (10 - (sum % 10)) % 10;
    int actualCheckDigit = Character.getNumericValue(isbn.charAt(12));

    return expectedCheckDigit == actualCheckDigit;
  }

}
