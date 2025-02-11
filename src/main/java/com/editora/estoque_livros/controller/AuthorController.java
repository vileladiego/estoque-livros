package com.editora.estoque_livros.controller;

import com.editora.estoque_livros.api.AuthorApi;
import com.editora.estoque_livros.dto.AuthorDTO;
import com.editora.estoque_livros.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController implements AuthorApi {
    @Autowired
    private AuthorService bookService;

    @PostMapping
    public ResponseEntity<AuthorDTO> save(@RequestBody AuthorDTO author) {
        return ResponseEntity.ok(bookService.save(author));
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> findAll() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
