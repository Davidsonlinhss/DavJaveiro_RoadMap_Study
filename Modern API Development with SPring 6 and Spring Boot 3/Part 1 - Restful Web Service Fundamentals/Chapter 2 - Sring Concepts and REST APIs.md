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
Programas tradicionais CLI (Command Line Interface) são o método típico para implementações de programação procedural, onde o fluxo é determinado pelo programador e o código é executado sequencialmente, ou seja, uma etapa após a outra. No entanto, aplicações baseadas em interface gráfica (UI) determinam o fluxo dos programas com base nas entradas e eventos do usuário, que são dinâmicos. 

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

No entanto, em modelos de programação, muitas vezes é necessário implementar funcionalidades que se estendem por várias classes. Em algumas aplicações, a maioria das classes utiliza recursos como logging (registro de logs) ou métricas. Funcionalidades como **logging**, segurança, gerenciamento de transações e métricas são exigidas em várias classes/módulos. O código dessas funcionalidades geralmente fica espalhado por diversas classes. Na OPP, não há uma forma nativa de abstrair e encapsular esses recursos. É aqui que o AOP entra em cena. 

Essas funcionalidades, conhecidas como #aspectos, são preocupações transversais que atravessam vários pontos no modelo de objetos. O AOP fornece uma maneira de lidar com esses aspectos de forma consistente em várias classes/módulos. 

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
Escrevemos os objetos de negócio e fornecemos o metadado de configuração, e o contêiner Spring gera um sistema totalmente configurado e pronto para uso, como mostrado na figura 2.1:
![[Chapter 2 - Sring Concepts and REST APIs.png]]
Agora que você tem uma ideia de como os beans são gerenciados, vamos aprender mais sobre o que é um bean e o que ele pode fazer.

## Defining a bean and its scope
Os #Beans são objetos Java gerenciados pelos contêineres de Inversão de Controle (IoC). O desenvolvedor fornece os metadados de configuração para um contêiner IoC, que utiliza esses metadados para construir, montar e gerenciar os beans. Cada bean deve ter um identificador único dentro de um contêiner. Um bean pode até mesmo possuir mais de uma identidade, utilizando um alias.

Vamos declarar um bean simples utilizando uma configuração baseada em Java:
```java
public class SampleBean {
	public void init() { // initialization logic }
	public void destroy() { // destruction logic }
	// bean code
}

public interface BeanInterface {
	// interface code
}

public class BeanInterfaceImpl implements BeanInterface
```