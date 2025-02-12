O *Feature Branch Workflow* é uma estratégia de trabalho no Git onde cada nova funcionalidade ou alteração significativa é desenvolvida em um *branch* separado, chamado de *feature branch*. Esse *branch* é criado a partir do *branch* principal (geralmente chamado de **main** ou **master**) e só é mesclado *merge* de volta ao *branch* principal após a conclusão e revisão da funcionalidade.

Esse fluxo de trabalho ajuda a manter o *branch* principal estável, pois as mudanças experimentais ou incompletas não afetam diretamente o código principal até que estejam prontas para serem integradas.

## **Benefícios do Feature Branch Workflow**
- **Isolamento de mudanças:** cada funcionalidade ou correção é desenvolvida isoladamente, reduzindo o risco de conflitos e bugs no *branch* principal. 
- **Colaboração facilitada**: várias pessoas podem trabalhar simultaneamente em diferentes funcionalidades sem interferir umas nas outros.
- **Revisão de código:** antes de mesclar uma *feature branch* no *branch* principal, é possível realizar revisões de código *code reviews* e testes automatizados.
- **Histórico limpo:** o histórico do repositório fica mais organizado, pois cada *merge* representa uma funcionalidade concluída.

## Como funciona na prática?
### Passo 1: criar uma Feature Branch
Sempre que uma  nova funcionalidade ou correção precisa ser implementada, cria-se um novo *branch* a partir do *branch (main)*:
```git
git checkout -b feature/nome-da-funcionalidade main
```
- *feature/nome-da-funcionalidade*: nome descritivo do *branch*.
- *main*: o *branch* principal do qual o novo *branch* é derivado.

### Passo 2: desenvolver a Funcionalidade
No *feature branch*, o desenvolvedor implementa a nova funcionalidade ou correção. Durante esse processo, ele pode fazer commits frequentes:
```bash
git add .
git commit -m "Adiciona funcionalidade X"
```

### Passo 3: Sincronizar com o Branch Principal
Antes de finalizar o trabalho, é importante garantir que o *feature branch* esteja atualizado com as últimas mudanças do *branch* principal:
```bash
git checkout main
git pull origin main
git checkout feature/nome-da-funcionalidade
git merge main
```
Isso evita conflitos futuros durante o *merge*.

### Passo 4: revisão de código
Após concluir a funcionalidade, o desenvolvedor solicita uma revisão de código *code review* por meio de uma ferramente como o GitHub ou Bitbucket. Isso geralmente envolve a criação de um *Pull Request (PR)*.

### Passo 5: Mesclar no Branch Principal
Se a revisão for aprovada e os testes forem bem-sucedidos, o *feature branch* é mesclado no *branch* principal:
```bash
git checkout main
git merge feature/nome-da-funcionalidade
git push origin main
```
Depois disso, o *feature branch* pode ser excluído:
```bash
git branch -d feature/nome-da-funcionalidade
```

### **Conclusão**

O Feature Branch Workflow é uma abordagem prática e amplamente adotada para gerenciar projetos de software com Git. Ele permite que equipes trabalhem de forma colaborativa e organizada, minimizando riscos e maximizando a qualidade do código. Se você está começando com Git ou quer melhorar sua estratégia de desenvolvimento, esse workflow é uma excelente escolha.

Se precisar de mais detalhes ou exemplos práticos, posso ajudar! 😊


---
### **Tipos Comuns de Branches**

1. **`feature/`**: Para desenvolvimento de novas funcionalidades.
    
    - Exemplo: `feature/adicionar-login`
        
    - Exemplo: `feature/integracao-api-pagamentos`
        
2. **`fix/`**: Para correções de bugs.
    
    - Exemplo: `fix/corrigir-erro-autenticacao`
        
    - Exemplo: `fix/resolver-bug-carrinho`
        
3. **`hotfix/`**: Para correções urgentes em produção.
    
    - Exemplo: `hotfix/corrigir-falha-seguranca`
        
    - Exemplo: `hotfix/resolver-erro-pagamento`
        
