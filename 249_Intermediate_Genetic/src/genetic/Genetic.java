package genetic;

public class Genetic {

    public static void main(String[] args) {

        GeneticSimulation gs
                = new GeneticSimulation("Hello, world!", 100, 0.1, true);
        gs.runSimulation();
        System.out.println(gs);
        System.out.println(gs.getHistory());

    }
}
