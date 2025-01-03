*Um algoritmo tem de ser visto para ser entendido.* - Donal KNuth

Como programador profissional, passaremos muito tempo trabalhando com dados. Caso se torne um desenvolvedor web ou de aplicações, exibirá dados para os usuários quando eles visitarem seu site ou aplicação. Com frequência, <span style="background:#d4b106">teremos que manipular os dados antes de exibi-los para eles</span>. Caso nos tornemos um cientista de dados, passaremos ainda mais tempo trabalhando com dados. Talvez a Netflix o contrate para usar seus dados a fim de melhorar o algoritmo de recomendação de filmes.

Uma das tarefas mais básicas que um programador que trabalhe com dados precisa saber é como buscar os dados. Cientistas da computação buscam dados escrevendo um **algoritmo de busca**: um algoritmo que procure dados em um conjunto de dados. Um **conjunto de dados** é uma #coleção de dados. Dois exemplos comuns de algoritmos de busca seriam as buscas #linear e #binária. Como programador profissional, provavelmente não faremos algoritmos de busca, pois linguagens de programação, como Python, têm os própios algoritmos internos. No entanto, aprender como codificar alguns algoritmos de busca o tornará um programador melhor, já que o ajudará a entender com mais profundidade conceitos básicos da ciência da computação, como ordens de grandeza #linear e #logarítmica.

Neste capítulo, aprenderemos como procurar um número em uma lista usando dois algoritmos diferentes: busca linear e busca binária. Em seguida, após codificar por conta própria cada algoritmo de busca, iremos ver ferramentas de busca internas de Python, no meu caso, em #Java.

## Busca Linear
Em uma **busca linear** (ou **busca sequencial**), percorremos cada elemento de um conjunto de dados e o comparamos com o número-alvo. Se a comparação encontrar uma ocorrência, o número estará na lista. Se o algoritmo terminar sem encontrar uma correspondência, o número não estará na lista.
Algoritmo de busca linear em Java:
```run-java
public class LinearSearch {
	public static boolean linearSearch(int[] array, int n) {
		for(int i : array) {
			return true;
		}
	}
	return false;
}

	public static void main(String[] args) {
		int[] array = {1, 8, 32, 91, 5, 15, 9, 100, 3};
		int target = 91;
		System.out.println(linearSearch(array, target));
	}
}
```
[[LinearSearch.java | Algoritmo de Busca Linear em Java]]
A primeira parte do nosso programa é um método que retorna um valor do tipo boolean e passa uma lista e o número a ser procurado, *n*.
No nosso caso, int n = 91; logo, o nosso algoritmo está tentando ver se 91 está em nossa lista, array.

Utilizamos um loop #for-each para <span style="background:#d4b106">percorrer cada elemento do array</span>, for(int i : array). Se *n* for encontrado, o valor retornado será #True. Se percorrermos a lista e *n* não for encontrado, será retornado #False.

## Quando utilizar busca linear?
A complexidade de tempo de uma busca linear é O(n). No cenário de pior caso, em uma lista de dez itens, o nosso algoritmo executará dez etapas. O cenário de melhor caso de uma busca linear tem complexidade O(1) porque o item que estamos procurando pode ser o primeiro item da lista, logo o algoritmo executará apenas uma etapa, já que parará assim que encontrar uma correspondência. Na média, uma busca linear executa n/2 etapas.

Devemos considerar o uso de busca linear quando os dados não estiverem ordenados. **Dados ordenados** são dados organizados de maneira significativa. Por exemplo, podemos ordenar uma lista de números sequencialmente (na ordem crescente ou decrescente):
```java
int array1[] = {1, 2, 3, 4, 5, 6, 7, 10}; // lista ordernada em ordem crescente
int array2[] = {1, 23, 12, 44, 2, 3}; // lista não ordenada
```
Se os nossos dados estiverem ordenados, podemos usar uma busca #binária 

Quando estivermos programando no mundo real, em vez de escrever a própria busca linear, podemos utilizar a palavra-chave interna, Pyhton é #in, mas em Java, podemos utilizar o método #contains, exemplo:
[[SearchContains.java | Código usando Contains ArrayList]]
Com o uso do método #contains em #ArrayList, percorremos todos os elementos da lista até encontrar o elemento procurado, resultando em uma **busca linear** com complexidade O(n).

Também podemos fazer uma buscar linear para encontrar caracteres em #String:
[[SearchContainsString.class | Código usando Contains ArrayListString]]

## Busca Binária
É outra forma de #algoritmo mais rápido para a busca de um número em uma lista. No entanto, não podemos utilizar em qualquer conjunto de dados, pois ela só funciona quando os dados estão #ordenados. Uma busca binária procura um elemento em uma lista, dividindo-a em metades. Suponhamos que tivéssemos a lista de números ordenadas (do número mais baixo ao mais alto), abaixo:
```java
int nums[] = {10, 12, 13, 14, 15, 18, 19};
```
**A primeira etapa de uma busca binária** é localizar o número do meio. Há sete itens nessa lista, logo o número do meio é 14.

