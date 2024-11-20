package com.editora.estoque_livros.repository;

import com.editora.estoque_livros.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
