/*
 * * Name: Andrew Ward
Student ID: 15002106
 */
package footballresultspredictionnbnn;

import javax.swing.JOptionPane;

/**
 *
 * @author Andrew Ward
Student ID: 15002106
 */
public class Learning {
    //Attributes
    private static double home = 1.0;
    private static double draw = 0.5;
    private static double away = 0.0;
    private double result;
    
    //2d array
    private static double input[][] = {
        //home,away,socre, score, outcome
        {1.0,0.1,3.0,2.0,1.0},
        {0.8,0.4,1.0,0.0,1.0},
        {0.5,0.5,2.0,2.0,0.5},
        {0.3,0.4,1.0,1.9,0.5},
        {0.5,0.8,1.0,3.0,0.0}        
    };
    
    //number of inputs in the above array
    private static int inputPatterns = 5;
    private static double LEASTMEANSQUAREERROR = 0.001;
    private static double TEACHINGSTEP = 0.01;
    private static int MAX_TESTS = 5;
    
    /**
     * Normalises x
     * @param x
     * @return 
     */
    public static double normalise(double x)
    {
        return 0.00392*x;
    }
    
    /**
     * 
     * @param n
     * @return +ve value of n
     */
    public static double fabs(double n)
    {
        //if +ve, return value, else return +ve value
        if(n >= 0)
            return n; 
        else 
            return 0 - n; 
    }
    
    /**
     * 
     * @param homeTeam
     * @param awayTeam
     * @param homePred
     * @param awayPred 
     */
    public Learning(double homeTeam, double awayTeam, int homePred, 
            int awayPred)
    {
        //Local Variables
        double output;
        
        //create a neural net with 2 inputs
        NeuralNet nn = new NeuralNet(4);
        
        double mse = 99;
        int epochs = 0;
        double weights[] = nn.getWeights();
        //train NN
        while(fabs(mse-LEASTMEANSQUAREERROR)>0.81)
        {
            mse = 0;
            double error = 0;
            
            for(int i=0; i< MAX_TESTS; i++)
            {
                output = nn.calcWeights(i,input);
                error += fabs(input[i][4]-output);
                
                weights[0] += TEACHINGSTEP*(input[i][4]-output)*
                        input[i][0];
                weights[1] += TEACHINGSTEP*(input[i][4]-output)*
                        input[i][1]; 
                weights[2] += TEACHINGSTEP*(input[i][4]-output)*
                        input[i][2];
                weights[3] += TEACHINGSTEP*(input[i][4]-output)*
                        input[i][3];
                weights[4] += TEACHINGSTEP*(input[i][4]-output);
            }
            mse = error/(double)MAX_TESTS;
            epochs++;
                
            
            //run through all input patterns(epochs)
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
//            }
//                
////                System.out.println("The mean square error of " + epochs + 
////                        " epoch is " +mse);
//                epochs++;          
                
        }
        double average = 0;
        for(int i = 0; i<MAX_TESTS; i++)
        {
            result = nn.recall(normalise(homeTeam),normalise(awayTeam),i,input);
           
               average += result;
            
                      
            System.out.println("Test " +(i+1)+": "+ result);
        }     
        average = average/MAX_TESTS;
        //BAsed on result, output either home win/draw/away win
        System.out.println("Average: "+average);
           if(average<=0.3)
           {
               System.out.println("Win for away team");
               JOptionPane.showMessageDialog(null, "Win for Away Team");
           }
           else if(average>=0.5)
           {
               System.out.println("Win for home team");
               JOptionPane.showMessageDialog(null, "Win for Home team");
               
           }
           else
           {
               System.out.println("Draw");
               JOptionPane.showMessageDialog(null, "draw");
           }
    }   
   
}