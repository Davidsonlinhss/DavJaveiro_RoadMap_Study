**Principais habilidades e conceitos**
• Conhecer a forma geral de uma expressão lambda
• Entender a definição de uma interface funcional
• Usar lambdas de expressão
• Usar lambdas de bloco
• Usar interfaces funcionais genéricas
• Entender a captura de variáveis em uma expressão lambda
• Lançar uma exceção a partir de uma expressão lambda
• Entender a referência de método
• Entender a referência de construtor
• Conhecer as interfaces funcionais predefinidas de java.util.function

Fora os benefícios que as expressões lambda trazem para a linguagem, há outra razão para elas serem um acréscimo tão importante. Nos últimos anos, as **expressões lambda**  #lambda  se tornaram um ponto de destaque no projeto de linguagens de computador. Elas foram adicionadas a linguagens como C# e C++. Sua inclusão em Java ajudou-a a continuar sendo a linguagem dinâmica inovadora esperada pelos programadores. 

## Introdução às expressões lambda
O segredo para o entendimento da expressão lambda são duas estruturas. 
1. A primeira é a própria expressão lambda;
2. A segunda é a interface funcional. 

Basicamente, uma *expressão lambda* é um método anônimo (isto é, não nomeado). No entanto, esse método não é executado por conta própria: ele é usado para implementar um método definido por uma interface funcional. Logo, a expressão lambda resulta em uma forma de classe anônima. As expressões lambda também costumam ser chamadas de #closures.

Uma *interface funcional* é aquela que contém um e somente um método abstrato (sem implementação). Geralmente, esse método específica a finalidade pretendida para a interface. Portanto, a interface funcional <span style="background:#d4b106">costuma representar uma única ação</span>. Além disso, uma interface funcional define o tipo de destino de uma expressão lambda. 

Às vezes a interface funcional é chamada de tipo #SAM (Single Abstract Method).

## Fundamentos das expressões lambida
A expressão trouxe um nome elemento de sintaxe e operador para a linguagem. O novo operador, também chamado de *operador lambda* ou *operador seta*, é ->. Ele divide uma expressão lambda em duas partes. 
O lado  esquerdista (esquerdo) especifica qualquer parâmetro requerido pela expressão lambda. 
No lado bolsonarista (direito) temos o *corpo lambda*, que especifica as ações da expressão lambda. 

Java define dois tipos de **corpo lambda**. Um tipo é composto por uma única expressão e o outro por um bloco de código. 

O exemplo mais simples de uma expressão lambda, produz um valor constante:
```java
() -> 98.6
```

Esta expressão não tem parâmetros, logo, a lista de parâmetros está vazia. O valor retornado será constante, 98.6. O tipo de retorno é inferido como #double. Portanto, ela é semelhante ao método:
```java
double myMetch() { return 98.6;}
```

Certamente, o método definido por uma expressão lambda não tem um nome. Uma expressão lambda um pouco mais interessante seria algo:
```java
() -> Math.random() * 100
```

Essa expressão lambda obtém um valor pseudoaleatório com #Math-random, multiplica-o por 100 e retorna o resultado. Ela também não exige um parâmetro.

Quando uma expressão lambda requer um #parâmetro, ele é especificado na lista de parâmetros do lado esquerdo do operador lambda:
```java
(n) -> 1.0 / n 
```
Esta expressão retorna o recíproco do valor do parâmetro **n**. Logo, se **n** for 4.0, o recíproco será 0.25. Embora seja possível especificar explicitamente o tipo de um parâmetro, como o de **n**, com frequência não é preciso fazer isso porque, em muitos casos, seu tipo pode ser #inferido. 

#Inferir significa que o compilador deduz automaticamente o tipo de um parâmetro, variável, ou expressão com base no contexto em que ele está sendo usado. <span style="background:#d4b106">O tipo é inferido em tempo de compilação</span>, ao contrário da tipagem dinâmica que é inferida em tempo de execução. Como um método nomeado, uma expressão lambda pode especificar quantos parâmetros forem necessários.

