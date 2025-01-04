## Arrays
Um #array é um conjunto de variáveis do mesmo tipo, referenciadas por um nome comum. Em Java, os arrays podem ter uma ou mais dimensões, embora os arrays #unidimensionais sejam os mais comuns. Podemos usar os arrays para armazenar um registro de temperatura máxima diária durante um mês, uma lista de médias de preços de ações ou uma lista de sua coleção de livros de programação.

A principal vantagem é que o array organiza os dados de tal forma que é fácil tratá-los. Se tivermos um array contendo a renda de um determinado grupo de família, é fácil calcular a renda média percorrendo-o. Os arrays também organizam os dados de uma maneira que fica fácil classificar os seus dados.

Os #arrays **são implementados como objetos** em Java. Como os arrays são implementados como objetos, logo, eles participam da coleta de lixo.

## Arrays unidimensionais
Um array unidimensional é uma lista de variáveis relacionadas. Essas listas são comuns em programação. Podemos utilizar um array unidimensional para armazenar os números de conta dos usuários ativos em uma rede. Outro array poderia ser usado para armazenar as médias de rebatidas anual de um time de baseball.
Para declarar uma array, utilizamos a seguinte forma:
```java
tipo nome-array[] = new tipo[tamanho];
```
 Aqui, *tipo* declara o tipo de elemento do #array. (Normalmente, o tipo de elemento também é chamado de tipo base). O tipo de elemento determina o tipo de dado de cada elemento contido no #array. O número de elementos que o array conterá é determinado pelo tamanho do array. 
Em Java, todos os arrays têm zero como o índice de seu primeiro elemento. Já que a variável **sample** tem 10 elementos, ela tem valores de índice que vão de 0 a 9. 

Os #arrays são comuns em programação, pois nos permitem lidar com grandes quantidades de variáveis relacionadas. 

Outra maneira de declararmos arrays é passando o seu valor entre chaves, por exemplo, inicializando os arrays no momento de sua criação:
```java
tipo array-name[] = {val1, val2, val3, ..., valN};
```
Java aloca automaticamente um array grande o suficiente para conter os inicializadores especificados. 

Os limites do array são impostos rigorosamente em Java; é um erro de tempo de execução estar abaixo ou acima da extremidade de um array. 

---
**Tente Isto 5-1 Classifique um array**
Já que um #array #unidimensional organiza os dados em uma lista linear que pode ser #indexada, é a estrutura de dados perfeita para classificações. Neste projeto, vamos aprender uma maneira simples de classificar um array. Existem vários #algoritmos de #classificação. Exemplos:
- Classificação #short
- Classificação Troca #bubble-sort (classificação por #bolha)
- #Shell-sort
A classificação mais simples e fácil de entender é a classificação por bolha. Embora a classificação por bolha não seja muito eficiente - na verdade, seu desempenho ẽ inaceitável para classificação de #arrays grandes - ela pode ser utilizada de forma eficaz na classificação de #arrays pequenos.

**Passos**:
1. Crie um arquivo chamado Bubble.java

2. A classificação por bolha tem esse nome devido a forma como executa a operação de classificação. Ela usa a comparação repetida e, se necessário, **a troca de elementos adjacentes do array**. Neste processo, valores pequenos se movem em direção a uma extremidade e os maiores em direção à outra. O processo é conceitualmente semelhante a bolhas encontrando seu nível em um tanque de água. A classificação por bolha funciona percorrendo várias vezes o array e trocando os elementos que estiverem fora do lugar quando preciso. O número de passagens necessárias para assegurar que o array esteja classificado é igual a um menos o número de elementos do #array.
Código base para a classificação por bolha:
```java
// Esta é a classificação por bolha
for(a=1; a < size; a++)
	for(b=size-1; b >= a; b--) {
		if(nums[b-1] > nums[b]) { // se fora de ordem, troca elementos
		t = nums[b-1];
		nums[b-1] = nums[b];
		nums[b] = t;
		
		}
	}
```
A classificação se baseia em dois laços #for. O laço interno verifica os elementos adjacentes do array, procurando elementos fora de ordem. Quando um par de elementos fora de ordem é encontrado, os dois elementos são trocados. A cada passagem, o menor dos elementos restantes se move para o local apropriado. O laço externo faz esse processo se repetir até o array inteiro ser classificado.

