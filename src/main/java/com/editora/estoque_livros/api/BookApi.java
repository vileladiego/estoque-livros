package com.editora.estoque_livros.api;

import com.editora.estoque_livros.dto.BookDTO;
import com.editora.estoque_livros.entity.Book;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Livro", description = "Endpoints para gerenciamento de livros")
@RequestMapping("/api/book")
public interface BookApi {

    @Operation(
            summary = "Registrar um livro",
            description = "Registra um novo livro",
            requestBody = @RequestBody(
                    required = true,
                    description = "Dados do livro a ser registrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Book.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Exemplo de Registro de livro",
                                            summary = "Exemplo de requisição para registrar um livro",
                                            value = """
                                                    {                                                   \s
                                                       "title": "Aventuras na Programação",
                                                       "stock": 100,
                                                       "authorId": 1                                                      \s
                                                     }
                                                   \s"""
                                    )
                            }
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Livro registrado com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Book.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos")
            }
    )
    @PostMapping
    ResponseEntity<BookDTO> save(@RequestBody BookDTO alertLog);
}
