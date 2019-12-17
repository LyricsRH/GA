import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tour {

    public List<City> tour=new ArrayList<>();
    public Tour(List<City> tour){
        this.tour=tour;
    }
    public Tour(){
        for (int i = 0; i <20 ; i++) {
            tour.add(null);
        }
    }

    public void setCity(int i,City city){
        tour.set(i,city);
    }

    public City getCity(int i){
        return tour.get(i);
    }

    public void generatorIndiv(){
        Collections.shuffle(tour);
    }
    public double distance(City c1,City c2){
        return Math.sqrt((c1.getX()-c2.getX())*(c1.getX()-c2.getX())+(c1.getY()-c2.getY())*(c1.getY()-c2.getY()));
    }
    
    public double getDistance(){
        double res=0.0;
        for (int i = 0; i <tour.size()-1 ; i++) {
            res+=distance(tour.get(i),tour.get(i+1));
        }
        res+=distance(tour.get(tour.size()-1),tour.get(0));
        return  res;
    }

    public int getSize(){
        return tour.size();
    }

    public double getFitness(){
        return  1/getDistance();
    }

    public boolean containCity(City city){
        return  tour.contains(city);
    }

    @Override
    public String toString() {
        String re="|";
        for (int i = 0; i <tour.size() ; i++) {
            re+=tour.get(i)+"|";
        }
        return re;
    }
}
