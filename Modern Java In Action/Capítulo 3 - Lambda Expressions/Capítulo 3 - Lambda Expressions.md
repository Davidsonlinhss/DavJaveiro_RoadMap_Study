*This chapter covers*
- Lambdas in a nutshell
- Where and how to use lambdas
- The execute-around pattern
- Functional interfaces, type inference
- Method references
- Composing lambdas

No capítulo anterior nós vimos que passar código utilizando a **parametrização de comportamento** é útil para lidar com *mudanças frequentes* nos requisitos do nosso código. Isso permite que definamos um bloco de código que represente um comportamento e, em seguida, o passe adiante. Podemos executar esse bloco de código quando um determinado evento ocorrer (por exemplo, um clique em um botão) ou em determinados pontos de um algoritmo (por exemplo, um predicado como "apenas maçãs mais pesadas que 150g" no algoritmo de filtragem ou a operação de comparação personalizada em uma ordenação). De forma geral, utilizando esse conceito, podemos escrever um código mais flexível e reutilizável. 

Utilizar **classes anônimas** para representar diferentes comportamentos é insatisfatório. É algo verboso, o que não incentiva os programadores a utilizarem a parametrização de comportamento na prática. Neste capítulo, vamos apresentar um novo recurso do Java 8 que resolve esse problema: **expressões lambda.** Elas permitem representar um comportamento ou passar código de forma concisas. Por enquanto, podemos pensar nas expressões lambda como **funções anônimas** - **métodos sem nomes declarados**, mas que também **podem ser passados como argumentos** para outro método, assim como ocorre com classes anônimas. 

Vamos mostrar como construir expressões lambda, onde utilizá-las e como tornar seu código mais conciso com o uso delas. Também explicaremos algumas novidades, como *inferência de tipos* e *interfaces* importantes adicionadas na API do Java 8. Por fim, introduzimos as referências a métodos, um recurso útil que complementa perfeitamente as expressões lambda. 

Este capítulo foi organizado para ensinar, passo a passo, como escrever um código mais conciso e flexível. Ao final, reunimos todos os conceitos ensinados em um exemplo concreto: retomamos o exemplo de ordenação apresentando no capítulo 2 e o aprimoramos gradualmente, utilizando expressões lambda e referências a métodos para torná-lo mais conciso e legível. Este capítulo é importante por si só e também porque você usará expressões lambda extensivamente ao longo do livro. 

## 3.1 *Lambdas in a nutshell*
Uma expressão lambda pode ser entendida como uma representação concisa de uma função anônima que pode ser passada adiante. Ela não **possui um nome**, mas **tem uma lista de parâmetros**, um corpo, um tipo de **retorno** e, possivelmente, uma **lista de exceções** que podem ser lançadas. Essa é uma definição ampla; vamos dividi-la:

- **Anônima:** dizemos que é anônima porque *não possui um nome explícito* como um método normalmente teria; ou seja, menos para escrever e se preocupar!
- **Função:** chamamos de função porque uma lambda não está associada a uma classe específica como um método está. No entanto, <span style="background:#d4b106">assim como um método</span>, uma lambda possui uma lista de *parâmetros*, um *corpo*, um *tipo de retorno* e uma lista *possível de exceções* que podem ser lançadas.
- **Passável:** uma expressão lambda *pode ser passada como argumento* para um método ou *armazenada em uma variável* estamos nos referindo ao conceito de **funções de primeira classe** (first-class function).
- **Concisa:** não é necessário escrever muito código repetitivo como ocorre com **classes anônimas**.

Mas de onde vem o termo #lambda? Ele se origina de um sistema desenvolvido na academia chamado #lambda-calculus, usado para descrever cálculos. Passar código atualmente é uma tarefa tediosa e verbosa em Java. Lambdas resolvem esse problema, permitindo que passemos código de uma maneira mais concisa.

Agora não precisamos escrever códigos desajeitados usando classes anônimas para se beneficiar da parametrização de comportamento.

As expressões lambda encorajarão a adotarmos o estilo de parametrização de comportamento já utilizados no capítulo anterior. 

Por exemplo, podemos criar um objeto *Comparator* personalizado de maneira muito mais concisa:

*Antes*
```java
Comparator<Apple> byWeight = new Comparator<Apple>() {
	public int compare(Apple a1, Apple a2) {
		return a1.getWeight().compareTo(a2.getWeight());
	}
};
```

*Depois (com expressões lambdas)*:
```java
Comparator<Apple> byWeight = (Apple a1, Apple a2) -> 
        a1.getWeith().compareTo(a2.getWeight());
```

Observamos que estamos literalmente passando o código necessário para comparar duas maçãs com base no pedo delas. 

Podemos mostrar a expressão lambda em três partes:
- <font color="#d7e3bc">(Apple a1, Apple a2)</font> - #parameters, reflete os parâmetros do método *compare* de um *Comparator*, ou seja, duas instâncias de *Apple*.


- <font color="#d7e3bc">-></font> - #Arrow, separa a lista de *parâmetros* do *corpo da expressão* lambda.


- <font color="#d7e3bc">a1.getWeight().compareTo(a2.getWeight())</font> - #body compara duas instâncias de *Apple* com base no peso delas. A expressão é considerada o valor de retorna da lambda.

#### Exemplos de expressões lambdas válidas

- Fornece um parâmetro do tipo String e retorna um #int. Não há declaração de retorno, pois o retorno está implícito. O compilador consegue deduzir o tipo de retorno de uma expressão lambda com base no contexto em que ela é usada. O método #length retorna um int, logo, o compilador deduz que a lambda também retorna um #int.
```java
(String s) -> s.lenght()
```

- Fornece um parâmetro do tipo Apple e retorna um valor #boolean (se a maçã for mais pesada do que 150g):
```java
(Apple a) -> a.getWeight() > 150
```

- Fornece dois parâmetros do tipo #int e não retorna nenhum valor ( #void return). Seu body contém duas declarações:
```java
(int x, int y) -> {
	System.out.println("Result:");
	System.out.print(x + y);
}
```

