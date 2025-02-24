*This Chapter covers*
- Using the singleton bean scope;
- Using eager and lazy instantiation for singleton beans
- Using the prototype bean scope


Thus far we have discussed several essential things about object instances managed by Spring (beans). We covered the important syntaxes you need to know to create beans, and we discussed establishing relationships among beans (including the necessity of using abstractions). But we didn't focus on how and when Spring creates the beans. From this perspective, we've only relied on the framework'k default approaches.

I chose not to discuss this aspect earlier in the book because I wanted you to focus on the syntaxes you'll need up-front in your projects. However, production app's scenarios are complex, and sometimes relying on the framework's default behavior is not enough. For this reason, in this chapter we need to go a bit deeper with our discussion on how Spring manages the beans in its context.

Spring has multiple <span style="background:#d4b106">different approaches for creating beans and managing their life cycle</span>, and in the Spring world we name these approaches *scopes*. In this chapter, we discuss two scopes you'll often (frequência) find in Spring apps: *singleton* and *prototype*. 

**NOTE** Later, in chapter 9, we discuss three more bean scopes that apply to web applications: *request*, *session*, and *application*.

#Singleton is the default scope of a bean in Spring, and it's what we've been using up to now. We'll deal first with how Spring manages singleton beans and then discuss essential things you need to know about using the singleton scope in real-world apps.  

In section 5.2, we continue by discussing the prototype bean scope. Our focus will be on how the prototype scope is different from singleton and real-world situations in which you'd need to apply one or another.

## 5.1 Using the singleton bean scope
The singleton bean scope defines Spring's default approach for managing the beans in its context. It is also the bean scope you'll most encounter in production apps.

In section 5.1.1, we start our discussion by learning how Spring creates and manages singleton beans, which is essential for understanding where you should use them. 

For this purpose, we'll take two examples that employ the different approaches you can use to define beans (which you learned in chapter 2) and analyze Spring's behavior for these beans. 

We'll then discuss (in section 5.1.2) the critical aspects of using singleton beans in real-world scenarios. We end this section by discussing two singleton bean instantiation approaches (eager and lazy) and where you should use them in production apps.

### 5.1.1 How singleton beans work
Vamos começar com o comportamento do Spring para gerenciar beans com escopo #singleton. É importante saber o que esperar ao usar esse escopo, especialmente porque singleton é o escopo de bean padrão (e o mais utilizado) no Spring. Nesta seção, descreverei a ligação entre o código que escrevemos e o contexto do Spring para tornar o comportamento do Spring fácil de entender. Em seguida, testaremos o comportamento com alguns exemplos.

O spring cria um *singleton bean* quando carrega o contexto e atribui a ele um nome (às vezes também chamado de **bean ID**).<span style="background:#d4b106"> Chamamos esse escopo de singleton porque sempre obtemos a mesma instância ao referenciar um determinado bean</span>. Mas devemos ter cuidado! É possível ter várias instâncias do mesmo tipo no contexto do Spring, desde que tenham nomes diferentes.

Se você conhece o *Singleton pattern*, o funcionamento no Spring pode parecer estranho, pois nele geralmente temos apenas uma instância de um tipo na aplicação. Para o Spring, o conceito de *singleton* permite múltiplas instâncias do mesmo tipo, onde *singleton* significa **único por nome**, mas não único por aplicação.

Logo, o Singleton Pattern é um design pattern que garante que uma classe tenha apenas uma instância e fornece um ponto de acesso global a essa instância. Isso é particularmente útil em situações onde a criação de múltiplas instâncias de uma classe seria ineficiente ou desnecessária. 

Por padrão, todos os beans no Spring são singleton. Ou seja, uma única instância do bean é criada e compartilhada em todo o contêiner Spring.

Uma única instância significa menos uso de recurso de memória.
Simplicidade na configuração e gerenciamento de beans.
Garante que todas as solicitações ao bean compartilhem a mesma instância, mantendo consistência. 

