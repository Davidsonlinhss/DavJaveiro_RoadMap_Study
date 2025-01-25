## Prefácio
Nascida como uma alternativa aos EJBs no início dos anos 200, a estrutura do Spring rapidamente superou seu oponente com a simplicidade de seu modelo de programação, a variedade de seus recursos e as integrações de bibliotecas de terceiros. O ecossistema do Spring cresceu ao longo dos anos e se tornou a estrutura de desenvolvimento mais ampla e madura disponível em qualquer linguagem de programação. Seu principal concorrente abandonou a corrida quando a Oracle interrompeu a evolução do Java EE 8, e a comunidade assumiu sua manutenção por meio do Jarkarte EE.
De acordo com as pesquisas recentes, o Spring é a estrutura subjacente em mais da metade dos aplicativos Java. Esse fato cria uma enorme base de código que torna fundamental que qualquer desenvolvedor Java aprenda o Spring, pois é inevitável que encontremos essa tecnologia em nossa carreira. 
Apesar de ser tão popular, é muito difícil encontrar material introdutório de qualidade. A documentação de referência tem milhares de páginas, descrevendo todas as sutilezas e detalhes que podem ser úteis em cenários muitos específicos, portanto, não é uma opção para um novato. 

---
### Este capítulo abordará:
- O que um framework de fato é;
- Quando usar e quando evitar utilizar frameworks;
- O que o Spring Framework é;
- Utilizando Spring em cenários do mundo real.
---
O Spring Framework (resumidamente, Spring) é um framework de aplicativos que faz parte do ecossistema Java. Uma *application framework* é um conjunto funcionalidade de software que fornece uma estrutura de fundação para o desenvolvimento de um aplicativo. Isto facilita o trabalho de criação de um aplicativo, eliminando o esforço de escrever o código do programa do zero.
Atualmente, usamos o Spring no desenvolvimento de muitos tipos de aplicativos, desde grande soluções de back-end até aplicativos de teste de automação. De acordo com muitos relatórios de pesquisa sobre tecnologias Java, o Spring é a estrutura Java mais usada atualmente. 

O Spring é popular, e os desenvolvedores começaram a usá-los com mais frequência também em outras linguagens JVM além do Java. Nos últimos anos, nós observamos um crescimento impressionante de desenvolvedores utilizando Spring com Kotlin (outra linguagem apreciada da família JVM). 

Antes de nos aprofundarmos em detalhes mais técnicos, vamos falar sobre a estrutura do Spring e onde podemos realmente usá-las, Por que o Spring é tão apreciado e quando devemos utilizá-lo. 

Neste capítulo, vamos nos concentrar no que é um framework, referindo-nos especialmente à estrutura do Spring. Na seção 1.1, discutiremos as vantagens de usar uma estrutura. Na seção 1.2, discutiremos o ecossistema do Spring com os componentes que você precisa aprender para começar a usar o Spring. Em seguida, na seção 1.3, apresentarei os possíveis usos da estrutura do Spring - em particular, cenários do mundo real. Na seção 1.4, discutiremos quando o uso de frameworks pode não ser a abordagem correta. Você precisa entender todos esses aspectos sobre a estrutura do Spring antes de tentar usá-la. Caso contrário, você pode tentar usar um martelo para cavar seu jardim.

---
## 1.1 Por que devemos utilizar frameworks?
Nesta seção, vamos falar sobre frameworks. O que eles são? Como esse conceito surgiu e por que? Para ser motivado a usar algo, precisamos saber como esse algo nos traz valor. E esse também é o caso do Spring. 

Um #aplication #framework é um conjunto de funcionalidades sobre as quais criamos aplicativos. A estrutura de aplicativos nos **fornece** um amplo <span style="background:#fff88f">conjunto de ferramentas</span> e funcionalidades que podemos usar para criar aplicativos. Não precisamos utilizar todos os recursos que a estrutura oferece. Dependendo dos requisitos do aplicativo, escolheremos as partes certas da estrutura a serem usadas. 

Um #framework fornece várias peças de um software que precisamos para construir nossos aplicativos. Precisamos saber os recursos que escolheremos e como montarmos para atingirmos o resultado desejado. 

