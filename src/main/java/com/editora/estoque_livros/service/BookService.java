package com.editora.estoque_livros.service;

import com.editora.estoque_livros.dto.BookDTO;
import com.editora.estoque_livros.entity.Book;
import com.editora.estoque_livros.exception.ResourceNotFoundException;
import com.editora.estoque_livros.mapper.BookMapper;
import com.editora.estoque_livros.repository.AuthorRepository;
import com.editora.estoque_livros.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;

    public BookDTO save(BookDTO bookDTO) {
        Book book = bookMapper.toEntity(bookDTO);
        book.setAuthor(authorRepository.findById(bookDTO.getAuthorId())
                .orElseThrow(() -> new EntityNotFoundException("Author not found")));
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

    public BookDTO updateStock(Long id, int stockChange) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado"));

        book.setStock(book.getStock() + stockChange);

        if (book.getStock() < 0) {
            throw new IllegalArgumentException("O estoque não pode ser negativo");
        }

        return bookMapper.toDTO(bookRepository.save(book));
    }

}
