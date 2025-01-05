**Este capítulo abordará**
- Introdução aos projetos Hibernate e Spring Data;
- Desenvolvendo um "Hello World" com a API Jakarta Persistence, Hibernate e Spring Data;
- Examinando a configuração e as opções de integração.

Vamos iniciar o capítulo com a API Jakarta Persistence, Hibernate e Spring Data, trabalhando em um exemplo passo a passo. Exploraremos as APIs de persistência e veremos os benefícios de usar a JPA padronizada, o Hibernate nativo ou o Spring Data.

Iniciaremos com uma introdução ao #JPA, Hibernate e Spring Daata, analisando uma aplicação simples de "Hello World". O JPA (Jakarta Persistence API, anteriormente Java Persistence API) é a especificação que define uma API para gerenciar a persistência de objetos e mapeamentos objeto/relacional - ela especifica o que deve ser feito para persistir objetos. O #Hibernate, a implementação mais popular dessa especificação, é o responsável por realizar a persistência. Já o Spring Data torna a implementação da camada de persistência ainda mais eficiente; trata-se de um projeto abrangente que segue os princípios do framework Spring e oferece uma abordagem ainda mais simplificada.

## 2.1 Introducing Hibernate

O mapeamento objeto/relacional (ORM) é uma técnica de programação usada para criar a conexão entre os mundos incompatíveis dos sistemas orientados a objetos e os bancos de dados relacionais.

O Hibernate é um projeto ambicioso que busca oferecer uma solução completa para o problema de gerenciar dados persistentes em Java. Atualmente, o Hibernate não é apenas um serviço de ORM, mas também uma coleção de ferramentas de gerenciamento de dados que vai muito além do ORM. 
O conjunto de projetos Hibernate inclui o seguinte:
- **Hibernaate ORM** - consistem em um núcleo, um serviço base para persistência com bancos de dados SQL e uma API proprietária nativa. Hibernate ORM é a fundação para vários outros projetos na suíte, e é o projeto Hibernate mais antigo. Podemos usar o Hibernate ORM por conta própria, independente de qualquer framework ou de qualquer ambiente de execução específico com todos os JDKs. Desde que uma fonte de dados seja acessível, podemos configurar o Hibernate e ele funcionará.
- **Hibernate EntityManager** - Esta é a implementação do Hibernate da API de Persistência padrão do Jakarta. É um módulo opcional que podemos adicionar em cima do Hibernate ORM. Os recursos nativos do Hibernate são um superconjunto dos recursos de persistência JPA em todos os aspectos. 
- **Hibernate Validator** - O Hibernate fornece a implementação de referência da especificação de validação de Bean (JSR 303). Independente de outros projetos do Hibernate, ele fornece validação declarativa para classes de modelo de domínio (ou qualquer outra). 
- **Hibernate Envers** - O Envers é dedicado ao registro de auditoria e à manutenção de múltiplas versões de dados no banco de dados SQL. 
- **Hibernate Search** - O Hibernate Search mantém um índice dos dados do modelo de domínio atualizado em um banco de dados Apache Lucene. Ele permite que consultemos esse banco de dados com uma API poderosa e naturalmente integrada. Muitos projetos usam o Hibernate Search além do Hibernate ORM, adicionando capacidades de busca em texto completo. 
- **Hibernate OGM** - estre projeto do Hibernate é um mapeador objeto/grid. Ele fornece suporte JPA para soluções NoSQL, reutilizando o núcleo do Hibernate, mas persistindo entidades mapeadas em repositórios de dados orientados a chave/valor, documento ou gráfico.
- **Hibernate Reactive** - Hibernate Reactive é uma API reativa para o Hibernate ORM, <span style="background:#d4b106">interagindo com um banco de dados de maneira não bloqueante</span>. Ele suporta divers de banco de dados não bloqueantes. O Hibernate Reactive não é abordado neste material.

