## Entidade user e resource
**Basic entity checklist** - sempre que criarmos qualquer entidade em Java, devemos criar mais ou menos na seguinte ordem:
1. **Atributos básicos**:
São as <span style="background:#d3f8b6">variáveis de instância</span>, os dados, do membro da classe, neste caso, no projeto:
- id: Long - #Long e #Integer são tipos de dados numéricos utilizados para **armazenar valores inteiros** em diversas linguagens de programação, incluindo Java. A principal diferença entre eles reside no **tamanho** e no **intervalo de valores** que podem representar.

#Integer ocupa menos espaço em memória e possuí um intervalo de valores menor. É ideal para representar **números inteiros** que **não exigem** uma grande faixa de valores.

#Long: ocupa mais espaço em memória e possui um intervalo de valores muito maior. É utilizado para representar números inteiros grandes, como identificadores únicos, timestamps ou valores que podem exceder o limite do tipo Integer. O uso pode ser frequente em identificadores únicos (IDs) em sistemas com muitos registros e também em cálculos envolvendo números extremamente grandes. 

- name: String
- email: String
- phone: String
- password: String

2. **Associações (instantiate collections)**;
Como é a primeira classe, não haverá associações;

3. **Constructors**
São os métodos construtores. 

4. Getters & Setters (collection: only get);
#Getters: São os métodos que permitem acessar (ou "obter") o valor de um atributo privado de uma classe;
#Setters: são métodos que permitem modificar (ou "definir") o valor de um atributo privado de uma classe;
**Vantagens:** 
1. Encapsulamento - permitem proteger os dados, garantindo que a manipulação dos atributos ocorra de maneira controlada;
2. Validação - Possibilitam incluir a lógica de validação no método setter antes de alterar o valor do atributo;
3. Flexibilidade - tornam mais fácil de mudar a implementação interna de uma classe sem afetar o código que usa a classe, desde que os métodos mantenham a mesma assinatura.

4. hashCode & equals;
Por padrão só comparamos o id, não havendo necessidade de outros valores para comparação, logo depende do critério que formos utlizar para realizar a comparação.

5. **Serializable**:
Permite que os objetos sejam transformados em cadeias de bytes, logo, os objetos poderão trafegar na rede de dados, gravado em arquivos etc...
