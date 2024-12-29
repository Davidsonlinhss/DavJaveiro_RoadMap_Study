**Objetivos de aprendizado**
1. Declarando um array;
2. Inicializando um array;
3. Usando subscritos de variáveis com um array;
4. Declarando e utilizando arrays de objetos;
5. Procurando um array e utilizando arrays paralelos
6. Passando e retornando arrays dos métodos;
7. Classificando elementos de um array;
8. Utilizando arrays bidimensionais e outros multidimensionais;
9. Usando a classe Arrays;
10. Criando enumerations;

## 8.1 Declarando um Array;
Um array é uma lista nomeada de itens de dados, <span style="background:#d4b106">todos do mesmo tipo</span>. Cada item de dados é chamado de elemento do #array.

A declaração de um array segue o padrão de uma variável qualquer, porém inserimos um par de colchetes após o tipo de dados. Exemplo para declaração de um array de valores do tipo #double:
```java
double[] salesFigures;
```

Os arrays unidimensionais são os mais comuns em Java. A principal vantagem de um #array é que ele organiza os dados de tal forma  que á fácil tratarmos esses dados posteriormente, evitando código #boilerplate. 

De forma semelhante, podemos criar outro array de inteiros para armazenar os números de identificação dos alunos da seguinte maneira:
```java
int[] idNums; // javeiros
int idNumes[];
```

As duas formas de declaração acima são válidas. O formato mais utilizado e preferido por programadores Java é o <span style="background:#d4b106">com os colchetes (brackets) após o tipo da variável e antes do nome da variável</span>. 

Quanto a nomeação dos arrays, os programadores seguem uma das seguintes convenções para tornar mais óbvio que o nome representa um grupo de itens:
- Como arrays armazenam múltiplos itens, eles frequentemente são nomesados usando um substantivo no plural, como salesFigures;
- Às vezes, arrays são nomeados adicionando uma palavra final que implica um grupo, como salesList, salesTable ou SalesArray.

Uma diferença é que os arrays em Java são implementados como objetos. 

Após criarmos o array, precisamos reservar espaço na memória. Usamos o mesmo procedimento para criar um array que utiliza para criar um #objeto, afinal, são objetos...

Em Java, a criação de um objeto e a criação de um array seguem um processo semelhante.

Quando declaramos a #variável para um tipo de dados, como um #array ou #objeto, estamos apenas reservando um espaço na memória para a variável, mais ainda não criamos o objeto em si.

```java
double[] salesFigures; // espaço reservado na memória
```

Na declaração acima, apenas criamos um espaço em memória, não criamos o objeto de fato. Só criamos o objeto, de fato, quando utilizamos a palavra-chave #new e chama o construtor:

```java
double[] salesFigure = new double[10]; // declarando um array com uma única instrução
salesFigures = new double[10];
```

Uma das vantagens dos #arrays serem implementados como objetos é que eles podem ser alvos de coleta de lixo ou #garbare_collection, isso permite liberação na memória de objetos que não têm mais referências válidas apontando para eles. Logo, quando um array não está mais acessível, ele pode ser coletado pelo GC...

Em Java, o tamanho de um array, vem depois do tipo de dados e nunca é declarado imediatamente após o nome do array, como em outras linguagens...

A instrução:
```java
double[] salesFigure = new double[20];
```
reserva 20 locais de memória para 20 valores do tipo double. Podemos distinguir cada item de salesFigures dos outros com um subscrito. Um subscrito é um número inteiro contido dentro de colchetes que especifica um dos elementos de um array. Em Java, os elementos de qualquer array são numero a partir de 0, então, podemos legalmente usar qualquer subscrito de 0 a 19 ao trabalhar com um array que tem 20 elementos. 

![[Capítulo 8 - Arrays.png]]

Um subscrito é também chamado de #index. 

É um erro comum esquecer que o primeiro elemento de um array é o elemento 0. É comum esquecer que o subscrito do último elemento é um a menos que o tamanho do array e não o próprio tamanho do array.  

