# NS APIs

Projeto de desenvolvimento de APIs de Campanhas e Clientes, separadas em diferentes microserviços. A descrição dos desafios estão descritos abaixo:
* [Campaign Challenge](campaign-api/Challenge.md)
* [Customer Challenge](customer-api/Challenge.md)

# Uso
Coloque cada uma das APIs em execução utilizando o comando `mvn spring-boot:run`.

*Informações sobre as tecnologias utilizadas:*

* **Java 8** como linguagem;
* **Maven** para o processo de build;
* **Spring Boot** para criação dos microserviços;
* **H2** como base de dados, com armazenamento em disco;
* **Lombok** para melhorar a verbosidade do código;
* **HttpCliente** para comunicação entre os microserviços;
* **Mockito** para os testes automatizados.

### API de Campanha

Endpoints:

* Criação de campanhas: *POST* `http://localhost:8080/api/campaigns`
* Listagem de camapanhas ativas: *GET* `http://localhost:8080/api/campaigns`
* Obter dados de uma campanha: *GET* `http://localhost:8080/api/campaigns/{id}`
* Apagar uma campanha: *DELETE* `http://localhost:8080/api/campaigns/{id}`
* Atualizar uma campanha: *PUT* `http://localhost:8080/api/campaigns/{id}`

### API de Cliente

Endpoints:

* Criação de cliente: *POST* `http://localhost:8081/api/customers`
* Associação de cliente com campanha: *PATCH* `http://localhost:8081/api/customers/updatecampaigns`
* Listagem de clientes e suas campanhas: *GET* `http://localhost:8081/api/customers`
