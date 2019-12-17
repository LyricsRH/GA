package myNN;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class OutputLayer extends  Layer{

    public OutputLayer initLayer(OutputLayer outputLayer){
        ArrayList<Double> listOfWeighOut=new ArrayList<Double>();
        ArrayList<Neuron> listOfNeuron=new ArrayList<Neuron>();

        for (int i = 0; i <outputLayer.getNumberOfNeuronsInLayer() ; i++) {
            Neuron neuron=new Neuron();

            listOfWeighOut.add(neuron.initNeuron());

            neuron.setListOfWeightOut(listOfWeighOut);

            listOfNeuron.add(neuron);

            //清空i神经元的weighOut
            listOfWeighOut=new ArrayList<Double>();
        }

        outputLayer.setListOfNeurons(listOfNeurons);
        return  outputLayer;
    }

    public void printLayer(OutputLayer outputLayer){
        System.out.println("### OUTPUT LAYER ###");
        int n = 1;
        for (Neuron neuron : outputLayer.getListOfNeurons()) {
            System.out.println("Neuron #" + n + ":");
            System.out.println("Output Weights:");
            System.out.println(Arrays.deepToString( neuron.getListOfWeightOut().toArray() ));
            n++;
        }
    }

}
