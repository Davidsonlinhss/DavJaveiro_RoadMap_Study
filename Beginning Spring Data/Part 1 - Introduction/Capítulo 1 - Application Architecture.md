## Introduction
No passado, os desenvolvedores armazenavam informações em banco de dados relacionais utilizando métodos convencionais, como #JDBC. Essa abordagem funcionou por muito tempo - quando os sistemas eram menores ou não excessivamente complexos de manter. Gradualmente, novas tecnologias como ibatis e Hibernate surgiram para reduzir a complexidade das consultas e facilitar o acesso a diferentes bancos de dados relacionais, permitindo que os desenvolvedores se concentrassem em criar ótimas aplicações, em vez de gastar muito tempo tentando descobrir como conectar-se a um banco de dados ou reduzir o número de conexões.

Pouco tempo depois, muitos bancos de dados não relacionais (noSQL) surgiram, resolvendo certos problemas, embora outros persistissem. Como desenvolvedor, ainda era necessário usar um cliente de banco de dados e lidar com uma infinidade de problemas de desempenho e/ou más práticas.

O Spring se mostrou a solução para múltiplos problemas, seja relacionados à injeção de dependências ou à interceptação de um método ou classe antes/depois que outro método o chamasse. Não é de surpreender que os criadores do framework tenham desenvolvido algo para reduzir a complexidade envolvida no uso de múltiplos bancos de dados. O Spring Data resolve a maioria desses problemas, aumentando a produtividade dos desenvolvedores e sua capacidade de criar novas aplicações.

Este livro abordará os fundamentos da criação de uma aplicação capaz de acessar múltiplos bancos de dados e demonstrará como podemos realizar operações em diferentes bancos de dados. Além disso, aprenderemos as melhores práticas para ajudar a reduzir ineficiências em suas aplicações.

---

O Spring Data é um tópico amplo que precisa ser aprendido passa a passo. Nesta primeira parte, aprenderemos os conceitos básicos de acesso a um banco de dados sem utilizar um módulo específico do Spring. A ideia é que compreendamos as vantagens e desvantagens de cada mecanismo para entender melhor por que o Spring Data é uma solução eficaz. Além disso, veremos diferentes padrões e soluções que são independentes de linguagem e biblioteca, podendo, portanto, ser aplicados a qualquer framework ou módulo.

O principal objetivo da Parte 1 é ensinar os fundamentos do Spring e como criar um projeto básico no Spring Boot utilizando uma IDE, CLI (Interface de Linha de Comando) ou o site Spring Initializr. Aprendederemos a criar um endpoint simples que persiste informações em um banco de dados relacional e utilizará essa aplicação básica ao longo do livro, alterando apenas o tipo de banco de dados.

## Why Persistence Is So Important
A persistência de informações é um dos aspectos mais importantes de microsserviços e aplicações. Perderemos tudo se não preservamos os dados ao realizar um deploy, reiniciar o sistema ou se algo acontecer com sua infraestrutura.

No entanto, há outras coisas que precisamos considerar, pois elas podem afetar o desempenho de toda a plataforma, adicionando latência ou erros. A seguir estão alguns problemas associados ao uso ou design inadequado de um banco de dados:

- Aumentar a latência para executar as consultas e obter informações de um banco de dados;
- Aumentar o uso de CPI/memória para acessar um banco de dados devido a um design incorreto na forma de conexão;
- Escolher a versão antiga ou o tipo incorreto de banco de dados para o tipo de informação a ser persistida.

Há muitos problemas associados à persistência, mas este livro não existe que você conheça todos eles. Uma decisão errada ou não entender os prós/contras de cada tipo de banco de dados e como o acesso às informações pode afetar todo o sistema. 

## The History of Persistence
Nas aplicações, ocorreu uma evolução desde o surgimento do JDBC no JDK 1.1, passando para ORM e, agora, como uso do Spring Data, que oferece uma interface padrão para acessar diferentes bancos de dados. Alguns deles são relacionais, enquanto outros não são relacionais. Existem várias maneiras de persistir informações em banco de dados. Considerando que pelo menos 50% dos desenvolvedores utilizam Spring Boot, de acordo com relatórios da Snyk, parece lógico que muitos deles usem Spring Data. A figura 1-1 mostra a história da persistência em Java até o surgimento do Sprint Data em 2011.

