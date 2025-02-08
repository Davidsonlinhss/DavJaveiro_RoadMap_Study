
import java.util.function.Consumer;

public class GenericClass {
    public static void main(String[] args) {
        

        Consumer<String> imprimirMensagem = GenericClass::printMessage;

        imprimirMensagem.accept("Teste");
        
        



    }

    public static void printMessage(String message) {
        System.out.println(message);
    }
}
