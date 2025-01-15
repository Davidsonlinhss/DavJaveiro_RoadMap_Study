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
