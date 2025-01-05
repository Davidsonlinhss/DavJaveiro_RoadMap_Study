## Este capítulo aborda
- Estabelecendo relações entre beans;
- Utilizando injeção de dependência;
- Acessando beans do contexto spring via injeção de dependência.

No capítulo 2, discutimos o contexto do Spring: o local na memória do aplicativo onde adicionamos as instâncias de objeto que queremos que o Spring gerencie. Como o Spring usa o princípio IoC, conforme discutimos no capítulo 1, <span style="background:#fff88f">precisamos informar ao Spring quais objetos do nosso aplicativo ele precisa controla</span>r. O Spring precisa de controle sobre alguns dos objetos do nosso aplicativo para aumentá-los com os recursos que ele fornece. No capítulo 2, você aprendeu várias maneiras de adicionar instâncias de objetos ao contexto do Spring. Você também aprendeu que adicionamos essas instâncias (beans) ao contexto do Spring para que o Spring tenha conhecimento delas.
Neste capítulo, discutiremos como acessar os beans que adicionamos ao contexto do Spring. No capítulo 2, <span style="background:#fff88f">usamos o método getBean() da instância</span> do contexto diretamente para acessar os beans. Mas nos aplicativos, precisamos fazer referência de um bean a outro de forma direta, dizendo ao Spring para fornecer uma referência a uma instância de seu contexto onde precisarmos dela. Dessa forma, estabelecemos relações entre os beans (um bean terá uma referência a outro para delegar chamadas quando necessário). Como você provavelmente já sabe, em qualquer linguagem de programação orientada a objetos, <span style="background:#fff88f">um objeto precisa delegar responsabilidades específicas a outros ao implementar seu comportamento</span>, portanto, você também precisa saber como estabelecer essas relações entre objetos ao usar o Spring como estrutura.
Você aprenderá que há mais maneiras de acessar os objetos que adicionou ao contexto do Spring, e estudaremos cada uma delas com exemplos, recursos visuais e, é claro, trechos de código. Ao final deste capítulo, você terá as habilidades necessárias para usar o contexto do Spring e configurar beans e relacionamentos entre eles. Essa habilidade é a base do uso do Spring; você não encontrará nenhum aplicativo Spring no qual não aplicaria as abordagens que discutimos neste capítulo. Por esse motivo, tudo neste livro (e tudo o que você aprenderá em qualquer outro livro, artigo ou vídeo tutorial) depende da compreensão adequada das abordagens que discutimos nos capítulos 2 a 5.

No capítulo 2, você aprendeu a usar <span style="background:#fff88f">a anotação @Bean para adicionar beans no contexto do Spring</span>. Na seção 3.1, começamos implementando uma relação entre dois beans que você definirá na classe de configuração usando a anotação @Bean. Aqui discutiremos duas maneiras de estabelecer as relações entre os beans:
- Vincule os beans chamando diretamente os métodos que os criam (que chamaremos de fiação).
- Permitir que o Spring nos forneça um valor usando um parâmetro de método (que chamaremos de auto-wiring).
Em seguida, na seção 3.2, discutiremos uma terceira abordagem, que é uma técnica apoiada pelo princípio IoC: injeção de dependência (DI). Discutiremos como usar a DI no Spring, aplicando a anotação @Autowired para implementar a relação entre dois beans (que também é um exemplo de conexão automática). Você usará essas duas abordagens juntas em projetos reais.

---
**NOTA**
Você pode pensar que os exemplos dos capítulos 2 e 3 não estão suficientemente próximos do código de produção. No fim das contas, os aplicativos reais não gerenciam papagaios e pessoas! Mas quero começar suavemente com os exemplos mais diretos e garantir que você se concentre nessas sintaxes essenciais, que serão usadas em praticamente todos os aplicativos Spring. Dessa forma, certifico-me de que você entenda corretamente como as abordagens discutidas funcionam e se concentre somente nelas. A partir do capítulo 4, o design de nossa classe ficará mais próximo do que você encontrará em projetos do mundo real.

