
                                    // neste caso, o argumento de tipo deve
class NumericFns<T extends Number> { // ser Number ou uma subclasse de Number
    T num;

    // Passa para o construtor uma referência
    // a um objeto numérico.
    public NumericFns(T n) {
        num = n;
    }

    // retorna o recíproco
    double reciprocal() {
        return 1 / num.doubleValue();
    }

    // retorna o componente fracionário
    double fraction() {
        return num.doubleValue() - num.intValue();
    }

    
}

public class BoundsDemo {
    public static void main(String args[]) {
        // podemos utilizar Integer pois é subclasse de Number
        NumericFns<Integer> iOb = new NumericFns<Integer>(5);

        System.out.println("Reciprocal of iOb is " + iOb.reciprocal());
        System.out.println("Fractional component of iOb is " + iOb.fraction());

        System.out.println();
        // Double também pode ser usado
        NumericFns<Double> dOb = new NumericFns<Double>(5.25);

        System.out.println("Reciprocal of dOb is " + dOb.reciprocal());
        System.out.println("Fractional of dOb is " + dOb.fraction());
    }
}