A ideia por trás de um framework não é nova. Ao longo da história do desenvolvimento de software, os programadores observaram que podiam reutilizar partes do código que haviam escritos em vários aplicativos. Inicialmente, quando não havia tantos aplicativos implementados, cada aplicativo era único e desenvolvido do zero usando uma linguagem de programação específica. 
Quando o domínio do desenvolvimento de software se expandiu e mais e mais aplicativos começaram a ser publicados no mercado, ficou mais fácil observar que muitos desses aplicativos tinham requisitos semelhantes. Vamos citar alguns deles:
- Logging error, warning, and info messages happen in every app.
- A maioria dos aplicativos usa transações para processar alterações de dados. As transações representam um mecanismo importante que cuida da consistência dos dados. Discutiremos esse assunto em detalhes no capítulo 13.
- A maioria dos aplicativos usa mecanismos de proteção contra as mesmas vulnerabilidades comuns.
- A maioria dos aplicativos usa maneiras semelhantes de se comunicar entre si.
- A maioria dos aplicativos usa mecanismos semelhantes para melhorar seu desempenho, como cache ou compactação de dados.

Quando digo "código de lógica comercial", refiro-me ao código que implementa os requisitos comerciais do aplicativo. Esse código é o que implementa as expectativas do usuário em um aplicativo. Por exemplo, "<span style="background:#fff88f">clicar em um link específico gerará uma fatura</span>" é algo que os usuários esperam que aconteça. Alguns códigos do aplicativo que você desenvolve implementam essa funcionalidade, e essa parte do código é o que os desenvolvedores chamam de código de lógica de negócios. Entretanto, qualquer aplicativo cuida de vários outros aspectos: segurança, registro, consistência de dados e assim por diante (Figura 1.2).
![[Capítulo 1 - Spring no mundo real.png]]
Figura 1.2 A perspectiva do usuário é como um iceberg. Os usuários observam principalmente os resultados do código de lógica comercial, mas isso é apenas uma pequena parte do que constrói a funcionalidade completa do aplicativo. Assim como um iceberg tem a maior parte debaixo d'água, em um aplicativo corporativo não vemos a maior parte do código porque ele é fornecido por dependências.

Além disso, o código da lógica comercial é o que torna um aplicativo diferente de outro do ponto de vista da funcionalidade. Se você pegar dois aplicativos diferentes, por exemplo, um sistema de compartilhamento de viagens e um aplicativo de rede social, eles terão casos de uso diferentes.

Você <span style="background:#fff88f">realiza ações diferentes</span>, mas ambas precisam de <span style="background:#d3f8b6">armazenamento de dados</span>, <span style="background:#d3f8b6">transferência de dados</span>, <span style="background:#d3f8b6">registro em log</span>, <span style="background:#d3f8b6">configurações de segurança</span>, provavelmente cache e assim por diante. Vários aplicativos podem reutilizar essas implementações não comerciais. Então, é eficiente reescrever as mesmas funcionalidades todas as vezes? É claro que não:
- Você economiza muito tempo e dinheiro ao reutilizar algo em vez de desenvolvê-lo você mesmo.
- Uma implementação existente que muitos aplicativos já usam tem menos chances de introduzir bugs, pois outros já a testaram.
- Você se beneficia dos conselhos de uma comunidade porque agora tem muitos desenvolvedores que entendem a mesma funcionalidade. Se você tivesse implementado seu próprio código, apenas algumas pessoas o conheceriam.
---
## Uma história de transação
Um dos primeiros aplicativos em que trabalhei foi um sistema enorme desenvolvido em Java. Esse sistema era composto de vários aplicativos projetados em torno de um servidor de arquitetura antiga, todos eles escritos do zero usando Java SE. O desenvolvimento desse aplicativo começou com a linguagem há cerca de 25 anos. Esse foi o principal motivo de sua forma. E quase ninguém poderia imaginar o tamanho que ele se tornaria. Naquela época, não existiam conceitos mais avançados de arquiteturas de sistemas, e as coisas em geral funcionavam de forma diferente dos sistemas individuais devido à lentidão da conexão com a Internet.

