# Sistema de Gestão de Processos Judiciais

Este é um projeto Spring Boot com banco em memória H2, que permite o **cadastro de processos judiciais**, **agendamento de audiências** e **consulta de agenda por comarca e data**.

---

## 🔧 Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database
- Spring Validation
- Swagger (Springdoc OpenAPI)
- JUnit 5
- Mockito
- Maven

---

## 🚀 Como Subir o Projeto

### Pré-requisitos

- Java 17+
- Maven 3.8+
- IDE (IntelliJ ou Eclipse, opcional)

- Antes de subir o projeto, certifique-se de rodar:
- mvn clean install
- mvn spring-boot:run

## Documentação da API
http://localhost:8080/api/swagger-ui/index.html


Funcionalidades Implementadas
1. Cadastro de Processo Judicial

    Campos:

        numeroProcesso (único)

        vara

        comarca

        assunto

        status: ATIVO, ARQUIVADO, SUSPENSO

    Recursos:

        Listar todos os processos

        Filtrar por status e comarca

2. Agendamento de Audiências

    Cada processo pode ter uma ou mais audiências

    Campos:

        dataHora

        tipo: CONCILIACAO, INSTRUCAO, JULGAMENTO

        local

    Validação personalizada:

        Não permite sobreposição de audiências na mesma vara e local

3. Consulta de Agenda

    Endpoint que retorna todas as audiências de uma comarca em um determinado dia.

