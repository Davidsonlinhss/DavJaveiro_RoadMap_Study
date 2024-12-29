Objetivos do capítulo:
- Descrever #exceções;
- Código Try e Captura de Exceções;
- Lançar e Capturar múltiplas exceções;
- Usar o block #finally;
- Apreciar as vantagens do uso de tratamento de exceções;
- Especificar as exceções que um método pode lançar;
- Rastrear exceções através da pilha de chamado;
- Criar nossas próprias classe de exceções;
- Usar asserção;

## 10.1 Aprendendo sobre #Exceções
Uma exceção é uma condição ou um erro inesperado. Os programas que nós escrevemos podem lançar diversos tipos de exceções potenciais. Por exemplo:
- Um comando pode ser utilizado para ler um arquivo do disco, mas o arquivo não ser localizado;
- Um programa pode tentar escrever um dado em um disco, mas o disco pode estar cheio ou não estar formatado;
- O nosso programa pode solicitar ao usuário que entre digite um dado, mas o usuário pode digitar um dado do tipo inválido ao solicitado;
- Um programa pode tentar dividir um valor por zero;

Esses erros são chamados de exceções, porque, presume, que não são ocorrências comuns; eles são "excepcionais".

**Exception handling** é o nome para técnicas orientada a objetos que administram ou resolve esses erros. Uma exceção não planejada que ocorre durante a execução do programa é chamada de **runtime exception**, em contrate com um erro de sintaxe que é detectado durante a compilação do programa. 

---
**NOTA**
Uma exceção não é necessariamente ruim. Por exemplo, podemos armazenar o lucro anual da nossa empresa em um #int, e, sem aviso, o lucro ultrapassar o valor máximo que um #int pode suportar. Isso é uma boa notícia, mas ainda assim é uma exceção.

**Valor máximo de um #int**: o valor vai de 2.147.483.647 (2^31) e o valor mínimo é de -2.147.483.648(-2^31). Ele é um tipo de dado inteiro com decimal de 32 bits.

Caso o valor ultrapasse o valor máximo ou mínimo, ocorre um #overflow, e o valor "retorna" ao limite negativo, resultando em comportamentos inesperados. Em caso de necessidade de valores maiores, usa-se o #long (64 bits), que suporta valores **-9.223.372.036.854.775.808** e **9.223.372.036.854.775.807**.

---

Java inclui duas classes básicas de erros: #Error e #Exception. Ambas essas classes descendem da classe #Throwable. Assim como todas as outras classes no Java, Error e Exception descendem originalmente da classe #Object, que é definida no pacote java.lang, <span style="background:#d4b106">importado automaticamente</span>. Logo, ambas são do tipo object. Como objeto, 
elas possuem métodos herdados (como toString(), hashCode() e equals()).

- #Error representa <span style="background:#d4b106">erros mais graves</span> dos quais nosso programa geralmente não consegue se recuperar. Por exemplo, pode haver memória insuficiente para executar um programa. Normalmente, não implementamos objetos da classe Error em nossos programas. Um programa não pode se recuperar de condições de erro dessa natureza por conta própria.

- #Exception abrange erros menos graves que representam condições incomuns que surgem enquanto um programa está em execução e das quais ele pode se recuperar. Por exemplo, um tipo de erro da classe #Exception ocorre quando um programa utiliza um índice de array inválido, e o programa pode se recuperar atribuindo um valor válido à variável do índice. 

O código abaixo mostra duas execuções típicas do programa *Division*. Na primeira execução, o usuário insere dois valores válidos e o programa é executado normalmente. Na segunda execução, o usuário insere 0 como valor para o denominador e uma mensagem de #Exception é exibida. (O Java não permite divisão de um inteiro por 0, mas permite divisão por 0 em números de ponto flutuante, cujo o resultado é exibido como #Infinity).

Na segunda execução, a maioria dos programadores diria que o programa sofreu um #crash, o que significa que ele terminou prematuramente devido a um erro. O termo #crash provavelmente surgiu a partir de erros de hardware...

```java
import java.util.Scanner;

public class Division {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int numerator, denominator, result;

        System.out.print("Enter numerator >> ");
        numerator = input.nextInt();

        System.out.print("Enter denominator >> ");
        denominator = input.nextInt();

        result = numerator / denominator;

        System.out.println(numerator + " / " + denominator + " = " + result);
    }
}
```

`Enter numerator >> 12`
`Enter denominator >> 4`
`12 / 4 = 3`

`Enter numerator >> 12`
`Enter denominator >> 0`
`Exception in thread "main" java.lagn.ArithmeticException: / by zero`
`at Division.main(Division.java:12`

Além do tipo de Exception, o método gerou o erro (Division.main) e o arquivo e número da linha onde ocorreu o erro (Division.java, linha 12).

---
**NOTA**
É muito importante ler as mensagens de erro com cuidado; elas podem conter muitas informações importantes.

---
![[Capítulo 10 - Tratamento de Exceções.png]]
A lista de mensagens de erro após cada tentativa de execução acima é chamada de **lista de histórico de rastreamento de pilha**, ou mais simplesmente, #stack_trace ou #stack_traceback. <span style="background:#d4b106">A lista mostra cada método que foi chamado enquanto o programa estava em execução.</span>  Técnicas de tratamento de exceções orientadas a objetos fornecem soluções mais elegantes e seguras para lidar com erros.

O tratamento de exceções não é novidade, programadores tiveram que lidar com condições de erro muito antes dos métodos orientados a objetos serem concebidos. 

