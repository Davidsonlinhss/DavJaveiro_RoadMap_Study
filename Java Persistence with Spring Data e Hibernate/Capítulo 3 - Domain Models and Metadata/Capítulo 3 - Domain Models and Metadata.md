O capítulo abordará
- Introdução ao exemplo da aplicação CaveatEmptor (*Que o comprador esteja atento.*)
- Implementação do modelo de domínio
- Análise das opções de metadados para mapeamento objeto/relacional

O exemplo "Hello World" do capítulo anterior apresentou uma introdução ao Hibernate, Spring Data e JPA, mas não é o suficiente para compreender os requisitos de aplicações do mundo real com modelos de dados complexos. Ao longo do restante do livro, vamos utilizar um exemplo mais sofisticado - criaremos um sistema de leilão online - para demonstrar o Uso do JPA, Hibernate e, posteriormente, Spring Data.

Começaremos a discussão sobre a aplicação apresentando sua #arquitetura em #camadas. Em seguida, aprenderemos a identificar as entidades de negócios de um domínio problemático. Criaremos um modelo conceitual dessas entidades e seus atributos, chamado de **modelo de domínio**, e o implementará em Java por meio da criação de **classes persistentes**.

Vamos explorar como as classes Java devem ser estruturadas e onde elas se encaixam dentro de uma arquitetura típica em camadas. Também examinaremos as capacidades de persistência dessas classes e como isso influencia o design e a implementação da aplicação. Adicionaraemos a #Bean_Validation, que ajudará a verificar automaticamente a integridade dos dados do modelo de domínio - tanto as informações persistentes quanto a lógica de negócio.

Além disso, vamos analisar o uso de metadados de mapeamento - os meios de informar ao Hibernate como as classes persistentes e suas propriedades se relacionam com tabelas e colunas do banco de dados. Isso pode ser feito de forma simples, adicionando anotações diretamente no código-fonte Java ou utilizando documentos XML que são implementados junto com as classes Java compiladas que o Hibernate acessa em tempo de execução.

