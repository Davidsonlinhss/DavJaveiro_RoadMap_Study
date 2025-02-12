O Spring Security é a estrutura de fato para proteger aplicativos Spring, mas pode ser complicado de configurar.

Spring é considerado um framework confiável no ecossistema Java e é amplamente utilizado. Não é mais válido se referir ao Spring como um framework, pois é mais um termo abrangente que abrange vários frameworks. Um desses frameworks é o **Spring Security**, que é um framework de autenticação e autorização poderoso e personalizável. Ele é considerado o padrão de fato para proteger aplicativos baseados em Spring. Logo, estamos procurando implementar uma solução de token Spring JWT, faz sentido baseá-la no Spring Security.

Apesar de sua popularidade, quando se trata de *single-page application*, o Spring não é simples e direto de configurar. Suspetamos que o motivo é que ele começou mais como um framework orientado a aplicativos MVC, onde a renderização de páginas da web acontece no lado do servidor e a comunicação é baseada em #sessão.

Se o backend for baseado em Java e Spring, faz sentido usar o Spring Security com JWT para autenticação/autorização e configurá-lo para comunicação sem estado. Embora existam muitos artigos explicando como isso é feito, ainda é frustrante configurá-lo pela primeira vez. 

## Definindo Terminologia
Antes de mergulharmos nos detalhes técnicos, precisamos definir explicitamente a terminologia usada no contexto do Spring Security apenas para ter certeza de que todos falamos a mesma língua.

- **Autenticação:** se refere ao processo de verificação da identidade de um usuário, com base nas credenciais fornecidas. Um exemplo comum é inserir um nome de usuário e uma senha ao fazer login em um site. 
- **Autorização:** se refere ao processo de determinar se um usuário tem permissão adequada para executar uma ação específica ou ler dados específicos, assumindo que o usuário seja autenticado com sucesso. Podemos pensar nisso como uma resposta à pergunta *Um usuário pode fazer/ler isso?*

- **Princípio:** refere-se ao usuário atualmente autenticado.
- **Autoridade concedida:** refere-se à permissão do usuário autenticado.
- **Função:** refere-se a um grupo de permissões do usuário autenticado.

## Criando um aplicativo Spring básico
Antes de passar para a configuração do framework Spring Security, vamos criar um aplicativo web Spring básico. Para isso, podemos usar um [Spring Initializr](https://start.spring.io/) e gerar um projeto de template. Para um aplicativo web simples, apenas uma dependência do framework web Spring é suficiente:

Depois de criarmos o projeto, podemos adicionar um controlador REST simples:
```java
@RestController @RequestMapping("hello")
public class HelloRestController{
...
}
```

[[HelloRestController.java]]

Agora, podemos adicionar o framework Spring Security ao nosso projeto, e podemos fazer isso adicionando a seguinte dependência ao nosso pom.xml:

Ao adicionarmos o Spring Security, há um efeito imediato, após adicionarmos, e ao tentarmos executar o projeto, novamente, receberemos outro resultado, uma rota direcionada para *http://localhost:8080/logi*. Esse é um comportamento padrão, visto que o Spring Security Framework requer autenticação pronto para uso para todas as URLs.

Para passar pela autenticação, basta usarmos o nome de usuário padrão *User* e encontrar uma senha gerada automaticamente em nosso console:
*Using generated security password:*

A senha muda cada vez que executarmos novamente o aplicativo. Se quisermos mudar esse comportamento e tornar a senha estática, podemos adicionar a seguinte configuração ao nosso *application.properties*:
*spring.security.user.password=Teste12345*

O processo de autenticação pronto para uso é baseado em sessão e, se quisermos fazer logout, podemos acessarmos o endpoint logout.

Esse comportamento pronto para uso pode ser útil para aplicativos web MVC clássicos em que temos autenticação baseada em sessão, mas no caso de aplicativos de página única, geralmente não é útil porque, na maioria das vezes, temos renderização do lado do cliente e autenticação sem estado baseada em JWT. Neste caso, teremos que personalizar bastante a estrutura do Spring Security.

## Visão geral da arquitetura do Spring Security

![[Spring Security With JWT for REST API.png]]

Vamos dividir o diagrama em componentes e discutir cada um deles separadamente.

## Cadeia de filtros de segurança de mola
