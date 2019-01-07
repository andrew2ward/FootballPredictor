/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package footballresultspredictionnbnn;
import java.util.*;

/**
 *
 * @author Andy
 */
public class NeuralNet {
    //decalre number of inputs
    private int numInputs = 2;
    //create input layer with 1+numInputs to make room for bias
    private double[] inputLayer = new double[numInputs+1];
    //create array of weights, amount=numInputs plus 1 for bias
    public double[] weights = new double[numInputs+1];
    private int activationFunction;
        
    /**
     * 
     * @param inputNumber
     * 
     */
    public NeuralNet(int inputNumber)
    {
        //create random number
        Random rand = new Random();
        //loop to populate weights with values from -0.5 to 0.5
        for(int i = 0; i<inputNumber+1; i++)
        {
            weights[i] = (( (double)rand.nextInt(10)/((double)(10)+(double)(1)) ))-0.5;
        }
       //activationFunction = function;
    }
    
    public void inputAt(int inputPos, double d)
    {
        inputLayer[inputPos] = d;   
    }
    /**
     * 
     * @return 
     */
    public double calculateNet()
    {
        double action = 0.0;
        //get action potential for this input pattern
        for(int i=0; i< this.numInputs +1; i++)
        {
            action += inputLayer[i] * weights[i];
        }
        
        //sigmoid function
        action = 1.0/(1.0 + Math.exp(-action));
        
        return action;        
    }
    
    public void adjustWeights(double TEACHINGSTEP, double output, double target)
    {
        //For all weights, adjust the weight vector to train network
        for(int i=0; i<this.numInputs + 1; i++)
        {
            weights[i] = weights[i] + TEACHINGSTEP*(target-output)*inputLayer[i];
        }
    }
    
    public double recall(double d, double e)
    {
        //num inputs
        inputLayer[0] = d;
        inputLayer[1] = e;
        inputLayer[2] = 1.0;
        
        return calculateNet();
    }
}
