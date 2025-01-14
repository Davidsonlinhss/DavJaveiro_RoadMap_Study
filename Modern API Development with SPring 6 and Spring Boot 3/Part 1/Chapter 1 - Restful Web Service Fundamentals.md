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

As APIs do SDK do Java permitem que uma parte de um programa se comunique com outra. Podemos escrever uma função e expô-la com modificadores de acesso públicos para que outras classes possam utilizá-la. Essa assinatura de função é uma API para essa classe. No entanto, as APIs expostas usando essas classes ou bibliotecas permitem apenas a comunicação interna dentro de uma única aplicação ou serviço individual. <span style="background:#b1ffff">Então, o que acontece quando duas ou mais aplicações (serviços) precisam comunicar-se entre si? Em outras palavras, como integrar dois ou mais serviços? É aqui que as APIs de nível de sistema entram em ação. </span>

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
