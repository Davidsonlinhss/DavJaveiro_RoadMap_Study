 Um algoritmo é uma #sequência de passos ou **etapas** que usamos para resolver um problema ou para sequenciar uma ação. Por exemplo, um algoritmo de como fazer ovos mexidos seria algo parecido com:
1. Abrir a geladeira;
2. Pegar os ovos na geladeira;
3. Fechar a porta da geladeira;
4. Colocar os ovos sobre uma bancada;
5. Abrir o armário;
6. Pegar uma tigela e uma frigideira;
7. Colocar a tigela sobre o balcão e a frigideira na boca do fogão;
8. Quebrar os ovos na tigela;
9. Separar as casas dos ovos quebrados (gema e clara);
10. Pegar uma batedeira;
11. Bater o conteúdo dentro da tigela;
12. Abir o fornecimento de gás;
13. Acender a boca do fogão onde a frigideira se encontra;
14. Pegar a tigela com a gema e a clara batida;
15. Despejar sobre a frigideira quente;
16. Mexer com uma espátula os ovos;
17. Retirar a frigideira do fogão assim que estiverem prontos.

Devemos levar em consideração que as etapas descritas estão completamente abstraídas, ou seja, muitas ações foram omitidas para tornar o exemplo simplório.

Neste capítulo, aprenderemos a comparar dois algoritmos para que isso nos ajude a analisarmos esses algoritmos. É importante sabermos o porque um algoritmo pode ser melhor do que o outro, porque passamos horas escrevendo algoritmos e decidindo que estruturas de dados usar com eles. **Se não soubermos o motivo de escolher um algoritmo em vez de outro, não saremos programadores eficientes**.

Embora os algoritmos sejam um conceito essencial na CIência da Computação, os cientistas da computação ainda não entraram em acordo sobre uma definição formal. A definição de **Donald Knuth** (pensei que fosse Donal Trump, pois li bem rápido, kkk), é uma das melhores definições. Ele descreve um algoritmo como um processo definido, eficaz e finito que recebe entradas e produz saídas de acordo com essas entradas. 

Aqui, o autor define:
1. #Precisão como etapas claras, concisas e não ambíguas;
2. #Eficácia significa poder executar com precisão cada operação para resolver o problema;
3. #Finitude significa o algoritmo parar após um número finito de etapas.
4. #Exatidão um algoritmo deve sempre produzir a mesma saída para uma entrada específica e essa saída deve ser a resposta correta para o problema que ele resolve.

## Analisando algoritmos
Podemos utilizar mais de um algoritmo para resolver um problema. Por exemplo, existem diversas maneiras de ordenar uma #lista. Se muitos algoritmos já resolveram o problema, então, como **decidir e saber qual é o melhor**? É o mais simples? O mais rápido? O menor?

Podemos avaliar um algoritmo pelo **seu tempo de execução** (**run time**). O tempo de execução, #run_time é quanto tempo um computador leva para executar um algoritmo escrito em uma linguagem de programação como Python. Exemplo:
```run-python
for i in range(1, 6):
print(i)
```
Podemos medir o tempo de execução desse algoritmo usando o módulo interno #time do #Python para rastrear quanto tempo o nosso computador leva para executá-lo:
```python
import time
start = time.time()
for i in range(1, 6):
print(i)
end = time.time()
print(end - start)
```

O tempo de execução muda constantemente a cada vez que rodamos o código pelo fato do processamento disponível em nosso computador mudar constantemente a cada momento, o que por sua vez acaba afetando o tempo de execução. (Fico imaginando como seria rodar em uma máquina de escrever)...
O tempo de execução também seria diferente em outro computador. Bem como, também será afetado se executarmos o mesmo algoritmo em outra linguagem de programação. 

