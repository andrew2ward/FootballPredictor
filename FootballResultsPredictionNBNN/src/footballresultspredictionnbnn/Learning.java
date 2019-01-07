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
public class Learning {
    private static double home = 1.0;
    private static double draw = 0.5;
    private static double away = 0.0;
    
    private static double input[][] = {
        //home,away,outcome
        {1.0,0.1,1.0},
        {0.8,0.4,1.0},
        {0.5,0.5,0.5},
        {0.3,0.7,0.0},
        {0.5,0.8,0.0}        
    };
    
    //number of inputs in the above array
    private static int inputPatterns = 5;
    private static double LEASTMEANSQUAREERROR = 0.001;
    private static double TEACHINGSTEP = 0.01;
    
    public static double normalise(double x)
    {
        return 0.00392*x;
    }
    
    public static double fabs(double n)
    {
        //if +ve, return value, else return +ve value
        if(n >= 0)
            return n; 
        else 
            return 0 - n; 
    }
    
    public static void neuralNetwork(double homeTeam, double awayTeam)
    {
        double output;
        double result;
        //create a neural net with 2 inputs
        NeuralNet nn = new NeuralNet(2);
        
        double mse = 999;
        int epochs = 0;
        
        //train NN
        while(fabs(mse-LEASTMEANSQUAREERROR)>0.002)
        {
            mse = 0;
            double error = 0;
            
            //run through all input patterns(epochs)
            for(int j=0; j<inputPatterns; j++)
            {
               //give the two strength values to the network
                for(int k=0;k<2;k++)
                {
                    nn.inputAt(k, normalise(input[j][k]));
                }
                //input bias at last position
                nn.inputAt(2,1);
                
                output = nn.calculateNet();
                
                System.out.println("The mean square error of " + epochs + 
                        " epoch is " +mse);
                epochs++;
            }
                
        }
            
        //test NN
       result = nn.recall(normalise(homeTeam),normalise(awayTeam));

       System.out.println(result);
    }
}