- Não fornece parâmetro e retorna o inteiro 42:
```java
() -> 42;
```

- Fornece dois parâmetros do tipo Apple e retorna um int representando a comparação dos seus tamanhos:
```java
(Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight())
```

Essa sintaxe foi escolhida pelos designs da linguagem Java porque foi bem recebida em outras como C# e Scale. O JavaScript também possui uma sintaxe semelhante. A sintaxe básica de uma lambda é (referida como uma lambda no estilo de expressão):

**(parametros)** -> *{declarações;}*

A declaração abaixo não é uma expressão válida:
```java
(String s) -> {"Iron Main";}
```
"Iron Man" é uma expressão, não uma declaração. Para tornar essa lambda válida, precisamos remover os chaves e o ponto e vírgula: <span style="background:#b1ffff">(String s) -> "Iron Man"</span>. Ou, se preferirmos, podemos utilizar uma declaração de retorno explícita como: <span style="background:#b1ffff">(String s) -> { return "Iron Man"; }</span>;

## 3.2 Where and how to use lambdas
Onde podemos usar expressões lambdas? No exemplo anterior, atribuímos uma lambda a uma variável do tipo *Comparator< Apple >*. Também podemos usar outra lambda com o método *filter* que implementamos no capítulo anterior:
```java
List<Apple> greenApples =
filter(inventory, (Apple a) -> GREEM.equals(a.getColor()));
```

Onde exatamente podemos usar lambdas? Podemos usar uma expressão lambda no contexto de uma *interface funcional*. No código mostrado aqui, podemos passar uma lambda como segundo argumento para o método *filter* porque ele espera um objeto do tipo *Predicate< T>*, que é uma interface funcional. 

### 3.2.1 Functional interface
Lembremos da interface *Predicate< T>* que criamos no capítulo 2 para poder parametrizar o comportamento do método *filter*. Ela é uma interface funcional! Pois a *Predicate* <span style="background:#b1ffff">específica apenas um método abstrado</span>:
```java
public interface Predicate<T> {
	boolean test(T t);
}
```

Em resumo, uma *interface funcional* é uma interface que <span style="background:#affad1">especifica exatamente um método abstrato</span>. Já conhecemos outras interfaces funcionais na API do Java, como *Comparator* e *Runnable*
```java
public interface Comparator<T> {
	int compare(T o1, T o2);
}

public interface Runnable {
	void run();
}

public interface ActionListener extends EventListener {
	void actionPerformed(ActionEvent e);
}

public interface Callable<V> {
	V call() throws Exception;
}

public interface PrivilegedAction<T> {
	T run();
}
```

---
**Nota**
Veremos no capítulo 13 que as interfaces agora também podem ter métodos *default* (um método com corpo que fornece uma **implementação padrão** para um método caso ele não seja implementado por uma classe). Uma interface ainda é uma interface funcional se ela tiver muitos métodos *default*, desde que ela especifique apenas um método abstrato.

---

Um exemplo, clássico de interfaces que não são interfaces funcionais:
```java
public interface Adder {
	int add(int a, int b);
}
```

```java
public interface SmartAdder extends Adder {
	int add(double a, double b);
}
```

```java
public interface Nothing {
}
```
Apenas *Adder* is a functional interface.

*SmartAdder* isn't a functional interface because it specifies **two abstract methods** called *add* (one is inherited (herda) from *Adder*).

*Nothing* isn't a functional interface because it declares no abstract method at all (at all = em momento algum).

Logo, a regra é clara, uma interface funcional deve ter exclusivamente um único método abstrato.

O que podemos fazer com interfaces funcionais? As expressões lambda permitem que forneçamos a implementação do *método abstrato* de uma **interface funcional** diretamente no local e <span style="background:#b1ffff">trate toda a expressão como uma instância de uma interface funcional</span> (mais tecnicamente falando, uma instância de uma implementação concreta da interface funcional). Podemos alcançar a mesma coisa com uma classe anônima interna, embora fosse mais complicado: fornecemos uma implementação e a instancia diretamente no local. O código a seguir é válido porque *Runnable* é uma interface funcional que define apenas um método abstrato, *run*:
```java
// Uses a lambda
Runnable r1 = () -> System.out.println("Hello World 1");

// uses an anonymous class
Runnable r2 = new Runnable() {
	public void run() {
		System.out.print("Hello World 2");
	}
};

public static void process(Runnable r) {
	r.run();
}
process(r1); // prints hello world 1
process(r2); // Prints Hello World 2
process(() -> System.out.println("Hello World 3")); // Prints Hello World 3
```

### 3.2.2 Function descriptor
A assinatura do <span style="background:#affad1">método abstrato da interface funcional</span> descreve a *assinatura da expressão lambda*. Chamamos esse método abstrato de descritor de função. Por exemplo, a interface *Runnable* pode ser vista como a assinatura de uma função que não aceita nada e não retorna nada (void), porque ela possui apenas um método abstrato chamado *run*.

```java
public interface Runnable {
	void run();
}
```

Usamos uma anotação especial ao longo deste capítulo para descrever as assinaturas de lambdas e interfaces funcionais. A notação *() -> void* representa uma função com uma lista de parâmetros vazia que retorna void. Isso é exatamente o que a interface *Runnable* representa. Como outro exemplo, *(Apple, Apple) -> int* denota uma função que recebe dois objetos *Apple* como parâmetros e retorna um *int*. Forneceremos mais informações sobre descritos de funções.

Como as expressões lambda são verificadas em termos de tipo? Explicamos como *o compilador verifica se uma lambda é válida em um determinado* contexto na seção 3.5. Por enquanto, basta entender que uma expressão lambda pode ser atribuída a uma variável ou passada para um método que espera uma interface funcional como argumento, **desde que a expressão lambda tenha a mesma assinatura do método abstrato da interface funcional**. Por exemplo, em nosso exemplo anterior, podemos passar uma lambda diretamente para o método *process* da seguinte maneira:
```java
public void process(Runnable r) {
	r.run();
}

process(() -> Sustem.out.println("This is awesome!!"));
```

This code when executed will print "This is awesome!!" The lambda expression () -> System.out.println("This is awesome!!") takes no parameters and returns void. This is exactly the signature of the run method defined in the Runnable interface. 

---
**Lambdas and void method invocation**
Embora isso possa parecer estranho, a seguinte expressão lambda é válida:
```java
process(() -> System.out.println("This is awesome"));
```

Afinal, System.out.println retorna *void*, então isso claramente não é uma expressão! Por que não precisamos envolver o corpo com chaves, como neste exemplo?
```java
process(() -> { System.out.println("This is awesome"); });
```
Acontece que há uma regra especial definida na *Java Language Specification* para chamadas de métodos que retornam void. Não é necessário envolver uma única chamada de método *void* em chaves.

Por que podemos passar uma expressão lambda apenas onde uma *functional interface* é esperada?

Os projetistas da linguagem consideraram abordagens alternativas, como adicionar *function types* (algo semelhante à notação especial que usamos para descrever a assinatura das expressões lambda - revisaremos esse tópico nos capítulos 20 e 21) ao Java. No entanto, escolheram o modelo atual porque ele se encaixa naturalmente sem aumentar a complexidade da linguagem.

Além disso, a maioria dos programadores Java já estão familiarizados com a ideia de uma interface com um único método abstrato (como no caso do tratamento de eventos). Entretanto, a razão mais importante é que as *functional interfaces* já eram amplamente usadas antes do Java 8. Isso significa que elas oferecem um bom caminho de migração para o uso de expressões lambda. 

Na verdade, se já utilizamos *functional interfaces* como *Comparator* e *Runnable*, ou até mesmo suas próprias interfaces que definem apenas um único método abstrato, agora pode usar expressões lambda sem precisar alterar suas APIs.

---
**What about @FunctionalInterface?**
Se explorarmos a nova API do Java, perceberemos que as interfaces funcionais são geralmente anotadas com @FunctionalInterface. 

Essa anotação é usada para indicar que a interface foi projetada para ser uma *functional interface*, sendo, portanto, útil para documentação. Além disso, o compilador retornará um erro significativo caso definamos uma interface com a anotação *@FunctionalInterface*, mas ela não seja uma functional interface. Por exemplo, uma mensagem de erro como "Multiple non-overring abstract methods found in interface Foo" pode indicar que há mais de um método abstrato disponível. 

Vale destacar que a anotação *@FunctionalInterface* não é obrigatória, mas é uma boa prática utilizá-la quando uma interface é projetada para esse propósito. Podemos pensar nela como uma anotação @Override, que indica que um método está sendo sobrescrito. 

## 3.3 Putting lambdas into practice: the execute-around pattern
Vamos analisar um exemplo de como lambdas, junto com a parametrização de comportamento (behavior parameterization), podem  ser usados na prática para tornar seu código mais flexível e conciso.

Um padrão recorrente no processamento de recursos (por exemplo, ao lidar com arquivos ou banco de dados) é abrir um recurso, realizar algum processamento nele e, em seguida, fechar o recurso. As fases de configuração *setup* e limpeza *cleanup* são sempre semelhantes e envolvem o código principal que realiza o processamento. Isso é chamado de #execute-around-pattern, como ilustrado na figura 3.2.

![[Capítulo 3 - Lambda Expressions.png]]

No código a seguir, as linhas destacadas mostram o código repetitivo *boilerplate* necessário para ler uma linha de um arquivo (observe também que usamos a instrução try-with-resources do Java 7), que já simplifica o código, pois não é necessário fechar o recurso explicitamente:
```java
public String processFile() throws IOException {
	try (BufferedRead br = new BufferedReader(New FileReader("data.txt"))) {
		return br.readLine();
	}
}
```
### 3.3.1 Step 1: Remember behavior parameterization
O código atual é limitado. Você só consegue ler a primeira linha do arquivo. E se você quiser retornar as duas primeiras linhas ou até mesmo a palavra mais usada no arquivo? Idealmente, seria bom reutilizar o código de configuração *setup* e limpeza *cleanup*, dizendo ao método #processFile para que ele possa executar ações diferentes utilizando um #BufferedReader. Passar comportamentos é exatamente o propósito das lambdas. Precisamos parametrizar o comportamento do *processFile* para que ele possa executar comportamentos diferentes usando um *BufferedReader*.

Passar comportamento é exatamente para isso que as lambdas servem. Como um novo método *processFile* deve se parecer se você quiser ler duas linhas de uma vez? Vamos precisar de uma lambda que recebe um *BufferedReader* e retorne uma String. Por exemplo, imprimindo em duas linhas:
```java
String result = processFile((BufferedReader br) -> br.readLine() + br.readLine());
```

### 3.3.2 Step 2: Usa a functional interface to pass behaviors
Explicamos anteriormente que as lambdas só podem ser usadas no contexto de uma interface funcional. Precisamos criar uma interface que corresponde à assinatura *BufferedRead -> String* e que possa lançar uma *IOException*. Vamos chamar essa interface de *BufferedReaderProcessor*:
```java
@FunctionalInterface
public interface BufferedReaderProcessor {
	String processFile(BufferedReader b) throws IOException;
} 
```
Essa é a linha que faz o trabalho útil.
*BufferedReaderProcessor* é a interface funcional que define um método *process*, que recebe um *BufferedReader* e retorna uma String, podendo lançar uma *IOException*.

*processFile*: o método recebe um objeto do tipo *BufferedReaderProcessor* como argumento, permitindo passar comportamentos diferentes de processamento de arquivo de forma flexível com lambdas.

### 3.3.3 Step 3: Execute a behavior!
Qualquer lambda da forma *BufferedReader -> String* pode ser passado como argumento, pois corresponde à assinatura do método *process* definido na interface *BufferedReaderProcessor*. Agora, você só precisa de uma forma de executar o código representado pela lambda dentro do corpo do método *processFile*.  

