*Quando for desenvolver uma aplicação web, você deve pensar na arquitetura que deseja usar.*

A arquitetura dos aplicativos Web, ou do software em geral, diz respeito a como você deseja dividir um aplicativo em componentes menores e como organizar a interação entre eles. Neste capítulo, apresentarei uma visão geral das principais arquiteturas que desempenham um papel no contexto dos aplicativos Web. É claro que livros inteiros podem ser escritos sobre o tema da arquitetura e, não à toa, existe até mesmo uma descrição de trabalho separada para arquitetos de software. Portanto, apresentarei apenas uma seleção de tópicos neste capítulo, para que você conheça os termos mais importantes. Você deve ser capaz de classificar as arquiteturas individuais e ter um entendimento básico para trabalhar mais com arquiteturas da Web. Conforme mostrado na figura 12.1 abaixo, uma arquitetura inclui todo o aplicativo, portanto, no caso de aplicativos da Web, são considerados tanto o lado do cliente quanto o lado do servidor.

![[Capítulo 12 - Compreendendo e utilizando arquiteturas Web.png]]
*A arquitetura de aplicativos Web, que afeta tanto o lado do cliente quanto o lado do servidor, trata de como os aplicativos da Web devem ser estruturados.*

## 12.1 Arquiteturas em camadas
Assim, a arquitetura descreve como um aplicativo pode ser subdividido ou decomposto. Uma subdivisão comum de uma arquitetura é em camadas individuais. Nessa arquitetura em camadas, o aplicativo é dividido em camadas horizontais, sendo que <font color="#f79646">cada camada desempenha uma função específica dentro do aplicativo</font>.

### 12.1.1 Estrutura básica de arquiteturas em camadas
Na literatura (e na prática), existem muitas maneiras diferentes de subdividir as camadas. Normalmente, porém, são consideradas pelo menos as <span style="background:#d3f8b6">quatro camadas</span> a seguir, conforme mostrado na figura 12.2:

- A camada de apresentação (*presentation layer*) é responsável por gerenciar toda a lógica da interface do usuário (UI) (lógica de apresentação). Essa camada cuida da aparência da UI, dos dados que são exibidos, de como os dados são formatados e das ações do usuário que podem ser acionadas;
- A camada de lógica de negócios (também chamada de camada lógica) é responsável por <font color="#f79646">fornecer e executar a lógica do aplicativo</font>. Essa camada inclui componentes que implementam a lógica do aplicativo e a funcionalidade essencial de um aplicativo, como <font color="#f79646">cálculos específicos</font> ou o <font color="#f79646">processamento de dados do aplicativo</font>.
- A camada de persistência controla o acesso aos dados persistidos. Essa camada contém <span style="background:#d3f8b6">componentes que armazenam dados em um banco de dados</span> ou<span style="background:#d3f8b6"> carregam dados de um banco de dados</span>. A camada de <span style="background:#d3f8b6">persistência</span> é usada principalmente para <span style="background:#d3f8b6">abstrair o acesso aos bancos de dados reais</span>. 
- A camada de dados é responsável pelo armazenamento (persistência) dos dados gerenciados em um aplicativo. Essa camada <span style="background:#d3f8b6">contém os bancos de dados reais</span>.

Assim, <font color="#f79646">cada camada</font> da arquitetura <font color="#f79646">tem uma tarefa ou área de responsabilidade definida</font> e não se preocupa com <font color="#f79646">as áreas de responsabilidade de outras camadas</font>. Por exemplo, os componentes da camada de apresentação não precisam saber ou se preocupar exatamente com a forma como os dados são recuperados do banco de dados. Da mesma forma a camada de conceito de negócios não precisa se preocupar com a forma como os dados devem ser apresentados para exibição na interface do usuário. As vantagens dessa divisão em camadas incluem <span style="background:#d3f8b6">melhor capacidade de teste</span>, <span style="background:#d3f8b6">desenvolvimento independente</span> e uma melhor <span style="background:#d3f8b6">visão geral do código</span>.  

