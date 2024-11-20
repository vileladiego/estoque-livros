package com.editora.estoque_livros.api;


import com.editora.estoque_livros.dto.AuthorDTO;
import com.editora.estoque_livros.entity.Author;
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

@Tag(name = "Author", description = "Endpoints para gerenciamento de autores")
@RequestMapping("/api/author")
public interface AuthorApi {

    @Operation(
            summary = "Registrar um author",
            description = "Registra um novo author",
            requestBody = @RequestBody(
                    required = true,
                    description = "Dados do author a ser registrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Author.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Exemplo de Registro de Author",
                                            summary = "Exemplo de requisição para registrar um author",
                                            value = """
                                {
                                  "name": "JK Rowling"
                                }"""
                                    )
                            }
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Author registrado com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Author.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos")
            }
    )
    @PostMapping
    ResponseEntity<AuthorDTO> save(@RequestBody AuthorDTO alertLog);
}
