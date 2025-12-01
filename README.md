# 💰 Gerenciador Financeiro Pessoal

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Status](https://img.shields.io/badge/Status-Em_Desenvolvimento-yellow?style=for-the-badge)

Um sistema de controle de gastos pessoais via console, desenvolvido para aplicar conceitos fundamentais de **Orientação a Objetos** em Java. O projeto permite organizar despesas, gerenciar categorias dinamicamente e visualizar extratos detalhados com precisão monetária.

---

## 📋 Funcionalidades

O sistema foi projetado com uma arquitetura que separa as responsabilidades entre o gerenciador, as categorias e as transações individuais.

### 1. Gestão de Transações
* **Cadastro Flexível:** Graças à sobrecarga de métodos, é possível registrar gastos de formas variadas:
    * Apenas com nome, valor e data/hora.
    * Com descrição detalhada opcional.
    * Com parcelamento (lógica de crédito).
* **Tipos de Dados Precisos:** Utilização de `BigDecimal` para evitar erros de arredondamento em valores monetários e `Java Time API` (`LocalDate`, `LocalTime`) para registros temporais.
* **Lógica de Pagamento:**
    * Com parcelas: O sistema entende como **Crédito**.
    * Sem parcelas: O sistema entende como **Débito/PIX**.

### 2. Gestão de Categorias
* **Associação Obrigatória:** Toda transação pertence a uma `Categoria`, garantindo organização (Composição).
* **Criação Dinâmica:** O usuário pode criar novas categorias (ex: "Alimentação", "Lazer") com descrições personalizadas.
* **Remoção:** Funcionalidade para remover categorias que não são mais necessárias.

### 3. Relatórios e Extratos
* **Extrato Mensal:** O método `gerarExtrato(int mes)` filtra e exibe apenas as movimentações do mês solicitado.
* **Visualização por Categoria:** O sistema permite listar gastos agrupados, facilitando a análise de onde o dinheiro está sendo gasto.

---
## 🛠️ Tecnologias Utilizadas

* **Java:** Linguagem principal do projeto.
* **Java Time API:** Uso de `LocalDate` e `LocalTime` para gestão temporal precisa.
* **BigDecimal:** Para alta precisão em cálculos financeiros (evitando erros de ponto flutuante).
* **Conceitos de OO:** Aplicação prática de Encapsulamento, Composição, Sobrecarga e Listas.

---

## 🚀 Como Executar o Projeto

Pré-requisitos: Ter o [Java JDK](https://www.oracle.com/java/technologies/downloads/) instalado.

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/denilsonqrs/Gerenciador-Financeiro
2. **Acesse a pasta do projetoÇ**
   ```bash
   cd Gerenciador-Financeiro
3. **Compile os arquivos:**
   ```bash
   javac *.java
4. **Execute os arquivos:**
   ```bash
   java Main

## 🧩 Estrutura do Projeto (Diagrama de Classes)

Abaixo está a representação da arquitetura do sistema. O `GerenciadorFinanceiro` atua como a classe controladora principal, compondo uma lista de `Categorias`, que por sua vez armazenam as `Transações`.

```mermaid
classDiagram
    class Main {
        +main(args: String[]) void
    }

    class GerenciadorFinanceiro {
        -categorias: List~Categoria~
        +adicionarTransacao(nome: String, valor: BigDecimal, data: LocalDate, hora:LocalTime, nomeDaCategoria: String) void
        +adicionarTransacao(nome: String, valor: BigDecimal, data: LocalDate, hora:LocalTime, nomeDaCategoria: String, parcelas: int) void
        +adicionarTransacao(nome: String, valor: BigDecimal, data: LocalDate, hora:LocalTime, nomeDaCategoria: String, descricao: String) void
        +adicionarTransacao(nome: String, valor: BigDecimal, data: LocalDate, hora:LocalTime, nomeDaCategoria: String, parcelas: int, descricao: String) void
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
        +adicionarTransacao(nome: String, valor: BigDecimal, data: LocalDate, hora:LocalTime) void
        +adicionarTransacao(nome: String, valor: BigDecimal, data: LocalDate, hora:LocalTime, parcelas: int) void
        +adicionarTransacao(nome: String, valor: BigDecimal, data: LocalDate, hora:LocalTime, descricao: String) void
        +adicionarTransacao(nome: String, valor: BigDecimal, data: LocalDate, hora:LocalTime, parcelas: int, descricao: String) void
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
        -data: LocalDate
        -hora: LocalTime
        +Transacao(nome: String, valor: BigDecimal, data: LocalDate, hora:LocalTime)
        +Transacao(String, valor: BigDecimal, data: LocalDate, hora:LocalTime, descricao: String)
        +Transacao(String, valor: BigDecimal, data: LocalDate, hora:LocalTime, parcelas: int, descricao: String)
        +Transacao(String, valor: BigDecimal, data: LocalDate, hora:LocalTime, parcelas: int)
        +getMes():int
        +getValor() BigDecimal
        +toString() String
    }

    Main --> GerenciadorFinanceiro : usa
    GerenciadorFinanceiro "1" *-- "*" Categoria : tem
    Categoria "1" *-- "*" Transacao : tem
