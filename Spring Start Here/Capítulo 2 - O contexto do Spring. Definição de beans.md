O que este capítulo aborda:
- Compreensão da necessidade do contexto do Spring 
- Adição de novas instâncias de objeto ao contexto do Spring
- 
Neste capítulo, você começará a aprender a trabalhar com um elemento crucial da estrutura do Spring: o contexto (também conhecido como <span style="background:#d3f8b6">contexto</span> do aplicativo em um app Spring). Imagine o contexto como um local na memória de seu aplicativo no qual adicionamos todas as instâncias de objeto que queremos que a estrutura gerencie. <span style="background:#fff88f">Por padrão</span>, <span style="background:#d3f8b6">o Spring não conhece nenhum dos objetos que você define em seu aplicativo</span>. Para permitir que o Spring veja seus objetos, <span style="background:#d3f8b6">você precisa adicioná-los ao contexto</span>. Mais adiante neste livro, discutiremos o uso de diferentes recursos fornecidos pelo Spring nos aplicativos. Você aprenderá que a conexão desses recursos é feita por meio do contexto, adicionando instâncias de objetos e estabelecendo relacionamentos entre eles. O Spring usa as instâncias no contexto para conectar seu aplicativo a várias funcionalidades que ele fornece. Você aprenderá os conceitos básicos dos recursos mais importantes (por exemplo, transações, testes etc.) ao longo do livro.
Aprender o que é o contexto do Spring e como ele funciona é a primeira etapa para aprender a usar o Spring, pois sem saber como gerenciar o contexto do Spring, quase nada mais do que aprenderemos a fazer com ele será possível. O contexto é um mecanismo complexo que permite ao Spring controlar as instâncias que nós definimos. Dessa forma, ele permite que usemos recursos que a estrutura oferece.
Neste capítulo, começamos aprendendo a adicionar instâncias de objetos ao contexto do Spring. No capítulo 3, aprenderemos a fazer referência às instâncias que adicionamos e a estabelecer relacionamento entre elas.
Chamaremos essas instâncias de objeto de #beans. É claro que, para as sintaxes que aprenderemos, escreveremos trechos de código, e podemos encontrar todos esses trechos nos projetos fornecidos com o livro.
Como quero que sua introdução ao Spring seja progressiva e que tudo seja feito passo a passo, neste capítulo vamos nos concentrar nas sintaxes que você precisa conhecer para trabalhar com o contexto do Spring. Você descobrirá mais tarde que nem todos os objetos de um aplicativo precisam ser gerenciados pelo Spring, portanto, não é necessário adicionar todas as instâncias de objeto do seu aplicativo ao contexto do Spring. Por enquanto, convido você a se concentrar em aprender as abordagens para adicionar uma instância para o Spring gerenciar.

## 2.1 Criando um projeto Maven
Nesta seção, discutiremos a criação de um projeto Maven. O Maven não é um assunto diretamente relacionado ao Spring, mas é uma ferramenta que você usa para gerenciar facilmente o processo de <span style="background:#d3f8b6">compilação</span> de um aplicativo, independentemente da estrutura usada. Você precisa conhecer os conceitos básicos do projeto Maven para seguir os exemplos de codificação. O Maven também é uma das ferramentas de compilação mais usadas para projetos Spring em cenários reais (com o Gradle, outra ferramenta de compilação, em segundo lugar, mas não a discutiremos neste livro). Como o #Maven é uma ferramenta muito conhecida, você já deve saber como criar um projeto e adicionar dependências a ele usando sua configuração. Nesse caso, pode pular esta seção e ir diretamente para a seção 2.2.
Uma ferramenta de compilação é um software que usamos para criar aplicativos com mais facilidade. Devemos configurar uma ferramenta de compilação para realizar as tarefas que fazem parte da criação do aplicativo, em vez de fazê-las manualmente. <span style="background:#fff88f">Alguns exemplos de tarefas</span> que geralmente fazer parte da criação do aplicativo são os seguintes:
- Download das dependências necessárias para o nosso app;
- Executar testes;
- Validar que a sintaxe segue as regras que nós definimos;
- Checar por vulnerabilidades de segurança;
- Compilar o app;
- Empacotar o app em um arquivo executável.

