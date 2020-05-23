import edu.duke.*;

/**
 * Write a description of WordLengths here.
 * 
 * @author Saurabh Godse
 * @version (a version number or a date)
 */
public class WordLengths {
    public void countWordLengths(FileResource resource, int [] counts){
        int len = counts.length;
        for(String word : resource.words()){
            int wordLen = word.length();
            if(!Character.isLetter(word.charAt(wordLen - 1))){
                wordLen -= 1;
            }
            if(!Character.isLetter(word.charAt(0))){
                wordLen -= 1;
            }
            if(wordLen < len){
                counts[wordLen] += 1;
            }
            else{
                counts[len - 1] += 1;
            }
            
        }
        
    }
    
    public void testCountWordLengths(){
        FileResource resource = new FileResource();
        int[] counts = new int[31];
        countWordLengths(resource, counts);
        for(int i = 0; i < 31; i++){
            System.out.println(i + " : " + counts[i]);
        }
        System.out.println("max index : " + indexOfMax(counts));
    }
    
    public int indexOfMax(int[] counts){
        int maxindex = 0;
        int maxval = 0;
        for(int i = 0; i < counts.length; i++){
            if(counts[i] > maxval){
                maxval = counts[i];
                maxindex = i;
            }
        }
        return maxindex;
    }
    

}
