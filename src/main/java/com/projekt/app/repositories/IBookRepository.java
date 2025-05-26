package com.projekt.app.repositories;

import java.util.List;
import java.util.Optional;
import com.projekt.app.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Book entities.
 * Provides methods to find books by title and retrieve all books.
 * Extends JpaRepository to leverage Spring Data JPA features.
 */
@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {
  List<Book> findAll();

  Optional<Book> findByTitle(String title);

}
