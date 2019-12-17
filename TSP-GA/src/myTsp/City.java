package myTsp;

public class City {

    private int x;
    private  int y;
    public City(int x,int y){
        this.x=x;
        this.y=y;
    }

    public double distanceFrom(City city){
        return Math.sqrt(Math.pow(city.getX()-this.x,2)+Math.pow(city.getY()-this.y,2));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
