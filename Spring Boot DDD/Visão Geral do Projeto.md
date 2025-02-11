O projeto é uma aplicação Spring Boot que segue os princípios do **Domain-Driven Design (DDD)**, uma abordagem para desenvolver software complexo focando no domínio (área de negócio) e na linguagem ubíqua (termos comuns entre desenvolvedores e especialistas do domínio). O DDD é aplicado aqui através da divisão do código em camadas e da organização em pacotes que refletem os conceitos do domínio.

## Estrutura do Projeto
1. **Camada do DDD**
O projeto está organizado em camadas, que são uma parte fundamental do DDD:
- #domain: contém a lógica de negócio central (o "coração" da aplicação). Aqui estão as entidades, value objects, agregados, serviços de domínio e regras de negócio. Exemplo: *Customer*, *Order*, *Product* (entidades que representam conceitos do domínio). Essa camada **não depende de frameworks ou tecnologias externas.**

- #application: coordena as operações do domínio e orquestra os fluxos de trabalho. Aqui estão os serviços de aplicação que expõem a funcionalidade do domínio para o mundo externo. Exemplo: *CustomerService*, *OrderService*...

- #infrastructure: implementa detalhes técnicos, como acesso ao banco de dados, mensageria, APIs externas, etc. Exemplo: *Repositórios JPA* *CustomerRepository*, *OrderRepository*, configurações do Spring, etc.

- #interfaces: expõe a aplicação para o mundo externo, como APIs REST, interfaces gráficas, etc. Exemplo: *Controladores REST* (*CustomerController*, *OrderController*)/

2. **Pacotes Principais**
- **Domain:** contém as entidades, value objects e serviços de domínio; 
- **application:** contém os serviços de aplicação e casos de uso;
- **infrastructure:** contém implementações técnicas, como repositórios e configurações;
- **interfaces:** contém controladores REST e DTOs (Data Transfer Objects).

1. **Tecnologias Utilizadas**
- **Spring Boot:** Framework principal para construir a aplicação;
- **Spring Data JPA:** Para acesso ao banco de dados e repositórios;
- **H2 Database:** Banco de dados em memória usado para testes e desenvolvimento;
- **Lombok:** Para reduzir boilerplate code (getters, setters, construtores, etc);
- **DTOs**: usados para transferir dados entre as camadas (especialmente entre a camada de interfaces e a aplicação);

## O que o projeto faz?
O projeto é um exemplo de uma aplicação de e-commerce simplificada, com foco em *clientes (Customers)*, *pedidos (Order)* e *Produtos (Product)*. Aqui estão alguns dos fluxos de trabalho:

1. **Gerenciamento de Clientes:** criar, buscar, atualizar e excluir cliente; Exemplo: *CustomerController*  expõe endpoints REST para operações CRUD. 
2. **Gerenciamento de Pedidos:** Criar pedidos associados a clientes e produtos. Exemplo: *OrderController* expõe endpoints para criar e buscar pedidos. 
3. **Gerenciamento de Produtos:** Adicionar e buscar produtos. Exemplo: *ProductController* expõe endpoints para operações com produtos.

## Como o DDD é Aplicado?
1. **Entidades e Agregados:** As entidades (como *Customer*, *Order*, *Product*) são modeladas para refletir os conceitos do domínio. Agregados são usados para garantir consistência e encapsular regras de negócio. Um **agregado** é um grupo de entidades que são tratadas como uma única unidade de consistência. Ele tem um agregados raiz, que controla o acesso às outras entidades dentro dele.
📌 **Exemplo Prático: Pedido (`Order`) e Itens do Pedido (`OrderItem`)**
- Um Pedido (Order) pode ter vários *Itens* (*OrderItem*);
- O *Pedido* é o Agregado Raiz e controla a consistência dos itens.
- Um *Item do Pedido* só pode existir dentro de um *Pedido* (não pode ser acessado diretamente).

2. **Serviços de Domínio**: Lógica de negócio complexa é encapsulada em serviços de domínio. Exemplo: regras para criar um pedido ou calcular o total de um pedido.

3. **Repositórios**: interfaces de repositório são definidas na camada de domínio, mas implementadas na camada de infraestrutura. Exemplo: *CustomerRepository* é implementado usando Spring Data JPA.
4. **Separação de Camadas:** O projeto segue a separação clara entre domínio, aplicação, infra e interfaces.

---
## 1. O que é Command Pattern?
O Command Pattern é um padrão de design comportamental que encapsula uma solicitação como um objeto. Isso permite que a gente parametrize clientes com diferentes solicitações, enfileire ou registre log solicitações e suporte operações que podem ser desfeitas. 

