package myTsp;

public class TSP {

    public static  int maxGeneration=10000;

    public static void main(String[] args) {
        int numCities=100;
        City[] cities=new City[numCities];

        for (int i = 0; i <numCities ; i++) {
            int x= (int) (Math.random()*100);
            int y=(int)(Math.random()*100);
            cities[i]=new City(x,y);
        }

        GeneticAlgorithm ga=new GeneticAlgorithm(100,0.9,0.0001,2,5);

        Population population=ga.initPopulation(cities.length);

        //评估种群，计算种群中所有fitness,
        ga.evalPopulation(population,cities);

        //初始路线
        Route startRoute=new Route(population.getFittest(0),cities);
        System.out.println("start distance"+startRoute.getDistance());

        int generation=1;

        while(ga.isTerminatedConditionMet(generation,maxGeneration)==false){

            Route route=new Route(population.getFittest(0),cities);
            System.out.println("Generation"+generation+"best distance"+route.getDistance());

            population=ga.crossoverPopulation(population);


            population=ga.mutatePopulation(population);

            ga.evalPopulation(population,cities);

            generation++;
        }

        System.out.println("stoop after"+maxGeneration+"generation");
        Route route=new Route(population.getFittest(0),cities);
        System.out.println("best distance"+route.getDistance());

    }
}
