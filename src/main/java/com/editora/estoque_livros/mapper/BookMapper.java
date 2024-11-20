package com.editora.estoque_livros.mapper;

import com.editora.estoque_livros.dto.BookDTO;
import com.editora.estoque_livros.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "author", ignore = true)
    Book toEntity(BookDTO bookDTO);
    @Mapping(source = "author.id", target = "authorId")
    @Mapping(source = "author.name", target = "authorName")
    BookDTO toDTO(Book book);

    List<BookDTO> toDTOList(List<Book> books);
}
