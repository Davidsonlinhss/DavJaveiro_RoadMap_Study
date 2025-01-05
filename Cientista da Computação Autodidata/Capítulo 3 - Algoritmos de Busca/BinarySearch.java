
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BinarySearch {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(10, 12, 13, 14, 15, 16, 19);

        List<String> words = Arrays.asList("Abacate", "Banana", "Maçã");

        String target2 = "Maçã";
        // Collections.sort(words);

        int index2 = Collections.binarySearch(words, target2);

        System.out.println((index2 >= 0) ? "Sua fruta foi localizada na lista: " + target2 + "índice: " + index2 : "Elemento não encontrado. Posição esperada: " + (-index2 -1));

        int target = 14;
        int index = Collections.binarySearch(nums, target);

        System.out.println(
                (index >= 0) ? "Elemento encontrado no índice: " + index
                        : "Elemento não encontrado. Posição esperada: " + (-index - 1));
    }
}
