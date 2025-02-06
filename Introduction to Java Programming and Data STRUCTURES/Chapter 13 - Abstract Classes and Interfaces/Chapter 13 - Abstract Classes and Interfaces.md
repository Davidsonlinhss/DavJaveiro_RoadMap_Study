**Objectives**
- [x] To design and use abstract classes;
- [x] To generalize numeric wrapper classes **BigInteger** and **BigDecimal** using the abstract **Number** class.
- [ ] To process a calendar using the **Calendar** and **GregoriCalendar** classes. 
- [ ] To specify common behavior for objects using interfaces;
- [ ] To define interfaces and define classes that implement interfaces;
- [ ] To define a natural order using the **Comparable** interface;
- [ ] To explore the similarities and differences among concrete classes, abstract classes, and interfaces;
- [ ] To design the **Rational** class for processing rational numbers;
- [ ] To design classes that follow the class-design guidelines.

## 13.1 Introduction
*A superclass defines common behavior for related subclasses. An interface can be used to define common behavior for classes (including unrelated classes).*

Por exemplo, o método `Arrays.sort` pode ser utilizado para ordenar números e strings, mas e se quisermos ordenar um array de livros? Para isso, é essencial entender **interfaces**, que permitem definir comportamentos comuns **entre diferentes classes**. No entanto, antes de explorarmos interfaces, é importante compreender um conceito relacionado: as classes abstratas.

## 13.2 Abstract Classes
*An abstract class cannot be used to create objects. An abstract class can contain abstract methods that are implemented in concrete subclasses.*

Na **hierarquia de herança**, as classes tornam-se mais específicas e concretas com cada nova subclasse. Se movermos de uma subclasse para cima, de volta para uma superclasse, as classes tornam-se mais gerais/genéricas e menos específicas, quem projetar a superclasse, precisa garantir que a superclasse vá conter as características que serão comuns as subclasses logo abaixo. 

Quando uma superclasse possuí um **nível de abstração elevado**, essa superclasse não pode ser diretamente utilizada para **criar instâncias específicas**, daí denominamos essa superclasse -> de **classe abstrata**.


No capítulo 11, GeometricObject foi definida como a #superclasse de Circle e Rectangle. GeometricObject modela características comuns de objetos geométricos. 
Tanto circle quanto Rectangle contêm os métodos getArea() e getPerimeter() para calcular a área e o perímetro de um círculo e de um retângulo. 

Como podemos calcular áreas e perímetros de todos os objetos geométricos, é melhor definirmos os métodos getArea() e getPerimeter() na classe GeometricObject. No entanto, esses métodos não podem ser implementados na classe GeometricObject porque sua implementação depende do tipo específico de objeto geométrico.

Os métodos descritos acima, são denominados métodos abstratos e são denotados usando o modificador #abstract no cabeçalho do método. Depois que definirmos os métodos em GeometricObject, ela se torna uma classe abstrata. Classes abstratas são denotadas usando o modificador #abstract no cabeçalho da classe. Na notação gráfica UML, os nomes das classes abstratas e seus métodos abstratos são italicizados, como mostrado na figura 13.1.
![[Chapter 13 - Abstract Classes and Interfaces.png]]

```java
public abstract class GeometricObject {
	private String color = "white";
	private boolean filled;
	private java.util.Date dateCreated;

	/** Construct a default geometric object */
	protected GeometricObject() {
		dateCreated = new java.util.Data();
	}

	/** Construct a geometric object with color and filled value */
	protected GeometricObject(String color, boolean filled) {
		dateCreated = new java.util.Data();
		this.color = color;
		this.filled = filled;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isFilled() {
		return filled;
	}

	public void setFilled(boolean filled) {
		this.filled = filled;
	}

	public java.util.Date getDateCreated() {
		return dateCreated;
	}

	
}
```

As **classes abstratas** são semelhantes às classes comuns, mas não é possível criar instâncias delas usando o operador new. Um **método abstrato** é definido sem implementação, e essa implementação deve ser fornecida pelas subclasses. Toda classe que contém métodos abstratos **deve ser declarada como abstrata**. 

O construtor de uma classe abstrata geralmente é definido como #protected, pois ele é utilizado apenas **por suas subclasses**. Quando uma instância de uma subclasse concreta é criada, o construtor da superclasse é chamado para inicializar os atributos definidos nela. 