Quando trabalhamos com qualquer elemento individual de um #array, tratamos-o da mesma forma que se trata uma variável simples do mesmo tipo. Por exemplo, para atribuir um valor ao primeiro elemento de salesFigures em um #array, usamos uma simples instrução de atribuição:
```java
salesFigures[0] = 2100.00;
```

Para exibir o último elemento de um array de tamanho 20, podemos usar:
```java 
System.out.println(salesFigures[19]);
```

Alguns exemplos de declaração de um array:

```java
double[] moneyValues = new double[10];

final int NUMBER_ELS = 10;

double[] moneyValues = new double[NUMBER_ELS]; //

double[] moneyValues = new double[2 * 5];

double[] moneyValues = new double[getElements()]; // valor retornando um método que deveser um inteiro, neste exemplo...
```

---
**Duas verdades e uma mentira sobre declaração de Arrays**
1. A declaração int[] idNums = new int[35]; reserva memória suficiente para exatamente 34 inteiros; FALSO, a memória será reservada para os exatos 35 elementos...
2. O primeiro elemento de qualquer array terá subscrito de 0, não importa qual tipo de dados é armazenado; VERDADEIRO
3. Em java, podemos utilizar variáveis bem como uma constante para declarar o tamanho de um array; VERDADEIRO, desde que já tenham sido declaradas anteriormente e o valor atribuído seja um inteiro.

Os arrays são comuns em programação, pois nos permitem lidar facilmente com grandes quantidades de variáveis relacionadas. 

Os arrays também podem ser inicializados quando são criados. A forma geral de inicialização de um array unidimensional é:
```java
type array-name[] = { val1, val2, val3, val4};
```

## 8.2 Inicializando um Array
Uma variável que possui um tipo primitivo, como #int, armazena um valor. Já uma variável com um tipo de referência, como um array, armazena um endereço de memória onde um valor está armazenado. Em outras palavras, os nomes dos arrays contêm referências, assim como todos os nomes de objetos em Java. 

já declaramos array acima...

❯❯ Cada elemento em um array `int` recebe o valor 0.  
❯❯ Cada elemento em um array `double` ou `float` recebe o valor 0.0.  
❯❯ Cada elemento em um array `char` recebe o valor `\u0000`, que é o valor Unicode para um caractere nulo.
❯❯ Cada elemento em um array #boolean recebe o valor `false`.  
❯❯ Cada elemento em um array de #objetos, incluindo Strings, recebe `null`.

A forma de atribuir um valor diferente a um único elemento de um array:
```java
someNums[0] = 46;
```

Quando adicionamos dados na criação fornecendo uma lista de inicialização, não é necessário especificar o tamanho do array - o tamanho é atribuído com base no número de valores que adicionamos dentro da inicialização do array. Além disso, não precisamos usar a palavra chave #new, pois a nova memória é atribuída com base no comprimento da lista de valores fornecidos.

Em Java, não podemos inicializar diretamente uma parte de um array. Por exemplo, não é possível criar um array de 10 elementos e inicializar apenas cinco.

Em Java, os limites do array são impostos rigorosasmente em Java; é um erro de tempo de execução estar fora do limite de elementos de um array.

## 8.3 Uso de subscritos de variáveis com uma matriz
Se tratamos cada elemento do array como uma entidade individual, não há muita vantagem em declarar um array em vez de declarar variáveis individuais do tipo primitivo, como int, double ou char. 

Por exemplo, suponhamos que declaremos um array de cinco inteiros que armazena as notas de uma prova:
```java
int[] scoreArray = {2, 14, 35, 67, 85}
```

Suponhamos que o administrador da prova decida aumentar cada pontuação em 3 pontos para compensar uma questão que foi considerada inválida. Para aumentar cada elemento do array scoreArray em três pontos, devemos escrever o seguinte:
```java
final int INCREASE = 3;
scoreArray[0] += INCREASE;
scoreArray[1] += INCREASE;
scoreArray[2] += INCREASE;
scoreArray[3] += INCREASE;
scoreArray[4] += INCREASE;

```

