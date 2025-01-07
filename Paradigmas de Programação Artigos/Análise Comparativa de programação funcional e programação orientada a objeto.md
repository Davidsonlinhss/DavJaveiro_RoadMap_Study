## Abstract
[[Comparative_analysis_of_functional_and_object-oriented_programming.pdf]]
A escolha da primeira linguagem de programação e do paradigma correspondente é uma parte importante do processo de desenvolvimento de software. Conhecer as vantagens e limitações de cada paradigma de programação é crucial para uma implementação de software bem-sucedida. Neste artigo, foi realizada uma comparação empírica entre linguagens de programação funcionais e orientada a objetos, utilizando exemplos equivalentes em C#, F#, Haskell e Java. Três algoritmos foram implementados: o algoritmo para resolver o problema das N-rainhas, o algoritmo para gerar o n-ésimo número primo truncável à esquerda e o algoritmo de ordenação por intercalação ( #merge-sort) nessas linguagens.

Foi apresentada uma visão geral da eficiência das linguagens, medida por dois parâmetros básicos: **número de linhas de código** e **velocidade de execução** dos programas. Além disso, o uso de recursos do sistema foi monitorado durante a execução. Experimentos limitados mostraram que a linguagem Java foi mais rápida que as outras três linguagens testadas. Surpreendentemente, Java apresentou alto desempenho em problemas que geralmente são mais adequados para linguagens funcionais. Haskell foi menos intensivo em memória (até duas vezes menos que Java) e teve tempos de execução semelhantes, enquanto as linguagens da plataforma .NET foram até quatro vezes mais lentas em comparação com Java. As linguagens orientadas a objetos  C# e Java exigiram significativamente mais linhas de código para todos os três algoritmos em comparação com a linguagem funcional Haskell e a híbrida F#.

## Introdução
Como em muitas questões da história das linguagens de programação, há um debate constante sobre qual paradigma de programação é melhor e quando um paradigma específico deve ser usado. Até mesmo a classificação dos paradigmas em si frequentemente gera discussões. No entanto, os paradigmas de programação são frequentemente classificados em seis paradigmas fundamentais: #imperativo, #declarativo, #orientado-a-objetos, #funcional, #simbólico e #lógico. Os paradigmas declarativos e simbólico às vezes são excluídos, restando apenas quatros paradigmas fundamentais. 

