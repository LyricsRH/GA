package myNN;

import java.util.ArrayList;
import java.util.Arrays;

public class InputLayer extends  Layer {

    public InputLayer initLayer(InputLayer inputLayer){
        //初始化层  初始化每个神经元（给每个神经元设置weightIn weightOut）

        ArrayList<Double> listOfWeightInTemp= new ArrayList<Double>();
        ArrayList<Neuron> listOfNeurons=new ArrayList<Neuron>();

        //为该层每个神经元赋初值，输入层只有weightIn
        for (int i = 0; i <inputLayer.getNumberOfNeuronsInLayer() ; i++) {
            //输入层，每个神经元只有一个输入

            Neuron neuron=new Neuron();

            listOfWeightInTemp.add(neuron.initNeuron());
//            inputLayer.getListOfNeurons().get(0).setListOfWeightIn();
            neuron.setListOfWeightIn(listOfWeightInTemp);
            listOfNeurons.add(neuron);
            listOfWeightInTemp=new ArrayList<Double>();
        }
        inputLayer.setListOfNeurons(listOfNeurons);
        return  inputLayer;
    }


    public void printLayer(InputLayer inputLayer){
        System.out.println("### INPUT LAYER ###");
        int n = 1;
        for (Neuron neuron : inputLayer.getListOfNeurons()) {
            System.out.println("Neuron #" + n + ":");
            System.out.println("Input Weights:");
            System.out.println(Arrays.deepToString( neuron.getListOfWeightIn().toArray() ));
            n++;
        }
    }


    public void setNumberOfNeuronsInLayer(int numberOfNeuronsInLayer) {
        this.numberOfNeuronsInLayer = numberOfNeuronsInLayer + 1; //BIAS,基准层
    }





}
