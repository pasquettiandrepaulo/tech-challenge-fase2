# tech-challenge-fase1
Tech-Challenge: Fase 1 - Arquitetura Hexagonal + Aplicação

Sistema backend monolítico para autoatendimento em uma lanchonete, desenvolvido em Java com Spring Boot, seguindo os princípios da Arquitetura Hexagonal (Ports & Adapters) e Domain-Driven Design (DDD).

## 📦 Funcionalidades

- Cadastro e listagem de produtos por categoria (Lanche, Acompanhamento, Bebida, Sobremesa)
- Criação de pedidos com status (Recebido, Em preparação, Pronto, Finalizado)
- Simulação de pagamento via API mockada (QR Code)
- Acompanhamento e finalização de pedidos
- Cadastro e identificação de clientes via CPF
- Gestão de produtos e pedidos via painel administrativo (API)

---

## 🧱 Arquitetura

- **Arquitetura Hexagonal (Ports & Adapters)**
- **Spring Boot 3**
- **Java 17**
- **MongoDB (via Docker)**
- **Swagger/OpenAPI para documentação de rotas**

---

## 🚀 Como rodar o projeto localmente

### Pré-requisitos

- [Java 17+](https://adoptium.net/)
- [Docker](https://www.docker.com/)
- [Maven](https://maven.apache.org/)

### 📮 Postman Collection

Você pode importar a collection no Postman para testar os endpoints da API:

[🔗 Clique aqui para acessar a collection](https://github.com/pasquettiandrepaulo/tech-challenge-fase1/raw/main/TechChallenge.postman_collection.json)

ou acessar o swagger localmente e ver a documentação pelo link [🔗 Documentação swagger](http://localhost:8080/swagger-ui/index.html)

### 🐳 Com Docker (recomendado)


```bash
# Clone o projeto
git clone https://github.com/pasquettiandrepaulo/tech-challenge-fase1.git
cd tech-challenge-fase1

# Suba a aplicação com Docker Compose
docker-compose up --build

