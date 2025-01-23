In the previous chapter, we learned about the REST architecture style. Before we go and implement RESTful web services using Spring and Spring Boot, we need to have a proper (apropriado) understanding of the basic Spring concepts. In this chpater, you will learn about the Spring fundamentals and features that are required to implement RESTful web services using the Sring Framework. This will (isto irá) provide the technical perspective required for developing the example e-commerce app. If you already aware of the Spring fundamentals required for implementing RESTful APIs, you can move on to the next chapter. 

We'll cover the following topics as part of this chapter:
- Introduction to Spring
- Understanding the basic concepts of the Spring Framework
- Working with the *servlet dispatcher*

## Technical requirements
This chapter covers concepts and does not involve writing actual code. However, you'll need basic Java knowledge.

## Understanding the patterns and paradigms of Spring
Spring is a framework written in the Java language. Its provides lots (muitos) of modules, such as Spring Data, Spring Security, Spring Cloud, Spring Web, and so on (*e assim por diante*). Is is popular for building enterprise applications. Initially, it was looked at as a Java Enterprise Edition (JEE) alternative. However, over the years, it has become over JEE. Spring supports *dependecy injection (DI)*, also know as *inversion of control (IoC)*, and *aspect-oriented programming* #AOP out of the box at its core. Apart from Java, Spring supports other JVM languages such as Groovy and Kotlin.

With the introduction of Spring Boot, the **turnaround time** (tempo de resposta)  for the development of web services was reduced. We can hit the ground running. Isso é enorme e um dos motivos pelos quais o Spring se tornou tão popular recentemente.

Cobrir os fundamentos do Spring por si só exige um livro dedicado. Tentarei ser conciso e abordar todos os recursos necessários para que você avance e adquira o conhecimento necessário sobre a implementação de REST de forma detalhada.

No entanto, antes de prosseguirmos, devemos entender os princípios e padrões de design que formam as bases do Spring, em particular #IOC (inversão de controle), DI (injeção de Dependência) e AOP (Programação Orientada a Aspectos). 

## What is IoC?
Programas tradicionais CLI (Command Line Interface) são o método típico para implementações de programação #procedural, onde <span style="background:#d4b106">o fluxo é determinado pelo programador</span> e o código é executado sequencialmente, ou seja, uma etapa após a outra. No entanto, aplicações baseadas em interface gráfica (UI) determinam o fluxo dos programas com base nas entradas e eventos do usuário, que são dinâmicos. 

Há muito tempo, quando predominavam os métodos procedurais de programação, era necessário encontrar uma forma de transferir o controle do fluxo do método tradicional procedural (onde o programador dita o fluxo) para fontes externas, como um framework ou componentes que determinassem o fluxo do programa. Esse movimento é conhecido como #IoC (Inversão de Controle). É um princípio genérico e parte integrante da maioria dos frameworks.

Com a chegada da abordagem de Programação Orientada a Objetos (OOP), os frameworks começaram a oferecer a implementação do padrão de contêiner IoC, que dá suporte à injeção de Dependência (DI). 

## What is DI?
Imagine que estamos escrevendo um programa que precisa obter dados de um banco de dados. O programa, portanto, requer uma conexão com o banco de dados. Você poderia usar o objeto de conexão do banco de dados do JDBC, instanciando e atribuindo o objeto de conexão diretamente no programa.

Ou, alternativamente, poderia simplesmente receber o objeto de conexão como um parâmetro em um construtor, um método #setter ou um método de fábrica. Nesse caso, o framework criaria o objeto de conexão com base na configuração e o atribuiria ao seu programa em tempo de execução. Aqui, o **framework essencialmente injeta o objeto de conexão durante a execução**o. Isso é chamado de Injeção de Dependência. O Spring oferece suporte à DI para composição de classes. 

---
**Nota**
O Spring Framework lança um erro em tempo de execução caso alguma dependência esteja indisponível ou se o nome correto do objeto não for especificado quando houver mais de um tipo de objeto disponível. Em contraste, alguns frameworks, como o Dagger, verificam essas dependências em tempo de compilação. 

---

