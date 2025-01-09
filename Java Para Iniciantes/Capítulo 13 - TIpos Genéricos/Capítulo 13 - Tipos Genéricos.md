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
Podemos declarar mais de um parâmetro de tipo em um tipo genérico. Para especificar dois ou mais parâmetros de tipo, precisamos apensar usar uma lista separada por vírgulas. Por exemplo, a classe #TwoGen é uma variação da classe #Gen que criamos acima, possuindo dois parâmetros de tipo:
[[SimpGen.java | Two Generics Type]]
Observamos como TwoGen é declarada:

```java
class TwoGen<T, V> {...}
```
Ela especifica dois parâmetros de tipo, T e V, separados por uma vírgula. Já que há dois parâmetros de tipos, dois #argumentos de tipo devem ser passados para TwoGen quando um objeto for criado:
```java
TwoGen<Integer, String> tgObj = new TwoGen<Integer, String>(88, "Super-man");
```

No nosso exemplo, T é substituído por Integer e V é substituído por String. Embora os dois **argumentos de tipo** sejam diferentes, é possível que ambos sejam iguais. Por exemplo, a linha de código a seguir também é válida:
```java
TwoGen<String, String> x = new TwoGen<String, String>("A", "B");
```
Neste exemplo, tanto T quanto V seriam do tipo String. Uma observação é que, se os argumentos de tipo forem sempre iguais, estes seriam desnecessários.

## A forma geral de uma classe genérica
A sintaxe dos genéricos mostrada nos exemplos anteriores pode ser generalizada. Esta é a sintaxe de declaração de uma classe genérica:
```java
class nome-class<lista-parâm-tipo>{...}
```
E esta é a sintaxe completa de declaração de uma referência a uma classe genérica e criação de uma instância genérica:
```java
nome-class<lista-arg-tipo> nome-var = new nome-class<lista-arg-tipo>(lista-arg-cons);
```


## Tipos limitados
Nos exemplos anteriores, os parâmetros de tipo podiam ser substituídos por qualquer tipo de classe. Em muitos casos isso é bom, mas às vezes **é útil limitar os tipos** que podem ser passados para um parâmetro de tipo. (Limitando os tipos...)

Por exemplo, suponhamos que quiséssemos criar uma classe genérica que armazenasse um valor numérico e pudesse executar várias funções matemática, como calcular ou obter o componente fracionário. Também queremos usar a classe para calcular esses valores para qualquer tipo de número, inclusive **Integer**, **Float**, e **Double**. Logo, queremos especificar o tipo dos números genericamente, usando um parâmetro de tipo. Para criar essa classe, podemos fazer algo assim:
```java
class NumericFns<T>{
	T num;
	// Passa para o construtor uma referência
	// a um objeto numérico.
	NumericsFns(T n) {
		num = n;
	}

	// Retorna o recíproco.
	double reciprocal() {
		return 1 / num.doubleValue(); // Erro!
	}

	double fraction() {
		return num.doubleValue() - num.intValue(); // Erro!
	}
}
```
NumericsFns não será compilada da forma como foi escrita, porque os dois métodos gerarão erros de tempo de compilação. Primeiro, examinemos o método **reciprocal()**, que tenta retornar o recíproco de num. Para fazê-lo, ele deve dividir 1 pelo valor de **num**. O valor de num é obtido com uma chamada a **double Value()**, que obtém a versão **double** do objeto numérico armazenado em **num**. Já que todas as classes numéricas, como Integer e Double, são subclasses de Number, e Number define o método doubleValue(), esse método está disponível para todas as classes de encapsuladores numéricos. Resumindo:
A classe genérica **NumericFns< T >** tenta realizar operações numéricas com base em um tipo genérico T. No entanto, o compilador não sabe que T será sempre um tipo numérico, como #Integer, #Double, etc.

Por isso, os métodos como **num.doubleValue()** ou **num.intValue()** geram erros de compilação. O compilador não consegue garantir que T terá esses métodos, **pois T pode ser qualquer tipo**. Para resolver esse problema, precisamos de alguma maneira de <span style="background:#d4b106">dizer ao compilador que pretende passar apenas tipos numéricos</span> para **T**. Além disso, precisa de uma maneira de assegurar que só tipos numéricos sejam realmente passados. 

