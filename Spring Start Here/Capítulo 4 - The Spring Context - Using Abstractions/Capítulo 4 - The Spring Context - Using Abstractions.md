*This chapter covers*
- Using interfaces to define contracts;
- Using abstractions for beans in the Spring context;
- Using dependency injection with abstractions.

Neste capítulo, discutiremos o uso de abstração com Spring beans. Este tópico é essencial pois, em projetos do mundo real, muitas vezes usamos abstrações para desacoplar implementações. Como aprenderemos neste capítulo, garantimos que nossa aplicação seja fácil de manter e testar ao desacoplar implementações.

Começaremos com uma revisão de como usar interfaces para definir contratos na seção 4.1. Para abordar esse assunto, começamos discutindo as responsabilidades dos objetos e descobrimos como elas se encaixam em um design de classe padrão de um aplicativo. Usaremos nossas habilidades de codificação para implementar um pequeno cenário no qual não usamos Spring, mas focamos na implementação de um requisito e no uso de abstrações para desacoplar os objetos dependentes do aplicativo.

Em seguida, discutimos o comportamento do Spring ao usar injeção de dependência (DI) com abstrações na seção 4.2. Começaremos a partir da implementação trabalhada na seção 4.1 e adicionaremos o Spring às dependências do aplicativo. 

## 4.1 Using interfaces to define contracts
Nesta seção, discutimos o uso de interface para definir contratos. Em Java, a interface é uma estrutura abstrata usada para declarar uma responsabilidade específica. Um objeto que implementa a interface precisa definir essa responsabilidade. Mais objetos que implementam a mesma interface podem definir a responsabilidade declarada por essa interface de maneiras diferentes. Podemos dizer que a interface específica o "que precisa acontecer", enquanto cada objeto que implementa a interface especifica o "como isso deve acontecer".

Quando eu era criança, meu pai me deu um rádio antigo que eu poderia desmontar e brincar (eu era bastante entusiasmado com a ideia de desmontar coisas). Olhando para ele, percebi que precisava de algo para desparafusar os parafusos da carcaça. Depois de pensar um pouco, decidi que poderia usar uma faca para esse trabalho, então pedi uma faca ao meu pai. Ele me perguntou: "Para que você precisa de uma faca?" Eu disse que precisava dela para abrir a carcaça. "Oh!", disse ele. "É melhor você usar uma chave de fenda; aqui está!" Naquela época, aprendi que é sempre mais inteligente pedir o que você precisa, em vez de pedir uma solução quando você não tem ideia do que está fazendo. As interfaces são a maneira como os objetos pedem o que precisam.

### 4.1.1 Using interfaces for decoupling implementations
Essa seção discute o que são contratos e como podemos defini-los em um aplicativo Java usando *interfaces*. Vamos começar com uma analogia. 

**Analogia**: suponha que usemos um aplicativo de compartilhamento de viagens porque precisamos ir a algum lugar. Quando solicitamos uma viagem, geralmente não nos importamos com a aparência do carro ou quem é o motorista. Só precisamos chegar a algum lugar. No meu caso, não me importo se um carro ou uma nave espacial vem me buscar, desde que eu chegue ao destino a tempo. O aplicativo de carona é uma interface. O cliente não solicita um carro ou um motorista, mas uma viagem. Qualquer motorista com um carro que possa oferecer o serviço pode responder à solicitação do cliente. O cliente e o motorista estão desacoplados pelo aplicativo (interface); o cliente não sabe quem é o motorista nem qual carro irá buscá-lo antes que um carro responda à sua solicitação, e o motorista não precisa saber para quem está prestando o serviço. Usando essa analogia, podemos deduzir o papel das interfaces em relação aos objetos em Java.  

Suponhamos que implementemos um objeto que precisa imprimir os detalhes dos pacotes a serem entregues para um aplicativo de transporte. Os detalhes impressos devem ser ordenados pelo endereço de destino. O objeto que lida com a impressão dos detalhes precisa delegar a outro objeto a responsabilidade de classificar os pacotes pelos endereços de entrega. 
![[Capítulo 4 - The Spring Context - Using Abstractions.png]]