Com cinco elementos no array scoreArray, essa tarefa é relativamente simples, exigindo apenas cinco instruções. No entanto, podemos reduzir a quantidade de código do programa usando uma variável como índice. Assim, podemos usar um loop para realizar a operação aritmética em cada elemento do array:
```java
final int INCREASE = 3;
for (int i = 0; i <scoreArray.length; i++) {
	scoreArray[i] += INCREASE;
}
```

Um processo que antes exigia cinco declarações, agora requerer apenas uma. Além disso, se o array tivesse 100 elementos, o primeiro método de aumentar os valores do array em 3, usando declarações separadas, resultaria em 95 declarações... sem condições. 

Por exemplo, suponha que você declare uma variável inteira chamada `sub`, um array chamado `scoreArray`, e uma constante nomeada `NUMBER_OF_SCORES`, da seguinte forma:
```java
int sub;
int[] scoreArray = {2, 14, 35, 67, 85};
final int NUMBER_OF_SCORES = 5;
```

Nós podemos declarar o mesmo array de forma a otimizar o processamento dos elementos do array, de acordo com o tamanho total do array:
```java
for(sub = 0; sub < NUMBERS_OF_SCORES; ++sub)
	scoreArray[sub] += INCREASE;
```
Se o tamanho do array mudar devido à remoção ou adição de elementos, precisamos apenas alterar o valor da constante nomeada uma vez, e todos os loops que utilizam esse constante serão automaticamente ajustados para realizar o número correto de repetições. 

Utilizando o canmpo length, o array mudará de forma apropriada. Quando trabalhamos com elementos de array, é sempre melhor usar uma constante nomeada ou o campo #length ao escrever o loop que manipula o array.

Um erro comum de programador é tentar usar #length  como um método de array. length() é um método usado para determinar o comprimento de uma #String. No entanto, com um array, #length não é um método; é um campo. Uma variável de instância ou campo de objeto. 

Quando criamos um array, Java é inteligente o suficiente para criar uma variável interna que armazena o tamanho do array, que pode ser acessar com o atributo **length**. 

---
**Nota**
<span style="background:#d4b106">Muitos programadores tradicionalmente utilizam i como um identificador para um subscrito pelo fato dele ser curto para o index. Outros acham que i é muito parecido com o número 1 e preferem outros tipos de identificadores para o loop.</span>

---
**Utilizando o Loop for aprimorado**
Java também oferece o laço for aprimorado. Esse laço permite percorrer um array sem precisar especificar os pontos de início e fim para a variável de controle do laço. Por exemplo, podemos usar umas das seguintes instruções para exibir cada elemento em um array chamado scoreArray:
```java
for (int sub = 0; sub < scoreArray.length; ++sub)
	System.out.println(scoreArray[sub]);
	
for (int val : scoreArray) 
	System.out.println(val);
```
Para cada val em scoreArray, exiba val. Veremos o laço for aprimorado sendo referido como um laço foreach. 


```run-java
public class Arrays_one {

    public static void main(String[] args) {

        int[] numbers = {1, 23, 512, 2};

        int min, max;

        min = max = numbers[0];

        for(int i = 2; i < 4; i++) {

            if(numbers[i] < min) min = numbers[i];

            if(numbers[i] > max) max = numbers [i];

        }

        System.out.println(min);

        System.out.println(max);

    }

}
```

O laço for-each percorre um conjunto de objetos, como um array, de maneira rigorosamente sequencial, do início ao fim. O laço for-each foi implementando com o lançamento do JDK 5.
A forma geral de escrever um laço for-each é a seguinte:
```java
for(tipo x : conjunto) bloco de instruções
```

O laço for-each elimina a necessidade de utilizarmos um contador de laço. 
A cada passagem do laço, x recebe automaticamente um valor igual ao próximo elemento de *conjunto*...

Lembrando, o laço no estilo for-each, percorre o conjunto de elementos (array), de forma sequencial, do índice menor ao maior. 