Programas que podem lidar com exceções de maneira adequada são considerados mais <span style="background:#d4b106">tolerantes a falhas e robustos</span>. Aplicações tolerantes a falhas são projetadas de modo que continuam a operar, possivelmente em um nível reduzido, quando alguma parte do sistema falha. A robustez representa o grau de resiliência de um sistema ao estresse e sua capacidade de continuar funcionando.

---
## Duas verdades e uma mentira | Aprendendo sobre Exceções

1. O tratamento de exceções é o nome das técnicas orientadas a objetos usadas para gerenciar erros em tempo de execução;
2. A classe Error representa erros graves dos quais seu programa geralmente não pode se recuperar, mas a classe Exception abrange erros menos graves dos quais o programa pode se recuperar;
3. Quando exceções ocorrem, programas orientados a objetos devem tratá-las. (NÃO OBRIGATORIAMENTE).
A afirmação 3 é falsa, pois, não é pelo fato de uma exceção ter ocorrido que devemos necessariamente tratá-la. Já escrevemos muitos programas que não tratam as exceções que surgem. 

## Testando Códigos e Capturando Exceções
Em terminologia orientada a objetos, nós usamos o #try para indicar um bloco de código que <span style="background:#d4b106">está sendo executado com a possibilidade de gerar um erro ou exceção.</span> Quando uma exceção ocorre dentro do bloco Try, o fluxo normal do programa é interrompido e o controle é transferido para um bloco #catch. Um bloco Try consiste nos seguintes elementos:
- A palavra-chave #try seguida por um par de chaves;
- Instruções executáveis entre as chaves, incluindo algumas instruções que podem causar exceções;

Para tratar uma exceção lançada, podemos codificar um ou mais blocos #catch imediatamente após o bloco #try. Um bloco #cath é um segmento de código que pode lidar com uma exceção que pode ser lançada pelo bloco #try que o precede. 

A exceção pode ser uma que é lançada automaticamente, ou podemos escrever explicitamente uma instrução #throw. Uma instrução #Throw é aquela que envia um objeto Exception para fora de um bloco ou de um método, para que ela possa ser tratado em outro lugar. 

Uma exceção lançada pode ser capturada por um bloco #catch. Cada bloco #catch pode "capturar" um tipo de exceção, ou seja, um objeto do tipo Exception ou de uma de suas classes filhas. 

Para criar um bloco #catch, digite os seguintes elementos:
- A palavra-chave #catch seguida por um par de parênteses;
- Entre os parênteses, um tipo de Exceção e um identificador para uma instância;
- Um par de chaves que contêm instruções que realizam as ações que desejamos usar para tratar a condição de erro.

O código abaixo mostra o formato geral de um método que inclui um par try...catch.


Exemplo:
```java
returnType methodName(optional arguments) 
{
	// optional statements prior to code that is tried
	try
	{
	// declaração ou declarações que podem gerar uma exceção
	}
	catch(Exception someException)
	{
		// ações caso alguma exceção ocorra
	}
	// declarações opcionais que ocorrem após o try;
	// se o bloco de captura for executado ou não.
}
```
Se nenhuma exceção ocorrer dentro do bloco #try, o bloco #catch não é executado. De qualquer forma, quaisquer instruções que seguem o bloco #catch são executadas normalmente.

---
Podemos realizar mais em um bloco #catch do que exibir uma mensagem de erro. Podemos forçar a operação aritmética a dividir por 1 em vez de 0. 

## Usando um bloco #try para tornar os programas infalíveis
Um dos usos mais comuns para um bloco try é contornar erros de entrada de dados. Por exemplo, ao testar nossos programas, podemos inserir acidentalmente o tipo de dado errado em resposta a um prompt. Se o usuário inserir um caractere ou um número de ponto flutuante em resposta a uma chamada do método #nextInt(), o programa falha. Usar um bloco try pode permitir que trate exceções potenciais de conversão de dados causadas por usuários descuidados. Podemos colocar tentativas de conversão, como chamar um #nextInt ou #nextDouble, em um bloco #try e, em seguida, lidar com quaisquer erros gerados.

## Declarando e inicializando variáveis em blocos try...catch

Podemos incluir qualquer tipo de instrução Java dentro de um bloco try ou catch, incluindo declarações de variáveis. No entanto, devemos lembrar que uma variável declarada dentro de um bloco possuí escopo local a esse bloco. Logo, qualquer variável declarada dentro desse bloco <span style="background:#d4b106">deve servir apenas a um propósito temporário</span>.

Se quisermos utilizar uma variável tanto dentro de um bloco #try quanto fora, #catch, devemos declarar a variável antes do início do bloco #try. No entanto, se declararmos a variável antes de um bloco #try, mas esperar para atribuir seu valor inicial utilizável dentro do bloco, devemos ter cuidado para que a variável receba um valor útil; caso contrário, quando usarmos a variável após o término do par, o programa não será compilado.

---
**Duas verdades e uma mentira**
1. Um bloco try é um bloco de código que tentamos executar, reconhecendo que uma exceção pode ocorrer;
2. Geralmente codificamos pelo menos um bloco #catch imediatamente após um bloco #try para lidar com uma possível exceção que pode ser lançada pelo bloco #try;
3. Uma instrução #throw é aquela que envia um objeto #Exception para um bloco try para que ele possa ser tratado. - falso, uma instrução throw envia um objeto Exception para um bloco #catch




REALIZAR O EXERCÍCIO ANTES;
## 10.3 Lançamento e captura de várias exceções