DI (Injeção de Dependência) é um tipo de IoC (Inversão de Controle). Contêineres IoC constroem e mantêm objetos de implementação. Esses tipos de objetos (objetos necessários por outros objetos - uma forma de dependência) são injetados nos objetos que precisam deles, seja por meio de um construtor, um setter ou uma interface. Isso desacopla a instanciação e permite a DI em tempo de execução.

A DI também pode ser alcançada utilizada o padrão Service Locator. No entanto, aqui nos limitaremos à abordagem baseada no padrão IoC. Vamos examinar o IoC mais detalhadamente com um exemplo de código na próxima seção principal deste capítulo.

## What is AOP?
#AOP (Programação Orientada a Aspectos) é um paradigma de programação que trabalha em conjunto com a OOP (Programação Orientada a Objetos). Uma boa prática na OOP é lidar com uma única responsabilidade em uma classe específica - esse princípio é chamado de **Princípio da Responsabilidade Única** #SRP, aplicável a módulos, classes e métodos. Por exemplo, ao escrever uma classe *Gear* em uma aplicação do domínio automotivo, ela deve lidar apenas com funções relacionadas ao objeto *gear* (marcha) e não deve executar outras funções, como frenagem.

No entanto, em modelos de programação, muitas vezes é necessário implementar funcionalidades que *se estendem por várias classes*. Em algumas aplicações, a maioria das classes utiliza recursos como logging (registro de logs) ou métricas. Funcionalidades como **logging**, segurança, gerenciamento de transações e métricas são exigidas em várias classes/módulos. O código dessas funcionalidades geralmente fica espalhado por diversas classes. Na OPP, não há uma forma nativa de abstrair e encapsular esses recursos. É aqui que o AOP entra em cena. 

Essas funcionalidades, conhecidas como #aspectos, são *preocupações transversais que atravessam vários pontos no modelo de objetos*. O #AOP fornece uma maneira de lidar com esses aspectos de forma consistente em várias classes/módulos. 

**AOP permite:**
- Abstrair e encapsular preocupações transversais
- Adicionar o comportamento dos aspectos ao redor do seu código
- Modularizar o código para preocupações transversais, facilitando a manutenção e a extensão
- Concentrar-se na lógica de negócio no código principal, tornando-o mais limpo. Preocupações transversais são encapsuladas e mantidas separadamente.

Sem o APO, é muito difícil e complexo alcançar todos os pontos mencionados anteriormente. 
Esta seção deve ter ajudado a entendermos conceitualmente IoC, DI e AOP. Ao longo do restante deste capítulo, faremos uma análise detalhada da implementação em código desses padrões e paradigmas.

## Understanding the application of IoC containers
O backbone (espinha dorsal) do Spring Framework é o contêiner IoC, que é responsável pelo ciclo de vida dos beans. No mundo do Spring, um objeto Java pode ser considerado um #bean se for instanciado, montado e gerenciado pelo contêiner IoC. 

Criamos uma grande quantidade de beans, ou objetos, para nossa aplicação. Um bean pode ter dependências que exigem outros objetos para funcionar. Um contêiner IoC é responsável por injetar as dependências do objeto ao criar esse bean.

No contexto do SPring, IoC também é conhecido como DI (Injeção de Dependência). 

*Mas qual o papel do Application Context:*
- **Gerenciamento de Beans** ele cria, configura e gerencia o ciclo de vida dos objetos (beans) declarados em sua aplicação;
- **Injeção de Dependências (DI)** - resolve e injeta automaticamente as dependências necessárias para os beans.
- **Configuração Centralizada** - permite que definamos como os beans interagem e dependem um dos outros por meio de arquivos de configuração XML, anotações ou Java Config.
Quando usamos o **Application Context**, estamos interagindo com o mecanismo IoC que faz parte do Spring.

---
**Nota**
O núcleo do contêiner IoC do Spring Framework está definido em dois pacotes:
*org.springFramework.beans* e *org.springframework.context*.


São duas interfaces importantes que formam a base para os contêineres IoC:

#BeanFactory *org.springframework.beans.factory.BeanFactory*:
**BeanFactory** fornece a estrutura de configuração e funcionalidades básicas, cuidando da instanciação e do *wiring* (ligações ou injeções de dependência) dos beans.


