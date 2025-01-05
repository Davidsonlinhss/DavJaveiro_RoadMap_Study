O modelo de classes e o modelo relacional são dois paradigmas utilizados para representar e estruturar dados em diferentes contextos: um mais focado na programação orientada a objetos e o outro no gerenciamento de banco de dados relacionais. A diferença entre eles está na maneira como organizam e relacionam informações.

### 1. **Modelo de Classes (Orientado a Objetos)**
O modelo de classes é usado na programação orientada a objetos (OOP) e define como os dados e comportamentos são organizados e manipulados por meio de #objetos e #classes. Ele é uma abstração que mapeia o comportamento e as propriedades do mundo real em classes de programação.
<span style="background:#fff88f">Características principais:</span>
- **Classes**: Uma classe é um molde ou blueprint que define os atributos (dados) e métodos (comportamentos) de objetos. Cada objeto criado a partir da classe é uma **instância** dessa classe.
- **Herança**: Classes podem herdar características (atributos e métodos) de outras classes, permitindo a reutilização de código.
- **Polimorfismo e Encapsulamento**: O polimorfismo permite que um objeto se comporte de diferentes maneiras dependendo do contexto, enquanto o encapsulamento protege os dados internos do objeto, tornando-os acessíveis apenas através de métodos públicos.
- **Relacionamentos**: As classes podem se relacionar por meio de composições, agregações e associações, representando ligações entre objetos no código.

### 2. **Modelo Relacional (Bancos de Dados)**
O **modelo relacional** é usado em sistemas de gerenciamento de banco de dados relacionais (RDBMS) e organiza os dados em **tabelas** (também chamadas de relações). Cada tabela é composta de linhas e colunas, onde:
- As **linhas** representam **registros** (instâncias) dos dados.
- As **colunas** representam os **atributos** (ou campos) dos registros.

#### Características principais:
- **Tabelas**: Dados são armazenados em tabelas, que possuem colunas para definir os tipos de dados e linhas para os registros. Cada tabela representa uma entidade (por exemplo, uma pessoa ou um produto).
- **Chaves primárias e estrangeiras**: O modelo relacional usa chaves primárias para identificar exclusivamente cada registro em uma tabela, e chaves estrangeiras para estabelecer relacionamentos entre diferentes tabelas.
- **Normalização**: O processo de normalização é usado para minimizar a redundância de dados, dividindo os dados em várias tabelas relacionadas.
- **Consultas SQL**: A manipulação dos dados é feita usando a linguagem SQL (Structured Query Language), que permite inserir, atualizar, excluir e consultar dados.

### Comparação: **Modelo de Classes vs. Modelo Relacional**

| <font color="#ff0000">Característica</font> | <font color="#ff0000">Modelo de Classes (OOP)</font> | <font color="#ff0000">Modelo Relacional (RDBMS)</font>                |
| ------------------------------------------- | ---------------------------------------------------- | ---------------------------------------- |
| **Estrutura de Dados**                      | Classes e Objetos                                    | Tabelas, Linhas e Colunas                |
| **Representação**                           | Atributos e métodos de objetos                       | Atributos (colunas) e registros (linhas) |
| **Relacionamentos**                         | Associações, Composição, Herança                     | Chaves Primárias e Estrangeiras          |
| **Forma de Manipulação**                    | Métodos e propriedades de classes                    | SQL (consultas e manipulações de dados)  |
| **Persistência**                            | Objetos em memória                                   | Dados armazenados em disco (tabelas)     |
| **Uso**                                     | Desenvolvimento de software (lógica)                 | Armazenamento e recuperação de dados     |

A álgebra relacional é um formalismo que define operações básicas:
- Restrição
- Projeção
- Produto Cartesiano:
- Junção

**<span style="background:#d4b106">Produto cartesiano em SQL</span>**
```sql
SELECT * FROM PRODUCT, CATEGORY // busca todos os dados
```

O produto cartesiano é o resultado da combinação de cada registro de categoria com cada registro de produto. E esse resultado normalmente será uma tabela.

**<span style="background:#d4b106">Operação Junção</span>**
A sintaxe da operação operação Junção é feita da seguinte forma:
```SQL
SELECT * FROM PRODUCT, CATEGORY WHERE PRODUCT.CATEGORY_ID = CATEGORY.ID;
```

Segunda opção para fazer uma junção usando INNER JOIN
```SQL
SELECT * FROM PRODUCT INNER JOIN CATEGORY cat ON PRODUCT.CATEGORY_ID = cat.ID;
```

**<span style="background:#d4b106">Operação RESTRIÇÃO</span>
```SQL
SELECT FROM PRODUCT INNER JOIN CATEGORY cat ON PRODUCT.CATEGORY_ID = cat.ID WHERE CATEGORY.NAME = 'Computers';
```

**<span style="background:#d4b106">Operação PROJEÇÃO</span>

Vamos super que não queiramos todos os dados. 
```SQL
SELECT PRODUCT.*, CATEGORY.NAME FROM PRODUCT INNER JOIN CATEGORY cat ON PRODUCT.CATEGORY_ID = cat.ID WHERE CATEGORY.NAME = 'Computers';
```