Como mostrado na figura 4.1, o *DeliveryDetailsPrinter* delega diretamente a responsabilidade de classificação ao objeto *SorterByAddress*. Se mantivermos esse design de classe, podemos enfrentar dificuldades posteriormente, caso precisemos alterar essa funcionalidade. Vamos imaginar que precisemos mudar a ordem dos detalhes impressos, e a nova ordem seja pelo nome do remetente. Precisaríamos substituir o objeto *SorterByAddres* por outro que implemente a nova responsabilidade, mas também teria que alterar o objeto DeliveryDetailsPrinter que utiliza a responsabilidade de classificação.

```java
public class DeliveryDetailsPrinter {
	private SorterByAddres sorter;

	public DeliveryDetailsPrinter(SorterByAddres sorter) {
		this.sorter = sorter;
	}

	public void printDetails() {
		sorter.sortDetails();
		// printing the delivery details
	}
}
```

- Pelo fato dos dois objetos estarem fortemente acoplados, se quisermos mudar a responsabilidade de classificação, precisaríamos alterar o objeto que utiliza essa responsabilidade. Um design melhor permitiria que mudássemos apenas a responsabilidade de classificação sem mudar o objeto que utiliza essa responsabilidade.


**Como podemos melhorar esse design?**
Ao mudar a responsabilidade de um objeto, queremos evitar a necessidade de mudar outros objetos que utilizam a responsabilidade alterada. O problema deste design ocorre porque o objeto *DeliveryDetailsPrinter* especifica tanto o que precisa quanto como precisa. Como discutido anteriormente, um objeto só precisa especificar o que precisa e permanecer completamente alheio ao como o "o que" é implementado. Fazemos isso, é claro, utilizando interfaces. Vamos criar uma interface chamada *Sorter* para desacoplar os dois objetos. Agora podemos ter quantos objetos quisermos para resolver o "o que" solicitado pelo DeliveryDetailsPrinter. Qualquer objeto que implemente a interface *Sorter* pode satisfazer a dependência do objeto **DeliveryDetailsPrinter** a qualquer momento. 
![[Capítulo 4 - The Spring Context - Using Abstractions-1.png]]
Com o uso da interface, podemos implementar mais de um objeto utilizando a mesma interface. 

A definição da interface *Sorter*:
```java
public interface Sorter {
	void sortDetails();
}
```
Veja a figura 4.4 e comparte com a figura 4.2. Como o objeto **DeliveryDetailsPrinter** depende da interface em vez da implementação diretamente, não precisamos alterá-lo novamente se mudar a forma como os detalhes da entrega são ordenados.

```java
public class DeliveryDetailsPrinter {
	private Sorter sorter;

	public DeliveryDetailsPrinter(Sorter sorter) {
		this.sorter = sorter;
	}

	public void printDetails() {
		sorter.sortDetails();
		// printing the delivery details
	}
}
```

Agora podemos usar qualquer implementação da interface *Sorter* e não precisamos mais alterar o objeto que utiliza essa responsabilidade.

 Qual essa introdução teórica, agora entendemos o motivo de usarmos interfaces para desacoplar os objetos que dependem uns dos outros no design de classes. A seguir, implementamos um requisito para um cenário. Vamos implementar esse requisito usando Java puro, sem nenhum framework, e vamos focar nas responsibility dos objetos e no uso de interfaces para desacoplá-los. Ao final desta seção, teremos um projeto definindo alguns objetos que colaboram para implementar um caso de uso. 

Na seção 4.2, mudaremos o projeto e adicionaremos o Spring para gerenciar os objetos, bem como as relações entre eles, com injeção de dependência. Ao adotar essa abordagem passo a passo, observaremos mais facilmente as mudanças necessárias para adicionar o Spring a uma aplicação, bem como os benefícios que acompanham essa mudança. 