![[Capítulo 1 - Application Architecture.png]]
A maioria das bibliotecas que interagem com bancos de dados ainda é relevante hoje. Todas elas evoluíram ao longo dos anos. Algumas interagem entre si; por exemplo, Enterprise JavaBeans #EJB e Java Persistence API #JPA são duas coisas diferentes. As primeiras versões do EJB continham todas as especificações do JPA, que cresceram em relevância. A versão 3.0 do EJB aparece como duas entidades separadas que interagem entre si.

## JDBC
O #JDBC, ou *Java Data Base Connectivity*, fornece uma interface comum para se comunicar e interagir com diferentes bancos de dados. Mais especificamente, o JDBC oferece uma maneira de conectar-se a um banco de dados, criar e executar instruções como criar, remover, truncar tabelas, além de executar consultas, como Select, Insert, Update e Delete. A figura 1.2 Mostra uma visão geral dos diferentes componentes que interagem na comunicação com um banco de dados usando JDBC.
![[Capítulo 1 - Application Architecture-1.png]]
A interação com um banco de dados envolve um conjunto de componentes ou camadas:
- #JDBC API: essa camada contém todas as interfaces que todos os drivers precisam implementar para permitir que o desenvolvedores Java o utilizem. Essa camada é uma das mais importantes, pois podemos mudar de um banco de dados para o outro sem alterar muitas coisas, apenas trocando o driver do outro banco de dados e fazendo pequenos ajustes nos tipos de colunas.

- #JDBC  Driver Manager: esse conjunto de classes atua como conector entre os drivers e a JDBC API, registrando e cancelando o registro dos bancos de dados disponíveis, obtendo a conexão com os bancos de dados e as informações relacionadas a essa conexão.

- #Drivers: para fazer a conexão com bancos de dados, o JDBC oculta toda a lógica relacionada à forma de interação com um banco de dados em uma série de #drivers, cada um contendo a lógica específica para um banco de dados. Normalmente, as empresas que desenvolvem esses bancos de dados criam os drivers para a maioria das linguagens.

JDBC não é algo que permaneceu sem evolução desde a primeira versão em 1997. As versões subsequentes buscaram adicionar mais recursos ou resolver problemas relacionados ao desempenho. Frameworks como Spring implementam uma versão do JDBC com algumas das funcionalidades desses framework.
![[Capítulo 1 - Application Architecture-2.png]]

## JDBC Driver
O componente JDBC driver implementa as interfaces definidas na JDBC API para interagir com um banco de dados. Pense nesse componente como um adaptador do lado cliente, que converte os elementos ou a sintaxe específica de um banco de dados para o padrão que o **Java** pode entender.

O #JDBC oferece quatro tipos recomendados para diferentes situações, conforme ilustrado abaixo:
![[Capítulo 1 - Application Architecture-3.png]]

## Type 1: The JDBC-ODBC Bridge
Esse tipo foi adotado para os primeiros drivers JDBC porque a maioria dos bancos de dados inicialmente oferecia suporte ao acesso **ODBC**. Esse driver converte **JDBC** em **ODBC** e vice-verse, atuando como um adaptador independente do banco de dados. Podemos pensar nele como um driver universal que faz parte do JDK, portanto, não é necessário incluir nenhuma dependência no nosso projeto.

Esse tipo de driver é usado apenas para desenvolvimento ou testes; não sendo utilizado em ambientes de produção devido a problemas relacionados ao desempenho na conversão de JDBC para ODBC, além de questões de segurança.

## Type 2: Client Based
A próxima geração de **drivers** se tornou mais popular porque elimina toda a transformação de #JDBC em #ODBC, realizando as chamadas diretamente por meio de bibliotecas nativas de cada fornecedor de banco de dados, que geralmente reutilizam código existente em C/C++ para criar esses bibliotecas.

As vantagens incluem um aumento no desempenho, pois não há conversão entre diferentes formatos. No entanto, existem algumas desvantagens; por exemplo, o **driver** precisa ser instalado na máquina cliente.

## Type 3: Two-Tier Architecture
Arquitetura two-tier, protocolo de rede e driver puro Java com **middleware** referem-se ao mesmo conceito. Esse tipo utiliza JDBC, que usa um #socket para interagir com um servidor #middleware e a se comunicar com um banco de dados. Esse #middleware contém os drivers de diferentes bancos de dados, eliminando a necessidade de instalar tudo em cada máquina.

