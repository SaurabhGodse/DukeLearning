import edu.duke.*;
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author Saurabh Godse 
 * @version (a version number or a date)
 */
public class TestCaesarCipherTwo {
    public String halfOfString(String message, int start){
        String newString = "";
        int len = message.length();
        for(int i = start; i < len; i += 2){
            newString += message.charAt(i);
        }
        return newString;
    }
    
    public String mergeString(String a, String b){
        String mergedStr = "";
        int len1 = a.length();
        int len2 = b.length();
        for(int i = 0; i < len1 + len2; i++){
            if(i % 2 == 0){
                mergedStr += a.charAt(i / 2);
            }
            else{
                mergedStr += b.charAt(i / 2);
            }
        }
        return mergedStr;
    }

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
    
    public int getKey(String encrypted){
        int[] freq = new int [26];
        countLetters(encrypted, freq);
        int maxindex = indexOfMax(freq);
        int dkey = maxindex - 4;
        
        if(maxindex < 4){
            dkey = 26 - (4 - maxindex);
        }
        return dkey;
    }
    
    public void breakCaesarCipher(String input){
        String evenstr = halfOfString(input, 0);
        String oddstr = halfOfString(input, 1);
        int key1 = getKey(evenstr);
        int key2 = getKey(oddstr);
        CaesarCipherTwo cct = new CaesarCipherTwo(key1, key2);
        System.out.println(cct.decrypt(input, key1, key2));
    }
    
    public void simpleTests(){
        //FileResource fr = new FileResource();
        CaesarCipherTwo cct = new CaesarCipherTwo(14, 24);
        String encrypted = cct.encrypt("hi");
        System.out.println(encrypted);
        System.out.println(cct.decrypt("Hfs cpwewloj loks cd Hoto kyg Cyy.", 14, 24));
        
    }

}
