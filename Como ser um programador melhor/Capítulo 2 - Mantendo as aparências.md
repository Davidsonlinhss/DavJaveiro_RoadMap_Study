Nós nos importarmos com código. E, naturalmente, nos importarmos com a estética do código; é o que de imediato determina o nível de facilidade que teremos para trabalhar com uma seção do código. Praticamente todo livro sobre programação tem uma apresentação. 

A *lei de Goodlife* afirma que à medida que qualquer discussão sobre layout de código aumenta, a probabilidade de ela se tornar uma discussão infrutífera se aproxima de um.

Bons programadores se importam muito com uma boa apresentação do código. Porém eles estão acima desse tipo de disputa. 

## A apresentação é eficaz
Não podemos fingir que a formatação do código não seja importante. Mas saiba por quê. Uma boa formatação não é aquela que a gente acha ser a mais bonita. Não organizamos código para pôr em prática nossos profundos conhecimentos artísticos. 

Um bom código é *consistente*. O layout é quase invisível. Uma boa apresentação não chama a atenção nem provoca distrações; ela serve somente para revelar o propósito do código. Isso reduz o esforço necessário para manter o código.

**PONTO-CHAVE**: uma boa apresentação de código revela seu propósito. Não é um empreendimento artístico. 

A boa apresentação tem como propósito evitar erros em nosso código e não apenas para a beleza. Consideramos o seguinte trecho:
```java
boolean ok = thisCouldGoWrong();
if (!ok)
    System.err.println("Error: exiting...");
System.exit(0);
```

**Ausência de Chaves *{}*** em Java, como em C, quando omitimos as chaves *{}* após uma instrução condicional *if*, apenas a primeira linha após o *if* é considerada parte do bloco condicional.
**Comportamento Indesejado** no código acima, a chamada *System.exit(o)* está fora do escopo do *if*, o que significa que ela será executada **independentemente** do valor de *ok*. O programa sempre será encerrado com *System.exit(0)*. 

Os nomes têm um efeito profundo semelhante. Nomes ruins podem fazer mais do que simplesmente distrair: podem ser muito perigosos. Qual desses nomes é ruim?
bool numberOfGreenWidgets;
string name;
void turnGreen();

**PONTO-CHAVE:** precisamos de uma boa apresentação para evitar erros no código, e não para criar uma arte ASCII bonita. 

Se os autores não tiveram cuidado com o layout, provavelmente não tiveram nenhuma preocupação com outras questões vitais relacionadas à qualidade (por exemplo, um bom design, testes abrangentes etc).

## É uma questão de comunicação
Escrevemos código para dois público-alvo. 
O primeiro é o *compilador* ou o run-time. Ele não se importa se o seu código é antigo, ruim e mal escrito.

O código também será lido por humanos. Estes incluem:
- Eu, o código deve estar claro como água para que não cometamos erros de implementação.
- Eu, algumas semanas (ou meses) depois, quando preparar o software para que uma versão seja disponibilizada. 
- Outras pessoas  de minha equipe que farão a integração de meus trabalhos com esse código.
- O programador responsável pela manutenção (que poderá ser eu ou outro programador) anos depois, quando estiver investigando um bug em uma versão antiga.

Um código difícil de ser lido é um código com o qual é difícil de se trabalhar. É por isso que nos esforçamos para ter uma apresentação clara, agradável e útil.

**PONTO-CHAVE:** lembre-se para quem você está escrevendo código: os outros.

O código pode parecer bonito, porém obscuro quanto ao seu propósito. Ele também pode parecer bonito, mas se excessivamente difícil de ser mantido. Um ótimo exemplo desse caso está na caixa de comentário. 

# Layout
*Se alguém quiser escrever com um estilo claro, devemos ter um raciocínio claro antes de tudo.* - Johann von Goethe.

## Crie uma boa estrutura
Escreva o seu código como se estivesse em prosa.

Separe-o em capítulos, parágrafos e sentenças. Reúna os itens semelhantes e separe o que for diferente. As funções são como capítulos. Em cada capítulo pode haver partes do código distintas, porém relacionadas. **Separe-as em parágrafos inserindo linhas em branco entre elas**. Não insira linhas em branco, a menos que haja uma quebra natural de parágrafo. 

Por exemplo:
```c
void exampleFunction(int param)
{
	// Agrupamos o que está relacionado aos dados de entrada
	parm = sanitiseParmValue(parm);
	doSomethingWithParm(param);

	// Outras tarefas são inseridas em um "parágrafo" separado
	updateInternalInvariants();
	notifyOthersOfChange();
}
```

A ordem em que o código é apresentado é  importante. Leve o leitor em consideração: coloque as informações mais importantes antes, e não no final. Garanta que as APIs sejam lidas em uma ordem sensata. Coloque aquilo que for importante para o leitor no início de sua definição de classe. *Isso significa que toda informação público deverá vir antes das informações privadas. A criação de um objeto deve vir antes de seu uso*.

Prefira escrever blocos menos de código. Não crie uma função com cinco "parágrafos". 

