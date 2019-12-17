import java.util.ArrayList;
import java.util.List;

public class Main {


        public static void main(String[] args) {

            // Create and add our cities
            City city = new City(60, 200);

            City city2 = new City(180, 200);

            City city3 = new City(80, 180);
            City city4 = new City(140, 180);

            City city5 = new City(20, 160);

            City city6 = new City(100, 160);

            City city7 = new City(200, 160);

            City city8 = new City(140, 140);

            City city9 = new City(40, 120);

            City city10 = new City(100, 120);

            City city11 = new City(180, 100);

            City city12 = new City(60, 80);

            City city13 = new City(120, 80);
            City city14 = new City(180, 60);
            City city15 = new City(20, 40);

            City city16 = new City(100, 40);

            City city17 = new City(200, 40);
            City city18 = new City(20, 20);

            City city19 = new City(60, 20);
            City city20 = new City(160, 20);
            List<City> list=new ArrayList<City>();
            list.add(city);list.add(city2);
            list.add(city3);
            list.add(city4);
            list.add(city5);
            list.add(city6);
            list.add(city7);
            list.add(city8);
            list.add(city9);
            list.add(city10);
            list.add(city11);
            list.add(city12);
            list.add(city13);
            list.add(city14);
            list.add(city15);
            list.add(city16);
            list.add(city17);
            list.add(city18);
            list.add(city19);
            list.add(city20);

            // Initialize population
            Population pop = new Population(100, true,list);
            System.out.println("Initial distance: " + pop.getBestTour().getDistance());

            // Evolve population for 100 generations
            pop = Ga.evolvePopulation(pop);
            for (int i = 0; i < 500; i++) {
                pop = Ga.evolvePopulation(pop);
                System.out.println("第"+i+"代"+pop.getBestTour().getDistance());
            }

            // Print final results
            System.out.println("Finished");
            System.out.println("Final distance: " + pop.getBestTour().getDistance());
            System.out.println("Solution:");
            System.out.println(pop.getBestTour());
        }
    }

