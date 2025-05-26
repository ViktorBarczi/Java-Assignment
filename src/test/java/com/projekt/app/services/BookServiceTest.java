
package com.projekt.app.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import java.util.Optional;
import com.projekt.app.dtos.BookRequest;
import com.projekt.app.entities.Book;
import com.projekt.app.exceptions.BadRequestException;
import com.projekt.app.exceptions.NotFoundException;
import com.projekt.app.repositories.IBookCopyRepository;
import com.projekt.app.repositories.IBookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BookServiceTest {

  @Mock
  private IBookRepository bookRepository;

  @Mock
  private IBookCopyRepository bookCopyRepository;

  @InjectMocks
  private BookService bookService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testEmptyDataBAse_ValidRequest_ReturnsEmptyList() {
    when(bookRepository.findAll()).thenReturn(java.util.Collections.emptyList());

    var result = bookService.getAllBooks();

    assertTrue(result.isEmpty());
  }

  @Test
  void testSaveBook_ValidRequest_Success() {
    BookRequest request = new BookRequest();
    request.setTitle("Test Title");
    request.setAuthor("Test Author");
    request.setIsbn("1234567890");
    request.setPublishedYear(2000);

    when(bookRepository.findByTitle("Test Title")).thenReturn(Optional.empty());
    when(bookRepository.save(any(Book.class))).thenAnswer(i -> i.getArguments()[0]);

    Book result = bookService.saveBook(request);

    assertEquals("Test Title", result.getTitle());
    assertEquals("Test Author", result.getAuthor());
    assertEquals("1234567890", result.getIsbn());
    assertEquals(2000, result.getPublishedYear());
  }

  @Test
  void testSaveBook_TitleExists_ThrowsException() {
    BookRequest request = new BookRequest();
    request.setTitle("Existing Title");
    request.setAuthor("Author");
    request.setIsbn("1234567890");
    request.setPublishedYear(1999);

    when(bookRepository.findByTitle("Existing Title")).thenReturn(Optional.of(new Book()));

    Exception exception = assertThrows(BadRequestException.class, () -> {
      bookService.saveBook(request);
    });

    assertTrue(exception.getMessage().contains("already exists"));
  }

  @Test
  void testGetBookById_NotFound_ThrowsException() {
    when(bookRepository.findById(1L)).thenReturn(Optional.empty());

    Exception exception = assertThrows(NotFoundException.class, () -> {
      bookService.getBookById(1L);
    });

    assertTrue(exception.getMessage().contains("does not exist"));
  }
}
