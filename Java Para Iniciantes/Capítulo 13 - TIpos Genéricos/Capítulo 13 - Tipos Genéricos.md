**Principais habilidades e conceitos**
• Entender as vantagens dos tipos genéricos
• Criar uma classe genérica
• Aplicar parâmetros de tipo limitado
• Usar argumentos curingas
• Aplicar curingas limitados
• Criar um método genérico
• Criar um construtor genérico
• Criar uma interface genérica
• Utilizar tipos brutos
• Aplicar a inferência de tipos com o operador losango
• Entender a técnica erasure
• Evitar erros de ambiguidade
• Conhecer as restrições dos genéricos

Desde  a sua versão original, muitos recursos novos foram adicionados a Java. Todos melhoraram e expandiram seu escopo, mas o que teve impacto particularmente profundo e extenso foi o tipo #genérico, o seu uso refletiu em toda a linguagem. Eles adicionam um elemento de sintaxe totalmente novo e causaram mudanças em muitas das classes e métodos da API principal. 

Um conhecimento básico sobre os tipos genéricos é essencial, visto que o tópico é extenso demais. 

## Fundamentos dos tipos genéricos
Quando nos referimos ao termo genérico, estamos nos referindo aos **tipos parametrizados**. Os tipos parametrizados são importantes porque nos permitem criar #classes, #interfaces e #métodos em que o tipo de dado usado é especificado como parâmetro. Uma classe, interface ou método que opera sobre um parâmetro de tipo é chamado de genérico, como em classe genérica ou método genérico. 

Uma vantagem importante do código genérico é que ele funciona automaticamente com o tipo de dado passado para seu parâmetro de tipo. Muitos algoritmos são logicamente iguais, não importando o tipo de dado ao qual estão sendo aplicados. Por exemplo, uma classificação rápida é igual classificando itens de tipo #Integer, #String, #Object ou #Thread. Com os genéricos, **podemos definir um algoritmo uma única vez**, independentemente do tipo de dado, e então aplicá-lo a uma ampla variedade de **tipos de dados** sem nenhum esforço adicional. 

É importante entender que Java sempre permitiu a criação de classes, interfaces e métodos generalizados usando referências de tipo #Object. Já que #Object é a super classe de todas as outras classes, uma referência #Object pode referenciar qualquer tipo de objeto. Logo, em códigos anteriores aos genéricos, classes, interfaces e métodos generalizados usavam referências #Object para operar com vários tipos de dados. O problema é que eles não faziam isso com segurança de tipos, já que #coerções eram necessárias para converter explicitamente #Object no tipo de dado que estava sendo tratado. Portanto, era possível gerar acidentalmente discrepâncias de tipo. Os genéricos adicionaram a segurança de tipos que estava faltando, porque tornaram essas coerções automáticas e implícitas. Resumindo, eles expandem nossa habilidade de reutilizar código e nos permitem fazê-lo de maneira segura e confiável. 

## Exemplo simples de genérico
Antes de discutir mais teoria, vamos examinar um exemplo simples de genérico. O programa a seguir define duas classes. A primeira é a classe genérica **Gen** e a segunda é **GetDemo**, que usa **Gen**. 
[[GenGemo.java | Código tipos primitivo]]

Vamos revisar o código acima com detalhes. Observamos como **Gen** é declarada pela linha a seguir:
```java
class Gen<T> {

}
```
Aqui, #T é o nome de um *parâmetro de tipo*. Esse nome é usado como espaço reservado para o tipo real que será passado para **Gen** quando um objeto for criado. Logo, **T** será usado dentro de **Gen** sempre que o parâmetro de tipo for necessário. Observe que **T** está dentro de <>. Essa sintaxe pode ser generalizada. Sempre que um parâmetro de tipo estiver sendo declarado, ele será especificado dentro de colchetes angulares <>. Já que **Gen** usa um parâmetro de tipo, é uma classe genérica.

Na declaração de Gen, não há um significado especial no nome T. Qualquer identificador válido poderia ter sido usado, mas o uso de T é tradicional. Além disso, é recomendável que os nomes dos parâmetros de tipo tenham apenas um caractere: uma letra maiúscula. Outros nomes de parâmetros de tipo normalmente usados são V e E.

Em seguida, **T** é usado para declarar um objeto chamado **ob**, como mostrado abaixo:
```java
T ob; // declara um objeto de tipo T. 
```

Como explicado, **T** <span style="background:#d4b106">é um espaço reservado para o tipo real </span>que será especificado quando um objeto **Gen** for criado. Logo, ob será um objeto do tipo passado para **T**. Por exemplo, se o tipo de **String** for passado para **T**, então, nesse caso, ob será de tipo #String.
Agora, consideramos o construtor de **Gen**:
```java
Gent(T o) {
ob = o;
}
```
Observamos que seu parâmetro, **o**, é de tipo T, ou seja, o tipo real de o será determinado pelo tipo passado para T quando um objeto **Gen** for criado. Além disso, já que tanto o parâmetro o quanto a variável membro ob são de tipo T, ambos terão o mesmo tipo quando da criação de um objeto **Gen**.
O parâmetro de tipo **T** também pode ser usado para especificar o tipo de retorno de um método, como ocorre com o método getOb:
```java
T getob() {
	return ob;
}
```
Já que ob também é de tipo T, seu tipo é compatível com o tipo de retorno especificado por #getOb()

