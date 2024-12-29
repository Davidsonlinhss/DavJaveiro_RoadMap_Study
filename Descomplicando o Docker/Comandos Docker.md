Listar containers em execução:
```docker
docker ps
```

Listar containers parados:
```
docker ps -a
```

Para executar um container parado, primeiramente precisamos saber o ID do container, podemos descobrir isso utilizando o comando <span style="background:#affad1">docker ps -a</span> ; ao descobrirmos o ID do container, usaremos o seguinte comando:
```node
docker start <id ou nome_do_container>
```

Listar imagens
```docker
docker images
```