A classe abstrata `GeometricObject` define as características comuns (atributos e métodos) para objetos geométricos e fornece construtores apropriados. Como não há uma forma genérica de calcular áreas e perímetros para qualquer objeto geométrico, os métodos getArea e getPerimeter são declarados como **abstratos** e implementados nas **subclasses**.

As implementações das classes `Circle` e `Rectangle` são as mesmas apresentadas anteriormente, com diferença que elas agora herdam a classe abstrata #GeometricObject:

```java
public class Circle extends GeometricObject {
	...
}
```

```java
public class Rectangle extends GeometricObject {
	...
}
```

### 13.2.1 Why Abstract Methods?
Qual vantagem é obtida ao definir os método getArea() e getPerimeter() como abstratos na classe #GeometricObject? O exemplo na Listagem 13.4 mostra os benefícios de defini-los na classe GeometricObject. O programa cria dois objetos geométricos, um círculo e um retângulo, invoca o método equalArea para verificar se eles têm áreas iguais, e invoca o método displayGeometricObject para exibi-los.

**Listing 13.4 TestGeometricObject.java**
```java
public class TestGeometricObject {
	public static void main(String[] args) {
		//Create two geometric objects
		GeometricObject geoObject1 = new Circle(5);
		GeometricObject geoObject2 = new Rectangle(5, 3);

		System.out.println("Os dois objetos possuem a mesma área?"+ 
			equalArea(geoObject1, geoObject2));

		// Display circle
		displayGeometricObject(geoObject1);

		// Display rectangle
		displayGeometricObject(geoObject2)
	}

	
		public static boolean equalArea(GeometricObject object1, GeometricObject object2) {
			return object1.getArea() == object2.getArea();
		}


		public static void displayGeometricObject(GeometricObject object) {
			System.out.println();
			System.out.println("The area is " + object.getArea());
			System.out.println("The perimeter is " + object.getPerimeter());
		}
}
```

Os métodos getArea( ) e getPerimeter() definidos na classe GeometricObject são sobrescritos nas classes Circle e Rectangle. As instruções 
```java
GeometricObject geoObject1 = new Circle(5);

GeometricObject geoObject2 = new Rectangle(5, 3);
```
criam um novo círculo e um retângulo e os atribuem às variáveis geoObject1 e geoObject2. Essas duas variáveis são do tipo GeometricObject. Ao invocar equalArea(geoObject1, geoObject2), o método getArea() definido na classe Circle é usado para object1.getArea(). 

### 13.2.2 Interesting Points about Abstract Classes
Os seguintes pontos sobre classes abstratas são dignos de nota:
- Um método abstrato não pode estar contido em uma classe não abstrata. Se uma subclasse de uma superclasse abstrata não implementar todos os métodos abstratos, a subclasse deve ser definida como abstrata. Em outras palavras, em uma subclasse não abstrata estendida de um a classe abstrata, todos os métodos abstratos devem ser implementados. Também observe que métodos abstratos não são estáticos. 

- Uma classe abstrata não pode ser instanciada usando o operador #new, mas ainda podemos definir seus construtores, que são invocados nos construtores de suas subclasses. Por exemplo, os construtores de GeometricObject são invocados na classe Circle e na classe Rectangle.

- Uma classe que contém métodos abstratos deve ser abstrata. No entanto, é possível definir uma classe abstrata que não contenha nenhum método abstrato. Esta classe abstrata é usada como uma classe base para definir subclasses.

- Uma subclasse pode sobrescrever um método de sua superclasse para defini-lo como abstrato. Isso é muito incomum, mas é útil quando a implementação do método na superclasse se torna inválida na subclasse. Nesse caso, a subclasse deve ser definida como abstrata.

- Uma subclasse pode ser abstrata mesmo se sua superclasse for concreta. por exemplo, a classe Object é concreta, mas suas subclasses, como GeometricObject, no exemplo acima, são abstratas.

- Você não pode criar uma instância de uma classe abstrata usando o operador #new, mas uma classe abstrata pode ser usada como um tipo de dado. Portanto, a seguinte instrução, que cria um array cujos elementos são do tipo GeometricObject, está correta:
```java
GeometricObject[] objects = new GeometricObject[10];
objects[0] = new Circle();
```
No exemplo acima, criamos uma instância de GeometricObject e atribuímos sua referência ao array. 

