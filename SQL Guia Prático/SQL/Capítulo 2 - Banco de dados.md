## O que é um banco de dados?
Na definição mais ampla, um banco de dados é qualquer coisa que <span style="background:#affad1">colete e organize dados</span>. Uma planilha que contém reservas de cliente é um banco de dados, assim como um arquivo de texto simples que contém dados de programação de voos. Os próprios dados de texto simples podem ser armazenados em vários formatos, inclusive XML e CSV. 
No entanto, profissionalmente, quando alguém se refere a um "banco de dados", provavelmente está se referindo a um sistema de gerenciamento de banco de dados relacional (RDBMS). Esse termo pode parecer técnico e intimidador, mas um RDBMS é simplesmente um tipo de banco de dados que <span style="background:#ff4d4f">contém uma ou mais tabelas</span> que <span style="background:#ff4d4f">podem ter relacionamento entre si</span>. 

## Explorando banco de dados relacionais
Uma tabela deve ser um conceito familiar. Ela tem colunas e linhas para armazenar dados, como em uma planilha eletrônica. Essas tabelas podem ter relacionamentos entre si, como uma tabela ORDER que se refere a uma tabela CUSTOMER para obter informações sobre o cliente.

Por exemplo, suponhamos que tenhamos uma tabela ORDEM com um campo chamado de CUSTOMER_ID:

![[Capítulo 2 - Banco de dados.png]]
É razoável esperar que haja outra tabela, talvez chamada CUSTOMER, que contém as informações do cliente para cada CUSTOMER_ID:
![[Capítulo 2 - Banco de dados-1.png]]

Quando percorremos a tabela ORDER, podemos usar o CUSTOMER_ID para procurar as informações do cliente na tabela CUSTOMER. Essa é a ideia fundamental por trás de um banco de dados relacional, em que as tabelas podem ter campos que apontam para informações em outras tabelas. Esse conceito pode soar familiar se você já usou o PROCV no Excel para recuperar informações em uma planilha a partir de outra planilha em uma pasta de trabalho. 
## Por que separar tabelas?
Mas por que essas tabelas são separadas e projetadas dessa forma? A motivação é a normalização, que consiste em separar os diferentes tipos de dados em suas próprias tabelas, em vez de colocá-los em uma única tabela. Se tivéssemos todas as informações em uma única tabela, ela seria redundante, inchada e muito difícil de manter. Imagine se armazenássemos as informações do cliente na tabela ORDER. 

![[Capítulo 2 - Banco de dados-2.png]]

Observe que, para os pedidos da Re-Barre Construction, alguém teve de preencher as informações do cliente <span style="background:#ff4d4f">três vezes</span> para todos os três pedidos (nome, região, endereço, cidade, estado e CEP). <span style="background:#affad1">Isso é muito redundante</span>, ocupa espaço de armazenamento desnecessário e é difícil de manter. <span style="background:#affad1">Imagine se um cliente mudasse de endereço e tivéssemos que atualizar todos os pedidos para refletir isso</span>. É por isso que é melhor separar CUSTOMERS e ORDERS em duas tabelas distintas. Se for necessário alterar o endereço de um cliente, basta alterar um registro na tabela CUSTOMER. 

Exploraremos os relacionamentos de tabela novamente no Capítulo 8 e aprenderemos a usar o operador <span style="background:#ff4d4f">JOIN</span> <span style="background:#affad1">para mesclar tabelas em uma consulta</span>, de modo que <span style="background:#d3f8b6">as informações do cliente possam ser visualizadas juntamente com o pedido</span>. 

## Escolha de solução de um banco de dados
Os bancos de dados relacionais e o SQL não são proprietários. No entanto, há varias empresas e comunidades que desenvolveram seu próprio software de banco de dados relacional, todos eles usando tabelas e aproveitando o SQL.; Algumas <span style="background:#affad1">soluções de banco de dados são leves e simples</span>, armazenando dados em um único arquivo acessível a um pequeno número de usuários. Outras soluções de banco de dados são enormes e executadas em um servidor, suportando milhares de usuários e aplicativos simultaneamente. Algumas soluções de banco de dados são gratuitas e de código aberto, enquanto outras exigem licenças comerciais.

Por uma questão de praticidade, dividiremos as soluções de banco de dados em duas categorias: leve e centralizada. Esse termos não são necessariamente o vernáculo do setor, mas ajudarão a esclarecer a distinção.