### 4.1.2 The requirement of the scenario
Até agora, usamos exemplos simples e escolhemos objetos simples (como Parrot). Mesmo que eles não estejam próximos do que uma aplicação pronta para produção utiliza, eles nos ajudam a focarmos nas sintaxes que precisamos aprender. Agora é hora de dar um passo mais adiante e usar o que aprendemos nos capítulos anteriores com um exemplo mais próximo do que acontece no mundo real.

Suponhamos que estejamos implementando um aplicativo que uma equipe usa para gerenciar suas tarefas. Um dos recursos do aplicativo é permitir que os usuários deixem comentários nas tarefas. Quando um usuário pública um comentário, ele é armazenado em algum lugar (por exemplo, em um banco de dados) e o aplicativo envia um e-mail para um endereço específico configurado no aplicativo.

Precisamos projetar os objetos e encontrar as responsabilidades e abstrações corretas para implementar este recurso. 

### 4.1.3 Implementing the requirement without using a framework
Vamos focar na implementação do requisito descrito na seção 4.1.1. Faremos isto usando o que aprendemos sobre interface até agora. Primeiro, precisamos identificar os objetos (responsabilidades) a serem implementados. 

Em aplicações reais, geralmente nos referimos aos objetos que implementam casos de uso como *services* e é isto que faremos aqui. Precisaremos de um serviço que implemente o caso de uso *"publicar comentário"*. Vamos nomear este objeto de **CommentService**. Eu prefiro dar aos serviços nomes que terminem com Service para que seu papel no projeto fique evidente. Para mais detalhes sobre boas práticas de nomenclatuura, leia o capítulo 2 do livro Clean Code: A handbook of Agile Software Craftsmasnship de Robert C. Martin.

---
- #Service - Quando nos referimos a *Service* em desenvolvimento de software, estamos nos referindo sobre uma **classe ou um objeto** <span style="background:#d4b106">que oferece uma funcionalidade específica dentro do sistema</span>. Um serviço encapsula uma lógica de negócio ou uma funcionalidade e fornece uma interface para que outras partes do sistema possam interagir com ele. A ideia é separar responsabilidades, facilitando a manutenção e a evolução do código.
- Por exemplo, o *CommentService* é uma classe que encapsula a lógica relacionada ao processo de publicação de comentários. Ele pode interagir com outras partes do sistema, como o banco de dados para armazenar comentários ou um serviço de e-mail para enviar notificações. 

---

Ao analisar o requisito novamente, observamos que o caso de uso consiste em duas ações: armazenar o comentário e enviar o comentário por e-mail. Como são bastante diferentes entre si, consideramos essas ações como duas responsabilidades distintas e, portanto, precisamos implementar dois objetos diferentes. 

Quando temos um objeto que trabalha diretamente com um banco de dados, geralmente nomeamos esse objeto de *Repository.* Às vezes, encontramos objetos referidos como *objetos de acesso a dados DAO*. Vamos implementar a responsabilidade de armazenar comentários como *CommentRepository*.

Por fim, em um aplicativo do mundo real, ao implementar objetos cuja responsabilidade é estabelecer comunicação com algo fora do aplicativo, nomeamos esses objetos como *proxies*. Portanto, vamos nomear o objeto cuja responsabilidade é enviar e-mail de *CommentNotificationProxy*. 

![[Capítulo 4 - The Spring Context - Using Abstractions-2.png]]

Mas não dissemos que não deveríamos usar acoplamento direto entre implementações? Precisamos garantir que desacoplamos as implementações usando interfaces. No final, o *CommentRepository* pode atualmente usar um banco de dados para armazenar os comentários. Mas no futuro, talvez isso precise ser alterado para usar alguma outra tecnologia ou um serviço externo. Podemos dizer o mesmo para o objeto *CommentNotificationProxy*. Agora ele envia a notificação por e-mail, mas talvez em uma versão futura a notificação do comentário precise ser enviada por algum outro canal. Certamente queremos garantir que desacoplamos o *CommentService* das implementações de suas dependências para que, quando precisarmos mudar as dependências, não precisemos mudar o objeto que as utiliza também.

