interface MyFunc {
    int func(int n);
}

public class VarCapture {
    public static void main(String args[]) {
        // Uma variável local que pode ser capturada
        final int num = 10; // a variável local é efetivamente final

        MyFunc myLambda = (n) -> {
            // Correto, não estamos modificando num, apenas capturando o seu valor
            int v = n + num;

            // Incorreto, não podemos modificar num
            // num++;


            return v;
        };

        System.out.println(myLambda.func(10));
    }
}
