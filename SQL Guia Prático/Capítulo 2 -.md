## Ferramentas de banco de dados
Ao invés de trabalhar usando um RDBMS diretamente, a maioria das pessoas usam uma ferramenta de banco de dados para interagir com um banco de dados. A ferramenta de banco de dados vem com uma interface gráfica de usuário adequada que **permite** apontar, clicar e escrever código SQL em uma configuração amigável.
Em segundo plano, a ferramenta usa um *driver de banco de dados*, que é software que ajuda a se comunicar com um banco de dados. 
![[Figura 2.1 Acessando um RDBMS por meio de uma janela de terminal versus uma ferramenta de banco de dados..png]]
*Figura 2.1: Acessando um RDBMS por meio de uma janela de terminal versus uma ferramenta de banco de dados.*

Existem várias ferramentas de banco de dados disponíveis. Algumas funcionam com um *único RDBMS* e outras com *vários*. 

DataGrip - é uma ferramenta de gerenciamento e desenvolvimento de banco de dados desenvolvida pela JetBrains. 

## Conectando a ferramenta a um banco de dados
Ao abrirmos uma ferramenta de banco de dados, a primeira etapa a ser executada é nos conectarmos com um banco de dados. Isso pode ser feito de várias maneiras:
- Opção 1: <span style="background:#d4b106">*Crie um novo banco de dados*</span> - podemos criar um novo banco de dados escrevendo uma instrução CREATE DATABASE my_new_db;
- **Porta**: Já deve haver um número de porta padrão nesse campo e não devemos alterar. Ele será diferente para cada RDBMS
- MySQL: 3306
- Oracle: 1521
- PostgreSQL: 5432
- SQL Server: 1433