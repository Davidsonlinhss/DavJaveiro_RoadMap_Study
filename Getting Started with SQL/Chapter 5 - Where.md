Vamos adicionar mais funcionalidades à instrução SELECT. Uma tarefa muito comum ao trabalhar com dados é filtrar registros com base em critérios, o que pode ser feito usando a instrução #WHERE .
Na maioria das vezes, expressões e funções podem ser usadas em qualquer parte de uma instrução SQL. 

### Filtering Records
Vamos abrir outro banco de dados chamado weather_Stations. 

Há cerca de 28.00 registros. Não vamos conseguir obter muitas informações interessantes percorrendo esses registros um por um. Precisamos transformar esses dados em algo significativo. Vamos utilizar a instrução #WHERE, que podemos usar para filtrar registros com base em um critério.

Nomes de tabelas e colunas podem ser definidas em caixa alta e caixa baixa. Os comandos SQL tais como SELECT, FROM e WHERE também podem ser em caixa alta ou caixabaixa. 

## Using WHERE on Numbers
Vamos dizer que estamos interessados em registros de station_data apenas para o ano de 2010. Usar um WHERE é bastante simples para um critério como este. 

Da mesma forma, podemos usar != ou <> para obter tudo menos 2010.

Também podemos qualificar intervalos inclusivos usando uma instrução BETWEEN:
```mysql
SELECT * FROM STATION_DATA WHERE YEAR >= 2005 AND YEAR <= 2010;
```

### Declarações AND, OR, and IN
Um BETWEEN pode, alternativamente, ser expressado usando maior ou igual a (>=) e menos ou igual a (<=) expressões e uma instrução AND.

Podemos usar a expressão IN para fornecer e tornar a lista válida de valores.
```mysql
SELECT * FROM STATION_DATA WHERE MONTH IN (3, 6, 7, 9, 12)
```

Ou, podemos selecionar tudo, exceto:
```mysql
SELECT * FROM STATION_DATA WHERE MONTH NOT IN (3, 6);
```

Também podemos utilizar expressões matemáticas em nossas instruções WHERE. Anteriormente, estávamos tentando filtrar os meses 3, 6, 9 e 12. Podemos também solicitar apenas meses divisíveis por 3 (os meses do "trimestre"), onde usamos o operador de módulo %. Com isso:
```mysql
SELECT * FROM STATION_DATA WHERE MONTH % 3 = 0;
```

Oracle não suporta o operador de módulo. Em vez disso, ele usa a função MOD(). 

### Using WHERE on Text
Cobrimos vários exemplos de como qualificar campos numéricos em instruções WHERE. As regras para qualificar campos de texto seguem a mesma estrutura, embora haja diferenças sutis. Podemos usar =, AND, OR e IN com texto. No entanto, ao usar texto, devemos colocar os literais (os valores de texto que especificamos) entre aspas simples. Por exemplo, se quisermos filtrar um report_code específico, podemos executar a consulta:
```mysql
SELECT * FROM STATION_DATA WHERE REPORT_CODE = '513A63'
```
Como report_code é texto (não um número), precisamos colocar aspas simples ao redor do valor para qualificá-lo. 

Esta regra das aspas simples se aplica a todas as operações de texto, incluindo esta operação IN:
```mysql
SELECT * FROM STATION_DATA WHERE REPORT_CODE IN ('1231', '1231'...);
```

Existem outras operações de texto úteis e funções que podemos usar nas instruções WHERE e SELECT. Por exemplo, a função #length() contará o número de caracteres para um determinado valor ou coluna. Então, se fôssemos designados ao controle de qualidade e precisássemos garantir que cada report_code tivesse seis caracteres de comprimento, gostaríamos de garantir que nenhum registro fosse retornado ao executar esta consulta:
```mysql
SELECT * FROM STATION_DATA WHERE LENTH(REPORT_CODE)!= 6;
```

Outra operação comum é usar curingas com uma expressão LIKE, onde % representa qualquer número de caracteres e _ representa qualquer caractere único. Portanto, para encontrar todos os códigos de relatório que começam com a letra "A", executaríamos a instrução:
```mysql
SELECT * FROM STATION_DATA WHERE REPORT_CODE KILE 'A%';
```

Se quisermos encontrar todos os códigos de relatório que têm um "B" como o primeiro caractere e um "C" como o terceiro caractere, podemos especificar um sublinhado (_ ) para a segunda posição e seguira com qualquer número de caracteres após o C:
```MYSQL
SELECT * FROM STATION_DB WHERE REPORT_CODE LIKE 'B_C%'
```

### Using WHERE on Booleans
Booleanos são valores verdadeiros/falsos. No mundo dos bancos de dados, tipicamente falso é expresso como 0 e verdadeiro é expresso como 1. Algumas plataformas de banco de dados (como MySQL) permitem que use implicitamente as palavras true e false para qualificar, como mostrado aqui:
```mysql
SELECT * FROM station_data WHERE tornado = TRUE and hail = true;
```

Se estivermos procurando apenas valores verdadeiros, não precisamos usar a expressão = 1. Como os campos já são booleanos (nos bastidores, toda condição WHERE se resume a uma expressão booleana), eles se qualificam por si mesmos. Portanto, podemos obter os mesmos resultados executando a seguinte consulta:
```MYSQL
SELECT * FROM STATION_DATA WHERE TORNADO AND HAIL;
```

