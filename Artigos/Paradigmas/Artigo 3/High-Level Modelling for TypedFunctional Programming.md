## Resumo
Atualmente, não existe uma maneira estabelecida de modelar o design estrutural de alto nível de um sistema funcional. Dada a forte ligação entre a **programação funcional e a matemática**, a hipótese é de que a linguagem matemática pode oferecer insights sobre como um sistema funcional pode ser modelado. A abordagem mostrou-se bem-sucedida, utilizando tanto a filosofia quanto a linguagem matemática para identificar os conceitos necessários de modelagem e delinear brevemente algumas notações de modelagem, acompanhadas de um pequeno estudo de caso.

## 1. Introdução
O design estrutural de sistemas funcionais - ou seja, aqueles escritos em uma linguagem de programação funcional e/ou que obedecem às convenções do #paradigma #funcional - não pode ser modelado: não existem notações ou ferramentas de modelagem específicas para esse propósito. Este artigo busca contribuir para preencher essa lacuna de pesquisa.

É importante compreender a extensão dessa lacuna: atualmente, não há uma notação padrão **para modelar sistemas funcionais** tipados ou não tipados em um nível elevado, seja comportamental ou estrutural. Isso não significa que nenhuma notação exista. Pelo contrário, diversas anotações *ad hoc* foram desenvolvidas e diferentes tipos de diagramas, como o **Diagrama de Processo** do UM  ou BPMN, foram adaptados para atender às necessidades específicas do momento. No entanto, nenhuma dessas notações afirma ser capaz de modelar sistemas funcionais de forma geral, porque, de fato, não conseguem. Assim, **permanece a ausência de uma maneira padronizada para modelar a estrutura de um sistema funcional**. 

Este trabalho faz parte de um esforço mais amplo para permitir que programas funcionais sejam modelados de forma intuitiva. Ele foca especificamente na modelagem estrutural de programas funcionais tipados em um nível elevado e propõe uma notação geral e padrão que deve ser aplicável a todas as linguagens funcionais. 

A computação em uma linguagem orientada a objetos baseia-se na ideia de que o progresso ocorre por meio da comunicação entre objetos, <span style="background:#affad1">cada um colaborando para alcançar o objetivo do sistema</span>. Em contraste, a computação em uma linguagem funcional tipada é fundamentada na transformação de valores através de funções puras, que podem resultar em um valor final, representando o objetivo do sistema. 

Existem várias características importantes de um sistema funcional tipado que são difíceis ou até impossíveis de modelar usando diagramas estruturais do UML. Essas características estão presentes na maioria das linguagens funcionais tipadas. Por exemplo:
- As enumerações do UML são insuficientes para representar *sum types*, já que os casos desses tipos frequentemente estão ligados a outros dados; para contornar isso, seria necessário utilizar o padrão de design #Composite. 
- Não há uma notação adequada para modelar as **semânticas funcionais** centrais da programação funcional, como funções lambda, funções aninhadas, funções de ordem superior, funções parcialmente aplicadas, funções compostas e #closures.
- Todo diagrama estrutural do UML implicitamente ou explicitamente exige algum mapeamento do domínio para a ideia de contêineres semelhantes a classes ou objetos, algo que pode estar ausente em linguagens funcionais.

Isso significa que, embora o UML possar ser utilizado para representar uma ampla gama de sistemas, incluindo sistemas do mundo real que não envolvem computadores, ele os modela principalmente como **entidades com relacionamentos**, em vez de **operações envolvendo essas entidades**. O problema subjacente pode estar na filosofia desses diagramas,  que é fundamentalmente baseada em objetos.

A relação entre programação funcional e modelagem é fundamentalmente matemática, em contraste com a relação entre programação orientada a objetos e modelagem, que <span style="background:#ff4d4f">pode ser descrita como amplamente pragmática</span>. A linguagem de modelagem predominante na orientação a objetos, UML, foi criada por meio de um processo de consenso e refinamento, com vínculos à matemática ou a uma filosofia subjacente, sendo frequentemente resultado de adaptações retroativas. <span style="background:#b1ffff">Já as raízes da programação funcional estão no cálculo lambda, uma descrição matemática do que significa computador. </span> A introdução de sistemas de tipos, como o sistema Hindley-Milner, levou ao desenvolvimento de programação funcional tipada, baseada em uma teoria rigorosa de tipos. 

