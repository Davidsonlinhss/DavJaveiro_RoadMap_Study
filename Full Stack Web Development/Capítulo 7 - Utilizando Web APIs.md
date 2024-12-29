*Os navegadores oferecem várias interfaces que podem ser controladas por meio de um programa, ou seja, via JavaScript. Neste capítulo, descreverei várias dessas chamadas APIs (Application Programming Interface, interfaces de programação de aplicativos) da Web que podem ser controladas por JavaScript e usadas para tornar as páginas da Web ainda mais interativas e profissionais. *

Este capítulo será dividido em três partes:
1. A primeira parte é sobre a API Document Object Model (DOM) que pode ser usada para acessar elementos individuais de um documento (ou página da web) em Hypertext Markup Language (HTML), **adicionar novos elementos**, **modificar ou excluir elementos** existentes e muito mais. O conhecimento da API do DOM é um requisito para qualquer desenvolvedor da Web.

2. Na segunda parte do capítulo, mostrarei como você pode usar o Ajax e a API Fetch para carregar dados de forma assíncrona do servidor usando JavaScript. Esse recurso permite carregar ou atualizar áreas individuais de um aplicativo da Web.

3. Além disso, há uma verdadeira riqueza de outras APIs da Web disponíveis para você nos navegadores modernos. Como desenvolvedor da Web, você deve ter pelo menos uma visão geral de quais APIs existem e o que é possível fazer com os navegadores modernos. Essa é a única maneira de avaliar rapidamente se e como determinados requisitos de um aplicativo da Web podem ser implementados em projetos. Por exemplo, você pode usar **APIs da Web** para **armazenar dados localmente no navegador**, **criar animações**, **acessar o sistema de arquivos**, **aplicar algoritmos de criptografia** e muito mais. Na terceira parte deste capítulo, apresentarei uma visão geral compacta das APIs da Web mais importantes.

![[Capítulo 7 - Utilizando Web APIs.png]]
**Figura 7.1 Você pode utilizar Web APIs com JavaScript para adicionar diversos recursos em suas aplicações Web.**

## 7.1 Modificando páginas da Web dinamicamente utilizando DOM API
Sempre que você acessa uma página da Web no navegador, o navegador faz uma solicitação ao servidor via HTTP, e o servidor envia o código HTML de volta ao navegador; esse código HTML é analisado pelo navegador em seu próprio modelo de objeto, que é mantido na memória. Esse modelo de objeto é chamado de DOM (Document Object Model), e você pode acessá-lo usando JavaScript.

### 7.1.1 O DOM
O DOM representa os componentes de uma página da Web (ou seja, os elementos HTML e os atributos HTML) hierarquicamente como uma árvore, a chamada árvore DOM. Essa árvore DOM é composta de nós, cuja disposição reflete a estrutura de uma página da Web, conforme mostrado na figura 7.3. A **API do DOM define**, portanto, uma interface de programação para acessar a árvore DOM por meio de um programa.

#### Nota
Uma API é uma interface de programação que fornece vários objetos e métodos que, por sua vez, devem estar presentes nas implementações (ou seja, as implementações reais da respectiva interface). As implementações podem diferir entre si; o importante é que a interface seja respeitada.

A API DOM fornece um conjunto de objetos (com métodos) por meio dos quais o conteúdo de uma página da Web (ou, de forma mais geral, o conteúdo de documentos HTML) podem ser acessados. Existem implementações da API DOM para várias linguagens de programação (incluindo Java, Python e C#). Entretanto, nas seções a seguir, vamos nos concentrar na implementação para JavaScript, que está implicitamente disponível em todos os navegadores.

![[Capítulo 7 - Utilizando Web APIs-1.png]]

### 7.1.2 Os diferentes tipos de Nodes
Os nós de uma árvore DOM podem ser divididos em diferentes categorias ou tipos. Há um total de doze tipos diferentes de nós, quatro dos quais são particularmente importantes para o início (a esse respeito, você também pode comparar o código HTML da Listagem 7.1).

- O **nó do documento** (mostrado com uma borda em negrito na figura 7.4) representa toda a página da Web e forma a raiz da árvore DOM (por isso é frequentemente chamado de nó raiz). Esse nó é representado pelo objeto #document, que também é o objeto de entrada para todo trabalho com DOM.
- Os **nós de elementos** (mostrados com fundo branco), representam elementos HTML individuais de uma página da Web. Em nosso exemplo, temos os elementos #main, #h1, #table, #thead, #tbody, por exemplo.
- Os **nós de atributo** (mostrados cercados por linhas tracejadas e com um fundo branco) representam atributos de elemento HTML, neste exemplo, os nós de atributos são #lang, #id e #summary.
- O texto dentro dos elementos HTML é representado por seu próprio tipo de nó, chamado de **nó de texto** (mostrado cercado por linhas tracejadas e colorido em cinza na figura 7.4). Em nosso exemplo, temos os nós para os textos "Exemplo de lista de contatos", "Lista de contatos", "Primeiro Nome", "Sobrenome" e "Endereço de e-mail". Observe que nem todos os nós de texto são mostrados por motivos de espaço. 