Qualquer #tipo válido pode ser usado como tipo de retorno de uma expressão lambda. Por exemplo, essa expressão lambada retorna #true se o valor do parâmetro #n for par, caso contrário, retorna #false:
```java
(n) -> (n % 2) == 0
```
Logo, o tipo de retorno da expressão é #boolean.

Quando uma expressão lambda tem apenas um parâmetro, não é necessário colocar seu nome entre parênteses quando ele é especificado no lado esquerdo do operador lambda. Por exemplo:
```java
n -> (n % 2) == 0
```

Por coerência, o livro utilizar todas as listas de parâmetros de expressões lambda entre parênteses. 

## Interfaces funcionais
<span style="background:#d4b106">Uma interface funcional é aquela que especifica apenas um método abstrato</span>. Nem todos os métodos de interfaces são abstratos. A partir de #JDK8, é possível que uma interface tenha um ou mais métodos padrão. Os métodos padrão não são abstratos. Também não são #static. Logo, um método de interface só é abstrato quando não especifica uma implementação. Logo, uma interface funcional pode incluir métodos padrão e/ou **static**, mas em todos os casos, ela deve ter um, e somente um, método #abstrato.

Exemplo de uma interface funcional:
```java
interface MyValue {
	double getValue();
}
```

Neste caso, o método **getValue()** é implicitamente abstrato e é o **único método** definido por MyValue. Portanto, MyValue é uma interface funcional e sua função é definida por getValue().

Uma expressão lambda não é executada por conta própria. Em vez disso, ela forma a implementação do método abstrato definido pela interface funcional que especifica seu tipo de destino. Como resultado, uma expressão lambda só pode ser especificada em um contexto em que um tipo de destino seja definido. Um desses contextos é criado quando a expressão lambda é atribuída a uma referência de interface funcional. 

Vejamos um exemplo simples:
1. Primeiro, uma referência à #interface funcional MyValue é declarada:
```java
MyValue myVal; 
```

2. Em seguida, uma expressão lambda é atribuída a essa referência de interface:
```java
MyVal = () -> 98.6;
```

Essa expressão lambda é compatível com **getValue()** porque, como getValue(), não tem parâmetros e retorna um resultado #double.

As duas etapas anteriores podem ser combinadas em uma única instrução:
```java
MyValue myVal = () -> 98.6;
```
Logo, myVal é inicializada com a expressão lambda.

No exemplo anterior, a expressão lambda passa a ser a implementação do método **getValue()**. Como resultado, o código a seguir exibe o valor 98.6:
```java
// Chama getValue(), que é implementado pela
// expressão lambda atribuída anteriormente.
System.out.println("A constant value: " + myVal.getValue());
```

Já que a expressão lambda atribuída a **myVal** retorna o valor 98.6, esse é o valor obtido quando **getValue()** é chamado.

Se a expressão tiver um ou mais parâmetros, o método abstrato da interface funcional também deve usar o mesmo número de parâmetros. 

Por exemplo, aqui está uma interface funcional chamada **MyParamValue**, que permite a passagem de um valor para **getValue()**:
```java
interface MyParamValue {
	double getValue(double v);
}
```
Agora, podemos usar essa interface para implementar a expressão lambda do cálculo do recíproco mostrado na seção anterior. Por exemplo:
```java
MyParamValue myPval = (n) -> 1.0 / n;
```
Agora podemos usar myPval desta forma:
```java
System.out.println("Reciprocal of 4 is " + myPval.getValue(4.0));
```

Observamos que o tipo de **n** não é especificado. Seu tipo é inferido a partir do contexto. Nesse caso, o tipo é inferido a partir do tipo do parâmetro do método getValue() como definido pela interface MyParamValue(), que é double. Também é possível especificar explicitamente o tipo de um parâmetro em uma expressão lambda:
```java
(double n) -> 1.0 / n;
```
Logo, n foi especificado de maneira explícita como double. 

