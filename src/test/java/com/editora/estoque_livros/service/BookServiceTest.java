package com.editora.estoque_livros.service;

import com.editora.estoque_livros.dto.BookDTO;
import com.editora.estoque_livros.entity.Author;
import com.editora.estoque_livros.entity.Book;
import com.editora.estoque_livros.mapper.BookMapper;
import com.editora.estoque_livros.repository.AuthorRepository;
import com.editora.estoque_livros.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookService bookService;

    private BookDTO bookDTO;
    private Book bookEntity;
    private Author authorEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        authorEntity = new Author();
        authorEntity.setId(1L);
        authorEntity.setName("John Doe");

        bookDTO = new BookDTO();
        bookDTO.setId(1L);
        bookDTO.setTitle("Sample Book");
        bookDTO.setAuthorId(1L);

        bookEntity = new Book();
        bookEntity.setId(1L);
        bookEntity.setTitle("Sample Book");
        bookEntity.setAuthor(authorEntity);
    }

    @Test
    void save_ShouldSaveAndReturnBookDTO_WhenAuthorExists() {
        when(bookMapper.toEntity(bookDTO)).thenReturn(bookEntity);
        when(authorRepository.findById(1L)).thenReturn(Optional.of(authorEntity));
        when(bookRepository.save(bookEntity)).thenReturn(bookEntity);
        when(bookMapper.toDTO(bookEntity)).thenReturn(bookDTO);

        BookDTO savedBook = bookService.save(bookDTO);

        assertNotNull(savedBook);
        assertEquals(bookDTO.getId(), savedBook.getId());
        assertEquals(bookDTO.getTitle(), savedBook.getTitle());
        verify(authorRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).save(bookEntity);
    }

    @Test
    void save_ShouldThrowException_WhenAuthorDoesNotExist() {
        when(bookMapper.toEntity(bookDTO)).thenReturn(bookEntity);
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, 
            () -> bookService.save(bookDTO));

        assertEquals("Author not found", exception.getMessage());
        verify(authorRepository, times(1)).findById(1L);
        verify(bookRepository, never()).save(any());
    }

    @Test
    void findAll_ShouldReturnListOfBooks() {
        when(bookRepository.findAll()).thenReturn(Collections.singletonList(bookEntity));
        when(bookMapper.toDTOList(Collections.singletonList(bookEntity)))
                .thenReturn(Collections.singletonList(bookDTO));

        List<BookDTO> books = bookService.findAll();

        assertNotNull(books);
        assertEquals(1, books.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void findById_ShouldReturnBookDTO_WhenBookExists() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(bookEntity));
        when(bookMapper.toDTO(bookEntity)).thenReturn(bookDTO);

        BookDTO foundBook = bookService.findById(1L);

        assertNotNull(foundBook);
        assertEquals(bookDTO.getId(), foundBook.getId());
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void findById_ShouldThrowException_WhenBookDoesNotExist() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, 
            () -> bookService.findById(1L));

        assertEquals("Book not found", exception.getMessage());
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void delete_ShouldCallRepositoryDeleteById() {
        doNothing().when(bookRepository).deleteById(1L);

        bookService.delete(1L);

        verify(bookRepository, times(1)).deleteById(1L);
    }
}