Lembre-se: **expressões lambda** permitem fornecer diretamente a **implementação do método** **abstrato** de uma *interface funcional* de forma #inline, tratando toda a expressão como uma instância da interface funcional. Assim, é possível chamar o método **process** no objeto *BufferedReaderProcessor* resultante dentro do corpo do método *processFile* para realizar o processamento:
```java
public String processFile(BufferedReaderProcessor p) throws IOException {
    try (BufferedReader br = 
        new BufferedReader(new FileReader("data.txt"))) {
        return p.process(br);
    }
}
```

**try-with-resources** garante que o recurso *BufferedReader* seja fechado automaticamente após o uso, tornando o código mais seguro e conciso.

### 3.3.4 Step 4: Pass lambdas
Agora, podemos reutilizar o método *processFile* e processar arquivos de diferentes maneiras, passando diferentes lambdas.

O exemplo a seguir demonstra o processamento de uma linha:
```java
String oneLine = processFile((BufferedReader br) -> br.readLine());
```

Método para processamento de duas linhass:
```java
String oneLine = processFile((BufferedReader br) -> br.readLine()  + br.readLine());
```

A figura 3.3 abaixo resume os quatro passos realizados para tornar o método *processFile* mais flexível.

**Etapa 1**: Criação de um método *processFile* básico, que lê apenas a primeira linha de um arquivo usando #BufferedReader. 
```java
public String processFile() throws IOException {
	try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
		return br.readLine();
	}
}
```

**Etapa 2**: definição da *interface funcional* **BufferedReaderProcessor** para parametrizar o comportamento. Essa interface tem um único método abstrato *process* que recebe um *BufferedReader* e retorna uma #String.
```java
public interface BufferedReaderProcessor {
	String process (BufferedReader b) throws IOException;
}

```

**Etapa 3:** alteração do método *processFile* para receber uma instância de *BufferedReaderProcessor* como parâmetro, permitindo processo o arquivo de diferentes formas ao executar o método *process*.
```java
public String processFile(BuffereadReaderProcessor p) throws IOException {
	try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
		return p.process(br); // método process pertencente ao objeto BufferedReader
	}
}
```

**Etapa 4:** utilização de lambdas para passar diferentes comportamentos ao método *processFile*. Exemplos mostram como ler uma linha ou duas linhas do arquivo.
```java
String oneLine = processFile((BufferedReader br) -> br.readLine());
String twoLines = processFile((BufferedReader br) -> br.readLine() + br.readLine());
```

## 3.4 Using functional interfaces
Como aprendemos na seção 3.2.1, uma interface funcional especifica exatamente um único método abstrato. Interfaces funcionais são úteis porque a **assinatura do método abstrato** pode descrever a assinatura de uma expressão lambda. A assinatura do método abstrato de uma interface funcional é chamada de *descritor de função* ou *function descriptor*. Para usar diferentes expressões lambda, precisamos de um conjunto de interfaces funcionais que possam descrever descritores de função comuns.  

Várias interfaces funcionais já estão disponíveis na API Java, como #Comparable, #Runnable e #Callable. 

Os designers da biblioteca Java para o Java 8 facilitaram ainda mais, introduzindo várias novas interfaces funcionais no pacote #java-util-function. Vamos descrever as *interfaces* #Predicate, #Consumer e #Function. 

### 3.4.1 Predicate
 A interface #java-util-function-PredicateT define um método abstrato chamado *test*, que aceita um objeto do tipo genérico *T* e retorna um *boolean*.  É a mesma que criamos anteriormente, mas já está disponível diretamente na API! Podemos usar essa interface quando precisarmos representar uma expressão booleana que utiliza um objeto do tipo *T*. Por exemplo, é possível definir uma lambda que aceita objetos do tipo *String*, como mostrado no exemplo a seguir:
```java
@FunctionalInterface
public interface Predicate<T> {
	boolean test(T t);
}

public <T> List<T> filter(List<T> list, Predicate<T> p) {
	List<T> results = new ArrayList<>();
	for(T t: list) {
		if(p.test(t)) { // para cada t em list, (if = true), add t in results
			results.add(t); // add se atender ao critério
		}
	}
	return results; // retorna os elementos que passaram no filtro
}
Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty(); // verifica se não é vazia
List<String> nonEmpty = filter(listOfStrings, nonEmptyStringPredicate);
// filter percorre cada string na lista listOfStrings
// Para cada string, ele chama nonEmptyStringPredicate.test(s);
// no final, nonEmpty contém apenas as strings não vazias da lista original


// Lista de string com algumas vazias
List<String> listOfStrings = Arrays.asList("Java", "", "Predicate", "Lambda", "");


// Definindo o predicado para verificar strings não vazias
Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();

// Filtrando a lista com base no predicado
List<String> nonEmpty = filter(listOfStrings, nonEmptyStringPredicate);

// Exibindo o resultado
System.out.println("Lista original: " + listOfStrings);
System.out.println("Lista sem strings vazias: " + nonEmpty);


```
Fazendo uma analogia simples:
Assim como no metabolismo, onde moléculas passam por reações catalisadas por enzimas para formar novos produtos, no Java, no exemplo acima, os elementos de uma lista passam pelo "processo" definido pelo **Predicate** para gerar um novo conjunto de elementos filtrados. Essa é a "química" da programação funcional. 
### 3.4.2 Consumer
A interface *java.util.function.Consumer* define um método abstrato chamado #accept, que recebe um objeto do tipo genérico T e não retorna nenhum resultado (void). Podemos usar essa interface quando precisarmos acessar um objeto do tipo T e realizar algumas operações sobre ele. Por exemplo, podemos utilizá-la para criar um método forEach, que recebe uma lista de Inteiros e aplica uma operação em cada elemento dessa lista:
```java
@FunctionalInterface // iformar que é uma interface funcional, garantia de compilação
public interface Consumer<T> {
	void accept(T t);
}
public <T> void forEach(List<T> list, Consumer<T> c) {
	for(T t: list)
		c.accept(t);
}

forEach(Arrays.asList(1,2,3,4,5), (Integer i) -> System.out.println(i));
```
A interface #Consumer pode ser explicada usando uma analogia com uma fábrica que processa materiais. Imagine que tenhamos várias peças brutas (os elementos da lista), e queremos realizar alguma operação em cada uma dessas peças, polir ou inspecionar. Nesse caso:
- As peças brutas (os elementos da lista) são os objetos do tipo genérico T;
- A máquina da fábrica é a interface #Consumer. Ela aceita uma peça de cada vez e realiza a operação específica sobre ela.
- O método *accept* é como a ação da máquina: ela executa a operação definida para processar cada peça.

