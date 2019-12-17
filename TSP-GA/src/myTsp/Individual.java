package myTsp;

public class Individual {

    //每个染色体的基因数组
    public int[] chromsome;
    //这个染色体的适应度
    public double fitness=-1;

    //构造函数：通过基因数组直接构建染色体
    public Individual(int[] chromsome){
        this.chromsome=chromsome;
    }

    //构造函数：通过基因长度构造染色体
    public Individual(int len){
        //不需要打乱，变异和遗传会打乱顺序
        int[] chromsome_new=new int[len];
        //给基因赋初值
        for(int i=0;i<len;i++){
            chromsome_new[i]=i;
        }
        this.chromsome=chromsome_new;
    }

    //获得这个染色体的基因数组
    public int[] getChromsome(){
        return  chromsome;
    }

    //返回这个染色体的基因长度
    public int getChromsomeLen(){
        return  this.chromsome.length;
    }

    //设置第index位置的基因值
    public void setGen(int index,int value){
        this.chromsome[index]=value;
    }

    //获得index位置的基因值
    public int getGen(int index){
        return  this.chromsome[index];
    }

    //获得这条染色体的适应度值
    public double getFitness(){
        return this.fitness;
    }

    //设置染色体的适应度值
    public void setFitness(double fitness){ this.fitness=fitness;}

    //重写toString方法,打印染色体每个基因
    public String toString(){
        String input="";
        for (int i = 0; i <chromsome.length ; i++) {
            input+=chromsome[i]+",";
        }
        return  input;
    }

    //返回染色体是否包含某个基因
    public boolean containsGen(int gen){

        for (int i = 0; i <chromsome.length ; i++) {
            if(chromsome[i]==gen) return  true;
        }
        return  false;
    }


}
