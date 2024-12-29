A agregação de dados (também chamada de rolagem, resumo ou agrupamento de dados) é a criação de <span style="background:#affad1">algum tipo de total</span> a partir de um número de registros.  Soma, mínimo, máximo, contagem e média são operações de agregação comuns. No SQL, é possível agrupar esses totais em qualquer coluna especificada, o que permite controlar facilmente o escopo dessas agregações. 

## Agrupamento de registros
Primeiro, execute a agregação mais simples: conte o número de registros em uma tabela. Abra o editor SQL e obtenha uma contagem de registros para os dados da estação:
```sql
SELECT COUNT(*) AS record_count FROM station_data
```

O COUNT( * ) significa contar os registros. Também podemos usá-lo em combinação com outras operações SQL, como WHERE. Para contar o número de registros em que um tornado estava presente, digite o seguinte:
```sql
SELECT COUNT(*) AS record_count from station_data
WHERE tornado = 1
```

Identificamos 3.000 registros com a presença de tornados. Mas e se quiséssemos separar a contagem por ano? Também podemos fazer isso com essa consulta:
```sql
SELECT year, COUNT(*) AS record_count FROM station_data
WHERE tornado = 1
GROUP BY year
```

De repente, esses dados se tornam mais significativos. Agora vemos a contagem de avistamentos de tornado por ano. Vamos detalhar essa consulta para ver como isso aconteceu.

Primeiro, selecionamos o ano, depois selecionamos o COUNT( * ) dos registros e filtramos apenas os registros em que tornado é verdadeiro:
```sql
SELECT year, COUNT(*) AS record_count FROM station_data
WHERE tornado = 1
GROUP by year
```

No entanto, também especificamos que estamos agrupando por ano. Isso é o que efetivamente nos permite contar o número de registros por ano. <span style="background:#ff4d4f">A última linha, destaca em negrito, realiza esse agrupamento</span>.

Podemos fatiar esses dados em mais de um campo. Se quiséssemos uma contagem por ano e mês, poderíamos agrupar no campo mês também.
```sql
SELECT year, month, COUNT(*) AS counted_record FROM station_data
WHERE tornado = 1
GROUP by year, month
```

Como alternativa, podemos usar posições ordinais em vez de especificar as colunas no GROUP BY. As posições ordinais correspondem à posição numérica de cada item no comando SELECT. Portanto, em vez de escrever GROUP BY ano, mês, poderíamos fazer GROUP BY 1,2 (o que é especialmente útil se nosso SELECT tiver nomes de colunas ou expressões longas e não quisermos reescrevê-las no GROUP BY):
```sql
SELECT month, year, COUNT(*) AS record_count FROM station_data
WHERE tornado = 1
GROUP BY 1, 2
```

Observe que nem todas as plataformas aceitam posições ordinais. No Oracle e no SQL Server, por exemplo, teremos de reescrever todo o nome da coluna ou a expressão no GROUP BY.

## Pedido de Registros
Observe que a coluna mês não está em uma ordenação natural como seria de se esperar. Esse é um bom momento para mencionar o operador ORDER BY, que podemos colocar ao final de uma instrução SQL (depois de qualquer WHERE ou GROUP BY). Se quisermos ordenar por ano e depois por mês, basta adicionar este comando:
```sql
SELECT year, month, COUNT(*) AS record_count FROM station_data
WHERE tornado = 1
GROUP BY 1, 2
ORDER BY 1, 2
```

Por padrão, a classificação é feita com o operador ASC, que ordena os dados em ordem crescente. Se, em vez disso, quiser classificar em ordem decrescente, aplique o operador DESC à ordenação do ano para fazer com que os registros mais recentes apareçam na parte superior dos resultados:
```sql
SELECT year, month, ORDER(*) AS record_count FROM station_data
WHERE tornado = 1
GROUP BY 1, 2
ORDER BY 1 DESC, 2
```

## Funções agregadas
Já usamos a função COUNT( * ) para contar registros. Mas há outras funções de agregação, incluindo SUM(), MIN() e AVG(). Podemos usar funções de agregação em uma coluna específica para realizar algum tipo de cálculo nela.

Mas, primeiro, vamos dar uma olhada em outra maneira de usar o COUNT(). A função COUNT() pode ser usada para uma finalidade diferente da simples contagem de registros. <span style="background:#d4b106">Se especificarmos a coluna em vez de um asterisco</span>, ela contará o número de valores não nulos nessa coluna. Por exemplo, podemos fazer uma contagem dos registros snow_depth, que contará o número de valores não nulos.
```sql
SELECT COUNT(snow_depth) as recorded_snow_depeth_count
FROM station_data
```

