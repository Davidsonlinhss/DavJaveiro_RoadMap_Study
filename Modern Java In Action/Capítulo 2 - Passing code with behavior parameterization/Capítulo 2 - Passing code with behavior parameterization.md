**Este capítulo abordará:**
- Parametrização de comportamento;
- Classes anônimas;
- Prévia de expressões lambda;
- Exemplos do mundo real: #Comparator, #Runnable e #GUI.

Um problema bem conhecido na Engenharia de Software é que, independentemente do que você faça, <span style="background:#b1ffff">os requisitos dos usuários sempre mudam</span>. Imagine um aplicativo para ajudar um agricultor a gerenciar seu estoque. Inicialmente, o agricultor pode querer uma funcionalidade para encontrar todas as maçãs verdes no estoque. No dia seguinte, ele pode dizer: "Na verdade, também quero encontrar todas as maçãs que pesam mais de 150g." Dois dias depois, o agricultor retorna e acrescenta: "Seria ótimo se eu pudesse encontrar todas as maçãs que são verdes e que também pesam mais de 150g."

Como lidar com esses requisitos em constante mudança? Idealmente, gostaríamos de minimizar o esforço de desenvolvimento. Além disso, novas funcionalidades similares deveriam ser simples de implementar e fáceis de manter ao longo prazo. 

A **Parametrização de comportamento** é um padrão de desenvolvimento de software que permite lidar com mudanças frequentes de requisitos. Em resumo, <span style="background:#fdbfff">isso significa pegar um bloco de código e disponibilizá-lo sem executá-lo imediatamente</span>. Esse bloco de código <span style="background:#fdbfff">pode ser executado posteriormente</span> por outras partes do programa, o que significa que você pode adiar a execução desse bloco. Podemos passar o bloco de código como argumento para outro método, que irá executá-lo mais tarde. 

Como resultado, o <span style="background:#d4b106">comportamento do método é parametrizado</span> com base nesse bloco de código. Por exemplo, ao processar uma coleção, você poder escrever um método que:
- Realize "algo" para cada elemento de uma lista;
- Faça "algo diferente" ao terminar de processar a lista;
- Execute "outra coisa" caso encontre um erro.

Exemplo clássico, imagine que tenhamos uma lista de números e queremos realizar operações diferente sobre cada número da lista, como dobar o valor, verificar se é par, ou simplesmente impimi-lo.

Código implementado sem a parametrização de comportamento:
Teríamos que criar métodos diferentes para cada operação:
```java
public void printEach(List<Integer> list) {
	for (Integer i : list) 
		System.out.println(i);
}

public void doubleEach(List<Integer> list) {
	for (Integer i : list)
		System.out.println(i);
}
```

Com a parametrização de comportamento:
Podemos passar o comportamento (como uma função) como parâmetro, tornando o método genérico:
```java
public  void processEach(List<Integer> list, Consumer<Integer> action) {
	for (Integer i : list)
		action.accept(i); // chamada o método accept do Consumer
}
```
Consumer< Integer > aciton -> é parâmetro de argumento que será fornecido por uma expressão lambda. Ele apenas aceita expressões ou implementações que manipulam valores do tipo Integer.

Agora, podemos usar o mesmo método para várias ações:
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4);