- **Exemplo Prático:** imagine um sistema onde temos ações como "CriarPedido", *CancelarPedido*, *AtualizarPedido*. Cada uma dessas ações pode ser encapsulada em um objeto #Command.
- **Vantagem:** isso desacopla quem invoca a ação (quem chama o comando) de quem executa a ação (quem processa o commando). 
### **2. O que é CQRS?**
O **CQRS (Command Query Responsibility Segregation)** é um padrão de arquitetura que separa as operações de leitura (queries) e escrita (commands) em um sistema. Em vez de usar o mesmo modelo para ler e escrever dados, você tem modelos separados:

- **Command**: Responsável por alterar o estado do sistema (criar, atualizar, deletar).
    
- **Query**: Responsável por consultar o estado do sistema (ler dados).

### **3. Por que isso pode ser considerado over-engineering?**

O uso de **Command Pattern** ou **CQRS** pode ser visto como **over-engineering** (complexidade desnecessária) em cenários onde:
1. **O sistema é simples**:
    
    - Se o seu sistema tem operações básicas de CRUD (Create, Read, Update, Delete) e não precisa de uma separação complexa entre leitura e escrita, adicionar Command Pattern ou CQRS pode ser exagero.
        
    - Exemplo: Um sistema de cadastro de clientes simples não precisa de CQRS.
        
2. **Custo de manutenção**:
    
    - Esses padrões introduzem camadas adicionais de complexidade, o que pode aumentar o custo de manutenção.
        
    - Exemplo: Manter handlers de comandos, eventos, e modelos separados para leitura e escrita pode exigir mais tempo e mão de obra.
        
3. **Custo de infraestrutura**:
    
    - Em sistemas que usam CQRS, muitas vezes é necessário manter bancos de dados separados para leitura e escrita, o que pode aumentar os custos de servidor e infraestrutura.
        
4. **Mão de obra especializada**:
    
    - Desenvolvedores precisam entender esses padrões para manter o código, o que pode exigir treinamento ou contratação de profissionais mais experientes.

### **4. Quando usar Command Pattern ou CQRS?**

Esses padrões são úteis em *sistemas complexos*, onde:

- Há uma grande diferença entre as *operações de leitura e escrita*.
    
- O sistema precisa *escalar horizontalmente*.
    
- Há necessidade de *auditoria ou rastreamento* de comandos executados.
    
- O sistema precisa suportar *operações assíncronas ou distribuídas*.
    

**Exemplos de cenários adequados**:

- Sistemas de e-commerce com alto volume de pedidos.
    
- Sistemas de gerenciamento de estoque.
    
- Aplicações financeiras com requisitos complexos de auditoria.


---

## Resolvendo as Issues
#Issues são uma funcionalidade usada para rastrear tarefas, bugs, melhorias, solicitações de funcionalidades e outras atividades relacionadas ao desenvolvimento de software. Eles são uma forma generalizada de gerenciar o trabalho em um projeto, especialmente em equipes.

### Criar o fluxo de autenticação - sem o security por hora #1
Dado que um usuário queira realizar o login através do *index.html* respeitando o que já está sendo feito, precisamos que esses dados sejam levados ao servidor e validados, para isso, precisamos iniciar implementando de forma #mocada o #hash (pode ser sempre true) e criar de fato esse usuário, pois isso faz a validação do fluxo de autenticação, para isso, devemos implementar:
- Método de Login no account controller e suas respectivas interfaces e implementações;
- Método Create no account controller e suas respectivas interfaces e implementações;


# Nosso sistema:
## Account:
1. *Account*: Representa uma conta de usuário com atributos como *id*, *email* e *hashedPassword*;
```java
package br.com.translatoria.infra.persistence.mapping;  
  
import jakarta.persistence.Entity;  
import jakarta.persistence.GeneratedValue;  
import jakarta.persistence.GenerationType;  
import jakarta.persistence.Id;  
import jakarta.persistence.Table;  
  
import java.io.Serializable;  
  
@Entity  
@Table(name = "account")  
public class Account implements Serializable {  
    @Id  
    @GeneratedValue(strategy = GenerationType.AUTO)  
    private Long id;  
  
    private String email;  
  
    private String hashedPassword;  
  
    public Long getId() {  
        return id;  
    }  
  
    public void setId(Long id) {  
        this.id = id;  
    }  
  
    public String getEmail() {  
        return email;  
    }  
  
    public void setEmail(String email) {  
        this.email = email;  
    }  
  
    public String getHashedPassword() {  
        return hashedPassword;  
    }  
  
    public void setHashedPassword(String hashedPassword) {  
        this.hashedPassword = hashedPassword;  
    }  
}
```