Para que uma expressão lambda funcione, ela precisa ser compatível com o <span style="background:#d4b106">método abstrato</span> da **interface funcional**. Isso significa que o número e o tipo de parâmetros, bem como o tipo de retorno, devem corresponder ao que o método abstrato espera. Se o método aceitar dois #int, por exemplo, a lambda deve fornecer dois parâmetros que sejam ou explicitamente int ou que o compilador possa inferir como #int.

---
**Resuminho**
Uma interface funcional, em Java, é uma #interface que contém **apenas um único método abstrato**. Isso a torna ideal para expressões lambdas e referências a métodos.

---
## As expressões lambda em ação
Vamos examinar alguns exemplos simples que colocam em prática os conceitos básicos das expressões lambda. O primeiro exemplo reúne em um programa completo os elementos mostrados na última seção:

[[LambdaDemo.java | Código Expressão Lambda + Interface simples]]
A expressão lambda deve ser compatível com o método abstrato que ela implemente pela interface funcional. Portanto, as linhas desativadas por comentários no fim do programa não são válidas, se tentarmos executar, será lançada um erro em tempo de execução. 

Vamos considerar o programa a seguir que é estruturado como:
1. **Define uma interface funcional**: chamada **NumericTest** que declara o método abstrato **test()**. 
2. **O método test()** possuí dois parâmetros #int e retorna um resultado #boolean.
3. **A nossa função** é determinar se os dois argumentos passados atendem uma condição. Ele retorna o resultado do teste.
4. **Em main()** três testes diferentes são criados com o uso de expressões lambda. Um verifica se o primeiro argumento pode ser dividido exatamente pelo segundo, o outro determina se o primeiro argumento é menor do que o segundo, e o terceiro retorna **true** se os valores absolutos dos argumentos forem iguais. 
5. **As expressões lambdas** que implementam esses testes têm dois parâmetros e retornam um resultado #boolean, isso é necessário já que o método abstrato **test()** tem dois parâmetros e retorna um resultado #boolean.
[[LambdaDemo2.java | Código 1 interface funcional - várias expressões]]
Como o programa acima demonstra, como todas as três expressões lambda são compatíveis com **test**, todas podem ser executadas por intermédio de uma referência a NumericTest. Não precisamos usar três variáveis de referência NumericTest diferentes pois a mesma variável poderia ser usada nos três testes:
```java
NumericTest myTest;

myTest = (n, d) -> (n % d) == 0;
if(myTest.test(10, 2))
	System.out.println("2 is less than 10");
// ...

```

Utilizar variáveis de referência diferentes chamadas **isFactor**, **lessTHan** e **absEqual**, como faz o programa original, deixa claro qual expressão lambda cada variável referencia. 

Há outro ponto interessante no programa anterior. Observamos como os dois parâmetros são especificados para as expressões lambda:
```java
(n, d) -> (n % d) == 0;
```
Observamos que **n** e **d** estão separados por vírgula. Em geral, sempre que mais de um parâmetro são requeridos, eles são especificados, separados por vírgulas, em uma lista entre parênteses no lado esquerdo do operador lambda. 

Embora utilizamos valos primitivos como tipos dos parâmetros e tipo de retorno do método abstrato definido por uma #interface-funcional, não há restrições quanto isso. Podemos testar algum valor relacionado a String também. Abaixo, foi criado uma expressão lambda que determina se um string está contido em outro:
[[LambdaDemo3.java | Código Lambda 3]]
Observamos que a expressão lambda usa o método #indexOf definido pela classe #String para determinar se um string faz parte de outro. Isto funciona porque os parâmetros **a** e **b** são considerados pela inferência de tipos como de tipo **String**. Logo, é permitido chamar um método da classe **String** em **a**. 

