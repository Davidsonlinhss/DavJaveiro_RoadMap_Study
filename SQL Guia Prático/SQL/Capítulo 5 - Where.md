Nos próximos capítulos, adicionaremos mais funcionalidades ao comando #SELECT. Uma tarefa muito comum ao trabalhar com dados é <span style="background:#d4b106">filtrar registros</span> com base em critérios, o que pode ser feito com um comando #WHERE. Aprenderemos mais funções e as usaremos na cláusula WHERE, mas também podemos usá-las em comandos SELECT, conforme discutido no capítulo anterior. Na maioria das vezes, as expressões e funções podem ser usadas em qualquer parte de um comando SQL.

## Filtrando registros
Ao executarmos o banco de dados STATION_DATA, há uma grande quantidade de dados, aproximadamente 28.000 registros. Não obteremos muitas informações interessantes percorrendo esses registros um a um. Precisaremos aprender mais alguns recursos SQL para <span style="background:#d4b106">transformar esses dados</span> em algo significativo. Começaremos aprendendo a instrução WHERE, que pode ser usada para filtrar registros com base em um critério.

Os nomes de tabelas e colunas podem ser definidos em letras maiúsculas ou minúsculas. Os comandos SQL, como SELECT, FROM  e WHERE, podem ser definidos em letras maiúsculas ou minúsculas. 

## Uso do WHERE em números
Digamos que estejamos interessados em registros de station_data apenas para o ano de 2010. Usar um WHERE é bastante simples para um critério simples como esse. Com essa consulta, nós devemos obter registros em que o campo year seja igual a 2010:

```sql
SELECT * from STATION_DATA
WHERE year = 2010
```
Por outro lado, podemos usar != ou <> para obter tudo, exceto 2010. Por exemplo:

```sql
SELECT * from STATION_DATA
WHERE year != 2010
```
ou
```SQL
SELECT * from STATION_DATA
WHERE year <> 2010
```

Essas duas sintaxes fazem a mesma coisa. O SQLite e a maioria das plataformas agora suportam ambas, mas o <span style="background:#d4b106">Microsoft Acess</span> e o <span style="background:#d4b106">IBM DB2</span> suportam apenas <>. 

Também podemos qualificar intervalos exclusivos usando uma instrução #BETWEEN:
```SQL
SELECT * from STATION_DATA
WHERE year BETWEEN 2005 and 2010
```

## Declarações AND, OR e IN
Um BETWEEN pode ser expresso alternativamente usando expressões maiores ou iguais a e menores ou iguais a e uma instrução AND. É um pouco mais prolixo, mas demonstra que podemos usar duas condições com um AND. Nesse caso, o ano deve ser maior ou igual a 2005 e menor ou igual a 2010:

```sql
SELECT * from station_data
WHERE year >= 2005 AND year <= 2010
```

Se quiséssemos exclusivamente tudo entre 2005 e 2010, ou seja, sem incluir esses dois anos, simplesmente eliminaríamos os caracteres =. Assim, apenas 2006, 2009 se qualificariam:

```sql
SELECT * from station_data
WHERE year > 2005 AND year < 2010
```

Também temos a opção de usar OR. Em uma instrução OR, <span style="background:#d4b106">pelo menos um dos critérios deve ser verdadeiro para que o registro seja qualificado</span>. Se quiséssemos apenas registros com os meses 3, 6, 9 ou 12. 
```SQL
SELECT * from station_data
WHERE MONTH = 3
or MONTH = 6
or MONTH = 9
or MONTH = 12
```

Isso é um pouco verboso. Uma maneira mais eficiente de fazer isso é usar uma <span style="background:#d4b106">instrução IN</span> para fornecer uma lista válida de valores:
```js
SELECT * from station_data
WHERE MONTH IN (3, 6, 9, 12)
```

Se quiséssemos tudo, exceto 3, 6, 9 e 12, poderíamos usar <span style="background:#d4b106">NOT IN</span>:
```sql
SELECT * from station_data
WHERE MONTH NOT IN (3, 6, 9, 12)
```

Podemos usar outras expressões matemáticas em nossas instruções WHERE. Anteriormente, estávamos tentando filtrar os meses 3, 6, 9 e 12. Se quiséssemos apenas os meses divisíveis por 3 (os meses do "trimestre"), podemos usar o operador de módulo (%). O módulo é semelhante ao operador de divisão (/), mas retorna o resto em vez do quociente. Um resto de 0 significa que não há resto algum, portanto, podemos aproveitar essa lógica procurando um resto de 0 com módulo 3. 

O #ORACLE não é compatível com o operador módulo. Em vez disso, usa-se a função MOD().

