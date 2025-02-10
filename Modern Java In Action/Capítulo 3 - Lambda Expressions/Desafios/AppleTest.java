import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.Map;

public class AppleTest {
    public static void main(String[] args) {
        BiFunction<String, Integer, Apple> appleCreator = Apple::new;

        // Apple apple1 = appleCreator.apply("Green", 200);

        // System.out.println("Maçã de variação: " + apple1.getColor() + "\n Peso: " + apple1.getWeight());

        // List with cors and weights
        List<String> colors = Arrays.asList("Green", "Red", "Yellow", "Green");
        List<Integer> weights = Arrays.asList(200, 150, 180, 220);


    }

      static class Apple {
        
        private String color;

        private int weight; // in g

        Apple() {

        }

        public Apple(String color, int weight) {
            this.color = color;
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public int getWeight() {
            return weight;
        }


        public void setWeight(int weight) {
            this.weight = weight;
        }
        
    }
}
