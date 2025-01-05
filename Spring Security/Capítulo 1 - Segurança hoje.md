<span style="background:rgba(240, 200, 0, 0.2)">Este capítulo abordará:</span>
- O que é Spring Security e o que você pode resolver utilizando-o;
- O que é segurança para um aplicativo de software?
- Por que segurança de software é essencial e por que deve se importar.

Como desenvolvedores, começamos aprendendo que a finalidade de um aplicativo é resolver problemas comerciais. Essa finalidade refere-se a algo em que os dados podem ser processados de alguma forma, mantidos e, por fim, exibidos ao usuário conforme especificado pelos requisitos. Essa visão geral do desenvolvimento de software, que de certa forma é imposta desde os estágios iniciais do aprendizados dessas técnicas, tem a infeliz desvantagem de ocultar práticas que também fazem parte do processo. Embora o aplicativo funcione corretamente do ponto de vista do usuário e, por fim, faça o que ele espera em termos de funcionalidade, há muitos aspectos ocultos no resultado final.

As qualidades não funcionais do software, como desempenho, escalabilidade, disponibilidade e segurança, além de outras, podem ter um efeito ao longo do tempo, de curto a longo prazo. Se não forem levadas em consideração desde o início, essas qualidades podem afetar drasticamente a lucratividade dos proprietários de aplicativos. Além disso, a negligência dessas considerações também podem desencadear falhas em outros sistemas (por exemplo, pela participação involuntária em um ataque distribuído de negação de serviço [#DDoS]).

---
**#DDoS (Distributed Denial of Service)** - é um tipo de ataque cibernético onde vários dispositivos comprometidos, frequentemente espalhados por várias partes do mundo, são usados para sobrecarregar um servidor, serviço ou rede alvo com uma grande quantidade de tráfego. O objetivo é tornar o sistema indisponível para usuários legítimos, causando lentidão extrema ou até mesmo forçando o sistema a ficar offline. 
Os dispositivos que participam de um ataque #DDoS geralmente fazem isso sem que seus proprietários saibam, pois podem ter sidos infectados por malware que os transforma em "bots" ou "zumbis". Esses dispositivos infectados forma uma "botnet", que é uma rede de máquinas sob o controle de um invasor, usadas para lançar o ataque. 

----------------------------------------------
Os aspectos ocultos dos requisitos não funcionais (o fato de que é muito mais difícil perceber se algo está faltando ou incompleto) os tornam ainda mais perigosos. 

Há vários aspectos não funcionais a serem considerados ao trabalhar em um sistema de software. Na prática, todos eles são importantes e precisam ser tratados com responsabilidade no processo de desenvolvimento de software. Este livro se concentra em um deles: a segurança. Aprenderemos a proteger nosso aplicativo, passo a passo, usando o Spring Security.

Este capítulo mostrará o panorama geral dos conceitos relacionados à segurança. Ao longo do livro, trabalharemos com exemplos práticos e, quando for o caso, faremos referência à descrição que apresento  neste capítulo.

## Descobrindo o Spring Security
Esta seção discute a relação entre o Spring Security e o Spring. Primeiro, é importante entender o vínculo entre os dois antes de começar a usá-los. Se lermos a documentação no site oficial, veremos que o Spring Security é descrito como uma **estrutura avançada e altamente personalizável para autenticação e controle de acesso**. Eu diria simplesmente que é uma estrutura que <span style="background:#fff88f">simplifica</span> muito a aplicação (ou <span style="background:#fff88f">incorporação</span>) de segurança aos aplicativos Spring.

O Spring Security é a principal opção para implementar a segurança em nível de aplicativo nos aplicativos Spring. Em geral, <span style="background:#fff88f">seu objetivo é</span> oferecer uma maneira altamente personalizável de implementar **autenticação**, **autorização** e **proteção** <span style="background:#fff88f">contra ataques comuns</span>. O SS é um software de código aberto lançado sob a licença Apache 2.0. Podemos acessar seu código fonte no GitHub...

---
**OBSERVAÇÃO:** Podemos usar o Spring Security para servlets da Web Padrão e em reactive appications e em aplicações não web. 

----
OSpring Security não protege automaticamente seu aplicativo. Ele não é um tipo de panaceia mágica que garante um aplicativo livre de vulnerabilidades. Os desenvolvedores precisam entender como configurar e personalizar o Spring Security de acordo com as necessidades de seus aplicativos. A maneira de fazer isso depende de muitos fatores, desde os requisitos funcionais até a arquitetura.

Tecnicamente, aplicar a segurança com o Spring Security nos aplicativos Spring é simples. Como já implementamos aplicativos Spring, sabemos que a filosofia da estrutura começa com o gerenciamento do contexto do Spring. Definimos beans no contexto do Spring para permitir que a estrutura os gerencie com base nas configurações que especificarmos.

Nós utilizamos anotações para dizer ao Spring o que fazer: expor pontos de extremidade, envolver métodos em transações, interceptar métodos em aspectos e assim por diante. O mesmo acontece com as configurações do Spring Security, que é onde o Spring Security entra em ação. Em um aplicativo Spring, o comportamento que você precisa proteger é definido por métodos. 

Para pensar na segurança em nível de aplicativo, considere sua casa e a maneira como permitimos o acesso a ela. Colocamos a chave embaixo do tapete de entrada? Você tem ao menos uma chave para a porta da frete? O mesmo conceito se aplica aos aplicativos, e o Spring Security ajuda a desenvolver essa funcionalidade. É um quebra-cabeça que oferece muitas opções para criar a imagem exata que descreve o nosso sistema. Podemos optar por deixar nossa casa completamente desprotegida ou podemos decidir não permitir que todos entrem em nossa casa. 

A maneira como configuramos a segurança pode ser simples, como esconder a chave debaixo do tapete, ou podemos fazer de uma forma mais complicada, como escolher uma variedade de sistemas de alarme, câmeras de vídeo e fechaduras. Em nossos aplicativos, temos a mesma opção, mas, assim como na vida real, quanto mais complexidade adicionarmos, mais caro fica. Em um aplicativo, esse custo se refere à maneira como <span style="background:#fff88f">a segurança afeta a capacidade de manutenção e o desempenho</span>. 

Mas como você usa o Spring Security com os aplicativos Spring? Geralmente, no nível do aplicativo, um dos casos de uso mais frequentemente encontrados é quando estamos decidindo se alguém tem **permissão para executar uma ação** ou usar algum dados. Com base nas configurações, escrevemos componentes do Spring Security que interceptam as solicitações e garantem que quem faz as solicitações tem permissão para acessar recursos protegidos. O desenvolvedor configura os componentes para fazer exatamente o que é desejado. Se montarmos um sistema de alarme, sou eu quem deve certificar que ele esteja configurado tanto para as janelas quanto para as portas. 

Outras responsabilidades dos componentes do Spring Security estão relacionadas ao <span style="background:#fff88f">armazenamento de dados</span>, bem como ao <span style="background:#fff88f">trânsito de dados</span> entre <span style="background:#fff88f">diferentes partes do sistema</span>. Por exemplo, quando os dados são armazenados, esses componentes podem aplicar algoritmos de criptografia ou hashing. 

---
#Criptografia a criptografia é o processo de converter dados legíveis (texto simples) em um formato ilegível (texto cifrado) usando um algoritmo e uma chave de criptografia. O objetivo é proteger a confidencialidade dos dados, garantindo que apenas pessoas autorizadas, com a chave de descriptografia correta, possam acessar as informações originais.
#hashingo processo de transformar dados de tamanho variávcel em uma representação fixa (chamada de "hash") usando uma função hash. Ao contrário da criptografia, o hashing é unidirecional, ou seja, não podemos converter o hash de volta para os dados originais. 
**Exemplo Simplificado**:
- Texto original: "minhaSenha123"
- Usando a função de hash SHA-256, o resultado seria algo como: "ef92b779d7b04c3c3d9a7b67c7922bcb52409f632f2f6ed54e507a6a8f3f7b7a"
- Mesmo que o hash seja exposto, não é possível determinar a senha original com facilidade.
----
As codificações de dados mantêm os dados acessíveis somente a entidades privilegiadas. Em um aplicativo Spring, o desenvolvedor precisa adicionar e configurar um componente para fazer essa parte do trabalho sempre que for necessário. O Spring Security nos fornece um contrato por meio do qual sabemos o que a estrutura exige que seja implementado, e escrevemos a implementação de acordo com o design do aplicativo. Podemos dizer o mesmo sobre o trânsito de dados. 

Como qualquer estrutura, um dos principais objetivos do Spring é permitir que escrevamos menos código para implementar a funcionalidade desejada. Isso também é o que o Spring Security faz. Ele completa o Spring como uma estrutura, ajudando-o a escrever menos código para executar um dos aspectos mais críticos de um aplicativo - a segurança. O Spring Security fornece funcionalidade predefinida para ajudá-lo a evitar **escrever código boilerplate** ou escrever repetidamente a mesma lógica de aplicativo para aplicativo. 

## 1.2 O que é software security?
