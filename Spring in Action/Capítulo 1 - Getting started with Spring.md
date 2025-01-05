## Prefácio
O Spring entrou no mundo do desenvolvimento há mais de 18 anos com a missão fundamental de facilitar o desenvolvimento de aplicativos Java. Originalmente, isso significa oferecer uma alternativa leve ao EJB 2.X. Mas o Spring estava apenas começando. Com o passar dos anos, o Spring expandiu sua missão de simplicidade para abordar desafios comuns de desenvolvimento, incluindo #Persistência, #segurança, #integração, #cloud e outros.

Embora o Spring esteja se aproximando de duas décadas de habilitação e simplificação do desenvolvimento Java empresarial, não mostra sinais de desaceleração. O Spring continua a abordar os desafios do desenvolvimento Java, seja criando uma aplicação implantada em um servidor de aplicação convencional ou uma aplicação containerizada implantada em um cluster Kubernetes de construção e monitoramento em tempo de execução, E com o Spring Boot fornecendo autoconfiguração, ajuda na dependência de construção e monitoramento em tempo de execução, nunca houve um momento melhor para ser um desenvolvedor Spring!

Esta edição do Spring in Action é seu guia para Spring e Spring Boot, e foi atualizada para refletir o melhor que ambos têm a oferecer. Mesmo que estamos iniciando no Spring, teremos nosso primeiro aplicativo Spring funcionando antes do final do primeiro capítulo.

À medida que o livro avança, aprenderemos a criar aplicativos web, trabalhar com dados, proteger nosso aplicativo e gerenciar a configuração do aplicativo. Em seguida, exploraremos opções para integrar nosso aplicativo Spring com outros aplicativos e como se beneficiar da programação reativa em nossos aplicativos Spring, incluindo o novo protocolo de comunicação #RSocket. Conforme o livro chega ao fim, veremos como preparar nosso aplicativo para produção e aprenderemos opções de implementação.


--- 
## Parte 1 - Spring Fundacional

A parte 1 deste livro irá ajudar a começar a escrever um aplicativo Spring, aprendendo os fundamentos do Spring ao longo do caminho. 

No capítulo 1, darei uma visão geral rápida dos pontos essenciais do Spring e Spring Boot e mostrarei como inicializar um projeto Spring enquanto trabalha na construção do Taco Cloud, nosso primeiro aplicativo Spring.

No capítulo 2, mergulharemos mais a fundo no Spring MVC e aprenderemos a apresentar dados de modelo no navegador e como processar e validar a entrada de formulário. Também receberemos dicas sobre como escolher uma biblioteca de modelos de visualização. 

Você adicionará persistência de dados ao aplicativo Taco Cloud no capítulo 3, onde abordaremos o uso do template JDBC do Spring e como inserir dados usando instruções preparadas e key holders. Em seguida, você verá como declarar repositórios JDBC (Java Database Connectivity) e JPA (Java Persistence API) com Spring Data. 

O Capítulo 4 continua a história da persistência do Spring, analisando mais dois módulos Spring Data para persistir dados em Cassandra e MongoDB.

O Capítulo 5 aborda a segurança para seu aplicativo Spring, incluindo a autoconfiguração do Spring Security, a definição de armazenamento de usuários personalizados, a personalização da página de login e a proteção contra ataques de falsificação de solicitação entre sites.

Para fechar a Parte 1, veremos as propriedades de configuração no Capítulo 6. Você aprenderá a ajustar os beans autoconfigurados, aplicar propriedades de configuração aos componentes do aplicativo e trabalhar com perfis Spring.

---
## Chapter 1 - Getting started with Spring
**Este capítulo abordará**
- Fundamentos do Spring e Spring Boot;
- Inicializando um projeto Spring;
- Uma visão geral do cenário Spring;

Embora o filósofo grego Heráclito não fosse conhecido como desenvolvedor de software, parece que ele tinha uma boa noção do assunto. A ele é atribuída a frase: "<span style="background:#fff88f">A única constante é a mudança</span>". Essa afirmação resume uma verdade fundamental do desenvolvimento de software. 

A maneira como desenvolvemos aplicativos hoje é diferente de como fazíamos há um ano, cinco anos atrás, dez anos atrás e, com certeza, há vinte anos atrás, antes da introdução de uma forma inicial do Spring Framework no livro de Rod Johnson, Expert One-on-One J2EE Design and Development...

Naquela época, os tipos mais comuns de aplicativos desenvolvidos eram aplicativos web baseados em navegador, suportados por bancos de daados #relacionais. Embora esse tipo de desenvolvimento ainda seja relevante - e o Spring esteja bem equipado para esses tipos de aplicativos -, agora também estamos interessados em desenvolver aplicativos compostos de #microsserviços destinados à nuvem que persistem dados em uma variedade de banco de dados. E um novo interesse em programação reativa visa fornecer maior escalabilidade e desempenho melhorado com operações não bloqueantes. 

À medida que o desenvolvimento de software evoluiu, o Spring Framework também mudou para abordar preocupações modernas de desenvolvimento, incluindo <span style="background:#d4b106">microsserviços e programação reativa</span>. Os criadores do Spring também se propuseram a simplificar seu modelo de desenvolvimento, introduzindo o Spring Boot.

---
**Programação reativa:** modelo de desenvolvimento focado em dados assíncronos e não bloqueantes, onde as operações reagem a eventos ou mudanças de estado, trabalhando com fluxos contínuos de dados. No Spring, isso é implementado pelo **Spring WebFlux**, que permite criar aplicações escaláveis e eficientes. Diferente do modelo tradicional (Spring MVC), que bloqueia threads enquanto espera resultados (como consultas a banco ou chamadas de API), a programação reativa libera threads para outras tarefas, melhorando o desempenho em sistemas de alta concorrência. 
**Exemplo:** enquanto uma operação de banco de dados está sendo processada, a nossa aplicação pode atender outras requisições, evitando "esperas".

