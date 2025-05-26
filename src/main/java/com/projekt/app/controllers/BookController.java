package com.projekt.app.controllers;

import java.util.List;
import com.projekt.app.dtos.BookCopyAvaliability;
import com.projekt.app.dtos.BookRequest;
import com.projekt.app.dtos.BookResponse;
import com.projekt.app.dtos.BookUpdate;
import com.projekt.app.entities.Book;
import com.projekt.app.entities.BookCopy;
import com.projekt.app.services.BookService;
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



@RestController
@RequestMapping("/api/books")
public class BookController {
  // This class will handle book-related requests
  // Add methods to handle CRUD operations for books here

  @Autowired
  private BookService bookService;

  @GetMapping
  public List<BookResponse> getAllBooks() {
    return bookService.getAllBooks();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void postBook(@RequestBody BookRequest bookRequest) {
    this.bookService.saveBook(bookRequest);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.FOUND)
  public Book getBook(@PathVariable Long id) {
    return bookService.getBookById(id);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.FOUND)
  public Book putBook(@PathVariable Long id, @RequestBody BookUpdate bookUpdate) {
    return bookService.updateBook(id, bookUpdate);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.GONE)
  public void deleteBook(@PathVariable Long id) {
    bookService.deleteBook(id);
  }

  @GetMapping("/{id}/copies")
  @ResponseStatus(HttpStatus.FOUND)
  public List<BookCopy> getBookCopies(@PathVariable Long id) {
    return bookService.getBookCopies(id);
  }

  @PostMapping("/{id}/copies")
  @ResponseStatus(HttpStatus.CREATED)
  public BookCopy postBookCopy(@PathVariable Long id) {
    return bookService.postBookCopy(id);
  }

  @PutMapping("/{id}/copies/{copyId}")
  @ResponseStatus(HttpStatus.FOUND)
  public BookCopy putMethodName(@PathVariable Long id, @PathVariable Long copyId,
      @RequestBody BookCopyAvaliability avaliability) {
    return bookService.updateBookCopy(id, copyId, avaliability);
  }

}
