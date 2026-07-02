# 🌍 Zenyatlas - Backend

Uma API REST robusta desenvolvida em **Java com Spring Boot** para gerenciar um sistema de viagens, pacotes turísticos e hospedagens. Este projeto foi desenvolvido como trabalho de conclusão de curso (TCC).

## 📋 Descrição

Zenyatlas é uma plataforma completa de backend que oferece funcionalidades para:
- 🏖️ Gerenciamento de destinos turísticos
- 🏨 Gerenciamento de hospedagens
- 📦 Gerenciamento de pacotes turísticos
- 🛫 Processamento de pedidos/reservas de viagens
- 👥 Autenticação e autorização de usuários
- 🔐 Sistema de segurança com JWT (JSON Web Tokens)

## 🛠️ Tecnologias

- **Java 21** - Linguagem de programação
- **Spring Boot 4.0.2** - Framework web
- **Spring Data JPA** - ORM para persistência
- **Spring Security** - Autenticação e autorização
- **SQL Server** - Banco de dados
- **JWT (jjwt 0.11.5)** - Autenticação stateless
- **Lombok** - Redução de código boilerplate
- **OpenAPI/Swagger** - Documentação interativa da API
- **Maven** - Gerenciamento de dependências

## 📦 Dependências Principais

```xml
- springdoc-openapi-starter-webmvc-ui (2.5.0)
- spring-boot-starter-validation
- spring-boot-starter-web
- spring-boot-starter-data-jpa
- spring-boot-starter-security
- mssql-jdbc
- jjwt (JWT authentication)
- lombok


🚀 Como Executar
Pré-requisitos

    Java 21+
    Maven 3.6+
    SQL Server (ou banco de dados compatível)


- Instalação (No bash)

    git clone https://github.com/pjjoaoo/BackEndZenyatlas.git
    cd BackEndZenyatlas

- Configure o banco de dados

    Edite o arquivo application.properties (ou application.yml)
    Configure as credenciais do SQL Server

- Instale as dependências e execute

mvn clean install
mvn spring-boot:run

Acesse a API

    API: http://localhost:8080
    Swagger UI: http://localhost:8080/swagger-ui.html


📚 Estrutura do Projeto

src/
├── main/java/com/itb/tcc/mif3an/pizzaria/
│   ├── PizzariaApplication.java          # Classe principal
│   ├── model/
│   │   ├── controller/                   # Controllers REST
│   │   │   ├── DestinoController.java
│   │   │   ├── HospedagemController.java
│   │   │   ├── PacoteController.java
│   │   │   └── PedidoController.java
│   │   ├── entity/                       # Entidades JPA
│   │   ├── repository/                   # Repositórios Spring Data
│   │   └── services/                     # Lógica de negócio
│   ├── security/
│   │   └── config/                       # Configurações de segurança
│   ├── auth/
│   │   └── AuthenticationService.java   # Serviço de autenticação
│   ├── dto/                              # Data Transfer Objects
│   └── exceptions/                       # Tratamento de exceções
├── test/                                 # Testes
└── resources/
    └── application.properties            # Configurações


🔌 Endpoints Principais
🏖️ Destinos

    GET /destinos - Listar todos os destinos
    GET /destinos/{id} - Obter destino por ID
    POST /destinos - Criar novo destino
    PUT /destinos/{id} - Atualizar destino
    PATCH /destinos/{id} - Atualização parcial
    DELETE /destinos/{id} - Deletar destino

🏨 Hospedagens

    GET /hospedagens - Listar hospedagens
    POST /hospedagens - Criar hospedagem
    DELETE /hospedagens/{id} - Deletar hospedagem

📦 Pacotes

    GET /pacotes - Listar pacotes
    POST /pacotes - Criar pacote
    PATCH /pacotes/{id} - Atualizar pacote

🛫 Pedidos

    POST /api/v1/pedidos - Criar pedido de viagem
    GET /api/v1/pedidos/cliente/{id} - Listar pedidos do cliente

🔐 Autenticação

    POST /auth/register - Registrar novo usuário
    POST /auth/authenticate - Fazer login

---

🔒 Segurança

    ✅ JWT (JSON Web Tokens) para autenticação
    ✅ Spring Security para autorização baseada em roles
    ✅ Senha criptografada com BCrypt
    ✅ CORS configurado para aceitar requisições de clientes web
    ✅ Autorização por tipo de usuário (ADMIN, CLIENTE, etc)


Usuário Padrão

    Email: admin@zenyatlas.com
    Senha: 12345678
    Tipo: ADMIN

Criado automaticamente na primeira execução
🔗 CORS

A API está configurada para aceitar requisições de qualquer origem:

    Todos os métodos HTTP: GET, POST, PUT, DELETE, PATCH, etc
    Todos os headers
    Qualquer origem (*)

🐛 Tratamento de Exceções

A aplicação possui um ExceptionHandler global que trata erros comuns:

    Recursos não encontrados (404)
    Validações falhas
    Erros de autenticação

📝 Variáveis de Ambiente

Configure as seguintes variáveis para sua aplicação:
properties

spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=zenyatlas
spring.datasource.username=sa
spring.datasource.password=sua_senha
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver
spring.jpa.hibernate.ddl-auto=update


Note: Acesse o Swagger UI para explorar os endpoints:

http://localhost:8080/swagger-ui.html

👤 Autor

    pjjoaoo - Desenvolvedor principal

📄 Licença

Este projeto é de código aberto e disponível sob licença aberta.
🙏 Agradecimentos

Desenvolvido como trabalho de conclusão de curso (TCC) - MIF3AN - Instituto Tecnico de Barueri (ITB).

Última atualização: Junho 2026
Versão: 0.0.1-SNAPSHOT
