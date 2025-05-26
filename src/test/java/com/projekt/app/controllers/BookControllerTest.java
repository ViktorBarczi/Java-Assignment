
package com.projekt.app.controllers;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projekt.app.dtos.BookRequest;
import com.projekt.app.dtos.BookUpdate;
import com.projekt.app.entities.Book;
import com.projekt.app.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(BookController.class)
public class BookControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @InjectMocks
  private BookService bookService;

  private ObjectMapper objectMapper;

  @BeforeEach
  void setup() {
    objectMapper = new ObjectMapper();
  }

  @Test
  void testGetBookById() throws Exception {
    Book book = new Book();
    book.setId(1L);
    book.setTitle("Sample Book");

    when(bookService.getBookById(1L)).thenReturn(book);

    mockMvc.perform(MockMvcRequestBuilders.get("/api/books/1")).andExpect(status().isFound())
        .andExpect(jsonPath("$.title").value("Sample Book"));
  }

  @Test
  void testPostBook() throws Exception {
    BookRequest request = new BookRequest();
    request.setTitle("New Book");
    request.setAuthor("Author");
    request.setIsbn("123456");
    request.setPublishedYear(2020);

    String json = objectMapper.writeValueAsString(request);

    mockMvc.perform(MockMvcRequestBuilders.post("/api/books")
        .contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isCreated());
  }

  @Test
  void testPutBook() throws Exception {
    BookUpdate update = new BookUpdate();
    update.setTitle("Updated Title");
    update.setPublishedYear(2021);

    Book updatedBook = new Book();
    updatedBook.setId(1L);
    updatedBook.setTitle("Updated Title");
    updatedBook.setPublishedYear(2021);

    when(bookService.updateBook(Mockito.eq(1L), any(BookUpdate.class))).thenReturn(updatedBook);

    String json = objectMapper.writeValueAsString(update);

    mockMvc
        .perform(MockMvcRequestBuilders.put("/api/books/1").contentType(MediaType.APPLICATION_JSON)
            .content(json))
        .andExpect(status().isFound()).andExpect(jsonPath("$.title").value("Updated Title"));
  }

  @Test
  void testDeleteBook() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.delete("/api/books/1")).andExpect(status().isGone());
  }
}
