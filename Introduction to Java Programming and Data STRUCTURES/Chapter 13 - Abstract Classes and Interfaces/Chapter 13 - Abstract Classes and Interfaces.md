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

You can use the #java-util-Arrays-sort method to sort an array of numbers or strings. Can you apply the same **sort** method to sort an array of geometric objects? In order to write such code, you have to know about interfaces. An *interface* is for defining common behavior for classes (including unrelated classes). Before discussing interfaces, we introduce a closely related subject: abstract classes.

## 13.2 Abstract Classes
*An abstract class cannot be used to create objects. An abstract class can contain abstract methods that are implemented in concrete subclasses.*

In the inheritance hierarchy, classes become more specific and concrete *with each new subclass*. If you move from a subclass back up to a superclass, the classes become more general and less specific. Class design should ensure a superclass contains common features of its subclasses. Sometimes, a superclass is so abstract it cannot be used to create any specific instances. Such a class is referred to as an *abstract class*. 

In Chapter 11, **GeometricObject** was defined as the superclass for **Circle** and **Rectangle.GeometricObject** models common features of geometric objects. Both **Circle** and **Rectangle** contain the **getArea()** and **getPerimeter()** methods for computing the area and perimeter of a circle and a rectangle. Since you can compute areas and perimeters for all geometric objects, it is better to define the **getArea()** and **getPerimeter()** methods in the **GeometricObject** class. However, theses methods cannot be implemented in the **GeometricObject** class because this implementation depends on the specific type of geometric object. Such methods are referred to as *abstract methods* and are denoted using the **abstract** modifier in the method header. After you define the methods in **GeometricObject**, it becomes an abstract class. Abstract classes are denoted using the **abstract** modifier in the class header. In UML graphic notation, the names of abstract classes and their abstract methods are italicized, as shown in Figure 13.1. Listing 13.1 gives the source code for the new **GeometricObject** class.

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

![[Chapter 13 - Abstract Classes and Interfaces.png]]

Abstract classes are like regular classes, but you cannot create instances of abstract classes using the **new** operator. An abstract method is defined without implementation. Its implementation is provide by the subclasses. A class that contains abstract methods must be defined as abstract. 

The constructor in the abstract class is defined as protected because it is used only by subclasses. When you create an instance of a concrete subclass, its superclass's constructor is invoked to initialize data fields defined in the superclass.

The **GeometricObject** abstract class defines the common features (data and methods) for geometric objects and provides appropriate constructors. Because you don't know how to compute areas and perimeters of geometric objects, **getArea()**  and **getPerimeter()** are defined as abstract methods. These methods are implemented in the subclasses. The implementation of **Circle** and **Rectangle** is the same as in Listings 11.2 and 11.3, except they extend the **GeometricObject** class defined in this chapter. 

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
   You may be wondering what advantage is gained by defining the methods **getArea()** and **getPerimeter()** as abstract in the **GeometricObject** class? The example in Listing 13.4 shows the benefits of defining them in the **GeometricObject** class. The program creates two geometric objects, a circle and a rectangle, invokes the **equalArea** method to check whether they have equal areas, and invokes the **displayGeometricObject** method to display them.

The methods **getArea()** and **getPerimeter()** defined in the **GeometricObject** class are overridden in the **Circle** class and the **Rectangle** class. The statements (lines 5-6)
GeometricObject geoObject1 = new Circle(5);
GeometricObject geoObject2 = new Rectangle(5, 3);

create a new circle and rectangle and assign them to the variables **geoObject1** and **geoObject2**. These two variables are of the **GeometricObject** type.

When invoking **equalArea(geoObject1, geoObject2)**, the **getArea()** method defined in the **Circle** class is used for **object1.getArea()**, since **geoObject1** is a circle/..

Similarly, when invoking **displayGeometricObject** (**geoObject1**), the methods **getArea()** and **getPerimeter()** defined in the **Circle** class are used, and when invoking **displayGeometricObject(geoObject2)**, the methods **getArea** and **getPerimeter** defined in the **Rectangle** class are used. The JVM dynamically determines which of theses methods to invoke at runtime, depending on the actual object that invokes the method.

### 13.2.2 Interesting Points about Abstract Classes
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
