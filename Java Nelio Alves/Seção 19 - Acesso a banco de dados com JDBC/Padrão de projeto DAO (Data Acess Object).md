Para cada entidade, haverá um objeto **responsável por fazer acesso a dados relacionado a esta entidade.** Por exemplo:
- Cliente: ClienteDao
- Produto: ProdutoDao
- Pedido: PedidoDao
Cada DAO será definido por uma **interface**. O acesso a dados ele pode mudar. Podemos usar um banco de dados MySQL e amanhá podemos mudar para Oracle. Podemos estar utilizando uma tecnologia JDBC e podemos usar outra tecnologia como um ORM. A técnica especifica de acessar um dado pode mudar. Por isso vamos definir uma #interface para manipular o banco de dados.
A injeção de dependência pode ser feita por meio do padrão de projeto Factory. 

O DAO é muito utilizado em sistemas legados ou em arquiteturas mais simples que utilizam acesso direto a banco de dados. 
O objetivo do DAO é separar a lógica de acesso a dados da lógica de negócio, facilitando a manutenção do código. 
Porém, com o tempo e o avanço de frameworks como o #Spring Data JPA, o uso do DAO de forma manual e explícita tem diminuído. Hoje, frameworks modernos conseguem automatizar boa parte do que o padrão DAO fazia, tornando seu uso menos necessário. 

Em projetos modernos, frameworks como **Spring Data JPA** e **Hibernate** já encapsulam grande parte da lógica de persistência, tornando o DAO manual menos necessário. Esses frameworks adotam o padrão **Repository**, que tem uma abordagem mais completa e automatizada para lidar com dados.