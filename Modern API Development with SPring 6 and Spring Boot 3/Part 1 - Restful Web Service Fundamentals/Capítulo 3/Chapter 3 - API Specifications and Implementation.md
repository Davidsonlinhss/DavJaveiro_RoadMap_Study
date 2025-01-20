Nos capítulos anteriores, aprendemos sobre os aspectos de design de APIs REST e os fundamentos do Spring necessários para desenvolver serviços web RESTful. Neste capítulo, usaremos essas duas áreas para implementar APIs REST.

Optamos por uma abordagem de **design-first** para implementação, a fim de tornar o processo do desenvolvimento compreensível também para #stakeholders não técnicos. Para viabilizar essa abordagem, utilizaremos a **OpenAPI Specification (OAS)** para primeiro, projetar uma API e, depois, implementá-la. Também aprenderemos como lidar com erros que ocorrem durante o processamento das requisições. Neste capítulo, usaremos o exemplo de projetar e implementar uma API para um aplicativo de e-commerce simples.

Ao final deste capítulo, seremos capazes de projetar as especificações da API e utilizar o **OpenAPI Codegen** para gerar o código dos modelos e interfaces Java da API. Além disso, saberá como escrever controladores pseudo-Sring para implementar as interfaces Java da API e um **Global Exception Handler** para o serviço web.

We'll cover the following topics as part of this chapter:
- Designing APIs with OAS
- Understanding the basic structure os OAS
- Converting OAS to Spring code
- Implementing the OAS code interfaces
- Adding a Global Exception Handler
- Testing the implementation of the controllers

---
**Revisão sobre IoC**
A inversão de Controle é um princípio que transfere o controle da execução de partes do código para uma entidade externa. Em vez de um componente conhecer diretamente a execução de suas dependências, essas dependências são injetadas diretamente no componente, invertendo o controle de fluxo de execução.

**Relacionamento com desenvolvimento:**
- **Cliente (classe)**: escrevemos uma classe que precisa realizar uma ação;
- **Ação:** sua classe quer realizar uma ação, mas não precisa saber exatamente como.
- **Implementação (Outra classe)** outra classe é como um "especialista" em realizar essa ação. 
- **Execução da Ação:** em vez de dizer exatamente como realizar a ação, sua classe pede à outra classe para fazer isso.
- **Flexibilidade:** se quiser mudar como a ação é realizada, só precisamos mudar a outra classe, mantendo o pedido da ação na sua classe.

---

## Designing APIs with OAS
Podemos codificar a API diretamente, no entanto, essa abordagem leva a diversos problemas, como modificações frequentes, dificuldade na gestão da API e complicações nas revisões, especialmente conduzidas por equipes não técnicas do domínio. Por isso, é recomendado utilizar a abordagem de design primeiro (**design-first**).

A primeira pergunta que surge é: como podemos projetar APIs REST? Aprendemos no Capítulo 1, **Fundamentos de Serviços Web RESTful**, não existe um padrão estabelecido para governar a implementação de APIs REST.

O **OpenAPI Specification (OAS)** foi introduzido para resolver, pelo menos, os aspectos relacionados à especificação e descrição de APIs REST. Ele permite que escrevamos APIs REST utilizando as linguagens de marcação YAML ou JSON. 

Vamos utilizar a versão 3.0 do OAS para implementar a API REST do aplicativo de e-commerce. Usaremos o YAML, que é mais limpo e fácil de ler. O YAML é sensível a espaços, utilizando-os para identação. Por exemplo, ele representa pares chave:valor.

O OAS era anteriormente conhecido como Swagger Specification. Atualmente, as ferramentas que suportam OAS ainda são chamadas de ferramentas de Swagger. As ferramentas Swagger são projetos de código aberto que auxiliam no ciclo de vida de desenvolvimento de APIs REST. Neste capítulo, faremos uso das seguintes ferramentas de Swagger:
 - **Swagger Editor:** essa ferramenta é usada para projetar e descrever as APIS REST do aplicativo de e-commerce. Ela permite que escrevamos e visualize simultaneamente o design e a descrição de suas APIs REST. Certifique-se de usar a versão OAS 3.0. 
 - **Swagger Codegen:** essa ferramenta é usada para gerar modelos de API baseados no Spring e interfaces Java. Usaremos o plugin do Gradle para gerar código que funciona sobre o Swagger Codegen. Também existe uma ferramenta Grade do OpenAPI.
 - **Swagger UI:** Essa ferramenta é usada para gerar a documentação das APIs REST. O mesmo plugin do Gradle será utilizado para gerar a documentação da API.

## Understanding the basic structure of OAS
A definição da estrutura da Open-API pode ser dividida nas seguintes seções (todas são palavras-chave e sensíveis a maiúsculas e minúsculas):
- **openapi** (versão)
- **info**
- **externalDocs**
- **servers**
- **tags**
- **paths**
- **components**

Todos os termos mencionados anteriormente fazem parte da raiz. As três primeiras seções (**openapi**, **info** e **externalDocs**) são usadas para definir os metadados da API.

Podemos colocar a definição de uma API em um único arquivo ou dividi-la em vários arquivos, e o OAS suporta ambas as abordagens. Utilizaremos um único arquivo para definir a API de exemplo do e-commerce.

Em vez de discutir todas as seções teoricamente e depois escrever as definições da API de e-commerce, faremos ambos simultaneamente. Primeiro, abordaremos cada seção da definição da API de e-commerce e, em seguida, discutiremos por que a utilizamos e o que ela implica.

### The metadata sections of OAS
Vamos dar uma olhada nas seções de metadados das definições da API de e-commerce:
[[openapi.yaml | yaml]]

- #openapi: indica qual a versão do OAS (OpenAPI Specification) está sendo utilizada para escrever a definição da API. O OpenAPI utiliza versionamento semântico , o que significa que a versão segue o formato *major:minor:patch*. No valor de metadados da seção **openapi**, utilizamos 3.0.3. Isso indica que estamos usando a versão principal 3 com o patch 3. 

- #info: a seção #info contém os metadados sobre a API. Essas informações são usadas para gerar a documentação e podem  ser utilizadas pelo cliente. Ela inclui os seguintes campos, dos quais apenas *title* e *version* são obrigatórios, sendo os outros opcionais: *title*: é o título da API; *description*: usado para descrever os detalhes da API. Como podemos ver, é possível usar Markdown. 

- #termsOfService: este é um URL que aponta para os termos de serviço. Certifique-se de que ele segue o formato adequado de URL.

- #Contact: contém as informações de contato do provedor da API. O atributo #email deve ser o endereço de e-mail da pessoa ou organização de contato. Outros atributos incluem:
- *name:* representa o nome da pessoa ou organização de contato;
- *url:* fornece o link para a página de contato. Este campo é opcional, e todos os seus atributos também são opcionais.

- #license: fornece informações sobre a licença. O atributo *name* é obrigatório e deve representar corretamente o nome da licença, como "MIT". O atributo *url* é opcional e fornece um link para o documento da licença.

- #version: exibe a versão da API em formato de string.

- #externalDocs: este é um campo opcional que aponta para a documentação estendida da API. Ele possui dois atributos - *description* e *url*. O atributo *description* é um campo opcional que define um resumo da documentação externa. Podemos usar a sintaxe Markdown para a descrição. O atributo *url* é obrigatório e fornece o link para a documentação externa. 

vamos continuar construindo a definição da nossa API. Já concluímos a seção de metadados, então vamos discutir as seções *servers* e *tags*. 