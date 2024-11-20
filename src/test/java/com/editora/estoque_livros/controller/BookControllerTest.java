package com.editora.estoque_livros.controller;

import com.editora.estoque_livros.dto.BookDTO;
import com.editora.estoque_livros.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private BookDTO bookDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        bookDTO = new BookDTO();
        bookDTO.setId(1L);
        bookDTO.setTitle("Sample Book");
        bookDTO.setAuthorId(1L);
    }

    @Test
    void save_ShouldReturnSavedBook() {
        when(bookService.save(bookDTO)).thenReturn(bookDTO);

        ResponseEntity<BookDTO> response = bookController.save(bookDTO);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(bookDTO.getId(), response.getBody().getId());
        assertEquals(bookDTO.getTitle(), response.getBody().getTitle());
        verify(bookService, times(1)).save(bookDTO);
    }

    @Test
    void findAll_ShouldReturnListOfBooks() {
        when(bookService.findAll()).thenReturn(Collections.singletonList(bookDTO));

        ResponseEntity<List<BookDTO>> response = bookController.findAll();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(bookDTO.getId(), response.getBody().get(0).getId());
        verify(bookService, times(1)).findAll();
    }

    @Test
    void findById_ShouldReturnBook_WhenBookExists() {
        when(bookService.findById(1L)).thenReturn(bookDTO);

        ResponseEntity<BookDTO> response = bookController.findById(1L);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(bookDTO.getId(), response.getBody().getId());
        assertEquals(bookDTO.getTitle(), response.getBody().getTitle());
        verify(bookService, times(1)).findById(1L);
    }

    @Test
    void delete_ShouldReturnNoContent() {
        doNothing().when(bookService).delete(1L);

        ResponseEntity<Void> response = bookController.delete(1L);

        assertNotNull(response);
        assertEquals(204, response.getStatusCodeValue());
        verify(bookService, times(1)).delete(1L);
    }
}
