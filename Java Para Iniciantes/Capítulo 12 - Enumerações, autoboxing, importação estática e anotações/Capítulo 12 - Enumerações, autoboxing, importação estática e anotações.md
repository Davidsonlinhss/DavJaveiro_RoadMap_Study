**Principais habilidades e conceitos**
• Entender os fundamentos da enumeração
• Usar os recursos de enumeração baseados em classes
• Aplicar os métodos values( ) e valueof( ) a enumerações
• Criar enumerações que tenham construtores, variáveis de instância e
métodos
• Empregar os métodos ordinal( ) e compareTo( ) que as enumerações
herdam de Enum
• Usar os encapsuladores de tipos Java
• Saber os aspectos básicos do autoboxing e autounboxing
• Usar autoboxing com métodos
• Entender como autoboxing [[Capítulo 12 - Enumerações, autoboxing, importação estática e anotações]]funciona com expressões
• Aplicar a importação estática
• Ter uma visão geral das anotações

## Enumerações
#Enumeração é uma lista de constantes nomeadas que define um novo tipo de dados. Um objeto de um tipo de enumeração só pode conter os valores definidos pela lista. Logo, uma enumeração fornece uma maneira de definirmos precisamente um novo tipo de dado que tem um número fixo de valores válidos.  

As enumerações são comuns no dia a dia. Por exemplo, uma enumeração dos meses do ano seria composta pelos nomes que vão de janeiro a dezembro, já uma enumeração dos dias da semana conteria domingo, segunda, terça, quarta, quinta, sexta e sábado.

Do ponto de vista da programação, as enumerações são úteis sempre que precisamos definir um conjunto de valores que **representam um grupo de itens**. Podemos utilizar uma enumeração para representar um conjunto de códigos de #status, como sucesso, em espera, falha e nova tentativa, indicando o progresso de alguma ação.

## Fundamentos da enumeração
Uma enumeração é criada com o uso da palavra-chave #enum:
```java
enum Transport {
CAR, TRUCK, AIRPLANE, TRAIN, BOAT
}
```
Os identificadores CAR, TRUCK, etc., são chamados de *constantes de enumeração*. Cada identificador é declarado implicitamente como membro público estático de Transport. O tipo das *constantes de enumeração* é o mesmo da enumeração em que elas são declaradas, que nesse caso é Transport. Na linguagem Java, essas constantes são chamadas de *autotipadas*, onde "auto" representa a enumeração que as contêm.

Uma vez definido uma enumeração, podemos criar uma #variável desse tipo. No entanto, ainda que as enumerações definam um tipo de classe, não podemos instanciar um enum usando o contrutor #New. Devemos declarar e usar uma variável de enumeração de maneira semelhante ao que fazemos com tipos primitivos:
```java
Transport tp;
```
Já que tp é de tipo Transport, os únicos valores que ela pode receber são os definidos pela enumeração. Por exemplo:
```java
tp = Transport.AIRPLANE;
```
Estamos atribuindo o valor tp ao valor AIRPLANE;

Duas constantes de enumeração podem ser comparadas em busca de igualdade com o uso do operador relacional == . 

O valor de uma enumeração também pode ser usado no controle de uma instrução #switch, por exemplo:
```java
// utilizaando uma enum para controlar uma isntrução switch
switch(tp) {
	case CAR:
	// ...
	case TRUCK:
	// ...
}
```

Os nomes das constantes de enumeração são usados sem qualificação pelo nome do tipo de enumeração, pois o tipo da enumeração na expressão #switch já especificou #implicitamente o tipo #enum das constantes case. 

Quando uma constante de enumeração é exibida, como em uma instrução println(), seu nome compõe a saída. Por exemplo:
```java
System.out.println(Transport.BOAT);
```
o nome BOAT será exibido no terminal.

As constantes de Transport usam maiúsculas. (Logo, usamos CAR e não car). No entanto, o uso de maiúsculas não é obrigatório, não há uma regra que exija que as constantes de enumeração estejam em maiúsculas. Já que com frequência as enumerações substituem variáveis de tipo #final, que <span style="background:#d4b106">tradicionalmente têm usado maiúsculas</span>, alguns programadores também acham apropriado escrever as constantes de enumeração em maiúsculas. 

