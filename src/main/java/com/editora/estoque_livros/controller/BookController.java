package com.editora.estoque_livros.controller;

import com.editora.estoque_livros.api.BookApi;
import com.editora.estoque_livros.dto.BookDTO;
import com.editora.estoque_livros.entity.Book;
import com.editora.estoque_livros.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class BookController implements BookApi {
    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<BookDTO> save(@RequestBody BookDTO book) {
        return ResponseEntity.ok(bookService.save(book));
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> findAll() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<BookDTO> updateStock(
            @PathVariable Long id,
            @RequestParam int stockChange
    ) {
        BookDTO updatedBook = bookService.updateStock(id, stockChange);
        return ResponseEntity.ok(updatedBook);
    }
}