Para que nossos exemplos possam gerenciar facilmente as dependências, precisamos usar uma ferramenta de compilação para os projetos que desenvolvemos. Esta seção ensina apenas o que você precisa saber para desenvolver os exemplos deste livro;
Depois de selecionar o tipo de projeto, na janela seguinte (figura 2.2), você precisa dar um nome a ele. Além do nome do projeto e da escolha do local em que ele será armazenado, para um projeto Maven você também pode especificar o seguinte: 
- Um ID de grupo, que usamos para agrupar vários projetos relacionados 
- Um ID de artefato, que é o nome do aplicativo atual 
- Uma versão, que é um identificador do estado atual da implementação

Em um aplicativo do mundo real, esses três atributos são detalhes essenciais, e é importante fornecê-los. Mas, no nosso caso, como trabalhamos apenas com exemplos teóricos, você pode omiti-los e deixar que seu IDE preencha alguns valores padrão para essas características. 
Depois de criar o projeto, você verá que sua estrutura se parece com a apresentada na Figura 2.3. Novamente, a estrutura do projeto Maven não depende do IDE que você escolher para desenvolver seus projetos. Quando você olha primeiro para o seu projeto, observa <span style="background:#d3f8b6">duas coisas principais</span>:
- A pasta "src" (também conhecida como source folder), onde colocaremos tudo que pertence ao aplicativo;
- O arquivo pom.xml, onde está escrito a configuração do projeto Maven, como adicionar novas dependências;

O Maven organiza a pasta "src" nas seguintes pastas:
- A pasta "<span style="background:#d3f8b6">main</span>", local de armazenamento do código fonte de nossa aplicação. Essa pasta contém o código Java e as configurações separadamente em duas subpastas diferentes nomeadas "java" e "recursos".
- A pasta "test", onde está armazenado o código fonte dos nossos testes unitários (será discutido mais sobre esses testes e como defini-los no capítulo 15).


![[Capítulo 2 - O contexto do Spring. Definição de beans.png]]
Figura 2.3 How a Maven project is organized. Inside the src folder, we add everthing that belongs to the app: the applications source code goes into the main folder, and the source code for the unit testes goes into the test folder. In the pom.xml file we write configurations for the Maven project (in our exemples well primarily use it do define the dependencies).

Nos projetos que criamos neste livro, usamos muitas dependências externas: bibliotecas ou estruturas que usamos para implementar a funcionalidade dos exemplos. Para adicionar essas dependências aos seus projetos Maven, precisamos alterar o conteúdo do arquivo pom.xml. 

Com o arquivo pom.xml, o projeto não utilizará qualquer dependência externa. Se olharmos na pasta do projeto de dependências externas, nós veremos apenas o JDK.
A listagem a seguir mostra como adicionar dependências externas ao nosso projeto. Nós devemos digitar as nossas dependências entre as tagas < dependencies > < /dependencies >. Cada dependência é representada pelo grupo de tags < dependency >, onde escrevemos os atributos das dependências. Maven pesquisará pela dependência através dos valores que nós fornecermos para tais atributos e irá baixar as dependências para um repositório. 
Depois de adicionar a dependência no arquivo pom.xml, conforme apresentado na listagem anterior, o IDE faz o download delas, e agora você encontrará essas dependências na pasta "External Libraries" (bibliotecas externas) (figura 2.6).