processEach(numbers, i -> System.out.println(i));
processEach(numbers, i -> System.out.println(i * 2));
processEach(numbers, i -> System.out.println(i % 2 == 0));
```

Isso é o que se refere à parametrização de comportamento. Aqui está uma analogia: seu colega de quarto sabe como dirigir até o supermercado e voltar para casa. Você pode pedir a ele para comprar uma lista de coisas como pão, queijo e vinho. Isso é equivalente a chamar um método goAndBuy passando uma lista de produtos como argumento. Mas um dia você está no escritório e precisa que ele faça algo que nunca fez antes - pegar um pacote no correio. Você precisa passar a ele uma lista de instruções: ir ao correio, usar este número de referência, falar com o gerente e pegar o pacote. Você pode passar a ele a lista de instruções por e-mail e, quando ele a receber, ele pode seguir as instruções. Você agora fez algo um pouco mais avançado que é equivalente a um método goAndDo, que pode executar vários novos comportamentos como argumentos.

Inciaremos este capítulo guiando você por um exemplo de como podemos evoluir nosso código para ser mais flexível para requisitos em constante mudança. Com base nesse conhecimento, mostraremos como usar a <span style="background:#d4b106">parametrização de comportamento</span> para vários exemplos do mundo real.

Por exemplo, você pode ter usado o padrão de parametrização de comportamento já, usando classes e interfaces existentes na API Java para ordenar uma Lista, filtrar nomes de arquivos ou instruir uma Thread a executar um bloco de código ou até mesmo realizar o tratamento de eventos da GUI.

Veremos que esse padrão é historicamente verboso em Java. Expressões lambda no Java 8 em diante abordam o problema da verbosidade. 

## 2.1 Coping with changing requirements
Escrever código que possa lidar com requisitos em mudança é difícil. Vamos percorrer um exemplo que iremos melhorar gradualmente, mostraremos algumas boas práticas para tornar seu código mais flexível. No contexto de um aplicativo de inventário de fazenda, precisamos implementar uma funcionalidade para filtrar maçãs verdes de uma lista. Parece fácil, né?

### 2.1.1 First attempt: filtering green apples
Suponha, como no capítulo 1, que tenhamos um #enum Color disponível para representar diferentes cores de uma maçã:
- enum Color {RED, GREEN, WELLOW}

Essa linha de código define um enum chamado "Color" com três constantes: RED, GREEN e YELLOW. 

Uma primeira solução pode ser a seguinte:
```java
public static List<Apple> filterGreenApples(List<Apple> inventory) {
	List<Apple> result = new ArrayList<>(); // Um acumulador lista para maçãs
	for(Apple apple : inventory) {
		if( GREEN.equals(apple.getColor()) ) { // selecionando apenas maçãs verdes
			result.add(apple);
		}
		return result;
	}
}
```

O código dentro do bloco if mostra a condição necessária para selecionar maçãs verdes. Podemos assumir que possuímos um enum color com um conjunto de cores, como GREEN, disponível. Mass agora o agricultor muda de ideia e deseja filtrar também as maçãs vermelhas. O que podemos fazer? Duplicar o código...

Quando nos encontramos escrevendo código quase repetido, precisamos abstrair ao invés de continuarmos escrevendo código #boilerplate. 

### 2.1.2 Second attempt: parameterizing the color
Como evitamos duplicar a maior parte do código em filterGreenApples para fazer filterRedAPPLES? Para parametrizar a cor e ser mais flexível a tais mudanças, o que podemos fazer é <span style="background:#d4b106">adicionar um parâmetro ao nosso método</span>:
```java
public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
	List<Apple> result = new ArrayList<>();
	for(Apple apple: inventory) {
		if( apple.getColor().equals(color)) {
			result.add(apple);
		}
	}
	return result;
}
```

Agora podemos fazer o agricultor satisfeito e invocar seu método da seguinte maneira:
```java
List< Apple > greenApples = filterApplesByColor(inventory, GREEN);
List< Apple > redApples = filterApplesByColor(inventory, RED);
```
Estamos passando um valor do tipo ENUM como argumento para o método filterApplesByColor.

Fácil demais, certo? Vamos complicar o exemplo um pouco mais. O agricultor volta até a gente e diz: "Seria muito legal diferenciar entre maçãs leves e pesadas. Maçãs pesadas geralmente têm um peso superior a 150g."
Logo, criamos o seguinte método:
```java
public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
	List<Apple> result = new ArrayList<>();
	For(Apple apple : inventory) {
		if ( apple.getWeight() > weight) {
			result.add(apple);
		}
	}
	return result;
}
```

Esta é uma boa solução, mas observe como precisamos duplicar a maior parte da implementação para percorrer o inventário e aplicar os critérios de filtragem em cada maçã. Isso é um pouco decepcionante, pois viola o princípio #DRY (<span style="background:#b1ffff">não se repita</span>) da engenharia de software. E se quisermos alterar o método de percorrer a filtragem para melhor o desempenho? Agora <span style="background:#b1ffff">teríamos que modificar a implementação de todos os seus métodos</span> em vez de apenas um único método. Isso é caro do ponto de vista do esforço de engenharia.
Podemos combinar a cor e o peso em um único método chamado *filter*. Mas ainda precisaríamos de uma forma de diferenciar qual atributo desejamos filtrar. <span style="background:#ff4d4f">Podemos adicionar uma flag para diferenciar entre consultar de cor e peso (MAS NUNCA FAÇA ISSO!)</span>

### 2.1.3 Third attempt: filtering with every attribute you can think of
Uma tentativa feia de mesclar todos os atributos pode ser a seguinte:
```java
public static List<Apple> filterApples(List<Apple> inventory, Color color, int weight, boolean flag) {
	List<Apple> result = new ArrayList<>();
	if ( (flag && apple.getColor().equals(color)) ||
		 (!flag && apple.getWeight() > weight) ) { // uma forma feia de selecionar a cor ou peso
			 result.add(apple);
		 }
	}
	return result;
}

