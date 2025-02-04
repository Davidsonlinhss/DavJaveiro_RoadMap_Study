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

