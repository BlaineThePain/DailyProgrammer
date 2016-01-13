package genetic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Genetic {

    private static String target = "Hello, world!";
    private static int targetLength = target.length();

    private static List<Individual> population;
    private static int popSize = 100;
    private static double mutationRate = 0.1;
    private static boolean sexually = true;

    private static int generationCount;
    private static Individual bestIndividual;
    private static int bestFitness;

    public static void main(String[] args) {

        runSimulation();

        /*Individual i = new Individual("0000000000000");
         Individual j = new Individual("1111111111111");
        
         Individual s = i.reproduceAsexually();
         s.mutate(mutationRate);
        
         System.out.println(i);
         System.out.println(s);*/
    }

    public static void runSimulation() {
        
        System.out.println("Population size: " + popSize);
        System.out.println("Mutation rate: " + mutationRate);
        System.out.println("Sexual reproduction: " + sexually);
        System.out.println("*****************");

        initialize(popSize);
        killUnfitNoRandom();

        while (bestFitness > 0 && generationCount < 1000) {

            reproduce();
            System.out.println("Gen: " + generationCount + " | " + bestIndividual);
            killUnfitNoRandom();
        }
        generationCount++;
        System.out.println("Gen: " + generationCount + " | " + bestIndividual);
    }

    public static void initialize(int populationSize) {
        population = new ArrayList<>();
        generationCount = 0;

        for (int i = 0; i < populationSize; i++) {
            String newName = randomString(targetLength);
            int newFitness = hammingDist(target, newName);
            population.add(new Individual(newName, newFitness));
        }
    }

    public static void killUnfitNoRandom() {

        population.sort(null);
        bestIndividual = population.get(0);
        bestFitness = bestIndividual.getFitness();

        int killCount = population.size() / 2;
        Iterator<Individual> popIterator = population.iterator();
        for (int i = 0; i < killCount; i++) {
            popIterator.next();
        }

        while (popIterator.hasNext()) {
            popIterator.next();
            popIterator.remove();
        }
    }
    
    public static void reproduce() {
        if (sexually) {
            reproduceSexually();
        } else {
            reproduceAsexually();
        }
    }

    public static void reproduceAsexually() {
        ArrayList<Individual> newbornList = new ArrayList<>();

        for (Individual individual : population) {
            Individual newborn = individual.reproduceAsexually();

            newborn.mutate(mutationRate);
            newborn.setFitness(hammingDist(target, newborn.getName()));
            newbornList.add(newborn);
        }

        population.addAll(newbornList);
        generationCount++;
    }

    public static void reproduceSexually() {
        ArrayList<Individual> newbornList = new ArrayList<>();

        for (int i = 0; i < population.size(); i += 2) {
            Individual firstParent = population.get(i);
            Individual secondParent = population.get(i + 1);

            Individual[] newborns = firstParent.reproduceSexually(secondParent);

            for (Individual newborn : newborns) {
                newborn.mutate(mutationRate);
                newborn.setFitness(hammingDist(target, newborn.getName()));
                newbornList.add(newborn);
            }
        }

        population.addAll(newbornList);
        generationCount++;
    }

    public static String randomString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char currChar = (char) (32 + (int) (Math.random() * 93));
            sb.append(currChar);
        }
        return sb.toString();
    }

    public static int hammingDist(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return -1;
        }
        int dist = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                dist++;
            }
        }
        return dist;
    }

    //TEST
    public static void printPop() {
        for (Individual i : population) {
            System.out.println(i);
        }
        System.out.println("******************");
    }

}
