Ao trabalhar com bancos de dados e SQL, a tarefa mais comum é solicitar dados de uma ou mais tabelas e exibi-los. A instrução #SELECT realiza isso. Mas o #SELECT pode fazer muito mais do que simplesmente recuperar e exibir dados. Podemos transformar os dados de maneiras significativas e construir resumos poderosos a partir de milhões de registros.

Vamos aprender a como selecionar colunas de uma única tabela, bem como compor expressões nelas.

## Retrieving Data With SQL
A operação mais comum é a instrução SELECT, que extrai dados de uma tabela e, em seguida, exibe os resultados. 

Uma instrução SELECT permite que escolhamos quais colunas resgatar de uma tabela. Então, a primeira parte do SQL mostrado aqui deve ser lida como "Selecionar todas as colunas", onde * <span style="background:#d4b106">é um espaço reservado para especificar todas as colunas</span>.

Podemos resgatar apenas as colunas a quais estamos interessado. A consulta a seguir puxará apenas as colunas CUSTOMER_ID e NAME:
```mysql
SELECT CUSTOMER_ID, NAME FROM CUSTOMER;
```

Uma única instrução SQL pode, opcionalmente, terminar com um ponto e vírgula (;). Já o ponto e vírgula é necessário para executar várias instruções SQL de uma vez, o que é útil ao escrever dados, conforme será visto no capítulo 10. 

Poder escolher colunas pode não parecer interessante no momento, mas nos permite focar no que estamos interessado. Reduzir o escopo para apenas certas colunas também ajudará em tarefas de agregação com GROUP BY, como veremos no capítulo 6.

## Expressions in SELECT Statements
A instrução SELECT pode fazer muito mais do que simplesmente selecionar colunas. Vamos trabalhar com outra tabela chamada PRODUCT. Vamos fazer um SELECT ALL para ver os dados.

Suponhamos que quiséssemos gerar uma coluna chamada TAXED_PRICE que seja 7% maior que PRICE. Poderíamos usar uma consulta SELECT para calcular isso dinamicamente para nós:
```mysql
SELECT PRODUCT_ID, DESCRIPTION, PRICE, PRICE * 1.07* AS TAXED_PRICE FROM PRODUCT;
```

Na instrução SELECT podemos distribuir nosso SQL em várias linhas para torná-lo mais legível. O software ignorará espaços em branco extras e linhas separadas, para que possamos usá-lo para tornar nosso SQL mais fácil de ler.

A coluna TAXED_PRICE foi calculada dinamicamente na consulta SELECT. Esta coluna não está armazenada na tabela, mas é calculada e exibida para nós toda vez que executarmos esta consulta. Esta é uma característica poderosa do SQL, que nos permite manter os dados armazenados simples e usar consultas para adicionar camadas de cálculos sobre eles.

Podemos usar aliases para dar nomes a expressões. Também podemos usar aliases para aplicar um novo nome a uma coluna existente dentro da consulta. Por exemplo, podemos atribuir um alias para a coluna PRICE como UNTAXED_PRICE. Isso não muda realmente o nome da coluna na tabela, mas dá a ela um novo nome dentro do escopo da nossa instrução SELECT.

Podemos arredondar os valores. SQLite fornece a função round() que aceita dois argumentos entre parênteses, separados por vírgula: o número a ser arredondado e o número de casas decimais para arredondar. Para arredondar o TAXED_PRICE para duas casas decimais, podemos passar a expressão de multiplicação PRICE * 1.07* como o primeiro argumento e um 2 como o segundo. 

### Text Concatenation
As expressões não precisam funcionar apenas com números. Também podemos usar expressões com texto e outros tipos de dados. Um operador útil para usar com texto é a concatenação, que mescla dois ou mais pedaços de dados juntos. O operador de concatenação é especificado por um duplo pipe (||), e colocamos os valores de dados a serem concatenados em ambos os lados deles.

Podemos concatenar CITY e STATE da tabela

Podemos usar o concat
```mysql
SELECT NAME, CONCAT(CITY, ', ', STATE) AS LOCATION FROM CUSTOMER;
```

