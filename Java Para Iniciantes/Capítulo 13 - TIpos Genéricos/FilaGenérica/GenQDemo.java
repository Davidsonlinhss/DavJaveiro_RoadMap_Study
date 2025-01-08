public class GenQDemo {
    public static void main(String[] args) {
        Integer iStore[] = new Integer[10];

        GenQueue<Integer> q = new GenQueue<Integer>(iStore);

        Integer iVal;

        System.out.println("Demonstrate a queue of Integers");
        try {
            for(int i=0; i < 10; i++) {
                System.out.println("Adding " + i + " to q.");
                q.put(i); // adiciona o valor inteiro à q Código
            }
        } catch (QueueFullException e) {
            System.out.println(e);
        }

        // obtendo os valores integer
        try {
            for(int i = iStore.length -1; i >= 0; i--) {
                System.out.println("Getting next INteger from q ");
                iVal = q.get();
                System.out.println(iVal);
            }
        } catch (QueueEmptyException e) {
            System.out.println(e);
        }
        System.out.println();

        // Cria uma fila DOuble
        Double dSDouble[] = new Double[10];
        GenQueue<Double> q2 = new GenQueue<Double>(dSDouble);

        Double dVAl;

        System.out.println("Demonstrate a queue of Doubles.");
        try {
            for(int i = 0; i < 10; i++){
                System.out.println("Adding " + (double) i/2 + " to q.");
                q2.put((double) i/2); // adiciona o valor double à q2
            }
        } catch (QueueFullException e) {
            System.out.println(e);
        }
        System.out.println();

        try {
            for(int i=0; i < 5; i++) {
                System.out.println("Getting next Double from q2: ");
                dVAl = q2.get();
                System.out.println(dVAl);
            }
        } catch (QueueEmptyException e) {
            System.out.println(e);
        }


    }

}
