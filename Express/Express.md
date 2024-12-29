## Estrutura do capítulo
- Visão geral sobre o Express.js;
- Instalando o Express;
- Como o Express funciona;
- Adicionando rotas no Express;
- Objetos de requisição e resposta;
- Utilizando Middleware dentro da rota;
- Servindo arquivos estáticos;;
- Express.js Application Generator;

### Express.js Overview

O Express.js é uma ferramenta de código aberta para o #desenvolvimento de aplicações web utilizando o ecossistema Node.js. Isso ajuda no encapsulamento complexo de código e também auxilia os desenvolvedores a escreverem códigos focados em aplicações.

Nós podemos criar uma #single-page, #multi-page, ou um site #hybrid através do uso do Express.js. O Express é leve e pode ser utilizado para desenvolver aplicações web do lado do servidor que são altamente #escaláveis e #sustentáveis. 

Podemos criar qualquer aplicativo empresarial usando uma tecnologia baseada em JavaScript chamada de pilha de software ( #stack) #MEAN ou #MERN, que significa MongoDB, Express.js, Angular/React e Node.js. 

Recentemente, a #MEVN stack surgiu e tem ganhado popularidade entre a comunidade desenvolvedora. Essa stack inclui #MongoDB, #Express, #Vue e #Node. 
1. MongoDB;
2. Express.js
3. Vue.js
4. Node.js
O Express.js é muito utilizado para o backend e fornece alguns dos componentes core tal como #roteamento, #sessões, #HTTP, #requisições, #tratamento de erros, #registro e muito mais...

### Principais recursos do Express.js

A seguir, são os principais recursos para se utilizar o Express.js:
1. #Middleware: tem o recurso interessante de usar qualquer middleware embutido ou definido pelo cliente para gerenciar o processamento do pipeline de solicitação e resposta. Isso ajuda a organizar e encapsular alguns dos componentes reutilizáveis nos aplicativos. Por exemplo, podemos implementar uma maneira centralizada de tratar erros e registrar a lógica em todo o aplicativo. 
2. #Roteamento: possui um avançado mecanismo de roteamento que é utilizado para gerenciar URLs além de realizar o gerenciamento de estado de aplicativos. Usando os módulos de roteamento, nós podemos definir rotas estáticas bem como rotas dinâmicas que pode sem altamente escaláveis para buildar aplicações em tempo real. 
3. #Autenticação e #autorização: as aplicações web são utilizadas através da internet e nós precisamos garantir que as aplicações estejam seguras e protegidas para serem utilizadas pelos clientes. O Node.js fornece um ótima ferramente para implementar a autenticação e autorização. Alguns desses pacotes populares utilizados para esse propósito icluem o jsonwebtoken, bcryptjs e passportjs.

### Benefícios do Express.js
1. Fácil de aprender e desenvolver: é muito fácil de aprender o framework Express.js, na qual é baseada em JavaScript. Com a popularidade do TypeScript, nós podemos desenvolver as aplicações Node em TypeScript também.  
2. Baixa manutenção;
3. Desenvolvimento leve e rápido;
4. Implementações automatizadas: com a ajuda dos DevOps, nós podemos facilmente automatizar a construção e o deploy de aplicativos de servidores desenvolvidos utilizando o Express.js com o mínimo de configuração.


### Como o Express.js funciona?
Existem quatro estágios na qual a aplicação Express.js trabalha:
1. Criando uma nova aplicação Express;
2. Adicionando novas rotas;
3. Iniciando um servidor HTTP em uma determinada porta;
4. Entrega de request/response - solicitação/resposta;

No exemplo anterior, não criamos um código que exibiu uma mensagem básica "Hello, my server using Express" na janela do navegador. Vamos entender cada uma das linhas importantes do código:

**Importando o módulo do Express:**
```js
const express = require('express');

```

**Criando o objeto da aplicação usando express()**
```js
const app = express()
```

**Adição de uma rota GET padrão para ouvir a solicitação de entrada**

O roteamento no express é utilizado para tratar as requisições do cliente, e nós podemos conectar essas rotas com a aplicação Express.
- Nós definimos a rota get(), na qual ela será a raiz da aplicação web, e uma função callback deve ser fornecida para tratar todas as solicitações do cliente. Após o processamento, o objeto de resposta é retornado:
```js
app.get('/', (req, res) => {

})
```

Dentro da função callback de uma rota, nós retornamos a resposta para o cliente na forma de String, JSON, ou qualquer outro formato. Isto é feito chamando o método res.send('Hello, My server using Express');

O método app.listen é usado no framework Express.js para iniciar um servidor HTPP e escutar por conexões em um determinado endereço IP e porta. Ele é frequentemente utilizado para iniciar um servidor Express e torná-lo disponível para receber solicitações HTTP.
```node
const express = require('express')
const app = express()

const port = 3000

app.liten(port, () => {
	console.log(`Servidor Express iniciado na porta ${port}.`)
})
```

Neste exemplo, app.listen é chamado para iniciar o servidor Express.
O primeiro parâmetro é o número da porta onde o servidor escutará por conexões.
O segundo parâmetro (opcional) é uma função de retorno de chamada que será executada uma vez que o servidor estiver escutando por conexões. Geralmente essa função é usada para registrar que o servidor foi iniciado com sucesso.

