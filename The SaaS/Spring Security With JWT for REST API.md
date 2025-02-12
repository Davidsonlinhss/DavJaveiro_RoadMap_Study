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
Quando adicionamos o Spring Security ao nosso aplicativo, ele registra automaticamente uma cadeia de filtros que intercepta todas as solicitações de entrada. Essa cadeia consiste em vários filtros, e cada um deles lida com um caso de uso específico. 

- Verificar se a URL solicitada é acessível publicamente, com base na configuração.
- Em caso de autenticação baseada em sessão, verificar se o usuário já está autenticado na sessão atual. 
- Verifique se o usuário está autorizado a executar a ação solicitada, e assim por diante. 

Um detalhe importante que quero mencionar é que os filtros do Spring Security são registrados com a ordem mais baixa e são os primeiros filtros invocados. Para alguns casos de uso, se quisermos colocar o nosso filtro personalizados na frente deles, precisamos adicionar #padding à ordem deles. Isso pode ser feito com a seguinte configuração:
```json
spring.security.filter.order=10
```

Depois de adicionar essa configuração ao nosso *properties*, teremos espaço para 10 filtros personalizados na frente dos filtros do Spring Security.

## Gerenciador de autenticação
Podemos pensar *AuthenticationManager* como um coordenador onde podemos registrar vários provedores e, com base no tipo de solicitação, ele entregará uma solicitação de autenticação ao provedor correto. 


## Provedor de autenticação
*AuthenticationProvider* processa tipos específicos de autenticação. Sua interface expõe apenas duas funções:
- *authenticate* realiza autenticação com a solicitação;
- *supports* verifica se este provedor suporta o tipo de autenticação indicado. 

Uma implementação importante da interface que estamos usando em nosso projeto de exemplo é *DaoAuthenticationProvider*, que recupera detalhes do usuário de um *UserDetailService*.

## Detalhes do userService
*UserDetailService* é descrito como uma interface central que carrega dados específicos do usuário na documentação do Spring. 

Na maioria dos casos de uso, os provedores de autenticação extraem informações de identidade do usuário com base em **credenciais de um banco de dados** e, em seguida, realizam a validação. Como esse caso de uso é tão comum, os desenvolvedores do **Spring decidiram extraí-la como uma interface separada**, que expõe a função única:
- *loadUserByUserName* aceita nome de usuário como parâmetro e retorna o objeto de identidade do usuário.

## Autenticação usando JWT com Spring Security
Depois de discutirmos os aspectos internos do framework Spring Security, vamos configurá-lo para autenticação sem estado com um *token JWT*.  
![[https://www.toptal.com/web/cookie-free-authentication-with-json-web-tokens-an-example-in-laravel-and-angularjs | Token JWT]]

Para personalizar o Spring Security para uso do JW, precisamos de uma classe de configuração anotada com *@EnableWebSecurity* annotation em nosso classpath. Além disso, para simplificar o processo de personalização, o framework expõe uma *webSecurityConfigurerAdapter* classe. Estenderemos esse adaptador e substituiremos ambas as suas funções para:

- Configure o gerenciador de autenticação com o provedor correto;
- Configura a segurança da web (URLs públicas, URLs privadas, autorização, etc).

[[SecurityConfig.java]]

Em nosso aplicativo de exemplo, armazenamos identidades de usuários em um banco de dados MongoDB, na *users* collection. 

*@EnableWebSecurity* ativa a configuração de segurança do Spring Security.

**Método** *configure(AuthenticationManagerBuild auth*: esse método serve para configurar a autenticação dos usuários. Exemplo de configuração em memória:
```java
@Override
protected void configure(AuthenticatiManagerBuilder auth) throws Exception {
	auth.inMemoryAuthentication()
		.withUser("admin")
		.password("{noop}1234")
		.roles("ADMIN");
}
```

**Método** *configure(HttpSecurity http)* esse método define as regras de autorização para as requisições HTTP:
```java
@Override
protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable() // Desativa proteção CSRF (não recomendado em produção)
        .authorizeRequests()
        .antMatchers("/admin").hasRole("ADMIN") // Apenas ADMIN acessa /admin
        .antMatchers("/").permitAll() // Página inicial aberta a todos
        .and()
        .formLogin(); // Ativa autenticação via formulário
}

```

Se estivermos utilizando a versão do Spring Security 5.7+, a melhor abordagem é usar o #SecurityFilterChain.

---
## **1️⃣ O que são filtros (`Filter`) em Java?**
No Java EE (Jakarta EE), um *filtro* #filter é um componente que pode *interceptar requisições HTTP* antes que elas cheguem a um #servlet. Ele pode modificar a requisição ou a resposta antes de serem processados.

```java
@WebFilter("/meu-endpoint")
public class MeuFiltro implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("Antes do servlet");

		chain.doFilter(request, response);

		System.out.println("Depois do servlet");
	}
}
```

## **2️⃣ Como o Spring Security usa filtros?**
O Spring Security usa o conceito de filtros para interceptar todas as requisições HTTP e aplicar regras de segurança, como:
- Autenticação de usuários;
- Autorização de acessos
- Proteção contra ataques (CSRF, CORS, etc.)
- Gerenciamento de sessões

## **3️⃣ O que é `SecurityFilterChain`?**
O #SecurityFilterChain representa uma sequência de filtros que processam a requisição antes de chegar ao controlador Spring *@RestController, @Controller*.

## **4️⃣ Como o Spring Security processa uma requisição?**
1. Um usuário faz uma requisição */admin*.
2. - Um usuário faz uma requisição para `/admin`.
3. O **Spring Security intercepta** a requisição usando filtros internos (como `UsernamePasswordAuthenticationFilter`).
4. O `SecurityFilterChain` verifica se o usuário está autenticado e tem permissão.
5. Se for permitido, a requisição segue para o controlador (`@RestController`).
6. Se não for permitido, o Spring Security retorna um erro `403 Forbidden` ou redireciona para a página de login.

---

Agora, quando aceitamos a solicitação de autenticação, precisamos recuperar a identidade correta do banco de dados usando as credenciais fornecidas e então verificá-la. Para isso, precisamos da implementação da *UserDetailsService* interface, que é definida da seguinte forma:
```java
public interface UserDetailsService {
	UserDetails loadUserByUserName(String username) throws UserNameNotFoundException;
}
```

Aqui, podemos ver que é necessário retornar o objeto que implementa a *UserDetails* interface, e nossa *User* entidade o implementa (para detalhes de implementação, consulte o repositório do projeto de exemplo). Considerando o fato de que ele expõe apenas o protótipo de função única, podemos tratá-lo como uma interface funcional e fornecer implementação como uma expressão lambda.

