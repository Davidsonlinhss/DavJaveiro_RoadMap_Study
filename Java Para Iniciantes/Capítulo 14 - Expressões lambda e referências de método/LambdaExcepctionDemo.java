import java.io.*;

interface MyIoAction {
    boolean ioAction(Reader rdr) throws IOException;
}

public class LambdaExcepctionDemo {
    public static void main(String[] args) {
        double[] values = { 1.0, 2.0, 3.0, 4.0};

        // Este bloco de lambda pode lançar uma IOException.
        // Logo, a IOException deve ser especificada em uma cláusula
        // throws da ioAction() em MyIOAction.

        MyIoAction myIo = (rdr) -> {
            int ch = rdr.read();  // esta expressão pode lançar uma exceção
            // ...
            return true;
        };
    }
}
