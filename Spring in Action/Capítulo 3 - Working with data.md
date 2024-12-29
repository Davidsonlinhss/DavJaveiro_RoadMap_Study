This chapter covers
- Using Springs JdbcTemplate
- Creating Spring Data JDBC repositories
- Declaring JPA repositories with Spring Data

A maioria das aplicações oferece mais do que apenas uma #interface bonita. Embora a interface do usuário possa fornecer interação com uma aplicação, são os dados que ela apresenta e armazena que separam as aplicações dos sites estáticos. Na aplicação Taco Cloud que estamos criando, precisamos ser capazes de manter informações sobre ingredientes, tacos e pedidos. Sem um <span style="background:#d4b106">banco de dados</span> para armazenar essas informações, a aplicação não poderia progredir muito além do que já desenvolvemos. Neste capítulo, adicionaremos #persistência de dados à aplicação. Começaremos usando o suporte do Spring para JDBC (Java Database Connectivity) para eliminar código repetitivo (código boilerplate). Em seguida, iremos refatorar os repositórios de dados para trabalhar com JPA (Java Persistence API), eliminando ainda mais código.

Em Java, configurações de conexão de banco de dados e configurações de mapeamento ORM são frequentemente considerados código boilerplaaate.

## 3.1 Lendo e escrevendo dados com JDBC
Durante décadas, banco de dados #relacionais e SQL desfrutaram de sua posição como escolha principal para persistência de dados.  Mesmo que muitos tipos alternativos de banco de dados tenham surgido nos últimos anos, o banco de dados relacional ainda é a principal escolha para armazenamento de dados de propósito geral e provavelmente não será usurpado de sua posição tão cedo.

As duas escolhas principais para se trabalhar com banco de dados em Java, JDBC e JPA. 

Realizando uma consulta simples em Java sem o uso do JDBCTemplate, pode tornar o código extremamente confuso e verboso, além de se tornar mais propenso a erros. Quando utilizamos o JDBCTemplate do Spring, muito código é abstraído, tornando o código mais limpo, fácil de entender e manter. 

Código com uso do JDBCTemplate se concentra exclusivamente em executar uma consulta (a chamada para o método query()) do JDBcTemplate e mapear os resultados para um objeto. 

### 3.1.1 Adaptação do domínio para persistência
Quando persistimos objetos em um banco de dados, é uma boa ideia ter um campo que identifique unicamente o objeto. Nossa classe #Ingredient já possui um campo id. Também é útil saber quando um item do pedido é criado e quando o pedido é feito, logo, precisaremos adicionar um campo em cada objeto para capturar a data e a hora em que os objetos são salvos. 

- Campo #Id: Campo utilizado para identificar unicamente cada objeto no banco de dados. É essencial para distinguir entre diferentes instâncias do mesmo tipo de objeto. O campo Id permite que identifiquemos diferentes tipos de dados armazenados no banco de dados. 
- createdAt: este campo armazena a data e a hora em que o objeto foi criado. É útil para rastrear quando um Taco foi feito e quando um TacoOrder foi realizado permitindo auditoria e histórico.

Como estamos utilizando o Lombok para gerar automaticamente os métodos acessores em tempo de execução, não é necessário fazer nada além da declaração das propriedades id e createdAt.  Eles terão métodos getter e setter apropriados conforme necessário em tempo de execução. 

Após adicionarmos o campo Id e Date para identificarmos cada objeto e quando eles foram criados no banco de dados, nossas classes de domínio estão prontas. Vamos agora ver como usar o JdbcTemplate para de fato realizar persistência em um banco de dados.

Usamos #Serializable em Java para habilitar a serialização de um objeto, que é o processo de converter o estado do objeto em um formato que pode ser gravado em um arquivo, enviado através da rede ou armazenado em um banco de dados. Quando um objeto é serializado, suas variáveis de instância (estado) são convertidas em uma sequência de bytes.

Ao implementarmos #Serializable, estamos dizendo que a classe TacoOrder <span style="background:#d4b106">pode ser convertida para bytes e reconstruída a partir desses bytes</span>, preservando o estado dos seus campos #id e #placedAt...

