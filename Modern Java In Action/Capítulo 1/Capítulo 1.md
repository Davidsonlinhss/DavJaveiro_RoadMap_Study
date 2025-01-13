"Streams - o que são?" São uma ótima adição do Java 8. Eles se comportam como coleções, mas têm várias vantagens que possibilitam novos estilos de programação. Se você já programou usando uma linguagem de consulta de banco de dados como SQL, reconhecerá que <span style="background:#d4b106">ela permite escrever consultas em poucas linhas</span> que levariam muitas linhas em Java. Os #streams do Java 8 suportam esse estilo conciso de programação de consultas a banco de dados - mas com a sintaxe do Java e sem a necessidade de saber sobre banco de dados! Segundo, os streams são projetados para que nem todos os dados precisem estar na memória (ou até mesmo serem computador) ao mesmo tempo. Logo, podemos processar streams que são grandes demais para caber na memória do computador.  O Java 8 pode otimizar operações em streams de uma maneira que o Java não pode fazer para coleções - por exemplo, ele pode agrupar várias operações no mesmo stream para que os dados sejam percorridos apenas uma vez, em vez de ser percorridos várias vezes, o que seria mais caro. O Java pode paralelizar automaticamente as operações de streams para nós (ao contrário das coleções.). 
Quando utilizarmos o #parallelStream, o Java cuida da distribuição da carga de trabalho entre os núcleos do processador, e a paralelização é feita de maneira otimizada, sem que precisemos nos preocupar com os detalhes da implementação.

Programação no estilo funcional, o que é isso?" É outro estilo de programação, assim com a programação orientada a objetos, mas centrado no uso de funções como valores.  As boas escolhas de design permitem que vejamos a programação funcional no Java 8 como um conjunto adicional de padrões de design e idioms, permitindo que escrevamos código mais claro, conciso e rápido. Pensamos nisso como ter um arsenal mais amplo de ferramentas em programação.

Como o livro *Modern Java in Action* está dividido em suas seis partes:
1. Fundamentos;
2. Processamento de dados no estilo funcional com streams;
3. Programação eficaz com streams e lambdas;
4. Java no dia a dia;
5. Concorrência aprimorada no Java;
6. Programação funcional e a evolução futura do Java.

## Part 1 - Fundamentals
Esta primeira parte fornece os fundamentos para começarmos com as novas ideias do Java introduzidas no Java 8. Ao final desta primeira parte, teremos uma compreensão completa do que são as expressões lambda e seremos capazes de escrever código que seja tanto conciso quanto flexível o suficiente para se adaptar facilmente às mudanças nos requisitos.

No capítulo 1 - resumiremos as principais mudanças no Java (expressões lambda, referências de métodos, streams e métodos default) e preparamos o cenário para o restante do livro.

No capítulo 2 - aprenderemos sobre a parametrização de comportamento, um padrão de desenvolvimento de software do qual o Java 8 depende fortemente e que motiva as expressões lambda.

No capítulo 3 - ofereceremos uma explicação completa, com exemplos de código e questionários em cada etapa, sobre os conceitos de expressões lambda e referências de métodos.

## Java 8, 9, 10, and 11: what's happening?
**Esta capítulo aborda**
- Mudança no cenário da computação;
- Pressões para o Java evoluir;
- Introduzindo novas funcionalidades centrais do Java 8 e 9.

Desde o lançamento do JDK 1.0, em 1996, o Java conquistou uma grande base de seguidores, incluindo estudantes, gerentes de projetos e programadores que são usuários ativos. É uma linguagem expressiva e continua sendo utilizada em projetos de todos os tamanhos. Por que precisamos nos importar com as mudanças ocorridas no Java?

### 1.1 So, what's the big story?
Argumentamos que as mudanças introduzidas no Java 8 foram, de muitas maneiras, mais profundas do que qualquer outra mudança na história do Java (o Java 9 adiciona mudanças importantes, mas menos significativas, relacionadas à produtividade, como veremos mais adiante, enquanto o Java 10 faz ajustes muitos menores na **inferência de tipos**).  A boa notícia é que as mudanças permitem que escrevamos programas mais facilmente. Por exemplo, ao invés de escrever um código verboso (para ordenar uma lista de maçãs no inventário com base no peso delas) como:
```java
Collection.sort(inventory, new Comparator<Apple>() {
	public int compare(Apple a1, Apple a2) {
		return a1.getWeight().compareTo(a2.getWeight());
	}
});
```
No Java 7, podemos escrever um código mais conciso que se aproxima muito mais da descrição do problema, como seguinte:
```java
inventory.sort(comparing(Apple::getWeight));
```
Ele lê como "ordenar o inventário comparando o peso da maçã". 

