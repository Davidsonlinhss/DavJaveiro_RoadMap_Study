## 1.8 Creating, Compiling and Executing a Java Progam
*You sava a Java programa in a .java file and compile it into a .class file. The .class file is executed by the Java Virtual Machine (JVM).*

You have to create your program and compile it before it can be executed. This process is repetitive, as shown in Figure 1.6. If your program has compile errors, you have to modify the program to fix them, then recompile it. If your program has runtime errors or does not produce the correct result, you have to modify the program, recompile it, and execute it again. 
![[Chapter 1 - Introduction to Computers, Programs and Java.png | 1000]]

A Java compiler translates a Java source file into a Java bytecode file. The following command compiles *Welcome.java:*
```sh
javac Welcome.java
```

If there aren't any syntax errors, the *compiler* generates a bytecode file with a **.class** extension. Thus, the preceding command generates a file name **Welcome.class**, as shown in Figure 1.8a. The Java language is a high-level language, but Java bytecode is a low-level language. The *bytecode* is similar to machine instructions but is architecture neutral and can run on any platform that has a *Java Virtual Machine (JVM)*. <font color="#ffc000">Rather than</font> (diferente de) a physical machine, the virtual machine is a program that **interprets Java bytecode**. This is onde of Java's primary advantages: *Java bytecode can run on a variety of hardware platforms and operating systems.* Java source code is compiled into Java bytecode, and Java bytecode is interpreted by the JVM. Your Java code may use the code in the Java library. The JVM executes your code along with (juntamente) the code in the library.

![[Chapter 1 - Introduction to Computers, Programs and Java-1.png]]

---
**Note**
O código das bibliotecas não são incluídos no momento de **compilação**, mas é integrado dinamicamente durante a execução pelo ambiente da JVM. 
1. **Compilação (java)**:
- O compilador traduz o código-fonte *.java* para bytecode *.class*;
- Durante esse processo, o compilador verifica a **presença das bibliotecas necessárias** (por exemplo, classes do Java API como *java.util.List*) para garantir que seu código é válido. Mas ele não insere diretamente o código dessas bibliotecas no bytecode gerado.
- A JVM carrega o bytecode do arquivo *.class* e, ao mesmo tempo, carrega o código das bibliotecas Java que seu programa necessita.
- Esse carregamento dinâmico permite que a JVM combine o bytecode do programa com as <span style="background:#affad1">implementações das bibliotecas no momento da execução</span>.

Resumindo: as APIs e bibliotecas Java não são incluídas no bytecode no momento da compilação, mas são vinculadas dinamicamente pela JVM durante a execução. Isso garante flexibilidade e portabilidade para o programa, aproveitando o design modular do Java.

---
You can execute the bytecode on any platform with a JVM, which is an interpreter. It translates the individual instructions in the bytecode into the target machine language code one at a time, rather than the whole program as a single unit. 

*Caution*
Do not use the extension *.class* in the command line when executing the program. Use *java ClassName* to run the program. If you use **java className.class** in the command line, the system will attempt to fetch **ClassName.class.class**.

*Note*
In JDK 11, you can use **java ClassName.java** to compile and run a single-file source code program. This command combines compiling and running in one command. A single-file source code program contains <span style="background:#affad1">only one class in the file</span>. This is the case for all of our programs in the first eight chapters.

*Tip*
If you execute a class file that does not exist, a **NotClassDefFoundError** will occur. If you execute a class file that does not have a **main** method or your mistype the **main** method (e.g., by typing Main instead of main), a **NoSuchMethodError** will occur.

*Note*
When executing a Java program, the JVM first loads the bytecode of the class to memory using a program called the *class loader*. If your program uses other classes, the class loader dynamically loads them just before they are needed. After a class is loaded, the JVM uses a program called the *bytecode verifier* to check the validity of the bytecode and to ensure that the bytecode does not violate Java's security restrictions. Java enforces strict security to make sure Java class files are not tempered with and do hot harm your computer.

## 1.9 Programming Style and Documentation
*Good programming style and proper documentation make a program easy to read and help programmers prevent erros.*

*Programming style* deals with what programs look like. A program can compile and run properly even if written on only one line, but writing it all on one line would be a bad programming style because it would be hard to read. *Documentation* is the body of explanatory remarks and comments pertaining to a program. Programming style and documentation reduce the chance of errors and make programs easy to read. 

### 1.9.1 Appropriate Comments and Comment Styles
Include a summary at the beginning of the program that explains what the program does, its key features, and any unique techniques it uses.  In a long program, you should also include comments that introduce each major step explain anything that is difficult to read. It is important to make comments concise so that they do not <span style="background:#d4b106">crowd</span> (sobrecarregam) the program or make it difficult to read. 

