## <span style="background:#d4b106">INTRODUÇÃO</span>
A [[orientação a objetos]] é um conjunto de técnicas que podem ser utilizadas para realizar o desenvolvimento de programas mais eficientes, melhorando a **confiabilidade** dos programas de computação. Na programação orientada a objetos, os objetos são os elementos principais de construção. Entretanto, a simples compreensão do que é um objeto, ou o uso de objetos em um programa, não significa que estamos programando de uma maneira orientada a objetos. O que conta é o sistema no qual os objetos se interconectam e se comunicam entre si.

Este texto se limita ao campo da programação, mas é também possível falar de sistemas de administração de banco de dados orientadas a objetos, sistemas operacionais orientados a objetos, interfaces de usuários orientados a objetos etc.

Neste capítulo, vamos apresentar o conceito de #herança e mostramos como criar #classes-derivadas. A **herança torna possível** criar hierarquias de classes relacionadas e reduz a quantidade de código redundante em componentes de classes. 

A #herança é a propriedade que permite definir novas classes usando como base as classes já existentes. A nova classe (classe derivada) herda os atributos e comportamento que são específicos dela. A herança é uma ferramenta poderosa que proporciona um marco adequado para produzir software confiável, compreensível, com baixo custo, adaptável e reutilizável. 

## <span style="background:#fff88f">O QUE É PROGRAMAÇÃO ORIENTADA A OBJETOS?</span>

*Grady Booch* define a programação orientada a objetos (POO) como:
	"Um método de implementação no qual os programas são organizados como coleções cooperativas de objetos, cada uma das quais representando uma instância de alguma classe e cujas classes são membros de uma hierarquia de classes unidas mediante relações de herança.".

Existem três partes importante na definição: a programação orientada a objetos (1) utiliza #objetos, não algoritmos, como blocos de construção lógicos (*hierarquia de objetos*); (2) cada objeto é uma instância de uma #classe, e (3) as classes se relacionam umas com as outras por meio de relações de #herança. 

Um programa pode parecer orientado a objetos, mas, se qualquer desses elementos não existe, não é um programa orientado a objetos. Especificamente, a programação sem herança é diferente de programação orientada a objetos, e a denominamos *programação com tipos abstratos de dados ou programação baseada em objetos*.

Um conceito de #objeto, como os tipos abstratos de dados ou tipos definidos pelo usuário, é uma coleção de elementos de dados, juntamente com as funções associadas para operar sobre esses dados. Entretanto, a potência real dos objetos reside na maneira como eles podem definir outros objetos. Objeto = (dados + funções) classe;

**Os programas orientados a objetos constam de objetos. Os objetos de um programa se comunicam com cada um dos restantes passando mensagens.**

### <span style="background:#d4b106">O OBJETO</span>
A ideia fundamental das linguagens a objetos é combinar em uma só unidade #dados e #funções que operam sobre esses dados. Tal unidade é denominada #objeto. Consequentemente, dentro dos objetos residem os dados das linguagens de programação tradicionais, como números, arrays, cadeias e registros, assim como funções ou sub-rotinas que operam sobre eles.

