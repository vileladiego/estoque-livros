package com.editora.estoque_livros.api;

import com.editora.estoque_livros.dto.BookDTO;
import com.editora.estoque_livros.entity.Book;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Operation(
            summary = "Atualizar o estoque de um livro",
            description = "Permite atualizar o estoque de um livro existente informando o ID e a quantidade a ser alterada. O valor pode ser positivo (incremento) ou negativo (decremento)."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Estoque atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Livro não encontrado",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Estoque inválido ou outro erro de validação",
                    content = @Content(mediaType = "application/json")
            )
    })
    @PatchMapping("/{id}/stock")
    ResponseEntity<BookDTO> updateStock(
            @PathVariable Long id,
            @Parameter(
                    description = "Valor para alterar o estoque do livro. Use valores negativos para decrementar.",
                    examples = @ExampleObject(
                            name = "Decremento de estoque",
                            value = "-5",
                            summary = "Decrementa 5 unidades do estoque"
                    )
            )
            @RequestParam int stockChange
    );
}
