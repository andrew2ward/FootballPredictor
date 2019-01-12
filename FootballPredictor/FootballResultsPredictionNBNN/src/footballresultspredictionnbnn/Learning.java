/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package footballresultspredictionnbnn;
/**
 *
 * @author Andy
 */
public class Learning {
    private static double home = 1.0;
    private static double draw = 0.5;
    private static double away = 0.0;
    private double result;
    
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
    private static double TEACHINGSTEP = 0.05;
    private static int MAX_TESTS = 1;
    
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
    
    public Learning(double homeTeam, double awayTeam)
    {
        double output;
        
        //create a neural net with 2 inputs
        NeuralNet nn = new NeuralNet(2);
        
        double mse = 999;
        int epochs = 0;
        double weights[] = nn.getWeights();
        //train NN
        while(fabs(mse-LEASTMEANSQUAREERROR)>0.002)
        {
            mse = 0;
            double error = 0;
            
            for(int i=0; i< MAX_TESTS; i++)
            {
                output = nn.calcWeights(i,input);
                error += fabs(input[i][2]-output);
                
                weights[0] += TEACHINGSTEP*(input[i][2]-output)*
                        input[i][0];
                weights[1] += TEACHINGSTEP*(input[i][2]-output)*
                        input[i][1];
                weights[2] += TEACHINGSTEP*(input[i][2]-output);
            }
            mse = error/(double)MAX_TESTS;
            epochs++;
                
            
//            //run through all input patterns(epochs)
//            for(int j=0; j<inputPatterns; j++)
//            {
//               //give the two strength values to the network
//                for(int k=0;k<2;k++)
//                {
//                    nn.inputAt(k, normalise(input[j][k]));
//                }
//                //input bias at last position
//                nn.inputAt(2,1);
//                
//                output = nn.calculateNet();
//                
////                System.out.println("The mean square error of " + epochs + 
////                        " epoch is " +mse);
//                epochs++;
           
                
        }
        
        for(int i = 0; i<MAX_TESTS; i++)
        {
            result = nn.recall(normalise(homeTeam),normalise(awayTeam),i,input);
        
            
            //test NN

           if(result<0.3)
           {
               System.out.println("Win for away team");
           }
           else if(result>0.7)
           {
               System.out.println("Win for home team");
           }
           else
           {
               System.out.println("Draw");
           }
        }
       System.out.println(result);
    }
    
   
}