Observe que, em uma arquitetura em camadas, <span style="background:#d3f8b6">geralmente somente os componentes das camadas superiores podem acessar as camadas inferiores</span>, e os componentes das camadas inferiores não podem acessar as camadas superiores. Por exemplo, os componentes da camada de apresentação podem acessar a camada de lógica comercial para chamar funções específicas ou a lógica do aplicativo. Os componentes da camada de lógica de negócios, por sua vez, podem recorrer à camada de persistência para carregar ou armazenar dados necessários ou gerados como parte da lógica do aplicativo. Os componentes da camada de persistência se dirigem à camada de dados, que é responsável pelo armazenamento dos dados. 

![[Capítulo 12 - Compreendendo e utilizando arquiteturas Web-1.png]]
*Arquitetura em camadas: aplicação dividida em diversas camadas com diferentes tarefas.*

### Terminologia
Geralmente, o termo <span style="background:#d3f8b6">arquitetura multicamadas</span> refere-se a uma arquitetura com <span style="background:#d3f8b6">quatro camadas</span>. Entretanto, <font color="#f79646">podem existir ainda mais camadas</font>. Por esse motivo, o termo arquitetura de <span style="background:#d3f8b6">N camadas é geralmente usada para qualquer arquitetura com mais de duas camadas</span>. Voltarei a falar sobre a arquitetura de duas camadas e a arquitetura multicamadas na Seção 12.1.2 e na Seção 12.1.3, respectivamente.

Os termos layer e tier são frequentemente usados de forma intercambiável. Assim, na literatura, você encontrará os termos arquitetura N-tier e arquitetura N-layer (sendo o primeiro geralmente mais comum). No entanto, a rigor, existe uma diferença: #Layer denota uma subdivisão lógica, enquanto #tier denota uma subdivisão física. No entanto, usaremos os termos de forma intercambiável neste capítulo porque essa distinção - se as camadas individuais (camadas) <font color="#f79646">estão fisicamente separadas ou apenas logicamente separadas</font> -<span style="background:#d3f8b6"> não é relevante para a descrição de arquitetura em geral</span>. 

### 12.1.2 Cliente-Server Architecture (Two-Tier Architecture)
Na arquitetura cliente-servidor, mostrada na figura 12.3, <span style="background:#d3f8b6">um aplicativo é dividido em duas partes ou camadas</span>: o <font color="#f79646">cliente</font> (ou geralmente vários clientes) e o <font color="#f79646">servidor</font>, que é  o componente central nessa arquitetura. <font color="#f79646">Os clientes e os servidores</font> <span style="background:#d3f8b6">são conectados por meio de uma rede e se comunicam por meio de um protocolo</span>. Usando esse protocolo, os clientes podem fazer solicitações ao servidor, que processa essas solicitações; <span style="background:#d3f8b6">o servidor envia</span> a resposta correspondente <span style="background:#d3f8b6">de volta ao cliente exato</span> do qual <font color="#f79646">a solicitação foi enviada</font>. 

A arquitetura cliente-servidor clássica também é geralmente chamada de <span style="background:#d3f8b6">arquitetura de duas</span> camadas porque usa duas camadas. 
![[Capítulo 12 - Compreendendo e utilizando arquiteturas Web-2.png]]
*Figura 12.3: arquitetura cliente-servidor com o cliente (Frontend) e o servidor Backend.*

#### Nota
<span style="background:#affad1">Tanto o lado do cliente quanto o lado do servidor podem, por sua vez, ser divididos em camadas, mas falaremos mais sobre esse tópico na Seção 12.1.3.</span>

