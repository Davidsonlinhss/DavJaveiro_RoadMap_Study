## SQL
Suponhamos que tivesse uma aplicação que guardasse os aniversários de todos os nossos amigos. o SQL é a linguagem popular que usaria para se comunicar com a aplicação.
`Português: "Olá aplicação. Quando é o aniversário do meu marido?"`
```SQL
SELECT * FROM birthdays WHERE person = `husband`;
```
Geralmente os bancos de dados SQL são chamados de *banco de dados relacionais* porque são **compostos de relações**, que normalmente são chamadas de #tabelas. Muitas tabelas conectadas umas com as outras compõem um banco de dados.

## NoSQL
Geralmente os bancos de dados NoSQL são chamados de *banco de dados não relacionais* e são fornecidos em vários tipos e formas. Suas principais características são seus esquemas dinâmicos (o que significa que o esquema não precisa ser definido de maneira inalterável antecipadamente) e o fato de permitirem escalabilidade horizontal (ou seja, os dados podem ser distribuídos em várias máquinas).

O banco de dados NoSQL mais popular é o #MongoDB, que é mais especificamente um banco de dados de documentos. Podemos perceber que os dados não estão organizados mais em uma tabela estruturada e o número de campos (semelhantes a uma coluna) varia para cada documento (semelhante a uma linha). 
![[Figura 1.2 Uma coleção (uma variante de uma tabela) no MongoDB, um banco de dados NoSQL..png]]
*Figura 1.2: Uma coleção (uma variante de uma tabela) no MongoDB, um banco de dados NoSQL.*

Mesmo com a introdução do NoSQL, a maioria das empresas ainda armazena grande parte de seus dados em tabelas de banco de dados relacionais. 

## Sistemas de gerenciamento de banco de dados (DBMS)
Já ouvimos termos como #PostgreSQL ou #SQLite. Existem dois tipos de DBMS  (Sistema de Gerenciamento de Banco de Dados), que é o software usado no trabalho com um banco de dados.

Isso inclui itens como descobrir como importar dados e organizá-los e como gerenciar a maneira de os usuários acessarem os dados. Um RDBM (Sistema de Gerenciamento de Banco de Dados Relacional) é o software especificamente para banco de dados relacionais, ou banco de dados compostos de tabelas. 

Cada RDBMS tem uma implementação diferente do SQL, o que significa que a sintaxe varia um pouco de um software para o outro. Por exemplo:
```MySQL
//MySQL, PostgreSQL e SQLite
SELECT * FROM birthdays LIMIT 10;
```

Ao procurar a sintaxe SQL online, devemos sempre incluir o RDBMS com o qual estamos trabalhando. 