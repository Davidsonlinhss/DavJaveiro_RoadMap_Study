## 2.1.1 Start Container
O #Initializr gera uma classe com o nome de nossa aplicação e com o método `main()`. No corpo do método main, há uma chamada run(), que inicia o container. 
![[Capítulo 2 - Containers for Spring-Managed Beans.png]]
O método run() é um método estático da **classe SpringApplication**.

*Métodos estáticos*: são métodos que pertencem à própria **classe**, e não a uma instância específica da classe. Isso significa que podemos chamá-los diretamente pela classe sem precisar criar um objeto daquela classe.  

**Quando utilizar métodos estáticos**
1. Operações Utilitárias: métodos estáticos são ótimos para funções utilitárias, como métodos de biblioteca, onde a operação realizada é a mesma para qualquer objeto. Por exemplo, a classe #Math em Java contém vários métodos estáticos

Três coisas acontecem quando o método run() é chamado:
1. Se houver um erro na configuração, o contêiner não será iniciado e o programa será interrompido; run(...) termina com lançando uma exceção;
2. Se toda a configuração estiver correta, o contêiner inicializa todos os componentes e é concluído, e o método run(...) é encerrado. No entanto, um servidor da Web, como o Tomcat, pode ter sido iniciado, por exemplo, e o programa continua a ser executado mesmo que o método main(...) tenha sido encerrado;
3. O método run(...) pode ficar em um loop infinito porque, por exemplo, um shell interativo é iniciado. 