#ApplicationContext *org.springframework.contexto.ApplicationContext*:
Também pode cuidar da instanciação e do *wiring* dos beans. No entanto, ele se destaca por oferecer funcionalidades mais específicas para aplicações corporativas, como:
- **Gerenciamento integrado do ciclo de vida**
- **Registro automático de BeanPostProcessor** e **BeanFactoryPostProcessor**
- **Internacionalização (internationalization) com acesso facilitado ao MessageSOurce para tratamento de recursos de mensagens**
- **Publicação de eventos utilizando APplicationEvent integrado**
- **Provisionamento de WebApplicationContext**, um contexto específico da camada de aplicação para aplicações web.

#ApplicationContext é uma subinterface de #BeanFactory . Vamos observar sua assinatura de classe:
```java
public interface ApplicationContext extends EnvironmentCapable,  
ListableBeanFactory, HierarchicalBeanFactory, MessageSource,  
ApplicationEventPublisher, ResourcePatternResolver {…}

```
Aqui, **ListableBeanFactory** e **HieararchicalBeanFactory** são subinterfaces de **BeanFactory**.

O Spring recomenda o uso de #ApplicationContext devido a esses recursos adicionais, bem como suas funcionalidades avançadas de gerenciamento de beans.

Agora, sabemos que a interface #ApplicationContext representa o contêiner IoC e gerencia os beans, mas você deve estar se perguntando como ele determina quais beans instanciar, montar e configurar.  De onde ele ob´tem suas instruções? A resposta é o metadado de configuração. O metadado de configuração permite que você expresse os objetos da sua aplicação e as interdependências entre esses objetos. O metadado de configuração pode ser representado de três maneiras: 
- Configuração XML
- #Annotation 
- Java e Código Java
Escrevemos os objetos de negócio e fornecemos o metadado de configuração, e o <span style="background:#affad1">contêiner Spring gera um sistema</span> totalmente configurado e pronto para uso, como mostrado na figura 2.1:
![[Modern API Development with SPring 6 and Spring Boot 3/Part 1 - Restful Web Service Fundamentals/Imagens/Chapter 2 - Sring Concepts and REST APIs.png]]

![[Modern API Development with SPring 6 and Spring Boot 3/Part 1 - Restful Web Service Fundamentals/Imagens/Chapter 2 - Sring Concepts and REST APIs-1.png]]

Agora que você tem uma ideia de como os beans são gerenciados, vamos aprender mais sobre o que é um bean e o que ele pode fazer.

## Defining a bean and its scope
Os #Beans são #objetos <span style="background:#b1ffff">Java gerenciados pelos contêineres de Inversão de Controle</span> (IoC). O desenvolvedor **fornece os metadados** de configuração para um contêiner IoC, que utiliza esses metadados para construir, **montar e gerenciar os beans**. Cada bean deve ter um identificador único dentro de um contêiner. Um bean pode até mesmo possuir mais de uma identidade, utilizando um alias.

#IoC - Inversão de Controle para Gerenciamento de Objetos
#Beans - Objetos Java Gerenciado pelo Contêiner IoC. 

Vamos declarar um bean simples utilizando uma configuração baseada em Java:
```java
public class SampleBean {

    public void init() { 
        // initialization logic
    }

    public void destroy() { 
        // destruction logic
    }

    // bean code
}

public interface BeanInterface {
    // interface code
}

public class BeanInterfaceImpl implements BeanInterface {
    // bean code
}

@Configuration
public class AppConfig {

    @Bean(initMethod = "init", destroyMethod = "destroy", name = {"sampleBean", "sb"})
    @Description("Demonstrate a simple bean")
    public SampleBean sampleBean() {
        return new SampleBean();
    }

    @Bean
    public BeanInterface beanInterface() {
        return new BeanInterfaceImpl();
    }
}

```

No código acima, o #bean é declarado usando a classe *AppConfig*. A anotação *@Configuration* é uma anotação de nível de classe que indica que a classe contém código de configuração. A anotação *@Bean* é uma anotação de nível de método usada para definir o bean. Também podemos passar os métodos de ciclo de vida de inicialização e destruição do beans usando os atributos da anotação *@Bean*, conforme mostrado no código acima.