Portanto, o padrão garante que uma classe tenha apenas uma instância e fornece um ponto de acesso global a essa instância. Isso significa que, para uma classe específica que segue o padrão Singleton, haverá apenas uma instância dessa classe em toda a aplicação. O nome *singleton* reflete justamente a esse conceito de **único**.

Podemos ter várias classes diferentes implementando o padrão Singleton na mesma aplicação. Por exemplo, podemos ter um bean de configuração, um de conexão com o banco de dados... cada um desses beans seria único dentro do seu próprio contexto.

![[The Spring context - Bean scopes and life cycle.png]]

- **Singleton Pattern:** com o padrão Singleton, a classe gerencia a criação da instância e garante que <span style="background:#d4b106">apenas uma instância de um tipo seja criada</span>.

```java
public class CommentService {
	public static CommentService getInstance() {
		if (instanceHasNotYetBeenCreated()) {
			createCommentServiceInstance();
		}

		return commentService;
	}
}
```

- **Spring's singleton scope:** com o Spring, podemos definir tantos beans do mesmo tipo quanto precisarmos, utilizando métodos anotados com *@Bean* na classe de configuração. Cada um desses beans é um singleton. Logo, podemos ter várias instâncias da mesma classe no contexto do Spring, se a instâncias tiverem nomes diferentes.

```java
@Configuration
public class ProjectConfig {
	@Bean
	public CommentService commentService1() {
		return new CommentService();
	}

	@Bean
	public CommentService commentService2() {
		return new CommentService();
	}

	@Bean
	public CommentService commentService3() {
		return new CommentService();
	}
}

```

---
**Um pouco mais de explicação**
- **Singleton Pattern**
É um padrão de projeto da #GoF (Gang of Four) que garante que uma classe tenha *apenas uma instância* na aplicação e fornece um ponto global de acesso a ela.

*Implementação*: geralmente, é implementado com um **construtor privado** e um método estático que retorna a única instância da classe.

*Controle:* o controle da instância é feito manualmente pelo próprio código da aplicação.

*Escopo:* a instância única é válida **para toda a JVM**.

*Uso comum:* classes que representam recursos globais, como gerenciadores de conexões ou cache.
```java
public class Singleton {
	private static Singleton instance;

	private Singleton() {} // construtor privado

	public static Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}
}
```

- **Spring Singleton Scope**
*Definição:* no Spring, um **bean singleton** significa que há apenas uma instância do bean por contexto do Spring, e não por aplicação inteira.
*Gerenciamento:* o próprio Spring gerencia a instância e injeta a mesma instância sempre que o bean for solicitado.
*Escopo:* a instância única é válida dentro do contexto do Spring, mas não para toda a JVM. Se houver múltiplos contextos do Spring na mesma aplicação, cada um pode ter sua própria instância do **singleton bean**. 
*Criação:* o Spring cria automaticamente um *bean singleton* quando o contexto é carregado, e ele fica disponível durante todo o ciclo de vida do contexto.
*Uso:* evita a necessidade de implementação manual do padrão **Singleton** dentro da classe.
```java
@Configuration
public class AppConfig {

	@Bean
	public MyService myService() {
		return new MyService();
	}

}
```

---

**Declaring Singleton-Scoped Beans With @Bean**
Vamos demonstrar o comportamento de um singleton bean com um exemplo, utilizando a anotação *@Bean* para adicionar uma instância ao contexto do Spring e, em seguida, <span style="background:#d4b106">referenciá-la múltiplas vezes na classe main. </span>Fazemos isso para provar que <span style="background:rgba(205, 244, 105, 0.55)">obtemos na mesma instância sempre que referenciamos o bean</span>.


![[The Spring context - Bean scopes and life cycle-1.png]]

A figura acima é uma representação do código próximo do contexto que configura o que precisamos obter.
O grão de café na visualização representa a instância que o Spring adiciona ao seu contexto. Observe que o contexto contém apenas uma instância (grão de café) com um nome associado *comentService()*. Como já discutimos no capítulo 2, ao usar a abordagem de anotação *@Bean* para adicionar um bean ao contexto, o nome do método anotado com *@Bean* torna-se o nome do bean.

