Ao trabalhar com banco de dados e SQL, a tarefa mais comum é <span style="background:#affad1">solicitar dados de uma ou mais tabelas e exibi-los</span>. A instrução SELECT faz isso. Mas o SELECT pode fazer muito mais do que simplesmente recuperar e exibir dados. <span style="background:#b1ffff">Como aprenderemos nos próximos capítulos</span>, podemos transformar esses dados de forma significativa e criar resumos poderosos a partir de milhões de registros. 

Mas primeiro, nós vamos aprender como SELECIONAR colunas de uma única tabela bem como compor expressões nelas.

## Retrieving Data with SQL
O painel SQL Editor é onde nós vamos escrever o nosso SQL e o painel Query Results é onde será exibido os resultados do nosso SQL.

Vamos escrever nossa primeira instrução SQL. A operação SQL mais comum é uma instrução #SELECT, que extrai dados de uma tabela e, em seguida, exibe os resultados.

Escrevendo a primeiro instrução:
```sql
SELECT * FROM CUSTOMER
```

Vamos detalhar exatamente o que aconteceu. Um comando #SELECT permite que você escolha quais colunas serão extraídas de uma tabela. Portanto, a primeira parte do SQL mostrado aqui deve ser lida como "Selecionar todas as colunas", em que * é o espaço reservado para especificar todas as colunas:

Quando executamos a declaração SELECT, ele retorna todas as colunas da tabela CUSTOMER e exibe elas para nós.
Não é necessário extrair todas as colunas em um comando SELECT. Podemos selecionar apenas as colunas de nosso interesse. A consulta a seguir extrairá apenas as colunas CUSTOMER_ID e NAME:

**Uma única instrução SQL pode, opcionalmente, terminar com um ponto e vírgula (;), conforme mostrado nos exemplos anteriores. No entanto, o ponto e vírgula é necessário para executar várias instruções SQL de uma só vez, o que é útil ao gravar dados, conforme abordaremos no capítulo 10.**

O fato de poder escolher colunas pode não parecer interessante no momento, mas nos permite aprimorar o que nos interessa. A redução do escopo para apenas determinadas colunas também ajudará nas tarefas de agregação GROUP BY.

## Expressões em declarações SELECT
O comando SELECT pode fazer muito mais <span style="background:#affad1">do que simplesmente selecionar colunas</span>. Nós <span style="background:#affad1">podemos também fazer cálculos em uma ou mais colunas</span> e incluí-los no resultado de consulta.
Vamos trabalhar com outra tabela chamada PRODUCT. Primeiro, faça um SELECT all para ver os dados da tabela product.
Suponhamos que desejamos gerar uma coluna calculada chamada TAXED_PRICE que seja 7% maior que price. Podemos usar a consulta #SELECT para calcular isso dinamicamente:

```SQL
SELECT PRODUCT_ID, DESCRIPTION, PRICE, PRICE * 1.07 AS TAXADED_PRICE FROM PRODUCT;
```

Observe na instrução SELECT que podemos distribuir nosso SQL em várias linhas para torná-lo mais legível. O software ignorará espaços em branco estranhos e linhas separadas, portanto, podemos usá-los para facilitar a leitura do nosso SQL.

Observe como a coluna TAXED_PRICE foi calculada dinamicamente na consulta SELECT. <span style="background:#affad1">Essa coluna não está armazenada na tabela</span>, mas é calculada e exibida para nós toda vez que executamos essa consulta. Esse é um recurso poderoso do SQL, que nos permite <span style="background:#affad1">manter os dados armazenados simples</span> e usar consultas para fazer cálculos em camada sobre eles.

Vamos dar uma olhada em nossa coluna TAXED_PRICE e detalhar como ela foi criada. Primeiro, vemos que o PRICE é multiplicado por 1.07 para calcular o valor tributado. Geramos esse valor TAXED_PRICE para cada registro:

Observe também que demos um nome a esse valor calculado usando uma instrução #AS (isso é conhecido como alias).

Podemos usar #aliases para dar nomes a expressões. Também podemos usar #aliases para <span style="background:#affad1">aplicar um novo nome a uma coluna existente na consulta</span>. Por exemplo, podemos usar o alias da coluna PRICE como UNTAXED_PRICE. Na verdade isso não altera o nome da coluna na tabela, mas dá a ela um <span style="background:#affad1">novo nome dentro do escopo de nossa</span> instrução SELECT:

```SQL
SELECT

PRODUCT_ID,

DESCRIPTION,

PRICE AS UNTAXED_PRICE,

PRICE * 1.07 AS TAXADED_PRICE

FROM PRODUCT;
```

**Ao dar nomes a qualquer coisa no SQL (seja um alias, um nome de coluna, um nome de tabela ou qualquer outra entidade), sempre use o sublinhado underline como espaço reservado para espaços.**

Se fôssemos distribuir os resultados dessa instrução SQL como um relatório para o nosso local de trabalho, provavelmente precisaríamos retocar o arredondamento do TAXED_PRICE. Talvez não seja desejável ter mais de duas casas decimais. Toda plataforma de banco de dados tem funções built-in. O SQLite fornece uma função <span style="background:#affad1">round()</span> que <span style="background:#affad1">aceita dois argumentos entre parênteses separados por uma vírgula</span>: o número a ser arredondado e o número de casas decimais a serem arredondadas. Para arredondar o TAXED_PRICE para duas casas decimais, podemos passar a expressão de multiplicação PRICE * 1.07 como primeiro argumento e um 2 como o segundo:
```sql
SELECT
PRODUCT_ID,
DESCRIPTION,
PRICE,
round(PRICE * 1.07, 2) AS TAXED_PRICE
FROM PRODUCT;
```

Aqui está um breve resumo dos operadores matemáticos que podemos usar no SQL (veremos esses operadores sendo usados ao longo do livro):

1. + Soma dois números -> STOCK + NEW_SHIPMENT;
2. - Subtrai dois números STOCK - DEFECTS;
3. * Multiplica dois números PRICE * 1.07
4. / Divide dois números STOCK / PALLET_SIZE;
5. % Divide dois números, mas retorna o resto STOCK % PALLET_SIZE;

## Text Concatenation
As expressões não precisam funcionar apenas com números. Podemos usar expressões com texto e outros tipos de dados. Um operador útil para usar com texto é a concatenação, que mescla duas ou mais parte de dados. O operador de concatenação é especificado por um pipe duplo ||, e você os valores de dados a serem concatenados em ambos os lados dele.
```SQL
SELECT NAME,

CITY || ',' || STATE AS LOCATION

FROM CUSTOMER;
```

A concatenação deve funcionar com qualquer tipo de dados (números, datas, etc.) e tratá-los como texto ao mesclar. O Campo ZIP mostrado aqui é um número, mas foi implicitamente convertido em texto durante a concatenação.

Muitas plataformas de banco de dados usam pipes duplos para concatenar, mas o MySQL e algumas outras exigem o uso de uma função CONCAT().

## Resumindo
Neste capítulo, nós vimos como usar a declaração #SELECT, a mais comum em operações SQL. Ele recupera e transforma dados de uma tabela sem afetar a própria tabela. Também aprendemos a selecionar colunas e a escrever expressões. Nas expressões, podemos usar operadores e funções para realizar tarefas como arredondamento, matemática e concatenação. No próximo capítulo, aprenderemos sobre a instrução #WHERE, que nos permitirá filtrar registros com base nos critérios que especificamos. 