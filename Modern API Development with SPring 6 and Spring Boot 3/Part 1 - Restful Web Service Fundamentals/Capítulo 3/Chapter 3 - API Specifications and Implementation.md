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

Assim que abrirmos o projeto na nossa IDE favorita, adicionaremos as seguintes dependências extras necessárias para suporte ao OpenAPI na seção de dependências do arquivo build.gradle:
```gradle
swaggerCodegen 'org.openapitools:openapi-generator-cli:6.2.1' compileOnly 'io.swagger:swagger-annotations:1.6.4' compileOnly 'org.springframework.boot:spring-boot-starter- validation' compileOnly 'org.openapitools:jackson-databind-nullable:0.2.3' implementation 'com.fasterxml.jackson.dataformat:jackson- dataformat-xml' implementation 'org.springframework.boot:spring-boot-starter- hateoas' implementation 'io.springfox:springfox-oas:3.0.0'
```

Como mencionado anteriormente, usaremos o plugin Swagger para geração de código a partir das definições da API que acabamos de escrever:
1. **Adicionar o plugin Gradle:** Para utilizar a ferramenta OpenAPI Generator CLI, podemos adicionar o plugin swagger Gradle na seção *plugins {*} do arquivo **build.gradle**, conforme mostrado abaixo:
```groovy
plugins {
   ...
   ...
   id 'org.hidetake.swagger.generator' version '2.19.2'
}

```

2. **Definindo a configuração do OpenAPI para geração de código**: É necessário especificar certas configurações, como os nomes dos pacotes de modelos e APIs que o CLI do OpenAPI Generator deve usar, ou a biblioteca que ele deve utilizar para gerar as interfaces REST ou objetos relacionados a data/hora. Todas essas configurações, e outras adicionais, podem ser definidas no arquivo **config.json** (localizado em `/src/main/resources/api/config.json`):

```json
{  
  "library": "spring-boot",  
  "dateLibrary": "java8",  
  "hideGenerationTimestamp": true,  
  "modelPackage": "com.packt.modern.api.model",  
  "apiPackage": "com.packt.modern.api",  
  "invokerPackage": "com.packt.modern.api",  
  "serializableModel": true,  
  "useTags": true,  
  "useGzipFeature" : true,  
  "hateoas": true,  
  "unhandledException": true,  
  "useSpringBoot3": true,  
  "useSwaggerUI": true,  
  "importMappings": {  
"ResourceSupport":"org.springframework.hateoas.RepresentationModel",  
    "Link": "org.springframework.hateoas.Link"  
  }  
}
```

Essa configuração define o **spring-boot** como a biblioteca - ou seja, o Swagger Codegen gerará classes alinhadas com o Spring Boot. Note que a propriedade useSpringBoot3 está definida como true para garantir que as classes geradas estejam alinhadas com o **Spring Boot 3**.

Todas as outras propriedades são autoexplicativas, exceto **importMappings**. Essa propriedade contém o mapeamento de um tipo definido em um arquivo YAML para um tipo em **Java** ou para um tipo existente em uma biblioteca externa. Assim, quando o código é gerado para o objeto **importMappings**, ele utiliza a classe mapeada no código gerado.

Por exemplo, se utilizarmos **Link** em algum dos modelos, os modelos gerados usarão a classe mapeada **org.springframework.hateoas.Link** em vez do modelo definido no arquivo YAML.

A propriedade de configuração **hateoas** permite usar a biblioteca **Spring HATEOAS** e adicionar links HATEOAS.

