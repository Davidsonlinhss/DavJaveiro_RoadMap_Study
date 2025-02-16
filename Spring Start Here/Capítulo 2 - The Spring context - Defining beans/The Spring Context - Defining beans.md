*This chapter covers*
- Understanding the need for Spring context;
- Adding new object instances to the Spring context.

Neste capítulo, começaremos a aprender como trabalhar com um elemento crucial do framework Spring: o **context** (também conhecido como *application context* em um aplicativo Spring). Imagine o context como um espaço na memória do nosso aplicativo onde adicionamos todas as instâncias de objetos que queremos que o framework gerencie. Por padrão, o Spring não conhece nenhum dos objetos que definimos em nossa aplicação. Para permitir que o Spring veja nossos objetos, precisamos adicioná-los ao context.

Mais adiante neste livro, discutiremos o uso de diferentes capacidades fornecidas pelo Spring em aplicativos. Aprenderemos que a integração dessas funcionalidades é feita por meio do contexto, adicionando instância de objetos e estabelecendo relações entre eles. O Spring utiliza as instâncias no contexto para conectar sua aplicação às várias funcionalidades que ele oferece. Ao longo do livro, aprenderemos os fundamentos das características mais importantes (por exemplo, transações, testes, etc.).

Aprender o que é o **Spring Context** e como ele funciona é o primeiro passo para aprender a usar o Spring, porque, sem saber como gerenciar o contexto Spring, quase nada mais do que aprenderemos a fazer com ele será possível. O context é um mecanismo complexo que permite ao Spring controlar as instâncias que definimos. Dessa forma, ele permite que utilizemos as capacidades fornecidas pelo framework.

Vamos aprender como adicionar instâncias de objetos ao Spring Context. No capítulo 3, aprenderemos como referenciar as instâncias que adicionamos e estabelecer relações entre elas.

Chamaremos essas instâncias de objetos de #beans. Nem todos os objetos de uma aplicação precisam ser gerenciados pelo Spring, então **não é necessário adicionar todas as instâncias de objetos de nossa aplicação ao contexto Spring**. Por enquanto, convido você a se concentrar em aprender as abordagens para adicionar uma instância para o Spring gerenciar.

## 2.1 Creating a Maven project
Nesta seção, discutiremos a criação de um projeto Maven. O Maven não é um assunto diretamente relacionado ao Spring, mas é uma ferramenta que você pode usar para gerenciar facilmente o processo de construção de uma aplicação, independentemente do framework utilizado. É fundamental entender os conceitos básicos de um projeto Maven para acompanhar os exemplos de código. O Maven também é uma das ferramentas de construção mais utilizadas em projetos Spring no mundo real (sendo o Gradle, outra ferramenta de construção, o segundo mais popular, mas não o abordaremos neste livro). Como o Maven é uma ferramenta amplamente conhecida, você pode já estar familiarizado com a criação de projetos e a adição de dependências usando sua configuração. Nesse caso, você pode pular esta seção e ir diretamente para a seção 2.2.

Uma ferramenta de construção (_build tool_) é um software que utilizamos para facilitar a criação de aplicações. Você configura uma ferramenta de construção para realizar tarefas que fazem parte do processo de construção da aplicação, em vez de executá-las manualmente. Alguns exemplos de tarefas que frequentemente fazem parte da construção de uma aplicação são:

- **Downloading the dependencies needed by your app** (Baixar as dependências necessárias para sua aplicação)
    
- **Running tests** (Executar testes)
    
- **Validating that the syntax follows rules that you define** (Validar se a sintaxe segue as regras que você define)
    
- **Checking for security vulnerabilities** (Verificar vulnerabilidades de segurança)
    
- **Compiling the app** (Compilar a aplicação)
    
- **Packaging the app in an executable archive** (Empacotar a aplicação em um arquivo executável)

Essas tarefas são automatizadas pela ferramenta de construção, o que torna o processo de desenvolvimento mais eficiente e menos propenso a erros.

Para que nossos exemplos possam gerenciar dependências de forma fácil, precisamos usar uma ferramenta de construção (*build tool*) para os projetos que desenvolvemos. Esta seção ensina apenas o que você precisa saber para desenvolver os exemplos deste livro; vamos percorrer, passo a passo, o processo de criação de um projeto Maven, e eu vou te ensinar o essencial sobre sua estrutura. Se você quiser aprender mais detalhes sobre o uso do Maven, recomendo o livro *Introducing Maven: A Build Tool for Today’s Java Developers*, de Balaji Varanasi (APress, 2019).