Em geral, o nome de um bean é o nome da classe com a primeira letra em minúsculo. Por exemplo, o nome do bean de *BeanInterface* seria *beanInterface*. No entanto, também podemos usar o atributo *name* para definir o nome do bean e seus #aliases - como podemos ver no código acima, o *SampleBean* tem dois nomes de bean: *sampleBean* e *sb*.

---
**Nota**
Os métodos padrão para destruição são os métodos públicos *close/shutdow*, que são chamados **automaticamente pelo contêiner**. No entanto, se desejarmos seguir um método diferente, podemos usar o código acima. Se não quiser que o contêiner chame o método de destruição padrão, podemos atribuir uma string vazia ao atributo *destroyMethod (destroyMethodo="")*.

Um exemplo de uso 
1. **Caso Padrão (usando close ou shutdown):** neste exemplo, temos um bean *DatabaseConnection* que gerencia a conexão com o banco de dados. No final da execução, queremos garantir que a conexão seja fechada corretamente.
```java
@Bean
public DatabaseConnection databaseConnection() {
	return new DatabaseConnection();
}
```

2. **Controle Customizado (definido destroyMethod = ""):** Agora, digamos que você não quer que o Spring use o método *close()* automaticamente, porque talvez queiramos fechar a conexão de banco de dados manualmente em um momento específico ou após um evento, e não quando o bean for destruído pelo Spring.
```java
@Bean(destroyMethodo="")
public DatabaseConnection databaseConnection() {
	return new DatabaseConnection();
}
```
Neste caso, estamos assumindo o controle total do fechamento da conexão. O Spring não chamará automaticamente o método de destruição *close()* ou *shutdown()*.

Por padrão, o contêiner do Spring chama automaticamente o método de destruição de um bean quando ele é removido do contexto, ou seja, quando a aplicação é finalizada ou o bean é explicitamente destruído.


---

Também podemos criar um bean usando as interfaces mostrados no código anterior para o bean *BeanInterface*. Observe que a anotação *@Bean* deve estar dentro de uma classe anotada com *@Component*. A anotação *@Component* é uma forma **genérica** de declarar um bean. Uma classe anotada com *@Configuration* permite que um método retorne um bean anotado com *@Bean*. 

A anotação *@Configuration* é meta-anotada com *@Component*, o que faz com que a anotação *@Bean* funcione dentro dela. Existem outra anotações, como *@Controller*, *@Service* e *@Repository*, que também são anotadas com *@Component*. 

A anotação *@Description*, como nome sugere, é usada para descrever um bean. Quando ferramentas de monitoramento são utilizadas, essas descrições ajudam a entender os beans em tempo de execução. 

### The *@ComponentScan* annotation
A anotação *@ComponentScan* permite a varredura automática de beans. Ela aceita alguns argumentos, como pacotes base e suas classes. **O container do Spring**, então, analisa todas as classes dentro do <span style="background:#d4b106">pacote base</span> (o Spring Boot considera como pacote base o mesmo pacote onde está localizada a classe principal, aquela com a anotação *@SpringBootApplication*) e busca por beans. Ele verifica todas as classes anotadas com *@Component* ou outras anotações que são meta-antoada com *@component*, como *@Configuration*, *@Configuration*, *@Controller*, entre outras...

Por padrão, o Spring Boot define o pacote base a partir da classe que possui a anotação *@SpringBootApplication* ou *@ComponentScan*. No entanto, podemos utilizar o atributo *basePackageClasses* para especificar diretamente quais pacotes devem ser escaneados.

Outra forma de escanear mais de um pacote é utilizando o atributo *basePackages*, que permite definir múltiplos pacotes para varredura. 

Se for necessário usar mais de uma anotação *@ComponentScan*, podemos agrupá-las dentro da anotação *@ComponentScans*:
```java
@Configuration
@ComponentsScans({
	@ComponentScan(basePackages = "com.packt.modern.api"),
	@ComponentScan(basePackageClasses = appConfig.class)
})
class AppConfig {
	// Código da configuração
}
```

