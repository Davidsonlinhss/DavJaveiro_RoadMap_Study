Este Capítulo aborda
- Apresentação de modelo de dados no navegador;
- Processando e validando formulários de entrada;
- Escolhendo uma biblioteca de template view.

As primeiras impressões são importantes. A atratividade externa <span style="background:#d4b106">pode vender</span> uma casa muito antes do comprador entrar pela porta. A pintura vermelha de um carro chamará mais atenção do que o que está sob o capô. E a literatura está repleta de histórias de amor à primeira vista. O que está por dentro é importante, mas <span style="background:#d4b106">o que está por fora</span> **–** o que é visto primeiro **–** também é significativo. 

As aplicações que serão construídas em Spring farão todos os tipos de coisas, como processar dados, ler informações de um banco de dados e interagir com outras aplicações. Mas a primeira impressão que os usuários terão de nossa aplicação vem da interface de usuário (UI). Em muitas aplicações, essa interface de usuário é um aplicativo web apresentado em um navegador.

Neste capítulo desenvolveremos a primeira funcionalidade principal de nossa aplicação **Taco Cloud**, como a capacidade de projetar tacos personalizados. 

## 2.1 Displaying information
A nossa aplicação Taco Cloud será responsável por disponibilizar pedidos online de Tacos. O site também permitirá que os clientes personalizem os seus pedidos com diversos tipos de ingredientes. 

Logo, vamos precisar de uma página que exiba a seleção de ingredientes para que os "artistas de tacos" possam escolher. As opções de ingredientes podem mudar a qualquer momento, então elas não devem ser codificadas diretamente em uma página HTML. Em vez disso, a lista de ingredientes disponíveis deve ser buscada de um <span style="background:#d4b106">banco de dados</span> e repassada para a página para ser exibida ao cliente.

A responsabilidade de buscar e processar os dados em uma aplicação spring Web é da classe #controller. E é responsável de uma #view renderizar esses dados em HTML que será exibido no navegador.

Criaremos os seguintes componentes para dar suporte à página de criação de tacos:
- Uma classe de domínio que define as propriedades de um ingrediente de taco;
- Uma classe de controller do Spring MVC que busca as informações dos ingredientes e as repassa para a view;
- Um template view que renderiza a lista de ingredientes no navegador do usuário.

![[Capítulo 2 - Desenvolvendo aplicações web-1.png]]
**Figure 2.1 - A typical Spring MVC request flow**

O capítulo focará no framework Spring Web, deixando para o capítulo 3 qualquer aspecto relacionado ao banco de dados. Por hora, a controller será responsável por fornecer os dados dos ingredientes para a view.

A partir do capítulo 3, refaremos o controller utilizando um repositório em um banco de dados. 

Antes de escrever a classe controller e a classe view, vamos definir o tipo de domínio que representa um ingrediente. 
Definir um tipo de domínio significa criar uma classe que descreve as propriedades e comportamentos essenciais de uma entidade. Isso inclui #atributos (propriedades) e #métodos relacionados. 

### 2.1.1 Estabelecendo o domínio
Em nossa aplicação **Taco Cloud**, o domínio incluirá objetos como designs de tacos, os ingredientes que compõem esses designs, clientes e pedidos de tacos feitos pelos clientes. 
![[Capítulo 2 - Desenvolvendo aplicações web-2.png]]
**Figura 2.2 - Domínio da aplicação Taco Cloud**

Os ingredientes de tacos são objetos relativamente simples. Cada ingrediente possui um nome, bem como um tipo (tipagem), para que possa ser categorizado visualmente (proteínas, queijos, molhos, etc). Além disso, cada ingrediente tem um ID, pelo qual pode ser facilmente e de forma inequívoca ser referenciado.  

A nossa classe aparentemente não contém os métodos habituais como #getters, #setters e #hashCode, #equals... nós estamos utilizando uma biblioteca chamada #Lombok, que gera esses métodos automaticamente em tempo de compilação, tornando-os disponíveis em tempo de execução. Isso evita código #boilerplate, ou seja, repetições de código em nosso projeto. 