Logo, para tratar este problema, Java fornece os *tipos limitados*. Na especificação de um parâmetro de tipo, podemos criar um limite superior <span style="background:#d4b106">declarando a superclasse da qual todos os argumentos de tipo devem derivar</span>. Isso é feito com o uso de uma cláusula #extends na especificação do parâmetro de tipo:
```java
<T extends superclasse>
```

Essa sintaxe especifica que *T* só pode ser substituído pela superclasse, ou por subclasses da superclasse. Logo, superclasse define um limite superior no qual ela também se inclui.

Podemos usar um limite superior para corrigir a classe **NumericFns** especificando Number como o limite:
[[BoundsDemo.java]]

```Java
class NumericFns<T extends Number> {...}
```
Como **T** é limitado por #Number, ou seja, #Extends a superclasse #Number, o compilador Java sabe que todos os objetos de tipo **T** podem chamar **doubleValue()**; porque esse é um método declarado por **Number**.

A partir deste momento, não podemos criar um objeto do tipo String... Ocorrerá erro de compilação:
```Java
NumericFns<String> strOb = new NumericFns<String>("Error");
// String não pode ser usado porque não é subclasse de Number
```

Os tipos limitados são particularmente úteis quando é necessário assegurar que um parâmetro de tipo seja compatível com outro. 
Por exemplo, consideramos a classe a seguir chamada **Pair**, que armazena dois objetos que devem ser compatíveis:
```java
class Pair<T, V extends T> {
	T first;
	V second;

	Pair(T a, V b) {
		first = a;
		second = b;	
	}
}
```

Pair usa dois parâmetros de tipo, **T** e **V**, e que **V** estende **T**. Ou seja, **V** será igual a **T** ou a uma subclasse de **T**. Isso assegura que os dois argumentos do construtor de **Pair** sejam objetos do mesmo tipo ou de tipos relacionados. Por exemplo, as construções a seguir são válidas:
```java
// Isto está certo porque T e V são Integer.
Pair<Integer, Integer> x = new Pair<Integer, Integer>(1, 2);
// Isto está certo porque Integer é uma subclasse de Number.
Pair<Number, Integer> y = new Pair<Number, Integer>(10.4, 12);

//No entanto, a mostrada aqui não é válida:
// Esta linha causa um erro, porque String não é
// subclasse de Number
Pair<Number, String> z = new Pair<Number, String>(10.4, "12");
```
Neste último exemplo, String não é subclasse de #Number, o que viola o limite especificado por #Pair.

## Usando argumentos curingas
Mesmo sendo útil, às vezes a segurança de tipos pode invalidar construções perfeitamente aceitáveis. Dada a classe **NumericFns** mostrada no fim da seção anterior, suponhamos que quiséssemos adicionar um método chamado #absEqual que retornasse verdadeiro se dois objetos NumericFns contivessem números cujos valores absolutos fossem iguais. Além disso, queremos que esse método funcione apropriadamente, não importando o tipo de número que cada objeto contém. Mesmo se um objeto tiver o valor #Double 1.25 e o outro tiver o valor #Float -1.25, #absEqual retornará verdadeiro. Uma maneira de implementar absEqual é passar para ele um argumento **NumericFns** e então comparar o valor absoluto desse argumento com o valor absoluto do objeto chamador, só retornando verdadeiro se os valores forem iguais:
```java
NumericFns<Double> dOb = new NumericFns<Double>(1.25);
NumericFns<Float> fOb = new NumericFns<Float>(-1.25);

if(dOb.absEqual(fOb))
	System.out.println("Absolute values are the same.");
else
	System.out.println("Absolute values differ.");
```

À primeira vista, criar #absEqual parece uma tarefa fácil. Infelizmente, os problemas começam a surgir assim que tentamos declarar um parâmetro de tipo **NumericFns**. Que tipo devemos especificar como parâmetro de NumericFns? Inicialmente, poderíamos pensar em uma solução dada como a seguir, em que T é usado como parâmetro de tipo:
```java
boolean absEqual(NumericFns<T> ob) {
	if(Math.abs(num.doubleValue())) ==
		Math.abs(ob.num.doubleValue()) return true;
	return false;
}
```

O problema dessa abordagem é que ela só funcionará com outros objetos NumericFns cujo tipo for igual ao do objeto chamador. Por exemplo, se o objeto chamador for de tipo **NumericFns< Integer >**, o parâmetro ob também deve ser de tipo **NumericFns< Integer >**. Ele não pode ser usado para comparar um objeto de tipo NumericFns< Double >. 