## Consistência
Escreva códigos usando as mesmas convenções de layout usadas pelo restante de sua equipe. Não utilize seu próprio estilo só porque você acha que ele é mais bonito ou melhor. Se não houver consistência em seu projeto, considere a adoção de um *padrão de codificação* ou de um *manual de estilo*. O documento não precisa ser extenso ou draconiano; um conjunto de princípios de layout em relação ao qual a equipe tenha concordado será suficiente. Nessa situação, deve haver um consenso mútuo sobre os padrões de codificação; esses não devem ser impostos.

**Tabulações vs Espaços**
A maioria das convenções modernas recomenda **espaços** em vez de tabulação, especialmente no **Java**. O guia de estilo oficial do **Google Java Style Guide** recomenda **4 espaços por nível de indentação**. O **Java Code Conventions da Oracle** também recomenda **4 espaços**.

✅ **Motivos para usar espaços (recomendado para Java):**

- Mantém a formatação consistente em diferentes editores e IDEs.
- Evita problemas de alinhamento quando o código é visualizado em diferentes ferramentas.
- É amplamente aceito na comunidade Java.

🚫 **Motivos contra tabulação:**

- O tamanho da tabulação pode variar dependendo da configuração do editor (exemplo: pode aparecer como 4, 8 ou até 2 espaços).
- Pode causar desalinhamento ao misturar tabulações e espaços.

## Nomes
*Quando uso uma palavra, disse Humpty Dumpty em um tom de desdém, ela quer dizer exatamente o que eu quero que ela signifique - nem mais nem menos*. - Lewis Carroll

Damos nomes a vários itens: variáveis, funções e métodos, tipos (por exemplo, enumerações, classes), namespaces e pacotes. Igualmente importantes são os itens maiores como arquivos, projetos e programas. As APIs públicas (por exemplo, interfaces de bibliotecas ou APIs da web services) talvez sejam os itens mais significativos aos quais damos nomes, pois as APIs públicas "disponibilizadas" em uma versão, com muita frequência, são escritas a ferro e fogo e são particularmente difíceis de mudar. 

Um nome representa a identidade de um objeto; ele descreve o item, indica o seu comportamento e o uso pretendido. Uma variável indevidamente nomeada pode ser *muito* confusa. Um bom nome é descritivo, correto e idiomático.

Só podemos nomear algo se soubermos *exatamente* o que está sendo nomeado. Se não conseguirmos descrever um item claramente ou não souber para que ele será usado, simplesmente não será possível lhe dar um bom nome.

## Evite redundância
Ao dar nomes, evite redundância e explore o contexto. Considere este código:
```java
class WidgetList {
	public int numberOfWidgets () {
		...
	}
}
```
O nome do método *numberOfWidgets* é desnecessariamente longo e repete a palavra *Widget*. Isso torna o código difícil e maçante de ler. Como esse método retorna o tamanho da lista, ele poderá simplesmente ser chamado de *size()*. 

## Seja claro
Favoreça a clareza em relação à concisão. Os nomes não precisam ser curtos para economizar na digitação - você *lerá* o nome da variável muito mais vezes do que irá digitá-la. Entretanto há um caso de uso de nomes de variável com uma única letra: variáveis de contador em loops curtos tendem a ser lidas de modo mais claro. Novamente, o contexto é importante!  

Se for preciso organizar o código, jamais altere a apresentação ao mesmo tempo que fizer mudanças funcionais. Faça check-in das mudanças relacionadas à apresentação no sistema de controle de versões.

É confuso ver commits que misturem os dois passos. As mudanças de layout podem mascarar erros na funcionalidade.

**PONTO-CHAVE** Jamais altere a apresentação e o comportamento ao mesmo tempo. Faça com que sejam alterações separadas no sistema de controle de versões.

Não pense que você deve escolher um estilo de layout e permanecer fielmente com ele pelo resto da vida. Reúna feedback continuamente sobre o modo como as operações de layout afetam a maneira de trabalhar com o código. Aprenda com o código que lemos. 

Hook Pré-commit
No Git, os pre-commit hooks são scripts que são executados antes que um commit seja finalizado. Eles podem ser usados para automatizar verificações, como:
- Aplicar formatação de código (por exemplo, *Prettier* ou *Google Java Format*);
- Verificar se o código segue padrões (Linting);
- Rodar testes automatizados;
- Impedir commits com erros de sintaxe ou código não formatado.


## Perguntas
1. Você deve alterar o layout de códigos legados para atender aos padrões de codificação da empresa? Ou é melhor manter o estilo original do autor? Por quê?
*Estou com bastante dificuldade de responder a esta pergunta.*

Alterar o Layout do Código Legado:
**Vantagens**
- **Padronização e Consistência:** quando todos os códigos seguem os mesmo padrões, fica mais fácil para novos desenvolvedores entenderem e trabalharem no projeto. Isso reduz a curva de aprendizado e facilita a manutenção.
- **Facilidade de Integração**: se o código legado precisa ser integrado com novas funcionalidades ou sistemas que seguem os padrões da empresa, a padronização evita conflitos e inconsistências.
- **Melhora na Qualidade do Código:** ao aplicar os padrões atuais, você pode aproveitar para refatorar partes problemáticas do código legal, melhorando a sua legibilidade, desempenho e segurança.


1. É importante seguir o layout e as convenções de nomenclatura de linguagem? Ou é mais produtivo adotar um estilo doméstico diferente para que possamos diferenciar o código de sua aplicação do código da biblioteca-padrão?