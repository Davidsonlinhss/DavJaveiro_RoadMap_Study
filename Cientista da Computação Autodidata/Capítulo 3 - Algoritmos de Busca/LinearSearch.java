public class LinearSearch {
    public static boolean linearSearch(int[] array, int n) {
        for (int i : array) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] array = {1, 8, 32, 91, 5, 15, 9, 100, 3};
        int target = 91;
        System.out.println(linearSearch(array, target));
    }
}
