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

Esta expressão não tem parâmetros, logo, a lista de parâmetros está vazia. O valor retornado será constante, 98.6. 