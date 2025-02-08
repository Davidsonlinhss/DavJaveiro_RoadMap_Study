import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;

public class FirstExer {
    public static void main(String[] args) {
        Function<String, Integer> stringToInt = Integer::parseInt;
        String numeroComoString = "42";
        int numero = stringToInt.apply(numeroComoString);
        System.out.println("O número convertido é: " + numero);


        
        String nome = "Java";
        Supplier<Integer> stringLength = nome::length;
        System.out.println(stringLength.get());

        // Criando um Supplier que gera números aleatórios
        Supplier<Integer> numeroAleatorio = ( ) -> new Random().nextInt(100);

        // Criando o Supplier Random, utilizando Method Reference
        Random random = new Random();
        Supplier<Number> numeroAleatorio2 = random::nextDouble;



        System.out.println(numeroAleatorio.get());
        System.out.println(numeroAleatorio2.get());
        
        Supplier<Number> numeroAleatorio3 = FirstExer::gerarNumeroAleatorio;

        System.out.println(numeroAleatorio3.get());

        // quarta forma
        Random random1 = new Random();

        Function<Double, Double> numeroAleatorio4 = random1::nextDouble;
        System.out.println("Numero aleatório com Function: " +
            numeroAleatorio4.apply(100.00));


    }

    private static double gerarNumeroAleatorio() {
        return new Random().nextDouble(10);
    }

}