O principal problema dessa abordagem é a necessidade de um servidor dedicado para interagir com os bancos de dados, o que implica maior transferência de informações e introduz um ponto de falha. Além disso, os fornecedores precisam reescrever o código, que originalmente está em C/C++, para puro Java.

## Type 4: Wire Protocol Drivers
O wire protocol ou native protocol driver é uma das formas mais populares de conexão direta com um banco de dados. Neste tipo, o driver é escrito inteiramente em Java e se comunica com o banco de dados usando o protocolo definido por cada fornecedor.

Esse tipo oferece várias vantagens, incluindo excelente desempenho em comparação com o anterior e a não necessidade de instalação de qualquer coisa no cliente ou no servidor. No entanto, a principal desvantagem é que cada banco de dados possui um driver que utiliza protocolos diferentes.

## How to Connect with a Database
Existem várias abordagens dependendo do desempenho, como o uso de um **pool de conexões**. O processo básico para conectar-se a um banco de dados consiste nas seguintes etapas:
1. **Importar as classes** devemos incluir todas as classes necessárias para usar o JDBC e conectar-se a um banco de dados. Na maioria dos casos, todas as classes estão no pacote `java.sql.*`.
2. **Abrir uma conexão:** criar uma conexão usando *DriverManager.getCOnnection()*, que representa uma conexão física com um banco de dados;
3. **Executar a consulta** - criar um objeto *Statement* que contém a consulta a ser executada no banco de dados.
4. **Obter os dados -** após executar a consulta no banco de dados, é necessário usar o método *ResultSet.getxxx()* para recuperar as informações de cada coluna.
5. **Fechar a conexão -** devemos fechar explicitamente a conexão com o banco de dados em todos os casos, independentemente de a aplicação gerar uma exceção ou não.

Vamos analisar todos os conceitos relacionados ao acesso a um banco de dados e à obtenção de informações. A ideia é criar uma estrutura de tabelas que represente um catálogo de países, estados e cidades, que será utilizada nos capítulos sobre banco de dados relacionais. 

![[Capítulo 1 - Application Architecture-4.png]]
Quando tudo parece bastante semelhante no seu banco de dados, podemos conectarmos ao banco e obter uma lista dos países existente usando um bloco de código semelhante ao seguinte:

<<<<<<< HEAD
=======
## EJB
Enterprise JavaBeans (EJB) é uma especificação para a construção de aplicações empresariais portáteis, escaláveis e reutilizáveis, sem a necessidade de reinventar soluções para tarefas comuns, como segurança, acesso a banco de dados, envio de mensagens entre diferentes aplicações e muito mais.

Nas primeiras versões do Java, alguns desses recursos existiam em outra bibliotecas ou em versões básicas dentro do JDK. Como surgimento do EJB, todos esses recursos foram unificados em um único framework.

Para acessar um banco de dados com EJB, é necessário um **container EJB** que roda dentro de um servidor de aplicação, como **GlassFish**, **WildFly** ou **Jetty**.
![[Capítulo 1 - Application Architecture-5.png]]


O EJB passou por várias versões desde 1999, introduzindo novos recursos, aprimorando o desempenho e corrigindo buds das versões anteriores.

Recentemente, o EJB foi incorporado ao Jakarta EE nas versões 8 e 9.

A Tabela 1-3 descreve os aspectos mais relevantes de cada versão:
## Table 1-3. List of EJB Versions with Their Characteristics

