
## The Comparable Interface
Objetos de classes que implementam #Comparable pode ser ordenados. Em outras palavras, classes que #implementam #Comparable contém objetos que podem ser comparados de maneiras significativas. Comparable é #generic e é declarada como:
```java
interface Comparable<T>;
```
Logo, **T** representa o tipo de objeto sendo comparado.

A interface Comparable declara um método que é utilizado para determinar que Java chama de *ordenação natural* de instâncias de uma classe. A assinatura do método é:
```java
int compareTo(T obj);
```
Este método compara um objeto chamado com *with*. 
**Iguais** retorna 0 se os valores são iguais. 
E um valor negativo é retornado se o objeto chamado possui um valor abaixo. 
Do contrário, um valor positivo é retornado.

Essa interface é implementada por diversos tipos de classes , como #Byte, #Character, #Double, #Float, #Long, #Short, #String, #Integer, #Enum.

[[ComparablePlanet.java | Comparable]]
Exemplo de código, no código acima, usamos o método  #CompareTo de forma #Explícita . 