---

Seja você desenvolvendo um simples aplicativo web baseado em banco de dados ou construindo um aplicativo moderno baseado em microsserviço, o Spring é o framework que o ajudará a alcançar nossos objetivos. Esse capítulo será o nosso primeiro passo em uma jornada pelo desenvolvimento de aplicativos modernos com Spring. 

---
## 1.1 What is Spring?
Qualquer aplicativo não trivial é composto por muitos componentes, cada um responsável por sua própria parte da funcionalidade geral do aplicativo, coordenando-se com os outros elementos do aplicativo para realizar o trabalho. Quando o aplicativo é executado, esses componentes precisam de alguma forma ser criados e apresentados uns aos outros. 

No seu núcleo, o Spring oferece um contêiner, muitas vezes referido como #spring-context, que cria e gerencia componentes de aplicativo. Esses componentes, ou #beans, são conectados dentro do contexto do aplicativo Spring para formar um aplicativo completo, muito parecido com tijolos, argamassa, madeira, pregos, encanamento e fiação são unidos para formar uma casa. 

O ato de conectar beans juntos é baseado em um padrão conhecidos como injeção de dependência (DI). Em vez de ter componentes criando e mantendo o ciclo de vida de outros beans dos quais dependem, um aplicativo injetado por dependência depende de uma entidade separada (o contêiner) para criar e manter todos os componentes e injetá-los nos beans que precisam deles. Isso é feito tipicamente através de argumentos do construtor ou métodos de acesso à propriedade. 

Por exemplo, suponha que, entre muitos componentes de um aplicativo, abordaremos dois: um <span style="background:#d4b106">serviço de inventário</span> (para buscar níveis de estoque) e um <span style="background:#d4b106">serviço de produto</span> (para fornecer informações básicas sobre produtos). O serviço de produto depende do serviço de inventário para poder fornecer um conjunto completo de informações sobre os produtos. A figura 1.1 ilustra as relações entre esses beans e o contexto do aplicativo Spring. 

![[Capítulo 1 - Getting started with Spring.png]]
Figura 1.1 Componentes do aplicativo  são gerenciados e injetados uns nos outros entre si pelo Spring-context do aplicativo.

Além de seu contêiner principal, o Spring e um portfólio completo de bibliotecas relacionadas oferecem um framework web, uma variedade de opções de persistência de dados, um framework de segurança, integração com outros sistemas, monitoramento em tempo de execução, suporte a microsserviços, um modelo de programação reativa e muitos outros recursos necessários para o desenvolvimento de aplicativos modernos.

Historicamente, a maneira de orientar o contexto do aplicatico Spring a conectar beans era com um ou mais arquivos XML que descreviam os componentes e sua relação com outros componentes. 

Por exemplo, o seguinte código XML declara dois beans, um bean InvetoryService e um bean ProductService, e conecta o Invetory ao Product via uma argumento construtor:
```xml
<bean id="inventoryService"
      class="com.example.InventoryService" />
<bean id="productService"
      class="com.example.ProductService">
    <constructor-arg ref="inventoryService" />
</bean>
```

Em versões recentes do Spring, no entanto, uma configuração baseada em Java é mais comum. A seguinte classe de configuração baseada em Java é equivalente à configuração XML:
```java
@Configuration
public class ServiceConfiguration {
    @Bean
    public InventoryService inventoryService() {
        return new InventoryService();
    }

    @Bean
    public ProductService productService() {
        return new ProductService(inventoryService());
    }
}

```
A anotação @configuration indica ao Spring que esta é uma classe de configuração que fornecerá beans ao contexto do aplicativo Spring. 

Os métodos da configuração são anotados com @Bean, indicando que os objetos que eles retornam devem ser adicionados como beans no contexto do aplicativo (onde, por padrão, seus respectivos IDs de bean serão os mesmo que os nomes dos métodos que os definem.)

A configuração baseada em Java oferece vários benefícios em relação à configuração baseada em XML, incluindo maior segurança de tipos e melhor refatorabilidade. Mesmo assim, a configuração explícita com Java ou XML é necessária apenas se o Spring não conseguir configurar automaticamente os componentes. 

A configuração automática tem suas raízes nas técnicas do Spring conhecidas como #autowiring e varredura de componentes. Com a varredura de componentes, o Spring pode automaticamente descobrir componentes do classpath de um aplicativo e criá-los como beans no contexto do aplicativo Spring. Com o autowiring, o Spring injeta automaticamente os componentes com os outros beans dos quais eles dependem.  

Mais recentemente, com a introdução do Spring Boot, a configuração automática foi muito além da varredura de componentes e do autowiring. O Spring Boot é uma extensão do Spring Framework que oferece várias melhorias de produtividade. A mais conhecida dessas melhorias é a <span style="background:#d4b106">autoconfiguração</span>, onde o Spring Boot pode fazer suposições razoáveis sobre quais componentes precisam ser configurados e conectados, com base em entradas no classpath, variáveis de ambiente e outros fatores. 

Gostaria de mostrar alguns exemplos de código que demostram a autoconfiguração, mas não posso. A autoconfiguração é muito parecida com o vento - você pode ver os efeitos dela, mas não há código que eu possa mostrar e dizer "Olha! Aqui está um exemplo de autoconfiguração!" Coisas acontecem, componentes são habilitados e funcionalidades sem escrever código. É essa falta de código que é essencial para a autoconfiguração e o que a torna tão maravilhosa.  

