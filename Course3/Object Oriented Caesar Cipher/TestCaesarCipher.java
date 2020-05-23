import edu.duke.*;
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author Saurabh Godse
 * @version (a version number or a date)
 */
public class TestCaesarCipher {
    public void countLetters(String s, int[] counts){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        for(int i = 0; i < s.length(); i++){
            char ch = Character.toLowerCase(s.charAt(i));
            int idx = alph.indexOf(ch);
            if(idx != -1){
                counts[idx]++;
            }
        }
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
    
    public void simpleTests(){
        FileResource fr = new FileResource();
        CaesarCipher cc = new CaesarCipher(18);
        String encrypted = cc.encrypt(fr.asString());
        System.out.println(encrypted);
        System.out.println(cc.decrypt(encrypted));
        breakCaesarCipher(encrypted);
        
    }
    
    public void breakCaesarCipher(String encrypted){
        int[] freq = new int [26];
        countLetters(encrypted, freq);
        int maxindex = indexOfMax(freq);
        int dkey = maxindex - 4;
        
        if(maxindex < 4){
            dkey = 26 - (4 - maxindex);
        }
        System.out.println("Key : " + dkey);
        CaesarCipher cc = new CaesarCipher(dkey);
        System.out.println(cc.decrypt(encrypted));
    }
}
