package myNN.learn;

import myNN.InputLayer;
import myNN.NeuralNet;
import myNN.Neuron;

import java.util.ArrayList;

public abstract class Training {

    private int epochs;
    private double error;
    //均方误差
    private double mse;

    //训练
    public NeuralNet train(NeuralNet n){
        //训练的对象 只有3-1 weighIn
        //输入数组，
        ArrayList<Double> inputWeighIn=new ArrayList<Double>();

        int rows=n.getTrainSet().length;
        int colmuns=n.getTrainSet()[0].length;

        while(this.getEpochs()<n.getMaxEpochs()){

            //计算输出和实际输出
            double estimatedOutput=0.0;
            double realOutput=0.0;

            for (int i = 0; i <rows ; i++) {

                double newValue=0.0;
                //计算第i组数据计算结果
                for (int j = 0; j <colmuns ; j++) {
                    inputWeighIn=n.getInputLayer().getListOfNeurons().get(j).getListOfWeightIn();
                    newValue+=inputWeighIn.get(0)*n.getTrainSet()[i][j];
                }
                //
                estimatedOutput=newValue;
                realOutput=n.getRealOutputSet()[i];

                //计算误差,后面getError 计算新权重
                this.setError(realOutput-estimatedOutput);

                //误差大于最小目标误差才更新权重
                if(Math.abs(this.getError())>n.getTargetError()){
                    InputLayer inputLayer=new InputLayer();
                    inputLayer.setListOfNeurons(this.teachNeuronsOfLayer(colmuns,i,n,newValue));
                    n.setInputLayer(inputLayer);
                }

            }

            //setMse均方误差
            this.setMse(Math.pow(realOutput-estimatedOutput,2));
            n.getListOfMSE().add(this.getMse());

            this.setEpochs(this.getEpochs()+1);
        }

        n.setTrainingError(this.getError());

        return n;
    }

    //训练一层的神经元并返回神经元集合
    public ArrayList<Neuron> teachNeuronsOfLayer(int numberOfInputNeurons,int line, NeuralNet n ,double netValue){

        ArrayList<Neuron> list=new ArrayList<Neuron>();
        ArrayList<Double> weightIn=new ArrayList<Double>();
        ArrayList<Double> weightOld=new ArrayList<Double>();
        for (int i = 0; i <numberOfInputNeurons ; i++) {
                Neuron neuron=new Neuron();
                weightOld=n.getInputLayer().getListOfNeurons().get(i).getListOfWeightIn();
                double weightInOld=weightOld.get(0);

                weightIn.add(this.calcNewWeight(n.getTrainType(),weightInOld,n,this.getError(),n.getTrainSet()[line][i],netValue));

                neuron.setListOfWeightIn(weightIn);
                weightIn=new ArrayList<Double>();
                list.add(neuron);
        }
        return list;
    }

    //计算权重, trainSample是这个神经元的输入值。。。netValue是加权之后输出值
    public double calcNewWeight(TrainingTypesENUM traintype,double inputWeightOld,NeuralNet n,double error,double trainSample,double netValue){

        switch (traintype){
            case PERCEPTRON:
                return inputWeightOld+n.getLearningRate()*error*trainSample;
            case ADALINE:
                return inputWeightOld+n.getLearningRate()*error*trainSample*derivativeActivationFnc(n.getActivationFnc(),netValue);
            default:
                throw  new IllegalArgumentException(traintype+"not exist");
        }
    }

    //（激活函数，值）return 计算值
    public double activeFnc(ActivationFncENUM actFn,double value){
        switch (actFn){
            case STEP:
                return fncStep(value);
            case LINEAR:
                return fncLiner(value);
            case SIGLOG:
                return fncSigLog(value);
            case HYPERTAN:
                return  fncHyperTan(value);
            default:
                throw new IllegalArgumentException(actFn+"does not exist this function");
        }
    }

    public double derivativeActivationFnc(ActivationFncENUM fnc,double value){
        switch (fnc){
            case HYPERTAN:
                return derivativeFncHyperTan(value);
            case LINEAR:
                return derivativeFncLinear(value);
            case SIGLOG:
                return  derivativeFncSigLog(value);
            default:
                throw new IllegalArgumentException(fnc+"does not exist derivativeFnc");
        }
    }

    //
    public void printTrainedNetResult(NeuralNet n){

        ArrayList<Double> inputWeightIn=new ArrayList<Double>();

        for (int i = 0; i <n.getTrainSet().length ; i++) {
            double netValue=0.0;
            for (int j = 0; j <n.getTrainSet()[0].length ; j++) {
                inputWeightIn=n.getInputLayer().listOfNeurons.get(j).getListOfWeightIn();
                netValue+=inputWeightIn.get(0)*n.getTrainSet()[i][j];
                System.out.println(n.getTrainSet()[i][j]+"\t");
            }
            double estiOut=this.activeFnc(n.getActivationFnc(),netValue);
            System.out.print("new Output="+estiOut+"\t");
            System.out.print("real Output="+n.getRealOutputSet()[i]);
            double error=estiOut-n.getRealOutputSet()[i];
            System.out.print("errror="+error);
        }

    }

    public int getEpochs() {
        return epochs;
    }

    public void setEpochs(int epochs) {
        this.epochs = epochs;
    }

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }

    public double getMse() {
        return mse;
    }

    public void setMse(double mse) {
        this.mse = mse;
    }


    //训练方法
    public enum TrainingTypesENUM{
        PERCEPTRON,ADALINE;
    }
    //激活函数
    public enum ActivationFncENUM{
        STEP,LINEAR,SIGLOG,HYPERTAN;
    }

    private double fncStep(double v){
        if (v>=0) {return 1.0;}else {
            return 0.0;
        }
    }

    private double fncLiner(double v){return  v;}
    private double fncSigLog(double v){return 1.0/(1.0+Math.exp(-v));}
    private double fncHyperTan(double v){return Math.tanh(v);}

    //激活函数倒数
    private double derivativeFncLinear(double v){
        return  1.0;
    }
    private  double derivativeFncSigLog(double v){
        return v*(1.0-v);
    }
    private double derivativeFncHyperTan(double v) {
        return (1.0 / Math.pow(Math.cosh(v), 2.0));
    }

    //输出结果

}