Vamos começar do início. Primeiro, como no desenvolvimento de qualquer outra aplicação, você precisa de um ambiente de desenvolvimento integrado (*Integrated Development Environment*, ou IDE). Qualquer IDE profissional hoje em dia oferece suporte a projetos Maven, então você pode escolher o que preferir: IntelliJ IDEA, Eclipse, Spring STS, NetBeans, entre outros. Para este livro, eu uso o IntelliJ IDEA, que é o IDE que utilizo com mais frequência. Não se preocupe—a estrutura de um projeto Maven é a mesma, independentemente do IDE que você escolher.

Vamos começar criando um novo projeto. No IntelliJ, você cria um novo projeto acessando **File > New > Project**. Isso levará você a uma janela como a da figura 2.1.

Depois de selecionar o tipo do seu projeto, na próxima janela (figura 2.2), você precisará dar um nome a ele. Além do nome do projeto e da escolha do local onde ele será armazenado, para um projeto Maven, você também pode especificar o seguinte:
- **Group ID**: Usado para agrupar vários projetos relacionados.
- **Artifact ID**: O nome da aplicação atual.
- **Version**: Um identificador do estado atual da implementação.
Esses três elementos (**Group ID**, **Artifact ID** e **Version**) são fundamentais para identificar o projeto de forma única no ecossistema Maven. Eles são usados para gerenciar dependências, publicar artefatos e garantir que o projeto seja corretamente identificado em repositórios.

Por exemplo:

- **Group ID**: `com.example` (geralmente relacionado ao domínio da organização).
    
- **Artifact ID**: `meu-projeto` (o nome do projeto ou módulo).
    
- **Version**: `1.0.0` (a versão atual do projeto, seguindo convenções como _Semantic Versioning_).
    

Essas informações são essenciais para o arquivo `pom.xml`, que é o arquivo de configuração central de um projeto Maven. Ele define as dependências, plugins e outras configurações necessárias para construir e gerenciar o projeto.

Em uma aplicação do mundo real, esses três atributos (**Group ID**, **Artifact ID** e **Version**) são detalhes essenciais, e é importante fornecê-los. No entanto, no nosso caso, como estamos trabalhando apenas com exemplos teóricos, você pode omiti-los e deixar que o IDE preencha alguns valores padrão para essas características.

Depois de criar o projeto, você verá que sua estrutura é semelhante à apresentada na figura 2.3. Novamente, a estrutura de um projeto Maven não depende do IDE que você escolheu para desenvolver seus projetos. Quando você olha pela primeira vez para o seu projeto, observa duas coisas principais:

1. **A pasta "src"** (também conhecida como pasta de origem), onde você colocará tudo o que pertence à aplicação.
    
2. **O arquivo `pom.xml`**, onde você escreve as configurações do seu projeto Maven, como adicionar novas dependências.

### Estrutura do Projeto Maven:
- **src**: Contém todo o código-fonte e recursos do projeto.
    - **main**: Código principal da aplicação.
        - **java**: Código-fonte Java.
        - **resources**: Arquivos de configuração, propriedades, templates, etc.
    - **test**: Código de teste.
        - **java**: Código-fonte de testes.
        - **resources**: Recursos específicos para testes.        
- **pom.xml**: Arquivo de configuração do Maven, onde você define dependências, plugins e outras configurações do projeto.

Nos projetos que criamos neste livro, utilizamos muitas dependências externas: bibliotecas ou frameworks que usamos para implementar a funcionalidade dos exemplos. Para adicionar essas dependências aos seus projetos Maven, precisamos alterar o conteúdo do arquivo `pom.xml`. No trecho a seguir, você encontra o conteúdo padrão do arquivo `pom.xml` imediatamente após a criação do projeto Maven.

O trecho a seguir mostra como adicionar dependências externas ao seu projeto. Você escreve todas as dependências entre as tags `<dependencies> </dependencies>`. Cada dependência é representada por um grupo de tags `<dependency> </dependency>`, onde você insere os atributos da dependência: o **group ID**, o nome do **artifact** e a **versão**. O Maven buscará a dependência com base nos valores fornecidos para esses três atributos e fará o download das dependências de um repositório. Não entrarei em detalhes sobre como configurar um repositório personalizado. Você só precisa saber que, por padrão, o Maven fará o download das dependências (geralmente arquivos JAR) de um repositório chamado **Maven Central**. Você pode encontrar os arquivos JAR baixados na pasta de dependências externas do seu projeto, conforme apresentado na figura 2.6.

