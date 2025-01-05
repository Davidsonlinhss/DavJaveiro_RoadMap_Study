public class AutoBoxTres {
    public static void main(String args[]) {
        Integer iOb, iOb2;
        int i;

        iOb = 99;
        System.out.println("Original value of iOb: " + iOb);

        // o trecho a seguir faz o unboxing automático
        // de iOb, executa o incremento e encapsula
        // o resultado novamente em iOb.
        ++iOb;
        System.out.println("After ++iOb" + iOb);
        
        // Aqui iOb sofre unboxing, seu valor é aumentado em 10 e o
        // resultado é encapsulado e armazenado novamente em iOb.

        iOb += 10;
        System.out.println("iOb2 after expression: " + iOb);

        // Agora, iOb sofre unboxing, a expressão é
        // avaliada e o resultado é encapsulado novamente
        // e atribuído a iOb2
        iOb2 = iOb + (iOb / 3);
        System.out.println("iOb2 after expression: " + iOb2);


        // A mesma expressão é avaliada, mas o resultado não é encapsulado
        i = iOb + (iOb / 3);
        System.out.println("i after expression: " + i);
    }
}