No nosso exemplo de código acima, o método *forEach* é como uma linha de produção. Ele pega cada peça (elemento da lista) e a envia a máquina (o *consumer*), que realiza a operação determinada.

**Array.asList**
O método estático #asList da classe #Arrays retorna uma lista que é passada para o construtor para a criação de um **ArrayList**.
Exemplo:
```java
String[] array = {"Red", "Green", "Blue"};
ArrayList<String> list = new ArrayList<>(Array.asList(array));
```
A lista criada por *asList* possuí tamanho fixo, não podemos adicionar ou remover elementos com os métodos #add ou #remove, caso utilizemos, uma exceção poderá ser lançada como #UnsupportedOperationException. 

Podemos passar diretamente o retorno de Arrays.asList() como argumento de um método:
```java
forEach(Arrays.asList(1, 2, 3, 4, 5), (Integer i) -> System.out.println(i));
```

**Vantagens de passar diretamente**
- Código mais conciso: evitamos a criação de uma variável intermediária para a lista, reduzindo o número de linhas de código;
- **Melhora a legibilidade**: para quem lê o código, fica claro que a lista é apenas temporária e usada unicamente naquele método.

**Quando usar uma variável intermediária?**
- **Reutilização da lista:** se a lista for usada em outros lugares do nosso código;
- **Depuração:** para inspecionar ou validar o conteúdo da lista antes de passá-la ao método;
- **Clareza:** se o método tiver muitos argumentos ou se a criação da lista for complexa.

### 3.4.3 Function
A interface *java.util.function.Function<T, R>* define um método abstrato chamado #apply, que recebe um objeto do tipo genérico T como entrada e retorna um objeto do tipo genérico R. Podemos usar essa interface quando precisarmos definir  uma expressão lambda que mapeia informações de um objeto de entrada para uma saída (por exemplo, extraindo o peso de uma maçã ou mapeando uma String para seu comprimento). No exemplo a seguir, mostramos como podemos utilizá-la para criar um método #map que transforma uma lista de Strings em uma lista de Inteiros contendo o comprimento de cada String.
```java
@FunctionalInterface
public interface Function<T, R> {
	R apply(T t);
}
public <T, R> List<R> map(List<T> list, Function<T, R> f) {
	List<R> result = new ArrayList<>();
	for(T t: list) {
		result.add(f.apply(t));
	} 
	return result;
}

public class Main {
	public static void main(String[] args) {
		List<String> words = Arrays.asList("Apple", "banana", "cherry");

		Function<String, Integer> lengthFunction = (String s) -> s.length();

		List<Integer> lengths = map(words, lengthFunction);

		System.out.println(lengths); // Saída: [5, 6, 6]
	}
}
```

**Especializações Primitivas**
Descrevemos três interfaces funcionais que *são genéricas*: #Predicate, #Consumer e #Function. Também existem **interfaces funcionais** que são especializadas com certos **tipos**. 

Para relembrar um pouco: todo tipo em Java é ou um **tipo de referência** (por exemplo, Byte, Integer, Object, List) ou um **tipo primitivo** (por exemplo, int, double, byte, char). 
No entanto, os parâmetros genéricos (por exemplo, o T em Consumer) podem ser vinculados apenas a *tipos de referência*. Isso se deve à forma como os genéricos são implementados internamente. Como resultado, em Java existe um mecanismo para converter um tipo primitivo em um tipo de referência correspondente. Esse mecanismo é chamado de #boxing. A abordagem oposta (converter um tipo de referência em um tipo primitivo correspondente) é **chamada de unboxing**. Java também possui um mecanismo autoboxing para facilitar a tarefa: as operações de boxing e unboxing que são realizadas automaticamente. Por exemplo, é por isso que o seguinte código é válido (um int é convertido em um Integer):
```java
List<Integer> list = new ArrayList<>();
for (int i = 300; i < 400; i++)
	list.add(i);
```

Mas isto gera um custo de desempenho, os valores encapsulados são um invólucro em torno dos tipos primitivos e **são armazenados na heap**. Portanto, os valores encapsulados utilizam mais memória e requerem buscas adicionais na memória para acessar o valor primitivo encapsulado.

1. **Alocação de Objetos na Memória**
- Tipos primitivos são armazenados diretamente na **pilha (stack)**, que é uma região de memória mais rápida e eficiente.
- Tipos de referência (objetos) são armazenados no #heap, que é uma região de memória mais lenta e sujeita a gerenciamento de garbage collection;
- Quando ocorre o boxing, um novo objeto é criado no heap para armazenar o valor primitivo. Essa alocação de memória é mais custosa do que simplesmente armazenar o valor primitivo na pilha.

2. **Garbage Collection**
- Objetos criados no heap precisam ser gerenciados pelo **garbage collector** (coletor de lixo) do Java.
- Quando muitos objetos são criados devido ao boxing (por exemplo, em loops ou operações repetitivas), o garbage collector precisa trabalhar mais para liberar memória, o que pode causar pausas e impactar o desempenho da aplicação.

3. **Uso de Memória**
- Objetos consomem mais memória do que tipos primitivos. Por exemplo: Um #int ocupa *4 bytes* já um objeto do tipo #integer ocupa *16 bytes* (ou mais, dependendo da JVM), devido ao overhead do objeto (como cabeçalho, referências, etc).
- Esse aumento no uso de memória pode levar a mais alocações e, consequentemente, a mais trabalho para o garbage collector.

