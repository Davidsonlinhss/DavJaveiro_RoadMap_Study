**Objetivos**
- Escrever aplicativo Java simples;
- Usar declarações de entrada e saída;
- Aprender os tipos primitivos em Java;
- Compreender os conceitos básicos de memória;
- Usar operadores aritméticos;
- Entender a precedência dos operadores aritméticos;
- Escrever declarações de tomada de decisão;
- Usar operadores relacionais e de igualdade.

## 2.1 Introdução
Começaremos com exemplos de programas que exibem (saída) mensagens na tela. Em seguida, demonstraremos um programa que obtém (aceita a entrada) dois números de um usuário, calcula a soma e exibe o resultado. Aprenderemos a como instruir o computador a executar cálculos aritméticos e a salvar os resultados para uso posterior. O último exemplo demonstra como tomar decisões. O aplicativo compara dois números, então exibe mensagens que mostram os resultados da comparação. 

## 2.2 Nosso primeiro programa Java: imprimindo uma linha de texto
Um #aplicativo Java é um programa de computador que é executado quando utilizamos o **comando java** para carregar a **Java Virtual Machine** (JVM). Mais adiante, nesta seção, discutiremos como compilar e executar um aplicativo Java. Primeiro consideramos um aplicativo simples que exibe uma linha de texto.

*Comentando programas*
Inserimos #comentários para documentar programas e aprimorar sua legibilidade. O compilador Java *ignora* os comentários, portanto, eles não fazem o computador realizar qualquer ação quando o programa é executado.  
Por convenção, iniciamos cada programa com um comentário indicando o número da figura e o nome do arquivo.

Exemplo de um **comentário de fim de linha**.
```java
// Figura 2.1: Welcome1.java
```

O Java também tem **comentários tradicionais**, que podem ser distribuídos ao longo de várias linhas, como abaixo:
```Java
/* Esse é um comentário tradicional. Ele
pode ser dividido em várias linhas */
```