## 2.2 Introducing Spring Data
O Spring Data é uma família de projetos pertencentes ao framework Spring, cujo propósito é simplificar o acesso tanto a bancos de dados relacionais quanto a bancos NoSQL, a suíte conta com:
- **Spring Data Commons** parte do projeto principal Spring Data, fornece um modelo de #metadados para a persistência de classes Java e interfaces de repositório independentes de tecnologia;
- **Spring Data JPA** lida com a implementação de repositórios baseados em JPA. Ele melhora o suporte às camadas de acesso a dados baseados em JPA, reduzindo o código repetitivo e criando implementações para as interfaces de repositório;
- **Spring Data JDBC** lida com a implementação de repositórios baseados em JDBC. Ele melhora o suporte às camadas de acesso a dados baseados em JDBC, mas não oferece algumas funcionalidades do JPA, como cache ou carregamento lento ( #laze loading), resultando em um ORM mais simples e limitado.
- **Spring Data REST** lida com a exportação de repositórios Spring Data como recursos #RESTFul.
- **Spring Data MongoDB** lica com o acesso a bancos de dados de documentos MongoDB. Baseia-se no estilo de acesso a dados por repositórios e no modelo de programação POJO.
- **Spring Data Redis** lida com o acesso a banco de dados de chave/valor Redis. Simplifica a infraestrutura e oferece abstrações de alto e baixo nível para acessar o armazenamento de dados. 

## 2.3 "Hello World" with JPA
Vamos escrever nossa primeira aplicação JPA, que armazenará uma mensagem no banco de dados e, em seguida, a recuperará. 

Na aplicação "Hello Worl", queremos armazenar mensagens no banco de dados e carregá-las do banco de dados. Aplicações Hibernate definem classes persistentes que são mapeadas para tabelas do banco de dados. Definimos essas classes com base em nossa análise do domínio de negócio da aplicação; portanto, elas são um modelo de domínio. Este exemplo consistirá em uma classe e seu mapeamento. Escreveremos os exemplos como testes executáveis, com asserções que verificam o resultado correto de cada operação. 

O módulo hibernate-entitymanager inclui dependências transitivas de outros módulos necessários, como o **hibernate-core** e os #stubs da interface JPA. Também precisamos da dependência junit-jupiter-engine para executar os testes com a ajuda do JUnit 5, e da dependência mysql-connector-java, que é o driver JDBC oficial para MySQL.

Nosso ponto de partida no JPA é a unidade de persistência. Uma unidade de persistência é um emparelhamento dos mapeamentos de classes de nosso modelo de domínio com uma conexão de banco de dados, além de algumas outras configurações. Cada aplicação tem pelo menos uma unidade de persistência; algumas aplicações têm várias, se estiverem falando com vários bancos de dados (lógico ou físicos). Portanto, nosso primeiro passo é configurar uma unidade de persistência na configuração de nossa aplicação.  Logo, <span style="background:#d4b106">faremos a associação das nossas classes do modelo de domínio a uma conexão de banco de dados</span>.

### 2.3.1 Configuring a persistence unit
O arquivo de configuração padrão para unidades de persistência está localizado no classpath em META-INF/Persistence.xml. 

**Configurações**
**1. Configuração do arquivo `persistence.xml`**  
O arquivo `persistence.xml` configura pelo menos uma unidade de persistência; cada unidade deve ter um nome único.

**2. Implementação do Provedor de Persistência**  
Como o JPA é apenas uma especificação, precisamos indicar a implementação do provedor de persistência específica do fornecedor. A persistência que definimos será suportada por um provedor Hibernate.

**3. Propriedades JDBC**  
Devemos indicar as propriedades JDBC, como:

- O driver.
- A URL do banco de dados.
- O nome de usuário.

**4. Credenciais e configuração no ambiente local**  
Não há senha para o acesso. A máquina na qual estamos executando os programas tem o MySQL 8 instalado, e as credenciais de acesso são as do arquivo `persistence.xml`. Você deve modificar as credenciais para que correspondam às da sua máquina.

**5. Dialeto do Hibernate**  
O dialeto do Hibernate é MySQL8, já que o banco de dados com o qual interagimos é o MySQL versão 8.0.

**6. Exibição e formatação do SQL**  
Durante a execução, o código SQL será exibido. O Hibernate formatará o SQL de maneira organizada e incluirá comentários na string SQL para que possamos entender por que o Hibernate executou a declaração SQL.

**7. Criação do banco de dados do zero**  
Toda vez que o programa for executado, o banco de dados será criado do zero. Isso é ideal para testes automatizados, quando queremos trabalhar com um banco de dados limpo para cada execução de teste.

### 2.3.2 Writing a persistent class
O objetivo desse exemplo é armazenar mensagens em um banco de dados e recuperá-las para exibição. A aplicação possui uma classe persistente simples, #Message.

Algumas annotations:
A) #Entity - toda classe de entidade persistente deve ter pelo menos a anotação. O hibernate mapeia essa classe para uma tabela chamada MESSAGE, conforme o nome da classe. 
B) #Id - Toda classe persistente deve ter um atributo identificador anotado com @Id. O Hibernate mapeia esse atributo para uma coluna chamada id. 