**Como Evitar o Custo do Boxing?**
1. **Usar tipos primitivos sempre que possível:**
- Evite usar tipos de referência (como *Integer*, *Double*, etc) quando tipos primitivos #int, #double são suficientes.

2. **Estruturas de dados especializados:**
- Use classes como #IntStream, #LongStream, ou bibliotecas como **Eclipse Collection ou Trove**, que evitam o boxing ao trabalhar com tipos  primitivos.

3. **Cuidado com APIs que exigem tipos de referência**
- Algumas APIs (como coleções do Java, por exemplo, **List< Integer >**) exigem tipos de referência. Nesses casos, avalie se o uso é realmente necessário ou se há alternativas mais eficientes. 

O Java 8 também adicionou uma versão especializada de interfaces funcionais que descrevemos anteriormente, a fim de evitar operações de autoboxing quando as entradas ou saídas são tipos primitivos. Por exemplo, no código a seguir, o uso de um *IntPredicate* evita a operação de boxing do valor 1000, enquanto o uso de um *Predicate< Integer>* faria o boxing do argumento 1000 para um objeto *Integer*:
```java
public interface IntPredicate {
	boolean test(int t);
}

IntPredicate evenNumbers = (int i) -> i % 2 == 0;
evenNumbers.test(1000); // True (no boxing)

Predicate<Integer> oddNumbers = (Integer i) -> i % 2 != 0;
oddNumbers.test(1000); // False (boxing)
```

Essas interfaces funcionais evitam o autoboxing, pois trabalham diretamente com tipos primitivos.
Quando usamos *IntPredicate*, o valor de 1000 é tratado como *int*, sem conversão para **Integer**. 
Quando usamos *Predicate< Integer>*, o valor de 1000 é convertido automaticamente para **Integer**, o que gera um custo adicional.