## As enumerações Java são tipos de classes
Java implementa enumerações como tipos de classe. Mesmo que não instanciemos uma **enum** usando new, seu comportamento é semelhante ao de outras classes. O fato de enum definir uma classe permite que a enumeração Java tenha poderes que as enumerações de outras linguagens não tem. Podemos fornecer construtores, adicionar métodos e variáveis de instância e até mesmo implementar interfaces .

### Métodos values() e valuesOf()
Todas as enumerações possuem dois métodos automaticamente predefinidos: 
#valueOf e #values. 

O método #values retorna um #array contendo uma lista com as constantes de enumeração. 
O método #valueOf retorna a constante de enumeração cujo valor corresponde ao #string passado 

No exemplo anterior, no caso da enumeração de Transport, o tipo de retorno de Transport.valueOf("TRAIN") é igual a Transport. 

Exemplo de uso:
```java
Transport tp;

for(Transport t : Transport.values())
	System.out.println(t);

tp = Transport.valueOf("AIRPLANE");
System.out.println("top contains " + tp);
```
O método valueOf retorna o valor da enumeração associado ao nome da constante representado como um string.

## Construtores, métodos, variáveis de instância e enumerações
Cada constante de enumeração é um #objeto de seu tipo de enumeração. Logo, uma enumeração pode definir construtores, adicionar métodos e ter variáveis de instância. Quando definimos um construtor para uma #enum, ele é chamado quando cada constante de enumeração é criada. Cada constante pode chamar qualquer método definido pela enumeração e terá sua própria cópia de qualquer variável de instância também definida pela enumeração. 

Exemplo:
```java
enum Transport {
	CAR(65), TRUCK(55), AIRPLANE(600), TRAIN(70), BOAT(22); // última constante terminada em ;

	private int speed; // variável de instância

	Transport(int s) {speed = s;} // constructor, passando o valor recebido como argumento para speed
	
	int geetSpeed() {return speed;} // método

	class EnumDemo3 {
	public static void main(String args[]) {
			Transport tp;

			// Exibindo a velocidade;
			System.out.println("Velocidade típica de um avião: " + Tansport.AIRPLANE.getSpeed() + "mph".)

			// Exibindo todos os meios de transportes e velocidaades
			for(Transport t : Transport.values())
				System.out.println(t + "typical speed is " + t.getSpeed() + "mph");
		}
	}
}
```
Essa versão de Transport adiciona três elementos:
1. Variável de instância **speed**, que é utilizada para conter a velocidade de cada meio de transporte. 
2. Construtor de Transport, que recebe a velocidade de um meio de transporte.
3. Método **getSpeed** que retorna o valor de **speed**.
Quando a variável **tp** é declarada dentro de **main**, o construtor de Transport é chamado uma vez para cada constante especificada. Observamos como os argumentos do construtor são especificados, sendo colocados em parênteses após cada constante:
```java
CAR(65), TRUCK(55), AIRPLANE(600), TRAIN(70), BOAT(22)
```
Os valores são passados como argumentos para o parâmetro do método construtor em **s** de Transport(), que então irá atribuir o valor para speed.

Há algo mais que devemos observar sobre as constantes de enums, ela finaliza com ;, isto é, a última constante, BOAT, é seguida, por um ponto e vírgula. 

Cada constante de enumeração possuí seu próprio valor de speed, podemos obter a velocidade de um meio de transporte especificado chamando getSpeed(). Por exemplo:
```java
Transport.AIRPLANE.getSpeed();
```
Quando percorremos as constantes, utilizando o laço for-each, obtemos o valor de speed para cada constante de enumeração, o valor associado a uma constante fica separado e é diferente do valor associado a outra constante. <span style="background:#d4b106">Esse é um meio poderoso , que só está disponível quando enumerações são implementadas como classes, como ocorre em Java</span>. 

---
**Observação**
As enumerações tornaram as variáveis #final obsoletas? Não, as enumerações são apropriadas quando trabalhamos com listas de itens que devem se representados por identificadores. Uma variável #final é apropriada quando temos um valor constante, como o tamanho de um array, que será usado em muitos locais. 

