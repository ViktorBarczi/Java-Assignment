package com.projekt.app.controllers;

import java.time.LocalDate;
import java.util.List;
import com.projekt.app.dtos.BookCopyAvaliability;
import com.projekt.app.dtos.BookRequest;
import com.projekt.app.dtos.BookResponse;
import com.projekt.app.dtos.BookUpdate;
import com.projekt.app.entities.Book;
import com.projekt.app.entities.BookCopy;
import com.projekt.app.exceptions.BadRequestException;
import com.projekt.app.services.BookService;
import com.projekt.app.utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for handling book-related API operations.
 * Provides endpoints to manage books and their copies,
 * including creating, retrieving, updating, and deleting books.
 * Also allows adding and managing copies of books.
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

  @Autowired
  private BookService bookService;

  /**
   * Get a list of all books.
   */
  @GetMapping
  public List<BookResponse> getAllBooks() {
    return bookService.getAllBooks();
  }

  /**
   * Add a new book.
   * Validates required fields and checks ISBN and published year.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void postBook(@RequestBody BookRequest bookRequest) {
    // Basic field validations
    if (bookRequest.getTitle() == null || bookRequest.getTitle().isEmpty()) {
      throw new BadRequestException("Book title cannot be null or empty.");
    }
    if (bookRequest.getAuthor() == null || bookRequest.getAuthor().isEmpty()) {
      throw new BadRequestException("Book author cannot be null or empty.");
    }
    if (bookRequest.getIsbn() == null || bookRequest.getIsbn().isEmpty()) {
      throw new BadRequestException("Book ISBN cannot be null or empty.");
    }
    if (bookRequest.getPublishedYear() == null) {
      throw new BadRequestException("Book publishedYear cannot be null.");
    }
    if (bookRequest.getPublishedYear() > LocalDate.now().getYear()) {
      throw new BadRequestException("Published year cannot be in the future.");
    }
    if (bookRequest.getPublishedYear() <= 0) {
      throw new BadRequestException("Published year must be a positive number.");
    }
    // ISBN validation using utility method
    if (!Utilities.isValidIsbn13(bookRequest.getIsbn())) {
      throw new BadRequestException("Invalid ISBN number.");
    }

    // Save book to the database
    this.bookService.saveBook(bookRequest);
  }

  /**
   * Get a single book by ID.
   */
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.FOUND)
  public Book getBook(@PathVariable Long id) {
    return bookService.getBookById(id);
  }

  /**
   * Update a book by ID.
   * Validates that title and year are provided.
   */
  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.FOUND)
  public Book putBook(@PathVariable Long id, @RequestBody BookUpdate bookUpdate) {
    if (bookUpdate.getTitle() == null || bookUpdate.getTitle().isEmpty()) {
      throw new BadRequestException("Book title cannot be null or empty.");
    }
    if (bookUpdate.getPublishedYear() == null) {
      throw new BadRequestException("Book publishedYear cannot be null.");
    }
    return bookService.updateBook(id, bookUpdate);
  }

  /**
   * Delete a book by ID, including all its copies.
   */
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.GONE)
  public void deleteBook(@PathVariable Long id) {
    bookService.deleteBook(id);
  }

  /**
   * Get all copies for a specific book.
   */
  @GetMapping("/{id}/copies")
  @ResponseStatus(HttpStatus.FOUND)
  public List<BookCopy> getBookCopies(@PathVariable Long id) {
    return bookService.getBookCopies(id);
  }

  /**
   * Add a new copy for a specific book.
   */
  @PostMapping("/{id}/copies")
  @ResponseStatus(HttpStatus.CREATED)
  public BookCopy postBookCopy(@PathVariable Long id) {
    return bookService.postBookCopy(id);
  }

  /**
   * Update the availability of a specific book copy.
   */
  @PutMapping("/{id}/copies/{copyId}")
  @ResponseStatus(HttpStatus.FOUND)
  public BookCopy putMethodName(@PathVariable Long id, @PathVariable Long copyId,
      @RequestBody BookCopyAvaliability avaliability) {
    return bookService.updateBookCopy(id, copyId, avaliability);
  }
}