A annotation @Data no nível da classe é fornecida pelo Lombok e instrui a biblioteca a gerar todos esses métodos ausentes, além de um construtor que aceita todas as propriedades finais como argumentos. Com isso, ao utilizarmos o Lombok, mantemos o código da nossa classe Ingredient enxuta e organizada. 

A mágica do #Lombok <span style="background:#d4b106">é aplicada no tempo de compilação</span>, portanto, não é necessário que ele esteja disponível em tempo de execução (<span style="background:#ff4d4f">quando o programa está sendo executado</span>). Excluí-lo dessa forma mantém o Lombok fora do arquivo JAR ou WAR resultante, visto que os métodos necessários já foram inseridos... 

A dependência do **Lombok** fornece as anotações do **Lombok** (como `@Data`) durante o tempo de desenvolvimento e gera os métodos automaticamente no tempo de compilação.

Os ingredientes são os blocos essenciais de um taco. Para capturar como esses ingredientes são reunidos, vamos definir a classe de domínio Taco. 

Agora que definimos as classes Ingredient e Taco, precisamos de mais uma classe de domínio que defina como os clientes especificam os tacos que desejam pedir, juntamente com as informações de pagamento e entrega. Essa é a função da classe **TacoOrder**, que é mostrada a seguir. 

Além de ter mais propriedades do que as classes Ingredient ou Taco, não há nada de particularmente novo a ser discutido sobre a classe **TacoOrder**. Ela é uma classe de domínio simples com nove propriedades: 
1. Cinco propriedades relacionadas a entrega;
2. Três para informações de pagamento;
3. Uma lista de objeto Taco que compõem o pedido.

### 2.1.2 Criando uma classe controller
Os **controladores** são os principais componentes no framework MVC do Spring. Sua principal função é lidar com <span style="background:#d4b106">requisições HTTP</span> e, ou bem, repassar a requisição para uma view para gerar o HTML (exibido no navegador), ou escrever os dados diretamente no corpo de uma resposta (estilo RESTful). 

Neste capítulo, estamos focando nos tipos de controladores que utilizam #views para gerar conteúdo para navegadores web. Quando chegarmos ao capítulo 7, veremos como escrever controladores que lidam com requisições em uma **API REST**, os dados são enviados diretamente para o cliente, normalmente no formato JSON ou XML.   

Em uma aplicação **RESTful**, o servidor não gera uma página HTML como em um aplicativo MVC tradicional, mas em vez disso, retorna dados (geralmente de objetos ou recursos) em um formato estruturado que o cliente (geralmente um frontend ou outro serviço) pode processar. Isso é útil para aplicativos que precisam de comunicação entre sistemas (por exemplo, entre um servidor e um aplicativo mobile, ou entre microserviços). Podemos utilizar #Angular para consumir os dados estruturados. 

Para a aplicação Taco Cloud, vamos precisar de um controlador simples que fará o seguinte:
- Lidar com requisições HTTP GET onde o caminho da requisição será /design;
- Construir uma lista de ingredientes;
- Passar a requisição e os dados dos ingredientes para um template de view para serem rendereziados como HTML e enviados para o navegador que fez a requisição;

A primeira coisa a ser observado sobre a classe é o conjunto de anotações aplicadas no nível da classe. A primeira, @Slf4j, é uma anotação fornecida pelo Lombok que, no momento da compilação, irá gerar automaticamente uma propriedade estática #Logger do SLF4J (Simples Loggin Facade for Java) na classe. Esta modesta anotação tem  o mesmo efeito que se adicionássemos as seguintes linhas dentro da classeL
```java
private static final org.slf4j.Logger log =
  org.slf4j.LoggerFactory.getLogger(DesignTacoController.class);

```
Usaremos esse registrador mais tarde. Ele cria um #logger para a classe DesignTacoController. A biblioteca permite que registremos mensagem de log como informações, avisos, erros durante a execução da aplicação. 
**Exemplo de uso do logger**
Uma vez que o logger é configurado, podemos usá-lo em qualquer parte da classe para registrar mensagens, como:
```java
log.info("Iniciando a criação do taco");
log.error("Erro ao criar taco", exception);
```
Essas mensagens de log ajudam os desenvolvedores a monitorar o comportamento da aplicação, diagnosticar problemas e entender o fluxo de execução. 

