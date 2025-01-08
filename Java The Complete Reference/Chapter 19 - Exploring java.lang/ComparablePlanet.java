
class Planet implements Comparable<Planet> {
    private String name;
    private double diameter;

    public Planet(String name, double diameter) {
        this.name = name;
        this.diameter = diameter;
    }

    public String getName() {
        return name;
    }

    public double getDiameter() {
        return diameter;
    }

    // implementando o método compareTo
    @Override
    public int compareTo(Planet other) {
        // ordenação pelo diâmetro
        return Double.compare(this.diameter, other.diameter); // passando Double.compare pois os valores são Double
    }

    @Override
    public String toString() {
        return name + " (" + diameter + " km)";
    }

}

public class ComparablePlanet {
    public static void main(String[] args) {
        Planet earth = new Planet("Earth", 12742);
        Planet mars = new Planet("Mars", 6779);

        int result = earth.compareTo(mars);

        // em Java podemos omitir as chaves em uma estrutura if ou else
        // se houver apenas uma instrução associada ao bloco
        if(result > 0)
            System.out.println(earth + " é maior do que " + mars);
        else if(result < 0)
            System.out.println(earth + " é menor que " + mars);
        else
            System.out.println(earth + " tem o mesmo diâmetro que " + mars);
    }
}
