*This chapter covers*
- Understanding the need for Spring context;
- Adding new object instances to the Spring context.

Neste capítulo, começaremos a aprender como trabalhar com um elemento crucial do framework Spring: o **context** (também conhecido como *application context* em um aplicativo Spring). Imagine o context como um espaço na memória do nosso aplicativo onde adicionamos todas as instâncias de objetos que queremos que o framework gerencie. Por padrão, o Spring não conhece nenhum dos objetos que definimos em nossa aplicação. Para permitir que o Spring veja nossos objetos, precisamos adicioná-los ao context.

Mais adiante neste livro, discutiremos o uso de diferentes capacidades fornecidas pelo Spring em aplicativos. Aprenderemos que a integração dessas funcionalidades é feita por meio do contexto, adicionando instância de objetos e estabelecendo relações entre eles. O Spring utiliza as instâncias no contexto para conectar sua aplicação às várias funcionalidades que ele oferece. Ao longo do livro, aprenderemos os fundamentos das características mais importantes (por exemplo, transações, testes, etc.).

Aprender o que é o **Spring Context** e como ele funciona é o primeiro passo para aprender a usar o Spring, porque, sem saber como gerenciar o contexto Spring, quase nada mais do que aprenderemos a fazer com ele será possível. O context é um mecanismo complexo que permite ao Spring controlar as instâncias que definimos. Dessa forma, ele permite que utilizemos as capacidades fornecidas pelo framework.

Vamos aprender como adicionar instâncias de objetos ao Spring Context. No capítulo 3, aprenderemos como referenciar as instâncias que adicionamos e estabelecer relações entre elas.

Chamaremos essas instâncias de objetos de #beans. Nem todos os objetos de uma aplicação precisam ser gerenciados pelo SPring, então não é necessário adicionar todas as instâncias de objetos de nossa aplicação ao contexto Spring. Por enquanto, convido você a se concentrar em aprender as abordagens para adicionar uma instância para o Spring gerenciar