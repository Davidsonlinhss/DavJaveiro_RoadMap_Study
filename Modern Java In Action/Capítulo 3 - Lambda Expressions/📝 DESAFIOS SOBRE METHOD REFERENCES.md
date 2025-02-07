## **📝 DESAFIOS SOBRE METHOD REFERENCES**

### **1️⃣ Criando uma referência a um método estático**

Crie uma referência ao método estático `parseInt(String s)` da classe `Integer`, usando um `Function<String, Integer>` chamado `stringToInt`.

💡 **Dica:** Method references para métodos estáticos seguem o formato `Classe::método`.

---

### **2️⃣ Criando uma referência a um método de instância de um objeto específico**

Dado um `String nome = "Java";`, crie um `Supplier<Integer>` chamado `stringLength` que retorne o comprimento do nome usando method reference.

💡 **Dica:** Você pode usar `nome::length`, pois está referenciando um método de instância de um objeto específico.

---

### **3️⃣ Criando uma referência a um método de instância de um objeto arbitrário**

Dado um `List<String>`, use um `BiPredicate<List<String>, String>` chamado `verificarPresenca` para referenciar o método `contains` da interface `List`.

💡 **Dica:** Aqui, estamos chamando um método de instância, mas de um objeto arbitrário. O formato correto é `Classe::método`.

---

### **4️⃣ Criando uma referência a um construtor**

Crie um `Supplier<ArrayList<String>>` chamado `criarLista` para instanciar um `ArrayList<String>` usando method reference.

💡 **Dica:** Para referenciar construtores, usamos `Classe::new`.

---

### **5️⃣ Trabalhando com uma classe genérica**

Crie uma classe chamada `Printer` com um método `printMessage(String message)`. Depois, use um `Consumer<String>` chamado `imprimirMensagem` para referenciar esse método em uma instância da classe `Printer`.

💡 **Dica:** Quando referenciamos um método de instância de um objeto já existente, usamos `objeto::método`.

---

### **6️⃣ Ordenação de uma lista usando method references**

Dado um `List<String>`, ordene os elementos em ordem alfabética utilizando `Collections.sort()` e method reference.

💡 **Dica:** `String` tem um método estático `compareToIgnoreCase`, que pode ser útil para ordenação.

---

### **7️⃣ Transformação de dados com `map()`**

Dado um `List<String>` contendo números como `["1", "2", "3"]`, transforme essa lista em uma `List<Integer>` usando `map()` e method reference.

💡 **Dica:** Você já fez algo parecido no **Exercício 1**.

---

### **8️⃣ Persistência de objetos com method references**

Dado um `List<Pessoa>`, onde `Pessoa` tem um atributo `nome`, filtre todas as pessoas cujo nome começa com "A", utilizando method reference para chamar `startsWith(String prefix)`.

```java
class Pessoa {
    private String nome;
    public Pessoa(String nome) { this.nome = nome; }
    public String getNome() { return nome; }
}
```

💡 **Dica:** O método `getNome()` retorna um `String`, e podemos chamar `startsWith("A")` diretamente nele.

---

### **9️⃣ Criando um repositório usando method references**

Suponha que você tenha um repositório `PessoaRepository` com um método `findById(Long id)`.  
Crie um `Function<Long, Pessoa>` chamado `buscarPessoa` que usa method reference para chamar `PessoaRepository::findById`.

```java
class PessoaRepository {
    public Pessoa findById(Long id) { 
        return new Pessoa("Pessoa Exemplo"); 
    }
}
```

💡 **Dica:** A assinatura do método `findById` combina exatamente com um `Function<Long, Pessoa>`.

---

### **🔟 Criando uma API REST simplificada**

Suponha que você tenha um controlador Spring Boot chamado `PessoaController` com um método `getPessoa(Long id)`.  
Crie um `Function<Long, ResponseEntity<Pessoa>>` chamado `obterPessoa` que usa method reference para chamar `PessoaController::getPessoa`.

```java
@RestController
class PessoaController {
    @GetMapping("/pessoa/{id}")
    public ResponseEntity<Pessoa> getPessoa(@PathVariable Long id) {
        return ResponseEntity.ok(new Pessoa("Exemplo"));
    }
}
```

💡 **Dica:** `getPessoa(Long id)` retorna um `ResponseEntity<Pessoa>`, então encaixa bem em um `Function<Long, ResponseEntity<Pessoa>>`.

---

## **✅ RESPOSTAS**

### **1️⃣ Criando uma referência a um método estático**

```java
Function<String, Integer> stringToInt = Integer::parseInt;
```

---

### **2️⃣ Criando uma referência a um método de instância de um objeto específico**

```java
String nome = "Java";
Supplier<Integer> stringLength = nome::length;
```

---

### **3️⃣ Criando uma referência a um método de instância de um objeto arbitrário**

```java
BiPredicate<List<String>, String> verificarPresenca = List::contains;
```

---

### **4️⃣ Criando uma referência a um construtor**

```java
Supplier<ArrayList<String>> criarLista = ArrayList::new;
```

---

### **5️⃣ Trabalhando com uma classe genérica**

```java
class Printer {
    public void printMessage(String message) {
        System.out.println(message);
    }
}

Printer printer = new Printer();
Consumer<String> imprimirMensagem = printer::printMessage;
```

---

### **6️⃣ Ordenação de uma lista usando method references**

```java
List<String> lista = Arrays.asList("Banana", "Maçã", "Uva");
lista.sort(String::compareToIgnoreCase);
```

---

### **7️⃣ Transformação de dados com `map()`**

```java
List<String> numeros = Arrays.asList("1", "2", "3");
List<Integer> inteiros = numeros.stream()
    .map(Integer::parseInt)
    .collect(Collectors.toList());
```

---

### **8️⃣ Persistência de objetos com method references**

```java
List<Pessoa> pessoas = Arrays.asList(new Pessoa("Ana"), new Pessoa("Bruno"));
List<Pessoa> filtradas = pessoas.stream()
    .filter(p -> p.getNome().startsWith("A"))
    .collect(Collectors.toList());
```

---

### **9️⃣ Criando um repositório usando method references**

```java
PessoaRepository repository = new PessoaRepository();
Function<Long, Pessoa> buscarPessoa = repository::findById;
```

---

### **🔟 Criando uma API REST simplificada**

```java
PessoaController controller = new PessoaController();
Function<Long, ResponseEntity<Pessoa>> obterPessoa = controller::getPessoa;
```

---

Com esses exercícios, você construiu uma base sólida sobre **method references**, desde os conceitos básicos até aplicações reais em **persistência** e **APIs REST**. 🚀