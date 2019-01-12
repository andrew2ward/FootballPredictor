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
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 *
 * @author Andy
 */
public class PredictorUI{
    //private vars
    private final String[] data ={"Arsenal","Bournemouth","Brighton","Burnley",
        "Chelsea","Crystal Palace","Everton","Huddersfield","Leicester",
        "Liverpool","Manchester City","Manchester United","Newcastle",
        "Southampton","Watford","West Ham"};
    private int homePred;
    private int awayPred;
    private String homeTeamName;
    private String awayTeamName;
    
    private String[][] teamData = new String[38][5];
    
     
     private String[][] testData1 = new String[3][4];
     
     private String[][] teamData2 = new String[38][5];
     
     
     private String[][] testData2 = new String[3][4];
     
    
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
        
        JPanel p1 = new JPanel();
        
        JLabel l1 = new JLabel("Home Team");
        JLabel l2 = new JLabel("Away Team");
        
        GridLayout g = new GridLayout(0,2);
        JComboBox teamHomeCombo = new JComboBox(this.data);
        teamHomeCombo.setSize(50, 20);
        teamHomeCombo.setSelectedIndex(0); 
        p1.add(l1);
        p1.add(teamHomeCombo, BorderLayout.NORTH);
                
        JComboBox teamAwayCombo = new JComboBox(this.data);
        teamAwayCombo.setSize(50, 20);
        teamAwayCombo.setSelectedIndex(0); 
        p1.add(l2);
        p1.add(teamAwayCombo, BorderLayout.SOUTH);
        
        f.add(p1);
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
                homeTeamName = (String) teamHomeCombo.getSelectedItem();
                awayTeamName = (String) teamAwayCombo.getSelectedItem();
                homePred = Integer.parseInt(homeTeam.getText());
                awayPred = Integer.parseInt(awayTeam.getText());
                String[] scoreHome = {homeTeam.getText(),awayTeam.getText(), "0","0"};
                testData1[0] = scoreHome;
                String[] scoreAway = {awayTeam.getText(), homeTeam.getText(), "0","0"};
                testData2[0] = scoreAway;
                homeTeam.setText("");
                awayTeam.setText("");
                try {
                    loadTeams(homeTeamName,'h');
                    loadTeams(awayTeamName,'a');
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(PredictorUI.class.getName()).log(Level.SEVERE, null, ex);
                }
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
        NaiveBayesClassifier team2 = new NaiveBayesClassifier();
        
        //when call calculate propability, send team data
        //System.out.println("Liverpool:");
        double probTeam1 = team1.calc(teamData, testData1);
        //System.out.println("Newcastle:");
        double probTeam2 = team2.calc(teamData2, testData2);
        
        Learning neuralNet = new Learning(probTeam1, probTeam2);
        
    }
    
    /**
     * Reads relevant files and populates arrays
     * @param teamName
     * @param homeAway
     * @throws FileNotFoundException 
     */
    public void loadTeams(String teamName, char homeAway) throws FileNotFoundException
    {
        String filePath = "src\\footballresultspredictionnbnn\\Results\\"+teamName+".txt";
        //System.out.println(file.getAbsolutePath());
        File file = new File("");
        File f = new File(file+filePath);
        Scanner team = new Scanner(f);
        
        String token = "";
        //String[] homeRes = new String[5];
        int j = 0;
        System.out.println(teamName);
        while(team.hasNext())
        {
            token = team.nextLine();
            System.out.println(token);
            String[] result = token.split("\t");
            if(homeAway == 'h')
            {
                teamData[j] = result;
            }
            else if(homeAway == 'a')
            {
                teamData2[j] = result;            
            }
            
            j++;  
        }
        team.close();
        
            
    }

    
}
