Aprender algo novo pode parecer um pouco como cair na lateral de um navio. Tudo está se movendo e você mal consegue manter a cabeça acima da água. Você está apenas começando a sentir que entende como algo funciona e, então, um novo conhecimento surge do nada e todo o seu mundo fica de cabeça para baixo novamente. Ter algo sólido a que se agarrar lhe dá a chance de olhar em volta e descobrir para onde está indo. Isso pode fazer toda a diferença do mundo quando se está aprendendo algo novo. 

Neste capítulo, quero lhe dar esse pedaço de terra para se firmar. Como em quase todas as especialidades, o teste e o desenvolvimento de APIs têm sua própria terminologia. Há muito termos que têm significados especializados quando se está trabalhando com APIs. 

Este capítulo abordará os seguintes tópicos principais: 
<span style="background:#ff4d4f">O que é uma API; </span>
<span style="background:#ff4d4f">Tipos de chamadas de API; </span>
<span style="background:#ff4d4f">Instalando o Postman;</span>
<span style="background:#ff4d4f">A estrutura de uma solicitação de API;</span>
<span style="background:#ff4d4f">Considerações sobre o teste de API;</span>
<span style="background:#ff4d4f">Diferentes tipos de APIs.</span>

Para finalizar o capítulo, seremos capazes de usar o Postman para fazer solicitações de API e teremos uma boa noção da terminologia básica de API. Teremos a oportunidade de realizar um exercício que nos ajudará a consolidar o que estamos aprendendo para que possamos começar a usar essas habilidades no seu trabalho diário.

## O que é uma API?
Uma publicação da NASA de 1969 intitulada Computer Program Abstracts (que pode ser encontrada no seguinte link: https://tinyurl.com/y52x4aqy) contém um resumo de um programa de controle de exibição em tempo real vendido pela IBM (apenas US$ 310! Mais US$ 36 se você quiser a documentação). 
Diz-se que esse programa foi projetado como uma interface de programação de aplicativo para o operador, em outras palavras, uma API.

As interfaces de programação de aplicativos, ou APIs, existem há quase tanto tempo quanto o código de computador. <span style="background:#d3f8b6">Conceitualmente, é apenas uma maneira de dois códigos diferentes (ou um humano e algum código) interagirem entre si</span>. Uma classe que fornece determinados métodos públicos que outros códigos podem consumir (chamar), possuem uma API. Um driver em nosso computador, que requer que os programas o chamem, de uma determinada maneira, tem uma API.

<span style="background:#fdbfff">Na criação de uma API, o código precisa ser transpilado?</span>
1. APIs RESTful: geralmente definem #endpoints que retornam dados em formatos como #JSON ou #XML. O código API em si não precisa ser transpilado, pois a comunicação entre cliente e servidor geralmente é feita através de HTTP.
<span style="background:#fdbfff">Resumindo: a transpilação não é um passo obrigatório na criação de APIs, a necessidade de transpilação depende da linguagem de programação, do ambiente de desenvolvimento e do tipo de API que estamos criando.</span>

No entanto, à medida que a internet cresceu, o termo API diminuiu de foco. Quase sempre, <span style="background:#d3f8b6">agora, quando alguém fala de uma API, está falando de uma API da Web</span>. Esse é o contexto que usarei neste livro. Uma <span style="background:#d3f8b6">API da Web</span> <span style="background:#d4b106">utiliza o conceito de uma interface entre duas coisas e o aplica à relação</span> <span style="background:#d3f8b6">cliente/servidor</span> sobre a qual a internet foi construída. Em uma API da Web, um cliente está em um lado da interface e envia solicitações, enquanto um servidor (ou servidores) está no outro lado da interface e responde à solicitação. 

Com o tempo, a Internet mudou e evoluiu, e as APIs da Web mudaram e evoluíram junto com ela. Muitas das primeiras APIs da Web foram criadas para casos de uso corporativo com regras rígidas sobre como os dois lados da interface poderiam interagir entre si. O protocolo SOAP foi desenvolvido para essa finalidade. No entanto, no início dos anos 2000, a Web começou a se transformar em um local mais voltado para o consumidor. Alguns sites de comércio eletrônico, como eBay e Amazon, começaram a publicar APIs mais públicas e flexíveis, o que foi seguido por muitos sites sociais, como Twitter, Facebook e outros. Muitas dessas APIs foram criadas usando o protocolo REST, que era mais flexível e foi criado diretamente nos protocolos subjacentes da Internet.

No entanto, a Internet continuou a mudar e, à medida que a popularidade dos aplicativos e sites móveis crescia, também aumentava a importância das APIs. Algumas empresas enfrentaram desafios com a quantidade de dados que queriam transferir em dispositivos móveis e, por isso, o Facebook criou o GraphQL. Essa linguagem de consulta ajuda a reduzir o volume de dados transferidos e, ao mesmo tempo, introduz uma estrutura um pouco mais rígida na API. Cada um dos diferentes tipos de API funciona bem em alguns cenários. 

## Tipos de chamadas de API
Algumas chamadas para APIs <span style="background:#fdbfff">podem alterar coisas no servidor</span>, <span style="background:#fdbfff">enquanto outras retornam dados sem alterar nada</span>. É importante distinguir entre esses diferentes tipos de chamadas ao testar ou projetar uma API. Há alguns termos diferentes que são usados para descrever essas diferenças. 

Imagine que há uma mesa com algumas peças de Lego sobre ela. Agora imagine que a pessoa à direita no diagrama a seguir sou eu. Eu represento uma API, enquanto as peças de Lego representam um servidor.
![[Capítulo 1 - API Terminologia e Tipos.png]]
Eu posso solicitar que você faça coisas, eu posso pedir para você me dizer qual é a cor da peça de Lego do topo. Eu respondo que é azul. Esse é um exemplo de uma solicitação e resposta de API que é #segura. Uma solicitação segura é aquela que não altera nada no servidor. Ao solicitar informações sobre o que está acontecendo no servidor, não alteramos nada dentro do servidor, de fato. 

No entanto, há outros tipos de chamadas de API que podem ocorrer. Se quisermos adicionar outra peça de lego, verde, por exemplo, no lugar do tijolo azul, ao fazer, estamos alterando o estado do servidor. A pilha de tijolos agora é composta por um tijolo amarelo, um vermelho e um verde, conforme mostrado no diagrama a seguir:

Como o servidor foi alterado, essa não é uma solicitação #segura. Chamada indempotente é quando pedimos para o servidor substituir um valor por outro igual, #idempotent call. As chamadas de API que retornam o mesmo resultado, não importa quantas vezes a chamaremos, são conhecidas como idempotentes (chamadas que podem ser aplicadas mais de uma vez sem que o resultado se altere).

Vamos imaginar mais um tipo de chamada. 

<span style="background:#d3f8b6">Eu forneço mais um tijolo verde e peço para que adicione ele no topo da lista</span>, e agora passamos a ter quatro tijolos, ainda sim é uma chamada idempotente?
A resposta é não, vamos pensar:
	Eu acredito que não seria idempotente pelo fato de estarmos passando o tijolo para um novo índice, ou seja, a posição lógica dele. Além de termos mais de 3 itens, 4 tijolos Lego, no caso...

Uma chamada idempotente é aquela que só altera as coisas na primeira vez em que é executada e não faz nenhuma alteração nas chamadas subsequentes. <span style="background:#fdbfff">Como essa chamada altera algo todas as vezes, ela não é idempotente (exemplo em verde acima).</span>. Como essa chamada altera algo todas as vezes, ela não é idempotente. 

Segurança e idempotência são conceitos importantes a serem compreendidos, especialmente quando se trata de testar APIs. Por exemplo, se estiver testando chamadas que são seguras, poderá executar testes em paralelo sem precisar se preocupar com a interferência entre eles. <span style="background:#d3f8b6">Mas se estivermos testando chamadas que não são seguras ou idempotentes</span>, talvez seja necessário ter um pouco mais de cuidado com os tipos de testes que executa e quando os executa.

Agora que entendemos um pouco dessa terminologia básica, falaremos sobre a estrutura de uma API. Mas será mais fácil realizarmos isso se tivermos algo concreto para analisarmos. Para isso, vamos realizar o download do Postman. 

### Configurando uma requisição no Postman
É hora de configurarmos uma API call para que possamos dissecá-la  e ver como tudo funciona.
1. Comece clicando no botão Novo e, em seguida, escolha o componente básico Solicitação:
CONFIGURADO


### A estrutura de uma requisição API

A guia de request do Postman fornece muitas informações sobre os vários elementos que compõem uma solicitação de API. Cada uma dessas partes desempenham um papel importante no envio e no recebimento de dados com uma API e, por isso, vamos ver cada uma delas. Algumas partes de uma solicitação de API são opcionais, dependendo do tipo de solicitação e do que estamos tentando fazer com ela, mas há duas partes que são necessárias para cada solicitação de API. T<span style="background:#d3f8b6">oda solicitação de API precisa de um endpoint e de uma ação</span>. #Endpoint 

### API Endpoint
Toda solicitação de API baseada na Web deve especificar um endpoint. Na guia de solicitações do Postman, ele nos pede para inserirmos uma URL. Usamos o termo URL que, às vezes, esquecemos o que ele significa. URL é um acrônimo de Uniform Resource Locator (localizador uniforme de recursos). O endpoint de uma chamada de API especifica o recurso, ou o "R" do URL. Em outras palavras, um #endpoint de API é o localizador uniforme de um determinado recurso com o qual desejamos interagir com o servidor. Os URLs nos ajudam a localizar recursos em um servidor e, portanto, são usados como endpoints em uma chamada de API.

Ao inserirmos o endpoint fornecido, nós obteremos informações sobre o repositório GitHub. 

Veremos com frequência endpoint de API especificado sem a parte de base dessa API. Assim, por exemplo, se olharmos a documentação da API do GitHub, ela informará o endpoint para isso como /users/:username/repos. Todas as chamadas da API do GitHub começam com a mesma URL de base (em outras palavras, https://api.github.com) e, portanto, essa parte do endpoint é frequentemente deixada de fora quando se fala de um endpoint. <span style="background:#d3f8b6">Precisamos sempre encontrar a URL de base da API para o endpoint a fim de chamá-lo. </span>

### API actions
Toda API call precisa especificar um recurso com o qual estamos trabalhando. Esse é o endpoint, mas há uma segunda coisa que toda chamada de API precisa. Uma API precisa fazer algo com o recurso especificado. Especificamos o que queremos que uma API faça com as ações de API, API #actions. Essas ações às vezes são chamadas de verbos e informam à chamada de API que esperamos que ela faça com o recurso que lhe fornecemos. 

No Postman, podemos selecionar a ação desejada usando o menu suspenso ao lado da caixa de texto onde inserimos a URL. Existem muitas outras ações disponíveis para chamadas de API. Algumas dessas ações são especializadas para determinados aplicativos e, portanto, não os encontraremos com muito frequência. Neste livro, usaremos apenas o método #GET, #POST, #PUT e #DELETE. Muitas APIs também usam #PATCH, #OPTIONS e #HEAD, mas o uso dessas ações é muito semelhante ao uso das quatro que usaremos e, portanto, aprenderemos facilmente como usá-las.

As quatro ações (GET, POST, PUT e DELETE)<span style="background:#d3f8b6"> às vezes são resumidas com o acrônimo</span> #CRUD. Isso significa #Create, #Read, #Update e #Delete. Em uma API, o método POST é usado para criar novos objetos, a ação GET é usada para ler informações sobre objetos, a ação PUT é usada para modificar objetos existentes e a ação DELETE é usada para excluir objetos. Ter uma API que ofereça suporte a todos os aspectos do CRUD nos dará a flexibilidade de fazer quase tudo o que for necessário, e é por isso que essas quatro ações são as mais comuns que veremos.

API actions e endpoints são necessários para web APIs, mas esses são umas das várias outras peças importantes para uma requisição API que nós devemos considerar.

### API parameters
Os parâmetros de API são usados para criar estrutura e ordem em uma API. Eles organizam coisas semelhantes. Por exemplo, na chamada de API que estamos analisando, estamos obtendo os repositórios de um usuário específico no GitHub. Há muitos users no GitHub, e podemos usar exatamente o mesmo endpoint de API para obter a lista de repositórios de qualquer um deles com a alteração do nome de usuário no endpoint. <span style="background:#d4b106">A parte do endpoint em que ele aceita diferentes nomes de usuário é um parâmetro</span>. 

#### #Request #parameter
O parâmetro de nome de usuário no ponto de extremidade da API dos repositórios do GitHub é conhecido como um <span style="background:#d3f8b6">parâmetro de solicitação</span>. Podemos pensar em um parâmetro de solicitação como uma cadeia de caracteres de substituição no ponto de extremidade da API. Eles são muito comuns em APIs da Web. 

Por exemplo, a documentação do GitHub usa dois pontos na frente do parâmetro de solicitação para indicar que ele é um parâmetro de solicitação e não apenas outra parte do endpoint: /users/<span style="background:#ff4d4f">:username</span>/repos.

Em outras APIs, veremos parâmetros de solicitação entre chaves. Nesse caso, o endpoint seria semelhante a /users/<span style="background:#ff4d4f">{{username}}</span>/repos. Seja qual for o formato usado, o objetivo dos parâmetros de solicitação é obter informações específicas sobre diferentes objetos que são todos do mesmo tipo. Já vimos como podemos fazer, alterando o nome de usuário pelo nosso nome de usuário do GitHub.

#### #Query #parameters
Há outro tipo de parâmetro que podemos ter em um endpoint. Esse tipo de parâmetro é conhecido como <span style="background:#d3f8b6">parâmetro de consulta</span> e é um pouco mais complicado de lidarmos. Um parâmetro de consulta geralmente funciona como um tipo de filtro ou ação adicional que podemos adicionar a um endpoint. Eles são representados por um ponto de interrogação no endpoint da API e são especificados com uma <span style="background:#ff4d4f">chave</span>, q<span style="background:#d3f8b6">ue é o valor item que estamos consultando</span>, e um <span style="background:#ff4d4f">valor</span>, <span style="background:#d3f8b6">que é o que desejamos que a consulta retorne</span>.

-> Query parameters: representado por interrogação "?" e possuem o modelo <span style="background:#ff4d4f">chave</span> (<span style="background:#d3f8b6">item que desejamos</span>) <span style="background:#ff4d4f">valor</span> (<span style="background:#d3f8b6">retorno dos dados da chave solicitada</span>). 

Isso é muito abstrato, então vamos dar uma olhada nisso com a solicitação do GitHub que temos aberta. Esse endpoint é compatível com alguns parâmetros de consulta diferentes. Um deles é o parâmetro #type. Para adicionar parâmetros a um endpoint de API no Postman, certificamos que a guia Params esteja selecionada e, em seguida, coloque o nome do parâmetro de consulta no campo (key) e o valor no campo (value).

Se eu enviar essa solicitação, receberei de volta todos os repositórios dos quais sou membro, em vez dos que eu possuo. Os parâmetros são um poderoso paradigma de API, mas ainda há mais algumas partes fundamentais da estrutura da API sobre os quais ainda não falamos.

#### API #headers
Toda solicitação de API precisa incluir alguns cabeçalhos. Os cabeçalhos incluem algumas informações de fundo que geralmente não são tão importantes para os usuários humanos, mas ajudam o servidor a ter algumas informações sobre o cliente que está enviando a solicitação. Às vezes, precisamos modificar ou adicionar determinados cabeçalhos para que uma API faça o que desejamos, mas muitas vezes podemos simplesmente deixar que a ferramente que estamos usando envie os cabeçalhos padrão que precisa enviar sem nos preocuparmos com isso.

No Postman, podemos ver quais cabeçalhos serão enviados com a sua solicitação usando a guida Headers (cabeçalhos)


### API #Body
Se quisermos criar ou modificar recursos com uma API, precisamos fornecer ao servidor algumas informações sobre o tipo de propriedades que desejamos que o recurso tenha. Esse tipo de informação geralmente é especificado no corpo de uma solicitação. 
<span style="background:#ff4d4f">O corpo da solicitação</span> pode assumir várias formas. Se clicarmos na guia Body (corpo) na solicitação do Postman, poderemos ver alguns dos diferentes tipos de dados que podem ser enviados. <span style="background:#d3f8b6">Podemos enviar dados de, dados de formulários codificados, dados brutos, dados binários e até mesmo dados GraphQL</span>. Como podemos imaginar, há muitos detalhes envolvidos no envio de dados no corpo de uma solicitação. <span style="background:#d3f8b6">Na maioria das vezes, as solicitações GET não exigem que especifiquemos um corpo</span>. Outros tipos de solicitações, como POST e PUT, que exigem que especifiquemos um body, geralmente requerem alguma forma de autorização, pois permitem que a gente modifique os dados do servidor. Quando pudermos autorizar solicitações, haverá muito mais exemplos dos tipos de coisas que podemos querer especificar no corpo de uma solicitação de API.

### API #response

Até agora, passamos muito tempo falando sobre várias partes que compõem uma API, mas há um aspecto muito importante que estamos ignorando. Uma API é uma via de mão dupla. Ela envia dados para servidor na request, mas o servidor processa essa solicitação e envia uma resposta #response .

A visualização padrão que o Postman usa exibe a resposta na parte inferior da página de solicitação. Podemos modificar a visualização para ver a solicitação e a resposta em painés lado a lado.

Há alguns aspectos diferentes na resposta. O mais óbvio é o corpo da resposta. Normalmente, é aqui que a maioria das informações que estamos procurando será incluída. Nas solicitações de repositórios do GitHub que fizemos, <span style="background:#d3f8b6">as listas de repositórios aparecerão no corpo da resposta</span>, e o Postman as exibirá nessa guia.

Uma resposta de API também pode incluir alguns outros elementos, como cookies e cabeçalhos. Esses tipos de coisas podem ser dicas muito importantes sobre o que está acontecendo ao testar ou criar APIs, e falarei mais sobre eles à medida que avançarmos no livro.

Abordaremos muitos aspectos relacionados ao funcionamento das solicitações de APIs. Vimos como uma solicitação de API pode ter muitas partes diferentes. Todas essas partes simples se juntam para criar uma ferramenta poderosa. Agora temos uma noção das partes básicas que compõem uma chamada de API e como usá-las no Postman. Está quase na hora de falarmos como usar isso para testar APIs, mas, antes de entrarmos nesse assunto, vamos fazer uma pau de um minuto  para que a gente possa colocar em prática toda essa teoria que abordamos.

### Aprendendo na prática - Fazendo API calls

Os livros são uma ótima maneira para se aprender e crescer. Ler um livro (ou até mesmo três ou quatro livros) sobre um assunto não significa que entendemos esse assunto. Existe a teoria e a prática. Há o conhecimento de algo que nós lemos e o conhecimento de algo que nós fizemos. 

Vamos dar uma olhada em um exercício que podemos fazer para ajudarmos a fazer com que toda a teoria sobre a estrutura de uma solicitação de API se mantenha. Chamarei esse exercício de "Mapear o aplicativo"

### Map the App, exercício
O objetivo desse exercício é nos ajudar a consolidar o que aprendemos sobre APIs e garantir que a gente saiba como chamar essas APIs. Para esse exercício:
1. Mapear a API de um aplicativo;

Escolher uma API pública na lista:
https://github.com/public-apis/public-apis?tab=readme-ov-file

Quando dizemos mapear o aplicativo, não estamos referindo sobre um mapa cartográfico. Estamos falando de algo como um diagrama de linhas ligando diferentes partes do aplicativo, ou até mesmo um mapa mental ou uma lista de diferentes partes do aplicativo. 

O que faremos com o exercício será:
1. Tentar chamar alguns dos endpoints do aplicativo e anotar algumas observações sobre o que eles fazem e como se relacionam entre si. 
2. Ver se podemos mapear o que a API permite que a gente faça e como as diferentes partes se relacionam entre si. Podemos criar uma lista dos diferentes endpoints.
3. Criar uma coleção no Postman e ver se podemos organizar os diferentes endpoints dentro dessa coleção.
4. Explorar as diferentes opções da API e praticar com os endpoints da API.

Ao concluir este exercício, reserve um tempo para refletir sobre o que aprendemos e o que já é capaz de fazer. Podemos fazer chamadas de API e realizar algumas investigações para nos aprofundarmos e entender como uma API é montada. 

### Exercício
Para o exercício eu utilizei a API recomendada cat-facts:
https://alexwohlbruck.github.io/cat-facts/docs/

A URL base para todos os endpoints dessa api é: https://cat-fact.herokuapp.com

Os endpoints:
/facts -> recuperar e consultar fatos sobre os gatos;
/users* -> obter os dados do usuário

#### Começando com a exploração
Se quisermos melhorar a qualidade de uma API, nós precisamos entender como ela funciona, precisamos explorá-la.
<span style="background:#ff4d4f">Mas como fazemos isso?</span>

Felizmente, o Postman é uma das melhores ferramentas disponíveis para explorar APIs. Com o Postman, podemos experimentar facilmente muitos endpoints e consultas diferentes sobre o que está acontecendo na API. O Postman facilita a brincadeira com uma API e a ida de um lugar para o outro. A exploração envolve seguir as pistas que estão bem na sua frente. Ao receber os resultados de uma solicitação, devemos pensar nas perguntas que esses resultados trazem à mente e tentar respondê-las com outras chamadas. Tudo isso é muito simples com o Postman. 
Para essa sessão, usaremos a API https://swapi.dev/. Essa é uma pequena API divertida que expõe dados sobre os filmes de Star Wars. - `https://swapi.dev/api/`

Estudo de caso de teste exploratório
Na resposta, há links para outras partes da API. Uma resposta que inclui links para outros recursos relevantes é conhecida como API de hipermídia. #Hypermedia API.

Como estamos no modo de exploração, fazemos a pergunta; "o que será que aconteceria se eu tentasse solicitar a pessoa número 83?"

Seja qual for o caso, isso ilustra o quão poderoo pode ser um pouco a exploração. Mais adiante, neste livro, abordaremos algumas maneiras poderosas de usar o Postman para automação de testes, mas não nos apressemos em passar pelo óbvio.

Teste de API é teste. Quando estamos testando, estamos tentando descobrir novas informações ou problemas que podem ter passado despercebidos. Se corrermos direto para a automação de testes, poderemos perder algumas coisas importantes. Reserve um tempo para explorar e entender as APIs no início do processo. 

Explorar é a parte chave para qualquer teste, mas devemos fazer mais do que apenas testar coisas em nossa aplicação. Bons testes requerem a capacidade de conectar o trabalho com o que estamos fazendo para valores de negócio.

#### Looking for business problems
Ao considerar o teste e o design da API, é importante levar em conta <span style="background:#ff4d4f">o problema comercial que a API está resolvendo</span>. 
Uma API não existe em um vácuo. Ela está lá para ajudar a empresa a atender a algum tipo de necessidade comercial. 

Entender qual é essa necessidade ajudará a orientar as abordagens e estratégias de teste que adotaremos. 

Por exemplo, se uma API for usada apenas por consumidores internos, os tipos de coisas que ela precisa fazer e suportar são muito diferentes daqueles necessários se ela for uma API pública que os clientes externos possam acessar.

Quando testamos uma API, procuramos por problemas de negócios. S<mark style="background: #FFB8EBA6;">e conseguir encontrar problemas que impeçam a API de fazer o que a empresa precisa que ela faça, estaremos de encontro com problemas valiosos e melhorando a qualidade do aplicativo</mark>. Nem todos os bugs são iguais.

### Experimentar coisas estranhas
Nem todos os usuários de uma API vão usá-la da maneira que aqueles que escreveram a API pensaram que eles usariam. 

Não podemos saber todas as coisas possíveis que os usuários do nosso sistema farão, mas há estratégias que podem ajudá-lo a ver as coisas de uma forma diferente. 

O teste não precisa ser uma repetição sem sentido dos mesmos casos de teste. Use sua imaginação. Experimente coisas estranhas e interessantes. Veja o que podemos aprender. O objetivo deste livro é que a gente aprenda a usar uma nova ferramenta. O fato de ter pegado este livro mostra que estamos interessando em aprender. Tente algo estranho e veja o que acontece.

Obviamente, há muito mais a ser testado do que apenas essas poucas considerações que apresentei aqui. 

### Diferentes tipos de APIs
Existem diversos tipos de APIs que são utilizadas com frequência na internet. Antes de mergulharmos com detalhes no uso do Postman, vale a pena conhecer um pouco sobre os diferentes tipos de APIs e como reconhecê-las e testá-las. 

### Rest APIs
O tipo mais comum de API que encontraremos na web moderna será uma #rest API. REST é a sigla de Rpresentational State Transefr (Transferência de estado representacional) e refere-se a um estilo arquitetônico que orienta como devemos criar APIs. Há algumas pistas que podem indicar que estamos testando uma API RESTful.

Como as APIs RESTFULL são baseada em um conjunto de diretrizes, nem todas têm a mesma aparência. Não existe um padrão oficial que defina as especificações exatas com as quais uma resposta deve estar em conformidade. Isso significa que muitas APIs que são consideradas RESTFUL. Em geral, o REST tem mais flexibilidade do que um protocolo baseado em padrões, como o SOAP, mas isso significa que pode haver muita diversidade na forma como as APIs REST são definidas e usadas.

<span style="background:#ff4d4f">So how do you know whether the API that you are looking at is RESTful?</span>

Bem, em primeiro lugar, que tipo de requests são tipicamente definidas? Muitas REST APIs possuem <span style="background:#affad1">GET, POST, PUT e DELETE</span> calls, e talvez algumas outras poucas. Dependendo das necessidades da API, ela pode não usar todas essas ações, mas essas s<span style="background:#affad1">ão as mais comuns que provavelmente veremos se a API que estamos analisando for RESTFUL</span>.

Outra pista está nos tipos de solicitações ou respostas permitidas pela API. Geralmente, as APIs REST usam dados JSON em suas respostas (embora possam usar texto ou até mesmo XML). De modo geral, se os dados nas respostas e solicitações da API não forem XML, há uma boa chance de estamos lidando com algum tipo de API baseada em REST. Há muitos exemplos de APIs REST na web.

- Comumente, as APIs RESTful utilizam <span style="background:#affad1">**JSON (JavaScript Object Notation)**</span> para estruturar e transmitir esses dados nas solicitações (requisições) e respostas.
- JSON é um formato leve e legível por humanos, tornando-o ideal para a troca de dados entre aplicativos web.
- **Estado sem sessão:** <span style="background:#affad1">As APIs RESTful são **sem estado**, o que significa que cada requisição deve conter todas as informações necessárias para ser processada.</span> O <span style="background:#ff4d4f">servidor não mantém o estado entre as solicitações</span>.

 A maioria das APIs modernas, incluindo APIs de redes sociais, serviços de armazenamento em nuvem e APIs de pagamento, são RESTful e utilizam JSON.

### SOAP APIs
Antes do REST, havia o #SOAP. O Soap singifica Simple Object Acess Protocol (Procolo Simples de Acesso a Objetos). O SOAP existe desde muito antes de Roy Fielding ter criado o conceito de APIs REST. Atualmente, ele não é tão amplamente usado na Web (especialmente para aplicativos menores), mas, por muitos anos, foi a maneira padrão de criar APIs e, portanto, ainda existem muitas APIs SOAP.

SOAP é um protocolo real com uma definição de padrões W3C. Isso singifca que seu uso é muito mais estritamente definido do que o REST, que é uma diretriz arquitetônica em vez de um protocolo estritamente definido. 

As APIs SOAP exigem que uma mensagem XML altamente estruturada seja enviada com a solicitação. Por serem criadas em XML, essas solicitações não são fáceis de ler para os seres humanos e exigem muita complexidade para serem criadas. Existem, é claro, muitas ferramentas (um exemplo é o SoapUI) que podem ajudar com isso, mas, em geral, as APIs SOAP tendem a ser um pouco mais complexas para começar. 

But how do you know whether the API you are looking at is a SOAP API?

A regra geral mais importante aqui é: ela exige que a gente especifique XML estruturado para funcionar? Se for o caso, trata-se de uma API SOAP. Como esses tipos de APIs precisam seguir a especificação W3C, elas devem usar XML e especificar coisas como nós. Se a API que estamos utilizando exige que o XML seja especificado, e esse XML inclui o nó Envelope.

### GraphQL APIs
O SOAP veio antes do REST e, de muitas maneiras, o REST foi projetado para lidar com algumas das deficiências do SOAP. É claro que, em software, nunca deixamos de melhorar as coisas e, por isso, agora temos um tipo de API conhecido como GraphQL. O GraphQL é uma linguagem de consulta e foi projetado para lidar com algumas das situações em que as APIs REST apresentam deficiências. <span style="background:#affad1">As APIs RESTful não sabem quais informações específicas podemos estar procurando</span> e, portanto, quando fazemos um endpoint de API, ele fornece todas as informações que possui. Isso pode significar que estamos enviando todas as informações de que precisamos e que precisamos chamar vários endpoints para obter o que desejamos. Qualquer um desses casos pode tornar as coisas mais lentas e, para aplicativos grandes com muitos usuários, isso pode se tornar problemático. O GraphQL foi projetado pelo Facebook para lidar com esses problemas. 

O GraphQL é uma linguagem de consulta para APIs e, portanto, exige que específiquemos em uma consulta o que estamos procurando. Com as APIs REST, normalmente só precisamos saber quais são os diferentes endpoints para encontrar as informações que procura, mas com uma API GraphQL, <span style="background:#affad1">um único ponto de extremidade conterá a maioria ou todas as informações de que precisemos</span> usaremos consultas para filtrar essas informações apenas para os bits que nos interessam. Isso significa que, com as APIS GraphQL, <span style="background:#affad1">precisaremos conhecer o esquema ou a estrutura dos dados para saber como consultá-los</span> adequadamente, em vez de precisar saber quais são todos os endpoints. 

### GraphQL API example
De forma semelhante à chamada de uma API SOAP, precisaremos especificar o serviço que queremos e fazer uma solicitação POST em vez de uma solicitação GET. As consultas GraphQL podem ser feitas com GET, mas é muito mais fácil especificar a consulta no corpo de uma solicitação POST, <span style="background:#affad1">portanto, a maioria das chamadas à API GraphQL é enviada com o método POST</span>.

Neste ponto, obviamente, só conseguimos fazer uma introdução muito rápida e cada um desses tipos de APIs. 