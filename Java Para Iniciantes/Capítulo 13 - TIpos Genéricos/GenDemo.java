class Gen<T> {
    T ob; // declara um objeto de tipo T

    Gen(T o) {
        ob = o;
    }

    T getob() {
        return ob;
    }
}

public class GenDemo {
    public static void main(String[] args) {
        // criando um objeto Gen para Integers
        Gen<Integer> x1 = new Gen<Integer>(88);

        Gen<String> s1 = new Gen<String>("Generics Teste");

        // criando um objeto de tipo bruto e fornecendo um valor Double
        Gen raw = new Gen(98.2); 

        // precisamos fazer uma coerção
        double d = (Double) raw.getob(); // Casting + coerção
        System.out.println("value: " + d);

        // o uso de um tipo bruto pode levar a exceções
        // de tempo de execução. 

        // int i = (Integer) raw.getob();
    }
}