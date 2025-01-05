public class Recursivo {
    public static void main(String[] args) {
        int number = 5;
        int calc = 1;
        int i = 1;
        while(i <= number) {
            calc *= i;
            i++; // usado para icrementar
            System.out.println(i-1 + " X ");
        }
        System.out.println("O fatorial de " + number + " é " + calc);
    }
}