É importante notar que **a maioria das linguagens de programação amplamente utilizadas atualmente suporta múltiplos paradigmas** (linguagens de programação #multiparadigma). Por exemplo, o C++ possui suporte nativo para sete paradigmas de programação, com bibliotecas adicionais que estendem esse suporte para mais de seis paradigmas. 

Neste artigo, focamos em dois paradigmas fundamentais de programação: o orientado a objetos e o funcional. O **paradigma orientado a objetos** é amplamente utilizado na **indústria atualmente**, enquanto o **paradigma funcional** é **empregado principalmente pela comunidade científica**. 

No entanto, há uma tendência notável de linguagens de programação orientada a objetos introduzirem alguns princípios básicos da programação funcional em suas novas versões. Em 2008, expressões lambda foram introduzidas na versão 3.5 do .NET Framework, principalmente para dar suporte ao LINQ (Language Integrated Query). #Expressões-lambda também foram adicionadas no Java 8, permitindo que os desenvolvedores **expressem instâncias** de classes com um único método de forma mais compacta. 

A introdução de princípios de programação funcional em linguagens orientadas a objetos demonstra que os paradigmas funcional e orientados a objetos **não são mutuamente exclusivos** e que a combinação desses dois princípios pode ser eficaz.

## 2. Functional VS Object-Oriented Programming
Apesar do constante debate sobre a escolha do paradigma de programação, há um raciocínio por trás dessa decisão para resolver problemas. Linguagens orientadas a objetos são adequadas quando há um conjunto fixo de operações sobre os elementos, e, à medida que o código evolui, principalmente novos elementos (como classes) são adicionados, enquanto as classes existentes permanecem inalteradas. Já as linguagens funcionais são ideias quando há um conjunto fixo de elementos, e, à medida que o código evolui, novas operações são adicionadas aos elementos existentes (como novas funções que trabalham com os mesmos tipos de dados). Esse conceito é conhecido como o **problema da expressão** (expression problem), termo cunhado por #Philip-Wadler. 

É importante destacar que é possível programar de forma funcional em linguagens orientadas a objetos. Para isso, todos os objetos (dados) do programa devem ter estado constante, e todos os métodos e operações devem depender apenas de seus argumentos e do contexto disponível no momento da definição.

# A. Object-Oriented Programming Paradigm
O paradigma orientado a objetos é baseado no conceito de objetos - estrutura de dados que contêm informações conhecidas como atributos e código correspondente conhecido como métodos. <span style="background:#d4b106">A ideia básica das linguagens orientadas a objetos é combinar os dados e as funções ou método que operam sobre esses dados em um único componente</span>. 

A linguagem de programação orientada a objetos implementa três princípios básicos da abordagem orientada a objetos:
- #Encapsulamento - limitar o acesso ao objeto a partir de outras partes do código;
- #Herança - usar um objeto ou classe existente como base para criar novos;
- #Polimorfismo - fornecer uma única interface para entidades de diferentes tipos (**o objeto pode assumir várias formas durante a execução do programa**). 

A programação orientada a objetos não se preocupa principalmente com os detalhes da operação do programa. Em vez disso, ela trata da organização geral do programa.

## B. Paradigma de Programação Funcional
A programação funcional, em sua essência, é baseada no aparato matemático. A base para todas as linguagens de programação funcional é o cálculo lambda. O cálculo lambda possui três regras básicas:
![[Pasted image 20250107133807.png]]
Pode-se argumentar que as linguagens de programação funcional implementam apenas o cálculo #lambda, adicionando constantes e novos tipos de dados. O cálculo lambda trata as funções como expressões que são gradualmente transformadas até que a solução final seja encontrada - a função, na verdade, define o algoritmo. O cálculo lambda é amplamente utilizado em matemática, filosofia, linguística e ciência da computação.

Em contraste com a programação imperativa, a ordem de execução nas linguagens de programação funcional não é definida pelo programador. O programador apenas informa o que precisa ser feito, sem definir explicitamente como deve ser feito. O compilador/interpretor determinará a ordem de execução. As funções nas linguagens de programação funcional são avaliadas apenas quando o resultado que elas retornam é necessários. Essa abordagem é conhecida como **avaliação preguiçosa** #lazy-loading 

---
**Nota**
Na #programação-imperativa (a programação imperativa refere-se a um **estilo de programação**), o programador define a **ordem de execução** das instruções, ou seja, ele diz como as coisas devem ser feitas, uma por uma, de forma **sequencial**.

Já na #programação-funcional, o programador diz o que precisa ser feito, mas não precisa se preocupar **com a ordem**. O compilador ou interpretador decide automaticamente como as coisas serão feitas. 

Além disso, **avaliação preguiçosa** ( #lazy-evaluation) significa que uma função só será executada quando o resultado for necessário, e não antes. Ou seja, o programa não vai calcular tudo de um vez, mas vai "esperar" até precisar de algum valor para então calcular e usá-lo.

---
## Differences Between Functional and Object-Oriented Programming
Uma das diferenças fundamentais entre os dois paradigmas é a **inter-relação entre dados e operações**. 
A regra básica da programação orientada a objetos é que **os dados e as operações que os afetam estão fortemente ligados** (dados + operações) fortemente ligados. **Objetos possuem seus próprios dados e controlam todas as ações (funções e métodos) que os modificam**, enquanto ocultam esses dados de outros objetos, usando a interface. Isso implica que o modelo de abstração na programação orientada a objetos é o próprio dado. 

Na **programação funcional**, os dados estão fortemente relacionados às funções. É possível implementar diferentes operações sobre a mesma estrutura de dados. No entanto, a abstração básica é uma função, e não a estrutura de dados. As funções ocultam sua própria implementação.

A #recursão é utilizada em ambos os paradigmas de programação, mas programadores orientados a objetos tendem a evitá-la. Embora a recursão reduza o comprimento do código, ela pode ter um efeito negativo na **legibilidade** do código. Além disso, a abordagem **iterativa** costuma ser mais eficiente em termos de **desempenho** em comparação a abordagem **recursiva**. Na programação orientada a objetos, sempre existe um equivalente iterativo para qualquer algoritmo recursivo. Por outro lado, a recursão é uma parte essencial da programação #funcional, uma vez que ela é baseada no cálculo lambda, onde "tudo é uma função". Os blocos essenciais dos programas orientados a objetos são **declarações**, enquanto os programas funcionais são compostos principalmente por **expressões**.

Outra diferença fundamental entre os dois paradigmas de programação é o **estado do programa**. A principal limitação do paradigma funcional e das linguagens de programação funcional puras é a **ausência de estado do programa**. Linguagens de programação funcional puras são estritamente **sem estado** e trabalham com dados **imutáveis**. Um programa funcional pode ser visto como independente da máquina, significando que não há #estado, pois não temos a noção de memória. Em contrapartida, os programas orientados a objetos trabalham com dados **mutáveis**, armazenando os dados em variáveis e definindo declarações que podem alterar o estado do programa. Como resultado da abordagem sem estado na programação funcional, a execução do programa não tem **efeitos colaterais**. Isso implica que a ordem de execução das declarações não é importante. O programa, para entrada idênticas, retornará saídas idênticas, independente da ordem da execução das declarações. Isso é conhecido como **transparência referencial**, sendo o oposto disso a **opacidade referencial**. A opacidade referencial é um efeito colateral de trabalhar com dados mutáveis. Eliminar os efeitos colaterais pode tornar mais simples tanto entender quanto prever o comportamento do programa. 

## III. Programming Languages in Comparative Analysis
Quatro linguagens de programação foram escolhidas para esta análise comparativa: #Haskell, #C e #F e #Java. Haskell é uma linguagem de programação puramente funcional, C# e Java são linguagens orientadas a objetos com suporte embutido para paradigmas de programação adicionais, enquanto F é um híbrido de programação funcional e orientada a objetos. A seguir, são apresentados algumas das características mais importantes das quatro linguagens de programação utilizadas na análise.
![[Análise Comparativa de programação funcional e programação orientada a objeto.png]]
Todas as linguagens de programação escolhidas são **estaticamente tipadas**,  oque significa que todos os tipos de dados são conhecidos no momento da **compilação**, o que resulta em um código **seguro em termos de tipo**. No entanto, enquanto Haskell e F# são verificadas estaticamente quanto aos tipos, o compilador infere automaticamente um tipo preciso para todos os valores. Em Java as maneiras de evadir o sistema de tipos estáticos são controlados pelo sistema de tipos da máquina virtual Java. O C# é semelhante ao Java nesse aspecto, mas permite desabilitar a verificação de tipos dinâmicos ao colocar explicitamente trechos de código em um contexto inseguro. F# e C# são baseados na plataforma .NET. Todas as linguagens .NET são compiladas para Commom INtermidate Language (CIL), o que significa que, teoricamente, programas que implementam a mesma funcionalidade escritos em F# e C# serão traduzidos para o mesmo código CIL.

![[Análise Comparativa de programação funcional e programação orientada a objeto-1.png]]

## Haskell 
Haskell é uma linguagem de programação puramente funcional, sem características de linguagens de programação #imperativa. É uma linguagem de alta ordem, onde funções podem receber outras funções como argumentos e retornar funções como resultado. Usando esse recurso, é possível definir uma nova função como a composição de duas funções existentes.

As expressões condicionais podem ser aninhadas e devem incluir uma ramificação else. Em Haskell, não existem loops; a #recursão é o equivalente da programação para essa funcionalidade.

Funções podem ser definidas usando **expressões lambda**, que criam uma estrutura para cada argumento e corpo da função. Essa estrutura calcula o resultado com base nos argumentos fornecidos, mas não atribui um nome à função. Essa estrutura calcula o resultado com base nos argumentos fornecidos, mas não atribui um nome à função. Em outras palavras, **expressões lambda são funções sem nome** em Haskell.

Os algoritmos em Haskell são compostos por **casos** e **casos recursivos**. Determinar os casos base é essencial para produzir o resultado correto e evitar loops infinitos.

Por exemplo, para calcular o fatorial de um número inteiro fornecido como entrada, o **caso base** é o número 0, no qual o resultado é gerado imediatamente. O **caso recursivo** é utilizado para todos os outros valores de entrada. #caso-base, #caso-recursivo.

## F#
F# é uma linguagem de programação que suporta tanto os paradigmas de programação **orientada a objetos** como **funcional**.
A abordagem orientada a objetos permite ao programador escrever código usando classes e objetos. Já com a abordagem funcional, o programador pode especificar **o que precisa ser feito** sem definir explicitamente **como isso deve ser feito.**

F# utiliza técnicas de avaliação **ávida #greedy-evaluation** o que significa que as expressões são avaliadas assim que são vinculadas a uma variável, ao invés de esperar que seu resultado seja necessário, uma característica distintiva das linguagens de programação imperativas é o uso da **avaliação ávida**, onde uma expressão é avaliada assim que é vinculada a uma variável.

Na programação orientada a objetos, todas as variáveis são mutáveis, a menos que sejam declaradas explicitamente como **estáticas**. Já no F#, todas as variáveis são #imutáveis ( #estáticas), a menos que sejam declaradas explicitamente como #mutáveis.

O F# não suporta valores nulos, o que elimina a possibilidade de exceções do tipo **NullReferece**.

Outra diferença importante entra a maioria das linguagens orientadas a objetos e o F# é que o F# não exige que o programador declare explicitamente os tipos de dados. O tipo é determinado com base no valor fornecido". No C#, isso pode ser alcançado usando a palavra-chave var, que oferece funcionalidade semelhante.

## C. Java
Java é uma linguagem de programação de uso geral. Os aplicativos Java são compilados para bytecode, que pode ser executado na **máquina virtual java (JVM)**. Uma das principais vantagens do Java é que o mesmo código pode ser executado em todas as plataformas que suportam Java, sem a necessidade de recompilar o código.

A existência do bytecode teve um impacto significativo no desempenho até a **compilação just-in-time (JIT)** fosse implantada, o que resolveu parcialmente o problema de desempenho. A compilação #JIT  #just-in-time é uma forma de compilação dinâmica que permite que o código seja compilado durante a execução, em vez de ser compilado antes da execução. No entanto, isso pode causar um pequeno atraso ao iniciar o aplicativo.

O Java 8 introduziu as **expressões lambda**, um conceito chave na programação funcional. Uma expressão lambda no Java é um bloco de código que pode ser passado a executado posteriormente várias vezes (execução adiada). As expressões lambda no Java são especialmente úteis em aplicativos de interface gráfica (GUI).

De acordo com o índice TIOBE [[https://www.google.com/search?q=TIOBE+java&sca_esv=58d0df3a52d22f31&sxsrf=ADLYWIIc9aGF6ouL_N2yyTQexKIxx8IS2Q%3A1736276279170&ei=N3l9Z6iNCr-L5OUPqKXAmQk&ved=0ahUKEwjohOiWpeSKAxW_BbkGHagSMJMQ4dUDCBA&uact=5&oq=TIOBE+java&gs_lp=Egxnd3Mtd2l6LXNlcnAiClRJT0JFIGphdmEyCBAAGIAEGMsBMggQABgWGAoYHjIGEAAYFhgeMgYQABgWGB4yCBAAGKIEGIkFMgUQABjvBTIIEAAYgAQYogQyCBAAGIAEGKIESKEHUDZYpQZwAXgAkAEAmAGMAaABkgWqAQMwLjW4AQPIAQD4AQGYAgWgAq8EwgIKEAAYsAMY1gQYR8ICDRAAGIAEGLADGEMYigXCAggQABiABBixA8ICChAAGIAEGBQYhwLCAgUQABiABJgDAIgGAZAGDJIHAzEuNKAH5Ck&sclient=gws-wiz-serp | TIOBE Java]], o Java é a linguagem de programação mais popular atualmente, o que torna a comparação com outras linguagens importante para destacar seus pontos fortes.

## D. C#
C# é uma linguagem de uso geral orientada a objetos. C# suporta mais tipos de dados do que Java e implementa a maioria dos recursos que o Java possui, sendo a principal exceção as expressões verificadas. O C# 3.0 adicionou suporte para expressões lambda e árvore de expressão como um passo incremental para expandir gradualmente o suporte à programação funcional no C#.

Uma expressão lambda no C# é uma função anônima que pode ser usada para criar delegados ou tipos de árvores de expressão. **Árvores de expressão** são um código em uma estrutura de dados em forma de árvore, onde cada nó é uma expressão. Árvores de expressão também são usadas no runtime de linguagem dinâmica DLR para fornecer interoperabilidade entre linguagens dinâmicas e o .NET Framework.

## IV. Owerview of Execution Environment and Definition of Problems for ANalysis
Para o propósito da análise comparativa, três diferentes algoritmos foram implementados em todas as quatro linguagens escolhidas. Os algoritmos implementados são:
- **Algoritmo para resolver o problema das N rainhas**: as rainhas de xadrez podem atacar horizontalmente, verticalmente e diagonalmente. O problema das N-rainhas pergunta: "Como as N rainhas podem ser posicionadas em um tabuleiro NxN de xadrez de modo que nenhuma delas ataque a outra?". A complexidade do problema das N-rainhas é aproximadamente exponencial. A abordagem ingênua para resolver o problema consiste em gerar todas as configurações possíveis das rainhas no tabuleiro e encontrar aquelas que atendem ao requisito.**Algoritmo de backtracking (retrocesso)**: reduz significativamente o número de variações verificadas, eliminando variações em checá-las explicitamente, pois elas não farão parte da solução. A abordagem de backtracking para resolver o problema foi usada para demonstrar as limitações da programação funcional, uma vez que essa abordagem exige dados mutáveis para funcionar. No entanto, linguagem de programação puramente funcionais trabalham apenas com dados imutáveis. O algoritmo foi usado para resolver o problema das N rainhas para N = 12, 13 E 14.

- **Algoritmo para gerar o n-ésimo número primo truncável pela esquerda**: um primo truncável pela esquerda é um número primo que, em uma base específica, não contém zeros e, ao remover sucessivamente o dígito mais à esquerda, todos os números resultantes também são primos. Em base 10, existem apenas 4260 números primos truncáveis pela esquerda conhecidos, sendo o maior deles atualmente o número de 24 dígitos: 357 686 312 646 216 457 629 13. O desempenho do algoritmo foi medido durante a geração do 1300 e 1700 e 1800 número primo truncável pela esquerda. 
- **Merge Sort** é um algoritmo de ordenação baseado no paradigma **dividir para conquistar**. Ele funciona dividindo uma lista desordenada em sublistas até que cada sublista contenha apenas um elemento. Em seguida, inicia-se a fase de mesclagem #merge. Nessa etapa, as sublistas são combinadas repetidamente para formar novas sublistas ordenadas, até que reste apenas uma única sublista, que é a lista ordenada. Arquivos de texto contendo 10.000, 30.000, 50.000 e 100.000 números aleatórios foram gerados. O fluxo do programa consistiu em: 1) Importar o arquivo de texto; 2) Ordenar os números contidos nele utilizando o algoritmo Merge Sort.
A abordagem orientada a objetos foi utilizada para implementar os algoritmos em C# e Java, enquanto a abordagem funcional foi empregada para implementar os algoritmos em Hasckell, e uma combinação das abordagens funcional e orientada a objetos foi usada na implementação dos algoritmos em F#.

Os algoritmos **não foram implementados para oferecer suporte a qualquer tipo de paralelismo no código**. É importante destacar que foi utilizado o Glasgow Haskell Compilar #GHC. O #GHC é escrito em Haskell, com base em uma técnica conhecida como #bootstrapping. O sistema de runtime do Haskell (usado para executar os programas) é escrito em C.

## A. *N Queens Alghorithm with Backtracking*
Haskell teve o menor número de linhas de código, enquanto o programa em C# teve o maior. O programa #Java teve apenas duas linhas a menos que a versão em C#. O código em C# teve o dobro de linhas em comparação ao F#, mas o F# apresentou quase três vezes mais linhas de código em relação ao Haskell.
Quanto ao tempo de execução dos programas, o programa #Java foi notavelmente mais rápido que todos os outros. O C# foi, em média, quatro vezes mais lento em comparação ao Java. Já os programas em Haskell e F# foram significativamente mais lentos na geração de resultados em comparação aos programas orientados a objetos em Java e C#. Isso pode ser atribuído à incapacidade de aproveitar plenamente a abordagem #backtracking.

#backtracking é uma técnica de algoritmo utilizada para resolver problemas que envolvem a busca de todas as possíveis soluções dentro de um espaço de busca. Essa técnica funciona explorando caminhos de forma sistemática, voltando (backtracking) para um ponto anterior quando um caminho se mostra infrutífero. A programação funcional, por sua vez, é um paradigma de programação que trata a computação como a avaliação de funções matemáticas e evita estados mutáveis. Funções puras, imutabilidade e recursão são seus pilares.
![[Análise Comparativa de programação funcional e programação orientada a objeto-2.png]]
![[Análise Comparativa de programação funcional e programação orientada a objeto-3.png]]

## B. Generating n-th left trucatable prime
Assim como no algoritmo das N rainhas, o programa em Haskell teve a menos quantidade de linhas de código. No entanto, dessa vez, o F# teve apenas nove linhas a mais que o Haskell. Os programas em Java e C# ficaram quase empatados novamente. Os programas em Java e C# tiveram quase três vezes mais linhas de código em comparação ao Haskell e mais do que o dobro em relação ao F#.
![[Análise Comparativa de programação funcional e programação orientada a objeto-4.png]]
![[Análise Comparativa de programação funcional e programação orientada a objeto-5.png]]

## C. Merge Sort
Haskell também teve o menor número de linhas de código. Java foi o mais rápido, seguido de perto por Haskell. C# ficou em terceiro, e F# foi o mais lento.
O #merge-sort foi implementado de forma recursiva em todas as linguagens, mas o esperado era que as linguagens funcionais, como Haskell, tivessem desempenho superior. Mas o programa em Java foi ligeiramente mais rápido que o do Haskell:
![[Análise Comparativa de programação funcional e programação orientada a objeto-6.png]]
![[Análise Comparativa de programação funcional e programação orientada a objeto-7.png]]

## Discussão
Os programas em Java apresentaram os tempos de execução mais rápidos em todos os três algoritmos analisados, enquanto os programas em Haskell tiverem o menor número de linhas de código. As linguagens da plataforma .NET foram significativamente mais lentas ao resolver os problemas em comparação com Haskell e Java.
Linguagens orientadas a objetos se destacaram ao utilizar o algoritmo de retrocesso ( #backtracking ), aproveitando melhor suas vantagens. O uso do processador foi semelhante entre os programas, com diferença negligível de +-1%. No entanto, os programas puramente funcionais em Haskell consumiram, em média, metade da memória das outras linguagens, devido à abordagem sem estado (uso exclusivo de dados imutáveis), que é menos intensiva em memória.

Embora as linguagens funcionais geralmente demandem menos linhas de código, isso não garante melhor legibilidade, pois depende da implementação e da preferência do programador.
Seria interessante comparar os paradigmas no uso de multithread e concorrência. Em linguagens orientadas a objetos, variáveis mutáveis podem gerar muitos problemas. Já em linguagens puramente funcionais, o uso de dados imutáveis elimina a maioria desses problemas, permitindo ao programador focar menos nos efeitos colaterais da concorrência.

## Conclusão
O paradigma funcional se mostrou uma escolha preferível para implementar os algoritmos descritos, considerando a quantidade de linhas de código, uso de memória e velocidade. Contudo, Java surpreendeu ao ser mais rápido que Haskell. A implementação em C# apresentou resultados esperados, enquanto F# teve um desempenho decepcionante.

Os experimentos reforçam que é difícil determinar qual paradigma é superior, mas indicam que a combinação de paradigmas é a melhor abordagem para aumentar a eficiência de execução, especialmente na engenharia de software. Além disso, a escolha da linguagem de programação também é crucial. **Os resultados apontam Java como a melhor opção entre as linguagens analisadas, o que ajuda a explicar sua popularidade.**

No futuro, será interessante comparar Java e Haskell, identificados como os melhores neste estudo, com outras linguagens que não são puramente funcionais, mas suportam programação funcional (como Swift), considerando limitações de plataformas, como dispositivos móveis.