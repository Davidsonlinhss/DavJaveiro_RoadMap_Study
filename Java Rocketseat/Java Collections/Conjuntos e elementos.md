## 2.7 - Arrays e Coleções
Arrays ou coleções são estruturas de dados que consistem em itens ou elementos relacionados a um mesmo tipo ou sub-tipo de dados. 

### 2.7.1 - Arrays
Consideramos também um array, como um grupo de posições reservadas na memória para armazenar elementos localizados através de um índice que se inicia com zero. Vejamos:

## 2.7.2 - Coleções
Uma coleção é a reunião de objetos de mesma natureza.
Em computação, uma coleção é um conjunto de dados geralmente do mesmo tipo que corresponde a um contexto abstrato que pode ser representado por estruturas como Listas, Conjuntos, Filas, Mapas, Grafos e etc. 

As coleções representam um conjunto de objetos. Em Java, o conceito de **coleção** é definido pela interface #Collection, que faz parte do **framework de coleções** do Java. Coleções incluem várias estruturas de dados, como **listas, conjuntos e filas.**

#### 2.7.2.4 Listas
Uma java.util.List é uma coleção que pode conter elementos duplicados. Uma `List`, é as vezes chamada de sequência. Assim como #arrays, as listas são indexadas a partir do índice zero (isto é, o índice do primeiro elemento é zero). 
Além de ser uma #interface que herda definições da #Colletion, uma lista fornece métodos para manipular seus elementos utilizando índices além de, manipular um intervalo especificado de elementos, pesquisar elementos e obter um #ListIterator para acessar  os elementos.

Conforme estrutura hierárquica da Collections Framework, a interface #List é implementada pelas classes abaixo:
#Vector - um array redimensionável com sincronização segura;
#ArrayList - um array redimensionável e indexado mais performático;
#LinkedList - uma lista encadeada com mais recursos de inserção de elementos;

# **Diferenças entre Arrays e Listas**
## 1. **Tamanho:**
- **Array**: Tem um tamanho fixo. Uma vez criado, o tamanho do array não pode ser alterado.
```Java
int[] arry = new int[5]; // Tamanho fixo de 5 elementos.
```
- **List**: tem um **tamanho dinâmico**. Podemos adicionar ou remover elementos, e o tamanho da lista será ajustado automaticamente. 
```Java
List<Integer> list = new ArrayList<>();
list.add(1); // O tamanho aumenta automaticamente.
```

## 2. Tipos de Dados:
- **Arry**: pode conter **tipos primitivos** (como int, char, double) ou objetos.
- **List:** apenas **objetos** podem ser armazenados, mesmo quando queremos trabalhar com tipos primitivos. Para isso, são usadas classes empacotadores, como #Integer no lugar de #int.

## 3. Funcionalidades:
- **Array:** tem funcionalidades limitadas. Para adicionar, remover ou buscar elementos, precisamos gerenciar manualmente o índice. 
- **List**: oferece **métodos de alto nível** para manipulação de dados, como #add, #remove, #contains, etc... facilitando o trabalho com os elementos. 
### 4. **Desempenho**:
- **Array**: Como o array tem um tamanho fixo, ele é geralmente mais **eficiente em termos de memória** e desempenho para operações simples (como acessar um elemento via índice).
- **List**: O **ArrayList** (uma implementação comum de `List`) pode ser menos eficiente em termos de desempenho quando há muitas operações de adição e remoção, especialmente em grandes listas, pois pode ser necessário redimensionar o array interno.

### 5. **Flexibilidade**:
- **Array**: Menos flexível, pois o tamanho é fixo e não possui métodos avançados para manipulação de elementos.
- **List**: Mais flexível, com suporte para manipulação dinâmica de dados e um conjunto robusto de operações úteis.

### 6. **Tipos Comuns**:
- **Array**: Utilizado em cenários onde o tamanho dos dados é conhecido antecipadamente e não precisa mudar.
- **List**: Usado em cenários em que o tamanho da coleção de dados pode variar e há necessidade de operações frequentes de manipulação.

## 2.7.2.5 Conjuntos
Uma java.util.Set é uma coleção que se destaca por garantir a exclusividade dos elementos em um conjunto, isso quer dizer, só poderá haver um elemento de mesmo valor ou referência com base nas regras de comparação.

#HashSet - uma coleção exclusiva e com alto desempenho;
#LinkedHashSet - uma coleção exclusiva e <span style="background:rgba(136, 49, 204, 0.2)">ordenada pela inserção</span>;
#TreeSet - uma coleção exclusiva, ordenada e classificada pela ordem <span style="background:rgba(5, 117, 197, 0.2)">natural dos elementos</span>.

## 2.7.2.7 Maps
A java.util.Map é uma coleção que representa uma estrutura chave/valor ou "mapping key/value" mapeando-se uma chave única à um valor onde, obviamente tanto a chave quanto o valor são basicamente objetos.

##
