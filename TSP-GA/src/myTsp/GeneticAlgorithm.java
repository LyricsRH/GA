package myTsp;

import java.util.Arrays;

public class GeneticAlgorithm {
    //种群大小
    private int populationSize;
    //染色体长度
   // private int chromsomeLen;
    //遗传概率
    private double crossoverRate;
    //变异概率
    private double mutationRate;
    //精英规模
    private int elitismCount;
    //锦标赛值
    private int tournamentSize;


    //构造函数，传值
    public GeneticAlgorithm(int populationSize,double crossoverRate,double mutationRate,int elitismCount,int tournamentSize){
        this.populationSize=populationSize;
       // this.chromsomeLen=chromsomeLen;
        this.crossoverRate=crossoverRate;
        this.mutationRate=mutationRate;
        this.elitismCount=elitismCount;
        this.tournamentSize=tournamentSize;
    }

    //初始化种群
    //返回初始化过的种群
    public Population initPopulation(int len){
        Population population=new Population(populationSize,len);
        return  population;
    }

    //判断是否结束
    //1，到达进化代数，2，求得最优解
    //1
    public boolean isTerminatedConditionMet(int generation,int maxGeneration){
        return  generation>maxGeneration;
    }

    //计算适应度值
    //因为individual只有城市编号信息，因此需要city[]包含位置信息的cities才可以计算适应度值
    public double calFitness(Individual individual,City[] cities){
        Route route=new Route(individual,cities);
        double fitness=1/route.getDistance();
        //存储适应值，，individual和cities在这区别，都是城市集合，，和route区别？？？？？
        //individual只有city编号，cities city包含城市位置信息
        individual.setFitness(fitness);
        return  fitness;
    }

    //更新整个种群的适应值
    //找到最优秀的fitness并保存
    public void evalPopulation(Population population,City[] cities){
        //种群适应值和，归一化用
        double populationFittes=0;
        for (int i = 0; i <population.size() ; i++) {
            //取得种群中每一个染色体，计算他的适应值，计算完并更新适应值（在calFitness中更新）
            populationFittes+=this.calFitness(population.getIndividual(i),cities);
        }
        //计算平均适应度值
        double  avgFitness=populationFittes/population.size();
        population.setPopulationFitness(avgFitness);
    }

    //选择与当前染色体交叉的另一条染色体
    //传入一个Population 这个类里面都是方法类，没有population
    public Individual selectParaent(Population population){

        //锦标赛选择亲代
        //tournamentSize,从种群中选择几个进入锦标赛
        //1，随机从种群中选择一些个体，进入锦标赛
        //2，比较锦标赛里适应值最大的个体作为亲代


        //被选中的个体集合也是一个种群
        Population tournament=new Population(tournamentSize);

        //打乱种群，从而达到从种群随机选择个体进入锦标赛的目的
        population.shuffle();
        for (int i = 0; i <tournamentSize ; i++) {
            //population已经被打乱，因此直接选择前锦标赛个数个体进入锦标赛即可
            tournament.setIndividual(i,population.getIndividual(i));
        }

        //getFittnest对锦标赛种群排序，并返回第一个染色体是最大的
        return tournament.getFittest(0);
    }

    //交叉操作
    //1，遍历种群每个染色体，达到交叉概率转2,没有交叉概率直接new=这个染色体转3
    //2，选择parent，交叉操作，返回new等于新产生的染色体
    //3，用new替代原来染色体

    //直接换一个种群，将交叉完成的染色体放入这个新种群中，并返回
    //传入旧种群，交叉操作完成后返回新的种群
    public Population crossoverPopulation(Population population){
        Population new_Population=new Population(population.size());

        for (int i = 0; i <population.size() ; i++) {
            //判断是否需要交叉的染色体
            //一定要用getFittest()获得第i个染色体，而不是getIndividual,
            //因为要确保最优秀的在最前面被访问，用来保证精英策略
            Individual parent1=population.getFittest(i);

            //Individual newIndividual=parent1;

            //通过交叉概率和精英策略决定parent1是否需要进行交叉
            //只要随机数小于交叉概率，并且超过精英策略个数，需要交叉，获得parent2
            if(crossoverRate>Math.random()&&i>elitismCount){
                Individual parent2=selectParaent(population);

                //交叉的到的后代,直接用染色体数组表示
                int [] offspringChromsome=new int[parent1.getChromsomeLen()];
                //初始化都为-1
                Arrays.fill(offspringChromsome,-1);
                //传入数组方式构建后代对象
                Individual offSpring=new Individual(offspringChromsome);

                //开始交叉操作，
                //1，获得交叉部位,因为不知道谁大谁小，目前不是开始和结束
                int subPoint1=(int)(Math.random()*parent1.getChromsomeLen());
                int subPoint2=(int)(Math.random()*parent1.getChromsomeLen());

                //判断大小决定start和end
                int startPoint=Math.min(subPoint1,subPoint2);
                int endPoint=Math.max(subPoint1,subPoint2);

                //顺序交叉方法，
                //1，将parent1,(start ,end)放入子代对应位置
                //2，从end位置便利parent2每个基因，如果子代包含该基因，跳过。不包含子代加入该基因，如果走到尽头，跳到开头

                //将parent1，s-e 放入子代
                for (int j = startPoint; j <endPoint ; j++) {
                    offSpring.setGen(j,parent1.getGen(j));
                }

                //遍历parent2，将offspring没有的基因按顺序加入offspring
                //j是相对与endPoint的偏移量
                //移动整个parent2长度，肯定能补满offspring
                for (int j = 0; j <parent2.getChromsomeLen() ; j++) {
                    int parent2GenIndex=endPoint+j;
                    if(parent2GenIndex>=parent2.getChromsomeLen()){
                        //index =len => len-len=>0 回到开头
                        parent2GenIndex-=parent2.getChromsomeLen();
                    }

                    //如果子代不包含此基因，加入offspring,找到加入的位置
                    //从头遍历，==-1即代表是空的
                    if(!offSpring.containsGen(parent2.getGen(parent2GenIndex))){

                        for (int k = 0; k <parent1.getChromsomeLen() ; k++) {
                            if(offSpring.getGen(k)==-1){
                                offSpring.setGen(k,parent2.getGen(parent2GenIndex));
                                //跳出parent1循环，只添加第一个
                                break;
                            }
                        }
                    }
                    //如果包含此基因，什么也不做，跳到parent2下一个基因
                }

                new_Population.setIndividual(i,offSpring);

            }else{
                //else 不进行交叉
                new_Population.setIndividual(i,parent1);
            }

        }
        return new_Population;
    }

    //交换变异，遍历每个基因，通过变异概率决定是否变异
    //交叉是对每个染色体进行判断是否交叉，变异是对每个基因判断
    //如果变异，从该染色体随机选择一个基因，交换位置
    public Population mutatePopulation(Population population){

        Population new_Population=new Population(population.size());

        //遍历每隔染色体
        for (int i = 0; i <population.size() ; i++) {
            //获得排序后的第i个染色体，确保精英策略
            Individual individual=population.getFittest(i);

            if(i>elitismCount){
                //不是精英，进行遍历
                for (int j = 0; j <individual.getChromsomeLen() ; j++) {
                    //j基因满足变异
                    if(Math.random()<mutationRate){
                        //取得随机基因
                        int newGenPos= (int) (Math.random()*individual.getChromsomeLen());

                        int tempGen=individual.getGen(j);
                        individual.setGen(j,individual.getGen(newGenPos));
                        individual.setGen(newGenPos,tempGen);
                    }
                }
            }
            new_Population.setIndividual(i,individual);
        }
        return  new_Population;
    }
}
