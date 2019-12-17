package myNN;



import myNN.learn.Adaline;
import myNN.learn.Perceptron;
import myNN.learn.Training;

import java.util.ArrayList;

public class NeuralNet {

    //神经网络结构
    private  InputLayer inputLayer;
    private  HiddenLayer hiddenLayer;
    private ArrayList<HiddenLayer> listOfHiddenLays;
    private  OutputLayer outputLayer;
    private int numberOfHiddenLayers;

    //参数
    //训练集
    private double[][] trainSet;
    //真是输出
    private  double[] realOutput;
    //
    private  int maxEpochs;
    private double learnRating;
    //目标误差和实际误差
    private double targetError;
    private double trainingError;

    //？存储均方误差的实数
    private ArrayList<Double> listOfMSE=new ArrayList<Double>();
    private Training.ActivationFncENUM activationFnc;

    public Training.TrainingTypesENUM getTrainType() {
        return trainType;
    }

    private Training.TrainingTypesENUM trainType;



    //每层个数
    public NeuralNet initNet(int numOfNeuronInInputLayer,int numOfNeuronInHiddenLayer,int numOfHiddenLayers,int numOfNeuronInOutputLayer){

        //new对象
        inputLayer=new InputLayer();
        inputLayer.setNumberOfNeuronsInLayer(numOfNeuronInInputLayer);

        listOfHiddenLays=new ArrayList<HiddenLayer>();
        for (int i = 0; i <numOfHiddenLayers ; i++) {
            hiddenLayer=new HiddenLayer();
            hiddenLayer.setNumberOfNeuronsInLayer(numOfNeuronInHiddenLayer);
            listOfHiddenLays.add(hiddenLayer);
        }

        outputLayer=new OutputLayer();
        outputLayer.setNumberOfNeuronsInLayer(numOfNeuronInOutputLayer);

        //init
        inputLayer.initLayer(inputLayer);


        if (numOfHiddenLayers>0) {
            listOfHiddenLays=hiddenLayer.initLayer(hiddenLayer,listOfHiddenLays,inputLayer,outputLayer);
        }

        outputLayer.initLayer(outputLayer);

        //NeuralNet newNet=new NeuralNet();
        NeuralNet newNet = new NeuralNet();
        newNet.setInputLayer(inputLayer);
        newNet.setHiddenLayer(hiddenLayer);
        newNet.setListOfHiddenLayer(listOfHiddenLays);
        newNet.setNumberOfHiddenLayers(numberOfHiddenLayers);
        newNet.setOutputLayer(outputLayer);

        return newNet;
    }

    public void printTrainedNetResult(NeuralNet n) {
        switch (n.trainType) {
            case PERCEPTRON:
                Perceptron t = new Perceptron();
                t.printTrainedNetResult( n );
                break;
            case ADALINE:
                Adaline a = new Adaline();
                a.printTrainedNetResult( n );
                break;
            default:
                throw new IllegalArgumentException(n.trainType+" does not exist in TrainingTypesENUM");
        }
    }

    public InputLayer getInputLayer() {
        return inputLayer;
    }

    public void setInputLayer(InputLayer inputLayer) {
        this.inputLayer = inputLayer;
    }

    public HiddenLayer getHiddenLayer() {
        return hiddenLayer;
    }

    public void setHiddenLayer(HiddenLayer hiddenLayer) {
        this.hiddenLayer = hiddenLayer;
    }

    public ArrayList<HiddenLayer> getListOfHiddenLayer() {
        return listOfHiddenLays;
    }

    public void setListOfHiddenLayer(ArrayList<HiddenLayer> listOfHiddenLayer) {
        this.listOfHiddenLays = listOfHiddenLayer;
    }

    public OutputLayer getOutputLayer() {
        return outputLayer;
    }

    public void setOutputLayer(OutputLayer outputLayer) {
        this.outputLayer = outputLayer;
    }

    public int getNumberOfHiddenLayers() {
        return numberOfHiddenLayers;
    }

    public void setNumberOfHiddenLayers(int numberOfHiddenLayers) {
        this.numberOfHiddenLayers = numberOfHiddenLayers;
    }

    //NEW
    public double[][] getTrainSet() {
        return trainSet;
    }

    public void setTrainSet(double[][] trainSet) {
        this.trainSet = trainSet;
    }

    public double[] getRealOutputSet() {
        return realOutput;
    }

    public void setRealOutputSet(double[] realOutputSet) {
        this.realOutput = realOutputSet;
    }

    public int getMaxEpochs() {
        return maxEpochs;
    }

    public void setMaxEpochs(int maxEpochs) {
        this.maxEpochs = maxEpochs;
    }

    public double getTargetError() {
        return targetError;
    }

    public void setTargetError(double targetError) {
        this.targetError = targetError;
    }

    public double getLearningRate() {
        return learnRating;
    }

    public void setLearningRate(double learningRate) {
        this.learnRating= learningRate;
    }

    public double getTrainingError() {
        return trainingError;
    }

    public void setTrainingError(double trainingError) {
        this.trainingError = trainingError;
    }


    public ArrayList<Double> getListOfMSE() {
        return listOfMSE;
    }

    public void setListOfMSE(ArrayList<Double> listOfMSE) {
        this.listOfMSE = listOfMSE;
    }

    public Training.ActivationFncENUM getActivationFnc() {
        return activationFnc;
    }

    public void setActivationFnc(Training.ActivationFncENUM activationFnc) {
        this.activationFnc = activationFnc;
    }

    public void setTrainType(Training.TrainingTypesENUM trainType) {
        this.trainType = trainType;
    }
}