| Year   | Version  | Characteristics |
|--------|---------|----------------|
| 1998/1999 | EJB 1.0 | - Introduces support to use Entity Beans for persistent objects. <br> - Has a remote interface to access remotely, making EJB portable. |
| 1999  | EJB 1.1  | - Introduces the deployment descriptor, replacing a class that contained all the metadata. |
| 2001  | EJB 2.0  | - Introduces compatibility with other Java APIs. <br> - Clients in the same J2EE container could access another EJB using a local interface. <br> - Introduces JavaBeans Query Language (JBQL), allowing developers to perform SQL-like queries. |
| 2003  | EJB 2.1  | - Adds support to create web services. <br> - Introduces the timer service, allowing cron-like scheduled service invocations. <br> - Adds support for aggregate functions like `BY`, `AVG`, `MIN`, `MAX`, `SUM` in EJB queries. |
| 2006  | EJB 3.0  | - Introduces POJOs (Plain Old Java Objects) as a replacement for EJB Bean classes. <br> - Rewrites most of the code to use annotations instead of large configuration files. <br> - Remote and local interfaces are no longer required to be explicitly implemented. <br> - JPA entities are decoupled from the EJB container for independent use. |
| 2009  | EJB 3.1  | - Introduces EJB Lite, allowing multiple components to run in the same VM as an EJB client. |
| 2013  | EJB 3.2  | - Introduces AutoCloseable interfaces. <br> - Adds more control over transactionality of lifecycle interceptor methods. |
| 2020  | EJB 4.0  | - Deprecates the `EJBContext.getEnvironment()` method. <br> - Removes methods relying on JAX-RPC. <br> - Moves most packages from `javax.ejb` to `jakarta.ejb`. |

## EJB Types
A arquitetura do EJB possuí três componentes principais: o **container EJB**, o **servidor de aplicativos** e os **Enterprise JavaBeans (EJB)**, que são divididos em diferentes tipos:
- **Session beans:** contêm toda a lógica relacionada a uma tarefa do usuário ou a um caso de uso. Eles estão disponíveis apenas durante a execução dessa tarefa, funcionando como uma conversa. Se o servidor falhar ou reiniciar, todas as informações armazenadas dentro do **session bean** serão perdidas.
- Existem dois tipos: **stateful:** mantém o estado após ser chamado. e **stateless**: a principal diferença é que o primeira salva os estados após ser chamado, enquanto o segundo (Stateless) não salva nenhuma informação após a invocação.

**Message-driven beans:** os beans anteriores funcionam bem quando se deseja comunicação síncrona entre aplicativos, mas os message-driven beans são a opção correta se for necessário comunicação assíncrona. A implementação mais comum usa JMS (Java Message Service). Esse tipo de EJB atua como um ouvinte em um tópico de uma fila, aguardando uma mensagem e executando uma ação quando a mensagem chega. Esse tipo de comunicação tornou-se popular com o surgimento dos microservices.

- **Entities:** Esse componente contém classes que representam tabelas e fornecem uma interface para operações CRUD em um banco de dados. O EJB 3.0 introduziu uma mudança nesse tipo de componente para usar JPA em vez dos entity beans anteriores, que faziam da especificação EJB.
![[Capítulo 1 - Application Architecture-6.png]]
Um contêiner EJB gerencia dois tipos de #beans: 
1. **Session Bean**
2. **Message Driven Bean**
Além disso, há uma seção rotulada como JPA (Java Persistence API), que contém as entidades. 

## JPA
A Java Persistence API, ou JPA, é uma especificação conectada diretamente com a persistência em banco de dados. Para ser mais específico, o JPA fornece um conjunto de interfaces que todos os provedores precisam seguir para garantir que estão cumprindo o padrão, permitindo assim a troca de provedor sem muito esforço. Usando o JPA, os desenvolvedores podem acessar as informações em um banco de dados e executar certas operações como inserir, atualizar, deletar e recuperar usando uma classe Java que representa a estrutura das tabelas. Para realizar essas operações, é necessário anotar as classes com anotações que representam os elementos mais comuns em uma tabela, como o nome da tabela, tamanho da coluna e a relação entre as tabelas.

O JPA oferece várias maneiras de realizar consultas para recuperar informações. Uma é utilizando #queries SQL diretamente, como no JDBC, com classes para ajudar a construir a sentença. Outra maneira introduz uma abstração, de forma que não precisamos escrever toda a sentença, pois o provedor do JPA gera a sentença dinamicamente. 

Example of the structure of layers using JPA:
![[Capítulo 1 - Application Architecture-7.png]]
Essa especificação possui muitas implementações, mas as mais relevantes são Hibernate, EclipseLink, Apache OpenJPA e Spring Data JPA. <span style="background:#affad1">Cada uma delas implementa a especificação do JPA</span> de maneira diferente, priorizando aspectos como desempenho ou a experiência do desenvolvedor. Vamos ver um exemplo onde podemos declarar uma classe representando uma tabela em um banco de dados:
```java
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "currency")
public class Currency implements Serializable {

	@Id
	@GeneratedValue(Strategy)
	private Long id;

	private String code;
	private String description;
	private Boolean enable;

	@Column(name = "decimal_places", length = 5)
	private String decimalPlaces;

	public Currency(Long id, String code, String description, Boolean enable, String decimalPlaces) {
		this.id = id;
		this.code = code;
		this.description = description;
		this.enable = enable;
		this.decimalPlaces = decimalPlaces;
	
	}
	( // getters and setters)
}
```


