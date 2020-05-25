import java.util.*;
import edu.duke.*;
import java.io.*;
/**
 * Write a description of WordsInFiles here.
 * 
 * @author Saurabh Godse
 * @version (a version number or a date)
 */
public class WordsInFiles {
    private HashMap<String, ArrayList<String>> fileMap;
    public WordsInFiles(){
        fileMap = new HashMap<String, ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        for(String word : fr.words()){
            if(fileMap.containsKey(word)){
                
                int idx = fileMap.get(word).indexOf(f.getName());
                //System.out.println("idx : " + idx);
                if(idx == -1){
                    fileMap.get(word).add(f.getName());
                }
                
            }
            else{
                
                ArrayList<String> temp = new ArrayList<String>();
                temp.add(f.getName());
                fileMap.put(word, temp); 
            }
        }
        
    }
    
    private int maxNumber(){
        int maxfreq = 0;
        for(ArrayList<String> filelist : fileMap.values()){
            int temp = filelist.size();
            if(temp > maxfreq){
                maxfreq = temp;
            }
        }
        return maxfreq;
    }
    
    private ArrayList<String> wordsInNumFiles(int num){
        ArrayList<String> words = new ArrayList<String>();
        for(String word : fileMap.keySet()){
            if(fileMap.get(word).size() == num){
                words.add(word);
            }
        }
        return words;
    }
    
    private void printFilesIn(String word){
        if(fileMap.containsKey(word)){
            for(String fname : fileMap.get(word)){
                System.out.println(fname);
            }
            
        }
        else{
            System.out.println("word not found");
        }
    }
    
    public void tester(){
        fileMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
        //System.out.println(wordsInNumFiles(4).size());
        for(String name : fileMap.get("laid")){
            System.out.println(name);
        }
    }
}
