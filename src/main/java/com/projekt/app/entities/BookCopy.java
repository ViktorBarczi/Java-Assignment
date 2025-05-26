package com.projekt.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

@Entity
@Data
public class BookCopy {
  @Id
  @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
  private Long id;

  @JoinColumn(name = "book_id", nullable = false)
  @ManyToOne
  @JsonBackReference
  private Book bookId;

  @Column(nullable = false)
  private boolean isAvailable = true;

  public BookCopy() {
    // Default constructor for JPA
  }
}