Para criar um método absEqual genérico, devemos usar outro recurso dos genéricos Java: o #argumento-curinga. O argumento curinga é especificado pelo símbolo **?** e representa um tipo desconhecido. Com o uso de um curinga, veja uma maneira de criar o método:
``` java
boolean absEqual(NumericFns<?> ob) {
	if(Math.abs(num.doubleValue()) ==
		Math.abs(ob.num.doubleValue())) return true;
	return false;
}
```
Aqui, NumericFns< ? > equivale a qualquer tipo de objeto NumericFns, permitindo que dois objetos NumericFns, sejam quais forem, tenham seus valores absolutos comparados. Programa usando curinga:
[[WildcardDemo.java | Exemplo Utilizando Tipo Curinga]]
 Resumindo
 Usamos o tipo #curinga quando não sabemos de antemão qual tipo será usado, mas ainda precisa fazer alguma operação com ele. O curinga permite que criemos métodos genéricos que funcionem com qualquer tipo de objeto, sem especificar um tipo exato.

```JAVA
boolean compare(Box<?> otherBox) {
	return this.value.equals(otherBox.getValue());
}
```
**Explicação**
- Box< ? >: o ? no parâmetro significa que otherBox pode ser de qualquer tipo (Box< Integer >, Box< String> etc).

Logo, o curinga é útil quando queremos permitir que um método aceite qualquer tipo de objeto, sem precisar especificar qual tipo será. Isso dá flexibilidade para o método funcionar bem com diferentes tipos de dados. No exemplo, o curinga permitiu comparar caixas de tipos diferentes, como Box< Integer > e Box< String >

## Curingas limitados
 Os argumentos curingas podem ser limitados de maneira semelhante a como fizemos com o parâmetro de tipo. Um curinga limitado é particularmente importante quando estamos criando um método projetado para operar somente com objetos que sejam subclasses de uma superclasse específica.
```java
class A {
	// ...
}

class B extends A {
	// ...
}

class C extends A {
	// ...
}
// D não estende A
clas D {
	// ...
}
 
```
Aqui, a classe A é estendida pelas classes B e C, mas não por D.

Em seguida, consideramos a classe genérica simples mostrada abaixo:
```java
// classse genérica simples
class Gen<T> {
	T ob;

	Gen(T o) {
		ob = o;
	}
}
```
**Gen** usa um parâmetro de tipo, que especifica o tipo de objeto armazenado em ob. Já que T é ilimitado, seu tipo é irrestrito. Isto é, **T** pode ser qualquer tipo de classe.

Agora, suponhamos que quiséssemos criar um método que recebesse como argumento qualquer tipo de objeto **Gen** contanto que seu parâmetro de tipo seja A ou subclasse de **A**. Em outras palavras, queremos criar um método que opere somente com objetos de **Gen**< tipo >, onde tipo é **A** ou a subclasse de **A**. Para fazê-lo, deve usar um curinga limitado. Por exemplo, vejamos um método chamado **test** que só aceita como argumento objetos **Gen** cujo parâmetro de tipo é **A** ou subclasse de **A**:
```java
class UseBoundedWildcard {
    // Aqui, o símbolo ? equivalerá a A ou a
    // qualquer tipo de classe que estenda A.
    static void test(Gen<? extends A> o) {
        // ...
    }

    public static void main(String args[]) {
        A a = new A();
        B b = new B();
        C c = new C();
        D d = new D();

        Gen<A> w = new Gen<A>(a);
        Gen<B> w2 = new Gen<B>(b);
        Gen<C> w3 = new Gen<C>(c);
        Gen<D> w4 = new Gen<D>(d);

        // Estas chamadas a test() estão corretas.
        test(w);
        test(w2);
        test(w3);

        // Não pode chamar test() com w4 porque
        // ele não é um objeto de uma classe que
        // herde A.
	    test(w4); // Error!
    }
}

```

Em geral, para estabelecer o limite superior de um curinga, usamos o tipo de expressão abaixo:
```java
<? extends superclasse?
```
onde *superclasse* é o nome da classe que serve como limite superior. 

Também podemos especificar um limite inferior para um curinga adicionando uma cláusula super à sua declaração:
```java
<? super subclasse>
```
Nesse caso, só classes que sejam superclasses de subclasse são argumentos aceitáveis. A cláusula é inclusiva.