A figura abaixo mostra como desacoplar esse design de classe usando abstrações. Em vez de projetar *CommentRepository* e *CommentNotificationProxy* como classes, projetamos essas funcionalidades como interfaces que podemos implementar para definir a funcionalidade.

Estrutura do projeto de forma desaclopada:
![[Capítulo 4 - The Spring Context - Using Abstractions-3.png]]

Para tornar a estrutura do projeto fácil de entender, organizamos o projeto distribuindo as responsabilidades em pacotes separados.

Uma coisa que não foi mencionada anteriormente (para permitir que nos concentremos nas principais responsabilidades do aplicativo) é que também precisaremos representar o comentário de alguma forma. Precisamos escreve uma pequena classe #POJO para definir o comentário. Começamos a implementação do caso de uso escrevendo essa classe #POJO. A responsabilidade desse tipo de objeto é simplesmente modelar os dados que o aplicativo utiliza, e o chamamos de **model**. Consideraremos um comentário que tem dois atributos: um <span style="background:#d4b106">texto e um autor</span>. Vamos criar um pacote **model** no qual definimos uma classe **Comment**. A listagem a seguir apresenta a definição desta classe.

**NOTA:** A POJO is a simple object without dependencies, only described by its attributes and methods. In our case, the *Comment* class defines a POJO describing the details of a comment by its two attributes: author and text.

```java
public class Comment {
	private String author;
	private String text;

	// Omitted getters and setters
}
```

Agora podemos definir as responsabilidades de *repository* e *proxy*. Na próxima listagem, podemos ver a definição da interface *CommentRepository*. O contrato definido por esta interface declara o método *storeComment(Comment comment)*, que o objeto *CommentService* precisa implementar no caso de uso. Armazenamos esta interface e a classe que a implementa no pacote *repositories* do projeto. 

[[CommentRepository.java]]

A interface fornece apenas o que o objeto *CommentService* precisa para implementar em caso de uso: armazenar um comentário. Quando definimos um objeto que implementa este contrato, ele precisa sobrescrever o método *storeComment(Comment comment)* para definir como usar. A classe DBCommentRepository faz o uso da interface. Escreveremos apenas um texto no console para simular a função de conectar-se ao banco de dados.,

Agora, podemos implementar o objeto em si com as duas dependências do objeto CommentService (o CommentRepository e o ComentNotificationProxy).
Definindo duas dependências como atributos da classe
public class CommentService {
	private final CommentRepository commentRepository;
	private final CommentNotificationProxy commentNotificationProxy;
}
public commentService(CommentRepository commentRepository, CommentNotificationProxy commentNotificationProxy) {
	this.commentRepositroy = commentRepository;
	this.commentNotificationProxy = commentNotificationProxy;
}
________________________________________
Por qual motivo não passamos as implementações concretas para a classe CommentService?
É para aderirmos ao Princípio da Inversão de Dependência DIP, um dos princípios #SOLID.
Motivos para utilizar interfaces (CommentRepository e CommentNotificationProxy):
1.	Baixo acoplamento
•	Se CommentService dependesse diretamente das implementações concretas (DBCommentRepository e EmailCommentNotificationProxy), qualquer mudança nessas classes exigiria alterações diretas no CommentService.
•	Com interfaces, CommentService não precisa se preocupar com detalhes de implementação, apenas com a funcionalidade exposta pelas interfaces.
2.	Facilidade de substituição e testes
•	Podemos substituir as implementações facilmente, por exemplo, mudar DBCommentRepository para um FileCommentRepository sem modificar CommentService.
•	Nos testes, podemos passar um repositório fake ou mock sem precisar criar um banco de dados real.
No código main, é o momento em que passamos as implementações concretas DBCommentRepository e EmailCommentNotificationProxy como argumentos para o CommentService.
Isso acontece por CommentService depende de abstrações, e só na hora da execução decidimos quais implementações concretas usar.
________________________________________
## 4.2 Using dependency injection with abstractions
Nesta seção, aplicaremos o framework Spring sobre o design de classes que implementamos na seção 4.1. Usando este exemplo, podemos discutir como o Spring gerencia a injeção de dependências ao usar abstrações. Este tópico é essencial pois, na maioria dos projetos, implementaremos dependências entre objetos usando abstrações. No capítulo 3, discutimos a injeção de dependências e usamos classes concretas para declarar as variáveis onde queríamos que o Spring definisse os valores dos beans em seu contexto. Mas, como aprenderemos neste capítulo, o Spring também entende abstrações.
Começaremos adicionando a dependência do Spring ao nosso projeto e, em seguida, decidiremos quais dos objetos desta aplicação precisam ser gerenciados pelo Spring. Aprenderemos a decidir quais objetos vamos precisar que o Spring tenha conhecimento sobre a sua existência.
<span style="background:#d4b106">Descobriremos que a @Component não é a única anotação de estereótipo que podemos usar e quando deve usar outras anotações.</span>

