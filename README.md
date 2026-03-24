# Produtos API

API REST em Java com Spring Boot para cadastro de produtos.  
Projeto desenvolvido para estudos de Spring Boot, Spring Data JPA, banco em memória H2 e boas práticas de desenvolvimento backend.

---

## 🔧 Tecnologias utilizadas

- Java (versão compatível com o projeto)
- Spring Boot
  - Spring Web
  - Spring Data JPA
- Banco de dados em memória: **H2**
- Maven
- IDE: IntelliJ IDEA / Eclipse / VS Code (qualquer IDE compatível com Maven)

---

## 🧱 Arquitetura do projeto

Pacotes principais:

- `io.github.FernandoLacerda90.produtosapi`
  - Classe principal da aplicação:
    - `ProdutosapiApplication`
- `io.github.FernandoLacerda90.produtosapi.controller`
  - `ProdutoController` – recebe requisições HTTP e expõe os endpoints.
- `io.github.FernandoLacerda90.produtosapi.model`
  - `Produto` – entidade JPA que representa a tabela `produto`.
- `io.github.FernandoLacerda90.produtosapi.repository`
  - `ProdutoRepository` – interface de acesso a dados (Spring Data JPA).

Arquivos de recursos:

- `src/main/resources/application.yml` – configurações do Spring Boot, JPA e H2.
- `src/main/resources/schema.sql` – criação da tabela `produto`.
- `src/main/resources/data.sql` – carga inicial de dados.

---

## 🗃 Entidade `Produto`

A classe `Produto` é mapeada para a tabela `produto` no banco de dados:

```java
@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "preco")
    private Double preco;

    // getters, setters e toString()
}
```

**Campos:**

- `id` – identificador único (String, gerado como UUID no controller)
- `nome` – nome do produto
- `descricao` – descrição do produto
- `preco` – preço do produto

---

## 💾 Banco de dados (H2)

O projeto utiliza o banco de dados em memória **H2**.  
Configuração principal em `application.yml`:

```yaml
spring:
  application:
    name: produtosapi
  datasource:
    url: jdbc:h2:mem:produtos
    username: sa
    password: password
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: none
  sql:
    init:
      mode: always
  h2:
    console:
      enabled: true
      path: /h2-console
```

### Scripts SQL

**Criação da tabela (`schema.sql`):**

```sql
CREATE TABLE produto (
   id VARCHAR(255) PRIMARY KEY,
   nome VARCHAR(50),
   descricao VARCHAR(255),
   preco DECIMAL(18, 2)
);
```

**Dados iniciais (`data.sql`):**

```sql
INSERT INTO produto (id, nome, descricao, preco)
VALUES ('1', 'Produto 1', 'Descrição do produto 1', 10.00);
```

### Console H2

Após subir a aplicação, você pode acessar o console do H2 em:

- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:produtos`
- Usuário: `sa`
- Senha: `password`

---

## 🌐 Endpoints

### 1. Criar um novo produto

**Método:** `POST`  
**URL:** `/produtos`

**Descrição:**

Cria um novo produto na base de dados.  
O `id` é gerado automaticamente no backend usando `UUID`.

Trecho do controller:

```java
@PostMapping
public Produto salvar(@RequestBody Produto produto) {
    System.out.println("Produto recebido: " + produto);

    var id = UUID.randomUUID().toString();
    produto.setId(id);

    produtoRepository.save(produto);
    return produto;
}
```

**Request Body (JSON):**

```json
{
  "nome": "Notebook Dell",
  "descricao": "Notebook Dell Inspiron 15",
  "preco": 3500.00
}
```

**Resposta de sucesso (exemplo):**

```json
{
  "id": "c0a80136-6d8b-4d2d-bb74-4e1c0b1f9a21",
  "nome": "Notebook Dell",
  "descricao": "Notebook Dell Inspiron 15",
  "preco": 3500.0
}
```

---

## ▶️ Como executar o projeto

### Pré-requisitos

- Java instalado (versão compatível com o projeto)
- Maven instalado (ou uso do Maven Wrapper)
- Git (para clonar o repositório, se necessário)

### Passo a passo

1. **Clonar o repositório (caso ainda não tenha):**

```bash
git clone https://github.com/FernandoLacerda90/produtos-api.git
cd produtos-api
```

2. **Rodar a aplicação com Maven:**

```bash
mvn spring-boot:run
```

Ou, se estiver usando o Maven Wrapper (Linux/macOS):

```bash
./mvnw spring-boot:run
```

No Windows:

```bash
mvnw spring-boot:run
```

3. **Testar o endpoint de criação de produto:**

Usando `curl`:

```bash
curl -X POST http://localhost:8080/produtos \
  -H "Content-Type: application/json" \
  -d '{
        "nome": "Mouse Gamer",
        "descricao": "Mouse com 6 botões",
        "preco": 120.50
      }'
```

Ou use ferramentas como **Postman** ou **Insomnia**.

---

## ✅ Próximos passos (melhorias futuras)

Algumas ideias de evolução para este projeto:

- [ ] Adicionar mais endpoints:
  - [ ] Listar todos os produtos (`GET /produtos`)
  - [ ] Buscar produto por id (`GET /produtos/{id}`)
  - [ ] Atualizar produto (`PUT /produtos/{id}`)
  - [ ] Deletar produto (`DELETE /produtos/{id}`)
- [ ] Adicionar validações com Bean Validation (`@NotNull`, `@Size`, etc.)
- [ ] Tratar erros com `@ControllerAdvice` e respostas padronizadas
- [ ] Documentar a API com Swagger / OpenAPI
- [ ] Criar testes unitários e de integração (JUnit + Spring Boot Test)
- [ ] Containerizar a aplicação com Docker

---

## 👨‍💻 Autor

- **Fernando Lacerda Ramos**  
- LinkedIn: [linkedin.com/in/fernando-lacerda-ramos](https://www.linkedin.com/in/fernando-lacerda-ramos/)  
- GitHub: [@FernandoLacerda90](https://github.com/FernandoLacerda90)
