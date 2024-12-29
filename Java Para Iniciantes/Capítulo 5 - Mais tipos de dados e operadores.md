## <span style="background:#d4b106">Usando o membro length</span>
Como os arrays são implementados como objetos, cada array tem uma variável de instância #length associada que contém o número de elementos que ele pode conter. (Em outras palavraas, #length contém o tamanho do array). 

A forma que o #length interage com o array bidimensional é especificamente diferenciada, quando usamos a expressão `table.length`, ela obtém o número de arrays armazenado em **table** que, no caso do exemplo anterior, é 3. Para obter o tamanho de qualquer array individual de table, usamos a expressão `table[0]. length`., que aqui obtém o tamanho do primeiro array de table. 

Uma outra coisa a ser notada é a forma como list.length é usado pelos lações #for para controlar o número de iterações. Uma vez que cada array carrega com ele seu tamanho, podemos usar essa informação em vez de controlar manualmente o tamanho de um array. Length não tem a ver com o número de elementos que estão sendo usados, ele **contém o número de elementos que o array pode conter**. 

A inclusão do membro #length simplifica os algoritmos tornando mais fácil – e seguro – executar certos tipos de operação com arrays. Por exemplo, o programa abaixo usa #length para copiar um array para outro ao mesmo tempo em que impede exceder o limite do array e a geração de exceção durante a execução.

## Tente Isto 5-2: Uma classe Queue
Como já sabemos, uma estrutura de dados é um meio de organizar os dados. A estrutura de dados mais simples é o array, uma lista linear que permite o acesso aleatório aos seus elementos. Com frequência, os arrays são usados como base para estruturas de dados mais sofisticados, como as #pilhas e #filas. 

#Pilha: é uma lista em que os elementos só podem ser acessados na ordem primeiro a entrar, último a sair (FILO, first-in, last-out).
#Fila: é uma lista em que os elementos só podem ser acessados na ordem primeiro a entrar, primeiro a sair (FIFO, first-in, last-out). 

Logo, uma pilha é como uma pilha de pratos em uma mesa - o primeiro de baixo para cima é o último a ser usado;
Uma fila é como uma fila em um banco - primeiro da fila é o primeiro a ser atendido;

 O que torna estruturas de dados como as pilhas e filas interessantes é que elas combinam o armazenamento de informações com os métodos que as acessam. Portanto, as pilhas e filas são *máquinas de dados* em que o armazenamento e a recuperação são fornecidos pela própria estrutura de dados e não **manualmente** pelo programa.

Em geral, as filas dão suporte a duas operações básicas: #put e #get. Cada operação put insere um novo elemento no fim da fila. Cada operação get, recupera o próximo elemento do início da fila. As operações de fila têm natureza consumidora: quando um elemento é retirado, não pode ser recuperado novamente. A fila também pode ficar cheia, se não houver espaço disponível para armazenar um item, e vazia, se todos os elementos tiverem sido removidos. 

Existem dois tipos básicos de filas – circular e não circular. 
**Circular:** reutiliza os locais do array subjacente quando elementos são removidos. 
**Não circular:** não reutiliza os locais e acaba se exaurindo. 
Para simplificar, esse exemplo cria uma fila não circular, mas com um pouco de raciocínio e esforço, podemos facilmente transformá-lo em uma fila circular. 

