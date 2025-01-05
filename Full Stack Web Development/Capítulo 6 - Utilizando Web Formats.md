*Este capítulo apresenta uma visão geral dos formatos da Web mais importantes que você deve conhecer como desenvolvedor full stack*

Neste capítulo, quero apresentá-lo a vários formatos que desempenham um papel importante no desenvolvimento da Web, juntamente com a HTML, CSS e JavaScript. Os formatos de dados podem ser amplamente categorizados como formato de dados, formatos de imagem e formatos multimídia. <span style="background:#d3f8b6">Os formatos de dados são usados principalmente para a troca de dados</span> entre um **cliente e um servidor** ou, no **lado do servidor**, **entre diferentes serviços da Web**, conforme mostrado na figura 6.1.  Os formatos de imagem e os formatos de multimídia são relevantes principalmente para o lado do cliente (porque são exibidos pelo navegador), embora, é claro, devam ser transferidos do servidor para o cliente antes disso.

![[Capítulo 6 - Utilizando Web Formats.png]]
*Figura 6.1: Em adição ao HTML, CSS e JavaScript, outros formatos são importantes para o desenvolvimento de aplicações Web.*

## 6.1 Formatos de dados
Nesta seção, discutirei vários formatos de dados usados principalmente como formato de troca, por exemplo, quando os dados são enviados de um servidor para um cliente, ou vice-versa, e entre diferentes serviços da Web. 

Por exemplo, digamos que você queira implementar um aplicativo da Web para gerenciar contatos. Como os usuários devem ser capazes de criar novos contatos e consultar e atualizar os contatos existentes, os dados de contato (como nome e sobrenome) devem, de alguma forma, passar do cliente para o servidor durante a criação e vice-versa, do servidor para o cliente, durante a consulta.
Neste casos de uso, os **formatos de dados** ou **formatos de intercâmbio** ajudam a estruturar os dados.

### CSV
O formato de dados mais simples é o #CSV (Comma Separated Values) ou valores separados por vírgula. Esse formato é especialmente adequado para a troca de dados de tabela estruturados de forma simples. Por padrão, os registros individuais (linhas) são introduzidos por uma quebra de linha; a vírgula homônima é o separador padrão dos campos de dados individuais, as colunas. (Outros caracteres podem ser especificados para separar registros e campos de dados). Opcionalmente, os nomes das colunas podem ser definidos na primeira linha para rotular melhor um campo de dados. 

Em nosso exemplo de dados de contato, que está no formato CSV, um total de três conjuntos de dados diferentes (contatos) são definidos. Cada contato contém informações sobre o nome, o sobrenome, o número de telefone e o endereço de e-mail de um contato, todos separados por vírgulas.

firstname,lastname,phone,email
John,Doe,01234567,john.doe@example.com
Paula,Doe,01234567,paula.doe@example.com
Peter,Doe,3456789,peter.doe@example.com

*Exemplo de um documento CSV simples*

Como você pode facilmente imaginar, o formato CSV não é adequado para dados estruturados mais complicados, que podem, por exemplo, <span style="background:#d3f8b6">ter uma estrutura aninhada</span>. 

### 6.1.2 XML
Um dos formatos de troca mais importantes na Web (por exemplo para trocas de dados entre serviços da Web) é o formato #XML (**Extensible Markup Language**). XML é uma linguagem de marcação que pode **estruturar dados hierarquicamente** e é bastante semelhante ao HTML. (Afinal, o HTML também é uma linguagem de marcação). No XML, você também está lidando com elementos e atributos, mas agora com elementos XML e atributos XML.

Ao contrário do HTML, no entanto, com o XML, **temos a liberdade para decidir** quais **elementos** usar **em um documento XML** e quais **atributos** usar em um elemento. Portanto, o XML é extensível para atender às suas necessidades. 

A listagem 6.2 mostra um documento XML típico. A primeira linha contém informações sobre a versão XML usada e a codificação; o restante do documento representa o conteúdo real. O elemento `contacts` neste exemplo é o nó raiz. (Como no HTML, somente um nó raiz pode existir por ver em um documento). Abaixo de `<contacts>` há um elemento filho do tipo `<contact>`, que, por sua vez, tem diferentes elementos filhos para os vários campos de dados dos contatos. Ao contrário do CSV, o XML também permite **hierarquias ou estruturas complexas** por meio do <span style="background:#d3f8b6">aninhamento de elementos</span>. Assim, os elementos podem ser agrupados logicamente, como o elemento `address` em nosso exemplo, que agrupa os dados de endereço (rua, número da rua, CEP e a cidade).

