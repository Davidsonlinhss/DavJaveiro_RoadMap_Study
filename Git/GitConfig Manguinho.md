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