Já que o tempo de execução altera dependendo das variáveis, como poder de processamento, linguagem etc. Não é uma maneira eficaz comparar dois algoritmos pelo tempo de execução, em vez disso, os cientistas da computação comparam algoritmos examinando o **número de etapas** que eles exigem para processar a entrada e exibir o resultado. Podemos inserir o número de etapas envolvidas em um algoritmo, em uma fórmula que compare dois ou mais algoritmos, sem considerar a linguagem de programação ou o computador. 

Se o exemplo anterior leva cinco etapas para ser concluído (percorre um loop cinco vezes e exibe i cada vez). Podemos expressar o número de etapas que o algoritmo requer com a seguinte equação:
```math
||{"id":16996501352}||

f(n) = 5
```
Se tornarmos o programa mais complexo, a equação mudará. Por exemplo, podemos querer calcular a soma de todos os números que está sendo exibido:
```python
count = 0
for i in range(1, 6)
print(i)
count += i
```
Agora o algoritmo leva 11 etapas para ser concluído. Primeiro, atribui zero à variável #count. Em seguida, exibe cinco números e incrementa cinco vezes (1 + 5 + 5)

```math
||{"id":151115420421}||

f(n) = 11
```

O que acontecerá se alterarmos o 6 do código para uma variável?
```python
count = 0
for i in range(1, n)
print(i)
count += i
```
A equação mudará para
```math
||{"id":602409042456}||

f(n) = 1 +2n
```
Agora, o número de etapas que o algoritmo executará vai depender do valor de *n*. O 1 da equação representa a **primeira etapa** : count = 0. Em seguida, existem duas vezes *n* etapas. Por exemplo, se n for 5, f(n) = 1 + 2 * 5. Os cientistas da computação chamam a variável *n* de uma equação que descreve o número de etapas de um algoritmo de **tamanho do problema**. Podemos dizer que o tempo necessário para resolver um problema de tamanho *n* é 1 +2n ou, em notação matemática:
```math
||{"id":198861292058}||

T(n) = 1 + 2n
```

No entanto, uma equação que descreva o número de etapas de um algoritmo não é muito útil porque, entre outras coisas, nem sempre é possível contar confiavelmente o número de etapas. Por exemplo, se um algoritmo tiver muitas instruções condicionais, não teremos como saber de antemão qual delas será executada. A boa notícia é que, como cientista da computação, não precisamos nos preocupar com o número de etapas de um algoritmo. O que é necessário saber é o desempenho do algoritmo quando *n* tende a aumentar.

