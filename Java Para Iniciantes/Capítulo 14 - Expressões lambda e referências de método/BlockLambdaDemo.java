// Uma lambda de bloco que encontra o menor fator positivo
// de um valor int;

interface NumericFunc {
    int func(int n);
}

public class BlockLambdaDemo {
    public static void main(String args[]) {
        // Esta lambda de bloco retorna o menor fator positivo de um valor.
        NumericFunc smallestF = (n) -> {
            int result = 1;

            // Obtém o valor absoluto de n, exceto 1;
            n = n < 0 ? -n : n;
            
            for(int i=2; i <= n/i; i++)
                if((n % i) == 0) {
                        result = i;
                        break;
                }
            return result;
        };
        System.out.println("Smallest factor of 4 is " + smallestF.func(4));

    }
}