C) #GeneratedValue - alguém deve gerar o valor da chave primária. A annotation gera automaticamente os valores para a chave primária.

D) Geralmente implementamos atributos regulares de uma classe persistente com campos private e, com isso, precisamos dos métodos acessadores getters e setters públicos. 

O atributo #identifier de uma classe persistente permite que a aplicação acesse a identidade do banco de dados - o valor da chave primária - de uma instância persistente. 
Se duas instâncias de Message tiverem o mesmo valor de #identifier, elas representam a mesma linha no banco de dados.

Este exemplo usa #Long como o tipo do atributo #identifier, mas isso não é obrigatório. O Hibernate permite que usemos praticamente qualquer tipo para o identificador.

Podemos ter notado que o atributo #text da classe #message possui métodos de acesso de propriedade no estilo JavaBeans. A classe também tem um construtor sem parâmetro. 

Instâncias da classe Message podem ser gerenciadas (tornadas persistentes) pelo Hibernate, mas não precisam ser. Como o objeto Message não implementa nenhuma classe ou interface específica de persistência, podemos usá-lo como qualquer outra classe Java:
```java
Message msg = new Message();
msg.setText("Hello");
Sout("msg.getText());
```
Pode parecer que estamos tentando ser engraçadinhos aqui; mas na verdade, estamos demonstrando um recurso importante que diferencia o Hibernate de algumas outras soluções de persistência.

Podemos usar a classe persistente em qualquer contexto de execução - nenhum contêiner especial é necessário.

**Não precisamos usar anotações para mapear uma classe persistente.** Mais tarde, mostraremos outras opções de mapeamento, como o arquivo de mapeamento JPA orm.xml e os arquivos de mapeamento nativos hbm.xml, e veremos quando elas são uma melhor solução do que as anotações de origem, que são a abordagem mais frequentemente usada atualmente.

A classe Message agora está pronta. Podemos armazenar instâncias em nosso banco de dados e escrever consultas para carregá-las novamente na memória da aplicação

### 2.3.3 Storing and loading messages
O que faremos é JPA com Hibernate, então, vamos salvar uma nova Message no banco de dados.

A)
```java
EntityManagerFactory emf =
	Persistence.createEntityManagerFactory("ch02");
```

Primeiramente, precisamos de um EntityManagerFactory para <span style="background:#d4b106">conversar com o banco de dados</span>. Essa API representa a unidade de persistência, e a maioria das aplicações tem um EntityManagerFactory para uma unidade de persistência configurada. Uma vez que o aplicativo inicia, ele deve criar o EntityManagerFactory; a fábrica é segura para threads, e todo o código no aplicativo que acesso o banco de dados deve compartilhá-la.

