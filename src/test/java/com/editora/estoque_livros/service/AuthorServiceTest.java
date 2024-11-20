package com.editora.estoque_livros.service;

import com.editora.estoque_livros.dto.AuthorDTO;
import com.editora.estoque_livros.entity.Author;
import com.editora.estoque_livros.mapper.AuthorMapper;
import com.editora.estoque_livros.repository.AuthorRepository;
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

class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private AuthorMapper authorMapper;

    @InjectMocks
    private AuthorService authorService;

    private AuthorDTO authorDTO;
    private Author authorEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        authorDTO = new AuthorDTO();
        authorDTO.setId(1L);
        authorDTO.setName("John Doe");

        authorEntity = new Author();
        authorEntity.setId(1L);
        authorEntity.setName("John Doe");
    }

    @Test
    void save_ShouldSaveAndReturnAuthorDTO() {
        when(authorMapper.toEntity(authorDTO)).thenReturn(authorEntity);
        when(authorRepository.save(authorEntity)).thenReturn(authorEntity);
        when(authorMapper.toDTO(authorEntity)).thenReturn(authorDTO);

        AuthorDTO savedAuthor = authorService.save(authorDTO);

        assertNotNull(savedAuthor);
        assertEquals(authorDTO.getId(), savedAuthor.getId());
        assertEquals(authorDTO.getName(), savedAuthor.getName());
        verify(authorRepository, times(1)).save(authorEntity);
    }

    @Test
    void findAll_ShouldReturnListOfAuthors() {
        when(authorRepository.findAll()).thenReturn(Collections.singletonList(authorEntity));
        when(authorMapper.toDTOList(Collections.singletonList(authorEntity)))
                .thenReturn(Collections.singletonList(authorDTO));

        List<AuthorDTO> authors = authorService.findAll();

        assertNotNull(authors);
        assertEquals(1, authors.size());
        verify(authorRepository, times(1)).findAll();
    }

    @Test
    void findById_ShouldReturnAuthorDTO_WhenAuthorExists() {
        when(authorRepository.findById(1L)).thenReturn(Optional.of(authorEntity));
        when(authorMapper.toDTO(authorEntity)).thenReturn(authorDTO);

        AuthorDTO foundAuthor = authorService.findById(1L);

        assertNotNull(foundAuthor);
        assertEquals(authorDTO.getId(), foundAuthor.getId());
        verify(authorRepository, times(1)).findById(1L);
    }

    @Test
    void findById_ShouldThrowException_WhenAuthorDoesNotExist() {
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, 
            () -> authorService.findById(1L));

        assertEquals("Book not found", exception.getMessage());
        verify(authorRepository, times(1)).findById(1L);
    }

    @Test
    void delete_ShouldCallRepositoryDeleteById() {
        doNothing().when(authorRepository).deleteById(1L);

        authorService.delete(1L);

        verify(authorRepository, times(1)).deleteById(1L);
    }
}