A autoconfiguração do Spring Boot reduziu drasticamente a quantidade de configuração explícita (seja com XML ou Java) necessária para construir uma aplicativo. Na verdade, quando você terminar o exemplo deste capítulo, terá um aplicativo Spring <span style="background:#d4b106">funcionando com apenas uma  linha de código de configuração Spring</span>!

O Spring Boot aprimora tanto o desenvolvimento Spring que é difícil de imaginar desenvolver aplicativos Spring sem ele. Por esse motivo, este livro trata Spring e Spring Boot como se fossem um só. Usaremos o Spring Boot o máximo possível e a configuração explícita apenas quando for necessário. E, como configuração XML do Spring é a maneira antiga de trabalhar com Spring, nos concentraremos principalmente na configuração baseada em Java do Spring.

## 1.2 Initializing a Spring application
1. Para iniciar um novo projeto Spring no Spring Tool Suite, vá ao menu **File** e selecione **New**, em seguida, selecione **Spring Starter Project**. A Figura 1.2 mostra a estrutura do menu a ser procurada.
2. Uma vez selecionado o Spring Starter Project, uma nova caixa de diálogo do assistente de projeto (figura 1.3) aparece. A primeira página do assistente solicita algumas informações gerais do projeto, como o nome do projeto, descrição e outras informações essenciais. Se você estiver familiarizado com o conteúdo de um arquivo pom.xml Maven, reconhecerá a maioria dos campos como itens que acabam em uma especificação de build Maven. Para o aplicativo Taco Cloud, preencha o diálogo conforme mostrado na figura 1.3 e, em seguida, clique em Next.
3. A próxima página do assistente permite que você selecione as dependências a serem adicionadas ao seu projeto (veja a figura 1.4). Observe que, próximo ao topo da caixa de diálogo, você pode selecionar em qual versão do Spring Boot deseja basear seu projeto. Isso é definido como padrão para a versão mais recente disponível. Geralmente é uma boa ideia deixá-lo como está, a menos que você precise direcionar uma versão diferente.

Quanto às próprias dependências, você pode expandir as várias seções e procurar as dependências desejadas manualmente ou pesquisá-las na caixa de pesquisa no topo da lista Disponível. Para o aplicativo Taco Cloud, você começará com as dependências mostradas na figura 1.4.

Neste ponto, você pode clicar em Concluir para gerar o projeto e adicioná-lo ao seu espaço de trabalho. Mas se você estiver se sentindo um pouco aventureiro, clique em Next mais uma vez para ver a página final do assistente de novo projeto inicial, como mostrado na figura 1.5.

