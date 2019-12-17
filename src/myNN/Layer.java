package myNN;

import java.util.ArrayList;

public  abstract  class Layer {

    //输入 隐藏 输出层
    //每层都由神经元组成
    public ArrayList<Neuron> listOfNeurons;
    //这层神经元数量
    protected int numberOfNeuronsInLayer ;

    public void printLayer(){
    }

    public ArrayList<Neuron> getListOfNeurons() {
        return listOfNeurons;
    }

    public void setListOfNeurons(ArrayList<Neuron> listOfNeurons) {
        this.listOfNeurons = listOfNeurons;
    }

    public int getNumberOfNeuronsInLayer() {
        return numberOfNeuronsInLayer;
    }

    //NEW
    public void setNumberOfNeuronsInLayer(int numberOfNeuronsInLayer) {
        this.numberOfNeuronsInLayer = numberOfNeuronsInLayer;
    }

}
