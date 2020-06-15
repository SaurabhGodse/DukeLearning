import java.util.*;
/**
 * Write a description of MarkovWordTwo here.
 * 
 * @author Saurabh Godse 
 * @version (a version number or a date)
 */
public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwo() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-2);  // random word to start with
        String key1 = myText[index];
        String key2 = myText[index + 1];
        sb.append(key1);
        sb.append(" ");
        sb.append(key2);
        sb.append(" ");
        for(int k=0; k < numWords-2; k++){
            ArrayList<String> follows = getFollows(key1, key2);
            //System.out.println(key + " : " + follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key1 = key2;
            key2 = next;
        }
        
    return sb.toString().trim();
    }
    
    private int indexOf(String [] words, String target1, String target2, int start){
        String target = target1 + " " + target2;
        for(int i = start; i < words.length - 1; i++)
            if(target.equals(words[i] + " " + words[i + 1]))
                return i;
        return -1;
    }
    
    private ArrayList<String> getFollows(String key1, String key2) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while(pos < myText.length){
            int start = indexOf(myText, key1, key2, pos);
            if(start == -1)
                break;
            pos = start + 2;
            if(pos < myText.length){
                String next = myText[pos];
                follows.add(next);
            }

        }
        return follows;
    }
    
    public void testIndexOf(){
        String s = "this is just a test yes this is a simple test";
        String [] words = s.split(" ");
        System.out.println(indexOf(words, "this", "is", 0));
        System.out.println(indexOf(words, "frog", "of", 1));
    }


}
