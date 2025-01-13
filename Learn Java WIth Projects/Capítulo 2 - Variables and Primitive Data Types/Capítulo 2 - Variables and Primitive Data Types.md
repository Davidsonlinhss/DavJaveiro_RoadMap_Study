Todas as linguagens de programação precisam de variáveis e oferecerem tipos de dados #primitivos . Eles são essenciais para o funcionamento até mesmo dos programas mais simples. Ao final deste capítulo, seremos capazes de declarar variáveis usando os tipos primitivos do Java. Além disso, entenderemos as diferenças entre os diversos tipos primitivos e saberemos como usar cada um deles em determinadas situações.

Neste capítulo, nós vamos abordar os seguintes tópicos principais:
1. <span style="background:#b1ffff">Entendendo e declarando variáveis;</span>
2. <span style="background:#b1ffff">Explorando os tipos de dados primitivos em Java.</span>

## Entendendo e declarando variáveis
Se você precisar armazenar um valor para utilizar depois, você precisa de uma variável. Portanto, toda linguagem de programação oferece essa funcionalidade por meio de variáveis. Nesta seção, vamos aprender o que é uma variável e como declará-la. A <span style="background:#fdbfff">área de seu código onde você pode usar uma determinada variável </span>é conhecida como o #escopo <span style="background:#fdbfff">da variável</span>. Esse é um conceito muito importante e será abordado em detalhes no Capítulo 4.

### What is a variable?
Variáveis <span style="background:#fdbfff">são locais na memória</span> que possuem um *nome* (conhecido como identificador) e um *tipo*.  Elas se assemelham a compartimentos ou caixas postais nomeadas. O nome de uma variável é necessária para que possamos referenciá-la e distingui-lá de outras variáveis. 

Um *tipo* de variável especifica o tipo de valores que ela pode armazenar. Por exemplo, a variável será usada para armazenar números inteiros, como 4, ou números decimais, como 2,98? A resposta a essa pergunta determina o tipo da variável.

### Declaring a variable
Vamos supor que queremos armazenar o número 25 em uma variável. Vamos assumir que este número representa a idade de uma pessoa, logo nós iremos utilizar o identificar **age** para referenciá-la. Introduzir uma variável pela primeira vez é conhecido como **declarar** a variável. 

Um número inteiro (positivo ou negativo) é chamado de inteiro, e o Java fornece um tipo primitivo integrado especialmente para inteiros, chamado de #int. Ao declarar uma variável no Java, <span style="background:#fdbfff">é necessário especificar o tipo da variável</span>. Isso ocorre porque o Java é conhecido como uma linguagem fortemente tipada, o que significa que temos que definir o tipo da variável imediatamente ao declará-la. 

Declarando uma variável e fornecendo um tipo:
```java
int age;
age = 25;
```
A primeira linha declara **age** como um **int**;
A segunda linha define o seu valor como sendo 25.

Observamos que os pontos e vírgulas (;) no final das linhas s<span style="background:#fdbfff">ão delimitadores que indicam ao compilador onde uma instrução Java termina</span>. O sinal de igual (=) é o operador de atribuição, que será abordado no Capítulo 3. Por enquanto, basta entendemos que o valor 25 é "atribuído" à variável **age**. 

---
**O operador de Atribuição**
O sinal de igual = no Java não é o mesmo que o sinal de = na matemática. O Java usa o sinal de igual duplo == para representar "igualdade", que é chamado de equivalência. 

---
Podemos escrever o código anterior de duas linhas em uma única linha:
```java
int age = 25;
```
**Age** é o nome de uma variável e 25 é o valor inteiro armazenado na localização da variável. 

### Naming a variable
Um identificador é simplesmente um nome que damos à construção Java que está codificando; por exemplo, os identificadores (nome) são necessários para nomear variáveis, métodos, classes e assim por diante. 

---
**Identifiers**
Um #identificador é composto por letras, dígitos, underscores e símbolos de moeda. Identificadores não podem começar com um número e não podem conter espaços em branco (espaços, tabs e quebras de linha). Nos exemplos a seguir, as vírgulas separam os diferentes identificadores.
- Exemplos de identificadores válidos, embora incomuns, são: `a£€_23`, `_29`, `Z`, `thisIsAnExampleOfAVeryLongVariableName`, `€2_`, e `$4;`.
- Exemplos de identificadores inválidos: `9age` e `abc def;`.
---

Nomeie suas variáveis com cuidado. Isso ajuda a tornar o código mais legível, o que resulta em menos erros e manutenção mais fácil. #camelCase é uma técnica muito popular nesse sentido. Em relação aos nomes das variáveis, o **camel case** significa que toda a primeira palavra é escrita em minúsculas. Além disso, a primeira letra <span style="background:#fdbfff">de cada palavra subsequente no nome da variável </span>começa com letra maiúscula. Aqui está um exemplo:
```java
int ageOfCar;
int numberOfCHildren;
```
Neste bloco de código, nós temos duas variáveis de números inteiros com seus nomes identificadores seguindo o camel case.
## Accessing a variable
Para acessar o valor de uma variável, basta digitar o nome da variável. Quando digitamos o nome de uma variável no Java, o compilador primeiro garante que uma variável com esse nome exista. Supondo que exista, a JVM, em tempo de execução, retornará o valor armazenado no "compartimento" dessa variável. Portanto, o seguinte código exibirá o valor 25 na tela:
```java
		int age = 25;
		System.out.println(age);
```

A primeira linha declara a variável **age** e a inicializa com o valor 25. A segunda linha acessa o local da variável e exibe o seu valor.

---
**System.out.println()**
System.out.println() exibirá tudo que estiver dentro das chaves () na tela.

---
### Acessando uma variável que eu não tenha declarado
Como mencionado anteriormente, o Java é conhecido por ser uma linguagem fortemente tipada. Isso significa que <span style="background:#affad1">devemos especificar o tipo da variável imediatamente ao declará-la</span>. Se o compilador encontrar uma variável e não souber o seu tipo, ele irá gerar um erro. Por exemplo, consideramos a seguinte linha de código:
```java
age = 25; // Erro: tipo de variável não especificada
```

Supondo que nenhum outro código declare `age`, o compilador gera um erro informando que não é possível resolver o símbolo age. Isso ocorre porque o compilador está procurando o tipo para associar à variável `age` e não consegue encontrá-lo (pois não especificamos o tipo).  
Aqui está um exemplo ligeiramente diferente. Neste exemplo, estamos tentando exibir a variável age na tela:
```java
int length = 25;
System.sout.println(age);
```

Neste exemplo, declaramos uma variável chamada **length** e, portanto, não há declaração da variável **age**. Quando tentamos acessar a variável **age** no **System.out.println()**, o compilador começa a procurar por age. O compilador não consegue encontrar age e gera um erro informando que não é possível resolver o símbolo `age`. De fato, o que o compilador está dizendo é que tentamos usar uma variável chamada **age** que o compilador não consegue encontrar. Isso acontece porque nem declaramos a variável, sem falar em especificar seu tipo.

---
**Comments**
Comentários são muito úteis, pois nos ajudam a explicar o que está acontecendo no código. 
Quando o compilador vê //, ele ignora o restante da linha.
/* texto qualquer * / é um comentário de várias linhas. Qualquer coisa entre o início /*