### 4.2.1 Deciding which objects should be part of the Spring context
When we discussed Spring in chapters 2 and 3, we focused on syntax, and we didn't have a use case to mirror something you can find in a real-world scenario. Isso também é por que não discutimos se precisamos adicionar um objeto ao Spring context. Com base na nossa discussão, você pode pensar que precisa adicionar todos os objetos do aplicativo no Spring context, mas não é o caso. Lembre-se, <span style="background:#d4b106">a principal razão para adicionar um objeto ao Spring Context é permitir que o Spring o controle e o aumente ainda mais com as funcionalidades que o framework fornece.</span> Portanto, a decisão deve ser fácil e baseada na pergunta: *Este objeto precisa ser gerenciado pelo framework?*
- *CommentService:* possui duas dependências, o *CommentRepository* e o *CommentNotificationProxy*;
- *DBCommentRepository:* implementa a interface *CommentRepository* e é uma dependência do *CommentService*.

- *EmailCommentNotificationProxy:* implementa a interface *CommentNotificationProxy* e é uma dependência do *CommentService*.

Mas por que não adicionar as instâncias de Comment também? Muitas vezes me fazem essa perguntas quando ensino cursos de Spring. **Adicionar objetos ao Spring context sem precisar que o framework os gerencie adiciona complexidade desnecessária ao nosso aplicativo**, tornando-o mais desafiador de manter e menos eficiente. Quando adicionamos um objeto ao Spring Context, nós permitimos que o framework o gerencie com algumas funcionalidades específicas que ele fornece. <span style="background:#d4b106">Se adicionamos o objeto para ser gerenciado pelo Spring sem obter nenhum benefício do framework, estamos apenas superprojetando sua implementação</span>. #Over-engineer

No capítulo 2, discutimos que usar anotações de estereótipo *@Component* é a maneira mais confortável de adicionar beans ao contexto do Spring quando as classes pertencem ao seu projeto e você pode alterá-las. Vamos usar essa abordagem aqui também. Observamos que as duas interfaces permanecem brancas (não as marcamos com *@Component*). Eu frequentemente vejo alunos confusos sobre onde devem usar as anotações de estereótipo quando também usam interfaces em suas implementações. Usamos anotações de estereótipos para as classes que o <span style="background:#b1ffff">Spring precisa criar instâncias e adicionar essas instâncias ao seu contexto</span>. <span style="background:#d4b106">Não faz sentido adicionar anotações de estereótipo em interfaces ou classes abstratas porque estas não podem ser instanciadas. </span>

```java
@Component
public class DBCommentRepository implements CommentRepository {
	@Override
	public void storeComment(Comment comment) {
		System.out.println("Storing comment: " + comment.getText());
	}
}
```

@Component marking the class para instruir o Spring a instanciar a classe e adicionar ao seu contexto como um bean.

