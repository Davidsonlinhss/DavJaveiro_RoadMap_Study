Definimos as especificações da API usando OpenAPI no capítulo anterior. As interfaces e modelos Java da API foram gerados pelo OpenAPI (Swagger Codegen). Neste capítulo, implementaremos o código da API em termos de lógica de negócios e persistência de dados. Aqui, lógica de negócios se refere ao código real estamos escrevendo para funcionalidades do domínio, que, no nosso caso, compreendem operações realizadas para e-commerce, como finalizar a compra do carrinho de compras.

Escreveremos serviços e repositórios para a implementação e adicionaremos hypermedia e tags de entidade (ETags) às respostas da API. 
O hypermedia As The Engine Of Application State (HATEOAS) será implementando usando Spring e Hypertext Application Language (HAL). HAL é um dos padrões para implementar HATEOAS. Outros são **Collection + JSON** e **JSON-LD**. Você usará HAL neste livro. Você pode encontrar um exemplo disso no primeiro exemplo da seção "Adicionando ETags às respostas da API", denotado pelo campo links. Vale ressaltar que o código fornecido consiste apenas nas linhas importantes e não no arquivo completo, por uma questão de brevidade.  

Este capítulo cobrirá:
- Visão geral para o serviço de design;
- Adicionando  componentes de repositório;
- Implementando hypermedia;
- Aprimorando o controlador com um serviço e HATEOAS;
- Adicionando ETags às respostas da API;
- Testando as APIs.

## Technical requirements
Para executar as instruções neste e nos próximos capítulos, precisaremos de algum cliente API REST, tal como *Insomnia* or *Postman*.

## Overvien of the service design
