A #interface #Map em Java é parte do pacote #java_util e define uma estrutura de dados que mapeia #chaves para #valores. *Cada <span style="background:#d4b106">chave</span> no mapa deve ser única*, enquanto os <span style="background:#d4b106">valores</span> associados às chaves *podem ser duplicados*. 
Chave -> única;
Valores -> podem ser duplicados;

#Map não herda da #interface #Colletion, pois trabalha com <span style="background:#d4b106">pares de chave-valor</span>, diferentemente de outras coleções como #listas ou #conjuntos. 

## Características principais:
- **Chaves únicas:** cada chave pode ter apenas um valor associado. Se tentarmos inserir uma <span style="background:#d4b106">chave que já existe</span>, o <span style="background:#d4b106">valor será sobrescrito</span>;
- **Associação chave-valor:** cada item armazenado no mapa <span style="background:#d4b106">é um par de chave-valor</span>. A chave é usada para acessar o valor correspondente;
- **Implementações:** as principais da #interface #Map são:
1) #HashMap baseada em uma tabela hash, oferece busca rápida (O(1) em média), mas <font color="#ff0000">não mantém a ordem</font> das chaves;
2) #LinkedHashMap similar ao #HashMap, mas <font color="#ff0000">mantém a ordem</font> de inserção das chaves;
3) #TreeMap implementa a interface #SortedMap e mantém as chaves ordenadas;
4) #Hashtable: uma implementação mais antiga e sincronizada de HashMap

## Métodos Principais:
- **`put(K key, V value)`**: Associa o **valor** à **chave** especificada no mapa. Se a chave já estiver presente, o valor anterior será sobrescrito.
- **`get(Object key)`**: Retorna <span style="background:#d4b106">o valor associado à chave</span> fornecida ou `null` se a chave não existir no mapa.
-  **`remove(Object key)`**: Remove a chave (e seu valor associado) do mapa.
- **`containsKey(Object key)`**: Verifica se uma chave específica está presente no mapa.
-  **`containsValue(Object value)`**: Verifica se um determinado valor está presente no mapa.
-  **`keySet()`**: Retorna um `Set` <span style="background:#40a9ff">contendo todas as chaves presentes no mapa</span>.
-  **`values()`**: Retorna uma coleção <span style="background:#40a9ff">contendo todos os valores armazenados no mapa</span>.
-  **`entrySet()`**: Retorna um conjunto de pares `Map.Entry`, onde cada entrada é um par chave-valor do mapa.

### Diferentes tipos de mapas:
- **`HashMap`**: Não mantém a ordem das chaves.
- **`TreeMap`**: Ordena <span style="background:#d4b106">as chaves de forma natural</span> ou usando um `Comparator`.
- **`LinkedHashMap`**: Mantém a ordem de inserção das chaves.