Enum não pode herdar outra classe e enum não pode ser uma superclasse, ou seja, ser estendida. O segredo é sempre lembrar que cada constante de enumeração é um objeto da classe em que é definida.

## Enumerações herdam Enum 
Apesar de não podermos herdar uma superclasse ao declarar uma #enum, todas as enumerações herdam uma automaticamente: **java.lang.Enum**. Essa classe define vários métodos que estão disponíveis para uso de todas as enumerações. Quase nunca precisamos usar esses métodos, mas há dois que podemos empregar ocasionalmente: **ordinal()** e **compareTo()**.
O método #ordinal obtém um valor que indica a **posição de uma constante de enumeração** na lista de constantes. Ele é chamado de valor ordinal.
Os valores ordinais começam em zero. Logo, na enumeração Transport, CAR tem valor ordinaal igual a zero, TRUCK tem valor ordinal 1, AIRPLANE tem valor ordinal 2 e assim por diante.
Podemos comparar o valor ordinal de duas constantes da mesma enumeração usando o método #compareTo
```java
final int compareTo(tipo-enum e)
```
Se os valores comparados forem iguais, será retornado zero, se for diferentes, -1. Exemplo de programa que demonstra #ordinal e #compareTo :
```java
enum Transport {
	CAR, TRUCK, AIRPLANE, TRAIN, BOAT // não há outros membros, logo, não precisa de ponto e vírgula
}

class EnumDemo4 {
	public static void main(String args[]) {
		Transport tp, tp2, tp3;

		//Obtém todos os valores ordinais usando ordinal
		for(Transport t : Transport.values())
			System.out.println(t + " " + t.ordinal()); // obtendo os valores oridinais

		tp = Transport.AIRPLANE;
		tp2 = Transport.TRAIN;
		tp3 = Transport.AIRPLANE;

		System.out.println();

		// Demonstrando o compareTo
		if(tp.compareTo(tp2) < 0)
			System.out.println(tp + " comes before " + tp);
		if(tp.compareTo(tp2) > 0)
			System.out.println(tp2 + " comes before " + tp);
		if(tp.compareTo(tp3) == 0)
			System.out.println(tp + " equals " + tp3);
	}
}
```
Quando usamos o método #compareTo, estamos comparando o valor ordinal de duas constantes de enumerações. 

## Autoboxing
A partir do JDK5, Java incluiu dois recursos úteis: #autoboxing e #autounboxing. Esses recursos<span style="background:#d4b106"> visam simplificar e otimizar códigos que precisam converter tipos</span> #Primitivos em #objetos e vice-versa. Já que essas situações são encontradas com frequência em código Java, os benefícios do autoboxing/unboxing, afetam quase todos os programadores Java. 

Eles estão diretamente relacionados aos encapsuladores de tipos Java e à maneira como os valores são movidos para dentro e para fora da instância de um encapsulador. 

## Encapsuladores de tipos
Java usa tipos primitivos como #int ou #double, para armazenar os tipos de dados básicos suportados pela linguagem. Tipos #primitivos, em vez de #objetos, são usados para representar esses valores por questões de desempenho. O uso de objetos para esses tipos básicos adicionaria uma sobrecarga inaceitável até mesmo ao cálculo mais simples. Logo, **os tipos primitivos não fazem parte da hierarquia de objetos e não herdam** #Object.

Apesar dos benefícios oferecidos ao desempenho pelos tipos primitivos, podemos precisar de uma representação na forma de objeto. **Por exemplo, não podemos passar um tipo primitivo por referência para um método.**  Não podemos alterar o valor de um número do tipo primitivo dentro do método, por exemplo. Quando passamos para um método, o método recebe uma **cópia** do valor e não o valor original. Muitas das estruturas de dados padrão implementadas por Java operam em objetos, ou seja, não podemos usar essas estruturas de dados para armazenar tipos primitivos. Para tratar deste problema, Java fornece *encapsuladores de tipos*, que são classes que encapsulam um <span style="background:#d4b106">tipo primitivo dentro de um objeto</span>. 

