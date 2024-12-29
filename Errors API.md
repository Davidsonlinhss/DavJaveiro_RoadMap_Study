Normalmente, os códigos de status de resposta HTTP 4xx e 5xx são considerados erros. As especificações HTTP definem a finalidade desses códigos de status. Nem todos os códigos de status de resposta HTTP podem ter sido implementados para uma API. A documentação da API deve incluir os códigos de status de resposta HTTP que o consumidor da API pode esperar em diferentes cenários de erro. A amostra da carga útil da resposta a erros e o código de status da resposta HTTP também devem ser especificados. Isso ajuda o aplicativo do consumidor a analisar as mensagens de erro. A carga útil da resposta a erro pode incluir códigos de erro comerciais específicos e mensagens de erro descritivas que oferecem informações sobre a causa exata do erro.

## Status Code
Entre outras coisas, o código de status de uma resposta HTTP fornece informações sobre se uma solicitação HTTP pôde ser processada com êxito ou não. Um código de status é um número de três dígitos seguido de um texto descritivo curto. No total, existem cinco categorias de códigos de status, começando com os dígitos de 1 a 5, conforme listado <span style="background:#d3f8b6">abaixo</span>:
1. 1xx: a solicitação foi recebida e será processada;
2. 2xx: a solicitação foi processada com sucesso pelo servidor;
3. 3xx: a solicitação requer ações futuras;
4. 4xx: a requisição não pode ser processada devido a um erro localizado no lado do <span style="background:#d4b106">cliente</span>;
5. 5xx: a requisição não pode ser processada devido a um erro localizado no lado do <span style="background:#d4b106">servidor</span>;

Alguns dos erros mais importantes são listados abaixo, como por exemplo, o famoso 404. O código de status 304 indica que o recurso solicitado (ou a URL) não existe:
1. <span style="background:#affad1">200, OK</span>: sua requisição foi processada pelo servidor com sucesso;
2. <span style="background:#d4b106">301, MOVIDO PERMANENTEMENTE</span>: o recurso solicitado foi atribuído permanentemente a uma nova URL;
3. <span style="background:#ff4d4f">400, Bad Request</span>: Requisição incorreta;
4. <span style="background:#ff4d4f">401, Unauthorized</span>: A requisição não pode ser processada sem autorização;
5. <span style="background:#ff4d4f">403, Forbidden</span>: O acesso ao recurso solicitado é proibído;
6. <span style="background:#ff4d4f">404, Not Found</span>: O recurso solicitado não foi encontrado;
7. <span style="background:#ff4d4f">405, Method Not Allowed</span>: o método HTTP utilizado (por exemplo, GET, PUT ou POST) não é permitido para o recurso solicitado;
8. <span style="background:#ff4d4f">500, Internal Server Error</span>: erro interno do servidor devido ao qual a solicitação não pode ser processada;





