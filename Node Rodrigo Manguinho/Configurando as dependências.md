## Configurando o Git
Configurando o branch para ser "master" em todos os novos repositórios que criarmos;
1. git config --global init.defaultBranch master
 Ao executarmos o comando acima, todos os novos repositórios que criarmos serão inicializados com a brach "master" como a branch ativa;

Isso significa que quando fizermos um commit em um novo repositório, nossas alterações serão adicionadas à branch "master";

Se quisermos utilizar uma branch diferente como a branch padrão, podemos utilizar o comando git checkout para alternar para ela. 



//---------//
2. Conectar a conta Git:
nome de usuário:
git config --global user.name "nome"

email: 
git config --global user.email "email"


<span style="background:#ff4d4f">Comandos para o git</span>
3. <span style="background:#affad1">git init</span>: inicia um novo repositório Git em um diretório específico. 

4. <span style="background:#affad1">git add</span>: adiciona um arquivo ao índice (staging area) para ser commitado;

5. <span style="background:#affad1">git log</span>: é utilizado para obter o histórico de commits em um repositório git
 
6. <span style="background:#affad1">git config --global core.editor code</span>: configurando o editor padrão do git para ser o vs code

7. <span style="background:#affad1">git config --global --edit</span>: abre o arquivo de configuração global do Git no editor padrão, no caso, o vs code;

## Configurando o alias
<span style="background:#affad1">c = !git add --all && git commit -m</span>: 
1. Adiciona todas as mudanças nos arquivos do diretório de trabalho ao índice (staging area).
2. Cria um novo commit com uma mensagem de commit especificada após a flag `-m`.

Então, quando você digitar `git c "Sua mensagem de commit"`, ele irá adicionar todas as alterações e, em seguida, criar um commit com a mensagem especificada.

<span style="background:#affad1">s = !git status -s</span>
1. Este comando exibe o status dos arquivos no seu repositório de forma resumida, mostrando apenas os arquivos modificados (M), arquivos adicionados (A), arquivos excluídos (D), entre outros.

<span style="background:#affad1">l = !git log --pretty=format:'%C(blue)%h%C(red)%d %C(white)%s - %C(cyan)%cn, %C(green)%cr'</span>
1. Este comando exibe o histórico de commits do repositório com um formato personalizado.

<span style="background:#affad1">amend = !git add --all && git commit --amend --no-edit</span>
1. Adiconará todas as mudanças ao commit mais recente sem alterar a mensagem do commit. Isso é útil para <span style="background:#d4b106">adicionar alterações esquecidas ou corrigir pequenos erros em um commit recente</span>.





## Iniciando o nosso projeto
```node
npm init -y 
```
Criando o package.json para iniciar o package.json;

Após isso, vamos realizar o primeiro commit, ele será responsável por demonstrar o ponto inicial, onde iniciamos o nosso package.json

//----------------------//
Mas para isso, nós utilizaremos o #convetional #Commits:

1. #fix: um commit do tipo *fix* <span style="background:#affad1">soluciona um problema na sua base de código</span> (isso se correlaciona com PATCH do versionamento semântico).
2. #feat: um commit do tipo *feat* inclui um <span style="background:#affad1">novo recurso na sua base de código</span> (isso se relaciona com MINOR do versionamento semântico). 

	<span style="background:#d4b106">Tipos adicionais</span><span style="background:#d4b106">:</span>
	1. #build; 
	2. #chore: <span style="background:#affad1">adicionamos alguma configuração nova no nosso projeto, algum setup, alguma biblioteca nova;</span>
	3. #ci; 
	4. #docs:<span style="background:#affad1"> criação ou mudança de documentação; </span>
	5. #style; 
	6. #refactor; 
	7. #perf; 
	8. #test;



Logo, realizamos o nosso primeiro commit do projeto:

```js
git c "chore: add npm"
```

Após, iremos instalar o "<span style="background:#d3f8b6">git-commit-msg-linter</span>": "^3.2.8", o git-commit-msg-linter <span style="background:#affad1">é uma ferramenta que ajuda a garantir a consistência e a qualidade das mensagens de commit em um repositório Git</span>. Especificamente, ele é projetado para verificar se as mensagens de commit seguem determinadas convenções ou padrões pré-definidos.

<span style="background:#ff4d4f">**ATENÇÃO**</span>
Não commitamos a pasta node-modules, portanto, criamos o arquivo <span style="background:#d3f8b6">.gitignore</span> e adicionamos o /node_modules/.


## Typescript 
O próximo passo será adicionar o TypeScript e seus complementos; todos em ambiente de desenvolvimento:

```node
npm i -D typescript@^4.4.3 @types/node@^16.9.1
```

O TypeScript é  uma linguagem compilada, o JasvaScript se torna tipada; isso evita a cometermos erros bobos, visto que o JavaScript é extremamente dinâmico. O #Typescript permite que a gente utiliza recursos do JavaScript que ainda não foram implementados. 

Logo, iremos adicionar o <span style="background:#affad1">tsconfig.json</span>, arquivo de configuração do #Typescript.
```typescript
{

    "compilerOptions": {

        "outDir": "./dist",

        "module": "CommonJS",

        "target": "ES2019",

        "esModuleInterop": true,
      

    }

}
```

