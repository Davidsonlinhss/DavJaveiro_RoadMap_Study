#Comparator é uma interface em Java, usada para definir a ordem de objetos em coleções que não têm uma ordem natural ou para modificar a ordem natural de uma classe. 
Ela permite comparar dois objetos de forma personalizada. 
## Características principais
- **Comparação personalizada**: O #comparator permite que definamos a lógica de comparação fora da classe dos objetos a serem comparados. Isso é útil quando <span style="background:#d4b106">precisamos de diferentes maneiras</span> de ordenar os objetos. 
- **Comparação com múltiplos critérios:** Ele permite comparar objetos <span style="background:#d4b106">com base em vários atributos</span>, como ordenar uma lista de pessoas primeiro pelo nome e, em caso de empate, pela idade. 

## Métodos Principais
- `compare(T o1, T o2)`: este método compara dois objetos do tipo `T`. Ele retorna:
- Um valor **negativo** se `o1` é menor que `o2`.
- **Zero** se `o1` é igual a `o2`.
- Um valor **positivo** se `o1` é maior que `o2`.

**`equals(Object obj)`**: Este método verifica se o `Comparator` é equivalente a outro objeto, mas normalmente não é necessário sobrescrevê-lo.

### Exemplo básico:

Suponha que você tenha uma classe `Produto` e queira ordenar uma lista de produtos pelo preço

```Java
class Produto {
    String nome;
    double preco;

    Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    @Override
    public String toString() {
        return nome + ": " + preco;
    }
}

class ComparadorPorPreco implements Comparator<Produto> {
    @Override
    public int compare(Produto p1, Produto p2) {
        return Double.compare(p1.preco, p2.preco);  // Compara com base no preço
    }
}

```

### Usando o `Comparator`:

Agora, podemos ordenar uma lista de produtos com base no preço:
```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto("Laptop", 2000));
        produtos.add(new Produto("Celular", 1200));
        produtos.add(new Produto("Tablet", 600));

        Collections.sort(produtos, new ComparadorPorPreco()); // Tentar entender o que está fazendo

        for (Produto p : produtos) {
            System.out.println(p);
        }
    }
}

```
### Saída:
```yaml
Tablet: 600.0
Celular: 1200.0
Laptop: 2000.0
```

### Uso com expressões lambda:

<span style="background:#d4b106">Em vez de criar uma classe separada que implementa</span> `Comparator`, podemos usar <span style="background:#d4b106">expressões</span> #lambda para simplificar o código:
```Java
Collections.sort((p1, p2) -> Double.compare(p1.preco, p2.preco));
```

### Métodos estáticos em `Comparator` (desde Java 8):

O `Comparator` tem vários métodos utilitários, como:
- **`Comparator.comparing()`**: Cria um `Comparator` com base em um campo específico.
- **`thenComparing()`**: Permite adicionar critérios de comparação adicionais.
### Resumo:
- `Comparator` permite definir regras de ordenação externas à classe.
- Facilita a criação de múltiplos critérios de ordenação.
- Pode ser implementado como classe anônima, lambda ou método de referência.
