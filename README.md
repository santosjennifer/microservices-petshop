![Last Commit](https://img.shields.io/github/last-commit/santosjennifer/microservices-petshop)

## Plataforma para Petshop

Responsável por cadastrar animais e tutores

### Repositório com os serviços eureka-server, gateway-server, ms-pet e ms-person

- **eureka-server:** responsável por registrar e gerenciar a comunicação dos demais microserviços (gateway-server, ms-person e ms-pet).
- **gateway-server:** responsável gerenciar as rotas do API Gateway.
- **ms-pet:** responsável por cadastrar e retornar os pets/animais e vincular aos tutores/pessoas.
- **ms-person:** responsável por cadastrar e retornar as pessoas/tutores dos pets.
  
### Tecnologia

- Java 21
- Maven
- Spring Boot 3.2.4
- MongoDB
- Docker

### Como executar o projeto:

1. Clone o repositório:
```
git clone git@github.com:santosjennifer/microservices-petshop.git
```

2. Na raiz do projeto, execute o comando:
```
docker-compose up
```

3. Acesse o Eureka Server:
```
http://localhost:8761/eureka
```
### Eureka Server
![image](https://github.com/santosjennifer/microservices-petshop/assets/90192611/607d02e5-eddd-4ad4-a1b0-ce1520c3a7fe)

4. Acesse o Swagger do serviço ms-person:
```
http://localhost:8031/swagger-ui/index.html#/
```
### Swagger Person API
![image](https://github.com/santosjennifer/microservices-petshop/assets/90192611/9ec9019c-a85f-49df-96b6-86cd8dbd12a1)

5. Acesse o Swagger do serviço ms-pet:
```
http://localhost:8001/swagger-ui/index.html#/
```
### Swagger Animal API
![image](https://github.com/santosjennifer/microservices-petshop/assets/90192611/a76607ef-27fc-4c16-8ad9-4c57572007a5)


