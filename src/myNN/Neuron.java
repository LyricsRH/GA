package myNN;

import java.util.ArrayList;
import java.util.Random;

public class Neuron {

    //神经元只有 输入权重矩阵，输出权重矩阵
    private ArrayList<Double> listOfWeightIn;
    private ArrayList<Double> listOfWeightOut;

    //初始化weightOut 和weightIn 3,1的神经元 只有一个值
    public double initNeuron(){
        Random r=new Random();
        return  r.nextDouble();
    }

    public ArrayList<Double> getListOfWeightIn() {
        return listOfWeightIn;
    }

    public void setListOfWeightIn(ArrayList<Double> listOfWeightIn) {
        this.listOfWeightIn = listOfWeightIn;
    }

    public ArrayList<Double> getListOfWeightOut() {
        return listOfWeightOut;
    }

    public void setListOfWeightOut(ArrayList<Double> listOfWeightOut) {
        this.listOfWeightOut = listOfWeightOut;
    }



}
