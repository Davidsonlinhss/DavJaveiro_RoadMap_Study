// Usa uma referência a um método de instância

// Interface funcional para predicados numéricos que opera com
// valores inteiros.

interface IntPredicate {
    boolean test(int n);
}

// Esta classe armazena um valor int e define o método de instância isFactor(), que retorna true quando seu argumento é fato do valor armazendo.
class MyIntNum {
    private int v;

    MyIntNum(int x) {
        v = x;
    };
    
    int getNum() {return v;}

    boolean isFactor(int n) {
        return (v % n) == 0;
    }


}
public class MethodRefDemo2 {
    public static void main(String args[]) {
        boolean result;

        MyIntNum myNum = new MyIntNum(12);
        MyIntNum myNum2 = new MyIntNum(16);

        // Aqui, uma referência ao método isFactor é criada em myNum.
        IntPredicate ip = myNum::isFactor; // referência a um método
        
        // Agora, ela é usada para chamar isFactor() via test().
        result = ip.test(3);
        if(result) 
            System.out.println("3 is a factor of " + myNum.getNum());

        // Desta vez, uma referência ao método isFactor é criada em myNum2.
        // e usada para chamar isFactor() via test().

        ip=myNum2::isFactor;
        result = ip.test(3);
        
        if(!result) 
            System.out.println("3 isn't a factor of " + myNum2.getNum());

    }
}
