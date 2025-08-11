# Forumhub

Um sistema de fórum moderno e intuitivo para discussões e compartilhamento de conhecimento.

## 📋 Sobre o Projeto

O Forumhub é uma plataforma de discussões desenvolvida para facilitar a comunicação e o compartilhamento de conhecimento entre usuários. Com uma interface moderna e recursos robustos, permite criar tópicos, participar de discussões e gerenciar comunidades de forma eficiente.

## ✨ Funcionalidades

- **Autenticação de Usuários**
    - Registro e login
    - Perfis personalizáveis
    - Sistema de permissões

- **Gerenciamento de Tópicos**
    - Criação e edição de tópicos
    - Categorização por assuntos
    - Sistema de tags

- **Sistema de Respostas**
    - Comentários aninhados
    - Reações (like/dislike)
    - Menções a usuários

## 📋 Pré-requisitos

- [Git](https://git-scm.com)
- [Java](https://www.oracle.com/java/technologies/downloads/) (versão 17 ou superior)
- [Maven](https://maven.apache.org/download.cgi)
- [MySQL](https://www.mysql.com/downloads/) ou [PostgreSQL](https://www.postgresql.org/download/)

## 📡 API Endpoints

### Autenticação
- `POST /api/auth/auth` - Registrar usuário
- `POST /api/auth/login` - Login

### Tópicos
- `GET /api/topicos` - Listar tópicos
- `POST /api/topicos` - Criar tópico
- `GET /api/topicos/{id}` - Buscar tópico
- `PUT /api/topicos/{id}` - Atualizar tópico
- `DELETE /api/topicos/{id}` - Deletar tópico

### Respostas
- `GET /api/topics/{id}/respostas` - Listar respostas
- `POST /api/topics/{id}/respostas` - Criar resposta
- `PUT /api/respostas/{id}` - Atualizar resposta
- `DELETE /api/respostas/{id}` - Deletar resposta

## 📚 Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   └── com/forumhub/
│   │       ├── config/          # Configurações
│   │       ├── controller/      # Controladores REST
│   │       ├── dto/            # Data Transfer Objects
│   │       ├── entity/         # Entidades JPA
│   │       ├── repository/     # Repositórios
│   │       ├── service/        # Camada de serviço
│   │       ├── security/       # Configurações de segurança
│   │       └── exception/      # Tratamento de exceções
│   └── resources/
│       ├── db/              # Scripts de banco de dados
│       │   └── migration/    # Migrações do Flyway
│       └── application.properties
└── test/                       # Testes unitários e integração
```