## Métodos genéricos
Como os exemplos anteriores mostraram, os métodos de uma classe genérica podem fazer uso do parâmetro de tipo da classe e, portanto, são automaticamente genéricos de acordo com o parâmetro de tipo. Entretanto, podemos declarar um método genérico que use um ou mais <span style="background:#d4b106">parâmetros de tipo</span> exclusivamente seus. Além disso, podemos criar um método genérico embutido em uma classe não genérica. 

O programa a seguir declara uma classe não genérica chamada **GenericMethodDemo** e um  método genérico estático dentro dessa classe chamado **arraysEqual**. O método faz a verificação se dois arrays contêm os mesmo elementos, na mesma ordem. Pode ser usado para comparação de dois arrays, sejam eles quais forem, desde que sejam de tipos iguais ou compatíveis e seus elementos sejam comparáveis.
[[GenericMethodDemo.java]]

Examinando **arraysEqual**, observamos:
```java
static <T extends Comparable<T>, V extends T> boolean arraysEqual(T[] x, V[] y)
```
Os parâmetros de tipo são declarados *antes* do tipo de retorno do método. Observamos que T estende #Comparable. #Comparable é uma #interface declarada em #java-lang. Uma classe que implementa Comparable define objetos que podem ser ordenados. Logo, usar um limite superior Comparable assegura que **arraysEqual** só possa ser usado com objetos que possam ser comparados. Comparable é genérica e seu parâmetro de tipo especifica o tipo dos objetos que ela compara. Observamos também que **arraysEqual** é estático, o que permite que seja chamado independentemente de qualquer objeto. É bom ressaltar, no entanto, que os métodos genéricos podem ser estáticos ou não estáticos.

Na primeira chamada:
```java
if(arraysEqual(nums, nums))
```
o tipo de elemento do primeiro argumento é **Integer**, o que faz **T** ser substituído por Integer. O tipo de elemento do segundo argumento também é Integer, o que também o faz substituir V. Logo, a chamada a **arraysEqual()** é válida e os dois arrays podem ser comparados.

A sintaxe usada na criação de **arraysEqual** pode ser generalizada:
```java
<lista-parâm-tipo> tipo-ret nome-mét(lista-parâm) {
	//...
}
```

## Construtores genéricos
Um construtor pode ser genérico, mesmo que sua classe não seja. Por exemplo, no programa a seguir, a classe **Summation** não é genérica, mas seu construtor é.
[[GenConsDemo.java]]
A classe Summation calcula e #encapsula (significa que a classe **armazena** e **protege** o valor calculado da soma dentro do objeto) a soma do valor numérico passado para seu construtor. Logo, para conseguir obter o valor, faz-se necessário o uso de métodos acessadores, #get.
Já que **Summation** especifica um parâmetro de tipo que é limitado por **Number**, um objeto **Summation** pode ser construído com o uso de qualquer tipo numérico.

## Interfaces genéricas
Uma interface pode ser genérica, no exemplo, a interface padrão **Comparable< T >** foi usada para sabermos se elementos de dois arrays podem ser comparados. É claro que também podemos definir nossas próprias interfaces genéricas. As interfaces genéricas são especificadas como as classes genéricas. Exemplo, criando uma interface chamada **Containment** que pode ser implementada por classes que armazenem um ou mais valores. Também declaramos um método chamado **contains** que determina se um valor especificado está contido no objeto chamador.

Toda classe que implemente uma interface genérica também deve ser genérica.
[[GenIFDemo.java]]

Embora a maioria dos aspectos desse programa seja de fácil compreensão, algumas observações importantes devem ser feitas. Primeiro, observamos que **Containment** é declarada assim:
```java
interface Containment<T>
```
Normalmente, uma interface genérica é declarada da mesma forma que uma classe genérica. No caso em questão, o parâmetro de tipo T especifica o tipo dos objetos contidos.

