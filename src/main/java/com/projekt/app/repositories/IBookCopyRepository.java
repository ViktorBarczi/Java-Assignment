package com.projekt.app.repositories;

import java.util.List;
import com.projekt.app.entities.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing BookCopy entities.
 * Provides methods to find book copies by book ID.
 * Extends JpaRepository to leverage Spring Data JPA features.
 */
@Repository
public interface IBookCopyRepository extends JpaRepository<BookCopy, Long> {
  List<BookCopy> findByBookId(Long bookId);

}
