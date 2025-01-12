## Classes de fluxos de caracteres
O fluxo de caracteres são definidos com o uso de duas hierarquias de classes encabeçadas pelas seguintes duas classes:
1. #Reader - entrada de dados
2. #Writer - saída de daddos

Algumas classes de I/O de fluxo de caracteres:
#BufferedReader - Fluxo de caractere de entrada armazenado em buffer;
#BufferedWriter - Fluxo de caractere de saída armazenado em buffer;
#CharArrayReader - Fluxo de entrada que lê de um array de caracteres;
#CharArrayWriter - Fluxo de saída que grava em um array de caracteres;
#FileReader - Fluxo de entrada que lê um arquivo;
#FileWriter - Fluxo de saída que grava em um arquivo;
#InputStreamReader - Fluxo de entrada que converte bytes em caracteres;
#LineNumberReader - Fluxo de entrada que conta linhas;
#OutputStreamWriter - Fluxo de saída que converte caracteres em bytes;
#PipedWriter - Pipe de entrada (transferência de dados entre threads de forma segura e sincronizada);
#PipedReader - outra thread que faz o processo de leitura dos dados escritos pelo PipedWriter.
#PrintWriter - Fluxo de saída que contém print() e println();
#PushbackReader - Fluxo de entrada que permite que caracteres sejam retornados para o fluxo;
#Reader - Classe abstrata que descreve a entrada de caracteres em fluxo
#StringReader - Fluxo de entrada que lê de um string
#StringWriter - Fluxo de saída que grava em um string
#Writer - Classe abstrata que descreve a saída de caracteres em fluxo

## Gravando a saída do console

Como o System.out é um fluxo de bytes, a saída no console baseada em bytes ainda é amplamente utilizada. 

A saída no console é obtida mais facilmente com os métodos #print e #println, que já usamos. Esses métodos são definidos pela classe #PrintStream que é o tipo de objeto referenciado por #SystemOut. Mesmo com #systemOut sendo um fluxo de bytes, é aceitável usar esse fluxo para saídas simples no console. 

Já que #PrintStream é um fluxo de saída derivado de #OutputStream, ele também implementa o método de baixo nível #write. A forma mais simples de write é definidaa por #PrintStream é mostrada abaixo:
`void write(int valbyte)`
Esse método grava o byte especificado por valbyte no arquivo. Embora balbyte seja declarada como um inteiro, só os 8 bits de ordem inferior são gravados. 

## Lendo e gravando arquivos usando fluxos de bytes
Java fornece várias classes e métodos que permitem a leitura e gravação de arquivos. É claro que os tipos de arquivos mais comuns são os em disco. Os arquivos em Java são orientados a bytes e a linguagem fornece métodos para a leitura e gravação de bytes em um arquivo.  Java também permite encapsulamento de um fluxo de arquivo orientado a bytes dentro de um objeto baseado em caracteres.

Para criar um fluxo de bytes vinculado a um arquivo, usamos o #FileInputStream ou #FileOutputStream. Para abrir um arquivo, simplesmente criemos um objeto de uma dessas classes, especificando **o nome do arquivo como argumento do construtor**. Uma vez que o arquivo for aberto, poderemos ler e gravar nele.                                                                                                   
## Gerando entradas em um arquivo

## Código de comparação
```java
public class BankTransactionAnalyzerSimple {
private static final String RESOURCES =
"src/`main`/resources/";
public static void main(final String... args) throws
IOException {
final Path path = Paths.get(RESOURCES + args[0]);
final List<String> lines = Files.readAllLines(path);
double total = 0d;
for(final String line: lines) {
final String[] columns = line.split(",");
final double amount =
Double.parseDouble(columns[1]);
total += amount;
}
System.out.println("The total for all transactions is
" + total);
}
}
```
Um arquivo é aberto para gerar entradas com a criação de um objeto #FileInputStream. O construtor abaixo é muito usado:
```java
FileInputStream(String nomeArquivo) throws FileNotFoundException
```
Aqui, *nomeArquivo* especifica o nome do arquivo que desejamos abrir. Se ele não existir, uma #FileNotFoundException será lançada. FileNotFoundException é subclasse de IOException.

Geralmente, utilizamos #IOException porque ela é mais generalista e cobre uma ampla game de possíveis problemas relacionados a operações de entrada/saída (I/O). Como muitas operações de I/O podem gerar diferentes exceções, usar IOException é prático porque abrange todas as exceções derivadas dela.

Para ler em um arquivo, podemos usar #read. A versão que usaremos é mostrada aqui:

```java
int read() throws IOException
```
Sempre que é chamado, #read() lê um único byte no arquivo e o retorna como um valor inteiro. Ela retorna -1 quando o fim do fluxo é alcançado e lança uma IOException quando ocorre um erro. Portanto, essa versão de #read é igual à usada na leitura a partir do console.
Quando tiver terminado de usar um arquivo, devemos fechá-lo chamando o método close:
```java
void close() throws IOException;
```


O fechamento de um arquivo libera os recursos do sistema alocados para ele, permitindo que sejam usados por outro arquivo. **Não fechar um arquivo pode resultar em "vazamento de memória"**, porque recursos não usados permanecem alocados. 

https://mkyong.com/java/java-how-to-create-and-write-to-a-file/#google_vignette

#chunck 

















**Testes do Capítulo 10**

1. Por que Java define fluxos tanto de bytes quanto de caracteres?
Os fluxos de caracteres visaram o tratamento mais eficiente com relação a arquivos .txt, .json, .xml..., no nível mais baixo, todo o sistema de I/O em Java é baseado em fluxos de bytes, logo, o fluxo baseado em caracteres, é apenas uma maneira mais eficiente de trabalhar. Logo, os fluxos de caracteres, oferecem uma camada de abstração para facilitar o trabalho com arquivos baseados em texto, eliminando a necessidade de conversões manuais.



2. Já que a entrada e a saída do console são baseadas em texto, por que Java ainda usa fluxos de bytes para esse fim?

