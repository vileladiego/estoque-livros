package com.editora.estoque_livros.service;

import com.editora.estoque_livros.dto.AuthorDTO;
import com.editora.estoque_livros.entity.Author;
import com.editora.estoque_livros.mapper.AuthorMapper;
import com.editora.estoque_livros.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorDTO save(Author author) {
        return authorMapper.toDTO(authorRepository.save(author));
    }

    public List<AuthorDTO> findAll() {
        return authorMapper.toDTOList(authorRepository.findAll());
    }

    public AuthorDTO findById(Long id) {
        return authorMapper.toDTO(authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found")));
    }

    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}
