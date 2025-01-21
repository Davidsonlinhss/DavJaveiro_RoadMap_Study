## Part 1
Nesta parte do livro,  desenvolveremos e testaremos APIs baseadas em REST, prontas para produção e em constante evolução, com suporte a #HATEOAS e #ETags. As especificações das APIs serão escritas utilizando o **Open API Specifications (Swagger)**. Aprenderemos os fundamentos do desenvolvimento de APIs reativas usando o **Spring WebFlux**.

---
Neste capítulo, exploraremos os fundamentos das APIs RESTful (ou simplesmente APIs REST) e seus paradigmas de design. Faremos um breve histórico do #REST, aprenderemos como os recursos são formados e entenderemos os métodos e códigos de status antes de mergulharmos no conceito de **Hypermedia as the Engine of Application State (HATEOAS)**. Esses fundamentos fornecerão uma base sólida para permitir o desenvolvimento de um serviço web RESTful. Aprenderemos também as melhores práticas para projetar interfaces de programação de aplicativos (APIs).

Além disso, esta capítulo apresentará um aplicativo de e-commerce de exemplo, que será utilizado ao longo do livro para ilustrar os diferentes aspectos do desenvolvimento de APIs.

Neste capítulo, abordaremos os seguintes tópicos:
- Introdução às APIs REST
- Manipulação de recursos e identificadores Uniformes de Recursos (URIs)
- Exploração dos métodos e códigos de status do Protocolo de Transferência de Hipertexto (HTTP);
- O que é HATEOAS?
- Melhores práticas para projetar APIs REST
- Visão geral de um aplicativo de e-commerce (nosso app de exemplo)

## Introducing REST APIs
Uma API é a forma como um pedaço de código se comunica com outro. Provavelmente já escrevemos e consumimos APIs em nossos programas; o própio Java fornece APIs por meio de classes organizadas em diferentes módulos, como #collections, #IO e #streams.

As APIs do SDK do Java permitem que uma parte de um programa se comunique com outra. <span style="background:#d4b106">Podemos escrever uma função e expô-la com modificadores de acesso públicos para que outras classes possam utilizá-la</span>. Essa assinatura de função é uma API para essa classe. No entanto, as APIs expostas usando essas classes ou bibliotecas permitem apenas a comunicação interna dentro de uma única aplicação ou serviço individual. <span style="background:#b1ffff">Então, o que acontece quando duas ou mais aplicações (serviços) precisam comunicar-se entre si? Em outras palavras, como integrar dois ou mais serviços? É aqui que as APIs de nível de sistema entram em ação. </span>