Mas o tempo passou e, anos depois, o aplicativo parecia mais uma grande bola de lama. Por motivos válidos que não abordarei aqui, a equipe decidiu que precisava adotar uma arquitetura moderna. Essa mudança implicava primeiro a limpeza do código, e uma das principais etapas era usar uma estrutura. Decidimos usar o Spring. Naquela época, tínhamos como alternativa o Java EE (agora chamado Jakarta EE), mas a maioria dos membros da equipe considerou melhor optar pelo Spring, que oferecia uma alternativa mais leve, mais fácil de implementar e que também considerávamos mais fácil de manter.

A transição não foi fácil. Juntamente com alguns colegas, especialistas em seu domínio e conhecedores do próprio aplicativo, investimos muito esforço nessa transformação.

O resultado foi incrível! Removemos mais de 40% das linhas de código. Essa transição foi o primeiro momento em que compreendi quão significativo poderia ser o impacto do uso de uma estrutura.

---
## 1.2 O Ecossistema Spring
Nesta seção, discutiremos o Spring e os projetos relacionados, como o Spring Boot ou o Spring Data. Em cenários do mundo real, é comum usar diferentes estruturas juntas, sendo que cada estrutura foi projetada para ajudá-lo a implementar uma parte específica do aplicativo mais rapidamente.

Nós nos referimos ao Spring como uma estrutura, mas ele é muito mais complexo. O Spring é um ecossistema de estruturas. Normalmente, quando os desenvolvedores se referem à estrutura do Spring, eles se referem <span style="background:#fff88f">a uma parte dos recursos de software</span> que incluem o seguinte:
1. <span style="background:#fdbfff">Spring Core</span>: uma das partes fundamentais do Spring que inclui recursos básicos. Um desses recursos é o <span style="background:#fff88f">contexto do Spring</span>. O contexto do Spring é um recurso fundamental da estrutura do Spring que <span style="background:#d3f8b6">permite que o Spring gerencie as instâncias do nosso aplicativo</span>. Além disso, como parte do Spring Core, encontramos as funcionalidades de aspectos do Spring. Os aspectos ajudam o Spring a interceptar e manipular os métodos que definimos em nosso aplicativo. A Spring Expression Language (SpEL) é outro recurso que encontraremos como parte do Spring Core, que permite descrever as configurações do Spring usando uma linguagem específica. Mas logo você entenderá que o Spring Core contém os mecanismos que o Spring usa para se integrar ao seu aplicativo.
2. <span style="background:#fdbfff">Spring model-view-controller (MVC)</span> - a parte da estrutura do Spring que permite desenvolver aplicativos da Web que atendem a solicitações HTTP. Usaremos o Spring MVC a partir do <span style="background:#d3f8b6">capítulo 7</span>.
3. <span style="background:#fdbfff">Spring Data Access</span> - também é uma das partes fundamentais do Spring. Ele fornece ferramentas básicas que podemos usar para conectar a bancos de dados SQL para implementar a camada de persistência do nosso aplicativo. Usaremos o Spring Data Acess a partir do capítulo 13. 
4. <span style="background:#fdbfff">Teste do Spring</span> - A parte que contém as ferramentas necessárias para escrever testes para o seu aplicativo Spring. Discutiremos esse assunto no capítulo 15.

Inicialmente, você pode imaginar a estrutura do Spring como um **sistema solar**, em que o <span style="background:#fff88f">Spring Core representa a estrela no meio</span>, que mantém toda a estrutura unida (Figura 1.3).
![[Capítulo 1 - Spring no mundo real-1.png]]
Figura 1.3 - Podemos imaginar o Spring framework como um sistema solar com o Spring Core ao centro. As capacidades do software são os planetas ao redor do Spring Core que se mantém próximos através do campo gravitacional.

### 1.2.1 Descobrindo o Spring Core: a fundação do Spring
O Spring Core é a parte da estrutura do Spring que <span style="background:#fff88f">fornece os mecanismos básicos para integração</span> em aplicativos. O Spring funciona com base no princípio de <span style="background:#d3f8b6">inversão de controle</span> #IOC. Ao usar esse princípio, em vez de permitir que o aplicativo controle a execução, damos o controle a outra parte do software - no nosso caso, a estrutura do Spring. 

Por meio de configurações, instruímos a estrutura sobre como gerenciar o código que escrevemos, que define a lógica do aplicativo. É daí que vem a "inversão" em IoC: não deixamos o aplicativo controlar a execução por seu próprio código e usamos dependências. Em vez disso, permitimos que a estrutura (a dependência) controle o aplicativo e seu código. 

