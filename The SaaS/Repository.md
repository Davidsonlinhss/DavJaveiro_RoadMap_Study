A interface #UserRepository é uma *interface* que serve para acessar os dados dos usuários no banco de dados. Ele é como um *contrato* que define quais operações podem ser feitas, como **buscar**, **salvar**, **deletar** etc.
Exemplo Simples:
```java
public interface UserRepository {
	User findByEmail(String email);
}
```
- *findByEmail:* é um método que busca um usuário pelo email. 

## 2. Por que usar *Optional< User>*
O #Optional é uma forma de dizer: *Este valor pode existir ou não.* Em vez de retornar *null* quando o usuário não for encontrado, usamos *optional* para deixar isso mais claro. 

**Sem o optional**
```java
User user = userRepository.findByEmail("email@example.com");
if (user == null) {
	System.out.println("Usuário não encontrado");
}
```

**Com o optional**
```java
	Optional<User> optionalUSer = userRepository.findByEmail("email@example.com);
if(optionalUser.isPresent()) {
	SOUT("Usuário encontrado: " + optionalUser.get().getName());
} else {
	System.out.println("Usuário não encontrado!");
}
```

**Por que usar?**
A interface *UserRepository* define métodos para acessar os dados do banco. Por exemplo:
- Buscar um usuário pelo ID;
- Salvar um novo usuário;
- Atualizar ou deletar um usuário.
Quando usamos o Spring Data JPA, muitos desses métodos já estão prontos. Só precisamos declará-los na interface, e o Spring cuida do resto.

### **4. Por que ela extende `JpaRepository`?**
O #JpaRepository é uma classe especial do Spring Data JPA que já tem vários métodos prontos para trabalhar com o banco de dados. Ao extender essa classe, ganhamos acesso a esses métodos sem precisar escrever código extra.
```java
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
}
```
- *JpaRepository< User, Long>*: diz que estamos trabalhando com a enti

## Resumo Simples
- O *UserRepository* é uma interface para acessar os dados dos usuários;
- O *Optional< User>* ajuda a evitar problemas com valores nulos;
- O *JpaRepository* fornece métodos para facilitar o trabalho com banco de dados.