B) e C) - Iniciando uma nova sessão com o banco de dados criando um EntityManager. Este é o contexto para todas as operações de persistência. Obtenha acesso à API padrão de transações e inicia uma transação neste thread de execução.
```java
try{  
    EntityManager em = emf.createEntityManager();  
    em.getTransaction();
    em.begin(); // inicia a nova transação      
}
```

---

x.gein() - As transações são fundamentais para garantir a integridade dos dados no banco de dados. Elas agrupam uma série de operações de banco de dados de forma que todas sejam executadas com sucesso ou nenhuma seja executada. Isso evita problemas com dados inconsistentes caso ocorra algum erro durante a execução das operações.

Imagine que você precisa atualizar o saldo de duas contas bancárias. Se essas atualizações ocorrerem em transações separadas:

1. A conta A pode ter seu saldo atualizado com sucesso.
2. Se ocorrer um erro durante a atualização da conta B, a conta A ficará com o saldo incorreto, pois a primeira atualização já foi concluída.

Usando uma única transação, se ocorrer um erro na atualização de uma conta B, a atualização da conta A também deverá ser desfeita, mantendo a consistência dos dados.

---
D) e E)
```java
Message message = new Message();
message.setText("Hello World!");
em.persist(message); // método responsável por enlistar a instância msg no contexto
```
Cria uma nova instância da classe de modelo de domínio mapeada Message e define sua propriedade de texto.
Enliste a instância transitória com o contexto de persistência; tornamos ela persistente.
#Enlistar significa que o Hibernate agora sabe que desejamos persistir esse objeto no banco de dados.

```java
em.getTransaction().commit();
```
Confirma a transação. O Hibernate verifica automaticamente o contexto de persistência e executa a instrução #SQL #INSERT necessária. . Para ajudá-lo a entender como o Hibernate funciona, mostramos os comandos SQL gerados e executados automaticamente em comentários no código-fonte quando eles ocorrem. O Hibernate insere uma linha na tabela MESSAGE, com um valor gerado automaticamente para a coluna de chave primária ID e o valor TEXT.

Toda interação com o banco de dados deve ocorrer dentro dos limites de uma transação, mesmo que estejamos apenas lendo dados, portanto, inciamos uma nova transação. Qualquer falha potencial que apareça a partir de agora não afetará a transação previamente confirmada.