### 3.1.2 Working with JdbcTemplate
Antes de começarmos a usar o JdbcTemplate, precisamos adicioná-lo em nossa #classpath (dependência) do projeto. 

Vamos precisar de um banco de dados onde os nossos dados serão armazenados. 

**Definindo os repositórios JDBC**
O nosso #Repository de ingredientes precisa realizar as seguintes operações:
1. Consultar todos os ingredientes em uma coleção de objetos Ingredient;
2. Consultar um único Ingredient pelo seu id;
3. Salvar um objeto Ingredient. A interface IngredientRepository a seguir define essas três operações como declarações de métodos. 

Usar uma #interface em vez de uma classe para definir um repositório em Java <span style="background:#d4b106">tem algumas vantagens importantes</span>, especialmente em termos de design de software e flexibilidade:
1. Abstração e Flexibilidade: uma interface permite definir um <span style="background:#b1ffff">contrato ou conjunto de operações que um repositório deve implementar</span>. Isso significa que devemos ter múltiplas implementações dessa interface, oferecendo flexibilidade para mudar a implementação sem alterar o código que depende da interface.
2. Injeção de Dependência: com interfaces, é fácil usar técnicas como injeção de dependência para fornecer diferentes implementações da interface em tempo de execução. Isso é crucial para testes unitários, onde podemos usar #mocks ou #stubs.

Uso da classe #Optional: Optional é usada para lidar com <span style="background:#fdbfff">valores que podem ou não estar presentes</span>, oferecendo uma maneira de evitar null diretamente. 

#### **A nossa interface contém três métodos**:
#Iterable - Este método deve retornar uma coleção de todos os objetos Ingredient armazenados;
```java
@Override  
public Iterable<Ingredient> findAll() {  
    return jdbcTemplate.query(  // jdbcTemplate - query row...
            "select id, name, type from Ingredient",  
            this::mapRowToIngredient);  
}
```
---

#Optional<> findById(String id) - Este método deve buscar um objeto pelo seu Id. Retorna um Optional<>, que contém o ingrediente se ele for encontrado, ou vazio se não for. Com o uso do Optinal, evitamos problemas com retornos null;
```java
@Override  
public Optional<Ingredient> findById(String id) {  
    List<Ingredient> results = jdbcTemplate.query(  
            "select id, name, type from Ingredient where id=?",  
            this::mapRowToIngredient, id);  
  
    return results.size() == 0 ?  
            Optional.empty() :  
            Optional.of(results.get(0));  
}
```
---

Ingredient #save(Ingredient ingredient) - esse método salvará o objeto e retorna o objeto salvo.
```java
@Override  
public Ingredient save(Ingredient ingredient) {  
    jdbcTemplate.update(  
            "insert into Ingredient (id, name, type) values (?, ?, ?)",  
            ingredient.getId(), ingredient.getName(), ingredient.getType().toString()  
    );  
    return ingredient;  
}
```

---
Embora a interface capture a essência do que precisamos que um repositório faça, precisamos ainda escrever uma implementação da classe que use o JdbcTemplate para consultar o banco de dados. O código mostrado a seguir é o primeiro passo na escrita dessa implementação:

#Reposity
Ao anotarmos a classe com @Repository, declaramos que ele deve ser automaticamente descoberto pela varredura de componentes do Spring e instanciado como um bean dentro do Spring Context Application.

---
#Autowired
Com o uso do @Autowired é como se estivessemos dizendo ao Spring" Ei, eu preciso de uma instância deste objeto aqui". O spring encontra e injeta essa instancia para nós. Essa funcionalidade é parte do mecanismo de inversão de controle (IoC) do framework, que ajuda a desacoplar a criação e o gerenciamento de dependências no código. 

Quando injetamos, criamos uma variável de instancia que será usada em outros métodos para consultar e inserir dados no banco.  Com o uso da anotação @Autowired, não precisamos criar o objeto manualmente. 
<span style="background:#d4b106">Sem uso do @Autowired:</span>
```java
public class JdbcIngredientRepository implements IngredientRepository {
    private JdbcTemplate jdbcTemplate;

    public JdbcIngredientRepository() {
        this.jdbcTemplate = new JdbcTemplate(); // Criação manual do objeto
    }
    // Outros métodos...
}

```

