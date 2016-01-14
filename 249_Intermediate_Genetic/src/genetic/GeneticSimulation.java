package genetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class GeneticSimulation {
    
    private String target;
    private int targetLength;

    private List<Individual> population;
    private int popSize;
    private double mutationRate;
    private boolean sexually;

    private int generationCount;
    private Individual bestIndividual;
    private int bestFitness;
    
    private double time;
    private final StringBuilder history = new StringBuilder();
    
    public GeneticSimulation(String target, int popSize,
            double mutationRate, boolean sexually) {
        this.target = target;
        this.targetLength = target.length();
        this.popSize = popSize;
        this.mutationRate = mutationRate;
        this.sexually = sexually;
    }
    
    public GeneticSimulation(int popSize, double mutationRate, boolean sexually) {
        this("Hello, world!", popSize, mutationRate, sexually);
    }
    
    public void runSimulation() {
        
        long startTime = System.currentTimeMillis();

        initialize(popSize);
        killUnfitNoRandom();

        while (bestFitness > 0 && generationCount < 10000) {

            reproduce();
            killUnfitNoRandom();
            history.append("Gen: ").append(generationCount).append(" | ");
            history.append(bestIndividual).append("\n");
        }
        
        long endTime = System.currentTimeMillis();
        
        generationCount++;
        time = (double) (endTime - startTime) / 1000;
    }

    public void initialize(int populationSize) {
        population = new ArrayList<>();
        generationCount = 0;

        for (int i = 0; i < populationSize; i++) {
            String newName = randomString(targetLength);
            int newFitness = hammingDist(target, newName);
            population.add(new Individual(newName, newFitness));
        }
    }

    public void killUnfitNoRandom() {

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
    
    public void reproduce() {
        if (sexually) {
            reproduceSexually();
        } else {
            reproduceAsexually();
        }
    }

    public void reproduceAsexually() {
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

    public void reproduceSexually() {
        ArrayList<Individual> newbornList = new ArrayList<>();
        Collections.shuffle(population);

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

    public String randomString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char currChar = (char) (32 + (int) (Math.random() * 93));
            sb.append(currChar);
        }
        return sb.toString();
    }

    public int hammingDist(String s1, String s2) {
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
    
    public String getHistory() {
        return history.toString();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Target: ").append(target);
        sb.append("\nPopulation size: ").append(popSize);
        sb.append("\nMutation rate: ").append(mutationRate);
        sb.append("\nSexual reproduction: ").append(sexually);
        sb.append("\nGenerations: ").append(generationCount);
        sb.append("\nElapsed time: ").append(time);
        sb.append("\n*****************");
        
        return sb.toString();
    }

    //TEST
    public void printPop() {
        for (Individual i : population) {
            System.out.println(i);
        }
        System.out.println("******************");
    }

}