---
**Pergunte ao especialista**
Podemos declarar explicitamente o tipo de um parâmetro em uma expressão lambda se necessário. Em casos em que a expressão lambda precisar de dois ou mais parâmetros, devo especificar os tipos de todos os parâmetros ou posso deixar que um ou mais usem a inferência de tipos?
*Em casos em que precisemos declarar explicitamente o tipo de um parâmetro, todos os parâmetros da lista devem ter os tipos declarados. Por exemplo, isto é válido:*
```java
(int n, int d) -> (n % d) == 0;

// Isto não é válido
(int n, d) -> (n % d) == 0;

// E nem isso:
(n, ind d) -> (n % d) == 0;
```

---
## Expressões lambda de bloco
O corpo das expressões lambda mostradas nos exemplos anteriores eram compostos por uma única expressão. Estes tipos de corpos lambda são chamados de *corpos de expressão* e as expressões lambda que têm corpos de expressão às vezes são chamadas de *lambdas de expressão*. 

Em um corpo de expressão, o código do lado direito do operador (lado bolsonarista) deve ser composto por uma única expressão, que passa a ser o valor da lambda da expressão. Embora as lambdas das expressões sejam muito úteis, a situação **pode demandar mais de uma expressão**. 

Para lidar com esses casos, Java dá suporte a um segundo tipo de expressão lambda em que o código do lado direito do operador #lambda é composto por um bloco de código que pode conter mais de uma instrução. Este tipo de corpo lambda é chamado de *corpo de bloco*. Expressões lambda que têm corpos de bloco também são conhecidas como *lambdas de bloco*. 

Uma lambda de bloco expande os tipos de operações que podem ser efetuadas com uma expressão lambda porque permite que o corpo da expressão contenha várias instruções. Por exemplo, em uma lambda de bloco podemos declarar variáveis, usar laços, especificar instruções #if e #switch, criar bloco aninhados e assim por diante. É fácil criar uma lambda de bloco. Apenas coloque o corpo entre chaves como fazemos com qualquer outro bloco de instruções.

Exceto por permitir a inclusão de várias instruções, as lambdas de bloco são usadas de maneira semelhante às lambdas de expressão. Uma diferença importante, no entanto, é que d**evemos usar explicitamente uma instrução** #return para retornar um valor. Isso é necessário porque o corpo de uma lambda de bloco não representa uma única expressão, são várias expressões, portanto, precisamos definir um retorno.

Vejamos um exemplo que usa uma lambda de bloco para encontrar o <span style="background:#d4b106">menor fator positivo</span> (logo, o menor fator positivo é o menor número inteiro positivo que divide esse número exatamente, ou seja, sem deixar resto n % m == 0) de um valor #int. Ele usa uma interface chamada #NumericFune que tem um método de nome func(); o método recebe um argumento #int e retorna um resultado #int. Logo, **NumericFunc** dá suporte a uma função numérica de valores de tipo **int**. 

[[BlockLambdaDemo.java]]
No programa, observamos que a lambda de bloco declara uma variável chamada **result**, usa um laço **for** e tem uma instrução **return**. Esses elementos são válidos dentro do corpo de uma lambda de bloco. Na verdade, o corpo de bloco de uma expressão lambda é semelhante ao corpo de um método. Outra coisa: quando uma instrução **return** ocorre dentro de uma expressão lambda, elas apenas causa o retorno da expressão, não faz o método externo retornar. 

**Entendendo o Retorno em Expressões Lambda**
O #return dentro de uma expressão lambda tem um comportamento diferente do #return dentro de um método tradicional. **Ele retorna apenas do contexto da lambda, não do método que a contém**.
Isso ocorre, pelo fato de termos escopos diferentes, **lambdas de bloco** possuem return dentro desse escopo que só afeta esse escopo específico.

Lambdas são como funções anônimas, com um propósito específico dentro de um contexto maior. O #return delas serve para finalizar a execução daquela pequena "função" e retornar um valor para o código que a chamou.

