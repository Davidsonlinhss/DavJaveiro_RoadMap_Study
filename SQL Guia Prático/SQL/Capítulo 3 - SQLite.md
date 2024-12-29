## What Is SQLite?
Conforme discutido no capítulo anterior, há muitos lugares para colocar dados. Mas, muitas vezes, queremos um local rápido e fácil para colocar dados sem todo o incômodo de uma configuração cliente-servidor. Queremos armazenar dados em um arquivo simples e editá-lo com a mesma facilidade de um documento do Word. Essa é a situação ideal para usar o SQLite.

O SQLite é o banco de dados mais amplamente distribuído no mundo. Ele é colocado em iPhones, iPads, dispositivos Android, telefones Windows, termostatos, consoles de automóveis, satélites e muitos outros dispositivos modernos que precisam armazenar e recuperar dados facilmente. É muito usado no sistema operacional Windows 10, bem como na aeronave Aribus A350 XWB. <span style="background:#affad1">Ele se destaca quando é necessário simplicidade e baixa sobrecarga</span>. Também é excelente para criar protótipos de banco de dados comerciais.

Mas toda tecnologia tem uma desvantagem. Por não ter um servidor que gerencia o acesso a ele, ele falha em ambientes multiusuários em que várias pessoas podem editar simultaneamente o arquivo SQLite. Ainda assim, para nossos objetivos de treinamento, o SQLite é perfeito.

## SQLiteStudio
Há muitos editores SQL que podemos usar para trabalhar com um banco de dados SQLite. Recomendo enfaticamente o uso de SQLiteStudio, pois ele é intuitivo e facilita a exploração e o gerenciamento de um banco de dados. Usaremos esse aplicativo neste livro. 

Observe que o SQLiteStudio é um programa independente, de terceiros, não associado ao SQLite ou a seus desenvolvedores. O SQLite é um mecanismo de banco de dados criado por Richard Hipp e uma equipe talentosa de programadores. 

## Importando e navegando entre banco de dados

Ao iniciar o SQLiteStudio pela primeira vez, provavelmente veremos um painel sem conteúdo. O painel é o navegador de banco de dados e a área cinza á direita é a área de trabalho SQL, onde escreveremos o SQL nos bancos de dados.
![[Capítulo 3 - SQLite.png]]

Vamos colocar alguns bancos de dados no SQLiteStudio. Alguns exemplos de bancos de dados SQLite usados neste livro são fornecidos em http://bit.ly/1TLw1Gr.

Faça o download dos bancos de dados clicando no botão Download ZIP e copie o conteúdo para uma pasta de sua escolha. Você provavelmente desejará dedicar essa pasta a todos os bancos de dados com os quais trabalhará neste livro.

Depois de importar o banco de dados rexon_metals, clique duas vezes nele para ver seu conteúdo, que inclui três tabelas e duas exibições. 

Observe que você pode clicar nas setas para obter informações mais detalhadas sobre diferentes objetos do banco de dados, como tabelas (Figura 3-7). Por exemplo, clicar na seta da tabela CUSTOMER pode revelar informações como as colunas que ela contém.

O que são as views? Não nos preocuparemos agora, basicamente, são <span style="background:#ff4d4f">consultas</span> SQL pré-contruídas <span style="background:#ff4d4f">que são usadas com tanta frequência que são convenientemente armazenadas no banco de dados</span>. 

Se dermos dois cliques na própria tabela CUSTOMER, uma nova janela aparecerá na área de trabalho com todos os tipos de informações sobre a tabela. Inicialmente, ela é aberta na guia Structure (Estrutura), que fornece informações detalhadas sobre cada coluna. No momento, o único detalhe com o qual precisaremos nos preocupar é o tipo de dados de cada coluna. 

Os campos CUSTOMER_ID e ZIP são armazenados como <span style="background:#affad1">INTEGER</span>, que é o tipo de dados para um <span style="background:#affad1">número inteiro</span> (não decimal). Isso significa que esses campos devem conter apenas valores INTEGER. O restante das colunas é armazenado como TEXT. Há outros tipos de dados que poderiam ser usados, como DATETIME, BOOLEAN (verdadeiro/falso) e DECIMAL, que não são usados nessa tabela específica. 

Por enquanto, se você entende o conceito de tipos de dados, isso é tudo o que precisa para observar na guia Structure. Exploraremos o design da tabela em detalhes quando criarmos nossas próprias tabelas mais tarde.

Clique na guia data e veremos os dados na própria tabela. Há apenas cinco registros (ou linhas) nessa tabela, mas o SQLite poderia conter milhões se fosse necessário. Você também pode editar convenientemente os valores nessa tabela (sem usar o SQL) simplesmente clicando duas vezes e editando uma célula e, em seguida, clicando na marca de seleção verde para salvá-la.

Dedique algum tempo para se familiarizar com o SQLiteStudio. Assim que estiver satisfeito por ter mexido o suficiente, feche todas as janelas na área de trabalho. Em seguida, no menu superior, navegue até Tools- open SQL editor. Embora o SQLiteStudio fornece muitas maneiras de visualizar e manipular dados sem usar qualquer SQL, ele não chega perto da flexibilidade e do poder que o SQL oferece.

Agora que conhecemos nossas tabelas e com o que estamos trabalhando, escrever SQL será um pouco mais intuitivo. É difícil consultar banco de dados sem antes conhecer as tabelas neles contidas.