O TypeScript <span style="background:#affad1">vai compilar o nosso código</span> e vai gerar JavaScript no final, que é o que o Node.js compreende. Onde vamos gerar esses arquivos Js no servidor?

## Eslint With TypeScript

O Eslint é uma ferramenta de linting para JavaScript. Ele é usado principalmente para ajudar os desenvolvedores a manter um código JavaScript limpo, consistente e livre de erros. Aqui estão algumas das principais funcionalidades e usos do ESLint:
1. <span style="background:#d4b106">Identificação de erro de código</span>: o ESLint verifica o código JavaScript em busca de erros comuns, como erros de sintaxe, variáveis não definidas e outros problemas que podem ocasionar bugs.
2. <span style="background:#d4b106">Padronização do código</span>: o ESLint permite que os desenvolvedores definam regras personalizadas para o estilo de codificação, como convenções de nomeclatura, estilo de identação e espaçamento. <span style="background:#affad1">Isso ajuda a garantir que o código seja consistente em todo</span> o projeto e entre os membros da equipe.
3. <span style="background:#d4b106">Prevenção de práticas inadequadas</span>: o ESLint pode identificar práticas de codificação inadequadas que podem levar a problemas de desempenho, segurança ou legibilidade do código.
4. <span style="background:#d4b106">Integração com ferramentas de desenvolvimento</span>: o ESLint pode ser facilmente integrado a fluxos de trabalho de desenvolvimento, como sistemas de controle de versão (por exemplo, Visual Studio Code), fornecendo feedback instantâneo aos desenvolvedores sobre possíveis problemas no código enquanto eles estão escrevendo.



Precisamos instalar os seguintes plugins para conseguir usar o Eslint, visto que estamos usando no typeScript
```node
npm i --save-dev eslint@^7.32.0  eslint-config-standard-with-typescript@^21.0.1 eslint-plugin-import@^2.24.2 eslint-plugin-node@^11.1.0 eslint-plugin-promise@^5.1.0 eslint-plugin-standard@^5.0.0 @typescript-eslint/eslint-plugin@^4.31.1
```

Instalar o plugin do EsLint no vs code. E criar o arquivo de configuração do ESLint: <span style="background:#affad1">.eslintrc.json</span> 
```node

```

## Configurando o #husk
A versão utilizada do husk é a seguinte:
```node
"husky": "^7.0.2"
```

Mas afinal, qual a finalidade do husk? Com ele, podemos executar scripts personalizados automaticamente em determinados pontos do ciclo de vida do Git. Isso é útil para automatizar tarefas comuns, <span style="background:#affad1">como execução de testes</span>, <span style="background:#affad1">linting de código</span>, formatação de código, entre outros, antes de confirmar (commit) ou enviar (push) alterações para o repositório.

Podemos configurar o Husky, por exemplo, para:
1. Executar <span style="background:#d4b106">testes automatizados</span> antes de confirmar suas alterações;

2. Garantir que seu código atenda a determinados padrões de estilo (Usando ESLint, por exemplo) antes de confirmar:

4. Impedir confirmações que não seguem as covenções de mensagem de confirmação (commitlint)

## Instalação do linting-stage

Permite que rodemos script somente nos arquivos que estão na nossa <span style="background:#affad1">staged-area</span>, são os arquivos que vão entrar no commit; com isso, não precisamos rodar comando em todos os nossos arquivos, somente nos arquivos que vão entrar no commit. 

## A próxima biblioteca de teste a ser instalada é o Jest

A biblioteca Jest é uma estrutura de teste de código aberto muito popular projetada para testar aplicações JavaScript. Desenvolvida pelo Facebook, o Jest é especialmente conhecido por sua simplicidade, velocidade e capacidade de configurar testes de forma rápida e fácil. Aqui estão algumas das principais funcionalidades e usos do Jest:
1. <span style="background:#d4b106">Testes de unidade</span>: O Jest é ideal para escrever e executar testes de unidade em código JavaScript; Ele fornece uma sintaxe simples e clara para escrever, incluindo funções de asserção para verificar os resultados esperados.
2. <span style="background:#d4b106">Testes de integração</span>: além de testes de unidade, o Jest também suporta testes de integração. Isso permite testar o comportamento de componentes mais complexos ou módulos interagindo entre si.
3. <span style="background:#d4b106">Mocking</span>: o Jest oferece uma funcionalidade poderosa de mock para simular 
4. <span style="background:#d4b106">**Snapshot testing**</span>: O Jest introduziu o conceito de "snapshot testing", onde você pode capturar o estado de uma saída renderizada, como um componente React, e compará-lo com um snapshot previamente salvo. Isso é útil para detectar regressões de UI.

5. <span style="background:#d4b106">**Convenções padrão**</span>: O Jest é configurado com convenções padrão sensíveis, o que significa que você pode começar a escrever testes imediatamente sem a necessidade de configuração extensa.

6. <span style="background:#d4b106">**Suporte para TypeScript**</span>: O Jest tem suporte embutido para testes de código TypeScript, tornando-o uma escolha popular para projetos TypeScript.

7. **Integração contínua**: O Jest pode ser facilmente integrado a pipelines de integração contínua (CI) para automatizar a execução de testes em cada commit ou pull request, garantindo que o código esteja sempre em um estado testado e funcional
Parei em 27.25