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