## Usando WHERE em Text
Já abordamos vários exemplos de como qualificar campos numéricos em instruções WHERE. As regras para qualificar campos de texto seguem a mesma estrutura, embora haja diferenças sutis. Podemos usar as instruções =, AND, OR e IN com texto. <span style="background:#d4b106">Entretanto, ao usar texto, você deve envolver os literais (ou valores de texto que especificar) entre aspas simples.</span> Por exemplo, se quisermos filtrar por um exemplo um report_code específico, podemos executar esta consulta:
```sql
SELECT * FROM station_data
WHERE report_code = '513A63'
```

Observe que, como o campo report_code é um texto (não um número), precisamos colocar aspas simples em torno de '513A63' para qualificá-lo. Se não fizermos isso, o software SQL ficará confuso e pensará que 513A63 é uma coluna em vez de um valor de texto. Isso causará um erro e a consulta falhará.

Essa regra de aspas simples se aplica a todas as operações de texto, incluindo essa operação IN:
```sql
SELECT * FROM station_data
WHERE report_code IN ('513A63','1F8A7B','EF616A')
```

Há outras operações e funções de texto úteis que podemos usar nas instruções WHERE e SELECT. Por exemplo, <span style="background:#d4b106">a função length()</span> contará o número de caracteres de um determinado valor. Portanto, se tivéssemos um controle de qualidade e precisássemos garantir que cada report_code tivesse seis caracteres, seria bom garantir que nenhum registro retornasse ao executar essa consulta:

```sql
SELECT * FROM station_data
WHERE length(report_code) != 6
```

Outra operação comum é usar curingas com uma expressão LIKE, em que % é qualquer número de caracteres e é qualquer caractere único. Qualquer outro caractere é interpretado literalmente. Portanto, se quisermos encontrar todos os códigos de relatório que começam com a letra "A", executaria essa instrução para encontrar "A" seguido de qualquer caractere:

```sql
SELECT * FROM station_data
WHERE report_code LIKE 'A%'
```

Se quisermos encontrar todos os códigos de relatório que têm um "B" como primeiro caractere e um "C" como terceiro caractere, especificaria um sublinhado ( _ ) para a segunda posição e seguiria com qualquer número de caracteres após o "C":

```sql
SELECT * FROM station_data
WHERE report_code LIKE 'B_C%'
```

Não confunda o fato de o % ser usado para duas finalidades diferentes. Anteriormente, nós o usamos para executar uma operação de módulo, mas em uma instrução LIKE ele é um curinga em um padrão de texto. Como alguns outros símbolos e caracteres no SQL, o contexto em que ele é usado define sua funcionalidade. 

Há muitas outras funções de texto úteis, como INSTR, SUBST e REPLACE. Por uma questão de brevidade, deixaremos de abordar as funções de texto aqui, mas podemos consultar elas no apêndice. 

As funções de texto, como LIKE, SUBSTR e INSTR, podem começar a se tornar tediosas e prolixas ao qualificar padrões de texto complexos. Recomendo enfaticamente a pesquisa de expressões regulares quando nos depararmos com essa situação. Elas não são material para iniciantes, mas são úteis quando atingirmos essa necessidade intermediária. 

## Utilizando WHERE em Booleanos
Booleanos são valores verdadeiro/falso. No mundo dos bancos de dados, normalmente false é expresso como 0 e true é expresso como 1. Algumas plataformas de banco de dados (como o MySQL) permitem que usemos implicitamente as palavras true e false para se qualificar, como mostrado aqui:
```sql
SELECT * FROM station_data
WHERE tornado = true and hail = true;
```

o SQLite não oferece suporte a isso. Ele espera que usemos explicitamente 1 para verdadeiro e 0 para falso. O nosso código ficaria assim:
```sqç
SELECT * FROM station_data
WHERE tornado = 1 AND hail = 1
```

Se estivermos procurando apenas valores verdadeiros, nem precisará usar a expressão = 1. Como os campos já são booleanos (nos bastidores, cada condição WHERE se resume a uma expressão booleana), eles se qualificam inerentemente por si mesmos. Portanto, podemos obter os mesmo resultados executando a seguinte consulta:
```sql
SELECT * FROM station_data
WHERE tornado AND hail;
```

No entanto, <span style="background:#d4b106">a qualificação para condições falsas precisa ser explícita</span>. Para obter todos os registros sem tornado, mas com granizo, execute esta consulta:
```SQL
SELECT * FROM station_data
WHERE tornado = 0 AND hail = 1;
```

Podemos usar a <span style="background:#d4b106">palavra-chave NOT</span> para qualificar o tornado como falso:
```sql
SELECT * FROM station_data
WHERE NOT tornado AND hail
```

## Ação NULL
Colunas com valores nulos não é um valor que não tem valor. É a ausência completa de qualquer conteúdo. É um estado vazio. Em termos leigos, está em branco. 

