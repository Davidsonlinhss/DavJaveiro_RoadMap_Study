### Principais habilidades e conceitos
- Entender os aspectos básicos de herança;
- Chamar construtores de superclasse;
- Usar #super para acessar membros da superclasse;
- Criar uma hierarquia de classes com vários níveis;
- Saber quando os construtores são chamados;
- Entender as referências da superclasse a objetos da subclasse;
- Sobrepor métodos;
- Usar métodos sobrepostos para executar o despacho dinâmico;
- Usar classes abstratas;
- Usar #final;
- Conhecer a classse #Object.
\
#Herança é um dos três princípios básicos da programação orientada a objetos, porque permite a criação de classificações hierárquicas. Usando herança, podemos criar uma **classe geral** que defina **características comuns** a um conjunto de itens relacionados. Essa classe poderá então ser herdada por outras classes mais específicas, cada uma adicionando suas características exclusivas. 

No jargão Java, a classe que é herdada se chama #superclasse. A classe que herda se chama #subclasse. Portanto, uma subclasse **é uma versão especializada** da superclasse. Ela herda todas as variáveis e métodos definidos pela superclasse e adiciona seus próprios elementos exclusivos. 

## Aspectos básicos de herança


## Implementando interfaces
Quando uma interface tiver sido definida, uma ou mais classes poderão implementá-la. Para implementar uma interface, inclua a cláusula #implementes em uma definição de classe e então crie os métodos requeridos pela interface. A forma geral de uma classe que inclui a cláusula #implements é a seguinte:
```Java
	class nomeclasse extends superclasse implements interface {
		// corpo-classe
	}
```
Na implementação de mais de uma interface, as interfaces são separadas por uma vírgula. Obviamente, a cláusula #extends é opcional.

Os métodos que implementam uma interface devem ser declarados como #public. Além disso, a assinatura de tipo do método implementador deve coincidir exatamente com a assinatura de tipo especificada na definição da #interface. 

Exemplo que implementa a interface **Series**. Cria-se uma classe chamada **ByTwos**, que gera uma série de números, onde a cada dois números temos um valor maior do que o anterior. 

Observamos que os métodos getNext(), reset() e setStart() são declarados com o uso do especificador de acesso #public. Isso é necessário. Sempre que implementarmos um método definido por uma interface, ele deve ser implementado como #public, porque todos os membros de uma interface são implicitamente #public.

É permitido e comum classes que implementam interfaces definirem membros adicionais. Por exemplo, a versão a seguir de **ByTwos** adiciona o método **getPrevious()**, que retorna o valor anterior. 

Observamos que a inclusão de **getPrevious()** demandou uma alteração nas implementações dos métodos definidos por Series. No entanto, já que a interface dos métodos permaneceu igual, a alteração ocorreu normalmente e não prejudicou nenhum código preexistente. Essa é uma das vantagens das interfaces. 

Como explicamos, um número ilimitado de classes pode implementar uma #interface. Por exemplo, esta é uma classe chamada #ByThrees...

Se uma classe incluir uma interface, mas não implementar totalmente os métodos definidos por ela, deve ser declarada como #abstract. Não poderão ser criados objetos dessa classe, mas ela poderá ser usada como superclasse abstrata, permitindo que subclasses forneçam a implementação completa. 

## Usando referência de interfaces
Podemos declarar uma **variável de referência de um tipo de interface**. Podemos criar uma variável de referência de interface. Uma variável assim pode referenciar qualquer objeto que implemente sua interface. Quando chamamos um método em um objeto por intermédio de uma referência de interface, a versão do método implementada pelo objeto será executada. Uma variável de referência de #interface só tem conhecimento dos métodos declarados por sua declaração #interface, logo, não pode ser usada para acessar nenhuma outra variável ou método ao qual o #objeto dê suporte.

## Variáveis em interfaces
Como mencionado, podemos declarar variáveis em uma interface, mas elas serão implicitamente #public, #static e #final. À primeira vista, podemos pensar que, para essas variáveis, haveria uma **aplicação muito limitada**, mas é o contrário que acontece. Normalmente, programas grandes fazem uso de diversos valores constantes que descrevem coisas como o tamanho de array, limites, valores especiais etc. Já que um programa grande costuma ser mantido em muitos arquivos-fonte separados, é preciso haver uma maneira conveniente de disponibilizar essas constantes para cada arquivo. 

Para definir um conjunto de constantes compartilhadas, crie uma interface contendo apenas as constantes, sem nenhum método. Cada arquivo que precisar de acesso às constantes só precisará "implementar" a interface. Isso dará visibilidade às constantes. Aqui está um exemplo:



## Usando Classes abstratas
Podemos querer criar uma superclasse que defina uma forma generalizada para ser compartilhada por todas as suas subclasses, deixando que cada subclasse insira os detalhes. Esse tipo de classe determina a natureza dos métodos que as subclasses devem implementar, mas não fornece uma implementação de um ou mais desses métodos. Uma maneira de essa situação ocorrer é quando uma superclasse não pode criar uma implementação significativa de um método. 

Consideramos a classe **Triangle**, ela ficaria incompleta se area() não fosse definido. Nesse caso, querermos de alguma maneira assegurar que uma #subclasse sobreponha realmente todos os métodos necessários. A solução Java para esse problema é o *método abstrato*. 

O #método_abstrato é criado pela especificação do modificador de tipo #abstract. Ele não contém corpo e, portanto, não é implementado pela superclasse. Logo, uma subclasse deve sobrepô-lo ela não pode apenas usar a versão definida na superclasse. Para declarar um método abstrato, usamos esta forma geral:
``` java
abstract tipo nome(lista-parâmetros);
```
Como ficou claro, não há um corpo de método presente. O modificador #abstract só pode ser usado em métodos de instância. Ele não pode ser aplicado a métodos #static ou a construtores. 

Uma classe que contém um ou mais *métodos abstratos* também deve ser declarada como abstrata precedendo sua declaração #class com o modificador #abstract. Já que uma classe abstrata não define uma implementação completa, não pode existir objetos dessa classe. Logo, tentar criar um objeto de uma classe abstrata usando #new resultará em um erro de tempo de compilação. 

Quando uma subclasse herda uma classe abstrata, ela deve implementar todos os métodos abstratos da superclasse. 