## Interfaces funcionais genéricas
A expressão lambda não pode especificar parâmetros de tipo. Logo, ela não pode ser genérica. No entanto, a interface funcional associada a uma expressão lambda pode ser genérica. Nesse caso, o tipo de destino da expressão lambda é determinado, em parte, pelo argumento ou argumentos de tipo especificados quando uma referência de interface funcional é declarada.

Para entender o valor das interfaces funcionais genéricas, consideramos: anteriormente, duas **interfaces funcionais** diferentes foram criadas, uma chamada #NumericTest e a outra chamada #StringTest. Elas foram usadas para determinar se dois valores atendiam uma condição. Para fazê-lo, ambas definiam um método chamado **test()** que usava dois parâmetros e retornava um resultado #boolean. No caso de **NumericTest**, os valores testados eram inteiros. Em **StringTest**, os valores eram de tipo #String. Logo, **a única diferença entre os dois métodos era o tipo de dado com o qual operavam**.

Essa situação é perfeita para os genéricos #generic. Em vez de termos duas interfaces funcionais cujos métodos só diferem em seus tipos de dados, podemos declarar uma interface genérica para tratar as duas circunstâncias. O programa a seguir mostra essa abordagem:
[[GenericFunctionalInterfaceDemo.java]]

No programa acima, a interface funcional genérica **SomeTest** é declarada da seguinte maneira:
```java
interface SomeTest<T> {
	boolean test(T n, T m);
}
```

Aqui, **T** especifica o tipo dos dois parâmetros de **test()**. Ou seja, ele é compatível com qualquer expressão lambda que use dois parâmetros do mesmo tipo e retorno um resultado **boolean**.

A interface SomeTest é usada para fornecer uma referência a três tipos de lambdas diferentes. A primeira usa o tipo #Integert, a segunda #Double e a terceira #String. Logo, a mesma interface funcional pode ser usada para referenciar as três expressões lambdas. Só o tipo de argumento passado para SomeTest é diferente durante a referenciação da interface funcional.

---
**Tesnte Isto 14-1** Passe uma expressão lambda como argumento
Uma expressão lambda pode ser usada em qualquer contexto que forneça um tipo de destino. Os contextos de destino usados pelos exemplos anteriores foram de atribuição e inicialização. Outro seria quando uma expressão é passada como argumento. **Passar uma expressão lambdaa como argumento é comum no uso de lambdas**. Além disso, é uma aplicação poderosa, pois fornece uma maneira de passarmos código executável como argumento de um método, o que aumenta bastante o poder de expressão de Java.

Para ilustrarmos o processo, vamos criar três funções de strings que executam as operações a seguir:
1. Invertem um string;
2. Invertem a caixa das letras do string;
3. Substituem espaços por hifens.

Essas funções são implementadas como **expressões lambda** da interface funcional **StringFunc**. Elas são então passadas como o primeiro argumento de um método chamado **changeStr()**. Esse método funcional aplica a função à string passada como seu argumento e retorna o resultado. Logo, **changeStr()** pode ser usado na aplicação de várias funções de string diferentes.
O passo a passo está no material.
[[LambdaArgumentDemo.java | Código Tente Isto 14-1 ]]

Como podemos observar no código exemplo acima, é tecnicamente correto passar uma lambda de bloco como argumento, como o exemplo mostra.

A expressão lambda de inversão da caixa das letras usa os métodos #static #isUpperCase, #toUpperCase, #toLowerCase definidos por #Character. Character é uma classe #encapsuladora de tipos #char (portanto, ela encapsula o tipo primitivo char em um objeto, permitindo que o tipo primitivo tenha comportamentos associados a objetos e suporte algumas funcionalidades adicionais). 

O método #isUpperCase retorna #true caso o seu argumento for uma letra maiúscula, caso contrário retorna #false. 

---
**Pergunte ao especialista**
**Além da inicialização de variáveis, da atribuição e da passagem de argumentos, que outras situações constituem um contexto de tipo de destino para uma expressão lambda?**
#coerções, o operador ?, inicializadores de arrays, instruções de #return e as próprias expressões lambdas também podem servir como contextos de tipo de destino.


