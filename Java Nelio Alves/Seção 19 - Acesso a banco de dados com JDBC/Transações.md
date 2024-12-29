As transações em banco de dados são fundamentais para manter a consistência dos dados. Uma transação é um conjunto de operações que são tratadas como uma única unidade de trabalho. Para garantir a integridade e a consistência dos dados em um banco de dados, é necessário que as transações sigam as propriedades ACID:
1. #Atomicidade: toda transação deve ser "tudo ou nada". Ou seja, ou todas as operações da transação são realizadas com sucesso, ou nenhuma delas será aplicada.
2. #Consistência: a transação deve transformar o banco de dados de um estado consistente para outro. Isso significa que qualquer regra ou restrição do banco de dados deve ser mantida antes e depois da transação.
3. #Isolamento: transações simultâneas devem ser executadas como se fossem sequenciais, sem interferir umas nas outras, para evitar problemas como inconsistências de dados.
4. #Durabilidade: uma vez que uma transação é confirmada (committed), suas alterações nos dados devem ser permanentes, mesmo em caso de falha do sistema. 

Usando o JDBC, podemos tratar as transações usando três elementos da API:
- setAutoCommit(false): cada operação isolada vai ser confirmada automaticamente, se estiver configurado como false cada transação não estará confirmada.
- commit(): confirmar a transação;
- rollback(): desfazer o que já foi feito até o momento. 
