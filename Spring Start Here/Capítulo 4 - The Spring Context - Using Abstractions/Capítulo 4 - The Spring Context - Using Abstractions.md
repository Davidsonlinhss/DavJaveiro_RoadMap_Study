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

**Analogia**: suponha que usemos um aplicativo de compartilhamento de viagens porque precisamos ir a algum lugar. Quando solicitamos uma viagem, geralmente não nos importamos com a aparência do carro ou quem é o motorista. Só precisamos chegar a algum lugar. No meu caso, não me importo se um carro ou uma nave espacial vem me buscar, desde que eu chegue ao destino a tempo. 









----
**MERGE**
Agora, podemos implementar o objeto em si com as duas dependências do objeto **CommentService** (o **CommentRepository** e o **ComentNotificationProxy**). 

**Definindo duas dependências como atributos da classe**
```java
public class CommentService {
	private final CommentRepository commentRepository;
	private final CommentNotificationProxy commentNotificationProxy;
}
```

```java
public commentService(CommentRepository commentRepository, CommentNotificationProxy commentNotificationProxy) {
	this.commentRepositroy = commentRepository;
	this.commentNotificationProxy = commentNotificationProxy;
}
```

---
**Por qual motivo não passamos as implementações concretas para a classe *CommentService***?

É para aderirmos ao **Princípio da Inversão de Dependência DIP**, um dos princípios #SOLID.

Motivos para utilizar *interfaces* (**CommentRepository** e **CommentNotificationProxy**):
1. Baixo acoplamento
- Se **CommentService** dependesse diretamente das implementações concretas (**DBCommentRepository** e **EmailCommentNotificationProxy**), qualquer mudança nessas classes exigiria alterações diretas no *CommentService*.
- Com interfaces, *CommentService* não precisa se preocupar com detalhes de implementação, apenas com a funcionalidade exposta pelas interfaces.

2. Facilidade de substituição e testes
- Podemos substituir as implementações facilmente, por exemplo, mudar **DBCommentRepository** para um **FileCommentRepository** sem modificar *CommentService*.
- Nos testes, podemos passar um repositório fake ou mock sem precisar criar um banco de dados real.

No código *main*, é o momento em que passamos as implementações concretas **DBCommentRepository** e **EmailCommentNotificationProxy** como argumentos para o **CommentService**. 

Isso acontece por **CommentService** depende de abstrações, e só na hora da execução decidimos quais implementações concretas usar.

---

## 4.2 Using dependency injection with abstractions
Nesta seção, aplicaremos o framework Spring sobre o design de classes que implementamos na seção 4.1. Usando este exemplo, podemos discutir como o Spring gerencia a injeção de dependências ao usar abstrações. Este tópico é essencial pois, na maioria dos projetos, implementaremos dependências entre objetos usando *abstrações.* No capítulo 3, discutimos a injeção de dependências e usamos classes concretas para declarar as variáveis onde queríamos que o Spring definisse os valores dos beans em seu contexto. Mas, como aprenderemos neste capítulo, o Spring também entende abstrações.

Começaremos adicionando a dependência do Spring ao nosso projeto e, em seguida, decidiremos quais dos objetos desta aplicação precisam ser gerenciados pelo Spring. Aprenderemos a decidir quais objetos precisemos que o Spring tenha conhecimento sobre a sua existência. 

Descobriremos que a *@Component* não é a única anotação de estereótipo que podemos usar e quando deve usar outras anotações.