```

Podemos usar isso da seguinte maneira, mas é feio:
```java
List<Apple> greenApple = filterApples(inventory, GREEN, 0, true);
List<Apple> heavyApples = filterApples(inventory, null, 150, false);
```

Essa solução é extremamente ruim. Primeiro, o código do cliente fica terrível. O que "true" e "false" significam? Além disso, essa solução <span style="background:#b1ffff">não lida bem com mudanças nos requisitos</span>. E se o agricultor pedir para filtrar por diferentes atributos de uma maçã, como seu tamanho, formato, origem, e assim por diante? Além disso, e se o agricultor solicitar consultas mais complexas que combinem atributos, como maçãs verdes que também sejam pesadas? Teríamos múltiplos métodos duplicados de filtragem ou um único método extremamente complexo. Até agora, parametrizamos o método filterApples com valores como uma String, um Integer, um tipo enum ou um boolean. Isso pode ser adequado para certos problemas bem definidos. Mas, neste caso, o que precisamos é de uma maneira melhor de informar ao método filterApples os critérios de seleção para as maçãs. Na próxima seção, descrevemos como usar a parametrização de comportamento para alcançar essa flexibilidade. 

## 2.2 Behavior parameterization
Vimos na seção anterior que é necessário uma maneira melhor do que adicionar muitos parâmetros para lidar com mudanças nos requisitos. Vamos dar um passo atrás e encontrar um nível melhor de abstração. Uma solução possível é modelar seus critérios de seleção: estamos trabalhando com maçãs e retornando um booleano com base em alguns atributos da maçã. Por exemplo, ela é verde? Ela é mais pesada do que 150g? Chamamos isso de #predicado (uma função que retorna um booleano). Vamos, definir uma interface para modelar os critérios de seleção:
```java
public interface ApplePredicate {
	boolean test(Apple apple);
}
```
Agora, <span style="background:#b1ffff">podemos declarar várias implementações</span> de ApplePredicate para representar diferentes critérios de seleção, como mostrado a seguir:
```java
public class AppleHeavyWeightPredicate implements ApplePredicate {
	public boolean test(Apple apple) {
		return apple.getWeight() > 150;
	}
}

