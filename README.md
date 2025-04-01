# Projeto CRUD com Integração ViaCEP

Este projeto foi desenvolvido como parte da minha **M1**, a primeira média da minha faculdade. Ele consiste em uma API REST construída com **Spring Boot**, que realiza operações CRUD e integra a API ViaCEP para obter informações de endereço.

## Tecnologias Utilizadas
- **Java 17**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **PostgreSQL**
- **Flyway**
- **Lombok**
- **Postman** 
- **ViaCEP API**

## Como Executar o Projeto

1. **Clone o repositório:**
   ```sh
   git clone https://github.com/seu-usuario/atividade-m1-viacep.git
   cd atividade-m1-viacep
   ```

2. **Certifique-se de que o PostgreSQL está instalado e rodando**. Configure as credenciais no arquivo ``application.properties``: 

    ```sh
    spring.datasource.url=jdbc:postgresql://localhost:5432/product
    spring.datasource.username=[seu usuario]
    spring.datasource.password=[sua senha]
    ```

3. **Executar o projeto:**
   ```sh
   mvn spring-boot:run
   ```


4. **Teste no Postman**
Importe a collection localizada em: 

`postman_collection/ViaCep Requests.postman_collection.json`

## Endpoints da API

### 1. Teste com a resposta **true**

Faça uma requisição **GET** para o endpoint:

`GET http://localhost:8080/product/checkCityMatch?cep=08773380&productId=p1`

Resposta esperada:

![teste](https://i.imgur.com/diF3kKy.png)

### 2. Teste com a resposta **false**

Faça uma requisição **GET** para o mesmo endpoint:

`GET http://localhost:8080/product/checkCityMatch?cep=08773380&productId=p2`

Resposta esperada:

![teste](https://i.imgur.com/2vWyB05.png)

### 3. Teste com o ViaCEP

Faça uma requisição **GET** para o endpoint 

`GET https://viacep.com.br/ws/{{cep}}/json/` com o valor do parâmetro `cep` sendo `08773380`.

Resposta esperada:

![teste](https://i.imgur.com/cck8GsF.png)
