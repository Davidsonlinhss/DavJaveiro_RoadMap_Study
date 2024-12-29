**O maior problema ao deletarmos dados** é o problema de <span style="background:#fdbfff">integridade referencial</span>. A integridade referencial garante que as **relações** entre as tabelas do banco de dados sejam mantidas corretamente.

A integridade referencial é um conceito no qual o banco de dados **assegura que as referências** entre tabelas estejam sempre consistentes. Em termos simples, significa que se uma tabela A tem uma **chave estrangeira** (<span style="background:#fdbfff">foreign key</span>) que aponta para uma tabela B, não é permitido que a tabela A tenha um valor de chave estrangeira que não existe na tabela B. 

Quando tentamos **deletar dados que são referenciados por outras tabelas**, pode ocorrer um problema de **integridade referencial**. Isso significa que ao remover um registro que está sendo utilizado como referência em outra tabela, as relações entre os dados fiquem quebradas, resultando em inconsistência. 

**Exemplos**
1. Se temos uma tabela **Clientes** e uma tabela **Pedidos**, onde cada pedido está associado a um cliente, ao deletar um cliente da tabela **Clientes**, <span style="background:#fdbfff">todos os pedidos associados a esse cliente</span> podem ficar "órfãos" se o banco de dados não tratar isso corretamente. 

**Como evitar o problema da integridade referencial:**
1. On Delete Cascade:
- Uma opção é configurar a chave estrangeira para que, ao deletar um registro pai, todos os registros filhos sejam automaticamente deletados;
 **Exemplo:** Se deletarmos um cliente, todos os pedidos relacionados a esse cliente serão automaticamente deletados. 

2. On Delete Set Null:
- Outra opção é configurar a chave estrangeira para que, ao deletar o registro pai, a chave estrangeira na tabela filha seja automaticamente definida como #Null;
 **Exemplo:** se deletarmos um cliente, os pedidos relacionados a esse cliente terão seu campo de cliente configurado como #null.
 
3. Restrições (ON DELETE RESTRICT):
- A chave estrangeira pode ser configurada para impedir a exclusão de um registro pai se ele estiver sendo referenciado em uma tabela filha. 
**Exemplo:** Se um cliente tiver pedidos, o banco de dados não permitirá que deletemos o cliente até que os pedidos sejam tratados (deletados ou movidos). 


Código fonte: https://github.com/acenelio/jdbc5 
**Checklist**: 
- Criar DbIntegrityException;
- Tratar a exceção de #integridade #referencial;

