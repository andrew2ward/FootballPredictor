/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package footballresultspredictionnbnn;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;


/**
 *
 * @author Andy
 */
public class FootballResultsPredictionNBNN extends Frame{

   
     private static String[][] teamData = new String[][]
     {    
        //Liverpool
        //team, gs, ga, yc, rc, result  
        {/*"Watford",*/         "3","0","1","1","win"},
        {/*"Fulham",*/          "2","0","1","0","win"},
        {/*"Arsenal",*/         "1","1","1","1","draw"},
        {/*"Cardiff",*/         "4","1","0","0","win"},
        {/*"Huddersfield",*/    "1","0","2","0","win"},
        {/*"Manchester City",*/ "0","0","1","0","draw"}
     };
     
     private static String[][] testData1 = new String[][]
     {
         {"1","0","3","0"},
         {"1","1","2","0"},
         {"0","2","2","0"}
     };
     private String[] data ={"Arsenal"};
     
     private static String[][] teamData2 = new String[][]
     {
        //Newcastle
        //team, gs, ga, yc, rc, result  
        {/*"West Ham",*/        "0","3","3","0","loss"},
        {/*"Burnley",*/         "2","1","1","0","win"},
        {/*"Bournemouth",*/     "0","1","2","0","loss"},
        {/*"Watford",*/         "0","1","1","0","loss"},
        {/*"Southampton",*/     "0","0","1","0","draw"},
        {/*"Brighton",*/        "0","1","0","0","loss"}
    };
     
     private static String[][] testData2 = new String[][]
     {
         {"3","0","3","0"},
         {"1","1","2","0"},
         {"0","2","1","0"}         
     };
     
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        PredictorUI ui = new PredictorUI();
        
        
        /*
        NaiveBayesClassifier team1 = new NaiveBayesClassifier();
        //when call calculate propability, send team data
        NaiveBayesClassifier team2 = new NaiveBayesClassifier();
        
        
        
        System.out.println("Liverpool:");
        //double probTeam1 = team1.calc(teamData, testData1);
        System.out.println("Newcastle:");
        //double probTeam2 = team2.calc(teamData2, testData2);
        
        Learning neuralNet = new Learning();
       
        //Learning.neuralNetwork(probTeam1,probTeam2);
        //use these two values to input into Neural Network
        */
        //jsonGetRequest();
        
        
        
        

    }
    
    public static void jsonGetRequest() throws MalformedURLException, IOException 
    {
        URL oracle = new URL("http://unn-w15002106.newnumyspace.co.uk/footballResults.php");
        URLConnection yc = oracle.openConnection();
        //String header = yc.getRequestProperty("header");
        //System.out.println(header);
        //yc.setRequestProperty(header, "33daf3203c6b4b848852f5935f4226e8");
        BufferedReader in = new BufferedReader(new InputStreamReader(
        yc.getInputStream()));
        String inputLine;
        ArrayList <String> ar = new ArrayList<String>();
        while ((inputLine = in.readLine())!= null)
        {
            //System.out.println(inputLine);
            String team;
            String[] teams;
            if(inputLine.contains("name"))
            {
                teams = inputLine.split(":");
                team = teams[teams.length-1];
                System.out.println(team);
            }
                String score;   
                String[] scores;
            if(inputLine.contains("score"))
            {
                scores = inputLine.split(":");
                score = scores[scores.length-1];
                //ar.add(inputLine);
                System.out.println(score);
            }
        }
        
        in.close();
        
        
        
        /*
        //Creating a request
        URL url = new URL("https://api-football-v1.p.mashape.com/seasons");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        
        //Adding Request Parameters
        Map<String, String> parameters = new HashMap<>();
        parameters.put("param1", "val");

        con.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
        out.flush();
        out.close();
        
        //Set request headers
        con.setRequestProperty("Content-Type", "application/json");
        String contentType = con.getHeaderField("Content-Type");
        System.out.println(contentType);
        
        
        
        int status = con.getResponseCode();
        BufferedReader in = new BufferedReader(
        new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while((inputLine = in.readLine())!=null)
        {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();
        System.out.println(content);
*/
        
    }

}