Neste exemplo, estamos utilizando a abordagem de anotação @Bean para adicionar o bean ao contexto do Spring, mas, um Singleton, também pode ser criado utilizando anotações estereotipadas como *@Componenet* para adicionar o bean ao contexto. 

Utilizaremos o nome do bean para obtê-lo do contexto Spring nessa demonstração. Como já aprendemos lá no capítulo 2, quando temos apenas um bean de um tipo no Spring Context, não precisar mais usar seu nome para chamá-lo. Podemos obter esse bean pelo seu tipo. Neste exemplo, usamos o nome simplesmente para garantir que estamos nos referindo ao mesmo bean. 

Vamos escrever o código e executá-lo para concluir este exemplo. Vamos precisar de: 1) uma classe *CommentService* vazia; 2) classe de configuração; 3) classe principal.

```java
@Configuration
public class ProjectConfig {
	@Bean
	public CommentService commentService() {
		return new CommentService();
	}
}

public class Main {
	public static void main(String[] args) {
		var c = new AnnotationConfigApplicationContext(ProjectConfig.class);
		CommentService cs1 = c.getBean("coommentService", CommentService.class);
		CommentService cs2 = c.getBean("commentService", CommentService.class);

		boolean x = cs1 == cs2;

		System.out.println(x);
	}
}
```

Running the app will print "true" in the console because, being a singleton beam, Spring returns the same reference every time;

**Declaring Singleton Bean Using Stereotype Annotations**
As mentioned earlier, Spring's behavior for singleton bean isn't any different when using stereotype annotation than when you declared them with the @Bean annotation. But in this section, i'd like to enforce this statement with an example.

Consider a class design scenario where two service classes depend on a repository. Say we have both *CommentService* and *UserService* depending on a repository name *CommentRepository*:

![[The Spring context - Bean scopes and life cycle-2.png]]

The reason these classes are dependent uma das outras não importante, e nossos serviços não farão nada (é apenas um cenário). Assumimos que este design de classe faz parte de um aplicativo mais complicado, e focamos na relação entre os beans e como o Spring estabelece os links em seu contexto. A figura 5.4 é uma representação visual do contexto próximo ao código o configura.

Let's prove this behavior by creating the three classes and comparing the references Spring injects in the service beans.. Spring injects the same reference in both service beans. In the following code snippet, you find the definition of the *CommentRepository class*...

The next code snippet presents the definition of the *CommentService* class. Observe that I used *@Autowired* to instruct Spring to inject an instance of type *CommentRepository* in an attribute declared in the class. I also defined a getter method that I intend to user later to prove Spring injects the same object reference in both service beans:

[[Spring Start Here/Capítulo 5 - The Spring context Bean Scopes and life cycle/sq-ch5-ex21/src/main/java/org/example/sqch5ex1/comment/services/CommentService.java|CommentService]]
[[UserService.java]]

Both service classes...

Unlike the first example in this section, the configuration class remain empty in this project. We only need to tell Spring where to find the classes annotated with stereotype annotations. As discussed in chapter 2, to tell Spring where to find classes annotated with stereotype annotations we use the @ComponentScan annotation and use the argument *basePackages* for provides the name of stereotype classes that use the annotation in top of class name. 

[[Spring Start Here/Capítulo 5 - The Spring context Bean Scopes and life cycle/sq-ch5-ex21/src/main/java/org/example/configuration/ProjectConfig.java|ProjectConfig]]

Because the dependency (CommentRepository) is singleton, both services contain the same reference, so this line always prints "true";

## 5.1.2 Singleton beans in real-world scenarios
Thus far we've discussed how Spring manages singleton beans. It's time to also discuss things you need to be aware of when working with singleton beans. Let's start by considering some scnearios where you should or shoudn't use singleton beans.