## 2.2 Adicionando novos beans ao Spring Context
Nesta seção, aprenderemos como adicionar novas instancias (ou beans) ao Spring Context. Você descobrirá que há várias maneiras de adicionar beans no contexto do Spring, de modo que o Spring possa gerenciá-los e conectar os recursos que ele fornece ao seu aplicativo. Dependendo da ação, você escolherá uma maneira específica de adicionar o bean; discutiremos quando selecionar um ou outro. Você pode adicionar beans no contexto das seguintes maneiras (que descreveremos mais adiante neste capítulo):
- Usando a anotação @Bean;
- Usando anotações estereotipadas; 
- De forma programática.
Vamos primeiro criar um projeto sem referência a nenhuma estrutura, nem mesmo ao Spring. Em seguida, adicionaremos as dependências necessárias para usar o contexto do Spring e o criaremos (Figura 2.7). Esse exemplo servirá como pré-requisito para adicionar beans aos exemplos de contexto do Spring que trabalharemos nas seções 2.2.1 a 2.2.3.

Criamos um projeto Maven e definiremos uma classe. Por ser engraçado de imaginar, vou considerar uma classe chamada Parrot com apenas um atributo String representando o nome do papagaio (listagem 2.3).

The Parrot class
```java
public class Parrot {
	private String name;
//Omitted getters and setters
}
```

Agora você pode definir uma classe que contenha o método principal e criar uma instância da classe Parrot, conforme apresentado na listagem a seguir. Eu costumo chamar essa classe de Main.

Agora é hora de adicionar dependências necessárias ao nosso projeto. Pelo fato de estarmos utilizando o Maven, iremos adicionar as dependências no arquivo pom.xml, como apresentado na lista a seguir...

Um aspecto fundamental a ser observado é que o Spring foi projetado para ser modular. Por modular, quero dizer que você <span style="background:#d3f8b6">não precisa adicionar todo o Spring ao seu aplicativo</span> quando usar algo fora do ecossistema do Spring. Você só precisa adicionar as partes que usa. Por esse motivo, na listagem 2.5, você vê que adicionei apenas a dependência spring-context, que instrui o Maven a extrair as dependências necessárias para que possamos usar o contexto do Spring. Ao longo do livro, adicionaremos várias dependências aos nossos projetos de acordo com o que implementamos, mas sempre adicionaremos apenas o que precisamos.