Depois de adicionar a dependência no arquivo `pom.xml`, conforme apresentado no trecho anterior, o IDE fará o download delas, e você poderá encontrar essas dependências na pasta "Bibliotecas Externas" (*External Libraries*), como mostrado na figura 2.6.

Agora podemos seguir para a próxima seção, onde discutiremos os conceitos básicos do contexto do Spring. Você criará projetos Maven e aprenderá a usar uma dependência do Spring chamada **spring-context** para gerenciar o contexto do Spring.

Depois de adicionar a dependência no arquivo `pom.xml`, conforme apresentado no trecho anterior, o IDE fará o download delas, e você poderá encontrar essas dependências na pasta "Bibliotecas Externas" (*External Libraries*), como mostrado na figura 2.6.

Agora podemos seguir para a próxima seção, onde discutiremos os conceitos básicos do contexto do Spring. Você criará projetos Maven e aprenderá a usar uma dependência do Spring chamada **spring-context** para gerenciar o contexto do Spring.

## 2.2 Adding new beans to the Spring context
Nesta seção, você aprenderá como adicionar novas instâncias de objetos (ou seja, *beans*) ao contexto do Spring. Você descobrirá que existem várias maneiras de adicionar *beans* no contexto do Spring para que o Spring possa gerenciá-los e integrar os recursos que ele oferece à sua aplicação. Dependendo da ação, você escolherá uma maneira específica de adicionar o *bean*; discutiremos quando escolher uma abordagem ou outra. Você pode adicionar *beans* ao contexto das seguintes formas (que descreveremos mais adiante neste capítulo):
- Usando a anotação `@Bean`
- Usando anotações de estereótipo (*stereotype annotations*)
- Programaticamente

Primeiro, vamos criar um projeto sem referência a nenhum framework—nem mesmo ao Spring. Em seguida, adicionaremos as dependências necessárias para usar o contexto do Spring e criá-lo (figura 2.7). Este exemplo servirá como um pré-requisito para adicionar *beans* ao contexto do Spring, que trabalharemos nas seções 2.2.1 a 2.2.3.

Criamos um projeto Maven e definimos uma classe. Para tornar o exemplo mais divertido, vou considerar uma classe chamada `Parrot` com apenas um atributo do tipo `String` representando o nome do papagaio (listagem 2.3). Lembre-se de que, neste capítulo, focamos apenas em adicionar _beans_ ao contexto do Spring, então está tudo bem usar qualquer objeto que ajude você a memorizar melhor as sintaxes. Você pode encontrar o código deste exemplo no projeto "sq-ch2-ex1" (você pode baixar os projetos na seção "Recursos" do livro online). Para o seu projeto, você pode usar o mesmo nome ou escolher o que preferir.

Um ponto crucial a ser observado é que o Spring foi projetado para ser modular. **Por modular, quero dizer que você não precisa adicionar o Spring inteiro ao seu aplicativo quando utiliza algo do ecossistema Spring**. Você só precisa adicionar as partes que utiliza. Por esse motivo, na listagem 2.5, você verá que adicionei apenas a dependência `spring-context`, **que instrui o Maven a buscar as dependências necessárias para usarmos o contexto do Spring**. Ao longo do livro, adicionaremos várias dependências aos nossos projetos de acordo com o que implementamos, mas sempre adicionaremos apenas o que precisamos.

**NOTA** Você pode estar se perguntando como eu sabia qual dependência do Maven deveria adicionar. A verdade é que eu as usei tantas vezes que as conheço de cor. No entanto, você não precisa memorizá-las. Sempre que trabalhar em um novo projeto Spring, você pode pesquisar as dependências que precisa adicionar diretamente na referência do Spring ([https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/core.html](https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/core.html)). Geralmente, as dependências do Spring fazem parte do **group ID** `org.springframework`.

Com a dependência adicionada ao nosso projeto, podemos criar uma instância do contexto do Spring. No próximo trecho, você verá como modifiquei o método `main` para criar a instância do contexto do Spring.

```java
public class Main {
	public static void main(String[] args) {
		// criando uma instância do Spring Context
		var context = new AnnotationConfigApplicationContext(); 
	}
}
```