In addition to line comments (beginning with //) and block comments (begging with / * * ), Java supports comments of a special type, referred to as *javadoc comments*. Javadoc comments begin with /** and end with * /. They can be extracted into an HTML file using the JDK's *javadoc command*.  

Use javadoc comments for commenting on an entire class or an entire method. These comments must precede the class or the method header in order to be extracted into a javadoc HTML file. For commenting on steps inside a method, use line comments //. 

### 1.9.2 Proper Indentation and Spacing
A consistent indentation style makes programs clear and easy to read, and maintain. *Indentation* is used to illustrate the structural relationships between a program's components or statements. Java can read the program even if all of the statements are on the same long line, but humans find it easier to read and maintain code that is aligned properly. Indent each subcomponent or statement at least *two* spaces more than the construct withing which it is nested. 

A single space should be added on both sides of a binary operator:
1. Good style:
```java
System.out.println(3 + 4 * 4); // good
```

2. Bad style:
```java
System.out.println(3+4*4);
```

### 1.9.3 Block Styles
A *block* is a group of statements surrounded by braces. There are two popular styles, *next-line* style and *end-of-line* style:
1. Next-line style
```java
public class Test
{
	public static void main(String[] args)
	{
		System.out.println("Block Styles");
	}
}
```

2. End-of-line style
```java
public class Test {
	public static void main(String[] args) {
		System.out.println("Block Styles");
	}
}
```
The next-line style aligns braces vertically and makes programs easy to read, whereas the end-of-line style **saves space** (economiza espaço) and may help avoid some subtle programming erros. Both are acceptable block style. You should use a block style consistently - mixing styles is not recommended. <span style="background:#b1ffff">This books uses the end-of-line style to be consistent with de Java API source code</span> . Thanks!

## 1.10 Programming Erros
*Programming erros can be categorized in three types: syntax erros, runtime erros, and logic erros.*

### 1.10.1 Syntax Errors
Erros that are detected by the compiler are called *syntax erros* or *compile erros*. Syntax erros result from erros in code construction, such as <font color="#ffc000">mistyping a keyword</font> (digitação incorreta de uma palavra chave), omitting some necessary punctuation, or using an opening brace without a corresponding closing brace. 

These erros are usually easy to detect because the compiler tells you where they are and what caused them. For example, the program is Listing 1.4 a syntax error, as show:

```java
public class ShowSyntaxErros {
	public static main(String[] args) {
		System.out.println("Welcome to Java);
	}
}
```
Four erros are reported, but the program actually has two erros:
- The keyword *void* is missing before *main* in line 2;
- The string should be closed with a closing quotation mark in line 3.

Since a single error will often display many lines of compile erros, it is a good practice to fix erros from the top line and work downward. Fixing erros that occur earlier in the program may also fix additional erros that occur later.

**Tip**
If you don't know how to correct an error, compare your program closely, character by character, with similar examples in the text. In the first few weeks of this course, you will probably spend a lot of time fixing syntax erros. Soon you will be familiar with Java syntax, and can quickly fix syntax erros.

### 1.10.2 Runtime Erros
*Runtime erros* are erros that cause a program to terminate abnormally. The occur while a program is running if the environment detects an operation that is impossible to carry out, Input mistakes typically cause runtime errors. An *input error occurs* when the program is waiting for the user to enter a value, but the user enter a value that the program cannot handle. For instance, if the program expects to read in a number, but instead the user enters a string, this causes data-type erros to occur in the program. 

Another example of runtime erros is division by zero. This happens when the divisor is zero for integer divisions. For instance, the program is Listing 1.5 would cause a runtime error, as shown in Figure 1.11.

```run-java
public class ShowRun {
	public static void main(String[] args) {
		System.out.println(1 / 0);
	}
}
```

The runtime error causes the program to terminate abnormally. 

### 1.10.3 Logic Errors
*Logic errors* occur when a program does not perform the way it was intended to. Errors of this kind occur for many different reasons. For example, suppose you wrote the program in Listing 1.6. to convert Celsius 35 degrees to a Fahrenheit degree:
```java
public class ShowLogicErrors {
	public static void main(String[] args) {
		System.out.print("Celsius 35 is Fahrenhei9t degree ");
		System.out.println((9 / 5) * 35 + 32);
	}
}
```
You will get Fahrenheit 67 degrees, which is wrong. It should be 95.0. In Java, the division for integers is the quotient - the fraction part is truncated - so in Java 9 / 5 is 1. To get the correct result, you need to use 9.0 / 5, which results in 1.8.

In general, syntax errors are easy to find and easy to correct because the compiler gives indications as to where the erros came from and why they are wrong. Runtime erros are not difficult to find, either, since the reasons and locations for the erros are displayed on the console when the program aborts. Finding logic erros, on the **other hand**, can be very challenging. 

### 1.10.4 Common Errors
Missing a closing brace, missing a **semicolon**, missing quotation marks for string, and mispelling name are common erros for new programmers.