Em seguida, **Containment** é implementada por MyClass:
```java
class myClass<T> implements Containment<T> `{

}
```
Em geral, quando uma classe implementa uma interface genérica, essa classe também deve ser genérica, pelo menos ao ponto de usar um parâmetro de tipo passado para a interface. Por exemplo, a tentativa a seguir de declarar **MyClass** está incorreta:
```java
class MyClass implements Containment<T> { // ERRADO}
```
Essa declaração é errado, porque MyClass não declara um parâmetro de tipo, ou seja, não há como passar um para **Containment**. O identificador T é desconhecido e o compilador relatará erro. Mas uma classe pode implementar um tipo específico de interface genérica, como:
```java
class MyClass implements Containment<Double> {
	// CORRETO!
}
```
neste caso, a classe que está implementando não precisa ser genérica.

Também podemos limitar os tipos de dados passados como parâmetros para uma interface, logo, podemos limitar o tipo de dado para o qual a interface pode ser implementada. Por exemplo, se quiséssemos limitar **Containment** aos tipos numéricos, poderíamos declará-la assim:
```java
interface Containment<T extends Number> {
....
}
```
Neste caso, qualquer classe usuária deve passar para **Containment** um argumento de tipo com o mesmo limite. Por exemplo, agora **MyClass** deve ser declarada como mostrado aqui:
```java
class MyClass<T extends Number> implements Containment<T> {
	...
}
```

Como Containment requer um tipo que estenda Number, a classe que a está implementando deve especificar o mesmo limite. Uma vez que esse limite seja estabelecido, não há necessidade de espcificá-lo novamente na cláusula **implements**. Na verdade, seria errado fazê-lo. 
Uma vez que o parâmetro de tipo tiver sido estabelecido, ele será passado para a interface sem nenhuma modificação.

---
**Tente Isto 13-1** Crie uma fila genérica
Uma das vantagens mais arrojadas que os genéricos trazem à programação é a possibilidade de c<span style="background:#d4b106">onstrução de um código confiável e reutilizável</span>. Como mencionado no início deste capítulo, muitos algoritmos são iguais, não importando o tipo de dados em que são usados. Por exemplo, uma fila funciona da mesma forma, seja para inteiros, strings ou objetos File. Em vez de criar uma classe fila separada para cada tipo de objeto, podemos construir uma solução genérica para ser usada com qualquer tipo. Portanto, o ciclo de desenvolvimento composto por projeto, codificação, teste e depuração só ocorrerá uma vez quando criarmos a solução genérica - e não repetidamente, sempre que uma fila for necessária para um novo tipo de dado.

Neste projeto, vamos adaptar o exemplo da fila que vemos desenvolvendo desde a seção Tente isto 5-2 tornando-a genérica. O projeto representa a evolução final da fila. Ele inclui uma interface genérica que define operações da fila, duas classes de exceção e uma implementação da fila: uma fila de tamanho fixo. 

Este projeto organiza o código da fila em um conjunto de arquivos separados: 
- um para interface;
- um para cada exceção da fila;
- um para implementação da fila fixa;
- um para o programa que a demonstra.

[[GenQDemo.java | Código Projeto Tente isto 13-1]]

## Tipos brutos e código legado
Já que o suporte aos genéricos não existia antes do JDK5, era necessário que Java fornecesse algum meio dos códigos antigos anteriores aos genéricos fazerem a transição. Resumindo, os códigos anteriores aos genéricos tinham que ser ao mesmo tempo funcionais e **compatíveis com os genéricos**. Portanto, os códigos pré-genéricos devem funcionar com os genéricos e os códigos genéricos têm de funcionar com os códigos pré-genéricos.

Para realizar a transição para os genéricos, Java permite que uma classe genérica seja usada sem nenhum argumento de tipo. Isso cria um *tipo bruto* para a classe. <span style="background:#d4b106">Esse tipo bruto é compatível com códigos legados, que não têm conhecimento dos genéricos</span>. A principal desvantagem do uso de tipo bruto é a **segurança de tipos** dos genéricos ser perdida. 

Vejamos um exemplo que mostra um tipo bruto:
```java
Gen raw = new Gen(new Double(98.6));
```
No exemplo acima, vemos que não fornecemos um *argumento de tipo*, portanto, um tipo bruto é criado.
[[GenDemo.java]]

Esse programa contém várias coisas. Primeiro, um tipo bruto da classe genérica **Gen** é criado pela declaração:
```java
Gen raw = new Gen(new Double(98.6));
```

Observamos que nenhum argumento de tipo é especificado. Isso cria um objeto Gen cujo tipo **T** é substituído por #Object.

Um tipo bruto não garante a segurança de tipos. Logo, uma variável de tipo bruto pode receber uma referência a qualquer tipo de objeto Gen. O inverso também é permitido, onde uma variável de um tipo **Gen** específico pode receber uma referência a um objeto Gen bruto. No entanto, as duas opções são potencialmente inseguras, porque o mecanismo de verificação de tipos dos genéricos é ignorado.

A falta de segurança de tipos é ilustrada da seguinte maneira:
```java
int i = (Integer) raw.getob();
```
O valor de ob dentro de raw é obtido e induzido para **Integer**. O problema é que raw foi declarada com Double e não um valor inteiro. Porém, isto não é detectado no tempo de #compilação, pois o tipo de **raw** é desconhecido, Logo, essa instrução falha no tempo de #execução.
Passa na hora de compilar, falha na hora de executar.

Devido ao risco inerente ao uso dos tipos brutos, #javac exibe *avisos de não verificação* quando um tipo bruto é usado de uma maneira que possa ameaçar a segurança de tipos. 

**ATENÇÃO**
<span style="background:#ff4d4f">Devemos limitar o uso de tipos brutos aos casos em que tiver de combinar código legado com código genérico mais recente. Os tipos brutos são apenas um recurso de transição e não algo que deva ser usado em código novo.</span>

## Inferência de tipos com o operador losango
A partir do JKD7, podemos encurtar a sintaxe usada na criação de uma instância de um tipo genérico. Para começar, vamos relembrar da classe **TwoGen** mostrada anteriormente. Observamos que ela utiliza dois tipos genéricos:
```java
class TwoGen<T, V> {
	T ob1;
	V ob2;

