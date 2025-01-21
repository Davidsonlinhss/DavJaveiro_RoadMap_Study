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

### The servers and tags sections of OAS
Depois da seção de metadados, agora podemos descrever as seções *servers* e *tags*. Vamos dar uma nessas seções dentro do arquivo [[openapi.yaml]]. 

- #servers:  a seção é opcional, ela contém uma lista de servidores que hospedam a API. Se o documento da API hospedada for interativo, ele pode ser usado pelo Swagger UI para chamar diretamente a API e exibir a resposta. Caso não seja fornecida, ele apontará para a raiz (/) do servidor onde o documento está hospedado. Os URLs dos servidores são exibidos utilizando o atributo url. 

- #tags: a seção de tags, definida no nível raiz, contém uma coleção de tags e seus metadados. As tags são usadas para agrupar as operações realizadas nos recursos. Os metadados das tags incluem o *name*, que é um campo obrigatório, e dois atributos adicionais opcionais: *description* e *externalDocs*. O atributo *name* contém o nome da tag. 

### The components section of OAS
Se estivéssemos seguindo a estrutura de forma sequencial, teríamos discutido *patch* primeiro. No entanto, conceitualmente, queremos escrever nossos modelos primeiro, antes de usá-lo na seção *patch*. Portanto, vamos discutir a seção *componentes* primeiro, que é usada para definir os modelos. 

```yaml
components:
  schemas:
    Cart:
      description: Shopping Cart of the user
      type: object
      properties:
        customerId:
          description: Id of the customer who possesses the cart
          type: string
        items:
          description: Collection of items in cart.
          type: array
          items:
            $ref: '#/components/schemas/Item'

```
Se estamos trabalhando com YAML pela primeira vez, podemos achar um pouco desconfortável. No entanto, uma vez que passamos por essa seção, nos sentiremos mais confortáveis com YAML.

Aqui, definimos um modelo chamado *Cart*. O modelo *Car* é do tipo objeto e contém dois campos: *customerId* (um string) e *items* (um array). 

---
**O tipo de dado objeto**
Podemos definir qualquer modelo ou campo como um objeto. Assim que marcarmos um tipo como objeto, o próximo atributo será *properties*, que consiste em todos os campos do objeto. Por exemplo, o modelo *Cart* no código anterior terá a seguinte sintaxe:
```yaml
type: object
properties:
	<nome do campo>
		type: <tipo de dados>
```

OAS supports seis tipos de dados básicos, no qual são os seguintes (todos estão em caixa baixa):
- string
- number
- integer
- boolean
- object
- array

Vamos discutir o modelo *Cart*, no qual usamos os tipos de dados **string**, **object** e **array**. Outros tipos de dados são *number*, *integer* e *boolean*. Agora, como definimos os tipos *date*, *time*, *float*, e outros. Podemos fazer isso com o atributo *format*, que pode ser usado juntamente com o tipo *object*. Por exemplo, veja o seguinte código:
```yaml
orderDate:
	type: string
	format: date-time
```

No código anterior, *orderDate* é definido como o tipo *string*, mas o format determina qual valor de string ele conterá. Como o *format* é marcado com date-time, o campo orderDate conterá a data e a hora no formato definido na RFC 3339. 

O campo *items* do nosso modelo *Cart* é um array do tipo *Item*, definido pelo usuário. Aqui, *Item* é outro modelo e é referenciado usando *ref*. Na verdade, todos os tipos definidos pelo usuário são referenciados usando ref. O modelo Item também faz parte da seção *components/schemas*. Portanto, o valor ref contém um anexo para tipos definidos pelo usuário com #/components/schemas/{type}.

ref representa o objeto de referência. Ele é baseado na referência JSON e segue a mesma semântica no YAML. Ele pode se referir a um objeto no mesmo documento ou em documentos externos. Portanto, é usado quando você tem definições de API divididas em vários arquivos. Já vimos um exemplo de seu uso no código anterior. Vamos ver mais um exemplo:
```yaml
# Documento de Esquma Relativo
$ref: Cart.yaml

#Documento relativo com Esquema embutido
$ref: definitions.yaml#/Cart
```
Há outra consideração sobre o código anterior. Se observarmos com atenção, encontrará dois itens - um é uma propriedade do tipo Cart e o outro é um atributo do tipo **array**. O primeiro é simples - um campo do objeto *Cart*. No entanto, o segundo pertence ao array e faz parte da sintaxe do array.


## The path section of OAS
A seção *path* é a última seção do OAS (em termos de sequência, é a penúltima, mas já discutimos *components* na subseção anterior), onde definimos os endpoints. É aqui que formamos o URI e anexamos os métodos HTTP.

Vamos escrever a definição para *GET /api/v1/carts/{customerId}/items*. Essa API recupera os itens do carrinho associados a um identificador de cliente específico.

