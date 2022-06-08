
/**
 * Write a description of class card here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import javax.swing.JButton;


public class Card extends JButton{
    private String id;
    private boolean matched = false;


    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    public void setMatched(boolean matched){
        this.matched = matched;
    }

    public boolean getMatched(){
        return this.matched;
    }
}