**NOTA** Usamos a classe `AnnotationConfigApplicationContext` <span style="background:#d4b106">para criar a instância do contexto do Spring</span>. **O Spring oferece várias implementações**. Como, na maioria dos casos, você usará a classe `AnnotationConfigApplicationContext` (a implementação que utiliza a abordagem mais comum hoje em dia: anotações), focaremos nela neste livro. Além disso, menciono apenas o que você precisa saber para a discussão atual. **Se você está começando com o Spring, minha recomendação é evitar se aprofundar em detalhes sobre implementações de contexto e cadeias de herança dessas classes. Há uma grande chance de você se perder em detalhes irrelevantes em vez de focar no que é essencial.**

Conforme apresentado na figura 2.8, você criou uma instância de `Parrot`, adicionou as dependências do contexto do Spring ao seu projeto e criou uma instância do contexto do Spring. Seu objetivo agora é adicionar o objeto `Parrot` ao contexto, que é o próximo passo.
![[The Spring Context - Defining beans.png]]

Acabamos de concluir a criação do projeto de pré-requisito (esqueleto), que usaremos nas próximas seções para entender como adicionar *beans* ao contexto do Spring. Na seção 2.2.1, continuaremos aprendendo como adicionar a instância ao contexto do Spring usando a anotação `@Bean`. Em seguida, nas seções 2.2.2 e 2.2.3, você também aprenderá as alternativas de adicionar a instância usando anotações de estereótipo (*stereotype annotations*) e fazendo isso programaticamente. Após discutir as três abordagens, vamos compará-las, e você aprenderá as melhores circunstâncias para usar cada uma delas.

## 2.2.1 Using the *@Bean* annotation to add beans into the Spring context
Nesta seção, discutiremos como adicionar uma instância de objeto ao contexto do Spring usando a anotação `@Bean`. Isso permite que você <span style="background:#d4b106">adicione instâncias de classes definidas em seu projeto</span> (como `Parrot`, no nosso caso), <span style="background:#d4b106">bem como classes que você não criou</span>, <span style="background:#d4b106">mas que são usadas em sua aplicação</span>. Acredito que essa abordagem é a mais fácil de entender quando você está começando. <span style="background:#d4b106">Lembre-se de que o motivo pelo qual você aprende a adicionar</span> *beans* <span style="background:#d4b106">ao contexto do Spring é que o Spring só pode gerenciar os objetos que fazem parte dele</span>. Primeiro, vou mostrar um exemplo direto de como adicionar um *bean* ao contexto do Spring usando a anotação `@Bean`. Em seguida, mostrarei como adicionar vários *beans* do mesmo tipo ou de tipos diferentes.

Os passos que você precisa seguir para adicionar um *bean* ao contexto do Spring usando a anotação `@Bean` são os seguintes (figura 2.9):
1. **Defina uma classe de configuração** (anotada com `@Configuration`) para o seu projeto. Essa classe, como discutiremos mais adiante, é usada para configurar o contexto do Spring.
2. **Adicione um método à classe de configuração** que retorne a instância do objeto que você deseja adicionar ao contexto e anote esse método com a anotação `@Bean`.
3. **Faça o Spring usar a classe de configuração** definida no passo 1. Como você aprenderá mais adiante, usamos classes de configuração para escrever diferentes configurações para o framework.

Vamos seguir esses passos e aplicá-los no projeto chamado. Para manter todos os passos que discutimos separados, **recomendo que você crie novos projetos para cada exemplo**.
![[The Spring Context - Defining beans-1.png]]

**NOTA**: Uma classe de configuração (*configuration class*) é uma classe especial em aplicações Spring que usamos para instruir o Spring a realizar ações específicas. Por exemplo, podemos dizer ao Spring para criar *beans* ou habilitar determinadas funcionalidades. Ao longo do restante do livro, você aprenderá diferentes coisas que podem ser definidas em classes de configuração.

**PASSO 1: DEFININDO UMA CLASSE DE CONFIGURAÇÃO NO SEU PROJETO**
O primeiro passo é **criar uma classe de configuração no projeto**. Uma classe de configuração do Spring é caracterizada pelo fato de ser anotada com a anotação `@Configuration`. Usamos as classes de configuração para **definir várias configurações relacionadas** ao Spring no projeto. Ao longo do livro, você aprenderá diferentes coisas que podem ser configuradas usando as classes de configuração. Por enquanto, *focaremos apenas em adicionar novas instâncias ao contexto do Spring*. O próximo trecho mostra como definir a classe de configuração. Eu nomeei essa classe de configuração como `ProjectConfig`.
```java
// We use the @Configuration annotation to define this class as a Spring configuration class
@Configuration 
public class ProjectConfig {

}
```