O método showType exibe o tipo de T. Ele faz isso chamando getNAme() no objeto Clas retornado pela chamada a getClass() em ob. Não usamos esse recurso antes, logo, vamos examinar com detalhes. No capítulo 7 [[Capítulo 7 - Herança]], a classe #Object define o método getClass(). Portanto, #getClass é membro de todos os tipos de classe, visto que ela é definida pela classe Object. Ela retorna um objeto Class correspondente ao tipo de classe do objeto em que foi chmado. Class é uma classe definida dentro de #java-lang que encapsula informações sobre outra classe. Ela <span style="background:#d4b106">define vários métodos que podem ser usados na obtenção de informações sobre uma classe no tempo de execução</span>. Entre eles, está o método #getName, que retorna uma representação do nome da classe na forma de string. 

A classe GenDemo demonstra a classe genérica #Gen. Primeiro, ela cria uma versão de Gen para inteiros, como veremos abaixo:
```java
Gen<Integer> iOb;
```
Lembrando que não podemos passar tipos primitivos diretamente como parâmetros de tipos genéricos em Java. Isso ocorre pelo fato dos tipos genéricos em Java funcionarem apenas como tipos de referência (como classes), e os tipos primitivos (como int, double, etc) não são classes, mas sim tipos primitivos.

Podemos contornar essa limitação utilizando as classes #wrappers correspondentes dos tipos primitivos. Ao usar wrappers, nos beneficiamos do #autoboxing e #unboxing automáticos que convertem os tipos primitivos para wrappers e vice-versa.

Quando usamos Integer como argumento de tipo para o parâmetro de tipo de Gen, todas as referências a T são convertidas para referências a #Integer. Logo, para essa declaração, #ob é de tipo #Integer e o tipo de retorno de #getOb também.

No Java, o compilador não cria várias versões de uma classe genérica para cada tipo usado. Em vez disso, ele remove as informações de tipo genérico durante a compilação (um processo chamado #erasure) e insere verificações e conversões necessárias. Isso faz o código funcionar como se existissem versões especificadas da classe genérica, mas, na prática, apenas uma versão é gerada no programa. 

Logo, no exemplo do código:
```java
Gen<Integer> iOb;
```
1. Durante a #compilação, o compilador usa o tipo genérico (Integer) para verificar se o código está correto (como impedir que insiramos algo que não seja um #Integer);
2. Durante a #execução, não há mais referência ao tipo #Integer; tudo é tratado como se fosse o tipo base, geralmente #Object.
Logo, só temos uma única versão da classe Gen para todos os tipos genéricos, e o tipo especificado, #Integer, no caso, existe apenas quando o código está sendo compilado. O processo ao qual as informações do tipo genérico são removidos, se chama #erasure. 

A próxima linha atribui a iOb uma referência a uma instância de uma versão #Integer da classe #Gen.
```java
iOb = new Gen<Integer>(88);
```
Quando o construtor de #Gen é chamado, o argumento de tipo **Integer** também é especificado. Isso é necessário porque o objeto (nesse caso, **iOb**) ao qual a referência está sendo atribuída é de tipo **Gen<Integer** . Logo, a referência retornada por new também deve ser de tipo Gen< Integer >. Se não for, ocorrerá um erro de tempo de compilação. Por exemplo, a atribuição a seguir causará um erro de tempo de compilação. 
```java
iOb = new Gen<Double>(88.0); // Erro!
```
Isso é um dos principais benefícios dos genéricos porque assegura a segurança dos tipos.

Como os comentários do programa informam, a atribuição
```java
iOb = new Gen<Integer>(88);
```
faz uso do autoboxing para encapsular o valor 88, que é um int, em um #Integer. Isso funciona pelo fato de Gen< Integer > criar um construtor que recebe um argumento #Integer. Já que um #Integer é esperado, Java encapsulará automaticamente 88 dentro dele. 

## Genéricos só funcionam com tipos de referência
Na declaração de uma instância de um tipo genérico, o argumento de tipo passado para o parâmetro de tipo deve ser um tipo de referência. Não podemos usar um tipo primitivo, como #int ou #char. Por exemplo, com #Gen, é possível passar qualquer **tipo de classe** para T, mas não podemos passar um tipo primitivo para T. 

Certamente, não poder especificar um tipo primitivo não é uma restrição grave, porque podemos usar os **encapsuladores de tipos** (como fizemos com exemplo anterior) para encapsular um tipo primitivo. Além disso, o mecanismo Java de autoboxing e autounboxing torna o uso de encapsulador de tipos #transparente.

## Tipos genéricos diferem de acordo com seus argumentos de tipo
Um ponto-chave que devemos entender sobre os tipos genéricos é que uma referência de uma versão específica de um tipo genérico não tem compatibilidade de tipo com outra versão do mesmo tipo genérico. Por exemplo, supondo o programa que acabamos de mostrar, a linha de código abaixo está errada e não será compilada:
```java
iOb = strOb; // Errado!
```

Ainda que tanto iOb quanto strOb sejam de tipo Gen< T >, são referências a tipos diferentes porque seus argumentos de tipo diferem. Isso faz parte da maneira como os genéricos adicionam segurança de tipos e evitam erros.

## Classe genérica com dois parâmetros de tipo

