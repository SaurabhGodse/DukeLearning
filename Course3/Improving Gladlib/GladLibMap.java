import java.util.*;
import edu.duke.*;
/**
 * Write a description of GladLibMap here.
 * 
 * @author Saurabh Godse
 * @version (a version number or a date)
 */
public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    private Random myRandom;
    private ArrayList<String> usedWords;
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    private HashMap<String, Integer> categoriesUsed;
    
    public GladLibMap(){
        myMap = new HashMap<String, ArrayList<String>>();
        categoriesUsed = new HashMap<String, Integer>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String[] labels = {"adjective", "noun", "color", 
                        "country", "name", "animal", 
                        "timeframe", "verb", "fruit"};
        
        for(String s : labels){
            ArrayList<String> list = readIt(source + "/" + s + ".txt");
            myMap.put(s, list);
        }
        usedWords = new ArrayList<String>();
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        else if(myMap.containsKey(label)){
            return randomFrom(myMap.get(label));
        }
        return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String category = w.substring(first+1,last);
        categoriesUsed.put(category, 0);
        String sub = getSubstitute(category);
        int idx = usedWords.indexOf(sub);
        while(idx != -1){
            sub = getSubstitute(w.substring(first+1,last));
            idx = usedWords.indexOf(sub);
        }
        usedWords.add(sub);
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    private int totalWordsInMap(){
        int total = 0;
        for(String key : myMap.keySet()){
            total += myMap.get(key).size();
        }
        return total;
    }
    
    private int totalWordsConsidered(){
        int total = 0;
        for(String category : categoriesUsed.keySet()){
            if(myMap.containsKey(category)){
                total += myMap.get(category).size();
            }

        }
        return total;
    }
    public void makeStory(){
        System.out.println("\n");
        usedWords.clear();
        String story = fromTemplate("datalong/madtemplate2.txt");
        printOut(story, 60);
    }
    


}
