## Principais habilidades e conceitos
- Usar pacotes
- Entender como os pacotes afetam o acesso
- Aplicar o modificador de acesso #protected 
- Importar pacotes
- Conhecer os pacotes padrão Java
- Entender os aspectos básicos da interface
- Implementar uma interface
- Aplicar referências de interface
- Entender as variáveis de interface
- Estender interfaces
- Criar métodos de interface padrão e estáticos

Este capítulo examina dois recursos cruciais e inovadores em Java: os pacotes e as interfaces. #Pacotes são grupos de classes relacionadas. Os pacotes ajudam a organizar o código e fornecem outra camada de encapsulamento.
#Interface define um conjunto de métodos que será implementado por uma classe. Logo, a interface fornece uma maneira de especificarmos o que uma classe fará, mas não como ela fará. Os pacotes e as interfaces proporcionam um controle maior sobre a organização do programa. 

## Interfaces
Na programação orientada a objetos, às vezes é útil definir o que uma classe deve fazer, mas não como ela fará. Já vimos um exemplo disso: o método abstrato. Um método abstrato define uma assinatura de um método, **mas não fornece implementação**. Uma subclasse deve fornecer sua própria implementação de cada método abstrato definido por sua superclasse. Portanto, um método abstrato especifica a *interface* do método, mas não a *implementação*. Embora as classes e métodos abstratos sejam úteis, podemos levar esse conceito um passo adiante. Em Java, podemos separar totalmente a interface de uma classe de sua implementação usando a palavra-chave #interface.

Uma #interface é sintaticamente semelhante a uma classe abstrata no fato de podermos especificar um ou mais métodos sem corpo. Esses **métodos devem ser implementados** por uma classe para que suas ações sejam definidas. Logo, uma interface especifica o que deve ser feito, mas não como deve ser feito. Quando uma interface é definida, não há limite para o número de classes que podem implementá-la. 

Para implementar uma interface, a classe deve fornecer corpos (implementações) para os métodos descritos nela. Duas classes podem implementar a mesma interface de diferentes maneiras, mas ambas darão suporte ao mesmo conjunto de métodos. 

Quando não é incluído um modificador de acesso, isso resulta no acesso padrão e a interface só fica disponível para outros membros de seu pacote.

As #variáveis declaradas em uma interface não são variáveis de instância. Em vez disso, são implicitamente #public, #final e #static e devem ser inicializadas. Portanto, são basicamente constantes. Aqui está o exemplo de uma definição de #interface:
```Java
	public interface Series {
		int getNext(); // retorna o próximo número da série
		void reset(); // reinicia
		void setStart (int x); // define o valor inicial
	}
```
Essa interface é declarada como #public para poder ser implementada por códigos de qualquer pacote. 

## Implementando interfaces
Várias classes podem implementar uma #interface quando ela tiver sido definida. Para implementar uma #interface, basta usarmos a palavra #implements em uma definição de classes e então criamos os métodos requeridos pela interface. A forma geral é como segue:
```Java
	class nomeClasse extends superClasse implements interface {
		// corpo-classe
	}
```

No exemplo acima, a subclasse nomeClasse, implementa a interface. Na implementação de mais de uma interface, as interfaces são separadas por uma vírgula. Obviamente, a cláusula #extends é opcional. 

Os métodos que implementam uma interface devem ser declarados como #public. Além disso, a assinatura de tipo do método implementador deve coincidir exatamente com a assinatura de tipo especificada na definição da #interface. 

Exemplo, cria uma classe chamada **ByTwos**, que gera uma série de números, onde a cada dois números temos um valor maior do que o anterior:
``` java
// Implementa Series
	class ByTwos implements Series {
		int start;
		int val;

		ByTwos() {
			start = 0;
			val = 0;
		}

		public int getNext() {
			val += 2;
			return val;
		}

		
		public void reset() {
		val
		}

	}
```

Observamos que os métodos getNext(), reset() e setStart() são declarados com o uso do especificador de acesso #public. Sempre que implementarmos um método definido por uma interface, ele deve ser implementado como #public, porque todos os membros de uma interface são implicitamente #public.