A maioria dos algoritmos tem bom desempenho com um conjunto de dados ( #dataset) pequeno, mas pode ser um desastre com conjuntos de dados maiores. Até mesmo o algoritmo mais ineficiente terá um bom desempenho com n = 1. Mas no mundo real, n poderá ser centenas, milhares ou milhões.

O que precisamos, então, focar, não é saber o número de etapas que um algoritmo possuí para a sua execução, mas estimar o número de etapas conforme n aumenta. À medida que N aumentar, uma parte da equação ofuscará o restante a ponto de tudo o mais se tornar irrelevante. 

O que acontece quando n aumenta?
Equação do número de etapas do algoritmo
```math
T(n) = n + n^3
```
n^3 pelo fato de executar n etapas três vezes.

---
### Loops e crescimento exponencial
- Um loop simples (n): um único loop, como:
```python
for i in range(n);
```
Executa *n* vezes porque cada iteração é realizada uma vez para cada valor de *i* de 0 a *n-1*

- Dois loops aninhados (n²):
```python
for i in range(n):
	for j in range(n):
```
Aqui o loop externo executa n vezes, e para cada execução do externo, o interno também executa *n* vezes. No total:
```math
n * n = n²
```

### Por que o n^3 domina a equação?
Quando n é pequeno (por exemplo, n = 10), o n³ (1.000) é muito maior que n(10), mas ainda não esmagador.

Quando n cresce (por exemplo, n = 1000)
- n resulta em 1000 etapas;
- n³ resulta em 1.000.000.000 (i bilhão) de etapas.
- Isso mostra que o n³ domina o comportamento do algoritmo para valores maiores de n.

Loops aninhados levam ao crescimento exponencial do número de etapas. 

---
Logo, quando n aumenta, a segunda parte do algoritmo cresce tão rapidamente que a primeira parte torna-se irrelevante. Por exemplo, se precisarmos que o programa trabalhasse com 100.000.000 de registros de banco de dados, não nos preocuparíamos com quantas etapas a primeira parte da equação executa, pois a segunda parte vai executar exponencialmente mais etapas. Com 100.000.000 de registros, a segunda parte do algoritmo executaria mais de um septilhão de etapas, o que seria 1 seguido de 24 zeros, logo não se trata de um algoritmo razoável para ser utilizado. As primeiras 100.000.000 etapas não serão relevantes para a nossa decisão.

Já que a parte importante é a que cresce com a rapidez que *n* aumenta, os cientistas da computação usam a notação big O para expressar a eficiência do algoritmo em vez de uma equação T(n). A notação #big_O é uma notação matemática que descreve como os requisitos de tempo ou espaço de um algoritmo crescem conforme o tamanho de *n* cresce. 

Logo, os cientistas da computação usam a notação Big O para criar uma função de ordem de grandeza a partir de T(n). A **ordem de grandeza** é uma classe em um sistema de classificação no qual cada classe é muitas vezes maior ou menor do que a classe anterior. Em uma função de ordem de grandeza, usamos a parte de T(n) predominante na equação e ignoramos o restante. A parte de T(n) predominante na equação é a ordem de grandeza de um algoritmo. 

---
A notação Big O é uma forma de medir a **eficiência de um algoritmo** em termos de:
- **Tempo de execução** (quantas etapas ele precisa para terminar).
- **Uso de memória** (quanto espaço ele consome).

Ela foca em como o algoritmo se comporta **quando o tamanho da entrada (n) cresce muito.**

**Como funciona a Big O**
Imagine que temos a equação citada anteriormente, T(n), que representa o número de etapas que o algoritmo precisa executar, dependendo de *n*. Por exemplo:
```math
T(n) = n + n^3
```
1. **Identificar o termo predonominante**
- Quando *n* é pequeno (n = 2, n = 3), os dois termos (n e n³) são mais ou menos do mesmo tamanho.
- Mas quando n cresce (n = 1.000, n = 1.000.000), o n^3 é muito maior que o n.
- Exemplo:
- n = 1.000: n = 1.000 e n³ = 1.000.000.000

2. **Ignorar os termos menores**
- Na notação Big O, só nos <span style="background:#d4b106">preocupamos com o termo predominante</span> porque ele é o que realmente afeta o tempo de execução quando *n* é grande.
- NO caso de T(n) = n + n³, o termo predominante é n³.
- Logo, a complexidade do algoritmo é O(n³).

**O que o Big O mede, na prática?**
Big O mede a taxa de crescimento do **número de etapas** à medida que o tamanho da entrada (n) aumenta. Aí existem diversos exemplos de Big O. Constante, linear, quadrático, cúbico, exponencial

---
Logo, a parte T(n) predominante na equação é a ordem de grandeza de um algoritmo. Estas são as classificações mais usadas para a ordem de grandeza na notação big O, ordenadas da melhor (mais eficiente) para a pior (menos eficiente):
- Tempo constante;
- Tempo logarítmico
- Tempo linear
- Tempo log-linear
- Tempo quadrático
- Tempo cúbico
- Tempo exponencial

Cada ordem de grandeza descreve a complexidade de tempo de um algoritmo. A **complexidade de tempo** é o número máximo de etapas que um algoritmo executa para ser concluído quando n aumenta.

## Tempo constante
É a ordem de grandeza mais eficiente, um algoritmo é executado em **tempo constante** quando requer o mesmo número de etapas independente do tamanho do problema. A notação big O para a complexidade constante é O(1).

Suponhamos que tivéssemos uma livraria online e todo dia desse um livro grátis para o nosso primeiro cliente. Nós armazenamos os nossos clientes em uma lista chamada **customers**, nosso algoritmo teria a seguinte aparência:
```python
free_books = customers[0]
```
A equação T(n) seria:
```math
T(n) = 1
```
O nosso algoritmo possuí uma única etapa, não importando quantos clientes tivermos. Se tivermos 1.000 clientes, o algoritmo executará apenas uma etapa. Se tiver 10.000 clientes, também executará uma única etapa...
Se representarmos graficamente a complexidade de tempo constante com o número de entradas no eixo x e o número de etapas no eixo y, o gráfico será plano:
![[Cientista da Computação Autodidata/Capítulo 1 - O que é um algoritmo?.png]]

## Tempo logarítmico
O tempo logarítmico é a segunda complexidade de tempo mais eficiente. Um algoritmo é executado em **tempo logarítmico** quando seu tempo de execução cresce de acordo com o logaritmo do tamanho da entrada. 
Vemos essa complexidade de tempo em algoritmos como o de busca binária que pode descartar muitos valores a cada iteração. O algoritmo logarítmico é expresso na notação big O como O(log n).

![[Cientista da Computação Autodidata/Capítulo 1 - O que é um algoritmo?-1.png]]
O número de etapas cresce mais lentamente em um algoritmo logarítmico quando o conjunto de dados aumenta.

## Tempo linear
Um algoritmo executado em **tempo linear** cresce com a mesma proporção do tamanho do problema. O algoritmo linear é expresso na notação big O como O(n).
Suponhamos que precise modificar o nosso programa de livros gratuitos para, em vez de dar um livro grátis para o primeiro cliente do dia, iterasse pela lista de clientes e desse a eles um livro grátis se seus nomes começassem com a letra B. Dessa vez, entretanto, a lista de clientes não está ordenada alfabeticamente. Agora, estamos forçado a percorrer cada item da lista para encontrar os nomes que começam com B. 
```python
free_book = False
customers = ["Lexi", "Britney", "Danny", "Bobbi", "Chris"]
for customer in customers:
if customer[0] == ´B´:
print(customer)
```
Quando a nossa lista tiver cinco itens, o programa levará cinco etapas para ser concluído. Para uma lista de 10 clientes, o nosso programa demandará 10 etapas; para 20 clientes, 20 etapas; e assim por diante...
Esta é a equação da complexidade de tempo desse programa:
```math
f(n) = 1 + 1 + n
```

Na notação big O, podemos ignorar as constantes e nos concentrarmos na parte predominante da equação:
```math
O(n) = n
```
Quando *n* cresce, o número de etapas que ele executa aumenta de acordo com o aumento de *n*.
![[Capítulo 1 - O que é um algoritmo?.png]]

## Tempo log-linear
Um algoritmo executado em **tempo log-linear** cresce de acordo com uma combinação (multiplicação) das complexidades de tempo logarítmica e linear. Por exemplo, um algoritmo log-linear pode calcular uma operação O(log n) n vezes. Na notação big O, o algoritmo log-lineares dividem um conjunto de dados em partes menores e processam cada parte independentemente. Muitos algoritmos de #ordenação mais eficientes que conheceremos, como a #classificação por #interpolação (merge sort), são log-lineares.

![[Capítulo 1 - O que é um algoritmo?-2.png]]
A complexidade log-linear não é tão eficiente quanto a de tempo linear. No entanto, a complexidade não cresce com a mesma rapidez que a quadrática.

## Tempo quadrático
Após o tempo log-linear, a próxima complexidade de tempo mais eficiente é a de tempo quadrático. Um algoritmo é executado em **tempo quadrático** quando seu desempenho é diretamente proporcional ao tamanho do problema ao quadrado. Na notação big O, podemos expressar um algoritmo quadrático como O(n^2)
```python
number = [1, 2, 3, 4, 5]
for i in numbers:
for j in numbers:
x = i * j
print(x)
```

Esse algoritmo multiplica cada número de uma lista de números por todos os outros números, armazena o resultado em uma variável e exibe-o.

Nesse caso, *n* é o tamanho da lista de **numbers**. A equação da complexidade de tempo desse algoritmo é a seguinte:
```math
f(n) = 1 + n * n * (1 + 1)

```
A parte (1 + 1) da equação vem da instrução de multiplicação e exibição. Estamos repetindo a instrução de multiplicação n * n vezes com os dois loops **for** aninhados. 
Podemos simplificar a equação:
```math
f(n) = 1 + (1 + 1) * n^2
```
que é o mesmo que a linha a seguir:
```math
f(n) = 1 + 2 * n²
```
Como podemos perceber, a parte n² da equação ofusca o restante, logo, na notação big O, a equação é:
```math
O(n) = n²
```
Quando representamos um algoritmo com complexidade quadrática em um gráfico, o número de etapas aumenta nitidamente à medida que o tamanho do problema aumenta.

![[Capítulo 1 - O que é um algoritmo?-3.png]]
Como regra geral, se o nosso algoritmo tiver dois loops aninhados indo de 1 a n (ou de 0 a n -1), sua complexidade de tempo será no mínimo O(n²). Muitos algoritmos de ordenação como o de ordenação por inserção e por bolha seguem o tempo quadrático.

## Tempo cúbico
Após a complexidade quadrática, vem a complexidade de tempo cúbico. Um algoritmo de tempo cúbico é executado em **tempo cúbico** quando seu desempenho é diretamente proporcional ao tamanho do problema ao cubo. Na notação big O, um algoritmo cúbico é expresso como O(n³). Um algoritmo com complexidade cúbica é semelhante ao quadrático, exceto por *n* ser elevado à terceira potência em vez de à segunda.

Exemplo de algoritmo com complexidade de tempo cúbico:
```python
number = [1, 2, 3, 4, 5]
for i in numbers:
for j in numbers:
for k in numbers:
x = i + j + h
print(x)
```

A equação desse algoritmo é a seguinte:
```math
f(n) = 1 + 2 * n³
```
Da mesma forma que em um algoritmo com complexidade quadrática, a parte mais crítica dessa equação é n³, que cresce tão rapidamente que torna o restante da equação, mesmo se incluísse n², irrelevante. Logo, na notação big O, a complexidade cúbica é expressa assim:
```math
O(n) = n³
```
Enquanto dois loops aninhados são sinal de complexidade de tempo quadrático, três loops aninhados indo de 0 a n significa que o algoritmo seguirá o tempo cúbico. Encontraremos a complexidade de tempo cúbico se o nosso trabalho envolver ciência de dados ou estatística.

As complexidades de tempo tanto quadrático quanto cúbico são casos especiais de uma família maior de complexidades de tempo polinomial. Um algoritmo executado em **tempo polinomial** aumenta de escala seguindo o padrão O(n^a), onde a = 2 para o tempo quadrático e a = 3 para o tempo cúbico. Ao projetar algoritmos, devemos evitar o aumento em escala polinomial, quando possível, por que os algoritmos podem ficar muito lentos à medida que *n* aumenta. Pode ser difícil evitar o aumento em escala #polinomial, mas talvez sirva de consolo o fato de que certamente a complexidade polinomial não é o pior dos casos.

## Tempo Exponencial
O prêmio de pior complexidade de tempo vai para a complexidade exponencial. Um algoritmo executado em **tempo exponencial** contém uma constante elevada ao tamanho do problema. Em outras palavras, um algoritmo com complexidade de tempo exponencial leva *c* elevado à enésima potência em etapas para ser concluído. A notação big O da complexidade exponencial é O(C^n), onde C é uma constante. O valor da constante não importa. O que importa é que n está no expoente.

Felizmente não encontraremos a complexidade exponencial com frequência. Um exemplo de complexidade exponencial envolvendo a tentativa de adivinhar uma senha numérica composta de *n* dígitos decimais com a verificação de cada combinação possível teria complexidade O(10^n).

No exemplo de adivinhar a senha, o algoritmo cresce rapidamente à medida que *n* aumenta. Quando *n* = 1, o algoritmo executa 10 etapas. Quando n = 2, 100 etapas. Quando n = 3, o algoritmo irá executar 1.000 etapas. Adivinhar uma senha com 8 dígitos decimais leva 100 milhões de etapas e adivinhar uma senha com 10 dígitos decimais leva mais de 10 bilhões de etapas. Quanto maior a senha, melhor. 

Essa solução para adivinhar uma senha é um exemplo de **algoritmo de força bruta**. Um algoritmo de força bruta testa todas as opções possíveis. Em geral, não é eficiente e deve ser o nosso último recurso.

![[Capítulo 1 - O que é um algoritmo-4.png]]

## Complexidade de melhor caso versus de pior caso
O desempenho de um algoritmo pode mudar de acordo com diferentes fatores, como o tipo de dados com o qual estamos trabalhando. Portanto, avaliar o desempenho de um algoritmo, é preciso considerar suas complexidades de melhor caso, pior caso e caso médio.

**Complexidade de melhor caso** de um algoritmo indica como é seu desempenho com entradas ideais;
**Complexidade de pior caso** é como ele se sai no pior cenário possível;
**Complexidade de caso médio** de um algoritmo representa como é seu desempenho na média.

Por exemplo, se tivermos que pesquisar item em uma lista, podemos ter a sorte de encontrar o que estamos procurando após verificar o primeiro item. Essa seria a complexidade de melhor caso. No entanto, se o item que estamos procurando não estiver na lista, será preciso pesquisar a lista inteira e teremos a complexidade de pior caso.

Se tivermos de pesquisar item a item em uma lista cem vezes, em média encontraremos o que estamos procurando no tempo O(n/2), que é o mesmo que o tempo O(n) na notação big O. 

## Complexidade de Espaço
Os computadores possuem recursos finitos como a memória, logo, além de pensar na complexidade de tempo de um algoritmo, devemos considerar o uso de seus recursos. A **complexidade de espaço** é a quantidade de espaço na memória que o algoritmo demanda e inclui o espaço fixo, o espaço de estruturas de dados e o espaço temporário. 

**Espaço fixo** é a quantidade de memória que o programa precisa para armazenar o conjunto de dados, por exemplo, o tamanho de uma lista que estivermos pesquisando. 

**Espaço temporário** é a quantidade de memória que o algoritmo precisa para o processamento intermediário, por exemplo, se o algoritmo precisar copiar temporariamente uma lista para transferir dados.

Como na complexidade de tempo, o nível aceitável de complexidade de espaço de um algoritmo depende da situação. No entanto, quanto menor é o espaço que o algoritmo demanda, melhor ele é.

## Por que isso é importante?
Como cientista da computação, precisamos conhecer as diferentes ordens de grandeza para otimizar nossos algoritmos. Quando estivermos tentando melhor um algoritmo, devemos nos concentrar em alterar sua ordem de grandeza em vez de melhorá-lo de outras maneiras. Por exemplo, suponhamos que tenhamos um algoritmo O(n^2) que use dois loops for, em vez de otimizar o que ocorre dentro dos loops, é muito mais importante determinar se podemos rescrever o algoritmo para não ter dois loops for aninhados, portanto uma ordem de grandeza menor. 

As decisões que tomarmos referentes aos algoritmos podem ter consequências importantes no mundo real. Por exemplo, suponhamos que estamos desenvolvendo um site responsável por escrever um algoritmo que responda à solicitação web de um cliente. Sua decisão de escrever um algoritmo constante ou quadrático poderá resultar na diferença entre o site ser carregado em menos de um segundo, deixando o nosso cliente satisfeito, e o carregamento levar mais de um minuto, o que poderá fazê-lo perder clientes antes de a solicitação ser carregada. 

