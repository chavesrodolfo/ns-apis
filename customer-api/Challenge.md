# API de Clientes

## Expor um serviço de Sócio Torcedor

**Cadastro do Cliente:**

* Nome Completo;
* E-mail;
* Data de Nascimento;
* Meu Time do Coração;

### Regras de negócio

- Dado um E-mail que já existe, informar que o cadastro já foi efetuado, porém, caso o cliente não tenha nenhuma campanha associada, o serviço deverá enviar as novas campanhas como resposta;
- O Cliente não pode ter mais de um cadastro ativo;
- Ao efetuar o cadastro do usuário, utilize os serviços criados anteriormente para efetuar as operações e as associações necessárias:
    - O Cadastramento do cliente ocorre antes da associação com as campanhas, ou seja,
o processo de cadastro e associação ocorre em dois momentos separados;
    - O Usuários pode ter N Campanhas associadas a ele; Lembrando que as campanhas
são associadas ao Time do Coração;
    - A associação do usuário as campanhas podem ocorrer em dois momentos:
        - Se for usuário novo: Após o cadastramento do usuário, o sistema deverá solicitar as campanhas ativas para aquele time do coração e efetuar a associação;
        - Se for um usuário já cadastrado: Deverá ser feita a associação das campanhas novas, ou seja, as que o usuário daquele time do coração não tem relacionamento até o momento.
    - O Consumo das listas das campanhas deve ser feita via Serviço exposto conforme descrito no exercício anterior (API de Campanhas);
    - O Cadastramento das campanhas deverá ser feito via Serviço (API, conforme descrito no exercício anterior)
    - O Cadastramento não pode ser influenciado pelo serviço das campanhas, caso o serviço das campanhas não esteja “UP”;