A próxima etapa é determinar se o número que estamos procurando é maior ou menor do que o número do meio. O número procurado, 19, é maior do que 14, portanto, não é necessário pesquisar a metade inferior da lista. Podemos descartá-la. Agora só sobrou a metade superior com três número para serem pesquisados.

```java
int nums [] = {15, 18, 19};
```
A busca binária entra, então, o número do meio novamente.
Já que 18 não é o número procurado, determinamos novamente se devemos manter a parte inferior ou a superior da lista. Como 19 é maior do que 18, mantemos a metade superior e descartarmos a inferior.

Isso deixa sobrando apenas um número, 19, que é o que estamos procurando. Se o número não fosse, 19, saberíamos que ele não está na lista.

Em uma busca #linear, teríamos levado sete etapas para encontrar o número 19. Precisamos apenas de três etapas com uma busca binária, que é menos da metade do número de etapas.
Vejamos um código de busca binária em Java:
[[BinarySearch.java | Código BInarySearch em Java]]
No exemplo de código acima, nós utilizamos algumas técnicas mais aprofundadas, que precisam ser revisada, como uso de expressões #ternárias e utilizamos o método #binarySearch que faz parte da classe #Collections, que está localizado no pacote java.util da biblioteca padrão do Java.

## Quando usar a busca binária
Uma busca binária demanda tempo O(log n). É mais eficiente do que a busca linear porque não precisamos pesquisar uma lista inteira. Em vez disso, podemos descartar segmentos inteiros da lista sem pesquisá-los. A eficiência da busca binária faz grande diferença quando lidamos com grandes quantidades de dados. Por exemplo, suponhamos que se estivéssemos pesquisando uma lista com um milhão de números. Se executasse uma busca linear, esta poderia demandar um milhão de etapas para concluir a pesquisa. Por outro lado, com uma busca binária logarítmica, levaria apenas 20 etapas.

O que significa dizer que um algoritmo é #logarítmico. A #exponenciação é uma operação matemática escrita como b^n, na qual multiplicamos um número b por si mesmo n vezes. Nessa equação, o número *b* chama-se **base** e o número *n* é o expoente. O processo de exponenciação significa elevar **b** à potência n. 

Um #logaritmo é a potência à qual devemos elevar o número para produzir outro número. É o inverso da exponenciação. Por exemplo, um logaritmo pode mostrar quantas vezes teríamos de multiplicar 2 por si mesmo para obter 8. Em notação matemática, essa questão seria representada por log<sub>2</sub>(8). A solução é 3, porque temos de multiplicar 2 por si mesmo 3 vezes para obter 8. 

Em uma busca binária, na primeira vez que dividirmos nossa lista em 2, sobrarão n/2 itens nela. Após a segunda iteração, sobrarão n/2/2 itens e, após a terceira, n/2/2/2 itens. 

Podemos usar um logaritmo para determinar quantas iterações uma busca binária demandará para encontrar um número em uma lista no cenário de pior caso. Por exemplo, suponhamos que tivéssemos uma lista de 100 números e quisesse saber quantas iterações uma busca binária demandará para descobrir se um número está nela ou não. Para saber isso, precisamos encontrar n em 2** n = 100, que é o mesmo que log2(100). Poderíamos começar com um palpite de que n é 5, mas 2** 5 = 32, que é muito baixo. Continuamos, então, tentando advinhar: 2^6 = 64, que também é muito baixo, e 2 ^ 7 que é 128, sendo maior do que 100 e, portanto, é a nossa resposta. Basicamente, precisamos de 7 etapas para encontrar, log de 100 na base 2 = 6.644,  mas o número de passos necessários são 7, pois arredondamos para o próximo inteiro.

Quando executamos uma busca binária, estamos dividindo a nossa lista pela metade a cada iteração, o que significa que o logaritmo que está descrevendo seu tempo de execução é de base 2. No entanto, na notação big O, a base de um logaritmo não importa porque podemos alterá-la multiplicando o logaritmo por uma constante. Os detalhes matemáticos não fazem parte do escopo do livro, mas o que é importante saber é que a base do logaritmo não importa na notação bi O. O importante é se um algoritmo é logarítmico, o que costuma ocorrer quando reduz a quantidade de cálculos pela metade ou por uma quantia significativa a cada iteração. 

Pela eficiência da busca binária, se tivermos dados ordenados que precisamos pesquisar, será melhor utilizá-la. Contudo, mesmo se tiver dados não ordenados, talvez valerá a pena classificá-los para se beneficiar dela. 

Resolvendo algoritmo de busca binária para palavras...

## Procurando caracteres
Sabemos como procurar caracteres em uma lista usando ferramentas de busca linear e binária internas. No entanto, se precisasse escrever uma busca linear ou binária a partir do zero para procurar caracteres em vez de números? Para saber como procurar caracteres, precisamos conhecer melhor como um computador os armazena.


