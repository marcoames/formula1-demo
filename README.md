# 🏎️ Formula 1 - Sistema de Gerenciamento de Pilotos

## 📝 Descrição
Sistema de gerenciamento de dados históricos de pilotos da Fórmula 1, desenvolvido com Spring Boot. 
A aplicação permite consultar, adicionar, atualizar e remover informações sobre pilotos da F1.

## ✨ Funcionalidades
- Consulta de pilotos por nome
- Filtragem por nacionalidade
- Busca por temporadas disputadas
- Visualização de estatísticas dos pilotos
- API REST para integração com outros sistemas

## 🚀 Tecnologias
- Java 21
- Spring Boot 3.4.3
- H2 Database
- Maven

## 📋 Pré-requisitos
- Java 21 
- Maven 3.6 

## 🔧 Instalação
1. Clone o repositório
```bash
git clone https://github.com/seu-usuario/formula1-demo.git
`````
2. Execute o projeto
```bash
mvn spring-boot:run
`````
## 🌐 Endpoints

- GET /driver - Lista todos os pilotos
- GET /driver?name={nome} - Busca pilotos por nome
- GET /driver?nationality={nacionalidade} - Filtra pilotos por nacionalidade
- GET /driver?name={nome}&nationality={nacionalidade} - Busca pilotos por nome filtrando por nacionalidade
- GET /driver?seasons={temporada} - Busca pilotos por temporada
- GET /driver?champion=true - Busca pilotos campeoēs
- GET /driver/{id} - Busca piloto por ID


## 📊 Exemplos de Uso
```bash
# Buscar todos os pilotos brasileiros
curl http://localhost:8080/driver?nationality=Brazilian

# Buscar piloto específico
curl http://localhost:8080/driver?name=Senna

# Buscar pilotos da temporada
curl http://localhost:8080/driver?seasons=2022
````

## 🔄 Melhorias Futuras

- Adicionar testes unitários
- Migrar para banco de dados PostgreSQL ou Mysql
- Containerizar aplicação com Docker
- Implementar documentação com Swagger/OpenAPI
- Desenvolver frontend em React

