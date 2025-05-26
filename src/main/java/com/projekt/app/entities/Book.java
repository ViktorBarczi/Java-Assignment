package com.projekt.app.entities;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

/**
 * Entity representing a book in the library system.
 * This class contains details about the book such as title, author, ISBN,
 * published year, and a list of copies available in the library.
 * It is used to persist book information in the database.
 */
@Entity
@Data
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false, unique = true)
  private String title;

  @Column(nullable = false)
  private String author;

  @Column(nullable = false, unique = true)
  private String isbn;

  @Column(nullable = false)
  private Integer publishedYear;

  @OneToMany(mappedBy = "bookId")
  private List<BookCopy> copies;

  public Book(String title, String author, String isbn, int publicationYear) {
    this.title = title;
    this.author = author;
    this.isbn = isbn;
    this.publishedYear = publicationYear;
    this.copies = new ArrayList();
  }

  public Book() {
    this.copies = new ArrayList();
  }
}