A próxima anotação aplicada ao DesignTacoController é @Controller. Essa anotação serve para identificar esta classe como um controlador e marcá-la como candidata para o #component scanning, com isso o Spring passa a gerenciá-la e cria automaticamente uma instância do DesignTacoController como um #bean no contexto da aplicação Spring. 

A classe também é anotada com @RequestMapping. A anotação @RequestMapping, quando aplicada no nível da classe, especifica o tipo de requisições que este controller lida. Nesse caso, ela especifica que o DesignTaco lidará com requisições cujo caminho começa com /Design;

A última annotation a nível de classe é a @SessionAttributes("tacoOrder"). Isso indica que o objeto TacoOrder, que será colocado no modelo um pouco mais adianta na classe, deve ser mantido na sessão. Isso é importante, pois a criação de um taco também é o primeiro passo na criação de um pedido, e o pedido que criamos precisará ser mantido na sessão para que possar ser preservado ao longo de múltiplas requisições. 

**<span style="background:#d4b106">Tratando uma requisição GET</span>**

A especificação de @RequestMapping no nível da classe é refinada com a annotation @GetMapping que adorno o método showDesignForm(). @GetMapping, combinada com @RequestMapping no nível da classe, especifica que, quando uma requisição HTTP GET for recebidaa para /design, o Spring MVC chamará o método showDesignForm() para tratar a requisição. 

@GetMapping é apenas um membro de uma família de anotações de mapeamento de requisição. A tabela 2.1 lista todas as anotações de mapeamento de requisição disponíveis no Spring MVC.

#RequestMapping - tratamento de solicitações de uso geral;
#GetMapping - Trata de solicitações HTTP do tipo #Get;
#PostMapping - trata as requisições HTTP do tipo #Post;
#PutMapping - trata as requisições HTTP do tipo #Put;
#DeleteMapping - trata requisições do tipo #Delete ;
#PatchMapping - trata requisições do tipo #PATCH 

Quando showDesignForm() trata uma requisição GET para /design, ela na verdade não faz muita coisa. O principal é que ela retorna um valor String de "design", que é o nome lógico da view que será usada para renderizar o modelo no navegador. Mas, antes de fazer isso, ela também popula o **Model** fornecido com um objeto **Taco** vazio sob uma chave chamada "design". Isso permitirá que o formulário tenha uma tela em branco na qual criar uma obra-prima de taco.

Após **addIngredientsToModel()**, há dois métodos adicionais que também são anotados com **@ModelAttribute**. Esses métodos são muito mais simples e criam apenas um novo objeto **TacoOrder** e um objeto **Taco** para colocar no **model**. O objeto **TacoOrder**, mencionado anteriormente na anotação **@SessionAttributes**, mantém o estado do pedido que está sendo construído à medida que o usuário cria tacos ao longo de múltiplas requisições. O objeto **Taco** é colocado no **model** para que a _view_ renderizada em resposta à requisição GET para **/design** tenha um objeto não-nulo para exibir.

A nossa classe está começando a ter uma forma. 

### 2.1.3 Projetando a View

Após o controlador terminar o trabalho, é hora da visualização começar a funcionar. O Spring oferece várias opções para definir visualizações, incluindo JavaServer Pages (JSP), Thymleaf, FreeMarker, Mustache e templates baseados em Groovy. Por enquanto, vamos usar o Thymeleaf. 

