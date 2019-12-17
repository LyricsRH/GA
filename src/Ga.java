
public class Ga {

    private static final double mutationRate = 0.15;
    private static final int tournamentSize = 15;
    private static final boolean elitism = true;

    public static  Tour select(Population population){
        Population population1=new Population(tournamentSize,false,null);

        for (int i = 0; i <tournamentSize ; i++) {
            int n=(int)(Math.random()*population.tours.length);
            population1.setTour(i,population.getTour(n));
        }

        return population1.getBestTour();
    }

    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.tours.length, false,null);

        // Keep our best individual if elitism is enabled
        int elitismOffset = 0;
        if (elitism) {
            newPopulation.setTour(0, pop.getBestTour());
            elitismOffset = 1;
        }

        // Crossover population
        // Loop over the new population's size and create individuals from
        // Current population
        for (int i = elitismOffset; i < newPopulation.tours.length; i++) {
            // Select parents
            Tour parent1 = select(pop);
            Tour parent2 = select(pop);
            // Crossover parents
            Tour child = crossover(parent1, parent2);
            // Add child to new population
            newPopulation.setTour(i, child);
        }

        // Mutate the new population a bit to add some new genetic material
        for (int i = elitismOffset; i < newPopulation.tours.length; i++) {
            mutate(newPopulation.getTour(i));
        }

        return newPopulation;
    }

    public static Tour crossover(Tour parent1,Tour parent2){

        Tour childTour=new Tour();
        int index1=(int)(Math.random()*parent1.getSize());
        int index2=(int)(Math.random()*parent1.getSize());

        int start,end;
        if(index1<index2){
            start=index1;
            end=index2;
        }else if(index1>index2){
            start=index2;
            end=index1;
        }else{
            start=0;
            end=2;
        }

        for (int i = start; i <=end ; i++) {
               childTour.setCity(i,parent1.getCity(i));

        }

        int index=0;
        for (int i = 0; i <parent2.getSize() ; i++) {
            if (!childTour.containCity(parent2.getCity(i))){
                if (index!=start){
                    childTour.setCity(index,parent2.getCity(i));
                }else{
                    index=end+1;
                    if (index<parent2.getSize()){
                        childTour.setCity(index,parent2.getCity(i));
                    }
                }
                index++;
            }
        }

        return childTour;
    }

    public static void mutate(Tour tour){
        for (int i = 0; i <tour.getSize() ; i++) {
            if (Math.random()<mutationRate){
                int mIndex=(int)(Math.random()*tour.getSize());
                City temp=tour.getCity(i);
                tour.setCity(i,tour.getCity(mIndex));
                tour.setCity(mIndex,temp);
            }
        }
    }






}
