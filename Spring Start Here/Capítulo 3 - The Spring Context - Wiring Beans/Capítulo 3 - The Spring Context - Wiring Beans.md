*This chapter covers*
- Estabelecendo relacionamentos entre beans;
- Usando injeção de dependência;
- Acessando os beans do contexto do Spring através de injeção de dependência.

No capítulo 2, discutimos o contexto do Spring: o local na memória do aplicativo onde adicionamos as instâncias de objetos que queremos que o Spring gerencie. Como o Spring usa o princípio IoC, conforme discutimos no capítulo 1, <span style="background:#affad1">precisamos dizer ao Spring quais objetos do nosso aplicativo ele precisa controlar</span>. O Spring precisa controlar alguns dos objetos do nosso aplicativo para aprimorá-los com as capacidades que ele fornece. No capítulo 2, aprendemos várias maneiras de *adicionar instâncias de objetos* ao contexto do Spring. Aprendemos que também adicionamos essas instâncias (beans) ao contexto do Spring para torná-lo ciente delas.

Neste capítulo, discutimos como acessar os beans que adicionamos ao contexto do Spring. No capítulo 2, usamos o método *getBean()* da instância do contexto diretamente para acessar os beans, mas, em aplicativos, precisamos referenciar um bean a partir de outro de uma maneira direta - dizendo ao Spring para fornecer uma referência a uma instância do seu contexto onde for necessário. Dessa forma, estabelecemos relacionamentos entre os beans (um beans terá uma referência a outro para delegar chamadas quando necessário). 

Como já sabemos, muitas vezes, em qualquer linguagem de programação orientada a objetos, **um objeto precisa delegar responsabilidades específicas a outros** ao implementar seu comportamento, então *você precisa saber como estabelecer tais relacionamentos* entre objetos ao usar o Spring como framework também. 

Você aprenderá que há mais maneiras de acessar os objetos que adicionou ao contexto do Spring, e estudaremos cada uma com exemplos, visuais e, claro, trechos de código. Ao final deste capítulo, teremos as habilidades necessárias para usar o contexto do Spring e configurar beans e os relacionamentos entre eles. Esta habilidade é a base do uso do Spring; não há nenhum aplicativo Spring em que não aplicaremos as abordagens discutidas neste capítulo. Por isso, tudo neste livro (e tudo que aprenderemos em qualquer outro livro, artigo ou tutorial em vídeo) depende do entendimento adequado das abordagens discutidas nos capítulos 2 a 5.

No capítulo 2, aprendemos a usar a anotação *@Bean* para adicionar beans ao contexto do Spring. Na seção 3.1, começamos implementando um relacionamento entre dois beans que definimos na classe de configuração usando a anotação *@Bean*. Aqui, <span style="background:#d4b106">discutimos duas maneiras de estabelecer relacionamentos entre beans</span>:

- Vincular os beans chamando diretamente os métodos que os criam (o que chamaremos de *wiring*);
- Permitir que o Spring nos forneça um valor usando um parâmetro de método (o que chamaremos de *auto-wiring.*

Então, na seção 3.2, discutimos uma terceira abordagem, que é uma técnica suportada pelo princípio IoC: injeção de dependência (DI). Discutiremos como usar DI (injeção de dependência) no Spring, aplicando a anotação *@Autowired* para implementar o relacionamento entre dois beans (que também é um exemplo de auto-wiring). Usaremos essas duas abordagens juntas em projetos do mundo real.

**NOTA:** Podemos pensar que os exemplos nos capítulos 2 e 3 não estão próximos o suficiente do código de produção. Afinal, aplicativos reais não gerenciam papagaios e pessoas! Mas eu quero começar suavemente com os exemplos mais simples e garantir que você se concentre nessas sintaxes essenciais, que você usará em praticamente todos os aplicativos Spring. Desta forma, garanto que você compreenda adequadamente como as abordagens discutidas funcionam e se concentre apenas nelas. <span style="background:#d4b106">A partir do capítulo 4, nosso design de classe ficará mais próximo do que você encontrará em projetos do mundo real</span>.


## 3.1 Implementing relationships among beans defined in the configuration file

Nesta seção, aprenderemos a implementar o relacionamento entre dois beans definidos na classe de configuração, anotando métodos com a anotação *@Bean*. Frequentemente encontraremos essa abordagem para estabelecer os relacionamentos entre beans usando a configuração do Spring. No capítulo 2, discutimos que usamos a anotação *@Bean* para adicionar beans ao contexto do Spring nos casos em que não podemos alterar a classe para a qual queremos adicionar o beans, por exemplo, se a classe faz parte do JDK ou de outra dependência. E para estabelecer relacionamentos entre esses beans, precisamos aprender as abordagens discutidas nesta seção. Discutiremos como essas abordagens funcionam, fornecerei os passos necessários para implementar os relacionamentos entre os beans e, em seguida, aplicaremos esses passos com pequenos projetos de código.

Suponhamos que temos duas instâncias no contexto do Spring: um papagaio e uma pessoa. Vamos criar e adicionar essas instâncias ao contexto. Em outras palavras, precisamos vincular as duas instâncias. Este exemplo simples nos ajuda a discutir as duas abordagens para vincular os beans no contexto do Spring sem adicionar complexidade desnecessária e permite que nos concentremos apenas nas configurações do Spring.

Portanto, para cada uma das duas abordagens (wiring e auto-wiring), temos dois passos:
1. Adicionar os beans de pessoa e papagaio ao contexto do Spring (como aprendemos no capítulo 2);
2. Estabelecer um relacionamento entre a pessoa e o papagaio. 

![[Capítulo 3 - The Spring Context - Wiring Beans.png]]

**STEP 1:** In the Spring context you add a parrot and a person as beans;
**STEP 2:** You make the person own the parrot.

