// exemplo de interface genérica

// Interface genérica que lida com armazenamento
// Esta interface requer que a classe usuária
// tenha um ou mais valores

interface Containment<T> {
    // o método contains() verifica se um item
    // especificado está contido dentro de um
    // ojeto que implementa Containment

    boolean contains(T t);
}

// Implementea Containment usando um arrat
// para armazenar os valores
class MyClass<T> implements Containment<T> {
    T[] arrayRef;

    MyClass(T[] o) {
        arrayRef = o;
    }

    public boolean contains(T o) {
        for(T x : arrayRef)
            if(x.equals(o)) return true; // se contém valor
        return false; // se não contém
    }
}



public class GenIFDemo {
    public static void main(String[] args) {
        Integer x[] = {1, 2, 3};

        MyClass<Integer> ob = new MyClass<Integer>(x);

        System.out.println(ob.contains(2) 
            ? "2 is in ob" : "2 is NOT in ob");
        
        System.out.println(ob.contains(5) 
            ? "5 is in ob" : "5 is NOT in ob");
        
            
    }
}
