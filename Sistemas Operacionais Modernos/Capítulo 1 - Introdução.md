Se todo programador de aplicativos tivesse de compreender como todas as partes de um computador funciona, nenhuma código jamais seria escrito. Além disso, gerenciar todos esses componentes e usá-los de maneira otimizada é um trabalho extremamente desafiador. Por essa razão, computadores são equipados com um dispositivo de software chamado de #sistema #operacional, cuja função é fornecer aos programas de usuários um modelo de computador melhor, mais simples e mais limpo. O **programa com o qual os usuários interagem**, normalmente chamado de #shell (ou **interpretador de comandos**) quando ele é **baseado em texto** e de #GUI (Graphical User Interface) quando ele usa ícones, na realidade não é parte do sistema operacional, embora use esse sistema para realizar o seu trabalho. 

Uma visão simplificada dos principais componentes em discussão é a seguinte:
Figura 1.1 - Onde o sistema operacional se encaixa
![[Capítulo 1 - Introdução.png]]
1) Podemos ver o #hardware na parte inferior, ele consiste em chips, placas, discos e um teclado, um monitor e objetos físicos similares;
Em cima do hardware está o software. A maioria dos computadores tem dois modos de operação:

- <span style="background:#d4b106">Modo núcleo</span>: o sistema operacional, a peça fundamental de software, opera em #modo_núcleo (também chamado modo supervisor).  Nesse modo, ele tem acesso completo a todo o hardware e pode executar qualquer instrução que a máquina for capaz de executar. 

- <span style="background:#d4b106">Mudo usuário</span>: o restante do software opera nesse modo, no qual apenas um subconjunto das instruções da máquina está disponível. Em particular, as instruções que afetam o controle da máquina ou realizam E/S (entrada/saída) são proibidas para programas de modo usuário.

O programa de interface com o usuário, shell ou GUI, é o **nível mais inferior de software** de modo usuário, e permite que ele inicie outros programas, como um navegador da web, leite de e-mail, ou reprodutor de música. Esses programas, também, utilizam bastante o sistema operacional. 

O sistema operacional opera diretamente sobre o #hardware e proporciona a base para todos os outros softwares. 

Uma distinção importante entre o sistema operacional e o software normal (modo usuário) é que se um usuário não gosta de um leitor de e-mail em particular, ele é livre para para conseguir um leitor diferente ou escrever seu próprio, se assim quiser; ele não é livre para escrever seu próprio tratador de interrupção de relógio, o qual faz parte do sistema operacional e é protegido por hardware contra tentativas de usuários de modificá-lo.

Essa distinção, no entanto, às vezes é confusa em sistemas embarcados (que podem não ter o modo núcleo) ou interpretados (como baseados em Java que usam interpretação, não hardware, para separar os componentes). 

Também, em muitos sistemas há programas que operam em modo usuário, mas ajudam o sistema **operacional** ou **realizam funções privilegiadas**. Por exemplos, muitas vezes há um programa que permite aos usuários que troquem suas senhas. Não faz parte do sistema operacional e não opera em modo núcleo, mas claramente realiza uma função sensível e precisa ser protegido de maneira especial. 

Os sistemas operacionais diferem de programas de usuário (isto é, de aplicativos) de outras maneiras além de onde estão localizados. Em particular, eles são enormes, complexos e têm vida longa. O código-fonte do coração de um sistema operacional como Linux ou Windows tem cerca de cinco milhões de linhas. 

Além do Windows, o outro exemplo fundamental que usaremos ao longo deste livro é o #UNIX e suas variáveis e clones. Ele também evolui com os anos, com versões como o System V, Solaris e FreeBSD sendo derivadas do sistema original, enquanto o Linux possui um código base novo, embora muito proximamente modelado no UNIX e muito compatível com ele.

## 1.1 <font color="#ffc000">O que é um sistema operacional?</font>
É difícil dizer com absoluta precisão o que é um sistema operacional, além de ele ser o software que **opera em modo núcleo** **–** e mesmo isso nem sempre é verdade. Parte do problema é que os sistemas operacionais realizam duas funções essencialmente não relacionadas:
1) fornecer a programadores de aplicativos (e programas de aplicativos, claro) um conjunto de recursos abstratos limpo em vez de recursos confusos de hardware;
2) gerenciar os recursos de hardware;

