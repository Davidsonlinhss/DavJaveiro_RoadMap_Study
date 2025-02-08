
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

public class TerceiroExer {
    public static void main(String[] args) {
        List<String> frutas = new ArrayList<>(Arrays.asList("Banana", "Maçã", "Pé de Cuelo"));

        frutas.add("Jaca");

        BiPredicate<List<String>, String> verificarPresenca = List::contains;

        System.out.println(verificarPresenca.test(frutas, "Banana"));

        
    }

}