---
**Novas funcionalidades principais do JPA 2**
1. **Integração Automática com Bean Validation**
Agora, um provedor de persistência JPA integra-se automaticamente com um provedor de validação de beans ( #Bean_Validation )
Quando os dados são armazenados, o provedor valida automaticamente as restrições definidas nas classes persistentes, garantindo integridade e consistência dos dados.
2. **API de Metamodelo**
Foi adicionada uma API de Metamodelo que permite acessar nomes, propriedades e metadados de mapeamento das classes em uma entidade de persistência.
Essa funcionalidade é útil para inspecionar e trabalhar dinamicamente com o modelo de dados persistente.


---

## 3.1 The Example CaveatEmptor application
A aplicação de leilão online fará uso de técnicas de #ORM, #JPA, #Hibernate e funcionalidades do Spring Data. Não daremos muita atenção à interface do usuário (que poderia ser baseada na web); vamos nos concentrar exclusivamente no código de acesso a dados.

Para entender os desafios de design envolvidos no ORM, vamos imaginar que a aplicação ainda não existe e que estamos construindo-a do zero. Vamos começar analisando a sua arquitetura.

### 3.1.1 A layered architecture
Em qualquer aplicação não trivial, faz sentido organizar as classes por **preocupação**. #Persistência é uma dessas preocupações, assim como #apresentação, fluxo de trabalho, e #lógica de negócios. Uma arquitetura orientada a objetos típica inclui camadas de código que representam essas preocupações. 
**Preocupações** -> #Domínio , #aspecto. Logo, faz mais sentido organizarmos as classes por domínio. 

---
**Aspectos/Preocupações transversais**
Também existem os chamados **aspectos transversais**, que podem ser implementados de forma genérica, como por meio de código de Framework. Exemplos típicos de aspectos transversais incluem **registros de logs**, autorização e demarcação de transações.

---
Uma arquitetura em camadas define interfaces entre o código que implementa as diferentes áreas de responsabilidade, permitindo que **mudanças na implementação de uma área sejam feitas sem causar grandes interrupções no código de outras camadas**. O empilhamento de camadas determina os tipos de dependências entre elas. As regras são as seguintes:
- **As camadas se comunicam de cima para baixo**. Uma camada depende apenas da interface da camada diretamente abaixo dela. 
- **Cada camada não tem conhecimento de outras camadas, exceto da camada imediatamente abaixo e, eventualmente, da camada acima, caso receba solicitações explícitas dessa camada**. 
Este modelo facilita a manutenção e a escalabilidade, permitindo que ajustes em uma camada não afetem diretamente as demais, desde que as interfaces sejam respeitadas.

Diferentes sistemas agrupam as áreas de **responsabilidade de maneira distintas**, definindo camadas diferentes. A arquitetura de aplicação típica, comprovada e de alto nível, usa três camadas:
1. **Apresentação
2. **Lógica de negócios**
3. **Persistência**
Essas camadas são separadas para facilitar o gerenciamento, a manutenção e a escalabilidade do sistema, permitindo que cada parte do sistema evolua de forma independente.

![[Capítulo 3 - Domain Models and Metadata.png]]
- **Presentation Layer** - A <span style="background:#d4b106">lógica de interface do usuário</span> é a camada mais alta. O código responsável pela apresentação e controle da navegação de páginas e telas está na camada de **apresentação**. O código da interface do usuário pode acessar diretamente as entidades de negócios do modelo de domínio compartilhado e exibi-las na tela, juntamente com controles para executar ações. Em algumas arquiteturas, **as instâncias das entidades de negócios podem não ser acessíveis diretamente pelo código da interface de usuário**, como quando **a camada de apresentação não está rodando na mesma máquina que o restante do sistema**. 

- **Business Layer** - A camada de negócio é geralmente responsável pela implementação de todas as regras de negócio ou os requerimentos do sistema que são parte do domain problem. Essa camada costuma incluir algum tipo de componente controlador - o código que sabe quando invocar cada regra de negócio. Em alguns sistemas, essa camada possui sua própria representação interna das entidades do domínio de negócios. Alternativamente, ela pode depender de uma implementação do modelo de domínio compartilhada com as outras camadas da aplicação. Um bom exemplo da camada de negócios é o **código responsável por executar a lógica de negócios**. 

- **Persistence Layer** - A camada de persistência é um conjunto de classes e componentes responsáveis por armazenar dados e recuperá-los de um ou mais repositórios de dados. Essa camada precisa de um modelo das entidades de negócios para as quais desejamos manter um estado persistente. <span style="background:#d4b106">É a camada onde ocorre o uso do JPA, Hibernate e Spring Data</span>.

- **Database** - o banco de dados geralmente é externo. Ele é a representação persistente real do estado do sistema. Se um banco de dados SQL for usado, ele inclui um esquema e, possivelmente, procedimentos armazenados para a execução de lógica de negócios próxima aos dados. O banco de dados é o local onde os dados são persistidos a longe prazo. 

- **Classes Auxiliares e Utilitários** - toda a aplicação possui um conjunto de classes auxiliares ou utilitárias de infraestrutura que são usadas em cada camada da aplicação. Classes logs, segurança ou cache. 

### 3.1.2 Analyzing the business domain
Precisamos analisar os problemas de negócios que nosso sistema de software precisa resolver, identificando as #entidades principais relevantes e suas interações. O objetivo principal da <span style="background:#d4b106">análise e do design de um modelo de domíni</span>o é capturar a essência das informações de negócios para o propósito da aplicação. 
As #entidades geralmente são noções compreendidas pelos usuários do sistema: 
1. pagamento;
2. cliente;
3. pedido
4. item
5. lance
6. entre outras...

Algumas entidades podem ser abstrações de coisas menos concretas que o usuário pensa, como um algoritmo de precificação, mas até essas abstrações geralmente são compreensíveis para o usuário. Podemos encontrar todas essas entidades na visão conceitual do negócio, às vezes chamadas de modelo de informação.

A partir do modelo de negócios, engenheiros e arquitetos de softwares orientado a objetos criam um modelo orientado a objetos, ainda no nível conceitual (sem código Java). Este modelo pode ser tão <span style="background:#d4b106">simples quanto uma imagem mental que existe apenas na mente do desenvolvedor</span>, ou pode ser tão <span style="background:#d4b106">elaborado quanto um diagrama de classes UML</span>. Exemplo:
![[Pasted image 20241229153505.png]]

Este modelo contém entidades que provavelmente encontraremos em qualquer sistema de comércio eletrônico típico: categoria, item e usuário. Este modelo de domínio representa todas as entidades e seus relacionamentos (e, talvez, seus atributos). Este tipo de modelo orientado a objetos das entidades do domínio do problema, abrangendo apenas aquelas entidades que são de interesse para o usuário, é chamado de modelo de domínio. <span style="background:#d4b106">Ele é uma visão visão abstrata do mundo real</span>.

O **modelo de domínio** é uma representação conceitual e simplificada das entidades-chave no sistema, focando apenas nas informações que são relevantes para os usuários e os processos de negócios. Ele não se preocupa com a implementação específica ou com o código, mas sim com a modelagem das entidades e como elas interagem entre si dentro do contexto do sistema. 

As #entidades são os conceitos centrais do sistema, e elas interagem entre si de maneira que **simula o funcionamento do mundo real**. Um **cliente** pode fazer um **pedido**, o **pedido** contém **produtos** e o **pagamento** é feito para completar o pedido.

O modelo de domínio é uma **visão do que o sistema precisa fazer**, mas sem se preocupar com a forma como isso será feito tecnicamente, essa parte técnica fica para a **engenharia de requisitos**. 

Ao invés de usar modelo orientado a objetos, engenheiros e arquitetos podem começar o design da aplicação com um modelo de dados. Isso pode ser expresso com um diagrama entidade-relacionamento ( **entity-relationship diagram**), e ele conterá as entidades CATEGORIA, ITEM e USUÁRIO, juntamento com os relacionamentos entre elas. Geralmente, dizemos que, em relação à persistência, **há pouca diferença entre os dois tipos de modelos**; eles são apenas pontos de partidas diferentes. No final, qual linguagem de modelagem utilizarmos será secundária; o que mais nos interessa é a **estrutura das entidades de negócios** e os **relacionamentos entre elas**. 

### 3.1.3 The CaveatEmptor domain model
O site CaveatEmptor permitirá que os usuários leiloem diversos tipos de itens, desde equipamentos eletrônicos até passagens aéreas. Os leilões seguirão a estratégia de leilão inglês: os usuários continuarão fazendo lances em um item até que o período de lances para esse item termine, e o maior lance vencerá.

Em qualquer loja, os produtos são categorizados por tipo e agrupados com itens semelhantes em seções e prateleiras. O **catálogo** de leilões exige algum tipo de **hierarquia de categorias de itens** para que o **comprador** possa navegar pelas categorias ou realizar buscas com base em categorias e atributos dos **itens**. Listas de itens serão exibidas no navegador de categorias e nas telas de resultados de busca. Selecionar um item de uma lista levará o comprador a uma visão detalhada do item, onde ele pode ter imagens anexadas. 

Um leilão consiste em uma sequência de lances, sendo um deles o lance vencedor. Os detalhes do usuário incluirão nome, endereço e informações de faturamento. 

O resultado dessa análise, uma visão geral de alto nível do modelo de domínio, é mostrado na figura 3.3 abaixo, vamos discutir algumas características com relação ao modelo:
![[Capítulo 3 - Domain Models and Metadata-1.png | Domain Model CaveatEmptor]]
- Cada **item** pode ser leiloado apenas uma vez, então não é necessário diferenciar #Item de qualquer entidade de leilão. Logo, utilizamos uma única entidade de item de leilão chamada #Item. Assim, #Bid (lance) está diretamente associado a #Item. As informações de endereço de um #User (usuário) são modeladas como uma classe separada - um usuário pode ter até três endereços: residencial, de cobrança e de envio. Também permitimos que um usuário tenha vários #BillingDetails (Detalhes de Cobrança). Subclasses de uma classe abstrata representam as diferentes estratégias de cobrança, possibilitando futuras extensões.

- A aplicação pode aninhar uma #Category (Categoria) dentro de outra Category, e assim por diante. Uma associação #recursiva, da entidade #Category para ela mesma, representa esse relacionamento. É importante observar que uma única categoria pode ter várias subcategorias, mas, no máximo, um pai. Cada #Item pertence a pelo menos uma #Category.

- Essa representação não é o modelo de domínio completo; ela inclui apenas as classes para as quais precisamos de persistência. Vamos precisar armazenar e carregar #instâncias de Category, Item, User e outras. Simplificamos um pouco essa visão geral de alto nível; faremos modificações nessas classes quando necessário.


- As entidades em um modelo de domínio devem encapsular o **estado** e o **comportamento**. Por exemplo, a entidade #User (usuário) deve definir o nome e o endereço de um cliente, além da lógica necessária para calcular os custos de envio dos itens (para este cliente específico.)

- Podem existir outras classes no modelo de domínio que tenham apenas instâncias transitórias em tempo de execução. Consideramos uma claasse WinningBidStrategy (Estratégia de Lance Vencedor), **que encapsula o fato de que o maior lance vence um leilão**. Esta classe pode ser chamada pelo código da camada de negócios (controlador) ao verificar o estado de um leilão. Em algum momento, podemos precisar determinar como o imposto deve ser calculado para itens vendidos ou como o sistema deve aprovar uma nova conta de usuário. Não consideramos essas regras de negócios ou comportamentos do modelo de domínio como irrelevantes; ao contrário, esses assuntos são, em sua maioria, #ortogonais ao problema de persistência. 

Agora que temos um design de aplicativo (rudimentar) com um modelo de domínio, o próximo passo é implementá-lo em Java.

---
**ORM sem um modelo de domínio**
A persistência de objetos com ORM completo é mais adequada para aplicações baseadas em um modelo de domínio rico. Se sua aplicação não implementa regras de negócios complexas ou interações complexas entre entidades, ou se temos poucas entidades, talvez não precisemos de um modelo de domínio. Muitos problemas simples e até alguns problemas não tão simples são perfeitamente adequados para soluções baseadas em tabelas, onde a aplicação é projetada em torno do modelo de dados do banco de dados, em vez de um modelo de domínio orientado a objetos, e a lógica é frequentemente executada no banco de dados. Também vale a pena considerar a curva de aprendizado: uma vez que nos tornamos proficiente com Hibernate e Spring Data, usaremos para todas as aplicações - até mesmo algo tão simples como um gerador de consultas #SQL e mapeados de resultados. 
ORM completo é melhor para aplicações complexas, mas soluções orientadas a tabelas podem ser suficientes e mais eficientes para aplicações simples. Além disso, a curva de aprendizado do ORM, que pode não valer a pena para casos simples.

---

## Implementing the domain model
Vamos começar com uma questão que qualquer implementação deve lidar: a separação de responsabilidades - qual camada é responsável por qual tarefa. A implementação do modelo de domínio costuma ser um componentes central e organizador; ele é amplamente reutilizado sempre que implementamos uma nova funcionalidade na aplicação. Por esse motivo, precisamos nos esforçar para garantir que preocupações não relacionadas ao negócio não se infiltrem na implementação do modelo de domínio.

### 3.2.1 Addressing leakage of concerns
Quando preocupações como persistência, gerenciamento de transações ou autorização começam a aparecer nas classes do **modelo de domínio**, isso é um exemplo de <span style="background:#d4b106">vazamento de responsabilidades</span>. A implementação do modelo de domínio é um código importante que não deve depender de APIs ortogonais. Por exemplo, o código do modelo de domínio não deve chamar o banco de dados diretamente ou por meio de uma abstração intermediária. Isso permitirá que reutilizemos as classes do modelo de domínio praticamente em qualquer lugar. 
A arquitetura da aplicação inclui as seguintes camadas:
- **Camada de apresentação:** pode acessar #instâncias e #atributos das entidades do modelo de domínio ao renderizar as visualizações. O usuário pode interagir com a aplicação por meio da interface frontal (como um navegador). 
- **Componentes de controle na camada de negócios:** podem acessar o estado das #entidades do **modelo de domínio** e chamar os métodos dessas entidades. Aqui é onde os cálculos e a lógica de negócios são executadas. 
- **Camada de persistência:** pode carregar **instâncias de entidades** do modelo de domínio do banco de dados e armazená-las nele, preservando seu estado. Aqui é onde as informações são persistidas por um longo período. Essa preocupação também deve ser separada das preocupações das outras camadas. 

Prevenir o vazamento de responsabilidades torna mais fácil testar unitariamente o modelo de domínio, sem a necessidade de um ambiente de execução ou contêiner específico, ou de simular quaisquer dependências de serviço. Podemos escrever testes #unitários que verificam o comportamento correto das classes do modelo de domínio sem a necessidade de um ambiente de teste especializado. (Estamos falando de testes #unitários como calcular o custo de envio e o imposto, e não testes de #desempenho e #integração como "carregar do banco de dados" e "armazenar no banco de dados"). 

O padrão Jakarta EE resolve o problema do vazamento de responsabilidades com metadados, como anotações dentro do código ou descritores XML externos. Essa abordagem permite que o contêiner de execução implemente algumas preocupações transversais predefinidas - segurança, concorrência, persistência, transações e remotidade - de uma forma genérica, interceptando chamadas para os componentes da aplicação.

O #JPA define a classe de entidade como o principal #artefato de programação. Esse modelo de programação permite a persistência transparente, e um provedor JPA, como o Hibernate, também oferece persistência automatizada. O Hibernate não é um ambiente de execução Jakarte EE, nem um servidor de aplicações. Ele é uma implementação da técnica ORM.

 #artefato-de-programação são componentes criados durante o desenvolvimento do software que tem um papel específico na funcionalidade do sistema.

### 3.2.2 Transparent and automated persistence
Usamos o termo #transparent para nos referir a uma completa separação de responsabilidades entre as classes persistentes do modelo de domínio e a camada de persistência. As classes persistentes são desinformadas - e não têm dependência - do mecanismo de persistência. De dentro das classes persistentes, não há referência ao mecanismo de persistência externo. Usamos o termo **automático** para nos referir a uma solução de persistência (seu domínio anotado, a camada de mecanismo) que o isenta de lidar com detalhes mecânicos de baixo nível, como escrever a maioria das instruções #SQL e trabalhar com a API #JDBC.  Como um caso de uso real, vamos analisar como a **persistência transparente** e automatizada se reflete no nível da classe #Item.

A classe #Item do modelo de domínio CaveatEmptor não deve ter nenhuma dependência em tempo de execução de qualquer API do Jakarta Persistence ou do Hibernate. Além disso, o JPA não exige que classes persistentes #herdem ou #implementem quaisquer #superclasses ou interfaces especiais. 

Em um sistema com persistência transparente, as instâncias das entidades não estão cientes do armazenamento de dados subjacente; elas não precisam nem mesmo estar cientes de que estão sendo persistidas ou recuperadas. O JPA externaliza as preocupações de persistência para uma API genérica de gerenciamento de persistência. Portanto, a maior parte do código, e certamente sua lógica de negócios complexa, não precisa se preocupar com o estado atual de uma instância de entidade do modelo de domínio em uma única thread de execução. **Consideramos a transparência como um requisito, pois ela torna o aplicativo mais fácil de construir e manter**. A persistência transparente deve ser um dos principais objetivos de qualquer solução ORM.

Claramente, nenhuma solução de persistências automatizada é completamente transparente: cada camada de persistência automatizada, incluindo JPA e Hibernate, impõe alguns requisitos nas classes persistentes. Por exemplo, JPA exige que os atributos de coleção sejam tipizados para uma interface, como #java-util-Set ou #java-util-List ou seja, um #Set ou #List, e não para uma implementação concreta, como #java-util-HashSet (isto é uma boa prática de qualquer forma). Da mesma forma, uma classe de entidade JPA precisa ter um atributo especial, chamado de identificador de banco de dados (o que também é menos uma restrição, mas geralmente é conveniente). 

Sabemos que o mecanismo de persistência deve ter um efeito mínimo sobre como vamos implementar um modelo de domínio e que a <span style="background:#d4b106">persistência transparente</span> e <span style="background:#d4b106">automatizada</span> é necessária. Nosso modelo de programação preferido para alcançar isso é o #POJO (Plain Old Java Object).

**Note POJO** is the #acronym for Plain Old Java Objects. #Martin-Fowler, #Rebecca-Parsons, and #Josh-Mackenzie coined this term in 2000.

No início dos anos 2000, muitos desenvolvedores começaram a falar sobre **POJO**, uma abordagem "de volta ao básico" que essencialmente revive o **JavaBeans**, um modelo de componente para desenvolvimento de interfaces de usuário, e o reaplica para as outras camadas de um sistema. Várias revisões das especificações do EJB e do JPA trouxeram novas entidades mais leves, e seria apropriado chamá-las de **JavaBeans com capacidade de persistência**. Engenheiros Java frequentemente usam todos esses termos como sinônimos para a mesma abordagem de design básica.

Você não deve se preocupar muito com quais termos usamos neste livro; nosso objetivo final é aplicar o aspecto de persistência de forma tão transparente quanto possível às classes Java. Quase qualquer classe Java pode ter capacidade de persistência se você seguir algumas práticas simples. Vamos ver como isso se parece no código.

### 3.2.3 Wrinting persistence-capable classes
Um dos principais objetivos do Hibernate é oferecer suporte a modelos de domínio detalhados e ricos. Essa é uma das razões pelas quais trabalhamos com POJOs. De forma geral, usar objetos mais granulares **significa ter mais classes do que tabelas**.

Uma classe Java simples com **suporte à persistência** declara atributos, que representam o #estado, e métodos de negócio, que definem o comportamento. Alguns #atributos representam associações com outras classes compatíveis com persistência.

O exemplo a seguir apresenta uma implementação #POJO da entidade User do modelo de domínio.

[[Java Persistence with Spring Data e Hibernate/Capítulo 3 - Domain Models and Metadata/Ch03/domainmodel/src/main/java/com/manning/javapersistence/ch03/ex01/User.java| Entidade User POJO]] - Ela é independente de Frameworks e bibliotecas.

A classe pode ser #abstrata e, se necessário, estender uma classe não persistente ou implementar uma interface. Ela deve ser uma classe de nível superior, ou seja, não pode estar aninhada dentro de outra classe.

A classe compatível com persistência e qualquer um de seus métodos não devem ser marcados como #final (essa é uma exigência da especificação JPA). O Hibernate não é tão rígido e permitirá que declaremos final como entidades ou entidades com métodos final que acessam campos persistentes. No entanto, isso não é uma boa prática, pois impedem o Hibernate de usar o padrão de proxy para melhorar o desempenho.

O Hibernate e a JPA exigem um construtor sem argumentos para cada classe persistente. Alternativamente, se não escrevermos nenhum construtor, o Hibernate usará o construtor padrão do Java.

O Hibernate chama as classes utilizando a API de #Reflexão do Java nesses construtores sem argumentos para criar instâncias. Esse construtor não precisa ser público, mas deve ser, pelo menos, visível no pacote para que o Hibernate possar usar proxies gerados em tempo de execução para otimização de desempenho.

As propriedades do POJO implementam os #atributos das entidades de negócios, como o nome de usuário da classe User. Normalmente, implementaremos essas propriedades como campos membros privados ou protegidos, junto com métodos de acesso às propriedades públicos ou protegidos. Para cada campo, será necessário um método para recuperar o seu valor ( #getter) e outro para definir seu valor #setter.

O exemplo de POJO apresentado na listagem 3.1 [[Java Persistence with Spring Data e Hibernate/Capítulo 3 - Domain Models and Metadata/Ch03/domainmodel/src/main/java/com/manning/javapersistence/ch03/ex01/User.java|User]] declara métodos getter e setter para a propriedade username. 

A especificação do JavaBean define as diretrizes para nomear métodos de acesso, o que permite que ferramentas genéricas, como o Hibernate, descubram e manipulem facilmente os valores das propriedades. 

O nome de um método #getter começa com get, seguido pelo nome da propriedade (com a primeira letra em maiúsculo). Já um método setter, começa com set e, de forma semelhante, é seguido pelo nome da propriedade.

Para propriedades do tipo #boolean, podemos usar #is no lugar de #get como prefixo dos métodos #getter.

O Hibernate não exige métodos de acesso. Podemos escolher como o estado de uma instância das suas classes persistentes será armazenado. O Hibernate pode acessar diretamente os campos ou chamar os métodos de acesso.

Embora os métodos de acesso triviais sejam comuns, um dos motivos pelos quais gostamos de usar o estilo JavaBeans é que eles proporcionam encapsulamento: podemos alterar a implementação interna oculta de um atributo sem modificar a interface pública.

Se configurarmos o Hibernate para acessar atributos por meio de #métodos, isso abstrai a estrutura interna de dados da classe - as variáveis de instância - do design do banco de dados. 

Por exemplo, se o nosso banco de dados armazena o nome de um usuário em uma única coluna NAME, mas a nossa classe User possui os campos, variáveis de instância, firstname e lastname, podemos adicionar a seguinte propriedade persistente de nome à classe:
[[Java Persistence with Spring Data e Hibernate/Capítulo 3 - Domain Models and Metadata/Ch03/domainmodel/src/main/java/com/manning/javapersistence/ch03/ex02/User.java|User combinando First + Lastname]]

Portanto, um #POJO pode ser manipulado para adaptar atributos de banco de dados, como um campo NAME, para atributos separados como "firstname" e "lastname". Logo, no código acima, utilizamos exemplos de um método getter e setter para dividir ou juntar um nome.  

Ao persistir coleço~es, temos que ter cuidados com a criação de setters para coleções inteiras, como Set< Bid >, pois isso pode causar atualizações SQL desnecessárias no Hibernate. O Hibernate possui implementações próprias para coleções e, ao usar setters de coleções inteiras, pode levar a uma atualização redundante. 

### 3.2.4 Implementing POJO associations
Agora, vamos ver como podemos associar e criar diferentes tipos de relacionamentos entre objetos: um-para-muitos, muitos-para-um e relacionamentos bidirecionais. 
1. **Tipos de Relacionamentos**: 
	1. um-para-muitos ( #one-to-many): um objeto de uma classe pode estar relacionado a vários objetos de outra classe. Por exemplo, um item pode ter várias Bids (ofertas);
	2. muitos-para-um ( #many-to-one): vários objetos de uma classe podem estar relacionados a um único objeto de outra classe. Por exemplo, muitas ofertas podem estar associadas a um único Iteml;
	3. bidirecionais: a associação pode ser feito em ambas as direções. Um item pode acessar suas ofertas e uma oferta pode acessar o item ao qual pertence.

Utilizar essas associações facilita o gerenciamento de relacionamentos entre objetos, permitindo que o Hibernate cuide da persistência e do rastreamento das entidades relacionadas.

![[Capítulo 3 - Domain Models and Metadata-2.png]]

[[Java Persistence with Spring Data e Hibernate/Capítulo 3 - Domain Models and Metadata/Ch03/domainmodel/src/main/java/com/manning/javapersistence/ch03/ex03/Bid.java|Bid]] esse código é parte do que é necessário para mapear a relação entre as entidades Bid e Item. No contexto do JPA ou Hibernate, esse código pode ser complementado com anotações como #ManyToOne para definir a associação entre as duas entidades, indicando que várias ofertas podem estar associadas a um único item.


[[Java Persistence with Spring Data e Hibernate/Capítulo 3 - Domain Models and Metadata/Ch03/domainmodel/src/main/java/com/manning/javapersistence/ch03/ex03/Item.java|Item]] A inicialização do atributo Bids é feita com uma nova instância de HashSet< bid >, que é uma implementação de conjunto que não mantém a ordem dos elementos. 

Logo, a relação entre Item e Bid é do tipo muitos-para-um, onde um item pode ter várias ofertas. Essa associação é refletida na classe Item, onde um item tem um conjunto de ofertas (Set< Bid >), e cada Bid (oferta) está associado a um único item.

Essa associação entre as duas classes permite uma navegação bidirecional: o many-to-one é, sob essa perspectiva, uma multiplicidade one-to-many. Um item pode ter várias ofertas (bids) - elas são do mesmo tipo, mas foram geradas durante o leilão por diferentes usuários e com valores diferentes. 

Quando temos um relacionamento muitos-para-um, como no caso de Bid para Item, podemos acessar o Item de uma oferta, mas também podemos acessar todas as ofertas associadas a um item.

O JPA exige que as propriedades de coleção sejam definidas utilizando interfaces como #Set, #List ou #Collection, em vez de usar uma implementação como #HashSet ou #ArrayList.

A recomendação de programar com interfaces ao invés de implementações concretas não se limita ao JPA. Essa abordagem traz benefícios como:
- **Flexibilidade**: podemos mudar a implementação da coleção sem alterar a interface ou a lógica que a usa;
- **Desacoplamento:** facilita a manutenção do código, pois reduz a dependência de classes específicas.

Para criar um vínculo bidirecional entre duas entidades, como Item e Bid, são necessárias duas etapas:
- Adicionar a Bid à coleção de Bids do Item: isso envolve atualizar a coleção de ofertas associada ao item;
- Definir a propriedade item da Bid: isso envolve associar um item à oferta.

O autor sugere a criação de métodos convenientes para encapsular a lógica de manipulação das associações entre objetos, como no caso entre Item e Bid. Isso facilita o uso da associação, permitindo que o código seja reutilizado de forma mais eficiente e consistente.

Utilizar métodos convenientes não apenas melhora a legibilidade e a reutilização do código, mas também reduz o risco de erros ao manipular a lógica das associações. Isso é uma boa prática de programação, especialmente ao lidar com frameworks como JPA e Hibernate, onde a gestão de relacionamentos entre entidades pode ser complexa.

No desenvolvimento de aplicações com JPA e Hibernate, a gestão de relacionamentos entre entidades (como *Item* e *Bid* ) envolve várias considerações para garantir a integridade dos dados. 
1. **Relacionamento Bidirecional**: 
	- Para associar duas entidades, como *Item* e *Bid*, é necessário que ambas as extremidades da associação sejam devidamente gerenciadas. No caso do relacionamento #many-to-one , cada Bid deve ter um *Item* associado, e um *Item* pode ter muitos *Bids* associados. Essa relação bidirecional exige cuidados, pois as alterações em uma entidade precisam ser refletidas na outra.
2. **Scaffolding Code**
	- O #Scaffolding code refere-se ao código necessário para criar essas associações, incluindo os métodos getter e setter para acessar as propriedades. Além disso, é importante adicionar métodos convenientes, como addBid(), para garantir que as ações da manipulação de associações (adicionar um Bid a um item e associar um Item a uma Bid) sejam feitas de maneira consistente e sem erros.
3. **Coleções Imutáveis**
	- É recomendado retornar coleções imutáveis dos métodos *getter*, como Collections.unmodifiabletSet(), para evitar que clientes alterem diretamente a coleção de Bids de um Item. Isso força as modificações a passarem por métodos de gerenciamento de relacionamento, garantindo que a integridade dos dados seja mantida.
4. **Instâncias Imutáveis**
	- Outra estratégias para garantir a integridade é tornar as entidades imutáveis, exigindo que o relacionamento entre as entidades seja estabelecido no momento da criação do objeto, como no exemplo de exigir que um item seja passado no construtor de uma Bid.

Uma estratégia alternativa é usar instâncias imutáveis. Por exemplo, podemos garantir a integridade exigindo um argumento *Item* no construtor de *Bid*, como mostrando no exemplo de código abaixo:
[[Java Persistence with Spring Data e Hibernate/Capítulo 3 - Domain Models and Metadata/Ch03/domainmodel/src/main/java/com/manning/javapersistence/ch03/ex04/Bid.java|Bid]] 
Neste novo exemplo:
**A classe Bid** tem um atributo item do tipo Item, que representa a associação com a classe Item. 

O #construtor da classe Bid, recebe um objeto Item como parâmetro e o associa ao lance (Bid).

O importante é que, dentro do construtor de Bid, a referência para o item é passada e também a adição do lance this à coleção bids do Item. Isso mantém o relacionamento bidirecional. 

## 3.3 Domain Model Metadata
A metadata é informação sobre os dados, ou seja, a metadata do modelo de domínio é a informação que descreve seu modelo de domínio. Por exemplo, quando usamos a API de reflexão do Java Java Reflection API para descobrir os nomes das classes no seu modelo de domínio ou os nomes de seus atributos, estamos acessando metadata do modelo de domínio.

Logo, a metadata do modelo de domínio, refere-se à descrição das entidades (ou classes) e suas propriedades (ou atributos), relações entre elas e outras características relevantes que definem o modelo de dados de uma aplicação. 

As ferramentas ORM também requerem metadata para especificar o mapeamento entre classes e tabelas, propriedades e colunas, associações e chaves estrangeiras, tipos Java e tipos SQL, e assim por diante. Essa metadata de mapeamento objeto-relacional rege a transformação entre os diferentes sistemas de tipos e representações de relacionamentos nos sistemas orientados a objetos e SQL. 

O JPA possui uma API de metadata que podemos chamar para obter detalhes sobre os aspectos de persistência do modelo de domínio, como os nomes das entidades e atributos persistentes. É nossa responsabilidade, como engenheiro, criar e manter essas informações.

O JPA padroniza duas opções de metadata: anotações no código Java e arquivos de descritores XML externalizados. O Hibernate possui algumas extensões para funcionalidades nativas, também disponíveis como anotações ou descritores XML, normalmente, preferimos as anotações como a principal fonte de metadata de mapeamento. Um exemplo de anotação é o @Id que, no contexto do JPA, ela indica o atributo de uma classe é a chave primária da entidade mapeada, ou seja, é o identificador único para cada instância. Essa metadata, representada por anotações como @Id, ajuda o JPA e outras ferramentas ORM (como Hibernate) a entender como os objetos devem ser persistidos no banco de dados, incluindo a definição das chaves primárias e a relação entre a classe e a tabela. 

O JPA padroniza duas opções de metada: annotations no código Java e arquivos descritores XML externalizados. A maioria dos engenheiros hoje preferem anotações Java como o principal mecanismo para declarar metadata. 

### 3.3.1 Annotation-based metadata

```java
import javax.persistence.Entity;

@Entity
public class Item { ...
}
```
A grande vantagem das anotações é que elas colocam a metadata, como @Entity, próxima da informação que ela descreve, em vez de separá-la em um arquivo deiferente. No exemplo acima, a anotação @Entity é usada diretamente na classem Item, indicando que esta classe é uma entidade persistente, sem a necessidade de um arquivo externo para essa configuração. Isso torna o código mais compacto e fácil de entender. 

Podemos encontrar as anotações de mapeamento padrão do JPA no pacote javax.persistence. Quando definimos a classe Item com o uma entidade persistente, todos os seus atributos são tornados automaticamente persistentes com uma estratégia padrão. Logo, podemos carregar e armazenar instâncias (objeto concreto criado a partir da classe) de Item, e todas as propriedades da claasse fazem parte do estado gerenciado. 

As anotações são seguras em termos de tipo, e a metadata do JPA é incluída nos arquivos de classe compilados. As anotações ainda são acessíveis em tempo de execução. A IDE também pode facilmente validadr e destacar as anotações - afinal, elas são tipos regulares do Java.

Nossa classe depende do JPA para ser compilada, pois precisamos das bibliotecas do JPA no nosso classpath quando criamos uma instância da classe, como em um aplicativo cliente que não executa nenhum código do JPA. Apenas quando acessamos as anotações por meio de reflexão em tempo de execução (como o Hibernate faz) é que será necessário ter os pacotes no classpath.

**Using Vendor Extensions**
Embora possamos usar as anotações padrão do JPA, como #Entity, para mapear nossa classe, também podemos usar anotações específicas do fornecedor (como o Hibernate) para opções adicionais que não estão no padrão do JPA. Logo, prefixamos as anotações do Hibernate com org.Hibernate.annotations visa tornar claro qual parte do código usa funcionalidades padrão do JPA e qual parte usa recursos específicos do Hibernate. Isso ajuda na manutenção do código, pois fica mais fácil identificar e pesquisar por anotações que não fazem parte do JPA, permitindo uma visão geral e rápida sobre o uso de funcionalidades específicas do Hibernate na aplicação.
```java
import javax.persistence.Entity;
@Entity
@org.hibernate.annotations.Cache(
	usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE
)
public class Item {
}
```
Se mudarmos o fornecedor do Jarkarta Persistence, precisaremos apenas substituir as extensões específicas do fornecedor, e poderemos esperar que um conjunto de recursos semelhante esteja disponível na maioria das implementações maduras de JPA. 

Anotações em classes cobrem apenas metadados que se aplicam a essa classe específica. Muitas vezes, também precisaremos de metadados em um nível mais alto, para todo o pacote ou até mesmo para toda a aplicação. 

**Global Annotation Metadata**
A anotação @Entity mapeia uma classe específica. O JPA e o Hibernate também possuem anotações para metadados #globais. Por exemplo, uma @NamedQuery tem um escopo global; não aplicamos a uma classe específica. Então, onde colocamos essa anotação?

Mantemos os metadados globais em um arquivo separado. Anotações a nível de pacote são uma boa escolha; elas ficam em arquivo chamado package-info.java no diretório de um pacote específico. Podemos procurará-las em um único lugar, em vez de navegar por vários arquivos. 
Exemplo de uma declaração de consulta nomeada global:
[[package-info.java]] 

A menos que já tenhamos utilizado anotações a nível de pacote antes, a sintaxe deste arquivo, com as declarações de pacote e importações no final, é nova, realmente eu não vi ainda.

As #anotações de #metadados globais podem ser lidas em tempo de exxecução, desde que sejam configuradas corretamente. Isso depende do valor do elemento associada à anotação, logo o #Retention associado á anotação determina o momento em que a anotação estará disponĩvel (em tempo de #compilação,no #Bytecode ou em #run_time )
### 3.3.2 Applying constraints to Java objects
A maioria das aplicações contém uma série de verificações de integridade de dados. Quando violamos uma das restrições mais simples de integridade de dados, podemos receber uma #exception #NullPointerException, pois um valor não está disponível. Da mesma forma, pode ocorrer essa exceção. 

Essas regras de negócios afetam todas as camadas de uma aplicação: o código da interface do usuário precisa exibir mensagens de erros detalhadas e localizadas. As camadas de negócios e persistência devem verificar os valores de entrada recebidos do cliente antes de passá-los para o banco de dados. <span style="background:#d4b106">O banco de dados SQL deve ser o validador final, garantindo a integridade dos dados duráveis</span>. 

A ideia por trás da Bean Validadtion é que declarar regras como "<span style="background:#fdbfff">Essa propriedade não pode ser nula</span>" ou "<span style="background:#fdbfff">Esse número precisar estar dentro do intervalo dedeterminao</span>" é muito mais fácil e menos propenso a erros do que escrever repetidamente procedimentos if-then-else. Além disso, declarar essas regras no componente central da aplicação, a implementação do modelo de domínio, permite realizar verificações de integridade em todas as camadas do sistema. As regras ficam então disponíveis para as camadas de apresentação e persistência. E, se considerarmos como as restrições de integridade de dados afetam não apenas o código Java da aplicação, mas também o esquema do banco de dados SQL - que é uma coleção de regras de integridade - podemos pensar nas restrições da Bean Validation como metadados adicionais do ORM. 

Veja a seguinte classe de modelo de domínio Item estendida:
[[Java Persistence with Spring Data e Hibernate/Capítulo 3 - Domain Models and Metadata/Ch03/validation/src/main/java/com/manning/javapersistence/ch03/validation/Item.java|Item Com Bean Validation]]

Adicionamos dois #atributos quando um leilão é concluído: o nome de um Item e a data de término.

#NotNull e #Size- queremos garantir que o nome **esteja sempre presente** e seja **legível para humanos** (nomes de itens com apenas um caractere não fazem sentido), mas não muito longo. Nosso banco de dados SQL será mais eficiente com strings de comprimento variável de até 255 caracteres. Em segundo lugar, o horário de término de um leilão obviamente deve estar no futuro. Se não forneceremos uma mensagem de erro para uma restrição, será usada uma mensagem padrão. 

O mecanismo de validação acessará os campos diretamente se anotamos os campos. Se preferir usar chamadas através dos métodos acessores, anote o método getter com as restrições de validação, e não o setter. 

- Se anotarmos diretamente os campos (atributos), o mecanismo de validação (como o Hibernate Validator) acessará esses campos diretamente para aplicar as regras.
- Se preferir utilizar métodos acessores getters, devemos colocar as anotações nos getters. Isso significa que as validações serão realizadas sempre que o método getter for chamador, e as anotações também ficarão visíveis como parte da API pública da classe. 

A Bean Validation não se limta às anotações integradas; podemos criar nossas próprias restrições e anotações. 
Código que verifica manualmente a integridade de uma instância da classe Item:
[[ModelValidation.java]] o código apresentado é um exemplo de teste de validação usando o Bean Validation em uma classe Item. Ele demonstra como verificar se uma instância de Item segue as restrições definidas para seus atributos. 

O código simula uma situação em que os dados não atendem aos requisitos e verifica se as violações são identificadas corretamente.

Raramente escreveremos esse tipo de código de validação manual; geralmente, essa validação é tratada automaticamente pela nossa interface de usuário e pelo framework de persistência. Portanto, é importante procurar por integração com Bean Validation ao escolher um framework de UI.

O conjunto de ferramentas do Hibernate para geração automática de esquemas SQL entende muitas restrições e gera restrições equivalentes no #DDL SQL. Por exemplo:
- Uma anotação @NotNull é traduzida para uma restrição SQL NotNull;
- Uma regra @Size, define o número de caracteres em uma coluna do tipo #VARCHAR.

Portanto, quando usamos o Hibernate, ele detecta automaticamente anotações como @NotNull ou @Size na classe de domínio e valida os dados antes de realizar operações como salvar ou atualizar no banco de dados. Isso <span style="background:#d4b106">elimina a necessidade de escrever código de validação manual para cada operação</span>. 

Podemos controlar esse comportamento do Hibernate com o elemento validation-mode no arquivo de configuração persistence.xml. O modo padrão é auto, o que significa que o Hibernate só realizará a validação se encontrar um provedor de Bean Validation (como o Hibernate Validator) no classpath da aplicação em execução.

Com o modo #Callback, a validação sempre ocorrerá, e receberemos um erro de implantação caso esqueça de incluir um provedor de Bean Validation no pacote de aplicação. 

[[https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/ | Hibernate Validator]] - Validar dados é uma tarefa comum que ocorre em todas as camadas do aplicativo, da apresentação à camada de persistência. Frequentemente, a mesma lógica de validação  é implementada em cada camada, o que consome tempo e é propenso a erros. Para evitar a duplicação dessas validações, os desenvolvedores frequentemente agrupam a lógica de validação diretamente no modelo de domínio, desorganizando as classes de domínio com código de validação que é, na verdade, metadados sobre a própria classe. 
![[Capítulo 3 - Domain Models and Metadata-3.png | Validação No modelo de Domínio]]

### 3.3.3 Externalizing metadada with XML files
Podemos substituir ou sobrescrever todas as anotações do JPA com elementos em um descritor XML. Isso significa que não é obrigatório usar anotações, especialmente se for vantajoso para o design do sistema manter os metadados de mapeamento separados do código-fonte.
**Vantagens** - os **metadados ficam fora do código**, evitando que as classes Java fiquem sobrecarregadas; **reutilização de classes**, tornando as classes Java mais genéricas e reutilizáveis em diferentes contextos; **flexibilidade**, permite modificar o mapeamento sem alterar o código-fonte.

**Desvantagens** - menor segurança de tipo - com XML, erros de configuração só serão detectados em tempo de execução, enquanto as anotações oferecerem validação em tempo de compilação.
**Complexidade adicional** - a configuração em XML pode ser mais verboso e difícil de manter.

Embora esta abordagem esteja em desuso, ainda é relevante entender seu funcionamento, pois podemos encontrá-la em sistemas legados ou optar usá-la em projetos específicos.

Quando utilizamos arquivos XML para mapear a persistência em vez de anotações, o comportamento é similar em termos de execução. O JPA e os provedores de persistência como o Hibernate carregam e processam os metadados do XML em tempo de execução, da mesma forma que as anotações são processadas por reflexão. 