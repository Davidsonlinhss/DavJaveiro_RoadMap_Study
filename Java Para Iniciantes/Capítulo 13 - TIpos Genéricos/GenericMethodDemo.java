public class GenericMethodDemo {
    // Determina se o conteúdo de dois arrays é igual

    static <T extends Comparable<T>, V extends T> boolean 
        arraysEqual(T[] x, V[] y) {
        // Se o tamanho dos arrays for diferente, os arrays também serão.
        if(x.length != y.length) return false;

        for(int i=0; i < x.length; i++)
            if(!x[i].equals(y[i])) return false; // se os arrays são diferentes
        
        return true; // os conteúdos dos arrays são equivalentes
    }

    public static void main(String args[]) {
        Integer nums[] = {1, 2, 3, 4, 5};
        Integer nums2[] = {1, 2, 3, 4, 5};
        Integer nums3[] = {1, 2, 7, 4, 5};
        Integer nums4[] = {1, 2, 7, 4, 5, 6};

        
        System.out.println(arraysEqual(nums, nums) 
            ? "nums equals nums" 
                : "Não são.");
        

        System.out.println(arraysEqual(nums, nums2) 
            ? "nums equals nums2"
                : "nums don't equals nums2");
        
        System.out.println(arraysEqual(nums, nums3) 
            ? "nums equals nums3" 
                : "nums don't equals nums3");
        

        System.out.println(arraysEqual(nums, nums4) 
            ? "nums equals nums4" 
                : "nums don't equals nums4");

        // array de double
        Double dvals[] = { 1.1, 2.2, 3.3, 4.4, 5.5};

        // parte que não será compilada, pois nums e dvals 
        // não são do mesmo tipo
        // if(arraysEqual(nums, dvals))
        //     System.out.println("nums equals dvals?");
    }
}
