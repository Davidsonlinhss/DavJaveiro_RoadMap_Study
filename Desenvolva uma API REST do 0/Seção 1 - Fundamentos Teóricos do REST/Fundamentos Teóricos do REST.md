#REST é baseado em um conjunto de constraints:
1. **Cliente-servidor:** Clientes e servidores separados.
2. **Stateless server:** O servidor não deve guardar o estado do cliente. Cada request de um cliente contém todas as informações necessárias para atendê-la, ou seja, para processar a requisição. Em outras palavras, o servidor não mantém nenhum estado da sessão entre as requisições. Cada requisição é independente e isolada das demais. 
3. **Cacheable:** O cliente deve ser informado sobre as propriedades de cache de um recurso para que possa decidir quando deve ou não utilizar cache.
4. **Interface uniforme:** Existe uma interface uniforme entre cliente e servidor. Identificação de recursos (URI). Manipulação de recursos a partir de suas representações. Mensagem auto descritivas. 

## Vantagens
REST é um padrão arquitetural basicamente leve por natureza. Então quando tivermos limitações de banda prefira web services REST;

Desenvolvimento fácil e rápido;

Aplicativos Mobile tem ganhado cada vez mais espaço e precisam interagir rapidamente com o servidores e o padrão REST é mais rápido no processamento de dados das #requests e #responses.

## Entendendo Request e Response e seus métodos HTTP
A coluna vertebral do REST é o **protocolo** #HTTP. 
Na arquitetura REST, as requisições (requests) e as respostas (responses) são fundamentais para a comunicação entre o cliente e o servidor. Aqui está um resumo dos principais componentes de cada uma:
1. Método HTTP: indica a ação desejada no recurso.
- **GET:** recuperar informações; R
- **POST:** Criar um novo recurso; C
- **PUT:** Atualizar um recurso existente; U
- **DELETE:** Remover um recurso; D
- **PATCH:** Atualizar parcialmente um recurso;

2. URL: é o identificador do recurso que está sendo acessado ou manipulado.
- **Exemplo:** `/api/users/123` (recurso específico) ou `/api/users` (coleção de recursos).

3. Cabeçalhos HTTP (Headers): informações adicionais sobre a requisição.
- **Content-type:** o tipo de conteúdo sendo enviado (por exemplo, `application/json`);
- **Authorization:** informações de autenticação (por exemplo, `Bearer token`);
- **Accept:** tipos de conteúdo que o cliente aceita como resposta;

4. Corpo da Requisição (Body): dados enviados na requisição (principalmente em métodos POST e PUT).
- Geralmente em formato JSON, XML, ou outro formato estruturado;
- Exemplo: `{"name": "John Doe", "email": "john.doe@example.com" }`

## Status code
Forma como o servidor ou API responde aos clients
1xx Informacionais;
2xx Sucesso;
3xx Redirecionamento;
4xx Erro de Cliente;
5xx Erro de Server;

2xx Sucesso
<span style="background:#affad1">200 OK - Significa que a minha request (criação ou deleção) foi processada com sucesso;</span>
<span style="background:#affad1">201 Created - Criação de uma fila, tópico, fila temporária, tópico temporária, session, producer, consumer, listener, queu browser ou mensagem realizada com sucesso.</span> 
202 Accepted
<span style="background:#affad1">204 No Content - deleção de uma fila, tópico, sessão, producer ou listener bem sucedida, mas sem retorno do conteúdo.</span>

## 3xx Redirecionamento
301 Moved Permanently
## 4xx Erro
<span style="background:#d4b106">400 Bad Request: quando o cliente manda uma requisição para uma operação que não existe;</span>
<span style="background:#d4b106">401 - Unauthorized - o cliente não tem autorização para executar requisições na operação em questão.</span>
403 Forbidden - <span style="background:#d4b106">o cliente não tem permissão para executar requisições na operação em questão.</span>
404 Not Found - <span style="background:#d4b106">o objeto requisitado pelo path não existe.</span> 
405 Method Not Allowed - <span style="background:#d4b106">o usuário não tem permissão de acesso ao path.</span>
409 Conflict - <span style="background:#d4b106">um objeto já foi criado com as mesmas informações. </span>
## 5xx Erro de Server
500 Internal Server Error - <span style="background:#ff4d4f">Ocorreu uma falha no servidor, podendo ser desde uma falha no SQL por exemplo.</span> 


# Verbos HTTP (ou métodos HTTP)
- O verbo HTTP #Get é usado para ler ou recuperar uma representação de um recurso. Em um cenário feliz, uma requisição GET retorna uma representação em XML ou JSON e um HTTP status code 200 (OK). Em um cenário de erro o retorno mais comum é 404 (NOT FOUND) ou 400 (BAD REQUEST). 
- O verbo #POST é mais utilizado frequentemente para criar novos recursos - inserir um novo item na base. 
- O verbo #PUT é comumente usado para atualizar informações, colocando um recurso conhecido no (body) corpo da requisição contendo novas informações que representam o recurso original; parâmetros suportados, via URL (PATH ou Query Params), via header, via Body...
- O verbo #Delete é utilizado para deletar um recurso identificado por uma URI.  São suportados os parâmetros via URL ou via Header e via Body.
# Níveis de maturidade do REST
São quatro os níveis para que a API seja RESTfull:
- Level 0: pântano de XML. Gravar as ordens de serviço. HTTP para trafegar JSON e XML
- Level 1: informações organizadas com base em seus recursos. Existe um endpoint para gravar clientes, fornecedor, abrir ordens de serviço, gerir equipamentos, as coisas começam a ficar organizadas.
- Level 2: HTTP verbs: os verbos, GET, POST, PUT e Delete, passam a ser utilizados além de separar a informação com base em recursos.
- Level 3: Hypermedia Controls (Hipertexto).

# SWAGGER
O Swagger é um conjunto de ferramentas de código aberto que ajudam a projetar, construir, documentar e consumir APIs RESTful. Ele é amplamente utilizado para facilitar o desenvolvimento e a manutenção de APIs, proporcionando uma forma clara e padronizada de descrever e interagir com elas. Ele é como um passo além da escala de maturidade de Richardson. 

# Autenticação
A nossa API precisará de autorização para que seja permitido as operações dentro da API. Vamos utilizar o JWT. 






