
/**
 * Write a description of class menu here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Scanner;
import javax.swing.JFrame;
import java.util.Timer;
import java.awt.Dimension;
import java.io.*;
public class menu
{
    private int userChoice;
    private Scanner user = new Scanner(System.in);
    Timer myTimer = new Timer();
    
    public menu() throws FileNotFoundException,IOException{
        System.out.println("Welcome to the Memory Matching Game!");
        System.out.println("Please choose a difficulty below:");
        System.out.println("1. Easy - 4 x 4 , 1.5 Second Interval");
        System.out.println("2. Medium - 6 x 6 , 1 Second Interval");
        System.out.println("3. Hard - 8 x 8 , 0.5 Second Interval");
        while(userChoice != 1 && userChoice != 2 && userChoice != 3){
        userChoice = user.nextInt();
        if(userChoice == 1){
            Board b = new Board(4,4,8,1500);
            setUpGame(b);
        }
        else if(userChoice == 2){
            Board b = new Board(6,6,18,1000);
            setUpGame(b);
        }
        else if(userChoice == 3){
            Board b = new Board(8,8,32,500);
            setUpGame(b);
        }
        else{
            System.out.println("Invalid selection. Please try again");
            userChoice = user.nextInt();
        }
    }
    }
    public void setUpGame(Board b){
        b.setPreferredSize(new Dimension(500,500));
        b.setLocation(400, 200);
        b.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        b.pack();
        b.setVisible(true);
    }
}