Você pode encontrar mais informações sobre a configuração em:  
[https://github.com/swagger-api/swagger-codegen#customizing-the-generator](https://github.com/swagger-api/swagger-codegen#customizing-the-generator)

3. **Definindo o arquivo de exclusão do OpenAPI Generator**: É possível adicionar um arquivo semelhante ao **.gitignore** para ignorar certos códigos que você não deseja gerar. Adicione a seguinte linha de código ao arquivo **/src/main/resources/api/.openapi-generator-ignore**:

```
**/*Controller.java
```

Isso indica que não queremos gerar os controladores. Após adicionar essa configuração, somente as interfaces Java da API e os modelos serão gerados. Os controladores serão adicionados manualmente.

4. **Copie o arquivo OAS** do seguinte link:  
[https://github.com/PacktPublishing/Modern-API-Development-with-Spring-6-and-Spring-Boot-3/blob/main/Chapter03/src/main/resources/api/openapi.yaml](https://github.com/PacktPublishing/Modern-API-Development-with-Spring-6-and-Spring-Boot-3/blob/main/Chapter03/src/main/resources/api/openapi.yaml)

Cole-o no diretório:  
`/src/main/resources/api`

5. **Definindo uma tarefa `swaggerSources` no arquivo Gradle**: Agora, vamos adicionar a lógica à tarefa `swaggerSources` no arquivo **build.gradle**:

```groovy
swaggerSources {
    def typeMappings = 'URI=URI'
    def importMappings = 'URI=java.net.URI'
    eStore {
        def apiYaml = "${rootDir}/src/main/resources/api/openapi.yaml"
        def configJson = "${rootDir}/src/main/resources/api/config.json"
        inputFile = file(apiYaml)
        def ignoreFile = file("${rootDir}/src/main/resources/api/.openapi-generator-ignore")
        code {
            language = 'spring'
            configFile = file(configJson)
            rawOptions = ['--ignore-file-override', ignoreFile, '--type-mappings', typeMappings, '--import-mappings', importMappings] as List<String>
            components = [models: true, apis: true, supportingFiles: 'ApiUtil.java']
            dependsOn validation
        }
    }
}
```

Aqui, definimos **eStore** (o nome definido pelo usuário), que contém **inputFile**, apontando para o local do arquivo **openapi.yaml**. Depois de definir a entrada, o gerador precisa produzir a saída, que é configurada em **code**.

No bloco de código, **language** é definido como **Spring** (ele suporta várias linguagens); **configFile** aponta para o **config.json**; **rawOptions** contém um arquivo de exclusão, mapeamento de tipo e importações; e **components** contém as flags de arquivos que você deseja gerar – **models** e **interfaces API Java**. Exceto por **language**, todas as outras propriedades de configuração no bloco **code** são opcionais.

Queremos apenas gerar modelos e APIs. Você também pode gerar outros arquivos, como **clientes** ou **arquivos de teste**. **ApiUtil.java** é necessário na interface API Java gerada, caso contrário, ocorrerá um erro de compilação durante a construção. Por isso, ele é adicionado em **components**.

6. **Adicionando `swaggerSources` como dependência da tarefa `compileJava`**: Agora, precisamos adicionar **swaggerSources** como uma tarefa dependente da tarefa **compileJava**.

Este código aponta para o bloco **code** definido em **eStore**:

```groovy
compileJava.dependsOn swaggerSources.eStore.code
```

Além disso, você precisa adicionar a tarefa **generateSwaggerCode** como dependência da tarefa **processResources**:

```groovy
processResources {
    dependsOn(generateSwaggerCode)
}
```

Se você não definir essa dependência, pode receber um aviso em versões anteriores ao Gradle 8, mas ainda funcionará. No entanto, esse bloco de código é necessário para a versão do Gradle 8.

7. **Adicionando o código-fonte gerado aos `sourceSets` do Gradle**: Também precisamos adicionar o código-fonte e os recursos gerados aos **sourceSets**. Isso torna o código-fonte e os recursos gerados disponíveis para desenvolvimento e construção:
```groovy
sourceSets.main.java.srcDir "${swaggerSources.eStore.code.outputDir}/src/main/java"
sourceSets.main.resources.srcDir "${swaggerSources.eStore.code.outputDir}/src/main/resources"
```

O código-fonte será gerado no diretório **/build** do projeto, como em:  
`Chapter03/build/swagger-code-eStore`.

Esse bloco de código adicionará o código-fonte gerado e os recursos ao **sourceSets** do Gradle.

**Nota Importante:**  
Você gerou as interfaces API Java e os modelos usando a ferramenta Swagger Codegen. Por isso, ao carregar o projeto pela primeira vez em sua IDE, pode encontrar erros caso não tenha executado a build. Isso ocorre porque a IDE não encontrará os arquivos Java gerados (modelos e interfaces API Java). Para resolver, você pode executar o comando:

```bash
./gradlew clean build
```

**8. Construindo o projeto para gerar, compilar e construir o código:**  
O último passo é executar a build. Certifique-se de que há um código Java executável no caminho de build. A versão do Java deve corresponder à versão definida na propriedade **build.gradle** (exemplo: `sourceCompatibility = '17'`) ou nas configurações da IDE:

```bash
./gradlew clean build
```

alterando a versão de uso do JDK
sudo update-alternatives --config java

## Implementing the OAS code interfaces
Até agora, geramos um código que consiste nos modelos da aplicação de e-commerce e nas interfaces API em Java. Essas interfaces geradas contêm todas as anotações conforme a descrição YAML fornecida por nós. Por exemplo, no arquivo `CartApi.java`, as anotações `@RequestMapping`, `@PathVariable` e `@RequestBody` contêm, respectivamente, o caminho do endpoint (/api/v1/carts/{customerId}/items), o valor da variável de caminho (como `{customerId}` no caminho) e o #payload da requisição (como `Item`). 

Em uma requisição HTTP, o payload é o corpo da mensagem, ou seja, os dados que estão sendo enviados para um servidor. É como se fosse o "presente" que estamos enviando dentro de uma caixa. No contexto de APIs REST, o payload geralmente <span style="background:#affad1">contém os dados necessários</span> para realizar uma determinada ação, como criar um novo recurso, atualizar um existente ou buscar informações.

**Dados enviados no payload da requisição (anotação @RequestBody):**
- Objeto do tipo *Item*:
- Propriedade *id*: identificador único do item *SKU*
- Propriedade *quantity*: quantidade do item a ser adicionado ao carrinho
- Propriedade *unitPrice*: (opcional) preço unitário do item


A interface *CartApi* define endpoints

Da mesma forma, os modelos gerados contêm todo o mapeamento necessário para dar suporte aos tipos de conteúdo JSON e XML.

O Swagger Codegen escreve o código Spring para nós. **Nós apenas precisamos implementar a interface e escrever a lógica de negócios dentro dela**. O Swagger Codegen gera as interfaces API em Java para cada uma das tags fornecidas. Por exemplo, ele gera as interfaces Java `CartApi` e `PaymentApi` para as tags de carrinho e pagamento, respectivamente. Todos os caminhos são agrupados em uma única interface Java com base na tag fornecida. Por exemplo, todas as APIs com a tag `cart` serão agrupadas em uma única interface Java, `CartApi.java`.

Agora, precisamos apenas criar uma classe para cada uma das interfaces e implementá-las. Vamos criar o arquivo `CartController.java` no pacote `com.packt.modern.api.controllers` e implementar a interface `CartApi`.

[[CartControllers.java]]

Aqui, implementamos apenas os dois métodos para fins de demonstração. Implementaremos a lógica de negócios real no próximo capítulo.

Para adicionar um item (requisição #POST *api/v1/carts/{customerId}items*), apenas registramos o #payload da requisição recebida e o ID do cliente dentro do método *addCartItemsByCustomerId*.

Outro método, *getCartByCustomer Id*, simplesmente lança uma exceção. Isso nos permitirá demonstrar o *Global Exception Handler* na próxima seção.





---
**Algumas annotations importantes**
- @RestController em Spring indica que uma classe Java é um controlador RESTful. Isso significa que essa classe é responsável por:
- **Receber requisições HTTP:** a partir de cliente como navegadores web ou outras aplicações;
- **Processar essas requisições:** executando o código Java correspondente aos endpoints definidos na classe.
- **Gerar respostas HTTP:** enviando dados de volta ao cliente, como #JSON ou XML.
- Em outras palavras, *@RestController* transforma uma classe Java em um ponto de entrada para a sua aplicação web, permitindo que ela se comunique com o mundo externo através de requisições HTTP.

---

**Entendo o que a classe faz**
1. **Anotação** *@RequestController*
```java
@RestController
public class CartControllers implements CartApi 
```
- **@RequestController:** diz ao Spring que esta classe é um controlador REST. Isso significa ela pode lidar com requisições HTTP (como #GET, #POST etc).
- **implements CartApi**: a classe está implementando uma interface chamada **CartApi**. Isso significa que ela precisa fornecer as definições dos métodos declarados nessa interface.

2. **Logger**
```java
private static final Logger log = LoggerFactory.getLogger(CartControllers.class);
```
- **Logger:** serve para registrar mensagens no console. Essas mensagens ajudam a entender o que o código está fazendo enquanto roda. 

3. **Método *addCartItemsByCustomerId***
```java
@Override
public ResponseEntity<List<Item>> addCartItemsByCustomerId(String customerId, @Valid Item item) {
	log.infor("Request for customer ID: {}\nItem: {}", customerId, item);
	return ok(Collections.EMPTY_LIST);
}
```
**Parâmetros:**
- **String customerId**: recebe o ID do cliente;
- **@Valid Item item:** recebe um item, que será validado automaticamente. 

**Registro no log:**
```java
log.info("Request for customer ID: {}\nItem: {}", customerId, item);
```
- Registra o ID do cliente e os detalhes no console.
**Resposta**
```java
return ok(Collection.EMPTY_LIST);
```
- Retorna uma resposta HTTP com código 200 OK e uma lista vazia *Collections.EMPTY_LIST*. A lista vazia é uma implementação padrão ou temporária #placeholder

**4. Método `getCartByCustomerId`**
```java
	@Override
public ResponseEntity<List<Cart>> getCartByCustomerId(String customerId) {
    throw new RuntimeException("Manual Exception thrown");
}

```
- **Parâmetro**:
    - `String customerId`: Recebe o ID do cliente.
- **Lógica**:
```java
throw new RuntimeException("Manual Exception thrown");
```
    
Este método lança manualmente uma exceção (erro) com a mensagem "Manual Exception thrown". Isso significa que, se alguém chamar esse método, o sistema vai retornar um erro.

**5. Resumo Geral**
Essa classe é um **controlador REST** que implementa a interface `CartApi`. Ela faz o seguinte:

1. **`addCartItemsByCustomerId`**:
    
    - Recebe o ID de um cliente e um item.
    - Registra a solicitação no log.
    - Retorna uma lista vazia como resposta.
2. **`getCartByCustomerId`**:
    - Recebe o ID de um cliente.
    - Lança uma exceção de forma proposital (não está implementado ainda).

## Adding a Global Exception Handler
Temos múltiplos controladores que consistem em múltiplos métodos. Cada método pode ter *checked exceptions* ou lançar *runtime exceptions*. Devemos ter um local centralizado para lidar com todos esses erros para melhorar a manutenção, modularidade e código limpo. 

O Spring fornece um recurso #AOP ( #Aspect-Oriented-Programming é uma abordagem de programação que permite separar *preocupações transversais* da lógica principal do aplicativo. Essas *preocupações transversais* são funcionalidades que afetam várias partes do sistema, como tratamento de erros, **loggin**, **segurança** ou **monitoramento de desempenho**.

![[Pasted image 20250124183108.png]]

As *classes interceptadoras*, *utilitárias* e *auxiliadoras* podem estar relacionadas ao conceito de Aspect-Oriented Programming AOP e às **preocupações transversais** no desenvolvimento de software. 

A AOP é uma abordagem de programação que permite separar **preocupações transversais** do restante da lógica de negócio da aplicação. 

As preocupações transversais *cross-cutting concerns* são funcionalidades que afetam várias partes de uma aplicação, como:
- #Log (registro de evento);
- #controle-de-transações;
- #validação
- #segurança 
- #métricas e #melhoramento

1. #Interceptadores: são um exemplo clássico de AOP. Eles podem ser utilizados para executar código antes, durante ou após a execução de métodos *em diferentes camadas* de nossa aplicação.
Por exemplo, podemos usar interceptadores para registrar logs antes de um método ser executado, medir o tempo de execução das operações, validar entradas ou autenticar requisições.



---
Para isso. Basta escrever uma única classe anotada com *@ControllerAdvice*. Em seguida, basta adicionar *@ExceptionHandler* para cada tipo de exceção.

Este método *@ExceptionHandler* gerará mensagens de erro amigáveis ao usuário com outras informações relacionadas.

**Podemos usar a biblioteca Project Lombok se aprovado pela nossa empresa para o uso de bibliotecas de terceiros. Isso removerá a verbosidade do código para gtters, setters, construtores e assim por diante.**  
Vamos primeiro escrever a classe *Error* no pacote *exceptions*, que contém todas as informações sobre erros:
[[Error.java]]
No código de erro acima, usamos as seguintes propriedades:
- **errorCode**: código de erro da aplicação, que é diferente do código de erro HTTP;
- **message:** um resumo curto e legível para humanos sobre o problema;
- **status:** o código de status HTTP para essa ocorrência do problema, definido pelo servidor de origem.
- **url** a URL da requisição que gerou o erro
- **reqMethod** o método da requisição que gerou o erro

Depois disso, escreveremos um **enum** chamado **ErrorCode** que conterá todas as chaves de exceção, incluindo erros definidos pelo usuário e seus respectivos códigos de erro:
```java
public enum ErrorCode {
    GENERIC_ERROR("PACKT-0001", "The system is unable to complete the request. Contact system support."),
    HTTP_MEDIATYPE_NOT_SUPPORTED("PACKT-0002", "Requested media type is not supported. Please use application/json or application/xml as 'Content-Type' header value."),
    HTTP_MESSAGE_NOT_WRITABLE("PACKT-0003", "Missing 'Accept' header. Please add 'Accept' header."),
    HTTP_MEDIA_TYPE_NOT_ACCEPTABLE("PACKT-0004", "Requested 'Accept' header value is not supported. Please use application/json or application/xml as 'Accept' value."),
    JSON_PARSE_ERROR("PACKT-0005", "Make sure request payload should be a valid JSON object."),
    HTTP_MESSAGE_NOT_READABLE("PACKT-0006", "Make sure request payload should be a valid JSON or XML object according to 'Content-Type'.");

    private String errCode;
    private String errMsgKey;

    ErrorCode(final String errCode, final String errMsgKey) {
        this.errCode = errCode;
        this.errMsgKey = errMsgKey;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getErrMsgKey() {
        return errMsgKey;
    }
}
```
Adicionamos alguns enums de código de erro com seus códigos e mensagens. Também adicionamos mensagens de erro reais em vez de chaves de mensagem. Também podemos adicionar chaves de mensagem e incluir o arquivo de recursos em src/main/resources para internacionalização. 

Em seguida, criamos uma classe utilitária para centralizar a lógica de criação de objetos de erro personalizados, facilitando o gerenciamento e a reutilização de código em diferentes partes da aplicação. Ao invés de instanciar e configurar objetos da classe #Error repetidamente em vários lugares do código, a classe *ErrorUtilis* encapsula essa lógica em um único método *createError*.
Isso torna o código mais limpo e evita duplicação de lógica.
[[ErrorUtils.java]]

Finalmente nós vamos criar uma class para implementar o *Global Exception Handler:
[[RestApiErrorHandler.java]]

Marcamos a classe com *@ControllerAdvice*, o que permite que essa classe rastreie todo o processamento de requisições e respostas pelos controladores REST e nos permite tratar exceções usando *@ExceptionHandler*. 

No código anterior, tratamos duas exceções - uma genérica de erro interno do servidor e a *HttpMediaTypeNotSupportException*. O método de tratamento apenas popula o objeto Error usando *ErrorCode*, *HttpServeletRequest* e *HttpStatus*. Finalmente ele retorna o erro encapsulado dentro de um ResponseEntity com o status HTTP apropriado.

Neste código, também podemos adicionar exceções definidas pelo usuário. 

## Testing the implementation of the API
Após o código estar pronto para rodar, podemos compilar e buildar o artefato usando o seguinte comando através da pasta root do projeto:
```sh
./gradlew clean build
```

Similarmente, podemos fazer uma chamada para a API para adicionar um item ao carrinho, como mostrado:
```sh
$ curl --request POST 'http://localhost:8080/api/v1/carts/1/items' \ --header 'Content-Type: application/json' \ --header 'Accept: application/json' \ --data-raw '{ "id": "1", "quantity": 1, "unitPrice": 2.5 }' []
```

Acima, obtemos um [] array vazio como resposta, pois, na implementação, apenas retornamos a coleção vazia. Precisamos fornecer o cabeçalho **Content-Type** nesta requisição porque enviamos o payload (objeto item) junto com a solicitação. É possível alterar o **Content-type** para **application/xml** caso o payload esteja escrito em XML. 

## Resumo
Neste capítulo, optamos pela abordagem #design-first para escrever serviços web RESTful. Aprendemos como escrever uma descrição de API usando #OAS e como gerar modelos e interfaces Java da API usando a ferramenta *Swagger Codegen* (usando o plugin Gradle). Também implementamos um **Global Exception Handler** para centralizar o tratamento de todas as exceções. Uma vez que tenhamos a interface Java da API, podemos escrever nossas implementações para a lógica de negócios. Agora, sabemos como usar OAS e Swagger Codegen para escrever APIs RESTFul. 

## Question
1. What is OpenAPI and how does it help?
A OpenAPI é uma especificação para descrever APIs RESTful de forma padronizada e independente de linguagem. Ele permite que desenvolvedores definam a estrutura, endpoints, métodos HTTP, parâmetros, tipos de dados e respostas de uma API em  um único documento, geralmente escrito no formato **YAML** ou **JSON**. Ferramentas baseadas no OpenAPI podem gerar código automaticamente, como clientes da API servidores, ou até mesmo documentação interativa (ex.: Swagger UI).

2. How can you define a nested array in a model in a YAML OAS-based file?
Em um arquivo YAML baseado na especificação OAS (OpenAPI Specification), podemos definir um array aninhado em um modelo utilizando type: array e especificando os itens do array com outro **type: array**. 
- **nestedArray**: define o nome da propriedade no modelo;
- **type: array:** declara que essa propriedade é um array;
- **items:** define os elementos desse array como outro array
- **type: string**: especifica que os itens do array interno são strings. Pode ser ajustado para outros tipos, como **integer** ou até mesmo objetos. 
Exemplo de conteúdo:
```json
{
	"nestedArray": [
		["item1", "item2"]
		["item3", "item4"]
	]
}
```
Isso é útil para representar estruturas de dados hierárquicas ou matrizes bidimensionais em uma API.

---
3. What annotation do we need to implement a Global Exception Handler?
No Spring Framework, para implementar um **Global Exception Handler**, precisamos usar a anotação *@ControllerAdvice*. Essa anotação transforma a classe marcada em um componente global que pode tratar exceções em todos os controladores da aplicação.
**Etapas principais:**
1. Use @ControllerAdvice: aplica o escopo global para capturar exceções;
2. Use @ExceptionHandler: define métodos específicos para tratar tipos específicos de exceções.

**Entendendo melhor a estrutura modular para tratamento de exceções**
1. **Quando uma exceção ocorre no sistema**
Quando ocorre uma exceção em qualquer ponto da aplicação (por exemplo, um erro genérico ou uma requisição com o tipo de mídia não suportado), o **Spring** encaminha a exceção para o *@ControllerAdvice*. 
Nesse caso, a classe RestApiErrorHandler é responsável por capturar e tratar essas exceções globalmente. 

2. **Classe *RestApiErrorHandler***
Essa é a classe central de tratamento de erros, anotada com @ControllerAdvice, o que faz com que ela atue como interceptadora global de exceções para todos os controladores. Cada método marcado com *@ExceptionHandler* trata um tipo específico de exceção.

**Métodos importantes:**
1. *handleException*:
- É um método genérico que captura qualquer exceção da classe **Exception** (base de quase todas as exceções no Java).
- Ele utiliza o utilitário **ErrorUtils** para criar um objeto **Error** com as informações do erro.
- Adiciona informações como URL e método HTTP da requisição e retorno uma resposta *ResponseEntity* com código *500 (Internal Server Error)*.

2. *handleHttpMediaTypeSupportedException:*
- Captura exceções específicas do tipo *HttpMediaTypeNotSupportedException*, que ocorrem quando o cliente envia um tipo de mídia não suportado pelo servidor (ex.: tipo de **Content-type** inválido).
- Também cria um objeto **Error** via **ErrorUtils** e retorna uma resposta com o código 415 (Unsupported Media Type).

3. **Classe *Error***
Essa classe é um #POJO (Plain Old Java Object) que representa a estrutura do erro retornado para o cliente.  Contém os seguintes atributos:
- **ErrorCode:** código único do erro, definido no **ErrorCode**
- **Message:** mensagem de erro amigável (ex.:"Requested media type is not supported").
- **status:** código de status HTTP (ex.: 500, 415);
- **url:** URL da requisição onde ocorreu o erro;
- **reqMethod:** método HTTP da requisição (ex.: GET, POST).