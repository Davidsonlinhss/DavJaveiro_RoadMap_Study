## O Desafio
Mark deu a tarefa de desenvolvermos um software que automatize o processamento de nossos extratos bancários para que ele possa ter uma visão melhor de suas finanças. 

## O Objetivo
Neste capítulo aprenderemos os fundamentos do bom desenvolvimento de software antes de aprender as técnicas mais avançadas nos próximos capítulos.

Começaremos implementando a declaração do problema **em uma classe única**. Então, exploraremos por que essa abordagem apresenta diversos desafios em termos de superação de mudanças nas exigências e na manutenção do projeto. 

Primeiro aprenderemos sobre o #SRP ou *Single Responsibility Principle*, que ajuda a desenvolver um software mais fácil de manter e entender, e reduz a margem para o aparecimento de novos bugs. Ao longo do caminho, observaremos novos conceitos como #coesão e #acoplamento, que são características úteis para nos guiar acerca da qualidade do código e do software que estamos desenvolvendo. 

## Requisitos do Analista de Extratos Bancários
O analista de extrato bancários só precisa ler um arquivo de texto que contém uma lista de transações bancárias, que será baixada do portal de seu banco online. Este texto é estruturado utilizando o formato CSV.:
30-01-2017,-100,Deliveroo
30-01-2017,-50,Tesco
01-02-2017,6000,Salary
02-02-2017,2000,Royalties
02-02-2017,-4000,Rent
03-02-2017,3000,Tesco
05-02-2017,-30,Cinema

Ele gostaria de ter a resposta para as seguintes consultas:
- Qual o total de lucros e perdas em uma lista de extratos bancários? É positivo ou negativo?
- Quantas transações bancárias existem em um mês específico?
- Quais são suas 10 principais despesas?
- Em qual categoria ele mais gasta dinheiro?

## Princípio KISS
Podemos usar o princípio KISS, ou "Keep It Short and Simple" [Manter curto e Simples], e fazer o código da aplicação em uma única classe. 

## Variáveis final
<span style="background:#d4b106">Marcar uma variável local ou campo como final significa que ele não pode ser reatribuído</span>. A decisão de usar ou não final em seu projeto é tomada por sua equipe e projeto, já que seu uso tem vantagens e desvantagens.  Acreditamos que marcar o máximo de variáveis possível, como #final demarca claramente quais estados podem mudar durante a vida de um objeto e quais estados não são reatribuídos.

---
**OBSERVAÇÃO**
Em meus estudos sobre Spring Context, quando eu defino uma entidade e não quero que o valor dela seja alterado, declaramos as variáveis de instância com a palavra chave #final e omitimos métodos que permitam a modificação direta, como #setters. Isso tornará o campo imutável após a sua inicialização. 
```java
package org.example;

public class Parrot {
    private final String name;

    public Parrot(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

```
### Exemplo de uso no Spring
```java
@Configuration
public class AppConfig {
    @Bean
    public Parrot parrot() {
        return new Parrot("koko");
    }
}

```
Dessa forma, o nome do `Parrot` será imutável, garantindo que ele permaneça como "koko" durante toda a execução da aplicação.

---
O uso da palavra chave final não garante a imutabilidade do objeto em questão. Podemos ter um campo #final que se refere a um objeto com estado mutável. Algumas equipes assumem o compromisso de ter campos #final nos parâmetros de método a fim de garantir que não sejam reatribuídos claramente e não sejam variáveis locais. 

Uma área onde faz pouco sentido usar a palavra-chave final, apesar da linguagem Java permitir, é em <span style="background:#d4b106">parâmetros de métodos abstratos; por exemplo, nas interfaces</span>. Provavelmente o uso de final diminuiu desde a introdução da palavra-chave var no Java 10. 

### Manteabilidade do Código e Antipadrões
Quando escrevemos código, devemos procurar oferecer uma boa *manteabilidade do código*. O que isso significa? A melhor descrição é com uma lista de desejos das propriedades do código escrito:
1. Deve ser simples localizar o código responsável por determinado recurso;
2. Deve ser simples entender o que o código faz;
3. Deve ser simples incluir ou remover novos recursos;
4. Deve oferecer um bom #encapsulamento, ou seja, o usuário de nosso código não deve ver os detalhes da implementação para ser mais fácil entender e fazer alterações;

Se continuarmos copiando e colando código no nosso sistema, acabaremos com problemas que são chamados de #antipadrões porque são soluções ineficientes comuns:
1. Código difícil de entender porque temos uma classe "Deus" enorme;
2. Código frágil e facilmente corrompível por mudanças devido à duplicação de código. 

## Classe Deus
Ao colocar todo código em um arquivo, nós teremos uma classe gigantesca que dificulta entender o propósito da classe porque ela é responsável por tudo. Se precisarmos atualziar a lógica do código existente, como localizaremos e alteraremos facilmente o código? Tal problema é chamado de antipadrão "Classe Deus". <span style="background:#d4b106">Basicamente temos uma única classe que faz tudo</span>. É preciso evitar isso. Na próxima seção, aprenderemos sobre o *Single Responsibility Principle*, que é uma diretriz de desenvolvimento de software para ajudar a escrever códigos mais fáceis de entender e manter. #SRP 

### Duplicação de Código
Para cada consulta, estamos duplicando a lógica para ler e analisar a entrada. E se a entrada necessária não for mais um arquivo CSV, mas um JSON? E se for necessário suportar múltiplos formatos? Incluir tal requisito será uma mudança difícil porque nosso código assumiu uma solução específica e duplicou aquele comportamento em diversos lugares. Portanto, todos os lugares terão que mudar e provavelmente teremos novos bugs. 

## Princípio de Responsabilidade Única
O #SRP é uma diretriz geral de desenvolvimento de software a ser seguida para escrever códigos que sejam mais fáceis de gerenciar e manter. 

Devemos pensar no SRP de duas formas complementares:
1. Uma classe tem responsabilidade sobre uma única funcionalidade;
2. Só existe uma razão para uma classe mudar;

O SRP costuma ser aplicado a classes e métodos, e se relaciona a um comportamento, conceito ou categoria específico. Ele conduz a códigos mais robustos porque existe um motivo específico pelo qual deveria mudar, em vez de múltiplas considerações. O motivo pelo qual múltiplas considerações são problemáticas é, como vimos antes, complicar a mantenabilidade do código

Como aplicar o SRP no código mostrado no Exemplo 2-2? Está claro que a classe principal tem múltiplas responsabilidades que podem ser discriminadas individualmente:
1. Ler a entrada;
2. Analisar a entrada em determinado formato;
3. Processar o resultado;
4. Relatar um resumo do resultado.

Focaremos a parte de análise neste capítulo, e no próximo, aprenderemos a estender o Análista de Extratos Bancários para que seja totalmente modularizado. 

O primeiro passo natural é extrair a lógica de análise CSV para uma classe separada a fim de reutilizá-la para diferentes consultas de processamento. Vamos chamá-la de BankStatementCSVParser para ficar bem claro o que ela faz.
