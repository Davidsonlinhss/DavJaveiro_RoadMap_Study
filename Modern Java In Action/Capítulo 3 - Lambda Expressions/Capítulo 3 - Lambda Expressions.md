*This chapter covers*
- Lambdas in a nutshell
- Where and how to use lambdas
- The execute-around pattern
- Functional interfaces, type inference
- Method references
- Composing lambdas

No capítulo anterior nós vimos que passar código utilizando a **parametrização de comportamento** é útil para lidar com *mudanças frequentes* nos requisitos do nosso código. Isso permite que definamos um bloco de código que represente um comportamento e, em seguida, o passe adiante. Podemos executar esse bloco de código quando um determinado evento ocorrer (por exemplo, um clique em um botão) ou em determinados pontos de um algoritmo (por exemplo, um predicado como "apenas maçãs mais pesadas que 150g" no algoritmo de filtragem ou a operação de comparação personalizada em uma ordenação). De forma geral, utilizando esse conceito, podemos escrever um código mais flexível e reutilizável. 

Utilizar classes anônimas para representar diferentes comportamentos é insatisfatório. É algo verboso, o que não incentiva os programadores a utilizarem a parametrização de comportamento na prática. Neste capítulo, vamos apresentar um novo recurso do Java 8 que resolve esse problema: **expressões lambda.** Elas permitem representar um comportamento ou passar código de forma concisas. Por enquanto, podemos pensar nas expressões lambda como **funções anônimas** - **métodos sem nomes declarados**, mas que também **podem ser passados como argumentos** para outro método, assim como ocorre com classes anônimas. 

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
