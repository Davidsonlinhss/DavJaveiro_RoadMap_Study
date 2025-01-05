public class NumberOneToTen {
    public static void main(String[] args) {
        int number = 10;
        System.out.println("Contagem do número informado:");
        contagem(0, number); // Inicia a contagem a partir de 0 até 10
    }

    public static void contagem(int current, int max) {
        if (current > max) {
            return; // Caso base: para a recursão quando o número atual excede o máximo
        }
        System.out.println(current); // Imprime o número atual
        contagem(current + 1, max); // Chama a si mesma com o próximo número
    }
}
