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
Também existem os chamados **aspectos tranversais**, que podem ser implementados de forma genérica, como por meio de código de framework. Exemplos típicos de aspectos transversais incluem **registros de logs**, autorização e demarcação de transações.

---
Uma arquitetura em camadas define interfaces entre o código que implementa as diferentes áreas de responsabilidade, permitindo que **mudanças na implementação de uma área sejam feitas sem causar grandes interrupções no código de outras camadas**. O empilhamento de camadas determina os tipos de dependências entre elas. As regras são as seguintes:
- **As camadas se comunicam de cima para baixo**. Uma camada depende apenas da interface da camada diretamente abaixo dela. 
- **Camada camada não tem conhecimento de outras camadas, exceto da camada imediatamente abaixo e, eventualmente, da camada acima, caso receba solicitações explícitas dessa camada**. 
Este modelo facilita a manutenção e a escalabilidade, permitindo que ajustes em uma camada não afetem diretamente as demais, desde que as interfaces sejam respeitadas.

Diferentes sistemas agrupam as áreas de **responsabilidade de maneira distintas**, definindo camadas diferentes. A arquitetura de aplicação típica, comprovada e de alto nível, usa três camadas:
1. **Apresentação
2. **Lógica de negócios**
3. **Persistência**
Essas camadas são separadas para facilitar o gerenciamento, a manutenção e a escalabilidade do sistema, permitindo que cada parte do sistema evolua de forma independente.

![[Capítulo 3 - Domain Models and Metadata.png]]
- **Presentation Layer** - A lógica de interface do usuário é a camada mais alta. O código responsável pela apresentação e controle da navegação de páginas e telas está na camada de **apresentação**. O código da interface do usuário pode acessar diretamente as entidades de negócios do modelo de domínio compartilhado e exibi-las na tela, juntamente com controles para executar ações. Em algumas arquiteturas, **as instâncias das entidades de negócios podem não ser acessíveis diretamente pelo código da interface de usuário**, como quando **a camada de apresentação não está rodando na mesma máquina que o restante do sistema**. 

- **Business Layer** - A camada de negócio é geralmente responsável pela implementação de todas as regras de negócio ou os requerimentos do sistema que são parte do domain problem. Essa camada costuma incluir algum tipo de componente controlador - o código que sabe quando invocar cada regra de negócio. Em alguns sistemas, essa camada possui sua própria representação interna das entidades do domínio de negócios. Alternativamente, ela pode depender de uma implementação do modelo de domínio compartilhada com as outras camadas da aplicação. Um bom exemplo da camada de negócios é o **código responsável por executar a lógica de negócios**. 

- **Persistence Layer** - A camada de persistência é um conjunto de classes e componentes responsáveis por armazenar dados e recuperá-los de um ou mais repositórios de dados. Essa camada precisa de um modelo das entidades de negócios para as quais desejamos manter um estado persistente. <span style="background:#d4b106">É a camada onde ocorre o uso do JPA, Hibernate e Spring Data</span>.

- **Database** - o banco de dados geralmente é externo. Ele é a representação persistente real do estado do sistema. Se um banco de dados SQL for usado, ele inclui um esquema e, possivelmente, procedimentos armazenados para a execução de lógica de negócios próxima aos dados. O banco de dados é o local onde os dados são persistidos a longe prazo. 

- **Classes Auxiliares e Utilitários** - toda a aplicação possui um conjunto de classes auxiliares ou utilitárias de infraestrutura que são usadas em cada camada da aplicação. Classes logs, segurança ou cache. 

### 3.1.2 Analyzing the business domain
Precisamos analisar os problemas de negócios que nosso sistema de software precisa resolver, identificando as entidades principais relevantes e suas interações. O objetivo principal da análise e do design de um modelo de domínio é capturar a essência das informações de negócios para o propósito da aplicação. 
As entidades geralmente são noções compreendidas pelos usuários do sistema: 
1. pagamento;
2. cliente;
3. pedido
4. item
5. lance
6. entre outras...

Algumas entidades podem ser abstrações de coisas menos concretas que o usuário pensa, como um algoritmo de precificação, mas até essas abstrações geralmente são compreensíveis para o usuário. Podemos encontrar todas essas entidades na visão conceitual do negócio, às vezes chamadas de modelo de informação.

A partir do modelo de negócios, engenheiros e arquitetos de softwares orientado a objetos criam um modelo orientado a objetos, ainda no nível conceitual (sem código Java). Este modelo pode ser tão simples quanto uma imagem mental que existe apenas na mente do desenvolvedor, ou pode ser tão elaborado quanto um diagrama de classes UML. Exemplo:
![[Pasted image 20241229153505.png]]

Este modelo contém entidades que provavelmente encontraremos em qualquer sistema de comércio eletrônico típico: categoria, item e usuário. Este modelo de domínio representa todas as entidades e seus relacionamentos (e, talvez, seus atributos). Este tipo de modelo orientado a objetos das entidades do domínio do problema, abrangendo apenas aquelas entidades que são de interesse para o usuário, é chamado de modelo de domínio. Ele é uma visão visão abstrata do mundo real.

O **modelo de domínio** é uma representação conceitual e simplificada das entidades-chave no sistema, focando apenas nas informações que são relevantes para os usuários e os processos de negócios. Ele não se preocupa com a implementação específica ou com o código, mas sim com a modelagem das entidades e como elas interagem entre si dentro do contexto do sistema. 

As entidades são os conceitos centrais do sistema, e elas interagem entre si de maneira que **simula o funcionamento do mundo real**. Um **cliente** pode fazer um **pedido**, o **pedido** contém **produtos** e o **pagamento** é feito para completar o pedido.

O modelo de domínio é uma **visão do que o sistema precisa fazer**, mas sem se preocupar com a forma como isso será feito tecnicamente, essa parte técnica fica para a **engenharia de requisitos**. 

Ao invés de usar modelo orientado a objetos, engenheiros e arquitetos podem começar o design da aplicação com um modelo de dados. Isso pode ser expresso com um diagrama entidade-relacionamento ( **entity-relationship diagram**), e ele conterá as entidades CATEGORIA, ITEM e USUÁRIO, juntamento com os relacionamentos entre elas. Geralmente, dizemos que, em relação à persistência, **há pouca diferença entre os dois tipos de modelos**; eles são apenas pontos de partidas diferentes. No final, qual linguagem de modelagem utilizarmos será secundária; o que mais nos interessa é a **estrutura das entidades de negócios** e os **relacionamentos entre elas**. 