*This chapter covers*
- What a framework is
- When to use and when to avoid using frameworks
- What the Spring framerwokr is
- Using Spring in real-world scenarios

O framework Spring (ou simplesmente Spring) é um framework de aplicação que faz parte do ecossistema Java. Um framework de aplicação é um conjunto de funcionalidades de software comuns que fornece uma estrutura base para o desenvolvimento de uma aplicação. Um framework de aplicação facilita o esforço de escrever uma aplicação ao *eliminar a necessidade de escrever todo o código do programa do zero*.

Atualmente, utilizamos o Spring no desenvolvimento de diversos tipos de aplicações, desde grandes soluções de backend até aplicativos de automação de testes. De acordo com vários relatórios de pesquisa sobre tecnologias Java (como este da JRebel de 2020:  [http://mng.bz/N4V7](http://mng.bz/N4V7); ou este da JAXEnter: [http://mng.bz/DK9a](http://mng.bz/DK9a)), o Spring é o framework Java mais utilizado atualmente.)

O Spring é popular, e os desenvolvedores começaram a utilizá-lo com mais frequência também com outras linguagens da JVM além do Java. Nos últimos anos, observamos um crescimento impressionante de desenvolvedores utilizando o Spring com Kotlin (outra linguagem apreciada da família JVM). Neste livro, focaremos nos fundamentos do Spring, e eu ensinarei habilidades essenciais para usar o Spring  em exemplos do mundo real. Para facilitar sua compreensão e permitir que nos concentremos no Spring, usaremos apenas exemplos em Java. Ao longo do livro, discutiremos e aplicaremos, com exemplos, habilidades essenciais como **conectar-se a um banco de dados**, estabelecer comunicação entre aplicações e garantir a segurança e realizar testes em uma aplicação.

Antes de mergulhar em detalhes mais técnicos nos próximos capítulos, vamos conversar sobre o framework Spring e onde realmente o utilizaremos. Por que o Spring é tão apreciado, e quando devemos considerar utilizá-lo?

Neste capítulo, focaremos no que é um _framework_ , referindo-nos especificamente ao _Spring framework_ . Na seção 1.1, discutiremos as vantagens de usar um _framework_ . Na seção 1.2, exploraremos o ecossistema do Spring com os componentes que você precisa aprender para começar a trabalhar com o Spring. Em seguida, vou apresentar possíveis usos do _Spring framework_ — em particular, cenários do mundo real na seção 1.3. Na seção 1.4, discutiremos quando usar _frameworks_ pode não ser a abordagem mais adequada. É importante entender todos esses aspectos sobre o _Spring framework_ antes de tentar utilizá-lo. **Caso contrário, você pode acabar tentando usar um martelo para cavar seu jardim**.

Dependendo do nosso nível, podemos achar este capítulo desafiador. Posso introduzir alguns conceitos com os quais ainda não estamos familiarizado, e isso pode causar certa confusão. Mas não precisamos nos preocupar; mesmo que eu não entenda algumas coisas agora, elas serão esclarecidas mais adiante no livro. Às vezes, ao longo do livro, farei referência a algo mencionado em capítulos anteriores. Uso essa abordagem porque aprender um _framework_ como o Spring nem sempre nos oferece um caminho de aprendizado linear, e às vezes é necessário esperar até que você tenha mais peças do quebra-cabeça antes de ver a imagem completa. No final, porém, você terá uma visão clara e adquirirá as habilidades valiosas necessárias para desenvolver aplicativos como um profissional.

## 1.1 Why should we use frameworks?
Nesta seção, discutiremos os _frameworks_ . O que são eles? Como e por que esse conceito surgiu? Para se sentir motivado a usar algo, você precisa saber como isso traz valor para você. E o mesmo é verdade para o Spring. Vou ensinar esses detalhes essenciais compartilhando o conhecimento que reuni com base na minha própria experiência e no estudo e uso de vários _frameworks_ em cenários do mundo real, incluindo o Spring.

Um *framework* de aplicação é um conjunto de funcionalidades sobre as quais construímos aplicações. O *framework* de aplicação nos fornece uma ampla variedade de ferramentas e funcionalidades que podem ser usadas para construir aplicativos. **Não precisamos usar todas as funcionalidades que podem ser usadas para construir aplicativos**. Não precisamos usar todas as funcionalidades que o *framework* oferece. Dependendo dos requisitos do aplicativo que estamos desenvolvendo, escolheremos as partes mais adequadas do *framework* para utilizar.

![[Chapter 1 - Spring in the real world.png]]Figura 1.1 David encomendou um guarda-roupa na loja UAssemble. No entanto, a loja (framework) não entrega para David (o programador) apenas os componentes (software capabilities) de que ele precisa para construir seu novo guarda-roupa (o app). A loja envia todos os possíveis itens que ele pode precisar para montar o guarda-roupa. Cabe a David (o programador) decidir quais componentes (software capabilities) são adequados e como montá-los para obter o resultado desejado (a aplicação).

A ideia de um framework não é nova. Ao longo da história do desenvolvimento de software, os programadores observaram que poderiam reutilizar partes do código que haviam escrito em várias aplicações. Inicialmente, quando poucas aplicações eram implementadas, cada aplicação era única e desenvolvida do zero usando uma linguagem de programação específica.

À medida que o domínio do desenvolvimento de software se expandiu e mais e mais aplicações começaram a ser lançadas no mercado, tornou-se mais fácil perceber que muitas dessas aplicações tinham requisitos semelhantes. Vamos citar alguns deles:
- A **log** de mensagens de erro, aviso e informação ocorre em todas as aplicações;
- A maioria das aplicações utiliza transações para processar alterações de dados. As transações representam um mecanismo importante que garante a consistência dos dados. Discutiremos esse assunto em detalhes no capítulo 13.
- A maioria das aplicações utiliza mecanismos de proteção contra as mesmas vulnerabilidades comuns.
- A maioria das aplicações utiliza formas semelhantes para se comunicar umas com as outras;
- A maioria das aplicações utiliza mecanismos semelhantes para melhorar seu desempenho, como *caching* ou compressão de dados.

E a lista continua. Acontece que o código de lógica de negócios implementado em um aplicativo é significativamente menor do que as engrenagens e correias que fazem o motor da aplicação funcionar (também frequentemente referido como "a parte de infraestrutura" ou #plumbing).

Quando digamos que o **código de lógica de negócios**, estamos nos referindo ao código que implementa os requisitos de negócio da aplicação. Esse código é o que atende às expectativas dos usuários em uma aplicação. Por exemplo, clicar em um link específico irá gerar uma fatura é algo que os usuários esperam que aconteça. Parte do código da aplicação que desenvolvemos implementa essa funcionalidade, e essa parte do código é o que os desenvolvedores chamam de **código de lógica de negócios**. No entanto, qualquer aplicação precisa lidar com vários outros aspectos: segurança, registro de logs (logging), consistência de dados e assim por diante.
![[Chapter 1 - Spring in the real world-1.png]]
A perspectiva do usuário é como um iceberg. Os usuários principalmente observam os resultados do código de lógica de negócios, mas isso é apenas uma pequena parte do que compõe a funcionalidade completa do aplicativo. Assim como um iceberg tem a maior parte de sua massa submersa, em um aplicativo corporativo não vemos a maior parte do código porque ele é fornecido por dependências. 

Além disso, o código de lógica de negócios é o que diferencia um aplicativo de outro do ponto de vista funcional. Se pegarmos dois aplicativos diferentes, digamos um sistema de compartilhamento de viagens e um aplicativo de rede social, eles terão casos de usos diferentes.

**NOTA** Um caso de uso representa a razão pela qual uma pessoa utiliza o aplicativo. Por exemplo, em um aplicativo de compartilhamento de viagens, um caso de uso é “solicitar um carro.” Para um aplicativo que gerencia entregas de alimentos, um caso de uso é “pedir uma pizza.”

Realizamos diferentes ações em cada aplicativo, mas ambos precisam de armazenamento de dados, transfêrencia de dados, registro de logs #logging, configurações de segurança, provavelmente #caching e assim por diante. Diversos aplicativos podem reutilizar essas implementações não relacionadas à lógica de negócios. Então, é eficiente reescrever as mesmas funcionalidades todas vez?
Claro que não:
- Economizamos muito tempo e dinheiro ao reutilizar algo em vez de desenvolver sozinho;
- Uma implementação já existente, que muitos aplicativos já utilizam, tem menos chances de introduzir bugs, pois outros já a testaram;
- Nos beneficiamos dos conselhos de uma comunidade, pois agora há muitos desenvolvedores que entendem a mesma funcionalidade. Se tivéssemos implementado nosso próprio código, apenas algumas pessoas o conheceriam.

**Uma história de transição**
Um dos primeiros aplicativos nos quais trabalhei foi um sistema enorme desenvolvido em Java. Esse sistema era composto por múltiplos aplicativos projetados em torno de uma arquitetura de servidor antiquada, todos eles escritos do zero usando Java SE. O desenvolvimento desse aplicativo começou com a linguagem cerca de 25 anos atrás. Essa foi a principal razão para o seu formato. E quase ninguém poderia ter imaginado o quão grande ele se tornaria. Naquela época, conceitos mais avançados de arquiteturas de sistemas não existiam, e as coisas, em geral, funcionavam de maneira diferente nos sistemas individuais, devido à conexão lenta com a internet.

Mas o tempo passou, e anos depois, o aplicativo se transformou em algo parecido com "uma grande bola de lama" (big ball of mud). Por razões válidas que não abordarei aqui, a equipe decidiu que precisava migrar para uma arquitetura moderna. Essa mudança implicou, primeiro, limpar o código, e um dos principais passos foi usar um framework. Decidimos adotar o Spring. Naquela época, tínhamos como alternativa o Java EE (agora chamado Jakarta EE), mas a maioria dos membros da equipe considerou melhor optar pelo Spring, que oferecia uma alternativa mais leve, mais fácil de implementar e que também achávamos mais simples de manter.

A transição não foi fácil. Juntamente com alguns colegas, especialistas em seus domínios e conhecedores do próprio aplicativo, investimos muito esforço nessa transformação.

O resultado foi incrível! Removemos mais de 40% das linhas de código. Essa transição foi o primeiro momento em que entendi o quão significativo poderia ser o impacto do uso de um framework.

## 1.2 The Spring Ecosystem
Nesta seção, discutiremos o Spring e projetos relacionados, como o **Spring Boot** ou o **Spring Data** . Você aprenderá tudo sobre eles neste livro, bem como as conexões entre eles. Em cenários do mundo real, é comum usar diferentes frameworks juntos, onde cada framework é projetado para ajudá-lo a implementar uma parte específica do aplicativo de forma mais rápida.

Nos referimos ao Spring como um framework, mas ele é muito mais complexo do que isso. O **Spring** é, na verdade, um ecossistema de frameworks. Normalmente, quando os desenvolvedores se referem ao **Spring Framework**, eles estão falando de uma parte das capacidades de software que incluem o seguinte:
1. #Spring-Core - uma das partes fundamentais do Spring, que inclui capacidades básicas. Um desses recursos é o **Spring Context**. Como aprenderemos, em detalhes no capítulo 2, o #spring-context permite ao Spring gerenciar as instâncias do nosso aplicativo. Além disso, como parte do #Spring-Core, encontraremos a funcionalidade de **Aspectos do Spring**. Os aspectos ajudam o Spring a interceptar e manipular métodos que definimos em nosso aplicativo. Discutiremos mais detalhes sobre os aspectos no capítulo 6. A **Linguagem de Expressão do Spring (SpEL)** é outra capacidade que encontraremos como parte do **Spring Core**, que permite que descrevamos configurações para o Spring usando uma linguagem específica. Todos esses são conceitos novos, e não esperamos que os conheçamos ainda. Mas entenderemos que o **Spring Core** contém os mecanismos que o Spring usa para integrar ao nosso aplicativo.
2. **Spring Model-View-Controller (MVC)** - a parte do Spring Framework que permite que desenvolvamos aplicativos web que atendem solicitações HTTP. Começaremos a usar o **Spring MVC** a partir do capítulo 7.
3. **Spring Data Acess** - também uma das partes fundamentais do Spring. Ele fornece ferramentas básicas que podemos usar para nos conectarmos a bancos de dados SQL e implementar a camada de persistência do nosso aplicativo.
4. **Spring Testing** - a parte que contém as ferramentas necessárias para escrever testes para nossa aplicação Spring. 


### 1.2.1 Discovering Spring Core: The fundation of Spring
O **Spring Core** é a parte do **Spring Framework** que fornece os mecanismos fundamentais para integrar-se aos aplicativo. O Spring funciona com base no **princípio da *inversão de controle (IoC)***. Ao usar esse princípio, em vez de permitir que o aplicativo controle a execução, transferimos o controle para outra parte do software - no nosso caso, o **Spring Framework**.

Por meio de configurações, instruímos o framework sobre como gerenciar o código que descrevemos, o qual define a lógica do aplicativo. É daí que vem a **inversão** em IoC: não deixamos o aplicativo controlar a execução por meio de seu próprio código e dependências. Em vez disso, permitimos que o framework (a dependência) controle o aplicativo e seu código.

**NOTA** Neste contexto, o termo controle refere-se a ações como **criar uma instância** ou **chamar um método**. Um framework pode criar objetos das classes que definimos em nosso aplicativo. Com base nas configurações que escrevemos, o Spring intercepta métodos para aumentá-los com várias funcionalidades. Por exemplo, o Spring pode interceptar um método específico para registrar erro que possa ocorrer durante a execução do método.

![[Chapter 1 - Spring in the real world-2.png]]

Aprendemos sobre o Spring com o Spring Core, discutindo a funcionalidade IoC do Spring nos capítulos 2 a 5. O **contêiner IoC** conecta os componentes do Spring e os componentes da nossa aplicação ao framework. Usando o **contêiner IoC**, ao qual frequentemente nos referimos como **Spring Context**, tornamos certos objetos conhecidos pelo Spring, permitindo que o framework os utilize da maneira que configuramos. 

No capítulo 6, continuaremos nossa discussão com a **programação orientada a aspectos** #AOP  do Spring. O Spring pode controlar as intâncias adicionadas ao seu contêiner IoC, e uma das coisas que ele pode fazer é interceptar métodos que representam o comportamento dessas instâncias. Essa capacidade é chamada de **aspectar o método**. O **Spring AOP** é uma das formas mais comuns pelas quais o framework interage com o que sua aplicação faz. Essa característica faz com que o **Spring AOP** também seja parte dos elementos essenciais. Como parte do **Spring Core**, também encontraremos gerenciamento de recursos, internacionalização, conversão de tipos e SpEL.

### 1.2.4 Projects from the Spring Ecosystem
O ecossistema Spring é muito mais do que apenas as capacidades discutidas anteriormente. Ele inclui uma grande coleção de frameworks que se integram bem e forma um universo maior. Aqui, temos projetos como **Spring Data**, Security, Cloud, Batch e Boot e assim por diante. Quando desenvolvemos um aplicativo, podemos usar vários desses projetos juntos. Por exemplo, podemos construir um aplicativo utilizando **Spring Boot**, **Spring Security** e **Spring Data** simultaneamente.

**Extendendo as capacidades de Persistência com o Spring Data**
O projeto **Spring Data** implementa uma parte do ecossistema Spring que permite nos conectarmos facilmente a banco de dados e utilizarmos a camada de persistência com um número mínimo de linhas de código escritas. O projeto abrange tanto tecnologias SQL quanto NoSQL e cria uma camada de alto nível, que simplifica a forma como trabalhamos com a persistência de dados.
**NOTA** Temos o *Spring Data Access*, que é um módulo do Spring Core, e também temos um projeto independente no ecossistema Spring chamado *Spring Data*. O Spring Data Acess contém as implementações fundamentais para acesso a banco de dados, como mecanismos de transações e ferramentas JDBC. Já o Spring Data amplia o acesso a banco de dados e oferece um conjunto mais amplo de ferramentas, tornando o desenvolvimento mais acessível e permitindo que sua aplicação se conecte a diferente tipos de fontes de dados. 

**Spring Boot**
O Spring Boot é um projeto que faz parte do ecossistema Spring e introduz o conceito de convenção sobre configuração. A principal ideia desse conceito é que, em vez de configurar manualmente todos os aspectos de um framework, o **Spring Boot** oferece configuração padrão que podemos personalizar conforme necessário. O resultado, em geral, é que podemos escrever menos código porque segue convenções conhecidas, e nossa aplicação se diferencia de outras apenas em poucos ou pequenos aspectos. Assim, em vez de escrever todas as configurações para cada aplicativo, é mais eficiente começar com uma configuração padrão e alterar apenas o que for diferente da convenção. 

## 1.3 Spring em real-world scenarios
Agora que temos uma visão geral do Spring, e estamos ciente de quando e por que usar um framework. Nesta seção, apresentaremos alguns cenários de aplicação em que o uso do **Spring Framework** pode ser uma excelente escolha. Com muita frequência, vejo o desenvolvedores associarem o uso de frameworks como o Spring apenas a aplicações backend. Há até uma tendência de restringir ainda mais esse cenário a aplicações web backend. Embora seja verdade que, em muitos casos, o Spring seja usado dessa forma, é importante lembrar que o framework não está limitado a esse tipo de aplicação. Já vi equipes usando o Spring com sucesso em diferentes tipos de aplicativos, como no desenvolvimento de uma aplicação de automação de testes ou até mesmo em cenários de desktop autônomos.

Vamos descrever alguns cenários no mundo real comuns nos quais vi o Spring sendo usado com sucesso. Esses não são os únicos cenários possíveis, e o Spring pode nem sempre funcionar em todos esses casos. Um framework nem sempre é uma boa escolha. No entanto, estes são casos comuns em que, em geral, o Spring é uma boa opção:
1. **O desenvolvimento de uma aplicação backend**
2. **O desenvolvimento de um framework de automação de testes**
3. **O desenvolvimento de uma aplicação desktop**
4. **O desenvolvimento de uma aplicação móvel**

