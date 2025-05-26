package com.projekt.app.services;

import java.util.List;
import com.projekt.app.dtos.BookCopyAvaliability;
import com.projekt.app.dtos.BookRequest;
import com.projekt.app.dtos.BookResponse;
import com.projekt.app.dtos.BookUpdate;
import com.projekt.app.entities.Book;
import com.projekt.app.entities.BookCopy;

public interface IBookService {

  public List<BookResponse> getAllBooks();

  public Book getBookById(Long id);

  public Book getBookByTitle(String title);

  public Book saveBook(BookRequest BookRequest) throws IllegalArgumentException;

  public void deleteBook(Long id);

  public void deleteBookByTitle(String title);

  public Book updateBook(Long id, BookUpdate bookUpdate);

  public BookCopy postBookCopy(Long bookId);

  public List<BookCopy> getBookCopies(Long bookId);

  public BookCopy updateBookCopy(Long bookId, Long BookCopyId,
      BookCopyAvaliability bookCopyAvaliability);



}
