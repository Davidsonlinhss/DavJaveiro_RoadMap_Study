## <span style="background:#d4b106">Forma geral de uma classe</span>
Quando definimos uma classe, declaramos sua forma e natureza exatas. Fazemos isso especificando as **variáveis de instância** que ela contém e os #métodos que operam sobre elas. Embora classes muitos simples possam conter apenas #métodos ou apenas #variáveis_de_instância, a **maioria das classes** do mundo real contém ambos. 

Uma classe é criada com o uso da palavra-chave #class. Uma forma geral simplificada de uma definição #class é mostrada aqui:
```Java
class nome_da_classe {
// declara variáveis de instância
private int var1;
private int var2;

// método construtor padrão
public nome_da_classe() {

}
}
```

Embora não haja essa regra sintática, uma classe bem projetada deve definir apenas uma entidade lógica. Por exemplo, normalmente, uma classe que armazena nomes e números de telefone não armazena também informações sobre o mercado de ações, a média pluviométrica, os ciclos das manchas solares ou outros dados não relacionados. 

O método #main() só é necessário quando a classe é o ponto de partida do programa. Alguns tipos de aplicativos Java, como os #applets, também precisam de um método main().
## <span style="background:#d4b106">Definindo uma classe</span>
Desenvolveremos uma classe chamada **Vehicle** que conterá três informações sobre um veículo:
- o número de passageiros que ele pode levar;
- a capacidade de armazenamento de combustível;
- e o consumo médio de combustível (em milhas por galão).
A primeira versão da classe conterá apenas dados.
```Java
class Vehicle {
	int passengers;
	double fuelcap;
	double mpg;
}
```
Lembramos que uma declaração #class é só uma descrição de tipo; ela não cria um objeto real. Logo, o código anterior não faz nenhum objeto de tipo **Vehicle** passar a existir.

Para criar realmente um objeto Vehicle, usaremos a instrução como mostrada abaixo:
```Java
Vehicle minivan = new Vehicle(); // cria um objeto Vehicle chamaddo minivan
```
Após essa instrução ser executada, **minivan** será uma instância de Vehicle. Portanto, terá realidade "física". Por enquanto, não se preocupe com os detalhes da instrução. Sempre que criarmos uma instância de uma classe, estará criando um objeto contendo sua própria cópia de cada variável de instância definida pela classe. Para acessar as variáveis de instância de uma classe, usaremos o operador ponto (.). O *operador ponto* vincula o nome de um objeto ao nome de um membro. A forma geral do operador ponto é mostrada aqui: 
```java
objeto.membro
```
Por exemplo, para atribuir o valor 16 à variável fuelcap de minivan, use a instrução a seguir:
```java
minivan.fuelcap = 16;
```
