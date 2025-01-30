Definimos as especificações da API usando OpenAPI no capítulo anterior. As interfaces e modelos Java da API foram gerados pelo OpenAPI (Swagger Codegen). Neste capítulo, implementaremos o código da API em termos de lógica de negócios e persistência de dados. Aqui, lógica de negócios se refere ao código real estamos escrevendo para funcionalidades do domínio, que, no nosso caso, compreendem operações realizadas para e-commerce, como finalizar a compra do carrinho de compras.

Escreveremos serviços e repositórios para a implementação e adicionaremos hypermedia e tags de entidade (ETags) às respostas da API. 
O hypermedia As The Engine Of Application State (HATEOAS) será implementando usando Spring e Hypertext Application Language (HAL). HAL é um dos padrões para implementar HATEOAS. Outros são **Collection + JSON** e **JSON-LD**. Você usará HAL neste livro. Você pode encontrar um exemplo disso no primeiro exemplo da seção "Adicionando ETags às respostas da API", denotado pelo campo links. Vale ressaltar que o código fornecido consiste apenas nas linhas importantes e não no arquivo completo, por uma questão de brevidade.  

Este capítulo cobrirá:
- Visão geral para o serviço de design;
- Adicionando  componentes de repositório;
- Implementando hypermedia;
- Aprimorando o controlador com um serviço e HATEOAS;
- Adicionando ETags às respostas da API;
- Testando as APIs.

## Technical requirements
Para executar as instruções neste e nos próximos capítulos, precisaremos de algum cliente API REST, tal como *Insomnia* or *Postman*.

## Overvien of the service design
Vamos implementar uma arquitetura em camadas que compreender quatro níveis, a arquitetura em camada é um bloco fundamental no estilo de arquitetura conhecido como **Domain-Driven Design(DDD)**.
1. **Camada de apresentação** (**Presentation Layer**):  essa camada representa a interface do usuário (UI). No capítulo 7, vamos desenvolver a UI para o aplicativo de e-commerce de exemplo.

2. **Camada de aplicação (*Application layer*)**: a camada de aplicação contém a lógica da aplicação e mantém e coordena o fluxo geral do aplicativo. Apenas para lembrar, ela contém apenas a lógica da aplicação, e não a lógica de negócios. Serviços web RESTful, APIS assíncronas, APIs gRPC e APIs GraphQL fazem parte desta camada. Já cobrimos APIs REST e controladores no capítulo 3, *API Specifications and Implementation*, que fazem parte da camada de aplicação. Implementamos os controladores para fins de demonstração no capítulo anterior. Neste capítulo, implementaremos um controlador de forma mais extensa para fornecer dados reais. 

3. **Camada de domínio**: esta camada contém a lógica de negócio e as informações do domínio. Ela contém o estado dos objetos de negócios, como *order* ou *product*. É responsável por ler/persistir esses objetos na camada de infraestrutura. A camada de domínio também inclui serviços e repositórios. Também abordaremos esses conceitos neste capítulo. 

4. **Camada de infraestrutura:** a camada de infraestrutura fornece suporte a todas as outras camadas. Ela é responsável pela comunicação, como a interação com o banco de dados, *message brokers* e sistemas de arquivos. O Spring Boot atua como uma camada de infraestrutura e fornece suporte para comunicação e interação com sistemas externos e internos, como bancos de dados e *message brokers*.

Nós usaremos a abordagem bottom-to-top. Vamos começar implementando a *camada de domínio* com o componente *@Repository*.

## Adding a Repository component
Usaremos a abordagem de baixo para cima para adicionar um componente *@Repository*. Vamos começar implementando a camada de domínio com um componente *@Repository*. Posteriormente, implementaremos o serviço e aprimoraremos o componente *@Controller* de acordo com as seções seguintes. Primeiro, codificaremos o componente *@Repository* e, em seguida, o utilizaremos no componente *@Service* por meio de injeção via construtor. O componente *@Controller* será aprimorado utilizando o componente *@Service*, que também será injetado no Controller por meio de injeção via construtor.

---
**Injeção via construtor**
A injeção via construtor é uma forma de implementar *injeção de dependência* em que as dependências de uma classe são fornecidas a ela por meio de seu construtor. É um dos padrões mais recomendados para gerenciar dependências em aplicações orientadas a objetos, especialmente no uso de frameworks como Spring.