É possível encerrar o laço antes do fim do conjunto utilizando uma instrução #break. 
```java
        int[] numbers = {1, 2 , 3, 4, 5};
        int sum = 0;
        
        for(int x : numbers) {
            sum += x;
            if(x == 3) break;
            System.out.println(x);
        }
```

Há um ponto importante sobre o laço for-each, sua variável de iteração está associada ao array ao qual estamos iterando, porém é apenas de leitura. Não conseguimos alterar o valor de um array atribuindo um novo valor a variável de iteração. Logo, <span style="background:#d4b106">não podemos alterar os elementos da coleção.</span>

**Usando parte de um array**
Às vezes, não queremos usar todos os valores que estão dentro de um array. Por exemplo, suponhamos que escrevemos um programa que permite a um estudante inserir até 10 notas de teses e, em seguida, calcula e exiba a média. Para permitir notas de testes, criamos um array que possa armazenar 10 valores, mas como o estudante pode inserir menos de 10 valores, podemos usar apenas parte do array. 

O programa *AverageOfQuizzes* declara um array que pode armazenar 10 notas de testes. O usuário é solicitado a inserir a primeira nota; então, o laço #while começa a continuar enquanto o usuário não inserir 999. Dentro do laço, a nota inserida é colocada no array de notas, a nota é adicionada a um total acumulado e a contagem de notas inseridas é incrementada. Se a nota inserida for a décima, a nota é forçada a 999 e o laço termina; caso contrário, o usuário é solicitado a inserir a próxima nota. O laço #while verifica automaticamente para garantir que o usuário não inseriu 999 para sair. Quando o laço eventualmente termina, a variável #count contém o númeero de notas inseridas. Na primeira execução, apenas duas notas são inseridas antes do valor sentinela. Na segunda execução, todas as 10 notas permitidas são inseridas, então o usuário nunca insere um valor sentinela."

---
**Duas verdades e uma mentira usando Subscritos de Variáveis com um Array**
1. <span style="background:#affad1">Quando uma aplicação contém um array, é comum executar laços que variam a variável de controle do laço de 0 até um a menos que o tamanho do array.</span>
2. O campo de comprimento de um array contém o valor mais alto que pode ser usado como subscrito  do array. Falso, o campo de comprimento de um array não contém o valor mais alto que pode ser usado como subscrito do array, em vez disso, ele contém o número total de elementos no array. O valor mais alto que pode ser usado como subscrito é #length -1.
3. <span style="background:#affad1">O laço for aprimorado permite percorrer um array sem especificar os pontos de início e término para a variável de controle do laço.</span>

## 8.4 Declarando e utilizando Arrays de Objetos
Assim como podemos declarar arrays de tipos simples, como #int ou #double, também podemos declarar arrays que contêm elementos de objetos. Por exemplo, suponhamos que criemos a classe Employee, esta classe possui dois campos de dados (empNum e empSal), um construtor e um método #get para cada campo. 

Podemos criar objetos Employee separados com nomes únicos, como os seguintes:
`Employee painter, Employee electrician, plumber;`
`Employee firstEmployee, secondEmployee, thirdEmployee;`

No entanto, em muitos programas é muito mais conveniente criar um array de objetos Employee. Um array chamado emps que contém sete objetos Employee pode ser definido como:
```java
Employee[] emps = new Employee[7];
```

Essa declaração reserva memória suficiente no computador para sete objetos Employee nomeados emps[0] até emps[6]. No entanto, a declaração não constrói realmente esses objetos Employee. Ao invés disso, devemos chamar os sete construtores individuais. De acordo com a definição da classe, o construtor Employee requer dois argumentos: um número de identificação do empregado **empId** e um salário **empSal**, construindo laço que constrói os empregados:
```java
final int start_NUM = 101;
final double STARTING_SALARY = 20.000;
for(int x = 0; x < emps.length; ++x)
	emps[x] = new Employee(Start_NUM +x, STARTING_SALARY);
```

**Usando o Laço For Aprimorado com Objetos**
Podemos usar o laço for aprimorado para percorrer um array de objetos. Por exemplo, para exibir os dados de sete Employees armazenados no array emps...

