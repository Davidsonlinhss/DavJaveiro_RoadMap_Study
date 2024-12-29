Uma #classe é definida por #atributos e #métodos, que são os #membros de uma classe. 
- Os atributos (variáveis de instância) são variáveis de diferentes tipos e valores. 
- Os #métodos, por sua vez, correspondem a funções ou sub-rotinas disponíveis dentro de nossas classes.

Como devem ser declarados:
- Deve ser nomeado como verbo;
- Seguir o padrão #camelCase (Todas as letras minúsculas com a exceção da primeira letra da segunda palavra).

Exemplo de nomeação de métodos:

```Java
somar(int n1, int n2) {}

abrirConexao(){}

concluiProcessamento() {}

findById(int id){}

calcularImprimir(){}

```

## Definindo parâmetros e retorno de métodos
Como sabemos a melhor forma, de definir os métodos das nossas classes? Para chegar a essa conclusão, somos auxiliados por uma convenção estrutural para todos os métodos. Essa convenção é determinada pelos aspectos abaixo:
1. **Qual a proposta principal do método?** Devemos se perguntar constantemente até compreender a real finalidade do mesmo.
2. **Qual o tipo de retorno esperado após executar o método?** Devemos analisar se o método será responsável por retornar algum valor ou não. 
3. **Qual o tipo de parâmetro esperado pelo método?** Devemos analisar se o método irá receber algum parâmetro ou não. 
4. **O método possui risco de apresentar alguma exceção?** Exceções são comuns na execução de métodos, as vezes é necessário prever e tratar a possível existência de uma exceção.
5. **Qual a visibilidade do método?**  Avaliar se será necessário que o método seja visível em toda a aplicação, somente em pacotes...