Os #encapsuladores-de-tipos são #Double, #Float, #Long, #Integer, #Short, #Byte, #Character e #Boolean, que ficam no pacote java.lang. Essas classes oferecerem um amplo conjunto de métodos que nos permite integrar totalmente os tipos primitivos à hierarquia de objetos Java. 

Os encapsuladores mais utilizados são os que representam valores numéricos. #Byte, #Short, #Integer, #Long,  #Float e #Double. Todos herdam da classe #abstract #Number. Number declara métodos que retornam o valor de um objeto em cada um dos tipos numéricos diferentes:

```java
byte byteValue()

double doubleValue()

float floatValue()

int intValue()

long longValue()

short shortValue()
```

Cada um dos encapsuladores de tipos numéricos define construtores que permitem que um objeto seja construído a partir de um valor dado, ou a partir da representação desse valor na forma de string.:
```java
Integer(int num)

Inter(String str) throws NumberFormatException
```
Se *str* não tiver um valor numérico válido, uma #NumberFormatException será lançada. 

Todos os encapsuladores sobrepõem o método #toString.  Ele retorna a forma legível por humanos do valor contido dentro do encapsulador. Isso nos permite exibir o valor passando um objeto encapsulador de tipo para #println sem precisar convertê-lo em seu tipo primitivo.

O processo de encapsular um valor dentro de um objeto chama-se #boxing. Antes do JDK5, o boxing era feito manualmente, com o programador construindo de maneira explícita a instância de um encapsulador com o valor desejado. Por exemplo, a linha seguinte encapsula manualmente um valor 100 em um #Integer:
```java
Integer iOb = new Integer(100);
```
Neste exemplo, um novo objeto Integer, com valor 100, é criado #explicitamente e uma #referência (a #referência é o endereço dessa posição de memória, ou seja, um ponteiro para onde o objeto está armazenado na memória, geralmente #heap) a ele é atribuída a iOb.

O processo de extrair o valor de um encapsulador de tipo se chama #unboxing. Novamente, antes de JDK5, o #unboxing também era feito de forma manual, como programador chamando de maneira explícita um método no encapsulador para obter o seu valor. Por exemplo:
```java
int i = iOb.intValue();
```
Aqui, #intValue retorna o valor encapsulado dentro de iOb como um #int, ou seja, como um tipo primitivo. 

Programa mostrando os conceitos:
[[Wrap.java | Código Encapsulador Manual]]

O código acima possuí um recurso que foi #depreciado, depreciação é um aviso para evitar o uso de recursos que podem:
- Ter comportamento imprevisível ou pouco eficiente;
- Ser removido ou já ter sido removido em versões futuras, o que pode quebrar o código.
Logo, new Integer(int) não deve mais ser usado porque foi marcado como obsoleto.

O procedimento geral utilizado pelo exemplo anterior no boxing e unboxing manual de valores era requerido por versões de códigos Java anteriores a JDK 5 e ainda pode ser encontrado em código #legado. Felizmente, o autoboxing/unboxing melhora muito esses procedimentos essenciais. 

## Fundamentos do autoboxing
Autoboxing é o processo pelo qual um tipo primitivo é encapsulado (embalado) automaticamente no encapsulador de tipo equivalente sempre que um objeto desse tipo é necessário. Não há necessidade de construir explicitamente um objeto. O autounboxing é o processo pelo qual o valor de um objeto embalado é extraído (desembalado) automaticamente de um encapsulador de tipo quando seu valor é necessário. Não há necessidade de chamar um método como #intValue ou #doubleValue.

Maneira moderna de construir um objeto #Integer com o valor 100:
```java
Integer iOb = 100; // faz o autobox de um int
```
Observamos que o objeto não é criado explicitamente com o uso de #new. Java trata isso automaticamente. 

Para fazer o #unbox, apenas atribuímos a referência desse objeto a uma variável de tipo primitivo. Por exemplo, para fazer o unbox de iOb, podemos usar a seguinte linha:
```java
int i = iOb;
```

