
/**
 * Write a description of class board here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.*;
import javax.swing.JLabel;


public class Board extends JFrame{


    private List<Card> cards;
    private Card selectedCard;
    private Card c1;
    private Card c2;
    private Timer t;
    private int pairs = 18;
    private int second, minute;
    JFrame window;
    JLabel counterLabel;
    
    Font font1 = new Font("Times New Roman", Font.PLAIN, 70);
    private Timer timer;
    public void setPairs(int x){
        pairs = x;
    }
    public int getPairs(){
        return pairs;
    }
    public Board(int x, int y, int p, int time) throws FileNotFoundException,IOException{

        List<Card> cardsList = new ArrayList<Card>();
        List<String> cardVals = new ArrayList<String>();
        List<String> myWordArray = new ArrayList<String>();
        
        File file = new File("myWordFile.txt");
        Scanner infile = new Scanner(file);
        for(int i = 0;i < 50; i += 1){ // initializing myWordArray
            myWordArray.add(null);
        }
        
        for(int i = 0; infile.hasNextLine() && i < 50; i += 1){ // setting words from myWordFile to myWordArray
            myWordArray.set(i, infile.nextLine());
        }
        for(int i = 0;i < p; i ++){ // initializing cardVals to null 
            cardVals.add(null);
            cardVals.add(null);
        }
        int j = 0;
        for (int i = 0; i < p; i+=1){
            cardVals.set(j, myWordArray.get(i));
            cardVals.set(j+1, myWordArray.get(i));
            j+=2;
        }
        Collections.shuffle(cardVals);
        
        for (String val : cardVals){
            Card c = new Card();
            c.setId(val);
            c.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae){
                    selectedCard = c;
                    doTurn();
                }
            });
            cardsList.add(c);
        }
        this.cards = cardsList;
        //set up the timer for the interval between user selection
        t = new Timer(time, new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                checkCards();
            }
        });

        t.setRepeats(false);
        //timer for extra credit
        window = new JFrame();
        window.setSize(400,400);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        
        counterLabel = new JLabel("");
        counterLabel.setBounds(200,230,200,100);
        counterLabel.setHorizontalAlignment(JLabel.CENTER);
        counterLabel.setFont(font1);
        
        window.add(counterLabel);
        window.setVisible(true);
        
        second = 0;
        simpleTimer();
        timer.start();
        
        Container container = getContentPane(); //building the board
        container.setLayout(new GridLayout(x, y));
        for (Card c : cards){
            container.add(c);
        }
        setTitle("Memory Match");
    }

    public void doTurn(){
        if (c1 == null && c2 == null){
            c1 = selectedCard;
            c1.setText(String.valueOf(c1.getId()));
        }

        if (c1 != null && c1 != selectedCard && c2 == null){
            c2 = selectedCard;
            c2.setText(String.valueOf(c2.getId()));
            t.start();

        }
    }
    public void checkCards(){
        if (c1.getId() == c2.getId()){//match condition
            c1.setEnabled(false); //disables the button
            c2.setEnabled(false);
            c1.setMatched(true); //flags the button as having been matched
            c2.setMatched(true);
            if (this.isGameWon()){
                JOptionPane.showMessageDialog(this, "You win!");
                System.exit(0);
                timer.stop();
            }
        }
        else{
            c1.setText(""); //'hides' text
            c2.setText("");
        }
        c1 = null; //reset c1 and c2
        c2 = null;
    }
    public void simpleTimer(){
        timer = new Timer(1000, new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e){
                second++;
                counterLabel.setText(minute + ":" + second);
                if(second == 60){
                    second = 0;
                    minute++;
                    counterLabel.setText(minute + ":" + second);
                }
            }
        });
    }
    public boolean isGameWon(){
        for(Card c: this.cards){
            if (c.getMatched() == false){
                return false;
            }
        }
        return true;
    }
}
