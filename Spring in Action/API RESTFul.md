A estrutura de uma API Restful em #Java segue os padrões de camadas, geralmente organizadas em um modelo MVC (Model-View-Controller) adaptado para aplicações backend. Aqui está uma explicação sobre cada camada e suas interações:

## Camadas na Arquitetura RESTful
1. #Controller (controlador):
- Responsável por lidar com as requisições HTTP recebidas e enviá-las para as camadas apropriadas;
- É onde as APIs são definidas ( #endpoints) e as respostas são devolvidas. 
- Geralmente usa anotações como #RestController e #RequestMapping.
- Fica na pasta #controller.
Exemplo em Java:
```java
@RestController
@RequestMapping("/users")
public class UserController {
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
		UserDTO user = userService.findById(id);
		return ResponseEntity.ok(user);
	}
}
```

2. #Service (serviço):
- Contém a lógica de negócios da aplicação.
- Serve como intermediário entre o #controlador e o repositório.
- Geralmente usa a anotação #Service.
- O local padrão é dentro da <span style="background:#d4b106">pasta service</span>.
Exemplo:
```java
@Service
public class UserService { 
	private final UserRepository userRepository;
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserDTO findById(Long id) {
		User user = userRepository.findById(id)
			.orElseThrow(() -> new RunTimeException("User not found!"));
		return new UserDTO(user);
	}
}
```

3. #Repository (Repositório):
- Responsável por interagir diretamente com o banco de dados.
- Define métodos para buscar, salvar, atualizar e excluir dados. CRUD.
- Usa anotações como @Repository e interfaces que estendem #JpaRepository
- Classe fica na pasta <span style="background:#d4b106">repository</span>.

Exemplo:
```java
public interface UserRepository extends JpaRepository<User, Long> {

}
```

4. Entities (Entidades): 
- Representam as tabelas do banco de dados.
- Contêm atributos que mapeiam os campos da tabela e anotações como @Entity, @Id, @Column...
- A classe fica na <span style="background:#d4b106">pasta entities ou model</span>.

Exemplo:
``` java
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;

	// Getters and setters
}
```

5. DTO (Data Transfer Object): **Microservices**?
- São objetos usados para transferir dados entre camadas sem expor diretamente as entidades.
- Útil para evitar exposição desnecessária de informações sensíveis.
- Fica na <span style="background:#d4b106">pasta DTO</span>.
Exemplo:
``` java
public class UserDTO {
	private Long id;
	private String name;

	public UserDTO(User user) {
	this.id = user.getId();
	this.name = user.getName();
	}

}
```

## **Fluxo de Dados**
1. O cliente faz uma requisição para um endpoint do #Controller.
2. O #Controller chama o #Service para processar a lógica.
3. O #Service interage com o #Repository para acessar os dados.
4. O #Repository consulta o banco de dados e retorna os dados para o #Service.
5. O #Service retorna os dados processados para o #Controller.
6. O #Controller responde ao cliente.

Diagrama simplificado
![[Diagram.png]]