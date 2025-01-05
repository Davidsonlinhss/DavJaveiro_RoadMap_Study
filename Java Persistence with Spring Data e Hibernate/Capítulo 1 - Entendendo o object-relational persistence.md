## Prefácio
Atualmente, existem diversos programas Java que acessam bancos de dados e dependem de técnicas e frameworks de alto nível, como JPA, Hibernate e Spring Data. 
O mapeamento objeto/relacional e a persistência em Java percorreram um longo caminho desde os seus primeiros dias. 

---
# **Dica de material**
- <span style="background:#ff4d4f">SQL and Relational Theory de C.J. Date (2015)</span> - https://www.amazon.com.br/SQL-Relational-Theory-C-j-Date/dp/1491941170
- Fundamentals of Database Systems Ramez Elmasri - Sistema de Banco de Dados


---
**O capítulo aborda**
- Persistência de dados com SQL em aplicações Java;
- Analisando a incompatibilidade do paradigma objeto/relacional;
- Introdução ao ORM, JPA, Hibernate e Spring Data.

A persistência sempre foi um tópico de debate acalorado na comunidade Java. A persistência é um problema que já foi resolvido pelo SQL e suas extensões, como procedimentos armazenados, ou é um problema mais abrangente que deve ser abordado por frameworks especiais Java? <span style="background:#d4b106">Devemos codificar manualmente até as operações CRUD (criar, ler, atualizar, excluir) mais primitivas em SQL e JDBC, ou esse trabalho deve ser delegado a uma camada intermediária?</span> O debate pode nunca terminar, mas uma solução chamada mapeamento objeto/relacional ( #ORM) agora tem ampla aceitação. Isso se deve em grande parte ao #Hibernate, uma implementação de serviço ORM de código aberto, e ao Spring Data, um projeto abrangente da família Spring cujo propósito é unificar e facilitar o acesso a diferentes tipos de repositórios de persistência, incluindo sistemas de bancos de dados relacionais e bancos de dados #NoSQL

Antes de começarmos a estudar Hibernate e Spring Data, precisamos entender os problemas centrais da persistência de objetos e ORM. Esses problemas deixam claro que precisamos de ferramentas e padrões para minimizar o tempo que gastamos com código relacionado à persistência em nossas aplicações.

## 1.1 What is persistence?
A maioria das aplicações exigem dados persistentes. Persistência é um dos conceitos fundamentais no desenvolvimento de aplicações. Persistência de objetos significa que objetos individuais podem sobreviver ao processa da aplicação; eles podem ser salvos em um repositório de dados e recriados em um momento posterior. <span style="background:#d4b106">Quando falamos sobre persistência em Java, geralmente estamos falando sobre **mapear e armazenar** instâncias de objetos em um banco de dados usando SQL</span>.

Começaremos dando uma breve olhada na persistência e como ela é usada em Java. Munidos dessas informações, continuaremos nossa discussão sobre persistência e veremos como ela é implementada em aplicações orientada a objetos.

### 1.1.1 Relational databases
Os bancos de dados relacionais são uma abordagem flexível e robusta para a gestão de dados. Devido à fundamentação teórica bem pesquisada do modelo de dados relacional, os bancos de dados relacionais podem garantir e proteger a integridade dos dados armazenados, além de possuírem outras características desejáveis.

Os sistemas de gerenciamento de banco de dados relacionais (DBMSs) não são específicos para Java, nem um banco de dados SQL é específico para uma aplicação particular. Esse importante princípio é conhecido como #independência de #dados. Os dados geralmente vivem mais do que uma aplicação. <span style="background:#d4b106">A tecnologia relacional fornece uma maneira de compartilhar dados entre diferentes aplicações, ou entre diferentes partes do mesmo sistema geral (uma aplicação de entrada de dados e uma aplicação de relatórios, por exemplo)</span>. A tecnologia relacional é um denominador comum de muitos sistemas e plataformas de tecnologia diferentes. Portanto, o modelo de dados relacional é frequentemente a base para a representação empresarial de **entidades de negócios**.

<span style="background:#d4b106">Embora comercializado como relacional, um sistema de banco de dados que fornece apenas uma interface de linguagem de dados SQL não é realmente relacional e, de muitas maneiras, não está nem perto do conceito original. </span>

### 1.1.2 Understanding SQL
Para usar JPA, Hibernate e Spring Data de forma eficaz, precisamos de uma sólida compreensão do modelo relacional e do SQL. Será necessário entender o modelo relacional e o modelo de informações e tópicos como normalização para garantir a integridade dos dados. 

<span style="background:#d4b106">Hibernate e Spring Data simplificam muitas tarefas repetitivas de codificação</span>, mas o nosso conhecimento sobre a tecnologia de persistência deve ir além dos próprios farmeworks se <span style="background:#d4b106">quisermos aproveitar todo o poder dos bancos de dados SQL modernos</span>... 

SQL é usado como uma linguagem de definição de dados ( #DDL), com sintaxe para **criar**, **alterar** e **excluir** artefatos como tabelas e restrições no catálogo do DBMS. 
Quando esse esquema está pronto, podemos usar SQL como uma linguagem de manipulação de dados ( #DML) para realizar operações nos dados, incluindo inserções, atualizações e exclusões. 

Podemos recuperar dados executando declarações da linguagem de consulta de dados #DQL com restrições, projeções e produtos cartesianos. Para relatórios eficientes, podemos usar SQL para unir, agregar e agrupar dados conforme necessário. 

Quando os requisitos de negócio mudam, teremos que modificar o esquema do banco de dados novamente com declarações DDL após os dados terem sido armazenados; isso é conhecido como evolução no esquema. Também podemos usar SQL como uma linguagem de controle de dados DLC para conceder e revogar acesso a banco de dados ou a partes dele.

Embora o banco de dados<span style="background:#d4b106"> SQL seja uma parte do ORM</span>, a outra parte, é claro, consiste nos dados em sua aplicação Java que precisam ser persistidos e carregados do banco de dados. 

### 1.1.3 Using SQL in Java
Quando trabalhamos com um banco de dados SQL em uma aplicação Java, emitimos declarações SQL para o banco de dados través da API de Conectividade de Banco de Dados Java (JDBC). Seja SQL escrito manualmente e incorporado no código Java ou gerado dinamicamente pelo código Java, usamos a API JDBC para vincular argumentos ao preparar parâmetros de consulta, executar uma consulta, percorrer os resultados da consulta, recuperar valores de um conjunto de resultados, e assim por diante. Essas são tarefas de acesso a dados em nível baixo; como engenheiros de aplicação, estamos mais interessados no <span style="background:#affad1">problema de negócio</span> que requer esse acesso a dados. <span style="background:#d4b106">O que realmente gostaríamos de escrever é código que salva e recupera instâncias de nossas classes, nos aliviando desse trabalho de baixo nível</span>. 

Como as tarefas de acesso a dados são tediosas, temos que perguntar: o modelo de dados relacional e (especialmente) o SQL são as escolhas certas para<span style="background:#affad1"> persistência em aplicações orientadas a objetos</span>? Podemos responder a essa pergunta inequivocamente: sim! Existem diversas razões pelas quais os bancos de dados SQL dominam a indústria da computação - os sistemas de gerenciamento de bancos de dados relacionais são a única tecnologia de gerenciamento de dados genérica comprovada, e eles são quase sempre uma exigência em projetos Java.

Não estamos afirmando que a tecnologia relacional é sempre a melhor solução. Muitos requisitos de gerenciamento de dados justificam uma abordagem completamente diferente. 

Por exemplo, sistemas distribuídos em escala da internet (motores de busca na web, redes de distribuição de conteúdo, compartilhamento ponto a ponto, mensagens instantâneas) têm que lidar com volumes excepcionais de transações. Muitos desses sistemas não requerem que, após uma atualização de dados ser concluída, todos os processos vejam os mesmos dados atualizados (consistência transacional forte). Os usuários podem estar satisfeitos com uma consistência fraca; após uma atualização, pode haver períodos de inconsistência antes que todos os processos vejam os dados atualizados.

- <span style="background:#d4b106">Consistência forte</span>: assim que o dado é atualizado, todos os processos/máquinas veem imediatamente essa atualização;
- <span style="background:#d4b106">Consistência fraca</span>: após uma atualização, nem todos os processos verão os dados atualizados imediatamente. Pode haver um "atraso" antes que o sistema fique sincronizado.

Em contraste, algumas aplicações científicas trabalham com conjuntos de dados enormes, mas muito especializados. Tais sistemas e seus desafios únicos tipicamente requerem soluções de persistência igualmente únicas e muitas vezes feitas sob medida. Ferramentas genéricas de gerenciamento de dados, como bancos de dados SQL transacionais compatíveis com ACID, JDBC, Hibernate e Spring Data desempenhariam apenas um papel menor para esses tipos de sistemas. 

---
**Sistemas relacionais em escala de internet**
Para entender por que os sistemas relacionais e as garantias de integridade de dados associadas a eles são difíceis de escalar, recomendamos que você primeiro se familiarize com o teorema CAP. Segundo essa regra, um sistema distribuído não pode ser consistente, disponível e tolerante a falhas de partição ao mesmo tempo.

Um sistema pode garantir que todos os nós verão os mesmos dados ao mesmo tempo e que as solicitações de leitura e gravação de dados sempre serão respondidas. Mas, quando uma parte do sistema falha devido a um problema de host, rede ou data center, você deve abdicar da consistência forte ou da disponibilidade de 100%. Na prática, isso significa que você precisa de uma estratégia que detecte falhas de partição e restaure a consistência ou a disponibilidade até certo grau (por exemplo, tornando alguma parte do sistema temporariamente indisponível para que a sincronização de dados possa ocorrer em segundo plano). <span style="background:#d4b106">Frequentemente, os dados, o usuário ou a operação determinarão se a consistência forte é necessária</span>.

Neste livro, consideramos os problemas de armazenamento e compartilhamento de dados no contexto de uma aplicação orientada a objetos que usa um <span style="background:#b1ffff">modelo de domínio</span>. O modelo de domínio é a forma de organizar e estruturar os dados e a lógica da aplicação de acordo com as necessidades do problema que o sistema pretende resolver.

---
#Domínio - é o contexto ou área de interesse do sistema que estamos desenvolvendo. Exemplo:
sistema que gerencia planetas no Sistema Solar: o domínio seria #astornomia , o modelo poderia incluir:
- **Entidades (classes)** - Planeta, Satélite e Estrelas
- **Atributos e métodos** - Na classe Planeta, atributos como nome, massa, tamanho e métodos como orbitar();
- **Relacionamentos** - um planeta pode ter vários Satélites (relação um-para-muitos);
- Um planeta orbita uma **Estrela**;
- --

Em vez de trabalhar diretamente com as linhas e colunas de um java.sql.ResultSet, <span style="background:#affad1">a lógica de negócios da aplicação interagirá com o modelo de domínio orientado a objetos </span>específico da aplicação. Se o esquema do banco de dados SQL de um sistema de leilão online tiver tabelas ITEM e BID, por exemplo, a aplicação Java define classes correspondentes Item e Bid. Em vez de ler e escrever o valor de uma linha e coluna especifica com a API ResultSet, a aplicação carrega e armazena instâncias das classes Item e Bid.

Em tempo de execução, a aplicação opera com instâncias dessas classes. Cada instância de Bid tem uma referência a um Item de leilão, e cada Item pode ter uma coleção de referências a instâncias de Bid. <span style="background:#d4b106">A lógica de negócios não é executada no banco de dados (como um procedimento armazenado em SQL); ela é implementada em Java e executada na camada de aplicação.</span> Isso permite que a lógica de negócios use conceitos sofisticados de orientação a objetos, como #herança e #polimorfismo. Por exemplo, poderíamos usar padrões de design bem conhecidos, como estratégia, mediador e composite, todos os quais dependem de chamadas de métodos polimórficos.

Nem todos as aplicações Java são projetadas dessa forma, nem deveriam ser. <span style="background:#d4b106">Aplicações simples podem se beneficiar muito mais sem um modelo de domínio</span>. Use o ResultSet do JDBC se isso for tudo o que precisamos. Chame procedimentos armazenados existentes e leia os conjuntos de resultados SQL também.  Muitas aplicações precisam executar procedimentos que modificam grandes conjuntos de dados, próximos aos dados. Você também pode implementar algumas funcionalidades de relatório com consultas SQL simples e renderizar os resultados diretamente na tela. SQL e a API JDBC são perfeitamente úteis para lidar com representações tabulares de dados, e o #RowSet do #JDBC torna as operações #CRUD ainda mais fáceis. Trabalhar com tal representação de dados persistentes é direto e bem compreendido

Mas para aplicações com lógica de negócios não trivial, a abordagem de modelo de domínio ajuda a melhorar significativamente a reutilização de código e a manutenção. Na prática, ambas as estratégias são comuns e necessárias.

Por várias décadas, desenvolvedores têm falado sobre um descompasso de paradigmas. Os paradigmas referidos são modelagem de objetos e modelagem relacional, ou, mais praticamente, programação orientada a objetos e SQL. Esse descompasso explica por que cada projeto empresarial despende tanto esforço em questões relacionadas à persistência. Com essa concepção, você pode começar a ver os problemas—alguns bem compreendidos e outros menos compreendidos—que devem ser resolvidos em uma aplicação que combina um modelo de domínio orientado a objetos e um modelo relacional persistente. Vamos dar uma olhada mais de perto neste chamado descompasso de paradigmas.

## 1.2 The paradigm mismatch
Refere-se ao conflito entre dois paradigmas diferentes que precisam trabalhar juntos, <span style="background:#d4b106">mas possuem abordagens ou conceitos incompatíveis</span>. Esse termo é muito comum no desenvolvimento de software, especialmente ao integrar paradigmas #orientados a #objetos (como em linguagens de programação, incluindo Java) com paradigmas relacionais (como em banco de dados SQL). 

Suponhamos que queiramos projetar e implementar uma aplicação de comércio eletrônico online. Nessa aplicação, precisamos de uma classe para representar informações sobre um usuário do sistema e de outra classe para representar informações sobre os detalhes de faturamento do usuário, como mostrado na figura 1.1:
![[Pasted image 20241219222229.png]]
Neste diagrama, podemos ver que um #User tem muitos BillingDetails. Isso é uma composição, indicada pelo diamante cheio. Uma composição é o tipo de associação onde um objeto (BillingDetails no nosso caso) não pode existir conceitualmente sem o contêiner (User no nosso caso). Podemos navegar pela relação entre as classes em ambas as direções; isso significa que podemos iterar através de coleções ou chamar métodos para chegar ao outro lado da relação. As classes que representam essas entidades podem ser extremamente simples:

```java
public class User {
	private Integer id;
	private String username;
	private String address;

	private Set<BillingDetails> billingDetails = new Hashset<>();
	// Constructor, acessor methods, business methods
}

public class BillingDetails {
	private String account;
	private String bankName;
	private User user;

	// Constructor, acessor methods, business methods
}
```

---
**Nota**, no [[Capítulo 3 - Working with data.png]] do livro Spring In Action, fizemos uma composição, entre um pedido #Order e um #taco, podemos ter vários #tacos em um pedido, denotando claramente a relação de composição 1:N.

---
Observe que estamos interessados apenas no estado de persistência das entidades, por isso omitimos a implementação de construtores, métodos de acesso e métodos de negócio.
É fácil criar um design de esquema #SQL para este caso (a sintaxe das consultas a seguir é aplicável ao MySQL):
```MySQL
CREATE TABLE USERS (
	ID INT NOT NULL PRIMARY KEY,
	USERNAME VARCHAR(15) NOT NULL,
	ADDRESS VARCHAR (255) NOT NULL
);

CREATE TABLE BILLINGDETAILS (
	ACCOUNT VARCHAR(15) NOT NULL PRIMARY KEY, // coluna não aceita valores nulos
	BANKNAME VARCHAR(255) NOT NULL,
	USERNAME VARCHAR(15) NO NULL,
	FOREIGN KEY (USERNAME) REFERENCES USERS(USERNAME)
);
```
A coluna USERNAME em BILLINGDETAILS com restrição de chave estrangeira representa a relação entre as duas entidades. Para este modelo de domínio simples, o descompasso objeto/relacional é praticamente imperceptível; é fácil escrever código JDBC para inserir, atualizar e excluir informações sobre usuários e detalhes de faturamento.

Agora, vamos ver o que acontece quando consideramos algo um  pouco mais realista. O descompasso de paradigmas será visível quando adicionarmos mais entidades e relações entre entidades à aplicação.

### 1.2.1 The problem of granularity
Um problema mais óbvio com a implementação atual é que projetamos um endereço como um valor simples de String. Na maioria dos sistemas, é necessário armazenar separadamente as informações de rua, cidade, estado, país e código postal. Claro, podemos adicionar essas propriedades diretamente à classe #user, mas como outras classes no sistema provavelmente também conterão informações de endereço, faz mais sentido criar uma classe Address para reutilizá-la. A figura mostra o modelo atualizado:
![[Pasted image 20241219230554.png]]

A relação entre #User e #Address é uma #agregação, indicada pelo diamante vazio. Devemos adicionar uma tabela ADDRESS? Não necessariamente; é comum manter informações de endereço na tabela USERS, em colunas individuais. Esse design provavelmente <span style="background:#d4b106">terá um desempenho melhor porque uma junção de tabelas não é necessária se quisermos recuperar o usuário e o endereço em uma única consulta</span>. A solução mais elegante pode ser criar um noto tipo de dado SQL para representar endereços e adicionar uma única coluna desse novo tipo na tabela USERS, em vez de adicionar várias novas colunas.

Essa escolha de adicionar várias colunas ou uma única coluna de um novo tipo de dado SQL é um problema de #granularidade. De modo geral, granularidade refere-se ao tamanho relativo dos tipos com os quais estamos trabalhando. 

Vamos voltar ao exemplo. Adicionar um novo tipo de dado ao catálogo do banco de dados para armazenar instâncias #Address do Java em uma única coluna parece ser a melhor abordagem:
```MySQL
	CREATE TABLE USERS (
		USERNAME VARCHAR(15) NOT NULL PRIMARY KEY,
		ADDRESS ADDRESS NOT NULL
	);
```
Um novo tipo Address (classe) em Java e um novo tipo de dado SQL ADDRESS devem garantir a interoperabilidade. Mas encontraremos vários problemas se verificarmos o suporte para tipos de dados definidos pelo usuário (UDTs) nos sistemas de gerenciamento de banco de dados SQL atuais.

O suporte a #UDT (user-Defined Types) é uma das várias chamadas extensões objeto/relacional  ao SQL tradicional. Esse termo por si só é confuso, porque significa que o sistema de gerenciamento de banco de dados tem (ou deveria suportar) um sistema de tipos de dados sofisticados. Infelizmente, o suporte a UDT é um recurso um tanto obscuro na maioria dos DBMSs SQL, e certamente não é portátil entre diferentes produtos. Além disso, o padrão SQL suporta tipos de dados definidos pelo usuário, mas de forma insatisfatória. 

Essa limitação não é culpa do modelo de dados relacional. Podemos considerar que a falha em padronizar uma funcionalidade tão importante é resultado das guerras de banco de dados objeto/relacional entre os fornecedores na metade dos anos 1990. Hoje, a maioria dos engenheiros aceita que os produtos SQL têm sistemas de tipos limitados - sem questionamentos. Mesmo com um sistema UDT sofisticado no seu DBMS SQL, provavelmente ainda duplicaríamos as declarações de tipo, escrevendo o novo tipo em Java e novamente em SQL. Tentativas de encontrar uma solução melhor para o espaço Java, como SQLJ, infelizmente não tiveram muito sucesso. Os produtos DBMS raramente suportam a implementação e execução de classes Java diretamente no banco de dados, e se o suporte estiver disponível, ele é tipicamente limitado a funcionalidades muitos básicas no uso cotidiano.

Por essa e outras razões, o uso de UDTs ou tipos Java em um banco de dados SQL não é uma prática comum no momento, e é improvável que encontre um esquema legado que faça uso extensivo de UDTs. Portanto, não podemos e não iremos armazenar instâncias da nossa classe Address em uma única nova coluna que tenha o mesmo tipo de dado da camada Java.

A solução pragmática para esse problema é usar várias colunas de tipos SQL embutidos definidos pelo fornecedor (como tipos de dados booleanos, numéricos e de string). Definimos a tabela user da seguinte forma:
```MySQL
CREATE TABLE USERS (
	USERNAME VARCHAR(15) NOT NULL PRIMARY KEY,
	ADDRESS_STREET VARCHAR(255) NOT NULL,
	ADDRESS_ZIPCODE VARCHAR(5) NOT NULL,
	ADDRESS_CITY VARCHAR(255) NOT NULL
);
```

Portanto, a abordagem pragmática e amplamente adotada é representar a estrutura complexa de objetos (como Address) com várias colunas baseadas em tipos de dados SQL nativos, garantindo compatibilidade e simplicidade.

As classes no modelo de domínio Java possuem diferentes níveis de granularidade: desde classes de entidade mais gerais, como User, até classes mais específicas, como Address, e até mesmo classes simples como SwissZipCode, que estende AbstractNumericZipCode (ou qualquer outro nível de abstração desejado). Em contraste, no banco de dados SQL, apenas dois níveis de granularidade de tipo são visíveis: tipos de relação criadas por noós, users e billingdetails e tipos de dados internos, como #VARCHAR, #BIGINT e #TIMESTAMP.

Muitos mecanismos simples de persistência não reconhecem essa discrepância e acabam forçando a representação menos flexível do SQL sobre o modelo orientado a objetos, efetivamente achatando-o. No entanto, o problema de granularidade não é especialmente difícil de resolver, embora seja evidente em muitos sistemas existentes. Veremos a solução para esse problema na seção 5.1.1.

Um problema muito mais difícil e interessante surge quando consideramos modelos de domínio que dependem de #herança, um recurso do design orientado a objetos que você pode usar para cobrar os usuários da sua aplicação de e-commerce de maneiras novas e criativas.

### 1.2.2 The problem of inheritance
Em Java, implementamos a herança de tipos usando superclasses e subclasses. Para ilustrar por que isso pode apresentar um problema de descompasso, vamos modificar nossa aplicação de comércio eletrônico para que agora possamos aceitar não apenas faturamento por conta bancária, mas também cartões de crédito. A maneira mais natural de refletir essa mudança no modelo é usar herança para a superclaasse BiillingDetails, juntamente com várias subclasses concretas: CreditCaard e BankAccount. Cada uma dessas subclasses define dados ligeiramente diferentes (e funcionalidades completamente diferentes que atuam nesses dados). O diagrama de classes UML figura 1.3 ilustra esse modelo.

![[Pasted image 20241219234256.png]]
Quais mudanças devemos fazer para suportar essa estrutura de classes Java atualizada? <span style="background:#d4b106">Podemos criar uma tabela CREDITCARD que estenda BILLINGDETAILS?</span> Produtos de banco de dados SQL geralmente não implementam herança de tabelas (ou mesmo herança de tipos de dados), e se o fazem, não seguem uma sintaxe padrão. 

Assim que introduzimos a herança no modelo, temos a possibilidade de polimorfismo. A classe User tem uma associação polimórfica com a superclasse BillingDetails. Em tempo de execução, uma instância de User pode referenciar uma instância de qualquer uma das subclasses de BillingDetails. Da mesma forma, queremos ser capazes de escrever consultas polimórficas que se refiram à classe BillingDetails e que a consulta retorne instâncias de suas subclasses.

Claro, aqui está a tradução:

---

Os bancos de dados SQL carecem de uma maneira óbvia (ou pelo menos padronizada) de representar uma associação polimórfica. Uma restrição de chave estrangeira refere-se exatamente a uma tabela de destino; não é simples definir uma chave estrangeira que se refira a várias tabelas.

O resultado desse descompasso de subtipos é que a estrutura de herança em um modelo deve ser persistida em um banco de dados SQL que não oferece um mecanismo de herança. No capítulo 7, discutiremos como soluções ORM, como o Hibernate, resolvem o problema de persistir uma hierarquia de classes em uma tabela ou tabelas de banco de dados SQL, e como o comportamento polimórfico pode ser implementado. Felizmente, esse problema é agora bem compreendido na comunidade, e a maioria das soluções oferece aproximadamente a mesma funcionalidade.

O próximo aspecto do problema de descompasso objeto/relacional é a questão da identidade do objeto.

### 1.2.3 The problem of identity
O exemplo definiu USERNAME como a chave primária da tabela USERS. Foi uma boa escolha? Como lidamos com objetos idênticos em Java? Embora o problema de identidade possa não ser óbvio à primeira vista, encontraremos frequentemente em nosso sistema de comércio eletrônico em crescimento e expansão, como quando precisamos verificar se duas instâncias são idênticas. Existem três maneiras de abordar esse problema: duas no mundo Java e uma no banco de dados SQL. Como esperado, elas funcionam juntas apenas com alguma ajuda. 
Java define duas diferentes noções de semelhanças:
- Identididade de instância (aproximadamente equivalente a uma localização de memória, verificada com a == b);
- Igualdade de instância, determinada pela implementação do método #equals(também chamada de igualdade por valor);

Por outro lado, a identidade de uma linha de banco de dados é expressa como uma comparação de valores de chave primária. Como veremos, nem equals nem == são sempre equivalentes a uma comparação de valores de chave primária. É comum que várias instâncias não idênticas em Java representem simultaneamente a mesma linha de um banco de dados, como em threads de aplicação executando concorrentemente. Além disso, existem algumas dificuldades sutis envolvidas em implementar equals() corretamente para uma classe persistente e em entender quando isso pode ser necessário. 

Vamos usar um exemplo para discutir outro problema relacionado à identidade do banco de dados. Na definição da tabela `USERS`, `USERNAME` é a chave primária. Infelizmente, essa decisão torna difícil mudar o nome de um usuário; é necessário atualizar não apenas a linha em `USERS`, mas também os valores de chave estrangeira em (muitas) linhas de `BILLINGDETAILS`. <span style="background:#d4b106">Para resolver esse problema, mais adiante neste livro recomendaremos que você use chaves substitutas sempre que não conseguir encontrar uma boa chave natural</span>. Também discutiremos o que faz uma boa chave primária. Uma coluna de chave substituta é uma coluna de chave primária que não tem significado para o usuário da aplicação — em outras palavras, uma chave que não é apresentada ao usuário da aplicação. Seu único propósito é identificar dados dentro da aplicação.
Por exemplo, você pode mudar suas definições de tabelas para se parecerem com isto:
```MySQL
CREATE TABLE USERS (
	ID BIGINT NOT NULL PRIMARY KEY,
	USERNAME VARCHAR(15) NOT NULL UNIQUE,
	...
);

CREATE TABLE BILLINGDETAILS (
	ID BIGINT NOT NULL PRIMARY KEY,
	ACCOUNT VARCHAR(15) NOT NULL,
	BANKNAME VARCHAR(255) NOT NULL,
	USER_ID BIGINT NOT NULL,
	FOREIIGN KEY (USER_ID) REFERENCES USERS(ID)
)
```

As colunas `ID` contêm valores gerados pelo sistema. Essas colunas foram introduzidas puramente para o benefício do modelo de dados, então como (se é que devem) elas devem ser representadas no modelo de domínio Java? Discutiremos essa questão na seção 5.2, e encontraremos uma solução com ORM.

No contexto de persistência, a identidade está intimamente relacionada a como o sistema lida com cache e transações. Diferentes soluções de persistência escolheram diferentes estratégias, e isso tem sido uma área de confusão. Cobriremos todos esses tópicos interessantes — e veremos como eles estão relacionados — na seção 9.1.

Até agora, a aplicação de comércio eletrônico esqueleto que projetamos expôs os problemas de descompasso de paradigma com mapeamento de granularidade, subtipos e identidade. Precisamos discutir mais o conceito importante de associações: <span style="background:#d4b106">como as relações entre entidades são mapeadas e tratadas</span>. A restrição de chave estrangeira no banco de dados é tudo o que você precisa?


---
**Resumo**
#Paradigm #mismatch refere-se ao conflito entre paradigmas de programação orientada a objetos (como Java) e paradigmas relacionais (bancos SQL). Esse descompasso é evidenciado em problemas como #granularidade, #herança e #identidade.
- **Granularidade** dados complexos, como um endereço, podem ser representados por uma classe com atributos (rua, cidade, etc). Já no SQL, esses mesmos dados são divididos em colunas individuais ou, em casos específicos, em tipos compostos ( #UDT), que nem sempre são amplamente suportados pelos bancos de dados ou são portáveis.
- **Herança** Java permite modelar hierarquias com classes e subclasses, mas os bancos de dados relacionais não possuem suporte nativo para essa estrutura. Modelar herança no SQL exige soluções alternativas, como usar tabelas separadas para cada classe ou uma única tabela com colunas adicionais para diferenciar tipos.
- **Identidade** Em Java, dois objetos podem ser comparados por referência (usando == ) ou por valores dos atributos (usando equals). No SQL, a identidade de um registro é definida pela chave primária. Alterações em chaves primárias (como chaves naturais) ou a necessidade de sincronizar objetos Java com registros no banco podem causar inconsistências.

### 1.2.4 The problem of associations
No modelo de domínio, #associações representam os relacionamentos entre entidades. As classes user, Address e BillingDetails estão todas associadas; mas ao contrário de Address, BillingDetails é independente. O mapeamento de associações e a gestão de associações entre entidades são centrais em qualquer solução de persistência de objetos.

Linguagens orientadas a objetos representam associações usando referências de objetos, mas no mundo relacional, uma coluna com restrição de chave estrangeiro representa uma associação com cópias de valores-chave. A restrição é uma regra que garante a integridade da associação. Existem diferenças substancias entre os dois mecanismos.

Referências de objetos são inerentemente direcionais; a associação é de uma instância para a outra. Elas são ponteiros. Se uma associação entre instâncias deve ser navegável em ambas as direções, devemos definir a associação duas vezes, uma em cada uma das classes associadas. O diagrama de classes UML abaixo ilustra esse modelo com uma associação um-para-muitos.

![[Pasted image 20241220202908.png]]
Os relacionamentos são representados por #foreign_keys. Exemplo:
- A tabela BillingDetails teria uma coluna chamada **user_id** que armazena a chave primária da tabela User;
- Isso cria uma associação entre os registros nas tabelas. 

**Gestão de associações bidirecionais**
- <span style="background:#d4b106">Unidirecional</span>: A associação é navegável em apenas um sentido, um User sabe quais são seus BillingDetails, mas os BillingDetails não têm referência de volta para o User;
- <span style="background:#d4b106">Bidirecional</span>: ambos os lados da associação possuem referência entre si. Um user contém um conjunto de BillingDetails e BillingDetails também armazena o User a que pertence.


Já vimos isso nas classes do modelo de domínio:
```Java
public class User {
	private Set<BillingDetails> billingDetails = new HashSet<>();
}

public class BillingDetails {
	private User user;
}
```

A navegação em uma direção específica não tem significado para um modelo de dados relacional, porque podemos criar associações de dados com operadores de #junção e #projeção. O desafio é mapear um modelo de dados completamente aberto que é independente da aplicação que trabalha com os dados para um modelo de navegação dependente da aplicação - uma visão limitada das associações necessárias para essa aplicação específica.

Associações em Java podem ter multiplicidade de muitos-para-muitos. O diagrama de classe UML abaixo ilustra esse modelo:
![[Pasted image 20241220212724.png]]

```java
public class User {
	private Set<BillingDetails> billingDetails = new HashSet<>();
}

public class BillingDetails {
	private Set<User> users = new HashSet<>();
}
```

No entanto, a declaração de chave estrangeira na tabela BILLINGDETAILS é uma associação muitos-para-um: cada conta bancária está vinculada a um usuário específico, mas cada usuário pode ter várias contas bancárias vinculadas.

Se precisemos representar uma associação muitos-para-muitos em um banco de dados SQL, devemos introduzir uma nova tabela, geralmente chamada de tabela de ligação. Na maioria dos casos, essa tabela não aparece em nenhum lugar do modelo de domínio. Para este exemplo, se considerarmos que a relação entre o usuário e as informações de faturamento é muito-para-muitos, definiremos a tabela de ligação da seguinte forma:
```SQL
CREATE TABLE USER_BILLINGDETAILS (
	USER_ID BIGINT,
	BILLINGDETAILS_ID BIGINT,
	PRIMARY KEY (USER_ID, BILLINGDETAILS_ID),
	FOREIGN KEY (USER_ID) REFERENCES USERS(ID),
	FOREIGN KEY (BILLINGDETAILS_ID) REFERENCES BILLINGDETAILS(ID)
);
```

Você não precisa mais da coluna de chave estrangeira `USER_ID` e da restrição na tabela `BILLINGDETAILS`; esta tabela adicional agora gerencia os links entre as duas entidades. Discutiremos mapeamentos de associações e coleções em detalhes no capítulo 8.

Até agora, os problemas que consideramos são principalmente estruturais: você pode vê-los considerando uma visão puramente estática do sistema. Talvez o problema mais difícil na persistência de objetos seja um problema dinâmico: <span style="background:#d4b106">como os dados são acessados em tempo de execução</span>.

### 1.2.5 The problem of data navigation
Existe uma diferença fundamental entre como acessamos dados em código Java e em um banco de dados relacional. Em #Java, quando acessarmos as informações de faturamento de um usuário, chamamos someUser.getBillingDetails().iterator().next() ou algo semelhante. 

A partir do Java 8, podemos chamar someUser.getBillingDetails().stream().filter(someCondition).map(someMapping).forEach(billingDetails -> {doSomethin(billinDetails)}). Esta é a maneira mais natural de acessar dados orientados a objetos, e muitas vezes é descrita como "navegar na rede de objetos". Navegamos de uma instância para outra, até mesmo iterando coleções, seguindo ponteiros preparados entre as classes. Infelizmente, <span style="background:#d4b106">esta não é uma maneira eficiente de recuperar dados de um banco de dados SQL</span>.

A coisa mais importante que podemos fazer para melhorar o desempenho do código de acesso a dados é minimizar o número de solicitações ao banco de dados. A maneira mais óbvia de fazer isso é minimizar o número de consultas SQL. (Claro, outras formas mais sofisticadas - como caching extensivo - seguem como um segundo passo).

Portanto, o acesso eficiente aos dados relacionais com SQL geralmente requer #joins entre a tabelas de interesse. O número de tabelas incluídas no join ao recuperar dados determina a profundidade da rede de objetos que podemos navegar na memória. Por exemplo, se precisar recuperar um Usuário e não estivermos interessado nas informações de faturamento do usuário, podemos escrever esta consulta simples:
```sql
SELECT * FROM USERS WHERE ID = 123
```

Por outro lado, se precisar recuperar um Usuário e, em seguida, visitar cada uma das instâncias de BillingDetails associadas (digamos, para listar as contas bancárias do usuário), escreveremos uma consulta diferente:
```sql
SELECT * FROM USER, BILLINGDETAILS
WHERE USER.ID = 123 
AND BILLINGDETAILS.ID = USERS.ID;
```

Como podemos ver, para usar joins eficientemente, precisamos saber qual parte da rede de objetos planeja acessar antes de começar a navegar na rede de objetos! Cuidado, se recuperarmos muitos dados (provavelmente mais do que precisamos), estaremos desperdiçando memória na camada de aplicação. Podemos sobrecarregar o banco de dados SQL com conjuntos de resultados de produto cartesiano gigantescos. Imagine recuperar não apenas usuários e contas bancárias em uma consulta, mas também todos os pedidos pagos de cada conta bancária, os produtos em cada pedido, e assim por diante...

Qualquer solução de persistência de objetos permite buscar os dados de instâncias associadas somente quando a associação é acessada pela primeira vez no código Java. Isso é conhecido como carregamento lento (lazy loading).
#lazy_loading: recuperar dados apenas sob demanda. Esse estilo de fragmento de acesso a dados é fundamentalmente ineficiente no contexto de um banco de dados SQL, pois requer a execução de uma declaração para cada nó ou coleção da rede de objetos que é acessada. Este é o temido problema de seleções n+1. Em nosso exemplo, <span style="background:#d4b106">precisamos de uma seleção para recuperar um Usuário e depois n seleções para cada uma das n instâncias</span> de BillingDetails associadas.

Esse descompasso na forma como você acessa dados no código Java e dentro de um banco de dados relacional é talvez a fonte mais comum de problemas de desempenho em sistemas de informação Java. Evitar os problemas de produto cartesiano e seleções n+1 ainda é um problema para muitos programadores Java. O #Hibernate oferece recursos sofisticados para buscar redes de objetos do banco de dados de forma eficiente e transparente para a aplicação que os acessa. Discutiremos esses recursos no capítulo 12.

Agora temos uma lista considerável de problemas de descompasso objeto/relacional: o problema de granularidade, o problema de herança, o problema de identidade, o problema de associações e o problema de navegação de dados. Pode ser custoso (em tempo e esforço) encontrar soluções, como VOCÊ DEVE SABER POR EXPERIÊNCIA. (EU AINDA NÃO TENHO EXPERIÊNCIA)! Levará uma grande parte deste livro para fornecer respostas detalhadas a essas questões e demonstrar o ORM como uma solução viável. Vamos começar com uma visão geraal do ORM, o padrão de Persistência JAVA (JPA) e os projetos Hibernate e Spring Data.

## 1.3 ORM, JPA, Hibernate and Spring Data
Em resumo, o mapeamento objeto/relacional (ORM) <span style="background:#d4b106">é a persistência automatizada (e transparente) de objetos em uma aplicação</span> Java para as tabelas em um SGBDR (sistema de gerenciamento de banco de dados relacional), usando metadados que descrevem o mapeamento entre as classes da aplicação e o esquema do banco de dados SQL. Essencialmente, o ORM funciona transformando (reversivelmente) dados de uma representação para outra. Um programa que utiliza ORM fornecerá as metainformações sobre como mapear os objetos da memória para o banco de dados, e a transformação efetiva será realizada pelo ORM.

Algumas pessoas podem considerar uma vantagem do ORM ser que ele protege os desenvolvedores de SQL complicado. Essa visão sustenta que os desenvolvedores orientados a objetos não devem ser obrigados a se aprofundar em SQL ou banco de dados relacionais. Pelo contrário, os desenvolvedores Java devem ter um nível suficiente de familiaridade com - e apreciação por - modelagem relacional e SQL para trabalhar com Hibernate e Spring Data. ORM é uma técnica avançada usada por desenvolvedores que já fizeram isso da maneira difícil. 

JPA (Jakarta Persistence API, anteriormente Java Persistence API) é uma especificação que define uma API que gerencia a persistência de objetos e mapeamentos objeto/relacional. Hibernate é a implementação mais popular dessa especificação. Portanto, JPA especificará o que deve ser feito para persistir objetos, enquanto o Hibernate determinará como fazer isso. 

Spring Data Commons, como parte da família Spring Data, fornece os conceitos principais do framework Spring que suportam todos os módulos Spring Data. Spring Data JPA, outro projeto da família Spring Data, é uma camada adicional sobre as implementações JPA (como Hibernate). 

Para usar o Hibernate de forma eficaz, devemos ser capaz de visualizar e interpretar as instruções SQL que ele emite e entender suas implicações de desempenho. Para aproveitar os benefícios do Spring Data, devemos ser capazes de antecipar como o código padrão e as consultas geradas são criados.

A especificação JPA define o seguinte:

- Uma funcionalidade para especificar metadados de mapeamento—como as classes persistentes e suas propriedades se relacionam com o esquema do banco de dados. O JPA depende fortemente de anotações Java nas classes do modelo de domínio, mas você também pode escrever mapeamentos em arquivos XML
- APIs para realizar operações básicas de CRUD em <span style="background:#d4b106">instâncias de classes</span> persistentes, principalmente `javax.persistence.EntityManager` para armazenar e carregar dados.
- Uma linguagem e APIs para especificar consultas que se referem a classes e propriedades de classes. Esta linguagem é a Jakarta Persistence Query Language (JPQL) e se parece com SQL. A API padronizada permite a criação programática de consultas de critérios sem manipulação de strings.
- Como o mecanismo de persistência interage com instâncias transacionais para realizar verificação de sujeira (_dirty checking_), busca de associações e outras funções de otimização. A especificação JPA cobre algumas estratégias básicas de caching.

Hibernate implementa JPA e suporta todos os mapeamentos padronizados, consultas e interfaces de programação. Alguns dos benefícios:
- **Produtividade**: O Hibernate elimina muito do trabalho repetitivo (mais do que você esperaria) e permite que você se concentre no problema de negócios. Não importa qual estratégia de desenvolvimento de aplicação você prefere — de cima para baixo (começando com um modelo de domínio) ou de baixo para cima (começando com um esquema de banco de dados existente) — o Hibernate, usado juntamente com as ferramentas apropriadas, reduzirá significativamente o tempo de desenvolvimento.
- **Manutenibilidade**: O ORM automatizado com Hibernate reduz linhas de código, tornando o sistema mais compreensível e mais fácil de refatorar. O Hibernate proporciona um buffer entre o modelo de domínio e o esquema SQL, isolando cada modelo de pequenas mudanças no outro.
- **Desempenho**: Embora a persistência codificada manualmente possa ser mais rápida no mesmo sentido que o código assembly pode ser mais rápido que o código Java, soluções automatizadas como o Hibernate permitem o uso de muitas otimizações o tempo todo. Um exemplo é o cache eficiente e facilmente ajustável na camada de aplicação. Isso significa que os desenvolvedores podem gastar mais energia otimizando manualmente os poucos gargalos reais restantes em vez de otimizar prematuramente tudo.
- **Independência de fornecedor**: O Hibernate pode ajudar a mitigar alguns dos riscos associados ao bloqueio de fornecedor. Mesmo que você planeje nunca mudar seu produto DBMS, ferramentas ORM que suportam vários DBMSs diferentes permitem um certo nível de portabilidade. Além disso, a independência de DBMS ajuda em cenários de desenvolvimento onde os engenheiros usam um banco de dados local leve, mas implementam para testes e produção em um sistema diferente.

O Spring Data torna a implementação da camada de persistência ainda mais eficiente. O Spring Data JPA, um dos projetos da família, está sobre a camada JPA. O Spring Data JDBC, outro projeto da família, está sobre o JDBC. Vamos ver alguns dos benefícios do Spring Data:
**Infraestrutura Compartilhada**: O Spring Data Commons, parte do projeto guarda-chuva Spring Data, fornece um modelo de metadados para persistência de classes Java e interfaces de repositório neutras em termos de tecnologia. Ele oferece suas capacidades aos outros projetos do Spring Data.
**Remove Implementações DAO**: As implementações JPA utilizam o padrão de objeto de acesso a dados (DAO). Esse padrão começa com a ideia de uma interface abstrata para um banco de dados e mapeia chamadas de aplicação para a camada de persistência, ocultando os detalhes do banco de dados. O Spring Data JPA torna possível remover totalmente as implementações DAO, tornando o código mais curto.
**Criação Automática de Classes**: Usando o Spring Data JPA, uma interface DAO precisa estender a interface de repositório específica do JPA — `JpaRepository`. O Spring Data JPA criará automaticamente uma implementação para essa interface — o programador não precisará cuidar disso.
**Implementações Padrão para Métodos**: O Spring Data JPA gerará implementações padrão para cada método definido por suas interfaces de repositório. Operações básicas de CRUD não precisam mais ser implementadas. Isso reduz o código padrão, acelera o desenvolvimento e elimina a possibilidade de introduzir bugs.
**Consultas Geradas**: Você pode definir um método na sua interface de repositório seguindo um padrão de nomenclatura. Não há necessidade de escrever suas consultas manualmente; o Spring Data JPA analisará o nome do método e criará uma consulta para ele.
**Próximo ao Banco de Dados, se Necessário**: O Spring Data JDBC pode se comunicar diretamente com o banco de dados e evitar a "mágica" do Spring Data JPA. Ele permite interagir com o banco de dados por meio do JDBC, mas remove o código padrão usando as facilidades do framework Spring.