Exemplo de código que implementa as instruções:
```run-java
class AutoBox{
	public static void main(String args[]) {
		Integer iOb = 100;
		int i = iOb;
		System.out.println(i + " " + iOb); // exibe 100 100
	}
}
```

## Autoboxing e os métodos
Além do simples caso de atribuições, o autoboxing ocorre automaticamente sempre que um tipo primitivo deve ser convertido em um objeto, e o autounboxing ocorre sempre que um objeto deve ser convertido em um tipo primitivo. Logo, o autoboxing/unboxing pode ocorrer quando um argumento é passado para um método ou quando um valor é retornado por um método. Por exemplo:
[[AutoBox2.java | Código Boxing Automático]]
No programa, m() especifica um parâmetro Integer. Dentro de main(), m() recebe o valor int 199. Já que m() está esperando um **Integer**, esse valor sofre boxing automático. Em seguida, m2() é chamado. Ele retorna o valor int 10. Esse valor int é atribuiído a iOb em main(). Como iOb é um #Integer, o valor retornado por m2() sofre autoboxing. Agora, m3() é chamado. Ele retorna um Integer que é extraído automaticamente para um int. Para concluir, Math.sqrt() é chamado com iOb como argumento. Nesse caso, iOb sofre autounboxing e seu valor é promovido a #double, já que esse é o tipo esperado por #Math-sqrt.

## Autoboxing/unboxing ocorre em expressões
Em geral, o autoboxing e o unboxing ocorrem sempre que uma conversão para um objeto ou a partir de um objeto é necessária; isso se aplica a expressões. Dentro de uma expressão, um objeto numérico sofre unboxing automático. O resultado da expressão é encapsulado novamente, se preciso. Por exemplo:
[[AutoBoxTres.java]]
Prestemos a atenção na seguinte linha:
++iOb -> Ela faz o valor de iOb ser incrementado. Funciona do seguinte modo: iOb sofre #unboxing, o valor é incrementado e o resultado é encapsulado novamente, ou seja, sofre autoboxing.

## Advertência
Uma vez que temos o autoboxing e autounboxing, podemos ficar tentados a utilizar apenas objetos como Integer ou Double, abandonando totalmente os tipos primitivos. Mas não podemos utilizar em todos os casos, é menos eficiente do que o código escrito com o uso de tipos primitivos double ou int. Isso ocorre porque cada operação de autoboxing e autounboxing adiciona uma sobrecarga que não existe quando o tipo primitivo é usado. 

Em geral, devemos restringir o uso de encapsuladores de tipos apenas aos casos em que a representação de um tipo primitivo na forma de objetos seja requirida. O autoboxing/autounboxing não foi adicionado a Java como uma maneira "sorrateira" de eliminar os tipos primitivos. 

## Importação estática
Java dá suporte a um uso expandido da palava-chave #import. Se colocarmos a palavra-chave #static depois de #import, uma instrução import poderá ser usada para importar os membros estáticos de uma classe ou interface. Isso se chama importação estática. 
Para entender a utilidade da importação estática vejamos, o programa a seguir calcula as soluções de uma equação quadrática que tem esta forma:
```math
ax^2+bx+c=0
```
O programa usa dois métodos estáticos da classe interna Java Math de cálculos matemáticos, que faz parte de java.lang. O primeiro é Math.pow() e o segundo é Math.sqrt. 
[[Quadratic.java]]

Já que #pow e #sqrt são métodos estáticos, devem ser chamados com o uso do nome de sua classe, Math, o que por si, resulta em uma expressão um pouco confusa. Além disso, pode ser tedioso ter de especificar o nome da classe sempre que pow() ou outros métodos matemáticos forem necessários.

Podemos eliminar o incômodo de especificar o nome da classe usando a importação estática:
[[Quadratic2.java]]
Nesta versão do código, os nomes sqrt e pow ganham visibilidade por intermédio das isntruções de importação estática:
```java
import static java.lang.Math.sqrt;
import static java.lang.Math.pow;
```

A importação estática pode ser conveniente, mas é importante não utilizá-la de maneira abusiva. 

