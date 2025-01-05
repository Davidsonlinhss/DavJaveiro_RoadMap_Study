## Controller
A classe controller é um controlador em uma aplicação Spring Boot. Ela é responsável por definir #endpoints para operações relacionadas ao recurso "candidate" (candidato).

É a classe onde definimos os pontos de entrada HTTP (endpoinst) para interagir com o recurso "candidate". 

## Anotações
A anotação #RestController informa ao Spring que esta classe é um **controlador de API REST**. Isso significa que os #métodos desta classe retornam dados diretamente como resposta da API, em vez de redirecionar para uma página HTML. 

Os dados são geralmente retornados em formato JSON (ou outro tipo de mídia), tornando-os ideais para consumo por clientes frontend ou outras aplicações. 

#RequestMapping: essa anotação, usada na classe, define o caminho base para todos os endpoints dentro do candidateController. Nesse caso, /candidate é o caminho inicial.  Todos os métodos dentro deste controlador terão URLs começando com /candidate.

## Método Create
@PostMapping
Essa anotação mapeia o método create() para responder a **requisições HTTP do tipo POST** no caminho /candidate/.

## Exception Handler
O lançamento de exceções é algo fundamental em um software. Quando ocorre um erro no fluxo, é importante que ele seja mapeado para informar de modo evidente o que aconteceu por logs ou retorno ao usuário.

Em aplicações REST, a exceção é motrada na resposta do endpoint. A forma mais simples de se fazer seria adicionar um try-catch no controlador:
```java
@PostMapping("/users")  
fun createUser(@Valid @RequestBody user: User): User {  
try {  
return user  
} catch (exception: MethodArgumentNotValidException){  
// ... criar retorno padronizado  
// ex: { "field": "email", "error": "not follows the pattern mail@mail.com" }  
}  
}
```
Mas essa abordagem traz alguns problemas:
- Em cada função do controlador, deveremos usar um try-catch;
- Ao adicionar uma nova exceção, deveremos mudar em todos os controladores que iriam capturá-la;

Desse modo, o ideal seria centralizar a captura de exceções em uma outra parte do código e esta montar o retorno para nós. No final, o controlador ficaria com um código mais simples e, quando ocorresse a exceção, um controlador específico de exceções iria montar o retorno para gente. 

Quando criamos a classe global para tratar as exceções lançadas pelo endpoint da controladora, nós precisamos informar ao Spring que esta classe vai interceptar o retorno do endpoint e modificá-lo dependendo da exceção lançada, usamos o <span style="background:#d3f8b6">@ControllerAdvice</span> 