No entanto, qualificar para condições falsas precisa ser explícito. Para obter todos os registros sem tornado, mas com granizo, executa esta consulta:
```mysql
SELECT * FROM STATION_DATA WHERE TORNADO = 0 AND HAIL = 1;
```

Também podemos utilizar a palavra-chaave NOT para qualificar tornado como falso:
```mysql
SELECT * FROM STATION_DATA WHERE NOT TORNADO AND HAIL;
```

### HANDLING NULL
Null não possui valor. É a completa ausência de qualquer conteúdo. É um estado de vácuo. Em termos leigos, é um espaço em branco. 

Valores nulos não podem ser determinados com um =. Precisamos usar as instruções IS NULL ou IS NOT NULL para identificar valores nulos. Então, para obter todos os registros sem snow_depth registrado, podemos executar a seguinte consulta:

```mysql
SELECT * FROM station_data WHERE snow_dept IS NULL;
```

Frequentemente, valores nulos não são desejáveis. A coluna station_number deve ser projetada para nunca permitir nulos, caso contrário, poderíamos ter dados órfãos que não pertencem a nenhuma estação. Pode fazer sentido ter valores nulos para snow_depth ou precipitation, não porque foi um dia ensolarado (nesse caso, é melhor registrar como 0 ou false), mas porque algumas estações podem não ter os instrumentos necessários para fazer essas medições. Pode ser enganoso definir esses valores como 0 (o que implica que os dados foram registrados), então essas medições devem ser deixadas como nulas.

Isso mostra que os valores nulos podem ser ambíguos e pode ser difícil determinar seu significado nos negócios. <span style="background:#d4b106">É importante que colunas nulas (colunas que permitem valores nulos) tenham documentado o que um valor nulo significa para o contexto de negócio. Caso contrário, valores devem ser proibidos nessas colunas da tabela</span>.

Não devemos confundir nulos com texto vazio, que são duas aspas simples com nada entre elas (ou seja, "). Isso também se aplica a texto com espaços em branco (ou seja, ' '). Estes serão tratados como valores e nunca serão considerados nulos. Um nulo definitivamente não é o mesmo que 0, porque 0 representa um valor, enquanto nulo é uma ausência de valor.

Os valores nulos podem ser muito irritantes de lidar com compor instruções #WHERE. Se precisarmos consultar todos os registros onde precipitation seja menor que 0,5, podemos escrever essa instrução:
```mysql
SELECT * FROM STATION_DATA WHERE PRECIPITATION <= 0.5;
```

Nulos raramente se qualificam para qualquer coisa e quase sempre são filtrados em uma cláusula WHERE, a menos que os manipule explicitamente. Então, precisamos usar um OR para incluir nulos:
```mysql
SELECT * FROM STATION_DATA WHERE PRECIPITATION IS NULL OR PRECIPITATION <= 0.5;
```

Uma maneira mais elegante de lidar com valores nulos é usar a função <span style="background:#d4b106">coalesce()</span>, #Coalesce(), que transformará um valor possivelmente nulo em um valor padrão especificado se ele for nulo. Caso contrário, deixará o valor como está. O primeiro argumento é o valor possivelmente nulo, e o segundo é o valor a ser usado se for nulo. Então:
```MySQL
SELECT * FROM STATION_DATA WHERE coalesce(precipitation, 0) <= 0.5,
```

Como qualquer função, coalesce() pode ser usada na instrução SELECT também, e não apenas na WHERE. Isso é útil se quisermos melhorar um relatório e não ter valores nulos exibidos, mas sim ter algum valor substituto por exemplo, 0, N/A ou None. 

```MYsql
SELECT REPORT_CODE, coalesce(precipitation, 0) as RAINFALL FROM STATION_DATA;
```

## Grouping Conditions
Quando encadeamos AND e OR juntos, é bom agrupá-los  deliberadamente. Precisamos garantir que organizamos cada conjunto de condições entre cada OR de uma maneira que agrupe condições relacionadas. Digamos que estamos procurando condições de granizo ou neve. Para que granizo ocorra, deve haver chuva e uma temperatura menor ou igual a 32 F. Podemos testar essa condição de granizo ou uma profundidade de neve maior que 0:
```mysql
SELECT * FROM STATION_DATA WHERE RAIN = 1 AND TEMPERATURE <= 32 OR SNOW_DEPTH > 0;
```
 No exemplo acima, é necessário especificarmos  explicitamente os parenteses para agrupar as condições:
```mysql
SELECT * FROM STATION_DATA WHERE RAIN = 1 AND (TEMPERATURE <= 32) OR SNOW_DEPTH > 0;
```

Aqui, agrupamos a expressão de granizo entre parênteses para que seja calculada como uma unidade única, e a temperatura não seja misturada com o operador OR e acidentalmente confundida com a profundidade da neve (snow_depth). Agrupos com parênteses nas instruções WHERE não apenas torna a semântica mais clara, mas também a execução mais segura. Isso é muito a ordem de operação (PEMDAS). Qualquer coisa entre parenteses é calculada primeiro.