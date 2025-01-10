interface StringTest {
    boolean test(String aStr, String bStr);
}

public class LambdaDemo3 {
    
    public static void main(String args[]) {
        // Esta expressão lambda determina se um string faz
        // parte do outro
        
        StringTest isIn = (a, b) -> a.indexOf(b) != -1;

        String str = "This is a test";

        System.out.println("Testing string: " + str);

        // estamos verificando se b está contido em a
        System.out.println(isIn.test(str, "is a") 
            ? "'is a' found." 
                : "'is a' not found");

        System.out.println(isIn.test(str, "xfy") 
            ? "'xfy' is a found" 
                : "'xfy' is a not found");
    }   

}
