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
• Entender como autoboxing funciona com expressões
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
A partir do JDK5, Java incluiu dois recursos úteis: #autoboxing e #autounboxing. Esses recursos visam simplificar e otimizar códigos que precisam converter tipos #Primitivos em #objetos e vice-versa. Já que essas situações são encontradas com frequência em código Java, os benefícios do autoboxing/unboxing, afetam quase todos os programadores Java. 

Eles estão diretamente relacionados aos encapsuladores de tipos Java e à maneira como os valores são movidos para dentro e para fora da instância de um encapsulador. 

## Encapsuladores de tipos
Java usa tipos primitivos como #int ou #double, para armazenar os tipos de dados básicos suportados pela linguagem. 