### The bean's scope
Os **containers do Spring** são responsáveis por criar instâncias dos beans. O modo como essas instâncias são criadas é definido pelo *escopo* do bean. O escopo padrão é #singleton, onde apenas uma instância será criada por contêiner IoC, e a mesma instância será injetada. Se desejarmos criar uma nova instância cada vez que for solicitada, podemos definir o escopo #prototype para o bean. 

Os escopos #singleton e #prototype  estão disponíveis para todas as aplicações baseadas em Spring. Há mais quatro escopos disponíveis para aplicações web: #request, #session, #application e #websocket. Para esses quatro últimos escopos, o contexto da aplicação deve ter suporte a web, como as aplicações web baseadas em Spring Boot.

#Singleton - cria uma nova instância por contêiner IoC. Este é o escopo padrão.
#prototype - cria uma nova instância para cada injeção (para beans colaborativos)
#request - somente para contexto web. Será criada uma única instância de bean para cada requisição HTTP e será válida durante todo o ciclo de vida da requisição HTTP.
#Session - somente para contexto web. Será criada uma única instância de bean para cada sessão HTTP e será válida durante todo o ciclo de vida da sessão HTTP.

### Configurando Beans usando Java
Antes do Spring 3, os beans só podiam ser definidos utilizando XML. Com o Spring 3, foram introduzidas as anotações @Configuration, @Bean, @Import e @DependsOn para configurar beans no Spring utilizando Java.

A anotação *@Import* é especialmente útil ao desenvolver uma aplicação sem utilizar a configuração automática (*autoconfiguration*).

### The *@Import* annotation
*@Import* é utilizada para modularizar configurações quando temos mais de uma classe de configuração. Com ela, podemos importar as definições de beans de outras classes de configuração, o que é útil quando instancia o contexto manualmente. O Spring Boot utiliza configuração automática (auto-configuration), então é necessário usar *@Import*. No entanto, seria necessário usar *@Import* para modularizar as configurações caso queiramos instanciarmos o contexto manualmente. 

Vamos supor que a classe de configuração **FooConfig** contenha o **FooBean** e a classe de configuração **BarConfig** contenha o **BarBean**. A classe **BarConfig** também importa **FooConfig** utilizando *@Import*:
```java
@Configuration
public class FooConfig {
	@Bean
	public FooBean fooBean() {
		return new FooBean();
	}
}
@Configuration
@Import (FooConfig.class)
public class BarConfig {
	@Bean
	public BarBean barBean() {
		return new BarBean();
	}
}
```
Agora, ao instanciar o container (contexto), podemos fornecer apenas a classe BarConfig para carregar as definições de FooBean e BarBean no container do Spring:
```java
public static void main(String[] argts) {
	ApplicationContext appContext = new
		AnnotationConfigApplicationContext (
			BarConfig.class);
	// Get FooBean & BarBean beans from context
	FooBean fooBean =
		appContext.getBean(FooBean.class);
	BarBean barBean =
		appContext.getBean(BarBean.class);
		)
}
```

### The *@DependsOn annotation*
O container do Spring gerencia a ordem de inicialização dos beans. E se você tiver um bean que depende de outro? Vamos querer garantir que o bean necessáriio seja inicializado antes do bean que depende dele. A anotação *@DependsOn* ajuda a alcançar isso quando configuramos beans utilizando Java (e não através de XML).

Receberíamos a exceção *NoSuchBeanDefinitionException* se a ordem de inicialização dos beans estivesse correta, e o container do Spring falhasse ao encontrar a dependência como resultado.

Vamos supor que temos um bean chamado **BazBean** que depende dos beans **FooBean** e **BarBean**. Usamos a anotação *@DependsOn* para garantir que a ordem de inicialização seja mantida. O container do Spring seguirá as instruções e inicializará os beans **FooBean** e **BarBean** antes de criar o **BazBean**. 
```Java
@Configuration
public class AppConfig {
	@Bean
	public FooBean fooBean() {
		return new FooBean();
	}
	@Bean
	public BarBean barBean() {
		return new BarBean();
	}

	@Bean
	@DependsOn({"fooBean}","barBean"})
	public BarBean barBean() {
		return new BazBean();
	}
}
```

