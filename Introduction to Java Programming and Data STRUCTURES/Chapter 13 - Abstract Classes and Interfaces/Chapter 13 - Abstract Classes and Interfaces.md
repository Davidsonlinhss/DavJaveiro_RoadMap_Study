**Objectives**
- [ ] To design and use abstract classes;
- [ ] To generalize numeric wrapper classes **BigInteger** and **BigDecimal** using the abstract **Number** class.
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
<<<<<<< HEAD
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
>>>>>>> 0c6e8bbf279207ccdf56c3c5d23df0acdc6bf12d