**Manipulando Arrays de String**
Assim como qualquer outro objeto, podemos criar um array de objetos String. Podemos armazenar três nomes de departamentos da empresa da seguinte maneira:
```java
String[] deptNames = {"Contabilidade", "Recursos Humanos", "Vendas"};
```

Podemos acessar esses nomes de departamentos como um subscrito, como qualquer outro objeto Array. 
```java
for(int a = 0; a < deptNames.length; ++a)
	sout(deptNames[a]);
```
Uma distinção: 
- Arrays usam o campo length para indicar o número de elementos no array;
- Objetos String usam um método length() para indicar o número de caracteres na String. 
Por exemplo, se deptNames[0] é "Contabilidade", então deptNames[0].length() é 10 porque "Contabilidade " contém 10 caracteres. 

Strings em Java são objetos. Por exemplo, na instrução:
```java
System.out.println("DavJaveiro, o maior noob da bolha Java.")
```
o string é convertido automaticamente em um objeto #String por Java. 

#### Construindo Strings
Podemos construir um String como construiria qualquer outro tipo de objeto: usando o #new e chamando o construtor de #String. Por exemplo:
```java
String st = new String("Hello");
```
Outra maneira fácil de criar um String é da forma tradicional:
```java
String st = "Java strings are powerful.";
```

Uma vez que tivermos criado um objeto #String, poderemos utilizá-lo em qualquer local em que um string entre aspas for permitido. 

#### Operando com strings
A classe #String contém vários métodos que operam com strings. Aqui estão as formas gerais de alguns:

| boolean equals(str)  | Retorna verdadeiro se o string chamador tiver a mesma sequência de caracteres de str.                                                                               |     |
| -------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --- |
| int length()         | Obtém o tamanho de um string.                                                                                                                                       |     |
| char charAt(index)   | Obtém o caractere do índice especificado por index.                                                                                                                 |     |
| int compareTo(str)   | Retorna menor do que zero se o string chamador for menor do que str, maior do que zero se o string chamador for maior do que str e zero se os strings forem iguais. |     |
| int indexOf(str)     | Procura no string chamador o substring especificado por str. Retorna o índice da primeira ocorrência ou -1 em caso de falha.                                        |     |
| int lastIndexOf(str) | Procura no string chamador o substring especificado por str. Retorna o índice da última ocorrência ou -1 em cada de falha.                                          |     |
|                      |                                                                                                                                                                     |     |

## 8.5 Buscando em um Array e utilizando Arrays Paralelos
Suponhamos que uma empresa fabrique 10 itens. Quando um cliente faz um pedido de um item, precisamos determinar se o número do item no formulário de pedido é válido. <span style="background:#d4b106">Quando precisamos determinar se uma variável contém um dos muitos valores válidos</span>, uma opção é usar uma série de instruções #if para comparar a variável a uma série de valores válidos. Se os números dos itens válidos forem sequenciais, como de 101 a 110, a seguinte instrução #if simples que usa uma expressão lógica AND pode verificar o número do pedido e definir um campo Booleano como verdadeiro:
```java
final int LOW = 101;
final int HIGH = 110;
boolean isValidItem = false; // item inicia de forma não válida
if(itemOrdered >= LOW && itemOrdered <= HIGH)
	isValidItem = true; // se o item estiver dentro da faixa de valores, assegurando que o iten seja maior do que LOW e menor do que HIGH para satisfazer a condição da expressão lógica AND;
```
Se ambas as condições forem válidas, isValidItem é alterado para true; 

Quando os números dos itens válidos não são sequencias (por exemplo, 101, 108, 210), podemos usar uma série de instruções if-else para verificar cada número. Isso resulta em código aninhado e potencialmente longo, como mostrado abaixo:
```java
if(itemOrdered == 101)
	isValidItem = true;
else if(itemOrdered == 108)
	isValidItem = true;
else if(itemOrdered == 201)
	isValidItem = true;
```

