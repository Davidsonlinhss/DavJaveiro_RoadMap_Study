# Manipulação de Banco de Dados MySQL
## **Selecionando um Banco de Dados**
Para selecionar um banco de dados, use o comando:
```sql
USE teste;
```

## **Exibindo as Tabelas Existentes**
Para listar todas as tabelas no banco de dados atual, use:
```sql
SHOW TABLES;
```

## **Exibindo as Informações de uma Tabela**
Para visualizar a estrutura de uma tabela específica, como `pessoas`, use:
```sql
DESCRIBE pessoas;
```

## **Criando uma Tabela Dentro do Banco de Dados**
Para criar uma nova tabela chamada `carros` com as colunas `id`, `modelo`, `ano`, e `quilometragem`, use:
```sql
CREATE TABLE carros (
    id INT AUTO_INCREMENT PRIMARY KEY,
    modelo VARCHAR(100),
    ano YEAR,
    quilometragem DOUBLE
);
```
_Nota: `AUTO_INCREMENT` instrui o MySQL a gerar automaticamente um valor único e sequencial para `id` cada vez que uma nova linha é inserida na tabela. `PRIMARY KEY` define a coluna `id` como a chave primária, um identificador único para cada linha na tabela._

## **Deletando uma Tabela de um Banco de Dados**
Para deletar a tabela `carros`, use:
```sql
DROP TABLE carros;
```

## **Criando Novamente a Tabela**
Para recriar a tabela `carros` com a mesma estrutura, use:
```sql
CREATE TABLE carros (
    id INT AUTO_INCREMENT PRIMARY KEY,
    modelo VARCHAR(100),
    ano YEAR,
    quilometragem DOUBLE
);
```

## **Verificar os Dados Inseridos**
Para verificar os dados inseridos nas tabelas `pessoas` e `carros`, use:
```sql
SELECT * FROM pessoas;
SELECT * FROM carros;
```

## **Inserindo Dados na Tabela**
Para inserir um novo registro na tabela `carros` e depois visualizar o conteúdo da tabela, use:
```sql
INSERT INTO carros (modelo, ano, quilometragem) VALUES ('Gol', 2010, 29500);
SELECT * FROM carros;
```

## **Alterando o Tipo de Dados de uma Coluna**
Para alterar o tipo de dados da coluna `quilometragem` de `DOUBLE` para `INT` e depois revertê-lo para `DOUBLE`, use:
```sql
ALTER TABLE carros MODIFY COLUMN quilometragem INT;
SELECT * FROM carros;

ALTER TABLE carros MODIFY COLUMN quilometragem DOUBLE;
SELECT * FROM carros;
```

## **Atualizando um Valor de uma Coluna Específica**
Para atualizar o valor da coluna `quilometragem` para `29000` na linha onde o `id` é `1`, use:
```sql
UPDATE carros SET quilometragem = 29000 WHERE id = 1;
SELECT * FROM carros;
```

---