public class AppleGreenColorPredicate implements ApplePredicate {
	public boolean test(Apple apple) {
		returna GREEN.equals(apple.getColor());
	}
}
```

**Diferentes estratégias de seleção**
![[Capítulo 2 - Passing code with behavior parameterization.png]]

Podemos ver esses critérios como diferentes comportamentos para o método filter. O que acabamos de fazer está relacionado ao padrão de projeto *strategy*, que permite definir uma família de algoritmos, encapsular cada algoritmo (chamado de estratégia) e selecionar um algoritmo em tempo de execução. No nosso exemplo, *ApplePredicate* é a família de algoritmos e as diferentes estatégias são *AppleHeavyWeightPredicate* e *AppleGreenColorPredicate*.

Mas como podemos fazer uso das diferentes implementações de `ApplePredicate`? Precisamos que nosso método *filterApples* aceite objetos *ApplePredicate* para testar uma condição em uma maçã. Isso é o que significa *parametrização de comportamento:* a capacidade de informar a um método para múltiplos comportamentos (ou estratégias) como parâmetros e usá-los internamente para realizar diferentes comportamentos.

Para alcançar isso no exemplo em execução, podemos adicionar um parâmetro ao método filterApples para aceitar um objeto ApplePredicate. Isso tem um grande benefício em engenharia de software: agora podemos separar a lógica de iteração da coleção dentro do método filterApples do comportamento que desejamos aplicar a cada elemento da coleção. 

### 2.2.1 Fourth attempt: filtering by abstract criteria
Nosso método **filter** modificado, que utiliza um ApplePredicate:
```java
public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
	List<Apple> result = new ArrayList<>();
	for (Apple apple: inventory) {
		if(p.test(apple)) {
			result.add(apple)	
		}
	}
	return result;
}
```

**Passing CODE/BEHAVIOR**
Vale a pena fazer uma pequena celebração neste momento. Este código é muito mais flexível do que nossa primeira tentativa, mas ao mesmo tempo é fácil de ler e usar! Agora podemos criar diferentes objetos *ApplePredicate* e passá-los para o método *filterApples*. Flexibilidade total! Se o usuário **pedir para encontrar todas as maçãs vermelhas que são mais pesadas do que 150g**, tudo o que precisamos fazer <span style="background:#b1ffff">é criar uma classe que implemente</span> o *ApplePredicate* de acordo. Nosso código é flexível o suficiente para qualquer alteração nos requisitos envolvendo os atributos de *Apple*:
```java
public class AppleRedAndHeavyPredicate implements ApplePredicate {
		public boolean test(Apple apple) {
			return RED.equals(apple.getColor()) 
					&& apple.getWeiht() > 150;
		}
}
List<Apple> redAndHeavyApples = filterApples(inventory, new AppleRedAndHeavyPredicate());
```

O comportamento do método *filterApple* agora depende do código que passamos a ele através do objeto *ApplePredicate*. Parametrizamos o comportamento do método *filterApples*. 

Notamos que, no exemplo anterior, o único código que importa é a implementação do método *test*, como ilustrado na figura 2.2; é isso que define os novos comportamentos para o método *filterApples*. Infelizmente, como método *filterApples* só pode aceitar objetos, precisamos encapsular este código dentro de um objeto *ApplePredicate*. O que estamos fazendo é semelhante a **passar código inline**, pois estamos passando uma expressão #booleana por meio de um objeto que implementa o método *test*. Veremos na seção 2.3 (e em mais detalhes no capítulo 3) que, ao usar lambdas, podemos passar diretamente a expressão *RED.equals(apple.getColor()) && apple.getWeight() > 150* para o método *filterApples*, sem precisar definir várias classes *ApplePredicate*. Isso elimina verbosidade desnecessária! AMÉM!

![[Capítulo 2 - Passing code with behavior parameterization-1.png]]

**Multiples Behaviors, One Parameter**
Como explicamos anteriormente, a parametrização de comportamento é excelente porque <span style="background:#b1ffff">permite separar a lógica de iteração da coleção a ser filtrada do comportamento aplicada a cada elemento dessa coleção</span>. Como consequência, você pode reutilizar o mesmo método e fornecer diferentes comportamentos para alcançar resultados variados, como ilustrado na figura 2.3. Por isso, a parametrização de comportamento é um conceito útil que deve estar inclusiva no nosso conjunto de ferramentas para criar APIs flexíveis. 
![[Pasted image 20250116210344.png]]




---
Quiz 2.1: Escreva um método flexível chamado **prettyPrintApple**
Escreva um método chamado *prettyPrintApple* que receba uma lista de maçãs (*List< Apple >*) e permita ser parametrizado com múltiplas formas de gerar uma saída de texto (String) de uma maçã (de forma semelhante a vários métodos personalizados de toString). Por exemplo, podemos configurar o método *prettyPrintApple* para exibir apenas o peso de cada maçã. Além disso, poderíamos configurá-lo para imprimir cada maçã individualmente mencionando se é pesada ou leve. 

```java
public static List<Apple> prettyPrintApple(List<Apple> inventory, AppleFormatter formatter {
	List<Apple> result = new ArrayList<>();
	For(Apple apple: inventory)
		String output = formatter.accept(apple);
		System.out.println(output);
}

public interface AppleFormatter {
	String accept(Apple a);
}


public class Formatter implements AppleFormater {
	public String accept(Apple apple) {
		String characteristic = apple.getWeight() > 150 ? "heavy" : "light";
		return "A " + characteristic + " " + apple.getColor() +" apple"; 
	}
}
```

---
**Resumo etapas de Parametrização de comportamento**


1. **Definir uma Interface para representar o comportamento**;
```java
public interface AppleFormatter {
	String accept(Apple a);
}
```

2. **Implementar Comportamentos Específicos**
```java
public class AppleFancyFormatter implements AppleFormatter {
	public String accept(Apple apple) {
		String character = apple.getWeight() > 150 ? "heavy" : "light";
		return "A " + character + " " + apple.getColor() + " apple";
	}
}
```


3. **Modificar o método para Receber o Comportamento como Parâmetro**
- Altere o método para aceitar um parâmetro do tipo da interface criada.
```java
public static List<Apple> prettyPrintApple(List<Apple> inventory, AppleFormatter formatter) {
	for(Apple apple: inventory) {
		String output = formatter.accept(apple);
		System.out.println(output);
	}
}
```

4. **Instanciar e Passar Implementações Específicas**
```java
prettyPrintApple(inventory, new AppleFancyFormatter());
prettyPrintApple(inventory, new AppleSimpleFormatter());
```

É possível abstrair o comportamento e fazer o nosso código se adaptar às mudanças de requisitos, mas o processo é verboso, pois exige a declaração de várias classes que instanciamos apenas uma única vez. Vamos melhorar o processo.

## 2.3 Tackling verbosity
Sabemos que qualquer recurso ou conceito que seja difícil de usar será evitado. Atualmente, para passar um novo comportamento ao método *filterApples*, é necessário declarar várias classes que implementam a interface *ApplePredicate* e, em seguida, instanciar vários objetos *ApplePredicate*, que são alocados apenas uma vez. Como mostrado no exemplo a seguir, esse processo é bastante verboso e consome muito tempo! 

```java
// Predicate interface for apples
public interface ApplePredicate {
    boolean test(Apple apple);
}

// Implementation to filter de MAÇÃS PESADAS
public class AppleHeavyWeightPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}