Os valores nulos não podem ser determinados com um =. Precisamos usar as intruções IS NULL ou IS NOT NULL para identificar os valores nulos. Portanto, para obter todos os registros sem snow_depth registrado, podemos executar a seguinte instrução:
```sql
SELECT * FROM station_data
WHERE snow_depth IS NULL
```

Em geral, não é desejável ter valores nulos. A coluna station_number deve ser projetada de modo a nunca permitir valores nulos, caso contrário, poderemos ter dados órfãos que não pertencem a nenhuma estação. No entanto, pode fazer sentido ter valores nulos para snow_depth ou precipitação, não porque o dia estava ensolarado, mas porque algumas estações podem não ter os instrumentos necessários para fazer essas medições. Pode ser enganoso definir esses valores como 0 (o que implica que os dados foram registrados), portanto, essas medições deve ser deixadas nulas.

Isso mostra que os valores nulos podem ser ambíguos e pode ser difícil determinar seu significado comercial. É importante que as colunas anuláveis (colunas que podem ter valores nulos) <span style="background:#d4b106">tenham documentado o que significa um valor nulo do ponto de vista comercial</span>. Caso contrário, os nulos devem ser banidos dessas colunas da tabela. 

Não confunda nulos com textos vazio, que são duas aspas simples sem nada dentro (ou seja, ''). Isso também se aplica ao texto com espaço em branco (ou seja, ' '). Eles serão tratados como valores e nunca serão considerados nulos. Definitivamente, um nulo também não é o mesmo que 0, porque 0 é um valor, enquanto nulo é a ausência de um valor. 

Pode ser muito incômodo lidar com nulos ao compor instruções WHERE. Se quisessemos consultar todos os registros em que a precipitação é menor do que 0.5, podemos escrever a instrução:

```sql
SELECT * FROM station_data
WHERE precipitation <= 0.5
```

Mas e se quisermos considerar os valores nulos? Como nulo não é 0 ou qualquer número, ele não se qualificará. Os nulos raramento se qualificam com alguma coisa e quase sempre são filtrados em um WHERE, a menos que tratemos deles explicitamente. Portanto, precisamos usar um OR para incluir os nulos:
```sql
SELECT * FROM station_data
WHERE precipitation IS NULL OR precipitation <= 0.5
```

Uma maneira mais elegante de lidar com valores nulos é usar a função coalesce(), que transformará um valor possivelmente nulo em um valor padrão especificado se ele for nulo. Caso contrário, ela deixará o valor como está. O primeiro argumento é o valor possivelmente nulo e o segundo é o valor a ser usado se ele for nulo. Portanto, se quiséssemos que todos os nulos fossem tratados como 0 em nossa condição, poderíamos combinar() o campo de precipitação para converter nulo em 0:

```sql
SELECT * FROM station_data
WHERE coalesce(precipitation, 0) <= 0.5;
```

Como qualquer função, a coalesce( ) também pode ser usado no comando SELECT, e não apenas no WHERE. Isso é útil se quisermos embelezar um relatório e não exibir valores nulos, mas sim algum espaço reservado , por exemplo, O, "N/A" ou "None", que seja mais significativo para a maioria das pessoas:

```sql 
SELECT report_code, coalesce(precipitation, 0) as rainfall
FROM station_data;
```

## Condições grupos
Quando começamos a encadear E e OU, é bom agrupá-los deliberadamente. Precisamos ter certeza de que organizou cada conjunto de condições entre cada OU de forma a agrupar as condições relacionadas. Digamos que estejamos procurando condições de granizo ou neve. Para que ocorra granizo, deve haver chuva e uma temperatura menor ou igual a 32 graus.

```sql
SELECT * FROM station_data
WHERE rain = 1 AND temperature <= 32
OR snow_depth > 0;
```

Mas há um possível problema aqui. Embora isso funcione tecnicamente, há um grau de ambiguidade que, por sorte, o SQLite interpretou corretamente. O motivo é a questão pouco clara de "quais condições pertencem ao AND e quais condições pertencem ao OR?" O interpretador SQL poderia descarrilar rapidamente e interpretar incorretamente que estamos procurando por chuva E outra condição em que a temperatura está abaixo de 32 OU  a profundidade de neve é maior que 0. Ou seja, a sequência não está clara.

```sql
SELECT * FROM station_data WHERE (rain = 1 AND temperatura <= 32) OR snow_depth > 0
```

Aqui, agrupamos a expressão sleet entre parênteses para que ela seja <span style="background:#d4b106">calculada como uma única unidade</span>, e a temperatura não seja confundida com o operador OR e acidentalmente misturada com snow_depth. O agrupamento com parênteses nas instruções WHERE não apenas torna a semântica mais clara, mas também a execução mais segura. Isso é muito parecido com a ordem das operações (PEMDAS). 