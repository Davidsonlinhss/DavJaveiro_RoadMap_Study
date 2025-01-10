// Demonstra  duas expressões lambda simples

// uma interface funcional
interface MyValue {
    double getValue();
}

// OUtra interface funcional.
interface MyParamValue {
    double getValue(double v);
}



public class LambdaDemo {
    public static void main(String args[]) {
        MyValue myVal; // declarando uma referência de interface

        // Aqui, a expressão lambdaé simplesmente uma expressão constante.
        // QUando ela é atribuída a myVal, é construída a instância
        // de uma classe em que a expressão lambda implementa o 
        // método getValue() de MyValue
        myVal = () -> 98.6; // expressão lambda simples

        // chama getValue(), que é fornecido pela
        // expressão lambda atribuída anteriormente
        System.out.println("A constant value: " + myVal.getValue());

        // Agora, cria uma expressão lambda parametrizada e a atribui
        // a uma referência de MyParamValue. Essa expressão lambda retorna
        // o recíproco de seu argumento.
        MyParamValue myPval = (n) -> 1.0 / n;

        // CHama getValue() por intermédio da referência de myPval.
        System.out.println("Reciprocal of 4 is " + myPval.getValue(4.0));
        System.out.println("Reciprocal of 8 is " + myPval.getValue(8.0));

        // Uma expressão lambda deve ser compatível com o método definido
        // pela interface funcional. Logo, essas instruções não funcionarão:

        // myVal = () -> "three"; ERRO! String não é compatível com Double

        // Erro, o parâmetro é necessário.
        // myPval = () -> Math.random(); 
    }
}
