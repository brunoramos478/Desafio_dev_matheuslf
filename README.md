# Desafio dos inscritos elaborado pelo Youtuber Matheuslf 🚀

## Para esse desafio foi proposto implementar um sistema para gerenciar projetos, tarefas e demandas de uma empresa.

## 1 Controller 🌐

### *ProjectController*

#### Endpoint de acesso: http://localhost:8065/projects
#### Métodos

```
RequestMapping("/projects")

Metodos 

PostMapping()
GetMapping()
@DeleteMapping("/{id}")
```

#### JSON
```
{
  "name": "Viagem para o Caribe",
  "description": "Planejamento e organização de uma viagem para o Caribe, incluindo reservas de voos, hotéis e atividades turísticas.",
  "startDate": "2026-07-01",
  "endDate": "2026-07-25"
}
```

### *TaskController*

#### Endpoint de acesso: http://localhost:8065/tasks
#### Métodos

```
RequestMapping("/tasks")

Metodos 

PostMapping()
PutMapping("/{id}")
DeleteMapping("/{id}")
```

#### JSON

```
{
  "title": "Degustar um Drink Tropical",
  "description": "Chegada ao destino. Arquipélago de Turks e Caicos",
  "status": "TODO",
  "priority": "HIGH",
  "projectId": "ID_DO_PROJETO"
}

```

***


## 2 Dtos

###### Para os Dtos preferir utilizar o record envéz de classes tradicionais, pois o record já possui getters nativos e outra funcionalidade interresante é que os dados são imutáveis, sem falar quer o boilerplate é reduzido drasticamente. 
### *ProjectDto*
#### Campos 
````
String name,
String description,
LocalDate startDate,
LocalDate endDate
````

### *TaskDto*
#### Campos 

```
String title,
String description,
StatusTask status,
PriorityTask priority,
UUID projectId
```

***

## 3 Handler

### *GlobalHandlerException*
###### Para o tratamento de erros com o *`ControllerAdvice`* foi escolhido o *`ProblemDetail`* que oferece uma estrutura robusta e padronizada segindo especificações internacional a famosa *`RFC 7807`* para o tratamento de erros em APIs RESTful, isso garante uma comunicação clara e consistente dos erros para os clientes da API, facilitando a depuração e a resolução de problemas.

***

## 4 Service

### *ApplicationService*
###### O service engloba toda a questão lógica da aplicação, garantindo responsábilidade única *`SRP`*

***

## 5 Exceptions 

### *ProjectNotFound*
###### É acionada quando o Projeto não é encontrado.

### *InvalidDate*
###### É acionada quando a Data é inválida ou mal formada 

### *TaskNotFound*
###### É acionada quando a Tarefa não é encontrada.

***

## Como Rodar 🛞💨

### 1 Clone o repositorio.
````bash
git clone https://github.com/brunoramos478/Desafio_dev_matheuslf.git
````

### 2 Crie e defina as propriedades do DB no application.properties.

````bash
spring.datasource.driver-class-name=DRIVE_DO_POSTGRE
spring.datasource.url=URL_DO_POSTGRE
spring.datasource.username=USERNAME_POSTGRE
spring.datasource.password=PASSWORD_POSTGRE
spring.threads.virtual.enabled=true

spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
````
### 3 Rode o comando.

````bash
mvn spring-boot:run
````

***

## Outra forma de rodar o mesmo via Docker 🐋

### 1 Clone o repositorio.
````bash
git clone https://github.com/brunoramos478/Desafio_dev_matheuslf.git
````

### 2 Configure sua application.properties

````bash
# Exemplo de configuração para o Docker caso queira pode usar esse modelo aqui para agilizar o tempo.
spring.datasource.url=jdbc:postgresql://${DB_HOST:localhost}:${DB_PORT:5433}/${DB_NAME:postgres}
spring.datasource.username=${DB_USER:postgres}
spring.datasource.password=${DB_PASSWORD:postgres}
spring.datasource.driver-class-name=org.postgresql.Driver
spring.threads.virtual.enabled=true

spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
````

### 3 Builde todo o projeto.
````bash
docker compose build --no-cache .
````
### 4 Rode o projeto.
````bash
docker compose up -d
````
***

## 5 Ou, se preferir, use esse comando que já builda o projeto ao todo, além de criar o container, já roda tudo de forma automática.
````bash
docker compose build --no-cache; docker compose up -d
````

***

## Tecnologias usadas ⚙️⚒️
### `Java 25`
###### Motivo do uso do java 25 foi escolhido pela familiaridade que possuo e modernidade sendo bem eficiente em performance em relação as versoes anteriores.

### `Spring Boot 4.0.6`
###### Versão bem recente ainda mesmo atualmente já tendo a versão 4.1.0 disponível. Essa versão oferece performance aprimorada como por exemplo builds mais rápidos segurança aprimorada refinamentos no tomcat

### `Maven`
###### Gerenciador de dependências do spring.

### `PostgreSQL`
###### Para a persistência dos dados utilizei o PostgreSQL assim como o enunciado pede.

## Extras ➕

### `Docker`
###### Aplicação dockerizada já pronta pra uso.

### `Virtual Threads`
###### Threads virtuais muito mais leves em comparação com as trádicionais garantindo uma preservação de recursos muito maior.

### `MapStruct`
###### Optei pelo MapStruct, porque ele oferece menos boilerplate no mapper da aplicação em relação ao mapper feito à mão. Outra vantagem é que ele não usa reflection, porque é baseado em tempo de compilação. O que ele faz por baixo dos panos é gerar um código como se fosse feito à mão, mas só que esse código gerado vai para target.

### `OpenSwagger`
###### Documentação da aplicação.

### `SonarQube`
###### O código foi analisado no SonarQube para maior qualidade, tornando mais limpo, legível, profissional e mais tolerante a possíveis falhas.

### `Workflow CI`
###### Não era necessário, só implementei porque já tinha um modelo pré-definido, só fiz alguns microajustes para a implementação do workflow dar certo nesse repo.
***

## Link do desafio: 
### https://github.com/matheuslf/dev.matheuslf.desafio.inscritos