## <span style="background:#d4b106">Spring Data</span>

O principal problema com todos os mecanismos anteriores de persistência é que não temos a chance de acessar ambos os tipos de banco de dados, relacionais ou não relacionais. Mas o Spring Data resolve esse problema, permitindo que acessemos bancos de dados usando repositórios com uma interface para realizar operações CRUD. Além disso, podemos criar classes concretas que implementam as interfaces de repositório com o objetivo de realizar operações específicas não cobertas pelo framework Spring Data. Além disso, podemos criar classes concretas que implementam as interfaces de repositório com o objetivo de realizar operações específicas não cobertas pelo framework Spring Data. Esses repositórios estão disponíveis em quase todas as implementações para acessar banco de dados, reduzindo a complexidade de usar múltiplos bancos de dados na mesma aplicação. 

Existem dois repositórios básicos que utilizamos para estender a funcionalidade dos nossos repositórios. Em particular, dois são mais relevantes:
- #CrudRepository que possui as operações básicas para todas as entidades;
- #PagingAdnSortingRepository que contém toda a lógica para obter informações de uma estrutura em um banco de dados usando os critérios de paginação e ordenação.

## Object Mapping
Uma das maiores vantagens que o JPA oferece aos desenvolvedores é a possibilidade de mapear uma tabela com uma classe específica, considerando todos os possíveis tipos de colunas e a relação entre as tabelas. O Spring Data estende essa abordagem para todos os bancos de dados NoSQL. Cada tipo de banco de dados oferece suas anotações para representar as informações e mapear bancos de dados com classes Java. A tabela abaixo mostra que cada objeto pode ser representado em outros bancos de dados sem alterações significativas nos nomes e tipos dos campos. Só precisamos mudar as anotações relacionadas a cada banco de dados.

![[Capítulo 1 - Application Architecture-8.png]]

@Entity, @Table, @Id, @GeneratedValue... essas anotações são parte da especificação JPA, que permite o mapeamento de objetos Java para tabelas em um banco de dados relacional. 

## Repository Support
Quando escrevemos uma aplicação que precisa persistir/recuperar informações de um banco de dados, criamos uma classe ou camada que contém todas essas operações na maioria dos casos. Existem várias maneiras de agrupar essas operações: todas juntas na mesma classe, uma classe por tabela, mas a maneira mais comum de fazer isso é criar uma classe que representa o padrão DAO, que será discutido na próxima seção, com todas as operações de uma tabela. Isso significa que <span style="background:#d4b106">podemos ter várias classes</span> para acessar o banco de dados, onde <span style="background:#affad1">a principal diferença é a tabela que acessamos</span>. 

Para reduzir a complexidade de ter várias classes com o mesmo código, o Spring Data oferece a possibilidade de usar uma abstração que contém todas essas operações, e precisamos apenas indicar o nome da entidade/tabela que acessamos para criar uma interface que se estende de uma dessas interfaces comuns e indicar qual tabela ou estrutura podemos acessar. Nos bastidores, o Spring Data transforma essa interface em uma consulta para salvar/recuperar as informações, dependendo do tipo de banco de dados. A figura 1.9 ilustra a combinação de interfaces para ter mais operações disponíveis:
![[Capítulo 1 - Application Architecture-9.png]]


## Architectures Types
As aplicações geralmente têm uma estrutura que ajuda a entender onde podemos encontrar a lógica, como validações, lógicas de negócios, entidades e as interfaces/classes para acessar um banco de dados. Todos esses elementos são importantes para entender a hierarquia das camadas e quais regras precisamos seguir para criar uma nova funcionalidade ou aplicação. 

O Archunit  
https://www.archunit.org/
permite definirmos e verificarmos se tudo em nosso projeto segue a estrutura definida.