```XML
<?xml version="1.0" encoding="UTF-8"?>
<contacts>
	<contact>
	<firstname>John</firstname>
	<lastname>Doe</lastname>
	<phone type="cell">01234567</phone>
	<email>john.doe@example.com</email>
		<address>
		<street>Sample Street</street>
		<number>99</number>
		<code>12345</code>
		<city>Sample City</city>.
		</address>
	</contact>
	<contact>
	<firstname>Paula</firstname>
	<lastname>Doe</lastname>
	<phone type="cell">01234567</phone>
	<email>paula.doe@example.com</email>
		<address>
		<street>Sample Street</street>
		<number>99</number>
		<code>12345</code>
		<city>Sample City</city>.
		</address>
	</contact>
	<contact>
	<firstname>Peter</firstname>
	<lastname>Doe</lastname>
	<phone type="landline">3456789</phone>
	<email>peter.doe@example.com</email>
		<address>
		<street>Sample Street</street>
		<number>200</number>
		<code>12345</code>
		<city>Sample City</city>.
		</address>
	</contact>
</contacts>
```

**XML Parsers**
Se quiser processar XML, você precisará de um analisador de XML, que é um componente que converte o código XML em um modelo adequado para processamento posterior na linguagem de programação relevante. Os analisadores XML estão disponíveis para várias linguagens de programação e, felizmente, você não precisa reinventar a roda aqui e pode simplesmente recorrer às bibliotecas apropriadas.
Basicamente, existem vários tipos de analisadores XML, dois dos quais são particularmente importantes e serão descritos com um mais de detalhes a segir.

**XML-DOM Parsers**
Para converter um documento XML em uma estrutura de dados semelhante a uma árvore, o que é chamado de Document Object Model (DOM) ou, às vezes, de árvore DOM, você usaria um analisador XML-DOM. Você pode acessar esse analisador em um programa usando a API (Application Programming Interface, Interface de Programação de Aplicativos)

A análise com parsers DOM só é adequada para documentos XML de tamanho pequeno a médio porque a árvore DOM completa deve ser mantida na memória. Para documentos XML grandes, você deve usar o segundo tipo conhecido de análise de XML.

**XML-SAX Parsers**
Com a opção Simple API for XML (SAX), o analisador XML-SAX percorre um documento XML passo a passo sem criar um modelo de objeto e manter o resultado na memória. Em vez disso, os analisadores XMLSAX, usam eventos para fornecer informações sobre os elementos, atributos etc, que encontram ao percorrer o documento XML. Em um programa, você tem a opção de registrar esses eventos e responder a eles.

**XML Schemas**
Você pode definir como um documento XML deve ser estruturado no que é chamado de esquema XML ou, alternativamente, por meio de uma definição de tipo de documento (DTD). Com esquemas XML e DTDs é possível definir regras, por exemplo, quais elementos podem ser usados em um documento XML, quais elementos filhos um elemento pode ter, quais atributos podem ser usados para um elemento e muito mais.

Com base em um esquema XML, <span style="background:#d3f8b6">você pode usar validadores XML para verificar se um determinado código XML corresponde à estrutura especificado no esquema</span>. Por exemplo, quando você implementa um serviço da Web que recebe dados no formato XML, pode verificar se <span style="background:#d3f8b6">os dados recebidos são XML</span> e se estão de acordo com o esquema especificado.

A listagem 6.3 mostra o esquema XML para o código XML da Listagem 6.2. Observe como o próprio esquema XML também é XML. Por exemplo, você pode usar o elemento `<xs:element>` e seu atributo `name` para definir quais elementos seu XML pode conter. Você pode usar <span style="background:rgba(240, 200, 0, 0.2)">o atributo type</span> para <span style="background:rgba(240, 200, 0, 0.2)">especificar o tipo que um elemento pode ter</span>, por exemplo, se o elemento contém uma cadeia de caracteres como valor (em nosso exemplo, os elementos `firstname`, `lastname`, e `code`, entre outros) ou um número (no exemplo, o elemento `number` para o número da rua).

