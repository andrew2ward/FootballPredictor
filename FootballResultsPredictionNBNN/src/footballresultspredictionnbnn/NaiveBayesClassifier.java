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
public class NaiveBayesClassifier {
    
    //Declare new 2D array of Strings to store teamData.
    private String[][] teamData = new String[6][5];
    
    //variables
    private static double m;
    private static double p;
    private static int num_attr = 4; 
    private static int train_size = 6;
    private static int test_size = 3;
    private static int num_category = 3;
    
    /**
     * 
     */
    public NaiveBayesClassifier()
    {
        //m and p values        
        m=2.0;
        p=0.5;
        
    }
    
    /**
     * 
     * @param test
     * @param cat
     * @return 
     */
    public double calculateProb(String[] test, String cat)
    {
        //Declare count array with length num_attr
        int count[] = new int [num_attr];
        //initialises values in count array to 0
        for(int i=0; i<num_attr; i++)
        {
            count[i]=0;
        }
        
        //initialises values to 0.0 and 0
        double p_category = 0.0;
        int num_category = 0;
        
        //loop for the amount of training samples
        for(int j=0; j<train_size; j++)
        {
            //if the category is equal to the category in the final part of the 
            //array, increase the count for that category.
            if(cat.equals(teamData[j][num_attr]))
            {
                num_category++;
            }
        }
        
        p_category = (double)num_category/(double)train_size;
        
        //loop for the amount of attributes
        for (int i=0; i<num_attr; i++)
        {
            //loop for the amount of training samples
            for(int j=0; j<train_size; j++)
            {
                /*
                Possibly tweak this section to compare goals scored and goals 
                conceded as this is the key indicator as to whether the match 
                was a win, loss or draw. 
                Could also use the p(win/draw/loss) to create an average 
                strength value. 
                */                
                try
                {
                    int num = Integer.parseInt(teamData[j][i]);
                    if((num>=1)&&(cat.equals(teamData[j][num_attr])))
                    {
                        count[i]++;
                    }
                }
                catch (NumberFormatException e)
                {
                    if((test[i].equals(teamData[j][i]))&&
                            (cat.equals(teamData[j][num_attr])))
                    {
                        count[i]++;
                    }
                }
            }
            //calculate probability and concatinate to p_category
            p_category *= ((double)count[i]+m*p)/((double)num_category + m);
            System.out.println(test[i] + " : " + count[i] + " (probability = " +
                    ((double)count[i] + m * p)/((double)num_category + m)+")");
        }
        return p_category;
    }
    
    /**
     * 
     * @param teamData
     * @param testData
     * @return double max
     */
    public double calc(String[][] teamData, String testData[][])
    {
        //sets teamData 2D array
        this.teamData = teamData;
        
        //Iniitialises result array with the length for num of categories
        double result[] = new double[num_category];
        //initialises category array of Strings.
        String category[] = {"win", "draw", "loss"};
        
        
        double max = -1000.0;
        int max_position = -1;
        double average = 0.0;
        //loops for number of test samples (3)
        for(int k = 0; k<test_size; k++)
        {
            //loops for the number of categories (3)
            for(int i=0; i<num_category; i++)
            {
                //sets the result as the probability returned for the given 
                //category
                result[i] = calculateProb(testData[k], category[i]);
                //prints out probability of each category
                System.out.println(category[i] + ": " + result[i]);
                
                if(result[i]>max)
                {
                    max = result[i];
                    max_position = i;
                }
                average += result[i];
            }
            
            
            System.out.println("the " + k + " test data is classified as: " 
                    + category[max_position] + " with " + max);
            
        }
        average = average/num_category;
            System.out.println("Average: "+average);
        return max;
    }
}
