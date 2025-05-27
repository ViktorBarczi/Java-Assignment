package com.projekt.app.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.projekt.app.dtos.BookCopyAvaliability;
import com.projekt.app.dtos.BookRequest;
import com.projekt.app.dtos.BookResponse;
import com.projekt.app.dtos.BookUpdate;
import com.projekt.app.entities.Book;
import com.projekt.app.entities.BookCopy;
import com.projekt.app.exceptions.BadRequestException;
import com.projekt.app.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BookControllerTests {

  @Mock
  private BookService bookService;

  @InjectMocks
  private BookController bookController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getAllBooks_returnsBookList() {
    List<BookResponse> books = Arrays.asList(new BookResponse());
    when(bookService.getAllBooks()).thenReturn(books);

    List<BookResponse> result = bookController.getAllBooks();

    assertEquals(1, result.size());
    verify(bookService, times(1)).getAllBooks();
  }

  @Test
  void postBook_validInput_callsService() {
    BookRequest request = new BookRequest();
    request.setPublishedYear(2020);
    request.setTitle("Title");
    request.setAuthor("Author");
    request.setIsbn("9783161484100");
    when(bookService.saveBook(request)).thenReturn(new Book());

    assertDoesNotThrow(() -> bookController.postBook(request));
    verify(bookService, times(1)).saveBook(request);
  }

  @Test
  void postBook_invalidISBN_throwsBadRequestException() {
    BookRequest request = new BookRequest();
    request.setTitle("Title");
    request.setAuthor("Author");
    request.setIsbn("invalid-isbn");
    request.setPublishedYear(2020);

    Exception exception =
        assertThrows(BadRequestException.class, () -> bookController.postBook(request));
    assertTrue(exception.getMessage().contains("Invalid ISBN number"));
  }

  @Test
  void getBook_validId_returnsBook() {
    Book mockBook = new Book();
    when(bookService.getBookById(1L)).thenReturn(mockBook);

    Book result = bookController.getBook(1L);
    assertEquals(mockBook, result);
  }

  @Test
  void putBook_validUpdate_callsService() {
    BookUpdate update = new BookUpdate();
    update.setTitle("Updated Title");
    update.setPublishedYear(2021);

    Book updatedBook = new Book();
    when(bookService.updateBook(1L, update)).thenReturn(updatedBook);

    Book result = bookController.putBook(1L, update);
    assertEquals(updatedBook, result);
  }

  @Test
  void deleteBook_callsService() {
    assertDoesNotThrow(() -> bookController.deleteBook(1L));
    verify(bookService, times(1)).deleteBook(1L);
  }

  @Test
  void getBookCopies_returnsList() {
    List<BookCopy> copies = Collections.singletonList(new BookCopy());
    when(bookService.getBookCopies(1L)).thenReturn(copies);

    List<BookCopy> result = bookController.getBookCopies(1L);
    assertEquals(1, result.size());
  }

  @Test
  void postBookCopy_returnsBookCopy() {
    BookCopy copy = new BookCopy();
    when(bookService.postBookCopy(1L)).thenReturn(copy);

    BookCopy result = bookController.postBookCopy(1L);
    assertEquals(copy, result);
  }

  @Test
  void updateBookCopy_returnsUpdatedCopy() {
    BookCopy copy = new BookCopy();
    BookCopyAvaliability availability = new BookCopyAvaliability();
    availability.setAvailable(true);
    when(bookService.updateBookCopy(1L, 2L, availability)).thenReturn(copy);

    BookCopy result = bookController.putMethodName(1L, 2L, availability);
    assertEquals(copy, result);
  }
}