	// Passa para o construtor uma referência
	// a um objeto de tipo T
	TwoGen(T o1, v o2) {
		ob1 = o1;
		ob2 = o2;
	}
	// ...
}
```
Em versões anteriores a #JDK7, para criar uma instância de TwoGen, temos:
```java
Twogen<Integer, String> tgOb = new TwoGen<Integer, String>(42, "testing");
```
Neste caso, temos os argumentos de tipo sendo especificados duas vezes: 
1. quando tgOb é declarada;
2. quando uma instância de TwoGen é criada via **new**. 

Já que os genéricos foram introduzidos por #JDK5, essa é a forma requerida por todas as versões de Java anteriores a #JDK7. Essa forma acaba sendo um pouco mais verbosa do que precisa ser. Logo, o JDK7 adicionou um elemento sintático que nos permite evitar a segunda especificação:
```java
TwoGen<Integer, String> tgOb = new TwoGen<>(42, "testing");
```

Portanto, a parte que **cria a instância** usa simplesmente <>, que é uma lista de argumentos de tipo. Isso se chama #operador-losando. Ele solicita ao compilador que infira os argumentos de tipo requeridos pelo construtor na expressão **new**. Portanto, essa declaração encurta o que às vezes gera instruções de declaração muito longadas. É particularmente útil para tipos **genéricos que especificam limites**.
Sua forma generalizada:
```java
nome-classe<lista-arg-tipo> nome-var = new nome-classe<>(lista-arg-cons);
```

Embora seja mais usada em instruções de declaração, a inferência de tipos também pode ser aplicada à passagem de parâmetros:
```java
boolean isSame(TwoGen<T, V> o) {
	if(ob1 == o.ob1 && ob2 == o.ob2) return true;
	else return false;
}
```
Logo, a chamada abaixo também será válida:

```java
if(tgOb.isSame)(new TwoGen<>(42, "testing"))) System.out.println("Same");
```

## Erasure
Geralmente, não é necessário o programador saber os detalhes de como o compilador Java transforma o **código-fonte** em **código-objeto**. No entanto, no caso dos genéricos, algum conhecimento geral do processo é importante, já que ele explica por que os recursos genéricos funcionam como funcionam - e por que às vezes seu comportamento surpreende. 

Uma restrição importante que conduziu a maneira de os genéricos serem adicionados à Java foi a necessidade de compatibilidade com versões anteriores da linguagem. Resumindo: o código genérico tinha que ser compatível com códigos não genéricos preexistentes. Logo, qualquer alteração na sintaxe da linguagem Java, ou em JVM, não poderia invalidar códigos mais antigos.  A forma a qual Java implementa os genéricos é respeitando a restrição e com a técnica #erasure

Em geral, é assim que erasure funciona: Quando o código Java é compilado, todas as informações de tipos genéricos são removidas (em inglês, erased). Ou seja, é feita a substituição dos parâmetros de tipo por seu tipo limiitado, que é o #Object quando nenhum limite explícito é especificado, e aplicação das coerções apropriadas. Essa abordagem dos genéricos não permite que existam parâmetros de tipo no tempo de #execução. Eles são simplesmente mecanismos do código-fonte.

Portanto, os genéricos funcionam em códigos antigos sem mudanças na #JVM. O código é compilado e as informações sobre tipos genéricos desaparecem no processo de compilação. Os parâmetros de tipo são substituídos por #Object (ou outro tipo limitado caso haja) e o Java adiciona conversões automáticas onde necessário. Portanto, os genéricos só existem no código fonte, sendo eliminados em tempo de execução.

## Erros de ambiguidade
A inclusão dos genéricos fez surgir um novo tipo de erro contra o qual devemos nos proteger: *a ambiguidade*. Erros de ambiguidade ocorrem quando o erasure faz duas declarações genéricas aparentemente distintas produzirem o mesmo tipo, causando um conflito. Exemplo:
```java
class MyGenClass<T, V> {
	T ob1;
	V ob2;