Fazer uma contagem de valores não nulos em uma coluna pode ser útil, portanto, observe que COUNT() também pode cumprir essa finalidade quando aplicado a uma coluna. 

As funções de agregação, como COUNT(), SUM(), AVG(), MIN() e MAX(), nunca incluirão valores nulos em seus cálculos. Somente os valores não nulos serão considerados.

Vamos passar outras tarefas de agregação. Se quisermos encontrar a temperatura média de cada mês desde 2000, podemos filtrar os anos de 2000 e posteriores, agrupar por mês e fazer uma média em temp:
```sql
SELECT month, AVG(temperature) AS avg_temp
FROM station_data
WHERE year >= 2000
GROUP BY month
```
Como sempre, podemos usar funções nos valores agregados e executar tarefas como o arredondamento para torná-los mais bonitos:
```sql
SELECT month, round(AVG(temperatura),2) as avg_temp
FROM station_data
WHERE year >= 2000
GROUP BY month
```

SUM() é outra operação de agregação comum. Para encontrar a soma da profundidade da neve por ano desde 2000, execute esta consulta:
```sql
SELECT year, SUM(snow_depth) as total_snow
FROM station_data
WHERE year >= 2000
GROUP BY year
```

Não há limitação de quantas operações de agregação podemos usar em uma única consulta. Aqui encontramos o total_snow e o total_precipitation para cada ano desde 2000 em uma única consulta, bem como o max_precipitation:
```sql
SELECT year,
SUM(snow_depth) as total_snow,
SUM(precipitation) as total_precipitation,
MAX(precipitation) as max_precipitation
FROM station_data
WHERE year >= 2000
GROUP BY year
```

Talvez ainda não seja evidente, mas podemos obter algumas agregações muito específicas aproveitando o WHERE. Se quisesse obter o total de precipitação por ano somente quando houvesse um tornado, basteria filtrar se o tornado fosse verdadeiro. Isso incluirá apenas a precipitação relacionada a tornados nos totais:
```sql
SELECT year,
SUM(precipitation) as tornado_precipitation
FROM station_data
WHERE tornado = 1
GROUP BY year
```

## A declaração HAVING
Suponha que queiramos filtrar registros com base em um valor agregado. Embora seu primeiro instinto possa ser usar uma instrução WHERE, isso na verdade não funcionará porque o <span style="background:#affad1">WHERE filtra registros e não filtra agregações</span>. Por exemplo, se tentarmos usar o WHERE para filtrar os resultados em que total_precipitation for maior que 30, haverá um erro:
```sql
SELECT year,
SUM(precipitation) as total_precipitation
FROM station_data
WHERE total_precipitation > 30
GROUP BY year
```

Por que isso não funciona? Você não pode filtrar em campos agregados usando WHERE. É necessário usar a palavra-chave HAVING para fazer isso. A maneira como a agregação funciona é que o software processa registro por registro, descobrindo quais deseja manter com base na condição WHERE. Depois disso, ele reduz os registros com base no GROUP BY e executa quaisquer funções de agregação, como SUM(). Se quiséssemos filtrar o valor SUM(), precisaríamos que o filtro ocorresse depois que ele fosse calculado. É nesse ponto que o HAVING pode ser aplicado. 
```sql
SELECT year,
SUM(precipitation) as total_precipitation
FROM station_data
GROUP BY year
HAVING total_precipitation > 30
```

HAVING é o equivalente agregado de WHERE. A palavra-chave WHERE filtra registros individuais, mas #HAVING <span style="background:#d4b106">filtra agrupamentos</span>. Observe que algumas plataformas, inclusive a Oracle, não oferecem suporte a aliases na instrução HAVING (assim como o GROUP By). Isso significa que devemos especificar a função de agregação novamente na instrução HAVING. Se estiver executando a consulta anterior em um banco de dados Oracle, teria de escrever assim:
```sql
SELECT year,
SUM(precipitation) as total_precipitation
FROM station_data
GROUP BY year
HAVING SUM(precipitation) > 30
```

## Obtendo registros distintos
Não é incomum querer um conjunto de resultados distintos de uma consulta. Sabemos que há 28.000 registros em nossa tabela station_data. Mas suponhamos que desejemos obter uma lista distinta dos valores de station_number. Se executarmos essa consulta, obteremos duplicatas:
```sql
SELECT station_number FROM station_data
```

Se quisermos uma lista distinta de números de estações sem duplicadas, podemos usar a palavra-chave DISTINCT:
```sql
SELECT DISTINCT station_number FROM station_data
```

Podemos também obter resultados distintos para mais de uma coluna. Precisamos de conjuntos distintos de station_number e year, basta incluir essas duas colunas no comando SELECT:
```sql
SELECT DISTINCT station_number, year FROM station_data
```

## Resumo
