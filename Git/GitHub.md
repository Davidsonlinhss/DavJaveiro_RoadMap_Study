# Comandos Básicos
**git init
- <span style="background:#d4b106">Função</span>: comando para iniciar um repositório do git, uma vez que já está dentro da pasta;
- <span style="background:#d4b106">O que faz</span>: esse comando criará um subdiretório oculto `.git` na pasta atual, que conterá todos os arquivos necessários para o repositório Git.

**git clone
- Função: clona um repositório Git usando a chave SSH;
```Git
git clone git@github.com:Davidsonlinhss/NOME_REPOSITÓRIO
```

**git add .
- <span style="background:#d4b106">Função</span>: adiciona todos os arquivos da pasta atual na área de #stage ou **staging área**, nessa área nós marcamos um 'snapshot' das modificações realizadas no nosso código antes de confirmarmos essas mudanças em nosso código, <span style="background:#d2cbff">até esse ponto as modificações não foram realizadas de forma permanente</span>;
- <span style="background:#d4b106">Quando usar</span>: sempre que tivermos feitos modificações em nossos arquivos e quisermos incluí-las no próximo #commit

**git commit**
- <span style="background:#d4b106">Função</span>: cria um registro das mudanças que foram realizadas e adicionadas na área de #stage.
- <span style="background:#d4b106">O que faz</span>: gravamos as mudanças no repositório local com uma mensagem descritiva. 
- <span style="background:#d4b106">Quando usar</span>: após adicionarmos todas as mudanças que queremos incluir em um ponto de restauração ( #commit).
- Exemplo:
```git
git comnmit -m "Mensagem do commit"
```

**git push**
- <span style="background:#d4b106">Função</span>: envia os commits do nosso repositório local para um repositório remoto (servidor);
- <span style="background:#d4b106">O que faz</span>: atualiza o repositório remoto com os commits feitos no repositório local;
- <span style="background:#d4b106">Quando usar</span>: depois de fazer commits no repositório local e querer compartilhar essas mudanças com outros ou fazer backup no repositório remoto.

**.gitignore**
É um arquivo que indica o que não deve ser salvo pelo Git.
Geralmente o arquivo .gitignore fica salvo na pasta principal do repositório. Mas também é possível salvar outros arquivos .gitignore em subpastas do repositório, para indicar o que deve ser ignorado por cada subpasta. 

.**git checkout -- .**
Remove todas as modificações que foram feitas, mas que não estão na área de #stage git add .;

## git remote add origin <URL>
Usamos para associar um repositório online com um repositório local. 