![[Capítulo 4 - The Spring Context - Using Abstractions.png]]

Change the *CommentService* class

A classe *CommentService* declara as dependências para os outros dois componentes por meio das interfaces *CommentRepository* e *CommentNotificationProxy*. O Spring vê que os atributos são definidos com tipos de interface e é inteligente o suficiente para procurar em seu contexto por beans criados com classes que implementam essas interfaces. Como discutido no capítulo 2, devido ao fato de termos apenas um construtor na classe, a anotação *@Autowired* é opcional.

We only need to tell Spring where to find the classes annotated with stereotype annotations and test the app. The next listing presents the project's configuration class where we use the *@ComponentScan* annotation to tell Spring where to find the classes annotated with *@Component*. We discussed *@ComponentScan* in chapter 2.

```java
@Configuration
@ComponentScan(
	basePackages = {"proxies", "services", "repositores"}
)
public class ProjectConfiguration {
	
}
```

- The **@Configuration** annotation marks the configuration class;
- We use the **@ComponentScan** annotation to tell Spring in which packages to search for the classes annotated with stereotype annotation. Observe that the model packages is not specified because it doesn't contain classes annotated with stereotype annotations.

**NOTE** In this example, I use the *basePackages* attribute of the *@ComponentScan* annotation. Spring also offers the feature of directly specifying the classes (by using the *basePackageClasses* attribute of the same annotation). <span style="background:#d4b106">The advantage of defining the packages is that you only have to mention the package name</span>. In case it contains 20 components classes, you write only one line (the name of the package) instead of 20. The disavantage is that if a developer renames the package, they might not realize they also have to change the value of the *@ComponentScan* annotation. Mentioning the classes directly, you might write more, but when someone changes the code, they immediately see they also need to change the @ComponentScan annotation; In a production application, you might find both approaches, and, in my experience, one is not better than the other.

To test my setup, let's create a new main method, as presented in the following listing. We'll spin the Spring context, grab the bean of type *CommentService* out of it, and call the publishComment(Comment comment) method.

Este é um exemplo pequeno, e pode não parecer que o Spring melhor muito a aparência. Ao usar o recurso de injeção de dependência DI, não criamos a instância do objeto *CommentService* e suas dependências nós mesmos, e não precisamos fazer explicitamente a relação entre eles. Em um cenário real, onde temos mais de três classes, deixar o Spring gerenciar os objetos e as dependências entre eles faz uma enorme diferença. Isso elimina o código que pode ser implícito (o que os desenvolvedores também chamam de código boilerplate), permitindo que nos concentremos no que a aplicação faz.  

### 4.2.2 Choosing what to auto-wire from multiple implementations of an abstraction
Até agora, focamos no comportamento do Spring ao utilizar Injeção de Dependência (DI) com abstrações. No entanto, usamos um exemplo no qual garantimos que <span style="background:#d4b106">apenas uma instância de cada tipo de abstração</span> solicitada para injeção fosse adicionada.

Agora, vamos avançar um passo e discutir o que acontece quando o contexto do Spring contém múltiplas **instâncias que correspondem a uma abstração solicitada**. Esse cenário é comum em projetos do mundo real, e é essencial saber como lidar com ele para garantir que sua aplicação funcione corretamente.

Suponhamos que tenhamos dois beans criados a partir de duas classes diferentes que implementam a interface *CommentNotificationProxy*. Felizmente, o Spring adota um mecanismo para decidir qual bean escolher. 

Quando há mais de um bean do mesmo tipo no contexto do Spring, é necessário especificar qual deles deve ser injetado. Além disso, já conhecemos algumas abordagens para resolver esse problema:
- Using the @Primary annotation to mark one of the beans for implementation as the default;
- Using the @Qualifier annotation to name a bean and then refer to it by its name for DI;

![[Capítulo 4 - The Spring Context - Using Abstractions-1.png]]

