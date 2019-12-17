public class City {

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    private  int x;

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    private int y;
    public City(int x,int y){
        this.x=x;
        this.y=y;
    }

    @Override
    public String toString() {
        return x+","+y;
    }


}
