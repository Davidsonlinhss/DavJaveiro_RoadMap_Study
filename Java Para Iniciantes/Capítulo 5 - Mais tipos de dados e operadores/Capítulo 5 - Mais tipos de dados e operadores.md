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