### 1.1.1 O sistema operacional como uma máquina estendida
A #arquitetura (conjunto de instruções, organização de memória, E/S e estrutura de barramento) da maioria dos computadores em nível de linguagem de máquina é primitiva e complicada de programar, especialmente para entrada/saída. Para deixar esse ponto mais claro, consideramos os discos rígidos modernos SATA usados na maioria dos computadores. Um livro (ANDERSON, 2007) descrevendo uma versão inicial da interface do disco – o que um programador deveria saber para usar o disco –, tinha mais de 450 páginas. Desde então, a interface foi revista múltiplas vezes e é mais complicada do que em 2007. É claro que nenhum programador são iria querer lidar com esse disco em nível de hardware. Em vez disso, um software, chamado #driver_de_disco, lida com o hardware e fornece uma interface para ler e escrever bloco de dados, sem entrar nos detalhes. Sistemas operacionais contêm muitos drivers para controlar dispositivos de E/S. 

Mas mesmo esse nível é baixo demais para a maioria dos aplicativos. Por essa razão, todos os sistemas operacionais fornecem mais um **nível de abstração** para se utilizarem discos: arquivos. 
Usando essa abstração, os programas podem criar, escrever e ler arquivos, sem ter de lidar com os detalhes complexos de como o hardware realmente funciona. 

Boas abstrações transformam uma tarefa praticamente impossível em duas **tarefas** gerenciáveis. A primeira é definir e implementar as abstrações. A segunda é utilizá-las para solucionar o  problema à mão. A função dos sistemas operacionais é criar boas abstrações e então implementar e gerenciar os objetos abstratos criados desse modo. 

Uma das principais tarefas dos sistemas operacionais é esconder o hardware e em vez disso apresentar programas (e seus programadores) com abstrações de qualidade, limpas, elegantes e consistentes com as quais trabalhar. 

Deve se observar que os clientes reais dos sistemas operacionais são os programas de aplicativos (via programadores de aplicativos, é claro). São eles que **lidam diretamente com as abstrações** fornecidas pela interface do usuário, seja uma linha de comando (shell) ou uma interface gráfica. 

Neste livro, será trabalhado o estudo das abstrações fornecidas aos programas aplicativos, mas falaremos bem menos sobre interfaces com o usuário. 

### 1.1.2 O sistema operacional como um gerenciador de recursos
O conceito de um sistema operacional como fundamentalmente fornecendo abstrações para programas aplicativos é uma visão #top-down (abstração de cima para baixo). Uma visão alternativa, #bottom-up (abstração de baixo para cima), sustenta que o sistema operacional está ali para gerenciar todas as partes de um sistema complexo. Computadores modernos consistem de processadores, memórias, temporizadores, discos, dispositivos apontadores do tipo mouse, interfaces de rede, impressoras e uma ampla gama de outros dispositivos. Na visão bottom-up, a função do sistema operacional é fornecer uma **alocação ordenada e controlada** dos #processadores, #memórias e dispositivos de E/S entre os vários programas competindo por eles.
Sistemas operacionais modernos permitem que múltiplos programas estejam na memória e sejam executados ao mesmo tempo. 

A sua principal função é manter um controle sobre quais programas estão usando qual recurso, conceder recursos requisitados, contabilizar o seu uso, assim como mediar requisições conflitantes de diferentes programas e usuários. 

O gerenciamento de recursos inclui a #multiplexação (compartilhamento) de recursos de duas maneiras diferentes: no tempo e no espaço. Quando um recurso é multiplexado no tempo, diferentes programas ou usuários se revezam usando-o. Determinar como o recurso é multiplexado no tempo **–** quem vai em seguida e por quanto tempo **–** é a tarefa do sistema operacional. 

A multiplexação no espaço é quando separamos as partes do hardware para cada cliente, o acesso é limitado por porções do hardware. Alocar espaço de disco e controlar quem está usando quais blocos do disco é uma tarefa típica do sistema operacional. Presumindo que há memória suficiente para manter múltiplos programas, é mais eficiente manter vários programas na memória ao mesmo tempo do que fornecer toda a memória  para um único programa em execução. 

## 1.3 Revisão sobre hardware de computadores
Um sistema operacional está intimamente ligado ao hardware do computador no qual ele é executado. Ele **estende o conjunto de instruções** do computador e **gerencia seus recursos**. Para funcionar, ele deve conhecer profundamente o hardware, pelo menos como aparece para o programador. 

Conceitualmente, um computador pessoal simples pode ser abstraído em um modelo que lembra a figura 1.6 abaixo:
![[Capítulo 1 - Introdução-1.png]]
A CPU, memória e dispositivos de E/S estão totalmente conectados por um sistema de barramento e comunicam-se uns com os outros sobre ele. Computadores pessoais modernos têm uma estrutura mais complicada, envolvendo múltiplos #barramentos, os quais examinaremos mais tarde. Por ora, este modelo será suficiente. 