**Serializable**
#Serializable é uma interface de marcação em Java que permite que objetos dessa classe sejam convertidos em uma sequência de bytes. Isso é útil para persistência em arquivos, transmissão de dados pela rede e cache em algumas implementações. 

Logo, é o processo de converter um #objeto (ou uma estrutura de dados) em um formato que pode ser facilmente armazenado, transmitido ou reconstruído posteriormente. Esse formato pode ser:
- Binário: uma sequência de bytes
- Texto: como #JSON, #XMLL ou #YAML;
O processo inverso, ou seja, converter o formato serializado de volta para um objeto, é chamado de #desserialização.

**Por que usar #Serializable em uma entidade JPA?**
Embora não seja obrigatório, é uma boa prática em algumas situações:
2. **Armazenamento em cache**: algumas implementações de cache, como o Hibernate L2 Cache (EHCache, Redis, etc), exigem que as entidades sejam serializáveis;
3. **Sessões distribuídas:** Se nossa aplicação estiver distribuída entre vários servidores, o uso de serialização pode facilitar a replicação de estado.
4. **Persistência em contextos diferentes**: em alguns cenários de serialização de objetos para comunicação (por exemplo, enviar um *Account* via REST sem converter explicitamente para DTO), *Serializable* pode ser útil.

**hashedPassword**
Por que não armazenar a senha diretamente?
Armazenar a senha em texto plano (password) é <span style="background:#ff4d4f">um risco de segurança grave</span>! Se o banco de dados for comprometido, qualquer um poderá ver as senhas dos usuários. Ao invés de manter a senhora original, o sistema *gera um hash criptográfico* e armazena ele. Assim, mesmo que alguém acesso o banco de dados, *não verá a senha real, apenas um código indecifrável*.

**Como gerar e verificar *hasedPassword* corretamente?**
O ideal é usar o #BCrypt, uma biblioteca de hashing segura. 
## AuthenticationRequestDTO
```java
package br.com.translatoria.infra.rest.account.dto;  
  
public class AuthenticationRequestDTO {  
    public String email;  
    public String password;  
  
    public String getEmail() {  
        return email;  
    }  
  
    public String getPassword() {  
        return password;  
    }  
  
    public void setEmail(String email) {  
        this.email = email;  
    }  
  
    public void setPassword(String password) {  
        this.password = password;  
    }  
}
```
A classe é um #Data-Transfer-Object #DTO, usada para transportar dados da requisição HTTP quando um usuário tenta se autenticar. Vamos analisar em detalhes:

1. **Propósito da Classe**: essa classe é usada no corpo da requisição HTTP quando o usuário tenta fazer login. Imagine que um usuário envie um #POST para */api/v1/accounts/login* com o seguinte #JSON:
```JSON
{
	"email": "usuario@email.com",
	"password": "senha123"
}
```
O SpringBoot vai automaticamente converter esse JSON em um objeto *AuthenticationRequestoDTO*, que será recebido pelo *AccountController*.

Então, toda vez que o usuário fizer uma requisição, o Spring Boot vai criar um objeto *AuthenticationRequestDTO* e o passará para o *AccountController*.

## AuthenticationResponseDTO
```java
package br.com.translatoria.infra.rest.account.dto;  
  
public class AuthenticationResponseDTO {  
    public String jwt;  
    public Long expires_in;  
  
    public String getJwt() {  
        return jwt;  
    }  
  
    public void setJwt(String jwt) {  
        this.jwt = jwt;  
    }  
  
    public Long getExpires_in() {  
        return expires_in;  
    }  
  
    public void setExpires_in(Long expires_in) {  
        this.expires_in = expires_in;  
    }  
}
```

**Propósito da Classe**
A classe é utilizada para *retornar a resposta* após um usuário realizar o login com sucesso. 
Quando um usuário faz um #POST para */api/v1/accounts/login* com seu e-mail e senha, o sistema gera um #JWT (JSON Web Token) e o envia de volta para o cliente.

**Exemplo das resposta JSON esperada**
```JSON
{
	"jwt": "edaisjhd81",
	"expires_in": 1231412
}
```
#JWT contém o token JWT que o client e usará nas próximas requisições;

## AccountRepository
A classe *AccountRepository* é uma interface de repositório que gerencia operações de banco de dados para a entidade *Account*.
```java
package br.com.translatoria.infra.persistence.repository;  
  
import br.com.translatoria.infra.persistence.mapping.Account;  
import org.springframework.data.jpa.repository.JpaRepository;  
import org.springframework.stereotype.Repository;  
  
import java.util.Optional;  
  
@Repository  
public interface AccountRepository extends JpaRepository<Account, Long> {  
  
    Optional<Account> findByEmail(String email);  
  
}
```

