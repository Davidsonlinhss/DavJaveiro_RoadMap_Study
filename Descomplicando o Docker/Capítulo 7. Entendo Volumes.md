## 7.1. Introdução a volumes no Docker
Volumes nada mais são do que diretórios externos ao *container*, que são montados diretamente nele, e dessa forma *bypassam* seu *fylesystem*, ou seja, não seguem aquele padrão de camadas que falamos. 

<span style="background:#fff88f">A principal função do volume é persistir os dados</span>. Diferentemente do *filesystem* do container, que é volátil e toda informação escrita nele é perdida quando o container morre, quando escrevemos em um volume aquele dado continua lá, independentemente do estado do *container*. 

Existem algumas particularidades entre os volumes e *containers* que valem a pena ser mencionadas:
- O volume é inicializado quando o *container* é criado. 
- Caso ocorra de já haver dados no diretório em que estamos montando como um volume, ou seja, se o diretório já existe e está "populando" na imagem base, aqueles dados serão copiados para o volume.
- Um volume pode ser reusado e compartilhado entre *containers*.
- Alterações em um volume são feitas diretamente no volume.

Agora conhecemos um novo parâmetro do comando "docker container run", o "--mount".
O parâmetro "--mount" é responsável por indicar o volume, que em nosso exemplo é o "/volume", e onde ele será montado no *container*. Perceba que, quando passamos o parâmetro "--mount type=bind, src=/volume, dst=/volume", o docker montou esse diretório no *container*, porém, sem nenhum conteúdo.

Podemos também montar um volume no *container linkando-o* com um diretório do *host* já com algum conteúdo. Para exemplificar, vamos compartilhar o diretório "/root/primeiro_container", que utilizamos para guardar o nosso primeiro *dockerfile*, e montá-lo no *container* em um volume chamado "/volume" da seguinte forma:
