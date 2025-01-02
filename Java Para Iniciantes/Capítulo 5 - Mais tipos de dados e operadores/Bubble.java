public class Bubble{
    public static void main(String args[]) {
        int numes[] = {99, -10, 1000123, 18, -978, 5623, 463, -9, 287, 49};

        int a, b, t;
        int size;

        size = numes.length; // números de elementos a serem classificados

        // exibe o array original
        System.out.println("Original array is: ");
        for(int i = 0; i < size; i++)
            System.out.println(" " + numes[i]);

        // fazendo o Bubble-sort
        for(a=1; a < size; a++) // percorrendo os elementos

            // b = 4, quanto b [começa no último elemento] >= a [começa no primeiro índice 0(primeira varredura); diminua b--
            for(b = size-1; b >= a; b--) {

                // se o último elemento for maior, trocamos 
                if(numes[b-1] > numes[b]) { // se fora de ordem, troca

                    t = numes[b-1]; // t recebe o anterior
                    numes[b-1] = numes[b]; // o anterior recebe o posterior
                    numes[b] = t; // o posterior recebe o que era o anterior
                }
            }
        
            System.out.println("Sorted array is: ");
            for(int i = 0; i <size; i++)
                System.out.println(" " + numes[i]);
            System.out.println();
    }
}