## Expressões lambda e a captura de variáveis
As variáveis definidas pelo #escopo que contém uma expressão lambda podem ser acessadas dentro da própria expressão. Por exemplo, uma expressão lambda pode usar uma variável de instância ou uma variável static definida pela própria classe que a contém. **A expressão lambda também tem acesso a this** (explícita e implicitamente), que referencia a instância chamadora da classe que contém a expressão. Logo, uma expressão lambda **pode acessar e configurar o valor de uma variável de instância** ou #static **e chamar um método definido pela classe externa**. 

Quando uma expressão lambda usa uma variável local do escopo em que se encontra, é criado uma situação chamada *captura de variável*. Neste caso, a expressão lambda só pode usar variáveis locais que sejam *efetivamente finais*. Uma variável #final é aquela cujo **valor não muda após ser atribuído**. Esse tipo de variável não precisa ser declarado explicitamente como final, mas não é errado fazê-lo. 

É importante saber que uma variável local do escopo externo não pode ser modificada pela expressão lambda, isso removeria seu status #final, tornando a sua captura inválida. 

Demonstrando a diferença entre variáveis efetivamente finais e variáveis locais mutáveis:
[[VarCapture.java]]
Num é efetivamente final e pode ser capturada e utilizada dentro do bloco da expressão lambda. O código funciona pelo fato de não alterarmos o valor da variável final dentro do escopo, apenas capturamos e executamos a lógica de somar com o valor passado como argumento da expressão lambda. Porém, se tentássemos modificar o valor da variável dentro ou fora do bloco da expressão lambda, ela perderia o seu status de efetivamente #final. 
A expressão lambda pode usar e modificar uma variável de instância da classe chamadora. Ela só não pode usar uma variável local do escopo que a contém, a não ser que essas variável seja efetivamente final.

## Lance uma exceção de dentro de uma expressão lambda
Se a expressão lambda lançar uma exceção verificada, essa exceção deve ser compatível com as exceções listadas na cláusula #throws do método abstrato da interface funcional. Por exemplo, se uma expressão lambda lançar uma #IOException, o método abstrato da interface funcional deve listar #IOException em uma cláusula #throws:
[[LambdaExcepctionDemo.java]]
Já que uma chamada a read() pode resultar em uma IOException, o método iOAction da **inteface funcional** **MyIOAction** deve incluir **IOException** em uma cláusula throws:
```java
interface MyIoAction {
    boolean ioAction(Reader rdr) throws IOException;
}
```
Sem a declaração acima, o programa não será compilado porque a expressão lambda não será mais compatível com **ioAction**. 

## Referências de método
Há um recurso importante relacionado às expressões lambdas chamado *referência de método*. Uma referência de método fornece uma maneira de referenciarmos um método sem executá-lo. Este recurso está relacionado as expressões lambdas pelo fato delas exigirem um contexto de tipo de destino composto por uma interface funcional compatível. Quando avaliada, uma referência de método também cria uma instância de uma interface funcional. Há diferentes tipos de referências de método. 

## Referências a métodos static
Uma referência a um método static é criada com a especificação do nome do método precedido pelo nome de sua classe, com a sintaxe geral:
```java
NomeClass::nomeMétodo
```
O nome da classe é separado do nome do método por dois pontos duplos. Os dois pontos duplos :: são um novo separador adicionado a Java pelo #JDK8 expressamente para esse fim. Essa referência de método pode ser usada em qualquer local em que seja compatível com seu tipo de destino.

