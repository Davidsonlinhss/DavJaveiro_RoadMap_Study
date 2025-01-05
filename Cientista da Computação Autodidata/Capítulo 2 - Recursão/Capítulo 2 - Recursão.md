 *Para entender a recursão, primeiro é preciso saber o que é repetição*
Um #algoritmo-iterativo resolve problemas repetindo etapas continuamente, em geral usando um loop. A maioria dos algoritmos que escrevemos até agora, é iterativo. A #recursão é um método de solução de problemas em que **resolvemos partes menores do problema até chegar a uma solução**. Os algoritmos recursivos dependem de funções que chamam a si mesmas. Qualquer problema que puder resolvermos com um algoritmo iterativa, também poderá resolver com um #recursivo; no entanto, às vezes um #algoritmo recursivo é uma solução mais elegante.  Algoritmo recursivo é mais elegante do que um iterativo...

Um algoritmo recursivo é escrito dentro de uma #função ou #método que chama a si mesmo. O<span style="background:#d4b106"> código dentro da função altera a entrada e passa uma entrada nova na próxima vez que a função chamar a si mesma</span>. Portanto, a função deve ter um **caso base**: uma condição que encerre o algoritmo recursivo para que este não continue sendo executado eternamente. Cada vez que a função chamar a si própria, chegará mais perto do caso base. A condição do caso base acabará sendo satisfeita, o problema será resolvido e a função parará de chamar a si mesma. Um algoritmo que segue essas regras atende às três leis da #recursão:
1. Um algoritmo recursivo deve ter um caso base;
2. Um algoritmo recursivo deve alterar seu estado e prosseguir em direção ao caso base;
3. Um algoritmo recursivo deve chamar a si mesmo recursivamente.
Para nos auxiliarmos, vamos examinar o cálculo do #fatorial de um número usando um algoritmo #recursivo e um #iterativo. O #fatorial de um número é o produto de todos os inteiros positivos menores que ou iguais ao número. 
Exemplo:
```math
||{"id":1495692885643}||

5! = 5 * 4 * 3 * 2 * 1
```
Exemplo de algoritmo iterativo que calcula o fatorial de um número, **n**, refeito em Java:
[[Recursivo.java | Algoritmo Iterativo do Fatorial de n]]

Agora, vamos tentar escrever o algoritmo de maneira recursiva. Para isso, criamos um método separado que chama a si mesmo até alcançar a base da recursão. 
[[TrueRecursivo.java | Fatorial Recursivo]]
Nossa função chamará a si mesma repetidamente até *n* ser 0, ponto em que retornará 1 e parará de chamar a si mesma.
Enquanto a condição do caso base não for satisfeita, a linha de código a seguir, será executada:
```java
return n * factorial(n-1)
```
A nossa função chama a si mesmo com o valor de n-1. O parâmetro **n** uma hora será menor do que 1, o que atenderá ao caso base:

Internamente, cada vez que a função chega a uma instrução #return, insere-a em uma pilha ( #stack). Uma #pilha é uma estrutura de dados sobre a qual aprenderemos na Parte II do livro. É como uma lista, mas removemos os itens na mesma ordem na qual os adicionamos. 

## Quando usar a recursão
A frequência do uso é uma decisão do programador. Qualquer algoritmo que pudermos escrever recursivamente, também poderá ser escrito iterativamente. A principal vantagem da recursão é que ela é elegante. A desvantagem do algoritmo por recursão é que ele tende a usar mais memória, pois ele armazena mais dados na ilha. As funções recursivas também podem ser mais difíceis de ler e depurar do que algoritmos iterativos porque pode ser mais complicado seguir o que está ocorrendo em um algoritmo recursivo.

### Desafio: exiba os números de 1 a 10 recursivamente.
[[NumberOneToTen.java | Números de 1 a 10 com recursividade]]

