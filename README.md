# Projeto Algalog-Api

## Sobre a API
Esse projeto foi desenvolvido na Semana Mergulho Spring Rest, promovido pela AlgaWorks. Esse projeto consistiu no desenvolvimento de uma API de logistica de entrega, construido com Java, SpringBoot e SpringFramework. 
O projeto foi desenvolvimento de forma básica, de acordo com o que foi proposto pelo autor do curso. 

## Importante! ⚠️ <br>
Estarei dando continuidade na implementação do projeto, realizando melhorias e integrando novas funcionalidades.

## Endpoints

Esta API fornece os seguintes endpoints: 

#### Clientes
- Lista todos os clientes: `GET/clientes`
- Adiciona cliente: `POST/clientes`
- Busca por nome de cliente: `GET/clientes/buscaPorNome`
- Busca por ID de cliente: `GET/clientes/1`
- Atualiza um cliente: `PUT/clientes/1`
- Deleta um cliente: `DELETE/clientes/1`

#### Entrega
- Lista todas as entregas:  `GET/entregas`
- Solicita entrega: `POST/entregas`
- Busca entregas por ID: `GET/entregas/2`
- Alterar dados de solicitação de entrega: `PUT/entregas/2`
- Finaliza uma entrega: `PUT/entregas/2/finalizacao`

#### Ocorrencias de entrega
- Lista todas as ocorrencias de entrega:  `GET/entregas/2/ocorrencias`
- Registra entrega:  `POST/entregas/2/ocorrencias`

## Tecnologias usadas

Este projeto foi desenvolvido com as seguintes tecnologias:

- **SpringBoot 3.0.4**
- **Java 17 (Java Development Kit - JDK: 17.0.5)**
- **Maven**
- **Lombok**
- **Flyway** 
- **MySQL**
- **ModelMapper 3.0.0**

## Como executar o projeto

### Empacotando
Você pode está gerando um `jar` do projeto para executar a API. Para isso, você deve executar o seguinte comando:

```
mvn package
```

Ele irá gerar um pacote `.jar` no seguinte diretorio `MSR\algalog-api\target` como mostrado no exemplo: `algalog-api-0.0.1-SNAPSHOT`. 

### Executando
Para executar o projeto, você deve executar os seguinte comando:

```
java -jar algalog-api-0.0.1-SNAPSHOT 
```