Já adicionamos o Thymeleaf como dependência no capítulo 1. Em tempo de execução, a autoconfiguração do Spring Boot vê o que o Thymeleaf está no classpath e automaticamente cria os beans que dão suporte às visualizações do Thymeleaf para o Spring MVC.

Bibliotecas de views, como o Thymeleaf, são projetadas para serem desaclopadas de qualquer framework web específico. Como tal, elas não têm conhecimento da abstração do modelo do Spring e não podem trabalhar com os dados que o controlador coloca no Model. No entanto, elas podem trabalhar com os atributos de requisição do servlet. <span style="background:#d4b106">Portanto, antes de o Spring passar a requisição para uma visualização, ele copia os dados do modelo para os atributos de requisição, que o Thymeleaf e outras opções de templates têm acesso direto</span>.

Os templates do Thymeleaf são apenas HTML com alguns atributos adicionais nos elementos que orientam o template na renderização dos dados da requisição. Por exemplo, se houvesse um atributo de requisição com a chave "message" e quisesse que ele fosse renderizado em uma tag HTML p pelo thymeleaf, escreveriamos da seguinte forma:
```html
<p th:text="${message}">placeholder message</p>
```
Neste exemplo, o `th:text="${message}"` é um atributo do Thymeleaf que indica que o conteúdo da tag `<p>` deve ser substituído pelo valor do atributo "message" da requisição. O `${message}` é uma expressão do Thymeleaf que refere-se ao valor do atributo de requisição chamado "message".

Quando o template é renderizado em HTML, o corpo do elemento `<p>` será substituído pelo valor do atributo da requisição cujo chave seja "message". O atributo `th:text` é um atributo do namespace Thymeleaf que realiza essa substituição. O operador `${}` instrui o Thymeleaf a usar o valor de um atributo de requisição (no caso, "message").

Thymeleaf também oferece outro atributo, `th:each`, que <span style="background:#d4b106">itera sobre uma coleção de elementos</span>, renderizando o HTML uma vez para cada item na coleção. Esse atributo será útil à medida que você for criar sua visualização para listar os ingredientes do taco a partir do modelo. Por exemplo, para renderizar apenas a lista de ingredientes do tipo "wrap", você pode usar o seguinte trecho de HTML:
```html
<h3>Designate your wrap:</h3>
<div th:each="ingredient : ${wrap}">
	<input th:field="*{ingredients}" type="checkbox"
		th:value="${ingredient.id}"/>
	<span th:text="${ingredient.name}">INGREDIENT</span><br/>
</div>
```

Aqui, o atributo `th:each` é usado na tag `<div>` para repetir a renderização da `<div>` uma vez para cada item na coleção encontrada no atributo de requisição `wrap`. Em cada iteração, o item da coleção `ingredient` é vinculado a uma variável do Thymeleaf chamada `ingredient`.

Dentro do elemento `<div>` estão presentes um elemento `<input>` do tipo checkbox e um elemento `<span>` para fornecer um rótulo ao checkbox. O checkbox utiliza o atributo `th:value` do Thymeleaf para definir o valor do atributo `value` do elemento `<input>` renderizado, com base no valor encontrado na propriedade `id` do objeto `ingredient`.

O atributo `th:field` define o atributo `name` do elemento `<input>` e é usado para lembrar se o checkbox está marcado ou não. Quando adicionarmos validação mais tarde, isso garantirá que o estado do checkbox seja mantido caso o formulário precise ser redistribuído após um erro de validação.

O elemento `<span>` usa o atributo `th:text` <span style="background:#d4b106">para substituir o texto</span> "INGREDIENT" pelo valor da propriedade `name` do objeto `ingredient`.

