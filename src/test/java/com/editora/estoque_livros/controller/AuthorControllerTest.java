package com.editora.estoque_livros.controller;

import com.editora.estoque_livros.dto.AuthorDTO;
import com.editora.estoque_livros.service.AuthorService;
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

class AuthorControllerTest {

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private AuthorController authorController;

    private AuthorDTO authorDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        authorDTO = new AuthorDTO();
        authorDTO.setId(1L);
        authorDTO.setName("John Doe");
    }

    @Test
    void save_ShouldReturnSavedAuthor() {
        when(authorService.save(authorDTO)).thenReturn(authorDTO);

        ResponseEntity<AuthorDTO> response = authorController.save(authorDTO);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(authorDTO.getId(), response.getBody().getId());
        assertEquals(authorDTO.getName(), response.getBody().getName());
        verify(authorService, times(1)).save(authorDTO);
    }

    @Test
    void findAll_ShouldReturnListOfAuthors() {
        when(authorService.findAll()).thenReturn(Collections.singletonList(authorDTO));

        ResponseEntity<List<AuthorDTO>> response = authorController.findAll();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(authorDTO.getId(), response.getBody().get(0).getId());
        verify(authorService, times(1)).findAll();
    }

    @Test
    void findById_ShouldReturnAuthor_WhenAuthorExists() {
        when(authorService.findById(1L)).thenReturn(authorDTO);

        ResponseEntity<AuthorDTO> response = authorController.findById(1L);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(authorDTO.getId(), response.getBody().getId());
        verify(authorService, times(1)).findById(1L);
    }

    @Test
    void delete_ShouldReturnNoContent() {
        doNothing().when(authorService).delete(1L);

        ResponseEntity<Void> response = authorController.delete(1L);

        assertNotNull(response);
        assertEquals(204, response.getStatusCodeValue());
        verify(authorService, times(1)).delete(1L);
    }
}