As funções dentro do objeto ( #métodos em Java ) são o único meio de acessar os dados privados de um objeto. Desejando ler um elemento dado de um objeto, ativamos a função membro do objeto. O elemento é lido e devolvemos o valor. Não podemos acessar os dados diretamente. Os dados são ocultos, e isso segura que não podemos modificar acidentalmente por funções externas ao objeto. 

Dizemos que os **dados** e as **funções** associados estão *encapsulados* em uma única #entidade ou #módulo ou #classe. Os dados encapsulados e ocultos são termos importantes na descrição de linguagens orientadas a objetos.

Desejando modificar os dados de um objeto, conhecemos exatamente quais são as funções (métodos) que interagem com o objeto, visto que eles executam uma ou mais de uma ação. Nenhuma outra função (método) pode acessar os dados. Essas características simplificam a escrita, depuração e manutenção do programa. 

### EXEMPLOS DE OBJETOS
Que classe de coisas pode ser objeto em um programa orientado a objetos? A resposta está apenas limitada a sua imaginação. Alguns exemplos típicos podem ser:

- *Objetos físicos:*
Aviões em um sistema de controle de tráfego aéreo
Automóveis em um sistema de controle de tráfego terrestre
Casas
- *Elementos de Interface Gráficas de Usuário de Computadores:*
Janelas;
Menus;
Objetos gráficos (quadrados, triângulos etc.);
Teclados, impressoras, unidades de disco;
Caixas de diálogo;
Mouse, telefones celulares;
- *Animais:*
Animais vertebrados;
Animais invertebrados;
Peixes;

Um objeto é uma entidade que contém os #atributos que descrevem o estado de um objeto do mundo real e as ações que estão associadas ao objeto do mundo real. Designamos por um nome ou identificador do objeto. Nesse contexto de uma linguagem orientada a objtos (LOO), um objeto #encapsula dados (atributos) e as funções (métodos) que manuseiam esses dados. 

O encapsular de operações e informação é muito importante. Os métodos de um objeto só podem ser manipulados diretamente de dados associados a esse objeto. 

### MÉTODOS E MENSAGENS
Um programa orientado a #objetos consiste em um número de objetos que se comunicam uns com os outros ativando funções membro. As #funções são denominadas #métodos em Java. Uma mensagem é a **ação** que tem um objeto. Um método é o procedimento ou função que é invocado para atuar sobre um objeto e especifica como é executada uma mensagem. 

O conjunto de mensagens às quais um objeto pode responder é denominado *protocolo* do objeto. Por exemplo, o protocolo de um ícone pode constar de mensagens invocadas pelo clique de um botão do mouse quando o usuário localiza um ponteiro sobre um ícone.  

A estrutura interna de um objeto está oculta dos usuários e programadores. As mensagens que o objeto recebe **são os únicos condutores que conectam o objeto com o mundo externo**. As mensagens, são como #requisições, visto que, quando um objeto está enviando uma mensagem para outro objeto, ele está realizando uma requisição para que o objeto execute um dos seus métodos, ou faça uma ação. 
Os dados de um objeto estão disponíveis para serem manuseados somente pelos métodos do próprio objeto.

Quando executamos um programa orientado a objetos ocorrem <span style="background:#d3f8b6">três processos</span>. 
- <span style="background:#d4b106">Primeiro</span>: os objetos são criados à medida que são necessários. 
- <span style="background:#d4b106">Segundo</span>: as mensagens se movem de um objeto para outro (ou do usuário para um objeto) à medida que o programa processa internamente informação ou responde à entrada do usuário. 
- <span style="background:#d4b106">Terceiro</span>: quando os objetos já não são necessários, são apagados e é liberado a memória. 

### CLASSES
Uma #classe é a descrição de um conjunto de objetos; consta de métodos e dados que resumem características comuns de um conjunto de objetos. Cada  vez que construímos um objeto partindo de uma classe, estamos criando o que chamamos de uma #instância dessa classe. 
Um objeto nada mais é do que uma instância de uma classe. Geralmente, instância de uma classe e objeto são termos intercalados.

## UM MUNDO DE OBJETOS

Os objetos possuem uma vantagem importantíssima que é a sua representatividade do mundo real, os objetos podem representar entidades ou substantivos do mundo. Podemos criar um tipo base que representa o comportamento e características comuns aos tipos derivados desse tipo base. 

Os tipos objetos definidos pelo usuário contêm dados definidos pelo usuário (*características*) e operações (*comportamentos*). 

## IDENTIFICAÇÃO DE OBJETOS
O primeiro problema que temos quando queremos implementar programação orientada a objetos é o de identificar os objetos, ou seja, que coisas são objetos? Como deduzimos os objetos dentro do domínio da definição do problema?

A identificação de objetos é obtida examinando-se a descrição do problema (análise gramatical aparente do enunciado ou descrição) e localizando os nomes ou cláusulas nominais. 

Segundo Shlaer, Mellor e Coad/Yourdon, os <span style="background:#d3f8b6">objetos</span> podem enquadrar nas seguintes categorias:
- *Coisas tangíveis:*(avião, reator nuclear, fonte de alimentação, televisão, livro de automóveis);
- *Papéis jogados ou representados por pessoas:*(gerente, cliente, empregado, médico, paciente, engenheiro);
- *Organizações:*(empresa, divisão, equipe...);
- *Incidentes:* representa um evento - ou ocorrência - como vôo, acidente, chamada a um serviço de assistência técnica...;
- *Interações (implicam geralmente uma transação ou contrato e relacionam dois ou mais objetos do modelo:* compras - comprador, vendedor, artigo; casamento - esposo, esposa, data do casamento).
- *Especificações (mostram aplicações de inventário ou fabricação:* geladeira, freezer...);
- *Lugares:* sala de embarque, galpão de carga...

Uma vez identificado os objetos, será preciso identificar os atributos e as operações que atuam sobre eles. Os *atributos* descrevem a abstração de características individuais que todos os objetos possuem.

As <span style="background:#d4b106">operações</span> mudam o objeto - seu comportamento - de alguma maneira, ou seja, **mudam valores de um ou mais atributos** contidos no objeto. Mesmo existindo um grande número de operações que podem ser realizadas sobre um objeto, em geral elas se dividem em três grandes grupos:
- Calculam;
- Manipulam dados;
- Monitoram;

A identificação das operações é realizada fazendo-se uma nova análise gramatical da descrição do problema e buscando e separando os verbos do texto.

### DURAÇÃO DOS OBJETOS
Os objetos são entidades que existem no tempo; por isso, devem ser criados ou instanciados (normalmente, por meio de outros objetos). Essa operação é feita mediante operações especiais chamadas *construtores* ou *iniciadores*, que são executados implicitamente pelo compilador ou explicitamente pelo *programador* por meio da invocação dos citados construtores.