// Implementation to filter MAÇÃS VERDES
public class AppleGreenColorPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return "GREEN".equals(apple.getColor());
    }
}

// Main class to filter apples
public class FilteringApples {
    public static void main(String... args) {
        // Sample inventory of apples
        List<Apple> inventory = Arrays.asList(
            new Apple(80, "GREEN"),
            new Apple(155, "GREEN"),
            new Apple(120, "RED")
        );

        // Filter heavy apples
        List<Apple> heavyApples = filterApples(inventory, new AppleHeavyWeightPredicate());
        
        // Filter green apples
        List<Apple> greenApples = filterApples(inventory, new AppleGreenColorPredicate());
    }

    // Method to filter apples based on a predicate
    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
}
```
 
Isso é uma sobrecarga desnecessária. O Java possui mecanismos chamados classes anônimas, que permitem declarar e instanciar uma classe ao mesmo tempo. Elas possibilitam melhorar ainda mais o código, tornando um pouco mais *conciso*. No entanto, elas não são totalmente satisfatórias. 

### 2.3.1 Anonymous classes
*Anonymous classes* são como classes locais (uma classe definida em um bloco) que já conhecemos no Java. No entanto, classes anônimas não têm nome. Elas permitem declarar e instanciar uma classe ao mesmo tempo.  Em resumo, possibilitam criar implementações ad hoc.

### 2.3.2 Fifth attempt: using an anonymous class
O código a seguir mostra como reescrever o exemplo da filtragem criando um objeto que implementa *ApplePredicate* usando uma classe anônima:
```java
List<Apple> redApples = filterApples(inventory, new ApplePredicate()) {
	public boolean test(Apple apple){
		return RED.equals(apple.getColor());
	}
}
```
As classes anônimas são frequentemente usadas no contexto de aplicativos GUI para criar objetos manipuladores de eventos. 

Mas, as classes anônimas ainda não são boas o suficiente. Primeiro, elas tendem a ser volumosas porque ocupam muito espaço, como mostrado no código em negrito aqui, utilizando os mesmos dois exemplos usados anteriormente:
```java
List<Apple> redApples = filterApples(inventory, new ApplePredicate() {
    public boolean test(Apple a){
        return RED.equals(a.getColor());
    }
});