### How to code DI
Veja o seguinte exemplo. O **CarService** tem uma dependência de **CarRepository**. A instância do **CartRepository** é criada dentro do construtor do **CartService**:
```java
public class CarService {
	private CarRepository repository;
	public CarService() {
		this.repository = new CarRepositoryImpl();
	}
}
```

Podemos desacoplar essa dependência da seguinte maneira:
```java
public class CarService {
	private CarRepository repository;

	public CarService(CarRepository repository) {
		this.repository = repository;
	}
}
```
Se criarmos um bean da implementação do *CarRepository*, podemos facilmente injetar o bean *CarRepository* usando os metadados de configuração. 

Nós vimos como o #ApplicationContext pode ser inicializado na subseção "The @Import annotation" deste capítulo. Quando ele é criado, ele coleta todos os metadados da configuração dos beans. A anotação *@Import* permite que tenhamos múltiplas configurações. 

Cada bean pode ter suas dependências, ou seja, um bean pode precisar de outros objetos para funcionar (composições), como no exemplo do CarService. Essas dependência podem ser definidas por meio de construtores, métodos setter ou propriedades. Esses objetos dependentes (parte dos construtores, argumentos de métodos setter ou propriedades da classe) são injetados pelo contêiner Spring *ApplicationContext* utilizando a definição do bean e seu escopo. 

---
**Nota**
A Injeção de Dependência (DI) torna uma classe virtualmente independente de suas dependências. Portanto, uma alteração na dependência não afetará a classe (sem necessidade de alteração de código), dede que o contrato da interface não seja quebrado.
Na verdade, podemos alterar a implementação subjacente da dependência ou usar uma classe de implementação diferente. 

---

### Using a constructor to define a dependency
Agora, podemos ver como injetar o CarRepository no construtor CarService. Uma maneira de injetar uma dependência usando o construtor é a seguinte:
```java
@Configuration
public class AppConfig {
	@Bean
	public CarRepository cartRepository() {
		return new CartRepositoryImpl();
	}

	@Bean
	public CartService cartService() {
		return new CartService(cartRepository());
	}
}
```

### Using a setter method to define a dependency
Agora, vamos alterar a classe CartService. Em vez de usar um construtor, vamos utilizar o método setter para instanciar a dependência:

```java
public class CartService {
	private CartRepository repository;
	public void setCartRepository(CarRepository repository) {
		this.repository = repository;
	}
}

```

Agora, podemos usar a seguinte configuração para injetar a dependência:
```java
@Configuration
public class AppConfig {
	@Bean
	public CartRepository cartRepository() {
		return new CartRepository();
	}

	@Bean
	public CartService cartService() {
		CartService service = new CartService();
		service.setCartRepository(cartRepository());
		return service;
	}
}

```

### Using a class property to define a dependency
Usando uma propriedade de classe para definir uma dependência o Spring também oferece uma solução pronta para injeção de dependência usando a anotação *@Autowired*. Isso torna o código mais limpo:
```java
@Serivice
public class CartService {
	@Autowired
	private CartRepository repository; // repos
}
```
O container do Spring se encarregará de injetar o bean CarRepository, logo, repository passa a ter uma instância de um bean que impolementa a interface CartRepository, sem que precisemos instanciá-la manualmente. O Spring faz essa injeção de dependência durante o ciclo de vida do bean, assegurando que a dependência necessária esteja disponível quando o *CartService* for usado. 

## Configuring a bean's metadata using annotations
O Spring Framework oferece várias anotações para configurar os metadados dos beans. No entanto, vamos focar nas anotações mais usadas: *@Autowired, @Qualifier, @Inject, @Resource, @Primary e @Value*.

### How to use @Autowired?
A anotação @Autowired permite que definamos a configuração dentro da própria classe do bean, em vez de escrever uma classe de configuração separada anotada com *@Configuration*. 

A anotação *@Autowired* pode ser aplicada a um campo (como vimos no exemplo de DI baseado em propriedades de classe), construtor, setter ou qualquer método.