As contribuições deste trabalho são as seguintes:
 - Um embasamento filosófico sólido é buscado, encontrado e mapeado entre a linguagem matemática e a programação funcional tipada;
 - Um conjunto de conceitos comuns a toda programação funcional tipada, não vinculados a linguagens específicas, é identificado;
 - Uma notação de modelagem estrutural de alto nível é brevemente descrita, acompanhada de um pequeno estudo de caso para demonstrar sua aplicabilidade.
 
 O restante deste artigo está estruturado da seguinte forma:
 - Seção 2 justifica a abordagem utilizada para buscar uma solução de modelagem;
 - Seção 3 discute a modelagem, situando e delimitando explicitamente o trabalho dentro de um contexto de modelagem específico;
 - Seção 4 examina trabalhos relacionados; 
 - Seção 5 apresenta uma base filosófica;
 - Seção 6 exploração da linguagem matemática é realizada;
 - Seção 7 cominamos a filosofia e a linguagem para identificar um conjunto de conceitos para modelagem
- Seção 8 uma notação para modelar esses conceitos é primeiramente justificada teoricamente e, em seguida, proposta;
- Seção 9 um breve estudo de caso é apresentado;
- Seção 10 são discutidos as conclusões.

## 2. Aproach
A maior parte deste artigo percorre o terreno da filosofia, linguagem, discurso matemático e design - com um estudo de caso no final. Leva tempo para construir pontes entre essas áreas e explicá-las com detalhes suficientes para nossos propósitos. Por que seguir um caminho tão longo e sinuoso, em vez de simplesmente encontrar alguma notação que pareça utilizável e faça com que os praticantes acenem com a cabeça?

O design de sistemas é, em última análise, uma atividade proposital: todo sistema tem algum motivo para existir, mesmo que esse motivo seja puramente exploratório. **Esse design geralmente seguirá as formas de pensamento desenvolvidas pelo designer e**, muitas vezes, também será restrito por essas formas de pensamento. 

Portanto, é de fundamental importância garantir que as partes interessadas do sistema sejam incentivadas a pensar de uma maneira que facilite um design que possa ser facilmente codificado como um sistema de computador e que desestimule formas de pensar que são difíceis de codificar. Três categorias de pensamento comum relacionado ao design são categorizadas aqui:
1. **Pensando em um domínio do problema:** uma pessoa pode pensar em um problema em termos de sua área de especialização, como contabilidade ou botânica, ou termos de alguma outra influência, como pesca ou discussões familiares. **A facilidade de tradução para um sistema de computador depende do grau em que as linguagens do sistema são capazes de representar ou codificar o pensamento original.** 

2. **Pensando em uma linguagem de programação:** um desenvolvedor pode pensar em um problema em termos que uma linguagem disponibiliza para ele. A facilidade de tradução para um sistema de computador é garantida, mas <span style="background:#b1ffff">o grau em que esse sistema reflete o domínio do problema real será relativo a quão precisamente o domínio do problema pode ser expresso na linguagem</span>. Outra questão é que um design desse tipo pode ser inacessível às partes interessadas sem o conhecimento técnico necessário.

3. **Pensando em uma notação:** uma parte interessada - seja um desenvolvedor, analista de negócios ou outra pessoa - pode pensar em um problema **em termos que uma notação suporta**. A facilidade de tradução para um sistema de computador está relacionada à facilidade com que a semântica da notação é traduzível para a semântica computacional, e o grau em que o sistema reflete o domínio do problema real é relativo a quão-precisamente o domínio do problem a pode ser expresso na notação. 

Linguagens específicas de domínio podem ser usadas com sucesso para conectar (1) e (2), mas introduzem problemas próprios e, portanto, não devem ser usadas sem a devida consideração. É a categoria (3) que é mais acessível ao público mais amplo e com a qual este artigo está preocupado. No entanto, a acessibilidade dessa categoria introduz problemas próprios, pois as partes interessadas devem usar a mesma filosofia subjacente para poder participar de qualquer processo de modelagem que usa a notação. Essa filosofia é, no mundo orientado a objetos, fornecida por uma teoria subjacente de objetos - embora os detalhes do que é um "objeto" em si tenham sido contestados. Se uma filosofia subjacente compartilhada, argumenta-se que as partes interessadas terão dificuldade em conceituar suas ideias notacionalmente e ficarão frustradas com uma notação em vez de usá-la efetivamente. 

Um respaldo filosófico apropriado para a modelagem é importante, e a profunda preocupação com a filosofia ao propor um modelo de alto nível não é simplesmente teórica: ela está enraizada na história de nosso campo:

A maioria de nossas atuais "linguagens de modelagem (MLs)" remonta à década de 1990 e, portanto, afirmam ser de "propósito geral" - um exemplo principal sendo a Unified Modeling Language (UML) do Object Management GRoup (OMG). Embora afirme suportar uma ampla gama de níveis de abstração de modelagem (ou seja, análise e design) em um único pacote, seu histórico de desenvolvimento indica claramente que está altamente focada em design (de baixo nível ou detalhado) e até mesmo em implementação (por exemplo, conceitos Java e C++ style são evidentes). Algumas Linguagens de Modelagem Específicas de Domínio (DSMLs) mostram o mesmo viés, quando apresentadas como um "Perfil UML". 

A questão central aqui é que as linguagens atualmente populares não devem ter um impacto excessivo na notação que pode sobreviver a elas; sem a influência estabilizadora de uma filosofia subjacente explícita, o vínculo entre notação e linguagem é contaminado por construções de linguagem específicas. O que parece "usável" ou "familiar" hoje muitas vezes é função da experiência do programador e não deve ser descartado, mas não deve ser considerado como motivo suficiente para a existência de determinada notação.

O vínculo entre o domínio do problema e a notação também é uma área de preocupação. Se a notação facilita a modelagem do domínio do problema de uma maneira inadequada para a implementação, ocorre conflito:
"[...]" não era apenas que a UML continha ambiguidades e inconsistências semânticas, mas sim que a maior proeminência dada a determinadas notações de modelagem havia, por sua vez, colocado um prêmio na realização de certos tipos de análise e atividade de design. Os analistas estavam adotando entusiasticamente novas abordagens para conceituar seus sistemas, eventualmente ficando presos em argumentos improdutivos sobre os objetos que povoam o sistema e a representação adequada da estrutura de controle do sistema. Os designers estavam então se recusando a implementar os modelos produzidos pelos analistas, já que muitas vezes era impossível mapear de modelos de caso de uso e diagramas de sequência para qualquer coisa que um engenheiro de software convencional reconheceria.

A um tempo, tais problema poderiam ser mitigado com referência a alguma filosofia subjacente compartilha que guie a análise, o design e a implementação. 

Encontrar uma filosofia subjacente adequada e criar uma ponte conceitual para vincular domínio do problema, linguagem e notação é visto como uma forma de evitar algumas das dificuldades que acompanharam o nascimento, desenvolvimento e uso da UML. A notação específica em si, como artefato dessa busca, é relativamente pouco importante, desde que alguma notação viável possa ser demonstrada. Isso não é afirmado para descartar a importância de uma notação concreta, mas para elevar a importância de sua fundação. Por analogia, <span style="background:#b1ffff">pode-se dizer que qualquer sistema de software específico</span> é relativamente pouco importante quando comparado à importância dos princípios de design de software que orientaram sua criação e a criação de milhares de outros sistemas de software.

Além disso, pesquisadores com acesso à base subjacente de uma notação estão livres para propor outras notações que se baseiem nela, e essa notação provavelmente será coerente com – ou superior a – o que é proposto neste artigo. Os pesquisadores também estão livres para criticar, complementar e revisar a fundação, criando assim uma base mais sólida para futuras notações. No entanto, pesquisadores sem acesso à base da notação devem simplesmente adivinhar os fundamentos filosóficos e as ligações trabalhando de trás para frente a partir da notação, levando a mal-entendidos desnecessários e propostas concorrentes improdutivas com base em motivos implícitos de preferência de linguagem, utilidade do domínio do problema e assim por diante.

- **O problema da influência das linguagens de programação:**  Muitas notações de modelagem são influenciadas por linguagens de programação existentes, o que limita sua aplicabilidade e pode levar a notações que são mais adequadas para implementação do que para compreensão do problema.
- **A necessidade de uma notação independente:** portanto, faz-se necessário a criação e uma notação que seja independente de linguagens de programação específicas e que se baseiem em uma filosofia sólida que abrange tanto o domínio do problema quanto a implementação. 
Uma notação bem fundamentada pode ser a chave para o sucesso de um projeto de desenvolvimento de software. 

No contexto, uma notação é um conjunto de símbolos e regas que permitem representar um sistema de forma visual ou textual. Ela é utilizada para:
- **Capturar o conhecimento:** representar o conhecimento sobre um sistema de forma estruturada.
- **Comunicar ideias:** transmitir informações sobre o sistema para diferentes stakeholders.
- **Analisar e validar:** analisar as propriedades do sistema e validar sua corretude.
- **Gerar código:** em alguns casos, a notação pode ser usada para gerar automaticamente código executável. 