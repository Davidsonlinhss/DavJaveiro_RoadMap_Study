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

**Declaring Singleton-Scoped Beans With @Bean**
Vamos demonstrar o comportamento de um singleton bean com um exemplo, utilizando a anotação *@Bean* para adicionar uma instância ao contexto do Spring e, em seguida, referenciá-la múltiplas vezes na classe main. Fazemos isso para provar que obtemos na mesma instância sempre que referenciamos o bean.