Por padrão, o assistente para novos projetos faz uma chamada ao Spring Initializr em [http://start.spring.io](https://www.google.com/url?sa=E&source=gmail&q=http://start.spring.io) para gerar o projeto. Geralmente, não há necessidade de substituir este padrão, por isso você poderia ter clicado em Concluir na segunda página do assistente. Mas se por algum motivo você estiver hospedando seu próprio clone do Initializr (talvez uma cópia local em sua própria máquina ou um clone personalizado em execução dentro do firewall da empresa), então você vai querer alterar o campo Base Url para apontar para sua instância do Initializr antes de clicar em Concluir.

Depois de clicar em Concluir, o projeto é baixado do Initializr e carregado em seu espaço de trabalho. Aguarde alguns minutos para que ele seja carregado e construído, e então você estará pronto para começar a desenvolver a funcionalidade do aplicativo. Mas primeiro, vamos dar uma olhada no que o Initializr lhe deu (estudando a estrutura). 

### 1.2.2 Examining the Spring project structure
Após o projeto ter sido carregado na IDE, expanda a tela para verificar o que ele contém. A figura 1.6 abaixo mostra o projeto Taco Cloud expandido no Spring Tool Suite.

Podemos reconhecer o projeto Taco Cloud como uma estrutura típica de projeto Maven ou Gradle, onde o código-fonte da aplicação é colocado em <span style="background:#d4b106">sr/main/java</span>, o código de teste é colocado em <span style="background:#d4b106">sr/test/java</span>, os recursos não Java são colocados em <span style="background:#d4b106">src/main/resources</span>. 

Dentro de um projeto #Spring, devemos observar algumas estruturas importantes:
- #mvnw and #mvnwcmd - são scripts do Maven Wrapper. Esses scripts <span style="background:#d4b106">permitem que construamos o nosso projeto</span>, mesmo que o Maven não esteja instalado em nossa máquina. 
- #pomxml - esse arquivo contém as especificações para o #build da aplicação.
- #TacoCloudApplication-java - essa é a classe principal Spring Boot responsável por inicializar o projeto.
- #Application-properties - esse arquivo é vazio inicialmente, mas ele oferece um local onde nós podemos <span style="background:#d4b106">especificar as propriedades de configuração</span> do nosso projeto. 
- #Static - essa pasta nós colocamos os nossos conteúdos estáticos (imagens, folhas de estilos, JavaScript ) que iremos servir/entregar para o navegador. 
- #templates - local onde colocaremos os arquivos de template que serão utilizados para renderizar o conteúdo para o navegador. É inicialmente vazio, mas podemos adicionar um template #Thymeleaf  em breve...
---
**ANOTAÇÃO**
#Thymeleaf - mecanismo de template engine utilizado em aplicações Java, especialmente em conjunto com o Spring Boot, ele <span style="background:#d4b106">renderiza páginas HTML de forma dinâmica</span>. Ele permite criar aplicações web interativas e com conteúdo dinâmico, ao integrar dados provenientes do backend diretamente no frontend.
### **Vantagens do Thymeleaf**

- **HTML amigável:** Pode ser editado por desenvolvedores frontend sem a necessidade de configurar um servidor.
- **Poderoso:** Suporte para manipulação de variáveis, formatação de dados, loops, condicionais, e muito mais.
- **Seguro:** Inclui proteção contra vulnerabilidades como XSS (Cross-Site Scripting), escapando automaticamente os dados dinâmicos.
- **Rápido de Configurar:** Praticamente pronto para uso em projetos Spring Boot.
---

- #TacoCloudApplicationTests - essa é uma simples classe de teste que garante que o contexto da aplicação Spring seja carregada com sucesso. Nós adicionaremos mais testes a medida que fomos criando a aplicação. 

Conforme a aplicação Taco Cloud cresce, preencheremos a estrutura base do projeto com código Java, imagens, folhas de estilo, testes e outros elementos que tornarão o projeto mais completo. Enquanto isso, vamos explorar mais detalhadamente alguns dos itens fornecidos pelo Spring #Initializr .

## Explorando o Build Specification
Quando preenchemos o formulário do Spring Initializr, especificamos que o projeto deveria ser construído com o #Maven (gerenciador de dependências Spring). Por isso, o arquivo pom.xml foi gerado de forma automática já configurado com as dependências selecionadas.  

## Spring Boot Plugin
Ferramenta integrada ao **Maven** e **Gradle**, que facilita o gerenciamento, a construção e a execução de projetos Spring Boot. Ele é essencial para otimizar o ciclo de desenvolvimento, desde a criação de arquivos executáveis até a configuração do ambiente de execução.

### **Principais Funcionalidades**

#### 1. **Criação de Arquivos Executáveis (Fat JAR/WAR):**

- O Spring Boot Plugin empacota todas as dependências do projeto em um único arquivo JAR ou WAR executável.
- Isso torna a aplicação independente do ambiente externo, pois contém tudo o que é necessário para ser executada.
- Exemplo: Após a execução de `mvn package` ou `gradle build`, você obtém um arquivo JAR executável que pode ser iniciado com:
    
    bash
    
    Copiar código
``` java
java -jar myapp.jar
```
#### 2. **Execução Simplificada da Aplicação:**

- Permite iniciar a aplicação diretamente com comandos como:
```
mvn spring-boot:run
```

#### 3. **Configuração Automática de Dependências:**

- Simplifica o uso de dependências, adicionando automaticamente configurações importantes para projetos Spring Boot. 

#### 4. **Recarregamento Rápido (Hot Reload):**
- Durante o desenvolvimento, o plugin permite executar a aplicação com <span style="background:#d4b106">recarregamento rápido</span>, aplicando mudanças sem reiniciar todo o servidor.
### **Vantagens do Spring Boot Plugin**

1. **Simplifica o Desenvolvimento:** Automatiza tarefas como empacotamento e execução.
2. **Reduz a Configuração Manual:** Facilita a integração de dependências e configuração do ambiente.
3. **Portabilidade:** Cria arquivos executáveis que funcionam em qualquer ambiente com Java instalado.
4. **Facilidade no Ciclo de Vida:** Torna mais rápido o desenvolvimento, teste e implantação da aplicação.

Com relação ao arquivo pom.xml, o primeiro ponto a ser observado é o elemento #parent e, mais especificamente, seu elemento filho #version. Ele especifica que o projeto utiliza spring-boot-starter-parent como seu POM pai. Esse POM pai fornece, entre outras funcionalidades, o **gerenciamento de dependências** para várias bibliotecas comumente usadas em projetos **Spring**.

Enquanto estamos falando sobre dependências, observe que há quatro dependências declaradas no elemento dependencies

A quarta dependência fornece diversas funcionalidades úteis para testes. Você não precisou marcá-la explicitamente para incluí-la, pois o **Spring Initializr** presume (esperançosamente, de forma correta) que você escreverá testes em seu projeto. Estamos falando da dependência **spring-boot-starter-test**.

Podemos perceber que todas as dependências, exceto a dependência DevTools, possuem a palavar #starter em seu artifact ID. As dependências #starter do Spring Boot são especiais porque geralmente não contém código de biblioteca em si, mas, em vez disso, trazem outras bibliotecas de forma transitiva.

Essas dependências #starter oferecem os seguintes benefícios principais: 
- O arquivo de build será menor e mais fácil de gerenciar, porque não precisaremos declarar uma dependência para cada biblioteca que possa precisar;
- Podemos pensar nas dependências em termos das capacidades que elas fornecem, em vez de seus nomes de bibliotecas. Se estiver desenvolvendo uma aplicação web, adicionaremos a dependência do **web starter** ao invés de uma lista extensa de bibliotecas individuais que permitem que escrevamos uma aplicação web.
- Você está livre da preocupação com as versões das bibliotecas. Pode confiar que as versões das bibliotecas trazidas de forma transitiva serão compatíveis para uma determinada versão do Spring Boot. Precisamos apenas nos preocupar com a versão do Spring Boot que estamos utilizando. 

Por fim, a especificação de compilação termina com o plug-in do Spring Boot. Esse plug-in executa
algumas funções importantes, descritas a seguir... já mencionado acima...

Falando sobre a classe bootstrap, vamos abri-lá e dar uma olhada.
Como você estará executando a aplicação a partir de um <span style="background:#d4b106">JAR executável</span>, é importante ter uma classe principal que será executada quando o arquivo JAR for iniciado. Você também precisará de pelo menos uma configuração mínima do **Spring** para inicializar a aplicação. Isso é o que você encontrará na classe **TacoCloudApplication**, mostrada no seguinte exemplo.

Embora haja pouco código na classe, o que está presente tem um grande impacto. Uma das linhas mais poderosas de código é também uma das mais curtas. A anotação @SpringBootApplication indica claramente que esta é uma aplicação Spring Boot. No entanto, há mais na anotação @SpringBootApplication do que à primeira vista.

O que faz a anotação **`@SpringBootApplication`**:
- **`@SpringBootApplication`** é uma anotação composta, que combina três anotações essenciais:
1. **`@EnableAutoConfiguration`**: Ativa a configuração automática do Spring, que tenta configurar automaticamente os beans necessários com base nas dependências que estão presentes no classpath.
2. **`@ComponentScan`**: Habilita a busca automática por componentes no pacote da aplicação e seus subpacotes, permitindo que o Spring encontre e registre beans como #Component, #Controller, #Service...
3. **`@Configuration`**: Indica que a classe contém configurações para a aplicação, sendo tratada como uma classe de configuração do Spring.
Essas funcionalidades combinadas tornam a inicialização da aplicação Spring Boot simples e eficiente, sem a necessidade de configurações complexas.

A outra parte importante é o método main() que será executado quando o arquivo <span style="background:#fff88f">JAR for iniciado</span>. Na maioria das vezes, este método é código padrão; toda aplicação Spring Boot que você escrever terá um método semelhante ou idêntico a este (com exceção do nome da classe). 
O método **main()** chama um método estático **run()** na classe **SpringApplication**, que realiza a inicialização real da aplicação, criando o contexto da aplicação Spring. Os dois parâmetros passados para o método **run()** são uma classe de configuração e os argumentos da linha de comando. Embora não seja necessário que a classe de configuração passada para o **run()** seja a mesma que a classe de inicialização, esta é a escolha mais conveniente e típica.
É improvável que você precise alterar algo na classe de inicialização. Para aplicações simples, pode ser conveniente configurar um ou dois outros componentes na classe de inicialização, mas para a maioria das aplicações, <span style="background:#d4b106">é melhor criar uma classe de configuração separada para tudo o que não é autoconfigurado</span>. Você definirá várias classes de configuração ao longo deste livro, então fique atento para mais detalhes. 

## Testando a aplicação
Os testes são uma parte importante do desenvolvimento de software. Nós sempre poderemos testar nossos projetos manualmente construindo e executando-o, através da linha de comando...

Ou, pelo fato de estarmos utilizando o Spring Boot, o plugin Spring Boot Maven torna o processo mais fácil, basta usar o comando:
```cmd
./mvnw spring-boot:run
```

Mas testes manuais implica que há humanos envolvidos e, portanto,  erros humanos em potencial podem ocorrer com os testes, gerando também inconsistências.  Testes automatizados são mais consistentes e repetível.
Reconhecendo isso, o Spring #Initializr fornece uma classe de testes para inciarmos. 

Não há muito o que ver em **TacoCloudApplicationTests**: o único método de teste na classe está vazio. Mesmo assim, essa classe de teste realiza uma verificação essencial para garantir que o contexto da aplicação Spring seja carregado com sucesso. Se você fizer alterações que impedem a criação do contexto da aplicação Spring, esse teste falha, e você pode agir para corrigir o problema.

A anotação **`@SpringBootTest`** informa ao JUnit para inicializar o teste com as capacidades do Spring Boot. Assim como **`@SpringBootApplication`**, **`@SpringBootTest`** é uma anotação composta, que está anotada com **`@ExtendWith(SpringExtension.class)`**, para adicionar capacidades de teste do Spring ao JUnit 5. Por enquanto, basta entender isso como o equivalente da classe de teste à chamada de **`SpringApplication.run()`** em um método **`main()`**.

Ao longo deste livro, você verá **`@SpringBootTest`** várias vezes, e vamos explorar alguns de seus recursos.

Finalmente, há o próprio método de teste. Embora **`@SpringBootTest`** seja responsável por <span style="background:#d4b106">carregar o contexto da aplicação Spring para o teste</span>, ele não fará nada se não houver métodos de teste. Mesmo sem afirmações ou qualquer código, esse método de teste vazio fará com que as duas anotações façam seu trabalho e carreguem o contexto da aplicação Spring. Se houver algum problema ao fazer isso, o teste falhará.

Para rodar esse e qualquer outra classe de teste a partir da linha de comando, você pode usar o seguinte comando Maven:
```cmd
./mvn test
```

## 1.3 Writing a Spring Application
Como estamos começando, vamos realizar uma mudança relativamente pequena na aplicação Taco Cloud, mas que demonstrará muitas das vantagens do Spring. Parece apropriado que, ao iniciar, o primeiro recurso que adicionaremos à aplicação Taco Cloud seja uma página inicial. Ao adicionar a página inicial, criaremos dois artefatos de código:
1. Uma classe controller que faz requisições para a home page;
2. Um template view que define a aparência que a página terá.
Como os testes são importantes, nós escreveremos uma classe de teste simples para testar a home page. Mas primeiro... vamos logo escrever este controller...

### 1.3.1 Handling web request
O Spring vem com uma poderosa ferramenta web conhecida como #SpringMVC. No centro do Spring MVC está o conceito de um #controller, uma classe que faz requisições e retorna/responde com informações de algum tipo (formato)? No caso de um aplicativo voltado para o navegador, um <span style="background:#d4b106">controller responde preenchendo opcionalmente os dados do modelo e transmitindo a solicitação a uma exibição para produzir HTML que é retornado ao navegador</span>.

Nós iremos aprender um pouco sobre o Spring MVC no capítulo 2. Mas, por agora, iremos escrever uma classe controller simples que  processa requisições para o caminho root (por exemplo, /) e encaminha essa solicitação para ser visualizada na página inicial sem preencher nenhum modelo de dados.

Como podemos ver, a classe está anotada com<span style="background:#d4b106"> @Controller</span>. Por si só, @Controller não faz muita coisa. Sua principal função é <span style="background:#d4b106">identificar essa classe como um componente para a varredura de</span> componentes (component scanning). Como a classe HomeController está anotada com @Controller, a varredura de componentes do Spring a detecta automaticamente e cria uma instância de HomeController como um #bean no spring application context. 

Existem diversas outras anotações com finalidade semelhante: 
1. @Componenet
2. @Service
3. @Repository
O uso de @Controller é mais descritivo e relacionado com o papel desempenhado pela nossa clase;
O método home() é tão simples quanto o método controller. 

Essa anotação <span style="background:#d4b106">@GetMapping</span> indica que, se uma requisição HTTP GET for recebida para o caminho raiz /, esse método deverá lidar com a requisição. Ele faz isso simplesmente retornando o valor home como uma string.

Esse valor é interpretado como o nome lógico de uma view. A implementação dessa view depende de alguns fatores, mas, como o #Thymeleaf está no classpath, podemos definir esse template utilizando o Thymeleaf.

---
## Por que Thymeleaf?
O autor prefere o uso do Thymeleaf por não conter tanta complexidade para configurá-lo nesse primeiro momento em comparação com outras ferramentas de view como JSP ou FreeMarker... veremos outras opções de template, incluindo o JSP...

--- 

O nome do template é derivado do nome lógico da view ao adicionar o prefixo /templates/ e o sufixo .html. O caminho resultante para o template será /templates/home.html. Portanto, precisamos adicionar o template no seguinte caminho /src/main/resources/templates/home.html

### 1.3.2 Definindo a view
Não há muito o que discutir sobre este template. A única linha de código notável é a que contém a tag **`<img>`** para exibir o logo do Taco Cloud. Ela utiliza o atributo **`th:src`** do Thymeleaf e uma expressão **`@{...}`** para referenciar a imagem com um caminho relativo ao contexto. Além disso, é basicamente uma página de "Hello World".

Vamos falar um pouco mais sobre essa imagem. Fica a seu critério definir um logo para o Taco Cloud que você goste. No entanto, é importante garantir que ele seja colocado no local correto dentro do projeto.

A imagem é referenciada com o path relativo /images/TacoCloud.png. 

O conteúdo estático, como imagens, deve ser armazenado na pasta /src/main/resources/static.
/src/main/resources/static/images/TacoCloud.png

Agora que temos um controlador para gerenciar as requisições da página inicial e um template de view para renderizar a página inicial, está quase tudo pronto para iniciar a aplicação...

### 1.3.3 Testando o Controller
Testar aplicações web pode ser desafiador ao fazer asserções contra o conteúdo de uma página HTML. Felizmente, <span style="background:#d4b106">o Spring oferece um suporte de teste poderoso</span> que torna o processo de testar uma aplicação web mais fácil. 

Para a página inicial, escreveremos um teste de complexidade comparável à própria página inicial. O teste realizará uma requisição HTTP GET para o caminho raiz/ e espera um resultado bem-sucedido, onde o nome da view seja home e o conteúdo resultado contenha a frase "Welcome to...". 

A primeira coisa que podemos perceber sobre este teste é que ele difere ligeiramente da classe Taco-CloudApplication no que diz respeito às anotações aplicadas a ele. Em vez da anotação @SpringBootTest, HomeControllerTest é anotada com <span style="background:#d4b106">@WebMvcTest</span>. Esta é uma anotação de teste especial fornecida pelo Spring Boot que configura o teste para ser executado no contexto de uma aplicação Spring MVC. Mais especificamente, neste caso, ela configura o HomeController para ser registrado no Spring MVC, permitindo que você envie requisições para ele.

@WebMvcTest também configura o suporte do Spring para testar o Spring MVC. Embora seja possível fazer com que inicie um servidor, simular a mecânica do Spring MVC é suficiente para seus objetivos. A classe de teste é injetada com um objeto MockMvc para que o teste controle a simulação.

O método testHomePage() define o teste que você deseja realizar contra a página inicial. Ele começa com o objeto MockMvc para realizar uma requisição HTTP GET para / (o caminho raiz). A partir dessa requisição, ele define as seguintes expectativas:

- A resposta deve ter um status HTTP 200 (OK).
- A visualização deve ter um nome lógico de home.
- A exibição renderizada deve conter o texto "Welcome to...."

Se, após o objeto MockMvc realizar a requisição, alguma dessas expectativas não for atendida, o teste falhará. Mas o seu controlador e o template de visualização foram escritos para atender essas expectativas, então o teste deve passar com louvor — ou pelo menos com um tom de verde indicando um teste bem-sucedido. O controlador foi escrito, o template de visualização criado, e você tem um teste passando. Parece que você implementou a página inicial com sucesso. Mas, mesmo que o teste passe, <span style="background:#d4b106">há algo um pouco mais satisfatório ao ver os resultados em um navegador</span>. Afinal, isso é...
## 1.3.4 Criando e Executando a aplicação
Temos várias maneiras de inicializar uma aplicação Spring. Uma forma é utilizando a **Spring Boot Dashboard**. 

Quando a aplicação iniciar, veremos uma arte ASCII do Spring passando rapidamente pelo console, seguida por algumas entradas de log descrevendo os passos durante o início da aplicação. Antes que o registro no log pare, veremos uma mensagem indicando "Tomcat started on port(s): 8080 (http), " o que significa que estamos pronto para acessar o navegador...

Aplicações Spring Boot geralmente trazem tudo o que precisam consigo e não precisam ser implantadas em um servidor de aplicações separado.  O #Tomcat é parte da nossa aplicação! 


### 1.3.5 Conhecendo o Spring Boot DevTools
Como o próprio nome sugere, O DevTools oferece aos desenvolvedores Spring algumas ferramentas úteis para o tempo de desenvolvimento. Entre elas estão as seguintes:
- Reinício automático da aplicação quando houver mudanças no código;
- **Atualização automática do navegador** quando recursos destinados ao navegador (como templates, JavaScript, folhas de estilo, etc.) sofrerem alterações.
- **Desativação automática dos caches de templates**.
- **Console H2 integrado**, caso o banco de dados H2 esteja sendo utilizado.

É importante entender que o DevTools não é um plugin de IDE, nem requer que usemos uma IDE específica. Ele funciona igualmente bem no **Spring Tool Suite**, **IntelliJ IDEA** e **NetBeans**.
Além disso, como é destinado apenas para fins de desenvolvimento, ele é inteligente o suficiente para desativar a si mesmo quando a aplicação for implantada em um ambiente de produção.

**<span style="background:#d4b106">Automatic Application Restart</span>**
Com a ferramenta como parte do nosso projeto, podemos realizar mudanças no código Java ou em arquivos de propriedades do nosso projeto e ver as mudanças sendo aplicadas por um breve momento. 

Mais precisamente, quando o DevTools está ativo, a aplicação será recarregada em duas classes loadaers separadas na #JVM. 
Um loader será responsável por carregar o nosso código Java, arquivos de propriedades e praticamente tudo o que está no caminho src/main/ do projeto. Esses são itens que provavelmente mudarão com frequência. O outro carregador de classes é responsável por carregar as bibliotecas de dependências, que não tendem a mudar com tanta frequência.

Quando uma mudança é detectada, o DevTools recarrega apenas o carregador de classes contendo o código do nosso projeto e reinicia o contexto da aplicação Spring, mas deixa o outro carregador de classes e a JVM intactos. 

O lado negativo é que as alterações nas dependências não estarão disponíveis nos reinícios automáticos. Isso acontece porque <span style="background:#d4b106">o carregador de classes contendo as bibliotecas</span> de dependências <span style="background:#d4b106">não é recarregado automaticamente</span>. Sempre que você adicionar, alterar ou remover uma dependência na sua especificação de construção, será necessário realizar um reinício completo da aplicação para que essas mudanças tenham efeito.

<span style="background:#d4b106">**Recarregamento automático de navegador e Template Cache Disable**</span>
Por padrão, opções de templates como Thymeleaf e FreeMarker são configuradas para armazenar em cache resultados de análise dos templates, de modo que os templates não precisam ser reanalisados a cada requisição que atendem. Isso é ótimo em produção, pois traz benefício de desempenho.

No entanto, templates em cache não são tão bons no tempo de desenvolvimento. Eles tornam impossível fazer alterações nos templates enquanto a aplicação está rodando e ver os resultados após atualizar o navegador. Mesmo que você tenha feito alterações, o template em cache continuará sendo usado até que a aplicação seja reiniciada.

O <span style="background:#d4b106">**DevTools** resolve esse problema desativando automaticamente o cache de todos os templates</span>. Faça quantas alterações quiser nos seus templates e saiba que você está a apenas uma atualização do navegador de ver os resultados.

Mas, se você for como eu, talvez nem queira ter o trabalho de clicar no botão de atualização do navegador. Seria muito mais conveniente se você pudesse fazer as alterações e ver os resultados no navegador imediatamente. Felizmente, o <span style="background:#d4b106">**DevTools** tem algo especial para aqueles de nós que são preguiçosos demais para clicar no botão de atualização</span>.

O **DevTools** ativa automaticamente um servidor **LiveReload** ([http://livereload.com/](http://livereload.com/)) junto com sua aplicação. Por si só, o servidor **LiveReload** não é muito útil. Mas, quando combinado com o plugin correspondente do **LiveReload** no navegador, ele faz com que seu navegador seja atualizado automaticamente quando alterações são feitas em templates, imagens, folhas de estilo, JavaScript e outros arquivos — na verdade, quase tudo o que acaba sendo servido ao seu navegador.

### Vamos revisar
O que já realizamos:
- Criou a estrutura inicial do projeto usando o **Spring Initializr**.
- Escreveu uma classe **controller** para lidar com a requisição da página inicial.
- Definiu um **template de view** para renderizar a página inicial.
- Escreveu uma classe de teste simples para validar seu trabalho.
- 
No arquivo **`pom.xml`**, você declarou uma dependência nos starters **Web** e **Thymeleaf**. Essas duas dependências, por sua vez, trouxeram outras dependências transitivamente, incluindo as seguintes:

- **O framework MVC do Spring**: Responsável por fornecer a arquitetura de Model-View-Controller (MVC) para sua aplicação, permitindo mapear requisições HTTP para métodos em controladores e renderizar respostas usando templates.
- **Tomcat embutido**: O Spring Boot vem com o **Tomcat** embutido, o que significa que você não precisa configurar um servidor de aplicação externo. Ele serve a sua aplicação diretamente.
- **Thymeleaf e o dialeto de layout do Thymeleaf**: O **Thymeleaf** é um motor de templates que o Spring Boot usa por padrão para renderizar conteúdo HTML. Ele permite a inclusão de templates dinâmicos na sua página e o uso de dialetos personalizados (como o **layout dialect**) para facilitar a estruturação do layout de várias páginas.

### 1.4 **Surveying the Spring Landscape**
Para ter uma ideia do **ecossistema Spring**, basta olhar para a enorme lista de opções de dependências na versão completa do formulário web do **Spring Initializr**. Ela lista mais de 100 opções de dependência, então não vou tentar listar todas aqui nem fornecer uma captura de tela. No entanto, recomendo que você dê uma olhada por conta própria. Enquanto isso, mencionarei alguns dos destaques.

### 1.4.1 **O Core do Spring Framework**

Como você pode imaginar, o **core do Spring Framework** é a base de tudo no universo Spring. Ele fornece o **container central** e o **framework de injeção de dependências**. Mas ele também oferece algumas outras funcionalidades essenciais.

Entre essas funcionalidades está o **Spring MVC**, o framework web do Spring. Você já viu como usar <span style="background:#d4b106">o **Spring MVC** para escrever uma classe de controlador que lida com requisições web</span>. No entanto, o que você ainda não viu é que o <span style="background:#d4b106">**Spring MVC** também pode ser usado para criar **APIs REST** que produzem saídas que não são HTML</span>. Vamos explorar mais o **Spring MVC** no **Capítulo 2** e depois dar uma olhada em como usá-lo para criar <span style="background:#d4b106">APIs REST no **Capítulo 7**</span>.

O **core do Spring Framework** também oferece suporte elementar para persistência de dados, especificamente, suporte baseado em templates para **JDBC**. Você verá como usar o **JdbcTemplate** no **Capítulo 3**.

O Spring inclui suporte para programação reativa, incluindo um novo framework reativo para a web chamado <span style="background:#d4b106">**Spring WebFlux**</span>, que é fortemente inspirado no **Spring MVC**. Você conhecerá o modelo de programação reativa do Spring na **Parte 3**, e o **Spring WebFlux** especificamente no **Capítulo 12**.

### 1.4.2 **Spring Boot**

Já vimos muitos dos benefícios do **Spring Boot**, incluindo as dependências starter e a autoconfiguração. Tenha certeza de que usaremos o **Spring Boot** o máximo possível ao longo deste livro e evitaremos qualquer forma de configuração explícita, a menos que seja absolutamente necessário. Mas, além das dependências starter e da autoconfiguração, o **Spring Boot** também oferece os seguintes recursos úteis:

- **Actuator**: Fornece uma visão em tempo real sobre o funcionamento interno de uma aplicação, incluindo métricas, informações sobre threads, saúde da aplicação e propriedades de ambiente disponíveis para a aplicação.
    
- **Especificação flexível de propriedades de ambiente**: Permite configurar propriedades de ambiente de forma flexível para diferentes ambientes de execução.
    
- **Suporte adicional para testes**: Oferece recursos extras para testes, complementando a ajuda de testes fornecida pelo framework core.

Além disso, o **Spring Boot** oferece um modelo de programação alternativo baseado em scripts **Groovy**, chamado **Spring Boot CLI** (interface de linha de comando). Com o **Spring Boot CLI**, você pode escrever aplicações inteiras como uma coleção de scripts **Groovy** e executá-los diretamente da linha de comando. Não vamos passar muito tempo com o **Spring Boot CLI**, mas o abordaremos ocasionalmente quando for útil para nossas necessidades.

O **Spring Boot** se tornou uma parte tão essencial do desenvolvimento com Spring que não consigo imaginar o desenvolvimento de uma aplicação Spring sem ele. Consequentemente, este livro adota uma visão **centrada no Spring Boot**, e você pode me ver usando a palavra **Spring** ao me referir a algo que o **Spring Boot** está fazendo.

### 1.4.4 **Spring Security**
A segurança de aplicações sempre foi um tema importante, e parece se tornar mais relevante a cada dia. Felizmente, o **Spring** possui um framework robusto de segurança, o **Spring Security**.

O **Spring Security** abrange uma ampla gama de necessidades de segurança para aplicações, incluindo **autenticação**, **autorização** e **segurança de APIs**. Embora o escopo do **Spring Security** seja grande demais para ser coberto de maneira completa neste livro, abordaremos alguns dos casos de uso mais comuns nos **Capítulos 5 e 12**.

### 1.4.5 **Spring Integration e Spring Batch**

Em algum momento, a maioria das aplicações precisará se integrar com outras aplicações ou até mesmo com outros componentes da mesma aplicação. Diversos padrões de integração de aplicações surgiram para atender a essas necessidades. **Spring Integration** e **Spring Batch** fornecem a implementação desses padrões para aplicações Spring.

O **Spring Integration** trata da integração em tempo real, onde os dados são processados à medida que ficam disponíveis. Em contraste, o **Spring Batch** lida com a integração em lotes, onde os dados são acumulados por um período até que algum gatilho (talvez um gatilho temporal) sinalize que é hora de processar o lote de dados. Você explorará o **Spring Integration** no **Capítulo 10**.

### 1.4.6 **Spring Cloud**
O mundo do desenvolvimento de aplicações está entrando em uma nova era, onde não desenvolveremos mais nossas aplicações como monólitos de implantação única. Em vez disso, vamos compor aplicações a partir de várias unidades de implantação individuais, conhecidas como **microserviços**.

Os **microserviços** são um tema atual, abordando várias preocupações práticas de desenvolvimento e tempo de execução. No entanto, eles trazem à tona seus próprios desafios. Esses desafios são enfrentados de frente pelo **Spring Cloud**, uma coleção de projetos para desenvolver aplicações nativas para a nuvem com o **Spring**.

O **Spring Cloud** cobre uma vasta gama de tópicos, e seria impossível abordar tudo neste livro. Para uma discussão completa sobre o **Spring Cloud**, sugiro dar uma olhada no livro **Cloud Native Spring in Action**, de **Thomas Vitale** (Manning, 2020, [www.manning.com/books/cloud-native-spring-in-action](https://www.manning.com/books/cloud-native-spring-in-action)).

### 1.4.7 **Spring Native**
Um desenvolvimento relativamente novo no Spring é o projeto **Spring Native**. Este projeto experimental permite a compilação de projetos **Spring Boot** em executáveis nativos usando o compilador **GraalVM native-image**, resultando em imagens que iniciam significativamente mais rápido e têm uma pegada mais leve.

Para mais informações sobre o **Spring Native**, veja [https://github.com/spring-projects-experimental/spring-native](https://github.com/spring-projects-experimental/spring-native).