```xml
<xs:schema attributeFormDefault="unqualified" elementFormDefault="qualified"
xmlns:xs="http://www.w3.org/2001/XMLSchema">
	<xs:element name="contacts">
		<xs:complexType>
			<xs:sequence>
				<xs:element name="contact" maxOccurs="unbounded" minOccurs="0">
					<xs:complexType>
						<xs:sequence>
						<xs:element type="xs:string" name="firstname"/>
						<xs:element type="xs:string" name="lastname"/>
						<xs:element name="phone">
							<xs:complexType>
								<xs:simpleContent>
										<xs:extension base="xs:int">
											<xs:attribute
											type="xs:string"
											name="type"
											use="optional"
											/>
										</xs:extension>
								</xs:simpleContent>
		</xs:complexType>
	</xs:element>
<xs:element type="xs:string" name="email"/>
<xs:element name="address">
<xs:complexType>
<xs:sequence>
<xs:element type="xs:string" name="street"/>
<xs:element type="xs:int" name="number"/>
<xs:element type="xs:string" name="code"/>
<xs:element type="xs:string" name="city"/>
</xs:sequence>
</xs:complexType>
</xs:element>
</xs:sequence>
</xs:complexType>
</xs:element>
</xs:sequence>
</xs:complexType>
</xs:element>
</xs:schema>
```

**XML Namespace**
Observe que o prefixo xs usado na Listagem 6.3 para os nomes dos elementos é um prefixo de namespace. Esse prefixo define o namespace do elemento correspondente. Esses namespaces permitem identificar elemento de forma exclusiva e também, por exemplo, usar elemento diferentes com o mesmo nome (mas de namespaces diferentes) em um documento XML.

### 6.1.3 JSON
O formato JavaScript Object Notation (JSON) é caracterizado, acima de tudo, por sua estrutura simples e por sua fácil integração em aplicativos JavaScript. Assim como o XML, o JSON também é adequado para a definição estruturada de dados e também é comumente usado como um **formato de troca de dados**. No entanto, ao contrário do XLM, o JSON é muito mais enxuto e pode ser processado com muito mais facilidade no código JavaScript.

Um recurso essencial do formato JSON são os colchetes, que definem objetos individuais. As propriedades dos objetos (também chaves) são escritas entre aspas duplas e separadas de seus valores por dois pontos.
```JSON
{
	"message": "Hello World"
}
```

É possível usar strings, valores numéricos, valores booleanos, matrizes ou outros objetos como valores, e a sintaxe é bastante semelhante à do JavaScript. A listagem 6.4 mostra a estrutura de um documento JSON que contém os mesmos dados que o documento XML da seção anterior. Em contraste com o XML, observe como o JSON é muito mais enxuto, principalmente devido à falta de tags de abertura e fechamento.
```JSON
{
	"contacts": [
		{
			"firstname": "John",
			"lastname": "Doe",
			"phone": {
				"type": "cell",
				"number": "01234567"
		},
		"email": "john.doe@example.com",
		"address": {
			"street": "Sample Street",
			"number": 99,
			"code": "12345",
			"city": "Sample City"
			}
		},
		{
		"firstname": "Paula",
		"lastname": "Doe",
		"phone": {
		"type": "cell",
		"number": "01234567"
		},
		"email": "paula.doe@example.com",
		"address": {
		"street": "Sample Street",
		"number": 99,
		"code": "12345",
		"city": "Sample City"
		}
		},
		{
		"firstname": "Peter",
		"lastname": "Doe",
		"phone": {
		"type": "landline",
		"number": "3456789"
		},
		"email": "peter.doe@example.com",
		"address": {
		"street": "Sample Street",
		"number": 200,
		"code": "12345",
		"city": "Sample City"
		}
		}
		]
}
```

**JSON Parsers**
Para processar documentos JSON, você precisará de um parser JSON adequado. Assim como no caso do XML, existem bibliotecas correspondentes para JSON em várias linguagens de programação que podem ser usadas para essa finalidade. Os JSON parsers convertem documentos JSON em uma estrutura de dados adequada.

No caso do JavaScript, a análise de documento JSON é incorporada nativamente à linguagem, o que significa que você não precisará de nenhuma biblioteca externa. Em vez disso, você pode converter diretamente uma cadeia de caracteres JSON em um #objeto JavaScript usando o método JSON.parse().

```JSON
const jsonString = `{
	"firstname": "John",
	"lastname": "Doe",
	"phone": {
		"type": "cell",
		"number": "01234567"
	},
	"email": "peter.doe@example.com",
	"address": {
		"street": "Sample Street",
		"number": 99,
		"code": "12345",
		"city": "Sample City"
	}
}`
const person = JSON.parse(jsonString);
console.log(person.firstname); // "John"
console.log(person.lastname); // "Doe"
console.log(person.phone.type); // "cell"
console.log(person.phone.number); // "01234567"
console.log(person.email); // "john.doe@example.com"
console.log(person.address.street); // "Sample Street"
console.log(person.address.number); // 99
console.log(person.address.code); // "12345"
console.log(person.address.city); // "Sample City"

```

