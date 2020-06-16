import java.util.*;

/**
 * Write a description of MarkovWord here.
 * 
 * @author Saurabh Godse 
 * @version (a version number or a date)
 */
public class MarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord(int order){
        myRandom = new Random();
        myOrder = order;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
//        String key = myText[index];
        WordGram key = new WordGram(myText, index, myOrder);
        sb.append(key.toString());
        sb.append(" ");
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(key);
            //System.out.println(key + " : " + follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }
        
    return sb.toString().trim();
    }
    
    private int indexOf(String [] words, WordGram target, int start){
        for(int i = start; i <= words.length - target.length(); i++){
            WordGram curr = new WordGram(words, i, target.length());
            if(target.equals(curr))
                return i;
        }
        return -1;
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while(pos < myText.length){
            int start = indexOf(myText, kGram, pos);
            if(start == -1)
                break;
            pos = start + kGram.length();
            if(pos < myText.length){
                String next = myText[pos];
                follows.add(next);
            }

        }
        return follows;
    }
    
    /*public void testIndexOf(){
        String s = "this is just a test yes this is a simple test";
        String [] words = s.split(" ");
        System.out.println(indexOf(words, "this", 3));
        System.out.println(indexOf(words, "frog", 3));
    }*/


}