---
**OBSERVAÇÃO**
Você deve estar se perguntando como eu sabia qual dependência do Maven eu deveria adicionar. A verdade é que já as usei tantas vezes que as sei de cor. Entretanto, você não precisa memorizá-las. Sempre que trabalhar com um novo projeto Spring, você poderá procurar as dependências que precisa adicionar diretamente na referência do Spring (https://docs.spring.io/spring-framework/docs/ current/spring-framework-reference/core.html). Em geral, as dependências do Spring fazem parte do ID do grupo org.springframework.

---

Com a dependência adicionada em nosso projeto, nós podemos criar uma instância do Spring context. Observamos como alterar o método main para criar uma instância do Spring context.

```java
package org.example;  
  
import org.springframework.context.annotation.AnnotationConfigApplicationContext;  
  
import javax.naming.Context;  
  
public class Main {  
    public static void main(String[] args) {  
        var context = new AnnotationConfigApplicationContext(); // Creates an instance of the Spring context
          
        Parrot parrot = new Parrot();  
    }  
}
```

---
**OBSERVAÇÃO**
Nós utilizamos a classe AnnotationConfigApplicationContext para criar a instância do Spring context. Como na maioria dos casos você usará a classe AnnotationConfigApplicationContext (a implementação que usa a abordagem mais usada atualmente: #annotations), vamos nos concentrar nela neste livro. Além disso, eu lhe digo apenas o que você precisa saber para a discussão atual. Se estiver começando a usar o Spring, minha recomendação é evitar entrar em detalhes sobre as implementações de contexto e as cadeias de herança dessas classes. É provável que, se fizer isso, você se perca com detalhes sem importância em vez de se concentrar nas coisas essenciais.

---

Conforme apresentado na Figura 2.8, você criou uma <span style="background:#d3f8b6">instância do Parrot</span>, <span style="background:#d3f8b6">adicionou as dependências do contexto do Spring</span> ao seu projeto e <span style="background:#d3f8b6">criou uma instância do contexto do Spring</span>. Seu objetivo é adicionar o objeto Parrot ao contexto, que é a próxima etapa.

![[Capítulo 2 - O contexto do Spring. Definição de beans-1.png]]
Figura 2.8 - Criamos a instância do Spring Context e a instância do Parrot. Agora, iremos adicionar a instância de Parrot dentro do Spring context para tornar o Spring ciente dessa instância.

### 2.2.1 Usando a annotation @Bean para adicionar beans dentro do Spring context
Nesta seção, discutiremos a adição de uma instância de objeto ao contexto do Spring usando a anotação @Bean. Isso peermite que adicionemos as instâncias das classes definidas em nosso projeto (como o Parrot, no nosso caso), bem como as classes que não criamos, ainda. Acredito que essa abordagem seja a mais fácil de entender quando se está começando. Lembre-se de que o motivo pelo qual aprendemos a adicionar beans ao contexto do Spring é que o Spring pode gerenciar apenas os objetos que fazem parte dele. 

As etapas que você precisa seguir para adicionar um bean ao contexto do Spring usando a anotação @Bean são as seguintes (Figura 2.9):
1. Defina uma classe de configuração (anotada com @Configuration) para seu projeto, que, como discutiremos mais adiante, será usada para configurar o contexto do Spring.
2. Adicione um método à classe de configuração que retorne a instância do objeto que você deseja adicionar ao contexto e anote o método com a anotação @Bean.
3. Faça com que o Spring use a classe de configuração definida na etapa 1. Como você aprenderá mais tarde, usamos classes de configuração para escrever diferentes configurações para a estrutura.

---
**NOTA** 
Uma classe de configuração é uma classe especial nos aplicativos Spring que usamos para instruir o Spring a realizar ações específicas. Por exemplo, podemos dizer ao Spring para criar beans ou para ativar determinadas funcionalidades. Você aprenderá diferentes coisas que podem ser definidas nas classes de configuração durante o restante do livro.

---
**ETAPA 1: DEFINIR UMA CLASSE DE CONFIGURAÇÃO EM SEU PROJETO**
A primeira etapa é criar uma classe de configuração no projeto. Uma classe de configuração do Spring é caracterizada pelo fato de ser anotada com a anotação @Configuration. Usamos as classes de configuração para definir várias configurações relacionadas ao Spring para o projeto. Ao longo do livro, você aprenderá diferentes coisas que pode configurar usando as classes de configuração. Por enquanto, vamos nos concentrar apenas em adicionar novas instâncias ao contexto do Spring. A próxima listagem mostra como definir a classe de configuração. Chamei essa classe de configuração de ProjectConfig

**ETAPA 2: CRIAR UM MÉTODO QUE RETORNE O BEAN E ANOTAR O MÉTODO COM @BEAN**
Uma das coisas que você pode fazer com uma classe de configuração é adicionar beans ao contexto do Spring. Para isso, precisamos definir um método que retorne a instância do objeto que desejamos adicionar ao contexto e anotar esse método com a anotação @Bean, que permite que o Spring saiba que precisa chamar esse método ao inicializar seu contexto e adicionar o valor retornado ao contexto. A próxima listagem mostra as alterações na classe de configuração para implementar a etapa atual.

**ETAPA 3: FAZER COM QUE O SPRING INICIALIZE SEU CONTEXTO USANDO A CLASSE DE CONFIGURAÇÃO RECÉM-CRIADA**
Implementamos uma classe de configuração na qual informamos ao Spring a instância do objeto que precisa se tornar um bean. Agora precisamos garantir que o Spring use essa classe de configuração ao inicializar seu contexto. A próxima listagem mostra como alterar a instanciação do contexto do Spring na classe principal para usar a classe de configuração que implementamos nas duas primeiras etapas.

Para verificar se a instância do Parrot realmente faz parte do contexto agora, você pode fazer referência à instância e imprimir seu nome no console, conforme apresentado na listagem a seguir.

---
**OBSERVAÇÃO** 
Em cenários do mundo real, usamos testes unitários e de integração para validar se nossas implementações funcionam conforme desejado. Os projetos deste livro implementam testes unitários para validar o comportamento discutido. Como este é um livro de "introdução", talvez você ainda não conheça os testes de unidade. Para não criar confusão e permitir que você se concentre no assunto discutido, não falaremos sobre testes unitários até o capítulo 15. Entretanto, se você já sabe como escrever testes unitários e a leitura deles o ajuda a entender melhor o assunto, poderá encontrar todos os testes unitários implementados na pasta de testes de cada um dos nossos projetos Maven. Se você ainda não sabe como funcionam os testes unitários, recomendo que se concentre apenas no assunto discutido.

---
Como no exemplo anterior, você pode adicionar qualquer tipo de objeto ao contexto do Spring (figura 2.10). Vamos adicionar também uma #String e um #Integer e ver se está funcionando.

NOTA Lembre-se da finalidade do contexto do Spring: adicionamos as instâncias que esperamos que o Spring precise gerenciar. (Dessa forma, conectamos as funcionalidades oferecidas pela estrutura.) Em um aplicativo real, não adicionaremos todos os objetos ao contexto do Spring. A partir do capítulo 4, quando nossos exemplos ficarão mais próximos do código em um aplicativo pronto para produção, também nos concentraremos mais em quais objetos o Spring precisa gerenciar. Por enquanto, concentre-se nas abordagens que você pode usar para adicionar beans ao contexto do Spring.

Agora você pode se referir a esses dois novos feijões da mesma forma que fizemos com o papagaio. A próxima listagem mostra como alterar o método principal para imprimir os valores dos novos beans.

### 2.2.2 Uso de anotações de estereótipo para adicionar beans ao contexto do Spring
Nesta seção, você aprenderá uma <span style="background:#fff88f">abordagem diferente para adicionar beans</span> ao contexto do Spring (mais adiante neste capítulo, também compararemos as abordagens e discutiremos quando escolher uma ou outra). Lembre-se de que adicionar beans ao contexto do Spring é essencial, pois é assim que você <span style="background:#fff88f">torna o Spring ciente das instâncias de objeto do seu aplicativ</span>o, que precisam ser gerenciadas pela estrutura. O Spring oferece mais maneiras de adicionar beans ao seu contexto. Em diferentes cenários, você descobrirá que usar uma dessas abordagens é mais confortável do que outra. Por exemplo, com anotações de estereótipo, você observará que escreve menos código para instruir o Spring a adicionar um bean ao seu contexto.
Mais tarde, você aprenderá que <span style="background:#fff88f">o Spring oferece várias anotações de estereótipo</span>. Mas, nesta seção, quero que você se concentre em como usar uma anotação de estereótipo em geral. Pegaremos a mais básica delas, <span style="background:#fff88f">@Component</span>, e a usaremos para demonstrar nossos exemplos.

Com as anotações de estereótipo, você adiciona a anotação acima da classe para a qual você precisa ter uma instância no contexto do Spring. Ao fazer isso, <span style="background:#fff88f">dizemos que você marcou a classe como um componente</span>. Quando o aplicativo cria o contexto do Spring, o Spring cria uma instância da classe que você marcou como um componente e adiciona essa instância ao seu contexto. Ainda teremos uma classe de configuração quando usarmos essa abordagem para dizer ao Spring onde procurar as classes anotadas com anotações de estereótipo. Além disso, você pode usar ambas as abordagens (usando @Bean e anotações de estereótipo juntas; trabalharemos com esses tipos de exemplos complexos em capítulos posteriores).

As etapas que precisamos seguir no processo são as seguintes (figura 2.12):
1. Usando a anotação @Component, marque as classes para as quais você deseja que o Spring adicione uma instância ao seu contexto (no nosso caso, o Parrot);
2. Usando a anotação @ComponentScan sobre a classe de configuração, instrua o Spring sobre onde encontrar as classes que você marcou.

Vejamos nosso exemplo com a classe Parrot. Podemos adicionar uma instância da classe no contexto do Spring anotando a classe Parrot <span style="background:#fff88f">com uma das anotações de estereótipo</span>, como @Component.
```Java
@Component // Ao usar a anotação @Component sobre a classe, instruímos o Spring a criar uma instância dessa classe e adicioná-la ao seu contexto.
public class Parrot {
	private String name;
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
```

Mas espere! Esse código ainda não funcionará. Por padrão, o Spring não procura classes anotadas com anotações de estereótipo, portanto, se deixarmos o código como está, o Spring não adicionará um bean do tipo Parrot em seu contexto. Para dizer ao Spring que ele precisa procurar classes anotadas com anotações de estereótipo, usamos a anotação @ComponentScan sobre a classe de configuração. Além disso, com a anotação <span style="background:#fff88f">@ComponentScan</span>, informamos ao Spring onde procurar essas classes. Enumeramos os pacotes em que definimos as classes com anotações de estereótipo. A próxima listagem mostra como usar a anotação @ComponentScan sobre a classe de configuração do projeto. No meu caso, o nome do pacote é "main".

``` java
@Configuration
@ComponentScan(basePackages = "main") // Usando o atributo basePackages da anotação, informamos ao Spring onde procurar classes anotadas com anotações de estereótipo.
public class ProjectConfig {

}
```
Agora você disse ao Spring o seguinte: 
1. Quais classes devem adicionar uma instância ao seu contexto (Parrot); 
2. Onde encontrar essas classes (usando @ComponentScan);
---
**OBSERVAÇÃO** 
Não precisamos mais de métodos para definir os beans. <span style="background:#fff88f">E agora parece que essa abordagem é melhor porque você consegue a mesma coisa escrevendo menos código</span>. Mas espere até o final deste capítulo. Você aprenderá que ambas as abordagens são úteis, dependendo do cenário.

---
Você pode continuar escrevendo o método principal conforme apresentado na listagem a seguir para provar que o Spring cria e adiciona o bean em seu contexto.

```java
public class Main {
	public static void main(String[] args) {
		var context = new
			AnnotationConfigApplicationContext(ProjectConfig.class);
			
			Parrot p = context.getBean(Parrot.class);
			
			System.out.println(p);
			System.out.println(p.getName());
	}
}
```
Ao executar esse aplicativo, você observará que o Spring adicionou uma instância Parrot ao seu contexto porque o primeiro valor impresso é a representação String padrão dessa instância. Entretanto, o segundo valor impresso é nulo porque não atribuímos nenhum nome a esse papagaio. O Spring apenas cria a instância da classe, mas ainda é nossa obrigação se quisermos alterar essa instância de alguma forma posteriormente (como atribuir um nome a ela).

Agora que abordamos as duas maneiras mais frequentes de adicionar beans ao contexto do Spring, vamos fazer uma breve comparação entre elas (tabela 2.1).

O que você observará é que, em cenários reais, você usará as anotações de estereótipo o máximo possível (porque essa abordagem implica escrever menos código) e só usará o @Bean quando não puder adicionar o bean de outra forma (por exemplo, você cria o bean para uma classe que faz parte de uma biblioteca e, portanto, não pode modificar essa classe para adicionar a anotação de estereótipo).

**Usando a annotation @Bean**
1. Você tem controle total sobre a criação da instância que adiciona ao contexto do Spring. É sua responsabilidade criar e configurar a instância no corpo do método anotado com @Bean. O Spring apenas pega essa instância e a adiciona ao contexto como está. 
2. Você pode usar esse método para adicionar mais instâncias do mesmo tipo ao contexto do Spring. Lembre-se de que, na seção 2.1.1, adicionamos três instâncias do Parrot ao contexto do Spring. 
3. Você pode usar a anotação @Bean para adicionar ao contexto do Spring qualquer instância de objeto. A classe que define a instância não precisa ser definida em seu aplicativo. Lembre-se de que anteriormente adicionamos uma String e um Integer ao contexto do Spring. 
4. Você precisa escrever um método separado para cada bean que criar, o que adiciona código #boilerplate (<span style="background:#d3f8b6">código boilerplate, são seções de código que precisam ser repetidas em vários lugares com pouca ou nenhuma modificação. Esse tipo de código geralmente é considerado repetitivo e desnecessário, mas ainda assim é necessário para que um programa funcione corretamente</span>) ao seu aplicativo. Por esse motivo, preferimos usar @Bean como uma segunda opção para estereotipar anotações em nossos projetos.

**Usando annotations esteriotipadas**
1. Você só tem controle sobre a instância depois que a estrutura a cria. 
2. Dessa forma, você só pode adicionar uma instância da classe ao contexto. 
3. Você só pode usar anotações de estereótipo para criar beans das classes que seu aplicativo possui. Por exemplo, não é possível adicionar um bean do tipo String ou Integer como fizemos na seção 2.1.1 com a anotação @Bean porque você não é proprietário dessas classes para alterá-las adicionando uma anotação de estereótipo. 
4. 4 O uso de anotações de estereótipo para adicionar beans ao contexto do Spring não adiciona código boilerplate ao seu aplicativo. Você preferirá essa abordagem em geral para as classes que pertencem ao seu aplicativo.
---
**Usando @PostConstruct para gerenciar a instância após sua criação** 
Como discutimos nesta seção, usando anotações de estereótipo, você instrui o Spring a criar um bean e adicioná-lo ao seu contexto. Mas, ao contrário de usar a anotação @Bean, você não tem controle total sobre a criação da instância. Usando @Bean, conseguimos definir um nome para cada uma das instâncias do Parrot que adicionamos ao contexto do Spring, mas usando @Component, não tivemos a chance de fazer nada depois que o Spring chamou o construtor da classe Parrot. E se quisermos executar algumas instruções logo após o Spring criar o bean? Podemos usar a anotação @PostConstruct. O Spring toma emprestada a anotação @PostConstruct do Java EE. Também podemos usar essa anotação com os beans do Spring para especificar um conjunto de instruções que o Spring executa após a criação do bean. Você só precisa definir um método na classe do componente e anotar esse método com @PostConstruct, que instrui o Spring a chamar esse método depois que o construtor terminar sua execução.

### Adição programática de beans ao contexto do Spring
Nesta seção, discutiremos a adição de beans de forma programática ao contexto do Spring. Tivemos a opção de adicionar beans programaticamente ao contexto do Spring com o Spring 5, que oferece grande flexibilidade porque permite adicionar novas instâncias no contexto diretamente chamando um método da instância do contexto. Você usaria essa abordagem quando quisesse implementar uma forma personalizada de adicionar beans ao contexto e as anotações @Bean ou estereótipo não fossem suficientes para suas necessidades. Digamos que você precise registrar beans específicos no contexto do Spring, dependendo de configurações específicas do seu aplicativo. Com as anotações @Bean e stereotype, você pode implementar a maioria dos cenários, mas não pode fazer algo como o código apresentado no próximo snippet:

Para continuar usando nosso exemplo dos papagaios, o cenário é o seguinte: O aplicativo lê uma coleção de papagaios. Alguns deles são verdes, outros são laranja. Você deseja que o aplicativo adicione ao contexto Spring somente os papagaios verdes (Figura 2.13).
```java
for (Parrot p : parrots) {
	if (parrot.isGreen()) {
		context.registerBean(...);
}
}

// Usando o método registerBean(), você pode escrever uma lógica personalizada para adicionar as instâncias desejadas ao contexto do Spring.
```
Vamos ver como esse método funciona. Para adicionar um bean ao contexto do Spring usando uma abordagem programática, basta chamar o método registerBean() da instância ApplicationContext. O registerBean() tem quatro parâmetros, conforme apresentado no próximo trecho de código:
1. Use o primeiro parâmetro beanName para definir um nome para o bean que você adiciona no contexto do Spring. Se você não precisar dar um nome ao bean que está adicionando, poderá usar null como valor ao chamar o método. 
2. O segundo parâmetro é a classe que define o bean que você adiciona ao contexto. Digamos que você queira adicionar uma instância da classe Parrot; o valor que você dá a esse parâmetro é Parrot.class. 
3. O terceiro parâmetro é uma instância de Supplier. A implementação desse Supplier precisa retornar o valor da instância que você adicionou ao contexto. Lembre-se de que Supplier é uma interface funcional que você encontra no pacote java.util .function. O objetivo da implementação de um fornecedor é retornar um valor que você define sem receber parâmetros.
4. O quarto e último parâmetro é uma varargs de BeanDefinitionCustomizer. (Se isso não lhe parecer familiar, não tem problema; o BeanDefinitionCustomizer é apenas uma interface que você implementa para configurar diferentes características do bean; por exemplo, torná-lo primário). Por ser definido como um tipo de varargs, você pode omitir totalmente esse parâmetro ou fornecer a ele mais valores do tipo BeanDefinitionCustomizer.
No projeto "sq-ch2-ex8", você encontra um exemplo de uso do método registerBean(). Você observou que a classe de configuração desse projeto está vazia, e a classe Parrot que usamos para nosso exemplo de definição de bean é apenas um objeto Java antigo (POJO); não usamos nenhuma anotação com ela. No próximo trecho de código, você encontra a classe de configuração conforme a defini para este exemplo:

```java
context.registerBean("parrot1", Parrot.class, parrotSupplier);
```
Você acabou de dar o primeiro grande passo no mundo do Spring. Aprender a adicionar beans ao contexto do Spring pode não parecer muito, mas é mais importante do que parece. Com essa habilidade, agora você pode fazer referência aos beans no contexto do Spring, que discutiremos no capítulo 3.

--- 
**OBSERVAÇÃO** 
Neste livro, usamos apenas abordagens de configuração modernas. No entanto, considero essencial que você também saiba como os desenvolvedores configuravam a estrutura nos primeiros dias do Spring. Naquela época, usávamos XML para escrever essas configurações. No Apêndice B, é fornecido um pequeno exemplo para que você tenha uma ideia de como usar XML para adicionar um bean ao contexto do Spring.

## Resumo 
- A primeira coisa que você precisa aprender no Spring é adicionar instâncias de objeto (que chamamos de beans) ao contexto do Spring. Você pode imaginar o contexto do Spring como um balde no qual você adiciona as instâncias que espera que o Spring seja capaz de gerenciar. O Spring pode ver apenas as instâncias que você adiciona ao seu contexto.  
- Você pode adicionar beans ao contexto do Spring de três maneiras: usando a anotação @Bean, usando anotações de #estereótipo e fazendo isso de forma #programática.

- O uso da anotação @Bean para adicionar instâncias ao contexto do Spring permite que você adicione qualquer tipo de instância de objeto como um bean e até mesmo várias instâncias do mesmo tipo ao contexto do Spring. Desse ponto de vista, essa abordagem é mais flexível do que o uso de anotações de estereótipo. Ainda assim, ela exige que você escreva mais código, pois é necessário escrever um método separado na classe de configuração para cada instância independente adicionada ao contexto.
- Usando anotações de estereótipo, você pode criar beans somente para as classes de aplicativos com uma anotação específica (por exemplo, @Component). Essa abordagem de configuração exige menos código, o que torna sua configuração mais fácil de ler. Você preferirá essa abordagem à anotação @Bean para classes que você define e pode anotar.
- O uso do método registerBean() permite que você implemente uma lógica personalizada para adicionar beans ao contexto do Spring. Lembre-se de que você pode usar essa abordagem somente com o Spring 5 e posterior.

**RESUMO 2.0**
Para usar o modo estereótipo no Spring, você pode marcar suas classes com anotações como `@Component`, `@Service`, `@Repository`, ou `@Controller`. Isso informa ao Spring que essas classes devem ser gerenciadas como beans no contexto da aplicação. Aqui está como você pode usar essas anotações:
