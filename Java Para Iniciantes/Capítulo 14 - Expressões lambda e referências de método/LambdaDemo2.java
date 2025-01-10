// Usa a mesma interface funcional com três expressões lambda diferentes

// Interface funcional que usa dois parÂmetros int e
// retorna um resultado boolean

interface NumericTest{
    boolean test(int n, int m);
}

public class LambdaDemo2 {
    public static void main(String[] args) {
        // Esta expressão lambda determina se um número
        // é fator de outro

        NumericTest myTest; // referenciamos apenas 1 única vez

        myTest = (n, d) -> (n % d) == 0; 

        if(myTest.test(10, 2))
            System.out.println(" 2 is a factor of 10");
        if(!myTest.test(10, 3))
            System.out.println(" 3 is not a factor of 10");
        System.out.println();

        // Esta expressão lambda retorna true se o
        // primeiro argumento for menor que o segundo.
        NumericTest lessThan = (n, m) -> (n > m);


        // expressão retorna true ou false a depender do argumento comparativo, onde o primeiro é maior do que o segundo
        System.out.println((lessThan.test(10, 2)) 
            ? " 10 is less than 2" 
                : " 10 don't less than 2");

        System.out.println((lessThan.test(10, 99)) 
            ? " 10 is less than 99" 
                : " 10 don't less than 99");

        // Comparando se os valores são igauis
        NumericTest absEqual = (n, m) -> n == m;
        System.out.println((absEqual.test(10, 10)) ? " 10 é igual a 10" : " 10 não é igual a 10");
    }
}
