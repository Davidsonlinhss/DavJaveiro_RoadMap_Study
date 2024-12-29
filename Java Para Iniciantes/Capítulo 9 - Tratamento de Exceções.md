## <span style="background:#d4b106">Principais habilidades e conceitos</span>
- Conhecer a hierarquia de exceções
- Usar #try e #catch
- Entender os efeitos de uma exceção não capturada
- Usar várias instruções #catch 
- Capturar exceções de subclasse
- Aninhas blocos try
- Lançar uma exceção
- Saber os menbros de #Throwable
- Usar #finally
- Usar #throws
- Conhecer as exceções internas Java
- Criar classes de exceção personalizadas

Este capítulo discutirá o tratamento de exceções. **Uma exceção é um erro que ocorre no tempo de execução**. Usando o subsistema Java de tratamento de exceção, podemos tratar **erros de tempo de execução** de forma estruturada e controlada. Embora a maioria das linguagens de programação modernas ofereça algum tipo de tratamento de exceções, o suporte Java é fácil de usar e flexível. 

A principal vantagem do tratamento de exceções é que ele automatiza grande parte do código de tratamento de erros que antigamente tinha que ser inserido "**à mão**" em qualquer programa grande. Por exemplo, em algumas linguagens de computador mais antigas, os códigos de erro são retornados quando um método falha e esses valores devem ser verificado manualmente sempre que o método é chamado. Essa abordagem é, ao mesmo tempo, tediosa e propensa a erros. O tratamento de exceções otimiza o tratamento de erros, permitindo que o programa defina um bloco de código, chamado *tratador de exceções*, executado automaticamente quando um erro ocorre. Não é necessário verificar manualmente o sucesso ou a falha de cada chamada de método ou operação específica. Se um erro ocorrer, ele será processado pelo tratador de exceções.

Outra razão que torna o tratamento de exceções importante é Java definir exceções padrões para erros que são comuns nos programas, como a divisão por zero ou um arquivo não encontrado. Para reagir a esses erros, o programa deve estar em alerta a esse tipo de exceção e tratá-las. Além disso, a biblioteca de APIs Java usa intensamente exceções. 

**No fim das contas, ser um programador de Java bem-sucedido significa ser plenamente capaz de navegar no subsistema de tratamento de exceções Java.** 

## <span style="background:#d4b106">Hierarquia de Exceções</span>
Em java, todas as exceções são representadas por classes e todas **as classes de exceções** são derivadas de uma classe chamada #Throwable. Logo, quando uma exceção ocorre em um programa, um objeto de algum tipo de classe de exceção é gerado. Há duas subclasses diretas de Throwable: #Exception e #Error. As exceções de tipo Erros estão relacionadas a erros que ocorrem na própria máquina virtual Java e não nos programas. Esses tipos de exceções fogem ao nosso controle e geralmente os programas não lidam com elas. Portanto, não serão descritas aqui.

Erros que resultam da atividade do programa são representados por subclasses de #Exception. Por exemplo, erros de divisão por zero, que excedem os limites do #array e relacionados a arquivos se enquadram nessa categoria. Em geral, os programas devem tratar exceções desses tipos. Uma subclasse importante de #Exception é #RuntimeException, que é usada para representar vários tipos comuns de erros de tempo de execução. 

## <span style="background:#d4b106">Fundamentos do tratamento de exceções</span>
O tratamento de exceções Java é gerenciado por cinco palavras-chave:
- #try 
- #catch 
- #throw 
- #throws 
- #finally 
Elas formam um subsistema interligado em que o uso de uma implica o uso de outra. 

As instruções do programa que quisermos monitorar em busca de exceções ficarão dentro de um bloco #try. Se uma exceção ocorrer dentro do bloco #try, ela será lançada. Seu código poderá capturar essa exceção usando #catch e tratá-la de alguma maneira racional. Exceções geradas pelo sistema são lançadas automaticamente pelo sistema de tempo de execução Java. Para lançar manualmente uma exceção, use a palavra-chave #throw. Em alguns casos, uma exceção que é lançada para fora de um método deve ser especificada como tal por uma cláusula #throws. Qualquer código que deva ser executado ao sair de um bloco #try deve ser inserido em um bloco #finally. 

`Pergunte ao especialista`
<span style="background:#d4b106">Pergunta</span>: Descreva novamente as condições que fazem uma exceção ser gerada?
<span style="background:#d4b106">Resposta</span>: Exceções são geradas de três maneiras diferentes.
1) A **Máquina Virtual Java** #JVM pode gerar uma exceção em resposta a algum erro interno sobre o qual não tenhamos controle. Normalmente, o programa não trata esses tipos de exceções. 
2) Exceções padrão, como as correspondentes à divisão por zero ou índices fora dos limites de um array, são geradas por erros no código do programa. Temos que tratar essas exceções.
3) Podemos gerar manualmente uma exceção usando a instrução #throw. 

