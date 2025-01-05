Ao longo deste capítulo, aprenderemos diferentes mecanismos de modelo e suas respectivas vantagens e desvantagens. Todos os templetes engines apresentados não estão vinculados ao uso com o Express, mas são frequentemente usados em combinação. No entanto, apresentaremos o uso autônomo de cada estrutura. 

Em termos simples, um template engine é uma estrutura estática, geralmente um documento HTML, com marcadores que são substituídos por valores concretos pelo mecanismo modelo em um momento posterior. Os <span style="background:#d3f8b6">templates engines</span> oferecem várias vantagens que justificam seu uso em aplicativos da Web. Entre elas estão as seguintes:

1. <span style="background:#d3f8b6">Separação da lógica e markup</span>: a mistura de códigos HTML e JavaScript em um aplicativo prejudica a legibilidade do código-fonte em ambos os lados e, portanto, também afeta a capacidade de manutenção de todo o aplicativo. Por isso, faz sentido separar a representação da lógica do aplicativo o máximo possível. Os <span style="background:#d3f8b6">templates models</span> contribuem para isso oferecendo a você, uma forma de escrever templates sem lógica e inserir as partes dinâmicas por meio de marcados. 
2. <span style="background:#d3f8b6">Reusabilidade</span>: muitos mecanismos dos templates possuem recursos que permitem s**ubdividir os modelos para definir blocos**, chamados de <span style="background:#d3f8b6">parciais</span>, e depois despejá-los em arquivos separados. Assim, as **parciais** podem ser usada várias vezes em seu aplicativo. Essa reutilização garante que a gente possa fazer alterações na aparência do aplicativo de forma mais rápida e conveniente. 
3. <span style="background:#d3f8b6">Paralelização de trabalho</span>: com os templates engines nós podemos separar a lógica do backend e do frontend de nossa aplicação e paralelizar o trabalho. Se um recurso precisar ser implementado, um desenvolvedor poderá lidar com os controllers e models que precisam ser implementados. Enquanto isso, outros desenvolvedores já podem cuidar da implementação dos modelos e da lógica de front-end. Ambas as equipes se coordenam na interface model-controller e concordam sobre quais variáveis estão disponíveis. 
Além das vantagens de usar template engines, o processamentos das templetes pelo aplicativo, ou seja, a pesquisa e a substituição dos marcadores, exige tempo e recursos. Entretanto, também há soluções para esse problema, ou seja, na forma de pré-compilação e armazenamento em cache. Antes de apresentarmos o Pug, o primeiro template engine deste capítulo, vamos dar uma revisada no capítulo anterior. 

No capítulo 6, já implementamos um template engine leve. Ele foi usado para exibir a lista quanto para o formulário. 

A solução que implementamos representa um tipo simples de template engine. Ele permite inserir variáveis na estrutura HTML e, graças à funcionalidade das cadeias de modelos JavaScript, podemos formular loops ou condicionais simples. Utilizamos os métodos #map e #join para colocar uma matriz no formulário apropriado e integrá-la ao #model (banco de dados).

Ao invés de usar template string, podemos implementar esse mecanismo de template usando marcadores em nosso template e substituindo-os por conteúdos dinâmicos usando o método #replace de string. Normalmente, caracteres especiais, como colchetes, são usados, resultando em marcadores como {{movie}}. 

Precisamos ter em mente duas coisas sobre os templates engines:
1. A edição de templates consome tempo e recursos, independente se usarmos uma implementação própria ou um mecanismo existe. Os templates devem ser processados.


#### Template engines na prática: Pug
O Pug é instalado utilizando o comando NPM install. Assim como muitos outros pacotes de gerenciamentos do node, o Pug é um projeto open-source projetado e mantido pelo Git-hub

#### Pug e Express: integração
Após realizarmos a instalação do Pug via NPM, nós devemos integrá-lo em nossa aplicação através do nosso código fonte do Express.

```node
app.set('view engine', 'pug')
```

Chamando o método acima, faz com que o template engine padrão seja definido do Express para o #Pug. Isso permite que a gente use o método de renderização do objeto de resposta para renderizar os nossos modelos. 

Por padrão, o método de renderização espera que os modelos estejam localizados e um diretório de exibição. Ao chamar app.set('view', './template'), podemos alterar esse comportamento e ajustar o diretório padrão para models, por exemplo. 

A conexão de um template engine ao Express ocorre por meio de um método denominado 
--express. Assim o Pug implementa esse método e, em seguida, chama seu próprio método renderFile. 

Como primeira visualização, devemos alterar a visualização de lista para Pug. Devemos criar um subdiretório views dentro do diretório movie e criar um arquivo list.pug nele. O Express espera a extensão de arquivo .pug para arquivos templates em combinação com o pug; 

O pug altera consideravelmente a estrutura do arquivo, o que significa que os colchetes angulares das tags são aliminados, assim como as tags de fechamento. A primeira palavra da linha é interpretada como uma tag. A identação das linhas reflete o aninhamento do DOM. Os atributos de um elemento são escritos entre parênteses após o nome da tag, seguidos pelo conteúdo textual de um nó. 

<span style="background:#ff4d4f">Para usar esse template, devemos alterar o método #send do object na resposta listAction do controller para #render.</span> 

Especificar:
```node
export async function listAction(req, res) {
  const data = await getAll();
  res.render(`${dirname(fileURLToPath(import.meta.url))}/views/list`);

}
```
Garante que o diretório de visualizações local do módulo seja usado ao invés do diretório padrão de visualizações do Express.