---
**Observação**: Neste contexto, o termo "controles" refere-se a ações como "criar uma instância" ou "chamar um método". Uma estrutura pode criar objetos das classes que definimos em nosso aplicativo. Com base nas configurações que escrevemos, o Spring intercepta o método para aumentá-lo com vários recursos. Por exemplo, o Spring pode interceptar um método específico para registrar qualquer erro que possa aparecer durante a execução do método. 

---

**Without IoC** 
![[Capítulo 1 - Spring no mundo real-2.png]]
**With IoC**
![[Capítulo 1 - Spring no mundo real-3.png]]
Começaremos a aprender o Spring com o Spring Core discutindo a funcionalidade IoC do Spring nos capítulos 2 a 5. O contêiner IoC une os componentes do Spring e os componentes do nosso aplicativo à estrutura. Usando o contêiner IoC, ao qual geralmente nos referimos o contexto do Spring, o que permite que a estrutura os use da maneira que nós configuramos.

No capítulo 6, continuaremos nossa discussão sobre a programação orientada a aspectos (AOP) do Spring. O Spring pode controlar as instâncias adicionadas ao seu contêiner IoC, e uma das coisas que ele pode fazer é interceptar métodos que representam o comportamento dessas instâncias. Esse recurso é chamado de aspectar o método. A AOP do Spring é uma das formas mais comuns de interação da estrutura com o que seu aplicativo faz. Essa característica faz com que o Spring AOP também faça parte dos elementos essenciais. Como parte do Spring Core, também encontramos gerenciamento de recursos, internacionalização (i18n), conversão de tipos e SpEL. Encontraremos aspectos desses recursos em exemplos ao longo do livro.

### 1.2.2 Uso do recurso Spring Data Access para implementar a persistência do aplicativo
Para a maioria dos aplicativos, é essencial persistir parte dos dados que eles processam. Trabalhar com bancos de dados é um assunto fundamental e, no Spring, é o módulo **Data Access** que você usará para cuidar da persistência de dados em muitos casos. O Spring Data Access inclui o uso de **JDBC**, a integração com estruturas de mapeamento objeto-relacional (ORM) como o Hibernate (não se preocupe se você ainda não sabe o que é uma estrutura ORM ou se nunca ouviu falar do Hibernate; discutiremos esses aspectos mais adiante no livro) e o gerenciamento de transações. Nos capítulos 12 a 14, abordaremos tudo o que é necessário para que você comece a usar o Spring Data Access.

### 1.2.3 Os recursos do Spring MVC para o desenvolvimento de aplicativos da Web
Os aplicativos mais comuns desenvolvidos com o Spring são aplicativos da Web e, no ecossistema do Spring, você encontrará um grande conjunto de ferramentas que permite escrever aplicativos e serviços da Web de diferentes maneiras. Você pode usar o Spring MVC para desenvolver aplicativos usando uma forma de servlet padrão, que é comum em um grande número de aplicativos atualmente. No capítulo 7, entraremos em mais detalhes sobre o uso do Spring MVC.

### 1.2.4 O recurso de teste do Spring
O módulo de teste do Spring nos oferece um grande conjunto de ferramentas que usaremos para escrever testes unitários e de integração. Muitas páginas foram escritas sobre o tópico de testes, mas discutiremos tudo o que é essencial para que você comece a usar os testes do Spring no capítulo 15. Também farei referência a alguns recursos valiosos que você precisa ler para obter todos os detalhes desse tópico. Minha regra geral é que você não é um desenvolvedor maduro se não entende de testes, portanto, você deve se preocupar com esse tópico.

### 1.2.5 Projetos do ecossistema Spring
O ecossistema do Spring é muito mais do que apenas os recursos discutidos anteriormente nesta seção. Ele inclui uma grande coleção de outras estruturas que se integram bem e formam <span style="background:#fff88f">um universo maior</span>. Aqui temos projetos como <span style="background:#d3f8b6">Spring Data</span>, <span style="background:#d3f8b6">Spring Security</span>, <span style="background:#d3f8b6">Spring Cloud</span>, <span style="background:#d3f8b6">Spring Batch</span>, <span style="background:#d3f8b6">Spring Boot</span> e assim por diante. Ao desenvolver um aplicativo, você pode usar mais desses projetos juntos. Por exemplo, você pode criar um aplicativo usando todo o Spring Boot, o Spring Security e o Spring Data. Nos próximos capítulos, trabalharemos em projetos menores que fazem uso de vários projetos do ecossistema do Spring. Quando digo projeto, estou me referindo a uma parte do ecossistema do Spring que é desenvolvida de forma independente. Cada um desses projetos tem uma equipe separada que trabalha para ampliar seus recursos. Além disso, cada projeto é descrito separadamente e tem sua própria referência no site oficial do Spring: https://spring.io/projects.

