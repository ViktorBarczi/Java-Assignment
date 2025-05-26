package com.projekt.app.repositories;

import java.util.List;
import com.projekt.app.entities.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookCopyRepository extends JpaRepository<BookCopy, Long> {
  List<BookCopy> findByBookId(Long bookId);

}
