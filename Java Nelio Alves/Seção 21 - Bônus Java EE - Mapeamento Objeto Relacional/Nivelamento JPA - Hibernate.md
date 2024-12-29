A JPA é como uma <span style="background:#d4b106">ponte</span> entre Objetos Java e Banco de Dados. A JPA é uma #especificação da plataforma Java que define uma interface padrão para mapeamento objeto-relacional (ORM). Em termos simples, ela serve como uma ponte entre os objetos que criamos em nossas aplicações Java e os dados armazenados em um banco de dados relacional. 
A JPA <span style="background:#fdbfff">simplifica o processo de salvar, atualizar, deletar e buscar objetos Java</span> (também chamados de entidades) no banco de dados. Ele <span style="background:#d4b106">abstrai a complexidade do acesso ao banco de dados</span>, permitindo que os desenvolvedores trabalhem com objetos Java, enquanto ele converte esses objetos em instruções SQL para o banco de dados subjacente.


## Por que usar a JPA?
- **Abstração da persistência:** a JPA esconde a complexidade da interação direta com o banco de dados, permitindo concentrarmos na lógica do negócio. 
- **Mapeamento automático:** ela mapeia automaticamente seus objetos Java para tabelas em um banco de dados, eliminando a necessidade de escrever manualmente as instruções SQL. 
- **Produtividade:** a JPA aumenta a produtividade do desenvolvimento, pois só precisamos trabalhar com os objetos de forma natural, sem se preocupar com os detalhes de como esses dados serão persistidos. 
- **Portabilidade:** as aplicações desenvolvidas com JPA são mais portáveis, pois a troca de um banco de dados por outro geralmente envolve apenas algumas configurações. 

Para a JPA funcionar, precisamos fazer o mapeamento dos objetos. 

## Principais classes da JPA
- **EntityManager:** a principal interface do JPA, usada para interagir com o contexto de persistência. É através desta classe que realizamos operações como `persist()`,`merge()`,`remove()` e `find()`. Ele serve para efetuarmos as **operações de acesso a dados**.
- **Entity:** é uma classe Java simpless (POJO) anotada com `@Entity`, que representa uma tabela no banco de dados.  
``` Java
@Entity
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String email;
    private Double salary;
    // Getters e setters
}

```
- **Persistence:** a classe persistence é usada para criar uma instância do `EntityManagerFactory`, que, por sua vez, é usada para obter um `EntityManager`. 
``` java
EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
EntityManager em = emf.createEntityManager();
```

## Incluindo JPA para persistir os objetos em banco de dados
**Passos**
1) **Criar uma base de dados MySQL vazia:** 
- Instale o Xampp no computador;
- Iniciar o Apache e o MySQL