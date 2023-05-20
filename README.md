# MaisFinanças - PoC

Este repositório contém uma prova de conceito (PoC) para validação dos seguintes requisitos:

- Uso de uma estratégia para migrations de Banco de Dados
- Utilização do docker para conteinerização da aplicação
- Configuração de perfis para execução local e em ambiente de produção
- Pipeline de CI/CD no projeto

A aplicação utiliza como exemplo endpoints para o controle de despesas pessoais.

-- Tecnologias e Ferramentas

A PoC foi desenvolvida utilizando as seguintes tecnologias:

Linguagem de Programação: Kotlin
Framework: Spring Boot
Automação de build: Gradle
Banco de Dados: PostgreSQL
Flyway: Biblioteca para gerenciamento de migrations
Docker: Conteinerização da aplicação e banco de dados de desenvolvimento

## Executando a aplicação

## Pré-Requisitos

Antes de começar, certifique-se de ter os seguintes pré-requisitos instalados em seu ambiente de desenvolvimento:

- **JDK (Java Development Kit)**: É necessário ter o JDK instalado para compilar e executar o projeto. Recomenda-se o
  uso do JDK 8 ou superior.

### Clone o projeto

```shell
  git clone https://github.com/maisfinancas/poc-financas-api
```

### Entre no diretório do projeto

```shell
  cd poc-financas-api
```

### Execute o comando

```shell
  ./gradlew bootRun
```

## API Reference

Os principais endpoints para verificação do projeto são os seguintes.

#### Adicionar Gestor

```http
  Post /api/gestores
```

#### Adicionar Gestor

```http
  POST /api/gestores
```

Adiciona um gestor ao sistema.

---

#### Listar Gestores

```http
  GET /api/gestores/{id}
```

Lista os gestores do sistema.

---

#### Adicionar Despesa

```http
  POST /api/gestores/{id}/despesas
```

| Parâmetro | Descrição              |
|:----------|:-----------------------|
| `id`      | Id do Gestor procurado |

Adiciona uma despesa ao gestor especificado.

---

#### Listar Despesas de um Gestor

```http
  GET /api/gestores/{id}/despesas
```

| Parâmetro | Descrição              |
|:----------|:-----------------------|
| `id`      | Id do Gestor procurado |

Retorna as despesas do gestor especificado.