Desse vasto universo criado pelo Spring, também nos referiremos ao Spring Data e ao Spring Boot. Esses projetos são frequentemente encontrados em aplicativos, por isso é importante conhecê-los desde o início.

**AMPLIAÇÃO DOS RECURSOS DE PERSISTÊNCIA COM OS DADOS DO SPRING**
O projeto Spring Data implementa uma parte do ecossistema Spring que permite que você se conecte facilmente a bancos de dados e use a camada de persistência com um número mínimo de linhas de código escritas. O projeto faz referência às tecnologias #SQL e #NoSQL e cria uma camada de alto nível que <span style="background:#d3f8b6">simplifica</span> a maneira <span style="background:#d3f8b6">como você trabalha com a persistência de dados</span>.

**OBSERVAÇÃO** Temos o Spring Data Access, que é um módulo do Spring Core, e também temos um projeto independente no ecossistema do Spring chamado Spring Data. O Spring Data Access contém implementações fundamentais de acesso a dados, como o mecanismo de transação e as ferramentas JDBC. O **Spring Data** aprimora o acesso a bancos de dados e oferece um conjunto mais amplo de ferramentas, o que torna o desenvolvimento mais acessível e permite que seu aplicativo se conecte a diferentes tipos de fontes de dados. Discutiremos esse assunto no capítulo 14.

**Spring Boot**
O Spring Boot é parte de um projeto do ecossistema Spring que introduz o conceito de "convenção em vez de configuração". A ideia principal desse conceito é que, em vez de você mesmo definir todas as configurações de uma estrutura, o Spring Boot oferece uma configuração padrão que pode ser personalizada conforme o necessário. O resultado, em geral, é que escrevemos menos código porque segue convenções conhecidas e nosso aplicativo difere dos outros em poucos ou pequenos aspectos. Portanto, em vez de escrever todas as configurações para cada aplicativo, é mais eficiente começar com uma configuração padrão e alterar apenas o que for diferente da convenção. Discutiremos mais sobre o Spring Boot a partir do capítulo 7.
O ecossistema do Spring é vasto e contém muitos projetos. Você encontrará alguns deles com mais frequência do que outros, e alguns talvez nem sejam usados se você estiver criando um aplicativo sem uma necessidade específica. Neste livro, nos referimos apenas aos projetos que são essenciais para você começar: <span style="background:#d3f8b6">Spring Core</span>, <span style="background:#d3f8b6">Spring Data</span> e <span style="background:#d3f8b6">Spring Boo</span>t. Você pode encontrar uma lista completa dos projetos que fazem parte do ecossistema do Spring no site oficial do Spring: https://spring.io/projects/.

## 1.3 Spring em cenários do mundo real
Agora que você tem uma visão geral do Spring, sabe quando e por que deve usar uma estrutura. Nesta seção, apresentarei alguns cenários de aplicativos nos quais o uso da estrutura do Spring pode ser uma excelente opção. Com muita frequência, tenho visto os desenvolvedores se referirem apenas a aplicativos de back-end para usar uma estrutura como o Spring. Vi até mesmo uma tendência de restringir, ainda mais, o cenário aos aplicativos Web de back-end. Embora seja verdade que, em muitos casos, vemos o Spring ser usado dessa forma, é importante lembrar que a estrutura não se limita a esse cenário. Já vi equipes usando o Spring com sucesso em diferentes tipos de aplicativos, como o desenvolvimento de um aplicativo de teste de automação ou até mesmo em cenários de desktop autônomos.
Além disso, descreverei para você alguns cenários comuns do mundo real nos quais vi o Spring ser usado com sucesso. Esses não são os únicos cenários possíveis, e o Spring pode não funcionar o tempo todo nesses casos. Lembre-se do que discutimos na seção 1.2: uma estrutura nem sempre é uma boa escolha. Mas esses são casos comuns em que, geralmente, o Spring é uma boa opção:
1. O desenvolvimento de um aplicativo de back-end; 
2. O desenvolvimento de uma estrutura de teste de automação; 
3. O desenvolvimento de um aplicativo de desktop; 
4. O desenvolvimento de um aplicativo móvel.

