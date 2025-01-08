class Summation {
    private int sum;

    <T extends Number> Summation(T arg) {
        sum = 0;

        for(int i=0; i <= arg.intValue(); i++)
            sum += i;
    }

    int getSum() {
        return sum;
    }
}
public class GenConsDemo {
    public static void main(String[] args) {
        Summation ob = new Summation(10);

        System.out.println("Summation of 10 is " + ob.getSum());
    }
}
