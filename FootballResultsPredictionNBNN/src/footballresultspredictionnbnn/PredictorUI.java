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
                
                homeTeam.setText("");
                awayTeam.setText("");
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

    
}