Historicamente, havia diferentes formas de integrar uma aplicação com outra - RPC, serviços baseados no Simpe Object Access Protocol ( #SOAP), entre outros. A integração de aplicativos tornou-se uma parte essencial das arquiteturas de software, especialmente após o boom da nuvem e dos dispositivos móveis. Agora, temos logins sociais, como Facebook, Google e GitHub, o que significa que podemos desenvolver nossa aplicação sem precisar criar um módulo de login independente, evitando problemas de segurança, como o armazenamento seguro de senhas. 

Esses logins sociais fornecem APIs utilizando #REST e #GraphQL. <span style="background:#d4b106">Atualmente, o REST é o mais popular e tornou-se o padrão para escrever APIs</span> para integração e consumo em aplicações web. Também abordaremos o GraphQL em detalhes nos capítulos finais do livro.

#REST signfica **Transferência de Estado Representacional**  que é um estilo de arquitetura de software. Os serviços web que seguem o estilo REST são chamados de serviços web RESTful.

## The History of REST
Antes da adoção do REST, quando a internet estava apenas começando a se tornar amplamente conhecida e aplicativos como Yahoo e Hotmail eram populares para mensagens sociais e e-mails, não existia uma arquitetura de software padrão que oferecesse uma maneira homogênea de integrar com aplicações web. Naquela época, utilizavam-se serviços web baseados em SOAP, que ironicamente, não eram nada simples.

#Roy-Fielding, em sua pesquisa de deoutorado, trouxe a solução *Architectural Styles and The Design of Network-Based Software Architectures*, apresentando o REST em 2000. O estilo arquitetural do REST permitiu que qualquer servidor se comunicasse com outro servidor pela rede. Isso simplificou a comunicação  e tornou a integração mais fácil.

O REST foi projetado para funcionar sobre o HTTP, o que permite seu uso em toda a web e em redes internas.

O eBay foi o primeiro a explorar APIs baseadas em REST, introduzindo sua API REST com parceiros selecionados em novembro de 2000.  Posteriormente, Amazon, Delicious e Flickr também começaram a oferecer APIs baseadas em REST. Em 2006, a Amazon Web Services (AWS) aproveitou o conceito da Web 2.0 (com a invenção do REST) e disponibilizou APIs REST para desenvolvedores consumirem seus serviços em nuvem.

Mais tarde, empresas como o Facebook, Twitter, Google e outras começaram a utilizá-lo. Atualmente (em 2023), dificilmente se encontra aplicações web desenvolvidas sem uma API REST. No entanto, as APIs baseadas em GraphQL, especialmente para aplicativos móveis, estão alcançando popularidade rapidamente.

## REST fundamentals
REST trabalha em cima do protocolo HTTP.  Cada URI funciona como um recurso da API. Portanto, devemos usar substantivos como #endpoints em vez de verbos. Endpoints no estilo RPC utilizam verbos, por exemplo, '/api/v1/getPersons'. Em comparação, no REST, esse endpoint poderia ser simplesmente escrito como '/api/v1/persons'.

Você deve estar se perguntando, então, como podemos **diferenciar as diferentes ações realizadas em um recurso REST**. É aqui que os métodos HTTP nos ajudam. Podemos fazer com que nossos métodos HTTP atuem como verbos, por exemplo, GET, DELETE, POST , PUT e PATCH. Por enquanto, o endpoint no estilo RPC getPerson é traduzido para GET /api/v1/persons no REST.
Portanto, os recursos são representados por substantivos e as ações que realizamos sobre esses substantivos são indicadas pelos métodos HTTP.
Logo, o endpoint persons é usado para múltiplas ações (listar, adicionar, atualizar e deletar) sem precisar criar endpoints específicos para cada ação. 

**Nota**
O endpoint REST é um URI único que representa um recurso REST. Por exemplo, 
- 'hhtps://demo.app/api/v1/persons' é um #endpoint REST. 
- '/api/v1/persons' é o caminho do endpoint, 
- 'persons' é o recurso REST

Aqui, há comunicação entre cliente e servidor. Portanto, o REST é baseado no <span style="background:#fdbfff">conceito de cliente servidor.</span>  O cliente chama a API REST e o servidor responde. O REST permite que um cliente (que pode ser um programa, serviço web ou aplicação de interface de usuário) se comunique com um servidor (ou serviço web) em execução remotamente (ou localmente) usando solicitações e respostas HTTP. 

O<span style="background:#b1ffff"> código de status HTTP geralmente indica o status,</span> e o corpo da resposta contém os dados retornados. Por exemplo, um código de status HTTP 200 OK normalmente representa **sucesso**.

Do ponto de vista do REST, uma solicitação HTTP é autodescritiva e contém contexto suficiente para que o servidor a processe. Por isso, as chamadas REST são #stateless (sem estado). Os estados são gerenciados no lado do cliente ou no lado do servidor.  Uma API REST não mantém seu próprio estado; ela apenas transfere estados do servidor para o cliente e vice-versa. É por isso que se chama **Transferência de Estado Representacional**.

O REST também utiliza o controle de cache do HTTP, tornando as APIs REST #cacheáveis. Assim, o cliente pode armazenar em cache a representação (ou seja, a resposta HTTP) (ou seja, a resposta HTTP), pois cada representação é autodescritiva.

O estado, no contexto do REST, refere-se aos dados (ou valores) transferidos entre o cliente e o servidor durante uma interação. Esses dados geralmente estão no formato JSON (ou às vezes XML, YAML, etc.), e representam o **estado atual do recurso** ou as informações necessárias para realizar uma operação. A troca de dados é chamada de **transferência de estado** porque os valores enviados representam o estado atual ou desejado de um recurso, mas o servidor não mantém esse estado nem o cliente, após a operação ser concluída. Portanto, o REST é #stateless ou seja, sem estado. 

As operações REST utilizam três componentes chaves:
- Recursos e URIs;
- Métodos HTTP;
- HATEOAS
Um exemplo de uma chamada REST simples seria a seguinte:
GET /licenses HTTP/2
Host: api.github.com

Aqui, o caminho /licenses denota o recurso de licenças. GET é um método HTTP. O número 2 no final da primeira linha denota a versão do protocolo HTTP. A segunda linha compartilha o host para a chamada.

O GitHub responde com um objeto JSON. O status é 200 OK e o objeto JSON está encapsulado em um corpo de resposta, da seguinte forma:
```JSON
HTTP/2 200 OK
date: Mon, 10 Jul 2023 17:44:04 GMT
content-type: application/json; charset=utf-8
server: GitHub.com
status: 200 OK
cache-control: public, max-age=60, s-maxage=60
vary: Accept, Accept-Encoding, Accept, X-Requested-With, Accept-Encoding
etag: W/"3cbb5a2e38ac6fc92b3d798667e828c7e3584af278aa314f6eb1857bbf2593ba"
… <bunch of other headers>
Accept-Ranges: bytes
Content-Length: 2037
X-GitHub-Request-Id: 1C03:5C22:640347:81F9C5:5F70D372

[
  {
    "key": "agpl-3.0",
    "name": "GNU Affero General Public License v3.0",
    "spdx_id": "AGPL-3.0",
    "url": "https://api.github.com/licenses/agpl-3.0",
    "node_id": "MDc6TGljZW5zZTE="
  },
  {
    "key": "apache-2.0",
    "name": "Apache License 2.0",
    "spdx_id": "Apache-2.0",
    "url": "https://api.github.com/licenses/apache-2.0",
    "node_id": "MDc6TGljZW5zZTI="
  },
  …
]

```
A terceira linha nesta resposta, ela informa o valor do tipo de conteúdo. É uma boa prática ter JSON como tipo de conteúdo tanto para a requisição quanto para a resposta.

Agora que nos familiarizamos com os fundamentos do REST, vamos nos aprofundar um pouco mais nos primeiros conceitos do REST: recursos e URIs.

## Handling resources and URIs
Todo documento na World Wide Web (WWW) é representado como um recurso em termos de HTTP. Esse recurso é representado por um URI, que é um #endpoint que representa um recurso único em um servidor.

Roy Fielding, em sua pesquisa de doutorado, afirma que u**m URI é conhecido por muitos nomes** - um endereço WWW, um Universal Document Identifier (UDI), um URI, um Uniform Resource Locator (URL) e um Uniform Resource Name URN.

Então, o que é um URI? Um URI é uma string (ou seja, uma sequência de caracteres) que identifica um recurso por sua localização, nome ou ambos (no mundo WWW). Existem dois tipos de URIs: URLs e URNs.

URLs são amplamente utilizados e até mesmo conhecidos por usuários não desenvolvedores. URLs não estão restritos apenas ao HTTP, mas também são usados por muitos outros protocolos, como #FTP, #JDBC e #MAILTO. Um URL é um <span style="background:#d4b106">identificador que identifica a localização de rede de um recurso</span>. 

## Sintaxe URI
A sintaxe URI é a seguinte:
```
scheme:[//authority]path[?query][#fragment]
```
Conforme a sintaxe, a lista de componentes de um URI é:
- #Scheme: refere-se a uma sequência não vazia de caracteres seguida por dois pontos :. O esquema começa com uma letra e é seguido por qualquer combinação de dígitos, letras, pontos, hífens ou caracteres de mais.
 Exemplos de esquemas incluem HTTP, HTTS, MAILTO, FILE e FTP.
 Esquemas URI devem ser registrados na Internet ASsigned Numbers Authoriy (IANA).

- #Authoriy: este é um campo opcional precedido por //. Ele consiste nos seguintes subcampos opcionais:
#Userinfo: este é um subcomponente que pode conter um <span style="background:#d4b106">nome de usuário e uma senha</span>, ambos opcionais.
#Host: este é um subcomponente que contém um endereço IP ou o nome de host ou domínio registrado.
#Port: este é um subcomponente opcional que é seguido por dois pontos :.

#Path: um caminho contém uma sequência de segmentos separados por caracteres de barra /. No exemplo anterior da API REST do GitHub, /licenses é o caminho.

#Query: este é um componente opcional e é precedido por um ponto de interrogação ?. O componente de consulta contém uma string de consulta de dados não hierárquicos. Cada parâmetro é separado por um e comercial & no componente de consulta e os valores dos parâmetros são atribuídos usando um operador de igual (=).

#Fragment: este é um campo opcional e é precedido por uma hashtag (#). O componente de fragmento inclui um identificador de fragmento que fornece direção a um recurso secundário. 

A lista a seguir contém exemplos de URIs:
- www.packt.com - este não contém o esquema. Contém apenas o nome do domínio. Não há nenhuma porta também, o que significa que aponta para a porta padrão.
- index.html - este não contém esquema nem autoridade. Contém apenas o caminho.
- https://www.packt.com/index.html - este contém o #scheme, a #Authoriy e o #path.

**Nota**
Do ponto de vista do REST, o componente de caminho de uma URI é muito importante porque representa o caminho do recurso, e os caminhos dos endpoints da sua API são formados com base nele. Por exemplo:
**GET**: https://www.domain.com/api/v1/order/1
Aqui, /api/v1/order/1 representa o caminho, e GET representa o método GTTP.

## What is a URL?
A maioria dos exemplos de URI mencionados anteriormente também podem ser chamados de URLs. Uma URI é um identificador; por outro lado, uma URL não é apenas um identificador, mas também informa como acessá-lo. 

**Request for Comments RFC**
De acordo com o RF-3986 sobre URIs, o termo URL refere-se ao subconjunto de URIs que, além de identificar um recurso, fornecem um meio de localizar o recurso ao descrever seu mecanismo de acesso primário (por exemplo, sua localização na rede). 

Uma URL **representa o endereço completo da web de um recurso**, incluindo:
- O nome do protocolo #schema;
- O nome do host e a porta (caso a porta HTTP não seja 80; para HTTPS, a porta padrão é 443);
- Parte do componente de autoridade;
- O caminho #path;
- E, opcionalmente, os subcomponentes de consulta e fragmento.

## What is a URN?
URNs não são comumente usados. Eles também são um tipo de URI que começa com o esquema "urn". O exemplo a seguir de URN é retirado diretamente do RFC-3986 sobre URIs:

urn:oasis:names:specification:docbook:dtd:xml:4.1.2
Este exemplo segue a sintaxe "urn:" < NID > ":" < NSS >, onde:
- < NID > é o identificador do namespace (namespace identifier);
- < NSS > é a string específica do namespace (name-specific string).

## Exploring HTTP methods and status codes
HTTP fornece vários métodos HTTP. No entanto, inicialmente você vai utilizar apenas cinco deles. Para iniciar com os métodos, precisamos saber as operações #Create, #Read, #Update e #Delete ( #CRUD) associadas aos métodos HTTP:
- #POST: create ou search;
- #GET: Read;
- #PUT: Update:
- #DELETE: Delete;
- #PATCH: Partial update;

Algumas organizações também utiliza o método HEAD em cenários onde você precisa recuperar as respostas dos endpoints REST. 

**Nota**
O REST não possui um requisito que especifique qual método deve ser usado para qual operação. No entanto, diretrizes e práticas amplamente utilizadas na indústria sugerem seguir certas regras. Logo, #REST é uma convenção utilizada na arquitetura de um sistema e não uma arquitetura por si.

Vamos discutir cada método em detalhes.

---
## POST
O método HTTP #POST normalmente é o que queremos associar às operações de **criação** de recursos. No entanto, existem certas exceções que queremos utilizar o método POST para realizar **consultas** e leituras. No entanto, isso deve ser colocado em prática após um processo bem pensado. Uma dessas exceções é uma operação de busca onde os critérios de filtro têm muitos parâmetros, que podem ultrapassar o limite de comprimento da chamada #GET. Uma string de consulta GET tem um limite de 256 caracteres. Além disso, o método HTTP #GET está limitado a um máximo de 2.048 caracteres, menos o número de caracteres no caminho real. Por outro lado, o método #POST não é limitado pelo tamanho do URL para submissão de apres nome-valor.

Também podemos querer usar o método #POST com HTTPS para uma chamada de leitura se os parâmetros de entrada enviados contiverem alguma informação privada o segura. 
Para operações de **criação bem-sucedidas**, podemos responder com o status 201 #Created, e para operações de busca ou leitura bem-sucedidas, devemos usar o status 200 OK ou 204 **No Content**. Isso se aplica mesmo que a chamada seja feita usando o método HTTP POST. 

Para operações de falhas, as respostas REST podem ter diferentes códigos de status de erro com base no tipo de erro, que veremos mais adiante.

## GET
O método HTTP #GET é o que geralmente queremos associar às operações de leitura de recursos. Da mesma forma, observamos a chamada GET/licenses do GitHub que retorna as licenças disponíveis no sistema GitHub. Além disso, operações GET bem sucedidas devem ser associadas ao código de status 200 OK se a resposta contiver dados, ou 204 No content se a resposta não contiver dados.

## PUT
O método HTTP #PUT é o que geralmente queremos associar às operações de atualização de recursos. Além disso, operações de atualização bem-sucedidas devem ser associadas ao código de status 200 OK se a resposta contiver dados, ou 204 No Content se a resposta não contiver dados. Alguns desenvolvedores usam o método HTTP #PUT para substituir recursos existentes. Por exemplo, a API V3 do GitHub usa PUT para substituir o recurso existente. 

## DELETE
O método HTTP DELETE é o que queremos associar às operações de exclusão de recursos. O GitHub não fornece a operação DELETE no recurso de licenças. No entanto, se você presumir que ela existe, certamente parecerá muito semelhante a DELETE. Uma chamada DELETE bem-sucedida deve excluir o recurso associado à chave agpl-3.0. Além disso, operações DELETE bem-sucedidas devem ser associadas ao código de status **204 No Content**. 

## PATCH
O método HTTP #PATCH é o que queremos associar às operações de atualização parcial de recursos. Além disso, operações PATCH bem-sucedidas devem ser associadas ao código de status **200 OK**. O PATCH é relativamente novo em comparação com outras operações HTTP. Na verdade, há alguns anos, o Spring não tinha suporte avançado para esse método para implementação REST devido à biblioteca HTTP antiga do Java. No entanto, atualmente, o Spring fornece suporte integrado para o método PATCH na implementação REST.

## HTTP Status Code
Existem cinco categorias de códigos de status HTTP, como segue:
- **Respostas Informativas** - (100-199);
- **Respostas Bem-sucedidas** - (200-299)
- **Redirecionamento** - (300 - 399)
- **Erros do cliente** - (400-499)
- **Erros do servidor** - (500-599)

Podemos ver a lista completa dos códigos de status HTTP através do documento da MDN Web [Status Code Doc](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status)

<iframe src="https://developer.mozilla.org/en-US/docs/Web/HTTP/Status" width="100%" height="400"></iframe>

No entanto, você pode encontrar os códigos de status de resposta REST mais utilizados na tabela a seguir:
Aqui está a tabela formatada em Markdown:

```markdown
| **HTTP Status Code** | **Description**                                                                                                                                             |
|-----------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **200 OK**            | For successful requests other than those already created.                                                                                                 |
| **201 Created**       | For successful creation requests.                                                                                                                         |
| **202 Accepted**      | The request has been received but not yet acted upon. This is used when the server accepts the request, but the response cannot be sent immediately, e.g., in batch processing. |
| **204 No Content**    | For successful operations that contain no data in the response.                                                                                           |
| **304 Not Modified**  | This is used for caching. The server responds to the client that the resource is not modified; therefore, the same cache resource can be used.            |
| **400 Bad Request**   | This is for failed operations when input parameters are incorrect or missing, or the request itself is incomplete.                                         |
| **401 Unauthorized**  | This is for operations that have failed due to unauthenticated requests. Although named "unauthorized," semantically, it means unauthenticated.          |
| **403 Forbidden**     | This is for failed operations that the invoker is not authorized to perform.                                                                              |
| **404 Not Found**     | This is for failed operations when the requested resource doesn’t exist.                                                                                  |
| **405 Method Not Allowed** | This is for failed operations when the method is not allowed for the requested resource.                                                             |
| **409 Conflict**       | This is for failed operations when an attempt is made for a duplicate create operation.                                                                  |
| **429 Too Many Requests** | This is for failed operations when a user sends too many requests in a given amount of time (rate limiting).                                          |
| **500 Internal Server Error** | This is for failed operations due to server errors. It’s a generic error.                                                                         |
| **502 Bad Gateway**   | This is for failed operations when upstream server calls fail, e.g., when an app calls a third-party payment service, but the call fails.                 |
| **503 Service Unavailable** | This is for failed operations when something unexpected has happened at the server, e.g., an overload or a service failure.                         |
```

Nós discutimos os componentes chave do REST, tais como #endpoints na forma de URIs, métodos e códigos de status. Vamos explorar o #HATEOAS, a espinha dorsal dos conceitos #REST que o diferencia do estilo #RPC.

## What is HATEOAS?
Com o HATEAOAS, serviços web RESTful fornecem informações dinamicamente através de hipermídia. Hipermídia é uma <span style="background:#d4b106">parte do conteúdo que</span> você recebe de uma resposta de chamada #REST. Esse conteúdo de hipermídia c<span style="background:#d4b106">ontém links para diferentes tipos de mídia, como texto, imagens e vídeos</span>. Os links de hipermídia podem estar contidos tanto nos cabeçalhos HTTP quanto no corpo da resposta. Se observamos as APIs do GitHub, veremos que elas fornecem links de hipermídia tanto nos cabeçalhos quanto no corpo da resposta. O GitHub usa o cabeçalho chamado Link para conter os links relecionados à paginação. Além disso, se observarmos as respostas das APIs do GitHub, também encontraremos outros links relacionados a recursos com chaves que têm um sufixo de URL. Vamos a um exemplo: Faremos uma chamada ao recurso GET /users e analisaremos a resposta:

```
curl -v https://api.github.com/users
```
 Esse comando quando executado fornece uma saída, faça o teste.

Na saída, perceberemos que o cabeçalho Link contém informações de paginação. Links para a próxima página e para a primeira página são fornecidos como parte da resposta. Além disso, podemos encontrar muitos URLs no corpo da resposta, como avatar_url ou followers_url, que fornecem links para outras hipermídias. Os clientes REST devem possuir um entendimento genérico de hipermídia para que possam interagir com serviços web RESTful sem ter qualquer conhecimento específico de como interagir com o servidor. <span style="background:#b1ffff">Simplesmente fazemos uma chamada a qualquer endpoint estático da API REST </span>e <span style="background:#b1ffff">receberemos links dinâmicos como parte da resposta para interagir mais.</span> O REST permite que clientes naveguem dinamicamente até o recurso apropriado percorrendo os links. Isso capacita máquinas, uma vez que os clientes REST podem navegar para diferentes recursos de forma semelhante a como os humanos olham para uma página WEB e clicam em qualquer link. Simplificando, o cliente REST usa esses links para navegar. 

HATEOAS é um conceito muito importante no REST. É um dos conceitos que diferencia o REST do RPC. Até o próprio Roy Fielding ficou tão preocupado com certas implementações de API REST que publicou o seguinte blog em seu site em 2008:
https://roy.gbiv.com/untangled/2008/rest-apis-must-be-hypertext-driven

Você deve estar se perguntando qual a diferença entre hipertexto e hipermídia. Essencialmente, hipermídia é apenas uma versão expandida do hipertexto.

---
**What is the difference between hypermedia and hypertext?**
Como Roy Fielding afirma: "Quando digo hipertexto, refiro-me à apresentação simultânea de informações e controles, de modo que as informações se tornam a affordance através da qual o usuário (ou autômato) obtém escolhas e seleciona ações. Quando Roy Fielding menciona affordance no contexto de HATEOAS, ele está se referindo a como os links fornecidos nas respostas da API indicam as próximas ações que um cliente pode realizar, portanto, #affordance é a maneira como os links na resposta da API orientam e capacitam os usuários e automatizações para navegar e interagir com os recursos de maneira dinâmica. A hipermídia é apenas uma expansão do que significa texto para incluir âncoras temporais dentro de um fluxo de mídia; a maioria dos pesquisadores abandonou a distinção. Hipertexto não precisa ser HTML em um navegador. Máquinas podem seguir links quando entendem o formato dos dados e os tipos de relacionamento."

#affordance sugere uma ação ou uso. Em interface web e de APIs, significa que os elementos dessa interface são intuitivos e naturalmente transmitem sua função.

**Resumo explicativo**
#HATEOAS e hipermídia são conceitos essenciais do REST. HATEOAS torna possível que aplicações naveguem pela web de maneira dinâmica, assim como nós navegamos em uma página da web. 

Aqui vai um resumo do que Roy Fielding mencionou:
- **Hipertexto** é como se fosse o guia ou mapa que nos permite fazer escolhas e ações enquanto lemos ou navegamos.
- **Hipermídia** é como uma versão mais avançada de hipertexto, incluindo links em diferentes tipos de mídia como vídeos e imagens.

- Hipertexto não precisar ser apenas HTML em um navegador. Máquinas também podem seguir links se entenderem o formato dos dados e os tipos de relação entre eles. 
Quando usamos a técnica de web scrping, estamos basicamento "raspando" dados de hipertexto e hipermídia de páginas da web. Isso inclui texto HTML, bem como outros tipos de mídia e links que a página possa conter. 

O REST, com seu foco em hipermídia e HATEOAS, oferece várias vantagens para a navegação entre hipertexto e hipermídia:
1. **Navegação Dinâmica:** com HATEOAS, as respostas da API incluem links para outros recursos relacionados, permitindo que clientes REST naveguem dinamicamente por esses links sem conhecimento prévio específico dos endpoints.
2. **Auto-Descoberta** clientes REST podem descobrir novos recursos e operações disponíveis dinamicamente, simplesmente seguindo os links fornecidos nas respsotas, similar a como um humano navega por um site.
3. **Desacoplamento** HATEOAS desacopla a interface do cliente da estrutura interna do servidor. O cliente pode agir com base nos links fornecidos, reduzindo a dependência de conhecimento pré-definido da API.
4. **Flexibilidade** HATEOAS melhora a flexibilidade do sistema, permitindo alterações e expansões sem quebrar clientes existentes, já que os clientes confiam nos links incluídos nas respostas para navegar.

Com base nos endpoints fornecidos para dinamizar a apresentação de dados em nossas páginas
1. **Puxar Dados**: usar as URLs fornecidas nas respostas para obter informações adicionais.
2. **Exibir de Maneira Estilizada** utilize os dados para criar uma interface de usuário mais rica e informativa, com links, imagens e outros conteúdos. 

## Best practices for designing REST APIs
Ainda é cedo para falar sobre as melhores práticas para implementação de APIs. As APIs são projetadas primeiro e implementadas depois. Portanto, encontraremos as melhores práticas relacionadas ao design nas próximas seções. Você também encontrará as melhores práticas para seguir em frente durante a implementação de APIs REST.

## Usando *substantivos* e não *verbos* ao nomear um recurso no caminho do endpoint
Já discutimos os métodos HTTP, que utilizam verbos. Assim, seria redundante usar verbos por conta própria, além além de fazer sua chamada parecer um endpoint de RPC, como por exemplo, GET /getlicenses. No #REST, **devemos sempre usar o nome do recurso**, pois, de acordo com os princípios REST, você transfere os estados e não as instruções. 

Por exemplo, observe novamente a API de licenças do GitHub, que recupera licenças. O endpoint correto é GET /licenses. Isso é perfeito. Suponha que usássemos verbos nesse endpoint, seria algo como GET /getlicenses. Isso ainda funcionaria, mas <span style="background:#b1ffff">semanticamente não seguiria os princípios REST,</span> pois transmite uma instrução de processamento em vez de uma transferência de estado. Portanto, use apenas nomes de recursos.  

No entanto, a API pública do GitHub oferece apenas operações de leitura no recurso de licenças, dentre todas as operações CRUD. Se precisarmos projetar as demais operações, seus caminhos devem se parecer com o seguinte:
- #POST /licenses - para criar uma nova licença;
- #PATCH /licenses/{license_key} - usado para atualizações parciais. Nesse caso, o caminho contém um parâmetro (um identificador) que retorna o caminho dinâmico. Aqui, o licence_key é um valor único na coleção de licenças e está sendo usado como identificador. Cada licença terá uma chave única. Essa chamada deve realizar a atualização na licença especificada. Lembre-se de que o GitHub utiliza #PUT para substituir o recurso.

## Usando a forma plural para nomear o recurso de coleção no caminho do endpoint
Se observamos a API de licenças do GitHUb, podemos notar que o nome do recurso é dado no plural. <span style="background:#b1ffff">É uma boa prática usar a forma plural se o recurso representar uma coleção</span>. Portanto, podemos usar /licenses em vez de /license. Uma chamada GET retorna a coleção de licenças. O GitHub não permite operações públicas de criação, atualização ou exclusão em um recurso de licença. Hipoteticamente, se isso fosse permitido, uma chamada POST criaria uma nova licença na coleção de licenças existentes. Da mesma forma, para chamadas DELETE e PATCH, uma chave de licença seria usada para identificar a licença específica para realizar, respectivamente, operações de exclusão e atualizações menores.

## Usando Hypermedia (HATEOAS)
Hypermedia (ou seja, links para outros recursos) facilita o trabalho do cliente REST. Existem **duas vantagens em fornecer links de URL explícitos** em uma *resposta*.  Primeiro, o cliente #REST não precisa construir os URLs REST por conta própria. Segundo, qualquer atualização no caminho do endpoint será tratada automaticamente, o que, por sua vez, torna as atualizações mais fáceis para clientes e desenvolvedores. 

### Versioning your APIs
O versionamento de APIs é fundamental para futuras atualizações. Com o tempo, as APIs continuam mudando, e podemos ter clientes que ainda utilizam uma versão mais antiga. Por isso, é necessário oferecer suporte a várias versões de APIs. Existem diferentes maneiras de versionar suas APIs:
- **Usando cabeçalhos:** a API do GitHub utiliza essa abordagem. Podemos adicionar um cabeçalho #Accept que indica qual a versão da API deve atender à requisição; por exemplo: *Accept: application/vnd.github.v3+json*. Essa abordagem oferece a vantagem de definir uma versão padrão. Se não houver o cabeçalho *Accept*, a requisição será direcionada para a versão padrão. No entanto, se um cliente REST que utiliza o cabeçalho de versionamento não for atualizado após uma recente modificação na API, isso pode quebrar a funcionalidade. Por isso, recomenda-se o uso de um cabeçalho de versionamento. 
- **Via Endpoint** envolve adicionar a versão diretamente na URL, como em *https://demo.app/api/v1/persons*, onde v1 indica a versão da API. Essa aobrdagem não permite definir uma versão padrão automaticamente, mas pode ser contornada com métodos como o encaminhamento de requisições. Os clientes sempre usam a versão específica da API que está indicada no caminho. 
Com base em suas preferências e visões, podemos escolher qualquer uma dar abordagens anteriores para o versionamento. No entanto, o ponto importante é que devemos sempre usar o versionamento. 

## Aninhamento de recursos
Considere esta questão muito interessante: como você vai construir o endpoint para recursos que são aninhados ou têm um certo relacionamento? Vamos dar uma olhada em alguns exemplos de recursos de clientes sob a perspectiva de um e-commerce:
- **GET /customers/1/addresses**: Retorna a coleção de endereços do cliente 1
- **GET /customers/1/addresses/2**: Retorna o segundo endereço do cliente 1
- **POST /customers/1/addresses**: Adiciona um novo endereço aos endereços do cliente 1
- **PUT /customers/1/addresses/2**: Substitui o segundo endereço do cliente 1
- **PATCH /customers/1/addresses/2**: Atualiza parcialmente o segundo endereço do cliente 1
- **DELETE /customers/1/addresses/2**: Deleta o segundo endereço do cliente 1

Até aqui tudo bem. Agora, podemos er um endpoint separado para o recurso de endereço (GET /addresses/2)? Faz sentido, e podemos fazer isso se houver um relacionamento que exija. Por exemplo, pedidos e pagamentos. EM vez de */orders/1/payments/1*, podemos preferir um endpoint separado */payments/1*. No mundo dos microsserviços, isso faz mais sentido; por exemplo, teríamos dois serviços web RESTful separados para **pedidos** e **pagamentos**. 

Agora, se combinarmos essa abordagem com *hypermedia*, as coisas ficam mais fáceis. Quando fazemos uma solicitação de API REST para o cliente 1, ele fornece os dados do cliente 1 e os links de endereço como *hypermedia*. O mesmo vale para pedidos, o link de pagamento estará disponível como *hypermedia*.

No entanto, em alguns casos, podemos ter uma resposta completa em uma única solicitação, em vez de usar URLs fornecidas pelo hypermedia para buscar o recurso relacionado. Isso reduz o número de acessos ao servidor. Porém, não existe uma regra definida. Para uma operação simples, faz sentido usar a abordagem de endpoint aninhado. Por exemplo, *PUT /GIST/2/star* que adiciona uma estrela e *DELETE /gist/2/star* que desfaz a estrela no caso da API do GitHub.

Além disso, em alguns cenários, você pode não encontrar um nome de recurso adequado quando múltiplos recursos estão envolvidos, como em uma operação de busca. Nesse caso, devemos usar um endpoint direto, como /direct/search

### Securing APIs
A segurança da sua API é uma expectativa importante e requer atenção cuidadosa. Aqui estão algumas recomendações:
- **Sempre use HTTPS** para comunicação criptografada;
- **Sempre consulte as principais ameaças e vulnerabilidades de segurança da API da OWASP**. Elas podem ser encontradas no site deles ou no repositório do GitHub.
- **APIs REST seguras devem ter autenticação implementada**. Como as APIs REST são sem estado, elas não devem usar cookies ou sessões. Em vez disso, devem ser protegidas usando tokens baseados em JWT ou OAuth 2.0.

### Manutenção da documentação
A documentação deve ser facilmente acessível e estar sempre atualizada com a implementação mais recente, incluindo as versões correspondentes. É sempre útil fornecer exemplos de código e casos de uso, pois isso facilita o trabalho de integração do desenvolvedor. 

Um registro de alterações (changelog) ou um **registro de versões** deve listar todas as bibliotecas afetadas. Se algum API for descontinuada, a documentação deve fornecer APIs de substituição ou alternativas detalhadas.

### Cumprindo com os códigos de status recomendado
Já aprendemos sobre os códigos de status na seção "Explorando métodos HTTP e códigos de status. Certifiquemos de seguir as mesmas diretrizes discutidas nessa seção.

### Garantindo o cache
O HTTP já fornece um mecanismo de cache. Você só precisa adicionar cabeçalhos adicionais na resposta da API REST. O cliente REST. então, utiliza a validação para decidir se faz uma nova chamada ou usa a resposta  em cache. Existem duas formas de fazer isso:
- #ETag: O ETag é um valor especial de cabeçalho que contém o valor de hash ou checksum da representação de recursos (ou seja, o objeto de respsota). Esse valor deve mudar em relação à representação da resposta. Ele permanecerá o mesmo se a resposta do recurso não mudar. Agora, o cliente pode enviar uma solicitação com outro campo de cabeçalho, chamado if-None-MAtch, que contém o valor do ETag. Quando o servidor recebe essa solicitação e econtra o valor do hash ou checksum da representação do recurso é diferente do valor do if-none-MAthc, ele deve retornar a resposta com uma nova representação do valor de hash no cabeçalho. 

## Introducing our e-coomerce app
O aplicativo de e-commerce que vamos construir será uma aplicação simples de compras online com as seguintes funcionalidades para os usuários:
- **Navegar pelos produtos**
- **Adicionar/remover/atualizar** os produtos no carrinho;
- **Realizar um pedido**
- **Modificar o endereço de entrega**
- **Soporte para uma única moeda**

E-commerce é um domínio muito popular. Se olharmos para as funcionalidades, podemos dividir a aplicação nos seguintes subdomínios usando contextos delimitados:
- **Usuários:** este subdomínio está relacionado aos usuários. Vamos adicionar o serviço web RESTful de usuários, que fornece APIs REST para o gerenciamento de usuários.
- **Carrinhos:** Este subdomínio está relacionado ao carrinho. Vamos adicionar o serviço web RESTful de carrinhos, que fornece APIs REST  para o gerenciamento de carrinhos. Os usuários podem realizar operações CRUD nos itens do carrinhos.
- **Produtos:** Este subdomínio está relacionado aos pedidos. Vamos adicionar o serviço web RESTful de pedidos, que fornece APIs REST para os usuários realizarem pedidos. 
- **Pagamentos:** Este subdomínio está relacionado aos pagamentos. Vamos adicionar o serviço web RESTful de pagamentos, que fornece APIs REST para processamento de pagamentos.
- **Envios:** Este subdomínio está relacionado ao envio. Vamos adicionar o serviço web RESTful de envios, que fornece APIs REST para rastreamento de pedidos e envio. 

![[Chapter 1 - Restful Web Service Fundamentals.png]]
Nós implementaremos um serviço Web RESTfull para cada subdomínio. Manteremos a implementação simples, e nós focaremos em aprender esses conceitos através deste livro.

## Resumo
Neste capítulo, nós aprendemos os conceitos básicos sobre o estilo de arquitetura REST, qual é baseada sobre HTTP, simples e tornando a integração de diferentes aplicações e serviços fáceis.

Nós exploramos os diferentes conceitos HTTP que nos permitiram escrever APIs REST de uma maneira agradável. Nós aprendemos sobre o HATEOAS, uma parte de implementação integral do REST. Adicionalmente, nós aprendemos as boas práticas para o design de APIs REST. 

## Questions
1. Por que estamos tendo serviços web RESTful se tornando popular, e, sem dúvida, o padrão da indústria?
REST está se tornando popular pelo fato dele trabalhar em cima do protocolo HTTP, que é a espinha dorsal da internet. Não precisamos separar as implementações do protocolo, semelhante ao SOAP. Podemos utilizar tecnologias web existentes para implementar APIs REST com integrações de aplicações simples comparado com outras tecnologias disponíveis. REST APIs tornam a integração de aplicações bem mais simples do que outras tecnologias disponíveis atualmente. 

Serviços RESTful trabalham sobre REST, na qual trabalham sobre recursos WEB. Os recursos representam os modelos de domínio. As ações são definidas usando métodos HTTP sobre recursos web. REST permite aos clientes que realizem ações baseadas nos links disponíveis através da implementação HATEOAS, semelhante a como um humano consegue navegar no navegador. 

2. What is the difference between RPC and REST?
RPC is more like functions that perform actions. Os endpoints RPC são diretamente formados baseados em verbos que levam para URLs separadas para cada ação. Whereas REST URLs represent nouns (substantivos) and could be the same for different operations.

3. **How would you explain HATEOAS?**
With HATEOAS, RESTful web services provide information dynamically through hypermedia. Hypermedia is the part of the content you recebie from a REST call response. 

4. **What error codes should be used for server-related issues?**
Status code 500 should be used for generic server erros. The 502 status code code should be used when an upstream server fails. Status code 503 is for unexpected server events such as an overload.

5. **Should verbs be used from REST endpoints, and why?**
Verbs should not be used to form REST endpoints. 