=======
Os seguintes pontos sobre classes abstratas merecem destaque:
- Um **método abstrato** não pode estar contido em uma classe **não abstrata**. Se uma subclasse de uma **superclasse abstrata** não implementar todos os métodos abstratos, essa subclasse deve ser definida como abstrata. Em outras palavras, em uma subclasse não abstrata estendida de uma classe abstrata, todos os métodos abstratos devem ser implementados. Além disso, note que métodos abstratos são não estáticos.
- Um **classe abstrata** não pode ser instanciada usando o operador #new, mas ainda é possível definir seus construtores, que são invocados nos construtores de suas subclasses. Por exemplo, os construtores da classe *GeometricObject* são chamados nas classes *Circle* e *Rectangle*.
- Uma classe que contém **métodos abstratos** deve ser abstrata. No entanto, é possível definir uma classe abstrata sem nenhum método abstrato. Esse tipo de classe abstrata é utilizado como **classe base** para definição de subclasses.
- Uma subclasse pode sobrescrever um método de sua superclasse e defini-lo como **abstrato**. Isso é incomum, mas pode ser útil quando a implementação do método na superclasse se torna inválida na subclasse. Nesse caso, a subclasse deve ser definida como abstrata.
- Uma subclasse pode ser **abstrata**, mesmo que sua superclasse seja **concreta**. Por exemplo, a classe #Object é concreta, mas suas subclasses, como *GeometricObject*, podem ser abstratas.
- Não podemos criar uma instância de uma classe abstrata usando o operador #new, mas uma classe abstrata pode ser usada como **tipo de dado**. Portanto, a seguinte declaração, que cria um array cujos elementos são do tipo **GeometricObject**, é válida:
```java
GeometricObject[] objects = new GeometricObject[10];
```
## 13.3 - Case Study: The Abstract Number Class
O *Number* é uma superclasse abstrata para as classes wrapper numéricas #BigInter e #BigDecimal. A seção 10.7 introduziu classes wrappers numéricas e a seção 10.9 introduziu #BigInter e #BigDecimal. Essas classes possuem métodos comuns *byteValue()*, *shortValue()*, *intValue()*, *longValue()*, *floatValue()* e *doubleValue()* para retornar um valor byte, short, int, long, float e double de um objeto dessas classes. Esses métodos comuns são realmente definidos na classe Number, que é uma superclasse para as classes de empacotamento numérico BigInter e BigDecimal.
![[Chapter 13 - Abstract Classes and Interfaces-1.png]]
Como os métodos intValue(), longValue(), floatValue() e doubleValue() não podem ser implementados na classe Number, eles são definidos como métodos abstratos na classe Number. Portanto, a classe Number é uma classe *abstrata*. Os métodos byteValue() e shortValue() são implementados a partir do método intValue() da seguinte forma:
```java
public byte byteValue() {
	return (byte)intValue();
}

public short shortValue() {
	return (short)intValue();
}
```
Com a classe *Number* definida como a superclasse para as classes numéricas, podemos definir métodos para realizar operações comuns com números. O programa abaixo apresenta um código para encontrar o maior número em uma lista de objetos Number:
```java
import java.util.ArrayList;
import java.math.*;

public class LargestNumber {
	public static void main(String[] args) {
		ArrayList<Number> list = new ArrayList<>();
		list.add(45); // add an integer
		list.add(3445.53); // add a double

		// add a BigInter
		list.add(new BigInteger("3432323234344343101"));

		// add a BigDecimal
		list.add(new BigDecimal("2.0909090989091343433344343"));

		System.out.println("The largest number is " + getLargestNumber(list));
	}

	public static Number getLargestNumber(ArrayList<Number> list) {
	if(list == null) || list.size() == 0
		return null

	Number number = list.get(0);
	for (int i = 1; i < list.size(); i++)
		if(number.doubleValue() < list.get(i).doubleValue())
			number = list.get(i);

	return number;
	}
}
```

