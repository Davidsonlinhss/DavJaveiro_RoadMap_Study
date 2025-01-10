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

## As expressões lambda em ação
Vamos examinar alguns exemplos simples que colocam em prática os conceitos básicos das expressões lambda. O primeiro exemplo reúne em um programa completo os elementos mostrados na última seção:
