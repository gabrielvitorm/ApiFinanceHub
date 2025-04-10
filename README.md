
# 📊 FinanceHub API

Este projeto é uma **API RESTful** desenvolvida em **Java com Spring Boot**, com o objetivo de gerenciar transações financeiras, orçamentos, exportações de dados e usuários. O sistema é parte de um projeto acadêmico para a disciplina de **Projeto de Extensão II** do curso de Sistemas para Internet.

## 🎓 Sobre o Projeto

O **FinanceHub** foi idealizado para auxiliar no controle financeiro pessoal ou empresarial, permitindo o cadastro de transações, orçamentos e exportações de dados financeiros em PDF, além de gerenciamento de usuários.

## 📁 Estrutura do Projeto

O projeto segue uma arquitetura MVC (Model-View-Controller), com os pacotes organizados da seguinte forma:

- `controller`: Contém os controladores REST da aplicação.
- `dto`: Objetos de transferência de dados usados para comunicação entre camadas.
- `enums`: Enumerações com tipos e categorias utilizadas na lógica do sistema.
- `model`: Entidades do sistema que representam a base de dados.
- `repository`: Interfaces que estendem `JpaRepository` para comunicação com o banco de dados.
- `service`: Camada de serviço contendo a lógica de negócio.
- `config`: Configurações de segurança e CORS.

## 🚀 Como Rodar o Projeto

Siga os passos abaixo para executar o projeto localmente:

### ✅ Pré-requisitos

- Java 17
- Maven 3.8+
- IDE como IntelliJ IDEA ou Eclipse
- PostgreSQL 15

### 🔧 Configuração

1. Clone este repositório:
   ```bash
   git clone https://github.com/gabrielvitorm/ApiFinanceHub.git
   cd ApiFinanceHub
   ```

2. Configure o arquivo `application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/finance_hub_2025
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

3. Crie o banco de dados no PostgreSQL:
   ```sql
   CREATE DATABASE finance_hub_2025;
   ```

### ▶️ Executando a API

Na raiz do projeto, execute:

```bash
./mvnw spring-boot:run
```

ou, se estiver usando uma IDE, execute a classe `FinanceHubApplication`.

A aplicação estará disponível em:  
**http://localhost:8080**

## 📌 Endpoints Principais

- `/api/transacoes`: Gerencia as transações financeiras
- `/api/orcamentos`: Gerencia orçamentos
- `/api/usuarios`: Cadastro e login de usuários
- `/api/exportacoes`: Exporta dados em PDF

## 📄 Documentação

Você pode acessar a documentação completa do projeto através do seguinte link:  
👉 [Clique aqui para acessar a documentação oficial](https://drive.google.com/file/d/1nPDIVbMV1qWG8IiXj1S42sS7RcRbIUko/view?usp=sharing)

---