Também há uma influência do hardware: Os CPUs de consumo se tornaram multicore- o processo do nosso laptop ou desktop provavelmente contém quatro ou mais núcleos de CPU. No entanto, a grande maioria dos programas Java existentes usa apenas um desses núcleos e deixa os outros três ociosos (ou gasta uma pequena fração de seu poder de processamento executando parte do sistema operacional ou um antivírus).

Antes do Java 8, especialistas podiam lhe dizer que precisaria usar threads para aproveitar esses núcleos. O problema é que trabalhar com threads é difícil e propenso a erros. O Java seguiu um caminho evolutivo, tentando continuamente tornar a concorrência mais fácil e menos sujeita a erros. O Java 8 nos deus uma nova e mais simples forma de pensar sobre paralelismo. 

O Java 9 adiciona um novo método de estruturação para concorrência - programação reativa. Embora isso tenha um uso mais especializado, padroniza uma forma de explorar as ferramentas de streams reativos RxJava e Akka, que estão se tornando populares para sistemas altamente concorrentes.

A partir de dois requisitos anteriores (código mais conciso e uso mais simples de processadores multicore), surge todo o edifício consistente capturado pelo Java 8. Resumindo as ideias centrais:
- The Streams API.
- Techniques for passing code to methods;
- Default methods in interfaces.
O Java 8 oferece uma nova API (chamada #Strems) que suporta muitas operações paralelas para processar dados e se assemelha à maneira como podemos pensar nas linguagens de consulta de banco de dados - expressamos o que desejamos de uma forma como se fosse de nível mais alto, e a implementação (a biblioteca Streams) escolhe o melhor mecanismo de execução de baixo nível. Como resultado, ela evita a necessidade de escrevermos código que use #synchronized, que não só é altamente propenso a erros, mas também mais caro do que podemos imaginar em CPUs multicore.

Do ponto de vista um pouco revisionista, a adição de Streams no Java 8 pode ser vista como uma causa direta das outras duas adições ao Java 8: técnicas concisas para passar código para métodos (referências de métodos, lambdas) e métodos default em interfaces.

Mas pensar em passar código para métodos apenas como uma consequência dos Streams diminui seu alcance dentro do Java 8. Ele oferece uma nova maneira concisa de expressar a #parametrização-de-comportamento. Suponhamos que queiramos escrever dois métodos que diferem apenas em algumas linhas de código. Agora podemos simplesmente passar o código das partes que diferem como um argumento (essa técnica de programação é mais curta, clara e menos propensa a erros do que a tendência comum de copiar e colar). Especialistas notarão que a parametrização de comportamento poderia, antes do Java 8, ser codificada usando classes anônimas -. 

O recurso do Java 8 de <span style="background:#d4b106">passar código para métodos</span> (e ser capaz de retorná-lo e incorporá-lo em estrutura de dados) também fornece acesso a uma série de técnicas adicionais que são comumente chamadas de programação no estilo funcional. 

## 1.2 Why is Java Still Changing?
Com a década de 1960 veio a busca pela linguagem de programação perfeita. #Peter-Landin, um renomado cientista da computação da época, observou em 1966, em um artigo marcante, que já haviam surgido 700 linguagens de programação e especulou sobre como seriam as próximas 700 - incluindo argumentos a favor da programação no estilo funcional, semelhante à do Java 8.

Muitas milhares de linguagens de programação depois, acadêmicos concluíram que as linguagens de programação se **comportam como ecossistemas**: novas linguagens surgem, **e as antigas são substituídas, a menos que evoluam**. Todos nós esperamos por uma linguagem universal perfeita, mas na realidade, certas linguagens são mais adequadas para nichos específicos. Por exemplo, C e C++ continuam populares na construção de sistemas operacionais e outros sistemas embarcados devido ao seu pequeno **consumo de recursos em tempo de execução**, apesar da falta de segurança na programação. Essa falta de segurança pode levar a falhas imprevisíveis nos programas e expor brechas de segurança para vírus e afins; na verdade, linguagens seguras como Java e C# têm substituído C e C++ em diversas aplicações, quando o aumento de consumo de recursos é aceitável.

A ocupação prévia de um nicho tende a desencorajar concorrentes. Mudar para uma nova linguagem e conjunto de ferramentas geralmente é doloroso demais para justificar um único recurso, mas, eventualmente, **as linguagens novas deslocarão as linguagens existentes, a menos que estas evoluam rápido o suficiente para acompanhar**. 

Somos programadores Java, e o Java tem sido bem-sucedido em colonizar (e substituir linguagens concorrentes em) um grande nicho de tarefas de programação por quase 20 anos. Vamos analisar algumas razões para isso.

### 1.2.1 Java's place in the programming language ecossystem
O Java começou bem. Desde o início, foi uma linguagem orientada a objetos bem projetada, com muitas bibliotecas úteis. Também ofereceu **suporte a concorrência em pequena escala desde o primeiro dia**, com seu suporte integrado para threads e locks (e com seu reconhecimento precoce, por meio de um modelo de memória neutro em relação ao hardware, de que threads concorrentes em processadores multicore podem ter comportamentos inesperados, além daquelas que ocorrem em processadores de núcleo único). Além disso, a decisão de compilar o Java para bytecode da JVM (um código de máquina virtual que logo passou a ser suportado por todos os navegadores) fez com que ele se tornasse a linguagem escolhida para programas da applet na internet. 

De fato, há o risco de que a Máquina Virtual Java #JVM e seu bytecode sejam vistos como mais importantes do que a própria linguagem Java e, para determinadas aplicações, o Java possa ser substituído por umas de suas linguagens concorrentes, como Scala, Groovy ou Kotlin, que também rodam na JVM. Várias atualizações recentes para a JVM (por exemplo, o novo bytecode invokedynamic no JDK7) têm como objetivo ajudar essas linguagens concorrentes a rodarem suavemente na JVM - e a interagirem com o Java. O Java também teve sucesso em colonizar vários aspectos da computação embarcada (desde cartões inteligentes, torradeiras e set-top boxes até sistemas de frenagem de carros). 

---
**Como o Java entrou em um nicho de programação geral?**
A orientação a objetos se tornou popular na década de 1990 por dois motivos: sua disciplina de encapsulamento resultou em menos problemas de engenharia de software do que os de C; e, como modelo mental, ela capturava facilmente o modelo de programação #WIMP (Windows, Ícones, Menus e Ponteiros) do Windows 95 em diante. Isso pode ser resumido da seguinte forma: tudo é um objeto; e um clique do mouse envia uma mensagem de evento para um manipulador (invoca o método cliclado em um objeto Mouse). O modelo "escreva uma vez, execute em qualquer lugar" do Java e a capacidade dos primeiros navegadores de executar applets Java (de forma segura) lhe deram um nicho nas universidades, cujos graduados depois preencheram a indústrica. Houve resistência inicial ao custo adicional de execução do Java em relação ao C/C++, mas as máquinas ficaram mais rápidas, e o tempo do programador passou a ser cada vez mais importante. O C# da Microsoft validou ainda mais o modelo orientado a objetos no estilo Java.

---

Mas o clima está mudando para o ecossistemas de linguagens de programação; os programadores estão cada vez mais lidando com o chamado big data (conjunto de dados de terabytes ou mais) e desejando explorar computadores multicore ou clusters de computação de forma eficaz para processá-los. E isso significa usar processamento paralelo - algo que o Java não era amigável até então.

Podemos nos ter se deparado com ideias de outros nichos de programação (por exemplo, o mapreduce do Google ou a relativa facilidade de manipulação de dados usando linguagens de consulta de banco de dados, como #SQL) que ajudam a trabalhar com grandes volumes de dados e CPUs multicore. A figura 1.1 resume o ecossistema de linguagens de forma pictórica: pensemos na paisagem como o espaço de problemas de programação e a vegetação dominante para uma determinada área como a linguagem preferida para aquela programa. A mudança climática é a ideia de que novos hardwares ou novas influências de programação (por exemplo,  "por que eu não posso programar de uma maneira parecida com SQL?") fazem com que diferentes linguagens se tornem a escolha para novos projetos, assim como o aumento das temperaturas regionais faz com que uvas prosperem em latitudes mais altas. Mas há uma histerese - muitos agricultores antigos cultinuam a cultivas as culturas tradicionais. Em resumo, novas linguagens estão surgindo e se tornando cada vez mais populares porque se adaptaram rapidamente à mudança climática.

![[Capítulo 1 - Java 8, 9, 10, and 11..png]]

O principal benefício das adições do Java 8 para um programador é que elas fornecem mais ferramentas e conceitos de programação para resolver problemas novos ou existentes de forma mais rápida ou, mais importante, de maneira mais concisa e mais fácil de manter. Embora os conceitos sejam novos para o Java, eles se mostraram poderosos em linguagens voltadas para nichos de pesquisa. Vamos destacar e desenvolver as ideias por trás de três conceitos de programação que impulsionaram o desenvolvimento dos recursos do Java 8 para explorar o paralelismo e escrever códigos mais concisos em geral. 

---
**Outro fator de mudança climática para o Java envolve como os sistemas grandes são projetados**
Hoje em dia, é comum que um grande sistema incorpore grandes subsistemas de componentes de outros lugares, e talvez esses componentes sejam construídos em cima de outros componentes de outros fornecedores. Pior ainda, esses componentes e suas interfaces também tendem a evoluir. O Java 8 e o Java 9 abordaram esses aspectos fornecendo métodos #default e módulos para facilitar esse estilo de design.

---
### 1.2.2 Stream processing
O primeiro conceito de programação é o processamento de #streams. Para fins introdutórios, <span style="background:#d4b106">uma stream é uma sequência de itens de dados que são conceitualmente produzidos um de cada vez</span>. 
Um programa pode ler itens de um stream de entrada, um por vez, e igualmente escrever itens em uma stream de saída. A stream de saída de um programa pode ser a stream de entrada de outro.

Um exemplo prático é no #Unix ou #Linux, onde muitos programas operam lendo dados da entrada padrão (stind no Unix e C, System.in no Java), processando-os e, em seguida, escrevendo seus resultados na saída padrão (stdout no Unix eC, System.out no Java). 

Primeiro, um pouco de contexto: o comando Unix #cat cria uma stream concatenando dois arquivos, tr traduz os caracteres em uma stream, #sort ordena linhas em uma stream, e #tail-3 retorna as últimas três linhas de uma stream.  A linha de comando do Unix permite que tais programas sejam encadeados por meio de pipes |, formando exemplos como:
```bash
cat file1 file2 | tr "[A-Z]" "[a-z]" | sort | tail -3
```
Isso (suponde que os arquivos file1 e file2 contenham uma palavra por linha) imprime as três palavras dos arquivos que aparecem mais recentemente na ordem do dicionário, depois de serem primeiramente convertidas para minúsculas. Dizemos que o comando #sort recebe uma stream de linhas como entradas e gera outra stream de linhas como saída (a qual está ordenada). Vale ressaltar que, no Unix, esses comandos (cat, tr, sort e tail) são executados de forma concorrente, permitindo que o sort comece a processar as primeiras linhas antes que o cat e o tr termine. 

Uma analogia mais mecânica seria uma linha de montagem de carros, onde uma stream de carros é enfileirada entre estações de processamento que cada uma pega um carro, modifica-o e passa para a próxima estação para mais modificações. O processamento nas estações separadas é tipicamente concorrente, embora a linha de montagem seja fisicamente uma sequência. 
![[Capítulo 1.png]]

O Java 8 adiciona uma API de Streams em #java-util-stream baseada nessa ideial **Stream< T >** é uma sequência de itens do tipo T (generic). <span style="background:#d4b106">Podemos pensar nela como um</span> #iterador sofisticado, por enquanto. A API Streams possui muitos métodos que podem ser encadeados para formar um #pipeline complexo, assim como os comandos do Unix foram encadeados no exemplo anterior.

A principal motivação para isso é que podemos programar no Java 8 em um nível mais alto de abstração,  estruturando o pensamento de transformar uma stream de "isso" em uma stream de "aquilo" (semelhante ao que fazemos ao escrever consultas de banco de dados), **em vez de tratar um item por vez**. Outra vantagem é que o Java 8 pode executar seu pipeline de operações de Stream em vários núcleos de CPU, em partes distintas da entrada - isto é paralelismo quase de graça, em vez de um trabalho árduo usando Threads. 

### 1.2.3 Passing code to methods with behavior parameterization
O segundo conceito de programação adicionado ao Java 8 é a capacidade de passar um trecho de código para uma API. Isso pode parecer bastante abstrato. No exemplo do Unix, podemos querer informar ao comando Sort para usar uma ordenação personalidade. Embora o comando sort suporte parâmetros de linha de comando para realizar diversos tipos de ordenação predefinidos, como a ordem inversa, esses parâmetros são limitados.

Por exemplo, vamos supor que tenhamos uma coleção de IDs de faturas com um formato semelhante a 2013UK0001, 2014US0002 e assim por diante. Os primeiros quatro dígitos representam o ano, o código do país e o quatro últimos dígitos representam o ID de um cliente. Podemos querer ordenar esses IDs de fatura por ano, talvez usando o ID do cliente ou até o código do país. O queremos a capacidade de informar ao comando sort que ele deve aceitar como argumento uma ordenação definida pelo usuário: <span style="background:#d4b106">um trecho de código separado passado para o comando</span> #sort. 

Agora, como um paralelo direto em Java, queremos informar a um método srot para comparar usando uma ordem personalizada. Podemos escrever um método #compareUsingCustomerId para comparar dois IDs de faturas, mas, **antes do Java 8, não seria possível passar esse método para outro método**! Poderíamos criar um objeto Comparator para passar para o método sort, mas isso seria verboso e dificultaria a ideia de simplesmente reutilizar um comportamento existente. **O Java 8 adiciona a capacidade de passar métodos (seu código) como argumentos para outros métodos**. Também nos referimos a isso conceitualmente como **parametrização de comportamento**. Por que isso é importante? A API Streams é construída com base na ideia de passar código para parametrizar o comportamento de suas operações, assim como passamos compareUsingCustomerId para parametrizar o comportamento de sort.
![[Capítulo 1-1.png]]


### 1.2.4 Parallelism and shared mutable data
O terceiro conceito de programação é mais implícito e surge da frase "paralelismo quase de graça" na nossa discussão anterior sobre processamento de streams. O que você precisa abrir mão? Podemos fornecer um comportamento que seja seguro para ser executado simultaneamente em diferentes partes da entrada. Normalmente, isso significa escrever código que não acesse dados mutáveis.
O paralelismo mencionado antes só surge se supusermos que várias cópias do seu código possam funcionar independentemente. <span style="background:#d4b106">Se houver uma variável ou objeto compartilhado</span>, e ele for escrito, as coisas deixam de funcionar. E se dois processos quiserem modificar a variável compartilhada ao mesmo tempo? 

As streams do Java 8 exploram o paralelismo mais facilmente do que a API de Threads existente do Java, então, embora seja possível usar #synchronized para quebrar a regra de "dados mutáveis compartilhados", isso vai contra o sistema, pois está abusando de uma abstração otimizada em torno dessa regra. Usar #synchronized em múltiplos núcleos de processamento é muitas vezes muito mais caro do que podemos imaginar, pois a sincronização força o código a ser executado sequencialmente, o que vai contra o objeto do paralelismo.

Para que o código funcione bem em paralelo (com múltiplos processadores), ele não deve alterar dados que possam ser acessados simultaneamente por diferentes partes do programa. Se duas partes do código tentarem modificar a mesma variável ao mesmo tempo, isso pode causar problemas. O código deve ser escrito de maneira que ele não dependa de dados que possam ser alterados enquanto estão sendo processados por várias threads. <span style="background:#d4b106">No paradigma de programação imperativa, normalmente escrevemos um programa em termos de uma sequência de instruções que mutam o estado</span>. 

### 1.2.5 Java needs to evolve
A introdução dos #generics e o uso de List< String > em vez de apenas List inicialmente pode ter sido irritando, mas agora estamos familiarizado com esse estilo e os benefícios que ele traz (capturando mais erros em tempo de compilação e tornando o código mais fácil de ler, pois agora sabemos o que algo é, como uma lista de...)

Outras mudanças tornaram as coisas comuns mais fáceis de expressar (por exemplo, usando um loop for-each em vez de expor o uso repetitivo de um #iterator). As principais mudanças no Java 8 refletem uma mudança da orientação a objetos clássica, que frequentemente foca na mutação de valores existentes, para o estilo de programação funcional,. no qual desejamos fazer em termos amplos..

<span style="background:#d4b106">A programação orientada a objetos clássica e a programação funcional, como extremos, podem parecer em conflito. Mas a ideia é obter o melhor de ambos os paradigmas de programação, para que tenhamos uma chance maior de ter a ferramenta certa para o trabalho. </span>

## 1.3 Functions in Java
A palavra função nas linguagens de programação é comumente usada como sinônimo de método, particularmente um método #estático; isso é além do seu uso como função matemática, uma que não possui efeitos colaterais. 

O Java 8 **adiciona funções como novas formas de valor**. Elas facilitam o uso de streams, cobertos na seção 1.4, que o Java 8 fornece para explorar a programação paralela em processadores multicore. 

Pense sobre os possíveis valores manipulados por programas Java. Primeiramente, existem valores primitivos, como 42 (do tipo int) e 3.14 (do tipo double). Em segundo lugar, os valores podem ser objetos (mais estritamente, referências a objetos). A única maneira de obter um desses valores é usando o #new, talvez por meio de um factory method ou uma função de biblioteca; referências de objetos apontam para instâncias de uma classe. Exemplos incluem "abc" (do tipo String), new Integer(111) (do tipo Integer) e o resultado new HashMap< Integer, String>(100) ao chamar explicitamente o construtor de HashMap. <span style="background:#d4b106">Qual o problema nessa abordagem?</span>

Para ajudar a responder essa pergunta, vamos observar que o objetivo principal de uma linguagem de programação é manipular valores, que, seguindo a tradição das linguagens de programação históricas, são chamadas de valores de **primeira-classe ou cidadão**. Outras estruturas em nossas linguagens de programação, que talvez nos ajudem a expressar a estrutura dos valores, mas que não podem ser passadas durante a execução do programa, são chamados de **cidadãos de segunda-classe**. Métodos são bons quando usados para definir classes, que, por sua vez, podem ser instanciadas para produzir valores, mas nem métodos nem classes são valores em si. Isso importa? Sim, na verdade, **ser capaz de passar métodos durante a execução e**, assim, torná-los cidadãos de primeira-classe é útil na programação, então os projetistas do Java 8 adicionaram a capacidade de expressar isso diretamente no Java. Logo, a partir do Java 8, podemos tratar métodos como dados, facilitando a programação, especialmente ao trabalhar com streams e outras funcionalidades que dependem de comportamento programático parametrizado.

## 1.3.1 Methods and lambdas as first-class citizens
Experimentos em outras linguagens, como Scala e Groovy, determinaram que permitir que conceitos como métodos sejam usados como valores de primeira-classe tornou a programação mais fácil, adicionando mais ferramentas ao conjunto disponível para os programadores. **E, uma vez que os programadores se tornam familiarizados com uma funcionalidade poderosa, eles ficam relutantes em usar linguagens sem elas!** Os designers do Java 8 decidiram permitir que os métodos sejam valores – para tornar a programação mais fácil. Além disso, o recurso do Java 8 de métodos como valores forma a base de várias outras funcionalidades do Java 8 (como Streams).

A primeira nova funcionalidade do Java 8 que apresentamos é a de **referência de métodos**. Suponhamos que queiramos filtrar todos os arquivos ocultos em um diretório. Precisamos começar escrevendo um método que, dado um objeto File, diria se ele é oculto. Felizmente, existe um método na classe #File chamado #isHidden. Ele pode ser visto como uma função que recebe um File e retorna um #boolean. Mas, para usá-lo como filtro, precisamos envolvê-lo em um objeto #FileFilter que então passaremos ao método #listFiles da classe #File:
```java
File[] hiddenFiles = new File(".").listFiles(
	new FileFilter() {
		public boolean accept(File file) {
			return file.isHiden();	
		}
});
```
Que horrível! Isso é péssimo. Embora sejam apenas três linhas significativas, são três linhas opacas. Já temos o métodos isHidden disponível, por que temos que envolvê-lo em uma classe FileFilter verbosa e depois instanciá-la? Porque era assim que tínhamos que fazer antes do Java 8. 
Agora, podemos reescrever esse código da seguinte forma:
```java
File[] hiddenFile = new File(".").listFile(File::isHidden);
```

Já temos uma função isHidden disponível, então passamos ela para o método listFiles usando a sintaxe de referência de método do Java 8 :: (o que significa "use este método como um valor"); O exemplo ilustra como o Java 8 facilita o uso de métodos como valores por meio de **referências de métodos**. 

Métodos não são mais valores de segunda classe. Análogo ao uso de uma referência de objeto quando passamos um objeto (e as referências de objetos são criadas com new), no Java 8, quando escrevemos `File::isHidden`, criamos uma referência de método, que pode ser passada de forma semelhante. Esse conceito é discutido em detalhes no capítulo 3. Dado que os métodos contém código (o corpo executável de um método), usar referências de método permite passar código como mostrado na figura 1.3. Portanto, os métodos sendo tratados como objetos, podemos passá-los como parâmetros. 

**Lambdas: anonymous functions**
Além de permitir que métodos (nomes de métodos) sejam valores de primeira classe, o Java 8 permite uma ideia mais rica de funções como valores, incluindo lambdas (ou funções anônimas). Por exemplo, podemos escrever `(int x) -> x + 1` para significar "a função que, quando chamada com o argumento x, retorna o valor x + 1". Mas para que isso é necessário? Não bata criarmos um método add1 dentro de uma classe MyMathUtils e então escrever MyMathsUtils::add1! Sim, mas a nova sintaxe de lambda é mais concisa para casos em que não temos um método e uma classe conveniente disponível. Programas escritos com esses conceitos são chamados de programas escritos no estilo de programação funcional; O estilo de programação funcional é aquele em que funções podem ser tratados como valores de primeira classe, ou seja, **elas podem ser passadas como parâmetros, retornadas de métodos e manipuladas diretamente, sem precisar de declarações formais de métodos dentro de classes.**

### 1.3.2 Passing code: an example
Vamos ver um exemplo de como isso ajuda a escrever programas (discutidos em mais detalhes no capítulo 2). 

Suponhamos que tenhamos uma classe **Apple** com um método getColor e uma variável inventory que contém uma lista de Apples; então podemos querer selecionar todas as maçãs verdes (aqui usando um tipo Color enum que incluir os valores GREEN e RED) e retorná-las em uma lista. A palavra #filter é comumente usada para expressar esse conceitos. Antes do Java 8, escreveríamos um método filterGreenApples:
```java
public static List<Apple> filterGreenApples(List<Apple> inventory) {
	List<Apple> result = new ArrayList<>();
	for (Apple apple : inventory) {
		if (GREEN.equals(apple.getColor())) {
			result.add(apple);
		}
	}
	return result;
}
```

Mas em seguida, alguém pode querer a lista de maçãs pesadas (digamos, acima de 150g), e então, escreveríamos o seguinte método:
```java
public static List<Apple> filterHeavyApples(List<Apple> inventory) {
	List<Apple> result = new ArrayList<>();
	for(Apple apple : inventory){
		if(apple.getWeight() > 150) {
			result.add(apple);
		}
	}
	return result;
}
```

Todos nós conhecemos os perigos de "copiar e colar" na engenharia de software (atualizações e correções de bug feitas em uma variante, mas não em outra), e, veja bem, esses dois métodos diferem apenas em uma linha: a condição destacada dentro da construção #if. Se a diferença entre as chamadas dos dois métodos estivesse na faixa de peso aceitável, podemos passar os limites inferior e superior como argumentos para o método #filter talvez, (150, 1000) para selecionar maçãs pesadass (acima de 150g) ou (0, 80) para selecionar maçãs leves (abaixo de 80g). 

Mas como mencionamos anteriormente, o Java 8 torna possível passar o código da condição como um argumento, evitando a duplicação de código no método #filter:
```java
public static boolean isGreenApple(Apple apple) {
	return GREEN.equals(apple.getColor());
}

public static boolean isHeavyApple(Apple apple) {
	return apple.getWeight() > 150;
}

public interface Predicate<T> {
	boolean test(T t);
}

static List<Apple> filterApples(List<Apple> inventory,
								Predicate<Apple> p) {								
	List<Apple> result = new ArrayList<>();
	for (Apple apple : inventory) {
		if (p.test(apple)) {
			result.add(apple);
		}
	}						
	return result;	
}
```

Logo, podemos usar isso para chamar os métodos
```java
filterApples(inventory, Apple::isGreenApple);

filterApples(inventory, Apple::isGreenApple);
```


---
**What's a Predicate?**
Um #Predicated é uma interface funcional especializada que recebe um argumento e retorna um valor booleano, ideal para expressar condições. Ele é mais efeiciente que (Function<Apple, Boolean) porque evita a conversão de valores primitivos (boolean) para seus equivalentes de objeto (Boolean), o que impactar a performance. 

---
### 1.3.3 From passing methods to lambdas
