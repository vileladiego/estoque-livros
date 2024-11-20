package com.editora.estoque_livros.mapper;

import com.editora.estoque_livros.dto.AuthorDTO;
import com.editora.estoque_livros.dto.BookDTO;
import com.editora.estoque_livros.entity.Author;
import com.editora.estoque_livros.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    Author toEntity(AuthorDTO authorDTO);

    AuthorDTO toDTO(Author author);
}