Quando renderizado com dados reais do modelo, uma iteração desse loop `<div>` pode ficar assim:
```html
<div>
  <input name="ingredients" type="checkbox" value="FLTO" />
  <span>Flour Tortilla</span><br/>
</div>

```
No final das contas, o trecho de Thymeleaf apresentado anteriormente é apenas parte de um formulário HTML maior, através do qual os usuários "artistas de tacos" poderão enviar suas deliciosas criações. O template completo do Thymeleaf, incluindo todos os tipos de ingredientes e o formulário, é mostrado na listagem a seguir.

## 2.2 Processando formulários de envio
Se observamos a tag form na view, veremos que o atributo #method está definido como POST. Além disso, o form não declara um atributo action. Isso significa que, ao enviar o formulário, o navegador reunirá todos os dados nele contidos e os enviará ao servidor em uma solicitação HTTP POST para o mesmo caminho que exibiu o formulário através de uma solicitação GET - o caminho /design.

Portanto, é necessário um método de manipulação no controlador que receba essa solicitação POST. Precisamos escrever um novo método de manipulação no DesignTacoController para tratar a solicitação POST no caminho /design.

Nós utilizamos até agora a notação @GetMapping para especificar que o método showDesignForm() deve lidar com requisições HTTP do tipo GET para o caminho /design. Assim como o @GetMapping é usado para tratar requisições GET, podemos usar o @PostMapping para tratar requisições **POST**.

Para lidar com os envios de tacos, vamos adicionar o método processTaco().

Ao utilizarmos a annotation @PostMapping, ela será coordenada com o @RequestMapping da classe para indicar o método processTaco() para lidar com as requisições POST para o caminho informado, /design. A partir daí, o método processTaco() pode fazer o que for necessário com o objeto Taco.

Quando o formulário é enviado, os campos no formulário são vinculados às propriedades de um objeto TACO (cuja classe é mostrada na próxima listagem) que é passado como parâmetro para o método processTaco(). A partir daí, o método processTaco() pode fazer o que for necessário com o objeto Taco. Neste caso, ele adiciona o Taco ao objeto TacoOrder, que é passado como parâmetro para o método. 

Resumindo
- Recebimento dos Dados do Formulário: quando o <span style="background:#d4b106">formulário é enviado</span> para o servidor, a requisição HTTP é do tipo POST. 
- A anotação `@PostMapping` informa ao Spring que esse método deve lidar com requisições HTTP do tipo **POST**.
- O Spring automaticamente vincula os dados enviados no formulário a um objeto **Taco**. Isso acontece porque o Spring possui um mecanismo de #binding que converte os dados do formulário (campo por campo) em um objeto Java. O Spring usa o nome dos campos do formulário e os mapeia para as propriedades do objeto Taco. 
- Se o formulário tiver campos como name, ingredients, esses dados serão automaticamente preenchidos no objeto Taco que é passado para o método. 
- A anotação `@ModelAttribute` indica que o objeto `TacoOrder` será retirado do modelo da aplicação. O Spring vai procurar por esse objeto no contexto da sessão ou no modelo que foi configurado anteriormente (provavelmente através de outro método que preparou o modelo, como o `order()` que é mencionado na explicação anterior)

O que o método faz, de fato?
- tacoOrder.addTaco(taco), o taco recebido do formulário é adicionado ao pedido de tacos (TacoOrder). Isso indica que o pedido está sendo montado com o taco enviado pelo form.
- log.info("Processing taco: {}", taco)> o método faz um log da operação, registrando o taco que está sendo processado.
- return "redirect:/orders/current": após processar o taco e adicioná-lo ao pedido, o método retorna um redirecionamento para a URL /orders/current. O navegador será instruído a fazer uma requisição para essa URL, para exibirmos status do pedido. 

Mas, há um erro até o momento. Estamos passando valores textuais (por exemplo, String) nas caixas de seleções e o objeto Taco representa uma lista de ingredientes como List < Ingredient >, logo há uma incompatibilidade!  
Como podemos vincular uma lista textual a uma lista de objetos do tipo Ingredient?

