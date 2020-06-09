import java.util.*;
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author Saurabh Godse
 * @version (a version number or a date)
 */
public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{
    public String lastWord(String str){
        return str.substring(str.lastIndexOf(" ") + 1);
    }
    
    public int compare(QuakeEntry q1, QuakeEntry q2){
        String lw1 = lastWord(q1.getInfo());
        String lw2 = lastWord(q2.getInfo());
        if(lw1.equals(lw2)){
            return Double.compare(q1.getMagnitude(), q2.getMagnitude());
        }
        return lw1.compareTo(lw2);
        
    }
    

}
