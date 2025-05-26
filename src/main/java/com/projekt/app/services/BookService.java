package com.projekt.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.projekt.app.dtos.BookCopyAvaliability;
import com.projekt.app.dtos.BookRequest;
import com.projekt.app.dtos.BookResponse;
import com.projekt.app.dtos.BookUpdate;
import com.projekt.app.entities.Book;
import com.projekt.app.entities.BookCopy;
import com.projekt.app.repositories.IBookCopyRepository;
import com.projekt.app.repositories.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService implements IBookService {

  @Autowired
  private IBookRepository bookRepository;
  @Autowired
  private IBookCopyRepository bookCopyRepository;

  //Get all books from the database
  public List<BookResponse> getAllBooks() {
    List<Book> list = bookRepository.findAll();
    if (list.isEmpty()) {
      return new ArrayList<>();
    }
    return list.stream().map(book -> {
      BookResponse bookResponse = new BookResponse();
      bookResponse.setId(book.getId());
      bookResponse.setTitle(book.getTitle());
      bookResponse.setAuthor(book.getAuthor());
      bookResponse.setIsbn(book.getIsbn());
      bookResponse.setPublishedYear(book.getPublishedYear());
      return bookResponse;
    }).toList();
  }

  //Get book by id from the database
  public Book getBookById(Long id) {
    Book book = bookRepository.findById(id).orElse(null);
    if (book == null) {
      throw new IllegalArgumentException("Book with id '" + id + "' does not exist.");
    }
    return book;
  }

  //Save a book to the database
  public Book saveBook(BookRequest BookRequest) throws IllegalArgumentException {
    if (BookRequest.getTitle() == null || BookRequest.getTitle().isEmpty()) {
      throw new IllegalArgumentException("Book title cannot be null or empty.");
    }
    if (BookRequest.getAuthor() == null || BookRequest.getAuthor().isEmpty()) {
      throw new IllegalArgumentException("Book author cannot be null or empty.");
    }
    if (BookRequest.getIsbn() == null || BookRequest.getIsbn().isEmpty()) {
      throw new IllegalArgumentException("Book ISBN cannot be null or empty.");
    }
    if (!bookRepository.findByTitle(BookRequest.getTitle()).isEmpty()) {
      throw new IllegalArgumentException(
          "Book with title '" + BookRequest.getTitle() + "' already exists.");
    }
    Book book = new Book();
    book.setTitle(BookRequest.getTitle());
    book.setAuthor(BookRequest.getAuthor());
    book.setIsbn(BookRequest.getIsbn());
    book.setPublishedYear(BookRequest.getPublishedYear());
    return bookRepository.save(book);
  }


  public void deleteBook(Long id) {
    Optional<Book> book = bookRepository.findById(id);
    if (book != null) {
      for (BookCopy bookcopy : book.get().getCopies()) {
        bookCopyRepository.delete(bookcopy);
      }
      bookRepository.delete(book.get());
    } else {
      throw new IllegalArgumentException("Book with id '" + id + "' does not exist.");
    }
  }

  public void deleteBookByTitle(String title) {
    Optional<Book> book = bookRepository.findByTitle(title);
    if (book != null) {
      for (BookCopy bookcopy : book.get().getCopies()) {
        bookCopyRepository.delete(bookcopy);
      }
      bookRepository.delete(book.get());
    } else {
      throw new IllegalArgumentException("Book with title '" + title + "' does not exist.");
    }
  }

  public Book updateBook(Long id, BookUpdate bookUpdate) {
    Book existingBook = bookRepository.findById(id).orElse(null);
    if (existingBook != null) {
      existingBook.setTitle(bookUpdate.getTitle());
      existingBook.setPublishedYear(bookUpdate.getPublishedYear());
      return bookRepository.save(existingBook);
    } else {
      throw new IllegalArgumentException("Book with id '" + id + "' does not exist.");
    }
  }

  public BookCopy postBookCopy(Long bookId) {
    BookCopy bookCopy = new BookCopy();
    Book book = bookRepository.findById(bookId).orElse(null);
    if (book == null) {
      throw new IllegalArgumentException("Book with id '" + bookId + "' does not exist.");
    }
    bookCopy.setBookId(book);
    book.getCopies().add(bookCopy);
    bookRepository.save(book);
    return bookCopyRepository.save(bookCopy);
  }

  public List<BookCopy> getBookCopies(Long bookId) {
    Book book = bookRepository.findById(bookId).orElse(null);
    if (book == null) {
      throw new IllegalArgumentException("Book with id '" + bookId + "' does not exist.");
    }
    return book.getCopies();
  }

  public BookCopy updateBookCopy(Long bookId, Long BookCopyId,
      BookCopyAvaliability bookCopyAvaliability) {
    Book book = bookRepository.findById(bookId).orElse(null);
    if (book == null) {
      throw new IllegalArgumentException("Book with id '" + bookId + "' does not exist.");
    }
    List<BookCopy> bookCopies = book.getCopies();
    for (BookCopy bookCopy : bookCopies) {
      if (bookCopy.getId().equals(BookCopyId)) {
        bookCopy.setAvailable(bookCopyAvaliability.isAvailable());
        bookRepository.save(book);
        return bookCopyRepository.save(bookCopy);
      }
    }
    throw new IllegalArgumentException("Book copy with id '" + BookCopyId + "' does not exist.");

  }

}
