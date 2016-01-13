package genetic;

public class Individual implements Comparable<Individual> {
    
    private String name;
    private int fitness;
    
    public Individual(String name, int fitness) {
        this.name = name;
        this.fitness = fitness;
    }
    
    public Individual(String name) {
        this(name, -1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }
    
    public Individual reproduceAsexually() {
        return new Individual(name);
    }
    
    public Individual[] reproduceSexually(Individual partner) {
        String partnerName = partner.getName();
        int crossoverPoint = (int) (name.length() * Math.random());
        
        String firstFirsthalf = name.substring(0, crossoverPoint);
        String firstSecondhalf = name.substring(crossoverPoint);
        
        String secondFirsthalf = partnerName.substring(0, crossoverPoint);
        String secondSecondhalf = partnerName.substring(crossoverPoint);
        
        Individual firstSon = new Individual(firstFirsthalf + secondSecondhalf);
        Individual secondSon = new Individual(secondFirsthalf + firstSecondhalf);
                
        return new Individual[]{firstSon, secondSon};
    }
    
    public void mutate(double mutationRate) {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < name.length(); i++) {
            if (Math.random() < mutationRate) {
                sb.append((char) (32 + (int) (Math.random() * 93)));
            } else {
                sb.append(name.charAt(i));
            }
        }
        
        name = sb.toString();
    }
    
    @Override
    public int compareTo(Individual other) {
        if (fitness == other.fitness) {
            return name.compareTo(other.name);
        }
        return Integer.signum(fitness - other.fitness);
    }

    @Override
    public String toString() {
        return (name + " | fitness: " + fitness);
    }

}