<span style="background:#d4b106">Como uso do @Autowired:</span>
```java
@Repository
public class JdbcIngredientRepository implements IngredientRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate; // Injeção automática do objeto
    }
    // Outros métodos...
}

```

Tanto  o método findAll() quanto o método findById() usam JdbcTemplate de maneira semelhante. O método findAll(), esperando retornar uma coleção de objetos, usa o método query() de JdbcTemplate. O método query() aceita o SQL da consulta, bem como uma implementação do RowMapper.

**Vantagens**
1. **Reduz o acoplamento**: o código não precisa gerenciar manualmente a criação ou inicialização de dependências. Isso segue o princípio de injeção de dependências.
2. **Facilidade de teste**: ao usar a injeção de dependências via construtor, é fácil substituir dependências reais por implementações simuladas ( #mocks ) durante os testes.
3. **Melhor legibilidade e simplicidade:** a anotação elimina a necessidade de escrever código explícito para localizar ou instanciar dependências.
4. **Integração com o Spring Context:** permite aproveitar as capacidades do contêiner Spring para gerenciar o ciclo de vida dos beans e resolver automaticamente dependências complexas.

**Por que usar injeção pelo construtor com @Autowired?**
1. Dependências *obrigatórias*: quando uma dependência é obrigatória, a injeção pelo construtor garante que ela esteja sempre disponível, pois a classe não pode ser instanciada sem o parâmetro. 
2. *Imutabilidade*: a dependência é injetada no momento da criação e não pode ser alterada posteriormente, promovendo segurança e consistência.
3. Facilidade no uso de *testes*: testes unitários podem passar dependências simuladas no construtor diretamente.
---

Ler dados de um banco de dados é apenas parte da história. Em algum momento, os dados precisam ser escritos no banco de dados para que possam ser lidos. Vamos ver como implementar o método save(). 

**Inserting a Row**
O método update() do JdbcTemplate pode ser usado para qualquer consulta que escreva ou atualize dados no banco de dados. E, como mostrado na listagem a seguir, ele pode ser usado para inserir dados no banco de dados.

Exemplo
```java
@Override  
public Ingredient save(Ingredient ingredient) {  
    jdbcTemplate.update(  
            "insert into Ingredient (id, name, type) values (?, ?, ?)",  
            ingredient.getId(), ingredient.getName(), ingredient.getType().toString()  
    );  
    return ingredient;  
}
```

Como não é necessário mapear os dados do ResultSet para um objeto, o método update é muitos mais simples que o query(). Ele requer apenas uma String contendo o SQL a ser executado, além de valores para atribuir a quaisquer parâmetros da consulta. Neste caso, a consulta possui três parâmtros, que correspondem aos três últimos parâmetros do método save(), fornecendo o ID, o nome e o tipo de ingrediente. 

Como o JdbcIngredientRepository completo, agora podemos injetá-lo no DesignTacoController e usá-lo para <span style="background:#d4b106">fornecer uma lista de objetos Ingredient</span> em vez de usar valores codificados. 

O método addIngredientsToModel() utiliza o método findAll() do IngredientRepository injetado para buscar todos os ingredients do bando de dados. Em seguida, ele filtra em tipos distintos de ingredientes antes de adicioná-los ao modelo.
```java
@ModelAttribute 
public void addIngredientToModel(Model model) { 
	Iterable<Ingredient> ingredients = ingredientRepo.findAll(); 
	Type[] types = Ingredient.Type.values(); 
	for (Type type : types) { 
		model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type)); } }
```
Destrinchando o método
1. ModelAttribute: indica que o método deve ser chamado antes de qualquer método de manipulador de requisição (como os anotados com @GetMapping ou @PostMapping). O objetivo é adicionar atributos ao Model para que eles estejam disponíveis em todas as requisições.

2. **Recuperação de Ingredientes**: 
 `Iterable<Ingredient> ingredients = ingredientRepo.findAll();`
 O método findAll do repositório é chamado para recuperar todos os ingredientes do banco de dados. O resultado é uma coleção #iterável de objetos Ingredient. 

3. **Obtenção dos Tipos de Ingredientes:**
Type[] types = Ingredient.Type. values();
Obtém todos os valores do enum Ingredient.Type, representando os diferentes tipos de ingredientes.

Agoora que temos um IngredientRepository de onde buscar objetos Ingredient, também podemos simplificar o IngredientByIdConverter que criamos no capítulo 2. Substituindo seu Map de objetos Ingredient codificados por uma simples chamada ao método IngredientRepository.findById().

Agora vamos criar a tabela no banco de dados e inserir os ingredientes nela. 

### 3.1.3 Definindo um esquema e pré-carregamento
Além da tabela Ingredient, vamos precisar de algumas tabelas que armazenam informações de pedidos e de design. A figura abaixo ilustra as tabelas que vamos precisar, bem como os relacionamentos entre essas tabelas. 
![[Capítulo 3 - Working with data.png]]

As tabelas cumprem os seguintes propósitos:
- Taco_Order: contém detalhes essenciais do pedido;
- **Taco**: contém informações essenciais sobre a montagem de um Taco;
- **Ingredient_Ref**: contém uma ou mais linhas para cada linha em Taco, mapeando o taco para os ingredientes desse taco;
- **Ingredient**: contém informações sobre os ingredientes.

Em nossa aplicação, um Taco não pode existir fora do contexto de um Taco_Order. Assim, Taco_Order e Taco são considerados membros de um agregado onde Taco_Order é a raiz do agregado. Objetos Ingredient, por outro lado, são membros únicos de seu próprio agregado e são referenciados por Taco por meio de Ingredient_Ref.

---
NOTA
#Aggregates e aggregates #roots são conceitos fundamentais do design orientado por domínio DDD, uma abordagem de design que promove a ideia de que a estrutura e a linguagem do código de software devem corresponder ao domínio de negócios. 

---
Onde devemos colocar o schema? Acontece que o Spring Boot responde a essa pergunta. Se houver um arquivo chamado schema.sql na raiz do classpath da aplicação, o SQL nesse arquivo será executado contra o banco de dados quando a aplicação iniciar. Portanto, devemos colocar o schema em nosso projeto como um arquivo chamado schema.sql na pasta src/main/resourcers.

### 3.1.4 Inserindo dados
Já tivemos uma visão de como usar o JdbcTemplate para gravar dados no banco de dados. O método #save() no JdbcIngredientRepository utilizou o método update() do JdbcTemplate para salvar objetos Ingredient. 

Em nosso aplicativo, TacoOrder e Taco <span style="background:#d4b106">fazem parte de um agregado em que TacoOrder é a raiz do agregado</span>. Objetos Taco não existem fora do contexto de um Pedido de Taco, ou TacoOrder. Portanto, por enquanto, precisamos apenas definir um repositório para persistir objetos TacoOrder e, por sua vez, os objetos Taco junto com eles. Tal repositório pode ser definido em uma #interface OrderRepository:
```java
package tacos;
import lombok.Data;
@Data
public class IngredientRef {
	private final String ingredient;
}
```

Quando salvamos um pedido, precisamos também salvar os objetos Taco que o acompanham. E quando salvamos Taco, também precisamos salvar um objeto que representa a ligação entre o Taco e cada Ingredient que compõe o taco. A classe IngredientRef define essa ligação Taco e Ingredient da seguinte forma: (DTO = Data Transfer Object)

#DTO - são objetos que transferem dados entre processos, geralmente usados para transferir dados entre a camada de apresentação e a camada de persistência em uma aplicação. São frequentemente utilizados para encapsular dados que precisam ser enviado pela rede ou entre diferentes partes de um sistema, reduzindo a quantidade de chamadas necessárias e melhorando a eficiência. 

O método save() será um pouco mais interessante do que o método correspondente que criamos anteriormente para salvar um humilde objeto #Ingredient.

The saveTaco() method is quite similar to the save() method, as you can see
here:

Road Map do DavJaveiro: 
1) Java para iniciantes - Herbert Schildt;
	*Após a conclusão iniciar - Desenvolvimento Real de Software - Urma & Warburton
2) Spring In Action - Craig Walls;
3) Java Persistence with Spring Data - Catalin Tudose.