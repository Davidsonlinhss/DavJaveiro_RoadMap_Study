## 1.1 Organização e Arquitetura
Ao se descreverem computadores, é comum se fazer uma distinção entre *arquitetura de computadores* e *organização de computadores*. 

#Arquitetura_de_computador: refere-se aos atributos de um sistema visíveis a um programador ou, em outras palavras, aqueles atributos que possuem impacto direto sobre a execução lógica de um programa. Um termo que é usado de forma intercambiável com as arquiteturas de computadores é **arquitetura de conjunto de instruçao** (ISA, *instruction Set Architecture*). O ISA define os formatos de instruções, códigos de operação da instrução (opcodes), registradores, memória de dados e instrução; <span style="background:#d4b106">Design lógico que define como o software interage com o hardware</span>.

#Organização_de_computadores: refere-se às unidades operacionais e suas interconexões que percebam as especificações de arquitetura. Os exemplos de atributos de arquitetura incluem o conjunto de instrução, o número de bits usados para representar vários tipos de dados (por exemplo, números, caracteres), mecanismos de E/S e técnicas de endereçamento de memória. Refere-se aos detalhes internos do hardware que implementam a arquitetura.

Por exemplo, é uma questão de #arquitetura se um computador terá uma instrução de multiplicação. É uma questão de #organização se essa instrução será implementada por uma unidade de multiplicação especial ou por um mecanismo que faça uso repetido da unidade de adição do sistema. A **decisão organizacional** pode ser baseada na previsão da frequência de uso da instrução de multiplicação, na velocidade relativa das duas técnicas e no custo e tamanho físico de uma unidade de multiplicação especial. 

Muitos fabricantes de computador oferecem uma família de modelos de computador, todos com a mesma arquitetura, mas com diferenças na organização. Uma #arquitetura em particular pode se espalhar por muitos anos e abranger diversos modelos diferentes de computador, com sua # organização variando conforme a mudança da tecnologia. 

Em uma classe de computadores chamada microcomputadores, o relacionamento entre arquitetura e organização é muito próximo. As mudanças na tecnologia não só influenciam a organização, mas também resultam na introdução de arquiteturas mais poderosas e mais complexas.  

## 1.2 Estrutura e Função


