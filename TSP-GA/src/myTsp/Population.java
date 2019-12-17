package myTsp;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Population {
    //种群，有很多route,也就是染色体，是Individual[]类型
    private Individual[] population;
    //种群中最优秀的个体。。。。还是。。是种群适应度值和，用来做归一化处理
    //是种群平均适应度值
    private double populationFitness=-1;

    //构造函数：通过种群大小构建，初始化Individual[]
    public Population(int populationSize){
        this.population=new Individual[populationSize];
    }

    //构造函数：同时知道基因长度，构造种群
    public Population(int populationSize,int chromsomeLen){
        this.population=new Individual[populationSize];
        //构造每条基因
        for (int i = 0; i <populationSize ; i++) {
            //新建基因
            Individual individual=new Individual(chromsomeLen);
            population[i]=individual;
        }
    }

    //返回种群中染色体适应度从大到小排序过的，第index位的染色体，返回过程中，对populaion完成排序
    //返回第n大的染色体
    public Individual getFittest(int index){
        //排序种群population,
        Arrays.sort(this.population, new Comparator<Individual>() {
            @Override
            public int compare(Individual o1, Individual o2) {
                if(o1.getFitness()>o2.getFitness()){
                    return  -2;
                }else if(o1.getFitness()<o2.getFitness()){
                    return  1;
                }
                return  0;
            }
        });
        return  population[index];
    }

    //设置种群最优秀的适应度值
    public void setPopulationFitness(double fitness){this.populationFitness=fitness;}

    //获得种群最优秀的适应值
    public double getPopulationFitness(){return  this.populationFitness;}

    //返回种群的大小，多少个染色体
    public int size(){return population.length ;}

    //设置种群第i个位置的染色体
    //遗传变异时候，更新种群使用
    //返回更新后的染色体? 返回有什么用
    public Individual setIndividual(int index,Individual individual){
        return population[index]=individual;
    }

    //返回种群index染色体
    public Individual getIndividual(int index){return  population[index];}

    //打乱种群中染色体的位置？？？？
    //不应该打乱city位置？，
    public void shuffle(){
        Random random=new Random();
        for (int i = population.length-1; i >0 ; i--) {
            //返回 与第i个 个体 染色体交换的染色体，[i--n]都是交换过的，与之前交换
            //[0,i] 等于i不交换
            int n=random.nextInt(i+1);
            Individual tempIndividual=population[i];
            population[i]=population[n];
            population[n]=tempIndividual;
        }
    }

}