## Bancos de dados leves
Se estiver buscando uma solução simples para um usuário ou um pequeno número de usuários (por exemplo, seus colegas de trabalho), um banco de dados leve é um bom ponto de partida. <span style="background:#ff4d4f">Os bancos de dados leves têm pouca ou nenhuma sobrecarga, o que significa que não têm servidores e são muito ágeis</span>. Em geral, os bancos de dados são armazenados em um arquivo que pode ser compartilhado com outras pessoas, embora isso comece a falhar quando várias pessoas fazem edições no arquivo simultaneamente. Quando você se depara com esse problema, talvez deva considerar a migração para um banco de dados centralizado.

<span style="background:#affad1">Os dois bancos de dados leves mais comuns são o SQLite e o Microsoft Access</span>. O SQLite é o que usaremos neste livro. Ele é gratuito, leve e de uso intuitivo. Ele é usado na maioria dos dispositivos que tocamos e pode ser encontrado em smartphones, satélites, aeronaves e sistemas automotivos. Ele praticamente não tem limitação de tamanho e é ideal para ambientes em que não é usado por mais de uma pessoa (ou, no máximo, por pouca pessoas). Entre muitos outros usos, o SQLite é ideal para aprender SQL devido à sua facilidade de instalação e simplicidade.

O Microsoft Access já existe há algum tempo e é inferior ao SQLite em termos de escalabilidade e desempenho. Mas ele é muito usado em ambientes de negócios e com qual vale a pena estar familiarizado. Ele tem muitas ferramentas visuais para escrever consultas sem usar SQL, bem como designers de formulários visuais e recursos de macro. Há muitos empregos disponíveis para assumir a propriedade de bancos de dados do Microsoft Acess e mantê-los, além de migrá-los para plataformas de banco de dados melhores, como o MySQL.

## Banco de dados centralizados
Se você espera que dezenas, centenas ou milhares de usuários e aplicativos usem um banco de dados simultaneamente, os bancos de dados leves não serão suficientes. Você precisa de um banco de dados centralizado que seja executado em um servidor e lide com um alto volume de tráfego de forma eficiente. Há uma grande variedade de soluções de banco de dados centralizados para escolher, incluindo as seguintes:
1. MySQL
2. Microsoft SQL Server;
3. Oracle;
4. PostgreSQL;
5. Teradata;
6. IBM DB2;
7. MariaDB;

Você pode instalar algumas dessas soluções em qualquer computador e transformá-lo em um servidor. Em seguida, é possível conectar os computadores dos usuários (também conhecidos como clientes) ao servidor para que eles possam acessar os dados. O cliente pode enviar uma instrução SQL solicitando dados específicos, e o servidor processa a solicitação e retorna a resposta. Essa é uma configuração clássica de cliente-servidor. O cliente solicita algo e o servidor fornece.

Embora você possa transformar qualquer MacBook ou PC barato em um servidor MySQL, volumes maiores de tráfego exigem computadores mais especializados (chamados computadores servidores) otimizados para tarefas de servidor. Normalmente, eles são mantidos por um departamento de TI cujos membros administram e controlam bancos de dados formalmente considerados essenciais para os negócios.

Não se confunda com o termo "SQL" usado para marcar plataformas de bancos de dados como MySQL, Microsoft SQL Server e SQLite. SQL é a linguagem universal para trabalhar com dados em todas essas plataformas. Elas simplesmente usaram SQL em seus nomes para fins de marketing. 

Ao entrar em um local de trabalho, é provável que exista um banco de dados centralizado com as informações de que você precisa, e você precisará solicitar acesso a ele. Embora não abordemos bancos de dados centralizados neste livro, <span style="background:#affad1">a experiência entre diferentes soluções de banco de dados deve ser basicamente a mesma.</span> Em todas as soluções de banco de dados, você usa SQL para interagir com tabelas de maneira bastante uniforme, e até mesmo as ferramentas do editor de SQL são um pouco semelhante. Cada solução pode ter nuances em sua implementação de SQL, como funcionalidades de data, mas tudo neste livro deve ser universalmente aplicável. 

Se você precisar criar uma solução de banco de dados centralizado, recomendo fortemente o MySQL. Ele é de código aberto, de uso gratuito, fácil de instalar e configurar e é usado pelo Facebook, Google, eBay, Twitter e centenas de outras empresas do Vale do Silício.

Com uma compreensão conceitual dos bancos de dados, podemos agora começar a trabalhar com eles. Embora usemos o SQLite neste livro, lembre-se de que ele usa SQL, portanto, o conhecimento adquirido é aplicável a todas as plataformas de banco de dados.