**NOTA**: Eu separo as classes em pacotes diferentes para tornar o código mais fácil de entender. Por exemplo, crio as classes de configuração em um pacote chamado `config` e a classe `Main` em um pacote chamado `main`. Organizar as classes em pacotes é uma boa prática; recomendo que você também siga isso em suas implementações do mundo real.

**PASSO 2: CRIE UM MÉTODO QUE RETORNA O BEAN E ANOTE O MÉTODO COM @BEAN**
Uma das coisas que você pode fazer com uma classe de configuração é adicionar *beans* ao contexto do Spring. Para isso, *precisamos definir um método que retorne a instância do objeto* que desejamos adicionar ao contexto e anotar esse método com a anotação `@Bean`. *Isso informa ao Spring que ele deve chamar esse método ao inicializar seu contexto e adicionar o valor retornado ao contexto*. O próximo trecho mostra as alterações na classe de configuração para implementar este passo.

**NOTA**: Para os projetos deste livro, eu uso o Java 11, a versão mais recente de suporte de longo prazo (LTS) do Java. Cada vez mais projetos estão adotando essa versão. Geralmente, o único recurso específico que uso nos trechos de código e que não funciona com versões anteriores do Java é a palavra reservada `var`. Eu uso `var` aqui e ali para tornar o código mais curto e fácil de ler, mas, se você quiser usar uma versão anterior do Java (como o Java 8, por exemplo), pode substituir `var` pelo tipo inferido. Dessa forma, você fará os projetos funcionarem também com o Java 8.

```java
//
@Bean  
Parrot parrot() {  
    var p = new Parrot();  
    p.setName("Koko");  
    return p;  
}
```
- *@Bean*: Ao adicionar a anotação `@Bean`, instruímos o Spring a chamar esse método durante a *inicialização do contexto* e adicionar o valor retornado ao contexto. (Retorna o valor ao contexto)
- *return p;*: o Spring adiciona ao seu **Application Context** a instância de **Parrot** retornada pelo método

Observe que o nome que usei para o método não contém um verbo. Você provavelmente aprendeu que uma das melhores práticas em Java é usar verbos nos nomes dos métodos, *pois os métodos geralmente representam ações*. No entanto, **para métodos que usamos para adicionar *beans* ao contexto do Spring, não seguimos essa convenção.** Esses métodos representam as instâncias de objetos que eles retornam e que agora farão parte do contexto do Spring. O nome do método também se torna o nome do *bean* (como no exemplo da listagem 2.8, o nome do *bean* agora é "parrot"). Por convenção, você **pode usar substantivos, e, na maioria das vezes, eles têm o mesmo nome da classe**.

**PASSO 3: FAÇA O SPRING INICIALIZAR SEU CONTEXTO USANDO A CLASSE DE CONFIGURAÇÃO RECÉM-CRIADA**
Implementamos uma classe de configuração na qual informamos ao Spring a instância do objeto que precisa se tornar um _bean_. Agora, precisamos garantir que **o Spring use essa classe de configuração ao inicializar seu contexto.** O próximo trecho mostra como alterar a instanciação do contexto do Spring na classe `main` para usar a classe de configuração que implementamos nos dois primeiros passos.
```java
public class Main {
	public static void main(String[] args) {
		var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
	}
}
```

- *var context = new AnnotationConfigApplicationContext(ProjectConfig.class);*
Quando criamos o a instância do Spring Context, enviamos a configuração da class como um parâmetro para instruir o Spring a utilizá-la.

Para verificar a classe *Parrot* se ela faz parte do contexto agora, podemos referenciar a instância e printar o nome no console:

**NOTA**: Em cenários do mundo real, usamos testes unitários e de integração para validar se nossas implementações funcionam conforme o desejado. Os projetos neste livro implementam testes unitários para validar o comportamento discutido. Como este é um livro de "introdução", ainda não estamos familiarizados com testes unitários. Para evitar confusão e permitir que nos concentremos no assunto abordado, não discutiremos testes unitários até o capítulo 15. 

Como no exemplo anterior, podemos adicionar qualquer tipo de objeto ao contexto do Spring. Vamos adicionar uma String e um Integer e verificar que está funcionando.

![[The Spring Context - Defining beans-2.png]]