**Como funciona?**
1. **Dependência como parâmetro do construtor:** a classe que precisa de outra classe (a dependência) declara essa dependência como um parâmetro no construtor. 
2. **Fornecimento da dependência:** o framework (como o Spring) ou o próprio desenvolvedor  passa a instância necessária no momento da criação do objeto.

**Exemplo:**
Imagine que tenhamos um serviço chamado *PlanetService* que depende de um repositório chamado *PlanetRepository:*
```java
@Repository
public class PlanetRepository {
	public List<String> findAllPlanets() {
		return List.of("Mercury", "Venus", "Earth");
	}

}
```

Aqui está a classe de serviço com injeção via construtor
```java
@Service
public class PlanetService {
	private final PlanetRepository planetRepository;

	// construtor  que recebe o repositório como depe
	public PlanetService(PlanetRepository planetRepository) {
		this.planetRepository = planetRepository;
	}

	public List<String> getAllPlanets() {
		return planetRepository.findAllPlanets();
	}
}
```

No caso do Spring, ele faz isso automaticamente ao criar os objetos, desde que as anotações como *@Repository*, *@Service* e *@Autowired* sejam usadas (o Spring sabe onde injetar). O código para o controller seria assim:
```java
@RestController
@RquestMapping("/planets")
public class PlanetController {
	private final PlanetService planetService;

	// injeção via construtor
	public PlanetController(PlanetService planetService) {
		this.planetService = planetService;
	}

	@GetMapping
	public List<String> getPlanets() {
		return planetService.getAllPlanets();
	}

}
```

Portanto, a injeção de dependência permite que as dependências de um componente (como classes, serviços ou repositórios) sejam fornecidas externamente , em vez de serem criadas diretamente dentro do componente. Isso promove um código mais modular, testável e de fácil manutenção.

**Como a Injeção de Dependência Funciona**
1. **Componente *@Repository***:
Este componente é responsável por interagir com a camada de dados (Bando de dados, APIs externas, etc).
Ele é marcado com a anotação *@Repository* (em frameworks como Spring), indicando que é um bean gerenciado pelo contêiner de IoC (Inversão de Controle). 
```java
import org.springFramework.stereotype.Repository;

@Repository
public class UserRepository {
	// Métodos para acessar o banco de dados
	public User findById(Long id) {
		// Lógica para buscar um usuário no banco
		return new User(id, "John Doe");
	}
}
```

2. **Camada de Serviço *@Service***
Este componente contém a lógica de negócio da aplicação. Ele depende do *@Repository* para acessar os dados. Em vez de criar uma instância do *@Repository* diretamente dentro do *@Service*, o *@Repository* é injetado diretamente dentro do *@Service*, o *@Repository* é injetado no *@Service* via construtor (ou outro método, como setter ou campo).
```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	private final UserRepository userRepository;

	@Autowired // injeção de dep. via construtor
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User getUserById(Long id) {
		return userRepository.findById(id);
	}
}
```

3. **Camada Controller *@Controller***
Este componente lida com as requisições HTTP e coordena a interação entre a camada de apresentação e a camada de serviço. Ele depende do *@Service* para executar a lógica de negócio. O *@Service* é injetado no *@Controller* via construtor:
```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
	private final UserService userService;

	@Autowired // injeção de dependência via construtor
	public userController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/{id}")
	public User getUser(@PathVariable Long id) {
		return userService.getUserById(id);
	}
}
```

## The @Repository annotation
Os componentes de repositório são classes Java marcadas com a anotação *@Repository.* Esse é um componente especial do Spring usado para interagir com banco de dados.

*@Repository* é um estereótipo de uso geral que representa tanto o padrão de Repositório do DDD (Domain-Driven Design) quanto o padrão DAO (Data Acess Object) da Java Enterprise Edition (JEE). Desenvolvedores e equipes devem gerenciar objetos de repositório com base na abordagem utilizada.

No DDD, um repositório é um objeto central que mantém referências a todos os objetos e deve retornar a referência de um objeto solicitado. Antes de começar a escrever classes marcadas com *@Repository*, é necessário garantir que todas as dependências e configurações necessárias estejam configuradas corretamente.

