// Classe genérica com dois parâmetros de tipos: T e V.

class TwoGen<T, V> {
    T ob1;
    V ob2;

    TwoGen(T o1, V o2) {
        ob1 = o1;
        ob2 = o2;
    }

    // Exibindo os tipos de T e V
    void showTypes() {
        System.out.println("Type of T is " + ob1.getClass().getName());
        System.out.println("Type of V is " + ob2.getClass().getName());
    }

    T getOb1() {
        return ob1;
    }

    V getOb2() {
        return ob2;
    }
}

public class SimpGen {
    public static void main(String[] args) {
        // classes wrappers encapsulando os tipos primitivos
        TwoGen<Integer, String> tgObj = new TwoGen<Integer, String>(88, "Super-Man");

        // exibindo os tipos
        tgObj.showTypes();

        int v = tgObj.getOb1(); // neste ponto = autounboxing;
        System.out.println("Value of Integer is: " + v);

        String str = tgObj.getOb2();
        System.out.println("Value of String is: " + str);

        

    }
}