Nesse caso, um conversor entra em cena. Um conversor é qualquer classe que implemente a interface Converter do Spring e implemente o seu método #convert, ele pega um valor e o converte para outro. Para converter uma String em um Ingredient, usaremos o IngredientByIdConverter:

Como ainda não possuímos um banco de dados ao qual possamos obter objetos Ingredient, o construtor do IngredientByIdConverter cria um Map cujas chaves são String representando o ID do ingredient e cujos valores são objetos Ingredient. O método convert simplesmente recebe uma String que é o ID do ingredient e a usa para procurar o ingredient no mapa. 

Usamos a annotation @Component para torná-lo descoberto como um #bean no spring context application. A autoconfiguração do Spring Boot irá descobrir este e qualquer outro bean do tipo Converter e registrá-la automaticamente no Spring MVC, para serem usados quando a conversão de parâmetros da requisição para propriedades vinculadas for necessária.

**Mapeamento de Ingredientes**
A classe mantém um map (ingredientMap) que associa:
- Chave: ID do ingredient (FLTO, COTO, etc.)
- Valor: Objeto Ingredient correspondente.

Esse mapa é preenchido no construtor da classe. Cada entrada do mapa é criada com um ID único, nome descritivo e tipo.


Poderíamos implementar uma melhoria, caso um ID não encontrado... o método convert() retornará null... 

Não implementaremos essa melhoria, pois faremos a persistência de dados no capítulo 3... faremos a lógica de persistência que salvará o objeto Taco enviado em um banco de dados.

O valor retornado por processTaco() é prefixado com "redirect", o que indica que esta é uma view de redirecionamento. Após a conclusão do método processTaco(), o navegador do usuário deve ser redirecionado para o caminho relativo /orders/current...

Vamos criar um controller que lide com a requisição para /orders/current...

1. Exibição dos Tacos:
A página começa listando os tacos adicionados ao pedido. Para isso, utiliza o th:each do Thymeleaf para iterar sobre os tacos do pedido e exibi-los na tela.

2. Diferença no form:
O formulário tem uma função (action) definida como /orders. Isso significa que, ao ser enviado, ele fará uma requisição HTTP POST para o caminho /orders. 

3. Ação necessária no controlador
Para processar o envio do formulário, será necessário adicionar um método na classe OrderController que trate requisições POST para /orders. 

4. Simplicidade inicial:
Como ainda não há um banco de dados para salvar os pedidos (isso será feito no próximo capítulo).
Resumindo: o formulário envia os dados para `/orders`, e o controlador precisará ter um método para lidar com essa requisição POST.

## 2.3 Validating form input

Ao criar um novo Taco, o que acontece se o usuário não selecionar nenhum ingrediente ou deixar de especificar um nome para sua criação? 

O nível atual de nossa aplicação não impede que o usuário crie um taco sem ingredientes, com um endereço de entrega vazio, ou até mesmo insira qualquer dado na área do cartão de crédito. Isso está acontecendo pelo fato de não termos implementado <span style="background:#d4b106">as regras de validação para esses campos</span>. 

Uma maneira de realizar a validação do formulário seria incluir diversos blocos de if/then nos métodos processTaco() e processOrder(), verificando cada campo para garantir que cumpra as regras de validação adequadas. No entanto, essa abordagem seria trabalhosa, <span style="background:#d4b106">difícil de ler e complicada para</span> #depurar. 

Felizmente, o Spring oferece suporte à JavaBean Validation API (também conhecida como JSR 303). Isso facilita a declaração de regras de validação, <span style="background:#d4b106">em vez de escrever explicitamente a lógica de validação no código da aplicação</span>. 

Para aplicar a validação no Spring MVC, precisamos:
- Adicionar o #starter de validação do Spring ao build;
- Declarar as regras de validação na classe que será validada, especificamente na classe Taco;
- Especificar que a validação deve ser realizada nos métodos do controller que exigem validação, como o método processTaco() do DesignTacoController e o método processOrder() do OrderController.
- Modificar as <span style="background:#d4b106">views dos formulários</span> para exibir os erros de validação;

A API de validação oferece várias anotações que podem ser aplicadas às propriedades de objetos de domínio para declarar regras de validação. A implementação do Hibernate da API de Validação adiciona ainda mais annotations de validação. Ambas podem ser incluídas em um projeto ao adicionar o starter de validação do Spring ao build.

Após termos instalado, vamos aplicar algumas annotations para validar o envio do Taco e do TacoOrder.

### 2.3.1 Declaring validation rules
Para a classe Taco, devemos garantir que a propriedade name não esteja vazia ou nula e que a lista de ingredientes selecionados tenha pelo menos um item. A seguinte listagem mostra uma classe Taco atualizada que usa @NotNull e @Size para declarar essas regras de validação.

Além de dizermos que os campos não podem ser nulos @NotNull, definimos que o campo deve conter ao menos cinco caracteres de comprimento;

Quando se trata de declarar validação em pedidos de tacos enviados, devemos aplicar annotations na classe TacoOrder. Para as propriedades do endereço, queremos garantir que o usuário não deixe nenhum dos campos em branco. Para isso, usaremos a annotation @NotBlank.

<span style="background:#d4b106">Validando Campos de Pagamento</span>
**<span style="background:#ff4d4f">ccNumber</span>**:  precisamos garantir que o número de cartão de crédito não esteja em branco @NotBlank e que ela contenha um valor que possa ser um número de cartão de crédito válido. 

<span style="background:#ff4d4f">ccExpiration</span>: deve seguir o formato MM/AA;

<span style="background:#ff4d4f">ccVV</span>: precisa ser um número de três dígitos. 

Vamos utilizar uma combinação de anotação para validação dos campos da API de Validação do JavaBean e mais algumas do Hibernate Validator. 


Para validar o número do cartão de crédito, usamos o algoritmo de Luhn https://www.creditcardvalidator.org/articles/luhn-algorithm.  Isso previne erros de dados fornecidos pelo usuário. 

Infelizmente não existe uma anotação pronta para validar o formato MM/YY da propriedade ccExpiration. Usamos uma annotation @Pattern, fornecendo uma expressão regular que garante que o valor da propriedade siga o formato desejado. A sintaxe de expressões regulares é <span style="background:#d4b106">uma arte obscura e certamente está fora do escopo do livro</span>.

Todas as annotations e validações incluem um atributo #message que define uma mensagem que será exibida ao usuário caso a informação inserida não atenda aos requisitos das regras de validação declaradas.

### 2.3.2 Performing validation at form binding
Após declaramos as validações nas classes desejadas, precisamos revisar cada um dos controladores, especificando que <span style="background:#d4b106">a validação deve ser realizada quando os formulários forem enviados para os respectivos métodos de tratamento.</span> 

Para validar um Taco enviado, precisamos utilizar a annotation #Valid da JavaBean Validation API ao argumento Taco do método processTaco() do controller.

A anotação #Valid instrui o Spring MVC a realizar a validação no objeto Taco submetido, após ele ser vinculado aos dados do formulário enviado e antes que o método processTaco() seja chamado. Se houver erros de validação, os detalhes desses erros serão capturados em um objeto #Errors, que é passado para o método processTaco(). Se houver erros, o método conclui sua execução sem processar o Taco e retorna o nome da View "design", para que o formulário seja redistribuído e o usuário possa corrigir os erros.

Mas como o usuário saberá quais erros precisam ser corrigidos? A menos que destaquemos os erros no formulário, o usuário ficará sem saber como enviar o formulário com sucesso.

### 2.3.3 Exibindo as validações de erros
O #Thymeleaf oferece acesso conveniente ao objeto Erros por meio da propriedade #fields e do atributo th:errors. Por exemplo, para exibir erros de validação no campo do número de cartão de crédito, podemos adicionar o elemento span que usa essas referências de erro ao template do formulário de pedido:

## 2.4 Working with view controllers
Até o momento, escrevemos três controllers para a nossa aplicação. Embora cada controlador tenha um propósito distinto na funcionalidade da aplicação, todos eles seguem, em grande parte, <span style="background:#d4b106">o seguinte modelo de programação</span>:
- Todos eles são anotados com @Controller para indicar que são classes controladores que devem ser automaticamente descobertas pelo mecanismo de varredura de componentes do Spring e instanciadas como beans no contexto da aplicação Spring.
- Todos, excento o HomeController, estão anotados com @RequestMapping no nível da classe para definir um padrão básico de requisição que o controlador irá manipular. Ela é usada para mapear solicitações HTTP para métodos específicos em um controller.  Ou seja, usamos para dizer qual URL e qual método HTTP (GET, POST, etc.) será tratado por um método ou controlador específico. 
- Todos possuem um ou mais métodos anotados com #GetMapping ou #PostMapping, que fornecem detalhes sobre quais métodos devem lidar com quais tipos de requisições;

A maioria dos controllers terão esse padrão. Porém, existe outra maneira de configurar um controlador que não preenche modelos ou processa dados, no caso uma viewcontroller.

A classe implementa a interface WebMVcConfigurer. Essa interface define vários métodos para configurar o Spring MVC. Ela fornece implementação padrão para todos os métodos, de modo que só precisamos sobrescrever os métodos que for utilizar.  addViewControllers()

O método `addViewControllers()` recebe um objeto do tipo `ViewControllerRegistry`, que você pode usar para registrar um ou mais controladores de visualização. Aqui, você chama o método `addViewController()` no registro, passando “/”, que é o caminho para o qual o controlador de visualização irá lidar com as requisições GET. Esse método retorna um objeto `ViewControllerRegistration`, no qual você chama imediatamente `setViewName()` para especificar `home` como a visualização para a qual uma requisição ao “/” deve ser encaminhada.

E, assim, você conseguiu substituir o `HomeController` por algumas linhas em uma classe de configuração. Agora você pode deletar o `HomeController`, e a aplicação deve continuar funcionando como antes. A única outra mudança necessária é revisar o `HomeControllerTest` do capítulo 1, removendo a referência ao `HomeController` na anotação `@WebMvcTest`, para que a classe de teste compile sem erros.

Com relação aos controladores de views, existem outros modelos de template... 

## 2.5 Choosing a view template library
A escolha de uma biblioteca de templates views é uma questão de gosto pessoal. 
Quando adicionamos uma biblioteca de template em nosso diretório, o Spring Boot detecta a biblioteca de templates escolhida e configura automaticamente os componentes necessários para que ela sirva as views para os nossos controladores Spring MVC.

### 2.5.1 Caching templates
Por padrão, os templates são analisados apenas uma vez, quando são utilizados pela primeira vez, e os resultados dessa análise são armazenados em cache para uso subsequente. Isso evita análise redundante dos templates, melhorando o desempenho. 

Resumo:

- O Spring oferece um poderoso framework web chamado Spring MVC, que pode ser usado para desenvolver a interface web de uma aplicação Spring.
    
- O Spring MVC é baseado em anotações, permitindo a declaração de métodos de manipulação de solicitações com anotações como @RequestMapping, @GetMapping e @PostMapping.
    
- A maioria dos métodos de manipulação de solicitações termina retornando o nome lógico de uma visualização, como um template Thymeleaf, para o qual a solicitação (junto com qualquer dado do modelo) é encaminhada.
    
- O Spring MVC oferece suporte à validação por meio da JavaBean Validation API e implementações da Validation API, como o Hibernate Validator.
    
- Controladores de visualização podem ser registrados com addViewController em uma classe WebMvcConfigurer para manipular solicitações HTTP GET para as quais não são necessários dados de modelo ou processamento.
    
- Além do Thymeleaf, o Spring suporta várias opções de visualização, incluindo FreeMarker, templates Groovy e Mustache.