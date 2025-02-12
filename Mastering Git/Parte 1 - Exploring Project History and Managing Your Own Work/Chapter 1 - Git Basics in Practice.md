Nessa primeira parte, você começara aprendendo os conceitos básicos do para utilizar Git em um exemplo simples. Em seguida, aprenderemos como utilizá-lo para responder a perguntas sobre o projeto e seu histórico. Aprenderemos a examinar o estado na nossa #worktree, gerenciar alterações e criar um *commit* adequado.

---
Este livro é destinado a usuários intermediários e avançados do Git, para ajudá-los em seu caminho para dominar o Git. Portanto, os capítulos seguintes a este assumirão que conheçamos os conceitos básicos do Git e já avançamos além do estágio iniciante.

Este capítulo servirá como um lembrete dos conceitos básicos de controle de versão com o Git. O foco será fornecer aspectos práticos da tecnologia, mostrando e explicando operações básicas de controle de versão no exemplo do desenvolvimento de um projeto de exemplo e da colaboração entre dois desenvolvedores.

Neste capítulo, abordaremos o seguinte:
- Uma breve introdução ao controle de versão e ao Git;
- Configuração de um ambiente Git e de um repositório Git (#init e #clone);
- Adição de arquivos, verificação de status, criação de commits e exame de histórico;
- Interação com outros repositórios Git ( #pull e #push);
- Criação e listagem de branches, troca para uma branch e mesclagem de alterações;
- Resolução de um conflito de merge simples;
- Criação e publicação de uma tag.

## Techinical Requirements
Para seguir os exemplos mostrados neste capítulo, você precisará do Git: [https://git-scm.com/](https://git-scm.com/).  
Você também precisará de um shell interativo (por exemplo, Git Bash se estiver usando Windows), um editor de texto ou uma IDE para desenvolvimento web (para editar JavaScript e HTML) e um navegador da web.

## A brief introduction to version control and Git
Um sistema de controle de versão (às vezes chamado de controle de revisão) é uma ferramente que permite rastrear o histórico dos arquivos do seu projeto ao longo do tempo (armazenados em um repositório) e ajuda os desenvolvedores da equipe a trabalhar juntos. Sistemas modernos de controle de versão fornecem a cada desenvolvedor sua própria "**área de testes**" (sandbox), evitando o que o trabalho em andamento entre em conflito e, ao mesmo tempo, oferecendo um mecanismo para **mesclar alterações** e sincronizar o trabalho. Eles também permitem alternar entre diferentes linhas de desenvolvimento, chamadas de *branches*, esse mecanismo permite que o desenvolvedor **mude**, por exemplo, de trabalhar na **introdução de uma nova funcionalidade** passo a passo **para corrigir um bug em uma versão mais antiga** e lançada do projeto. 

Sistemas de controle de versão distribuídos (como o Git) fornecem a cada desenvolvedor sua própria cópia do histórico do projeto, chamada de #clone de um repositório. Isso é o que torna o Git rápido, pois quase todas as operações são realizadas localmente. É também o que torna o Git flexível, pois podemos configurar repositórios de várias maneiras. Repositórios destinados ao desenvolvimento também fornecem uma **área de trabalho** separada (ou #worktree) com os arquivos do projeto para cada desenvolvedor. 

O modelo de branching do Git permite a criação de branches locais de forma eficiente, possibilitando a alternância de contexto ao criar "áreas de testes" para diferentes tarefas. Ele também torna possível usar um fluxo de trabalho muito flexível com *topic branches* para colaboração.

#sandbox é um ambiente isolado onde podemos fazer alterações no código sem afetar o código principal ou o trabalho de outras pessoas. No Git, isso é feito usando #branches (ramificações). Cada branch é como uma cópia separada do código onde podemos desenvolver novas funcionalidades, corrigir bugs ou testar ideias sem interferir na branch principal (como #main ou #develop).

#worktree é a pasta ou o diretório onde trabalhamos com os arquivos do projeto. Ele contém os arquivos que estamos editando no momento, refletindo o estado atual da branch em que estamos.

- No Git, o worktree é onde fazemos as alterações nos arquivos, testamos o códigos e preparamos os commits. Ele é separado do repositório Git em si (que armazena o histórico de commits e branches).
- **Exemplo:** se clonamos um repositório Git, a pasta que aparece no nosso computador é o nosso worktree. É nela que editamos os arquivos e vemos as mudanças em tempo real.

O fato de todo o histórico estar acessível permite um *"desfazer de longo prazo"*, voltar à última versão funcional, entre outras coisas. O rastreamento automático da autoria das mudanças torna possível descobrir quem foi responsável por qualquer área específica do código e quando cada alteração foi feita. Podemos comparar diferentes revisões, voltar à revisão em que um usuário relatou um bug e até mesmo descobrir automaticamente qual revisão introduziu um bug de regressão (com o comando *git bisect*). O rastreamento das mudanças nas pontas das branches com o *reflog* permite um "desfazer" e recuperação fáceis.

Uma característica única do Git é que ele permite acesso explícito à *área de staging* (ou índice) **para criar commits** (novas revisões - ou seja, novas versões de um projeto). Isso traz flexibilidade adicional para gerenciar sua área de trabalho e decidir sobre a forma de um commit futuro.

Toda essa flexibilidade e poder têm um custo. Não é fácil dominar o uso do Git, embora seja bastante fácil aprender seu uso básico. 

A #stating-area é uma área intermediária no Git onde preparamos as alterações que desejamos incluir no próximo *commit*. Ela funciona como um "meio-termo" entre o nosso #worktree (onde editamos os arquivos) e o repositório Git (onde as alterações são salvas permanentemente no histórico).

Na **Staging Area**, nós selecionamos quais alterações desejamos incluir no próximo commit usando o comando *git add*. A staging area permite que decidamos **o que** será commitado, dando controle sobre o que será salvo no histórico.

No IntelliJ, precisamos adicionar as alterações à staging area de **forma explícita**. 

## Git By Example
Vamos acompanhar um exemplo simples, passo a passo e seção por seção, de dois desenvolvedores utilizando o Git para trabalhar juntos em um projeto simples. Podemos encontrar todos os três repositórios (para os dois desenvolvedores e o repositório bare do servidor) com arquivos de código de exemplo, onde é possível examinar o código, o histórico e o #reflog.

#reflog é uma abreviação de *reference log*, uma funcionalidade **poderosa** do Git que registra todas as mudanças feitas nas referências (como branches e HEAD ) no repositório local. Ele funciona como um histórico de tudo o que aconteceu no nosso repositório, mesmo que essas mudanças não estejam mais visíveis no histórico principal do Git.

O #reflog armazena um registro das alterações locais, como:
- Commits realizados;
- Mudanças de branch;
- Resetes ou reverts;
- Checkouts
- Rebase ou merge
Ele é específico para o repositório local e não é compartilhado com outros desenvolvedores ou repositórios remotos. 

Para visualizar o #reflog, basta realizarmos um *git reflog* no repositório do projeto.

---
Para seguir este exemplo do processo de desenvolvimento em equipe em um único computador, você pode simplesmente criar três pastas chamadas, por exemplo, **alice/** , **bob/** e **server/** , e alternar para a pasta apropriada quando estiver acompanhando o trabalho feito por Alice, Bob e Carol, respectivamente.
Há algumas mudanças simples que você precisa fazer para que essa simulação funcione:

1. **Ao criar um repositório como Carol** , você não precisa criar e mudar para o diretório `/srv/git`, então pode simplesmente pular esses comandos.
    
2. **No papel de Alice ou Bob** , você precisa criar identidades separadas na configuração local do repositório. Isso pode ser feito usando o comando `git config` sem a opção `--global` ou editando o arquivo `.git/config` no repositório apropriado.
    
3. **Em vez da URL do repositório** `https://git.company.com/random`, que não existe, use o caminho para o repositório do servidor: `../server/random.git`.
    
4. **Se você planeja mover o diretório** que contém as subpastas **alice/** , **bob/** e **server/** , precisará editar a URL do repositório "origin" que está armazenada nos arquivos de configuração do repositório, alterando-a de um caminho absoluto para um caminho relativo — especificamente, `../../server/`.