import java.util.*;
import edu.duke.*;
/**
 * Write a description of Tester here.
 * 
 * @author Saurabh Godse
 * @version (a version number or a date)
 */
public class Tester {
    public void testGetFollows(){
        String st = "this is a test yes this is a test.";
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        System.out.println(markov.getFollows("."));
        
    }
    public void testGetFollowsWithFile(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        System.out.println(markov.getFollows("he").size());
        
    }

}