### Layers
Este tipo de arquitetura é o mais simples porque divide a aplicação em diferentes camadas, como um bolo, onde cada camada pode acessar apenas os elementos do mesmo nível ou do nível inferior. Esse tipo de arquitetura varia de uma arquitetura com quatro camadas, como visual/aparesentação, lógica de negócios, persistência e banco de dados, para outras com três ou cinco camadas. Não existe um critério universal para o número correto de camadas. No entanto, se considerarmos que três é um número pequeno de camadas, o Spring Boot é representado por controladores, servições, repository e data base, que não aparece diretamente no Spring como camada. 

![[Capítulo 1 - Application Architecture-10.png]]

**Benefits**
This structure has the following benefits:
- É simples de implementar e manter porque só temos uma hierarquia de camadas;
- Cada camada tem apenas uma responsabilidade e não sabe como implementar a lógica das outras camadas, então podemos introduzir modificações na lógica das camadas que não afetarão diretamente as outras.
- A estrutura é mais ou menos a mesma em todos os projetos. Podemos mudar de um projeto para outro e saber onde encontrar algo.

**Drawbacks**
This type of architecture has the following drawbacks:
- É complicado saber onde todas as classes ou objetos estão conectados para representar um caso de uso.
- Dependendo do tamanho e do número de camadas, podemos ter problemas com escalabilidade porque pode haver várias classes/interfaces no mesmo pacote ou camada.

## Persistence Design Patterns
Ao longo do material, alguns códigos respondem à mesma estrutura porque os padrões estão associados ao acesso a um banco de dados. Esta seção introduz os mais relevantes desses padrões, mas há outros que este livro não cobre.

### Objeto de Acesso a Dados (DAO)
Um padrão de objeto de acesos a dados (DAO) persiste e recupera informações para banco de dados. Ele permite que os desenvolvedores isolem a camada de negócios da camada de persistência, que na maioria dos casos está associada a um banco de dados, mas poderia ser qualquer coisa que tenha a responsabilidade de acessar a informação. 

Esse padrão oculta a complexidade de realizar todas as operações CRUD em um banco de dados, para que possamos mudar ou introduzir modificações sem afetar significativamente todas as outras camadas. DAO não é comum no Spring Data porque a maioria dos desenvolvedores usa o padrão de repositório. Mas alguns casos relacionados ao desempenho ou operações não são suportados por esse padrão, então os padrões DAO aparecem para nos resgatar do problema. 
![[Capítulo 1 - Application Architecture-11.png]]

## Repository Pattern
O padrão de repositório acessa um banco de dados para obter certas informações, introduzindo uma abstração para o restante das camadas. O espírito do padrão de repositório é emular ou mediar entre o domínio e o mapeamento de dados usando interfaces para acessar os objetos do domínio. O uso mais comum é criar uma interface contendo certos métodos; frameworks como Spring Boot e Quarkus transformam os métodos em consultas específicas para um banco de dados. Em contraposição, os DAOs implementam toda a lógica usando consultas para acessar um banco de dados.

Aqui está a tabela refeita dos padrões DAO e Repositório:

| **DAO Pattern**                                 | **Repository Pattern**                               |
|-------------------------------------------------|-----------------------------------------------------|
| É mais próximo do banco de dados porque lida com consultas e tabelas. | É mais próximo da camada de negócio/domínio porque usa abstração para esconder a implementação. |
| DAOs não podem conter repositórios porque estão em camadas diferentes. | Repositórios podem conter ou implementar DAOs.       |
| É uma abstração dos dados.                      | É uma abstração de uma coleção de objetos.           |

## Data Transfer Object (DTO)
O padrão DTO é usado em várias linguagens, agregando e encapsulando dados para transferência entre as camadas de sua aplicação ou aplicações. Podemos pensar nesse padrão como uma estrutura de dados que não contém nenhuma lógica de negócios e pode representar uma combinação de estruturas. Martin Fowler introduziu esse padrão em 2002, em seu livro _Patterns of Enterprise Application Architecture_. Esse padrão ajuda a reduzir o número de chamadas para diferentes endpoints ou processos para obter todas as informações necessárias para realizar uma determinada tarefa, o que é caro em termos de tempo ou tamanho das informações a serem transferidas.

![[Capítulo 1 - Application Architecture-12.png]]
>>>>>>> 559e7d83e1eaccc43213a76aff22941c40fdb35f
