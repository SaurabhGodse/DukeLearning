import java.util.*;
import edu.duke.*;
/**
 * Write a description of WordFrequencies here.
 * 
 * @author Saurabh Godse
 * @version (a version number or a date)
 */
public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    private void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for(String word : fr.words()){
            int idx = myWords.indexOf(word.toLowerCase());
            if(idx == -1){
                myWords.add(word.toLowerCase());
                myFreqs.add(1);
            }
            else{
                myFreqs.set(idx, myFreqs.get(idx) + 1);
            }
            
        }
        for(int i = 0; i < myWords.size(); i++){
            System.out.println(myWords.get(i) + " : " + myFreqs.get(i));
        }
        System.out.println("Number of unique words : " + myFreqs.size());
    }
    
    private int indexOfMax(){
        int maxindex = 0;
        int maxval = 0;
        for(int i = 0; i < myFreqs.size(); i++){
            int temp = myFreqs.get(i);
            if(maxval < temp){
                maxval = temp;
                maxindex = i;
            }
        }
        System.out.println("Max index is : " + maxindex);
        System.out.println("Max word is : " + myWords.get(maxindex) + " with freq : " + maxval);
        return maxindex;
    }
    public void tester(){
        findUnique();
        indexOfMax();
    }
}
