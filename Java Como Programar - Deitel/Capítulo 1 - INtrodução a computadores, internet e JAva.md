*O homem ainda é o computador mais extraordinário. - John F. Kennedy*

# 1.4 Linguagens de #máquina, #assembly e de alto nível
## Linguagens de máquina
As linguagens de máquinas são dependentes de máquina (uma determinada linguagem de máquina pode ser utilizada apenas para para um tipo de computador). Elas são complicadas para seres humanos. A linguagem de máquina consiste geralmente em string de números (em última instância, reduzidas a 1s e 0s). 

## Linguagens #assembly e #assemblers
A programação em linguagem de máquina era muito lenta e tediosa para a maioria dos programadores, em vez de utilizar strings de números que os computadores poderiam entender de maneira direta, os programadores começaram  a usar abreviações em inglês para representar operações elementares. Essas abreviações formaram a base de #linguagens #assembly. Programas tradutores chamados #assemblers foram desenvolvidos para converter os primeiros programas de linguagem assembly em linguagens de máquina a velocidades de computadores. 

## Linguagens de alto nível e compiladores
Com o advento das linguagens assembly, o uso de computadores aumentou rapidamente. Com intuito de acelerar o processo de programação, foram desenvolvidas linguagens de #alto-nível em que instruções únicas poderiam se escritas para realizar tarefas substanciais. Os programas tradutores chamados #compiladores convertem os programas de linguagem de alto nível em linguagem de baixo nível ou em linguagem de máquina. 
Do ponto de vista do programador, as linguagens de alto nível são preferíveis às de máquina e às assembly. O Java é uma das linguagens de programação de alto nível mais amplamente usadas.

## Interpretadores
Compilar um programa grande de alto nível em linguagem de máquina pode levar muito tempo de computador. Os programas interpretadores, desenvolvidos para executar diretamente programas de linguagem de alto nível, evitam o tempo de espera de compilação, embora sejam mais lentos do que programas compilados. Java utiliza uma combinação refinada de desempenho e inteligente de compilação e interpretação para executar os programas.

# Introdução à tecnologia de objetos
#Objetos ou, mais precisamente, as #classes de onde os objetos vêm são essencialmente componentes **reutilizáveis** de software. Há objetos data, data/hora, objetos áudio, objetos vídeo, objetos automóvel, objetos pessoas etc. Quase qualquer *substantivo* pode ser razoavelmente representado com um objeto de software em termos dos atributos (por exemplo, nome, cor e tamanho) e *comportamentos* (por exemplo, calcular, mover e comunicar). 

### 1.5.1 O automóvel como objeto
Suponhamos que queiramos guiar um carro e acelerar cada vez mais pisando no pedal do acelerador do veículo. O pedal oculta do motorista os complexos mecanismos que fazem o carro ir mais rápido, e o veículo é projetado pela engenharia, através de seu esboço. 

### 1.5.2 Métodos e classes
Para realizar uma tarefa em um carro é necessário um #método. O método <span style="background:rgba(240, 200, 0, 0.2)">armazena as declarações</span> do programa que, na verdade, executam tarefas; além disso, ele oculta essas declarações do usuário, assim como o pedal do acelerador. Em Java, criamos uma unidade de programa chamada #classe para armazenar o conjunto de métodos que executam tarefas dela. Por exemplo, uma classe que representa uma conta bancária poderia conter um método para fazer depósitos de dinheiro, outro para fazer saques e um terceiro para perguntar qual é o saldo atual. Uma classe é similar em termos de conceitos aos desenhos de engenharia de um carro, que armazenam o projeto de um pedal de acelerador, volante etc.

### 1.5.3 Instanciação
Assim como alguém tem de fabricar um carro a partir dos desenhos de engenharia antes que possa realmente dirigi-lo, você deve *construir um objeto* de uma classe que um programa possa executar as tarefas que os métodos da classe definem. O processo para fazer isso é chamado de #instanciação. Um objeto é então referido como uma #instância da sua classe. 