## Principais habilidades e conceitos
- Entender os aspectos básicos da herança
- Chamar construtores de superclasses
- Usar #super para acessar membros da superclasse
- Criar uma hierarquia de classes com vários níveis
- Saber quando os construtores são chamados
- Entender as referências da superclasse a objetos da subclasse
- Sobrepor métodos
- Usar métodos sobrepostos para executar o despacho dinâmico de métodos
- Usar classes abstratas
- Usar #final
- Conhecer a classe #Object

----------------------------------------------------------------------------
Herança é um dos três princípios básicos da programação orientada a objetos, porque permite a criação de classificações hierárquicas. Usando herança, você pode criar uma classe geral que defina características comuns a um conjunto de itens relacionados. Então, essa classe poderá ser herdada por outras classes mais específicas, cada uma adicionando suas características exclusivas.
No jargão Java, a classe que é herdada se chama #superclasse. A classe que herda se chama #subclasse. Portanto, uma subclasse é uma versão especializada da superclasse. Ela herda todas as variáveis e métodos definidos pela superclasse e adiciona seus próprios elementos exclusivos. 

# <span style="background:#d4b106">Aspectos básicos de herança</span>
Java dã suporte à herança, permitindo que uma classe incorpore outra em sua declaração. Isso é feito com o uso da palava-chave #extends. Portando, a subclasse traz acréscimos (estende) à superclasse. 

Um objeto de uma superclasse não conhece e não acessa qualquer subclasse sua. 
```Java
class subclasse extends superclasse { 
// corpo da classe
}
```

Só podemos especificar uma única superclasse para qualquer subclasse que criarmos. Podemos criar uma hierarquia de herança em que uma subclasse passe a ser a superclasse de outra subclasse. Obviamente, nenhuma classe pode ser superclasse de si mesma. 

Cada subclasse pode especificar com precisão sua própria classificação. 

# **Acesso a membros e a herança**
Herdar uma classe não invalida a restrição de acesso **private**. Logo, ainda que uma subclasse inclua todos os membros de sua superclasse, não poderá acessar os membros declarados como #private. Logo, membros privados não são herdados para a subclasse. 

	Quando devo tornar privada uma variável de instância?
	Não há regras fixas, mas aqui estão dois princípios gerais. Se uma variável de instância for usada apenas por métodos definidos dentro de sua classe, ela devera ser privada. Se tiver que estar dentro de certos limites, deve ser privada e só estar disponível por intermédio de métodos acessadores. Dessa forma, podermos impedir que valores inválidos sejam atribuídos.

# Construtores e herança
É possível que tanto as superclasses quanto as subclasses tenham seus próprios construtores. Então, que construtor será o responsável pela construção de um objeto da subclasse - o da superclasse, ou da subclasse ou ambos? A resposta é essa:
- O construtor da superclasse constrói a parte do objeto referente à superclasse e o construtor da subclasse constrói a parte da subclasse.
Faz sentido, visto que a superclasse não conhece ou acessa nenhum elemento de uma subclasse. 

Quanto tanto a **superclasse** quanto a **subclasse** definem **métodos construtores**, o processo é um pouco mais complicado, porque os dois construtores devem ser executados. Neste caso, devemos usar outra das palavras-chave Java, #super. Possuindo duas formas gerais:
- A primeira chama um construtor da superclasse
- A segundo é usada para acessar um membro da superclasse ocultado pelo membro de uma subclasse. 

# Usando super para chamar construtores da superclasse
Uma subclasse pode chamar um construtor definido por sua superclasse usando a forma de #super a seguir:
*Super(lista-parâmetros);*
A primeira instrução executada dentro do construtor de uma **subclasse** deve sempre ser #super(). 
```Java
Triangle(String s, double w, double h) {
	super(w, h); // chama o construtor da superclasse
	style = s;
}
```
Toda forma de construtor definida pela superclasse pode ser chamada por #super. O construtor executado será o que tiver os argumentos correspondentes. Quando uma subclasse chama #super, está chamando o construtor de sua superclasse imediata. Portanto, #super sempre referencia a superclasse imediatamente acima da classe chamadora. Além disso, super() deve ser sempre a primeira instrução executada dentro de um construtor de subclasse. 

# Usando super para acessar membros da superclasse
Há uma segunda forma de **super** que age um pouco como #this, exceto por referenciar sempre a superclasse da subclasse em que é usada. Essa aplicação tem a forma geral a seguir:
super.*membro*
Aqui, membro pode ser um método ou uma variável de instância.
Essa forma de super é mais aplicável a situações em que os nomes dos membros de uma subclassse ocultam membros com o mesmo nome na superclasse. 

# Criando uma hierarquia de vários níveis

