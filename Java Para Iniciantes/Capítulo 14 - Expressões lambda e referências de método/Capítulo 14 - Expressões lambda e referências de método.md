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