1. **Propósito da Classe**
Essa interface é responsável por fornecer *operações de CRUD* para a entidade #Account, aproveitando o **Spring Data JPA**.

Como ela estende *JpaRepository<Account, Long>*, nós conseguimos obter acesso a métodos prontos, como:
- **save(Acoount account)** - responsável por salvar um novo usuário ou atualizar um existente;
- **findById(Long id)** - Busca um usuário pelo ID;
- **deleteById(Long id)** - remove um usuário
- **findAll()** - retorna todos os usuários no banco de dados.

Além dos métodos padrão, a interface define:
*Optional< Account> findByEmail(String email);*
Esse método busca um usuário pelo e-mail, retorna um #Optional (Evitando #NullPointerException). O Spring Data JPA **gera automaticamente a implementação desse método**. 

Como o método é usado?
O *findByEmail(String email)* é usado no serviço *AccountServiceImpl* para buscar um usuário pelo email ao fazer login. Se o usuário existir, ele será recuperado no banco. Se o usuário não for encontrado, uma exceção *AccountNotFoundException* será lançada. 

<span style="background:#d4b106">Podemos adicionar um método extra útil</span>:
```java
boolean existsByEmail(String email); // verifica se o e-mail já está cadastrado
```
Esse método é útil para verificar se um e-mail já está em uso antes de cadastrar um novo usuário.

**Exemplo de Uso do Novo Método:**
```java
if (repository.existsByEmail(dto.getEmail())) {
	throw new EmailAlreadyExistsException("Email already in use");
}
```

## AccountService
A interface *AccountService* define o contrato para a autenticação de usuários no sistema. Essa interface estabelece um padrão de implementação para serviços que lidam com autenticação. Ela contém apenas um único método:
```java
AuthenticationResponseDTO authenticate(AuthenticationRequestDTO dto);
```

Ela define que qualquer classe que implemente *AccountService* precisa fornecer uma lógica para autenticação.
Ela garante que a aplicação pode trocar a implementação (trocar autenticação manual por Spring Security futuramente). 

A classe *AccountServiceImpl* é a implementação concreta dessa interface. 

**Benefícios do Uso de Interface**
- Facilidade para trocar implementações. Podemos criar outra classe *AccountServiceImplV2* e mudar apenas a injeção no Spring;
- **Melhora a organização do código:** o *AccountController* não depende diretamente da implementação, apenas da interface.
- **Facilidade para testes**: podemos criar um *MockAccountService* para testar o controller sem acessar o banco.

Criamos dois novos métodos:
1. *void registerAccount(AccounterRegistratiorDTO dto)*
2. *void changePassword(ChangePassWordDTO dto)*
(PODEMOS UTILIZAR O SPRING BOOT VALIDATION PARA ADICIONAR ANOTAÇÕES PARA VALIDAÇÃO, COMO):
```JAVA
@Email(message = "Email inválido")
@NotBlank(message = "O email não pode estar vazio")
private String email;
```


## AccountServiceImpl
Essa classe é a implementação do serviço de autenticação. Ela recebe os dados de login, busca o usuário no banco de dados e retorna um token JWT #mockado (por enquanto). 

1. **Anotação @Service**
```java
@Service
public classs AccountServiceImpl implements AccountService {}
```
- Marca essa classe como um componente de serviço no Spring;
- Permite que ela seja injetada automaticamente em outros lugares.

**2️⃣ Injeção do *AccountRepository***
```java
private AccountRepository repository;  
AccountServiceImpl(AccountRepository repository){  
    this.repository = repository;  
}
```
- Usa injeção de dependência para receber um *AccountRepository*, que faz operações no banco de dados.
- Isso segue o princípio de *inversão de controle (IoC)*.

**3️⃣ Método *authenticate***
```java
@Override
public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO dto) {
```
- Implementa o método da interface *AccountService*
- Recebe um DTO com *email* e *password* do usuário;

 **4️⃣ Busca o usuário no banco**
```java
Account account = repository.findByEmail(dto.getEmail())
    .orElseThrow(() -> new AccountNotFoundException("User Not Found"));
```
- Usa o repositório (AccountRepository) para buscar um usuário pelo e-mail. Se não encontrar, lança uma exceção *AccountNotFoundException*.

 **5️⃣ Retorno do Token Mockado**

```java
AuthenticationResponseDTO response = new AuthenticationResponseDTO();
response.setJwt("mocked_jwt");
response.setExpiresIn(Instant.now().getEpochSecond());
```
- Cria um **DTO de resposta** (`AuthenticationResponseDTO`).
- Retorna um **JWT falso** (`mocked_jwt`).
- Define um timestamp indicando a expiração.

Essa classe busca o usuário no banco de dados e retorna um JWT falso, sem validar a senha ainda. 