Se observamos o código anterior, podemos ver qual é o #endpoint, qual método HTTP e quais parâmetros essa API utiliza, e, mais importante, qual resposta podemos esperar. Aqui, *v1* representa a versão da API. Cada caminho de endpoint (como */api/v1/carts/{customerId}/items*) tem um método HTTP (como #POST) associado a ele. O caminho do endpoint sempre começa com /.

Cada método pode ter sete campos: *tags, sumary, description, operationId, paramters, responses e requestBody*. Vamos entender cada um deles:
- #tags: as tags são usadas par agrupar APIs, como mostrado na captura de tela a seguir, para APIs marcadas com o grupo *cart*. O Swagger Codegen utiliza tags para organizar os métodos de uma API na mesma classe. Por exemplo, todos os endpoints listados relacionados ao "cart" na captura estaráo na classe CartsApi.java.

- #sumary and #description: as seções *summary* e *description* são as mesmas mencionadas anteriormente na seção sobre metadados #OAS. Elas fornecem, respectivamente, o resumo da operação da API e uma descrição detalhada. O campo *description* permite o uso de Markdown, seguindo o mesmo esquema.

- #operationId: representa o nome da operação. Como mostrado no código anterior, atribuímos o valor *getCartByCustomerId* a ele. Esse mesmo nome de operação será utilizado pelo Swagger Codegen como o nome do método na interface Java gerada para a API.

- #parameters se observarmos com atenção, veremos um traço hífen na frente do campo nome. Isso é usado para declará-lo como um elemento de um array. O campo *parameters* pode conter múltiplos parâmetros - na verdade, uma combinação de parâmetros de *path* e *query*, por isso, ele é declarado como um array. Para parâmetros de *path*, é necessário garantir que o valor de name, no campo parameters, seja o mesmo especificado no path dentro de chaves. O campo *paramters* contém os parâmetros de *query*, *path*, cabeçalho (*header*) e *cookie* da API. No código anterior, utilizaremos o parâmetro de *path* (valor do campo in). É possível alterar esse valor para *query* caso deseje declará-lo como um parâmetro de *query*, e assim por diante para outros tipos de parâmetros. Podemos marcar um campo obrigatório *required* ou opcional utilizando o campo *required* dentro da seção *parameters*, que é um parâmetro booleano.

- #Responses: o campo *responses* é obrigatório para todas as operações da API. Ele define os tipos de respostas que podem ser enviadas pela operação da API ao ser requisitada. Esse campo contém os códigos de status HTTP como padrão. É necessário que haja pelo menos uma resposta definida, que pode ser uma resposta padrão (*default response*) ou um código de status HTTP bem-sucedido, como 200. Como o nome indica, a resposta padrão será usada quando nenhuma outra resposta estiver definida ou disponível para a operação da API. The description field is used to describe the response. The headers field is used to define the header and its value. A headers example is showns as follows:
```yaml
responses:
	200:
		description: operation successful
			headers:
				X-RateLimit-Limit:
					schema:
						type: integer

```

- The *content* field, like we have previous code, defines the type of content that denotes the different media types. We use application/json. Similarly, you can define other media types, such as application/xml. The content type field contains the actual response object that can be defined using the *schema* field, as we have defined an array of the Item model inside it. 
As mentioned earlier, <span style="background:#affad1">you can create a reusable response</span> under the components section and directly use it with $ref. 

- #requestBody: the requestBody field is used to define the request payload object. Like the responses object, requestBody also contains the descriptiuon and content fields. Content can be defined in a similar fashion to the way it is defined for the responses object. You can refer to the previus code of POST /carts/{customerId}/items for an axample. As a response, you can also create reusable request bodies under the components section and directly use them with $ref.

Here, we just described part of a sample e-commerce app's API. Similarly, you can describe other APIs. 

I suggest that you copy the code from openapi.yaml and paste it into the editor... my intellij IDE just it.

We have finished designing our APIs, so noew let's generate code using openapi.yaml and enjoy the fruits of our hard work. hahah

## Converting OAS to Spring code
I am sure you are as excited as I am to start implementing the API. So far (até o momento), we have learned about the RESTful web service theory and concepts and Spring fundamentals, and also designed our first API specs for a sample e-commerce application.

For this section, you can either clone the Git repository... or start to create a Spring project from scratch using Spring Initializr... with the following options:
- Project: Gradle - Groovy
-  Language: Java
 - Spring Boot: 3.0.8 Or use the 3.X.X available version. Replace the project metadata with your preferred values
-  Packaging: Jar
- Java: 17
-  Dependencies: Spring Web

Once you open the project in your favorite IDE, you can add the following extra dependencies required for OpenAPI support under dependencies in the build gradle file. 
continuous tomorrow this section...