4. **`refactor/`**: Para refatoração de código.
    
    - Exemplo: `refactor/melhorar-performance-api`
        
    - Exemplo: `refactor/organizar-pastas-projeto`
        
5. **`docs/`**: Para atualizações na documentação.
    
    - Exemplo: `docs/atualizar-readme`
        
    - Exemplo: `docs/adicionar-guia-instalacao`
        
6. **`test/`**: Para adicionar ou melhorar testes.
    
    - Exemplo: `test/adicionar-testes-usuario`
        
    - Exemplo: `test/cobrir-cenarios-autenticacao`
        
7. **`chore/`**: Para tarefas de manutenção ou configuração (ex.: atualização de dependências, configurações de CI/CD).
    
    - Exemplo: `chore/atualizar-dependencias`
        
    - Exemplo: `chore/configurar-github-actions`


Para correções no código, como adicionar setters a uma classe, é importante seguir um padrão claro e consistente no título e na descrição da PR. Isso ajuda a equipe a entender rapidamente o contexto e o propósito da mudança. Aqui está um guia para seguir:

---

### **Padrão para Títulos de PRs de Correções no Código**

1. **Usar verbos no imperativo**:
   - Exemplo: "Adiciona setters à classe `Usuario`".

2. **Ser específico sobre o que foi corrigido ou adicionado**:
   - Evite títulos genéricos como "Corrige bugs" ou "Atualiza código".
   - Exemplo ruim: "Corrige classe `Usuario`".
   - Exemplo bom: "Adiciona setters para os atributos `nome` e `email` na classe `Usuario`".

3. **Incluir o escopo da mudança (opcional)**:
   - Se o projeto for grande, adicione um prefixo para indicar o contexto.
   - Exemplo: "Feat: Adiciona setters à classe `Usuario`".

4. **Manter o título curto e direto**:
   - Idealmente, até 50-70 caracteres.

---

### **Exemplos de Títulos para Adicionar Setters**
- "Adiciona setters à classe `Usuario`"
- "Implementa setters para `nome` e `email` na classe `Usuario`"
- "Feat: Adiciona métodos setters à classe `Usuario`"
- "Corrige falta de setters na classe `Usuario`"

---

### **Padrão para Descrições de PRs de Correções no Código**

A descrição deve fornecer mais detalhes sobre o que foi feito e por quê. Aqui está um modelo sugerido:

---

**Descrição da PR:**

Esta PR adiciona métodos setters à classe `Usuario` para os atributos `nome` e `email`, permitindo a modificação desses valores após a criação do objeto. A alteração foi necessária para garantir que a classe siga o princípio de encapsulamento e permita flexibilidade no uso.

**Motivação:**
- A classe `Usuario` não permitia a alteração dos atributos `nome` e `email` após a criação do objeto.
- A adição de setters resolve essa limitação, seguindo boas práticas de orientação a objetos.

**Alterações realizadas:**
- Adicionados métodos `setNome(String nome)` e `setEmail(String email)` à classe `Usuario`.
- Atualizados testes unitários para cobrir os novos métodos.

**Impacto:**
- Agora é possível modificar os atributos `nome` e `email` de um objeto `Usuario` após sua criação.

**Testes realizados:**
- Testes unitários foram atualizados e executados com sucesso.
- Verifiquei a funcionalidade dos novos setters em um ambiente de desenvolvimento.

---

### **Boas Práticas para Correções no Código**
1. **Siga as convenções de código do projeto**:
   - Certifique-se de que os nomes dos métodos e a formatação estejam alinhados com o padrão do projeto.
   
2. **Adicione testes**:
   - Se possível, inclua testes unitários para os novos setters.

3. **Documente as mudanças**:
   - Atualize a documentação do código, se necessário, para refletir a adição dos novos métodos.

4. **Mencione issues relacionadas**:
   - Se a correção estiver vinculada a uma issue, referencie-a na descrição (ex.: "Resolve #123").

---
