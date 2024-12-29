## Principais Habilidades e Conceitos
- Controlar o acesso a membros;
- Passar objetos para um método
- Retornar objetos de um método
- Sobrecarregar métodos
- Sobrecarregar construtores
- User recursão
- Aplicar #static 
- Usar classes internas
- Usar varargs

Este capítulo tem por objetivo retomar o estudo sobre classes e métodos. Começamos explicando como controlar o acesso aos membros de uma classe. Em seguida, discutimos a passagem e o retorno de objetos, a sobrecarga de métodos, a recursão e o uso da palavra-chave #static.

## Controlando o acesso a membros de classes
Em seu suporte ao #encapsulamento, a classe fornece dois grandes benefícios:
1) ela vincula os dados (variáveis de instância) ao código que os trata (métodos);
2) fornece o meio pelo qual o acesso a membros pode ser controlado;

Existem dois tipos básicos de membros de classe: #publicos e #privados.
Um membro público pode ser acessado livremente por um código definido fora de sua classe.
Um membro privado só pode ser acessado por outros métodos definidos por sua classe. 

A restrição do acesso a membros de uma classe é parte fundamental da programação orientada a objetos, porque ajuda a impedir a má utilização de um objeto. Ao permitir o acesso a dados privados apenas por intermédio de um conjunto de métodos bem definidos, impedimos que valores inapropriados sejam definidos para os dados de uma classe. Um código de fora da classe não pode definir um valor para uma variável de dado diretamente. Logo, quando corretamente implementada, uma classe cria uma "caixa preta" que pode ser usada, mas cujo funcionamento interno não está aberto a alterações. 

## Modificadores de acesso da linguagem Java
 O controle de acesso a membros é obtido com o uso de três *modificadores de acesso*: #public, #private  e #protected. Se nenhum modificador de acesso for utilizado, será presumido o uso da configuração de acesso padrão. 

Quando o membro de uma classe é modificado pelo especificador #public, esse membro pode ser acessado por *qualquer código do programa*. Isso inclui métodos definidos dentro de outras classes. 
Quando o membro de uma classe é especificado como #private, ele só pode ser acessado por outros membros de sua classe. Logo, métodos de classes diferentes não podem acessar um membro #private de outra classe. 

Um modificador de acesso precede  o resto da especificação de tipo de membro. Isto é, ele deve começar a instrução de declaração do membro. Segue o exemplo:
```java
	public String errMsg;
	public accountBalance bal;

	private boolean isError(byte status) {
	// ...
	}
```

Para entender os efeitos, consideramos o programa a seguir:
``` java
public class App {
	public static void main(String[] args) {
		Myclass ob = new MyClass();
		ob.setAlpha(-99);
		System.out.println("ob.alpha is " + ob.getAlpha())
	}

	// Acesso público versus privado
	class MyClass {
		private int alpha; // acesso privado
		public int beta; // acesso público
		int gamma; // acesso padrão
		
		void setAlpha (int a) {
			alpha = a
		}
		
		int getAlphA() {
			return alpha;
		}
	}
}
```

Como alpha é privado, não pode ser acessado por um código de fora de sua classe. Devendo ser acessado de forma intermediária através de seus métodos acessadores públicos: #setAlpha e #getAlpha