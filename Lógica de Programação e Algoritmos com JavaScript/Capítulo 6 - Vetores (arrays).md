Vetores ou #arrays são estruturas que permitem armazenar uma lista de dados na memória principal do computador. Eles são úteis para inserir ou remover itens de uma lista de compras ou de alunos de uma turma, por exemplo. Com os vetores <span style="background:#affad1">é possível recuperar todos os itens inseridos na lista</span>. Um <span style="background:#affad1">índice numérico</span> (que começa em 0) <span style="background:#affad1">identifica cada elemento da lista</span>. A representação ilustrado na tabela abaixo, contém uma lista de itens de um supermercado armazenada no vetor produtos.****

| produtos |         |
| -------- | ------- |
| 0        | Arroz   |
| 1        | Feijão  |
| 2        | Iogurte |
| 3        | Leite   |
| 4        | Suco    |
| 5        | Pão     |

Para #referenciar um item do vetor, devemos indicar seu nome, seguido por um número entre colchetes que aponta para o seu índice. É importante reforçar que o vetor inicia pelo índice 0. Portanto, para obter o primeiro produto inserido no vetor, devemos utilizar: 
```js
produtos[0]
```
Também podemos alterar um produto da lista, com uma nova atribuição de conteúdo a um elemento do vetor, como:
```js
produtos[2] = "Queijo"
```

Na linguagem #JavaScript, <span style="background:#affad1">não é necessário indicar o número total de elementos do vetor na sua declaração</span>. Para declarar um vetor em JavaScript, devemos utilizar uma das seguintes formas:

```js
const produtos = []
const produtos = new array()
```

Prefira utilizar a primeira forma const produtos = [], que é a recomendada. Também é possível declarar um vetor com algum conteúdo inicial (e, mesmo assim, adicionar ou remover itens no vetor no decorrer do programa). A instrução a seguir declara e insere três produtos no vetor:

```js
const produtos = ["Arroz", "Feijão", "Iogurte"]
```

Vetores podem ser declarados com a palavra chave reservada #const e mesmo assim ter o valor dos seus elementos alterados. O que não pode ser feito com o #const é uma reatribuição de valor a uma variável. Isso evita possíveis erros que poderiam ocorrer com o var. 

É possível criar uma lista de contas, por exemplo, sem o uso de vetores. É possível exibir os dados informados pelo usuário em um programa #concatenando os conteúdos a serem exibidos em uma string. Contudo, não é possível (ou é muito mais complicado) <span style="background:#ff4d4f">gerenciar esses dados</span>. A <span style="background:#d4b106">diferença entre ouso de variáveis e vetores</span> é indicada a seguir:
1. Uma variável <span style="background:#affad1">armazena apenas um valor por vez</span>; quando uma nova atribuição a essa variável é realizada, o seu <span style="background:#affad1">valor anterior é perdido</span>. Após as duas atribuições a seguir, a variável idade permanece apenas com o último valor que lhe foi atribuído:
```js
let idade
idade = 18
idade = 15
```

2. Já os vetores permitem armazenar um conjunto de dados e acessar todos os seus elementos pela <span style="background:#d4b106">referência ao índice que identifica cada um deles</span>. Assim, após as duas atribuições a seguir, os dois valores atribuídos ao vetor idade podem ser acessados.
```js
const idade = []
idade[0] = 18
idade[1] = 15
```

## 6.1 Inclusão e exclusão de itens
Depois de realizarmos a declaração do vetor, podemos <span style="background:#affad1">gerenciar</span> a lista <span style="background:#affad1">com a inclusão e a exclusão de itens</span> a esse vetor. Os principais métodos JavaScript que executam essas tarefas estão indicados na Tabela 6.2.

| push()    | <span style="background:#d4b106"><font color="#000000">Adiciona um elemento ao final do vetor</font></span>;                                                       |
| --------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| unshift() | <span style="background:#d4b106"><font color="#000000">Adiciona um elemento ao início do vetor e desloca os elementos existentes uma posição abaixo</font></span>; |
| pop()     | <font color="#000000"><span style="background:#d4b106">Remove o último elemento do vetor</span>;</font>                                                            |
| shift()   | <span style="background:#d4b106"><font color="#000000">Remove o primeiro elemento do vetor e desloca os elementos existentes uma posição acima</font></span>;      |
|           |                                                                                                                                                                    |
```run-javascript
const cidades = ["Pelotas"] // declara e define o conteúdo inicial do vetor;

// Adicionando o final do vetor
cidades.push("São Lourenço")
console.log(cidades)

// Adicionando ao início do vetor e deslocando os demais
cidades.unshift("Porto Alegre")
console.log(cidades)

// Removendo a última cidade do vetor
cidades.pop()
console.log(cidades)

// Removendo a primeira e subindo as demais cidades
cidades.shift()
console.log(cidades)
```

Existem outros métodos para manipulação de elementos de um vetor em JavaScript. O método #splice (<span style="background:#d4b106">na ideia de emendar</span>) pode possuir diversos parâmetros e ser utilizado para alterar, inserir ou remover elementos do array. Já o método #slice (<span style="background:#d4b106">na ideia de fatiar</span>) obtém uma "fatia" de um vetor. Ele contém dois parâmetros que são posição inicial e final (que não é obrigatório) do array. Se a posição inicial for um número negativo, ela indica a quantidade de elemento do final para o início que serão obtidos. Observamos o exemplo abaixo:

```run-javascript
// Declarando a variável inicial;
const letras = ["A", "B", "C", "D"]

// Obtém 2 últimas letras
const letras2 = letras.slice(-4)
console.log(letras2)

// Obtendo as 2 primeiras letras
const letrasB = letras.slice(0, 2)
console.log(letrasB)

// Obtendo do início até o final, exceto a última
const letrasY = letras.slice(0, 3)
console.log(letrasY)
```

