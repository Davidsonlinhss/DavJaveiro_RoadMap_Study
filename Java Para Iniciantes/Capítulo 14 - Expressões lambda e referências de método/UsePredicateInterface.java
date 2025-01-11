
import java.util.function.Predicate;

// Usa a interface funcional interna Predicate

public class UsePredicateInterface {
    public static void main(String[] args) {
        Predicate<Integer> isEven = (n) -> (n %2) ==0;

        System.out.println((isEven.test(3)) ? " the number is even" : "the number is odd");
    }
}
