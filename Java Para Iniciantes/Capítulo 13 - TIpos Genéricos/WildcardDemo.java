// Usa um curinga
class NumericFns<T extends Number> {
    T num;

    // Passas para o construtor uma referência
    // a um objeto numérico

    NumericFns(T n) {
        num = n;
    }

    double reciprocal() {
        return 1 / num.doubleValue();
    }

    double fraction() {
        return num.doubleValue() - num.intValue();
    }

    // determina se os valores absolutos de dois objetos são iguais:
    boolean absEqual(NumericFns<?> ob) { // tipo curinga como argumento método
        if(Math.abs(num.doubleValue()) ==
            Math.abs(ob.num.doubleValue())) return true;

        return false;
    }
}

public class WildcardDemo {
    public static void main(String args[]) {
        NumericFns<Integer> iOb 
            = new NumericFns<Integer>(6);

        NumericFns<Double> dOb 
            = new NumericFns<Double>(-6.0);

        NumericFns<Long> lOb 
            = new NumericFns<Long>(5L);

        System.out.println("Testing iOb and dOb.");

        System.out.println(
            iOb.absEqual(dOb) 
                ? "Absolute values are equal." 
                    : "Absolute values differ");
        
        System.out.println();

        System.out.println("Testing iOb and lOb.");

        System.out.println(
            iOb.absEqual(lOb)
                ? "Absolute values are qual. "
                    : "Absolute values differ.");
    }
}
