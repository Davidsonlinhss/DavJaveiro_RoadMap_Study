# ![image](https://github.com/user-attachments/assets/ba18de02-632e-4f53-a110-9309eb158ca5)
Projeto criado com base no curso do Nélio Alves, realizado através da plataforma Udemy.

# Padrão de projeto DAO (Data Access Object)
A estrutura do projeto segue o padrão de design **DAO** (Data Acess Object), que é amplamente utilizada para organizar o código que interage com o banco de dados, separando a lógica de persistência da lógica de negócios, com isso o acesso ao banco de dados pode ser alterado ou melhorado sem afetar outras partes do sistema, além de proporcionar uma melhor organização do código. 
Como exemplo, o nosso projeto contará com duas tabelas de banco de dados, uma department (contendo os departamentos) e uma tabela seller (vendedores). 
## 1. Camada entities (entidades)
### Department e **Seller:**
Representam os modelos de dados, ou seja, classes que representam as **entidades** no sistema. Cada uma dessas classes tem atributos que correspondem às colunas de uma tabela no banco de dados. 
- **Department:** a classe departament, representa um departamento, ou um setor, contendo atributos como #id, #name, fizemos uso do recurso #wrappers, que basicamente envolvem classes ou tipos primitivos, como exemplo, id que é do tipo Integer, com isso, o tipo int passa a ser tratado como classe, adicionando métodos (funcionalidades) a ela, a partir disso podemos executar métodos como valueOf, parseInt etc. A classe também possui métodos construtores padrões e parametrizados. 
- **Seller:** representando um vendedor, possui atributos id, name, email, birthDate, baseSalary e department. Neste caso, usamos o department como atributo, pois fazemos uso de modelos de relacionamento entre entidades, onde cada vendedor está associado a um departamento, logo, com uma relação de "muitos para um", logo a entidade possui um atributo que faz referência a um objeto da classe **department**.
Essas classes mapeiam os dados que vêm do banco de dados para objetos no nosso sistema (ORM básico, mesmo sem frameworks como JPA).

## 2. Camada DAO (Data Access Object)
### Interfaces
A camada DAO fornece as interfaces (DepartmentDao e SellerDao), ou seja, estamos definindo o que as classes devem fazer, mas não como ela fará. Logo, nas interfaces não estamos fornecendo a implementação, ou lógica por trás dos métodos. 
### Implementações
- **SellerDaoJDBC** é a implementação concreta da interface SellerDao. Ela define como os métodos da interface serão realmente executados, usando JDBC (API que serve como ponte entre as aplicações e banco de dados relacionais, permitindo conexões a diversos bancos de dados, execução de consultas, Select, Insert, Update e Delete e manipulação de resultados, permitindo a exibição dos dados em uma interface gráfica ou armazená-los em outras estruturas de dados). 

## 3. Fábrica de DAOs (DaoFactory):
### DaoFactory 
É uma classe responsável por fornecer instâncias de DAOs. Ao invés de instanciar diretamente DAO no código, usamos a fábrica, o que facilita a troca de implementação no futuro. Por exemplo, caso queiramos alterar de JDBC para outro método de acesso, como (JPA, Hibernate, etc), basta alterarmos a implementação dentro da fábrica.
Quando precisarmos usar um DAO em nossa aplicação, nós chamaremos o DaoFactory para criar instância, exemplo:
``` java
public class Main {
    public static void main(String[] args) {
        SellerDao sellerDao = DaoFactory.createSellerDao(); // Cria o DAO
        List<Seller> sellers = sellerDao.findAll(); // Usa o DAO para buscar dados

        for (Seller seller : sellers) {
            System.out.println(seller);
        }
    }
}
```
## 4. MySQL (Data)
<p> O nosso aplicativo conta com banco de dados denominado jdbc, contendo duas tabelas relacionais, seller e department. <p>

 Portanto, o nosso projeto básico usa o padrão DAO que visa **isolar a lógica de acesso ao banco de dados** da lógica de negócios da aplicação. A vantagem é que o acesso ao banco de dados pode ser alterado ou melhorado sem afetar outras partes do sistema, além de proporcionar uma melhor organização do código.
 
 Referências:
https://www.devmedia.com.br/dao-pattern-persistencia-de-dados-utilizando-o-padrao-dao/30999
https://www.oracle.com/technetwork/java/dataaccessobject-138824.html.