Até agora, adicionamos um ou mais beans de diferentes tipos ao contexto do Spring. Mas poderíamos adicionar mais de um objeto do mesmo tipo? Se sim, como podemos nos referir individualmente a esses objetos? Vamos criar um novo projeto, para demonstrar como podemos adicionar múltiplos beans do mesmo tipo ao contexto do Spring e como referenciá-los posteriormente. 

![[The Spring Context - Defining beans-3.png]]

**NOTA:** não confunda o nome do *bean* com o nome do papagaio. No nosso exemplo, os nomes (ou identificadores) dos *beans* no contexto do Spring são **parrot1**, **parrot2**, e **parrot3** (como os nomes dos métodos anotados com *@Bean* que os definem). Os nomes que atribuí aos papagaios são **Koko**, **Miki**, **Riki**. O nome do papagaio é apenas um atributo do objeto **Parrot** e não tem nenhum significado para o Spring. 

Podemos declarar quantas instâncias do mesmo tipo desejarmos simplesmente declarando mais métodos anotados com o *@Bean*. A listagem a seguir mostra como declarei três *beans* do tipo *Parrot* na classe de configuração. 

Agora, obviamente, não podemos mais obter os *beans* do contexto apenas especificando o tipo. Se fizermos isso, uma exceção será lançada porque o Spring não pode adivinhar a qual instância declarada estamos nos referindo.

Para resolver esse problema de ambiguidade, precisamos referirmos a uma das instâncias utilizando o nome do **bean**. Por padrão, o Spring usa os nomes dos métodos anotados com *@Bean* como os próprios nomes dos **beans**. Lembre-se de que é por isso que não nomeamos os métodos *@Bean* usando verbos. No nosso caso, os *beans* têm os nomes **parrot1**, **parrot2** e **parrot3** (lembre-se, o método representa o *bean*). 

Agora, vamos alterar o método **main** para referir-se explicitamente a um desses *beans* usando seu nome:
```java
public class Main {
	public static void main(String[] args) {
		var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
	Parrot p = context.getBean("parrot2", Parrot.class);
	System.out.println(p.getName());
	}
}
```


Executando o aplicativo agora, não receberemos uma exception, em vez disso, veremos no console o nome do segundo papagaio, Miki.