## 1.3.1 Processadores
O "cérebro" do computador é a CPU. Ela busca instruções da memória e as executa. O ciclo básica de toda CPU é buscar a primeira instrução da memória, decodificá-la para determinar o seu tipo e operandos, executá-la, e então buscar, decodificar e executar as instruções subsequentes. O ciclo é repetido até o programa terminar. É dessa maneira que os programas são executados. 

Cada CPU **tem um conjunto específico de instruções** que ela consegue executar. Desse modo, um processador x86 não pode executar programas ARM e um processador ARM não consegue executar programas x86.

Como **o tempo para acessar a memória para buscar uma instrução ou palavra de operando** é muito maior do que o tempo para executar uma instrução, todas as CPUs têm alguns #registradores internos para armazenamento de variáveis e resultados temporários. Desse modo, o conjunto de instruções geralmente contém instruções para carregar uma palavra da memória para um registrador e armazenar uma palavra de um registrador para a memória. 

Além dos registradores gerais usados para armazenar variáveis e resultados temporários, a maioria dos computadores tem vários registradores especiais que são visíveis para o programador. Um desses é o **contador de programas** que contém o endereçamento de memória da próxima instrução a ser buscada. Após essa instrução ter sido buscada, o contador de programa é atualizado para apontar a próxima instrução. 

Outro registrador é o **ponteiro de pilha**, que aponta para o topo da pilha atual na memória. A pilha contém uma estrutura para cada rotina que foi chamada, mas ainda não encerrada. Uma estrutura de pilha de rotina armazena aqueles parâmetros de entrada, variáveis locais e variáveis temporárias que não são mantidas em registradores. 

Outro registrador ainda é o PSW (Program Status Word - palavra de estado do programa). Esse registrador contém os bits do código de condições, que são estabelecidos por instruções de comparação, a prioridade da CPU, o modo de execução (usuário ou núcleo) e vários outros bits de controle. Programas de usuários normalmente podem ler todo o PSQ, mas em geral podem escrever somente parte dos seus campos. O PSW tem um papel importante nas chamadas de sistema e em E/S.

O sistema operacional deve estar absolutamente ciente de todos os registros. Quando realizando a multiplexação de tempo da CPU, ele muitas vezes vai interromper o programa em execução para (re)começar outro. Toda vez que ele para um programa em execução, o sistema operacional tem de salvar todos os registradores de maneira que eles possam ser restaurados quando o programa for executado mais tarde. 

Muitas CPUs modernas têm recursos para executar mais de uma instrução ao mesmo tempo. Por exemplo, uma CPU pode ter unidades de busca, decodificação e execução separadas, assim enquanto ela está executando a instrução *n*, poderia também estar decodificando a instrução *n+1* e buscando a instrução *n+2*. 

Uma CPU superescalar é mais avançada do que um projeto de pipeline. Nesse projeto, unidades múltiplas de execução estão presentes. Uma unidade para aritmética de números inteiros, ponto flutuante e para operações booleanas. Duas ou mais instruções são buscadas ao mesmo tempo, decodificadas e jogadas em um buffer de instrução até que possam ser executadas. Tão logo uma unidade de execução fica disponível, ela procura no buffer de instrução para ver se há uma instrução que ela pode executar e, se assim for, ela remove a instrução do buffer e a executa. Uma implicação desse projeto é que as **instruções do programa são muitas vezes executadas fora de ordem**. Em geral, **cabe ao hardware** certificar-se de que o resultado produzido é o mesmo que uma implementação sequencial conseguiria, mas como veremos adiante, **uma quantidade incômoda de tarefas complexas** é <font color="#ffff00">empurrada</font> **para o sistema operacional**. 

A maioria das CPUs - exceto aquelas muito simples usadas em sistemas embarcados , tem dois modos, núcleo e usuário, como mencionado anteriormente. Em computadores de mesa e servidores, o sistema operacional normalmente opera em modo núcleo, dando a ele acesso a todo o hardware.
Na maioria dos sistemas embarcados, uma parte pequena opera em modo núcleo, com o resto do sistema operacional operando em modo usuário. 

**Programas de usuários** sempre são executados em modo usuário, o que permite que apenas um subconjunto das instruções possa ser executado e um subconjunto dos recursos possa ser acessado.  Geralmente, todas as instruções envolvendo E/S e proteção de memória são inacessíveis no modo usuário. 

**Chips multithread e multinúcleo**
