import java.util.List;

public class Population {
    Tour[] tours;
    public Population(int size,boolean init,List<City> initTour){
        tours=new Tour[size];
        Tour tour=new Tour(initTour);
        if (init){
            for (int i = 0; i <size ; i++) {
                tour.generatorIndiv();
                tours[i]=tour;
            }
        }
    }

    public void setTour(int i,Tour tour){
        tours[i]=tour;
    }

    public Tour getTour(int i){
        return tours[i];
    }

    public Tour getBestTour(){
        double best= tours[0].getFitness();
        int bestInd=0;
        for (int i = 1; i <tours.length ; i++) {
            if (tours[i].getFitness()>best) {
                best=tours[i].getFitness();
                bestInd=i;
            }
        }

        return tours[bestInd];
    }





}
