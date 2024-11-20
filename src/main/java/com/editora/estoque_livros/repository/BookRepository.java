package com.editora.estoque_livros.repository;

import com.editora.estoque_livros.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