Como alternativa, você pode até mesmo incorporar o JSON diretamente no JavaScript e atribuí-lo a uma variável, por exemplo, como mostrado na listagem 6.6. O JavaScript reconhece automaticamente o código JSON e o converte em um objeto correspondente.
```run-js
const person = `{
	"firstname": "John",
	"lastname": "Doe",
	"phone": {
		"type": "cell",
		"number": "01234567"
	},
	"email": "peter.doe@example.com",
	"address": {
		"street": "Sample Street",
		"number": 99,
		"code": "12345",
		"city": "Sample City"
	}
}`
console.log(person.firstname)
```

**JSON Schemas**
Semelhante a um esquema XML, você também pode definir esquema para o formato JSON. O esquema JSON também é um código JSON para **especificar exatamente a aparência do JSON** referido pelo esquema, por exemplo, quais objetos devem ser incluídos, quais propriedades esses objetos devem ter, quais valores eles têm e assim por diante. A Listagem 6.7 mostra o esquema JSON para o código JSON da Listagem 6.4.
```JSON
{
	"$schema": "http://json-schema.org/draft-04/schema#",
	"type": "object",
	"properties": {
	"contacts": {
	"type": "array",
	"items": {
	"type": "object",
	"properties": {
	"firstname": {
	"type": "string"
	},
}
```

Assim como ocorre com XML e esquemas XML, os validadores JSON correspondentes estão disponíveis para JSON e esquemas JSON, de modo que você pode verificar, para um determinado JSON e esquema JSON, se o JSON está em conformidade com as regras definidas no esquema JSON.

## 6.2 Image Formats
Além dos formatos de dados, os formatos de imagem naturalmente desempenham um papel importante no desenvolvimento de aplicativos da Web.

### 6.2.1 Fotografias no formato JPG
Para incluir fotografias ou imagens com alta profundidade ou dinâmica de cores (ou seja, imagens com muitas cores diferentes), uma prática recomendada é usar o formato JPG (também JPEG para Joint Photographic Experts Group). Esse formato pode exibir até 16 milhões de cores e também suporta diferentes níveis de compactação entre os dois extremos a seguir, o que afeta o tamanho do arquivo da imagem e sua qualidade:
1. 0% de compactação: compressão mais baixa, portanto, sem perda de qualidade, mas também com tamanho de arquivo inalterado;
2. 100% de compressão: compressão mais forte, menor tamanho de arquivo possível, mas com perda de qualidade;

### 6.2.2 Gráficos e animações no formato GIF
O formato GIF (Graphics Interchange Format) é particularmente adequado para imagens com poucas cores ou grandes áreas monocromáticas, pois só pode representar 256 cores (8 bits estão disponíveis por pixel, ou 28 valores possíveis). Para essas imagens, o formato GIF comprime os dados com mais eficiência do que o formato JPG. 
Em comparação com outros formatos, o GIF também pode fazer algo especial: você provavelmente já ouviu ou leu o termo GIF animado. Essas pequenas animações são salvas quadro a quadro em um único GIF. Os GIFs animados ganharam grande importância nos últimos anos, especialmente em plataformas como o Giphy e nas mídias sociais em geral.

### 6.2.3 Gráficos no formato PNG
O Portable Network Graphics (PNG) é uma mistura dos formatos GIF e JPG e tenta combinar os pontos fortes desses dois formatos. É feita uma distinção entre o método PNG-8, que é semelhante ao formato GIF, e o método PNG-24, que é mais parecido com o formato JPG. Você já deve ter advinhado: o método PNG-8 pode exibir 28 (256) cores, enquanto o método PNG-24 pode exibir 224 (aproximadamente 16,8 milhões) cores. Por esse motivo, você deve usar o processo PNG-8 somente se uma imagem tiver poucas cores ou consistir em áreas monocromáticas muito grandes, para fotografia é recomendado o PNG-24.

Além disso, com o método PNG-32, outros 8 bits estão disponíveis, usados para o canal alfa. Dessa forma, é possível criar imagens em que, dependendo do nível de transparência, o fundo da imagem fica mais ou menos visível, o que não é possível, por exemplo, com o formato JPG.

### 6.2.4 Vetores gráficos no formato SVG
JPG, GIF e PNG são formatos de bitmap. Os bitmaps consistem em pequenos retângulos (pixels), cada um deles com um valor de cor. Todos os formatos de bitmap dependem da resolução, portanto, não podem ser dimensionados arbitrariamente sem que os pixels individuais se tornem maiores e, portanto, visíveis. Você pode testar essa propriedade abrindo uma imagem em um dos formatos mencionados anteriormente em um navegador e, em seguida, ampliando a exibição (e, portanto, a imagem). 

