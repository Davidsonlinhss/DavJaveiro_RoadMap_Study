public class TrueRecursivo {
    public static void main(String args[]) {
        int number = 5;
        int result = factorial(number);
        System.out.println("O fatorial de " + number + " é " + result);
        
    }

    public static int factorial(int n) {
        // caso base, o método factorial verifica se n == 0 ou n ==1.
        if(n == 0 || n == 1) {
            return 1;
        }
        return n * factorial(n-1);
    }
    
}
