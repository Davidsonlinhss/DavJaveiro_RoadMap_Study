Aqui está o resumo completo dos comandos usados do início ao fim para iniciar um repositório local, conectá-lo a um repositório remoto, resolver o conflito de histórico e realizar o `push`:

### 1. Inicializar o repositório local e criar o branch `main`
```bash
git init -b main
```

### 2. Adicionar os arquivos e fazer o commit inicial
```bash
git add .
git commit -m "Commit inicial"
```

### 3. Conectar ao repositório remoto
```bash
git remote add origin <URL-do-repositorio>
```

### 4. Baixar as alterações do repositório remoto (fazer `pull`)
```bash
git pull origin main --allow-unrelated-histories
```

### 5. Resolver conflitos (se houver), adicionar as alterações e fazer commit
```bash
git add .
git commit -m "Resolvendo conflitos"
```

### 6. Enviar as alterações para o repositório remoto (fazer `push`)
```bash
git push origin main
```

Esse fluxo garante que o repositório local está sincronizado com o remoto e que você conseguiu enviar suas alterações.