No entanto, outro formato pode fornecer representações gráficas não baseadas em pixels, mas em linhas gerais de imagem. Os gráficos vetoriais são gráficos compostos de objetos gráficos primitivos, como linhas, círculos, polígonos e curvas. O que, à primeira vista, parece ser um gráfico relativamente monótomo, na prática é bem diferente: ao unir esses objetos gráficos primitivos, os gráficos mais complexos podem ser representados. 

Um formato vetorial que pode ser exibido por todo os navegadores modernos é o SVG (Scalable Vector Graphics), um formato baseado em XML para descrição de gráficos vetoriais. O nome já diz tudo: Os gráficos SVG são escalonáveis. Portanto, depois de criar um gráfico vetorial, você pode dimensioná-lo para qualquer tamanho sem perda de qualidade na renderização. 

#### Nota
Obviamente, como o SVG é um formato baseado em XML, você pode criar, modificar e até mesmo animá-lo dinamicamente usando linguagens de programação (porque o formato também oferece suporte 
à animação). Além disso, a aparência e o layout dos gráficos SVG podem ser modificados usando CSS.

### 6.2.5 Tudo fica melhor com o formato WebP
O formato WebP, criado pelo Google, tem o objetivo principal de minimizar as imagens para o menor tamanho de arquivo possível para a Web.  De acordo com o Google, as imagens e os gráficos nesse formato são cerca de 30% menores do que os arquivos PNG ou JPEG com a mesma qualidade de imagem. O WebP também permite que você selecione o método de compactação usado. Assim, você pode distinguir entre sem perdas (como PNG) e com perdas (como JPEG). Devido a essa liberdade de escolha, o formato WebP é adequado tanto para fotos quanto para gráficos.

### Incluindo imagens com HTML
Todos os formatos de imagem mencionados podem ser adicionados a uma página Web usando a tag `img`; Como parâmetro para o atributo src, basta especificar o URL do arquivo de imagem. E já que estamos falando disso: o atributo SRC pode ser passado não apenas um URL para um arquivo de imagem, mas também um URI (Uniform Resource Identifier) de dados, que pode conter os dados da imagem como texto codificado em Base64. 

## Video e formatos de áudios
Além dos formatos de dados e imagens que discutimos até agora, os formatos de vídeo e áudio são especialmente relevantes para o desenvolvimento de aplicativos da Web.

### 6.3.1 Formatos de vídeos
Antes do HTML5, os vídeos tinham de ser incorporados usando o Flash, mas desde o HTML5, você incorporar vídeos usando um elemento separado - usando a tag `video`. Os navegadores exibem o vídeo correspondente por meio de seu próprio player de vídeo. 

**Incluindo vídeos**
No início do HTML5, diferentes navegadores eram compatíveis com diferentes formatos de vídeo: enquanto o FireFox suportava o formato OGV, o Chrome suportava os formatos OGV, WebM e MP4, e o Safari suportava apenas MP4. Por esse motivo, a tag `video` foi projetada para lidar diretamente com vários formatos de vídeo (e codecs). Como resultado, você pode definir formatos alternativos do vídeo por meio do elemento `source`, que é simplesmente colocado como um elemento filho no `video` correspondente e, assim, oferecer suporte a diferentes navegadores. Você define o tipo de vídeo no elemento `source` por meio do atributo type: Basta especificar o tipo MIME aproprieado.
```html
<video controls="controls" height="360" width="640">
	<source src="my-video.mp4" type="video/mp4" >
	<source src="my-video.webm" type="video/webm" >
	<source src="my-video.ogg" type="video/ogg" >
	<p>The browser you’re using does not support HTML5 video</p>
</video>
```

**Configurando vídeos e players**
Além disso, podemos usar vários atributos para influenciar determinadas propriedades do vídeos ou do player de vídeo. Por exemplo, com a propriedade autoplay, podemos especificar se o vídeo deve ser reproduzido automaticamente ou se deve ser pausado primeiro quando a página da Web correspondente for aberta. A propriedade #controls permite especificar se os controles do player de vídeo devem ser exibidos ou não. 
![[Capítulo 6 - Utilizando Web Formats-1.png]]

A propósito, o reprodutor de vídeo que é exibido no navegador para reproduzir vídeos também pode ser controlado por meio de JavaScript. Assim, o elemento #video fornece métodos como play() e pause() por meio dos quais você pode influenciar a reprodução do vídeo no programa. 
