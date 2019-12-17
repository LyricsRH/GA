package myNN;



import java.util.ArrayList;
import java.util.Arrays;

public class HiddenLayer extends  Layer {

    //hiddenlayer 有weightIn 和weightOut
    //hidenLayer 可能有多层，用List<HiddenLayer>

    public ArrayList<HiddenLayer> initLayer(HiddenLayer hiddenLayer, ArrayList<HiddenLayer> listOfHiddenLayer, InputLayer inputLayer, OutputLayer outputLayer){
        //Arraylist<hiddenlayer>  多层隐藏层   input he outputLayer 确定第一层和最后一层weighIn 和weighOut

        ArrayList<Double> listOfWeightIn=new ArrayList<Double>();
        ArrayList<Double> listOfWeightOut=new ArrayList<Double>();
        ArrayList<Neuron> listOfNeurons=new ArrayList<Neuron>();

        //hidenLayer 一共几层
        int numOfHiddenLayers=listOfHiddenLayer.size();

        //每层
        for (int i = 0; i <numOfHiddenLayers; i++) {

            //求每层的输入和输出个由几个

            //weighIn个数
            int limitIn;
            //weighOut个数
            int limitOut;

            if(i==0){ //第一层，个数由inputLayer个数决定
                limitIn=inputLayer.getNumberOfNeuronsInLayer();
                if(numOfHiddenLayers>1){//第一层隐藏层的下层还是hiddenLayer
                    limitOut=hiddenLayer.getNumberOfNeuronsInLayer();
                }else {//只有一层hiddenLayer,下一层为outputLayer
                    limitOut=outputLayer.getNumberOfNeuronsInLayer();
                }
            }else if(i==numOfHiddenLayers-1){//最后一层 weighOut个数由OutPutLayer决定
                limitIn=listOfHiddenLayer.get(i-1).getNumberOfNeuronsInLayer();//等于前一个hidden层神经元个数
                limitOut=outputLayer.getNumberOfNeuronsInLayer();
            }else {//中间层
                limitIn=listOfHiddenLayer.get(i-1).getNumberOfNeuronsInLayer();//前一层个数
                limitOut=listOfHiddenLayer.get(i+1).getNumberOfNeuronsInLayer();//后一层个数
            }


            //每个神经元
            for (int j = 0; j <hiddenLayer.getNumberOfNeuronsInLayer() ; j++) {
                Neuron neuron=new Neuron();
                //已经知道这层输入和输出各有几个  limitIn ,limitOut
                //为每个神经元初始化 ，weighIn和weighOut
                for (int k = 0; k <limitIn ; k++) {
                    listOfWeightIn.add(neuron.initNeuron());
                }
                for (int k = 0; k <limitOut ; k++) {
                    listOfWeightOut.add(neuron.initNeuron());
                }

                neuron.setListOfWeightIn(listOfWeightIn);
                neuron.setListOfWeightOut(listOfWeightOut);
                //临时一层
                listOfNeurons.add(neuron);

                listOfWeightIn=new ArrayList<Double>();
                listOfWeightOut=new ArrayList<Double>();
            }

            //listOfHiddenLayer是<HiddenLayer>
            listOfHiddenLayer.get(i).setListOfNeurons(listOfNeurons);

            listOfNeurons=new ArrayList<Neuron>();

        }
        return  listOfHiddenLayer;
    }


    //打印每层每个神经元的输入和输出权值
    public void printLayer(ArrayList<HiddenLayer> listOfHiddenLayer) {
        if (listOfHiddenLayer.size() > 0) {
            System.out.println("### HIDDEN LAYER ###");
            int h = 1;
            for (HiddenLayer hiddenLayer : listOfHiddenLayer) {
                System.out.println("Hidden Layer #" + h);
                int n = 1;
                for (Neuron neuron : hiddenLayer.getListOfNeurons()) {
                    System.out.println("Neuron #" + n);
                    System.out.println("Input Weights:");
                    System.out.println(Arrays.deepToString(neuron
                            .getListOfWeightIn().toArray()));
                    System.out.println("Output Weights:");
                    System.out.println(Arrays.deepToString(neuron
                            .getListOfWeightOut().toArray()));
                    n++;
                }
                h++;
            }
        }
    }
}