O container do Spring faz uso de reflexão para injetar os beans anotados com *@Autowired*. Isso também torna esse processo mais custoso em comparação com outras abordagens de injeção.
Vale ressaltar que aplicar *@Autowired* a membros da classe funcionará apenas se não houver um construtor ou método setter para injetar o bean dependente.

**Exemplo Simples**
Imagine que tenhamos uma classe **CarService** que depende de um **Engine** para funcionar. Teríamos que criar uma instância do **Engine** manualmente dentro de **CarService**:
```java
public class CarService {
	private Engine engine;

	public CarService() {
		this.engine = new Engine();
	}
}
```

**Exemplo com @AuatoWired**
Como *@Autowired*, não precisamos instanciar manualmente o **Engine** dentro do **CarService**. O Spring cuida disso por você, e a dependência (no caso, **Engine**) é injetada automaticamente.


```java
import org.springframework.stereotype.Component;

@Component // Indica que o spring deve gerenciar essa classe como um bean
public class Engine {
	public void start() {
		System.out.println("Engine is runnning...");
	}
}
```

```java
@Service // a classe é um serviço gerenciado pelo Spring
public class CarService {
	@Autowired // Spring vai injetar a dependência de Engine automaticamente
	private Engine engine; 

	public void startCar() {
		engine.start();
		System.out.println("Car is ready to go!");
	}
}
```
Neste exemplo, o Spring vai procurar um bean do tipo Engine no contexto e irá automaticamente injetá-lo no campo *Engine*. Esse processo é chamado de **injeção de dependência**. 

```java
@SpringBootApplication
public class Main implements CommandLineRunner {

	@Autowired // Spring irá injetar o CarService automaticamente
	private CarService carService;

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		carService.startCar(); // Chama o método startCar que uso o Engine
	}
}
```

### Writing code for AOP
Discutimos AOP anteriormente na seção "Compreendendo os padrões e paradigmas do Spring". Em termos simples, AOP é um paradigma de programação que resolve preocupações transversais, como logging, transações e segurança. Essas preocupações transversais são conhecidas como aspectos em AOP. Elas permitem que modularizemos nosso código e centralizemos as preocupações transversais em um único local. 

```java
class Test
	public void performSomeTask() {
		long start = System.currentTimeMillis();

		long executionTime =
			System.currentTimeMillies()  - start;
		System.out.println("Time taken: " + executionTime + "ms");
	}
```


## Why use Spring Boot?
 Hoje em dia, o Spring Boot é a escolha óbvia para o desenvolvimento de aplicações web de ponta e prontas para produção, específicas para o Spring. O seu site também destaca suas enormes vantagens. 

O Spring Boot é uma ferramente incrível do Spring criada pela Pivotal, que foi lançada para Disponibilidade Geral em abril de 2014. O Spring Boot foca em se tornar um contêiner ultraleve.

O Spring Boot possui suas próprias configurações padrão e também oferece suporte à configuração automática para simplificar o desenvolvimento de aplicações web prontas para produção. O Spring Intializr é um serviço baseado na web onde podemos escolher nossas ferramentas build, como **Maven** ou **Gradle**, junto com os metadados do projeto, como o grupo, artefatos e quaisquer dependências necessárias. 

A opção de empacotamento do projeto padrão é #Jar, no entanto, podemos optar pelo empacotamento #War (arquivo de web application) caso desejamos implantar a aplicação em um servidor web, como #WebLogic ou #Tomcat.

Em termos simples, o **Spring Initializr** realiza toda a configuração para que possamos focar na escrita da lógica de negócio e das APIs.

---
**Compilando e executando o código**
Podemos compilar o código executando o comando:
```bash
gradlew clean build
```

## Understanding the importance of servlet dispatcher
Aprendemos que os serviços web RESTful são desenvolvidos sobre o protocolo HTTP. O Java possui a funcionalidade de #Servlets para trabalhar com HTTP. 

#Servlets permitem mapeamento de caminhos que podem funcionar como endpoints REST e oferecem métodos HTTP para identificação. Além disso, permitem a criação de diferentes tipos de objetos de resposta, incluindo JSON e XML. No entanto, servlets oferecem uma forma um tanto rudimentar de implementar endpoints REST, já que é necessário lidar manualmente com a URI da solicitação, analisar os parâmetros e converter JSON/XML nas respostas.

