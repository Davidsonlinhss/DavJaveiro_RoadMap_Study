## 3.3 Introdução
Compreender algumas expressões é uma das maneiras mais assertivas para se poder interagir em qualquer contexto de comunicação, e na programação não seria diferente, afinal, é através de escrita de algumas expressões que nos comunicamos e definimos as execuções que serão realizadas pelo nosso software. 

### 3.3.1 Formatação
Imagina que recebemos a tarefa de realizar a confecção de cupom de venda não fiscal que inicialmente será exibida no console para depois salvar em um arquivo. 

#### 3.3.1.1 Definição
A classe String possui o método estático chamado #format conforme assinatura abaixo:
```Java
format(String format, Object ... args)
```
1. O parâmetro `String format` representará o formato esperado
2. O parâmetro `Object ... args` representará o(s) valores(s) que serão formatados. 


**Tipos e especificadores**
Cada tipo de dado é representado para um caractere especificador prefixado pelo símbolo % que representa o início de cada especificador. Abaixo, a listagem com os mais utilizados:
- **`%s`:** For strings.
- **`%d`:** For integers.
- **`%f`:** For floating-point numbers.
- **`%c`:** For characters.
- **`%b`:** For booleans.
- **`%h`:** For hash codes.
- **`%n`:** For a newline character.
- **`%t`:** For time-related formats (e.g., `%tH` for hours in 24-hour format).

**Estrutura de formatação**
O parâmetro que representa a formatação possui a flexibilidade de receber em sua sintaxe os argumentos abaixo:



