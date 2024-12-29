Esse código Java define uma classe chamada `DB`, que gerencia a conexão com um banco de dados usando o JDBC (Java Database Connectivity). Vamos explicar o que está acontecendo passo a passo.

### Estrutura do código:

1. **Pacote e importações:**

```java
package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
```

- **`package db`**: O código está organizado dentro de um pacote chamado `db`, o que ajuda a estruturar e organizar o projeto.
- **Importações**:
  - **`FileInputStream`**: Para ler o arquivo de propriedades (`db.properties`) que contém as configurações de conexão com o banco de dados.
  - **`IOException`**: Para capturar erros relacionados à leitura do arquivo de propriedades.
  - **`Connection`**: Representa a conexão com o banco de dados.
  - **`DriverManager`**: Usado para obter uma conexão com o banco de dados com base nas propriedades fornecidas.
  - **`SQLException`**: Para tratar erros relacionados à interação com o banco de dados.
  - **`Properties`**: Para carregar o arquivo de configuração de propriedades.

---

### Atributos da classe:

```java
private static Connection conn = null;
```

- **`private static Connection conn = null;`**: Isso define um atributo estático `conn`, do tipo `Connection`, que será usado para armazenar a conexão com o banco de dados.
  - **`static`**: Significa que a conexão é única para toda a aplicação (instância única). (PERTENCE A CLASSE E NÃO ÀS INSTÂNCIAS (objetos) da classe)
  - **`null`**: Inicialmente, a conexão não existe, então é definida como `null`.

---

### Método `getConnection()`: Obter a conexão com o banco de dados

```java
public static Connection getConnection() {
    if(conn == null) {
        try {
            Properties prop = loadProperties();
            String url = prop.getProperty("dburl");
            conn = DriverManager.getConnection(url, prop);
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
    return conn;
}
```

- **`public static Connection getConnection()`**: Este método é responsável por fornecer a conexão com o banco de dados. Ele verifica se a conexão (`conn`) já foi criada, e se não foi, ele a cria.
  - **Verificação `if (conn == null)`**: Se a conexão ainda não foi criada, ele entra no bloco `if` e tenta estabelecer uma nova conexão.
  
- **Carregar as propriedades**:
  ```java
  Properties prop = loadProperties();
  ```
  O método chama **`loadProperties()`** (que será explicado mais adiante) para carregar as propriedades de configuração a partir do arquivo `db.properties`.

- **Obter a URL do banco de dados**:
  ```java
  String url = prop.getProperty("dburl");
  ```
  Ele obtém o valor associado à chave `"dburl"` no arquivo de propriedades. A URL de conexão é algo como `jdbc:mysql://localhost:3306/meu_banco`.

- **Estabelecer a conexão**:
  ```java
  conn = DriverManager.getConnection(url, prop);
  ```
  Usa o `DriverManager.getConnection()` para estabelecer a conexão com o banco de dados. Ele passa a **URL** e as propriedades, que podem conter informações como nome de usuário e senha do banco de dados.

- **Tratamento de exceção**:
  ```java
  catch (SQLException e) {
      throw new DbException(e.getMessage());
  }
  ```
  Caso ocorra um erro ao tentar se conectar, ele captura a exceção `SQLException` e lança uma exceção personalizada `DbException`.

- **Retorno da conexão**:
  Se a conexão já estiver estabelecida (não for nula), ela é retornada diretamente sem recriar.

---

### Método `closeConnection()`: Fechar a conexão

```java
public static void closeConnection() {
    if (conn != null) {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
}
```

- **`closeConnection()`**: Este método fecha a conexão com o banco de dados.
  - **`if (conn != null)`**: Verifica se a conexão já foi aberta. Se for diferente de `null`, tenta fechá-la.
  - **`conn.close()`**: Fecha a conexão aberta com o banco de dados.
  - **Tratamento de exceção**: Se houver um erro ao fechar a conexão, a exceção `SQLException` será capturada e uma exceção `DbException` será lançada.

---

### Método `loadProperties()`: Carregar propriedades de um arquivo

```java
private static Properties loadProperties() {
    try (FileInputStream fs = new FileInputStream("db.properties")) {
        Properties prop = new Properties();
        prop.load(fs);
        return prop;
    } catch (IOException e) {
        throw new DbException(e.getMessage());
    }
}
```

- **`private static Properties loadProperties()`**: Este método privado lê as configurações de conexão do arquivo `db.properties`.
  - **`FileInputStream fs = new FileInputStream("db.properties")`**: Usa um `FileInputStream` para abrir e ler o arquivo `db.properties`.
  - **`Properties prop = new Properties()`**: Cria um objeto `Properties`, que será usado para armazenar as chaves e valores do arquivo.
  - **`prop.load(fs)`**: Carrega o conteúdo do arquivo `db.properties` no objeto `Properties`.
  - **Tratamento de exceção**: Se ocorrer um erro ao tentar abrir ou ler o arquivo, ele captura a exceção `IOException` e lança uma `DbException`.

### Resumo do funcionamento:

1. **Carregar propriedades**:
   - O método `loadProperties()` lê o arquivo `db.properties`, que contém informações de conexão (como URL do banco, usuário e senha).

2. **Estabelecer conexão**:
   - O método `getConnection()` utiliza essas propriedades para estabelecer uma conexão com o banco de dados, retornando essa conexão.
   - Se a conexão já existir, ela é retornada diretamente.
   
3. **Fechar conexão**:
   - O método `closeConnection()` fecha a conexão com o banco de dados, garantindo que não haja vazamento de recursos.

Essa classe facilita o gerenciamento da conexão com o banco de dados, evitando múltiplas conexões abertas desnecessariamente e fornecendo um método centralizado para acessar e fechar a conexão.

## Recuperar os dados em nossa base de dados
Precisamos entender duas classes de nossa API do JDBC:
1) #Statement: montar um comando SQL para ser executado;
2) #ResultSet: representa um objeto contendo o resultado da nossa consulta na forma de uma tabela;
#first: move para posição 1, se houver;
#beforeFirst: move para posição 0;
#next: move para o próximo, retorna false se já estiver no último;
#absolute(int): move para a posição informada, lembrando que dados reais começam em 1.


## PreparedStatement 
É uma subinterface da interface #Statement na API JDBC (Java Database Connectitivy). Ela é usada para executar instruções SQL de forma eficiente e segura. O #PreparedStatement é especialmente útil para executar instruções SQL que têm parâmetros, e oferece várias vantagens em relação ao uso do #Statement comum. 