Se desejarmos atribuir outro nome ao *bean*, podemos usar tanto o atributo **name** quanto o atributo *value* da anotação *@Bean*. Qualquer uma das seguintes sintaxes alterará o nome do *bean* para "miki":
- *@Bean(name = "miki"*);
- *@Bean(value = "miki*);
- *@Bean("miki")*;

```java
@Bean(name = "miki") // sets the name of the bean
Parrot parrot2() {
    var p = new Parrot();
    p.setName("Miki"); // sets the name of the parrot
    return p;
}
```

**Definindo um bean como primário**
Anteriormente nesta seção, discutimos que você pode ter vários *beans* do mesmo tipo no contexto do Spring, mas precisa referir-se a eles usando seus nomes. No entanto, existe outra opção para referenciar *beans* no contexto quando há múltiplos do mesmo tipo.

Quando você tem vários *beans* do mesmo tipo no contexto do Spring, pode tornar um deles como **primário**. Você marca o *bean* que deseja tornar primário usando a anotação `@Primary`. Um *bean* primário é aquele que o Spring escolherá automaticamente se houver várias opções e você não especificar um nome; em outras palavras, o *bean* primário é simplesmente a escolha padrão do Spring.

O próximo trecho de código mostra como fica o método anotado com `@Bean` e marcado como primário:
```java
@Bean
@Primary
Parrot parrot2() {
    var p = new Parrot();
    p.setName("Miki");
    return p;
}
```
Se referenciarmos *Parrot* sem especificar o nome, o Spring irá selecionar agora *Miki* por padrão. Obviamente, se eu declarar um bean do tipo primary. 

### 2.2.2 Using stereotype annotation to add beans to the Spring context
Nesta seção, você aprenderá uma abordagem diferente para adicionar *beans* ao contexto do Spring (mais adiante neste capítulo, também vamos comparar as abordagens e discutir quando escolher uma ou outra). Lembre-se de que adicionar *beans* ao contexto do Spring é essencial porque é assim que você informa ao Spring sobre as instâncias de objetos da sua aplicação, que precisam ser gerenciadas pelo framework. O Spring oferece várias maneiras de adicionar *beans* ao seu contexto. Em diferentes cenários, você pode achar mais conveniente usar uma dessas abordagens em vez de outra. Por exemplo, <span style="background:#d4b106">com anotações estereótipos, você observará que escreve menos código para instruir o Spring a adicionar um *bean* ao seu contexto.</span>

Mais tarde, você aprenderá que o Spring oferece várias anotações estereótipos. Mas nesta seção, quero que você se concentre em como usar uma anotação estereótipo de forma geral. Vamos utilizar a mais básica delas, a `@Component`, e usá-la para demonstrar nossos exemplos.

Com anotações estereótipos, <span style="background:#affad1">você adiciona a anotação acima da classe para a qual deseja ter uma instância no contexto do Spring</span>. Ao fazer isso, dizemos que você **marcou a classe como um componente**. Quando o aplicativo cria o contexto do Spring, <span style="background:#d4b106">o Spring cria uma instância da classe que você marcou como componente e adiciona essa instância ao seu contexto</span>. Continuaremos a ter uma classe de configuração ao usar essa abordagem para informar ao Spring onde procurar as classes anotadas com anotações estereótipos. Além disso, você pode usar ambas as abordagens juntas (usando `@Bean` e anotações estereótipos simultaneamente; trabalharemos nesses tipos de exemplos complexos em capítulos posteriores).

Os passos que precisamos seguir no processo são os seguintes:
1. Usando a anotação *@Component*, marcamos as classes para as quais desejamos que o Spring adicione uma instância ao seu contexto (no nosso caso, a classe *Parrot*);
2. Usando a anotação *@ComponentScan* sobre a classe de configuração, instruímos o Spring sobre onde encontrar as classes que nós marcamos.

Vamos pegar nosso exemplo com a classe *Parrot*. Podemos adicionar uma instância da classe no Spring context anotando a class *Parrot* com um dos stereotype annotation, dizendo *@Component*.

![[The Spring Context - Defining beans-4.png]]

The next code shows you how to use the *@Component* annotation for the **Parrot** class:
```java
@Component
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

- By using *@Component* annotation over the class, we instruct Spring to create an instance of this class and add it to its context. 

Por padrão, o Spring não busca classes anotadas com *stereotype annotation*, então, se deixarmos o código como está, o Spring não adicionará um bean do tipo #Parrot em seu contexto. Para informar ao Spring que ele precisa buscar classes anotadas com *stereotype annotation*, usamos a anotação *@ComponentScan* na classe de configuração. Além disso, com a anotação *@ComponentScan*, indicamos ao Spring onde procurar essas classes. Listamos os pactos onde definimos as classes com *stereotype annotations*. A listagem a seguir mostra como usar a anotação *@ComponentScan* na classe de configuração do projeto.

```java

@Configuration
@ComponentScan(basePackages = "com.DavJaveiro.helloWorldJPA.main")
public class ProjectConfig {
	@Bean
	Parrot parrot1() {
		var p = new Parrot();
		p.setName("Koko");
		return p;
	}

}
```

Neste ponto, dizemos ao Spring o seguinte:
1. Quais classes adicionar uma instância ao seu context (Parrot.java);
2. Onde encontrar essas classes (usando o *@ComponenetScan(basePackages)*)

**NOTA:** Não precisamos mais de métodos para definir os beans. E agora, parece que essa abordagem é melhor, pois conseguimos o obter o mesmo resultado escrevendo bem menos código. 

Em cenários reais, usaremos anotações de estereótipo (*stereotype annotations*) tanto quanto possível, pois essa abordagem implica na escrita de menos código, e usaremos *@Bean* quando não for possível adicionar o bean de outra forma (quando criamos o bean para uma classe que faz parte de uma biblioteca, e portanto, não podemos modificar essa classe para adicionar a anotação de estereótipo). 

Para obtermos todos os beans, podemos utilizar algumas das abordagens abaixo:

#### 1. Usar o Nome do Bean Explicitamente
O Spring permite que **especifiquemos** o nome do bean ao recuperá-lo do contexto. O nome do bean é o mesmo que o nome do método que o define:
```java
var context = new AnnotationConfigApplicationContext(ProjectConfig.class); 

// Recuperando o bean pelo nome "parrot2"
Parrot p = context.getBean("parrot2", Parrot.class);
System.out.println(p.getName());

```

#### 2. Usando a Anotação *@Primary*
Quando utilizamos o *@Primary*, o Spring escolhe automaticamente um bean especificado por essa annotation quando houver múltiplos candidatos.

#### 3. Listar Todos os Beans do Tipo *Parrot*
Se quisermos listar todos os beans do tipo *Parrot* registrados no contexto do Spring, podemos usar o método *getBeansOfType*.

```java
var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

// Iterando sobre os beans e imprimindo seus nomes
Map<String, Parrot> parrots = context.getBeansOfType(Parrot.class);

parrots.forEach((name, parrot) -> {
	System.out.println("Bean Name: " + name + ", Parrot Name: " + parrot.getName());
})
```

Essa abordagem é útil quando precisamos trabalhar com todos os beans de um determinado tipo.

 **Tabela 2.1: Vantagens e Desvantagens - Comparação das Duas Formas de Adicionar Beans ao Contexto do Spring**

| **Critério**                                      | **Usando a Anotação `@Bean`**                                                                                                                                                                                                                             | **Usando Anotações de Estereótipo**                                                                                                                                                                                                                                       |
| ------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **1. Controle sobre a Criação da Instância**      | Você tem controle total sobre a criação da instância que é adicionada ao contexto do Spring. É sua responsabilidade criar e configurar a instância no corpo do método anotado com `@Bean`. O Spring apenas adiciona essa instância ao contexto como está. | Você só tem controle sobre a instância após o framework criá-la.                                                                                                                                                                                                          |
| **2. Número de Instâncias do Mesmo Tipo**         | Você pode usar esse método para adicionar várias instâncias do mesmo tipo ao contexto do Spring. Por exemplo, adicionamos três instâncias de `Parrot` na Seção 2.1.1.                                                                                     | Dessa forma, você só pode adicionar uma instância da classe ao contexto.                                                                                                                                                                                                  |
| **3. Tipos de Objetos que Podem Ser Adicionados** | Você pode usar a anotação `@Bean` para adicionar qualquer objeto ao contexto do Spring. A classe que define a instância não precisa estar definida em sua aplicação. Por exemplo, adicionamos uma `String` e um `Integer` ao contexto do Spring.          | Você só pode usar anotações de estereótipo para criar beans das classes que sua aplicação possui. Por exemplo, não poderia adicionar um bean do tipo `String` ou `Integer`, pois você não possui essas classes para modificá-las adicionando uma anotação de estereótipo. |
| **4. Código Adicional (Boilerplate)**             | Você precisa escrever um método separado para cada bean que cria, o que adiciona código boilerplate à sua aplicação. Por isso, preferimos usar `@Bean` como segunda opção em relação às anotações de estereótipo em nossos projetos.                      | Usar anotações de estereótipo para adicionar beans ao contexto do Spring não adiciona código boilerplate à sua aplicação. Em geral, você preferirá essa abordagem para as classes que pertencem à sua aplicação.                                                          |
### **Resumo da Tabela**
- **`@Bean`**: Útil quando você precisa de controle total sobre a criação da instância, deseja adicionar múltiplas instâncias do mesmo tipo ou precisa adicionar objetos de classes que não pertencem à sua aplicação (como `String` ou `Integer`). No entanto, essa abordagem gera mais código boilerplate.
- **Anotações de Estereótipo**: Preferidas para classes que pertencem à sua aplicação, pois reduzem o código boilerplate e são mais simples de usar. No entanto, elas têm limitações, como a impossibilidade de adicionar múltiplas instâncias do mesmo tipo ou trabalhar com classes externas.

---
### Usando `@PostConstruct` para gerenciar a instância após sua criação
Ao usar **anotações de esteriótipo (como *@Componenet***), instruímos o Spring a criar um bean e adicioná-lo ao seu contexto. No entanto, diferentemente do uso da anotação *@Bean*, não temos controle total sobre a criação da instância. Com o *@Bean*, conseguimos definir nomes para cada uma das instâncias *Parrot* que adicionamos ao contexto do Spring. Já com *@Component*, não tivemos a chance de fazer algo após o Spring chamar o construtor da classe **Parrot**. 

Mas e se quisermos executar algumas instruções logo após o Spring criar o bean? Podemos usar a anotação *@PostConstruct*.

### O que é `@PostConstruct`?
O Spring utiliza a anotação `@PostConstruct`, que foi originalmente introduzida no **Java EE** . Com ela, <span style="background:#d4b106">podemos especificar um conjunto de instruções que o Spring deve executar após a criação do bean.</span> Para isso, basta definir um método na classe do componente e anotá-lo com `@PostConstruct`. Isso instrui o Spring a chamar esse método logo após o construtor terminar sua execução.