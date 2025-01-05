### Introdução

O Express.js tem sido o framework para aplicações web mais popular para o Node.js por muitos anos. O propósito da framework era ajudar no desenvolvimento de aplicações web. O foco do Express.js está na velocidade e no escopo gerenciável da estrutura principal, e sua interface facilmente extensível. A sua arquitetura bem planejada possibilita a manutenção até os dias de hoje e, portanto, a estrutura se tornou uma companheira quase indispensável quando se trata do desenvolvimento de aplicativos de servidor da web baseado no Node.js. <span style="background:#d3f8b6">Estruturas como o Express existem porque o desenvolvimento da web geralmente envolve a solução de tarefas padrão</span>. Por exemplo, em PHP, há a estrutura Symfony; em Python, você pode usar o Django; e o Ruby on Rails oferece uma solução para aplicativos Web em Ruby. É possível implementar seu aplicativo completamente na respectiva linguagem (neste caso, Node.js) sem a ajuda de outras bibliotecas e estruturas. 

Além de manipular solicitações e resolver URLs, outras tarefas padrão precisam ser realizadas, como manipulação de sessões, autenticação ou uploads de arquivos. Já existem soluções estabelecidas para todas essas tarefas, que foram combinadas em uma estrutura sob a liderança do Express. 

### 6.1 Estrutura
O express é um framework compacto com uma gama de funções gerenciáveis. Ele pode ser facilmente estendido com componentes de middlware. A estrutura do Express, assim como a do próprio Node.js é em camadas:
![[Capítulo 6 - Express-2.png]]
O módulo HTTP do Node.js serve como a fundação para o Express. O módulo HTTP cria o processo do servidor na qual o Express é baseado. Além disso, os objetos de solicitação (<span style="background:#d3f8b6">request</span>) e resposta (<span style="background:#d3f8b6">response</span>) estão disponíveis para acessar as informações da solicitação e criar a resposta para o cliente, respectivamente. Internamente, por exemplo, o Expressa usa a URL que o usuário inseriu no navegador para implementar o processo de roteamento no aplicativo. O <span style="background:#ff4d4f">roteamento</span> no express é o processo de direcionar a solicitação do usuário (um pedido de informação ou ação) para a parte correta do seu aplicativo (o departamento que processa o pedido).

A segunda camada da arquitetura do Express é a camada Middleware, no contexto do Express, Middleware é uma função localizada <span style="background:#d3f8b6">entre a requesição</span> e a <span style="background:#d3f8b6">resposta do servidor</span> para o cliente. Várias funções middleware podem ser encadeadas para executar ações específicas com base na solicitação do cliente. 

A terceira camada da arquitetura do Express é o roteador. Esse componente do Express controla qual função deve ser executada, dependendo da URL chamada, para gerar uma resposta ao cliente. No roteamento (routing), são considerados o <span style="background:#d3f8b6">método HTTP</span> e o <span style="background:#d3f8b6">caminho da URL</span>. 

#Endpoint: considera o routing (método + caminho URL) + função callback

### 6.3 Princípios Básicos
O processo pelo qual um aplicativo Express funciona, tem sempre o mesmo padrão. O servidor Express recebe uma solicitação de um cliente. Com base no <span style="background:#d3f8b6">método HTTP</span> escolhido e no <span style="background:#d3f8b6">caminho da URL</span>, uma rota adequada é escolhida e <span style="background:#d3f8b6">uma ou mais funções de callback são executadas</span>. Nessa função callback, podemos acessar os objetos de requisição resposta, esses dois componentes (req e res) + (método HTTP + URL) + middlewares, são o coração de um aplicativo. 

#### 6.3.1 Request
O objeto #request, é o primeiro argumento das funções callback de uma <span style="background:#d3f8b6">rota Express</span> e <span style="background:#d3f8b6">representa a requisição do usuário</span>. Nós podemos ampliar este objeto utilizando componentes middlewares tal como o body parser e o cookie parser. 

#### 6.3.2 Response
O objeto response, este que podemos acessar via segundo argumento da função de roteamento, representa a resposta para o cliente. Como escrevemos neste objeto, ele oferece muito mais métodos do que propriedade. A propriedade mais importante é a headersSent.

#### 6.4 Configuração
Como prática recomendada geral para lidar com o Express, foi constatado que um aplicativo deve ser dividido em componentes separados, na medida do possível, cada um deles armazenado em arquivos separados. Para estruturar um aplicativo Express, uma abordagem clássica de #MVC é a melhor opção.

**MVC: The Model-View-Controller Pattern**
O padrão MVC é utilizado para estruturar aplicações, especialmente no desenvolvimento Web, onde se torna um padrão extremamente importante. Esse padrão descreve onde determinadas partes de um aplicativo devem ser armazenadas e como essas partes interagem entre si. O nome MVC já contém os três componentes do padrão:
1. #Model: os models de um aplicativo MVC são usados para o gerenciamento de dados. Os modelos encapsulam todas as operações relacionadas aos dados do aplicativo. Isso diz respeito tanto à criação quanto à modificação ou exclusão de informações. Normalmente, um modelo encapsula os acessos ao banco de dados. Além dos dados e da lógica associada para manipular esses dados, os models também encapsulam a lógica comercial da aplicação.
2. #View: a tarefa das visualizações consiste em exibir informações. As exibições de um aplicativo Express são, em sua maioria, modelos HTML preenchidos com os dados dinâmicos da aplicação antes de serem entregue ao cliente. Nos aplicativos modernos que utilizam muito a interface de aplicativos (API), esse aspecto da arquitetura MVC está ficando cada vez mais em segundo plano, pois os modelos raramente são renderizados e, em vez disoo, os objetos JSON são frequentemente enviados do servidor para o cliente.
3. #Controller: o controller contém a lógica de nossa aplicação. Devemos nos certificar que os controladores não se tornem muito grandes. Se o controlador contiver muita lógica, devemos tercerizá-la para função ou suport models.

### 6.5 Banco de dados Filme
#### 6.5.1 Roteamento
