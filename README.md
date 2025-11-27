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

![Diagrama UML](./docs/uml.png)
