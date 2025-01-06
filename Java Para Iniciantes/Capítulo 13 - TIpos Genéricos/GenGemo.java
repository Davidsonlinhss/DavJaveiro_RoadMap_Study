
class Gen<T> {
    T ob; // declara um objeto do tipo T, T ẽ o parâmetro de tipo genérico

    // Passa para o construtor uma
    // referência a um objeto de tipo T

    Gen(T o) {
        ob = o;
    }

    T getOb() {
        return ob;
    }

    // Exibindo o tipo de T
    void showType() {
        System.out.println("Type of T is " + ob.getClass().getName());
    }
}

// Demonstra a classe genérica
public class GenGemo {
    public static void main(String[] args) {
        // Cria uma referência Gen para INtegers.
        Gen<Integer> iOb = new Gen(99);

        // CRia um objeto GEn<Integer> e atribui sua
        // referência a iOb. OBserve o uso do autoboxing no encapsulamento do valor 88 dentro de um objeto INteger
        // iOb = new Gen<Integer>(88); // instanciando um objeto de tipo Gen<INteger>

        // Exibe o tipo de dado usado por iOb.
        iOb.showType();

        // OBtém o valor de iOb.
        int v = iOb.getOb(); //autounboxing
        System.out.println("value: " + v);

        System.out.println();

        // Cria um objeto Gen para Strings
        Gen<String> strOb = new Gen<String>("Generics Teste");

        strOb.showType();

        // Obtendo o valor de strOb,.
        String str = strOb.getOb();
        System.out.println("value: " + str);
    }
}