O programa cria um *ArrayList* de objetos *Number*. Ele adiciona um objeto Integer, um objeto Double, um objeto BigInteger e um objeto BigDecimal à lista. Observamos que o número 45 é automaticamente convertido em um objeto Double e adicionado à lista na linha 8 usando autoboxing. Ao chamarmos o método *getLargestNumber*, o método retornará o maior número na lista. O método retorna *null* se a lista for *null* ou se o tamanho da lista for 0. Para encontrar o maior número na lista, os números são comparados invocando seu método doubleValue(). O método doubleValue() é definido na classe *Number* e implementado na subclasse concreta de *Number*. Se um número é um objeto Integer, o doubleValue do BigDecimal é invocado. Se o método doubleValue()

### 13.3.1 Por que as duas linhas de código a seguir compilam, mas causam um erro em tempo de execução? 
```java
Number numberRef = Integer.valueOf(0);
Double doubleRef = Double(numberRef);
```
**Por que o código compila?**
1. **Polimorfismo e upcasting:**
- *Integer* é uma subclasse de **Number**, então *Integer.valueOf(0)* pode ser atribuído a uma variável do tipo #Number. *Number numberRef = Integer.valueOf(0);* é um **upcasting** válido, já que **Integer** herda de **Number**.

1. **Conversão explícita (cast) de *Number* para *Double***:
- **Double doubleRef = (Double) numberRef;**
O compilador aceita esse tipo de conversão, pois *numberRef* é do tipo *Number*, e *Double* também herda de *Number*.
O compilador **não verifica o tipo real do objeto** em tempo de compilação, apenas a compatibilidade das classes. 

**Por que o código falha em tempo de execução?**
Quando o programa roda, o erro ocorre na seguinte linha:
```java
Double doubleRef = (Double) numberRef;
```
- Em tempo de execução, *numberRef* contém um objeto *Integer* (*Integer.valueOf(0)*).
- O cast  `(Double) numberRef` tenta forçar um *Integer* a ser tratado como um *Double*.
- Como *Integer* não é um **Double**, o Java lança um **ClassCastException:**

### 13.3.2 Why do the following two lines of code compile but cause a runtime error?
```java
Number[] numberArray = new Integer[2]; // cria um array de Integer
numberArray[0] = Double.valueOf(1.5); // Erro em tempo de execução
```
1. **Polimorfismo com arrays:**
- **Integer[]** é um subtipo de *Number* porque arrays em Java seguem a **covariância**. Isso significa que podemos atribuir um array de Integer a uma variável do tipo **Number[]**, pois Integer herda de *Number*.
- Isso faz com que a linha *Number[] numberArray = new Integer[2];* seja aceita pelo compilador.
-
2. **Atribuição de *Double* ao array de *Number***:
- Double.valuoeOf(1.5) é do tipo *Double*, que também herda de *Number*.

O Array real que foi criado é do tipo *Integer* e não do tipo *Number*;
Em tempo de execução, o Java verifica o tipo real do array e vê que estamos tentando armazenar um Double dentro de um *Integer*.

Como Double não é um *Integer*, o Java lança um *ArrayStoreException*.
**Como podemos evitar esse erro?**
1. Usar um array de *Number[]* corretamente:
```java
Number[] numberArray = new Number[2];
numberArray[0] = Double.valueOf(2.0);
numberArray[1] = Integer.valueOf(10);
```

1. Usar *List< Number>* em vez de arrays para evitar problemas de #covariância:
```java 
List<Number> numberList = new ArrayList<>();
numberList.add(Double.valueOf(1.5)); // Funciona
numberList.add(Integer.valueOf(10));
```
A principal diferença aqui é que List< Number> não é covariante, então não permite atribuir List< Integer> a List< Number>, evitando esse tipo de erro.

### 13.3.4 What is wrong in the following code? (Note the *compareTo* method for the *Integer* and *Double* classes was introduced in Section 10.7)
```java
public class Teste {
	public static void main(String[] args) {
		Number x = Integer.valueOf(3);
		System.out.println(x.intValue()); // obtém o valor interior 3
		System.out.println(x.compareTo(4));
	}
}
```

Temos um erro de compilação no método *compareTo*, pois *compareTo(int)* é um método da classe **Integer**, não da classe **Number**. Como x é do tipo **Number**, não há garantia de que ele seja um **Integer**, e o método **compareTo(int)** não está disponível na classe Number.
## 13.4 Case Study: Calendar and GregorianCalendar
*GregorianCalendar* é uma **subclasse** concreta da classe abstrata *Calendar*. 