Como o escopo do singleton bean assume que múltiplos componentes do aplicativo podem compartilhar uma instância de objeto, <span style="background:#d4b106">a coisa mais importante a considerar é que esses beans devem ser imutáveis</span>. Na maioria das vezes, um aplicativo do mundo real executa ações em múltiplas threads (por exemplo, qualquer aplicativo web). Em tal cenário, múltiplas threads compartilham a mesma instância de objeto. Se essas threads alterarem a instância, você encontrará um cenário de condição de corrida.

Uma condição de corrida *race-condition* é uma situação que pode ocorrer em arquiteturas multithread quando múltiplas threads tentam alterar um recurso compartilhado. Em caso de uma condição de corrida, o desenvolvedor precisa sincronizar adequadamente as threads para evitar resultados de execução inesperados ou erros.

Se você deseja obter singleton beans mutáveis (cujos atributo mudam), você precisa tornar esses beans concorrentes por conta própria (principalmente empregando a sincronização de threads). Mas singleton beans não são projetados para serem sincronizados. Eles são comumente usados para definir o design de classe backbone de um aplicativo e delegar responsabilidades uns aos outros. Tecnicamente, a sincronização é possível, mas não é uma boa prática. Sincronizar a thread em uma instância concorrente pode afetar drasticamente o desempenho do aplicativo. Na maioria dos casos, encontraremos outros meios de resolver o mesmo problema e evitar a concorrência de threads.

![[Chapter 5 -The Spring context - Bean scopes and life cycle.png]]

No capítulo 3, foi mencionado que a injeção de dependência via construtor é uma boa prática e preferível à injeção de campo? Uma das vantagens da injeção de construtor é que ela permite tornar a instância imutável (definir os campos do bean como final). No nosso exemplo anterior, podemos aprimorar a definição da classe *CommentService* substituindo a injeção de campo pela injeção via construtor. Um design melhor da classe, seria:
[[Spring Start Here/Capítulo 5 - The Spring context Bean Scopes and life cycle/sq-ch5-ex21/src/main/java/org/example/sqch5ex1/comment/services/CommentService.java|CommentService]]

Tornar a instância imutável tem vantagens:
1. **Thread Safety**: instâncias imutáveis são inerentemente seguras para serem compartilhadas entre múltiplas threads, <span style="background:rgba(205, 244, 105, 0.55)">pois seus estados não podem ser alterados após a criação</span>. Isso elimina a necessidade de sincronização explícita, reduzindo o risco de condições de corrida. #race-condition
2. **Simplicidade e Previsibilidade:** objeto imutáveis são mais fáceis de entender e prever, já que seu estado não muda. Isso facilita o rastreamento do fluxo de dados e da lógica do programa.
3. **Evita Erros Acidentais:** com a imutabilidade, podemos evitar erros acidentais que podem ocorrer devido a modificações inesperadas nos objetos. Uma vez que o objeto é criado, sabemos que ele permanecerá o mesmo.
4. **Facilidade de Testes:** objetos imutáveis são mais fáceis de testar, já que eles não possuem efeitos colaterais. Podemos criar instâncias e ter certeza de que elas não serão alteradas durante os testes.
5. **Caching e Reuso:** como instância imutáveis não mudam, elas podem ser facilmente armazenadas em cache e reutilizadas, melhorando o desempenho do aplicativo.

*private **final** CommentRepository commentRepository;*
Marcar o campo final destaca que esse campo não pode ser intencionalmente modificado. 

### 5.1.3 Using eager and lazy instantiation

**Resume**
Singleton é um padrão de projeto (design pattern) que garante que uma classe tenha apenas uma única instância durante toda a execução do programa e fornece um ponto de acesso global a essa instância.

No Spring, os beans são singleton por padrão, ou seja, o framework já implementa o padrão Singleton automaticamente para nós. Logo, quando injetamos um bean, ele continua sendo a mesma instância em todos os locais da nossa aplicação.

---
Na maioria dos casos, o Spring cria todos os singleton beans quando inicializa o contexto - este é o comportamento padrão do Spring. 