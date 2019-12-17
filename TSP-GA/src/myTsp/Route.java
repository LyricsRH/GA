package myTsp;

public class Route {

    //private City route[]=new City[10];是一个City数组，表示路线
    public City route[];
    //表示这个路线的长度，用来计算适应度值
    private double distance=0;


    //传入一条染色体 和 一个城市列表
    public Route(Individual individual,City[]cities){
        //这个individual是初始的?没有打乱顺序还是？
        int[] chromsome=individual.getChromsome();
       ////////////////
       ////////this.route=cities为什么出错？？？？？？？？？？？//////////////
        this.route=new City[cities.length];
        for (int i = 0; i <chromsome.length ; i++) {
            ////////////？？？？？？？？？？？？？？？？////////
            //chromosome[i]是第i个位置的基因（城市），cities[城市]=？第i个城市，包含位置信息
            //individual里面基因只是编号，没有位置信息
            this.route[i]=cities[chromsome[i]];
        }
    }

    //计算route的长度,简单计算距离
    public double getDistance(){
       // double distance=0.0;
        //>0说明已经计算过并且赋值
        if(distance>0){
            return  distance;
        }

        double totalDistance=0.0;

        for (int i = 0; i <route.length-1 ; i++) {
            //route是city的集合，
            totalDistance+=route[i].distanceFrom(route[i+1]);
        }

        //加上最后一个city返回起点的距离
        totalDistance+=route[route.length-1].distanceFrom(route[0]);
        //计算过的距离赋值给route的distance,第二次调用distance>0直接返回，不重复计算
        this.distance=totalDistance;
        return  totalDistance;
    }


}
