import java.util.*;
import edu.duke.*;

/**
 * Write a description of CodonCount here.
 * 
 * @author Saurabh Godse 
 * @version (a version number or a date)
 */
public class CodonCount {
    HashMap<String, Integer> codonFreq;
    
    public CodonCount(){
        codonFreq = new HashMap();
    }
    
    private void buildCodonMap(String dna, int start){
        int len = dna.length();

        for(int i = start; i + 2 < len; i += 3){
            String codon = dna.substring(i, i + 3);
            if(codonFreq.containsKey(codon)){
                codonFreq.put(codon, codonFreq.get(codon) + 1);
            }
            else{
                codonFreq.put(codon, 1);
            }
        }
    }
    
    private String getMostCommonCodon(){
        String mostcommon = "";
        int maxfreq = 0;
        for(String key : codonFreq.keySet()){
            int freq = codonFreq.get(key);
            if(freq > maxfreq){
                mostcommon = key;
                maxfreq = freq;
            }
        }
        return mostcommon;
    }
    
    private void printCodonCounts(int start, int end){
        for(String key : codonFreq.keySet()){
            int freq = codonFreq.get(key);
            if(start <= freq && freq <= end){
                System.out.println(key + " : " + freq);
            }
            
        }
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        String dna = fr.asString().toLowerCase();
        buildCodonMap(dna, 0);
        buildCodonMap(dna, 1);
        buildCodonMap(dna, 2);
        System.out.println("Most common : " + getMostCommonCodon());
        
    }
}
