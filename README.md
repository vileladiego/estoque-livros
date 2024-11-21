
# Estoque Livros - Sistema de Gerenciamento de Livros

## Descrição

Estoque Livros é um sistema web desenvolvido em Java com Spring Boot para gerenciamento de estoque de Livros. O sistema permite:

- Adicionar novos livros.
- Excluir livros.
- Alterar informações de livros.
- Alterar o estoque de livros.


## Pré-requisitos

- **Docker** e **Docker Compose** instalados na máquina.

## Como Executar o Projeto com Docker

Siga os passos abaixo para executar a aplicação utilizando Docker:

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/vileladiego/estoque-livros.git
   cd estoque-livros   
   ```

2. **Construa e inicie os containers:**

   ```bash
   docker compose up --build
   ```
   ou
   ```bash
   sudo docker compose up --build
   ```
   Caso esteja tomando este erro (somente em Windows):
    ```bash
    => ERROR [build 6/6] RUN ./gradlew build      0.6s
     ------
    > [build 6/6] RUN ./gradlew build:
    0.526 /bin/sh: 1: ./gradlew: not found
   ```
   exeute este comando na raiz do projeto. Isto irá trocar os tipos de quebras de linha para Unix:
   ```bash
   dos2unix gradlew
   ```
   Isso irá:

    * Construir a imagem da aplicação baseada no Dockerfile.
    * Iniciar um container para a aplicação Spring Boot.<br><br>

3. **Aguarde até que a aplicação esteja pronta:**

    * A aplicação estará acessível em http://localhost:8080.<br><br>

##  Uso da API via Swagger
A aplicação expõe a documentação da API através do Swagger UI. Siga os passos abaixo para acessá-la:

Acesse o Swagger UI no navegador:
    ```        http://localhost:8080/swagger-ui.html
    ```
Autenticação no Swagger UI:
* Como a API está protegida por autenticação básica, você precisará fornecer as credenciais para testar os endpoints.
* Insira as credenciais abaixo:
    * Username: steppenwolf
    * Password: 7#F@x9qZ!mL2$wR3

Explorando a API:
* Você pode visualizar todos os endpoints disponíveis, suas descrições e modelos de dados.
* Execute chamadas de API diretamente pelo Swagger UI.


## Execução Sem Docker (Opcional)
Se preferir executar a aplicação localmente sem Docker, siga os passos abaixo:

1. **Pré-requisitos:**
    * Java 21 ou superior instalado.
    * PostgreSQL instalado e configurado.


2. **Configure o banco de dados:**
    * Crie um banco de dados chamado estoque-livros.
    * Atualize o arquivo ```src/main/resources/application.yml``` com as credenciais do seu banco de dados local:
   ```yaml
   spring:
    datasource:
      url: jdbc:postgresql://localhost:5432/estoque-livros
      username: seu_usuario
      password: sua_senha
     ```
3. **Executar a aplicação:**
   ```bash
   ./gradlew bootRun
   ```
4. **Acesse a aplicação em ```http://localhost:8080```.**

##  Testes
Para executar os testes unitários, utilize o comando:

```bash
./gradlew test
```

Os relatórios de testes estarão disponíveis no diretório ```build/reports/tests/test/index.html```.

## Tecnologias Utilizadas
* Java 21
* Spring Boot
* Spring Data JPA
* Spring Security
* Lombok
* PostgreSQL
* Docker e Docker Compose
* Swagger (Springdoc OpenAPI)
* JUnit 5 - Para testes unitários


