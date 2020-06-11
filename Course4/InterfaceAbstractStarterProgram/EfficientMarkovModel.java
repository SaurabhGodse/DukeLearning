import java.util.*;
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author Saurabh Godse
 * @version (a version number or a date)
 */
public class EfficientMarkovModel extends AbstractMarkovModel{
    private int N;
    private HashMap<String, ArrayList<String>> follows;
    public EfficientMarkovModel(int n) {
        myRandom = new Random();
        N = n;
        follows = new HashMap<String, ArrayList<String>>();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    public void buildMap(){
        int left = 0;
        int right = N;
        while(right < myText.length()){
            String s = myText.substring(left, right);
            String t = myText.substring(right, right + 1);
            if(follows.containsKey(s)){
                follows.get(s).add(t);
            }
            else{
                ArrayList<String> temp = new ArrayList<String>();
                temp.add(t);
                follows.put(s, temp);
            }
            left++;
            right++;
        }
        follows.put(myText.substring(left, right), new ArrayList<String>());
    }
    
    protected ArrayList<String> getFollows(String key){
        return follows.get(key);
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        buildMap();
        printHashMapInfo();
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - N);
        String key = myText.substring(index, index + N);
        sb.append(key);
        //System.out.println("key : " + key);
        for(int k=0; k < numChars - N; k++){
            ArrayList<String> following = getFollows(key);
            if(following.size() == 0)
                break;
            index = myRandom.nextInt(following.size());
            String next = following.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        
        return sb.toString();
    }
    
    public int getMaxCount(){
        int maxcount = 0;
        for(String key : follows.keySet()){
            int temp = follows.get(key).size();
            if(temp > maxcount){
                maxcount = temp;
            }
        }
        return maxcount;
    }
    
    public ArrayList<String> getMaxCountKeys(int maxcount){
        ArrayList<String> list = new ArrayList<String>();
        for(String key : follows.keySet()){
            if(follows.get(key).size() == maxcount){
                list.add(key);
            }
        }
        return list;
    }
    public void printHashMapInfo(){
        //System.out.println(follows);
        System.out.println("number of keys : " + follows.size());
        int maxcount = getMaxCount();
        System.out.println("Size of largest value : " + maxcount);
        System.out.println("keys that have max value : " + getMaxCountKeys(maxcount));
    }
    public String toString(){
        return "MarkovModel of order " + N;
    }

}