button.setOnAction(new EventHandler<ActionEvent>() {
    public void handle(ActionEvent event) {
        System.out.println("Uhuuu, um clique!!");
    }
});

```

A verbosidade no código é geralmente considerada ruim, pois ela desestimula o uso de um recurso de linguagem, já que exige mais tempo para escrever e manter o código, além de ser pouco agradável de ler. Um bom código deve ser fácil de entender rapidamente. Embora as classes anônimas ajudam a reduzir a verbosidade associada à declaração de várias #classes concretas para uma interface, ainda não são uma solução ideal. Quando passamos uma parte simples de código (como uma expressão booleana representando um critério de seleção), ainda precisamos criar um objeto e implementar explicitamente um método para definir um novo comportamento (como o método *test* para *Predicated* ou o método *handle* para *EventHandler*).

### 2.3.3 Sixth attempt: using a lambda expression
O código anterior pode ser reescrito utilizando expressões lambda:
```java
List<Apple> result = filterApples(inventory, (Apple apple) -> RED.equals(apple.getColor()));
```
Temos que admitir que este código aparenta estar mais limpo do que o código anterior. Isso é ótimo porque agora está muito mais próximo da descrição do problema. Agora, resolvemos o problema da verbosidade. 
![[Capítulo 2 - Passing code with behavior parameterization-2.png]]

### 2.3.4 Seventh attempt: abstracting over List type
Podemos dar mais um passo em nossa jornada em direção à abstração. No momento, o método *filterApples* funciona apenas para o tipo *Apple*. Mas também podemos abstrair sobre o tipo *List*, indo além do domínio do problema em que está pensando:
```java
public interface Predicate<T> {
	boolean test(T t);
}

public static <T> List<T> filter(List<T> list, Predicate<T> p) {
	List<T> result = new ArrayList<>();
	for(T e: list) {
		if(p.test(e)) {
			result.add(e);
		}
	}
	return result;
}
```
Agora, o método *filter* pode ser usado com qualquer tipo de objeto, tornando o código mais reutilizável e flexível.

Agora podemos usar o método *filter* com uma lista de bananas, laranjas, Integers ou String!
```java
List<Apple> redApples = filter(inventory, (Apple apple) -> RED.equals(apple.getColor()));

