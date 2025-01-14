## Resumo
Nos últimos anos, houve um interesse renovado em Programação Funcional (Functional Programming - FP), que se tornou um paradigma de programação popular em diversas linguagens, incluindo Python e JavaScript. Além disso, a Programação Funcional é o paradigma principal de linguagens cada vez mais utilizadas, como Clojure e Haskell, que adquiriram significativa importância tanto para pesquisadores quanto para desenvolvedores. A FP oferece diversos benefícios no desenvolvimento de software, proporcionando sistemas legíveis, manuteníveis e escaláveis. No entanto, apesar desses benefícios, <span style="background:#affad1">a maior parte da literatura relacionada ao design de software atualmente carece de especificações claras e detalhadas sobre o paradigma funcional</span>. Em contraste, a Programação Orientada a Objetos, por exemplo, possui uma vasta game de recursos e ferramentas de design. Esses e outros desafios podem dificultar a adoção da FP para desenvolvedores que buscam integrar esse paradigma em seus sistemas. 

O objetivo deste trabalho de pesquisa é apresentar o estado da arte do design de software orientado à FP, abrangendo metodologias, práticas e artefatos; analisando diferentes propostas e identificando desafios em aberto. Para atingir esse objetivo, foi conduzida uma Revisão Sistemática da Literatura (Systematic Literature Review - SRL), seguindo as diretrizes de Kitchenham. Identificamos um total de 14 estudos primários publicados entre 2012 e 2022, cobrindo diferentes artefatos e práticas para o design de software sob o paradigma da FP. Embora o design de software orientado à FP <span style="background:#b1ffff">seja atualmente um tópico pouco explorado na Engenharia de Software</span>, sua importância no desenvolvimento de software é inegável, e espera-se que seu uso continue crescendo, representando uma área de oportunidade interessante para pesquisas futuras.

## 1. Introduction
O design de software é uma atividade fundamental no desenvolvimento de software, pois serve como base para sistemas confiáveis, compreensíveis e manuteníveis, ou seja, é o conjunto de princípios, conceitos e práticas que levam ao desenvolvimento de um produto de alta qualidade.

Nesse sentido, a Programação Funcional FP tornou-se uma abordagem popular nos últimos anos devido aos seus benefícios. Por exemplo, adotar um estilo FP pode tornar o código mais robusto, compacto e fácil de **paralelizar**, ou seja, o código pode ser executado de forma eficiente em paralelo, ou seja, processamento simultaneo em diferentes núcleos de um processador ou em diferentes máquinas. 


Portanto, um bom design de software pode aproveitar os princípios e conceitos da FP para desenvolver sistemas confiáveis e eficientes. Além disso, linguagens populares como JavaScript e Python incorporaram paradigmas de programação funcional em sua sintaxe, permitindo que os desenvolvedores aproveitem os benefícios da FP enquanto operam em ambientes familiares dessas linguagens amplamente adotadas.

Um exemplo notável é como JavaScript incorporou funções de ordem superior, juntamente com a capacidade de **tratar funções** como **objetos de primeira classe**, demonstrando a relevância duradoura da Programação Funcional na indústria.

A complexidade e o custo de projetar software com programação funcional são fatores que contribuem para sua menor taxa de adoção na indústria.

## 11 - Related Work
Como foi difícil encontrar estudos secundários sobre design de software orientado a FP, para os estudos relacionados ampliamos nossos critérios de seleção para incluir artigos que ofereçam uma compreensão abrangente de FP ao longo do processo de desenvolvimento de software, independentemente do ano de publicação. Esses estudos foram principalmente obtidos nas bibliotecas digitais.

Diversos estudos destacaram a importância da programação funcional no design de software, e**specialmente na implementação de sistemas paralelos e distribuídos**. Além disso, a programação funcional tem sido reconhecida por sua capacidade de melhorar a legibilidade, a manutenção e a modularidade e a segurança do software. 

Este estudo se diferencia dos trabalhos utilizados na pesquisa ao oferecer uma análise abrangente da importância da programação funcional no design de software, especificamente do ponto de vista do design, com foco nos aspectos técnicos da construção de programas.

Vale ressaltar que a falta de estudos secundários específicos relacionados ao design de software orientado à programação funcional pode indicar que o tema tem sido insuficientemente explorado na literatura, apontando para uma lacuna de pesquisa e desenvolvimento nessa área. Isso ressalta ainda mais que a programação funcional no design de software é um campo amplo e em constante evolução, com tecnologias associadas ao desenvolvimento de software em contínua transformação. 

## IV. Results
### A. Ano de Publicação
Identificamos, de forma geral, um baixo nível de atividade de publicação em relação ao design de software orientado à programação funcional, sendo 2016 o ano com o maior número de estudos publicados sobre o tema. 

### B. Distribuição por classificação
Dos 14 estudos selecionados, identificamos a distribuição de suas classificações, revelando que 10 foram provenientes de periódicos e 4 de conferências. 

### C. Síntese dos dados
1. RQ1: Quais são as práticas de design de software para o paradigma de Programação Funcional?
É importante especificar que uma prática é uma atividade que contribui para o propósito ou os resultados de um processo ou melhora de sua capacidade.

A literatura relata que há uma falta de práticas de design de software orientado à Programação Funcional, em contraste com a abundância de práticas adotadas no POO. No entanto, os estudos coletados revelaram quatro práticas principais:

1) **Modelagem de abstrações funcionais de alto nível**
São conceitos e técnicas utilizadas na programação funcional para simplificar e abstrair a lógica de um programa ao resolver problemas gerais e genéricos de forma eficiente. Essas abstrações baseiam-se no uso de funções e são projetados para serem intuitivas e fáceis de usar pelos programadores. As abstrações funcionais de alto nível são benéficas para abstrair lógicas complexas e simplificar um programa, facilitando seu design e manutenção a longo prazo. Alguns exemplos de **abstrações funcionais de alto nível** incluem:
- #Monads: são estruturas de dados que encapsulam um valor e fornecem uma série de operações para manipular esse valor de maneira segura e controlada, exemplos em Java incluem #Optional, #Stream e #CompletableFuture. Esses objetos encapsulam valores e oferecerem métodos para transformações ou manipulação sem expor diretamente os valores.
- #Functores: são uma classe de tipos de dados que podem ser aplicados a outros tipos de dados para criar um novo tipo de dados. 

2) **Projetando com fundamentos matemáticos:** o paradigma de programação funcional está intrinsecamente ligado à matemática, pois utiliza funções matemáticas para definir a computação. Portanto, é comum usar linguagens matemáticas como ferramentas de design de software na Programação Funcional. Essa abordagem permite o modelamento preciso e conciso do comportamento dos sistemas funcionais, tornando-os mais fáceis de entender e projetar pelo programador. Além disso, a Programação Funcional frequentemente emprega o pensamento matemático como uma ferramenta intuitiva e geral para modelar sistemas, o que é altamente vantajoso no design de software. 

3) **Identificadores de Funções e Dados**: na POO, o processo comum é extrair substantivos e convertê-los em classes, seguido pela análise de verbos para determinar os métodos da classe. Em contraste, a FP inverte esse processo. Primeiro, os verbos são extraídos, seguidos pela extração de substantivos para identificar os dados utilizados no processamento [18]. A função ou processo é central no design de sistemas funcionais, tornando crucial a extração do verbo. Essa abordagem contribui para a criação de sistemas seguros, previsíveis, fáceis de compor e reutilizar. 

4) **Uso de padrões recursivos:** 