---
**Pergunte ao especialista**
**Usando a importação estática, posso importar os membros estáticos das classes que eu criar?**
Sim, podemos. Isso será particularmente conveniente quando definir vários membros estáticos usados com frequência em todo programa grande. 

## Anotações (metadados)
Java fornece um recurso que nos permite embutir informações complementares em um arquivo-fonte. Essas informações, chamadas de #anotação [[Capítulo 3 - Domain Models and Metadata | Metadados]], não alteram as ações de um programa. No entanto, podem ser usadas por várias ferramentas durante o desenvolvimento e a implantação, por exemplo, uma anotação pode ser **processada** por um gerador de código-fonte, pelo compilador ou por uma ferramenta de implantação . O termo #metadados também é usado para fazer referência a esse recurso, mas o termo anotação #anotation é o termo mais descritivo e normalmente mais usado.

A anotação é um tópico grande e sofisticado e não faz parte do escopo deste livro. 

Uma #anotação é criada com um mecanismo baseado na #interface. Exemplo simples:
```java
@interface MyAnno {
	String str();
	int val();
}
```
Esse exemplo declara uma anotação chamada **MyAnno**. Observamos o símbolo @ que precede a palavra-chave #interface. Ele informa ao compilador que um tipo de anotação está sendo declarado. Em seguida, temos dois #membros declarados dentro da interface str e val. Todas as anotações são compostas somente por declarações de #métodos. No entanto, não fornecemos corpos para esses métodos. Em vez disso, Java implementa os métodos. Além do mais, os métodos agem como campos.

Todos os tipos de anotações estendem automaticamente a interface #Annotation. Logo, Annotation é uma #superinterface de todas as anotações ela é declarada dentro do pacote #java-lang-annotation.
Originalmente, as anotações eram usadas para comentar apenas declarações. Quando usadas dessa forma, qualquer tipo de declaração pode ter uma anotação associada. Por exemplo, classes, métodos, campos, parâmetros e constantes enum podem ter anotações. Até mesmo a anotação pode ter uma anotação.

Quando aplicamos uma anotação, fornecemos valores aos seus membros. Por exemplo, aqui está um exemplo de MyAnno sendo aplicada a um método:
```java
// Anotação de um método
@MyAnno (str = "Annotation Example", val = 100)
public static void myMech() {
// ...
}
```
Essa anotação está vinculada ao método myMeth. Observamos com atenção a sua sintaxe. O nome da anotação, precedido por um @, é seguido por uma lista entre parênteses com **inicializações** de #membros. Para um membro receber um valor, ele é atribuído ao seu nome. Logo, no exemplo, o #string "Annotation Example" é atribuído ao membro str de MyAnno. Quando o membro de uma anotação recebe um valor, só seu nome é usado. Portanto, os membros da anotação parecem campos nesse contexto. 

As anotações que não têm parâmetros são chamadas de anotações marcadoras. Elas são especificadas sem a passagem de nenhum argumento e sem o uso de parênteses. Sua única função é a de marcar um item com algum atributo. #Entity é um exemplo @Entity.

Java define muitas anotações internas. A maioria é especializada, mas nove são de uso geral. Quatro são importadas de #java-lang-annotation: #Retention, #Documented, #Target e #Inherited. Cinco, #Override, #Deprecated, #SafeVarargs, #FunctionalInterface e #SupressWarnings, estão incluídas em #java-lan. 

#Override - um método com a anotação deve sobrepor o método de uma superclasse. Se não o fizer, isso resultará em um erro de tempo de compilação. É usada para assegurar que um método da superclasse seja realmente sobreposto e não apenas sobrecarregado. É uma anotação marcadora. 

---
## NOTA
JDK8 adicionou #Repeatable e #NAtive a #java-lang-annotation. #Repeatable dá suporte a anotações repetíveis, que são anotações que podem ser aplicadas mais de uma vez ao mesmo item. #Native é usada para comentar o campo de uma constante acessado por código executável (isto é, nativo). As duas são anotações de uso especial que não fazem parte do escopo deste livro. 
Exemplo que usa #Deprecated para marcar a classe **MyClass** e o método **getMesg()**. Quando tentar compilar esse programa, avisos relatarão o uso dos elementos substituídos.

