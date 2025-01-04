public class Search {
    public static void main(String args[]) {
        int nums[] = {6, 8, 3, 7, 5, 6, 1, 4};
        int target = 5;
        boolean found = false;


        // usando o laço
        for(int x : nums) {
            if(x == target) {
                found = true;
                System.out.println("Value is " + x);
                break;
            }
        }

        if(found)
            System.out.println("Value found!");
    }

}