List<Integer> evenNumbers = filter(numbers, (Integer i) -> i % 2 == 0);
```

## 2.4 Real-world examples
Vimos que a *parametrização de comportamento* é um padrão útil para se adaptar facilmente a requisitos que mudam. Esse padrão permite encapsular um comportamento (um pedaço de código) e *parametrizar* o comportamento de métodos ao passar a usar esses comportamentos  que criamos. Essa abordagem é semelhante ao padrão de projeto *strategy*. Muitos métodos na API do Java podem ser *parameterized* com comportamentos diferentes. Esses métodos são frequentemente usados junto com classes anônimas. Mostramos quanto exemplos que devem solidificar a ideia de passar código: ordenação com um *Comparator*, execução de um bloco de código com *Runnable*, retornando um resultado de uma tarefa usando um *Callable* e tratamento de eventos GUI.

### 2.4.1 Sorting with a *Comparator*
Ordenar uma coleção é uma tarefa comum em programação. Por exemplo, imagine que o nosso fazendeiro queira que organizemos o inventário de maçãs com base no peso delas. Ou talvez ele mude de ideia e prefira que as maçãs sejam ordenadas pela cor. Isso soa familiar? Sim, precisamos de uma maneira de representar e aplicar diferentes comportamentos de ordenação para nos adaptarmos facilmente às mudanças de requisitos. 

A partir do Java 8, a classe #List possui um método #sort (também podemos usar #Collections-sort). O <span style="background:#d4b106">comportamento</span> do método #Sort pode ser <span style="background:#d4b106">parametrizado</span> usando um objeto da interface #java-util-Comparator, que possui a seguinte definição:
```java
public interface Comparator<T> {
	int compare(T o1, T o2);
}
```

Dessa forma, podemos criar diferentes comportamentos para o método #sort implementando a interface #Comparator de forma personalizada. Por exemplo, é possível usá-la para ordenar um inventário de maçãs por peso crescente, utilizando uma classe anônima:
```java
inventory.sort(new Comparator<Apple>(){
	public int compare(Apple a1, Apple a2) {
		return a1.getWeight().compareTo(a2.getWeight());
	}
});
```

Se o fazendeiro mudar de ideia sobre como deseja ordenar as maçãs, podemos criar um #Comparator ad hoc para atender à nova necessidade e passá-lo para o método #sort. Os detalhes internos de como a ordenação é feita ficam abstraídos. Com uma expressão lambda, isso ficaria assim:
```java
inventory.sort(
	(Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight)
);
```


### 2.4.2 Executing a block of code with Runnable
Threads em Java permitem que um bloco de código seja executado simultaneamente com o restante do programa. Mas como informar a uma thread qual bloco de código ela deve executar? Diversas threads podem executar códigos diferentes. O que você precisa é de uma maneira de representar um pedaço de código para ser executado posteriormente.

Até o Java 8, apenas objetos podiam ser passados para o construtor de #Thread. Por isso, o padrão típico e pouco elegante era passar uma classe anônima contendo um método #run que retorna #void. Essas classes anônimas implementam a interface #Runnable.

Em Java, podemos usar a interface #Runnable para representar um bloco de código a ser executado; observe que esse código retorna #void (não tem retorno);
```java
public interface Runnable {
	void run();
}
```

Essa interface pode ser usada para criar threads com o comportamento desejado, como no exemplo abaixo:
```java
Thread t = new Thread(new Runnable() {
	public void run() {
		System.out.println("Hello World");	
	}
}); 
```

No entanto, desde o Java 8, podemos usar uma expressão lambda, tornando a chamada à Thread assim:
```java
Thread t = new Thread(() -> System.out.println("Hello World"));
```

### 2.4.3 Returning a result using Callable