As seguintes bibliotecas serão usadas como dependências do banco de dados:
- **Banco de dados H2:** para persistência de dados: utilizaremos uma instância em memória do H2, mas também é possível usar uma instância baseada em arquivo;
- **Hibernate (ORM - Object Relational Mapping)** - para mapeamento de objetos do banco de dados.
- **Flyway para migração de banco de dados:** ajuda a gerenciar o banco de dados, mantendo um histórico de alterações, permitindo rollbacks, atualizações de versão e outras funcionalidades.

Vamos adicionar essas dependências ao arquivo *build.gradle*;
O pacote *org.springframework.boot:spring-boot-starter-data-jpa* inclui todas as dependências necessárias do JPA, incluindo o Hibernate.

## Configurando o banco de dados e o JPA
Também precisamos modificar o arquivo *application.properties* com a seguinte configuração.

## Configuração do Datasource
A seguir, estão as configurações do datasource no Spring:
```properties
spring.datasource.name=ecomm
spring.datasource.url=jdbc:h2:mem:ecomm;DB_CLOSE_DELAY=-1;IGNORECASE=TRUE;DATABASE_TO_UPPER=false
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

```

Precisamos adicionar propriedades específicas do H2 ao datasource. O valor da URL indica que será usada uma instância de banco de dados H2 baseada em memória.

**Configuração do banco de dados H2**
```properties
# h2 configuration  
spring.h2.console.enabled=true  
spring.h2.console.settings.web-allow-others=false
```
Aqui, o **H2 Console** é um cliente web do H2 que permite realizar diversas operações, como visualizar tabelas e executar consultas.

O console do H2 está habilitado apenas para acesso local, o que significa que só podemos acessá-lo no *localhost*. Além disso, o acesso remoto está desativado definindo web-allow-other como false.

**Configuração do JPA/Hibernate**
```properties
spring.jpa.properties.hibernate.default_schema=ecomm
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.show-sql=true
spring.jpa.format_sql=true
spring.jpa.generate-ddl=false
spring.jpa.hibernate.ddl-auto=none

```
Não queremos que o Hibernate gere o DDL ou processe arquivos SQL, pois utilizaremos o *Flyway* para gerenciar as migrações do banco de dados.

Por isso, *generate-ddl* está definido como *false* e *ddl-auto* está definido como *none*.

## The database and seed data script
Agora que terminamos de configurar os arquivos *build.gradle* e *application.properties*, podemos começar a escrever código. Primeiro, adicionaremos o script de migração de banco de dados com o Flyway.

Esse script deve ser escrito apenas em SQL e deve ser colocado no diretório *db/migration*, dentro de `sr/main/resources`. Seguiremos a convenção de nomenclatura do Flyway.

```sql
CREATE SCHEMA IF NOT EXISTS ecomm;

CREATE TABLE IF NOT EXISTS ecomm.product (
    id UUID NOT NULL DEFAULT random_uuid(),
    name VARCHAR(56) NOT NULL,
    description VARCHAR(200),
    price NUMERIC(16, 4) DEFAULT 0 NOT NULL,
    count NUMERIC(8, 0),
    image_url VARCHAR(40),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS ecomm.tag (
    id UUID NOT NULL DEFAULT random_uuid(),
    name VARCHAR(20),
    PRIMARY KEY (id)
);

-- Other code is removed for brevity
```
Este script criou um schema ecomm e adicionou todas as tabelas necessárias para o nosso app simples de e-commerce. Também adiciona instruções *Insert* para os dados iniciais.

Logo, o código é um conjunto de instruções SQL para a criação de um banco de dados de e-commerce, com várias tabelas para gerenciar produtos, usuários, pedidos, pagamentos, etc. Inserimos dados simulados para popular essas tabelas, representando um catálogo de produtos, tags associadas aos produtos e informações de usuários. Além disso, há a criação de um sistema de pagamento, envio de pedidos e associação entre produtos e usuários através de endereços e cartões de crédito. 

## Adding entities
Agora, podemos adicionar as entidades. Uma entidade é um objeto especial marcado com a anotação *@Entity* que mapeia diretamente para a tabela do banco de dados utilizando uma implementação de ORM como o Hibernate. Outro ORM popular é o EclipseLInk. Podemos colocar todos os objetos de entidade no pacote `com.packt.modern.api.entity`.
Vamos criar o arquivo `CartEntity.java`.
