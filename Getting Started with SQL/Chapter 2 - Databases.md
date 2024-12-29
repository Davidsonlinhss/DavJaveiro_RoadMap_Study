## What is a Database?
Na definição mais ampla, um banco de dados é qualquer coisa que colete e organize dados. Uma planilha com reservas de clientes é um banco de dados, assim como um arquivo de texto simples contendo dados de horários de voo. Os próprios dados de texto simples podem ser armazenados em vários formatos, incluindo XML e CSV.

No entanto, profissionalmente, quando alguém se refere a um "banco de dados", provavelmente está se referindo a um sistema de gerenciamento de banco de dados relacional ( #RDBMS). Esse termo pode parecer técnico e intimidante, mas um RDBMS é simplesmente um tipo de banco de dados que contém uma ou mais tabelas que podem ter relacionamentos entre si.

## Exploring Relational Databases
Uma tabela deve ser um conceito familiar. Ela possui colunas e linhas para armazenar dados, muito parecido com uma planilha. Essas tabelas podem  ter relacionamentos entre si, como uma tabela de Pedidos que se refere a uma tabela de Clientes para obter informações do cliente.

Por exemplo, suponha que temos uma tabela de Pedidos com um campo chamado CUSTOMER_ID:
![[Chapter 2 - Databases.png]]
Podemos esperar razoavelmente que haja outra tabela, talvez chamada CUSTOMER que contenha as informações do cliente para cada CUSTOMER_ID.

Quando percorremos a tabela de ORDER, podemos usar o CUSTOMER_ID para procurar as informações do cliente na tabela CUSTOMER. Esta é a ideia fundamental por trás de um "banco de dados relacional", <span style="background:#d4b106">onde as tabelas podem ter campos que apontam para informações em outras tabelas</span>. Esse conceito pode parecer familiar se já usamos VLOOKUP no Excel para recuperar informações de uma planilha de outra planilha em uma pasta de trabalho. 

## Why Separate Tables?
Mas qual o motivo de projetarmos tabelas separadas? A motivação é a normalização, que consiste em separar os diferentes tipos de dados em suas próprias tabelas, em vez de colocá-los em uma única tabela. Se tivéssemos todas as informações em uma única tabela, ela seria redundante, inchada e muito difícil de manter. 
![[Chapter 2 - Databases.png]]
Observamos que os pedidos de Re-Barre Construction, alguém teve que preencher as informações do cliente três vezes para todos os três pedidos (nome, região, endereço da rua, cidade, estado e CEP). Isso é muito redundante, ocupa espaço de armazenamento desnecessário e é difícil de manter. Imagine se um cliente tivesse uma mudança de endereço e tivéssemos que atualizar todos os pedidos para refletir isso. É por isso que é melhor separar CUSTOMERS e ORDERS em duas tabelas separadaas. 

![[Pasted image 20241221135340.png]]
Exploraremos novamente os relacionamentos entre tabelas no capítulo 8 e aprenderemos como usar o operador #JOIN para mesclar tabelas em uma consulta, de modo que as informações do cliente possam ser visualizadas junto com o pedido.

## Choosing a Database Solution

Banco de dados relacionais e SQL não são proprietários. No entanto, várias empresas e comunidades desenvolveram seus próprios softwares de banco de dados relacionais, todos os quais usam tabelas e aproveitam o SQL. Algumas soluções de banco de dados são leves e simples, armazenando dados em um único arquivo acessível a um pequeno número de usuários. Outras soluções de banco de dados são massivas e rodam em um servidor, suportando milhares de usuários e aplicativos simultaneamente. Algumas soluções de banco de dados são gratuitas e de código aberto, enquanto outras exigem licenças comerciais. 

Por motivos práticos, dividiremos as soluções de banco de dados em duas categorias: leves e centralizadas. Esses não são necessariamente os termos usados na indústria, mas ajudarão a esclarecer a distinção.

## Lightweight Databases
Se estamos procurando uma solução simples para um usuário ou um pequeno número de usuários (por exemplo, colegas de trabalho), um banco de dados leve é um bom ponto de partida. Bancos de dados leves têm pouca ou nenhuma sobrecarga, ou seja, não possuem servidores e são ágeis. Os bancos de dados são normalmente armazenados em um arquivo que podemos compartilhar com outras pessoas, embora comece a apresentar problemas quando várias pessoas fazem edições no arquivo simultaneamente. Quando encontrarmos esse problema, podemos corrigir migrando para um banco de dados centralizado.

Os dois bancos de dados leves mais comuns são SQLite e Microsoft Access.

Usaremos o SQLite neste livro. Ele é gratuito, leve e intuitivo de usar. É usado na maioria dos dispositivos que tocamos e pode ser encontrado em smartphones, satélites, aeronaves e sistemas automotivos. Ele praticamente não tem limitação de tamanho e é ideal para ambientes onde não é usado por mais de uma pessoa (ou no máximo algumas pessoas). O SQLite é ideal para aprender SQL devido à sua facilidade de instalação e simplicidade. 

## Centralized Databases
Se esperamos que dezenas de pessoas, centenas ou milhares de usuários e aplicativos usem um banco de dados simultaneamente, os bancos de dados leves não serão suficientes. Precisaremos de um banco de dados centralizado que seja executado em um servidor e que possa lidar com um alto volume de tráfego de maneira eficiente. Há uma variedade de soluções:
- MySQL
- Microsoft SQL Server
- Oracle
- PostgreSQL
- Teradata
- IBM DB2
- MariaDB

Podemos instalar algumas dessas soluções em qualquer computador e transformá-lo em um servidor. Em seguida, podemos conectar os computadores dos usuários (também conhecidos como clientes) ao servidor para que eles possam acessar os dados. O cliente pode enviar uma instrução SQL solicitando dados específicos, e o servidor processa a solicitação e retorna a resposta. Essa é uma configuração clássica cliente-servidor. O cliente solicita algo e o servidor fornece. 

Embora possamos transformar qualquer MacBook ou PC barato em um servidor MySQL, volumes de tráfegos maiores exigem computadores mais especializados (chamados de servidores) otimizados para tarefas de servidor. Esses são normalmente mantidos por um departamento de TI cujos membros administram e controlam banco de dados considerados formalmente críticos para os negócios. 

Em todas as soluções de banco de dados, usamos SQL para interagir com tabelas de uma maneira bastante uniforme, e até mesmo as ferramentas de editor SQL são um tanto semelhantes. Cada solução pode ter nuances em sua implementação SQL, como funcionalidades de data, mas tudo neste livro deve ser universalmente aplicável. 

MySQL usa SQL, sendo aplicável a qualquer plataforma de banco de dados. 

