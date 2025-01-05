Os tipos primitivos não são objetos, mas às vezes é necessário trabalhar com eles como se fossem. Quando precisamos passar um <font color="#ffff00">valor primitivo</font> como um <font color="#ffff00">parâmetro</font> de um método, precisamos converter o valor primitivo em um objeto. Nesse caso, podemos usar os tipos #wrappers.

Os tipos #wrappers são classes que representam os tipos primitivos. Eles são imutáveis, o que significa que uma vez criado, um objeto wrapper não pode ser alterado para garantir a sua segurança. O Integer representa o tipo primitivo int.

#Integer -> int
#Long -> long
#Float -> float
#Double -> double
#Short -> short
#Byte -> byte
#Boolean -> boolean
#Character -> chat

## CONVERSÃO DE TIPOS

A conversão é necessária quando desejamos **armazenar** um valor de um tipo de dado em uma variável de outro tipo. Por exemplo, podemos armazenar um valor #int em uma variável de tipo #boolean porque o tipo #double pode armazenar valores inteiros e fracionários. 

São dois os modos de conversão:
- #Explícita: é feita por meio de uma expressão de conversão de tipos. Ela é necessária quando desejamos **converter um valor de um tipo de dado em outro que não seja compatível** com o dado **original**. 
- #Implícita: é realizada automaticamente pelo compilador da linguagem não sendo necessário nenhum tipo de demarcação.