### 1.3.1 Usando o Spring no desenvolvimento de um app backend
Um aplicativo backend é a parte de um sistema que é executada no lado do servidor e tem a responsabilidade de gerenciar dados e atender às solicitações dos aplicativos clientes. Os usuários acessam as funcionalidades usando diretamente os aplicativos clientes. Além disso, os aplicativos clientes fazem solicitações ao aplicativo backend para trabalhar com os dados dos usuários.
Você pode imaginar, em um cenário do mundo real, que o aplicativo seria o aplicativo de back-end que gerencia as transações em suas contas bancárias. Os usuários podem acessar suas contas e gerenciá-las por meio de um aplicativo da Web (banco on-line) ou de um aplicativo móvel. Tanto os <span style="background:#fff88f">aplicativos móveis</span> quanto os <span style="background:#fff88f">aplicativos da Web</span> representam <span style="background:#d3f8b6">clientes</span> para o <span style="background:#d3f8b6">aplicativo de backend</span>. Para gerenciar as transações dos usuários, o aplicativo de backend precisa se comunicar com outras soluções de backend, e parte dos dados que ele gerencia precisa ser mantida em um banco de dados. Na Figura 1.5, você pode visualizar a arquitetura desse sistema.
![[Capítulo 1 - Spring no mundo real figure 1.5.png]]
Figura 1.5.  Um aplicativo backend interage de várias maneiras com outros aplicativos e usa bancos de dados para gerenciar dados. Normalmente, um aplicativo de backend é complexo e pode exigir o uso de várias tecnologias. As estruturas simplificam a implementação fornecendo ferramentas que você pode usar para implementar a solução de backend mais rapidamente.

---
**OBSERVAÇÃO** - Não se preocupe se você não entender todos os detalhes da figura 1.5. Não espero que você saiba o que é um corretor de mensagens e nem mesmo como estabelecer a troca de dados entre os componentes. O que eu quero é que você veja que esse sistema pode se tornar complexo no mundo real e, então, entenda que os projetos do ecossistema Spring foram criados para ajudá-lo a eliminar essa complexidade o máximo possível.

---
O Spring oferece um excelente conjunto de ferramentas para a implementação de aplicativos de back-end. Ele facilita a sua vida com as diferentes funcionalidades que você geralmente implementa em uma solução de back-end, desde a integração com outros aplicativos até a persistência em várias tecnologias de banco de dados. Não é de se admirar que os desenvolvedores geralmente usem o Spring para esses aplicativos.

### 1.3.2 Usando o Spring em testes de automação de app
Atualmente, usamos com frequência os testes de automação para testes de ponta a ponta dos sistemas que implementamos. O teste de automação refere-se à implementação de software que as equipes de desenvolvimento usam para garantir que um aplicativo se comporte conforme o esperado. Uma equipe de desenvolvimento pode programar a implementação de testes de automação para testar frequentemente o aplicativo e notificar os desenvolvedores se algo estiver errado. Ter essa funcionalidade dá confiança aos desenvolvedores, pois eles sabem que serão notificados se quebrarem algo nos recursos existentes do aplicativo durante o desenvolvimento de novos recursos. 
Embora em sistemas pequenos você possa fazer o teste manualmente, é sempre uma boa ideia automatizar os casos de teste. Para sistemas mais complexos, testar manualmente todos os fluxos não é nem mesmo uma opção. Como os fluxos são muito numerosos, seria necessário um grande número de horas e muita energia para cobri-los completamente. 
Acontece que a solução mais eficiente é fazer com que uma equipe separada implemente um aplicativo que tenha a responsabilidade de validar todos os fluxos do sistema testado. Enquanto os desenvolvedores adicionam novas funcionalidades ao sistema, esse aplicativo de teste também é aprimorado para cobrir as novidades, e as equipes o utilizam para validar se tudo ainda funciona como desejado. Os desenvolvedores acabam usando uma ferramenta de integração e programam a execução regular do aplicativo para obter feedback o mais rápido possível sobre suas alterações (Figura 1.7).

