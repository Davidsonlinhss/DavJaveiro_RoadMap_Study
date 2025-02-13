*This chapter covers*
- What a framework is
- When to use and when to avoid using frameworks
- What the Spring framerwokr is
- Using Spring in real-world scenarios

O framework Spring (ou simplesmente Spring) é um framework de aplicação que faz parte do ecossistema Java. Um framework de aplicação é um conjunto de funcionalidades de software comuns que fornece uma estrutura base para o desenvolvimento de uma aplicação. Um framework de aplicação facilita o esforço de escrever uma aplicação ao *eliminar a necessidade de escrever todo o código do programa do zero*.

Atualmente, utilizamos o Spring no desenvolvimento de diversos tipos de aplicações, desde grandes soluções de backend até aplicativos de automação de testes. De acordo com vários relatórios de pesquisa sobre tecnologias Java (como este da JRebel de 2020:  [http://mng.bz/N4V7](http://mng.bz/N4V7); ou este da JAXEnter: [http://mng.bz/DK9a](http://mng.bz/DK9a)), o Spring é o framework Java mais utilizado atualmente.)

O Spring é popular, e os desenvolvedores começaram a utilizá-lo com mais frequência também com outras linguagens da JVM além do Java. Nos últimos anos, observamos um crescimento impressionante de desenvolvedores utilizando o Spring com Kotlin (outra linguagem apreciada da família JVM). Neste livro, focaremos nos fundamentos do Spring, e eu ensinarei habilidades essenciais para usar o Spring  em exemplos do mundo real. Para facilitar sua compreensão e permitir que nos concentremos no Spring, usaremos apenas exemplos em Java. Ao longo do livro, discutiremos e aplicaremos, com exemplos, habilidades essenciais como **conectar-se a um banco de dados**, estabelecer comunicação entre aplicações e garantir a segurança e realizar testes em uma aplicação.

Antes de mergulhar em detalhes mais técnicos nos próximos capítulos, vamos conversar sobre o framework Spring e onde realmente o utilizaremos. Por que o Spring é tão apreciado, e quando devemos considerar utilizá-lo?

Neste capítulo, focaremos no que é um _framework_ , referindo-nos especificamente ao _Spring framework_ . Na seção 1.1, discutiremos as vantagens de usar um _framework_ . Na seção 1.2, exploraremos o ecossistema do Spring com os componentes que você precisa aprender para começar a trabalhar com o Spring. Em seguida, vou apresentar possíveis usos do _Spring framework_ — em particular, cenários do mundo real na seção 1.3. Na seção 1.4, discutiremos quando usar _frameworks_ pode não ser a abordagem mais adequada. É importante entender todos esses aspectos sobre o _Spring framework_ antes de tentar utilizá-lo. **Caso contrário, você pode acabar tentando usar um martelo para cavar seu jardim**.

Dependendo do nosso nível, podemos achar este capítulo desafiador. Posso introduzir alguns conceitos com os quais ainda não estamos familiarizado, e isso pode causar certa confusão. Mas não precisamos nos preocupar; mesmo que eu não entenda algumas coisas agora, elas serão esclarecidas mais adiante no livro. Às vezes, ao longo do livro, farei referência a algo mencionado em capítulos anteriores. Uso essa abordagem porque aprender um _framework_ como o Spring nem sempre nos oferece um caminho de aprendizado linear, e às vezes é necessário esperar até que você tenha mais peças do quebra-cabeça antes de ver a imagem completa. No final, porém, você terá uma visão clara e adquirirá as habilidades valiosas necessárias para desenvolver aplicativos como um profissional.

## 1.1 Why should we use frameworks?
Nesta seção, discutiremos os _frameworks_ . O que são eles? Como e por que esse conceito surgiu? Para se sentir motivado a usar algo, você precisa saber como isso traz valor para você. E o mesmo é verdade para o Spring. Vou ensinar esses detalhes essenciais compartilhando o conhecimento que reuni com base na minha própria experiência e no estudo e uso de vários _frameworks_ em cenários do mundo real, incluindo o Spring.

Um *framework* de aplicação é um conjunto de funcionalidades sobre as quais construímos aplicações. O *framework* de aplicação nos fornece uma ampla variedade de ferramentas e funcionalidades que podem ser usadas para construir aplicativos. **Não precisamos usar todas as funcionalidades que podem ser usadas para construir aplicativos**. Não precisamos usar todas as funcionalidades que o *framework* oferece. Dependendo dos requisitos do aplicativo que estamos desenvolvendo, escolheremos as partes mais adequadas do *framework* para utilizar.

![[Chapter 1 - Spring in the real world.png]]