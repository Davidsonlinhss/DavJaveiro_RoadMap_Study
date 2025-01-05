public class Quadratic {
    public static void main(String[] args) {
        
        // a, b e c representam os coeeficientes da
        // equação quadrática: ax^2 + bx + c = 0
        double a, b, c, x;

        // resolve 4x^2 + x - 3 = 0 para achar x
        a = 4;
        b = 1;
        c = -3;

        // Encontra a primeira solução
        x = (-b + Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
        System.out.println("First solution: " + x);

        // Encontra a segunda solução
        x = (-b - Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
        System.out.println("Second solution: " + x);

    }

}
