A ideia deste capítulo é apresentar os passos iniciais sobre o desenvolvimento de aplicações cliente-servidor com JavaScript. O objetivo é dar uma visão geral sobre a criação de um WebService que realiza as operações básicas de <span style="background:#affad1">inclusão</span>, <span style="background:#affad1">alteração</span>, <span style="background:#affad1">consulta</span> e <span style="background:#affad1">exclusão</span> de dados e uma página web que irá consumir esse Web Service.

As aplicações web que utilizamos na internet e que contêm listas de produtos, formulários de cadastro ou campos de pesquisa geralmente <span style="background:#ff4d4f">interagem com um banco de dados</span>. Esse banco de dados bem como os arquivos e programas que compõem um site ficam hospedados em um servidor web. Esses servidores e provedores de conteúdo cobram uma taxa mensal para disponibilizar a sua aplicação 24h por dia.

Se quisermos hospedar uma página na internet (para um cliente), tereos que registrar um domínio. O site onde podemos verificar a disponibilidade e adquirir um domínio é o resgistro.br.

Há alguns anos, as aplicações desenvolvidas para rodar em um servidor web eram pensadas e construídas de forma conjunta. A mesma equipe ou profissional desenvolvia tanto a parte que iria manipular os dados no servidor (back-end), quanto a parte de apresentação desses dados (front-end). 

É possível desenvolver um projeto back-end, que recebe e envia dados em um formato padrão, e receber requisições de aplicações web, mobile ou até mesmo desktop. O back-end recebe e nvia dados, independentemente de qual seja a plataforma do software cliente.

## 12.1 Express
O Express é um framework para Node.js que fornece um conjunto de recursos para o desenvolvimento de aplicações web ou móveis. A ideia central de um framework é dispor de recursos que visam otimizar o processo de construção de aplicações. 

No Express, <span style="background:#ff4d4f">as aplicações funcionam a partir da ideia de rotas</span> - que são caminhos que podem ser acessados a partir do navegador web. 

## 12.2 Nodemon
Quando executamos o comando node app, o Node.js compila o código salvo naquele momento. Ele não 
"escuta" as alterações realizadas no arquivo app.js. Dessa forma, teríamos de finalizar a execução do comando node app no terminal e, em seguida, executá-lo novamente. Esse processo não é nada prático.

## 12.3 Rotas POST e Formato JSON
No programa app.js, é possível que cada arrow function associada a uma rota recebe dois parâmetros: req(request) e res(response). Eles podem receber qualquer nome, mas req e res são os geralmente utilizados. O parâmetro **req** contém as informações que são passadas para a rota - por quem a acionou. Já **res** refere-se à resposta da rota - como as mensagens exibidas nas duas rotas anteriormente criadas.

Os verbos HTTP servem para indicar o tipo de requisição a ser feita para um servidor. Os quatro principais verbos, conforme a metodologia RESTful, são descritos abaixo:

GET - Consulta de dados;
POST - Inclusão de dados;
PUT - Alteração de dados;
DELETE - Exclusão de dados;

Para obter os dados vindos do form, utilizaremos o parâmetro req (request). Como os dados vêm no formato de um array de objetos, pode-se obtê-los criando as variáveis linha a linha (forma comentada no exemplo) ou utilizando a atribuição via desestruturação.

## 12.4 Middlewares
Um middleware é uma espécie de mediador entre duas partes, algo que fica no meio (middle). Vamos construir um exemplo. 

## 12.5 Use o Knex e escolha o banco de dados
Os bancos de dados permitem gerenciar os dados de um sistema, com inúmeros recursos visando garantir a segurança, integridade e disponibilidade dos dados. Armazenar as informações manipuladas por um sistema em um banco de dados é uma premissa de qualquer aplicativo profissional. SQLite, MySQL, PostgreSQL, Oracle e MS SQL são exemplos de sistemas gerenciadores de banco de dados.

Observe que o termo SQL é comum na maioria dos nomes dos bancos de dados. Isso porque SQL (Structure Query Language) é a linguagem padrão para a construção de consultas para banco de dados relacionais. 

Além da escolha do banco de dados, é necessário também definir qual a forma que vamos codificar o programa para interagir com o banco de dados. São três essas abordagens: 
1. inserir os comandos SQL diretamente no meio do código do programa;
2. utilizar um pacote que converte códigos de programa em instruções SQL, conhecidos como Query Builders, como o pacote Knex;
3. utilizar um package que define modelos de objetos associados às tabelas - o mapeamento objeto relacional (ORM), com destaque para o Sequelize;

Nosso projeto irá realizar o cadastro de livros da editora Novatec. Então, vamos criar a tabela de livros. Para isso, vamos utilizar um conceito comum que são as migrations. Um arquivo de migrations contém a definição da estrutura de cada tabela da aplicação e das modificações que ocorrem ao longo do desenvolvimento. As migrations também são úteis quando quisermos desfazer uma modificação na estrutura de alguma tabela de livros.