Mas há uma solução mais elegante. Podemos comparar a variável itemOrdered a uma lista de valores em um array. Chamamos isso de pesquisa em um array:
Podemos iniciar um array com valores válidos:
```java
int[] validValues = {101, 108, 201, 213, 266, 304, 311, 409, 411, 412}
```

Neste caso, podemos usar um laço for ou uma abordagem mais moderna como o método #contains em uma lista...


#### Utilizando Arrays Paralelos
Se configurarmos outro array com o mesmo número de elementos e dados correspondentes, pode usar o mesmo índice para acessar informações adicionais. Um array paralelo é aquele com o mesmo número de elementos que outro, e para qual os valores nos elementos correspondentes estão relacionados. Por exemplo, se os 10 itens que sua empresa oferece têm 10 preços diferentes, podemos configurar um array para armazenar esses preços da seguinte maneira:
```java
double[] prices = {0.29, 1.23, 3.50, 0.69};
```

Os preços devem aparecer na mesma ordem que seus números de itens correspondentes no array validValues. Agora, o mesmo laço for que encontra o número do item válido, também encontra o preço. 

#### Pesquisando um Array para uma Correspondência de Intervalo
Pesquisar um array para uma correspondência exata nem sempre é prático. Suponhamos que nossa empresa ofereça descontos aos clientes com base na quantidade de itens pedidos. Talvez nenhum desconto seja dado para qualquer pedido de menos de uma dúzia de itens, mas descontos crescentes estão disponíveis para pedidos de quantidades crescentes como na tabela 8-1. 

### Tabela 8-1: Tabela de Descontos

| QUANTIDADE TOTAL PEDIDA | DESCONTO |
|-------------------------|----------|
| 1 a 12                  | Nenhum   |
| 13 a 49                 | 10%      |
| 50 a 99                 | 14%      |
| 100 a 199               | 18%      |
| 200 ou mais             | 20%      |
Quando numOfItem for igual a 13, discounts[ numOfItems ] será de 0.10. 

Uma opção melhor é criar dois arrays correspondentes e realizar uma correspondência de intervalo, na qual comparamos um valor com os pontos finais de itnervalos numéricos para encontrar a categoria à qual um valor pertence. Por exemplo,um array pode conter as cinco taxas de desconto e o outro array pode conter cinco limites de intervalo de desconto. 

Resumindo:
Podemos criar duas matrizes paralelas:
1. Representando a quantidade de itens;
2. Representando a quantidade de desconto oferecido com base na anterior;
Mas essa não é uma solução muito viável.

A solução mais viável seria usar dois arrays paralelos também:
1. discountRates: armazenando as taxas de desconto;
2. discountRangeLimits: armazenando os limites dos intervalos de quantidade para os descontos;
```java
double[] discountRates = { 0.0, 0.10, 0.14, 0.18, 0.20};
int[] discountRangeLimits = { 1, 13, 50, 100, 200};
```


## 8.6 Passando e Retornando Arrays de métodos
Podemos usar qualquer elemento individual de um array da mesma forma que usa qualquer variável única do mesmo tipo. Ou seja, se declaramos um array de inteiros como int[] someNums = new Int[12]; podemos exibir someNums[0], ou incrementar someNums[1], ou trabalhar com qualquer elemento exatamente com faz com qualquer inteiro. 

Observamos a aplicação:
- A aplicação cria um array de quatro inteiros e os exibe;
- Chama o método methodGetsOneInt() quatro vezes, passando cada elemento por vez;
- O método exibe o número, altera o número para 999 e depois exibe o número novamente;

Os quatro números alterados no método methodGetsOneInt() permanecem inalterados no main() após a execução do método. A variável `one` é local ao método methodGetsOneInt() e quaisquer mudanças nela não são permanentes e não se refletem no array no main(). Cada variável `one` no método contém apenas <span style="background:#d4b106">uma cópia do elemento do array</span> passado para o método. Os elementos individuais do array são passados por valor, ou seja, <span style="background:#d4b106">uma cópia do valor é feita</span> e usada dentro do método que recebe. 