---
## 3.1 Implementação de relações entre beans definidos no arquivo de configuração
Nesta seção, você aprenderá a implementar a relação entre dois beans definidos na classe de configuração, <span style="background:#fff88f">anotando métodos com a anotação @Bean</span>. Você encontrará com frequência essa abordagem para estabelecer as relações entre os beans usando a configuração do Spring. No capítulo 2, discutimos que usamos a anotação @Bean para adicionar beans ao contexto do Spring nos casos em que não podemos alterar a classe a qual queremos adicionar o bean, por exemplo, se a classe fizer parte do JDK ou de outra dependência. E para estabelecer relações entre esses beans, você precisa aprender as abordagens que discutiremos nesta seção. Discutiremos como essas abordagens funcionam, darei a você as etapas necessárias para implementar as relações entre os beans e, em seguida, aplicaremos essas etapas com pequenos projetos de código.

Digamos que tenhamos duas instâncias no contexto do Spring: um papagaio e uma pessoa. Criaremos e adicionaremos essas instâncias ao contexto. Queremos que a pessoa seja proprietária do papagaio. Em outras palavras, precisamos vincular as duas instâncias. Esse exemplo simples nos ajuda a discutir as duas abordagens para vincular os beans no contexto do Spring sem adicionar complexidade desnecessária e permite que você se concentre apenas nas configurações do Spring.

Portanto, para cada uma das duas abordagens (fiação e fiação automática), temos duas etapas (Figura 3.1):
ETAPA 1: No contexto do Spring, você adiciona um papagaio e uma pessoa como beans.
ETAPA 2: Você faz com que a pessoa seja a dona do papagaio
![[Capítulo 3 - O contexto do Spring - Fiação de beans.png]]
Figura 3.1 - Com dois beans no contexto do Spring, queremos estabelecer um relacionamento entre eles. Fazemos isso para que um objeto possa delegar ao outro na implementação de suas responsabilidades. Você pode fazer isso usando uma abordagem de conexão, o que implica chamar diretamente os métodos que declaram os beans para estabelecer o vínculo entre eles, ou por meio de conexão automática. Você usa os recursos de injeção de dependência da estrutura.

Antes de mergulhar em qualquer uma das abordagens, vamos começar com o primeiro exemplo deste capítulo ("sq-ch3-ex1") para lembrar como adicionar os beans ao contexto do Spring usando métodos anotados com @Bean na classe de configuração, conforme discutido na seção 2.2.1 (etapa 1). Adicionaremos uma instância de papagaio e uma instância de pessoa. Quando tivermos esse projeto pronto nós o alteramos para estabelecer a relação entre as duas instâncias (etapa 2). Na seção 3.1.1, implementamos a fiação e, na seção 3.1.2, implementamos a fiação automática para métodos anotados @Bean. No arquivo pom.xml do projeto Maven, adicionamos a dependência do contexto do Spring, conforme você encontra no próximo trecho de código:

Em seguida, definimos uma classe para descrever <span style="background:#fff88f">o objeto Parrot</span> e <span style="background:#fff88f">outra para descrever a Pessoa</span>. No próximo trecho de código, você encontra a definição da classe Parrot...

Agora você pode escrever uma classe Main, conforme apresentado na listagem a seguir, e verificar se as duas instâncias ainda não estão vinculadas uma à outra.


O mais importante a ser observado aqui é que o papagaio da pessoa (terceira linha de saída) é nulo. No entanto, as instâncias da pessoa e do papagaio estão no contexto. Essa saída é nula, o que significa que ainda não há um relacionamento entre as instâncias (figura 3.3).

**Observações**
1. A pessoa ainda não é proprietária do papagaio.
2. Os dois #beans estão no contexto, mas não foi estabelecido nenhum vínculo entre eles.


### 3.1.1 Fazer a fiação dos beans usando uma chamada de método direta entre os métodos @Bean
Nesta seção, estabelecemos a relação entre as duas instâncias de Person e Parrot. A primeira maneira (conexão entre dependência) de conseguir isso <span style="background:#fff88f">é chamar um método de outro na classe de configuração</span>. Você verá que isso é usado com frequência porque é uma abordagem direta. Na próxima listagem, você encontrará a pequena alteração que tive de fazer na minha classe de configuração para <span style="background:#fff88f">estabelecer um vínculo entre a pessoa e o papagaio</span> (veja a Figura 3.4). Para manter todas as etapas separadas e ajudá-lo a entender melhor o código, também separei essa alteração em um segundo projeto: "sq-ch3-ex2".