O Spring MVC surge como uma solução para isso. O Spring MVC é baseado no padrão **Model-View-Controller (MVC)** e faz parte do **Spring Framework** desde a sua primeira versão. O MVC é um padrão de design bem conhecido:
- #Model (Modelo): os modelos são objetos Java (também chamados de #POJO) que contêm os dados da aplicação. Eles também representam o estado da aplicação.
- #View (Visão): a visão é a camada de apresentação composta por arquivos HTML, JSP ou templates. Ela renderiza os dados dos modelos e gera a saída em HTML.
- #Controller (controlador): o controlador processa as solicitações do usuário e constrói o modelo.

O #DispatcherServlet faz parte do Spring MVC. Ele atua como um **front controller**, ou seja, lida com todas as solicitações HTTP recebidas. O Spring MVC é um framework web que permite desenvolver aplicações web tradicionais, onde a interface do usuário (UI) também faz parte do backend. No entanto, ao desenvolver serviços web RESTful, com a interface de usuário baseada na biblioteca JavaScript #React, o papel do #DispatcherServlet será limitado à implementação dos endpoints #REST usando #RestController.

Segue o fluxo de uma solicitação de usuário no Spring MVC para um controlador REST:
1. O usuário envia uma solicitação HTTP, que é recebida pelo #DispatcherServlet;
2. O #DispatcherServlet repassa o controle para o #HandlerMapping. O #HandlerMapping encontra o controlador correto para o URI solicitado e o retorna ao #DispatcherServlet;
3. O #DispatcherServlet utiliza o #HandlerAdapter para lidar com o #Controller;
4. O #HandlerAdapter chama o método apropriado dentro do #Controller;
5. O #Controller executa a lógica de negócio associada e forma a resposta.
6. O Spring realiza o processo de #marshaling #unmarshaling para converter os objetos da solicitação e resposta entre #JSON e objetos Java, e vice-versa.

![[Chapter 2 - Sring Concepts and REST APIs-1.png]]
O #DispatcherServlet se destaca como figura central, orquestrando a dança intricada de componentes envolvidos no tratamento de solicitações recebidas. Este servlet desempenha um papel fundamental em garantir que cada solicitação seja direcionada ao controlador apropriado, permitindo a execução da lógica de negócios e a geração de uma resposta significativa. 

1. **Ponto de entrada para solicitações**:
No coração de um aplicativo web Spring Boot está o #DispatcherServlet. Ao receber uma solicitação HTTP, esse servlet atua como o #gateway, interceptando a chamada de entrada e iniciando o processo de tratamento da solicitação. Ele serve como o hub central para <span style="background:#d4b106">gerenciar o fluxo de solicitações e respostas</span>. 

2. **Contexto do aplicativo da Web**
O #DispatcherServlet opera dentro do contexto do Spring. Este contexto é essencialmente um contêiner para gerenciar beans, incluindo controladores, serviços e outros componentes. O servlet usa este contexto para localizar e invocar os componentes apropriados para manipular a solicitação de entrada.

3. **Mapeamento do manipulador: Determinando o manipulador**
Uma das responsabilidades cruciais do #DispatcherServlet é consultar o #HandlerMapping. Esse mapeamento é responsável por determinar qual controlador (handler) deve processar a solicitação de entrada com base em fatores como URL da solicitação, método da solicitação ou outros parâmetros.

4. **Adaptador de manipulador: preenchendo a lacuna da interface**
Controladores em um aplicativo Spring podem ter assinaturas de métodos diferentes. O #HandlerAdapter entra em cena para preencher a lacuna entra as interfaces variáveis dos controladores. Ele garante que o manipulador escolhido possa processar efetivamente a solicitação, adaptando-a a uma interface padronizada, permitindo uma abordagem unificada para o tratamento de solicitações. 
https://medium.com/@lakshyachampion/the-dispatcherservlet-the-engine-of-request-handling-in-spring-boot-3a85c2bdbe6b