![[Capítulo 9 - Tratamento de Exceções-1.png]]

## <span style="background:#d4b106">Usando try e catch</span>
As palavras-chave #try e #catch forma  a base do tratamento de exceções. Elas funcionam em conjunto, ou seja, não podemos ter um #catch sem ter um #try. Esta é a forma geral dos blocos try/catch de tratamento de exceções:
```java
try {
// bloco de código cujos erros estão sendo monitorados
}
catch (TipoExceç1 obEx{
// tratador de TipoExceç1
}
catch (TipoExceç2 obEx) {
// tratador de TipoExceç2
}
```
Aqui, *TipoExceç* é o tipo de #exceção que ocorreu. Quando uma exceção é lançada (quando é detectado um problema com o código, essa exceção será disponibilizada para ser tratada em outro lugar do programa). Quando detectado, ao invés do programa continuar a execução normal, ele interrompe o fluxo do programa e "joga" a exceção para o fluxo de exceções, sendo capturada pela instrução #catch correspondente, que então a processa. Como podemos perceber, podemos ter mais de uma instrução catch associada a uma instrução #try. O tipo de exceção especificado por uma instrução catch coincidir com o da exceção ocorrida, essa instrução catch será executada (e todas as outras serão ignoradas). Quando uma exceção é capturada, *obEx* recebe seu valor. 

Agora um ponto importante: se nenhuma exceção for lançada, o bloco #try terminará normalmente e todas as suas instruções #catch serão ignoradas. A execução será retomada na primeira instrução após o último #catch. Logo, **as instruções catch só são executadas quando ocorre uma exceção**. 

*NOTA*
`A partir do JDK7, há outra forma de instrução #try que dá suporte ao gerenciamento automático de recursos. Essa nova forma de #try se chama #try_with_resourcer. Ela é descrita no capítulo 10, no contexto do gerenciamento de fluxo de I/O (como os conectados a um arquivo), porque os fluxos são um dos recursos mais usados.`

## <span style="background:#d4b106">Exemplo de exceção simples</span>
Este é um exemplo simples que ilustra como monitorar uma exceção e capturá-la. Como sabemos, é um erro tentar indexar um array além de seus limites. Quando isso ocorre, a JVM lança uma exceção #ArrayIndexOutOfBoundsException. O programa a seguir gera intencionalmente esse exceção e então a captura
```Java
// Demonstra o tratamento de exceções.
class ExcDemo1 {
	public static void main(String args[]) {
	int nums[] = new int[4];
	
	try {
		System.out.println("Before exception is generated.");
		
		// Gera um exceção de índice fora dos limites.
		nums[7] = 10; // TENTA INDEXAR EXCEDENDO O LIMITE DE NUMS
		System.out.println("this won't be displayed");
	}
	catch (ArrayIndexOutOfBoundsException exc) { // CAPTURA ERROS NOS LIMITES DO ARRAY
		// captura a exceção
		System.out.println("Index out-of-bounds!");
	}
		System.out.println("After catch statement.");
	}
}
```
Em primeiro lugar, ao analisamos o código anterior, o código que queremos monitorar está dentro de um bloco #try. Em segundo lugar, quando ocorre uma exceção (nesse caso, pela tentativa de indexar **nums** além de seus limites), ela é lançada fora do bloco #try e capturada pela instrução #catch. Nesse ponto, o controle passa para #catch e o bloco #try é encerrado. Isto é, catch não é chamada, em vez disso, a execução do programa é transferida para ela. Logo, percebemos que é função do tratamento de exceções remediar o problema que causou a exceção, para que a execução do programa possa continuar normalmente. 

Lembramos, se nenhuma exceção for lançada por um bloco try, nenhuma instrução catch será executada e o controle do programa será retomado após a instrução catch. 

## Teste do Capítulo 9
1. Qual classe fica no topo da hierarquia de exceções?
Throwable. É a classe base de todas as condições de erro/exceção. Contém método como getMessage, printStackTrace e getCause.

2. Explique resumidamente como #try e #catch são usados?
Dentro do bloco try, nós criamos o código que desejamos monitorar em tempo de execução, caso alguma exceção ocorra, ele será lançado para o bloco subsequente, #catch podendo ser tratado de forma apropriada.

 3. O que acontece quando uma exceção não é capturada?
 A exceção será propagada pela pilha de chamadas de métodos. Isso significa que o método atual é encerrado de forma abrupta e a exceção será passada para o método chamador. Isso ocorrerá até que a exceção seja tratada em algum ponto da pilha. A JRE exibe o #stack-trace no console, mostrando informações sobre o tipo de exceção. 