If you run this application as-is, you’ll get an exception because Spring doesn’t know
which of the two beans in its context to choose for injection. As you can see, it’s a NoUniqueBean- DefinitionException with the message “expected single matching but found 2.” This is how the framework tells us it needs guidance regarding the existing beans it should inject from the context: 
Caused by: org.springframework.beans.factory.NoUniqueBeanDefinitionException:

*No qualifying bean of type 'proxies.CommentNotificationProxy' available:*
*expected single matching bean but found 2:*
*commentPushNotificationProxy,emailCommentNotificationProxy*

**Marking An Implementation as Default for Injection With *@Primary***
The first solution is using *@Primary*. The only thing you need to do is add *@Primary* near the @Component annotation to mark the implementation provided by this class as the default for implementation, as show in the following listing. 
```java
@Component
@Primary
public class CommentPushNotificationProxy implements CommentNotificationProxy {
	@Override
	public void sendComment(Comment comment) {
		System.out.println(
			"Sending push notification for comment: " + comment.getText());
		)
	}
}
```

Spring injected the new implementation because we marked it as primary. 

A pergunta que sempre fazem é a seguinte, Agora temos duas implementações, mas o Spring sempre injetará apenas uma delas? Então, por que ter ambas as classes nesse caso?

Vamos explorar como essa situação pode surgir em um cenário do mundo real. Como você já sabe, as aplicações são complexas e dependem de diversas bibliotecas. Em algum momento, pode acontecer de você utilizar uma dependência que fornece uma implementação para uma determinada interface (figura 4.10), mas essa implementação não atender às necessidades da sua aplicação. Diante disso, você pode optar por definir sua própria implementação personalizada.

Nessa situação, a anotação `@Primary` é a solução mais simples para indicar qual implementação o Spring deve priorizar na injeção de dependências.

**Naming Implementation for Dependency Injection With @Qualifier**
Em algumas aplicações em produção, pode ser necessário definir múltiplas implementações para a mesma interface, sendo que diferentes objetos utilizarão essas implementações.

Imagine, por exemplo, que precisamos de duas formas de notificação de comentários: por e-mail ou por push notification (figura 4.11). Ambas ainda são implementações da mesma interface, mas cada uma depende de objetos distintos dentro da aplicação.

Vamos modificar o código para testar essa abordagem. Você pode encontrar essa implementação no projeto **“sq-ch4-ex6”**. Os trechos de código a seguir demonstram como utilizar a anotação `@Qualifier` para especificar qual implementação deve ser injetada em cada caso.

![[Capítulo 4 - The Spring Context - Using Abstractions-2.png]]

## 4.3 Focusing on object responsibilities with stereotype annotations
Até agora, ao discutir as anotações de estereótipo, usamos apenas `@Component` em nossos exemplos. No entanto, em implementações do mundo real, você perceberá que os desenvolvedores frequentemente utilizam outras anotações para o mesmo propósito. Nesta seção, vou mostrar como usar mais duas anotações de estereótipo: `@Service` e `@Repository`.

Em projetos reais, é uma prática comum definir explicitamente a finalidade de um componente por meio de uma anotação de estereótipo. O uso do `@Component` é genérico e não fornece detalhes sobre a responsabilidade do objeto que está sendo implementado. No entanto, os desenvolvedores geralmente trabalham com objetos que possuem responsabilidades bem definidas. Duas dessas responsabilidades são `service` e `repository`.

Os #Services são responsáveis por implementar os casos de uso da aplicação, enquanto os #repositorys gerenciam a persistência dos dados. Como essas responsabilidades são comuns e desempenham um papel importante no design das classes, ter uma maneira distinta de marcá-las ajuda os desenvolvedores a compreender melhor a arquitetura da aplicação.

Todas as três (`@Component`, `@Service` e `@Repository`) são anotações de estereótipo e instruem o Spring a criar e gerenciar uma instância da classe anotada dentro do seu contexto.

![[Capítulo 4 - The Spring Context - Using Abstractions-3.png]]
