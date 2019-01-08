/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package footballresultspredictionnbnn;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
/**
 *
 * @author Andy
 */
public class PredictorUI{
    //private vars
    private String[] data ={"Arsenal"};
    private int homePred;
    private int awayPred;
    
    
    private static int homePrediction;
    private static int awayPrediction;
    private static final String[][] teamData = new String[][]
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
     
     private String[][] testData1 = new String[3][4];
     
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
     
     private static String[][] testData2 = new String[3][4];
     
    
    //constructor
    public PredictorUI()
    {
        buildUI();
        
    }
    
    //methods
    
    public void buildUI()
    {
        JFrame f = new JFrame("");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(250,250);
        f.setLocation(300,200);
        
        GridLayout g = new GridLayout(0,2);
        //f.setLayout(g);
        JList teamList = new JList(this.data);
        teamList.setSize(50, 20);
        teamList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        teamList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        teamList.setVisibleRowCount(-1);
        f.add(teamList, BorderLayout.NORTH); 
        
        Container teams = new Container();
        teams.setLayout(g);
        JTextField homeTeam = new JTextField(10);
        JLabel lbl1 = new JLabel("Home Team");
        JTextField awayTeam = new JTextField(10);
        JLabel lbl2 = new JLabel("Away Team");
        teams.add(lbl1);
        teams.add(homeTeam);
        teams.add(lbl2);
        teams.add(awayTeam);
        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                homePred = Integer.parseInt(homeTeam.getText());
                awayPred = Integer.parseInt(awayTeam.getText());
                String[] scoreHome = {homeTeam.getText(),awayTeam.getText(), "0","0"};
                testData1[0] = scoreHome;
                String[] scoreAway = {awayTeam.getText(), homeTeam.getText(), "0","0"};
                testData2[0] = scoreAway;
                homeTeam.setText("");
                awayTeam.setText("");
                
                predict();
            }
        });
        
        teams.add(submit);
        f.add(teams, BorderLayout.SOUTH);
        
        f.setVisible(true);
        
    }
    
    public int getHomePred()
    {
        return this.homePred;
    }
    
    public int getAwayPred()
    {
        return this.awayPred;
    }
    
    public void predict()
    {
        NaiveBayesClassifier team1 = new NaiveBayesClassifier();
        //when call calculate propability, send team data
        NaiveBayesClassifier team2 = new NaiveBayesClassifier();
        
        
        
        System.out.println("Liverpool:");
        double probTeam1 = team1.calc(teamData, testData1);
        System.out.println("Newcastle:");
        double probTeam2 = team2.calc(teamData2, testData2);
        
        Learning neuralNet = new Learning(probTeam1, probTeam2);
        
    }

    
}