O código abaixo irá demonstrar a referência de método #static. Para fazer isso primeiro ele declara uma interface funcional chamada **IntPredicate** que tem um método chamado **test()**. Esse método tem um parâmetro #int e retorna um resultado #boolean. Logo, pode ser usado para testar um valor inteiro em relação a alguma condição. Em seguida, o programa cria uma classe chamada **MyIntPredicates**, que define três métodos #static, todos verificando se um valor atende a uma condição. Os métodos se chamam **isPrime()**, **isEven()** e **isPositive()** e ada método executa o teste indicado por seu nome. Dentro de **MethodRefDemo**, é criado um método chamado **numTest()** que tem como seu primeiro parâmetro uma referência a **IntPredicate**. Seu segundo parâmetro especifica o inteiro que está sendo testado. 
[[MethodRefDemo2.java]]

## Referências de construtor
Da mesma forma que podemos criar referências a métodos, também podemos criar referências a construtores. Esta é a forma geral da sintaxe usada:
```java
nomeclass:new
```
Esta referência pode ser atribuída a qualquer referência de interface funcional que defina um método compatível com o construtor. Vejamos um exemplo simples:
[[ConstructorRefDemo.class]]

No programa, observamos o método **func()** de **MyFunc** retornando uma referência de tipo **MyClass** e tem um parâmetro **String**.
MyClass define dois construtores: 
1. O primeiro especifica um parâmetro de tipo #String.
2. O segundo é o construtor padrão sem parâmetros. 
Examinamos a linha abaixo:
```java
MyFunc myClassCons = MyClass::new;
```

A expressão **MyClass::new** cria uma referência a um construtor de **MyClass**. Nesse caso, já que o método func() de myFunc usa um parâmetro **String** , o construtor que está sendo referenciado é **MyClass(String s)**.

---
**Pergunte ao especialista**
Posso declarar uma referência de construtor que crie um array?
Sim. Para criar a referência de construtor de um array, usamos a seguinte estrutura:
```java
tipo[]::new
```
Nesta sintaxe, *tipo* especifica o tipo do objeto que está sendo criado. Por exemplo, supondo o uso da forma de **MyClass** e dado a interface **MyClasssArrayCreator**:
```java
interface MyClassArrayCreator {
	MyClass[] func(int n);
}
```

O código a seguir cria um array de objetos **MyClass** e dá a cada elemento um valor inicial:
```java
MyClassArrayCreator mcArrayCons = MyClass[]::new;
MyClass[] a = mcArrayCons.func(3);
for(int i=0; i < 3; i++)
	a[i] = new MyClass(i);
```

## Interfaces Funcionais predefinidas
Nos exemplos, definimos as nossas próprias interfaces funcionais para que os conceitos básicos que compõem as expressões lambdas e as interfaces funcionais pudessem ser claramente ilustrados. Em muitos casos, no entanto, usaremos interfaces funcionais predefinidas, pois o JDK8 adicionou um novo pacote chamado #java-util-function que fornece várias interfaces predefinidas.

Mostrando o uso da interface #Predicated:
[[UsePredicateInterface.java]]

---
**Pergunte ao especialista**
No início do capítulo, foi mencionado que a inclusão das expressões lambda resultava em novos recursos sendo incorporados à biblioteca de APIs. Pode dar um exemplo?

**Resposta**: uma das melhorias mas importantes feitas na biblioteca de APIs Java e adicionada por JDK 8 é o novo pacote de fluxos #java-util-stream. Este pacote define várias classes de fluxos, a mais geral delas é a #Stream. No que diz respeito a #java-util-stream , *um fluxo é um canal de dados*. Logo, um fluxo representa uma sequência de objetos. Além disso, um fluxo dá suporte a muitos tipos de operações que permite a criação de pipeline que executará uma série de ações nos dados. Por exemplo, usando a API de fluxos, **podemos construir sequências de ações nos dados**. 

**Exemplo comparativo com SQL**
```sql
SELECT name FROM users WHERE age > 30;
```

- Com a API de fluxos:
```java
List<String> names = users.stream()
						.filter(user -> user.getAge() > 30)
						.map(User::getName)
						.collect(Collectors.toList());
```
- filter: filtra os usuários com idade maior que 30;
- map: extrai o nome de cada usuário;
- collect: coleta os resultados em uma lista.