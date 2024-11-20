package com.editora.estoque_livros.service;

import com.editora.estoque_livros.dto.BookDTO;
import com.editora.estoque_livros.entity.Book;
import com.editora.estoque_livros.mapper.BookMapper;
import com.editora.estoque_livros.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookDTO save(Book book) {
        return bookMapper.toDTO(bookRepository.save(book));
    }

    public List<BookDTO> findAll() {
        return bookMapper.toDTOList(bookRepository.findAll());
    }

    public BookDTO findById(Long id) {
        return bookMapper.toDTO(bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found")));
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