Eles começam e terminam com delimitadores, /* . O compilador ignora todo o texto entre os delimitadores. O Java incorporou comentário

Java também possuí um tipo de comentário, conhecido como comentário #Javadoc, Javadoc permite-lhe incorporar a documentação do programa diretamente aos seus programas. Esses comentários são o formato de documentação Java preferido na indústria. O programa utilitário javadoc (parte do JDK) lê comentários Javadoc e os usa para preparar a documentação do programa no formato HTML. 

*Utilizando linhas em branco*
Linhas em branco, caracteres de espaço e tabulações tornam os programas mais fácies de ler. Juntos, eles são conhecidos como **espaços em brancos**. O compilador ignora espaços em branco. 

- Utilize linhas e espaços em branco para aprimorar a legibilidade do programa.

*Declarando uma classe*

```Java
public class welcome1
```
A linha acima começa uma **declaração de class** para a classe Welcome1. Todo programa Java consiste em **pelo menos uma classe** que você (o programador) define. A palavra chave #class introduz uma declaração de classe e é imediatamente seguida pelo nome da classe (welcome1). 

*Nome do arquivo para uma classe public*
Uma classe public deve ser inserida em um arquivo com um nome na forma *NomeDaClasse.java*, assim a classe Welcome1 é armazenada no arquivo Welcome1.java.

*Erro comum de programação 2.2*
Um erro de compilação ocorre se um nome de arquivo da classe public não for exatamente igual ao nome dessa classe (tanto em termos de ortografia como capitalização), seguido pela extensão .java.

*Nomes e identificadores de classe*
Por convenção, os nomes de classes iniciam com letra **minúscula** e apresentam a letra inicial de cada palavra que eles incluem em **maiúscula** (por exemplo, SampleClassName). O nome de uma classe é um #identificador - uma série de caracteres que consiste em letras, dígitos, sublinhados ( _ ) e sinais de cifrão ($) que *não* inicie com um dígito e não contenha espaços. Alguns identificadores válidos são Welcome1, $valor...

O Java faz #distinção entre maiúsculas e minúsculas - letras maiúsculas e letras minúsculas são diferentes - assim, value e Value são identificadores diferentes (mas ambos válidos).

*Corpo de classe*
A **chave esquerda** { inicia o **corpo** de cada declaração de #classe, já a **chave direito** } deve terminar cada declaração de classe. 

*Declarando um método*
A linha 7 é o ponto de partida de cada aplicativo Java. Os parênteses depois do identificador #main indicam que ele é um bloco de construção do programa chamado #método. Declarações de classe Java normalmente contêm um ou mais métodos. Para um aplicativo Java, um dos métodos deve ser chamado #main.

```Java
public static void main(String[] args)
```

Os métodos realizam tarefas e podem retornar informações quando as completam. Explicaremos o propósito da palavra-chave #static na Seção 3.2.5. A palavra-chave #void indica que esse método não retornará nenhuma informação. Mais tarde, veremos como um método pode fazer isso. 

*Gerando saída com System.out.println*

```Java
System.out.println("Welcome to Java Programming!");
```

O #objeto #System.out - que é predefinido para você - é conhecido como **objeto de saída padrão**. Ele permite que um aplicativo Java exiba informações na **janela de comando** a partir da qual ele é executado. Em versões do Microsoft Windows, a janela de comando se chama Prompt de Comando. 

*Utilizando comentários de fim de linha em chaves de fechamento para melhorar a legibilidade*
Como uma ajuda para iniciantes em programação, incluímos um comentário de fim de linha depois de uma chave de fechamento que termina uma declaração de método e após uma chave de fechamento que termina uma declaração de classe. 

## 2.4 Exibindo texto com printf
O método System.out.prinf (f significa "formato") exibe os dados *formatdos*. A figura 2.6 utiliza esse método para gerar a saída em duas linhas das string "welcome to" e "Java Programming!".

```Java
System.out.printf("%s%n%s%n, "Welcome to", "Java Programming!");
```
Quando um método exige múltiplos argumentos, estes são colocados em uma **lista separada por vírgulas**. Chamar um método também é referido como #invocar um método.

## 2.5 Outra aplicação: adicionando inteiros
Nosso próximo aplicativo lê (ou insere) dois #inteiros (números inteiros como -22, 7, 9, 0 e 1024) digitados por um usuário no teclado, calcula sua soma e a exibe na tela. Os programas lembram-se dos números e de outros dados na memória do computador e os acessam por meio de elementos de programa chamados #variáveis. 

## 2.5.1 Declarações *import*
Um dos pontos fortes do Java é seu conjunto de classes predefinidas que você pode *reutilizar* em vez de "reinventar a roda". Essas classes são agrupadas em #pacotes - chamados de *grupos de classes relacionadas* - e, coletivamente, são chamados de *bibliotecas de classes java*, ou *Java Application Programming* Interface (Java API).

## 2.5.3 Declarando e criando um Scanner para obter entrada do usuário a partir do teclado
Uma #variável é uma posição na memória do computador na qual um valor pode ser armazenado para utilização futura em um programa. Todas as variáveis Java devem ser declaradas com um #nome e um #tipo antes que elas possam ser utilizadas. O nome de uma variável permite que o computador a acesse futuramente. 

```Java
Scanner input = new Scanner(System.in);
```
É uma declaração de instrução de declaração de variável que especifica o nome input e o tipo (Scanner) de uma variável utilizada nesse programa. Um #Scanner permite a um programa ler os dados (por exemplo, números e strings) para utilização nele. Os dados podem ser provenientes de várias origens, como os **digitados** pelo usuário ou um **arquivo de disco**. Antes de utilizar um Scanner, devemos criá-lo e especificar a origem dos dados.

O sinal de igual (=) na linha 11 indica que a variável Scanner input deve ser **inicializada** (isto é, preparada para utilização no programa) na sua declaração com o resultado da expressão à direita desse sinal - new Scanner(System.in). Essa expressão utiliza a palava-chave **new** para criar um objeto Scanner que lê caracteres digitados pelo usuário no teclado. O objeto de entrada padrão, System.in, permite que aplicativos leiam *bytes* de informações digitadas pelo usuário. O Scanner traduz esses bytes em tipos (como ints) que podem ser utilizados em um programa. O **Scanner** traduz esses bytes em tipos (como ints) que podem ser utilizados em um programa.

### 2.5.4 Declarando variáveis para armazenar números inteiros
As instruções de declaração de variável abaixo:
```java
int number1; // primeiro número a somar
int number2; // segundo número a somar
int sum; // soma de number1 e number 12
```
essas declarações de **variáveis** estão especificadas com dados do tipo #int (inteiro) - elas podem armazenar valores inteiros (números inteiros como 72, -1127 e 0). Essas variáveis ainda *não* são inicializadas. O intervalo de valores para um #int é -2.147.483.648 a +2.147.483.647. [Observação: os valores #int que usamos em um programa não podem conter pontos.]

Alguns outros tipos de dados são #float e #double para armazenar números reais, e #char para armazenar dados de caracteres. Os números reais contêm pontos decimais, como em 3.4, 0.0 e -11.19. Variáveis do tipo **char** representam caracteres individuais, como uma letra maiúscula (por exemplo, A), um dígito (por exemplo, 7), um caractere especial (por exemplo, * ou %) ou uma sequência de escape (por exemplo, o caractere de nova linha, \t). Os tipos int, float e char são chamados de **tipos primitivos**. Os nomes dos tipos primitivos são palavras-chave e devem aparecer em letras minúsculas. O apêndice D resume as características dos oito tipos primitivos (boolean, byte, char, short, int, long e double).

Diversas variáveis do mesmo tipo podem ser declaradas em uma declaração com os nomes da variável separados por vírgulas (isto é, uma lista separada por vírgulas de nomes de variáveis). Por exemplo:
```java
int number1, 
number2, 
sum; // variáveis do mesmo tipo sendo declaradas
```

#### Boa prática de programação 2.7:
*Declara cada variável em uma declaração própria. Esse formato permite que um comentário descritivo seja inserido ao lado de cada variável que é declarado*

#### Boa prática de programação 2.8:
*Escolher nomes de variáveis significativos ajuda um programa a ser autodocumentado (isto é, pode-se entender o programa simplesmente lendo-o em vez de ler os documentos associados ou visualizar um número excessivo de comentários).*

#### Boa prática de programação 2.9:
*Por convenção, identificadores de nomes de variáveis iniciam com uma letra minúscula e cada palavra no nome depois da primeira começa com uma letra minúscula. Por exemplo, o identificador de nome da variável firstNumber inicia a sua segunda palavra, Number, com uma letra N minúscula. Essa convenção de nomenclatura é conhecida como notação camelo, porque as letras minúsculas destacam-se como corcovas desse animal.*

## 2.5.5 Solicitando entrada ao usuário
A linha abaixo:
```java
System.out.print("Enter first integer: "); // prompt
```
utiliza System.out.print para exibir a mensagem "Enter first integer: ". Essa mensagem é chamada #prompt porque direciona o usuário para uma ação específica. Utilizamos o método #print aqui em vez de println para que a entrada do usuário apareça na mesma linha que o prompt. Lembre-se de que na Seção 2.2 esses identificadores que iniciam com letras maiúsculas representam em geral nomes de classe. A classe System faz parte do pacote **java.lang**. Observe que a classe System não é importada com uma declaração import no começo do programa. 

#### Observação de engenharia de software 2.2
Por padrão, o pacote **java.lang** é importado em cada programa Java; portanto, java.lang é o único na Java API que não requer uma declaração import.

## Obtendo um int como entrada do usuário
A linha abaixo:
```java
number1 = input.nextInt(); // lê o primeiro número fornecido pelo usuário
```
utiliza o método nextInt do valor de input do objeto #Scanner para obter um inteiro digitado pelo usuário. Nesse momento o programa espera que o usuário digite o número e pressione a tecla *Enter* para submeter esse número a ele. 
Nosso programa assume que o usuário insere um valor válido de inteiro. Se não, um erro de lógica em tempo de execução ocorrerá e o programa terminará. O capítulo 11, "Tratamento de exceção: um exame mais profundo", discute como tornar seus programas robustos, permitindo que eles lidem com esses erros. Isso também é conhecido como tornar seu programa tolerante a falhas.
Na linha 18, colocamos o resultado da chamada ao método nextInt (um valor int) na variável number1 utilizando o operador de atribuição, =. 

## 2.5.7 Solicitando e inserindo um segundo int
A linha 20:
```java
System.out.print("Enter second integer: "); // prompt
```
solicita que o usuário insira o segundo inteiro. 

A linha 21:
```java
number2 = input.nextInt(); // prompt
```
lê o segundo inteiro e o atribuí à variável number2. 

## 2.5.8 Usando variáveis em um cálculo
A linha 23
```java
sum = number1 + number2; // adiciona números e, então, armazena o total na soma
```
é uma instrução de atribuição que calcula a soma das variáveis number1 e number2 e, então, atribui o resultado à variável sum utilizando o operador de atribuição, =. A instrução é lida como "sum *obtém* o valor de number1 + number2". Quando o programa encontra a operação de adição, ele executa o cálculo com base nos valores armazenados nas variáveis number1 e number2. Na instrução anterior, o operador de adição é um *operador binário* - seus *dois* operandos são as variáveis number1 e number2. As partes das instruções que contêm cálculos são chamados **expressões**. De fato, uma expressão é qualquer parte de uma instrução que tem um valor associado com ela. Por exemplo, o valor da expressão number1 + number2 é a soma dos números. Da mesma forma, o valor da expressão input.nextInc() é um inteiro digitado pelo usuário. 

## 2.5.9 Exibindo o resultado do cálculo
Depois que o cálculo foi realizado, a linha 25:
```java
System.out.printf("Sum is %d%n", sum); // exibe a soma

```
utiliza o método System.out.printf para exibir a **variável** sum. O especificador de formato %d é um *marcador de lugar* para um valor int (nesse caso, o valor de sum) - a letra d significa "inteiro decimal". 
Os cálculos também podem ser realizados dentro de instruções printf. Poderíamos ter combinado as instruções nas linhas 23 e 25 na instrução:
```java
System.out.printf("Sum is %d%n, (number1 + number2));
```
Os parênteses em torno da expressão number1 + number2 não são necessários - eles são incluídos para enfatizar que o valor da saída da expressão *inteira* é gerado na posição do especificador de formato %d. Diz-se que esses parênteses são **redundantes**.