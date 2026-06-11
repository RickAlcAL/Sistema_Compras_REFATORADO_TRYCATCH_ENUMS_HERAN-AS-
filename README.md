# Sistema de Compras Refatorado

Este projeto representa a evolução de um sistema de compras tradicional, redesenhado para seguir os pilares da Programação Orientada a Objetos (POO) e garantir a robustez do software através de uma arquitetura limpa e resiliente.

## 🚀 Melhorias e Implementações

A refatoração teve como foco principal a manutenibilidade, legibilidade e segurança do código. As principais mudanças foram:

### 🔄 Herança e Polimorfismo
* **Especialização de Classes:** Criação de classes derivadas para reaproveitar código e definir comportamentos específicos (ex: diferenciação de tipos de produtos ou categorias de clientes).
* **Flexibilidade:** Redução de duplicação de código utilizando a classe base para gerenciar atributos comuns.

### 🔢 Enumerações (`Enums`)
* **Padronização:** Substituição de strings soltas e números mágicos por `Enums`.
* **Segurança de Tipos:** Controle estrito sobre o status do pedido (ex: `PENDENTE`, `PAGO`, `CANCELADO`) e tipos de pagamento, evitando estados inválidos no sistema.

### 🛡️ Tratamento de Exceções (`Try-Catch`)
* **Resiliência:** Implementação de blocos `try-catch` em pontos críticos do fluxo de compra (como validação de estoque e processamento de pagamento).
* **Exceções Personalizadas:** Criação de regras de negócio específicas que lançam erros controlados, impedindo que o sistema quebre abruptamente e oferecendo feedbacks claros ao usuário.

---

## 🛠️ Tecnologias Utilizadas

* Linguagem de Programação Java
* Conceitos Avançados de POO
* Gestão de Erros e Fluxos

---
