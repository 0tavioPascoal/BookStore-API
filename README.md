# BookStore-API

## Docker

Para usar o docker utilizar o comando a seguir no terminal da própria idea ->

```Docker
 $ docker compose up
```

Para derrubar o container do docker utilizar o comando a seguir no terminal da própria idea ->

```Docker
 $ docker compose down
```

## Documentação da API

### Autenticação

- Rotas para autenticação na aplicação, sendo possivel registrar um novo usuario e logar na aplicação, resultando em um token que deverá ser usado em todos os request.

##

``http
  POST /auth/register
``

| Corpo   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `login` | `string` | **Obrigatório**. E-mail para login na aplicação.  |
| `password` | `string` | **Obrigatório**. Senha para login na aplicação. |
| `email` | `string` | **Obrigatório**. E-mail. |
| `username` | `string` | **Obrigatório**. Definir um nome de usuário. |
| `role` | `string` | Tipo de usuário. *Admin* ou *User* |

##
``http
  POST /auth/login
``

| Corpo   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `login` | `string` | **Obrigatório**. E-mail para login na aplicação.  |
| `password` | `string` | **Obrigatório**. Senha para login na aplicação. |

### Usuários.

- Rotas para Modificação, exclusão e listagem de usuários.

##

``http
  GET /users
``

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `size`      | `integer` | Tamanho de itens na pagina. |
| `page`      | `integer` | Número da pagina. |
| `username`      | `string` | Nome do Usuário. |
| `login`      | `String` | Login do Usuário. |

##
``http
  GET /users
``

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Obrigatório** Id do Usuário. |


##
``http
  DEL /users
``
- Somente Admins.

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Obrigatório** Id do Usuário. |

##
``http
  PUT /authors
``
- Somente Admins.

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Obrigatório** Id do Usuário. |

| Corpo   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `login` | `string` | **Obrigatório**. E-mail para login na aplicação.  |
| `password` | `string` | **Obrigatório**. Senha para login na aplicação. |
| `email` | `string` | **Obrigatório**. E-mail. |
| `username` | `string` | **Obrigatório**. Definir um nome de usuário. |
| `role` | `string` | Tipo de usuário. *Admin* ou *User* |


### Autores

- Rotas para criação, alteração e deleção de Autores na aplicação.
- Para criar Autores e deletar apenas Admins

##

``http
  GET /authors
``
- Busca todos os autores com o nome ou nacionalidade passada por parâmetro.
  
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `name`      | `string` | Autores com esse nome ou letra. |
| `nacionality`| `string` | Autores com a nacionalidade. |

##
``http
  GET /authors/
``
- Busca Autor por id.

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Obrigatório** Id do autor. |

##
``http
  POST /authors
``

- Somente Admins.

| Corpo         | Tipo       | Descrição                                   |
| :----------   | :--------- | :------------------------------------------ |
| `name`        | `string` | **Obrigatório** nome do Autor. |
| `dateOfBirth` | `date` | **Obrigatório** Obrigatorio a data de nascimento do Autor. |
| `nationality`   | `string` | **Obrigatório** Nacionalidade do Autor. |

##
``http
  PUT /authors
``
- Somente Admins.

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Obrigatório** Id do autor. |

| Corpo         | Tipo       | Descrição                                   |
| :----------   | :--------- | :------------------------------------------ |
| `name`        | `string` | **Obrigatório** nome do Autor. |
| `dateOfBirth` | `date` | **Obrigatório** Obrigatorio a data de nascimento do Autor. |
| `nationality`   | `string` | **Obrigatório** Nacionalidade do Autor. |

##
``http
  DEL /authors
``
- Somente Admins.

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Obrigatório** Id do Autor. |



### Livros

- Rotas para criação de Livros.

##

``http
  GET /books
``

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `size`      | `integer` | Tamanho de itens na pagina. |
| `page`      | `integer` | Número da pagina. |
| `title`      | `string` | Titulo do livro. |
| `price`      | `bigDecimal` | Preço do livro. |
| `gender`      | `string` | Genero do livro -> (Romance, Aventura e etc...). |
| `publicationDate`      | `Date` | Data da publicação do livro. |
| `nameAuthor`      | `String` | Nome do Autor do livro. |

##
``http
  GET /books
``

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Obrigatório** Id do Livro. |

##
``http
  POST /books
``

| Corpo         | Tipo       | Descrição                                   |
| :----------   | :--------- | :------------------------------------------ |
| `title`    | `string`  | **Obrigatório** . |
| `publicationDate`   | `LocalDate`   | **Obrigatório** Data da publicação do Livro. |
| `gender`   | `string`   | **Obrigatório** Genero do livro. |
| `price`   | `bigDecimal`   | **Obrigatório** Preço do livro. |
| `idAuthor`   | `string`   | **Obrigatório** Id do Autor para cadastrar o Livro. |

##
``http
  DEL /books
``

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Obrigatório** Id do Livro. |

##
``http
  PUT /books
``


| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Obrigatório** Id do Livro. |

| Corpo         | Tipo       | Descrição                                   |
| :----------   | :--------- | :------------------------------------------ |
| `title`    | `string`  | **Obrigatório** . |
| `publicationDate`   | `LocalDate`   | **Obrigatório** Data da publicação do Livro. |
| `gender`   | `string`   | **Obrigatório** Genero do livro. |
| `price`   | `bigDecimal`   | **Obrigatório** Preço do livro. |
| `idAuthor`   | `string`   | **Obrigatório** Id do Autor para cadastrar o Livro. |


## Autor

- [@OtavioPascoal](https://github.com/0tavioPascoal)