Em geral, o **tipo primitivo apropriado** precede os nomes das interfaces funcionais que possuem uma especialização para o parâmetro de tipo de entrada (por exemplo, #DoublePredicate, #IntConsumer, #LongBinaryOperator, #IntFunction, e assim por diante). A interface #Function também possui variantes para o parâmetro de tipo de saída: #ToIntFunction< T>, #IntToDoubleFunction, e assim por diante...

A tabela 3.2 resume as interfaces funcionais mais comumente usadas disponíveis na API do Java e seus descritores de função, juntamente com suas especializações para tipos primitivos. Precisamos ter em mente estas são apenas interfaces de kit inicial, podemos sempre criar nossas próprias interfaces. <span style="background:#b1ffff">Criar nossas próprias interfaces também pode ajudar quando um nome específico do domínio contribuí para a compreensão e manutenção do programa</span>.

Lembramos sempre da notação **(T, U) -> R**, ela nos mostra como pensar sobre um descritor de função. O lado esquerda da seta é uma lista que representa os tipos de argumentos, e o lado direito representa os tipos dos resultados. Neste caso, representa uma função com dois argumentos de tipos genéricos T e U, respectivamente, e que tem um tipo de retorno R.

![[Capítulo 3 - Lambda Expressions-1.png]]

To summarize the discussion about functional interfaces and lambdas, table 3.3 provides a summary of use cases, examples of lambdas, and functional interfaces that can be used:
![[Capítulo 3 - Lambda Expressions-2.png]]

**E quanto as exceções, lambdas e interfaces funcionais?**
Note que nenhuma das interfaces funcionais permite que uma *checked exception* seja lançada. Temos duas opções caso precisemos que o corpo de uma expressão lambda lance uma exceção:
1. Definir sua própria interface funcional que declare uma *checked exception*;
2. Ou envolver o corpo da lambda em um bloco *try/catch*.
Por exemplo, na seção 3.3, introduzimos uma nova interface funcional chamada *BufferedReaderProcessor* que declarava explicitamente uma *IOException*:
```java
@FunctionalInterface
public interface BufferedReaderProcessor {
	String process(BufferedReader b) throws IOException;
}
```

Com isso, podemos utilizá-la assim:
```java
BufferedReaderProcessor p = (BufferedReader br) -> br.readLine();
```

No entanto, podemos usar uma API que espera uma interface funcional, como *Function< T, R>*, e não tem a opção de criar sua própria. No próximo capítulo, veremos que a API de Streams faz uso intenso das interfaces funcionais da tabela 3.2. Nesse caso, podemos capturar explicitamente a *checked exception* da seguinte forma:
```java
Function<BufferedReader, String> f =
	(BufferedReader b) -> {
		try {
			return b.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
```

---
**Resumo sobre Lambdas**
As expressões lambdas em Java, introduzidas no Java 8, são uma forma concisa de implementar *interfaces funcionais* (*interfaces com um único método* abstrato, como #Runnable, #Comparator, etc.). Resumidamente:

**Sintaxe Básica:**
```java
(parâmetros) -> {corpo}
```
- **Parâmetros**: lista de argumentos (tipos podem ser omitidos se inferidos);
- **Operador**: -> separa parâmetros do corpo;
- **Corpo:** Bloco de código ou expressão única (se for uma expressão, *return* e {} são opcionais).

**Exemplos:**
1. **Sem parâmetros:**
```java
Runnable r = () -> System.out.println("Hello World!");
```

2. **Com parâmetros:**
```java
Comparator<Integer> com = (a, b) -> a.compareTo(b);
```

3. **Corpo com múltiplas linhas:**
```java
MathOperation op = (x, y) -> {
	int result = x + y;
	return result;
}
```
**Características Principais:**
1. **Concisão:** reduz código comparado a classes anônimas;
2. **Inferência de Tipos:** o compilador infere tipos dos parâmetros;
3. **Interfaces Funcionais:** Funcionam apenas com interfaces que têm um único método abstrato (ex.: #Predicate, #Function, #Consumer ).
4. **Variáveis Externas:** podem acessar variáveis de escopo externo se forem **final** ou efetivamente final.

## 3.5 Type checking, type inference, and restrictions
**Verificação de tipos, inferência de tipos e restrições**
Quando mencionamos as expressões lambdas pela primeira vez, dissemos que elas permitem que geremos uma instância de uma interface funcional. No entanto, uma expressão lambda em si não contém a informação sobre qual interface funcional ela está implementando. Para ter um entendimento mais formal das expressões lambda, precisamos saber qual é o tipo de uma lambda. 
```java
@FunctionInterface
interface MyFunction {
	void execute();
}

public class LambdaExample {
	public static void main(String[] args) {
		// Aqui a lambda é "convertida" em uma instância de MyFunction
		MyFunction func = () -> System.out.println("Hello World");
		func.execute(); // chama o método da interface funcional
	}
}
```
A lambda em si não diz explicitamente qual interface funcional ela está implementando. O **tipo de lambda** é inferido pelo compilador com base no contexto em que ela é usada.

No exemplo acima, a lambda *() -> System.out.println("Hello World")* não diz explicitamente que está implementando *MyFunction*. O compilador **infere** que o tipo de lambda é *MyFunction* com base no contexto de criação, pois *func* é do tipo *MyFunction*. 

### 3.5.1 Type checking
O tipo de uma **lambda** é deduzido a partir do contexto em que a **lambda** é usada. O tipo esperado para a expressão **lambda** dentro do contexto (por exemplo, um parâmetro de método ao qual ela é passada ou uma variável local à qual ela é atribuída) é chamado de **target type**. Vamos ver um exemplo para entender o que acontece nos bastidores quando usamos uma expressão lambda:
```java
List<Apple> heavierThan150g = filter(inventory, (Apple apple) -> apple.getWeight() > 150);
```

O processo de verificação de tipo é decomposto da seguinte forma:
1. Primeiro, verifica-se a declaração do método *filter*;
2. O método espera, como segundo parâmetro formal, um objeto do tipo *Predicate< Apple>* (o tipo alvo);
3. *Predicate< Apple>* é uma **interface funcional** que define um único método abstrato chamado *test*.
4. O método *test* descreve um *function descriptor*, aceitando um objeto **Apple** e retornando um **boolean**;
5. Por fim, qualquer argumento passado para o método *filter* precisa corresponder a esse requisito.
6. 
O código é válido porque a expressão lambda que estamos passando também recebe um **Apple**
como parâmetro e retorna um **booleano**. Observe que, se a expressão lambda estivesse lançando
uma exceção, então a cláusula throws declarada do método abstrato também teria
para corresponder.

Uma expressão lambda deve seguir a **assinatura da interface funcional**, ou seja:
- O **Parâmetro da lambda** deve corresponder ao tipo esperado pelo método abstrato da interface;
- O **Valor de retorno** deve ser o mesmo tipo definido pela interface funcional.

O exemplo acima, pode também ser explanado no seguinte exemplo:
```java
@FunctionalInterface
public interface Predicate<T> {
	boolean test(T t);
}

Predicate<Apple> isGreenApple = (Apple apple) -> apple.getColor().equals("GREEN");
```

## 3.5.2 Same lambda, different functional interfaces
Devido ao conceito de *target typing*, a mesma expressão lambda pode ser associada a diferentes *interfaces funcionais*, desde que elas possuam uma **assinatura compatível** para o método abstrato. 

Por exemplo, as interfaces *Callable< T>* e *PrivilegedAction< T>* representam funções que não recebem parâmetros e retornam um tipo genérico *T*.

Logo, as seguintes atribuições são válidas:
```java
Callable<Integer> c = () -> 42;
PrivilegedAction<Integer> p = () -> 42;
```

Tanto **Callable< T>** quanto **PrivilegedAction< T>** possuem um método abstrato que *não recebe argumentos e retornam um genérico T*.
A lambda *() -> 42* se encaixa neste formato, podendo ser usada em ambas as interfaces.

Neste caso, a primeira atribuição tem o tipo salvo *Callable< Integer>*, enquanto a segunda atribuição tem o tipo *PrivilegedAction< Integer>*.

**Operador Diamante**
Aqueles que estão familiarizados com a evolução do Java lembrarão que o **Java 7** já havia introduzido a ideia de tipos sendo inferidos a partir do contexto, com a inferência genérica utilizando o **operador diamante** <> (essa ideia pode ser encontrada ainda antes com métodos genéricos). 

Uma determinada expressão de instância de classe pode aparecer em dois ou mais contextos diferentes, e o argumento de tipo apropriado será inferido, como exemplificado abaixo:
```java
List<String> listOfStrings = new ArrayList<>();
List<Integer> listOfIntegers = new ArrayList<>();
```

**Regra especial de compatibilidade com *void***
Se uma **lambda** possui uma expressão de instrução como seu corpo, ela é compatível com um **descritor de função** que retorna void (desde que a lista de parâmetros também seja compatível).

Por exemplo, ambas as linhas abaixo são válidas, mesmo que o método **add** de uma **List** retorne um **boolean** e não **void**, como seria esperado no contexto de um **Consumer**.

Java ignora o valor retornado pela expressão e a trata como se não retornasse nada. 

```java

// Predicate retorna um boolean
Predicate<String> p = (String s) -> list.add(s);

// Consumer retorna void
Consumer<String> b = (String s) -> list.add(s);
```

---
**Quiz 3.5: Verificação de Tipos - Por que o código abaixo não compila**
Como poderíamos corrigir esse problema?
```java
Object o = () -> { System.out.println("Tricky example");};
```

**Resposta**
O problema ocorre porque a expressão lambda está sendo atribuída a uma variável do tipo *Object*, que *não é uma interface funcional*. Expressões lambda precisam de um tipo alvo, ou seja, uma interface funcional que defina o comportamento esperado.

Para corrigir isso, podemos alterar o tipo da variável para *Runnable*, que é uma interface funcional com o método *run()*, cujo formato corresponde ao da expressão lambda (() -> void):
```java
Runnable r = () -> {System.out.println("Trycky example);};
```

### 3.5.3 Type Inference 
Podemos simplificar ainda mais o nosso código. O compilador Java deduz qual interface funcional associar a uma expressão lambda a partir de seu contexto circundante (o tipo alvo), o que significa que ele também pode deduzir uma assinatura apropriada para a lambda porque o descritor da função está disponível através do tipo alvo. O benefício é que o compilador tem acesso aos tipos dos parâmetros de uma expressão lambda. 
```java
List<Apple> greenApples = filter(inventory, apple -> GREEN.equals(apple.getColor()));
```

Os benefícios da legibilidade do código são mais perceptíveis com expressões lambda que têm vários parâmetros. Por exemplo, aqui está como criar um objeto Comparator:

```java
Comparator<Apple> c =
(Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()); // com tipo

Comparator<Apple> c = (a1, a2) -> a1.getWeight().compareTo(a2.getWeight()); // sem tipo
```
Às vezes é mais legível incluir os tipos explicitamente, e outras é mais legível excluí-los. Não há uma regra sobre qual maneira é melhor; os desenvolvedores devem fazer suas próprias escolhas sobre o que torna seu código mais legível.

## Using local variables
Todas as expressões lambda que mostramos até agora usaram apenas seus argumentos dentro de seu corpo. No entanto, as expressões lambda também podem usar variáveis livres (variáveis que não são os parâmetros e são definidas em um escopo externo), assim como as classes anônimas podem. Essas são chamadas de lambda de captura. Por exemplo, a seguinte lambda captura a variável portNumber:
```java
int portNumber = 1337;
Runnable r = () -> System.out.println(portNumber);
```

No exemplo acima, a variável `portNumber` é definida fora da expressão lambda, **mas a lambda captura essa variável** e a utiliza dentro de seu corpo para imprimir seu valor. 

**Restrições sobre variáveis locais**
Por qual motivos as variáveis locais têm restrições? Primeiro, há uma diferença fundamental em como variáveis de instância e variáveis locais são implementadas nos bastidores. Variáveis de instância são armazenadas no **heap**, enquanto variáveis locais vivem na **stack**. Se uma lambda pudesse acessar diretamente a variável local e a lambda fosse usada em uma thread, a thread usando a lambda poderia tentar acessar a variável depois que a thread que alocou a variável tivesse desalocada. Por tanto, o Java implementa o acesso a uma variável local livre como acesso a uma cópia dela, em vez de acesso à variável original. Isso não faz diferença se a variável local for atribuída apenas umas vez - daí a restrição. 

Em segundo lugar, essa restrição também desencoraja padrões típicos de programação imperativa (que, como explicamos em capítulos posteriores, impedem a paralelização fácil) que alteram uma variável externa.

**Closure**
Um #closure é uma instância de uma função que pode referenciar variáveis não locais dessa função sem restrições. 
Por exemplo, um #closure pode ser passado como argumento para outra função. Ele também pode acessar e modificar variáveis definidas fora de seu escopo.

As *lambdas* e *classes anônimas* do Java 8 fazem algo semelhante aos #closures: elas podem ser passadas como argumento para métodos e podem acessar variáveis fora de seu escopo. No entanto, há uma restrição: elas não podem modificar o conteúdo de variáveis locais do método onde a lambda foi definida. Essas variáveis precisam ser implicitamente *final*.

É útil pensar que as *lambdas* capturam valores em vez de variáveis. Como explicado anteriormente, essa restrição existe porque variáveis locais vivem na pilha (stack) e estão implicitamente confinada à *thread* em que foram criadas. Permitir a captura de variáveis locais mutáveis abriria possibilidades inseguras para *threads*, o que não é desejável (variáveis de instância, por outro lado, não apresentam esse problema, pois vivem no *heap*, que é compartilhado entre *threads*).

```java
public class Exemplo {
	public static void main(String[] args) {
		String nome = "Carlos"; // essa variável é efetivamente final;

		Runnable r = () -> System.out.println("Olá, " + nome);
		r.run();
	}

}
```
Aqui, a *lambda* acessa a variável *nome*, que está fora do seu escopo, mas **não pode modificá-la**. 
Se tentarmos modificar o *nome*, o código não compila:
```java
public class Exemplo {
	public static void main(String[] args) {
		String nome = "Carlos";

		Runnable r = () -> {
			nome = "João";
			System.out.println("Olá, " + nome);
		};
	r.run();
	}
}
```
Erro. Isso acontece porque variáveis locais vivem na *pilha (stack)* e não são compartilhadas entre *threads*. Permitir que uma lambda modifique essas variáveis poderia causar problemas de concorrência e insegurança em *multithreading*.

Por outro lado, **variáveis de instância** vivem no *heap*, que é compartilhado entre threads. Então, **lambdas podem modificar variáveis de instância:**
```java
public class Exemplo {
	private String nome = "Carlos";
	
	publiuc void executar() {
		Runnable r = () -> {
			nome = "Zulu"; // isso é permitido!
			System.out.println("Olá, " + nome);
		};
		r.run(); 
	}

	public static void main(String[] args) {
		new Exemplo().executar();
}
}
```

**Resumo**
- #closures podem acessar e modificar variáveis externas;
- Lambdas em Java podem acessar variáveis externas, mas não modificá-las, a menos que sejam variáveis de instância ou estáticas.
- Essa restrição existe porque variáveis locais vivem na *stack* e não são compartilhadas entre *threads*, evitando problemas de concorrência.

## 3.6 Method references
As referências a métodos permitem **reutilizar definições de métodos existentes** e passá-las como se fossem expressões lambda. Em alguns casos, elas tornam o código mais legível e natural do que o uso direto de lambdas.

Exemplo de ordenação de uma lista usando #lambdas:
```java
inventory.sort((Apple a1, Apple a2) ->
	a1.getWeight().compareTo(a2.getWeight()));
```

Usando *referência a método e comparator.comparing* do Java 8:
```java
inventory.sort(comparing(Apple::getWeight));
```
O método `Apple::getWeight` faz exatamente o que a lambda faria...

