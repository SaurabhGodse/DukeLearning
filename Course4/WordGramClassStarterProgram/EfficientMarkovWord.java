import java.util.*;
/**
 * Write a description of EfficientMarkovWord here.
 * 
 * @author Saurabh Godse
 * @version (a version number or a date)
 */
public class EfficientMarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<String, ArrayList<String>> myMap;
    public EfficientMarkovWord(int order){
        myRandom = new Random();
        myOrder = order;
        myMap = new HashMap<String, ArrayList<String>>();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public void buildMap(){
        for(int i = 0; i <= myText.length - myOrder; i++){
            WordGram wg = new WordGram(myText, i, myOrder);
            String key = wg.toString();
            //System.out.println("i : " + i + " key : " + key);
            if(myMap.containsKey(key)){
                if(i + myOrder < myText.length)
                    myMap.get(key).add(myText[i + myOrder]);
            }
            else{
                ArrayList<String> temp = new ArrayList<String>();
                if(i + myOrder < myText.length){
                    temp.add(myText[i + myOrder]);
                }
                myMap.put(key, temp);
            }
        }
        
    }
    
    public int getMaxCount(){
        int maxcount = 0;
        for(String key : myMap.keySet()){
            int temp = myMap.get(key).size();
            if(temp > maxcount){
                maxcount = temp;
            }
        }
        return maxcount;
    }
    
    public ArrayList<String> getMaxCountKeys(int maxcount){
        ArrayList<String> list = new ArrayList<String>();
        for(String key : myMap.keySet()){
            if(myMap.get(key).size() == maxcount){
                list.add(key);
            }
        }
        return list;
    }
    
    public void printHashMapInfo(){
        //System.out.println(myMap);
        System.out.println("Number of keys : " + myMap.size());
        int maxcount = getMaxCount();
        System.out.println("Size of largest value in map : " + maxcount);
        System.out.println("keys that have max size value : " + getMaxCountKeys(maxcount));
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
//        String key = myText[index];
        buildMap();
        printHashMapInfo();
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
        return myMap.get(kGram.toString());
    }

}
