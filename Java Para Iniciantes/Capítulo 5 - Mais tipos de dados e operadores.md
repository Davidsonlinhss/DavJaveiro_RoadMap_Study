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