Esse aplicativo pode se tornar tão complexo quanto um aplicativo de backend. Para validar os fluxos, o aplicativo precisa se comunicar com os componentes do sistema e até mesmo se conectar a bancos de dados. Às vezes, o aplicativo simula dependências externas para simular diferentes cenários de execução. Para escrever os cenários de teste, os desenvolvedores usam estruturas como Selenium, Cucumber, Gauge e outras. Mas, junto com essas estruturas, o aplicativo ainda pode se beneficiar de várias maneiras das ferramentas do Spring. Por exemplo, o aplicativo poderia gerenciar as instâncias de objeto para tornar o código mais sustentável usando o contêiner IoC do Spring. Ele pode usar o Spring Data para se conectar aos bancos de dados em que precisa validar os dados. Ele poderia enviar mensagens para filas ou tópicos de um sistema de corretor para simular cenários específicos ou simplesmente usar o Spring para chamar alguns pontos de extremidade REST (Figura 1.8). (Lembre-se de que não há problema se isso parecer muito avançado; o significado será esclarecido à medida que você avançar no livro).

### 1.3.3 Uso do Spring para o desenvolvimento de um aplicativo de desktop
Atualmente, os aplicativos de desktop não são desenvolvidos com tanta frequência, pois os aplicativos da Web ou móveis assumiram a função de interagir com o usuário. No entanto, ainda há um pequeno número de aplicativos de desktop, e os componentes do ecossistema do Spring podem ser uma boa opção para o desenvolvimento de seus recursos. Um aplicativo de desktop pode usar com sucesso o contêiner IoC do Spring para gerenciar as instâncias de objetos. Dessa forma, a implementação do aplicativo fica mais limpa e melhora sua capacidade de manutenção. Além disso, o aplicativo poderia usar as ferramentas do Spring para implementar diferentes recursos, por exemplo, para se comunicar com um backend ou outros componentes (chamando serviços da Web ou usando outras técnicas para chamadas remotas) ou implementar uma solução de cache.

### 1.3.4 Usando o Spring em aplicativos mobile
Com seu projeto Spring for Android (https://spring.io/projects/spring-android), a comunidade Spring tenta ajudar no desenvolvimento de aplicativos móveis. Embora você provavelmente raramente se depare com essa situação, vale a pena mencionar que é possível usar as ferramentas do Spring para desenvolver aplicativos para Android. Esse projeto do Spring fornece um cliente REST para Android e suporte de autenticação para acesso a APIs seguras.

## 1.4 Quando não usar frameworks
Nesta seção, discutiremos por que, às vezes, você deve evitar o uso de estruturas. É essencial que você saiba quando usar uma estrutura e quando evitá-la. Às vezes, o uso de uma ferramenta que é excessiva para o trabalho pode consumir mais energia e obter um resultado pior. Imagine usar uma serra elétrica para cortar pão. Embora você possa tentar e até conseguir um resultado final, seria mais difícil e consumiria mais energia do que usar uma faca comum (e você pode acabar com nada além de migalhas de pão em vez de pão fatiado). Discutiremos alguns cenários em que o uso de uma estrutura não é uma boa ideia e, em seguida, contarei uma história sobre uma equipe da qual fiz parte e que fracassou na implementação de um aplicativo devido ao uso de uma estrutura.
Acontece que, como tudo o mais no desenvolvimento de software, você não deve aplicar um framework em todos os casos. Você encontrará situações em que o framework não é adequada - ou talvez um framework seja adequada, mas não a estrutura do Spring. Em qual dos cenários a seguir você deve considerar não usar uma estrutura?
1. Você precisa implementar uma funcionalidade específica com o menor tamanho possível. Por "footprint", quero dizer a memória de armazenamento ocupada pelos arquivos do aplicativo. 
2. Requisitos de segurança específicos o obrigam a implementar somente código personalizado em seu aplicativo sem usar nenhuma estrutura de código aberto. 
3. Você teria que fazer tantas personalizações na estrutura que escreveria mais código do que se simplesmente não a usasse. 
4. Você já tem um aplicativo funcional e, ao alterá-lo para usar uma estrutura, não obterá nenhum benefício.