Uma instância de *java.util.Date* representa um instante específico no tempo com precisão de **milissegundo**. 
*java.util.Calendar* é uma classe base abstrata para extrair informações detalhadas de calendário, como o **ano**, **mês**, **data**, **hora**, **minuto**, e **segundo**. Subclasses de *Calendar* podem implementar sistemas de calendário específicos, como o calendário **Gregoriano**, o calendário **lunar** e o calendário **judaico**. Atualmente, o *java.util.GregorianCalendar* para o calendário **Gregoriano*** é suportado no **Java**, conforme mostrado na figura 13.3. O método add é abstrato na classe *Calendar*, pois sua implementação depende de um sistema de calendário concreto.

Podemos usar o *new GregorianCalendar()* para construir um GregorianCalendar padrão com a hora atual e *new GregorianCalendar(year, month, date)* para construir um **GregorianCalendar** como o **ano**, **mês** e **data** especificados. O parâmetro **mês** é baseado em 0, ou seja, 0 corresponde a **Janeiro**.

O método *get(int field)* definido na classe *Calendar* é útil para extrair informações de data e hora de um objeto Calendar. Os campos são definidos como constantes, conforme mostrado na Tabela 13.1

![[Chapter 13 - Abstract Classes and Interfaces-2.png]]

[[TestCalendar.java]]
**Índice do array**
O array *dayNameofWeek* está definido como:
```java
String[] dayNameOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday" ...}
```
Os índices deste array começcam em 0 (ou seja, "sunday" está no índice 0, "Monday" no índice 1, e assim por diante).

**Ajuste com *-1***
Como o valor retornado por *calendar1.get(Calendar.DAY_OF_WEEK)* começa em 1(para Domingo), mas o índice do array *dayNameofWeek* começa em 0, é necessário subtrair 1 para alinhar corretamente o valor do dia da semana com o índice do array.

**Portanto:** o *-1* serve para ajustar o valor retornado por *calendar1.get(Calendar.DAY_OF_WEEK*) que começa em 1 ao índice do array *dayNameOfWeek* que começa em 0. Isso garante que o dia da semana seja mapeado corretamente para o nome correspondente no array.

O método *set(int field, value)* definido na classe *Calendar* pode ser usado para definir um campo. Por exemplo, podemos usar *Calendar.set(Calendar.DAY_OF_MONTH, 1)* para definir o calendário para o primeiro dia do mês.

O método *add(field, value)* adiciona uma quantidade específica a um determinado campo.
- *add(Calendar.DAY_OF_MONT, 5)* adiciona cinco dias ao tempo atual do calendário.
- *add(Calendar.DAY_OF_MONTH, -5* subtrai cinco dias do tempo atual do calendário.
Para obter o número de dias em um mês, use *Calendar.getActualMaximum(Calendar.DAY_OF_MONTH)*. Se o calendário fosse Março, esse método retornaria 31.

Podemos definir um tempo representado em um objeto *Date* para o calendário invocando *calendar.setTime(date)*. Para recuperar o tempo, usamos *calendar.getTime()*.

### 13.4.1 É possível criar um objeto Calendar usando a classe Calendar?
Não podemos criar um objeto Calendar usando a própria classe Calendar, pois ela é uma classe *abstrata*. Em vez disso, usamos a subclasse concreta, como *GregorianCalendar*.

### 13.4.2 Qual método na classe Calendar é abstrato?
O método *add* na classe *Calendar* é **abstrato**. Sua implementação depende de um sistema de calendário concreto fornecido por uma subclasse (por exemplo, *GregorianCalendar*).

### 13.4.3 Como criamos um objeto Calendar para o horário atual?
Usamos o método *Calendar.getInstance()*, que retorna um calendário inicializado com a data e hora atuais no fuso horário e localidade padrão:
```java
Calender calendar = Calendar.getInstance();
```
### 13.4.4 Para um objeto Calendar C, como obter o ano, mês, data, hora, minuto e segundo?
```java
Calendar c = new GregoriCalendar();
// ano
c.get(Calendar.YEAR)

// mês
c.get(Calendar.MONTH)
```