Detalhes: <span style="background:#d4b106">slice() não modifica o conteúdo do vetor original, enquanto o splice() modifica. A variável retira é sempre um array, mesmo com um ou zero elementos. </span>

## <span style="background:#ff4d4f">Resumo</span>
Vetores, também conhecidos como arrays, são estruturas que permitem armazenar uma lista de dados na memória do computador. Eles **são úteis para inserir ou remover itens de uma lista**, como uma lista de compras ou de alunos em uma turma. Com os vetores, é possível recuperar todos os itens inseridos na lista, sendo cada elemento identificado por um índice numérico que começa em 0.

Em JavaScript, **não é necessário indicar o número total de elementos** ao declarar um vetor. Podemos declará-lo utilizando a sintaxe 'const produtos = []', sendo essa a forma recomendada. Também podemos inicializar um vetor com conteúdo, como em 'const produtos = ["Arroz", "Feijão", "Iogurte"]'.

Os vetores podem ser declarados com a palavra-chave 'const' e ainda ter o valor de seus elementos alterados. No entanto, não é permitido reatribuir um novo valor à variável que os contém. 

Ao contrário das variáveis, que armazenam apenas um valor por vez, <span style="background:#d3f8b6">os vetores permitem armazenar conjuntos de dados e acessá-los por meio de seus índices</span>. Podemos referenciar um item do vetor indicando seu nome seguido por um número entre colchetes que aponta para seu índice. 

Para gerenciar a lista de itens em um vetor, podemos utilizar métodos como `push(), unshift(), pop() e shift()`, que adicionam ou removem elementos no início ou no final do vetor. 
#pop = remove o último elemento do vetor;
#shift = remove o primeiro elemento do vetor e realoca os elementos;
#push = adiciona um novo elemento ao final do array;
#unshift = adiciona um novo elemento ao início do array.

Além disso, existem outros métodos como `splice() e slice()` para manipulação de elementos em um vetor. O método #splice  pode **alterar**, **inserir** ou **remover** elementos, enquanto o método #slice obtém uma parte do vetor sem modificá-lo.



## 6.2 Tamanho do vetor e exibição dos itens
Uma propriedade importante utilizada na manipulação de vetores é a propriedade #length, que <span style="background:#d4b106">retorna o número de elementos</span> do vetor. Vamos consultá-las quando **quisermos percorrer a lista**, realizar **exclusões** (para verificar antes da exclusão se a lista está vazia) ou, então, para exibir o **número total de itens** do vetor. <span style="background:#d4b106">Para percorrer e exibir os elementos do vetor cidades, podemos utilizar o comando for</span>, indicando que a variável de controle i começa em 0, e repetir o laço enquanto i for menor que cidades.length.

Outra forma de exibir o conteúdo do vetor é pelo uso dos métodos toString() e join(). Eles convertem o conteúdo do vetor em uma string, sendo que <span style="background:#d4b106">o método toString() uma vírgula é inserida entre os elementos</span> e no join() podemos indicar qual caractere vai separar os itens. 
```run-javascript
const cidades = ["Pelotas", "São Lourenço", "Porto Alegre"]
for (let i = 0; i < cidades.length; i++) {
	console.log(cidades[i])
}
console.log("-".repeat(40))
console.log(cidades.toString())
console.log(cidades.join(" - "))
```

## 6.3 For..of e forEach()
Para percorrer os elementos de um vetor, a linguagem JavaScript dispõe também do **loop for.. of** e do **método** **forEach()**. Observe, nos exemplos a seguir, a forma de utilizar esses comandos para apresentar o conteúdo do array  `cidades`. Começamos pelo for..of:
```run-js
const cidades = ["Pelotas", "São Lourenço", "Porto Alegre"]
for (const cidade of cidades) {
	console.log(cidade)
}

```

A cada interação, a variável `cidade` recebe um elemento do vetor cidades. Condições ou cálculos podem ser realizados dentro do loop com essa variável. E, como `cidade` é uma variável de bloco (deixa de existir após cada interação), podemos declará-la com `const`. Assim, a cada interação a variável não é modificada, mas, sim, deixa de existir e é novamente declarada.


Já o método forEach() é mais amplo e **pode chamar uma função** para manipular cada elemento do vetor. Uma forma simples de utilizá-lo para percorrer os elementos de um vetor é exemplificada a seguir:
```run-js
const cidades = ["Pelotas", "São Lourenço", "Porto Alegre"]
cidades.forEach((cidade, i) => {
	console.log(`${i+1}° Cidade: ${cidade}`)
})
```

Algumas operações sobre vetores ficam mais simples se executadas a partir do `forEach()`, como a soma dos elementos que compõem o vetor. Observe o código a seguir:
```run-js
const numeros = [5, 10, 15, 20]
let soma = 0
numeros.forEach(num => soma += num)
console.log(`Soma dos Números: ${soma}`)
```
Cada elemento do vetor `numeros` é recebido como `num` e então o acumulador 
`soma` é incrementado. Um cuidado com o método forEach() é que ele não permite interrupções no laço, ou seja, não podemos utilizar os comandos #break e #continue dentro dele. Dada as considerações sobre cada uma das formas de exibir o conteúdo de um vetor, vamos criar um exemplo para ilustrar a aplicação dos métodos de inclusão, exclusão e listagem de dados de um vetor. Nosso programa deve controlar a lista de atendimento dos pacientes de um consultório odontológico - como se fosse um painel em exposição em uma TV do consultório.  