# 💰 Gerenciador Financeiro Pessoal

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Status](https://img.shields.io/badge/Status-Em_Desenvolvimento-yellow?style=flat-square)

Um sistema de controle de gastos pessoais via console, desenvolvido para aplicar conceitos de Orientação a Objetos em Java. O projeto permite organizar despesas, gerenciar categorias e visualizar extratos detalhados.

## 📋 Funcionalidades

### 1. Gestão de Transações

- **Cadastro Detalhado:** É possível registrar gastos informando nome, data, horário, valor e categoria.
- **Lógica de Pagamento:** O sistema identifica automaticamente o tipo de pagamento:
  - Se houver **parcelas**: Considera como **Crédito**.
  - Se **não** houver parcelas: Considera como **Débito/PIX**.
- **Filtros e Buscas:** Localização de gastos específicos por categoria.

### 2. Gestão de Categorias

- **Organização:** Toda transação deve estar vinculada a uma categoria existente.
- **Criação Flexível:** Se a categoria não existir, o usuário pode criar uma nova informando:
  - Nome (Obrigatório)
  - Descrição (Opcional - breve texto explicativo)
- **Listagem:** Visualização de todas as categorias cadastradas.

### 3. Relatórios e Extratos

- **Extrato Geral:** Visualização de todas as movimentações.
- **Extrato Mensal:** Filtragem de transações por mês específico.
- **Agrupamento:** O extrato organiza os gastos separando-os por suas respectivas categorias.

## 🧩 Diagrama UML

```mermaid
classDiagram
    class Main {
        +main(args: String[]) void
    }

    class GerenciadorFinanceiro {
        -categorias: List~Categoria~
        +adicionarTransacao(nome: String, valor: BigDecimal, nomeDaCategoria: String) void
        +adicionarTransacao(nome: String, valor: BigDecimal, nomeDaCategoria: String, parcelas: int) void
        +adicionarTransacao(nome: String, valor: BigDecimal, nomeDaCategoria: String, descricao: String) void
        +adicionarTransacao(nome: String, valor: BigDecimal, nomeDaCategoria: String, parcelas: int, descricao: String) void
        +gerarExtrato(mes: int) List~String~
        +exibirCategorias() List~String~
        +criarCategoria(nome: String) void
        +criarCategoria(nome: String, texto: String) void
        +removerCategoria(nomeDaCategoria: String) void
        +sair() void
    }

    class Categoria {
        -nome: String
        -descricao: String
        -transacoes: List~Transacao~
        +Categoria(nome: String)
        +Categoria(nome: String, descricao: String)
        +adicionarTransacao(nome: String, valor: BigDecimal) void
        +adicionarTransacao(nome: String, valor: BigDecimal, parcelas: int) void
        +adicionarTransacao(nome: String, valor: BigDecimal, descricao: String) void
        +adicionarTransacao(nome: String, valor: BigDecimal, parcelas: int, descricao: String) void
        +getNome() String
        +getTransacoes(mes: int) List~String~
        +toString() String
        +equals(o: Object) boolean
    }

    class Transacao {
        -nome: String
        -valor: BigDecimal
        -parcelas: int
        -descricao: String
        +Transacao(nome: String, valor: BigDecimal)
        +Transacao(nome: String, valor: BigDecimal, descricao: String)
        +Transacao(nome: String, valor: BigDecimal, parcelas: int, descricao: String)
        +Transacao(nome: String, valor: BigDecimal, parcelas: int)
        +getValor() BigDecimal
        +toString() String
    }

    Main --> GerenciadorFinanceiro : usa
    GerenciadorFinanceiro "1" *-- "*" Categoria : tem
    Categoria "1" *-- "*" Transacao : tem
