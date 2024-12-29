O *dockerfile* nada mais é do que um arquivo onde determinamos todos os detalhes do nosso *container*, como, por exemplo, a imagem que nós vamos utilizar, aplicativos que necessitam ser instalados, comandos a serem executados, os volumes que serão montados, etc...

É um *makefile* para criação de *containers*, e nele passamos todas as instruções para a criação do nosso *container*.

1. Criamos um diretório onde deixaremos o nosso arquivo *dockerfile*, somente para ficar organizado.
2. E depois criaremos o dockerfile;

```ubuntu
# mkdir /root/primeiro_dockerfile
# cd /root/primeiro_dockerfile
# vim Dockerfile
```

Vamos adicionar as instruções que queremos para essa imagem de *container* que iremos criar:
FROM debian
RUN /bin/echo "HELLO DOCKER"

Agora vamos rodar o comando "docker build" para fazer a criação dessa imagem de *container* utilizando o #dockerfile criado. 
