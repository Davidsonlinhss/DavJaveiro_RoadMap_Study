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

O eBay foi o primeiro a explorar APIs baseadas em REST, introduzindo sua API REST com parceiros selecionados em novembro de 2000. 