	void set(T o) {
		ob1 = o;
	}
	

	void set(V o) {
		ob2 = o;
	}

}
```
T e V parecem ser tipos diferentes, portanto isso aparenta ser correto. No entanto, existem dois problemas de **ambiguidade**
1. Do modo como foi criada, não é necessário que T e V sejam tipos diferente. Por exemplo, podemos construir:
```java
MyGenClass<String, String> obj = new MyGenClass<String, String>();
```
Tanto T quanto V serão substituídos por **String**. Isso torna as duas versões de set idênticas, portanto, um erro.

## Algumas restrições dos genéricos
Existem restrições quanto ao uso dos genéricos. Elas envolvem a criação de objetos de um parâmetro de tipo, membros estáticos, exceções e arrays.

## Parâmetros de tipos não podem ser instanciados
Não é possível criar uma instância de um parâmetro de tipo. Por exemplo, consideramos a classe a seguir:

```java
class Gen<T> {
	T ob;
	Gen() {
		ob = new T(); // Inválido
 	}
}
```
O compilador não tem como saber que tipo de objeto criar. **T** é simplesmente um espaço reservado. 

## Restrições aos membros estáticos
Nenhum membro #static pode usar um parâmetro de tipo declarado pela classe externa. Por exemplo, os dois membros #static dessa classe não são válidos:
```java
class Wrong<T> {
	static T ob;

	static T getob() {
		return ob;
	}
}
```

## Restrições aos arrays genéricos
Há duas restrições importantes dos genéricos aplicáveis aos arrays. Não podemos instanciar um array cujo tipo do elemento seja um parâmetro de tipo. Em segundo lugar, não pode criar um array de referências genéricas específicas de um tipo:
```java
class Gen<T extends Number> {
	T ob;
	T vals[]'; // correto

	Gen(T o, T[] nums) {
		ob = o;
	}

	// Essa instrução não é válida
	// vals = new T[10];

class GenArrays {
	public static void main(String args[]) {
		Integer n[] = { 1, 2, 3, 4, 5};
		Gen<Integer> iOb = new Gen<Integer>(50, n);

		Gen<Integer> gens[] = new Gen<Integer>[10]; // ERRADO

		Gen<?> gens[] = new Gen<?>[10]; // correto
	}
}
}
```
Como o programa mostra, é válido declarar uma referência a um array de tipo T:
```java
T vals[];
```

Mas não podemos instanciar um array de tipo T:
```java
vals = new T[10];
```
Não podemos criar um array de tipo T, pois não há como o compilador saber que tipo de array deve seer realmente criado. No entanto, podemos passar para Gen() uma referência a um array de tipo compatível quando um objeto for criado e atribuir essa referência a vals:
```java
vals = nums; // CORRETO ATRIBUIR REFERÊNCIA A UM ARRAY EXISTENTE
```

Isso funciona pelo fato de o array passado para Gen ter um tipo conhecido, que será o mesmo tipo de T no momento da criação do objeto. 

## Restrições a exceções genéricas
Uma classe genérica não pode estender #Throwable. Ou seja, ñ podemos criar classes de exceção genéricas.