Como todos os objetos não primitivos, são tipos de referência, isso significa que o objeto realmente mantém um endereço de memória onde os valores são armazenados. Quando passamos um array (ou seja, passamos seu nome) para um método, o método receptor obtém uma cópia do endereço de memória real do array. Isso significa que o método receptor tem acesso e a capacidade de alterar os valores originais nos elementos do array no método que fez a chamada.

- **<span style="background:#d4b106">Passagem por Valor</span>**:
    
    - **Tipos Primitivos**: A cópia do valor é passada. Alterações feitas na cópia não afetam o valor original.
        
- <span style="background:#d4b106">**Passagem por Referência**</span>:
    
    - **Arrays e Objetos**: A referência é passada. Alterações feitas na referência afetam diretamente o objeto original.

Um método pode retornar uma referência a um array, e quando isso acontece, incluímos colchetes com o tipo de retorno no cabeçalho do método.  

Quando retornamos um array de um método, o processo é um pouco diferente de retornar um valor primitivo ou um objeto simples. 

## Classificação de Elementos de uma Matriz
Ordenar é o processo de arranjar uma série de objetos em alguma ordem lógica. Por exemplo, podemos querer ordenar uma lista de nomes em ordem alfabética pela sua letra inicial, de A a Z, ou podemos querer ordenar uma série  de preços do mais alto para o mais baixo. Quando colocamos objetos em ordem começando pelo objeto que tem o menor valor, estamos ordenando em ordem crescente; inversamente, estamos ordenando em ordem decrescente.

A ordenação mais simples possível envolve dois valores. Se os valores estão fora de ordem e queremos colocá-los em ordem, devemos trocar os dois valores. 

Suponhamos que tenhamos duas variáveis - valA e valB - e, além disso, suponha que valA = 16 e valB = 2. Para trocar os valores das duas variáveis, não podemos simplesmente usar o seguinte código:
```java
valA = valB;
valB = valA;
```
valA conterá o valor de valB. Quando executamos a segunda instrução de atribuição, valB = valA; cada variável ainda mantém o valor de 2.

A solução que permite reter ambos os valores é usar uma variável para armazenar o valor de valA e uma memória temporária durante a troca:
```java
temp = valA; // 16 vai para temp
valA = valB; // 2 vai para valA
valB = temp; // 16 vai para valB
```
Usamos uma variável para armazenar o valor e só depois trocamos.

Se quisermos ordenar dois valores, valA e valB, em ordem crescente para que valA seja o valor menor, usaremos a seguinte instrução #if para decidir se deve trocar. Se valA for maior que valB, os valores serão alterados. Se valA não for maior que valB, a troca não ocorrerá. 

```java
if(valA > valB) {
	temp = valA;
	valA = valB;
	valB = temp;
}
```

Precisamos de uma variável temporária para salvar o valor de uma das variáveis durante a troca. 

Ordenar dois valores é uma tarefa relativamente simples; ordenar mais valores é mais complicado, especialmente se tentarmos usar uma série de decisões.  A tarefa se torna gerenciável quando sabemos como usar um array. Vários algoritmos de ordenação foram desenvolvidos; um algoritmo é um processo ou conjunto de etapas que resolvem um problema. 

**Utilizando o Algoritmo Bubble Sort**
No algoritmo de ordenação bolha em ordem crescente, comparamos repetidamente pares de itens, trocando-os se estiverem fora de ordem, e eventualmente criando uma lista ordenada. Não é a técnica mais rápida nem mais eficiente, mas é uma das mais simples de compreender e proporciona um entendimento mais profundo da manipulação de elementos de array. 

Para usar uma ordenação bolha, colocamos os valores originais, não ordenados, em um array. Você compara os dois primeiros números; se eles não estiverem em ordem crescente, você os troca. Comparamos o segundo e o terceiros números; se não estiverem em ordem crescente, os trocamos. Continuamos descendo na lista, e para cada posição x, se o valor na posição x + 1 não for maior, devemos trocar os dois valores.
