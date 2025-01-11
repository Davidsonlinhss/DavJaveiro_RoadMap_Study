// Demonstra uma referência de construtor.

// MyFunc é uma interface funcional cujo método retorna
// uma referência MyClass.

interface MyFunc {
    MyClass func(String s);
}

class MyClass {
    private String str;

    // Este construtor recebe uma argumento.
    MyClass(String s) { str = s;}

    // Este é o construtor padrão
    MyClass() { 
        str = "";
    }

    String getStr() { return str;}
}

public class ConstructorRefDemo {
    public static void main(String[] args) {
        MyFunc myClassCons = MyClass::new;

        // Cria uma instância de MyClass usando essa referêcnai de construtor
        MyClass mc = myClassCons.func("Testing");

        System.out.println(mc.getStr());
    }
}
