// Usa uma interface funcional genérica.

// Interface funcional genérica com dois parâmetros que
// retorna um resoltado boolean

interface SomeTest<T> {
    boolean test(T n, T m);
}

public class GenericFunctionalInterfaceDemo {
    public static void main(String args[]) {
        
        // Esta expressão lambda determina se um inteiro
        // é fator de outro
        SomeTest<Integer> isFact = (n, d) -> (n % d) == 0;        

        System.out.println((isFact.test(10, 2)) 
            ? " 2 is factor of 10" 
                : " 2 ins't factor of 10");

        // Expressão lambda que determina se um Double é fator de outro
        SomeTest<Double> isDouble = (n, d) -> (n % d) == 0;
        
        System.out.println((isDouble.test(212.0, 4.0)) 
            ? "4.0 is factor of 212.0" 
                : "4.0 ins't factor of 212.0");
        
        // Esta expressão lambda, determina se um String faz parte de outro
        SomeTest<String> isIn = (a, b) -> a.indexOf(b) != -1;
        String str = "Superman, the superhero!";

        System.out.println(isIn.test(str, "the superhero") 
            ? "'the superhero' is found." 
                : "'the superhero'ins't found.");
    }
}