A arquitetura cliente-servidor também pode ser usada para aplicativos que não são aplicativos Web. O cliente não precisa necessariamente ser um cliente Web e o servidor não precisa ser um servidor Web. Nas seções a seguir, no entanto, vamos nos concentrar precisamente em aplicativos Web e presumir que estamos lidando com aplicativos Web em que existe um servidor web no lado do servidor e um ou mais clientes web (geralmente navegadores) no lado do cliente. Conforme mostrado na figura 12.4, também assumimos que a comunicação ocorre por meio de determinados protocolos ( #HTTP e #WebSockets), <span style="background:#affad1">conforme discutidos no capítulo 5</span>. 

![[Capítulo 12 - Compreendendo e utilizando arquiteturas Web-3.png]]
*Figura 12.4: arquitetura client-server para aplicações Web.*

### Client-server versus Peer-to-Peer
Para fins de completude, a arquitetura ponto a ponto (arquitetura P2P) também deve ser mencionada neste ponto. Nessa arquitetura alternativa, cada computador ou nó tem as mesmas funções e responsabilidades, e todos se comunicam entre si, conforme mostrado na figura 12.5. Em contraste com a arquitetura cliente-servidor, na qual há um servidor central e vários clientes, na arquitetura P2P, cada computador na rede é tanto um cliente quanto um servidor. 
![[Capítulo 12 - Compreendendo e utilizando arquiteturas Web-4.png]]

### 12.1.3 Multi-Tier Architecture
A abordagem de dividir um aplicativo em camada pode ser levada ainda mais longe do que na arquitetura clássica cliente-servidor. As partes do cliente e do servidor podem ser subdivididas conforme apropriado para o aplicativo. Em contrates com a arquitetura cliente-servidor ou a arquitetura de duas camadas, o aplicativo é dividido em mais de duas camadas na arquitetura de várias camadas (arquitetura de N camdas), conforme mencionado anteriormente neste capítulo.

Ao aplicar uma arquitetura de várias camadas a aplicativos da Web, é necessário distinguir entre aplicativos da Web clássicos e aplicativos da Web modernos. Os primeiros (clássicos) incluem aplicativos da Web em que a maior parte da geração de HTML ocorre no lado do servidor, enquanto os últimos incluem aplicativos em que a geração de HTML ocorre no lado do cliente. 

**Arquitetura Multi-tier para aplicação web clássicas**
Em aplicações web clássicas, o servidor é dividido em outras camadas na arquitetura de várias camadas, conforme mostrado na figura 12.6:

- A <span style="background:#affad1">camada de apresentação</span> contém os componentes responsáveis pela apresentação da interface do usuário. Para aplicativos da Web, essa camada usa códigos HTML, CSS e JavaScript.
![[Capítulo 12 - Compreendendo e utilizando arquiteturas Web-5.png]]
*Aplicações Web Clássicas dividida em camadas adicionais no lado do servidor em uma arquitetura de N camadas.*

- A camada de negócios contém a lógica da aplicação ou lógica de negócios, na qual, por exemplo, é responsável por <span style="background:#d3f8b6">recuperar as informações</span> solicitadas ou <span style="background:#d3f8b6">verificar os dados que são colocados nos armazenamentos de dados</span>. Para aplicativos da Web, essa camada geralmente é implementada em linguagens de programação com Java, Python, PHP, JavaScript ou C#.
- A camada de <font color="#f79646">persistência</font> contém os <span style="background:#d3f8b6">componentes que acessam os dados no banco de dados</span>. Para aplicações web, a linguagem de programação mencionadas anteriormente também são normalmente usadas nessa camada.
- A camada de dados contém os dados processados no aplicativo, que geralmente são armazenados em banco de dados. Há uma grande variedade de tipos de bancos de dados disponíveis para essa finalidade. 

**Arquitetura Multi-tier para aplicação web modernas**
Nos aplicativos Web modernos (por exemplo, os aplicativos de página única abordados no Capítulo 10), a arquitetura em camadas é deslocada um pouco do lado do servidor para o lado do cliente. Lembre-se de que esse tipo de aplicativo não apenas gera código HTML estático no lado do servidor e o envia ao cliente, mas também <font color="#f79646">gera HTML dinamicamente no lado do cliente</font>. A API (interface de Programação de Aplicativos) DOM (Document Object Model) é usada para gerar HTML no lado do cliente, e tecnologias como Ajax são usadas para solicitar dados do servidor. No entanto, como resultado, a maior parte da lógica de apresentação está agora no lado do cliente e não mais no lado do servidor, conforme mostrado na figura 12.7. Em outras palavras, <span style="background:#d3f8b6">nos aplicativos Web modernos</span>, a camada de <span style="background:#d3f8b6">apresentação</span> está localizada no <span style="background:#d3f8b6">lado do cliente</span> e <span style="background:#d3f8b6">não mais no lado do servidor</span>. 

#### Nota
Do ponto de vista técnico, é claro que o código do lado do cliente também está localizado no lado do servidor nos aplicativos modernos da Web e só é transferido para o cliente pelas solicitações HTTP do cliente ou pelas respostas HTTP subsequentes do servidor. Em termos de arquitetura, no entanto, é fundamental que <span style="background:#d3f8b6">o código para gerar a interface do usuário seja executado no lado do cliente</span>. 

![[Capítulo 12 - Compreendendo e utilizando arquiteturas Web-6.png]]
*Em aplicações Web modernas, a camada de apresentação é movida para o lado do cliente*

## 12.2 Monólitos e arquiteturas distribuidas
Além da divisão dos aplicativos em camadas ou níveis individuais com áreas de responsabilidade delimitadas, também existe a distinção entre arquiteturas de aplicativos monolíticas e distribuídas.

### 12.2.1 Arquitetura monolítica
A palavra "monólito" foi originalmente usada pelos gregos antigos para descrever um único bloco de pedra do tamanho de uma montanha. Aplicada à arquitetura de software, uma arquitetura monolítica <span style="background:#d3f8b6">considera o aplicativo como uma unidade única e indivisível</span>. Embora o código também seja (geralmente) dividido em camadas na arquitetura monolítica, os componentes individuais nessas camadas não são divididos em componentes reutilizáveis. 

Em uma arquitetura cliente-servidor típica (seja uma arquitetura de duas camadas ou uma arquitetura de várias camadas), um protocolo monolítico reside no servidor, onde processa solicitações HTPP, executa a lógica e interage com o banco de dados. 

**Vantagens de uma arquitetura monolítica**
A vantagem primária de uma aplicação monolítica é a <span style="background:#d3f8b6">simplicidade de sua infraestrutura</span>. Para implementar um aplicativo monolítico, geralmente apenas um arquivo ou diretório precisa ser implantado. Como toda a base de código do aplicativo reside em um único local, apenas um ambiente precisa ser configurado para criar e implantar o software, na maioria dos casos, resultando em menos tempo gasto na implantação do aplicativo adequado.


**Desvantagens de uma arquitetura Monolítica**
A principal desvantagem de uma arquitetura monolítica é que, quanto maior for o aplicativo, mais difícil será estendê-lo e mantê-lo. Outra desvantagem é que você se compromete com um determinado conjunto de tecnologias desde o início, o que é difícil de alterar posteriormente. Por exemplo, se você escolher Java como linguagem de programação, será difícil desenvolver componentes individuais em outra linguagem de programação.

Com o passar do tempo, ou seja, à medida que a funcionalidade é adicionada e desenvolvida novamente, uma arquitetura monolítica corre o risco de não compreender e supervisionar as mudanças nas inter-relações, dependências e implicações.

### 12.2.2 Service-Oriented Architecture
A divisão em cliente e servidor em uma arquitetura de várias camadas foi ampliada ao longo dos anos para incluir uma abordagem moderna conhecida como arquitetura orientada a serviços (SOA). Neste tipo de arquitetura, conforme mostrado na Figura 12.8, o aplicativo é complementado no lado do servidor por outra camada, a camada de serviço. 

**Vantagens de uma Arquitetura Orientada a Serviços**
A SOA tem várias vantagens em relação a uma arquitetura monolítica. Afinal de contas, o conceito da camada de serviço adicional é <span style="background:#d3f8b6">dividir o aplicativo em uma série de serviços menores e reutilizáveis</span>. Embora a arquitetura monolítica não tenha essa capacidade de reutilização, a SOA permite que os serviços individuais sejam reutilizados.  

A subdivisão em serviços individuais também significa que a <span style="background:#d3f8b6">implementação de serviços individuais</span> pode <span style="background:#d3f8b6">ocorrer independentemente uns dos outros</span> e ser distribuída entre diferentes equipes de desenvolvimento. Além disso, <span style="background:#d3f8b6">os serviços</span> podem até mesmo ser <font color="#f79646">executados em servidores diferentes</font> (não mostrados na figura), portanto, podem ser distribuídos em vários servidores.

Um barramento de mensagens é usado para a comunicação entre os serviços, o que garante que os serviços individuais não se comuniquem diretamente entre si. Por um lado, esse barramento de mensagens evita que os serviços sejam acoplados diretamente uns aos outros e, por outro, garante que todo o sistema possa ser facilmente ampliado com serviços adicionais. 

![[Capítulo 12 - Compreendendo e utilizando arquiteturas Web-7.png]]
**Figura 12.8 SOA: Aplicação divida do lado do servidor por uma camada de serviço adicional.**

**Desvantagens de uma arquitetura orientada a serviços**
A SOA tem alguns benefícios e também desvantagens. Uma desvantagem essencial é a complexidade que surge devido à comunicação entre serviços individuais. O fornecimento do barramento de mensagens e a comunicação associada são mais caros no total do que as comunicações diretas entre componentes individuais em uma arquitetura monolítica.

### 12.2.3 Arquitetura microserviço
A arquitetura de microsserviços é geralmente considerada uma evolução da SOA. Os (micro)serviços dessa arquitetura são ainda "menores" do que a SOA em termos de recursos implementados e agem de forma bastante independente uns dos outros. O foco de uma arquitetura de microsserviço está na escalabilidade de recursos individuais: na melhor das hipóteses, cada microserviço deve ser capaz de ser dimensionado de qualquer maneira. Em outras palavras, se no tempo de execução um microsserviço for considerado muito "lento", por exemplo, porque muitas solicitações estão sendo feitas a ele, outra instância do mesmo microsserviço pode simplesmente ser criada para que as solicitações possam ser distribuídas (balanceamento de carga). 

A comunicação entre a camada de apresentação e os microsserviços, bem como <span style="background:#d3f8b6">a comunicação entre os próprios microsserviços</span>, geralmente ocorre por meio do que é chamado de gateway ou <span style="background:#d3f8b6">gateway de API</span>. 
![[Capítulo 12 - Compreendendo e utilizando arquiteturas Web-8.png]]
**Arquitetura de Microserviços: aplicação dividida dentro de múltiplos pequenos serviços (Microservices) dentro do lado do servidor, que geralmente também lidam com o gerenciamento de dados de forma independente.**

**Vantagens da arquitetura de Microservices**
Como cada microsserviço opera de forma independente, uma arquitetura de microsserviço pode ser dimensionada melhor do que outras abordagens de arquitetura. Portanto, essas arquiteturas são particularmente adequadas para aplicativos que precisam ser extremamente dimensionáveis, como a plataforma de streaming da Netflix, que deve permitir que milhões de usuários assistam a vídeos simultaneamente. Além disso, com uma arquitetura de microsserviço, é possível desenvolver microsserviços individuais separadamente, o que facilita a distribuição do esforço de implementação entre diferentes equipes de desenvolvedores. 

**Desvantagens da arquitetura de Microsserviços**
Assim como a SOA, uma arquitetura de microsserviço também envolve um grande esforço de desenvolvimento e manutenção. Portanto, essa arquitetura é adequada principalmente para aplicativos que têm um tamanho correspondente e precisam ser extremamente escalonáveis. Configurar uma arquitetura desse tipo com dezenas ou até centenas de microsserviços não faz sentido se você não tiver capacidade de desenvolvimento. 

### 12.2.4 Arquitetura baseada em componente
Se observarmos as duas arquiteturas descritas anteriormente (ou seja, a SOA e a arquitetura de microsserviços), notaremos que, em ambas as arquiteturas, o lado do servidor (o backend) é dividido de tal forma que é possível <span style="background:rgba(240, 107, 5, 0.2)">reutilizar serviços individuais</span>. No entanto, o que ambas as arquiteturas não focam é que <span style="background:rgba(240, 107, 5, 0.2)">o lado do cliente</span> (o frontend) <span style="background:rgba(240, 107, 5, 0.2)">ainda está incorporado na arquitetura</span> como <span style="background:rgba(240, 107, 5, 0.2)">uma grande unidade</span> (ainda estamos lidando com um frontend monolítico, por assim dizer).

É claro que essa abordagem tem suas desvantagens. Se os <span style="background:#b1ffff">componentes da interface do usuário</span> (a seguir chamados apenas de "componentes") não puderem ser reutilizados, então, na pior das hipóteses, cada componente correspondente deverá ser implementado completamente de novo para cada projeto. <span style="background:#d3f8b6">Não seria melhor reutilizar os componentes</span>? Por exemplo, se você implementar um componente para exibir produtos em uma tabela, não seria inteligente estruturar esse componente de forma que ele pudesse ser usado não apenas no aplicativo A, mas também no aplicativo B?

Uma arquitetura que se tornou mais popular nesse contexto nos últimos anos, em que o <span style="background:#b1ffff">frontend é dividido em componentes</span> (ou <span style="background:#b1ffff">widgets</span>) <span style="background:rgba(240, 200, 0, 0.2)">reutilizáveis da interface do usuário</span>, é chamada de <span style="background:#affad1">arquitetura baseada em componentes</span>. A motivação por trás dessa arquitetura é tornas os componentes da interface do usuário reutilizáveis, de modo que possam ser usados em <span style="background:#affad1">diferentes aplicativos sem muito esforço</span>, conforme mostrado na Figura 12.10.

Uma maneira de implementar componentes são os <font color="#f79646">componentes da Web</font>. Esse conjunto de APIs pode ser usado para criar elementos HTML personalizados, reutilizáveis e autoencapsulados para páginas da Web e aplicativos da Web. 

As estruturas de front-end, como React, Angular e Vue, também seguem a arquitetura baseada em componentes: Nessas estruturas, é possível definir HTML, CSS e JavaScript na forma de componentes, que <span style="background:#b1ffff">podem ser reutilizados em diferentes aplicativos</span> (ou várias vezes em um único aplicativo) sem muito esforço.
![[Capítulo 12 - Compreendendo e utilizando arquiteturas Web-9.png]]
**Arquitetura baseada em componentes: Frontend de um aplicativo dividido em vários componentes reutilizáveis.**

### 12.2.5 Arquitetura microfrontends
Em contrates com a arquitetura baseada em componentes, a arquitetura de microfrontends subdivide o frontend em um nível diferente e transfere os conceitos de microsserviços para o mundo do frontend. Com relação aos microfrontends, <span style="background:#d3f8b6">o conceito é dividir o frontend por recursos</span>, cada um dos quais é complementado por um <span style="background:#d3f8b6">microsserviço no backend</span>. 

![[Capítulo 12 - Compreendendo e utilizando arquiteturas Web-10.png]]
**Microfrontends Architecture: Client Side (frontend) dividido em unidades pequenas independentes.**

### 12.2.5 Arquitetura de mensagens
Ao implementar sistemas de software complexos nos quais diferentes componentes ou aplicativos interagem entre si, os sistemas de mensagens ou corretores de mensagens ajudam a evitar o acoplamento entre componentes individuais. 

#### Nota
Nesse contexto, o termo "componentes" não se refere aos componentes da interface do usuário, mas aos componentes do lado do servidor, como serviços, classes e assim por diante, coisas que se comunicam entre si. 

**Direct Communication (Point-to-Point Communication)**
Para esse propósito, vamos primeiro examinar a comunicação sem um sistema de mensagens, conforme mostrado na figura 12.12. Nesse caso, os componentes individuais se comunicam diretamente entre si (comunicação ponto a ponto), ou seja, eles dependem diretamente uns dos outros. Quanto mais canais de comunicação você tiver, mais dependências diretas serão criadas e, como sabemos, essas dependências devem ser evitadas ou minimizadas, por exemplo, porque <span style="background:#affad1">os componentes com muitas dependências</span> são mais difíceis de serem "destacados" do sistema geral e substituídos.
![[Capítulo 12 - Compreendendo e utilizando arquiteturas Web-11.png]]
**Figura 12.12: Comunicação direta entre componentes**

**Comunicação indireta**
Os sistemas de mensagens neutralizam esse problema de ter muitos canais de comunicação ou dependências. <span style="background:#d3f8b6">Em vez de os componentes individuais se comunicarem diretamente uns com os outros</span>, a comunicação ocorre por meio de um <span style="background:#d3f8b6">corretor de mensagens central</span>, que funciona como o componente central para a troca de mensagens. Os componentes individuais podem enviar mensagens para o corretor ou recuperar mensagens dele. <span style="background:#d3f8b6">O corretor garante que as mensagens sejam encaminhadas para o(s) destinatários(s) correto(s)</span>, o que é chamado de roteamento de mensagens.
![[Capítulo 12 - Compreendendo e utilizando arquiteturas Web-12.png]]
**Figura 12.13 Comunicação por meio de um Broker Message**

**Mensagens em uma arquitetura orientada a serviços**
Na SOA, é usado um sistema de mensagens (nesse contexto, também <span style="background:rgba(240, 107, 5, 0.2)">chamado de barramento de mensagens</span>), conforme mostrado na Figura 12.14. Nessa abordagem, você evita dependências diretas entre serviços individuais porque eles não se comunicam diretamente entre si, mas sim por meio do sistema de mensagens. 

#### Nota
Uma arquitetura de microsserviço também pode usar sistemas de mensagens para a comunicação entre microsserviços individuais. No entanto, a arquitetura de microsserviços também tem alternativas que envolvem a comunicação ponto a ponto ou a comunicação por meio de um gateway de API central. Na SOA, por outro lado, um <span style="background:rgba(240, 107, 5, 0.2)">barramento de mensagens é o componente central da arquitetura</span>. 

![[Capítulo 12 - Compreendendo e utilizando arquiteturas Web-13.png]]
**Figura 12.14 SOA: Barramento de mensagem para comunicação entre serviços.**

### 12.2.7 Arquitetura Web Service
No contexto das arquiteturas, ainda precisamos explicar o termo <span style="background:rgba(240, 107, 5, 0.2)">Web Service</span> e, acima de tudo, diferenciá-lo dos serviços em SOA e dos <span style="background:rgba(240, 107, 5, 0.2)">microsserviços.</span> 
Nem a SOA nem os microsserviços especificam a tecnologia na qual os serviços ou microsserviços são desenvolvidos. Embora tenhamos considerado as arquiteturas no contexto de aplicativos da Web, ambas também pode ser aplicadas a outros tipos de aplicativos. 

Os Web Services, por outro lado, são serviços que podem ser acessados pela Web e que usam protocolos (da Web) e formatos (da Web) apropriados para comunicação. Em resumo, quando um <span style="background:#d3f8b6">serviço é disponibilizado na Web</span>, ele é chamado de um <span style="background:#d3f8b6">Web Service</span>.

Os principais #protocolos e padrões usados nos Web Services são #HTTP (Capítulo 5), os formatos de dados #XML (Extensible Markup Language) e #JSON (JavaScript Object Notation) (**consulte o capítulo 6**) e os padrões SOAP (Simple Object Access Protocol) e REST (**Representational State Transfer**), que descreveremos com mais detalhes no **capítulo 16**. 

## 12.3 MV* Architectures
A esta altura, você tem uma boa visão geral das arquiteturas comuns relevantes para o desenvolvimento de aplicativos Web. Nesta seção, quero discutir um grupo de padrões de arquitetura que você certamente encontrará mais cedo ou mais tarde. Esses padrões de arquitetura são as arquiteturas *model-view-controller* (*MVC*), *model-view-presenter* (*MVP*), e *model-view-viewmodel* (*MVVM*), também chamadas de padrões de arquitetura MV* devido aos componentes comuns de modelo e visualização. Esses padrões geralmente estão localizados na camada de #apresentação e definem como ocorre a <span style="background:rgba(240, 200, 0, 0.2)">comunicação entre a interface de usuário</span> (a #view) <span style="background:rgba(240, 200, 0, 0.2)">e os dados</span> (o #model). Todos esses padrões atendem aos mesmos objetivos, em primeiro lugar, o acoplamento frouxo entre os componentes individuais. 

### 12.3.1 Model-View-Controller
O princípio MVC foi introduzido pela primeira vez em 1979 para a linguagem de programação Smalltalk. <span style="background:#affad1">O conceito do MVC é separa</span>r (ou seja, **desacoplar**) o **gerenciamento de dados e a lógica comercial** de um aplicativo da representação ou **apresentação de seus dados**. 
![[Capítulo 12 - Compreendendo e utilizando arquiteturas Web-14.png]]
**Padrão arquitetônica MVC: Camada de apresentação dividida em modelo, visualização e controlador.**

#Controller: são responsáveis pela lógica da aplicação e **coordenação** da interação entre a #view e a #model. Usuário interagem com a view, por exemplo, para inserir textos ou clicar em um botão; o controller verifica a validade da entrada do usuário, processa-a e atualiza os dados no modelo;

A vantagem dessa subdivisão e da dissociação da visualização do modelo é que o modelo dos dados é independente da representação dos dados. Por um lado, esse desacoplamento facilita a implementação de diferentes exibições para os mesmos dados, evitando a necessidade de adaptar o modelo correspondente. Por outro lado, as visualizações e os modelos podem ser testados (e implementados) com muito mais facilidade se estiverem isolados uns dos outros. 

**Model-View-Controller em aplicações Web Clássicas**
O MVC foi originalmente projetado para aplicativos de desktop: portanto, o padrão existe há mais tempo do que os aplicativos da Web. No entanto, o #MVC **também é amplamente usado em relação aos aplicativos da Web**. No entanto, é preciso fazer uma distinção entre os aplicativos da Web clássicos e os aplicativos da Web modernos. 

Em aplicações web clássicas, o MVC são implementados em sua maioria, como:
![[Capítulo 12 - Compreendendo e utilizando arquiteturas Web-15.png]]
Os três componentes que formam o padrão estão localizados inteiramente na camada de apresentação no lado do servidor. O procedimento é o seguinte: Primeiro, o cliente faz uma solicitação HTTP por meio do navegador (solicitação HTTP), que é recebida e processada por um mecanismo de roteamento no lado do servidor. A tarefa do mecanismo de roteamento é usar a solicitação HTTP para selecionar o controlador apropriado para processar a solicitação. O mecanismo de roteamento usa informações como o URL, o cabeçalho da solicitação e os parâmetros da solicitação HTTP.

Conforme mencionado anteriormente, as tarefas de um controller incluem a validação dos dados recebidos, a atualização do modelo e a criação ou atualização da visualização que é enviada de volta ao cliente. Muitas vezes, também é usado um mecanismo de modelo, que gera o código HTML com base em um modelo de visualização (ou seja, um modelo HTML) e os dados reais do modelo, que são enviados de volta ao cliente como parte da resposta HTTP. 

**MVC em aplicações modernas**
Os aplicativos modernos da Web são caracterizados pelo fato de que **a maior parte da lógica** de **apresentação** **ocorre no lado do cliente**.  Muitas vezes, esses aplicativos são implementados como aplicativos de página única, ou seja, aplicativos que consistem em uma página da Web básica que é modificada dinamicamente pelo JavaScript. 

Essa mudança na camada de apresentação também altera o padrão MVC. O controller agora lida com a comunicação com o servidor, por exemplo, para carregar dados para o modelo na forma de JSON ou XML ou, inversamente, para enviar dados para o servidor, e atualiza a exibição usando a API DOM (possivelmente com a ajuda de mecanismos de roteamento e de modelos do lado do cliente).

### Model-View-Presenter

O desacoplamento do modelo da visualização não é totalmente consistente com o MVC: assim, uma conexão entre a visualização e o modelo entra em ação sempre que a visualização precisa reagir a alterações no modelo e ajustar a representação dos dados.

O padrão de arquitetura MVP é baseado em MVC mas é mais consistente quando desacoplamos a view e o model (data). view e model estão completamente desacopladas, e a view não tem acesso ao model como tem no MVC. 

A comunicação entre a visualização e o model ocorre exclusivamente por meio do terceiro componente, o apresentador, que é mais ou menos comparável a um componente controlador no MVC. Entretanto, um apresentador também garante que a visualização seja notificada das alterações no modelo de dados. 

### 12.3.3 Model-View-Viewmodel
MVVM é uma variação dos padrões de arquitetura MVC e MVP. Assim como o MVC e o MVP, o MVVM tem uma visualização e um modelo. No entanto, o desacoplamento da visualização e do modelo é realizado pelo viewmodel.

A ideia por trás do MVVM é fornecer campos de dados correspondentes no modelo de visualização para cada elemento da UI da visualização que pode ser alterado pelo usuário. Esses campos de dados são vinculados à visualização por meio de vínculos de dados bidirecionais. Se o valor de um elemento da interface do usuário for alterado por um usuário (por exemplo, o texto em um campo de texto ou uma seleção em uma lista suspensa), o campo de dados correspondente no modelo de visualização será atualizado automaticamente. Por outro lado, o valor de um elemento da interface do usuário é ajustado quando o valor do campo de dados correspondente no modelo de exibição é alterado (portanto, a associação é bidirecional).