3. Código #Bubble:
[[Bubble.java | Classificando um Array Unidimensional]]
4. Embora a classificação por bolha seja boa para arrays pequenos, ela não é eficiente quando usada em arrays maiores.O melhor algoritmo de classificação de uso geral é a classificação rápida ( #Quicksort). Quicksort será visto apenas no capítulo 6. [[Capítulo 6 - Verificação minuciosa dos métodos e classes]].

## Arrays multidimensionais
Embora o array unidimensional seja o mais usado em programação, os arrays multidimensionais (arrays de duas ou mais dimensões) certamente não são raros. Em Java, o array #multidimensional é um **array composto por arrays**.

## Arrays bidimensionais
O array #bidimensional é o array multidimensional mais simples. Um array bidimensional, é uma lista de arrays unidimensionais. Para declarar um array bidimensional de tipo inteiro e tamanho 10, 20 chamado **table** escrevemos:
#declaração
```java
int table[][] = new int[10] [20]; // 10 linhas e 20 colunas
int table2[][] = new int[3] [4]; // 3 linhas e 4 colunas
```
Java insere cada dimensão em seu próprio conjunto de colchetes. 

Exemplo de um array bidimensional carregado com os números de 1 a 12:
[[Twod.java | Array Bidimensional]]
No exemplo, teremos algo semelhante a imagem abaixo:
![[Capítulo 5 - Mais tipos de dados e operadores.png]]

## Arrays irregulares
Quando alocamos #memória para um #array multidimensional, só temos de especificar a memória da primeira dimensão (a da extrema esquerda, linha?). As outras dimensões podem ser alocadas separadamente. Por exemplo, o código a seguir aloca memória para a primeira dimensão do array #table quando este é declarado. A segunda dimensão é alocada manutalmente.
```java
int table[] [] = new int[3] [];
table[0] = new int[4];
table[1] = new int[4];
table[2] = new int[4];
```
Embora não haja vantagens em alocar individualmente os conjuntos da segunda dimensão nessa situação, pode haver outras. Quando alocamos as dimensões separadamente, não precisamos alocar o mesmo número de elementos para índice. Uma vez que os arrays multidimensionais são implementados como #arrays compostos por arrays, temos o controle do tamanho de cada array. Suponhamos que estivéssemos escrevendo um programa para armazenar o número de passageiros que pegam um ônibus do aeroporto. Se o ônibus faz o transporte 10 vezes ao dia durante a semana e duas vezes ao dia no sábado e domingo, poderíamos usar o array #riders mostrado no programa abaixo para armazenar as informações.

[[Ragged.java | Código Passageiros por viagem]]

## Arrays de três ou mais dimensões
Java permite arrays com mais de duas dimensões, como forma geral:
```java
tipo nome[][]...[] = new tipo[tamanho1][tamanho2]...[tamanhoN];
```

Criando um array tridimensional inteiro de 4 x 10 x 3:
```java
int multidim[][][] = new int [4] [10] [3];
```

## Inicializando arrays multidimensionais
Um array multidimensional pode ser inicializado com a inserção da lista de inicializadores de cada dimensão dentro de seu próprio conjunto de chaves:
```java
especificador-tipo nome_array[][] = {
	{val, val, val, ...,val};
	{val, val, val, ...,val};
	.
	.
	.
	{val, val, val, ...,val};
};
```
Por exemplo, o programa a seguir inicializa um array chamado **sqrs** com os números de 1 a 10 e seus quadrados:
[[Squares.java | Código ArrayBidimensional]]

## Sintaxe alternativa para a declaração de arrays
Há uma segunda forma que pode ser usada na declaração de um array:
```java
type[] var-name;
```

Aqui, <span style="background:#d4b106">os colchetes vêm depois do especificador de tipo</span>, e não do nome da variável de array. Por exemplo:
```java
int counter[] = new int[3];
int[] counter = new int[3];
```

Essa forma de declaração alternativa é conveniente na declaração de vários arrays do mesmo tipo. Por exemplo:
int[] nums, nums2, nums3, nums4...  numsN; 

A forma de declaração alternativa também é útil na especificação de um array como tipo de retorno de um método:
```java
int[] someMeth( ) {

}
```
declaramos que o método someMeth() retorna um array de tipo #int;

## Atribuindo referências de arrays
Como ocorre com os demais objetos, quando atribuímos uma variável de referência de array a outra variável de array, estamos simplesmente alterando o objeto que a variável referencia. <span style="background:#d4b106">Não estamos criando uma cópia do array, nem copiando o conteúdo de um array para o outro</span>.
Exemplo:
[[AssignARef.java | Código de Reatribuição de Arrays]]
Quando reatribuímos, estamos referenciando o mesmo objeto.

## Usando o membro length
Já que os #arrays são implementados como objetos, cada array tem uma variável de instância #length associada que contém o número de elementos que ele pode conter. (Em outras palavras, #length contém o tamanho do array). 
Demonstrando a propriedade:
[[LengthDemo.java | Código utilizando Length]]

## O laço for de estilo for-each
No trabalho com arrays, é comum encontrarmos situações em que um array deve ser examinado do início até o fim, elemento a elemento. Por exemplo, para calcularmos a soma dos valores contidos em um array, cada elemento do array deve ser examinado. A mesma situação ocorre no cálculo de uma média, na busca de um valor, na cópia de um array, e assim por diante. Já que essas operações de tipo "início ao fim" são tão comuns, Java define uma segunda forma do laço **for** que otimiza a operação.

A segunda forma de for implemente um laço de estilo #for-each. Um laço for-each percorre um conjunto de objetos, como um array, de maneira rigorosamente #sequencial, do início ao fim. Nos últimos anos, os laços de estilo for-each ganharam popularidade tanto entre projetistas quanto entre programadores de linguagens de computador. Lançado com o #JDK5, o laço for foi melhorado para fornecer essa opção. O estilo for-each de for também é chamado de *laço for melhorado*. 

A forma geral do laço é mostrada abaixo:
```java
for(tipo itr-vara : conjunto) bloco de instruções
```
- **tipo** - especifica o tipo;
- **var-iter** - especifica o nome de uma variável de iteração que receberá os elementos de um conjunto;

O laço se repete até todos os elementos do conjunto terem sido usados. Logo, na iteração por um array de tamanho N, o laço for melhorado obtém os elementos do array em ordem de índice, de 0 a N - 1.

---
**PERGUNTE AO ESPECIALISTA**
Além dos arrays, que outros tipos de conjuntos o laço for de estilo #for-each percorre?
Um dos mais importantes usos do laço de estilo for-each é para percorrer o conteúdo de um conjunto definido pela Collections Framework. A Collections Framework é um conjunto de classes que implementa várias estruturas de dados, como listas, vetores, conjuntos e maps... **Collections Framework**. 

---

O laço de estilo for-each automatiza o laço anterior. Especificamente, ele elimina a necessidade de estabelecermos um contador de laço, determina o valor inicial e final e indexa manualmente o array. Ele percorre o array inteiro, obtendo um elemento de cada vez, em sequência, do início ao fim. 

O laço for de estilo for-each percorre automaticamente um array em ordem, do índice menor ao maior. Também podemos encerrar o laço antecipadamente usando uma instrução #break. 
```java
for(int x : nums) {
	System.out.println("Value is: " + x);
	sum += X;
	if(x == 5) break; // interrompe o loop quando 5 é obtido
}
```

Há um ponto importante que precisa ser conhecido em relação ao for-each. Sua variável de iteração está associado ao array subjacente, mas é somente de leitura. Logo, uma atribuição à variável de iteração não tem efeito sobre o array subjacente. Em outras palavras, não podemos alterar o conteúdo do array atribuindo um novo valor à variável de iteração. 

## Aplicando o laço for melhorado
Já que o laço for de estilo for-each só pode percorrer o array sequencialmente, do início ao fim, nós tendemos a achar que seu uso é limitado. No entanto, isso não é verdade. Vários algoritmos precisam exatamente desse mecanismo. Um dos mais comuns é a #busca [[Capítulo 3 - Algoritmos de Busca  | Estudo sobre Busca]]
Escrevendo um programa que usa um laço for para procurar um valor em um <span style="background:#d4b106">array não classificado</span>. Ele para quando o valor é encontrado:
[[Search.java | Algoritmo de busca Linear]]

O laço for de estilo #for-each é uma ótima opção neste caso, pois pesquisar um array não classificado envolve examinar cada elemento em sequência. 

## Operando com strings
A classe #String contém vários métodos que operam com stringss. :
**boolean equals(str)** - retorna verdadeiro se o string chamador tiver a mesma sequência de caracteres de str.

**int length()** - obtém o tamanho de um string.

**char charAt(index)** - obtém o caractere do índice especificado por index.

**int compareTo(str)** - Retorna menor do que zero se o string chamador for menor do que str, maior do que zero se o string chamador for maior do que str e zero se os strings forem iguais. 
**int indexOf(str)** - procura no string chamador o substring especificado por str. 

**int lastIndexOf(str)** - procura no string chamador o substring especificado por str. Retorna o índice da última ocorrência ou -1 em caso de falha. 


