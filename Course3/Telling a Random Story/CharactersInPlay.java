import java.util.*;
import edu.duke.*;

/**
 * Write a description of CharactersInPlay here.
 * 
 * @author Saurabh Godse
 * @version (a version number or a date)
 */
public class CharactersInPlay {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public CharactersInPlay(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
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

    private void update(String character){
        int idx = myWords.indexOf(character);
        if(idx == -1){
            myWords.add(character);
            myFreqs.add(1);
        }
        else{
            myFreqs.set(idx, myFreqs.get(idx) + 1);
        }
    }
    
    private void findAllCharacters(){
        FileResource fr = new FileResource();
        myWords.clear();
        myFreqs.clear();
        for(String line : fr.lines()){
            int idx = line.indexOf(".");
            if(idx != -1){
                String character = line.substring(0, idx);
                update(character);
            }
            
        }
    }
    
    private void charactersWithNumParts(int num1, int num2){
        for(int i = 0; i < myFreqs.size(); i++){
            int temp = myFreqs.get(i);
            if(num1 <= temp && temp <= num2){
                System.out.println(myWords.get(i));
            }
        }
    }
    
    public void tester(){
        findAllCharacters();
        indexOfMax();
        charactersWithNumParts(10, 15);
    }
}
