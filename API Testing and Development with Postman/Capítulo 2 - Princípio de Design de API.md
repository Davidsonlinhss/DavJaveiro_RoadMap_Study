Os designers não trabalham para que as coisas tenham uma boa aparência? Uma API não tem muito o que ver, então por que gastaria um capítulo inteiro falando sobre design da API?
Acreditamos que o design, os testes e a documentação fazem parte da criação de um produto de qualidade tanto quanto o desenvolvimento. Talvez sejam até mais importantes. Um bom design pode facilitar muito a vida de um desenvolvedor ou testador. Projetar algo não é tanto fazer com que pareça bom, mas sim garantir que traga satisfação. Nós gostamos de coisas com boa aparência e, por isso, os designs podem, às vezes, se concentrar na aparência de algo, mas,<span style="background:#affad1"> mesmo no âmbito das APIs, em que não há muito o que "ver", um bom design pode tornar o uso muito mais agradável e, assim, melhorar a qualidade</span>. 

Este capítulo abordará os princípios de design de API e cobrirá os seguintes tópicos principais:
1. Descobrindo a finalidade de uma API;
2. Princípios de design para estruturar uma API de modo que ela seja fácil de usar;
3. Criando a documentação no Postman;
4. Uso de linguagens de especificação para projetar uma API antes de escrever qualquer código;

O material deste capítulo pode parecer um pouco abstrato e teórico, mas incluí alguns exercícios para ajudar a descobrir como usar esses conceitos. 


## Requisitos técnicos
https://github.com/PacktPublishing/API-Testing-and-Developmentwith-Postman/tree/master/Chapter02

## Start with the purpose
Não criamos uma API apenas porque é uma coisa divertida de se fazer. Criamos APIs para atingir um objetivo. Elas ajudam a nós ou a nossos usuários a fazer algo que queremos fazer com mais eficiência. Isso pode parecer óbvio, mas vamos ser honestos: esquecemos de fazer isso com muita frequência.

O design é um domínio difícil. Talvez sua empresa tenha designers. Às vezes, pessoas técnicas, como testadores e desenvolvedores, podem descartar o que os designer fazem como "apenas fazer as coisas parecerem bonitas", mas a realidade é que um bom design é muito difícil. Um bom design cria algo que é adequado para sua finalidade. Este livro não é um livro sobre design, recomendamos a leitura do livro o design de todas as coisas. Entretanto, se estivermos interessados em APIs de boa qualidade, precisaremos pensar por alguns minutos sobre a finalidade da nossa API e como podemos projetar para atender essa finalidade.

Saber que precisamos fazer algo é um primeiro passo importante. Infelizmente, muitas palestras, artigos e livros param por aí. 

## Descobrindo o propósito de nossa API
Queremos descobrir a finalidade de nossa API, mas como fazer isso? Há algumas estratégias simples que podemos usar para isso. A primeira coisa é fazer perguntas. Por que você precisa dessa API? Que problemas você acha que a API resolverá? Quero descobrir qual é a finalidade da API.

## Pessoas
Outra estratégia simples que podemos pensar é pensar em pessoas. Uma pessoa é simplesmente uma pessoa inventada que a gente usa para ajudar a pensar em que pode estar usando a API. Por exemplo, você ter uma persona que represente um usuário da sua empresa que usará a API para desenvolver interfaces de usuário ou pode ter uma pessoa de um engenheiro de software que trabalhe para um dos nossos clientes e usa a API para desenvolver um aplicativo personalizado. Pensar em diferentes tipos de usuários e considerar como eles podem interagir com a API ajudará a entender a finalidade a que ela serva.

Ao criar uma persona, pense em coisas que vão além da tecnologia que a pessoa pode usar ou entender. Pense em coisas como os objetivos e as motivações que ela pode ter e as coisas que podem frustá-la. Também é útil pensar em alguns detalhes pessoais que possam torná-la um pouco mais identificável para a gente. Por exemplo, podemos pensar se a pessoa gosta de cachorros ou se tem filhos. Em outras palavras, que tipo de pessoa ela é? Anotar detalhes como esses pode parecer um pouco bobo às vezes, mas ajuda você a ter mais empatia com essa pessoa e, ao fazer isso, seremos capazes de nos colocarmos no lugar dela e entender os tipos de coisas que são importantes para ela. Quanto mais entendermos isso, mais poderemos compreender o propósito da nossa API.

## O motivo
No centro da descoberta do objetivo está a pergunta "por que". Por que estamos criando essa API? O motivo quase sempre é a solução de um problema. Uma ótima maneira de descobrir a finalidade de uma API é descobrir o problema que ela resolve. Ela facilita a criação de elementos de interface do usuário? Ela permite que os clientes personalizem o aplicativo? Ela permite que desenvolvedores de terceiros usem sua plataforma? Ela simplifica as integrações com outros aplicativos? Que problema a sua API está resolvendo? Responda essas perguntas e estaremos no caminho certo para conhecer a finalidade da API.

### Nota
Esse exercício de descobrir a finalidade de uma API não se aplica apenas a novas APIs. Se estiver trabalhando com uma API existente, é muito importante entender a finalidade. Pode ser tarde demais para alterar radicalmente o design da API, mas não há ameaça mais importante à qualidade do que não ajudar as pessoas a resolver os problemas que elas precisam resolver. Se nada mais acontecer, entender a finalidade de uma API existente que estamos testando ajudará a descobrir quais bugs são importantes e quais podem não ter tanta importância. É preciso ter alguma habilidade para descobrir quais bugs são urgentes e quais não são, e entender a finalidade da API ajuda nisso.

## Experimente
O livro não se trata apenas de encher a cabeça com alguma teoria. Trata-se de ajudar a melhorar imediatamente o teste de APIs. Um pouco mais adiante no livro, mostrarei algumas das ferramentas que o Postman tem para ajudar no projeto da API. 

Pegue uma API existente na qual você esteja trabalhando e veja se consegue escrever o objetivo em duas ou três frases. Use as etapas a seguir para realizar o processo:

1. Identifique pelo menos dois participantes principais para a API. Para isso, faça a seguinte pergunta: "Quem quer (ou queria) que essa API fosse criada?". Anote os nomes desses participantes.


2. Se possível, converse com essas partes interessadas e <span style="background:#affad1">pergunte a elas qual é a finalidade dessa API e por que querem criá-la</span>. Anote as respostas.


3. Crie, de preferência, duas (mas pelo menos uma) personas que listem os tipos de pessoas que você acha que usarão a API. Qual é o nível de habilidade dessas pessoas? Que trabalho elas estarão tentando realizar? Como a sua API as ajudará?


4. Escreva o(s) problema(s) que você acha que a API resolverá.


5. Agora, pegue todas as informações que reunimos e examine-as. Desmembre-as em duas ou três frases que expliquem a finalidade dessa API.

## Criando APIs utilizáveis
A usubilidade tem a ver com o equilíbrio entre a exposição de muitos controles e de poucos. É muito difícil acertar esse aspecto. Nos extremos, é óbvio quando as coisas estão fora de equilíbrio. Nos extremos, é óbvio quando as coisas estão fora de equilíbrio. Por exemplo, o Metropolitan Museum of Art tem uma API que fornece informações sobre vários objetos de arte em seu poder. Se tudo o que a API fizesse fosse fornecer uma chamada que lhe devolvesse todos esses dados, ela estaria fornecendo poucos controles. Você precisaria fazer tanto trabalho depois de obter as informações que seria melhor não usar a API. No entanto, se, por outro lado, a API fornecesse um endpoint separado para cada parte dos metadados no sistema, teríamos dificuldade para encontrar o endpoint que fornecesse as informações específicas que eu gostaria de obter. Precisamos compreender muito para poder usar o sistema. 

Precisamos pensar cuidadosamente sobre isso se quisermos obter o equilíbrio certo. Certifique-se de que a API esteja fornecendo aos usuários dados específicos o suficiente para as coisas de que eles precisam (é aqui que conhecer a finalidade é útil) sem sobrecarregá-los. Em outras palavras, mantenha-a o mais simples possível.

### Estrutura de API utilizável
Uma coisa que pode ajudar a criar uma API utilizável é usar apenas substantivos como pontos de extremidade. Se quiser que os usuários entendam sua API, estruture-a de acordo com os objetos do seus sitema. Por exemplo, <span style="background:#affad1">se quiser permitir que os usuários da API obtenham informações sobre os alunos</span> em um sistema de aprendizagem, <span style="background:#ff4d4f">não crie um endpoint chamado /getAllStudents</span>. Crie um chamado <span style="background:#affad1">/students</span> e chame-o com o método GET.

A criação de <span style="background:#affad1">endpoints com base em substantivos o ajudará a estrutura melhor seus dados</span>. Por exemplo, se tivermos /students como um endpoint, <span style="background:#affad1">poderemos adicionar facilmente um endpoint para cada aluno em /students/studentId</span>. Esse tipo de estrutura de categorização é outro princípio útil de design de API que deve ser lembrado. <span style="background:#affad1">A criação de uma estrutura como essa mapeia o layout da API</span> para os tipos de coisas sobre as quais o usuário da API precisa de informações. Isso torna muito mais fácil saber onde encontrar as informações relevantes.

Uma estrutura como essa funciona bem, mas será que ela realmente corresponde à forma como os usuários interagem com a API? Se eu estiver procurando informações sobre um aluno, será que vou saber qual é o ID dele na API? Talvez, mas é mais provável que eu saiba como o nome dele. Então, devemos modificar a estrutura para ter um endpoint adicional como /students/name? Mas e se estivermos vendo todos os alunos de uma determinada idade? Devemos adicionar outro endpoint /students/age? 

É nesse ponto que os parâmetros de consulta são úteis. Um parâmetro de consulta é uma forma de obter algum subconjunto da categoria com base em uma propriedade que ela possui. <span style="background:#affad1">Portanto, nos exemplos que dei anteriormente, em vez de fazer com que "name" e "age" fossem endpoints na categoria "students", poderíamos simplesmente criar parâmetros de consulta.</span> Chamaríamos /students?name='JimJones' ou /students?age=25. 

Query parametrs ajudam a manter os pontos de extremidade simples e lógicos, mas ainda oferecerem aos usuários a flexibilidade de obter as informações de seu interesse de forma eficaz.


## Boas mensagens de erros
A API utilizável ajuda os usuários quando eles cometem erros. Isso significa que você fornece a eles os códigos HTTP corretos ao responder a uma chamada. Se a solicitação estiver mal formatada, a API deverá retornar um código de erro 400. Não vou listar todos os códigos HTTP aqui, pois eles podem ser facilmente pesquisados on-line, mas garantir que sua API esteja retornando códigos que façam sentido para o tipo de resposta que você está recebendo é uma consideração importante de qualidade. Além dos códigos HTTP, algumas APIs podem retornar mensagens que informam quais são as possíveis etapas que você pode tomar para corrigir esse problema. Isso pode ser muito útil, embora você deva ter cuidado para não revelar muitas informações para aqueles que podem ser maus atores. 

## Documente sua API
Um aspecto frequentemente negligenciado, mas de importância vital para uma API de boa qualidade, é a documentação adequada. Às vezes, os testadores e desenvolvedores podem ignorar a documentação como algo que está fora de sua área de especialização. Se você tiver uma equipe que escreva a documentação da sua API, certamente não há problema em pedir que ela a escreva, mas não trate a documentação como um cidadão de segunda classe! A documentação geralmente é a pimeira exposição que as pessoas têm à sua API E, se ela não apontar a direção que as pessoas precisam seguir, elas ficarão confusas e frustradas. Não importa quão bem você tenha projetado a sua API, os usuários precisarão de alguma documentação para ajudá-los a saber o que ela pode fazer e como usá-la.

