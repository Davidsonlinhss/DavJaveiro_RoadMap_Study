// o autoboxing/unboxing ocorre com parâmetros
// e os valores de retorno de
public class AutoBox2 {

    // Esse método tem um parâmetro Integer
    static void m(Integer v) {
        System.out.println("m() received " + v);
    }

    // Esse método retorna um int
    static int m2() {
        return 10;
    }

    // Esse método retorna um Integer
    static Integer m3() {
        return 99;
    }

    public static void main(String args[]) {
        m(199);
        // Aqui, iOb recebe o valor int retornado por m2().
        // Esse valor é encapsulado automaticamente para
        // poder ser atribuído a iOb.
        Integer iOb = m2();
        System.out.println("Return value from m3() is " + iOb); // se torna Integer

        // Em seguida, m3() é chamado. Ele retorna um valor Integer
        // que é encapsulado  automaticamente em um int.
        int i = m3();
        System.out.println("Return value from m3() " + i);

        // Agora, Math.sqrt() é chamado com iOb como argumento.
        // Nesse caso, iOb sofre autounboxing e seu valor é promovido
        // a double, que é o tipo que sqrt() precisa.
        iOb = 100;
        System.out.println("Square root of iOb is " + Math.sqrt(iOb));
    }
}