Executando o mesmo aplicativo, observamos que o resultado foi alterado no console. Agora podemos ver que a segunda linha exibe que *Ella* (a pessoa no Spring Context) é dona de Koko (o papagaio dentro do Spring Context):

Person's name: Ella
Person's parrot: Parrot : Koko : Nós agora podemos observar a relação entre a pessoa e o papagaio que foi estabelecida.

![[Capítulo 3 - O contexto do Spring - Fiação de beans-1.png]]
Figura 3.4 Nós estabelecemos a relação entre os beans utilizando conexões diretas. Está abordagem implica em chamar o método que retorna o bean que queremos definir diretamente. Precisamos chamar esse método a partir de uma que define o bean para o qual nós definimos a dependência. 

Sempre que ensino essa abordagem em uma aula, sei que alguns têm a seguinte pergunta: <span style="background:#fff88f">isso não significa que criamos duas instâncias do Parrot</span> (figura 3.5) - uma instância que o Spring cria e adiciona ao seu contexto e outra quando o método person() faz a chamada direta para o método parrot()? Não, na verdade temos apenas uma instância do Parrot nesse aplicativo em geral.

Pode parecer estranho a princípio, mas o Spring é inteligente o suficiente para entender que, ao chamar o método parrot(), você quer se referir ao bean parrot em seu contexto. Quando usamos a anotação @Bean para definir beans no contexto do Spring, o Spring controla como os métodos são chamados e pode aplicar a lógica acima da chamada do método (você aprenderá mais sobre como o Spring intercepta métodos no capítulo 6). Por enquanto, lembre-se de que quando o método person() chamar o método parrot(), o Spring aplicará a lógica, conforme descrito a seguir.

Se o bean do papagaio já existir dentro do contexto Spring, então, ao invés de chamar o método Parrot(), o Spring irá diretamente pegar a instância dentro do contexto. 

---
**OBSERVAÇÃO**
Sem o Spring, teríamos duas instâncias de **Parrot** ao usar esse código diretamente.
1. Sem o Spring, sempre que chamássemos o método **Parrot()** dentro do método **Person()**, ele criaria uma nova instância de **Parrot()** cada vez, resultando em duas instâncias separadas (uma em cada chamada).
2. No entanto, com o Spring, o contêiner de *beans* atua como um intermediário. Ele garante que, ao chamar **Parrot()**, apenas uma instância única de **Parrot()** seja criada e armazenada no contexto. Assim, quando **Parrot** é chamado dentro do método **Person()**, o Spring verifica se a instância de **Parrot()** é chamado dentro do método **Person()**, o Spring verifica se a instância de **Parrot()** já foi criada. Se sim, ele reutiliza a instância existente; se não, ele cria e armazena no contexto para uso futuro.

Portanto, o Spring evita a criação de múltiplas instâncias para o mesmo *bean*, promovendo a reutilização da mesma instância de **Parrot** em toda a aplicação, a menos que seja configurado de outra forma (por exemplo, no escopo *Prototype.*)

---



Figura 3.6 Quando dois métodos anotados com @Bean chamam um ao outro, o Spring sabe que você deseja criar um link entre os beans. Se o bean já existir no contexto (3A), o Spring retorna o bean existente sem encaminhar a chamada para o método @Bean. Se o bean não existir (3B), o Spring cria o bean e retorna sua referência.

Na verdade, é muito fácil testar esse comportamento. Basta adicionar um construtor sem argumento à classe Parrot e imprimir uma mensagem no console a partir dele. Quantas vezes a mensagem será impressa no console? Se o comportamento estiver correto, você verá a mensagem apenas uma vez. Vamos fazer esse experimento. No próximo trecho de código, alterei a classe Parrot para adicionar um construtor sem args:

Execute novamente o aplicativo. A saída foi alterada (veja o próximo trecho de código), e agora a mensagem "Parrot created" (Papagaio criado) também é exibida. Você observará que ela aparece apenas uma vez, o que prova que o Spring gerencia a criação do bean e chama o método parrot() apenas uma vez:
Parrot created
Person's name: Ella
Person's parrot: Parrot : Koko

### 3.1.2 Fazer a conexão dos beans usando os parâmetros do método anotado com @Bean

