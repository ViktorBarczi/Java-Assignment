package com.projekt.app.services;

import java.util.List;
import com.projekt.app.dtos.BookCopyAvaliability;
import com.projekt.app.dtos.BookRequest;
import com.projekt.app.dtos.BookResponse;
import com.projekt.app.dtos.BookUpdate;
import com.projekt.app.entities.Book;
import com.projekt.app.entities.BookCopy;

/**
 * Interface for Book Service.
 * Provides methods for managing books and their copies.
 * Includes methods to retrieve, save, update, and delete books,
 * as well as manage book copies.
 */
public interface IBookService {

  public List<BookResponse> getAllBooks();

  public Book getBookById(Long id);

  public Book saveBook(BookRequest BookRequest) throws IllegalArgumentException;

  public void deleteBook(Long id);

  public Book updateBook(Long id, BookUpdate bookUpdate);

  public BookCopy postBookCopy(Long bookId);

  public List<BookCopy> getBookCopies(Long bookId);

  public BookCopy updateBookCopy(Long bookId, Long BookCopyId,
      BookCopyAvaliability bookCopyAvaliability);



}