**Por que iniciar uma transação para leitura?**
- **Isolamento:** mesmo em operações de leitura, iniciar uma transação pode melhorar o isolamento entre diferentes operações concorrentes. Isso ajuda a evitar problemas como leitura suja ( #diry_reads), leitura fantasma ( #phantom reads) e leituras não repetitivas ( #non_repeatable_reads).
- **Padrão**:  é uma boa prática iniciar uma transação para todas as interações com o banco de dados, mesmo que sejam apenas leituras, para manter a consistência e evitar problemas inesperados.

**Observações**
- A duração de uma transação deve ser o mais curta possível para minimizar bloqueios e melhorar o desempenho do banco de dados.
- Em algumas situações, pode ser mais eficiente utilizar transações somente para operações de escrita ( write-online transaction) ou técnicas de isolamento mais leves, como leituras não bloqueantes.

```java
List<Message> messagens = em.createQuery("Select m from Message m", Message.class).getResultList();
```
Estamos executando uma consulta para recuperar todas as instâncias de Message do banco de dados.

**Explicação:**
    
    - Este método executa a consulta e retorna uma lista de objetos `Message` que correspondem aos resultados da consulta.

```java
messages.get(messages.size() - 1).setText("Hello World from JPA!");
```
Podemos alterar o valor de uma propriedade. O Hibernate detecta isso automaticamente porque a Message carregada ainda está associada ao contexto de persistência em que foi carregada.

Verificando se as mensagens que persistimos estão no banco de dados. Utilizamos o método assertAll do JUnit 5, que sempre verifica todas as asserções passadas a ele, mesmo que algumas delas falhem. 

A linguagem de consulta que usamos no exemplo não é SQL; é a Jakarta Persistence Query Language (JPQL). Embora, neste exemplo trivial, não haja diferença sintática, o Message na string de consulta não se refere ao nome da tabela do banco de dados, mas ao nome da classe persistente. 

Além disso, observe como o Hibernate detecta a modificação na propriedade `text` da mensagem e atualiza automaticamente o banco de dados. Esta é a funcionalidade de **verificação automática de alterações (automatic dirty-checking)** do JPA em ação. Ela nos poupa o esforço de pedir explicitamente ao gerenciador de persistência para atualizar o banco de dados quando modificamos o estado de uma instância dentro de uma transação.

## 2.4 Native Hibernate configuration
Embora a configuração básica (e extensiva) seja padronizada no JPA, não podemos acessar todos os recursos de configuração do Hibernate através das propriedades no persistence.xml. Vale notar que a maioria das aplicações, mesmo as mais sofisticadas, não precisa dessas operações de configuração especiais e, portanto, não precisa acessar a API de inicialização que mostraremos nesta seção. 
<span style="background:#d4b106">O Hibernate oferece recursos de configuração adicionais que não podem ser acessados diretamente através do arquivo persistence.xml</span>.

Ao usar o Hibernate nativo, utilizaremos as dependências e a API do Hibernate diretamente, em vez das <span style="background:#d4b106">dependências e classes do JPA</span>. O JPA é uma especificação, e pode usar diferentes implementações (o Hibernate é um exemplo, mas o EclipseLink é outra alternativa) através da mesma API. 

O Hibernate, como implementação, fornece suas própias dependências e classes. Embora o uso do JPA ofereça mais flexibilidade, ao longo do livro veremos que <span style="background:#d4b106">acessar a implementação do Hibernate diretamente permite usar recursos que não são cobertos pelo padrão do JPA. </span>

O Equivalente nativo do padrão JPA EntityManagerFactory é o org.hibernate.SessionFactory. Normalmente, temos um SessionFactory por aplicação, e ele envolve a mesma associação entre mapeamento de classes e a configuração de conexão com o banco de dados.

Para configurar o Hibernate nativo, podemos usar um arquivo de propriedades hibernate.properties ou um arquivo xml hibernate.cfg.xml. Este arquivo geralmente é colocado na pasta src/main/resource ou src/test/resource. 

## 2.5 Switching between JPA and Hibernate

Suponha que estejamos trabalhando com JPA e precisemos acessar a API do Hibernate, ou vice-versa, estamos trabalhando com Hibernate nativo e precise criar um EntityManagerFactory. Para obter um SessionFactory. Para obter um SessionFactory a partir de um EntityManagerFactory, precisamos desembrulhar o primeiro a partir do segundo:

A partir da versão 2.0 do JPA, podemos acessar as APIs das implementações subjacentes. O EntityManagerFactory (e também o EntityManager) declara um método #unwrap que retorna objetos pertencentes às classes correspondentes, como o SessionFactory ou Session, e começar a usá-los conforme demonstrado. Quando um recurso específico está disponível apenas no Hibernaate, podemos alternar para ele usando o método #unwrap.

Podemos nos interessar pela operação reversa: criar um EntityManagerFactory a partir de uma configuração inicial do Hibernate.

## "Hello World" with Spring Data JPA
Vamos escrever nossa primeira aplicação Spring Data JPA, que irá armazenar uma mensagem no banco de dados e depois recuperá-la.

Primeiro, vamos adicionar as dependências do Spring à configuração do Apache Maven.
```xml
<dependency>
<groupId>org.springframework.data</groupId>
<artifactId>spring-data-jpa</artifactId>
<version>2.7.0</version>
</dependency>
<dependency>
<groupId>org.springframework</groupId>
<artifactId>spring-test</artifactId>
<version>5.3.20</version>
</dependency>
```

**Spring-data-jpa**: O módulo spring-data-jpa fornece suporte de repositório para JPA e inclui dependências transitivas em outros módulos que precisaremos, como spring-core e
spring-context.

**Spring-test**: dependência que vamos precisar para executar os testes.

O arquivo de configuração padrão para o Spring Data JPA é uma classe Java que cria e configura os beans necessários para o Spring Data. A configuração pode ser feita usando um arquivo XML ou código Java, e escolhemos a segunda alternativa. Crie o seguinte arquivo de configuração para a aplicação "Hello World". 

O Spring Data JPA oferece suporte para camadas de acesso a dados baseados em JPA, reduzindo o código repetitivo #boilerplate e criando implementações automáticas para as interfaces de repositório. Apenas precisamos definir nossa própria interface de repositório estendendo uma das interfaces do Spring Data.

Isso simplifica bastante o desenvolvimento, permitindo que foquemos na lógica do negócio, enquanto o Spring gerencia operações comuns, como salvar, buscar e deletar dados, com base nas interfaces definidas. 

![[JPA - Starting a project.png]]
```java
public interface MessageRepository extends CrudRepository<Message, Long> {  
}
```
1. **MessageRepository:** Criamos uma interface que será responsável por realizar operações no banco de dados relacionado à entidade **Message**.

2. **CrudRepository:** A interface que criamos estende CrudRepository, que é uma interface genérica do Spring Data, fornecendo métodos padrão para operações de CRUD (Create, Read, Update, Delete):
- #Save - salvar ou atualizar uma entidade;
- #findById(Long id) - para buscar uma entidade pelo ID;
- #findAll() - para listar todas as entidades;
- #deleteById() - para excluir uma entidade pelo ID.

3. **Parâmetros genéricos**
- #Message: a entidade que será gerenciada;
- #Long: o tipo do ID usado na entidade Message.

Podemos chamar diretamente métodos como save, findAll ou findByID, que são herdados de #CrudRepository, e usá-los sem precisar de informações adicionais para executar operações comuns no banco de dados. O Spring Data JPA criará uma classe proxy que implementará a interface MessageRepository e os métodos dela.

## 2.7 Comparing the approaches of persisting entities
 Implementamos uma aplicação simples que interage com um banco de dados utilizando, de forma alternada, #JPA, #Hibernate nativo e #Spring_Data_JPA. O objetivo foi analisar cada abordagem e entender as diferenças na configuração e no código. A tabela 2.1 resume as principais caraterísticas de cada uma dessas abordagens.

Para analisar os tempos de execução, realizamos um lote de operações de inserção, atualização, seleção e exclusão utilizando as três abordagens, aumentando progressivamente o número de registros de 1.000 para 50.000. 

Os tempos de execução para inserção usando Hibernate e JPA são muito próximos, no entanto, o tempo de execução do Spring Data JPA aumenta de forma significativamente mais rápida à medida que o número de registros cresce.

As três abordagens apresentem desempenhos distintos. Hibernate e JPA mostram resultados muito próximos, com gráficos de tempos quase sobrepostos para as quatro operações. Apesar de o JPA ter sua própria API sobre o Hibernate, essa **camada adicional** não gera sobrecarga significativa. 

Os tempos de execução para inserções no Spring Data JPA começam cerca de duas vezes mais altos que os do Hibernate e JPA para 1.000 registros, chegando a aproximadamente 3,5 vezes mais para 50.000 registros. **Isso demonstra uma sobrecarga considerável do framework Spring Data JPA**.

O uso do Spring Data JPA é mais justificável em situações específicas, como quando o projeto já utiliza o framework Spring e precisa se basear em seus paradigmas existentes (como inversão de controle ou transações gerenciadas automaticamente), ou quando há uma necessidade significativa de reduzir a quantidade de código e, assim, encurtar o tempo de desenvolvimento (**hoje em dia é mais barato adquirir mais poder computacional do que contratar mais desenvolvedores**).